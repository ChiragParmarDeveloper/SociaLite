package com.ap.SociaLite.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ap.SociaLite.Activity.Search;
import com.ap.SociaLite.Pojo.data;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchProfileAdapter extends RecyclerView.Adapter<SearchProfileAdapter.MyHolder> {

    Context mContext;
    public List<data> datas;
    Search search;
    List<data> alldata;
    data item;

    String id;
    private int selectedItem;

    public SearchProfileAdapter(Context mContext, List<data> datas, Search search) {
        this.mContext = mContext;
        this.datas = datas;
        this.search = search;
        selectedItem = 0;

        this.alldata = new ArrayList<>();
        this.alldata.addAll(datas);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_profile_rs, parent, false);
        SearchProfileAdapter.MyHolder myHolder = new SearchProfileAdapter.MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = datas.get(position);
        id = datas.get(position).request_id;
        holder.name.setText(item.username);

        if (search.position == position) {
          //  holder.profile.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));
            search.RequestId = datas.get(position).request_id;
            search.search_profile_user_name.setText(datas.get(position).username);

         //   search.img_pic.set(mContext.getResources().getColor(R.color.colorAccent));
            if (datas.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
                search.img_pic.setVisibility(View.VISIBLE);
                String avatarTitle = String.valueOf(datas.get(position).username.charAt(0)).toUpperCase();
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int randomcolor = generator.getRandomColor();

                TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                search.img_pic.setImageDrawable(drawable);

            } else {
                search.img_pic.setVisibility(View.GONE);
                Picasso.get().load(item.profile_pic).into(search.search_profile_image);
            }

//            if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
//                Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                search.search_profile_image.setImageDrawable(upload_img);
//            } else {
//                Picasso.get().load(item.profile_pic).into(search.search_profile_image);
//            }

            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(position).is_connected.equals("Accepted")) {
                    search.txt_connection.setText("Connected");
                } else if (datas.get(position).is_connected.equals("Requested")) {
                    search.txt_connection.setText("Requesting");
                } else {
                    search.txt_connection.setText("Connect");
                }
            }
        }


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
            Picasso.get().load(datas.get(position).profile_pic).into(holder.profile);
        }



//        if (item.profile_pic.equals("http://the-socialite.com/admin/")) {
//            Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//            holder.profile.setImageDrawable(upload_img);
//        } else {
//            Picasso.get().load(item.profile_pic).into(holder.profile);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  holder.profile.setBorderColor(mContext.getResources().getColor(R.color.colorAccent));
                search.RequestId = datas.get(position).request_id;
                search.position = (position);

                search.search_profile_user_name.setText(datas.get(position).username);

//                if (datas.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
//                    search.img_pic.setVisibility(View.VISIBLE);
//                    Drawable upload_img = mContext.getDrawable(R.drawable.ic_user_icon);
//                    search.img_pic.setImageDrawable(upload_img);
//                } else {
//                    search.img_pic.setVisibility(View.GONE);
//                    Picasso.get().load(datas.get(position).profile_pic).into(search.search_profile_image);
//                }
                if (datas.get(position).profile_pic.equals("http://the-socialite.com/admin/")) {
                    search.img_pic.setVisibility(View.VISIBLE);

                    String avatarTitle = String.valueOf(datas.get(position).username.charAt(0)).toUpperCase();
                    ColorGenerator generator = ColorGenerator.MATERIAL;
                    int randomcolor = generator.getRandomColor();

                    TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().endConfig().round();

                    TextDrawable drawable = builder.build(avatarTitle, randomcolor);
                    search.img_pic.setImageDrawable(drawable);
                } else {
                    search.img_pic.setVisibility(View.GONE);
                    Picasso.get().load(datas.get(position).profile_pic).into(search.search_profile_image);
                  }


                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(position).is_connected.equals("Accepted")) {
                        search.txt_connection.setText("Connected");
                    } else if (datas.get(position).is_connected.equals("Requested")) {
                        search.txt_connection.setText("Requesting");
                    } else {
                        search.txt_connection.setText("Connect");
                    }
                }
            }

        });


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        CircularImageView profile;
        TextView name;

        @BindView(R.id.viewer_profile)
        ImageView viewer_profile;

        @BindView(R.id.text_user_avatar_title)
        TextView text_user_avatar_title;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            profile = itemView.findViewById(R.id.user_profile);
            name = itemView.findViewById(R.id.user_name);

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
