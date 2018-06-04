package com.craterstudio.juanfrancrater.ipewa.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.craterstudio.juanfrancrater.ipewa.R;


public class DialogUtils {

    public static final String MSG = "msg";
    public static final String TITLE = "title";

    public static Dialog showDeleteDialog (final Bundle b, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder
                .setMessage(b.getString(MSG))
                .setTitle(b.getString(TITLE))
                .setPositiveButton(R.string.btnOK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        return builder.create();
    }

    public static int DELETE = 0;
    public static int EDIT = 1;

    public static Dialog multipleOptionDialog(CharSequence[] options, final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pick a color");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == DELETE)
                {

                }else if (which == EDIT)
                {

                }
            }
        });

        return builder.create();
    }





    public static ProgressDialog makeProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDialog;
    }
}
