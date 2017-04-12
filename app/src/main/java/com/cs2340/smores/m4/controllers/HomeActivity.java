package com.cs2340.smores.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.R;

/**
 * The class for the home page and standard activities of the app.
 * The layout and functions available to each User are displayed based on the type of User.
 */

public class HomeActivity extends AppCompatActivity {

    private TextView welcome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (Model.user.getType()) {
            case 0:
                setContentView(R.layout.home_activity_customer);
                break;
            case 1:
                setContentView(R.layout.home_activity_worker);
                break;
            case 2:
                setContentView(R.layout.home_activity_manager);
                break;
            default:
                setContentView(R.layout.home_activity_admin);
                break;
        }

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome, " + Model.user.getRealName() + "!");
    }

    @Override
    protected void onRestart() {
        welcome.setText("Welcome, " + Model.user.getRealName() + "!");
        super.onRestart();
    }

    /**
     * Caller for the Activity to make and then submit a Source Report.
     * If the User is banned, they are informed by way of an error dialog, otherwise
     * they are brought to the submission screen for Source Reports.
     * Callable by all User types.
     *
     * @param view The Button's view.
     */
    public void onMakeSourceReport(View view) {
        if (Model.user.isBanned()) {
            new Error(view, getString(R.string.banned));
        } else {
            startActivity(new Intent(view.getContext(), SourceReportActivity.class));
        }
    }

    /**
     * Caller for the Activity to make and then submit a Source Report.
     * If the User is banned, they are informed by way of an error dialog, otherwise
     * they are brought to the submission screen for Source Reports.
     * Callable by Users of type Worker and above.
     *
     * @param view The Button's view.
     */
    public void onMakePurityReport(View view) {
        if (Model.user.isBanned()) {
            new Error(view, getString(R.string.banned));
        } else {
            startActivity(new Intent(view.getContext(), PurityReportActivity.class));
        }
    }

    /**
     * Caller for the Activity to view water sources in the form of a map.
     * Callable by Users of all types.
     *
     * @param view The Button's view.
     */
    public void onViewAvailability(View view) {
        startActivity(new Intent(view.getContext(), MapsActivity.class));
    }

    /**
     * Caller for the Activity to view water purity over time in the form of a graph.
     * Callable by all Users of type Manager and above.
     *
     * @param view The Button's view.
     */
    public void historicalReports(View view) {
        startActivity(new Intent(view.getContext(), HistoryReportActivity.class));
    }

    /**
     * Caller for the Activity to view all Reports in the form of two lists.
     * Callable by Users of type Manager and above.
     *
     * @param view The Button's view.
     */
    public void onManageReports(View view) {
        startActivity(new Intent(view.getContext(), ManageReportsActivity.class));
    }

    /**
     * Caller for the Activity to view all Users in the form of a list.
     * Callable only by Admins.
     *
     * @param view The Button's view.
     */
    public void onManageUsers(View view) {
        startActivity(new Intent(view.getContext(), ManageUsersActivity.class));
    }

    /**
     * Caller for the Activity to view the security log in the form of a list.
     * Callable only by Admins.
     *
     * @param view The Button's view.
     */
    public void onViewLogs(View view) {
        startActivity(new Intent(view.getContext(), ViewLogActivity.class));
    }

    /**
     * Caller for the Activity to edit the current User's account.
     * Callable by Users of all types.
     *
     * @param view The Button's view.
     */
    public void onEditAccount(View view) {
        startActivity(new Intent(view.getContext(), EditAccountActivity.class));
    }

    @Override
    public void onBackPressed() {
        Model.setUser(null);
        super.onBackPressed();
    }

    /**
     * Returns the User to the welcome page of the app and logs them out.
     *
     * @param view Default view parameter for onClick custom method.
     */
    public void onLogoutPressed(View view) {
        Model.setUser(null);
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
