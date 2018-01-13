package com.oyasumisoft.juanfrancrater.ipewa.ui.pref;


import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.oyasumisoft.juanfrancrater.ipewa.R;
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
      }


    }
