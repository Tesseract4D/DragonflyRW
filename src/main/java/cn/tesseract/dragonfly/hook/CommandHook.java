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

import java.util.HashMap;

public class CommandHook {
    public static HashMap<String, CommandBase> commands = new HashMap<>();

    static {
        commands.put("echo", new CommandBase(1, true, "������Ϣ�����ǽ��˰����ĵ�һ��ָ��") {
            @Override
            public void processCommand(class_315 sender, String[] args) {
                RWHelper.sendSysMessage(args[0]);
            }
        });
        commands.put("max", new SingleIntegerCommand(1, true, "�����������") {
            @Override
            public void processCommand(int n) {
                n = Math.min(n, 10);
                RWHelper.setMaxPlayer(n);
                RWHelper.sendSysMessage("�������������Ϊ " + n + " �ˣ�");
            }
        });
        commands.put("in", new SingleDoubleCommand(1, true, "�������뱶��") {
            static final MethodAccessor refreshServerInfo = new MethodAccessor(MultiplayerBattleroomActivity.class, "refreshServerInfo");

            @Override
            public void processCommand(double n) {
                RWHelper.getNetworkEngine().aA.h = (float) n;
                refreshServerInfo.invoke(MultiplayerBattleroomActivity.lastLoaded);
                RWHelper.sendSysMessage("���뱶������Ϊ " + n + " ��");
                RWHelper.sync();
            }
        });
        commands.put("sync", new CommandBase(0, true, "����ͬ��") {
            @Override
            public void processCommand(class_315 sender, String[] args) {
                RWHelper.sync();
            }
        });
        commands.put("debug", new CommandBase(0, true, "����ָ��") {

            @Override
            public void processCommand(class_315 sender, String[] args) {
                for (Object o : RWHelper.getNetworkEngine().aO) {
                    RWHelper.sendMessage((class_1054) o, "[��ʾ]", "����");
                }
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
                    RWHelper.sendSysMessage("��������ʹ�ø�ָ�", class_1054Var);
                else
                    cmd.processCommand(class_315Var, arr.length == 1 ? new String[0] : arr[1].split(" ", cmd.args));
            }
        }
    }
}
