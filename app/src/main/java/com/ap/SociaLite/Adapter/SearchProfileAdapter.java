package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.R;

import java.util.ArrayList;

public class SearchProfileAdapter extends RecyclerView.Adapter<SearchProfileAdapter.MyHolder> {

    ArrayList Name;
    Context context;

    public SearchProfileAdapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_profile_rs, parent, false);
        SearchProfileAdapter.MyHolder myHolder = new SearchProfileAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText((CharSequence)Name.get(position));
    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        ImageView profile;
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.user_profile);
            name = itemView.findViewById(R.id.user_name);

        }
    }

}
