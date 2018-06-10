package com.project.cryptonews.utils.logger;

import android.util.Log;

/**
 * Logger class to log different levels of information such as informational,
 * debug, and what a terrible failure.
 */

public class Logger {

    public static void log(String name, String format, Object... args) {
        Log.i(name, String.format(format, args));
    }

    public static void debug(String c, String format, Object... args) {
        Log.d(c, String.format(format, args));
    }

    public static void wtf(String c, String format, Object... args) {
        Log.wtf(c, String.format(format, args));
    }
}
