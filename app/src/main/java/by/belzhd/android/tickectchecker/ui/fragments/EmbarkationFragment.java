package by.belzhd.android.tickectchecker.ui.fragments;

import by.belzhd.android.tickectchecker.R;

public class EmbarkationFragment extends AbstractFragment {

    public static EmbarkationFragment newInstance() {
        return new EmbarkationFragment();
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_embarkation;
    }

    @Override
    int getTitleResId() {
        return R.string.title_embarkation;
    }
}
