package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Presenter.PostBusinessPresenter;
import com.ap.SociaLite.Presenter.PostPresenter;
import com.ap.SociaLite.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PostBusiness extends AppCompatActivity {

    @BindView(R.id.btn_share)
    Button btn_share;

    @BindView(R.id.business_create_your_card)
    Button business_create_your_card;

    @BindView(R.id.business_upload_your_card)
    Button business_upload_your_card;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.imageView8)
    ImageView imageView8;

    @BindView(R.id.constraintLayout67)
    ConstraintLayout constraintLayout67;

    @BindView(R.id.spinner)
    public Spinner spinner;

    @BindView(R.id.img_one)
    ImageView img_one;

    @BindView(R.id.img_two)
    ImageView img_two;

    @BindView(R.id.img_three)
    ImageView img_three;

    @BindView(R.id.layout_img1)
    LinearLayout layout_img1;

    @BindView(R.id.layout_img2)
    LinearLayout layout_img2;

    @BindView(R.id.layout_img3)
    LinearLayout layout_img3;

    @BindView(R.id.video_one)
    VideoView video_one;

    @BindView(R.id.video)
    VideoView video;

    @BindView(R.id.video_two)
    VideoView video_two;

    @BindView(R.id.video_three)
    VideoView video_three;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.edt_description)
    EditText edt_description;

    @BindView(R.id.edt_location)
    EditText edt_location;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    String img_url;
    Uri mSaveImageUri;
    boolean click = true;
    String imagetwo, imageone, imagethree, picture_path;
    ArrayList<String> mArrayUri = new ArrayList<String>();
    private static final int PICK_IMAGE = 100;
    Uri imageUri;



   // MultipartBody.Part image;
    MultipartBody.Part[] image;
    MultipartBody.Part[] post_image;
    ArrayList<String> share_List = new ArrayList<>();
    ArrayList<String> hide_list = new ArrayList<>();
    String user_id,date, time;
    public String selected_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_business);
        ButterKnife.bind(this);

        Session session = new Session(PostBusiness.this);
        user_id = session.getUser_id();

        img_url = getIntent().getStringExtra("img_url");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        share_List = (ArrayList<String>) getIntent().getSerializableExtra("finallist");
        //   Log.d("finallist_post", String.valueOf(share_List));

        if (img_url != null) {
            mSaveImageUri = Uri.fromFile(new File(img_url));
            imageView.setImageURI(mSaveImageUri);
        }


//        imageone = getIntent().getStringExtra("img1");
//        imagetwo = getIntent().getStringExtra("img2");
//        imagethree = getIntent().getStringExtra("img3");
//
//        if (imageone != null) {
//            layout_img1.setVisibility(View.VISIBLE);
//            File imgFile = new File(imageone);
//
//            if (imgFile.exists()) {
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                img_one.setImageBitmap(myBitmap);
//                imageView.setTag(imgFile.toString());
//
//            }
//        }
//        if (imagetwo != null) {
//            layout_img2.setVisibility(View.VISIBLE);
//            File imgFile = new File(imagetwo);
//
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                img_two.setImageBitmap(myBitmap);
//            }
//        }
//
//        if (imagethree != null) {
//
//            layout_img3.setVisibility(View.VISIBLE);
//            File imgFile = new File(imagethree);
//
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                imageView.setImageBitmap(myBitmap);
//                img_three.setImageBitmap(myBitmap);
//            }
//        }
        new PostBusinessPresenter(this, this).fetch_my_intrest(session.getUser_id());
    }

    @OnClick({R.id.img_back, R.id.btn_share, R.id.imageView8, R.id.img_one, R.id.img_two, R.id.img_three,
            R.id.business_create_your_card, R.id.business_upload_your_card})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.btn_share:
                RequestBody id = RequestBody.create(MediaType.parse("text/plain"), user_id);
                RequestBody description = RequestBody.create(MediaType.parse("text/plain"), edt_description.getText().toString().trim());
                RequestBody interest_id = RequestBody.create(MediaType.parse("text/plain"), selected_id);
                //     RequestBody shared_user = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(share_List));

                RequestBody time_;
                RequestBody date_;
                if (date != null) {
                    date_ = RequestBody.create(MediaType.parse("text/plain"), date);
                } else {
                    date_ = RequestBody.create(MediaType.parse("text/plain"), "");
                }

                if (time != null) {
                    time_ = RequestBody.create(MediaType.parse("text/plain"), time);
                } else {
                    time_ = RequestBody.create(MediaType.parse("text/plain"), "");
                }


                RequestBody business = RequestBody.create(MediaType.parse("text/plain"), "1");
                RequestBody location = RequestBody.create(MediaType.parse("text/plain"), edt_location.getText().toString().trim());
                RequestBody hide_user = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(hide_list));
                RequestBody share_user = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(share_List));


                mArrayUri.add(img_url);

                post_image = new MultipartBody.Part[mArrayUri.size()];

                for (int index = 0; index < mArrayUri.size(); index++) {
                    Log.d("Upload request", "image " + index + "  " + mArrayUri.get(index));
                    File file2 = new File(mArrayUri.get(index));
                    RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
                    post_image[index] = MultipartBody.Part.createFormData("post_image[]", file2.getPath(), surveyBody);
                }

                new PostBusinessPresenter(this, this).post(id, post_image, description, interest_id,
                        business, location, hide_user, share_user, date_, time_);

                break;

            case R.id.imageView8:

                if (click) {
                    constraintLayout67.setVisibility(View.VISIBLE);
                    click = false;
                } else {
                    constraintLayout67.setVisibility(View.INVISIBLE);
                    click = true;
                }
                break;

            case R.id.business_create_your_card:
                startActivity(new Intent(PostBusiness.this, AddCardActivity.class));
                break;

            case R.id.business_upload_your_card:
           //     openGallery();
                break;

