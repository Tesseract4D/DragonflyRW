package cn.tesseract.dragonfly;

import android.content.Context;
import android.os.Bundle;
import cn.tesseract.dragonfly.asm.Hook;
import com.corrodinggames.rts.ally.appFramework.MainMenuActivity;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.gameFramework.SettingsEngine;
import com.corrodinggames.rts.ally.gameFramework.e.class_916;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Hooks {
    File rwDir = new File(class_916.e("/SD/rustedWarfare"));

    @Hook
    public static void onCreate(MainMenuActivity c, Bundle bundle) {
    }

    @Hook(targetMethod = "<init>", injectOnExit = true)
    public static void init(SettingsEngine c, Context context) {
        c.teamColors = "#87ceeb,#d02013,#0463f3,#ffff40,#00ffff,#d0f8f7,#000000,#ff00ea,#ff7f18,#9368c4";
        c.teamColorsNames = "SKYBLUE,RED,BLUE,YELLOW,CYAN,WHITE,BLACK,PINK,ORANGE,PURPLE";
    }

    @Hook
    public static void b(class_1101 c, class_1054 class_1054Var, class_315 class_315Var, String str, String str2) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method m = class_1101.class.getDeclaredMethod("a", String.class, class_1054.class);
        m.setAccessible(true);
        m.invoke(c, "test " + str2, class_1054Var);
    }
}