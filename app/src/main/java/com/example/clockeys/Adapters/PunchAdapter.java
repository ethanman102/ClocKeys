package com.example.clockeys.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clockeys.R;
import com.example.clockeys.Time.Punch;

import java.util.List;

public class PunchAdapter extends RecyclerView.Adapter<PunchAdapter.MyViewHolder> {
    private Context context;
    private List<Punch> punches;

    public PunchAdapter(Context context, List<Punch> punches) {
        this.context = context;
        this.punches = punches;
    }

    @NonNull
    @Override
    public PunchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.punch_item,parent,false);
        return new PunchAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PunchAdapter.MyViewHolder holder, int position) {
        Punch punch = punches.get(position);
        holder.punchTime.setText(punch.toString());
        holder.punchHours.setText(String.valueOf(punch.punchTime()));
        holder.punchDate.setText(punch.getStringDate());
    }

    @Override
    public int getItemCount() {
        return punches.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView punchDate, punchHours,punchTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            punchDate = itemView.findViewById(R.id.punchDateText);
            punchHours = itemView.findViewById(R.id.punchHoursWorked);
            punchTime = itemView.findViewById(R.id.punchTimeText);
        }
    }


}
