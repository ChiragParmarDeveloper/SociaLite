package com.ap.SociaLite.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ap.SociaLite.R;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.Holder> {


    ArrayList CategoryNames;
    ArrayList CategoryImages;
    Context context;

    public CategoryListAdapter(Context context, ArrayList CategoryNames, ArrayList CategoryImages) {
        this.context = context;
        this.CategoryNames = CategoryNames;
        this.CategoryImages = CategoryImages;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_list_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.txt_iconname.setText((CharSequence) CategoryNames.get(position));
        holder.img_category.setImageResource((Integer) CategoryImages.get(position));

    }

    @Override
    public int getItemCount() {
        return CategoryNames.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView img_category;
        TextView txt_iconname;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img_category = itemView.findViewById(R.id.img_category);
            txt_iconname = itemView.findViewById(R.id.txt_iconname);

        }
    }
}