package com.ap.SociaLite.Adapter.Notification_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.SociaLite.R;

import java.util.ArrayList;

public class Notification_adapter extends RecyclerView.Adapter<Notification_adapter.MyHolder> {

    ArrayList Name;
    Context context;

    public Notification_adapter(ArrayList name, Context context) {
        Name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        String array = Name.toString();
        String Type = "connect";
//
//        for(int i = 0 ; i < Name.size() ; i++){
//            Type = (String) Name.get(i);
//
//            System.out.println("type is : " + i + " : " + Type);

            switch (Type) {
                case "accept_decline":
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_accept_decline_rs, parent, false);
                    Notification_adapter.MyHolder myholder = new Notification_adapter.MyHolder(view);
                    return myholder;

                case "connect":
                    View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_connect_rs, parent, false);
                    Notification_adapter.MyHolder holder1 = new Notification_adapter.MyHolder(view1);
                    return holder1;

                case "post":
                    View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_post_rs, parent, false);
                    Notification_adapter.MyHolder holder2 = new Notification_adapter.MyHolder(view2);
                    return holder2;
            }

       // }
//
//        System.out.println("type is : " + Type);
//        System.out.println("type is : " + viewType);



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return Name.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView notification_profile_user_1,notification_profile_user_2;
        TextView txt_interest1,txt_interest2;
        Button notification_accept,notification_decline;

        TextView notification_text;
        Button notification_connect;

        ImageView circularImageView1;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            notification_profile_user_1 = itemView.findViewById(R.id.notification_profile_user_1);
            notification_profile_user_2 = itemView.findViewById(R.id.notification_profile_user_2);
            txt_interest1 = itemView.findViewById(R.id.txt_interest1);
            txt_interest2 = itemView.findViewById(R.id.txt_interest2);
            notification_accept = itemView.findViewById(R.id.notification_accept);
            notification_decline = itemView.findViewById(R.id.notification_decline);

            notification_text = itemView.findViewById(R.id.notification_text);
            notification_connect = itemView.findViewById(R.id.notification_connect);

            circularImageView1 = itemView.findViewById(R.id.circularImageView1);

        }
    }
}
