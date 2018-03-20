package com.craterstudio.juanfrancrater.ipewa.ui.Welcome.interactor;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.MetaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.Welcome.contrat.WelcomeContrat;

import java.util.ArrayList;

/**
 * Created by usuario on 15/03/18.
 */

public class WelcomeInteractor implements WelcomeContrat.Interactor{
    WelcomeListener listener;
    public WelcomeInteractor(WelcomeListener listener) {
        this.listener=listener;
    }

    @Override
    public void obtainElement(int daysTask,int daysMeta) {
        ArrayList<Tarea> tareas;
        ArrayList<Meta> metas;
        ArrayList<Proyecto> proyectos;
       // tareas= TareaRepository.getInstance().getTareasInDays(daysTask);
        tareas=TareaRepository.getInstance().getTareas();
       // metas=MetaRepository.getInstance().getMetasInDays(daysMeta);
        metas=MetaRepository.getInstance().getMetasInDays(daysMeta);
        proyectos=ProjectRepository.getInstance().getProjects();
        listener.reloadList(tareas,metas,proyectos);

    }
    public interface WelcomeListener
    {
        void reloadList(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Proyecto> proyectos);
    }
}
