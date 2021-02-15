package com.ap.SociaLite.Presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ap.SociaLite.Activity.EditProfileActivity;
import com.ap.SociaLite.Application.AppUtils;
import com.ap.SociaLite.Application.RService;
import com.ap.SociaLite.Application.json;
import com.ap.SociaLite.Contract.EditProfileContract;
import com.ap.SociaLite.R;
import com.squareup.picasso.Picasso;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter implements EditProfileContract {

    public Context mContext;
    public EditProfileActivity editProfileActivity;
    public static String path;

    public EditProfilePresenter(Context context, EditProfileActivity fragment) {
        this.mContext = context;
        this.editProfileActivity = fragment;
    }

    @Override
    public void fetch_profile(String user_id) {
        try {
            new RService.api().call(mContext).profile(user_id).enqueue(new Callback<json>() {
                @Override
                public void onResponse(Call<json> call, Response<json> response) {

                    if (response.body().status.equals("1")) {
                        if (response.body().user_details != null) {

                            path = response.body().user_details.profile_pic;
                            if(response.body().user_details.profile_pic.length() > 0)
                            {
                                Picasso.get().load(response.body().user_details.profile_pic).placeholder(R.mipmap.ic_launcher).into(editProfileActivity.schedule_post_image);
                            }

                            editProfileActivity.edt_username.setText(response.body().user_details.username);
                            editProfileActivity.edt_email.setText(response.body().user_details.email);
                            editProfileActivity.edt_no.setText(response.body().user_details.mobile_number);

                            editProfileActivity.edt_bio.setText(response.body().user_details.bio);
                            editProfileActivity.edt_dob.setText(response.body().user_details.dob);
                            editProfileActivity.edt_location.setText(response.body().user_details.location);
                            editProfileActivity.edt_pwd.setText(response.body().user_details.password);

                        }
                    } else {
                        //         Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<json> call, Throwable t) {
                    //       Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                    //       Log.d("error", String.valueOf(t.getMessage()));
                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public boolean validate(EditText edt_username, EditText edt_email, EditText edt_no, Button edt_dob, EditText edt_location, EditText edt_pwd) {

        if (edt_username.getText().toString().isEmpty()) {
            edt_username.setError("Please enter username");
            return false;
        }
        if (edt_email.getText().toString().isEmpty()) {
            edt_email.setError("Please enter email");
            return false;
        }
        if (!AppUtils.isEmailValid(AppUtils.getText(edt_email))) {
            edt_email.setError("Please enter valid email");
            return false;
        }
        if (edt_no.getText().toString().isEmpty()) {
            edt_no.setError("Please enter phone number");
            return false;
        }
        if (!AppUtils.isValidMobile(AppUtils.getText(edt_no))) {
            edt_no.setError("Please enter valid phone number");
            return false;
        }
        if (edt_dob.getText().toString().isEmpty()) {
            edt_dob.setError("Please enter valid dob");
            return false;
        } else {
            edt_dob.setError(null);
        }
        if (edt_location.getText().toString().isEmpty()) {
            edt_location.setError("Please enter location");
            return false;
        }
        if (edt_pwd.getText().toString().isEmpty()) {
            edt_pwd.setError("Please enter password");
            return false;
        }
        return true;
    }

    @Override
    public void edit_profile(RequestBody user_id, RequestBody username, RequestBody email, RequestBody mobile_number, RequestBody password,
                             RequestBody location, RequestBody bio, RequestBody dob, MultipartBody.Part profile_pic) {
        try {
            new RService.api().call(mContext).editprofile(user_id, username, email, mobile_number, password, location, bio, dob, profile_pic)
                    .enqueue(new Callback<json>() {
                        @Override
                        public void onResponse(Call<json> call, Response<json> response) {

                            if (response.body().status.equals("1")) {
                                Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                                editProfileActivity.onBackPressed();

                            } else {
                                //   Toast.makeText(mContext, response.body().message, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<json> call, Throwable t) {
                            //Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG).show();
                            //Log.d("error", String.valueOf(t.getMessage()));
                        }
                    });
        } catch (Exception e) {

        }
    }
}
