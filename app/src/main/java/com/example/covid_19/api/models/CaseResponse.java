package com.example.covid_19.api.models;

import java.io.Serializable;

public class CaseResponse implements Serializable {
    private String continent;
    private String country;
    private float population;
    Case cases;
    Death deaths;
    Test tests;
    private String day;
    private String time;


    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getPopulation() {
        return population;
    }

    public void setPopulation(float population) {
        this.population = population;
    }

    public Case getCases() {
        return cases;
    }

    public void setCases(Case cases) {
        this.cases = cases;
    }

    public Death getDeaths() {
        return deaths;
    }

    public void setDeaths(Death deaths) {
        this.deaths = deaths;
    }

    public Test getTests() {
        return tests;
    }

    public void setTests(Test tests) {
        this.tests = tests;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Response{" +
                "continent='" + continent + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                ", CasesObject=" + cases +
                ", DeathsObject=" + deaths +
                ", TestsObject=" + tests +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

