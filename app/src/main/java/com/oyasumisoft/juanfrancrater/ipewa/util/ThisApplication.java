package com.oyasumisoft.juanfrancrater.ipewa.util;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by juanf on 13/01/2018.
 */

public class ThisApplication extends Application {
    private static FirebaseAuth mAuth;
    private AppPreferencesHelper appPreferencesHelper;
    private static ThisApplication mContext;

    public ThisApplication(){
        mContext=this;
    }

    public static FirebaseAuth getFirebase() {
        return mAuth;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper= AppPreferencesHelper.getInstance();
        mAuth=FirebaseAuth.getInstance();
    }

    public AppPreferencesHelper getAppPreferencesHelper()
    {
        return appPreferencesHelper;
    }

    public static Context getContext() {
        return mContext;
    }
}
