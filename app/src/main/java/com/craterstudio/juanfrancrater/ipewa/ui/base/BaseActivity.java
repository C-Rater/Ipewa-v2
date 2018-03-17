package com.craterstudio.juanfrancrater.ipewa.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Usuarios;

/**
 * Created by juanf on 17/03/2018.
 */

public class BaseActivity extends AppCompatActivity {

        private DrawerLayout drawL_base;
        private NavigationView navigationView;
        private Toolbar toolbar;
        private TextView txtV_username;
        private TextView txtV_email;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_base);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_home);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            drawL_base = (DrawerLayout) findViewById(R.id.drawL_base);

            navigationView = (NavigationView) findViewById(R.id.navigationView);
            setupNavigationView();
        }

        private void setupNavigationView() {

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.action_task:
                            showTask();
                            break;
                        case R.id.action_agenda:
                            showAgenda();
                            break;
                        case R.id.action_projects:
                            showProjects();
                            break;
                        case R.id.action_settings:
                            closeDrawerAndChangeTitle(item);
                            showSettings();
                            break;
                        case R.id.action_about:
                            closeDrawerAndChangeTitle(item);
                            showAbout();
                            break;
                    }
                    item.setChecked(true);

                    return true;
                }
            });
        }
    private void showProjects()
    {}
    private void showAgenda() {
    }

    private void showTask() {
    }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()) {
                case android.R.id.home:
                    drawL_base.openDrawer(GravityCompat.START);
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onBackPressed() {
            if (drawL_base.isDrawerOpen(GravityCompat.START))
                drawL_base.closeDrawer(GravityCompat.START);
            else
                super.onBackPressed();
        }

        private void closeDrawerAndChangeTitle(MenuItem item) {
            getSupportActionBar().setTitle(item.getTitle());
            drawL_base.closeDrawer(GravityCompat.START);
        }

        private void showSettings() {
        }

        private void showAbout() {
        }

        public void setDataToNavigationDrawer(Usuarios user) {
            txtV_email = (TextView) findViewById(R.id.txtV_email);
            txtV_username = (TextView) findViewById(R.id.txtV_username);
            txtV_username.setText(user.get_name());
            txtV_email.setText(user.get_email());
        }
    }
