package com.example.shaoxiaofei.webanddatabase;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by shaoxiaofei on 28/12/2017.
 */

public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";
    private static final String BASE_URL = "http://192.168.0.5:8877/";//2.0的url必须以/结尾


    Retrofit retrofit;


    public RetrofitHelper(){
        initRetrofit();
    }
    private void initRetrofit() {
        /*init ok http*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
//                .addNetworkInterceptor(null)
                .build();

        Gson gson = new GsonBuilder()
                //custom gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public interface BlogService {
        @GET("cmd/{id}")
        Call<Person> getTim(@Path("id") int id);//@path是url占位符

        @GET
        Call<Person> getTim1(@Url String url);//直接使用url来访问

        @GET("get/tim")
        Call<Person> getTim2(@Query("id") int id);//--> http://baseurl/group/users?id=groupId

        @GET("get/tim")
        Call<Person> getTim3(@QueryMap(encoded = true) Map<String, String> options);

        @FormUrlEncoded//通过表单提交
        @POST("post/tim")
        Call<Person> postTim(@FieldMap Map<String, String> options);

        @POST("post/tim")
        Call<Person> postTim1(@Body Person person);//使用@body就不能使用@FormUrlEncoded 但是目测现在还不成功

        @FormUrlEncoded//通过表单提交
        @POST("post/tim")
        Call<Person> postTim2(@Field("name") String first, @Field("des") String last);


        @Multipart
        @POST("upload/log")
        Call<Person> upload(@Part("description") RequestBody description,
                            @Part MultipartBody.Part file);

    }

    public void sendPost() {
        BlogService service = retrofit.create(BlogService.class);
        Call<Person> call = service.postTim1(new Person("tim","i am a strong man"));
//        Map<String,String> data = new HashMap<>();
//        data.put("name","tim");
//        data.put("des","i am a strong man");
//        Call<Person> call = service.postTim(data);
//        Call<Person> call = service.postTim2("tim", "i am a strong man");
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.d(TAG, "onResponse: " + response.body().getName());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void sendGet() {
        BlogService service = retrofit.create(BlogService.class);
        Call<Person> call = service.getTim(2);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.d(TAG, "onResponse: " + response.body().getName());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    public void unloadFile() {
        File file = new File("/sdcard/lenovoid-log.txt");
        if (file.exists()) Log.e(TAG, "unloadFile: file exists");

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("log", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        BlogService service = retrofit.create(BlogService.class);
        Call<Person> call = service.upload(description, body);
        // 用法和OkHttp的call如出一辙
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Log.d(TAG, "onResponse: " + response.body().getName());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
