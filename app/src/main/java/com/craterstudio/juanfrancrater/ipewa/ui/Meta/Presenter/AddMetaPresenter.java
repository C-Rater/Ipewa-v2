package com.craterstudio.juanfrancrater.ipewa.ui.Meta.Presenter;

import android.content.Context;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Contrats.MetaContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Interactor.AddMetaInteractor;

import java.util.ArrayList;

/**
 * Created by usuario on 31/05/18.
 */

public class AddMetaPresenter implements MetaContrat.addMeta.Presenter, AddMetaInteractor.AddProjectListener {

    MetaContrat.addMeta.Interactor interactor;
    MetaContrat.addMeta.View view;
    Context context;
    public AddMetaPresenter(MetaContrat.addMeta.View view,Context context) {
        this.view=view;
        interactor= new AddMetaInteractor(this);
        this.context=context;
    }

    @Override
    public void back() {
        view.back();
    }

    @Override
    public void showEmptyError() {
        view.showError(context.getString(R.string.ErrorEmptyName));
    }

    @Override
    public void fillIdList(ArrayList<String> idProjects) {
        view.fillIdList(idProjects);
    }

    @Override
    public void addMeta(String nombre, String description, int color, String deadLine, String priority, String difficulty, String _idProyecto, String idTablero) {
        interactor.addMeta(nombre,description,color,deadLine,priority,difficulty,_idProyecto,idTablero);
    }

    @Override
    public void getIdList() {
        interactor.getIdList();
    }
}
