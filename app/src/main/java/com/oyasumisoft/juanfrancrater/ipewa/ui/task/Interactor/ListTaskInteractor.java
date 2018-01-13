package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor;

import com.oyasumisoft.juanfrancrater.ipewa.adapter.TareaAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter.ListTaskPresenter;

import java.util.ArrayList;

/**
 * Created by PcCom on 04/01/2018.
 */

public class ListTaskInteractor implements TaskContrat.listTask.Interactor {
    ListTaskListener listener;
    public ListTaskInteractor(ListTaskListener listener) {
        this.listener=listener;
    }

    @Override
    public void delete(Tarea tarea) {
        TareaRepository.getInstance().deleteTask(tarea);
        listener.reload();
    }

    public interface ListTaskListener {
        void reload();
    }
}
