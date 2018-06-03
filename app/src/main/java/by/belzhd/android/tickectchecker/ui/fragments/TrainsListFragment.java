package by.belzhd.android.tickectchecker.ui.fragments;

import android.content.DialogInterface;
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
import java.util.Comparator;
import java.util.List;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.TicketCheckerApplication;
import by.belzhd.android.tickectchecker.data.PassengerTableFull;
import by.belzhd.android.tickectchecker.db.entities.general.Cariage;
import by.belzhd.android.tickectchecker.db.entities.general.Passengers;
import by.belzhd.android.tickectchecker.db.entities.general.PassengersStatus;
import by.belzhd.android.tickectchecker.db.entities.general.Route;
import by.belzhd.android.tickectchecker.db.entities.general.Seat;
import by.belzhd.android.tickectchecker.db.entities.general.StationCode;
import by.belzhd.android.tickectchecker.db.entities.general.Train;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;
import by.belzhd.android.tickectchecker.ui.adapters.FullTableAdapter;
import by.belzhd.android.tickectchecker.utils.AlertBuilder;

public class TrainsListFragment extends AbstractFragment implements View.OnClickListener, FullTableAdapter.OnItemClickListener {
    private Spinner leftSpinner;
    private Spinner rightSpinner;
    private RecyclerView recyclerView;
    private FullTableAdapter fullTableAdapter;
    private Button loadRoute;
    private Button refRoute;
    private Button sendReport;

    private List<PassengerTableFull> passengersTableFull = new ArrayList<>();

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

        ArrayAdapter<CharSequence> leftAdapter =  ArrayAdapter.createFromResource(getActivity(),
                R.array.left_spinner_list, R.layout.spinner_item_black);
        leftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leftSpinner.setAdapter(leftAdapter);
        leftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sortBySecondName();
                        break;
                    case 1:
                        sortBySeatNumber();
                        break;
                    case 2:
                        sortByStartStation();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> rightAdapter =  ArrayAdapter.createFromResource(getActivity(),
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
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void sortByStartStation() {
        if (passengersTableFull.size() > 0) {
            Collections.sort(passengersTableFull, (o1, o2) -> o1.getStartStation().compareTo(o2.getStartStation()));
            fullTableAdapter.notifyDataSetChanged();
        }
    }

    private void sortBySeatNumber() {
        if (passengersTableFull.size() > 0) {
            Collections.sort(passengersTableFull, (o1, o2) -> {
                if(o1.getSeatNumber() == o2.getSeatNumber())
                    return 0;
                return o1.getSeatNumber() < o2.getSeatNumber() ? -1 : 1;
            });
            fullTableAdapter.notifyDataSetChanged();
        }
    }

    private void sortBySecondName() {
        if (passengersTableFull.size() > 0) {
            Collections.sort(passengersTableFull, (o1, o2) -> o1.getSecondName().compareTo(o2.getSecondName()));
            fullTableAdapter.notifyDataSetChanged();
        }
    }

    private void loadFullData() {
        showProgress("Загрузка полной таблицы");
        new Thread(() -> {
            passengersTableFull.clear();
            Route route = TicketCheckerApplication.getGeneralDB().routeDao().getById(TicketCheckerApplication.prefs().getCurrentRoute());
            Train train = TicketCheckerApplication.getGeneralDB().trainDao().getTrainById(route.getTrainNumber());
            Cariage cariage = TicketCheckerApplication.getGeneralDB().cariageDao().getCarriageByTrainId(train.getId());
            List<PassengersStatus> passengersStatuses = TicketCheckerApplication.getGeneralDB()
                    .passengersStatusDao().getByRouteId(TicketCheckerApplication.prefs().getCurrentRoute());
            for (PassengersStatus passStatus: passengersStatuses) {
                PassengerTableFull passenger = new PassengerTableFull();

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
                fullTableAdapter.notifyDataSetChanged();
                hideProgress();
            });
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
    public void onItemClick(PassengerTableFull passenger) {
        LayoutInflater inflater = getLayoutInflater();
        View alertView = inflater.inflate(R.layout.full_table_intem_full_info_layout, null);
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

    private void showAlert() {
        AlertBuilder.showAlert(getActivity(), getActivity().getResources().getString(R.string.extra_report_title),
                getActivity().getResources().getString(R.string.extra_report_mesage),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showExtaReportScreen();
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
