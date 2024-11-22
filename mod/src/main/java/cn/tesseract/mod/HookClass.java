package cn.tesseract.mod;

import cn.tesseract.dragonfly.asm.Hook;

import java.io.IOException;
import java.lang.reflect.Field;
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
            log.info(e.getMessage());
        }
    }

    @Hook(targetClass = "com.corrodinggames.rts.java.Main")
    public static void main(Object c, String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field f = Class.forName("com.corrodinggames.rts.java.Main").getDeclaredField("c");
        f.set(null, "Custom Title");
        log.info(f.getName());
    }
}