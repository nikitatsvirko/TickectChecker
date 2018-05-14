package by.belzhd.android.tickectchecker.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.ui.fragments.DisembarkationFragment;
import by.belzhd.android.tickectchecker.ui.fragments.EmbarkationFragment;
import by.belzhd.android.tickectchecker.ui.fragments.TrainsListFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation =   findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        navigation.setSelectedItemId(R.id.navigation_embarkation);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_embarkation:
                fragment = EmbarkationFragment.newInstance();
                break;
            case R.id.navigation_disembarkation:
                fragment = DisembarkationFragment.newInstance();
                break;
            case R.id.navigation_dashboard:
                fragment = TrainsListFragment.newInstance();
                break;
        }
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment).commit();
        return true;
    }
}
