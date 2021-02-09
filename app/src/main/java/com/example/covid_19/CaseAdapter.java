package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CaseAdapter extends RecyclerView.Adapter<CaseModel> implements Filterable {
    Context context;
    List<CaseObject> list;
    List<CaseObject> listAll;

    public CaseAdapter(Context context, List<CaseObject> list) {
        this.context = context;
        this.list = list;
        this.listAll = list;
    }

    @NonNull
    @Override
    public CaseModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cases, parent, false);
        return new CaseModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaseModel holder, int position) {
        holder.country.setText("Country: " + list.get(position).getCountry());
        holder.cases.setText("Total cases: " + list.get(position).getCases());
        holder.deaths.setText("Total deaths: " + list.get(position).getDeaths());
        holder.recovered.setText("Total recovered: " + list.get(position).getRecovered());
        holder.date.setText("Update date: " + list.get(position).getLast_updated());
        holder.newCases.setText("New cases: " + list.get(position).getNewCases());
        holder.newDeaths.setText("New deaths: " + list.get(position).getNewDeaths());
        holder.newRecovered.setText("New recovered: " + list.get(position).getNewRecovered());
        holder.activeCase.setText("Active case: " + list.get(position).getActiveCase());
        holder.critical.setText("Critical case: " + list.get(position).getCritical());
        holder.additional.setText(list.get(position).getAdditional());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Filterable
    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list = (List<CaseObject>) results.values;
            notifyDataSetChanged();
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText = constraint.toString().toLowerCase();
            List<CaseObject> filteredList = null;

            if (searchText.length() == 0 || searchText.isEmpty()) {
                filteredList = list;
            } else {
                filteredList = getFilteredResults(searchText);
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

    };

    protected List<CaseObject> getFilteredResults(String constraint) {
        List<CaseObject> results = new ArrayList<>();

        for (CaseObject item : listAll) {
            if (item.getCountry().toLowerCase().contains(constraint)) {
                results.add(item);
            }
        }
        return results;
    }
}
