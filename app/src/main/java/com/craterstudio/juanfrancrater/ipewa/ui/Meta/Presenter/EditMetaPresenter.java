package com.craterstudio.juanfrancrater.ipewa.ui.Meta.Presenter;

import android.content.Context;

import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Contrats.MetaContrat;
import com.craterstudio.juanfrancrater.ipewa.ui.Meta.Interactor.EditMetaInteractor;

import java.util.ArrayList;

/**
 * Created by usuario on 31/05/18.
 */

public class EditMetaPresenter implements MetaContrat.editMeta.Presenter,EditMetaInteractor.EditMetaListener {

    MetaContrat.editMeta.View view;
    MetaContrat.editMeta.Interactor interactor;
    private Context context;
    public EditMetaPresenter(MetaContrat.editMeta.View view) {
        this.view=view;
        this.interactor=new EditMetaInteractor(this);
        this.context=(Context)view;
    }

    @Override
    public void back() {
        view.back();
    }

    @Override
    public void showEmptyName() {
        view.showError();
    }

    @Override
    public void fillIdList(ArrayList<Integer> idProjects) {
        view.fillIdList(idProjects);
    }

    @Override
    public void EditMeta(int id, String name, String description, int color, String deadLine, String priority, String difficulty, int _idProyecto, String creator) {
        interactor.EditMeta(id,name,description,color,deadLine,priority,difficulty,_idProyecto,creator);
    }

    @Override
    public void getIdList() {
        interactor.getIdList();
    }
}