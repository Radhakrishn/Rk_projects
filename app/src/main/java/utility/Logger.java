
package utility;

import android.util.Log;

public class Logger {
    private static boolean isDebug = false;

    public static void setDebugMode(boolean mode) {
        isDebug = mode;
    }

    public static void d(String tag, String message) {
        if (isDebug)
            Log.d(tag, message);
    }

    public static void e(String tag, String message) {
        if (isDebug)
            Log.e(tag, message);
    }

    public static void v(String tag, String message) {
        if (isDebug)
            Log.v(tag, message);
    }

    public static void printStackTrace(Exception in) {
        if (isDebug)
            in.printStackTrace();
    }
}
