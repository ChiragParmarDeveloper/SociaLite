package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Activity.ViewAllContectActivity;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class viewallshareAdapter  extends RecyclerView.Adapter<viewallshareAdapter.MyHolder>{

    Context mContext;
    public List<data> datas;
    ViewAllContectActivity viewAllContectActivity;
    List<data> alldata;
    data item;

    public viewallshareAdapter(Context mContext, List<data> datas, ViewAllContectActivity viewAllContectActivity) {
        this.mContext = mContext;
        this.datas = datas;
        this.viewAllContectActivity = viewAllContectActivity;

        this.alldata = new ArrayList<>();
        this.alldata.addAll(datas);

    }



    @NonNull
    @Override
    public viewallshareAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rs_sharetofrnd, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewallshareAdapter.MyHolder holder, int position) {
        item = datas.get(position);
        holder.txt_name.setText(item.username);

        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
            holder.user_profile.setImageDrawable(upload_img);
        } else {
            Picasso.get().load(item.profile_pic).into(holder.user_profile);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()) {
                    String id = datas.get(position).request_id;
                    viewAllContectActivity.sharefrnd_id.add(id);
                    Log.d("sharefrnd_id_check", String.valueOf(viewAllContectActivity.sharefrnd_id));
                } else {
                    String id = datas.get(position).request_id;
                    viewAllContectActivity.sharefrnd_id.remove(id);
                    Log.d("sharefrnd_id_uncheck---", String.valueOf(viewAllContectActivity.sharefrnd_id));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircularImageView user_profile;
        TextView txt_name;
        CheckBox checkBox;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            user_profile = itemView.findViewById(R.id.user_profile);
            txt_name = itemView.findViewById(R.id.txt_name);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        datas.clear();
        if (charText.length() == 0) {
            datas.addAll(alldata);
        } else {
            for (data wp : alldata) {
                if (wp.username.toLowerCase(Locale.getDefault()).contains(charText)) {
                    datas.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
