package com.oyasumisoft.juanfrancrater.ipewa.ui.Welcome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.ui.about.AboutActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.pref.PrefferencesActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.View.ListProjectActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.View.ListTaskActivity;
import com.oyasumisoft.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.oyasumisoft.juanfrancrater.ipewa.util.ThisApplication;

/**
 * En esta version, permite acceder a un menu de preferencias
 (no funcional), a la interfaz de AboutActivity y a las listas
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnTaskList;
    Button btnProjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Welcome);
        setSupportActionBar(toolbar);
        btnTaskList = (Button) findViewById(R.id.btn_taskList);
        btnTaskList.setOnClickListener(this);
        btnProjectList = (Button) findViewById(R.id.btn_projectList);
        btnProjectList.setOnClickListener(this);

        AppPreferencesHelper sharedPreferences=((ThisApplication)getApplicationContext()).getAppPreferencesHelper();

        if(sharedPreferences.getshowUser()) {
            String message= getString(R.string.welcome)+sharedPreferences.getCurrentUserName();
            Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflador = getMenuInflater();
        menuInflador.inflate(R.menu.menu_activity_welcome, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(WelcomeActivity.this, AboutActivity.class));
                break;
            case R.id.action_pref:
                startActivity(new Intent(WelcomeActivity.this, PrefferencesActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    public void onClick(View view) {
        if(view == btnTaskList)
        {
            startActivity(new Intent(WelcomeActivity.this, ListTaskActivity.class));
        }
        if(view == btnProjectList)
        {
            startActivity(new Intent(WelcomeActivity.this, ListProjectActivity.class));
        }
    }


}