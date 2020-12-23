package com.ap.SociaLite.Application;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RService {

    @FormUrlEncoded
    @POST("login.php")
    Call<json> login(@Field("email_or_mobile") String email_or_mobile,
                     @Field("password") String password);

    @POST("faq.php")
    Call<json> faq();

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


            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.MainURL)
                        .client(okHttpClient != null ? okHttpClient : null)
                    .addConverterFactory(GsonConverterFactory.create())
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

