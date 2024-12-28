package cn.tesseract.dragonfly.hook;

import cn.tesseract.dragonfly.RWHelper;
import cn.tesseract.dragonfly.asm.Hook;
import cn.tesseract.dragonfly.command.CommandBase;
import cn.tesseract.dragonfly.command.SingleDoubleCommand;
import cn.tesseract.dragonfly.command.SingleIntegerCommand;
import cn.tesseract.dragonfly.reflect.MethodAccessor;
import com.corrodinggames.rts.ally.appFramework.MultiplayerBattleroomActivity;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHook {
    public static HashMap<String, CommandBase> commands = new HashMap<>();

    static {
        commands.put("echo", new CommandBase(1, true, "发送信息，这是结盟版加入的第一个指令") {
            @Override
            public void processCommand(class_315 sender, String[] args) {
                RWHelper.sendSysMessage(args[0]);
            }
        });
        commands.put("max", new SingleIntegerCommand(1, true, "设置最大人数") {
            @Override
            public void processCommand(int n) {
                n = Math.min(n, 10);
                RWHelper.setMaxPlayer(n);
                RWHelper.sendSysMessage("最大人数已设置为 " + n + " 人！");
            }
        });
        commands.put("in", new SingleDoubleCommand(1, true, "设置收入倍数") {
            final MethodAccessor refreshServerInfo = new MethodAccessor(MultiplayerBattleroomActivity.class, "refreshServerInfo");

            @Override
            public void processCommand(double n) {
                RWHelper.getNetworkEngine().aA.h = (float) n;
                refreshServerInfo.invoke(MultiplayerBattleroomActivity.lastLoaded);
                RWHelper.sendSysMessage("收入倍率已设为 " + n + " ！");
            }
        });
        commands.put("sync", new CommandBase(0, true, "立刻同步") {
            @Override
            public void processCommand(class_315 sender, String[] args) {
                RWHelper.sync();
            }
        });
    }

    @Hook
    public static void b(class_1101 c, class_1054 class_1054Var, class_315 class_315Var, String str, String msg) {
        boolean isHost = c.D && class_315Var == c.A;
        msg = msg.trim();
        if (msg.startsWith(".") || msg.startsWith("-")) {
            String[] arr = msg.substring(1).split(" ", 2);
            CommandBase cmd;
            if ((cmd = commands.get(arr[0])) != null) {
                if (cmd.requireOp && !isHost)
                    RWHelper.sendSysMessage("仅房主可使用该指令！", class_1054Var);
                else
                    cmd.processCommand(class_315Var, arr.length == 1 ? new String[0] : arr[1].split(" ", cmd.args));
            }
        }
    }

    public static ArrayList<String> split(String str, char c) {
        ArrayList<String> strs = new ArrayList<>();
        int l = str.length();
        int j = 0;
        for (int i = 0; i <= l; i++)
            if (i == l || str.charAt(i) == c) {
                strs.add(str.substring(j, i));
                j = i + 1;
            }
        return strs;
    }
}
