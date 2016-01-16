package com.fireball1725.firecore.helpers;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class LogHelper {
    private String modName;

    public LogHelper(String modName) {
        this.modName = modName;
    }

    public void log(Level logLevel, Object object) {
        FMLLog.log(modName, logLevel, String.valueOf(object));
    }

    public void all(Object object) {
        log(Level.ALL, object);
    }

    public void debug(Object object) {
        log(Level.DEBUG, object);
    }

    public void trace(Object object) {
        log(Level.TRACE, object);
    }

    public void fatal(Object object) {
        log(Level.FATAL, object);
    }

    public void error(Object object) {
        log(Level.ERROR, object);
    }

    public void warn(Object object) {
        log(Level.WARN, object);
    }

    public void info(Object object) {
        log(Level.INFO, object);
    }

    public void off(Object object) {
        log(Level.OFF, object);
    }
}
