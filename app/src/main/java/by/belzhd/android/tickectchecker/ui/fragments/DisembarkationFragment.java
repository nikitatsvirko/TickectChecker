package by.belzhd.android.tickectchecker.ui.fragments;

import android.content.DialogInterface;
import android.support.transition.TransitionManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import by.belzhd.android.tickectchecker.data.PassengerDisemb;
import by.belzhd.android.tickectchecker.data.PassengerTableEntity;
import by.belzhd.android.tickectchecker.db.entities.general.Cariage;
import by.belzhd.android.tickectchecker.db.entities.general.Passengers;
import by.belzhd.android.tickectchecker.db.entities.general.PassengersStatus;
import by.belzhd.android.tickectchecker.db.entities.general.Route;
import by.belzhd.android.tickectchecker.db.entities.general.Seat;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.db.entities.general.Train;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.ui.adapters.DisEmbAdapter;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

import static by.belzhd.android.tickectchecker.utils.Constants.EMPTY_STRING;
import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_ENTRY;
import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_EXIT;

public class DisembarkationFragment extends AbstractFragment implements View.OnClickListener, DisEmbAdapter.OnSwithed {

    private RelativeLayout container;
    private AutoCompleteTextView stationAutoCompleteText;
    private Button startDisEmbButton;
    private LinearLayout finishButtonsContainer;
    private RecyclerView recyclerView;
    private Button addDisEmbButton;
    private Button finishDisEmbButton;

    private DisEmbAdapter adapter;
    private List<PassengerDisemb> mPassengers = new ArrayList<>();

    public static DisembarkationFragment newInstance() {
        return new DisembarkationFragment();
    }

    @Override
    protected void initUi(View view) {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        container = view.findViewById(R.id.container);
        finishButtonsContainer = view.findViewById(R.id.finishDisEmbButtonsContainer);
        stationAutoCompleteText = view.findViewById(R.id.stationAutoComplete);
        startDisEmbButton = view.findViewById(R.id.startDisEmbButton);
        addDisEmbButton = view.findViewById(R.id.addDisEmbButton);
        finishDisEmbButton = view.findViewById(R.id.finishDisEmbButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        startDisEmbButton.setOnClickListener(this);
        addDisEmbButton.setOnClickListener(this);
        finishDisEmbButton.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DisEmbAdapter(getActivity(), mPassengers, this);
        recyclerView.setAdapter(adapter);

        initData();
        enableContainers(!TicketCheckerApplication.prefs().getIsDisembarkationStarted());
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

    private void showAddScreen() {
        ((MainActivity) getActivity()).hideBottomNavigation();
        ((MainActivity) getActivity()).replaceFragment(DisembarkationAddFragment.newInstance(), true);
    }

    private void onFinishClicked() {
        TicketCheckerApplication.prefs().setIsDisembarkationStarted(false);
        stationAutoCompleteText.setText(EMPTY_STRING);
        enableContainers(true);
    }

    private void onStartClicked() {
        if (!stationAutoCompleteText.getText().toString().isEmpty()) {
            if (!TicketCheckerApplication.prefs().getIsEmbarkationStarted()) {
                TicketCheckerApplication.prefs().setIsDisembarkationStarted(true);
                enableContainers(false);
                loadData();
            } else {
                showToast("Завершите посадку!");
            }
        } else {
            showToast("Введите станцию!");
        }
    }

    private void enableContainers(boolean isEnabled) {
        recyclerView.setVisibility(isEnabled ? View.INVISIBLE : View.VISIBLE);
        startDisEmbButton.setVisibility(isEnabled ? View.VISIBLE : View.INVISIBLE);
        finishButtonsContainer.setVisibility(isEnabled ? View.INVISIBLE : View.VISIBLE);
        stationAutoCompleteText.setEnabled(isEnabled);
    }

    @Override
    public void onPassengerChecked(PassengerDisemb passenger, boolean isChecked) {
        showProgress("Обновляю...");
        new Thread(() -> {
            PassengersStatus passengersStatus = TicketCheckerApplication.getGeneralDB().passengersStatusDao().getByPassengerId(passenger.getId());
            passengersStatus.setStatus(isChecked ? STATUS_EXIT : STATUS_ENTRY);
            TicketCheckerApplication.getGeneralDB().passengersStatusDao().update(passengersStatus);
        }).start();
        hideProgress();
    }

    private void showAlert() {
        AlertBuilder.showAlert(getActivity(), getActivity().getResources().getString(R.string.finish_disemb_text),
                getActivity().getResources().getString(R.string.finish_disemb_message),
                (dialog, which) -> {
                    onFinishClicked();
                    dialog.cancel();
                },
                (dialog, which) -> dialog.cancel());
    }

    private void loadData() {
        showProgress("Загрузка текущей таблицы");
        new Thread(() -> {
            mPassengers.clear();
            Route route = TicketCheckerApplication.getGeneralDB().routeDao().getById(TicketCheckerApplication.prefs().getCurrentRoute());
            Train train = TicketCheckerApplication.getGeneralDB().trainDao().getTrainById(route.getTrainNumber());
            Cariage cariage = TicketCheckerApplication.getGeneralDB().cariageDao().getCarriageByTrainId(train.getId());
            List<PassengersStatus> passengersStatuses = TicketCheckerApplication.getGeneralDB()
                    .passengersStatusDao().getByRouteIdAndStatus(TicketCheckerApplication.prefs().getCurrentRoute(), STATUS_ENTRY);
            for (PassengersStatus passStatus: passengersStatuses) {
                PassengerDisemb passenger = new PassengerDisemb();

                StationCode exitStation = TicketCheckerApplication.getGeneralDB().stationCodeDao()
                        .getStationCodeById(passStatus.getExitStation());
                Passengers passengers = TicketCheckerApplication.getGeneralDB().passengersDao()
                        .getPassengerById(passStatus.getPassenger());
                Seat seat = TicketCheckerApplication.getGeneralDB().seatDao().getSeatById(passStatus.getSeatId());

                passenger.setId(passengers.getId());
                passenger.setSecondName(passengers.getSurname());
                passenger.setInitials(passengers.getInitials());
                passenger.setCarriageNumber(cariage.getNumber());
                passenger.setSeatNumber(seat.getNumber());
                passenger.setStatus(passStatus.getStatus());
                if (exitStation.getDescription().equals(stationAutoCompleteText.getText().toString())) {
                    mPassengers.add(passenger);
                }
            }

            getActivity().runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
                hideProgress();
            });
        }).start();
    }
}
