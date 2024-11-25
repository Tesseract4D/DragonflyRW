package cn.tesseract.dragonfly;

import com.corrodinggames.rts.ally.gameFramework.class_340;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RWUtils {
    public static Method sendSysMessage;

    static {
        try {
            sendSysMessage = class_1101.class.getDeclaredMethod("a", String.class, class_1054.class);
            sendSysMessage.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendSysMessage(String str) {
        sendSysMessage(str, null);
    }

    public static void sendSysMessage(String str, class_1054 class_1054Var) {
        try {
            sendSysMessage.invoke(class_340.t().bU, str, class_1054Var);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
