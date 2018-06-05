package com.craterstudio.juanfrancrater.ipewa.ui.Welcome.contrat;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

public interface WelcomeContrat {
    interface View{
        void fillList(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Proyecto> proyectos);

        void reload();
    }
    interface Presenter{
        void obtainElements(int daysTask,int daysMeta);

        void sortByDate();

        void deleteTask(int id);

        void deleteMeta(int id);

        void fillList();

        void sortByDiff();

        void sortPrio();
    }
    interface Interactor{
        void obtainElement(int daysTask,int daysMeta);

        void sortByDate();

        void deleteTask(int id);

        void deleteMeta(int id);

        void fillList();

        void sortPrio();

        void sortByDiff();
    }
}
