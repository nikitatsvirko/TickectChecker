package by.belzhd.android.tickectchecker.ui.fragments;

import android.content.DialogInterface;
import android.support.transition.TransitionManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.TicketCheckerApplication;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

public class DisembarkationFragment extends AbstractFragment implements View.OnClickListener {

    private RelativeLayout container;
    private AutoCompleteTextView stationAutoCompleteText;
    private Button startDisEmbButton;
    private LinearLayout finishButtonsContainer;
    private Button addDisEmbButton;
    private Button finishDisEmbButton;

    public static DisembarkationFragment newInstance() {
        return new DisembarkationFragment();
    }

    @Override
    protected void initUi(View view) {
        container = view.findViewById(R.id.container);
        finishButtonsContainer = view.findViewById(R.id.finishDisEmbButtonsContainer);
        stationAutoCompleteText = view.findViewById(R.id.stationAutoComplete);
        startDisEmbButton = view.findViewById(R.id.startDisEmbButton);
        addDisEmbButton = view.findViewById(R.id.addDisEmbButton);
        finishDisEmbButton = view.findViewById(R.id.finishDisEmbButton);

        startDisEmbButton.setOnClickListener(this);
        addDisEmbButton.setOnClickListener(this);
        finishDisEmbButton.setOnClickListener(this);
        initData();
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_disembarkation;
    }

    @Override
    int getTitleResId() {
        return R.string.title_disembarkation;
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(container);
        switch (v.getId()) {
            case R.id.startDisEmbButton:
                onStartClicked();
                break;
            case R.id.addDisEmbButton:
                showAddScreen();
                break;
            case R.id.finishDisEmbButton:
                showAlert();
                break;
        }
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<StationCode> stationCodeList = TicketCheckerApplication.getGeneralDB().stationCodeDao().getAll();
                List<String> stationsList = new ArrayList<>();
                for (StationCode station : stationCodeList) {
                    stationsList.add(station.getDescription());
                }
                final ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, stationsList);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stationAutoCompleteText.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    private void showAddScreen() {
        ((MainActivity) getActivity()).hideBottomNavigation();
        ((MainActivity) getActivity()).replaceFragment(DisembarkationAddFragment.newInstance(), true);
    }

    private void onFinishClicked() {
        startDisEmbButton.setVisibility(View.VISIBLE);
        finishButtonsContainer.setVisibility(View.GONE);
        stationAutoCompleteText.setEnabled(true);
    }

    private void onStartClicked() {
        startDisEmbButton.setVisibility(View.GONE);
        finishButtonsContainer.setVisibility(View.VISIBLE);
        stationAutoCompleteText.setEnabled(false);
    }

    private void showAlert() {
        AlertBuilder.showAlert(getActivity(), getActivity().getResources().getString(R.string.finish_disemb_text),
                getActivity().getResources().getString(R.string.finish_disemb_message),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onFinishClicked();
                        dialog.cancel();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }
}
