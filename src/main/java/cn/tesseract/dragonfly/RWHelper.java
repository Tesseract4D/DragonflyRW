package cn.tesseract.dragonfly;

import cn.tesseract.dragonfly.reflect.MethodAccessor;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.gameFramework.class_340;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

public class RWHelper {
    public static MethodAccessor sendSysMessage = new MethodAccessor(class_1101.class, "a", String.class, class_1054.class);

    public static class_340 getGameEngine() {
        return class_340.t();
    }

    public static class_1101 getNetworkEngine() {
        return getGameEngine().bU;
    }

    public static void setMaxPlayer(int n) {
        class_315.b(n, true);
    }

    public static void sendSysMessage(String str) {
        sendSysMessage(str, null);
    }

    public static void sendSysMessage(String str, class_1054 class_1054Var) {
        sendSysMessage.invoke(class_340.t().bU, str, class_1054Var);
    }

    public static class_315 getPlayer(int n) {
        return class_315.i(n);
    }

    public static void syncFast() {

        getNetworkEngine().bp = 3601;
        getNetworkEngine().bq = 14401;
        getNetworkEngine().br = 0;
    }
}
