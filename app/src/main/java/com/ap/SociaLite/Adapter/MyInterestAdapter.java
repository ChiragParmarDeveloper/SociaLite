package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.InterestActivity;
import com.ap.SociaLite.Fragment.InterestFragment;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInterestAdapter  extends RecyclerView.Adapter<MyInterestAdapter.Holder> {

    Context mContext;
    InterestActivity interestActivity;
    List<String> mList;

    public MyInterestAdapter(Context context, List<String> interest_name, InterestActivity fragment) {
        this.mContext = context;
        this.mList = interest_name;
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

        holder.checkbox_interest.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
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
