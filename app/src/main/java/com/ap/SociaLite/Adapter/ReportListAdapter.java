package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.Report;
import com.ap.SociaLite.Activity.Report_Status;
import com.ap.SociaLite.Pojo.Report_details;
import com.ap.SociaLite.Presenter.CategoryFragmentPresenter;
import com.ap.SociaLite.Presenter.Report_Presenter;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.Holder> {

    Context mContext;
    Report report;
    List<Report_details> report_details;
    Report_details item;

    public ReportListAdapter(Context context, List<Report_details> list, Report fragment) {
        this.mContext = context;
        this.report_details = list;
        this.report = fragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_rs, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        item = report_details.get(position);
        String reason = report_details.get(position).description;

        holder.txt_description.setText(item.description);

        if(position == report_details.size()-1){
            holder.view.setVisibility(View.GONE);
        }

        holder.txt_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Report_Presenter(mContext,report).report_post(report.user_id,report.post_id,reason);
            }
        });
    }

    @Override
    public int getItemCount() {
        return report_details.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_description)
        TextView txt_description;

        @BindView(R.id.view)
        View view;


        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
