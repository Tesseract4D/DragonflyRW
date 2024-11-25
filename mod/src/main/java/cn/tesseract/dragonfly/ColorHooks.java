package cn.tesseract.dragonfly;

import cn.tesseract.dragonfly.asm.Hook;
import cn.tesseract.dragonfly.asm.ReturnCondition;
import com.corrodinggames.rts.ally.game.class_315;

public class ColorHooks {
    public static String[] teamColorNames = new String[]{"GREEN", "RED", "BLUE", "YELLOW", "CYAN", "WHITE", "BLACK", "PINK", "ORANGE", "PURPLE"};
    public static int[] teamColors = new int[]{0xff00ff00, 0xffd02013, 0xff0463f3, 0xffffff40, 0xff00ffff, 0xffd0f8f7, 0xff000000, 0xffff00ea, 0xffff7f18, 0xff9368c4};

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static String h(class_315 c, int i2) {
        return (i2 < 0 || i2 >= teamColorNames.length) ? "GRAY" : teamColorNames[i2];
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static int g(class_315 c, int i2) {
        if (i2 >= 0 && i2 < teamColors.length) {
            return teamColors[i2];
        } else {
            return 0xff777777;
        }
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static int I(class_315 c) {
        if (c.l < 0)
            return 5;
        return c.E == -1 ? c.l % teamColors.length : c.E;
    }
}
