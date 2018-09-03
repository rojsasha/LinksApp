package alex.example.com.linksapp.ui.details;

import android.content.Intent;
import android.support.annotation.NonNull;

import alex.example.com.linksapp.data.SportsInterfaceConnection;
import alex.example.com.linksapp.data.entity.DetailsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter implements DetailsContract.Presenter {
    private DetailsContract.View mView;
    private SportsInterfaceConnection mService;

    DetailsPresenter(SportsInterfaceConnection service) {
        mService = service;
    }

    @Override
    public void bind(DetailsContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void startDownloadDetails(final Intent intent) {

        if (intent != null) {
            final String title = intent.getStringExtra("title");
            if (isViewAttached())
                mView.showProgressBar();
            mService.getDetailsPost(intent.getStringExtra("article"))
                    .enqueue(new Callback<DetailsModel>() {
                        @Override
                        public void onResponse(@NonNull Call<DetailsModel> call, @NonNull Response<DetailsModel> response) {
                            if (response.body() != null && response.isSuccessful()) {
                                if (isViewAttached())
                                    mView.onSuccess(response.body(), title);
                            } else {

                            }
                            if (isViewAttached())
                                mView.hideProgressBar();
                        }

                        @Override
                        public void onFailure(@NonNull Call<DetailsModel> call, @NonNull Throwable t) {
                            if (isViewAttached()) {
                                mView.onError();
                                mView.hideProgressBar();
                            }

                        }
                    });
        }


    }


    private boolean isViewAttached() {
        return mView != null;
    }
}
