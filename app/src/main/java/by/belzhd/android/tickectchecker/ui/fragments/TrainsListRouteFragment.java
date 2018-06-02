package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;
import android.widget.Button;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.ui.activity.MainActivity;

public class TrainsListRouteFragment extends AbstractFragment implements View.OnClickListener {

    private Button addRouteButton;
    private Button cancelRouteButton;

    public static TrainsListRouteFragment newInstance() {
        return new TrainsListRouteFragment();
    }

    @Override
    protected void initUi(View view) {
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        addRouteButton = view.findViewById(R.id.addRouteButton);
        cancelRouteButton = view.findViewById(R.id.cancelRouteButton);

        addRouteButton.setOnClickListener(this);
        cancelRouteButton.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_trains_list_route;
    }

    @Override
    int getTitleResId() {
        return  R.string.load_route;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addRouteButton:
                //addPerson();
                break;
            case R.id.cancelRouteButton:
                cancelAction();
                break;
        }

    }

    private void cancelAction() {
        getActivity().onBackPressed();
    }
}
