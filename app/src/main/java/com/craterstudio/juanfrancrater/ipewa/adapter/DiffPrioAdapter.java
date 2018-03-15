package com.craterstudio.juanfrancrater.ipewa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.DiffPrioRepository;


/**
 * Created by PcCom on 04/01/2018.
 */

public class DiffPrioAdapter extends ArrayAdapter<String> {

        public DiffPrioAdapter(@NonNull Context cntx)
        {
        super(cntx,android.R.layout.simple_spinner_dropdown_item, DiffPrioRepository.getInstance().getLevels());

        }
}
