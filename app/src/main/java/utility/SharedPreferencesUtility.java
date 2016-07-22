package utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Wrapper class for SharedPreferences.

 */
public class SharedPreferencesUtility {

    private static final String FILE_NAME = "AARP Now";

    /**
     * This method will save some boolean value in Shared Preference.
     *
     * @param context Context
     * @param key     Key name
     * @param value   Value to be saved against the specified key name.
     */
    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * This method will return the boolean value stored in Shared Preference.
     *
     * @param context Context
     * @param key     Key name whose value to be returned.
     * @return Value of the key name specified.
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    /**
     * This method will save some String value in Shared Preference.
     *
     * @param context Context
     * @param key     Key name
     * @param value   Value to be saved against the specified key name.
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * This method will return the String value stored in Shared Preference.
     *
     * @param context Context
     * @param key     Key name whose value to be returned.
     * @return Value of the key name specified.
     */
    public static String getString(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }


    /**
     * This method will save some Integer value in Shared Preference.
     *
     * @param context Context
     * @param key     Key name
     * @param value   Value to be saved against the specified key name.
     */
    public static void setInteger(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * This method will return the Integer value stored in Shared Preference.
     *
     * @param context Context
     * @param key     Key name whose value to be returned.
     * @return Value of the key name specified.
     */
    public static long getLong(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getLong(key, -1);
    }


    /**
     * This method will save some Integer value in Shared Preference.
     *
     * @param context Context
     * @param key     Key name
     * @param value   Value to be saved against the specified key name.
     */
    public static void setLong(Context context, String key, long value) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * This method will return the Integer value stored in Shared Preference.
     *
     * @param context Context
     * @param key     Key name whose value to be returned.
     * @return Value of the key name specified.
     */
    public static int getInteger(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, -1);
    }

    /**
     * This method will return the set value stored in Shared Preference.
     *
     * @param context Context
     * @param key     Key name whose value to be returned.
     * @return Value of the key name specified.
     */

    public static Set<String> getStringSet(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getStringSet(key, null);
    }




    /**
     * This method will save some set value in Shared Preference.
     *
     * @param context    Context
     * @param key        Key name
     * @param stringList Value to be saved against the specified key name.
     */

    public static void setStringSet(Context context, String key, List<String> stringList) {
        Set<String> set = new HashSet<>();
        set.addAll(stringList);
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, set);
        editor.apply();
    }



    public static void remove(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void clear(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }

}
