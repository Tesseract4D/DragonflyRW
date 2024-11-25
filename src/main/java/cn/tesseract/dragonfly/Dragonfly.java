package cn.tesseract.dragonfly;

import cn.tesseract.dragonfly.asm.HookClassTransformer;
import com.android.tools.r8.D8;
import org.apache.commons.io.FileUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Dragonfly {
    public static HookClassTransformer classTransformer = new HookClassTransformer();
    public static String dir = System.getProperty("user.dir");
    public static File workDir = new File(dir);

    public static void main(String[] args) throws IOException, InterruptedException {
        File apkFile = new File(workDir, "mod/libs/base.bak.apk"),
                apkOut = new File(workDir, "mod/libs/base.apk"),
                target = new File(workDir, "mod/libs/classes.bak.jar"),
                mod = new File(workDir, "mod/build/libs/mod-1.0-SNAPSHOT.jar"),
                out = new File(workDir, "mod/libs/classes.jar"),
                dexOut = new File(workDir, "mod/libs/classes.dex");

        if (apkOut.exists())
            apkOut.delete();
        if (out.exists())
            out.delete();
        out.createNewFile();

        try (JarOutputStream newJar = new JarOutputStream(Files.newOutputStream(out.toPath()))) {
            ZipInputStream oldJar = new ZipInputStream(Files.newInputStream(target.toPath()));
            ZipInputStream modJar = new ZipInputStream(Files.newInputStream(mod.toPath()));
            Set<String> existingEntries = new HashSet<>();
            Set<String> hookContainers = new HashSet<>();
            mergeJar(newJar, modJar, existingEntries, hookContainers, false);
            mergeJar(newJar, oldJar, existingEntries, hookContainers, true);
        }

        D8.main(new String[]{out.toPath().toString(), "--min-api", "28", "--output", dexOut.getParent()});

        FileUtils.copyFile(apkFile, apkOut);
    }

    public static void mergeJar(JarOutputStream newJar, ZipInputStream oldJar, Set<String> existingEntries, Set<String> hookContainers, boolean isTarget) throws IOException {
        ZipEntry entry;
        while ((entry = oldJar.getNextEntry()) != null) {
            String name = entry.getName();
            if (!isTarget && name.equals("META-INF/MANIFEST.MF")) {
                String[] mfs = new String(readEntryBytes(oldJar)).split("\n");
                for (String mf : mfs) {
                    String s = mf.trim();
                    int i = s.indexOf(':');
                    if (i != -1 && i < s.length() - 1) {
                        String key = s.substring(0, i).trim();
                        String value = s.substring(i + 1).trim();
                        if (key.equals("Hook-Container-Class"))
                            hookContainers.add(value);
                    }
                }
                continue;
            }
            if (existingEntries.contains(name)) {
                continue;
            }
            existingEntries.add(name);
            newJar.putNextEntry(new JarEntry(name));
            byte[] data = readEntryBytes(oldJar);
            if (name.endsWith(".class")) {
                String className = name.substring(0, name.length() - 6).replace('/', '.');
                if (hookContainers.contains(className)) {
                    classTransformer.registerHookContainer(data);
                } else {
                    data = classTransformer.transform(className, data);
                }
            }
            newJar.write(data);

            newJar.closeEntry();
        }
    }

    public static void mergeA(JarOutputStream newJar, ZipInputStream oldJar, Set<String> existingEntries, Set<String> hookContainers, boolean isTarget) throws IOException {
        ZipEntry entry;
        while ((entry = oldJar.getNextEntry()) != null) {
            String name = entry.getName();
            if (!isTarget && name.equals("META-INF/MANIFEST.MF")) {
                String[] mfs = new String(readEntryBytes(oldJar)).split("\n");
                for (String mf : mfs) {
                    String s = mf.trim();
                    int i = s.indexOf(':');
                    if (i != -1 && i < s.length() - 1) {
                        String key = s.substring(0, i).trim();
                        String value = s.substring(i + 1).trim();
                        if (key.equals("Hook-Container-Class"))
                            hookContainers.addAll(List.of(value.split(",")));
                    }
                }
                continue;
            }
            if (existingEntries.contains(name)) {
                continue;
            }
            existingEntries.add(name);
            newJar.putNextEntry(new JarEntry(name));
            byte[] data = readEntryBytes(oldJar);
            if (name.endsWith(".class")) {
                String className = name.substring(0, name.length() - 6).replace('/', '.');
                if (hookContainers.contains(className)) {
                    classTransformer.registerHookContainer(data);
                } else {
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
        ClassVisitor cw = new ClassVisitor(Opcodes.ASM5, new ClassWriter(cr, 0)) {
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
