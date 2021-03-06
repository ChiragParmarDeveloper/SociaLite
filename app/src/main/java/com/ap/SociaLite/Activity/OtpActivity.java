package com.ap.SociaLite.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtpActivity extends AppCompatActivity {

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.txt_sentno)
    TextView txt_sentno;

    @BindView(R.id.pinview)
    PinView pinview;

    public boolean isVarificationCompleted = false;
    String phoneNumber, otp, country_code, phone;
    FirebaseAuth auth;
    private String verificationCode;
    private PhoneAuthProvider.ForceResendingToken token;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        country_code = getIntent().getStringExtra("country_code");
        phoneNumber = getIntent().getStringExtra("phone_no");

        String mask = phoneNumber.replaceAll("\\w(?=\\w{4})", "X");

        String code = country_code + " " + mask;
        txt_sentno.setText("Please type the verification code sent to \n" + "" + code);

        phone = country_code + "" + phoneNumber;
        StartFirebaseLogin();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                OtpActivity.this,        // Activity (for callback binding)
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
                Toast.makeText(OtpActivity.this, "verification failed", Toast.LENGTH_SHORT).show();
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

                            startActivity(new Intent(OtpActivity.this, ResetpasswordActivity.class)
                                    .putExtra("phone_no",phoneNumber));

                        } else {
                            Toast.makeText(OtpActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.btn_submit, R.id.txt_resend})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:

                try {
                    if (pinview.getText().toString().isEmpty()) {
                        //       Toast.makeText(getApplicationContext(), "enter otp", Toast.LENGTH_SHORT).show();
                    } else {
                        otp = pinview.getText().toString();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
                        SigninWithPhone(credential);
                    }
                } catch (Exception e) {

                }
                break;
            case R.id.txt_resend:
                Toast.makeText(getApplicationContext(), "OTP Sended again", Toast.LENGTH_SHORT).show();
                resendVerificationCode(phone, token);
                break;
        }
    }
}