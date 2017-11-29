package com.sobolgmail.i.stanislav.testati.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Stanislav Sobol on 29.11.2017.
 * stanislav.i.sobol@gmail.com
 */

public class Logger {

    private static final String COMMON_TAG = "MyLog";
    private static final String ERROR = "MyError";
    private static final String HTTP_TAG = "MyHTTP";

    private static boolean DISABLED = false;

    public static void write(String msg) {
        if (!DISABLED && msg != null && !msg.isEmpty()) {
            Log.d(COMMON_TAG, msg);
        }
    }

    public static void writeHttp(String msg) {
        if (!DISABLED && msg != null && !msg.isEmpty()) {
            Log.d(HTTP_TAG, msg);
        }
    }

    static void writeError(Throwable throwable) {
        if (!DISABLED) {
            if (throwable != null) {
                final StringWriter trace = new StringWriter();
                throwable.printStackTrace(new PrintWriter(trace));
                final String message = (throwable.getMessage() == null) ? "no message" : throwable.getMessage();
                writeErrorMessage("Caught exception! Message: " + message + ", Stacktrace: " + trace.toString());
            }
        }
    }

    static void writeErrorMessage(String msg) {
        if (!DISABLED && !TextUtils.isEmpty(msg)) {
            Log.d(ERROR, msg);
            Log.e(ERROR, msg);
        }
    }
}