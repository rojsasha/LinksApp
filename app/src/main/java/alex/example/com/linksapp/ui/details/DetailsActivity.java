package alex.example.com.linksapp.ui.details;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import alex.example.com.linksapp.R;
import alex.example.com.linksapp.StartApplication;
import alex.example.com.linksapp.data.entity.DetailsModel;
import alex.example.com.linksapp.utils.NetworkUtils;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {
    private DetailsContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mProgressBar = findViewById(R.id.progressBar);
        mLinearLayout = findViewById(R.id.linearLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbat_article);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPresenter = new DetailsPresenter(StartApplication.get(this).getService());
        mPresenter.bind(this);

        checkInternetConnection();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    private void checkInternetConnection() {
        if (NetworkUtils.isNetworkConnected(this)) {
            mPresenter.startDownloadDetails(getIntent());
        } else {
            Snackbar.make(mLinearLayout, R.string.snackbat_connection_failed, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.reload, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkInternetConnection();
                        }
                    }).show();
        }

    }

    @Override
    public void onError() {
        checkInternetConnection();
    }


    @Override
    public void onSuccess(DetailsModel model, String title) {
        TextView header = findViewById(R.id.title);
        TextView team1 = findViewById(R.id.team1);
        TextView prediction = findViewById(R.id.prediction);
        TextView article = findViewById(R.id.article);
        TextView time = findViewById(R.id.time);
        TextView tournament = findViewById(R.id.tournament);

        header.setText(title);
        team1.setText(model.getTeam1());
        time.setText(model.getTime());
        tournament.setText(model.getTournament());
        prediction.setText(model.getPrediction());
        for (int i = 0; i < model.getArticle().size(); i++) {
            article.append(model.getArticle().get(i).getHeader() + "\n \n" +
                    model.getArticle().get(i).getText() + "\n \n");
        }
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
