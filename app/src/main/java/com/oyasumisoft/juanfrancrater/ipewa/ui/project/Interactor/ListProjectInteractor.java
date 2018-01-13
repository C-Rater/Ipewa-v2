package com.oyasumisoft.juanfrancrater.ipewa.ui.project.Interactor;

import android.os.Parcelable;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.ui.project.Contrats.ProjectContrat;

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
    }
    @Override
    public void getProject(int position) {
        try {
            listener.openEditProject((Parcelable) ProjectRepository.getInstance().getProjects().get(position));
        }catch (Exception e)
        {}
    }
}
