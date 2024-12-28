package cn.tesseract.dragonfly.command;

import cn.tesseract.dragonfly.RWHelper;
import com.corrodinggames.rts.ally.game.class_315;

public abstract class PlayerCommand extends SingleIntegerCommand {
    public PlayerCommand(int args, boolean requireOp, String description) {
        super(args, requireOp, description);
    }

    @Override
    public void processCommand(int n) {
        class_315 player = RWHelper.getPlayer(n);
        if (player == null)
            RWHelper.sendSysMessage("该玩家不存在！");
        else
            processCommand(player);
    }

    public abstract void processCommand(class_315 player);
}
