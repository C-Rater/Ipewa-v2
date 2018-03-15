package com.craterstudio.juanfrancrater.ipewa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Tarea;
import com.craterstudio.juanfrancrater.ipewa.data.db.Repository.TareaRepository;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Adapter del Pojo Tarea
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private ArrayList<Tarea> tareas;
    private OnItemClickListener listener;

    public TareaAdapter(OnItemClickListener listener,ArrayList tareas){
        this.listener=listener;
       this.tareas=tareas;
    }

    public interface OnItemClickListener extends Serializable{
        void onItemClick(Tarea tarea);
        void onLongClick(Tarea tarea);
    }


    public TareaAdapter getTareaAdapterSortByDiff()
    {
        tareas= TareaRepository.getInstance().getTareasSortByDif();
        return this;
    }

    public TareaAdapter getTareaByProjecto(int id)
    {
        tareas=TareaRepository.getInstance().getTareasByProjectId(id);
        return this;
    }

    public TareaAdapter getTareaAdapterSortByPrio()
    {
        tareas= TareaRepository.getInstance().getTareasSortByPri();
        return this;
    }


    @Override
    public TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflador = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflador.inflate(R.layout.item_task, null);
        TareaViewHolder tareaViewHolder =  new TareaViewHolder(view);
        return tareaViewHolder;
    }

    @Override
    public void onBindViewHolder(TareaViewHolder holder, int position) {

        holder.txtV_TareaName.setText(tareas.get(position).get_name());
        holder.txtV_DificultadTarea.setText(tareas.get(position).get_difficulty());
        holder.txtV_ImportanciaTarea.setText(tareas.get(position).get_priority());
        holder.txtV_DeadLine.setText(tareas.get(position).get_deadLine());
        holder.txt_DificultadTarea.setText(R.string.Dificultad);
        holder.txt_ImportanciaTarea.setText(R.string.Prioridad);
        holder.txt_DeadLine.setText(R.string.fechaFin);
        holder.letterIcon.setShapeColor(Color.parseColor(tareas.get(position).get_color()));
        holder.bind(tareas.get(position),listener);
    }

    @Override

    public int getItemCount() {
        return tareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {

        private TextView txtV_TareaName;
        private TextView txtV_DificultadTarea;
        private TextView txtV_ImportanciaTarea;
        private TextView txtV_DeadLine;
        private TextView txt_DificultadTarea;
        private TextView txt_ImportanciaTarea;
        private TextView txt_DeadLine;
        private MaterialLetterIcon letterIcon;

        public TareaViewHolder(View itemView) {
            super(itemView);

            txtV_TareaName = (TextView) itemView.findViewById(R.id.txtV_Name);
            txtV_DificultadTarea =(TextView) itemView.findViewById(R.id.txtV_Dificultad);
            txtV_ImportanciaTarea =(TextView) itemView.findViewById(R.id.txtV_Prioridad);
            txtV_DeadLine=(TextView) itemView.findViewById(R.id.txtV_DeadLine);
            txt_DificultadTarea =(TextView) itemView.findViewById(R.id.txt_Dificultad);
            txt_ImportanciaTarea =(TextView) itemView.findViewById(R.id.txt_Prioridad);
            txt_DeadLine=(TextView) itemView.findViewById(R.id.txt_DeadLine);
            letterIcon =(MaterialLetterIcon) itemView.findViewById(R.id.materialLetterIcon);
        }

        public void bind(final Tarea tarea, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tarea);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongClick(tarea);
                    return true;
                }

            });

        }
    }


}
