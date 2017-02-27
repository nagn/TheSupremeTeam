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
import com.cs2340.smores.m4.model.User;

/**
 * Custom ListAdapter for giving details and manipulation options for each User.
 */

class UserDetailAdapter extends BaseAdapter implements ListAdapter {

    private Context context;

    /**
     * Standard constructor for the custom User detail Adapter. Uses most of the
     * features of the existing ListAdapter. Deals with Model's users ArrayList.
     *
     * @param context The context of the list view.
     */
    UserDetailAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Model.numberOfUsers();
    }

    @Override
    public Object getItem(int position) {
        return Model.users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.user_detail_list_item, null);
        }

        final User user = Model.users.get(position);

        final TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText("User: " + user.getUsername() + ", " + user.getTypeName()
                + " - " + ((user.isBanned()) ? "Is Banned, " : "Isn\'t Banned, ")
                + ((user.isLocked()) ? "Is Locked" : "Isn\'t Locked"));

        Button deleteButton = (Button) view.findViewById(R.id.onDelete);
        Button unblockButton = (Button) view.findViewById(R.id.onUnblock);
        Button banButton = (Button) view.findViewById(R.id.onBan);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Model.removeUser(user, Model.user);
                notifyDataSetChanged();
            }
        });

        unblockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.unblock(Model.user);
                listItemText.setText("User: " + user.getUsername() + ", " + user.getTypeName()
                        + " - " + ((user.isBanned()) ? "Is Banned, " : "Isn\'t Banned, ")
                        + ((user.isLocked()) ? "Is Locked" : "Isn\'t Locked"));
                notifyDataSetChanged();
            }
        });

        banButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                user.ban(Model.user);
                listItemText.setText("User: " + user.getUsername() + ", " + user.getTypeName()
                        + " - " + ((user.isBanned()) ? "Is Banned, " : "Isn\'t Banned, ")
                        + ((user.isLocked()) ? "Is Locked" : "Isn\'t Locked"));
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
