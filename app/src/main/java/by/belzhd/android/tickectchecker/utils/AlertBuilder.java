package by.belzhd.android.tickectchecker.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import by.belzhd.android.tickectchecker.R;

public class AlertBuilder {

    public static void showAlert(Context context, String title, String message,
                                  DialogInterface.OnClickListener positiveListener,  DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setCancelable(true);

        builder.setPositiveButton(context.getResources().getString(R.string.yes), positiveListener);

        builder.setNegativeButton(context.getResources().getString(R.string.no), negativeListener);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
