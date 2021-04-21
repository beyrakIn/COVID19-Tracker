package com.example.covid_19;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.api.models.CaseResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseModel> implements Filterable {
    Context context;
    List<CaseResponse> cases;
    ArrayList<CaseResponse> allCases;

    public CaseAdapter(Context context, List<CaseResponse> cases) {
        this.context = context;
        this.cases = cases;
        allCases = new ArrayList<>(cases);
    }

    @NonNull
    @Override
    public CaseModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cases, parent, false);
        return new CaseModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseModel holder, int position) {
        CaseResponse object = cases.get(position);
        holder.countryName.setText(object.getCountry());
//        holder.linearLayout.addView(setTextView("Country", object.getCountry(), context));
//        holder.linearLayout.addView(setTextView("Total Cases", object.getCases(), context));
//        holder.linearLayout.addView(setTextView("Total Deaths", object.getDeaths(), context));
//        holder.linearLayout.addView(setTextView("Total Recovered", object.getRecovered(), context));
//        holder.linearLayout.addView(setTextView("New Cases", object.getNewCases(), context));
//        holder.linearLayout.addView(setTextView("New Deaths", object.getNewDeaths(), context));
//        holder.linearLayout.addView(setTextView("New Recovered", object.getNewRecovered(), context));
//        holder.linearLayout.addView(setTextView("Active case", object.getActiveCase(), context));
//        holder.linearLayout.addView(setTextView("Critical case", object.getCritical(), context));
//        holder.linearLayout.addView(setTextView("Additional", object.getAdditional(), context));
//        holder.linearLayout.addView(setTextView("Update date", object.getLast_updated(), context));
    }

    @Override
    public int getItemCount() {
        return cases.size();
    }

    //Filterable
    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CaseResponse> filteredElements = new ArrayList<>();

            System.out.println(constraint.toString());
            System.out.println(allCases.size());
            if (constraint.toString().isEmpty()) {
                filteredElements.addAll(allCases);
            } else {
                for (CaseResponse object : allCases) {
                    if (object.getCountry().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredElements.add(object);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredElements;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cases.clear();
            cases.addAll((Collection<? extends CaseResponse>) results.values);
            notifyDataSetChanged();
        }
    };

    private LinearLayout setTextView(String titleText, String s, Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        TextView title = new TextView(context);
        TextView desc = new TextView(context);
        linearLayout.setGravity(Gravity.CENTER);

        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setText(titleText);
        title.setAllCaps(true);
        title.setTextSize(16);
        title.setTypeface(ResourcesCompat.getFont(context, R.font.anton));

        desc.setGravity(Gravity.CENTER);
        desc.setText(s);
        desc.setTextSize(16);
        desc.setTypeface(ResourcesCompat.getFont(context, R.font.martel_sans_bold));

        linearLayout.addView(title);
        linearLayout.addView(desc);
        return linearLayout;
    }

}
