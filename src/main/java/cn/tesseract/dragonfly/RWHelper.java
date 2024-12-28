package cn.tesseract.dragonfly;

import cn.tesseract.dragonfly.reflect.MethodAccessor;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.ally.gameFramework.class_340;
import com.corrodinggames.rts.ally.gameFramework.class_945;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

import java.util.Iterator;

public class RWHelper {
    public static MethodAccessor class_1101_a = new MethodAccessor(class_1101.class, "a", String.class, class_1054.class);

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
        class_1101_a.invoke(class_340.t().bU, str, class_1054Var);
    }

    public static class_315 getPlayer(int n) {
        return class_315.i(n);
    }

    public static boolean isInGame() {
        return getNetworkEngine().aY;
    }

    public static void syncFast() {

        getNetworkEngine().bp = 3601;
        getNetworkEngine().bq = 14401;
        getNetworkEngine().br = 0;
    }

    static MethodAccessor class_1101_a1 = new MethodAccessor(class_1101.class, "a", boolean.class, boolean.class, boolean.class);
    static MethodAccessor class_1101_M = new MethodAccessor(class_1101.class, "M");

    public static void sync() {
        class_1101 c = getNetworkEngine();
        boolean z;
        boolean z2;
        synchronized (c) {
            Iterator it = c.aO.iterator();
            boolean z3 = false;
            boolean z4 = false;
            while (it.hasNext()) {
                class_1054 class_1054Var = (class_1054) it.next();
                if (class_1054Var.x) {
                    z3 = true;
                }
                if (class_1054Var.w) {
                    if (c.g) {
                        class_340.d("desync_count:" + class_1054Var.z + " lastResyncTimer:" + c.bp);
                    }
                    if (class_1054Var.z < 4 || c.bp > 3600.0f) {
                        z4 = true;
                    }
                }
            }
            if (z4) {
                boolean z5 = class_1101.c && c.bq > 5.0f;
                if (c.br == 0) {
                    z = z5;
                    if (c.bq > 60.0f) {
                        z = true;
                    }
                } else if (c.br == 1) {
                    z = z5;
                    if (c.bq > 420.0f) {
                        z = true;
                    }
                } else if (c.br == 2) {
                    z = z5;
                    if (c.bq > 3600.0f) {
                        z = true;
                    }
                } else {
                    z = z5;
                    if (c.br == 3) {
                        z = z5;
                        if (c.bq > 14400.0f) {
                            z = true;
                        }
                    }
                }
            } else {
                z = false;
            }
            if (class_1101.au && z) {
                class_340.d("disableDesyncFixing==true, running quick resync instead");
                z3 = true;
                z = false;
            }
            if (z || !z3) {
                z2 = z;
            } else {
                z2 = true;
                if (class_1101.b) {
                    if (!c.bt) {
                        class_340.d("Adding quick resync command");
                        class_340 t = class_340.t();
                        class_945 b2 = t.cc.b();
                        b2.i = class_315.i;
                        b2.s = true;
                        b2.v = 200;
                        t.bU.a(b2);
                        c.bt = true;
                    }
                    z2 = z;
                }
            }
            if (z2) {
                String str = VariableScope.nullOrMissingString;
                for (Object o : c.aO) {
                    class_1054 class_1054Var2 = (class_1054) o;
                    if (class_1054Var2.x || class_1054Var2.w) {
                        String str2 = str;
                        if (!str.equals(VariableScope.nullOrMissingString)) {
                            str2 = str + ", ";
                        }
                        str = str2 + class_1054Var2.d();
                    }
                }
                c.h("Resyncing game for " + str + "...");
                class_1101_M.invoke(c);
                class_1101_a1.invoke(c, c.l, false, true);
            }
        }

    }
}
