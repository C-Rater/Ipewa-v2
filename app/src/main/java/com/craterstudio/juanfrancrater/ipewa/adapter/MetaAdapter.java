package com.craterstudio.juanfrancrater.ipewa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.craterstudio.juanfrancrater.ipewa.R;
import com.craterstudio.juanfrancrater.ipewa.data.db.model.Meta;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by usuario on 15/03/18.
 */

public class MetaAdapter extends RecyclerView.Adapter<MetaAdapter.MetaViewHolder> {

    private ArrayList<Meta> Metas;
    private OnItemClickListener listener;

    public MetaAdapter(OnItemClickListener listener,ArrayList Metas){
        this.listener=listener;
        this.Metas=Metas;
    }

    public interface OnItemClickListener extends Serializable {
        void onItemClick(Meta Meta);
        void onLongClick(Meta Meta);
    }


    public MetaAdapter getMetaAdapterSortByDiff()
    {
     //   Metas= MetaRepository.getInstance().getMetasSortByDif();
        return this;
    }

    public MetaAdapter getMetaByProjecto(int id)
    {
        //Metas=MetaRepository.getInstance().getMetasByProjectId(id);
        return this;
    }

    public MetaAdapter getMetaAdapterSortByPrio()
    {
        //Metas= MetaRepository.getInstance().getMetasSortByPri();
        return this;
    }


    @Override
    public MetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflador = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflador.inflate(R.layout.item_meta, null);
        MetaViewHolder MetaViewHolder =  new MetaViewHolder(view);
        return MetaViewHolder;
    }

    @Override
    public void onBindViewHolder(MetaViewHolder holder, int position) {

        holder.txtV_MetaName.setText(Metas.get(position).get_name());
        holder.letterIcon.setShapeColor(Color.parseColor(Metas.get(position).get_color()));
        holder.bind(Metas.get(position),listener);
    }

    @Override

    public int getItemCount() {
        if(Metas==null)
            return 0;
        return Metas.size();
    }

    public static class MetaViewHolder extends RecyclerView.ViewHolder {

        private TextView txtV_MetaName;

        private MaterialLetterIcon letterIcon;

        public MetaViewHolder(View itemView) {
            super(itemView);

            txtV_MetaName = (TextView) itemView.findViewById(R.id.txtV_Name);
            letterIcon =(MaterialLetterIcon) itemView.findViewById(R.id.materialLetterIcon);
        }

        public void bind(final Meta Meta, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(Meta);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongClick(Meta);
                    return true;
                }

            });

        }
    }
}
