package com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository;

import java.util.ArrayList;

/**
 * Repositorio de los colores de la aplicacion(usando algunos de los colores disponibles en MaterialLetterIcon
 * @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class ColorRepository {
    private static ColorRepository colorRepository;
    ArrayList<String> colors;
    private  ColorRepository(){
        colors = new ArrayList<>();
        initialize();
    }

    static {
        colorRepository=new ColorRepository();
    }
    private void initialize()
    {
        addColor("Red");
        addColor("Blue");
        addColor("Green");
        addColor("Yellow");
        addColor("Grey");
        addColor("Black");

    }
    public static ColorRepository getInstance()
    {
        return colorRepository;
    }
    public ArrayList<String> getColors()
    {        return colors;    }
    public void addColor(String c) {
        colors.add(c);
    }

}
