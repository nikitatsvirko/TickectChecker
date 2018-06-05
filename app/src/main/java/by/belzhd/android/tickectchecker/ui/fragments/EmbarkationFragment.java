package by.belzhd.android.tickectchecker.ui.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.transition.TransitionManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.TicketCheckerApplication;
import by.belzhd.android.tickectchecker.db.entities.general.Cariage;
import by.belzhd.android.tickectchecker.db.entities.general.Passengers;
import by.belzhd.android.tickectchecker.db.entities.general.PassengersStatus;
import by.belzhd.android.tickectchecker.db.entities.general.Route;
import by.belzhd.android.tickectchecker.db.entities.general.Seat;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.db.entities.general.Train;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_ENTRY;
import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_NOT_ENTRY;


public class EmbarkationFragment extends AbstractFragment implements View.OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final String EMPTY_STRING = "";

    private RelativeLayout container;
    private static AutoCompleteTextView stationAutoCompleteText;
    private static EditText carriageText;
    private static EditText seatText;
    private static TextView secondNameText;
    private static TextView initialsText;
    private Button qrButton;
    private Button scanButton;
    private Button startEmbButton;
    private LinearLayout finishButtonsContainer;
    private static RelativeLayout addPersonContainer;
    private Button checkPersonButton;
    private Button addEmbButton;
    private Button finishEmbButton;
    private Passengers currentPassenger;

    public static EmbarkationFragment newInstance() {
        return new EmbarkationFragment();
    }

    public EmbarkationFragment getInstance() {
        return this;
    }

    @Override
    protected void initUi(View view) {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        if (!isCameraPermissionGranted()) {
            requestCameraPermissions();
        }
        container = view.findViewById(R.id.container);
        finishButtonsContainer = view.findViewById(R.id.finishEmbButtonsContainer);
        addPersonContainer = view.findViewById(R.id.addPersonContainer);
        carriageText = view.findViewById(R.id.carriageEditText);
        seatText = view.findViewById(R.id.placeEditText);
        secondNameText = view.findViewById(R.id.secondNameEditText);
        initialsText = view.findViewById(R.id.firstNameEditText);
        checkPersonButton = view.findViewById(R.id.checkButton);
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
        checkPersonButton.setOnClickListener(this);
        initData();
        enableContainers(!TicketCheckerApplication.prefs().getIsEmbarkationStarted());
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

    private void initData() {
        showProgress("Загрузка всех станций");
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
                        hideProgress();
                        stationAutoCompleteText.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }

    private void checkPerson() {
        if (currentPassenger != null) {
            showProgress("Добавляю пассажира...");
            new Thread(() -> {
                PassengersStatus passengersStatus = TicketCheckerApplication.getGeneralDB().passengersStatusDao().getByPassengerId(currentPassenger.getId());
                passengersStatus.setStatus(STATUS_ENTRY);
                TicketCheckerApplication.getGeneralDB().passengersStatusDao().update(passengersStatus);
                TicketCheckerApplication.getGeneralDB().passengersDao().update(currentPassenger);

                getActivity().runOnUiThread(() -> {
                    hideProgress();
                    addPersonContainer.setVisibility(View.GONE);
                });
            }).start();
        }
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

    public void onCodeScanned(String data) {
        addPersonContainer.setVisibility(View.VISIBLE);
        manageData(data);
    }

    private void manageData(String data) {
        seatText.setOnKeyListener((v, keyCode, event) -> {
            if (carriageText.getText().toString().equals(EMPTY_STRING)) {
                return false;
            }
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                //TODO find user and insert his second name and initials
                searchForUser(data);
                return true;
            }
            return false;
        });
    }

    private void searchForUser(String data) {
        showProgress("Поиск пассажира...");
        new Thread(() -> {
            Route route = TicketCheckerApplication.getGeneralDB().routeDao().getById(TicketCheckerApplication.prefs().getCurrentRoute());
            Train train = TicketCheckerApplication.getGeneralDB().trainDao().getTrainById(route.getTrainNumber());
            Cariage cariage = TicketCheckerApplication.getGeneralDB().cariageDao()
                    .getCarriageByNumberAndTrainId(Integer.parseInt(carriageText.getText().toString()), train.getId());
            Seat seat = TicketCheckerApplication.getGeneralDB().seatDao()
                    .getSeatByNumberAndCarriageId(Integer.parseInt(seatText.getText().toString()), cariage.getId());
            StationCode station = TicketCheckerApplication.getGeneralDB().stationCodeDao().getStationCodeByDescription(stationAutoCompleteText.getText().toString().trim());
            PassengersStatus passengersStatus = TicketCheckerApplication.getGeneralDB()
                    .passengersStatusDao().getPassengerStatusBy(seat.getId(), route.getId(), station.getId(), STATUS_NOT_ENTRY);
            Passengers passengers = TicketCheckerApplication.getGeneralDB().passengersDao().getPassengerById(passengersStatus.getPassenger());

            this.getActivity().runOnUiThread(() -> {
                hideProgress();
                secondNameText.setText(passengers.getSurname());
                initialsText.setText(passengers.getInitials());
                passengers.setTicketNumber(Integer.parseInt(data));
                currentPassenger = passengers;
            });
        }).start();
    }

    private void onFinishClicked() {
        TicketCheckerApplication.prefs().setIsEmbarkationStarted(false);
        stationAutoCompleteText.setText(EMPTY_STRING);
        enableContainers(true);
    }

    private void onStartClicked() {
        if (!stationAutoCompleteText.getText().toString().isEmpty()) {
            if (!TicketCheckerApplication.prefs().getIsDisembarkationStarted()) {
                TicketCheckerApplication.prefs().setIsEmbarkationStarted(true);
                enableContainers(false);
            } else {
                showToast("Завершите высадку!");
            }
        } else {
            showToast("Введите станцию!");
        }
    }

    private void enableContainers(boolean isEnabled) {
        startEmbButton.setVisibility(isEnabled ? View.VISIBLE : View.INVISIBLE);
        finishButtonsContainer.setVisibility(isEnabled ? View.INVISIBLE : View.VISIBLE);
        stationAutoCompleteText.setEnabled(isEnabled);
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
