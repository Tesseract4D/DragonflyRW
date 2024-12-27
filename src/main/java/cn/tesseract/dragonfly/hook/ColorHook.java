package cn.tesseract.dragonfly.hook;

import android.widget.Spinner;
import cn.tesseract.dragonfly.asm.Hook;
import cn.tesseract.dragonfly.asm.ReturnCondition;
import com.corrodinggames.rts.ally.appFramework.MultiplayerBattleroomActivity;
import com.corrodinggames.rts.ally.appFramework.class_169;
import com.corrodinggames.rts.ally.appFramework.class_182;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.game.class_330;
import com.corrodinggames.rts.ally.gameFramework.class_340;
import com.corrodinggames.rts.ally.gameFramework.h.class_993;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;
import com.corrodinggames.rts.ally.gameFramework.m.class_1202;
import com.corrodinggames.rts.ally.gameFramework.m.class_1238;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ColorHook {
    public static String[] teamColorNames = new String[]{
            "ÂÌ",
            "ºì",
            "À¶",
            "»Æ",
            "Çà",
            "°×",
            "ºÚ",
            "·Û",
            "³È",
            "×Ï",
            "éÏé­ÂÌ",
            "Éîºì",
            "ÌìÀ¶",
            "½ðÉ«",
            "°µ×Ï",
            "ÉîÇà",
            "°µ»Ò",
            "µ­·Û",
            "Ãµ¹åºì",
            "ÁÁ»Æ",
            "ÇàÂÌ",
            "É³×Ø",
            "µ­×Ï",
            "ÇÉ¿ËÁ¦É«",
            "º£¾üÀ¶",
            "µ­À¶",
            "°µÂÌ",
            "»ÒÀ¶",
            "ÁÁ·Û",
            "»ðÑæºì",
            "²ÝÂÌ",
            "×ÏÂÞÀ¼",
            "½´É«",
            "³Èºì",
            "±¡ºÉÂÌ",
            "ÀõÉ«",
            "±¡ºÉÀ¶",
            "ÂÌËÉÊ¯",
            "¹Å¶­°×",
            "Âêè§É«",
            "Ë®Ñ¼ÂÌ",
            "öùÀæÉ«",
            "ëÙÖ¬ºì",
            "²Ô°×ÂÌ",
            "µ°¿ÇÉ«",
            "Ì¼»ÒÉ«",
            "µÙÜ½ÄáÀ¶",
            "ÔÆÄ¸É«",
            "Ý®¹û×Ï",
            "ÐÓÉ«",
            "µ°»Æ",
            "ÏÊºì",
            "ÁÁÂÌ",
            "Âêè§ºì",
            "ÁÁ×Ï",
            "²ÝÆºÂÌ",
            "µ­»Æ",
            "²ªôÞµÚºì",
            "²èÉ«",
            "Ç³ÂÌ",
            "ÇàÍ­",
            "Ìì¿ÕÀ¶",
            "ÌÒºì",
            "ÁÁ×Ø",
            "ÇàÂÌ",
            "ÄÌÓÍÉ«",
            "ºÖ",
            "Éºº÷ºì",
            "Öìºì",
            "×Ïºì",
            "Ç³À¶",
            "Éî×Ï",
            "°µÌÒÉ«",
            "½ðÍ­É«",
            "ÌÒÉ«",
            "ÌìÀ¶",
            "îÜÀ¶",
            "»ðÉ½»ÒÉ«",
            "»ÆÂÌ",
            "ÉîÇàÂÌ",
            "³È»Æ",
            "Ç³×Ï",
            "ÄûÃÊ»Æ",
            "À¶±¦Ê¯",
            "½ª»Æ",
            "Éºº÷É«",
            "ó¿ËÚºì",
            "ÆÏÌÑ×Ï",
            "µ­×Ïºì",
            "»ÃÓ°×Ï",
            "º£ÑóÀ¶",
            "Ãµ¹åÉ«",
            "¹ÅÍ­É«",
            "ÏÊÂÌ",
            "ËÉÂÌ",
            "ÔÂÁÁ»Ò",
            "ÎÚÄ¾É«",
            "ÁÁ³È",
            "ÆÏÌÑÀ¶",
            "ÐÓÈÊÉ«"
    };
    public static int[] teamColors = new int[]{
            0xff00ff00,
            0xffd02013,
            0xff0463f3,
            0xffffff40,
            0xff00ffff,
            0xffd0f8f7,
            0xff000000,
            0xffff00ea,
            0xffff7f18,
            0xff9368c4,
            0xff6b8e23,
            0xff8b0000,
            0xff87ceeb,
            0xffffd700,
            0xff800080,
            0xff008b8b,
            0xff2f4f4f,
            0xffffb6c1,
            0xffff007f,
            0xfffff200,
            0xff20b2aa,
            0xfff4a300,
            0xffe6a8d7,
            0xffd2691e,
            0xff000080,
            0xffadd8e6,
            0xff006400,
            0xff6699cc,
            0xffff69b4,
            0xfff94c2f,
            0xff7cfc00,
            0xff8a2be2,
            0xff800000,
            0xffff4500,
            0xff98ff98,
            0xff800000,
            0xffb2e0e0,
            0xff40e0d0,
            0xfffaebd7,
            0xff000000,
            0xff00b4b4,
            0xff568203,
            0xff9b111e,
            0xffb4e7c4,
            0xfff1e3c6,
            0xff36454f,
            0xff0abfbc,
            0xffd1c7b7,
            0xff8e358a,
            0xffffc8a3,
            0xffffe135,
            0xffff3f34,
            0xff32cd32,
            0xff9f1d1d,
            0xff9b30b4,
            0xff7cfc00,
            0xfffff5b7,
            0xff9e1b32,
            0xffd2691e,
            0xffb0e57c,
            0xffcd7f32,
            0xff87cefa,
            0xffff6666,
            0xff8e6e53,
            0xff00a699,
            0xfffff1cc,
            0xffa52a2a,
            0xffff6347,
            0xffd84124,
            0xff9b1b30,
            0xffadd8e6,
            0xff6a0dad,
            0xffe9b7a3,
            0xffb87333,
            0xffffcc99,
            0xff009acd,
            0xff0047ab,
            0xff595959,
            0xff9acd32,
            0xff008080,
            0xffffb84d,
            0xffb89df7,
            0xfffff700,
            0xff0f52ba,
            0xfff4a300,
            0xffff7f50,
            0xff9b111e,
            0xff6f2da8,
            0xffff66cc,
            0xff9b30b4,
            0xff1e90ff,
            0xffff007f,
            0xff7f4f24,
            0xff32cd32,
            0xffb5e1a2,
            0xffb6b6b6,
            0xff3d2b1f,
            0xffffa500,
            0xff6a5acd,
            0xfff4c2c2
    };

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static void setupPlayerColorDropDown(MultiplayerBattleroomActivity c, Spinner spinner, boolean z, boolean z2, class_315 class_315Var) {
        String str;
        int i;
        int i2;
        List<class_169> arrayList = new ArrayList<>();
        if (z) {
            arrayList.add(new class_169("-99", class_993.a("menus.settings.option.default", new Object[0]), null));
        }
        for (int i3 = 0; i3 < teamColorNames.length; i3++) {
            boolean z3 = z2 && class_1101.a(i3, class_315Var);
            String h = class_315.h(i3);
            if (h == null) {
                str = null;
            } else if (h.isEmpty()) {
                str = h.toUpperCase();
            } else {
                str = h.substring(0, 1).toUpperCase(Locale.ROOT) + h.substring(1).toLowerCase(Locale.ROOT);
            }
            if (z3) {
                str = str + " (used)";
                i2 = -7829368;
                i = -99;
            } else {
                i = i3;
                i2 = i3;
            }
            arrayList.add(new class_169(String.valueOf(i), str, class_315.g(i2)));
        }
        class_182 class_182Var = new class_182(c, arrayList);
        class_182Var.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(class_182Var);
    }

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
        return c.D == null ? c.l % teamColors.length : c.E;
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static class_1202[] a(class_315 c, class_1202 var0, class_330 var1) {
        int var2;
        class_1202[] var4;
        class_1202[] var6;
        label41:
        {
            int var3 = 0;
            var4 = new class_1202[teamColors.length];
            if (class_340.aR) {
                var2 = var3;
                if (!class_340.aT) {
                    break label41;
                }
            }

            if (var1 != class_330.e) {
                class_1202[] var5 = var0.a(var1);
                if (var5 == null) {
                    for (var2 = 0; var2 < teamColors.length; ++var2) {
                        var3 = class_315.g(var2);
                        if (var2 == 0) {
                            var4[var2] = var0;
                        } else {
                            var4[var2] = new class_1238(var0, var3, var1, var2);
                        }
                    }

                    var0.a(var1, var4);
                    var6 = var4;
                } else {
                    var6 = var5;
                }

                return var6;
            }

            var2 = var3;
        }

        while (true) {
            var6 = var4;
            if (var2 >= teamColors.length) {
                return var6;
            }

            var4[var2] = var0;
            ++var2;
        }
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static class_1202[] b(class_315 c, class_1202 var0, class_330 var1) {
        int var2;
        class_1202[] var4;
        class_1202[] var8;
        label70:
        {
            byte var3 = 0;
            var4 = new class_1202[teamColors.length];
            if (class_340.aR) {
                var2 = var3;
                if (!class_340.aT) {
                    break label70;
                }
            }

            if (var1 != class_330.e) {
                class_1202[] var5 = var0.a(var1);
                if (var5 == null) {
                    int[] var10 = new int[teamColors.length];
                    int[] var6 = new int[teamColors.length];

                    for (var2 = 0; var2 < teamColors.length; var6[var2] = var2++) {
                        var10[var2] = class_315.g(var2);
                    }

                    for (var2 = 0; var2 < teamColors.length; ++var2) {
                        if (var2 != 0) {
                            var4[var2] = var0.d();
                            class_1202 var7 = var4[var2];
                            var7.g = "color(" + var2 + "):" + var0.a();
                            var4[var2].f();
                        }
                    }

                    var0.f();
                    if (var1 == class_330.b) {
                        class_315.c(var0, var4, var10);
                    } else if (var1 == class_330.d) {
                        class_315.b(var0, var4, var10);
                    } else {
                        class_315.a(var0, var4, var10);
                    }

                    for (var2 = 0; var2 < teamColors.length; ++var2) {
                        if (var4[var2] != null) {
                            var4[var2].j();
                            class_1202 var11 = var4[var2];
                            var11.k();
                        }
                    }

                    var0.k();
                    var4[0] = var0;
                    var0.a(var1, var4);
                    var8 = var4;
                } else {
                    var8 = var5;
                }

                return var8;
            }

            var2 = var3;
        }

        while (true) {
            var8 = var4;
            if (var2 >= teamColors.length) {
                return var8;
            }

            var4[var2] = var0;
            ++var2;
        }
    }
}
