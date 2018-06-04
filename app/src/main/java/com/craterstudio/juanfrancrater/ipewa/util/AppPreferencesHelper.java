package com.craterstudio.juanfrancrater.ipewa.util;

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
    public String getCurrentUserID() {
        String name=preferences.getString(PREF_KEY_CURRENT_USER_ID, null);
        return name;
    }
    public void setCurrentUserID(String currentUserID) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_ID,currentUserID);
    }

    public int getLastIDProject() {
        int id=preferences.getInt(PREF_KEY_LAST_ID_PROJECT, 0);
        return id;
    }
    public void setLastIDProject(int i)
    {
        preferences.edit().putInt(PREF_KEY_LAST_ID_PROJECT,i);
    }

    public int getLastIDMeta() {
        int id=preferences.getInt(PREF_KEY_LAST_ID_META, 0);
        return id;
    }

    public int getLastIDTarea() {
        int id=preferences.getInt(PREF_KEY_LAST_ID_TAREA, 0);
        return id;
    }

    public void setLastIDTarea(int i) {
        preferences.edit().putInt(PREF_KEY_LAST_ID_TAREA,i);
    }

    public void setLastIdMeta(int i) {
        preferences.edit().putInt(PREF_KEY_LAST_ID_META,i);
    }

    public void setRememberMe(Boolean bool) {
        preferences.edit().putBoolean(PREF_KEY_REMEMBER_ME,bool).apply();
    }
    public boolean getRememberMe() {
        Boolean remember=preferences.getBoolean(PREF_KEY_REMEMBER_ME, false);
        return remember;
    }

    public boolean getshowUser() {
        Boolean bool=preferences.getBoolean(PREf_KEY_SHOW_USER_NAME, false);
        return bool;
    }
    public void setCurrentUserName(String name) {
        preferences.edit().putString(PREF_KEY_CURRENT_USER_NAME,name).apply();
    }
    public void setDaysNotTask(int days)
    {
        preferences.edit().putInt(PREF_KEY_DAYS_NOTIFICATION_TASK,days).apply();
    }
    public int getsetDaysNotTask()
    {
        int days=preferences.getInt(PREF_KEY_DAYS_NOTIFICATION_TASK,7);
        return days;
    }
    public void setDaysNotMeta(int days)
    {
        preferences.edit().putInt(PREF_KEY_DAYS_NOTIFICATION_META,days).apply();
    }
    public int getsetDaysNotMeta()
    {
        int days=preferences.getInt(PREF_KEY_DAYS_NOTIFICATION_META,7);
        return days;
    }

}
