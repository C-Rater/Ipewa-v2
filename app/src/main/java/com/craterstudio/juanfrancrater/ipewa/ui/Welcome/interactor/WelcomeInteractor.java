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
        proyectos=ProjectRepository.getInstance().getProjects();
        if(daysMeta!=-1&&daysTask!=-1)
        {
            tareas= TareaRepository.getInstance().getTareasInDays(daysTask);
            metas=MetaRepository.getInstance().getMetasInDays(daysMeta);
        }else{
            tareas=TareaRepository.getInstance().getTareas();
            metas=MetaRepository.getInstance().getMetas();

        }
        listener.reloadList(tareas,metas,proyectos);


    }

    @Override
    public void sortByDate() {
        ArrayList<Tarea> tareas=TareaRepository.getInstance().getTareasSortByDate();
        ArrayList<Meta> metas=MetaRepository.getInstance().getMetasSortByDate();

        ArrayList<Proyecto> proyectos=ProjectRepository.getInstance().getProjects();
        listener.reloadList(tareas,metas,proyectos);
    }

    @Override
    public void deleteTask(int i) {
        TareaRepository.getInstance().deleteTask(i);
       listener.reload();
    }

    @Override
    public void deleteMeta(int i) {
        MetaRepository.getInstance().delete(i);
        listener.reload();
    }

    @Override
    public void fillList() {
        listener.reloadList(TareaRepository.getInstance().getTareas(),MetaRepository.getInstance().getMetas(),ProjectRepository.getInstance().getProjects());
    }

    @Override
    public void sortPrio() {
        listener.reloadList(TareaRepository.getInstance().getTareasSortByPri(),MetaRepository.getInstance().getMetasByPri(),ProjectRepository.getInstance().getProjects());
    }

    @Override
    public void sortByDiff() {
        listener.reloadList(TareaRepository.getInstance().getTareasSortByDif(),MetaRepository.getInstance().getMetasSortByDif(),ProjectRepository.getInstance().getProjects());
    }

    public interface WelcomeListener
    {
        void reloadList(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Proyecto> proyectos);

        void reload();
    }
}
