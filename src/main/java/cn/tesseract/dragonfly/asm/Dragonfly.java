package cn.tesseract.dragonfly.asm;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Dragonfly {
    public static HookClassTransformer classTransformer = new HookClassTransformer();
    public static String dir = System.getProperty("user.dir");
    public static File workDir = new File(dir);
    public static File jarFile = new File(dir, System.getProperty("java.class.path"));

    public static void main(String[] args) throws IOException {
        File apkFile = new File(workDir, "libs/bak/base.apk"),
                apkOut = new File(workDir, "libs/base.apk"),
                target = new File(workDir, "libs/bak/classes.jar"),
                out = new File(workDir, "libs/classes.jar");

        if (apkOut.exists())
            apkOut.delete();
        if (out.exists())
            out.delete();
        out.createNewFile();

        try (JarOutputStream newJar = new JarOutputStream(Files.newOutputStream(out.toPath()))) {
            ZipInputStream oldJar = new ZipInputStream(Files.newInputStream(target.toPath()));
            ZipInputStream modJar = new ZipInputStream(Files.newInputStream(jarFile.toPath()));
            Set<String> existingEntries = new HashSet<>();
            mergeJar(newJar, modJar, existingEntries);
            mergeJar(newJar, oldJar, existingEntries);
        }

        FileUtils.copyFile(apkFile, apkOut);
    }

    public static void mergeJar(JarOutputStream newJar, ZipInputStream oldJar, Set<String> existingEntries) throws IOException {
        ZipEntry entry;
        while ((entry = oldJar.getNextEntry()) != null) {
            String name = entry.getName();
            if (existingEntries.contains(name) || name.startsWith("cn/tesseract/dragonfly/asm")) {
                continue;
            }
            existingEntries.add(name);
            newJar.putNextEntry(new JarEntry(name));
            byte[] data = readEntryBytes(oldJar);
            if (name.endsWith(".class")) {
                String className = name.substring(0, name.length() - 6).replace('/', '.');
                if (className.startsWith("cn.tesseract.dragonfly.hook")) {
                    System.out.println(className);
                    classTransformer.registerHookContainer(data);
                } else {
                    ClassReader cr = new ClassReader(data);
                    ClassWriter cw = new ClassWriter(0);
                    cr.accept(new ClassVisitor(Opcodes.ASM9, cw) {
                        @Override
                        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                            return new MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                                @Override
                                public void visitLdcInsn(Object value) {
                                    super.visitLdcInsn(value);
                                }
                            };
                        }
                    }, 0);
                    data = cw.toByteArray();
                    data = classTransformer.transform(className, data);
                }
            }
            newJar.write(data);

            newJar.closeEntry();
        }
    }

    public static byte[] readEntryBytes(ZipInputStream jar) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        while ((bytesRead = jar.read(buffer)) != -1) {
            bytes.write(buffer, 0, bytesRead);
        }

        return bytes.toByteArray();
    }

    public static void dumpClassFile(byte[] bytes) {
        final String[] className = new String[1];
        ClassReader cr = new ClassReader(bytes);
        ClassVisitor cw = new ClassVisitor(Opcodes.ASM9, new ClassWriter(cr, 0)) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                className[0] = name;
                super.visit(version, access, name, signature, superName, interfaces);
            }
        };
        cr.accept(cw, 0);
        String name = className[0].substring(className[0].lastIndexOf('/') + 1);
        File file = new File(System.getProperty("user.dir") + File.separator + name + ".class");
        try {
            FileUtils.writeByteArrayToFile(file, bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
