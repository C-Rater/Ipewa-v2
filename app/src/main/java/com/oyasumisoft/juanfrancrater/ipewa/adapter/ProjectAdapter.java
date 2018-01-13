package com.oyasumisoft.juanfrancrater.ipewa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ProjectRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Proyecto;


/**
 * Adapter del Pojo Proyecto
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class ProjectAdapter extends ArrayAdapter<Proyecto> {

    public ProjectAdapter(@NonNull Context context) {
        super(context, R.layout.item_project, ProjectRepository.getInstance().getProjects());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProjectHolder projectHolder;
        View view=convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_project, null);
            projectHolder  = new ProjectHolder();
            projectHolder.icon = (MaterialLetterIcon) view.findViewById(R.id.icon);
            projectHolder.txvName = (TextView) view.findViewById(R.id.txtV_Name);

            view.setTag(projectHolder);
        }else {
            projectHolder=(ProjectHolder)view.getTag();
        }

        projectHolder.icon.setLetter(getItem(position).get_name().substring(0, 1));
        projectHolder.icon.setShapeColor(Color.parseColor(getItem(position).get_color()));
        projectHolder.txvName.setText(getItem(position).get_name());

        try {
            //The phone is horizontal
            projectHolder.txvDescription = (TextView) view.findViewById(R.id.txtV_Description);
            if(getItem(position).get_description().length()>130)
            {
                projectHolder.txvDescription.setText(getItem(position).get_description().substring(0,130)+"...");
            }else {
                projectHolder.txvDescription.setText(getItem(position).get_description());
            }
        }catch (NullPointerException e)
        {
            //The phone is vertical
        }
        return view;
    }

    class ProjectHolder{
        MaterialLetterIcon icon;
        TextView txvName;
        TextView txvDescription;

    }
}
