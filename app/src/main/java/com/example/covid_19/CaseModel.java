package com.example.covid_19;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CaseModel extends RecyclerView.ViewHolder {
    TextView countryName, newCase, newRecovered, population;

    public CaseModel(@NonNull View itemView) {
        super(itemView);
        countryName = itemView.findViewById(R.id.case_country_name);
        newCase = itemView.findViewById(R.id.cases_new_case);
        newRecovered = itemView.findViewById(R.id.cases_new_recovered);
        population = itemView.findViewById(R.id.case_country_population);
    }
}
