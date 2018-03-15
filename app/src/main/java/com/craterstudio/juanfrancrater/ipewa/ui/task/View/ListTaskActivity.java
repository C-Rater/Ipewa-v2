package com.craterstudio.juanfrancrater.ipewa.ui.task.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.adapter.TareaAdapter;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Presenter.ListTaskPresenter;

import java.util.ArrayList;

/**
 * Muestra una lista de las tareas del proyectos.
 Permite ordenar según un criterio por que el otro no funciona valgame dios de encontrar el fallo.
 Permite mediante un FloatingButton añadir tareas
 Permite haciendo longclick sobre una tarea eliminarla
 Comprueba si son las tareas generales o las de un proyecto
 Comprueba si son las tareas generales o las de un proyecto
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class ListTaskActivity extends AppCompatActivity implements TaskContrat.listTask.View{
    private RecyclerView recyclerView;
    private TareaAdapter tareaAdapter;
    private TareaAdapter.OnItemClickListener listener;
    private FloatingActionButton fab;
    private TaskContrat.listTask.Presenter presenter;
    private int idProyecto=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);
        fab=findViewById(R.id.fab);

        if(getIntent().getExtras()!=null) {
            idProyecto = getIntent().getExtras().getInt("project");
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ListTaskActivity.this, AddTaskActivity.class),0);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerTask);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Task);
        setSupportActionBar(toolbar);
        presenter= new ListTaskPresenter(this);

        listener= new TareaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea) {
                Intent intent = new Intent(ListTaskActivity.this, EditTaskActivity.class);
                intent.putExtra("editTask", tarea);
                startActivityForResult(intent,0);
            }

            @Override
            public void onLongClick(final Tarea tarea) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(ListTaskActivity.this);
                builder.setTitle(builder.getContext().getResources().getString(R.string.titleDeleteTask));
                builder.setMessage(builder.getContext().getResources().getString(R.string.messageDeleteTask));
                builder.setCancelable(true);
                builder.setPositiveButton(builder.getContext().getString(R.string.btnOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delete(tarea);
                    }
                });
                builder.setNegativeButton(builder.getContext().getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog= builder.create();
                alertDialog.show();
            }
        };
       presenter.obtenerTareas();
    }
    @Override
    protected void onResume() {
        super.onResume();
       presenter.obtenerTareas();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflador = getMenuInflater();
        menuInflador.inflate(R.menu.menu_activity_task, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case (R.id.action_sortPri):
                recyclerView.setAdapter(tareaAdapter.getTareaAdapterSortByPrio());
                break;
            case (R.id.action_sortDiff):
                recyclerView.setAdapter(tareaAdapter.getTareaAdapterSortByDiff());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reload() {
        presenter.obtenerTareas();
    }

    @Override
    public void reload(ArrayList<Tarea> tareas) {
        tareaAdapter = new TareaAdapter(listener,tareas);
        recyclerView.setAdapter(tareaAdapter);
    }
}
