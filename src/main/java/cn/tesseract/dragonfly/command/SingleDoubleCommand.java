package cn.tesseract.dragonfly.command;

import cn.tesseract.dragonfly.RWHelper;
import com.corrodinggames.rts.ally.game.class_315;

public abstract class SingleDoubleCommand extends CommandBase {
    public SingleDoubleCommand(int args, boolean requireOp, String description) {
        super(args, requireOp, description);
    }

    public void processCommand(class_315 sender, String[] args) {
        try {
            processCommand(Double.parseDouble(args[0]));
        } catch (NumberFormatException e) {
            RWHelper.sendSysMessage("\"" + args[0] + "\"不是正确的数字！");
        }
    }

    public abstract void processCommand(double n);
}
