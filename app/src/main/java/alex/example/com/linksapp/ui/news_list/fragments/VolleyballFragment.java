package alex.example.com.linksapp.ui.news_list.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import alex.example.com.linksapp.data.entity.Event;
import alex.example.com.linksapp.ui.news_list.BaseFragment;
import alex.example.com.linksapp.ui.news_list.NewsContract;

public class VolleyballFragment extends BaseFragment implements NewsContract.View {
    private final String CATEGORY = "volleyball";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCategory(CATEGORY);
    }

    @Override
    public void onSuccess(List<Event> list) {
        setAdapterNews(list);
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }


    @Override
    public void onCLickItem(String article, String title) {
        startActivityDetails(article, title);
    }
}
