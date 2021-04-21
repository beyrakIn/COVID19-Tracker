package com.example.covid_19.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Death implements Serializable {
    @SerializedName("new")
    private String newDeath;
    @SerializedName("1M_pop")
    private String oneMillion;
    private String total = null;


    // Getter Methods

    public String getNew() {
        return newDeath;
    }

    public String get1M_pop() {
        return oneMillion;
    }

    public String getTotal() {
        return total;
    }

    // Setter Methods

    public void setNew(String newDeath) {
        this.newDeath = newDeath;
    }

    public void set1M_pop(String oneMillion) {
        this.oneMillion = oneMillion;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Death{" +
                "newDeath='" + newDeath + '\'' +
                ", oneMillion='" + oneMillion + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
