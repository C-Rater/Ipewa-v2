package com.craterstudio.juanfrancrater.ipewa.ui.Welcome.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.AddMetasActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.contrat.WelcomeContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.presenter.WelcomePresenter;
import com.craterstudio.juanfrancrater.ipewa.ui.about.AboutActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.pref.PrefferencesActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.project.View.AddProjectActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.project.View.DetailProjectActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.task.View.AddTaskActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.task.View.EditTaskActivity;
import com.craterstudio.juanfrancrater.ipewa.util.AppPreferencesHelper;
import com.craterstudio.juanfrancrater.ipewa.util.ThisApplication;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class WelcomeActivity extends AppCompatActivity implements WelcomeContrat.View {


    private WelcomeContrat.Presenter presenter;
    static ArrayList<Meta> metas;
    static ArrayList<Tarea> tareas;
    static ArrayList<Proyecto> projects;
    static ArrayList<String> feed;
    ViewPager mViewPager;
    TabLayout tabs;
    private AppPreferencesHelper sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanban);
        FloatingActionButton fab = findViewById(R.id.fab);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.welcome));
        sharedPreferences=((ThisApplication) getApplicationContext()).getAppPreferencesHelper();
        mViewPager =  findViewById(R.id.container);
        tabs =  findViewById(R.id.tabs);
        setupViewPager(mViewPager);
        tabs.setupWithViewPager(mViewPager);
        presenter=new WelcomePresenter(this);
        if (sharedPreferences.getshowUser()) {
            String message = getString(R.string.welcome) + sharedPreferences.getCurrentUserName();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
        presenter.obtainElements(-1,-1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mViewPager.getCurrentItem())
                {
                    case 0:
                        startActivityForResult(new Intent(WelcomeActivity.this, AddProjectActivity.class),0);
                        break;
                    case 1:
                        startActivityForResult(new Intent(WelcomeActivity.this, AddTaskActivity.class),1);
                        break;
                    case 2:
                        startActivityForResult(new Intent(WelcomeActivity.this, AddMetasActivity.class),2);
                        break;
                }
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        WelcomeActivity.SectionsPagerAdapter adapter = new WelcomeActivity.SectionsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(TabFragment.newInstance(0,0),getResources().getString(R.string.projects));
        adapter.addFragment(TabFragment.newInstance(1,1),getResources().getString(R.string.tareas));
        adapter.addFragment(TabFragment.newInstance(2,2),getResources().getString(R.string.metas));

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflador = getMenuInflater();
        menuInflador.inflate(R.menu.menu_activity_welcome, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortbyDate:
                presenter.sortByDate();
                break;
            case R.id.showByDays:
                presenter.obtainElements(sharedPreferences.getsetDaysNotTask(),sharedPreferences.getsetDaysNotMeta());
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences=((ThisApplication)getApplicationContext()).getAppPreferencesHelper();
        presenter.obtainElements(sharedPreferences.getsetDaysNotTask(), sharedPreferences.getsetDaysNotMeta());
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void fillList(ArrayList<Tarea> tareas, ArrayList<Meta> metas,ArrayList<Proyecto> proyectos) {
        this.tareas=tareas;
        this.metas=metas;
        this.projects=proyectos;
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
    public static class TabFragment extends Fragment {

        private static final String tabNumber = "tabNumber";

        private static final String tipoTab = "tipo";
        private int tipo;

        public static TabFragment newInstance(int tabpos,int tipo) {
            TabFragment fragment = new TabFragment();
            Bundle args = new Bundle();
            args.putInt(tabNumber, tabpos);
            args.putInt(tipoTab,tipo);
            fragment.setArguments(args);
            return fragment;
        }

        public TabFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_kanban, container, false);
            int tablero= getArguments().getInt(tabNumber);
             tipo=getArguments().getInt(tipoTab);
            ListView view = rootView.findViewById(R.id.list);
            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(tipo==1)
                    {
                        Intent intent = new Intent(getContext(), EditTaskActivity.class);
                        intent.putExtra("editTask", tareas.get(i));
                        startActivityForResult(intent,3);
                    }else if(tipo==2){
                       // Intent intent = new Intent(getContext(), EditMetaActivity.class);
                        //intent.putExtra("editTask", tareas.get(i));
                        //startActivityForResult(intent,0);
                    } else
                    {
                        Intent intent = new Intent(getContext(), DetailProjectActivity.class);
                        intent.putExtra("detailProject",  projects.get(i));
                        startActivityForResult(intent,4);
                    }

                }
            });
            view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                    final AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                    builder.setTitle(builder.getContext().getResources().getString(R.string.titleDeleteTask));
                    builder.setMessage(builder.getContext().getResources().getString(R.string.messageDeleteTask));
                    builder.setCancelable(true);
                    builder.setPositiveButton(builder.getContext().getString(R.string.btnOK), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // presenter.delete();
                        }
                    }).setNegativeButton(builder.getContext().getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog alertDialog= builder.create();
                    alertDialog.show();
                    return true;
                }
            });
            ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1);
            if(tablero==1) {

                adapter.addAll(tareas);
            }else if(tablero==2){
                adapter.addAll(metas);

            }else {
                adapter.addAll(projects);

            }
            view.setAdapter(adapter);
            return rootView;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.obtainElements(-1, -1);
    }
}