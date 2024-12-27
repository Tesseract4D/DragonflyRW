package cn.tesseract.dragonfly.command;

import com.corrodinggames.rts.ally.game.class_315;

public abstract class CommandBase {
    public int args;
    public boolean requireOp;

    public CommandBase(int args, boolean requireOp) {
        this.args = args;
        this.requireOp = requireOp;
    }

    public abstract void processCommand(class_315 sender, String[] args);
}
