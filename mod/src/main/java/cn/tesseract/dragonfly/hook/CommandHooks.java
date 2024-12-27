package cn.tesseract.dragonfly.hook;

import cn.tesseract.dragonfly.RWUtils;
import cn.tesseract.dragonfly.asm.Hook;
import cn.tesseract.dragonfly.command.CommandBase;
import com.corrodinggames.rts.ally.game.class_315;
import com.corrodinggames.rts.ally.gameFramework.j.class_1054;
import com.corrodinggames.rts.ally.gameFramework.j.class_1101;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHooks {
    public static HashMap<String, CommandBase> commands = new HashMap<>();

    static {
        commands.put("echo", new CommandBase(1, false) {
            @Override
            public void processCommand(class_315 sender, String[] args) {
                RWUtils.sendSysMessage(args[0]);
            }
        });
    }

    @Hook
    public static void b(class_1101 c, class_1054 class_1054Var, class_315 class_315Var, String str, String msg) {
        msg = msg.trim();
        if (msg.startsWith(".") || msg.startsWith("-")) {
            String[] arr = msg.substring(1).split(" ", 2);
            CommandBase cmd;
            if ((cmd = commands.get(arr[0])) != null) {
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
