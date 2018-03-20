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
    private AppPreferencesHelper appPreferencesHelper;
    private static ThisApplication mContext;
    private static FirebaseDatabase database;
    private static DatabaseReference reference;

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
        //Pruebas
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("feed");
        reference.setValue("Referencia"+ Calendar.getInstance().toString(),"Referencia");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
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
