package by.belzhd.android.tickectchecker.ui.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.transition.TransitionManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.zxing.integration.android.IntentIntegrator;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

public class EmbarkationFragment extends AbstractFragment implements View.OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final String EMPTY_STRING = "";

    private RelativeLayout container;
    private static AutoCompleteTextView stationAutoCompleteText;
    private Button qrButton;
    private Button scanButton;
    private Button startEmbButton;
    private LinearLayout finishButtonsContainer;
    private static RelativeLayout addPersonContainer;
    private Button checPersonButton;
    private Button addEmbButton;
    private Button finishEmbButton;

    public static EmbarkationFragment newInstance() {
        return new EmbarkationFragment();
    }

    @Override
    protected void initUi(View view) {
        if (!isCameraPermissionGranted()) {
            requestCameraPermissions();
        }
        container = view.findViewById(R.id.container);
        finishButtonsContainer = view.findViewById(R.id.finishEmbButtonsContainer);
        addPersonContainer = view.findViewById(R.id.addPersonContainer);
        checPersonButton = view.findViewById(R.id.checkButton);
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
        checPersonButton.setOnClickListener(this);
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
                if (!isCameraPermissionGranted()) {
                    return;
                } else {
                    startQrScan();
                }
                break;
            case R.id.scanButton:
                if (!isCameraPermissionGranted()) {
                    return;
                } else {
                    startScan();
                }
                break;
            case R.id.addEmbButton:
                showAddScreen();
                break;
            case R.id.checkButton:
                checkPerson();
                break;
        }
    }

    private void checkPerson() {
        addPersonContainer.setVisibility(View.GONE);
        stationAutoCompleteText.setText(EMPTY_STRING);
    }

    private void showAddScreen() {
        ((MainActivity) getActivity()).hideBottomNavigation();
        ((MainActivity) getActivity()).replaceFragment(EmbarkationAddFragment.newInstance(), true);
    }

    private void startScan() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ITF);
        integrator.setPrompt(EMPTY_STRING);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    private void startQrScan() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PDF_417, IntentIntegrator.QR_CODE);
        integrator.setPrompt(EMPTY_STRING);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    public static void onCodeScanned(String data) {
        addPersonContainer.setVisibility(View.VISIBLE);
        stationAutoCompleteText.setText(data);
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

    private boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
    }
}
