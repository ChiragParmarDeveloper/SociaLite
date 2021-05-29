package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.Sync_contact;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SyncContactAdapter extends RecyclerView.Adapter<SyncContactAdapter.MyHolder> {

    Context mContext;
    public List<data> datas;
    Sync_contact sync_contact;
    data item;

    public SyncContactAdapter(Context mContext, List<data> datas, Sync_contact sync_contact) {
        this.mContext = mContext;
        this.datas = datas;
        this.sync_contact = sync_contact;
    }

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rs_sharetofrnd, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyHolder holder, int position) {

        item = datas.get(position);
        holder.checkBox.setVisibility(View.GONE);
        holder.txt_mobile.setVisibility(View.VISIBLE);
        holder.txt_name.setText(datas.get(position).name);
        holder.txt_mobile.setText(datas.get(position).number);

        holder.viewer_profile.setVisibility(View.VISIBLE);
        String avatarTitle = String.valueOf(datas.get(position).name.charAt(0)).toUpperCase();
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int randomcolor = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

        TextDrawable drawable = builder.build(avatarTitle, randomcolor);
        holder.viewer_profile.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircularImageView user_profile;
        TextView txt_name, txt_mobile;
        CheckBox checkBox;
        ImageView viewer_profile;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            user_profile = itemView.findViewById(R.id.user_profile);
            txt_name = itemView.findViewById(R.id.txt_name);
            checkBox = itemView.findViewById(R.id.checkBox);
            viewer_profile = itemView.findViewById(R.id.viewer_profile);
            txt_mobile = itemView.findViewById(R.id.txt_mobile);
        }
    }
}
