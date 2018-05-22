package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;
import android.widget.Button;

import by.belzhd.android.tickectchecker.R;

public class DisembarkationAddFragment extends AbstractFragment implements View.OnClickListener {

    private Button addButton;
    private Button cancelButton;

    public static DisembarkationAddFragment newInstance() {
        return new DisembarkationAddFragment();
    }

    @Override
    protected void initUi(View view) {
        addButton = view.findViewById(R.id.addDisembButton);
        cancelButton = view.findViewById(R.id.cancelDisembButton);

        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addDisembButton:
                addPerson();
                break;
            case R.id.cancelDisembButton:
                cancelAction();
                break;
        }
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_diembarkation_add;
    }

    @Override
    int getTitleResId() {
        return R.string.title_disembarkation;
    }

    private void cancelAction() {
        getActivity().onBackPressed();
    }

    private void addPerson() {
        showToast("Пассажир добавлен");
        getActivity().onBackPressed();
    }
}
