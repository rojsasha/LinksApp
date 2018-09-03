package alex.example.com.linksapp.ui.details;

import android.content.Intent;

import alex.example.com.linksapp.data.entity.DetailsModel;
import alex.example.com.linksapp.ui.Lifecicle;

public interface DetailsContract {
    interface View{
        void onError();
        void onSuccess(DetailsModel model, String title);
        void showProgressBar();
        void hideProgressBar();

    }

    interface Presenter extends Lifecicle<View>{
        void startDownloadDetails(Intent intent);
    }
}
