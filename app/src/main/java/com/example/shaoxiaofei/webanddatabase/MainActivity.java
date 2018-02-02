package com.example.shaoxiaofei.webanddatabase;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.shaoxiaofei.webanddatabase.gen.UserDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private RetrofitHelper retrofitHelper;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    GreenDaoManager greenDaoManager = GreenDaoManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions();
        retrofitHelper = new RetrofitHelper();
        greenDaoManager.init(this);
    }


    public void get(View v) {
        retrofitHelper.sendGet();
    }

    public void post(View v) {
        retrofitHelper.sendPost();
    }

    public void upload(View v) {
        retrofitHelper.unloadFile();
    }

    public void testAdd(View v){
        greenDaoManager.getmDaoSession().getUserDao().insertOrReplace(new User(1L,10,"1","tim","icon","18610044417",12,"i am strong","level 1",3243,"henan"));
        List<User> users = greenDaoManager.getmDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(1L)).list();
        Log.d(TAG, "testAdd: " + users.get(0).getUserNickname());
    }



    private void verifyStoragePermissions() {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

}
