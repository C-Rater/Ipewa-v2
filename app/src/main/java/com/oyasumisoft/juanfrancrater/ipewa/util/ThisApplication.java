package com.oyasumisoft.juanfrancrater.ipewa.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by juanf on 13/01/2018.
 */

public class ThisApplication extends Application {
    private AppPreferencesHelper appPreferencesHelper;
    private static ThisApplication mContext;

    public ThisApplication(){
        mContext=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper= AppPreferencesHelper.getInstance();
    }

    public AppPreferencesHelper getAppPreferencesHelper()
    {
        return appPreferencesHelper;
    }

    public static Context getContext() {
        return mContext;
    }
}
