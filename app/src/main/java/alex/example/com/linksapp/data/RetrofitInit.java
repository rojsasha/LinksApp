package alex.example.com.linksapp.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    private static SportsInterfaceConnection mService = null;

    public static SportsInterfaceConnection retrofitInit(){
        if (mService == null){
            return new Retrofit.Builder()
                    .baseUrl("http://mikonatoruri.win/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(SportsInterfaceConnection.class);
        }

        return mService;
    }
}
