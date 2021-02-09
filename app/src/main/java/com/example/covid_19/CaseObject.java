package com.example.covid_19;

import org.json.JSONException;
import org.json.JSONObject;

public class CaseObject {
    String country, last_updated;
    String cases, deaths, recovered, newCases, newDeaths, newRecovered, activeCase, population, critical, tests, additional;
    JSONObject object;

    public CaseObject(JSONObject object) {
        this.object = object;
        try {
            JSONObject cases = object.getJSONObject("cases");
            JSONObject deaths = object.getJSONObject("deaths");
            JSONObject tests = object.getJSONObject("tests");

            this.country = object.get("country").toString();
            this.population = object.get("population").toString();
            this.last_updated = object.get("time").toString().replace("T", "\nTime: ");
            this.cases = cases.get("total").toString();
            this.critical = cases.get("critical").toString();
            this.recovered = cases.get("recovered").toString();
            this.activeCase = checkText(cases.get("active").toString());
            this.newCases = checkText(cases.get("new").toString());
            this.deaths = deaths.get("total").toString();
            this.newDeaths = checkText(deaths.get("new").toString());
            this.tests = tests.get("total").toString();
            this.additional = "Total tests: " + this.tests
                    + "\nPopulation: " + this.population;


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String checkText(String text) {
        String s = "";
        if (text == null || text.isEmpty() || text.length() == 0) {
            s = "0";
        } else {
            s = text;
        }

        return s;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(String newDeaths) {
        this.newDeaths = newDeaths;
    }

    public String getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(String newRecovered) {
        this.newRecovered = newRecovered;
    }

    public String getActiveCase() {
        return activeCase;
    }

    public void setActiveCase(String activeCase) {
        this.activeCase = activeCase;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
