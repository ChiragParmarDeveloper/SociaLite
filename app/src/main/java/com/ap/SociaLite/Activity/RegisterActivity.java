package com.ap.SociaLite.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Presenter.RegisterPresenter;
import com.ap.SociaLite.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hbb20.CountryCodePicker;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.btn_img)
    Button btn_img;

    @BindView(R.id.btn_register)
    Button btn_register;

    @BindView(R.id.upload_img)
    CircularImageView upload_img;

    @BindView(R.id.txt_login)
    TextView txt_login;

    @BindView(R.id.user_name)
    EditText user_name;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.phone_no)
    EditText phone_no;

    @BindView(R.id.bio)
    EditText bio;

    @BindView(R.id.dob)
    Button dob;

    @BindView(R.id.location)
    EditText location;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.code_picker)
    CountryCodePicker code_picker;

    @BindView(R.id.txt_code)
    TextView txt_code;

    String profile_pic;
    private GoogleApiClient mGoogleApiClient;
    private static final int RESOLVE_HINT = 1000;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        txt_code.setText(code_picker.getTextView_selectedCountry().getText().toString().trim());
        getHintPhoneNumber();

    }

    public void getHintPhoneNumber() {
        HintRequest hintRequest =
                new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    private void Register() {
        if (new RegisterPresenter(this, this).validate(user_name, email, phone_no, bio, dob, location, password)) {

            Intent in = new Intent(RegisterActivity.this, RegistrationVerificationActivity.class);

            in.putExtra("profile_pic", profile_pic);
            in.putExtra("user_name", user_name.getText().toString().trim());
            in.putExtra("email", email.getText().toString().trim());
            in.putExtra("country_code", code_picker.getTextView_selectedCountry().getText().toString().trim());
            in.putExtra("phone_no", phone_no.getText().toString().trim());
            in.putExtra("bio", bio.getText().toString().trim());
            in.putExtra("dob", dob.getText().toString().trim());
            in.putExtra("location", location.getText().toString().trim());
            in.putExtra("password", password.getText().toString().trim());

            startActivity(in);
            finish();
        }
    }

    @OnClick({R.id.btn_img, R.id.btn_register, R.id.txt_login, R.id.dob})
    public void OnClick(View view) {
        switch (view.getId()) {

            case R.id.btn_register:
                Register();
                break;

            case R.id.btn_img:
                openGallery();
                break;

            case R.id.txt_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.dob:
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
                break;
        }
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
        dob.setText(sdf.format(myCalendar.getTime()));
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {

                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                if (credential.getId().startsWith("+")) {

                    if (credential.getId().length() == 12) {
                        phone_no.setText(credential.getId().substring(3));
                    } else if (credential.getId().length() == 13) {
                        phone_no.setText(credential.getId().substring(3));
                    } else if (credential.getId().length() == 14) {
                        phone_no.setText(credential.getId().substring(4));
                    }
                } else {
                    phone_no.setText(credential.getId());
                }
            }
        } else if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            upload_img.setImageURI(imageUri);
            profile_pic = getRealPathFromURI(imageUri);

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