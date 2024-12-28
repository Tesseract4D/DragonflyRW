package cn.tesseract.dragonfly;

import cn.tesseract.dragonfly.reflect.MethodAccessor;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.gameFramework.class_340;
import com.corrodinggames.rts.ally.gameFramework.j.class_1042;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

public class RWHelper {
    public static MethodAccessor class_1101_a = new MethodAccessor(class_1101.class, "a", String.class, class_1054.class);
    public static MethodAccessor class_1101_a1 = new MethodAccessor(class_1101.class, "a", class_1054.class, int.class, String.class, String.class);

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
        class_1101_a.invoke(getNetworkEngine(), str, class_1054Var);
    }

    public static class_315 getPlayer(int n) {
        return class_315.i(n);
    }

    public static boolean isInGame() {
        return getNetworkEngine().aY;
    }

    public static void syncFast() {

    }

    public static void sync() {
        class_1101 c = getNetworkEngine();
        for (Object o : c.aO) {
            class_1054 p = (class_1054) o;
            p.x = p.w = true;
        }
    }

    public static void sendMessage(class_1054 con, String sender, String msg, int color) {
        class_1042 pk = new class_1042();
        pk.b(msg);
        pk.b(3);
        pk.a(sender);
        pk.a(con);
        pk.c(color);
        con.a(pk.a(141));
    }

    public static void receiveMessage(String sender, String msg, int color) {
        class_1101_a1.invoke(getNetworkEngine(), null, color, sender, msg);
    }

    public static void sendMessage(class_1054 con, String sender, String msg) {
        sendMessage(con, sender, msg, -1);
    }
}
