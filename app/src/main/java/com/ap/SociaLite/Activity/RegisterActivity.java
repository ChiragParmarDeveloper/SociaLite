package com.ap.SociaLite.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ap.SociaLite.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.btn_img)
    Button btn_img;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.upload_img)
    CircularImageView upload_img;

    @BindView(R.id.txt_login)
    TextView txt_login;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_img,R.id.btn_register,R.id.txt_login})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.btn_register:
                startActivity(new Intent(RegisterActivity.this,RegistrationVerificationActivity.class));

                break;

            case R.id.btn_img:
                openGallery();
                break;

            case R.id.txt_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            upload_img.setImageURI(imageUri);
        }
    }
}