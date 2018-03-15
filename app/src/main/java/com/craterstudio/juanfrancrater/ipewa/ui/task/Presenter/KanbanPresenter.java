package com.craterstudio.juanfrancrater.ipewa.ui.task.Presenter;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskTabContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Interactor.TaskTabInteractor;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public class KanbanPresenter implements TaskTabContrat.presenter , TaskTabContrat.interactorListener{
    private final TaskTabContrat.interactor interactor;
    TaskTabContrat.View view;

    public KanbanPresenter(TaskTabContrat.View view) {
        this.view = view;
        interactor = new TaskTabInteractor(this);
    }

    @Override
    public void obtenerList(int id) {
        interactor.obtenerList(id);
    }

    @Override
    public void delete(int i,String tipo) {
        interactor.delete(i,tipo);
    }

    @Override
    public void reload(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Tablero> tableros) {
        view.reload(tareas,metas,tableros);
    }
}
