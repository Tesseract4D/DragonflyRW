package cn.tesseract.dragonfly.asm;

import org.objectweb.asm.tree.ClassNode;

@FunctionalInterface
public interface NodeTransformer {
    void transform(ClassNode node);
}
