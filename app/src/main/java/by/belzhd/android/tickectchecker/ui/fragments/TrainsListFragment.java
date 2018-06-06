package by.belzhd.android.tickectchecker.ui.fragments;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.TicketCheckerApplication;
import by.belzhd.android.tickectchecker.data.PassengerTableEntity;
import by.belzhd.android.tickectchecker.db.entities.general.Cariage;
import by.belzhd.android.tickectchecker.db.entities.general.Passengers;
import by.belzhd.android.tickectchecker.db.entities.general.PassengersStatus;
import by.belzhd.android.tickectchecker.db.entities.general.Route;
import by.belzhd.android.tickectchecker.db.entities.general.Seat;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.db.entities.general.Train;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.ui.adapters.CurrentTableAdapter;
import by.belzhd.android.tickectchecker.ui.adapters.FullTableAdapter;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

import static by.belzhd.android.tickectchecker.utils.Constants.STATUS_ENTRY;

public class TrainsListFragment extends AbstractFragment implements View.OnClickListener, FullTableAdapter.OnItemClickListener, CurrentTableAdapter.OnItemClickListener {
    private Spinner leftSpinner;
    private Spinner rightSpinner;
    private RecyclerView recyclerView;
    private FullTableAdapter fullTableAdapter;
    private CurrentTableAdapter currentTableAdapter;
    private Button loadRoute;
    private Button refRoute;
    private Button sendReport;

    private List<PassengerTableEntity> passengersTableFull = new ArrayList<>();
    private List<PassengerTableEntity> passengersTableCurrent = new ArrayList<>();

    public static TrainsListFragment newInstance() {
        return new TrainsListFragment();
    }

