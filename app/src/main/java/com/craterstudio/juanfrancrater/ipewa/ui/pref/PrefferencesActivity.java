package com.craterstudio.juanfrancrater.ipewa.ui.pref;


import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.Splash.SplashActivity;
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
        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ThisApplication.getFirebase().signOut();
                Intent intnt = new Intent(PrefferencesActivity.this, SplashActivity.class);
                startActivity(intnt);
                return true;
            }
        });
      }


    }
