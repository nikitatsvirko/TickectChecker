package by.belzhd.android.tickectchecker.ui.fragments;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import by.belzhd.android.tickectchecker.R;

public class EmbarkationFragment extends AbstractFragment implements View.OnClickListener {

    private RelativeLayout container;
    private AutoCompleteTextView stationAutoCompleteText;
    private Button qrButton;
    private Button scanButton;
    private Button startEmbButton;
    private LinearLayout finishButtonsContainer;
    private Button showListButton;
    private Button finishEmbButton;

    public static EmbarkationFragment newInstance() {
        return new EmbarkationFragment();
    }

    @Override
    protected void initUi(View view) {
        container = view.findViewById(R.id.container);
        finishButtonsContainer = view.findViewById(R.id.finishEmbButtonsContainer);
        stationAutoCompleteText = view.findViewById(R.id.stationAutoComplete);
        qrButton = view.findViewById(R.id.qrButton);
        scanButton = view.findViewById(R.id.scanButton);
        startEmbButton = view.findViewById(R.id.startEmbButton);
        showListButton = view.findViewById(R.id.listButton);
        finishEmbButton = view.findViewById(R.id.finishButton);

        startEmbButton.setOnClickListener(this);
        finishEmbButton.setOnClickListener(this);
    }

    @Override
    int getLayoutId() {
        return R.layout.fragment_embarkation;
    }

    @Override
    int getTitleResId() {
        return R.string.title_embarkation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startEmbButton:
                onStartClicked();
                break;
            case R.id.finishButton:
                showAlert();
                break;
        }
    }

    private void onFinishClicked() {
        TransitionManager.beginDelayedTransition(container);
        startEmbButton.setVisibility(View.VISIBLE);
        finishButtonsContainer.setVisibility(View.GONE);
        stationAutoCompleteText.setEnabled(true);
    }

    private void onStartClicked() {
        TransitionManager.beginDelayedTransition(container);
        startEmbButton.setVisibility(View.GONE);
        finishButtonsContainer.setVisibility(View.VISIBLE);
        stationAutoCompleteText.setEnabled(false);
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        builder.setTitle(getActivity().getResources().getString(R.string.finish_emb_text));
        builder.setMessage(getActivity().getResources().getString(R.string.finish_emb_message));

        builder.setCancelable(true);

        builder.setPositiveButton(getActivity().getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onFinishClicked();
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton(getActivity().getResources().getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
