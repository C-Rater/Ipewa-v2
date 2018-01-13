package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor.ListTaskInteractor;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.View.ListTaskActivity;

/**
 * Created by PcCom on 04/01/2018.
 */

public class ListTaskPresenter implements TaskContrat.listTask.Presenter,ListTaskInteractor.ListTaskListener {

    private TaskContrat.listTask.Interactor interactor;
    private TaskContrat.listTask.View view;

    public ListTaskPresenter(TaskContrat.listTask.View view) {
        this.view=view;
        this.interactor= new ListTaskInteractor(this);
    }

    @Override
    public void delete(Tarea tarea) {
        interactor.delete(tarea);
    }

    @Override
    public void reload() {
        view.reload();
    }
}
