package com.oyasumisoft.juanfrancrater.ipewa.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by juanf on 13/01/2018.
 */

public class AppPreferencesHelper implements GeneralPreferencesHelper {
    private static final String TAG = "AppPreferencesHelper";

    /**
     * 1) Se define todas las Key posibles del fichero preferences
     */
    public interface AppPreferencesListerner
    {
        void onSharedPrerenceChange();
    }

    //2. Objeto para editar las preferencias
    private final SharedPreferences preferences;
    private static AppPreferencesHelper instance;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;



    private AppPreferencesHelper() {
        //Si es el fichero por defecto de las preferencias
        this.preferences = PreferenceManager.getDefaultSharedPreferences(ThisApplication.getContext());
        listener= new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                ThisApplication.getContext();
            }
        };
        //Si es un fichero con nombre diferente
    }

    public static AppPreferencesHelper getInstance()
    {
        if(instance==null)
        {
            instance=new AppPreferencesHelper();
        }
        return instance;
    }

    /**
     * ID SQLite
     * @return
     */

    public String getCurrentUserName() {
        String name=preferences.getString(PREF_KEY_CURRENT_USER_NAME, null);
        return name;
    }

    public boolean getshowUser() {
        Boolean bool=preferences.getBoolean(PREf_KEY_SHOW_USER_NAME, false);
        return bool;
    }
    public void setCurrentUserName(String name) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_NAME,name).apply();
    }


}
