package alex.example.com.linksapp.ui.news_list;

import android.support.annotation.NonNull;


import alex.example.com.linksapp.data.SportsInterfaceConnection;
import alex.example.com.linksapp.data.entity.NewsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPresenter implements NewsContract.Presenter {
    private NewsContract.View mView;
    private SportsInterfaceConnection mService;

    public NewsPresenter(SportsInterfaceConnection service) {
        mService = service;
    }

    @Override
    public void getCurrentNews(String category) {

        mService.getSportsList(category)
                .enqueue(new Callback<NewsModel>() {
                    @Override
                    public void onResponse(@NonNull Call<NewsModel> call, @NonNull Response<NewsModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                                mView.onSuccess(response.body().getEvents());
                        } else {
                            if (isViewAttached())
                                mView.onError("Ошибка подключения");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                        if (isViewAttached())
                            mView.onError("Ошибка подключения");
                    }
                });

    }

    @Override
    public void bind(NewsContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
