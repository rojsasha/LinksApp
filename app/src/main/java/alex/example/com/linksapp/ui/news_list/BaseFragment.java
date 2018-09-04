package alex.example.com.linksapp.ui.news_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import alex.example.com.linksapp.Callback;
import alex.example.com.linksapp.R;
import alex.example.com.linksapp.StartApplication;
import alex.example.com.linksapp.data.entity.Event;
import alex.example.com.linksapp.ui.details.DetailsActivity;
import alex.example.com.linksapp.utils.NetworkUtils;

public abstract class BaseFragment extends Fragment implements NewsContract.View, RecyclerViewAdapterNews.AdapterInterfaceOnClick {
    private RecyclerView mRecyclerView;
    private NewsContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    private LinearLayout linearLayout;
    private String category;
    private Callback mCallback;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mProgressBar = view.findViewById(R.id.progressBar);
        linearLayout = view.findViewById(R.id.linearLayout);
        mPresenter = new NewsPresenter(StartApplication.get(getContext()).getService());
        mPresenter.bind(this);
        setRetainInstance(true);

        return view;
    }

    protected void setAdapterNews(List<Event> list) {
        if (list != null) {
            mRecyclerView.setAdapter(new RecyclerViewAdapterNews(list, this));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            hideProgress();

        }

    }

    protected void startActivityDetails(String article, String title) {
        startActivity(new Intent(getContext(), DetailsActivity.class)
                .putExtra("article", article)
                .putExtra("title", title));
    }

    protected void setCategory(final String category) {
        this.category = category;
        if (NetworkUtils.isNetworkConnected(getContext())) {
            mPresenter.getCurrentNews(category);
            showProgress();
        } else {
           mCallback.refreshAdapter();
        }

    }



    protected void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    protected void showToast(String msg) {
        mCallback.refreshAdapter();
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;

    }
}
