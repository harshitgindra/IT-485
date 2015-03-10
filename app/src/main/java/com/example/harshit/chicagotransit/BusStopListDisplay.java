package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by harshit on 3/8/2015.
 */
public class BusStopListDisplay extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        String[] stopID = i.getStringArrayExtra("stopid");
        String[] stopName = i.getStringArrayExtra("stopname");
        String stopLists[] = new String[stopName.length];
        for (int j = 0; j < stopID.length; j++) {
            stopLists[j] = stopID[j] + " " + stopName[j];
        }
        setListAdapter(new ArrayAdapter<String>(BusStopListDisplay.this, android.R.layout.simple_expandable_list_item_1, stopLists));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater disp = getMenuInflater();
        disp.inflate(R.menu.busstoplists, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
