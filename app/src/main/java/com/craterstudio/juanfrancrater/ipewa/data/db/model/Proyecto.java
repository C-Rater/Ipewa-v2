package com.craterstudio.juanfrancrater.ipewa.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Pojo Proyecto(implementa Comparable y Parcelable)
 *  @author Juan Francisco Benítez López
 * @version 0.1.0
 */

public class Proyecto implements Comparable, Parcelable {
    public static String TAG = "project";
    int _ID;
    String _name;
    String _description;
    int _color;
    String _deadLine;
    String _creator;

    public String get_creator() {
        return _creator;
    }

    public void set_creator(String _creator) {
        this._creator = _creator;
    }


    public Proyecto(int _ID, String _name, String _description, int _color, String _deadLine,String _creator) {
        this._ID = _ID;
        this._name = _name;
        this._description = _description;
        this._color = _color;
        this._deadLine = _deadLine;
        this._creator=_creator;
    }

    protected Proyecto(Parcel in) {
        _ID = in.readInt();
        _name = in.readString();
        _description = in.readString();
        _color = in.readInt();
        _deadLine = in.readString();
        _creator=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_ID);
        dest.writeString(_name);
        dest.writeString(_description);
        dest.writeInt(_color);
        dest.writeString(_deadLine);
        dest.writeString(_creator);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Proyecto> CREATOR = new Creator<Proyecto>() {
        @Override
        public Proyecto createFromParcel(Parcel in) {
            return new Proyecto(in);
        }

        @Override
        public Proyecto[] newArray(int size) {
            return new Proyecto[size];
        }
    };

    public int  get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public int get_color() {
        return _color;
    }

    public void set_color(int _color) {
        this._color = _color;
    }

    public String get_deadLine() {
        return _deadLine;
    }

    public void set_deadLine(String _deadLine) {
        this._deadLine = _deadLine;
    }



    @Override
    public String toString() {
        return _name ;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        if( _ID==((Proyecto)o).get_ID())
        {
            return 0;
        }else if(_ID>((Proyecto)o).get_ID())
        {
            return 1;
        }else{
            return -1;
        }
    }
}
