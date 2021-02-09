package com.example.covid_19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CaseModel extends RecyclerView.ViewHolder {
    public Context context;
    LinearLayout linearLayout;
    TextView country, cases, deaths, date, recovered, newCases, newDeaths, newRecovered, activeCase, critical, additional;
    boolean description = false;
    Typeface typeface, typeface2;

    public CaseModel(@NonNull View itemView) {
        super(itemView);
        linearLayout = itemView.findViewById(R.id.cases_card_view);
        country = new TextView(itemView.getContext());
        cases = new TextView(itemView.getContext());
        deaths = new TextView(itemView.getContext());
        recovered = new TextView(itemView.getContext());
        date = new TextView(itemView.getContext());
        newCases = new TextView(itemView.getContext());
        newDeaths = new TextView(itemView.getContext());
        newRecovered = new TextView(itemView.getContext());
        activeCase = new TextView(itemView.getContext());
        critical = new TextView(itemView.getContext());
        additional = new TextView(itemView.getContext());

        typeface2 = ResourcesCompat.getFont(itemView.getContext(), R.font.montserrat_semi_bold);

        country.setTypeface(typeface2);
        cases.setTypeface(typeface2);
        deaths.setTypeface(typeface2);
        recovered.setTypeface(typeface2);
        newCases.setTypeface(typeface2);
        newDeaths.setTypeface(typeface2);
        newRecovered.setTypeface(typeface2);
        activeCase.setTypeface(typeface2);
        date.setTypeface(typeface2);
        critical.setTypeface(typeface2);
        additional.setTypeface(typeface2);

        newCases.setTextColor(Color.rgb(153, 51, 0));
        newDeaths.setTextColor(Color.rgb(250, 0, 0));
        newRecovered.setTextColor(Color.rgb(0, 128, 0));

        cases.setVisibility(View.GONE);
        deaths.setVisibility(View.GONE);
        recovered.setVisibility(View.GONE);
        date.setVisibility(View.GONE);
        additional.setVisibility(View.GONE);
        newRecovered.setVisibility(View.GONE);

        layoutOnClick();


        linearLayout.addView(country);
        linearLayout.addView(activeCase);
        linearLayout.addView(critical);
        linearLayout.addView(newCases);
        linearLayout.addView(newDeaths);
        linearLayout.addView(newRecovered);
        linearLayout.addView(cases);
        linearLayout.addView(deaths);
        linearLayout.addView(recovered);
        linearLayout.addView(additional);
        linearLayout.addView(date);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void layoutOnClick() {
        linearLayout.setOnClickListener(v -> {
            if (!description) {
                cases.setVisibility(View.VISIBLE);
                deaths.setVisibility(View.VISIBLE);
                recovered.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                additional.setVisibility(View.VISIBLE);
            } else {
                cases.setVisibility(View.GONE);
                deaths.setVisibility(View.GONE);
                recovered.setVisibility(View.GONE);
                date.setVisibility(View.GONE);
                additional.setVisibility(View.GONE);
            }
            description = !description;
        });
    }

}
