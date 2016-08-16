package kr.co.mvp.dagger2.sample.dagger.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    //프리퍼런스 KEY값
    public final String TEACHER_PREFERENCE = "kyowon_preference";
    private PreferenceUtil kpointPreferencemodule = null;
    private Context mContext;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public PreferenceUtil(Context context) {
        mContext = context;
        if (prefs == null) {
            prefs = mContext.getSharedPreferences(TEACHER_PREFERENCE, Context.MODE_PRIVATE);
            editor = prefs.edit();
        }
    }


    public void putIntExtra(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putStringExtra(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void putLongExtra(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public void putBooleanExtra(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }


    public int getIntExtra(String key) {
        return prefs.getInt(key, 0);
    }

    public String getStringExtra(String key) {
        return prefs.getString(key, "");
    }


    public long getLongExtra(String key) {
        return prefs.getLong(key, 0);
    }


    public boolean getBooleanExtra(String key) {
        return prefs.getBoolean(key, false);
    }

    public void removePreference(String key) {
        editor.remove(key).commit();
    }

    public boolean containCheck(String key) {
        return prefs.contains(key);
    }

}
