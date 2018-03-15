package com.craterstudio.juanfrancrater.ipewa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;

/**
 * Created by PcCom on 11/01/2018.
 */

public class SpinnerProyectAdapter  extends ArrayAdapter<String> {

        public SpinnerProyectAdapter(@NonNull Context cntx)
        {
            super(cntx,android.R.layout.simple_spinner_dropdown_item, ProjectRepository.getInstance().getNameProjects());
        }
}
