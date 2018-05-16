package by.belzhd.android.tickectchecker.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.ui.fragments.DisembarkationFragment;
import by.belzhd.android.tickectchecker.ui.fragments.EmbarkationFragment;
import by.belzhd.android.tickectchecker.ui.fragments.TrainsListFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    private RelativeLayout container;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.layout_container);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigation = findViewById(R.id.navigation);
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
        replaceFragment(fragment, false);
        return true;
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        TransitionManager.beginDelayedTransition(container);
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.container, fragment).commit();
    }

    public void hideBottomNavigation() {
        navigation.setVisibility(View.GONE);
    }

    public void showBottonNavigation() {
        navigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (navigation.getVisibility() == View.GONE) {
            navigation.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }
}
