package com.appcare.followconnect.Common;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static final String SETTINGS_NAME = "Follow_and_Connect";
    private static AppPreference sSharedPrefs;
    private SharedPreferences mPref;

    private AppPreference(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static AppPreference getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new AppPreference(context.getApplicationContext());
        }
        return sSharedPrefs;
    }


    public static AppPreference getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }

        //Option 1:
        throw new IllegalArgumentException("Should use getInstance(Context) at least once before using this method.");

        //Option 2:
        // Alternatively, you can create a new instance here
        // with something like this:
        // getInstance(MyCustomApplication.getAppContext());
    }

    public void put(String key, String val) {
        SharedPreferences.Editor mEditor = mPref.edit();
        mEditor.putString(key, val);
        mEditor.commit();
    }

    public void put(String key, Integer val) {
        SharedPreferences.Editor mEditor = mPref.edit();
        mEditor.putInt(key, val);
        mEditor.commit();
    }

    public void put(String key, boolean val) {
        SharedPreferences.Editor mEditor = mPref.edit();
        mEditor.putBoolean(key, val);
        mEditor.commit();
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

   /* public boolean setBoolean(String key) {
        return mPref.put(key, false);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.isfromRegister, true);
        editor.putString(Constants.userName, email);
        editor.putString(Constants.userPassword, password);
        editor.apply();


    }
*/
    public String getString(String key) {
        return mPref.getString(key, null);
    }

    public Integer getInteger(String key) { return mPref.getInt(key, 0); }


}
