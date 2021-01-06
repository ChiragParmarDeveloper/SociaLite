package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Fragment.InterestFragment;
import com.ap.SociaLite.Pojo.interest_details;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InterestListAdapter extends RecyclerView.Adapter<InterestListAdapter.Holder> {


    Context mContext;
    InterestFragment interestFragment;
    List<interest_details> details = new ArrayList<>();
    interest_details item;

    public InterestListAdapter(Context context, List<interest_details> list, InterestFragment fragment) {
        this.mContext = context;
        this.details = list;
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
        item = details.get(position);
        String id = details.get(position).interest_id;

        holder.txt_iconname.setText(item.interest_name);
        Picasso.get().load(item.interest_image).placeholder(R.mipmap.ic_launcher).into(holder.img_category);

        Drawable plus_favorite = mContext.getDrawable(R.drawable.ic_category_plus);
        Drawable right_favorite = mContext.getDrawable(R.drawable.ic_category_right);

        if (item.flag.equals("1")) {
            holder.img_right.setVisibility(View.VISIBLE);
        } else {
            holder.img_right.setImageDrawable(plus_favorite);
        }

        //   int realposition = position % mList.size();

        // holder.txt_iconname.setText((CharSequence) mList.get(realposition));
//        holder.img_category.setImageResource((Integer) CategoryImages.get(realposition));
        //   Picasso.get().load(item.).placeholder(R.mipmap.ic_launcher).into(holder.img_category);

//        holder.img_category.setImageResource((Integer) CategoryImages.get(realposition));
        //   Picasso.get().load(item.).placeholder(R.mipmap.ic_launcher).into(holder.img_category);
    }

    @Override
    public int getItemCount() {
        return details.size();
        //return                Integer.MAX_VALUE;
    }

    public class Holder extends RecyclerView.ViewHolder {
        CircularImageView img_category;
        TextView txt_iconname;
        ImageView img_right;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img_category = itemView.findViewById(R.id.img_category);
            txt_iconname = itemView.findViewById(R.id.txt_iconname);
            img_right = itemView.findViewById(R.id.img_right);
        }
    }
}
