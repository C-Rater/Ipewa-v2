package com.craterstudio.juanfrancrater.ipewa.util;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static com.vansuita.materialabout.views.RoundedDrawable.TAG;

/**
 * Created by juanf on 13/01/2018.
 */

public class ThisApplication extends Application {
    private static FirebaseAuth mAuth;
    private static AppPreferencesHelper appPreferencesHelper;
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

    public static AppPreferencesHelper getAppPreferencesHelper()
    {
        return appPreferencesHelper;
    }

    public static Context getContext() {
        return mContext;
    }
}
