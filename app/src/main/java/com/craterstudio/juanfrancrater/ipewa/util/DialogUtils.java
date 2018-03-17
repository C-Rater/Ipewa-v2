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


    public static ProgressDialog makeProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return progressDialog;
    }
}
