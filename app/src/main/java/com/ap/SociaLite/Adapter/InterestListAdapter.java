package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Fragment.InterestFragment;
import com.ap.SociaLite.R;

import java.util.List;

public class InterestListAdapter extends RecyclerView.Adapter<InterestListAdapter.Holder> {

    Context mContext;
    InterestFragment interestFragment;
    List<String> mList;

    public InterestListAdapter(Context context, List<String> interest_name, InterestFragment fragment) {
        this.mContext = context;
        this.mList = interest_name;
        this.interestFragment = fragment;
    }

    @NonNull
    @Override
    public InterestListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_list_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InterestListAdapter.Holder holder, int position) {
        int realposition = position % mList.size();

        holder.txt_iconname.setText((CharSequence) mList.get(realposition));
//        holder.img_category.setImageResource((Integer) CategoryImages.get(realposition));
        //   Picasso.get().load(item.).placeholder(R.mipmap.ic_launcher).into(holder.img_category);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
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
