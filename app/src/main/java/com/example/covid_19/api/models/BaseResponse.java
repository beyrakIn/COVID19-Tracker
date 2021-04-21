package com.example.covid_19.api.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseResponse implements Serializable {
    private String get;
    List<Object> parameters = new ArrayList<>();
    List<Object> errors = new ArrayList<>();
    private float results;
    List<CaseResponse> response = new ArrayList<>();


    // Getter Methods

    public String getGet() {
        return get;
    }

    public float getResults() {
        return results;
    }

    // Setter Methods

    public void setGet(String get) {
        this.get = get;
    }

    public void setResults(float results) {
        this.results = results;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<CaseResponse> getCaseResponse() {
        return response;
    }

    public void setCaseResponse(List<CaseResponse> response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "get='" + get + '\'' +
                ", parameters=" + parameters +
                ", errors=" + errors +
                ", results=" + results +
                ", response=" + response +
                '}';
    }
}
