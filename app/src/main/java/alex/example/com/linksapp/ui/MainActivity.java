package alex.example.com.linksapp.ui;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import alex.example.com.linksapp.Callback;
import alex.example.com.linksapp.R;
import alex.example.com.linksapp.utils.NetworkUtils;
import alex.example.com.linksapp.utils.ZoomOutTransformation;
import alex.example.com.linksapp.ui.news_list.fragments.BasketballFragment;
import alex.example.com.linksapp.ui.news_list.fragments.CybersportFragment;
import alex.example.com.linksapp.ui.news_list.fragments.FootballFragment;
import alex.example.com.linksapp.ui.news_list.fragments.HockeyFragment;
import alex.example.com.linksapp.ui.news_list.fragments.TennisFragment;
import alex.example.com.linksapp.ui.news_list.fragments.VolleyballFragment;

public class MainActivity extends AppCompatActivity implements Callback {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_main_title);
        setSupportActionBar(toolbar);


        createTabs();
    }

    private void createTabs() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        ArrayList<TabMain> tabs = new ArrayList<>();
        tabs.add(new TabMain(new FootballFragment(), getString(R.string.viewpager_football)));
        tabs.add(new TabMain(new BasketballFragment(), getString(R.string.viewpager_basketball)));
        tabs.add(new TabMain(new TennisFragment(), getString(R.string.viewpager_tennis)));
        tabs.add(new TabMain(new VolleyballFragment(), getString(R.string.viewpager_volleyball)));
        tabs.add(new TabMain(new HockeyFragment(), getString(R.string.viewpager_hockey)));
        tabs.add(new TabMain(new CybersportFragment(), getString(R.string.viewpager_cybersport)));
        viewPager.setOffscreenPageLimit(6);
        viewPager.setAdapter(new TabStateAdapter(getSupportFragmentManager(), tabs));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(true, new ZoomOutTransformation());
        networkConnection();


    }

    public void networkConnection() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        if (!NetworkUtils.isNetworkConnected(this)) {
            Snackbar.make(linearLayout, R.string.snackbat_connection_failed, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.reload, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            createTabs();

                        }
                    }).show();
        }
    }

    @Override
    public void refreshAdapter() {
        networkConnection();
    }
}
