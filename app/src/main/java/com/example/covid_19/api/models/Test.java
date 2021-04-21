package com.example.covid_19.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Test implements Serializable {
    @SerializedName("1M_pop")
    private String oneMillion;
    private float total;


    // Getter Methods

    public String get1M_pop() {
        return oneMillion;
    }

    public float getTotal() {
        return total;
    }

    // Setter Methods

    public void set1M_pop(String oneMillion) {
        this.oneMillion = oneMillion;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Test{" +
                "oneMillion='" + oneMillion + '\'' +
                ", total=" + total +
                '}';
    }
}
