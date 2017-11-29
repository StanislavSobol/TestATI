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
    private static final String PUSH_TAG = "MyPush";
    private static final String SERVICE_TAG = "MyService";
    private static final String VK_TAG = "MyVK";
    private static final String VK_FIREBASE = "MyFirebase";
    /**
     * Should be disabled when unit-testing
     */
    private static boolean DISABLED = false;

    public static void write(String msg) {
        if (!DISABLED && msg != null && !msg.isEmpty()) {
            Log.d(COMMON_TAG, msg);
        }
    }

    public static void writePush(String msg) {
        if (!DISABLED && msg != null && !msg.isEmpty()) {
            Log.d(PUSH_TAG, msg);
        }
    }

    public static void writeService(String msg) {
        if (!DISABLED && msg != null && !msg.isEmpty()) {
            Log.d(SERVICE_TAG, msg);
        }
    }

    public static void writeVK(String msg) {
        if (!DISABLED && msg != null && !msg.isEmpty()) {
            Log.d(VK_TAG, msg);
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

    static void writeFirebaseAnalytics(String event) {
        if (!DISABLED && !TextUtils.isEmpty(event)) {
            Log.d(VK_FIREBASE, event);
        }
    }
}