    @Override
    protected void initUi(View view) {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
        leftSpinner = view.findViewById(R.id.leftSpinner);
        rightSpinner = view.findViewById(R.id.rightSpinner);

        loadRoute = view.findViewById(R.id.load_route);
        refRoute = view.findViewById(R.id.ref_route);
        sendReport = view.findViewById(R.id.send_report);

        loadRoute.setOnClickListener(this);
        refRoute.setOnClickListener(this);
        sendReport.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fullTableAdapter = new FullTableAdapter(getActivity(), passengersTableFull, this);
        recyclerView.setAdapter(fullTableAdapter);

        currentTableAdapter = new CurrentTableAdapter(getActivity(), passengersTableCurrent, this);

        ArrayAdapter<CharSequence> rightAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.right_spinner_list, R.layout.spinner_item_black);
        rightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rightSpinner.setAdapter(rightAdapter);
        rightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        loadFullData();
                        break;
                    case 1:
                        loadCurrentData();
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> leftAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.left_spinner_list, R.layout.spinner_item_black);
        leftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(leftAdapter);
        leftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (rightSpinner.getSelectedItemPosition() == 0) {
                            sortFullBySecondName();
                        } else if (rightSpinner.getSelectedItemPosition() == 1) {
                            sortCurrentBySecondName();
                        }
                        break;
                    case 1:
                        if (rightSpinner.getSelectedItemPosition() == 0) {
                            sortFullBySeatNumber();
                        } else if (rightSpinner.getSelectedItemPosition() == 1) {
                            sortCurrentBySeatNumber();
                        }
                        break;
                    case 2:
                        if (rightSpinner.getSelectedItemPosition() == 0) {
                            sortFullByStation();
                        } else if (rightSpinner.getSelectedItemPosition() == 1) {
                            sortCurrentByStation();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void sortFullByStation() {
        sortByEndStation(passengersTableFull, fullTableAdapter);
    }

    private void sortCurrentByStation() {
        sortByEndStation(passengersTableCurrent, currentTableAdapter);
    }

    private void sortByEndStation(List<PassengerTableEntity> list, RecyclerView.Adapter adapter) {
        if (list.size() > 0) {
            Collections.sort(list, (o1, o2) -> o1.getEndStation().compareTo(o2.getEndStation()));
            adapter.notifyDataSetChanged();
        }
    }

    private void sortFullBySeatNumber() {
        sortBySeatNumber(passengersTableFull, fullTableAdapter);
    }

    private void sortCurrentBySeatNumber() {
        sortBySeatNumber(passengersTableCurrent, currentTableAdapter);
    }

    private void sortBySeatNumber(List<PassengerTableEntity> list, RecyclerView.Adapter adapter) {
        if (list.size() > 0) {
            Collections.sort(list, (o1, o2) -> {
                if (o1.getSeatNumber() == o2.getSeatNumber())
                    return 0;
                return o1.getSeatNumber() < o2.getSeatNumber() ? -1 : 1;
            });
            adapter.notifyDataSetChanged();
        }
    }

    private void sortFullBySecondName() {
        sortBySecondName(passengersTableFull, fullTableAdapter);
    }

    private void sortCurrentBySecondName() {
        sortBySecondName(passengersTableCurrent, currentTableAdapter);
    }

    private void sortBySecondName(List<PassengerTableEntity> list, RecyclerView.Adapter adapter) {
        if (list.size() > 0) {
            Collections.sort(list, (o1, o2) -> o1.getSecondName().compareTo(o2.getSecondName()));
            adapter.notifyDataSetChanged();
        }
    }

    private void loadFullData() {
        showProgress("Загрузка полной таблицы");
        new Thread(() -> {
            try {
                passengersTableFull.clear();

                Route route = TicketCheckerApplication.getGeneralDB().routeDao().getById(TicketCheckerApplication.prefs().getCurrentRoute());
                Train train = TicketCheckerApplication.getGeneralDB().trainDao().getTrainById(route.getTrainNumber());
                Cariage cariage = TicketCheckerApplication.getGeneralDB().cariageDao().getCarriageByTrainId(train.getId());
                List<PassengersStatus> passengersStatuses = TicketCheckerApplication.getGeneralDB()
                        .passengersStatusDao().getByRouteId(TicketCheckerApplication.prefs().getCurrentRoute());
                for (PassengersStatus passStatus : passengersStatuses) {
                    PassengerTableEntity passenger = new PassengerTableEntity();

                    StationCode startStation = TicketCheckerApplication.getGeneralDB().stationCodeDao()
                            .getStationCodeById(passStatus.getEntryStation());
                    StationCode exitStation = TicketCheckerApplication.getGeneralDB().stationCodeDao()
                            .getStationCodeById(passStatus.getExitStation());
                    Passengers passengers = TicketCheckerApplication.getGeneralDB().passengersDao()
                            .getPassengerById(passStatus.getPassenger());
                    Seat seat = TicketCheckerApplication.getGeneralDB().seatDao().getSeatById(passStatus.getSeatId());

                    passenger.setSecondName(passengers.getSurname());
                    passenger.setInitials(passengers.getInitials());
                    passenger.setCarriageNumber(cariage.getNumber());
                    passenger.setStatus(passStatus.getStatus());
                    passenger.setStartStation(startStation.getDescription());
                    passenger.setEndStation(exitStation.getDescription());
                    passenger.setSeatNumber(seat.getNumber());
                    passengersTableFull.add(passenger);
                }

                getActivity().runOnUiThread(() -> {
                    recyclerView.setAdapter(fullTableAdapter);
                    fullTableAdapter.notifyDataSetChanged();
                    switch (leftSpinner.getSelectedItemPosition()) {
                        case 0:
                            sortFullBySecondName();
                            break;
                        case 1:
                            sortFullBySeatNumber();
                            break;
                        case 2:
                            sortFullByStation();
                            break;
                    }
                    hideProgress();
                });
            } catch (NullPointerException e) {
                getActivity().runOnUiThread(() -> {
                    hideProgress();
                    showToast("Не удалось загрузить данные");
                });
            }
        }).start();
    }

    private void loadCurrentData() {
        showProgress("Загрузка текущей таблицы");
        new Thread(() -> {
            try {
                passengersTableCurrent.clear();

                Route route = TicketCheckerApplication.getGeneralDB().routeDao().getById(TicketCheckerApplication.prefs().getCurrentRoute());
                Train train = TicketCheckerApplication.getGeneralDB().trainDao().getTrainById(route.getTrainNumber());
                Cariage cariage = TicketCheckerApplication.getGeneralDB().cariageDao().getCarriageByTrainId(train.getId());
                List<PassengersStatus> passengersStatuses = TicketCheckerApplication.getGeneralDB()
                        .passengersStatusDao().getByRouteIdAndStatus(TicketCheckerApplication.prefs().getCurrentRoute(), STATUS_ENTRY);
                for (PassengersStatus passStatus : passengersStatuses) {
                    PassengerTableEntity passenger = new PassengerTableEntity();

                    StationCode exitStation = TicketCheckerApplication.getGeneralDB().stationCodeDao()
                            .getStationCodeById(passStatus.getExitStation());
                    Passengers passengers = TicketCheckerApplication.getGeneralDB().passengersDao()
                            .getPassengerById(passStatus.getPassenger());
                    Seat seat = TicketCheckerApplication.getGeneralDB().seatDao().getSeatById(passStatus.getSeatId());

                    passenger.setSecondName(passengers.getSurname());
                    passenger.setInitials(passengers.getInitials());
                    passenger.setCarriageNumber(cariage.getNumber());
                    passenger.setEndStation(exitStation.getDescription());
                    passenger.setSeatNumber(seat.getNumber());
                    passengersTableCurrent.add(passenger);
                }

                getActivity().runOnUiThread(() -> {
                    recyclerView.setAdapter(currentTableAdapter);
                    currentTableAdapter.notifyDataSetChanged();
                    switch (leftSpinner.getSelectedItemPosition()) {
                        case 0:
                            sortCurrentBySecondName();
                            break;
                        case 1:
                            sortCurrentBySeatNumber();
                            break;
                        case 2:
                            sortCurrentByStation();
                            break;
                    }
                    hideProgress();
                });
            } catch (NullPointerException e) {
                getActivity().runOnUiThread(() -> {
                    hideProgress();
                    showToast("Не удалось загрузить данные");
                });
            }
        }).start();
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_trains_list;
    }

    @Override
    int getTitleResId() {
        return R.string.title_trains_list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.load_route:
                showLoadScreen();
                break;
            case R.id.ref_route:
                // showAddScreen();
                break;
            case R.id.send_report:
                showAlert();
                break;
        }
    }

    private void showLoadScreen() {
        ((MainActivity) getActivity()).hideBottomNavigation();
        ((MainActivity) getActivity()).replaceFragment(TrainsListRouteFragment.newInstance(), true);
    }

    private void showExtaReportScreen() {
        ((MainActivity) getActivity()).hideBottomNavigation();
        ((MainActivity) getActivity()).replaceFragment(TrainsListReportFragment.newInstance(), true);
    }

    @Override
    public void onFullItemClick(PassengerTableEntity passenger) {
        LayoutInflater inflater = getLayoutInflater();
        View alertView = inflater.inflate(R.layout.full_table_item_full_info_layout, null);
        TextView secondNameText = alertView.findViewById(R.id.secondNameValueText);
        secondNameText.setText(passenger.getSecondName());
        TextView initialsText = alertView.findViewById(R.id.initialsValueText);
        initialsText.setText(passenger.getInitials());
        TextView carriageText = alertView.findViewById(R.id.carriageValueText);
        carriageText.setText(String.valueOf(passenger.getCarriageNumber()));
        TextView seatText = alertView.findViewById(R.id.seatValueText);
        seatText.setText(String.valueOf(passenger.getSeatNumber()));
        TextView startStationText = alertView.findViewById(R.id.startStationValueText);
        startStationText.setText(passenger.getStartStation());
        TextView endStationText = alertView.findViewById(R.id.endStationValueText);
        endStationText.setText(passenger.getEndStation());
        TextView statusText = alertView.findViewById(R.id.statusValueText);
        statusText.setText(passenger.getStatus());

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Вся информация");
        alert.setView(alertView);
        alert.setCancelable(true);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public void onCurrentItemClick(PassengerTableEntity passenger) {
        LayoutInflater inflater = getLayoutInflater();
        View alertView = inflater.inflate(R.layout.current_table_item_full_info_layout, null);
        TextView secondNameText = alertView.findViewById(R.id.secondNameValueText);
        secondNameText.setText(passenger.getSecondName());
        TextView initialsText = alertView.findViewById(R.id.initialsValueText);
        initialsText.setText(passenger.getInitials());
        TextView carriageText = alertView.findViewById(R.id.carriageValueText);
        carriageText.setText(String.valueOf(passenger.getCarriageNumber()));
        TextView seatText = alertView.findViewById(R.id.seatValueText);
        seatText.setText(String.valueOf(passenger.getSeatNumber()));
        TextView endStationText = alertView.findViewById(R.id.endStationValueText);
        endStationText.setText(passenger.getEndStation());

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Вся информация");
        alert.setView(alertView);
        alert.setCancelable(true);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void showAlert() {
        AlertBuilder.showAlert(getActivity(), getActivity().getResources().getString(R.string.extra_report_title),
                getActivity().getResources().getString(R.string.extra_report_mesage),
                (dialog, which) -> {
                    showExtaReportScreen();
                    dialog.cancel();
                },
                (dialog, which) -> dialog.cancel());
    }
}
