package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;
import android.widget.Button;

import by.belzhd.android.tickectchecker.R;

public class EmbarkationAddFragment extends AbstractFragment implements View.OnClickListener {

    private Button addEmbButton;
    private Button cancelEmbButton;

    public static EmbarkationAddFragment newInstance() {
        return new EmbarkationAddFragment();
    }

    @Override
    protected void initUi(View view) {
        addEmbButton = view.findViewById(R.id.addEmbButton);
        cancelEmbButton = view.findViewById(R.id.cancelEmbButton);

        addEmbButton.setOnClickListener(this);
        cancelEmbButton.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_embarkation_add;
    }

    @Override
    int getTitleResId() {
        return  R.string.title_embarkation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addEmbButton:
                addPerson();
                break;
            case R.id.cancelEmbButton:
                cancelAction();
                break;
        }
    }

    private void cancelAction() {
        getActivity().onBackPressed();
    }

    private void addPerson() {
        showToast("Пассажир добавлен");
        getActivity().onBackPressed();
    }
}
