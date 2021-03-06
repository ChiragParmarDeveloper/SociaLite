package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Fragment.InterestFragment;
import com.ap.SociaLite.Pojo.interest_details;
import com.ap.SociaLite.Presenter.InterestFragmentPresenter;
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
    private int selectedItem;

    private int clickItem = 0;

    public InterestListAdapter(Context context, List<interest_details> list, InterestFragment fragment) {
        this.mContext = context;
        this.details = list;
        this.interestFragment = fragment;

        selectedItem = 0;
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

        if (selectedItem == position) {
            interestFragment.interest_id = details.get(position).interest_id;
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));
            new InterestFragmentPresenter(mContext, interestFragment).fetch_my_intrest_wise_post(interestFragment.user_id,interestFragment.interest_id);
        }

        if (position == clickItem) {
            interestFragment.interest_id = details.get(position).interest_id;
            holder.img_category.setSelected(true);
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));

            new InterestFragmentPresenter(mContext, interestFragment).fetch_my_intrest_wise_post(interestFragment.user_id,interestFragment.interest_id);

        } else {
            holder.img_category.setSelected(false);
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.white));
        }

        holder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interestFragment.interest_id = details.get(position).interest_id;
                clickItem = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

        holder.img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interestFragment.interest_id = details.get(position).interest_id;
                new InterestFragmentPresenter(mContext, interestFragment).remove_interest(interestFragment.user_id, interestFragment.interest_id);
                removeAt(position);
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

    public void removeAt(int pos) {
        details.remove(pos);
        notifyDataSetChanged();
    }
}
