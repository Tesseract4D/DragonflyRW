package cn.tesseract.mod;

import cn.tesseract.dragonfly.asm.Hook;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookClass {
    @Hook
    public static void b(class_1101 c, class_1054 class_1054Var, class_315 class_315Var, String str, String str2) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method m = class_1101.class.getDeclaredMethod("a", String.class, class_1054.class);
        m.setAccessible(true);
        m.invoke(c, str2, class_1054Var);
    }
}