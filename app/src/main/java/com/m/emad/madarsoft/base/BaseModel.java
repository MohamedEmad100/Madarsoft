package com.m.emad.madarsoft.base;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;


public class BaseModel implements Serializable {

    @DatabaseField(columnName = "id" ,unique = true ,id = true)
    protected String id;

    public void setID(String ID) { this.id = ID; }

    public String getID() { return id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o ==null ||!(o instanceof BaseModel)) return false;
        BaseModel baseModel = (BaseModel) o;
        return id.equals(baseModel.getID());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
