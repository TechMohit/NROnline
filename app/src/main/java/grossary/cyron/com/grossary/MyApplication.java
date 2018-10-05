package grossary.cyron.com.grossary;

import android.app.Application;

import grossary.cyron.com.grossary.utility.retrofit.RetrofitClient;


public class MyApplication extends Application {
    private static final MyApplication singleton=new MyApplication();


    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.create(getCacheDir());
    }


}

