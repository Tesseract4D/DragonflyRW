package cn.tesseract.dragonfly;

import org.objectweb.asm.*;

public class ModifierKiller extends ClassVisitor {
    public ModifierKiller(ClassWriter cw) {
        super(Opcodes.ASM5, cw);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return super.visitField(access & ~Opcodes.ACC_PRIVATE & ~Opcodes.ACC_PROTECTED | Opcodes.ACC_PUBLIC, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return super.visitMethod(access & ~Opcodes.ACC_PRIVATE & ~Opcodes.ACC_PROTECTED | Opcodes.ACC_PUBLIC, name, descriptor, signature, exceptions);
    }
}
