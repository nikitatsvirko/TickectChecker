package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;

import by.belzhd.android.tickectchecker.R;

public class EmbarkationAddFragment extends AbstractFragment {

    public static EmbarkationAddFragment newInstance() {
        return new EmbarkationAddFragment();
    }

    @Override
    protected void initUi(View view) {

    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_embarkation_add;
    }

    @Override
    int getTitleResId() {
        return  R.string.title_embarkation;
    }
}