//            case R.id.img_one:
//                if (imageone != null) {
//                    File imgone = new File(imageone);
//                    if (imgone.exists()) {
//                        //        imageView.setImageURI(Uri.fromFile(imgone));
//
//                        Bitmap myBitmap = BitmapFactory.decodeFile(imgone.getAbsolutePath());
//                        imageView.setImageBitmap(myBitmap);
//                        imageView.setTag(imgone.toString());
//                    }
//                }
//
//                break;

//            case R.id.img_two:
//
//                if (imagetwo != null) {
//                    File imgtwo = new File(imagetwo);
//                    if (imgtwo.exists()) {
//                        //   imageView.setImageURI(Uri.fromFile(imgtwo));
//                        Bitmap myBitmap = BitmapFactory.decodeFile(imgtwo.getAbsolutePath());
//                        imageView.setImageBitmap(myBitmap);
//
//                        imageView.setTag(imgtwo.toString());
//                        //     path = imageView.getTag().toString();
//
//                    }
//                }
//                break;

//            case R.id.img_three:
//
//                if (imagethree != null) {
//                    File imgthree = new File(imagethree);
//                    if (imgthree.exists()) {
//                        //       imageView.setImageURI(Uri.fromFile(imgthree));
//                        Bitmap myBitmap = BitmapFactory.decodeFile(imgthree.getAbsolutePath());
//                        imageView.setImageBitmap(myBitmap);
//
//                        imageView.setTag(imgthree.toString());
//                        //    path = imageView.getTag().toString();
//                    }
//                }
//                break;
        }
    }

//    private void openGallery() {
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //Result if we want hint number
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
//            imageUri = data.getData();
//            picture_path = getRealPathFromURI(imageUri);
//            File f = new File(picture_path);
//            mArrayUri.add(picture_path);
//
//            image = new MultipartBody.Part[mArrayUri.size()];
//
//            for (int index = 0; index < mArrayUri.size(); index++) {
//                Log.d("Upload request", "image " + index + "  " + mArrayUri.get(index));
//                File file2 = new File(mArrayUri.get(index));
//                RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
//                image[index] = MultipartBody.Part.createFormData("image[]", file2.getPath(), surveyBody);
//            }
//
//            Session session = new Session(PostBusiness.this);
//            RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), session.getUser_id());
//
//            new PostBusinessPresenter(this, this).upload_card(image, user_id);
//
//        } else {
//
//        }
//    }

//    public String getRealPathFromURI(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        @SuppressWarnings("deprecation")
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor
//                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
}