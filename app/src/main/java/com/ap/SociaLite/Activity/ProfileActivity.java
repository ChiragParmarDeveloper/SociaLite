package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Fragment.Profile_fragments.BusinessInteractionFragment;
import com.ap.SociaLite.Fragment.Profile_fragments.TimeLineFragment;
import com.ap.SociaLite.Presenter.EditProfilePresenter;
import com.ap.SociaLite.Presenter.ProfileActivityPresenter;
import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.constraint_setting)
    ConstraintLayout constraint_setting;

    @BindView(R.id.connections)
    ConstraintLayout connections;

    @BindView(R.id.profile_add_cover)
    ImageView profile_add_cover;

    @BindView(R.id.timeline_btn)
    Button timeline_btn;

    @BindView(R.id.business_btn)
    Button business_btn;

    @BindView(R.id.spotlight_btn)
    Button spotlight_btn;

    @BindView(R.id.frame_profile)
    FrameLayout frame_profile;

    @BindView(R.id.circularImageView6)
    public CircularImageView circularImageView6;

    @BindView(R.id.imageView12)
    public ImageView imageView12;

    @BindView(R.id.user_name)
    public TextView user_name;

    @BindView(R.id.textView16)
    public TextView textView16;

    @BindView(R.id.textView10)
    public TextView textView10;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    String image,user_id;
    MultipartBody.Part cover_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        Session session = new Session(ProfileActivity.this);
        user_id=session.getUser_id();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new TimeLineFragment()).commit();

        new ProfileActivityPresenter(this, this).profile_my_profile(session.getUser_id());
    }

    @OnClick({R.id.img_back, R.id.constraint_setting, R.id.connections, R.id.profile_add_cover, R.id.timeline_btn, R.id.business_btn, R.id.spotlight_btn})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.constraint_setting:
                Intent edit = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(edit);
                break;

            case R.id.connections:
                Intent connection = new Intent(ProfileActivity.this, ProfileConnectionsActivity.class);
                startActivity(connection);
                break;

            case R.id.profile_add_cover:
                openGallery();
                break;

            case R.id.timeline_btn:
                timeline_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                timeline_btn.setTextColor(Color.WHITE);
                business_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                business_btn.setTextColor(Color.BLACK);
                spotlight_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                spotlight_btn.setTextColor(Color.BLACK);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new TimeLineFragment()).commit();
                break;

            case R.id.business_btn:
                business_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                business_btn.setTextColor(Color.WHITE);
                timeline_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                timeline_btn.setTextColor(Color.BLACK);
                spotlight_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                spotlight_btn.setTextColor(Color.BLACK);

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_profile, new BusinessInteractionFragment()).commit();
                break;

            case R.id.spotlight_btn:
                spotlight_btn.setBackgroundColor(Color.parseColor("#EE6851"));
                spotlight_btn.setTextColor(Color.WHITE);
                timeline_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                timeline_btn.setTextColor(Color.BLACK);
                business_btn.setBackground(getResources().getDrawable(R.drawable.border_square_rs));
                business_btn.setTextColor(Color.BLACK);

                Intent spotlight = new Intent(ProfileActivity.this, SpotlightActivityForUser.class);
                startActivity(spotlight);
                break;
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView12.setImageURI(imageUri);
            image = getRealPathFromURI(imageUri);

            if (image != null) {
                File file = new File(image);
                RequestBody picture = RequestBody.create(MediaType.parse("image/*"), file);
                cover_photo = MultipartBody.Part.createFormData("cover_photo", file.getPath(), picture);
            } else {
                RequestBody picture = RequestBody.create(MediaType.parse("image/*"), "");
                cover_photo = MultipartBody.Part.createFormData("cover_photo", "", picture);
            }

            RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"), user_id);
            new ProfileActivityPresenter(this, this).cover_photo(u_id,cover_photo);


        } else {

        }
    }

    public String getRealPathFromURI(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}