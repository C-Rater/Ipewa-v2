package com.oyasumisoft.juanfrancrater.ipewa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.ColorRepository;

/**
 * Adapter de los colores de la aplicacion(usando algunos de los colores disponibles en MaterialLetterIcon
 * @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class ColorAdapter extends ArrayAdapter<String> {
    public ColorAdapter(@NonNull Context cntx)
    {
        super(cntx,android.R.layout.simple_spinner_dropdown_item, ColorRepository.getInstance().getColors());

    }
}
