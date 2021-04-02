package com.ap.SociaLite.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Application.Session;
import com.ap.SociaLite.Pojo.user_details;
import com.ap.SociaLite.Presenter.EditProfilePresenter;
import com.ap.SociaLite.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.edt_username)
    public EditText edt_username;

    @BindView(R.id.edt_email)
    public EditText edt_email;

    @BindView(R.id.edt_no)
    public EditText edt_no;

    @BindView(R.id.schedule_post_image)
    public ImageView schedule_post_image;

    @BindView(R.id.progressbar)
    public ProgressBar progressbar;

    @BindView(R.id.edt_bio)
    public EditText edt_bio;

    @BindView(R.id.edt_dob)
    public Button edt_dob;

//    @BindView(R.id.edt_pwd)
//    public EditText edt_pwd;

    @BindView(R.id.edt_location)
    public EditText edt_location;

    @BindView(R.id.img_back)
    public ImageView img_back;

    @BindView(R.id.edit_profile_save)
    public Button edit_profile_save;

    @BindView(R.id.edit_profile_change_profile)
    public Button edit_profile_change_profile;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    String image, id, path;
    Calendar myCalendar = Calendar.getInstance();
    MultipartBody.Part profile_pic;
    public List<user_details> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        Session session = new Session(EditProfileActivity.this);
        id = session.getUser_id();

        new EditProfilePresenter(this, this).fetch_profile(id);
    }

    @OnClick({R.id.img_back, R.id.edit_profile_save, R.id.edit_profile_change_profile, R.id.edt_dob})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;

            case R.id.edit_profile_save:

                if (new EditProfilePresenter(this, this).validate(edt_username, edt_email, edt_no, edt_dob, edt_location)) {

                    if (image != null) {
                        File idfile = new File(image);
                        RequestBody idcardfile = RequestBody.create(MediaType.parse("image/*"), idfile);
                        profile_pic = MultipartBody.Part.createFormData("profile_pic", idfile.getPath(), idcardfile);
                    } else {
                        RequestBody idcardfile = RequestBody.create(MediaType.parse("image/*"), "");
                        profile_pic = MultipartBody.Part.createFormData("profile_pic", "", idcardfile);
                    }

                    RequestBody user_id = RequestBody.create(MediaType.parse("text/plain"), id);
                    RequestBody username = RequestBody.create(MediaType.parse("text/plain"), edt_username.getText().toString().trim());
                    RequestBody email = RequestBody.create(MediaType.parse("text/plain"), edt_email.getText().toString().trim());
                    RequestBody mobile_number = RequestBody.create(MediaType.parse("text/plain"), edt_no.getText().toString().trim());
                    RequestBody bio = RequestBody.create(MediaType.parse("text/plain"), edt_bio.getText().toString().trim());
                    RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), edt_dob.getText().toString().trim());
                    RequestBody location = RequestBody.create(MediaType.parse("text/plain"), edt_location.getText().toString().trim());
              //      RequestBody password = RequestBody.create(MediaType.parse("text/plain"), edt_pwd.getText().toString().trim());

                    new EditProfilePresenter(this, this).edit_profile(user_id, username, email, mobile_number, location,
                            bio, dob, profile_pic);

                }

                break;
            case R.id.edit_profile_change_profile:
                openGallery();
                break;
            case R.id.edt_dob:
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
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
            schedule_post_image.setImageURI(imageUri);
            image = getRealPathFromURI(imageUri);

            if (image != null) {
                File file = new File(image);
                RequestBody picture = RequestBody.create(MediaType.parse("image/*"), file);
                profile_pic = MultipartBody.Part.createFormData("profile_pic", file.getPath(), picture);
            } else {
                RequestBody picture = RequestBody.create(MediaType.parse("image/*"), "");
                profile_pic = MultipartBody.Part.createFormData("profile_pic", "", picture);
            }

            RequestBody u_id = RequestBody.create(MediaType.parse("text/plain"),id);

            new EditProfilePresenter(this, this).profile_photo(u_id, profile_pic);
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

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            DOB();
        }
    };

    private void DOB() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edt_dob.setText(sdf.format(myCalendar.getTime()));
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        new EditProfilePresenter(this, this).fetch_profile(id);
//    }
}