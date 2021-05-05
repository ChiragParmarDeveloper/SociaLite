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

import com.ap.SociaLite.Fragment.BusinessFragment;
import com.ap.SociaLite.Pojo.interest_details;
import com.ap.SociaLite.Presenter.BusinessFragmentPresenter;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.Holder> {

    Context mContext;
    BusinessFragment businessFragment;
    List<interest_details> details = new ArrayList<>();
    interest_details item;
    private int selectedItem;

    private int clickItem = 0;

    public BusinessListAdapter(Context context, List<interest_details> list, BusinessFragment fragment) {
        this.mContext = context;
        this.details = list;
        this.businessFragment = fragment;

        selectedItem = 0;
    }

    @NonNull
    @Override
    public BusinessListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_category_list_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessListAdapter.Holder holder, int position) {
        item = details.get(position);

        holder.txt_iconname.setText(item.interest_name);
        Picasso.get().load(item.interest_image).into(holder.img_category);

        Drawable plus_favorite = mContext.getDrawable(R.drawable.ic_category_plus);
        Drawable right_favorite = mContext.getDrawable(R.drawable.ic_category_right);

        if (item.flag.equals("1")) {
            holder.img_right.setVisibility(View.VISIBLE);
        } else {
            holder.img_right.setImageDrawable(plus_favorite);
        }

        if (selectedItem == position) {

            if (details.get(position).flag.equals("1")) {
                String interest_id = details.get(position).interest_id;
                holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));
                new BusinessFragmentPresenter(mContext, businessFragment).business_post(interest_id,businessFragment.user_id);
            } else {
                //         Toast.makeText(mContext, "id not selected", Toast.LENGTH_SHORT).show();
            }
        }


        holder.img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = details.get(position).interest_id;
                businessFragment.interest_ids = details.get(position).interest_id;
                new BusinessFragmentPresenter(mContext, businessFragment).update_new_intrests(businessFragment.user_id, id);
                new BusinessFragmentPresenter(mContext, businessFragment).fetch_all_intrest(businessFragment.user_id);

            }
        });

        if (position == clickItem) {
            holder.img_category.setSelected(true);
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));

            String interest_id = details.get(position).interest_id;
            new BusinessFragmentPresenter(mContext, businessFragment).business_post(interest_id,businessFragment.user_id);

        } else {
            holder.img_category.setSelected(false);
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.white));
        }

        holder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if (details.get(position).flag.equals("1")) {
                    clickItem = holder.getAdapterPosition();
                    notifyDataSetChanged();
         //       } else {
                    //         Toast.makeText(mContext, "id not selected", Toast.LENGTH_SHORT).show();
           //     }
            }
        });
    }

    @Override
    public int getItemCount() {
        return details.size();
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
