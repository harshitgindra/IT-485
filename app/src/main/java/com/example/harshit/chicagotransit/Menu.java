package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by harshit on 3/2/2015.
 */
public class Menu extends ListActivity {


    String menuitem[] = {"AllBusRoutes","Spinner", "ServiceTrains"};

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String classSelected = menuitem[position];
        try {
            Class classname = Class.forName("com.example.harshit.chicagotransit." + classSelected);
            Intent itemList = new Intent(Menu.this, classname);
            startActivity(itemList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_expandable_list_item_1, menuitem));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater disp = getMenuInflater();
        disp.inflate(R.menu.trainlist, menu);
        return true;

    }
}
