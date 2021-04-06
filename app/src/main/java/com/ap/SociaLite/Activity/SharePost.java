package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharePost extends AppCompatActivity {

    @BindView(R.id.checkBox_pos_1)
    CheckBox checkBox_pos_1;

    @BindView(R.id.img_pos_1)
    CircularImageView img_pos_1;

    @BindView(R.id.txt_name_pos_1)
    TextView txt_name_pos_1;

    @BindView(R.id.constraintLayout54)
    ConstraintLayout constraintLayout54;

    @BindView(R.id.constraintLayout55)
    ConstraintLayout constraintLayout55;

    @BindView(R.id.checkBox_pos_2)
    CheckBox checkBox_pos_2;

    @BindView(R.id.img_pos_2)
    CircularImageView img_pos_2;

    @BindView(R.id.txt_name_pos_2)
    TextView txt_name_pos_2;

    @BindView(R.id.constraintLayout56)
    ConstraintLayout constraintLayout56;

    @BindView(R.id.checkBox_pos_3)
    CheckBox checkBox_pos_3;

    @BindView(R.id.img_pos_3)
    CircularImageView img_pos_3;

    @BindView(R.id.txt_name_pos_3)
    TextView txt_name_pos_3;

    @BindView(R.id.imageView)
    ImageView imageView;

    private Bitmap bitmap;
    String image;
    String UserId;
    @Nullable
    @VisibleForTesting
    Uri mSaveImageUri;
    String img_url;

    //public static ArrayList<String> mFinalList = new ArrayList<>();
    public ArrayList<String> share_frnd_ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_post);
        ButterKnife.bind(this);
        Session session = new Session(getApplicationContext());
        UserId = session.getUser_id();

//        image = getIntent().getStringExtra("img2");
//        if (image != null) {
//            File imgFile = new File(image);
//            if (imgFile.exists()) {
//                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(bitmap);
//                imageView.setTag(imgFile.toString());
//            }
//        }

        img_url = getIntent().getStringExtra("img_url");

        if (img_url != null) {
            mSaveImageUri = Uri.fromFile(new File(img_url));
            imageView.setImageURI(mSaveImageUri);
        }
        friend_list(UserId);
    }

    private void friend_list(String UserId) {

        try {
            new RService.api().call(SharePost.this).frnd_list(UserId).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {
                    if (response.body().status.equals("1")) {
                        if (response.body().data != null && response.body().data.size() > 0) {

                            txt_name_pos_1.setText(response.body().data.get(response.body().data.size() - 1).username);

                            String img0 = response.body().data.get(response.body().data.size() - 1).profile_pic;
                            Picasso.get().load(img0).into(img_pos_1);

                            checkBox_pos_1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (checkBox_pos_1.isChecked()) {
                                        String id = response.body().data.get(response.body().data.size() - 1).request_id;
                                        share_frnd_ids.add(id);
                                        Log.d("share_frnd_check-----", String.valueOf(share_frnd_ids));
                                    } else {
                                        String id = response.body().data.get(response.body().data.size() - 1).request_id;
                                        share_frnd_ids.remove(id);
                                        Log.d("share_frnd_uncheck--", String.valueOf(share_frnd_ids));

                                    }
                                }
                            });


                        } else {
                            constraintLayout54.setVisibility(View.GONE);
                        }

                        if (response.body().data != null && response.body().data.size() > 1) {

                            txt_name_pos_2.setText(response.body().data.get(response.body().data.size() - 2).username);

                            String img1 = response.body().data.get(response.body().data.size() - 2).profile_pic;
                            Picasso.get().load(img1).into(img_pos_2);

                            checkBox_pos_2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (checkBox_pos_2.isChecked()) {
                                        String id = response.body().data.get(response.body().data.size() - 2).request_id;
                                        share_frnd_ids.add(id);
                                        Log.d("share_frnd_check-----", String.valueOf(share_frnd_ids));
                                    } else {
                                        String id = response.body().data.get(response.body().data.size() - 2).request_id;
                                        share_frnd_ids.remove(id);
                                        Log.d("share_frnd_uncheck--", String.valueOf(share_frnd_ids));

                                    }
                                }
                            });

                        } else {
                            constraintLayout55.setVisibility(View.GONE);
                        }

                        if (response.body().data != null && response.body().data.size() > 2) {

                            txt_name_pos_3.setText(response.body().data.get(response.body().data.size() - 3).username);

                            String img2 = response.body().data.get(response.body().data.size() - 3).profile_pic;
                            Picasso.get().load(img2).into(img_pos_3);


                            checkBox_pos_3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (checkBox_pos_3.isChecked()) {
                                        String id = response.body().data.get(response.body().data.size() - 3).request_id;
                                        share_frnd_ids.add(id);
                                        Log.d("share_frnd_check-----", String.valueOf(share_frnd_ids));
                                    } else {
                                        String id = response.body().data.get(response.body().data.size() - 3).request_id;
                                        share_frnd_ids.remove(id);
                                        Log.d("share_frnd_uncheck--", String.valueOf(share_frnd_ids));

                                    }
                                }
                            });


                        } else {
                            constraintLayout56.setVisibility(View.GONE);
                        }

                    } else {
                        constraintLayout54.setVisibility(View.GONE);
                        constraintLayout55.setVisibility(View.GONE);
                        constraintLayout56.setVisibility(View.GONE);
                        //    Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //     Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }

    @OnClick({R.id.img_cross, R.id.btn_next, R.id.btn_save, R.id.All_contact})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_cross:
                finish();
                break;

            case R.id.btn_next:
                startActivity(new Intent(SharePost.this, Post.class)
                        .putExtra("img_url", img_url)
                        .putExtra("finallist", share_frnd_ids));
                share_frnd_ids.clear();
                break;

            case R.id.All_contact:
                Intent i = new Intent(this, ViewAllContectActivity.class);
                startActivityForResult(i, 1);
                share_frnd_ids.clear();
                break;

            case R.id.btn_save:
                Intent intent = new Intent();
                intent.putExtra("finallist", share_frnd_ids);
                setResult(RESULT_OK, intent);
                finish();
                share_frnd_ids.clear();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                share_frnd_ids = (ArrayList<String>) data.getSerializableExtra("finallist");
                Log.d("finalviewall++++", String.valueOf(share_frnd_ids));
            }
        }
    }
}