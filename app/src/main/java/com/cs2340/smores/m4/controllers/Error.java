package com.cs2340.smores.m4.controllers;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs2340.smores.m4.R;

/**
 * The popup window activity for all errors that occur in the app.
 */

class Error {

    Error(View view, String errorMessage) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(view.getContext());
        adBuilder.setView(R.layout.popup);
        final AlertDialog alertDialog = adBuilder.create();
        alertDialog.show();

        TextView txtView = (TextView) alertDialog.findViewById(R.id.txtView);
        if (txtView != null) {
            txtView.setText(errorMessage);
        }

        Button btn = (Button) alertDialog.findViewById(R.id.close_popup);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        }
    }
}
