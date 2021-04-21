package com.example.covid_19.api.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Case implements Serializable {
    @SerializedName("new")
    private String newCase;
    private float active;
    private String critical;
    private float recovered;
    @SerializedName("1M_pop")
    private String oneMillion;
    private float total;


    // Getter Methods

    public String getNew() {
        return newCase;
    }

    public float getActive() {
        return active;
    }

    public String getCritical() {
        return critical;
    }

    public float getRecovered() {
        return recovered;
    }

    public String get1M_pop() {
        return oneMillion;
    }

    public float getTotal() {
        return total;
    }

    // Setter Methods

    public void setNew(String newCase) {
        this.newCase = newCase;
    }

    public void setActive(float active) {
        this.active = active;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public void setRecovered(float recovered) {
        this.recovered = recovered;
    }

    public void set1M_pop(String oneMillion) {
        this.oneMillion = oneMillion;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Case{" +
                "newCase='" + newCase + '\'' +
                ", active=" + active +
                ", critical='" + critical + '\'' +
                ", recovered=" + recovered +
                ", oneMillion='" + oneMillion + '\'' +
                ", total=" + total +
                '}';
    }
}
