package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.ShareToFriend;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SharetoFrndAdapter extends RecyclerView.Adapter<SharetoFrndAdapter.MyHolder> {

    Context mContext;
    public List<data> datas;
    ShareToFriend shareToFriend;
    List<data> alldata;
    data item;
//asdasd
    public SharetoFrndAdapter(Context mContext, List<data> datas, ShareToFriend shareToFriend) {
        this.mContext = mContext;
        this.datas = datas;
        this.shareToFriend = shareToFriend;

        this.alldata = new ArrayList<>();
        this.alldata.addAll(datas);
    }


    @NonNull
    @Override
    public SharetoFrndAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rs_sharetofrnd, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SharetoFrndAdapter.MyHolder holder, int position) {
        item = datas.get(position);
        holder.txt_name.setText(item.username);

        if (datas.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {

            holder.viewer_profile.setVisibility(View.VISIBLE);
            String avatarTitle = String.valueOf(datas.get(position).username.charAt(0)).toUpperCase();
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int randomcolor = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

            TextDrawable drawable = builder.build(avatarTitle, randomcolor);
            holder.viewer_profile.setImageDrawable(drawable);
        } else {
            holder.viewer_profile.setVisibility(View.GONE);
            Picasso.get().load(datas.get(position).profile_pic).into(holder.user_profile);
        }

//        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
//            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//            holder.user_profile.setImageDrawable(upload_img);
//        } else {
//            Picasso.get().load(item.profile_pic).into(holder.user_profile);
//        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()) {

                    String id = datas.get(position).request_id;
                    shareToFriend.sharefrnd_id.add(id);
                 //   id = shareToFriend.sharefrnd_id.stream().collect(Collectors.joining(","));

                    Log.d("sharefrnd_id_check", String.valueOf(shareToFriend.sharefrnd_id));
                   // Log.d("id_check---", String.valueOf(id));

                } else {
                    String id = datas.get(position).request_id;

                    shareToFriend.sharefrnd_id.remove(id);
                //    id = shareToFriend.sharefrnd_id.stream().collect(Collectors.joining(","));

                    Log.d("sharefrnd_id_uncheck---", String.valueOf(shareToFriend.sharefrnd_id));
              //      Log.d("id_uncheck---", String.valueOf(id));

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
        ImageView viewer_profile;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            user_profile = itemView.findViewById(R.id.user_profile);
            txt_name = itemView.findViewById(R.id.txt_name);
            checkBox = itemView.findViewById(R.id.checkBox);
            viewer_profile= itemView.findViewById(R.id.viewer_profile);
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
