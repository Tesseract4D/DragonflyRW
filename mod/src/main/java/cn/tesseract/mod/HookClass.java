package cn.tesseract.mod;

import cn.tesseract.dragonfly.asm.Hook;
import com.corrodinggames.rts.java.Main;

import java.io.IOException;
import java.util.logging.*;

public class HookClass {
    public static Logger log = Logger.getLogger(HookClass.class.getName());

    static {
        try {
            Handler handler = new FileHandler("dragonfly.log");
            Formatter formatter = new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getMessage() + "\n";
                }
            };

            handler.setFormatter(formatter);
            log.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}