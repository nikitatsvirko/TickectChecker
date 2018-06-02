package by.belzhd.android.tickectchecker.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class BasePreferencesManager {

    private final SharedPreferences mSettings;

    BasePreferencesManager(final Context context, final String prefName) {
        mSettings = context.getSharedPreferences(prefName, MODE_PRIVATE);
    }

    void setString(final String key, final String value) {
        final SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    void setInt(final String key, final int value) {
        final SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    void setBoolean(final String key, final boolean value) {
        final SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    void setLong(final String key, final long value) {
        final SharedPreferences.Editor editor = mSettings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    String getString(final String key, final String defaultValue) {
        return mSettings.getString(key, defaultValue);
    }

    int getInt(final String key, final int defaultValue) {
        return mSettings.getInt(key, defaultValue);
    }

    boolean getBoolean(final String key, final boolean defaultValue) {
        return mSettings.getBoolean(key, defaultValue);
    }

    long getLong(final String key, final long defaultValue) {
        return mSettings.getLong(key, defaultValue);
    }

    void drop(final String key) {
        mSettings.edit().remove(key).commit();
    }

    public SharedPreferences getSettings() {
        return mSettings;
    }
}
