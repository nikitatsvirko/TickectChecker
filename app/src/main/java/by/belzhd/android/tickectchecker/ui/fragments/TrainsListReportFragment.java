package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;
import android.widget.Button;

import by.belzhd.android.tickectchecker.R;

public class TrainsListReportFragment extends AbstractFragment implements View.OnClickListener {

    private Button addReportButton;
    private Button cancelReportButton;

    public static TrainsListReportFragment newInstance() {
        return new TrainsListReportFragment();
    }

    @Override
    protected void initUi(View view) {
        addReportButton = view.findViewById(R.id.addReportButton);
        cancelReportButton = view.findViewById(R.id.cancelReportButton);

        addReportButton.setOnClickListener(this);
        cancelReportButton.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_trains_list_report;
    }

    @Override
    int getTitleResId() {
        return  R.string.title_trains_list_report;
    }

    @Override
    public void onClick(View v) {

    }

}
