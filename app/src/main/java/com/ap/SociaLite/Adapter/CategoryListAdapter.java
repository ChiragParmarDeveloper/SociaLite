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
import com.ap.SociaLite.Pojo.interest_details;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.Presenter.InterestFragmentPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.Holder> {

    Context mContext;
    CategoryFragment categoryFragment;
    List<interest_details> details = new ArrayList<>();
    interest_details item;
    private int selectedItem;

    public CategoryListAdapter(Context context, List<interest_details> list, CategoryFragment fragment) {
        this.mContext = context;
        this.details = list;
        this.categoryFragment = fragment;

        selectedItem = 0;
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
        item = details.get(position);
        String id = details.get(position).interest_id;

        holder.txt_iconname.setText(item.interest_name);
        Picasso.get().load(item.interest_image).into(holder.img_category);

        Drawable plus_favorite = mContext.getDrawable(R.drawable.ic_category_plus);
        Drawable right_favorite = mContext.getDrawable(R.drawable.ic_category_right);

        if (selectedItem == position) {
            String interest_id = details.get(position).interest_id;
            new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(interest_id);
        }


        if (item.flag.equals("1")) {
            holder.img_right.setVisibility(View.VISIBLE);
        } else {
            holder.img_right.setImageDrawable(plus_favorite);
        }

        holder.img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CategoryFragmentPresenter(mContext, categoryFragment).update_new_intrests(categoryFragment.user_id, id);
                new CategoryFragmentPresenter(mContext, categoryFragment).fetch_all_intrest(categoryFragment.user_id);
            }
        });

        holder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String interest_id = details.get(position).interest_id;
                new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(interest_id);
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