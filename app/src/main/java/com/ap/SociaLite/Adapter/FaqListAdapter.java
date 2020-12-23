package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.Faq;
import com.ap.SociaLite.Pojo.faq_list;
import com.ap.SociaLite.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqListAdapter extends RecyclerView.Adapter<FaqListAdapter.Holder> {

    Context mContext;
    Faq FaqActivity;
    List<faq_list> faq_lists;
    faq_list item;
    Boolean openlyout = true;

    public FaqListAdapter(Context context, List<faq_list> list, Faq fragment) {
        this.mContext = context;
        this.faq_lists = list;
        this.FaqActivity = fragment;
    }

    @NonNull
    @Override
    public FaqListAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_rs, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FaqListAdapter.Holder holder, int position) {

        item = faq_lists.get(position);

        holder.rlFaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(openlyout){
                    holder.tv_faq_description.setVisibility(View.VISIBLE);
                    holder.img_next.setVisibility(View.VISIBLE);
                    holder.img_down.setVisibility(View.GONE);
                    openlyout = false;
                }else {
                    holder.tv_faq_description.setVisibility(View.GONE);
                    holder.img_next.setVisibility(View.GONE);
                    holder.img_down.setVisibility(View.VISIBLE);
                    openlyout = true;
                }
            }
        });

        holder.tv_faq_title.setText(item.question);
        holder.tv_faq_description.setText(item.answer);
}

    @Override
    public int getItemCount() {
        return faq_lists.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_faq_title)
        TextView tv_faq_title;

        @BindView(R.id.tv_faq_answer)
        TextView tv_faq_description;

        @BindView(R.id.img_next)
        ImageView img_next;

        @BindView(R.id.img_down)
        ImageView img_down;

        @BindView(R.id.rlFaq)
        RelativeLayout rlFaq;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
