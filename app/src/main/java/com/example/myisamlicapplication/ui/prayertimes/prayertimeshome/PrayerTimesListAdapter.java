package com.example.myisamlicapplication.ui.prayertimes.prayertimeshome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myisamlicapplication.R;
import com.example.myisamlicapplication.data.pojo.prayertimes.PrayerTiming;

import java.util.ArrayList;

public class PrayerTimesListAdapter extends RecyclerView.Adapter<PrayerTimesListAdapter.ViewHolder> {


    private ArrayList<PrayerTiming> timings;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_prayer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(timings.get(position));
    }

    @Override
    public int getItemCount() {
        return timings == null ? 0 : timings.size();
    }

    public void setTimings(ArrayList<PrayerTiming> timings) {
        this.timings = timings;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView prayerName, prayerTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prayerName = itemView.findViewById(R.id.prayer_name);
            prayerTime = itemView.findViewById(R.id.prayer_time);
        }

        public void bind(PrayerTiming timing) {
            prayerName.setText(timing.getPrayerName());
            prayerTime.setText(timing.getPrayerTime());
        }
    }
}
