package com.example.covid_19;

import android.app.Dialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.api.models.CaseResponse;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

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
        CaseResponse caseResponse = cases.get(position);
        holder.countryName.setText("\t" + caseResponse.getCountry().replaceAll("-", " "));
        holder.population.setText("\t" + String.valueOf((int) caseResponse.getPopulation()));
        holder.newCase.setText(caseResponse.getCases().getNew());
        holder.newRecovered.setText(String.valueOf((int) caseResponse.getCases().getRecovered()));

        holder.itemView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Dialog dialog = new Dialog(activity);
            View view = activity.getLayoutInflater().inflate(R.layout.detail_page, null);
            AdView adView = new AdView(context);
            adView.setAdUnitId("ca-app-pub-2248916584991987/2941445224"); //REAL
//            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");   //FAKE
            adView.setAdSize(AdSize.FULL_BANNER);
            MobileAds.initialize(context, initializationStatus -> {
            });

            adView.setAdListener(new AdListener() {
                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    Toast.makeText(context, "Thanks)", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                }
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            LinearLayout linearLayout = view.findViewById(R.id.detail_page_linear_layout);

            linearLayout.addView(setTextView(context.getResources().getString(R.string.country), caseResponse.getCountry().replaceAll("-", " "), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.continent), caseResponse.getContinent(), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.total_cases), String.valueOf((int) caseResponse.getCases().getTotal()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.new_cases), String.valueOf(caseResponse.getCases().getNew()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.total_deaths), String.valueOf(caseResponse.getDeaths().getTotal()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.new_deaths), String.valueOf(caseResponse.getDeaths().getNew()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.total_recovered), String.valueOf((int) caseResponse.getCases().getRecovered()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.active_case), String.valueOf((int) caseResponse.getCases().getActive()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.critical_case), String.valueOf(caseResponse.getCases().getCritical()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.cases1mpop), String.valueOf(caseResponse.getCases().get1M_pop()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.deaths1mpop), String.valueOf(caseResponse.getDeaths().get1M_pop()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.total_tests), String.valueOf((int) caseResponse.getTests().getTotal()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.tests1mpop), String.valueOf(caseResponse.getTests().get1M_pop()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.population), String.valueOf((int) caseResponse.getPopulation()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.update_date), String.valueOf(caseResponse.getDay()), context));
            linearLayout.addView(setTextView(context.getResources().getString(R.string.update_time), String.valueOf(caseResponse.getTime()), context));
            linearLayout.addView(adView);

            dialog.setContentView(view);
            dialog.show();
        });
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
