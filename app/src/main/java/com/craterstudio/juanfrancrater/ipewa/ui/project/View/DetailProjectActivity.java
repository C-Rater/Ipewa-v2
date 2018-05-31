package com.craterstudio.juanfrancrater.ipewa.ui.project.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Presenter.DetailProjectPresenter;

/**
 * Muestra datos del proyecto y mediante un menu permite eliminarlo.
 * Un boton permite ver las tareas relacioadas con ese proyecto
 * Un floatingbutton permite editar el proyecto
 * @author Juan Francisco Benítez López
 * @version 0.2.0
 */
public class DetailProjectActivity extends AppCompatActivity implements ProjectContrat.DetailProject.View{

    Proyecto detailProject;
    ProjectContrat.DetailProject.Presenter presenter;
    TextView txvName,txvDescription,txvDeadLine;
    FloatingActionButton fab;
    MaterialLetterIcon icon;
    Button btnListaTareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_project);
        presenter= new DetailProjectPresenter(this);
        btnListaTareas=findViewById(R.id.btnListaTareas);

        detailProject =getIntent().getExtras().getParcelable("detailProject");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize();
        Log.d("Project",String.valueOf(detailProject.get_ID()));
        btnListaTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(DetailProjectActivity.this, KanbanActivity.class);
                Bundle bnd = new Bundle();
                bnd.putParcelable("detailProject", detailProject);
                intent.putExtras(bnd);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProjectActivity.this, EditProjectActivity.class);
                Bundle bnd = new Bundle();
                bnd.putString("editProject", detailProject.get_ID());
                intent.putExtras(bnd);
                startActivityForResult(intent,0);
            }
        });
    }

    private void initialize() {
        txvName=findViewById(R.id.txvName);
        txvName.setText(detailProject.get_name());
        txvDescription=findViewById(R.id.txvDescription);
        txvDescription.setText(detailProject.get_description());
        txvDeadLine=findViewById(R.id.txvDeadLine);
        try {
            txvDeadLine.setText(detailProject.get_deadLine().substring(8, 10) + detailProject.get_deadLine().substring(4, 8) + detailProject.get_deadLine().substring(0, 4));
        }catch (StringIndexOutOfBoundsException e){
            txvDeadLine.setText("");
        }
        fab=findViewById(R.id.fab);
        icon=findViewById(R.id.icon);
        icon.setLetter(detailProject.get_name().substring(0));
        icon.setLetterColor(Color.parseColor("white"));
        icon.setLetterSize(R.dimen.letter_icon_size);
        icon.setShapeColor(detailProject.get_color());
    }

    @Override
    protected void onResume() {
        if(detailProject!=null)
        presenter.getProject(detailProject.get_ID());
        super.onResume();
    }

    @Override
    public void reloadProject(Proyecto proyecto) {
        detailProject=proyecto;
        initialize();
    }

    @Override
    public void reloadList() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflador = getMenuInflater();
        menuInflador.inflate(R.menu.menu_project_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case (R.id.action_delete):
                presenter.deleteProject(detailProject);

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
