package cn.tesseract.dragonfly.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class StaticMethodAccessor {
    public final Method method;

    public StaticMethodAccessor(Class<?> cls, String name, Class<?>... parameterTypes) {
        try {
            method = cls.getDeclaredMethod(name, parameterTypes);
            method.setAccessible(true);
            if (!Modifier.isStatic(method.getModifiers()))
                throw new IllegalArgumentException();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public StaticMethodAccessor(Method m) {
        method = m;
        method.setAccessible(true);
        if (!Modifier.isStatic(method.getModifiers()))
            throw new IllegalArgumentException();
    }

    public Object invoke(Object... args) {
        try {
            return method.invoke(null, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
