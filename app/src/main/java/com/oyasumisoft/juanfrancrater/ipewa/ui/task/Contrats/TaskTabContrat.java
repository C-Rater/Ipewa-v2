package com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Meta;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tablero;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public interface TaskTabContrat {
    interface View{

        void reload(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Tablero> tableros);
    }
    interface presenter{
        void obtenerList(int id);

        void delete(int i, String tipo);
    }
    interface interactor{
        void obtenerList(int id);

        void delete(int i, String tipo);
    }
    interface interactorListener
    {
        void reload(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Tablero> tableros);
    }
}
