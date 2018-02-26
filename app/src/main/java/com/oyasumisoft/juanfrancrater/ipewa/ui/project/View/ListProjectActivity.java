package com.oyasumisoft.juanfrancrater.ipewa.ui.project.View;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.ProjectAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Presenter.ListProjectPresenter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Muestra una lista de los proyectos del sistema. Si se gira la
 Pantalla, además mostrara parte de la descripción del proyecto.
 Permite acceder a una pantalla de detalle y a añadir Proyectos
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */

public class ListProjectActivity extends AppCompatActivity implements ProjectContrat.listProject.View{

    private ProjectAdapter adapter;
    private ListView listView;
    private FloatingActionButton fab;
    private CoordinatorLayout cord;
    private ProjectContrat.listProject.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_project);
        listView = (ListView) findViewById(android.R.id.list);
        presenter = new ListProjectPresenter(this);
        cord = findViewById(R.id.cordL);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        adapter = new ProjectAdapter(this);
        presenter.getProjects();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.getProjectAtPosition(position);
            }

        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ListProjectActivity.this, AddProjectActivity.class), 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter= new ProjectAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void recargarProjects(ArrayList<Proyecto> proyectos) {
        adapter.addAll(proyectos);
        listView.setAdapter(adapter);
    }

    @Override
    public void openDetailProject(Parcelable object) {
        Intent intent = new Intent(ListProjectActivity.this, DetailProjectActivity.class);
        intent.putExtra("detailProject",  object);
        startActivity(intent);
    }
}
