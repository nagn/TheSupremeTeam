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

    /**
     * Constructor for a popup Error message. Used universally across the app.
     * When supplied with a message and a view, a popup Error message is
     * supplied with the matching info in the correct position, which can be
     * dismissed by the button on the bottom of the dialog box.
     *
     * @param view The view of the Button calling the error.
     * @param errorMessage The message to display as the error message.
     */
    Error(View view, String errorMessage) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(view
                .getRootView().getContext());
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
