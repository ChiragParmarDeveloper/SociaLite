package com.ap.SociaLite.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Fragment.BusinessFragment;
import com.ap.SociaLite.Fragment.CategoryFragment;
import com.ap.SociaLite.Fragment.InterestFragment;
import com.ap.SociaLite.Fragment.NetworkFragment;
import com.ap.SociaLite.Fragment.ShareFragment;
import com.ap.SociaLite.Presenter.HomeActivityPresenter;
import com.ap.SociaLite.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.layout_category)
    LinearLayout layout_category;

    @BindView(R.id.img_category)
    ImageView img_category;

    @BindView(R.id.txt_category)
    TextView txt_category;

    @BindView(R.id.txt_categorylist)
    TextView txt_categorylist;

    @BindView(R.id.layout_interest)
    LinearLayout layout_interest;

    @BindView(R.id.img_interest)
    ImageView img_interest;

    @BindView(R.id.txt_interest)
    TextView txt_interest;

    @BindView(R.id.layout_network)
    LinearLayout layout_network;

    @BindView(R.id.img_network)
    ImageView img_network;

    @BindView(R.id.txt_network)
    TextView txt_network;

    @BindView(R.id.layout_share)
    LinearLayout layout_share;

    @BindView(R.id.img_share)
    ImageView img_share;

    @BindView(R.id.txt_share)
    TextView txt_share;

    @BindView(R.id.layout_business)
    LinearLayout layout_business;

    @BindView(R.id.img_business)
    ImageView img_business;

    @BindView(R.id.txt_business)
    TextView txt_business;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.img_leftmenu)
    ImageView img_leftmenu;

    @BindView(R.id.navigation_view)
    NavigationView navigation_view;

    @BindView(R.id.imgsearch)
    ImageView imgsearch;

    @BindView(R.id.imgnotification)
    ImageView imgnotification;

    private int REQUEST_CODE = 1;
    public TextView txt_name, txt_email, txt_category1, txt_notification, txt_profile, txt_help, txt_faq, txt_setting, txt_logout;
    ImageView img_category1, img_notification, img_profile, img_help, img_faq, img_setting, img_logout,img_arrow;
    public CircularImageView img_dp;

    LinearLayout Notification,category, Profile, help, faq, setting, logout;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Session session = new Session(HomeActivity.this);
        user_id = session.getUser_id();

        Log.d("token_Socialite", FirebaseInstanceId.getInstance().getToken());

        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_home, new CategoryFragment()).commit();
        View headerView = navigation_view.getHeaderView(0);

        category = (LinearLayout) headerView.findViewById(R.id.category);
        txt_category1 = (TextView) headerView.findViewById(R.id.txt_category1);
        img_category1 = (ImageView) headerView.findViewById(R.id.img_category1);

        Notification = (LinearLayout) headerView.findViewById(R.id.Notification);
        txt_notification = (TextView) headerView.findViewById(R.id.txt_notification);
        img_notification = (ImageView) headerView.findViewById(R.id.img_notification);

        Profile = (LinearLayout) headerView.findViewById(R.id.Profile);
        txt_profile = (TextView) headerView.findViewById(R.id.txt_profile);
        img_profile = (ImageView) headerView.findViewById(R.id.img_profile);

        help = (LinearLayout) headerView.findViewById(R.id.help);
        txt_help = (TextView) headerView.findViewById(R.id.txt_help);
        img_help = (ImageView) headerView.findViewById(R.id.img_help);

        faq = (LinearLayout) headerView.findViewById(R.id.faq);
        txt_faq = (TextView) headerView.findViewById(R.id.txt_faq);
        img_faq = (ImageView) headerView.findViewById(R.id.img_faq);

        setting = (LinearLayout) headerView.findViewById(R.id.setting);
        txt_setting = (TextView) headerView.findViewById(R.id.txt_setting);
        img_setting = (ImageView) headerView.findViewById(R.id.img_setting);

        logout = (LinearLayout) headerView.findViewById(R.id.logout);
        txt_logout = (TextView) headerView.findViewById(R.id.txt_logout);
        img_logout = (ImageView) headerView.findViewById(R.id.img_logout);

        txt_name = (TextView) headerView.findViewById(R.id.txt_name);
        txt_email = (TextView) headerView.findViewById(R.id.txt_email);
        img_dp = (CircularImageView) headerView.findViewById(R.id.img_dp);

        img_arrow = (ImageView) headerView.findViewById(R.id.img_arrow);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(HomeActivity.this);
                dialog.setContentView(R.layout.custom_dailog);

                Button yes, no;
                TextView text;
                text = dialog.findViewById(R.id.dailog_text);
                yes = dialog.findViewById(R.id.dailog_yes);
                no = dialog.findViewById(R.id.dailog_no);

                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new Session(HomeActivity.this).removeuser();
                        Intent logout = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(logout);
                        finish();
                        dialog.dismiss();

                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                txt_logout.setTextColor(getResources().getColor(R.color.colorBlack));
                img_logout.setImageResource(R.drawable.ic_logout_black);

                txt_category1.setTextColor(getResources().getColor(R.color.colorWhite));
                img_category1.setImageResource(R.drawable.ic_category_white);

                txt_notification.setTextColor(getResources().getColor(R.color.colorWhite));
                img_notification.setImageResource(R.drawable.ic_notification_white);

                txt_profile.setTextColor(getResources().getColor(R.color.colorWhite));
                img_profile.setImageResource(R.drawable.ic_user_white);

                txt_help.setTextColor(getResources().getColor(R.color.colorWhite));
                img_help.setImageResource(R.drawable.ic_help);

                txt_faq.setTextColor(getResources().getColor(R.color.colorWhite));
                img_faq.setImageResource(R.drawable.ic_faq);

                txt_setting.setTextColor(getResources().getColor(R.color.colorWhite));
                img_setting.setImageResource(R.drawable.ic_setting_white);

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_faq.setTextColor(getResources().getColor(R.color.colorWhite));
                img_faq.setImageResource(R.drawable.ic_faq);

                txt_logout.setTextColor(getResources().getColor(R.color.colorWhite));
                img_logout.setImageResource(R.drawable.ic_logout);

                txt_setting.setTextColor(getResources().getColor(R.color.colorBlack));
                img_setting.setImageResource(R.drawable.ic_setting);

                txt_category1.setTextColor(getResources().getColor(R.color.colorWhite));
                img_category1.setImageResource(R.drawable.ic_category_white);

                txt_notification.setTextColor(getResources().getColor(R.color.colorWhite));
                img_notification.setImageResource(R.drawable.ic_notification_white);

                txt_profile.setTextColor(getResources().getColor(R.color.colorWhite));
                img_profile.setImageResource(R.drawable.ic_user_white);

                txt_help.setTextColor(getResources().getColor(R.color.colorWhite));
                img_help.setImageResource(R.drawable.ic_help);

                startActivity(new Intent(HomeActivity.this, Setting.class));


            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_setting.setTextColor(getResources().getColor(R.color.colorWhite));
                img_setting.setImageResource(R.drawable.ic_setting_white);

                txt_logout.setTextColor(getResources().getColor(R.color.colorWhite));
                img_logout.setImageResource(R.drawable.ic_logout);


                txt_faq.setTextColor(getResources().getColor(R.color.colorBlack));
                img_faq.setImageResource(R.drawable.ic_faq_black);

                txt_category1.setTextColor(getResources().getColor(R.color.colorWhite));
                img_category1.setImageResource(R.drawable.ic_category_white);

                txt_notification.setTextColor(getResources().getColor(R.color.colorWhite));
                img_notification.setImageResource(R.drawable.ic_notification_white);

                txt_profile.setTextColor(getResources().getColor(R.color.colorWhite));
                img_profile.setImageResource(R.drawable.ic_user_white);

                txt_help.setTextColor(getResources().getColor(R.color.colorWhite));
                img_help.setImageResource(R.drawable.ic_help);

                startActivity(new Intent(HomeActivity.this, Faq.class));

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(HomeActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

                txt_faq.setTextColor(getResources().getColor(R.color.colorWhite));
                img_faq.setImageResource(R.drawable.ic_faq);

                txt_help.setTextColor(getResources().getColor(R.color.colorBlack));
                img_help.setImageResource(R.drawable.ic_help_black);

                txt_profile.setTextColor(getResources().getColor(R.color.colorWhite));
                img_profile.setImageResource(R.drawable.ic_user_white);

                txt_category1.setTextColor(getResources().getColor(R.color.colorWhite));
                img_category1.setImageResource(R.drawable.ic_category_white);

                txt_notification.setTextColor(getResources().getColor(R.color.colorWhite));
                img_notification.setImageResource(R.drawable.ic_notification_white);

                txt_setting.setTextColor(getResources().getColor(R.color.colorWhite));
                img_setting.setImageResource(R.drawable.ic_setting_white);

                txt_logout.setTextColor(getResources().getColor(R.color.colorWhite));
                img_logout.setImageResource(R.drawable.ic_logout);


            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_profile.setTextColor(getResources().getColor(R.color.colorBlack));
                img_profile.setImageResource(R.drawable.ic_user);

                txt_category1.setTextColor(getResources().getColor(R.color.colorWhite));
                img_category1.setImageResource(R.drawable.ic_category_white);

                txt_notification.setTextColor(getResources().getColor(R.color.colorWhite));
                img_notification.setImageResource(R.drawable.ic_notification_white);

                txt_help.setTextColor(getResources().getColor(R.color.colorWhite));
                img_help.setImageResource(R.drawable.ic_help);

                txt_faq.setTextColor(getResources().getColor(R.color.colorWhite));
                img_faq.setImageResource(R.drawable.ic_faq);

                txt_setting.setTextColor(getResources().getColor(R.color.colorWhite));
                img_setting.setImageResource(R.drawable.ic_setting_white);

                txt_logout.setTextColor(getResources().getColor(R.color.colorWhite));
                img_logout.setImageResource(R.drawable.ic_logout);

                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt_notification.setTextColor(getResources().getColor(R.color.colorBlack));
                img_notification.setImageResource(R.drawable.ic_notifications);

                txt_profile.setTextColor(getResources().getColor(R.color.colorWhite));
                img_profile.setImageResource(R.drawable.ic_user_white);

                txt_category1.setTextColor(getResources().getColor(R.color.colorWhite));
                img_category1.setImageResource(R.drawable.ic_category_white);

                txt_help.setTextColor(getResources().getColor(R.color.colorWhite));
                img_help.setImageResource(R.drawable.ic_help);

                txt_logout.setTextColor(getResources().getColor(R.color.colorWhite));
                img_logout.setImageResource(R.drawable.ic_logout);

                txt_faq.setTextColor(getResources().getColor(R.color.colorWhite));
                img_faq.setImageResource(R.drawable.ic_faq);

                txt_setting.setTextColor(getResources().getColor(R.color.colorWhite));
                img_setting.setImageResource(R.drawable.ic_setting_white);

                startActivity(new Intent(HomeActivity.this, Notification.class));

            }
        });

        img_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    drawer_layout.closeDrawers();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(getApplicationContext(), InterestActivity.class));

                txt_category1.setTextColor(getResources().getColor(R.color.colorBlack));
                img_category1.setImageResource(R.drawable.ic_img_categoryblack);

                txt_notification.setTextColor(getResources().getColor(R.color.colorWhite));
                img_notification.setImageResource(R.drawable.ic_notification_white);

                txt_profile.setTextColor(getResources().getColor(R.color.colorWhite));
                img_profile.setImageResource(R.drawable.ic_user_white);

                txt_help.setTextColor(getResources().getColor(R.color.colorWhite));
                img_help.setImageResource(R.drawable.ic_help);

                txt_faq.setTextColor(getResources().getColor(R.color.colorWhite));
                img_faq.setImageResource(R.drawable.ic_faq);

                txt_setting.setTextColor(getResources().getColor(R.color.colorWhite));
                img_setting.setImageResource(R.drawable.ic_setting_white);

                txt_logout.setTextColor(getResources().getColor(R.color.colorWhite));
                img_logout.setImageResource(R.drawable.ic_logout);


                //    Toast.makeText(getApplicationContext(), "nice work", Toast.LENGTH_LONG).show();
            }
        });

        //NOTIFY USER THAT UPDATE IS AVILBLE FOR NEW VERSION
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(HomeActivity.this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {

                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        // For a flexible update, use AppUpdateType.FLEXIBLE
                        && result.isUpdateTypeAllowed(IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(result, IMMEDIATE, HomeActivity.this, REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                    // Request the update.
                }
            }
        });

        new HomeActivityPresenter(this,this).save_token(user_id,FirebaseInstanceId.getInstance().getToken());
        new HomeActivityPresenter(this, this).fetch_profile(user_id);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            //      Toast.makeText(this, "Start Download", Toast.LENGTH_SHORT).show();
            if (resultCode != RESULT_OK) {
                //         Log.d("update","Update flow failed! Result code: " + resultCode);
                // If the update is cancelled or fails,
                // you can request to start the update again.
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        new HomeActivityPresenter(this, this).fetch_profile(user_id);
    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.layout_category, R.id.layout_interest, R.id.layout_network, R.id.layout_share, R.id.layout_business, R.id.img_leftmenu,
            R.id.imgsearch,R.id.imgnotification})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.layout_category:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_home, new CategoryFragment()).commit();

                txt_categorylist.setText("Dashboard");

                imgsearch.setVisibility(View.VISIBLE);
                imgnotification.setVisibility(View.GONE);

                img_category.setImageResource(R.drawable.ic_img_category);
                txt_category.setTextColor(getResources().getColor(R.color.colororange));

                img_interest.setImageResource(R.drawable.ic_img_interest);
                txt_interest.setTextColor(getResources().getColor(R.color.colorBlack));

                img_network.setImageResource(R.drawable.ic_img_network);
                txt_network.setTextColor(getResources().getColor(R.color.colorBlack));

                img_share.setImageResource(R.drawable.ic_img_sharecare);
                txt_share.setTextColor(getResources().getColor(R.color.colorBlack));

                img_business.setImageResource(R.drawable.ic_img_business);
                txt_business.setTextColor(getResources().getColor(R.color.colorBlack));
                break;

            case R.id.layout_interest:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_home, new InterestFragment()).commit();

                txt_categorylist.setText("My Interest");

                imgsearch.setVisibility(View.VISIBLE);
                imgnotification.setVisibility(View.GONE);

                img_interest.setImageResource(R.drawable.interest_png);
                txt_interest.setTextColor(getResources().getColor(R.color.colororange));

                img_category.setImageResource(R.drawable.ic_img_categoryblack);
                txt_category.setTextColor(getResources().getColor(R.color.colorBlack));

                img_network.setImageResource(R.drawable.ic_img_network);
                txt_network.setTextColor(getResources().getColor(R.color.colorBlack));

                img_share.setImageResource(R.drawable.ic_img_sharecare);
                txt_share.setTextColor(getResources().getColor(R.color.colorBlack));

                img_business.setImageResource(R.drawable.ic_img_business);
                txt_business.setTextColor(getResources().getColor(R.color.colorBlack));
                break;

            case R.id.layout_network:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_home, new NetworkFragment()).commit();

                txt_categorylist.setText("My Network");

                imgsearch.setVisibility(View.VISIBLE);
                imgnotification.setVisibility(View.GONE);

                img_network.setImageResource(R.drawable.network_png);
                txt_network.setTextColor(getResources().getColor(R.color.colororange));

                img_interest.setImageResource(R.drawable.ic_img_interest);
                txt_interest.setTextColor(getResources().getColor(R.color.colorBlack));

                img_category.setImageResource(R.drawable.ic_img_categoryblack);
                txt_category.setTextColor(getResources().getColor(R.color.colorBlack));

                img_share.setImageResource(R.drawable.ic_img_sharecare);
                txt_share.setTextColor(getResources().getColor(R.color.colorBlack));

                img_business.setImageResource(R.drawable.ic_img_business);
                txt_business.setTextColor(getResources().getColor(R.color.colorBlack));

                break;

            case R.id.layout_share:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_home, new ShareFragment()).commit();

                txt_categorylist.setText("Share & Care");

                imgsearch.setVisibility(View.GONE);
                imgnotification.setVisibility(View.VISIBLE);

                img_share.setImageResource(R.drawable.share_png);
                txt_share.setTextColor(getResources().getColor(R.color.colororange));

                img_business.setImageResource(R.drawable.ic_img_business);
                txt_business.setTextColor(getResources().getColor(R.color.colorBlack));

                img_network.setImageResource(R.drawable.ic_img_network);
                txt_network.setTextColor(getResources().getColor(R.color.colorBlack));

                img_interest.setImageResource(R.drawable.ic_img_interest);
                txt_interest.setTextColor(getResources().getColor(R.color.colorBlack));

                img_category.setImageResource(R.drawable.ic_img_categoryblack);
                txt_category.setTextColor(getResources().getColor(R.color.colorBlack));

                break;

            case R.id.layout_business:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_home, new BusinessFragment()).commit();

                txt_categorylist.setText("Business Interaction");

                imgsearch.setVisibility(View.VISIBLE);
                imgnotification.setVisibility(View.GONE);

                img_business.setImageResource(R.drawable.business_png);
                txt_business.setTextColor(getResources().getColor(R.color.colororange));

                img_interest.setImageResource(R.drawable.ic_img_interest);
                txt_interest.setTextColor(getResources().getColor(R.color.colorBlack));

                img_category.setImageResource(R.drawable.ic_img_categoryblack);
                txt_category.setTextColor(getResources().getColor(R.color.colorBlack));

                img_share.setImageResource(R.drawable.ic_img_sharecare);
                txt_share.setTextColor(getResources().getColor(R.color.colorBlack));

                img_network.setImageResource(R.drawable.ic_img_network);
                txt_network.setTextColor(getResources().getColor(R.color.colorBlack));
                break;

            case R.id.img_leftmenu:
                if (!drawer_layout.isDrawerOpen(GravityCompat.START))
                    drawer_layout.openDrawer(GravityCompat.START);
                else drawer_layout.closeDrawer(GravityCompat.END);
                break;

            case R.id.imgsearch:
                startActivity(new Intent(HomeActivity.this, Search.class));
                break;
            case R.id.imgnotification:
                startActivity(new Intent(HomeActivity.this, Notification.class));
                break;




        }
    }

}
