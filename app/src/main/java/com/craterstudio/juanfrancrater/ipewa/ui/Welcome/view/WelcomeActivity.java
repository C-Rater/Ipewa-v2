package com.craterstudio.juanfrancrater.ipewa.ui.Welcome.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.adapter.MetaAdapter;
import com.craterstudio.juanfrancrater.ipewa.adapter.TareaAdapter;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.contrat.WelcomeContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.presenter.WelcomePresenter;
import com.craterstudio.juanfrancrater.ipewa.ui.about.AboutActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.pref.PrefferencesActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.project.View.ListProjectActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.task.View.ListTaskActivity;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;
import com.fangxu.allangleexpandablebutton.AllAngleExpandableButton;
import com.fangxu.allangleexpandablebutton.ButtonData;
import com.fangxu.allangleexpandablebutton.ButtonEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * En esta version, permite acceder a un menu de preferencias
 (no funcional), a la interfaz de AboutActivity y a las listas
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class WelcomeActivity extends AppCompatActivity implements WelcomeContrat.View {

    RecyclerView recyclerTask;
    RecyclerView recyclerMeta;
    TareaAdapter adapterTask;
    MetaAdapter adapterMeta;
    private TareaAdapter.OnItemClickListener listenerTask;
    private MetaAdapter.OnItemClickListener listenerMeta;
    ArrayList<Tarea> tareas;
    ArrayList<Meta> metas;
    WelcomeContrat.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        AllAngleExpandableButton button = (AllAngleExpandableButton)findViewById(R.id.button_expandable);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.drawable.ic_action_pref,R.drawable.project,R.drawable.task};
        int[] color = {R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimary};
        for (int i = 0; i < drawable.length; i++) {
            ButtonData buttonData = ButtonData.buildIconButton(this, drawable[i], 0);
            buttonData.setBackgroundColorId(this, color[i]);
            buttonDatas.add(buttonData);
        }
        button.setButtonDatas(buttonDatas);
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                if(index==1)
                {
                    startActivity(new Intent(WelcomeActivity.this, ListTaskActivity.class));
                }else if(index==2)
                {
                    startActivity(new Intent(WelcomeActivity.this, ListProjectActivity.class));
                }
            }

            @Override
            public void onExpand() {

            }

            @Override
            public void onCollapse() {

            }
        });
        recyclerTask=findViewById(R.id.recyclerTask);
        recyclerMeta=findViewById(R.id.recyclerMeta);
        AppPreferencesHelper sharedPreferences=((ThisApplication)getApplicationContext()).getAppPreferencesHelper();
        presenter= new WelcomePresenter(this);
        presenter.obtainElements(sharedPreferences.getsetDaysNotTask(),sharedPreferences.getsetDaysNotMeta());
        listenerTask= new TareaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea) {
            //
            }

            @Override
            public void onLongClick(Tarea tarea) {
            //
            }
        };
        listenerMeta= new MetaAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Meta Meta) {
            //
            }

            @Override
            public void onLongClick(Meta Meta) {
            //
            }
        };
        adapterTask= new TareaAdapter(listenerTask,tareas);
        adapterMeta = new MetaAdapter(listenerMeta,metas);
        recyclerTask.setLayoutManager(new LinearLayoutManager(this));
        recyclerTask.setAdapter(adapterTask);
        recyclerTask.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerTask, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

            }
        }));
        recyclerMeta.setLayoutManager(new LinearLayoutManager(this));
        recyclerMeta.setAdapter(adapterMeta);
        recyclerMeta.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerMeta, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

            }
        }));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Welcome);
        setSupportActionBar(toolbar);



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
    public void fillList(ArrayList<Tarea> tareas, ArrayList<Meta> metas) {
        this.tareas=tareas;
        this.metas=metas;
    }
}