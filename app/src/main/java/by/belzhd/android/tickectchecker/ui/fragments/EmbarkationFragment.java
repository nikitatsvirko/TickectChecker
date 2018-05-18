package by.belzhd.android.tickectchecker.ui.fragments;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.transition.TransitionManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.TicketCheckerApplication;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

public class EmbarkationFragment extends AbstractFragment implements View.OnClickListener {

    private RelativeLayout container;
    private AutoCompleteTextView stationAutoCompleteText;
    private Button qrButton;
    private Button scanButton;
    private Button startEmbButton;
    private LinearLayout finishButtonsContainer;
    private RelativeLayout addPersconContainer;
    private Button addEmbButton;
    private Button finishEmbButton;

    public static EmbarkationFragment newInstance() {
        return new EmbarkationFragment();
    }

    @Override
    protected void initUi(View view) {
        container = view.findViewById(R.id.container);
        finishButtonsContainer = view.findViewById(R.id.finishEmbButtonsContainer);
        addPersconContainer = view.findViewById(R.id.addPersonContainer);
        stationAutoCompleteText = view.findViewById(R.id.stationAutoComplete);
        qrButton = view.findViewById(R.id.qrButton);
        scanButton = view.findViewById(R.id.scanButton);
        startEmbButton = view.findViewById(R.id.startEmbButton);
        addEmbButton = view.findViewById(R.id.addEmbButton);
        finishEmbButton = view.findViewById(R.id.finishEmbButton);

        startEmbButton.setOnClickListener(this);
        finishEmbButton.setOnClickListener(this);
        addEmbButton.setOnClickListener(this);
        qrButton.setOnClickListener(this);
        scanButton.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_embarkation;
    }

    @Override
    int getTitleResId() {
        return R.string.title_embarkation;
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(container);
        switch (v.getId()) {
            case R.id.startEmbButton:
                onStartClicked();
                break;
            case R.id.finishEmbButton:
                showAlert();
                break;
            case R.id.qrButton:
                startQrScan();
                break;
            case R.id.scanButton:
                startScan();
                break;
            case R.id.addEmbButton:
                showAddScreen();
                break;
        }
    }

    private void showAddScreen() {
        ((MainActivity) getActivity()).hideBottomNavigation();
        ((MainActivity) getActivity()).replaceFragment(EmbarkationAddFragment.newInstance(), true);
    }

    private void startScan() {
        addPersconContainer.setVisibility(View.GONE);
    }

    private void startQrScan() {
        showToast("Scanning...");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addPersconContainer.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    private void onFinishClicked() {
        startEmbButton.setVisibility(View.VISIBLE);
        finishButtonsContainer.setVisibility(View.GONE);
        stationAutoCompleteText.setEnabled(true);
    }

    private void onStartClicked() {
        startEmbButton.setVisibility(View.GONE);
        finishButtonsContainer.setVisibility(View.VISIBLE);
        stationAutoCompleteText.setEnabled(false);
    }

    private void showAlert() {
        AlertBuilder.showAlert(getActivity(), getActivity().getResources().getString(R.string.finish_emb_text),
                getActivity().getResources().getString(R.string.finish_emb_message),
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
