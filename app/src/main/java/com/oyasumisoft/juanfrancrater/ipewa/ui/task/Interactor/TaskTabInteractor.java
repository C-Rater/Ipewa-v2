package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Interactor;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.MetaRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.TableroRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskTabContrat;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public class TaskTabInteractor implements TaskTabContrat.interactor {
    private final TaskTabContrat.interactorListener listener;

    public TaskTabInteractor(TaskTabContrat.interactorListener listener) {
        this.listener=listener;
    }

    @Override
    public void obtenerList(int id) {
       ArrayList tableros= TableroRepository.getInstance().getTableros(id);
       ArrayList metas= MetaRepository.getInstance().getMetasByProject(id);
       ArrayList tareas = TareaRepository.getInstance().getTareasByProjectId(id);
       listener.reload(tareas,metas,tableros);
    }

    @Override
    public void delete(int i, String tipo) {

    }
}
