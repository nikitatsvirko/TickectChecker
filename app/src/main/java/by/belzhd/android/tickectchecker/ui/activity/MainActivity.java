package by.belzhd.android.tickectchecker.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.TicketCheckerApplication;
import by.belzhd.android.tickectchecker.db.entities.general.Route;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.ui.fragments.DisembarkationFragment;
import by.belzhd.android.tickectchecker.ui.fragments.EmbarkationFragment;
import by.belzhd.android.tickectchecker.ui.fragments.TrainsListFragment;

import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_LOADED;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private Spinner mSpinner;
    private RelativeLayout container;
    private Fragment currentFragment;
    private Fragment fragmentEmb = EmbarkationFragment.newInstance();
    private Fragment fragmentDisemb = DisembarkationFragment.newInstance();
    private Fragment fragmentList = TrainsListFragment.newInstance();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (currentFragment instanceof EmbarkationFragment) {
                EmbarkationFragment.onCodeScanned("21001175627024");
            }
            /*if(result.getContents() == null) {
                Toast.makeText(this, R.string.cancelled_text, Toast.LENGTH_LONG).show();
            } else {
                if (currentFragment instanceof EmbarkationFragment) {
                    EmbarkationFragment.onCodeScanned(result.getContents());
                }
            }*/
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_embarkation:
                currentFragment = fragmentEmb;
                replaceFragment(fragmentEmb, false);
                break;
            case R.id.navigation_disembarkation:
                currentFragment = fragmentDisemb;
                replaceFragment(fragmentDisemb, false);
                break;
            case R.id.navigation_dashboard:
                currentFragment = fragmentList;
                replaceFragment(fragmentList, false);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        mSpinner = (Spinner) item.getActionView();
        /*if (TicketCheckerApplication.prefs().getCurrentRoute() != -1) {
            mSpinner.setSelection(0);
        }*/
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO improvements kostyl'
                TicketCheckerApplication.prefs().setCurrentRoute(2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        initData();
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Route> routesList = TicketCheckerApplication.getGeneralDB().routeDao().getByStatus(STATUS_LOADED);
                List<String> routesInString = new ArrayList<>();
                for (Route route : routesList) {
                    StationCode startStation = TicketCheckerApplication.getGeneralDB().stationCodeDao().getStationCodeById(route.getStartStation());
                    StationCode endStation = TicketCheckerApplication.getGeneralDB().stationCodeDao().getStationCodeById(route.getEndStation());
                    routesInString.add(startStation.getDescription() + " - " + endStation.getDescription());
                }
                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, routesInString);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSpinner.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    public Spinner getSpinner() {
        return mSpinner;
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        TransitionManager.beginDelayedTransition(container);
        final FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(R.id.container, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void hideBottomNavigation() {
        navigation.setVisibility(View.GONE);
    }

    public void showBottomNavigation() {
        navigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (navigation.getVisibility() == View.GONE) {
            showBottomNavigation();
        }
        super.onBackPressed();
    }
}
