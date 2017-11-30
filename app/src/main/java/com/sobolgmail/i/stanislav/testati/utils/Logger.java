package com.sobolgmail.i.stanislav.testati.utils;

import android.text.TextUtils;
import android.util.Log;

import com.sobolgmail.i.stanislav.testati.BuildConfig;

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
        if (isEnabled() && !StringUtils.isEmpty(msg)) {
            Log.d(COMMON_TAG, msg);
        }
    }

    public static void writeHttp(String msg) {
        if (isEnabled() && msg != null && !msg.isEmpty()) {
            Log.d(HTTP_TAG, msg);
        }
    }

    public static void writeError(Throwable throwable) {
        if (isEnabled()) {
            if (throwable != null) {
                final StringWriter trace = new StringWriter();
                throwable.printStackTrace(new PrintWriter(trace));
                final String message = (throwable.getMessage() == null) ? "no message" : throwable.getMessage();
                writeErrorMessage("Caught exception! Message: " + message + ", Stacktrace: " + trace.toString());
            }
        }
    }

    private static void writeErrorMessage(String msg) {
        if (isEnabled() && !TextUtils.isEmpty(msg)) {
            Log.d(ERROR, msg);
            Log.e(ERROR, msg);
        }
    }

    private static boolean isEnabled() {
        return BuildConfig.DEBUG;
    }
}