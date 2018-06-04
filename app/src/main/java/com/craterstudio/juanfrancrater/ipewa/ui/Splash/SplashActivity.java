package com.craterstudio.juanfrancrater.ipewa.ui.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.login.View.LoginActivity;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;


/**
 * Muestra el icono de la aplicación.
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        FirebaseUser firebaseUser = ThisApplication.getFirebase().getCurrentUser();
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            FirebaseMessaging.getInstance().subscribeToTopic("uid");
        }
                finish();
    }
}
