package com.craterstudio.juanfrancrater.ipewa.ui.task.Contrats;

import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tablero;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;

import java.util.ArrayList;

/**
 * Created by juanf on 25/02/2018.
 */

public interface TaskTabContrat {
    interface View{

        void reload(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Tablero> tableros);
    }
    interface presenter{
        void obtenerList(String id);

        void delete(String id, String tipo);
    }
    interface interactor{
        void obtenerList(String id);

        void delete(String id, String tipo);
    }
    interface interactorListener
    {
        void reload(ArrayList<Tarea> tareas, ArrayList<Meta> metas, ArrayList<Tablero> tableros);
    }
}
