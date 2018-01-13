package com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository;

import java.util.ArrayList;

/**
 * Created by PcCom on 04/01/2018.
 */

public class DiffPrioRepository {

    private static DiffPrioRepository instance;
    ArrayList<String> levels;
    private  DiffPrioRepository(){
        levels = new ArrayList<>();
        initialize();
    }

    static {
        instance=new DiffPrioRepository();
    }
    private void initialize()
    {
        levels.add("Alta");
        levels.add("Media");
        levels.add("Baja");
    }
    public static DiffPrioRepository getInstance()
    {
        return instance;
    }

    public ArrayList<String> getLevels()
    {        return levels;    }


}
