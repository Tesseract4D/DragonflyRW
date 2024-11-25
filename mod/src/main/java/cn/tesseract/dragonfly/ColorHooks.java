package cn.tesseract.dragonfly;

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

public class ColorHooks {
    public static String[] teamColorNames = new String[]{
            "绿",
            "红",
            "蓝",
            "黄",
            "青",
            "白",
            "黑",
            "粉",
            "橙",
            "紫",
            "天蓝",
            "梅克伦堡－什末林公国",
            "伊森堡亲王国",
            "法兰克福大公国",
            "维尔茨堡大公国",
            "巴登大公国",
            "黑森公国",
            "拿骚公国",
            "贝格大公国",
            "奥尔登堡公国",
            "威尔士",
            "爱尔兰",
            "英格兰",
            "苏格兰",
            "丹麦",
            "尼德兰",
            "符腾堡",
            "巴伐利亚",
            "汉诺威",
            "教宗",
            "托斯卡纳",
            "瑞士",
            "西班牙",
            "芬兰大公国",
            "法鲁克王朝",
            "萨丁王国",
            "西西里王国",
            "大不列颠及爱尔兰联合王国",
            "葡萄牙和阿尔加维王国",
            "瑞典王国",
            "普鲁士王国",
            "丹麦及挪威联合王国",
            "俄罗斯帝国"
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
            0xff87ceeb,
            0xFFFFDEA4,
            0xFFE9F2FF,
            0xFFCFFFA8,
            0xFFFFB747,
            0xFFFFF5B5,
            0xFFFBFF1D,
            0xFFD1BC00,
            0xFF4ED700,
            0xFFFF8E00,
            0xFF43D700,
            0xFFD7FF1B,
            0xFFFFB7E0,
            0xFF069BFF,
            0xFFFFF69C,
            0xFFFF7700,
            0xFFFFEA69,
            0xFF37DFFF,
            0xFFFFF3BB,
            0xFFFBEF00,
            0xFFFFBB0E,
            0xFFDDFF9A,
            0xFF00EBE8,
            0xFF1FBB00,
            0xFFFFC440,
            0xFFE8FF56,
            0xFF92FFE8,
            0xFFFF2F48,
            0xFF00AF26,
            0xFFFFEB55,
            0xFF656565,
            0xFFAEFFF2,
            0xFF00E460
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
        return  c.l % teamColors.length ;
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static void P(class_1101 c) {
        if (c.D) {
            int var1;
            int var2;
            int var3;
            class_315 var4;
            for (var2 = 0; var2 < class_315.f; ++var2) {
                var4 = class_315.i(var2);
                if (var4 != null) {
                    if (c.v) {
                        var4.af = 0;
                    } else if (var4.a()) {
                        var4.af = 100;
                    } else {
                        var4.af = var4.s;
                    }

                    if (var4.a()) {
                        var4.E = -1;
                    } else {
                        var3 = var4.J();
                        if (var4.D != null) {
                            var1 = var4.D;
                        } else {
                            var1 = var3;
                            if (class_1101.a(var3, (class_315) null)) {
                                var1 = -1;
                            }
                        }

                        var4.E = var1;
                    }
                }
            }

            for (var2 = 0; var2 < class_315.f; ++var2) {
                var4 = class_315.i(var2);
                if (var4 != null && var4.E == -1 && !var4.a()) {
                    var1 = 0;

                    while (true) {
                        if (var1 >= teamColorNames.length) {
                            var1 = -1;
                            break;
                        }

                        var3 = 0;

                        boolean var6;
                        while (true) {
                            if (var3 >= class_315.f) {
                                var6 = false;
                                break;
                            }

                            class_315 var5 = class_315.i(var3);
                            if (var5 != null && var5.E == var1 && !var5.a()) {
                                var6 = true;
                                break;
                            }
                            ++var3;
                        }
                        if (!var6) {
                            break;
                        }
                        ++var1;
                    }
                    var4.E = var1;
                }
            }
        }
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static int J(class_315 t) {
        byte var3 = 5;
        int var2;
        if (t.l == -1) {
            var2 = var3;
        } else {
            var2 = var3;
            if (t.l != -2) {
                var2 = t.l;
                int var1 = var2;
                if (var2 >= teamColors.length) {
                    var1 = var2 % teamColors.length;
                }

                if (class_315.c > teamColors.length) {
                    class_315 var4 = class_340.t().bU.A;
                    if (var4 != null && var4 != t && var4.I() == var1) {
                        var2 = var3;
                        if (var1 == 5) {
                            var2 = 4;
                        }

                        return var2;
                    }
                }

                var2 = var1;
            }
        }

        return var2;
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
