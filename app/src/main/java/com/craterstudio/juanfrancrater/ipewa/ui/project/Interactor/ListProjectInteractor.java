package com.craterstudio.juanfrancrater.ipewa.ui.project.Interactor;

import android.os.AsyncTask;
import android.os.Parcelable;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;

import java.util.ArrayList;

/**
 * Created by PcCom on 31/12/2017.
 */

public class ListProjectInteractor implements ProjectContrat.listProject.Interactor {
    private ListProjectListener listener;

    public ListProjectInteractor(ListProjectListener listener) {
        this.listener=listener;
    }

    public interface ListProjectListener
    {
        void openEditProject(Parcelable object);

        void reloadProjects(ArrayList<Proyecto> projects);
    }
    @Override
    public void getProject(int position) {
        try {
            listener.openEditProject((Parcelable) ProjectRepository.getInstance().getProjects().get(position));
        }catch (Exception e)
        {}
    }

    @Override
    public void getProjects() {
        AsyncTask<Void,Void, ArrayList<Proyecto>> asyncTask = new AsyncTask<Void, Void, ArrayList<Proyecto>>() {

            @Override
            protected ArrayList<Proyecto> doInBackground(Void... voids) {
                return ProjectRepository.getInstance().getProjects();
            }

            @Override
            protected void onPostExecute(ArrayList<Proyecto> proyectos) {
                listener.reloadProjects(proyectos);
            }
        }.execute();

    }
}
