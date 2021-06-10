package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Pojo.interest_details;
import com.ap.SociaLite.Pojo.post_list;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.Holder> {

    Context mContext;
    CategoryFragment categoryFragment;
    List<interest_details> details = new ArrayList<>();
    interest_details item;
    public String clickItem;
    public static int selectedItem;

    int i;
    public CategoryListAdapter(Context context, List<interest_details> list, CategoryFragment fragment) {
        this.mContext = context;
        this.details = list;
        this.categoryFragment = fragment;
        selectedItem = 0;
        clickItem = categoryFragment.interest_ids;
        i=Integer.parseInt(clickItem);
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
  //      new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.interest_ids);
   //     Toast.makeText(mContext, "default) id " + categoryFragment.interest_ids, Toast.LENGTH_SHORT).show();
//        if (selectedItem == position) {
//         //   categoryFragment.interest_ids = details.get(position).interest_id;
//
//            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));
//
//            Toast.makeText(mContext, "default1" + categoryFragment.interest_ids, Toast.LENGTH_SHORT).show();
//          //  new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.interest_ids);
//        }
//       else
//       {
//        //   Toast.makeText(mContext,"else condition" + categoryFragment.interest_ids, Toast.LENGTH_SHORT).show();
//      //     new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.interest_ids);
//       }

        if ( categoryFragment.interest_ids != ""){

            Log.d("category_id",categoryFragment.interest_ids);
            new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.interest_ids);
       //     Toast.makeText(mContext, categoryFragment.interest_ids, Toast.LENGTH_SHORT).show();
      //      holder.img_category.setSelected(true);
      //      holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));

        }else {
            //  Toast.makeText(mContext, "Fail", Toast.LENGTH_SHORT).show();
        }

        if (item.flag.equals("1")) {
            holder.img_right.setVisibility(View.VISIBLE);
        } else {
            holder.img_right.setImageDrawable(plus_favorite);
        }

        holder.img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFragment.interest_ids = details.get(position).interest_id;
                new CategoryFragmentPresenter(mContext, categoryFragment).update_new_intrests(categoryFragment.user_id, id);
                new CategoryFragmentPresenter(mContext, categoryFragment).fetch_all_intrest(categoryFragment.user_id);
            }
        });


        if (position == i ) {
            holder.img_category.setSelected(true);
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));

            new CategoryFragmentPresenter(mContext, categoryFragment).Category_post_fragment(categoryFragment.interest_ids);

        } else {
            holder.img_category.setSelected(false);
            holder.img_category.setBorderColor(mContext.getResources().getColor(R.color.white));
        }

        holder.img_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryFragment.interest_ids = details.get(position).interest_id;
                i = holder.getAdapterPosition();
                notifyDataSetChanged();
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