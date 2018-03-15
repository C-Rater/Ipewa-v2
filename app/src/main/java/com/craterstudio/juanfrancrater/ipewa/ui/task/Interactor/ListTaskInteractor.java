package com.craterstudio.juanfrancrater.ipewa.ui.task.Interactor;

import android.os.AsyncTask;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.TareaRepository;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;

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

    @Override
    public void obtenerTareas() {
        AsyncTask<Void,Void, ArrayList<Tarea>> asyncTask = new AsyncTask<Void, Void, ArrayList<Tarea>>() {

            @Override
            protected ArrayList<Tarea> doInBackground(Void... voids) {
                return TareaRepository.getInstance().getTareas();
            }

            @Override
            protected void onPostExecute(ArrayList<Tarea> tareas) {
                listener.obtenerTareas(tareas);
            }
        }.execute();
    }

    public interface ListTaskListener {
        void reload();
        void obtenerTareas(ArrayList<Tarea> tareas);
    }
}
