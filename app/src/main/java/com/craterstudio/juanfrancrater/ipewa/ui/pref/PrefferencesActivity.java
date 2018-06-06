package com.craterstudio.juanfrancrater.ipewa.ui.pref;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.Splash.SplashActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.about.AboutActivity;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;

/**
 * Activity de las preferencias
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class PrefferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        Preference button = findPreference(getString(R.string.signOut));
        Preference aboutme = (Preference) findPreference(getString(R.string.AboutMe));
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ThisApplication.getFirebase().signOut();
                AppPreferencesHelper sharedPreferences = ((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
                sharedPreferences.setRememberMe(false);
                Intent intnt = new Intent(PrefferencesActivity.this, SplashActivity.class);
                intnt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intnt);
                return true;
            }
        });
        aboutme.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(PrefferencesActivity.this, AboutActivity.class));
                return true;
            }
        });
      }


    }
