package cn.tesseract.dragonfly.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldAccessor {
    public final Field field;
    public final boolean isStatic;

    public FieldAccessor(Class<?> cls, String name) {
        try {
            field = cls.getDeclaredField(name);
            field.setAccessible(true);
            isStatic = Modifier.isStatic(field.getModifiers());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public FieldAccessor(Field f) {
        field = f;
        field.setAccessible(true);
        isStatic = Modifier.isStatic(field.getModifiers());
    }

    public Object get() {
        try {
            if (isStatic)
                return field.get(null);
            else
                throw new IllegalArgumentException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Object get(Object obj) {
        try {
            if (isStatic)
                throw new IllegalArgumentException();
            else
                return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(Object obj) {
        try {
            if (isStatic)
                field.set(null, obj);
            else
                throw new IllegalArgumentException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void set(Object instance, Object obj) {
        try {
            if (isStatic)
                throw new IllegalArgumentException();
            else
                field.set(instance, obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
