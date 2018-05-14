package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;

import by.belzhd.android.tickectchecker.R;

public class DisembarkationFragment extends AbstractFragment {

    public static DisembarkationFragment newInstance() {
        return new DisembarkationFragment();
    }

    @Override
    protected void initUi(View view) {

    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_disembarkation;
    }

    @Override
    int getTitleResId() {
        return R.string.title_disembarkation;
    }
}
