package com.cs2340.smores.m4.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.PurityReport;
import com.cs2340.smores.m4.model.Report;
import com.cs2340.smores.m4.model.SourceReport;
import com.cs2340.smores.m4.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom ListAdapter for giving details and deletion options for each Purity Report.
 */

class PurityReportDetailAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    private List data;

    /**
     * Standard constructor for the custom Purity Report detail Adapter. Uses most of the
     * features of the existing ListAdapter.
     *
     * @param context The context of the list view.
     * @param data The list of Purity Reports to use in the app.
     */
    PurityReportDetailAdapter(Context context, List data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((PurityReport) data.get(position)).getReportNumber();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.report_detail_list_item, null);
        }

        final PurityReport report = (PurityReport) getItem(position);
        final TextView reportInfoText = (TextView) view.findViewById(R.id.reportInfoText);
        final TextView reportDetailText = (TextView) view.findViewById(R.id.reportDetailText);
        reportDetailText.setText("Virus PPM: " + report.getVirusPPM()
                + ", Contaminant PPM: " + report.getContaminantPPM()
                + ", Condition: " + report.getCondition());
        reportInfoText.setText("At " + report.getTimeCreated() +  ", Purity Report left by "
                + report.getName() + " in " + report.getLocation());

        Button deleteButton = (Button) view.findViewById(R.id.onDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.removePurityReport(report);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
