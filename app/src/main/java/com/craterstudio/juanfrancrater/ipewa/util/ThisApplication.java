package com.craterstudio.juanfrancrater.ipewa.util;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by juanf on 13/01/2018.
 */

public class ThisApplication extends Application {
    private static FirebaseAuth mAuth;
    private AppPreferencesHelper appPreferencesHelper;
    private static ThisApplication mContext;

    private static GoogleSignInClient mGoogleSignInClient;

    public ThisApplication(){
        mContext=this;
    }

    public static FirebaseAuth getFirebase() {
        return mAuth;
    }


    public static GoogleSignInClient getmGoogleSignInClient() {
        return mGoogleSignInClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper= AppPreferencesHelper.getInstance();
        mAuth=FirebaseAuth.getInstance();
        //GoogleSignIN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public AppPreferencesHelper getAppPreferencesHelper()
    {
        return appPreferencesHelper;
    }

    public static Context getContext() {
        return mContext;
    }
}
