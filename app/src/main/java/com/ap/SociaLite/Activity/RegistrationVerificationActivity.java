package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ap.SociaLite.Presenter.LoginPresenter;
import com.ap.SociaLite.Presenter.RegistrationVerificationPresenter;
import com.ap.SociaLite.R;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RegistrationVerificationActivity extends AppCompatActivity {

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.pinview)
    PinView pinview;

    @BindView(R.id.otp_resend)
    TextView otp_resend;

    @BindView(R.id.txt_no)
    TextView txt_no;

    public boolean isVarificationCompleted = false;
    String image,name,mail,detail,date_birth,loc,pwd,phoneNumber, otp,country_code,phone;
    FirebaseAuth auth;
    private String verificationCode;
    private PhoneAuthProvider.ForceResendingToken token;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    MultipartBody.Part profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_verification);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        image = getIntent().getStringExtra("profile_pic");
        name = getIntent().getStringExtra("user_name");
        mail = getIntent().getStringExtra("email");
        country_code = getIntent().getStringExtra("country_code");
        phoneNumber = getIntent().getStringExtra("phone_no");
        detail = getIntent().getStringExtra("bio");
        date_birth = getIntent().getStringExtra("dob");
        loc = getIntent().getStringExtra("location");
        pwd = getIntent().getStringExtra("password");

//        Log.d("profile_pic", profile_pic);
//        Log.d("user_name", user_name);
//        Log.d("email", email);
//        Log.d("country_code", country_code);
//        Log.d("phoneNumber", phoneNumber);
//        Log.d("bio", bio);
//        Log.d("dob", dob);
//        Log.d("location", location);
//        Log.d("password", password);

        String mask = phoneNumber.replaceAll("\\w(?=\\w{4})", "X");

        String code = country_code + " " + mask;
        txt_no.setText(code);

        phone = country_code + "" + phoneNumber;
        StartFirebaseLogin();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                RegistrationVerificationActivity.this,        // Activity (for callback binding)
                mCallback); // OnVerificationStateChangedCallbacks

    }

    private void StartFirebaseLogin() {
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                System.out.println("************ : " + phoneAuthCredential.getSmsCode());
                pinview.setText(phoneAuthCredential.getSmsCode());
                isVarificationCompleted = true;
                SigninWithPhone(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                //             Toast.makeText(RegistrationVerificationActivity.this, "verification failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                  //         Toast.makeText(RegistrationVerificationActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if (image != null) {
                                File idfile = new File(image);
                                RequestBody idcardfile = RequestBody.create(MediaType.parse("image/*"), idfile);
                                profile_pic = MultipartBody.Part.createFormData("profile_pic", idfile.getPath(), idcardfile);
                            } else {
                                RequestBody idcardfile = RequestBody.create(MediaType.parse("image/*"), "");
                                profile_pic = MultipartBody.Part.createFormData("profile_pic", "", idcardfile);
                            }

                            RequestBody user_name = RequestBody.create(MediaType.parse("text/plain"), name);
                            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), mail);
                            RequestBody no = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
                            RequestBody bio = RequestBody.create(MediaType.parse("text/plain"), detail);
                            RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), date_birth);
                            RequestBody location = RequestBody.create(MediaType.parse("text/plain"), loc);
                            RequestBody passoword = RequestBody.create(MediaType.parse("text/plain"), pwd);

                            new RegistrationVerificationPresenter(RegistrationVerificationActivity.this, RegistrationVerificationActivity.this)
                                    .register(profile_pic,user_name,email,no,bio,dob,location,passoword);

                        } else {
                            Toast.makeText(RegistrationVerificationActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void resendVerificationCode(String phone,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallback,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    @OnClick({R.id.btn_submit, R.id.otp_resend})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:

                try {
                    if (pinview.getText().toString().isEmpty()) {
                        //    Toast.makeText(getApplicationContext(), "enter otp", Toast.LENGTH_SHORT).show();
                    } else {
                        otp = pinview.getText().toString();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                        SigninWithPhone(credential);
                    }
                } catch (Exception e) {

                }
                break;
            case R.id.otp_resend:

                Toast.makeText(getApplicationContext(), "OTP Sended again", Toast.LENGTH_SHORT).show();
                resendVerificationCode(phone, token);
                break;


        }
    }
}