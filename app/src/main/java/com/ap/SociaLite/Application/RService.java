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

    @FormUrlEncoded
    @POST("save_token.php")
    Call<json> token(@Field("user_id") String user_id,
                     @Field("token_id") String token_id);


    @FormUrlEncoded
    @POST("forgot_password.php")
    Call<json> forgot_pwd(@Field("mobile_number") String mobile_number,
                          @Field("password") String password);

    @POST("faq.php")
    Call<json> faq();

    @POST("report.php")
    Call<json> report();


    @FormUrlEncoded
    @POST("bussiness_wise_post.php")
    Call<json> post_business(@Field("interest_id") String interest_id,
                             @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("friend_list.php")
    Call<json> frnd_list(@Field("UserId") String UserId);


    @FormUrlEncoded
    @POST("notification_list.php")
    Call<json> notification(@Field("UserId") String UserId);


    @FormUrlEncoded
    @POST("request_accept.php")
    Call<json> accept(@Field("UserId") String UserId,
                      @Field("RequestId") String RequestId);


    @FormUrlEncoded
    @POST("request_denied.php")
    Call<json> denied(@Field("UserId") String UserId,
                      @Field("RequestId") String RequestId);


    @FormUrlEncoded
    @POST("connection_fetch.php")
    Call<json> connection(@Field("UserId") String UserId);


    @FormUrlEncoded
    @POST("my_network.php")
    Call<json> netork_post(@Field("UserId") String UserId);

    @FormUrlEncoded
    @POST("my_connections.php")
    Call<json> my_connection(@Field("UserId") String UserId);


    @FormUrlEncoded
    @POST("friend_story.php")
    Call<json> allfrnd_story(@Field("UserId") String UserId);


    @FormUrlEncoded
    @POST("search.php")
    Call<json> fetch_user(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("friend_connection.php")
    Call<json> connection_request(@Field("UserId") String UserId,
                                  @Field("RequestId") String RequestId);


    @FormUrlEncoded
    @POST("add_story_view.php")
    Call<json> added_story_view(@Field("user_id") String user_id,
                                @Field("story_id") String story_id);


    @FormUrlEncoded
    @POST("remove_connection.php")
    Call<json> disconnect(@Field("UserId") String UserId,
                          @Field("RequestId") String RequestId);


    @FormUrlEncoded
    @POST("view_hided_post.php")
    Call<json> hidepost(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("view_saved_post.php")
    Call<json> savepost(@Field("user_id") String user_id);


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

    @Multipart
    @POST("create_post.php")
    Call<json> create_post(@Part("user_id") RequestBody user_id,
                           @Part MultipartBody.Part[] post_image,
                           @Part("description") RequestBody description,
                           @Part("intrest_id") RequestBody intrest_id,
                           @Part("in_bussiness_interaction") RequestBody in_bussiness_interaction,
                           @Part("location") RequestBody location,
                           @Part("hide_users[]") RequestBody hide_users,
                           @Part("share_users[]") RequestBody share_users,
                           @Part("schedule_date") RequestBody schedule_date,
                           @Part("schedule_time") RequestBody schedule_time);

    @Multipart
    @POST("profile_image.php")
    Call<json> photo(@Part("id") RequestBody id,
                     @Part MultipartBody.Part profile_pic);


    @Multipart
    @POST("cover_image.php")
    Call<json> cover_photo(@Part("user_id") RequestBody user_id,
                           @Part MultipartBody.Part cover_photo);

    @FormUrlEncoded
    @POST("fetch_profile.php")
    Call<json> profile(@Field("user_id") String user_id);




    @FormUrlEncoded
    @POST("notification_on_off.php")
    Call<json> on_off_notification(@Field("user_id") String user_id,
                                   @Field("is_toggle") String is_toggle);

    @FormUrlEncoded
    @POST("contact_us.php")
    Call<json> contact(@Field("name") String name,
                       @Field("email") String email,
                       @Field("message") String message);

    @Multipart
    @POST("edit_profile.php")
    Call<json> editprofile(@Part("user_id") RequestBody user_id,
                           @Part("username") RequestBody username,
                           @Part("email") RequestBody email,
                           @Part("mobile_number") RequestBody mobile_number,
                           @Part("location") RequestBody location,
                           @Part("bio") RequestBody bio,
                           @Part("dob") RequestBody dob,
                           @Part MultipartBody.Part profile_pic);

    @Multipart
    @POST("upload_card.php")
    Call<json> card_upload(@Part("user_id") RequestBody user_id,
                           @Part MultipartBody.Part upload_image);

    @Multipart
    @POST("put_storys.php")
    Call<json> put_story(@Part("user_id") RequestBody user_id,
                         @Part MultipartBody.Part story_file);


    @FormUrlEncoded
    @POST("add_user_interest.php")
    Call<json> addinterest(@Field("interest_ids[]") ArrayList<String> interest_ids,
                           @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("sync_contacts.php")
    Call<json> contact(@Field("contacts[]") ArrayList<String> contacts);


    @FormUrlEncoded
    @POST("private_account.php")
    Call<json> account(@Field("user_id") String user_id,
                       @Field("is_private_account") String is_private_account);

    @FormUrlEncoded
    @POST("my_interest_list.php")
    Call<json> my_interest_list(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("post_list.php")
    Call<json> category_post(@Field("interest_id") String interest_id);


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
    @POST("profile_connection.php")
    Call<json> connection_type(@Field("UserId") String UserId,
                               @Field("RequestId") String RequestId);

    @FormUrlEncoded
    @POST("add_post_rating.php")
    Call<json> give_rating(@Field("user_id") String user_id,
                           @Field("post_id") String post_id,
                           @Field("rate") String rate);

    @FormUrlEncoded
    @POST("normal_post_delete.php")
    Call<json> timeline_post(@Field("user_id") String user_id,
                           @Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("unhide_post.php")
    Call<json> unhide(@Field("user_id") String user_id,
                             @Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("add_post_comment.php")
    Call<json> add_comment_post(@Field("user_id") String user_id,
                                @Field("post_id") String post_id,
                                @Field("comment") String comment);

    @FormUrlEncoded
    @POST("fetch_comments.php")
    Call<json> fetch_comments(@Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("my_post.php")
    Call<json> timeline_my_post(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("my_bussiness_post.php")
    Call<json> my_bussiness_post(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("interest_list_post_page.php")
    Call<json> interest_list_post_page(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("add_interest.php")
    Call<json> plus_add_interest(@Field("user_id") String user_id,
                                 @Field("interest_id") String interest_id);

    @FormUrlEncoded
    @POST("remove_interest.php")
    Call<json> delete_interest(@Field("user_id") String user_id,
                               @Field("interest_ids") String interest_ids);

    @FormUrlEncoded
    @POST("post_interest.php")
    Call<json> like_interest_btn(@Field("user_id") String user_id,
                               @Field("post_id") String post_id);


    @FormUrlEncoded
    @POST("delete_post_interest.php")
    Call<json> remove_interest_btn(@Field("user_id") String user_id,
                                 @Field("post_id") String post_id);


    @FormUrlEncoded
    @POST("interest_wise_post.php")
    Call<json> interest_wise_post(@Field("interest_id") String interest_id);


    @FormUrlEncoded
    @POST("my_schedule_post.php")
    Call<json> my_schedule_post(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("delete_post.php")
    Call<json> delete_post(@Field("post_id") String post_id);

    @FormUrlEncoded
    @POST("my_all_story.php")
    Call<json> my_all_story(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("story_viewers.php")
    Call<json> story_viewers(@Field("user_id") String user_id,
                             @Field("story_id") String story_id);

    @FormUrlEncoded
    @POST("report_post.php")
    Call<json> post_report(@Field("user_id") String user_id,
                           @Field("post_id") String post_id,
                           @Field("reason") String reason);

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

