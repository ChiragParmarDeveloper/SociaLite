package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.InterestActivity;
import com.ap.SociaLite.Fragment.InterestFragment;
import com.ap.SociaLite.Pojo.interest_list;
import com.ap.SociaLite.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInterestAdapter  extends RecyclerView.Adapter<MyInterestAdapter.Holder> {

    Context mContext;
    InterestActivity interestActivity;
   // List<String> mList;

    List<interest_list> interest_lists = new ArrayList<>();
    interest_list item;


    public static ArrayList<String> interest_ids = new ArrayList<>();


    public MyInterestAdapter(Context context, List<interest_list> list, InterestActivity fragment) {
        this.mContext = context;
        this.interest_lists = list;
        this.interestActivity = fragment;
    }

    @NonNull
    @Override
    public MyInterestAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interest_rs, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyInterestAdapter.Holder holder, int position) {
        item = interest_lists.get(position);

        String id = interest_lists.get(position).interest_id;
        holder.checkbox_interest.setText(item.interest_name);

        interest_ids.add(id);
        Log.d("interest_ID++-------", String.valueOf(interest_ids));

    }

    @Override
    public int getItemCount() {
//        if (mList == null) {
            return interest_lists.size();
//        } else {
//            return mList.size();
//        }
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox_interest)
        CheckBox checkbox_interest;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
