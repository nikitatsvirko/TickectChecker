package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;

import by.belzhd.android.tickectchecker.R;

public class TrainsListFragment extends AbstractFragment {

    public static TrainsListFragment newInstance() {
        return new TrainsListFragment();
    }

    @Override
    protected void initUi(View view) {

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
