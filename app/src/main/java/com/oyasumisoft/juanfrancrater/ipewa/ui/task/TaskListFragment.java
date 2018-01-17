package com.oyasumisoft.juanfrancrater.ipewa.ui.task;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.adapter.TareaAdapter;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Tarea;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Contrats.TaskContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.Presenter.ListTaskPresenter;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.View.EditTaskActivity;
import com.oyasumisoft.juanfrancrater.ipewa.ui.task.View.ListTaskActivity;

/**
 * Created by PcCom on 17/01/2018.
 */

public class TaskListFragment extends Fragment implements TaskContrat.listTask.View {
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);

    }


    TareaAdapter tareaAdapter;
    RecyclerView list;
    int idProyecto=-1;
    TareaAdapter.OnItemClickListener listener;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        int position = FragmentPagerItem.getPosition(getArguments());
        list=(RecyclerView)view.findViewById(R.id.recyclerTask);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        TareaAdapter.OnItemClickListener listener;
        final TaskContrat.listTask.Presenter presenter;
        presenter= new ListTaskPresenter(this);

        listener= new TareaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tarea tarea) {
                Intent intent = new Intent(getActivity(), EditTaskActivity.class);
                intent.putExtra("editTask", tarea);
                startActivityForResult(intent,0);
            }

            @Override
            public void onLongClick(final Tarea tarea) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle(builder.getContext().getResources().getString(R.string.titleDeleteTask));
                builder.setMessage(builder.getContext().getResources().getString(R.string.messageDeleteTask));
                builder.setCancelable(true);
                builder.setPositiveButton(builder.getContext().getString(R.string.btnOK), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delete(tarea);
                    }
                });
                builder.setNegativeButton(builder.getContext().getString(R.string.btnCancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog= builder.create();
                alertDialog.show();
            }
        };

        if(getActivity().getIntent().getExtras()!=null) {
            idProyecto = getActivity().getIntent().getExtras().getInt("project");
        }
        tareaAdapter = new TareaAdapter(listener,idProyecto);
        list.setAdapter(tareaAdapter);

    }

    @Override
    public void reload() {
        tareaAdapter = new TareaAdapter(listener,idProyecto);
        list.setAdapter(tareaAdapter);
    }
}
