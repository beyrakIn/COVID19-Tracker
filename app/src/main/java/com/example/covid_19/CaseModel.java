package com.example.covid_19;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CaseModel extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    TextView countryName;

    public CaseModel(@NonNull View itemView) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.cases_card_view);
        countryName = itemView.findViewById(R.id.case_country_name);
    }
}
