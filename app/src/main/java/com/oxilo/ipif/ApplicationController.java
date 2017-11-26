package com.oxilo.ipif;

import android.app.Application;

/**
 * Created by nikk on 25/11/17.
 */

public class ApplicationController extends Application{

    private AppPrefs appPrefs;
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        appPrefs = AppPrefs.getComplexPreferences(getBaseContext(), "ipif prefs", MODE_PRIVATE);
    }

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    public AppPrefs getAppPrefs() {
        if(appPrefs != null) {
            return appPrefs;
        }
        return null;
    }
}
