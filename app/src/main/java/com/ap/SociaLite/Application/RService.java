package com.ap.SociaLite.Application;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RService {

    @FormUrlEncoded
    @POST("login.php")
    Call<json> login(@Field("email_or_mobile") String email_or_mobile,
                     @Field("password") String password);

    @POST("faq.php")
    Call<json> faq();

    @FormUrlEncoded
    @POST("view_hided_post.php")
    Call<json> hidepost(@Field("user_id") String user_id);

    @POST("interest_list.php")
    Call<json> interest_list();

    @Multipart
    @POST("registration.php")
    Call<json> register(@Part("username") RequestBody username,
                        @Part("mobile_number") RequestBody mobile_number,
                        @Part("email") RequestBody email,
                        @Part("bio") RequestBody bio,
                        @Part("dob") RequestBody dob,
                        @Part("location") RequestBody location,
                        @Part("password") RequestBody password,
                        @Part MultipartBody.Part profile_pic);


    @FormUrlEncoded
    @POST("fetch_profile.php")
    Call<json> profile(@Field("user_id") String user_id);

    @Multipart
    @POST("edit_profile.php")
    Call<json> editprofile(@Part("user_id") RequestBody user_id,
                           @Part("username") RequestBody username,
                           @Part("email") RequestBody email,
                           @Part("mobile_number") RequestBody mobile_number,
                           @Part("password") RequestBody password,
                           @Part("location") RequestBody location,
                           @Part("bio") RequestBody bio,
                           @Part("dob") RequestBody dob,
                           @Part MultipartBody.Part profile_pic);


    @FormUrlEncoded
    @POST("add_user_interest.php")
    Call<json> addinterest(@Field("interest_ids[]") ArrayList<String> interest_ids,
                           @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("private_account.php")
    Call<json> account(@Field("user_id") String user_id,
                       @Field("is_private_account") String is_private_account);

    @FormUrlEncoded
    @POST("interest_list_post_page.php")
    Call<json> interest_list_post(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("post_list.php")
    Call<json> category_post(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("hide_post.php")
    Call<json> dashboard_hidepost(@Field("user_id") String user_id,
                                  @Field("post_id") String post_id);


    @FormUrlEncoded
    @POST("save_post.php")
    Call<json> dashboard_savepost(@Field("user_id") String user_id,
                                  @Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("view_card.php")
    Call<json> card_view(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("create_card.php")
    Call<json> create_card(@Field("user_id") String user_id,
                           @Field("name") String name,
                           @Field("website") String website,
                           @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("my_profile.php")
    Call<json> my_profileActivity(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("add_post_rating.php")
    Call<json> give_rating(@Field("user_id") String user_id,
                           @Field("post_id") String post_id,
                           @Field("rate") String rate);

    @FormUrlEncoded
    @POST("add_post_comment.php")
    Call<json> add_comment_post(@Field("user_id") String user_id,
                                @Field("post_id") String post_id,
                                @Field("comment") String comment);


    @FormUrlEncoded
    @POST("fetch_comments.php")
    Call<json> fetch_comments( @Field("post_id") String post_id);



    public class api {
        static Retrofit retrofit = null;

        public RService call(final Context mContext) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = null;

            TrustManagerFactory trustManagerFactory = null;
            try {
                trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                trustManagerFactory.init((KeyStore) null);
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }

            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
            }

            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                if (sslContext != null) {
                    sslContext.init(null, new TrustManager[]{trustManager}, null);
                }
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            if (AppUtils.isNetworkAvailable(mContext)) {
                //   Utils.showProgressDialog(mContext, true);
                okHttpClient = new OkHttpClient.Builder()
                        .sslSocketFactory(sslSocketFactory, trustManager)
                        .connectTimeout(35, TimeUnit.SECONDS)
                        .readTimeout(35, TimeUnit.SECONDS)
                        .writeTimeout(35, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request original = chain.request();
                                Request.Builder requestBuilder = original.newBuilder();
                                requestBuilder.addHeader("HEADER_TIME_STEMP", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())));
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        })
                        .build();
            } else {
                Toast.makeText(mContext, " No internet connection!! try again.", Toast.LENGTH_SHORT).show();
            }

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.MainURL)
                    //    .client(okHttpClient != null ? okHttpClient : null)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            return retrofit.create(RService.class);


            //       Parsing error
//        public static Errors parseError(Response response) {
//            Converter<ResponseBody, Errors> converter = retrofit.responseBodyConverter(Errors.class, new Annotation[0]);
//            Errors error;
//            try {
//                error = converter.convert(response.errorBody());
//            } catch (IOException e) {
//                return new Errors();
//            }
//                      return error;
//        }

        }
    }
}

