package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import by.belzhd.android.tickectchecker.R;

public class TrainsListFragment extends AbstractFragment {
    Spinner lSpinner;
    Spinner rSpinner;
    Button loadRoute;
    Button refRoute;
    Button sendReport;

    public static TrainsListFragment newInstance() {
        return new TrainsListFragment();
    }

    @Override
    protected void initUi(View view) {
        lSpinner = view.findViewById(R.id.leftspinner);
        rSpinner = view.findViewById(R.id.rightspinner);

        loadRoute = view.findViewById(R.id.load_route);
        refRoute = view.findViewById(R.id.ref_route);
        sendReport = view.findViewById(R.id.send_report);


        ArrayAdapter<CharSequence> leftAdapter =  ArrayAdapter.createFromResource(getActivity(),
                R.array.left_spinner_list, R.layout.spinner_item);
        leftAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lSpinner.setAdapter(leftAdapter);

        ArrayAdapter<CharSequence> rightAdapter =  ArrayAdapter.createFromResource(getActivity(),
                R.array.right_spinner_list, R.layout.spinner_item);
        rightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rSpinner.setAdapter(rightAdapter);

    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_trains_list;
    }

    @Override
    int getTitleResId() {
        return R.string.title_trains_list;
    }
}
