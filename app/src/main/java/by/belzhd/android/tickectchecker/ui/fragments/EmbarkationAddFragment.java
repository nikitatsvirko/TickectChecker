package by.belzhd.android.tickectchecker.ui.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import by.belzhd.android.tickectchecker.R;
import by.belzhd.android.tickectchecker.db.entities.general.Passengers;

public class EmbarkationAddFragment extends AbstractFragment implements View.OnClickListener {

    private Button addEmbButton;
    private Button cancelEmbButton;
    private EditText carriageEditText;
    private EditText placeEditText;
    private EditText secondNameEditText;
    private EditText firstNameEditText;
    private EditText stationToEditText;

    public static EmbarkationAddFragment newInstance() {
        return new EmbarkationAddFragment();
    }

    @Override
    protected void initUi(View view) {
        addEmbButton = view.findViewById(R.id.addEmbButton);
        cancelEmbButton = view.findViewById(R.id.cancelEmbButton);
        carriageEditText = view.findViewById(R.id.carriageEditText);
        placeEditText = view.findViewById(R.id.placeEditText);
        secondNameEditText = view.findViewById(R.id.secondNameEditText);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        stationToEditText = view.findViewById(R.id.stationToEditText);

        addEmbButton.setOnClickListener(this);
        cancelEmbButton.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_embarkation_add;
    }

    @Override
    int getTitleResId() {
        return R.string.title_embarkation;
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
        String surname = secondNameEditText.getText().toString();
        String initials = firstNameEditText.getText().toString();
        int tickNumb = Integer.parseInt(carriageEditText.getText().toString() + placeEditText.getText().toString());
        Passengers passenger = new Passengers(surname, initials, tickNumb);
        showToast("Пассажир добавлен");
        getActivity().onBackPressed();
    }
}
