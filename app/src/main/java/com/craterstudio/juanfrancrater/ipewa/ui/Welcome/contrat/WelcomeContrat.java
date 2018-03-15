package com.craterstudio.juanfrancrater.ipewa.ui.Welcome.contrat;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

public interface WelcomeContrat {
    interface View{
        void fillList(ArrayList<Tarea> tareas, ArrayList<Meta> metas);
    }
    interface Presenter{
        void obtainElements(int daysTask,int daysMeta);
    }
    interface Interactor{
        void obtainElement(int daysTask,int daysMeta);
    }
}
