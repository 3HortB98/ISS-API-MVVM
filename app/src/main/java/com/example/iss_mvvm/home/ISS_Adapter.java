package com.example.iss_mvvm.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iss_mvvm.R;
import com.example.iss_mvvm.net.ISSResponse;
import com.example.iss_mvvm.net.Response;

import java.util.ArrayList;
import java.util.List;

public class ISS_Adapter extends RecyclerView.Adapter<ISS_Adapter.itemViewHolder>{

    private final List<Response> responses= new ArrayList<>();

    public void setData(List<Response> newdata){
        responses.clear();
        responses.addAll(newdata);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_iss_pass,viewGroup,false);
        return new itemViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder itemViewHolder, int position) {
        itemViewHolder.bind(responses.get(position));

    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    static class itemViewHolder extends RecyclerView.ViewHolder {
        TextView tvDuration;
        TextView tvRiseTime;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvRiseTime= itemView.findViewById(R.id.tvRiseTime);

        }
        public void bind(Response response){
            tvDuration.setText(response.getDuration().toString());
            tvRiseTime.setText(response.getRisetime().toString());
        }
    }
}
