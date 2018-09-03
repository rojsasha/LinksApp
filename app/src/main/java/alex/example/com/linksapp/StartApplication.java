package alex.example.com.linksapp;

import android.app.Application;
import android.content.Context;

import alex.example.com.linksapp.data.RetrofitInit;
import alex.example.com.linksapp.data.SportsInterfaceConnection;

public class StartApplication extends Application {
    private SportsInterfaceConnection mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = RetrofitInit.retrofitInit();
    }

    public static StartApplication get(Context context) {
        return (StartApplication) context.getApplicationContext();
    }

    public SportsInterfaceConnection getService() {
        return mService;
    }
}
