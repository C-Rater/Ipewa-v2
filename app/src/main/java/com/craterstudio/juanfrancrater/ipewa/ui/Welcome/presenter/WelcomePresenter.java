package com.craterstudio.juanfrancrater.ipewa.ui.Welcome.presenter;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.contrat.WelcomeContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.interactor.WelcomeInteractor;

import java.util.ArrayList;

/**
 * Created by usuario on 15/03/18.
 */

public class WelcomePresenter implements WelcomeContrat.Presenter,WelcomeInteractor.WelcomeListener{
    private WelcomeContrat.View view;
    private WelcomeContrat.Interactor interactor;

    public WelcomePresenter(WelcomeContrat.View view) {
        this.view = view;
        this.interactor=new WelcomeInteractor(this);
    }

    @Override
    public void obtainElements(int daysTask,int daysMeta) {
        interactor.obtainElement(daysTask,daysMeta);
    }

    @Override
    public void sortByDate() {
        interactor.sortByDate();
    }

    @Override
    public void deleteTask(int i) {
        interactor.deleteTask(i);
    }

    @Override
    public void deleteMeta(int i) {
        interactor.deleteMeta(i);
    }

    @Override
    public void fillList() {
        interactor.fillList();
    }

    @Override
    public void reloadList(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Proyecto> proyectos) {
        view.fillList(tareas,metas,proyectos);
    }

    @Override
    public void reload() {
        view.reload();
    }
}
