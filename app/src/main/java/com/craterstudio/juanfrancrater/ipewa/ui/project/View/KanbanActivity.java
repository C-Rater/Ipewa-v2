package com.craterstudio.juanfrancrater.ipewa.ui.project.View;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.View.AddMetasActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.View.EditMetaActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter.KanbanPresenter;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskTabContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.View.AddTaskActivity;
import com.craterstudio.juanfrancrater.ipewa.ui.task.View.EditTaskActivity;

import java.util.ArrayList;
import java.util.List;

import static com.craterstudio.juanfrancrater.ipewa.util.DialogUtils.DELETE;
import static com.craterstudio.juanfrancrater.ipewa.util.DialogUtils.EDIT;


public class KanbanActivity extends AppCompatActivity implements TaskTabContrat.View{
    ArrayList<Tablero> tableros;
    static ArrayList<Meta> metas;
    static ArrayList<Tarea> tareas;
    static Proyecto detailProject;
    ViewPager mViewPager;
    TabLayout tabs;
    static TaskTabContrat.presenter presenter;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailProject =getIntent().getExtras().getParcelable("detailProject");
        setContentView(R.layout.activity_kanban);
        setToolbar();
        mViewPager = findViewById(R.id.container);
        tabs =  findViewById(R.id.tabs);
        presenter=new KanbanPresenter(this);
        presenter.obtenerList(detailProject.get_ID());
        setupViewPager(mViewPager);
        tabs.setupWithViewPager(mViewPager);
        setupFabButton();
    }

    private void setupFabButton() {
        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mViewPager.getCurrentItem()!=mViewPager.getAdapter().getCount()-1) {
                    Intent intent = new Intent(KanbanActivity.this, AddTaskActivity.class);
                    intent.putExtra("idProyecto", detailProject.get_ID());
                    startActivityForResult(intent, 0);
                }else{
                    Intent intent2= new Intent(KanbanActivity.this, AddMetasActivity.class);
                    intent2.putExtra("idProyecto",detailProject.get_ID());
                    startActivityForResult(intent2,1);
                }
            }
        });
    }

    @Override
    public void reload(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Tablero> tableros) {
        this.tableros=tableros;
        this.tareas=tareas;
        this.metas=metas;
    }

    @Override
    public void restart() {
        finish();
        startActivity(getIntent());
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

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (int i=0; i<tableros.size();i++)
        adapter.addFragment(TabFragment.newInstance(tableros.get(i).get_ID()),tableros.get(i).get_name());
        adapter.addFragment(TabFragment.newInstance(-1),"Metas");
        viewPager.setAdapter(adapter);
    }
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public static class TabFragment extends Fragment {

        private static final String tabNumber = "tabNumber";

        public static TabFragment newInstance(int tabpos) {
            TabFragment fragment = new TabFragment();
            Bundle args = new Bundle();
            args.putInt(tabNumber, tabpos);
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
            ListView view = rootView.findViewById(R.id.list);
            ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1);
            if(tablero!=-1) {

                view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        CharSequence[] options = {getResources().getString(R.string.action_delete),getResources().getString(R.string.action_edit)};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DELETE)
                                {
                                    presenter.delete(tareas.get(i).get_ID(),"1");

                                }else if (which == EDIT)
                                {
                                    Intent intent = new Intent(getContext(), EditTaskActivity.class);
                                    intent.putExtra("editTask", tareas.get(i));
                                    startActivityForResult(intent,3);
                                }
                            }
                        });
                        builder.setCancelable(true)
                                .setNegativeButton(builder.getContext().getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog= builder.create();
                        alertDialog.show();
                        return true;
                    }
                });
                            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   Intent intent = new Intent(getContext(), EditTaskActivity.class);
                    intent.putExtra("editTask", tareas.get(i));
                    startActivityForResult(intent,0);
                    }
                });

                for(int i=0;i<tareas.size();i++)
                {
                    if(tareas.get(i).get_idTablero()==(tablero))
                    {
                        adapter.add(tareas.get(i));
                    }
                }

            }else{

                adapter.addAll(metas);
                view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        CharSequence[] options = {getResources().getString(R.string.action_delete),getResources().getString(R.string.action_edit)};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == DELETE)
                                {
                                    presenter.delete(metas.get(i).get_ID(),"-1");


                                }else if (which == EDIT)
                                {
                                    Intent intent = new Intent(getContext(), EditMetaActivity.class);
                                    intent.putExtra("editMeta",  metas.get(i));
                                    startActivityForResult(intent,0);
                                }
                            }
                        });

                        builder.setCancelable(true)
                                .setNegativeButton(builder.getContext().getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog= builder.create();
                        alertDialog.show();
                        return true;
                    }
                });

                view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getContext(), EditMetaActivity.class);
                    intent.putExtra("editMeta", tareas.get(i));
                    startActivityForResult(intent,0);
                    }
                });
            }
            view.setAdapter(adapter);
            return rootView;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        startActivity(getIntent());
    }
}
