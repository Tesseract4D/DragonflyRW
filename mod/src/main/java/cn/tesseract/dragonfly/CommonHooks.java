package cn.tesseract.dragonfly;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Spinner;
import cn.tesseract.dragonfly.asm.Hook;
import cn.tesseract.dragonfly.asm.ReturnCondition;
import com.corrodinggames.rts.ally.appFramework.MainMenuActivity;
import com.corrodinggames.rts.ally.appFramework.MultiplayerBattleroomActivity;
import com.corrodinggames.rts.ally.appFramework.class_169;
import com.corrodinggames.rts.ally.appFramework.class_182;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.game.units.class_415;
import com.corrodinggames.rts.ally.gameFramework.class_328;
import com.corrodinggames.rts.ally.gameFramework.class_945;
import com.corrodinggames.rts.ally.gameFramework.class_998;
import com.corrodinggames.rts.ally.gameFramework.e.class_916;
import com.corrodinggames.rts.ally.gameFramework.f.class_1001;
import com.corrodinggames.rts.ally.gameFramework.f.class_1002;
import com.corrodinggames.rts.ally.gameFramework.f.class_960;
import com.corrodinggames.rts.ally.gameFramework.h.class_993;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;
import com.corrodinggames.rts.ally.gameFramework.m.class_1224;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommonHooks {
    public static File rwDir = new File(class_916.e("/SD/rustedWarfare"));

    //最初的加载
    @Hook
    public static void onCreate(MainMenuActivity c, Bundle bundle) {

    }

    @Hook(targetMethod = "<clinit>", injectOnExit = true)
    public static void clinit(class_315 c) {
        class_315.c = 30;
    }

    //指挥命令相关
    @Hook
    public static void a(class_1101 c, class_945 class_945Var) {

    }

    //发送聊天信息
    @Hook
    public static void b(class_1101 c, class_1054 class_1054Var, class_315 class_315Var, String str, String str2) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

    }

    @Hook(targetClass = "com.corrodinggames.rts.ally.gameFramework.m.class_1195", returnCondition = ReturnCondition.ALWAYS)
    public static void a(Object c, Canvas canvas, class_1224 class_1224Var) {
        try {
            canvas.restore();
        } catch (IllegalStateException ignored) {
        }
    }


    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static void setupSpawnPositionDropDown(MultiplayerBattleroomActivity c, Spinner spinner, boolean z) {
        List<class_169> arrayList = new ArrayList<>();
        if (z) {
            arrayList.add(new class_169("-99", class_993.a("menus.settings.option.default", new Object[0]), null));
        }
        arrayList.add(new class_169("-3", "观战", -1));

        for (int i = 0; i < 100; i++) {
            arrayList.add(new class_169(String.valueOf(i), (i + 1) + " 号 " + (i % 2 == 0 ? "A" : "B") + " 队 ", class_315.g(i % 10)));
        }

        class_182 class_182Var = new class_182(c, arrayList);
        class_182Var.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(class_182Var);
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static void setupTeamAllyDropDown(MultiplayerBattleroomActivity c, Spinner spinner, boolean z) {
        ArrayList<class_169> arrayList = new ArrayList<>();
        if (z) {
            arrayList.add(new class_169("0", "auto", -1));
        }
        for (int i = 0; i < 100; i++) {
            arrayList.add(new class_169(String.valueOf(i + 1), class_315.a(i) + " 队 ", class_315.g(i)));
        }
        class_182 class_182Var = new class_182(c, arrayList);
        class_182Var.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(class_182Var);
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static String a(class_315 c, int t) {
        return String.valueOf(t + 1);
    }

    @Hook(returnCondition = ReturnCondition.ALWAYS)
    public static void f(class_1002 c, float var1) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method a = class_1002.class.getDeclaredMethod("a", int.class, int.class, int.class, String.class, String.class, Paint.class, float.class);
        a.setAccessible(true);
        float var2 = c.b.cg;
        int var6 = (int) (c.b.cE - var2 * 30.0F);
        int var4 = (int) (c.b.ci - c.b.cn + 10.0F);
        int width = (int) (c.b.cn - 20.0F) / 3;
        int height = width - 5;
        var4 -= width * 2;

        int var5;
        for (int var3 = 0; var3 < c.aA.size(); var4 = var5) {
            class_960 var13 = (class_960) c.aA.get(var3);
            if (var13.h) {
                if (!var13.a.isEmpty()) {
                    ArrayList<class_415> var14 = new ArrayList<>();

                    for (Object o : var13.a) {
                        class_415 var15 = (class_415) o;
                        var15 = class_328.a(var15.ej, true);
                        if (var15 != null && !var15.bX) {
                            var14.add(var15);
                        }
                    }

                    var13.a = var14;
                }

                var13.h = false;
            }

            var13.c();
            if (c.b.bN.keyboardSupport && var3 < c.b.bQ.ai.length) {
                if (c.b.bQ.ak[var3].a()) {
                    var13.a.clear();
                    var13.b();
                }

                if (c.b.bQ.aj[var3].a()) {
                    c.a.e();
                    var13.a();
                }

                if (c.b.bQ.ai[var3].a()) {
                    c.a.e();
                    c.a.h();
                    var13.a();
                }
            }

            var5 = var4;
            if (c.b.bN.showUnitGroups) {
                if (var3 < 5) {
                    String var17;
                    if (var13.a.isEmpty()) {
                        if (c.a.bN) {
                            var17 = "Empty";
                        } else {
                            var17 = "(" + (var3 + 1) + ")";
                        }
                    } else {
                        var17 = "" + var13.a.size();
                    }

                    var13.d = class_998.a(var13.d, 0.01F * var1);
                    var13.e = class_998.a(var13.e, 0.01F * var1);
                    var13.f = class_998.a(var13.f, 0.01F * var1);
                    var5 = Color.argb(50, (int) (100.0F + var13.f * 100.0F), (int) (100.0F + var13.e * 100.0F), (int) (100.0F + var13.d * 100.0F));
                    class_1001 var18 = c.a;
                    int var9 = (int) (31.0F * c.b.cg);
                    boolean var11 = var18.a(var4, var6, height, var9, var17, true, var5, var18.aC, false, null);
                    boolean var16;
                    if (var11 && c.a.ac == null && !c.a.T) {
                        var13.b += var1;
                        c.a.a();
                        c.i.reset();
                        c.i.setColor(Color.argb(120, 200, 0, 0));
                        if (var13.b < 50.0F) {
                            var2 = var13.b / 50.0F;
                            c.i.setColor(Color.argb((int) (150.0F + 40.0F * var2), 0, 200, 0));
                            a.invoke(c, var4, var6, height, "Select Group", "(Hold for more..)", c.i, var2);
                        } else if (var13.b < 100.0F) {
                            var2 = (var13.b - 50.0F) / 50.0F;
                            c.i.setColor(Color.argb((int) (150.0F + 40.0F * var2), 200, 0, 0));
                            a.invoke(c, var4, var6, height, "Add to Group", "(Hold for more..)", c.i, var2);
                        } else {
                            a.invoke(c, var4, var6, height, "Replace Group", "", c.i, 0.0F);
                            var2 = 1.0F;
                        }

                        var5 = (int) (31.0F * c.b.cg);
                        c.s.set(var4, (int) ((float) (var6 + var5) - (float) var5 * var2), var4 + height, var5 + var6);
                        c.b.bL.b(c.s, c.i);
                        var16 = true;
                    } else {
                        var16 = false;
                    }

                    if (!var16) {
                        if (var13.b != 0.0F && !c.a.I) {
                            if (var13.b > 100.0F) {
                                var13.a.clear();
                                var13.b();
                                var13.f = 1.0F;
                            } else if (var13.b > 50.0F) {
                                var13.b();
                                c.a.e();
                                c.a.h();
                                var13.a();
                                var13.e = 1.0F;
                            } else if (!var13.a.isEmpty()) {
                                c.a.e();
                                c.a.h();
                                var13.a();
                                var13.d = 1.0F;
                            } else {
                                var13.a.clear();
                                var13.b();
                                var13.e = 1.0F;
                            }
                        }

                        if (!var16) {
                            var13.b = 0.0F;
                        }
                    }

                    var5 = var4 + width;
                }
            }

            ++var3;
        }
    }
}