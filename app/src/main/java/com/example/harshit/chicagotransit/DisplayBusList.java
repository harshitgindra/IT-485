package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

/**
 * Created by harshit on 3/3/2015.
 */
public class DisplayBusList extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String rootNo[] = i.getStringExtra("rootNo").split("z");
        String rootName[] = i.getStringExtra("rootName").split("1z");
        String finalDisplay[] = new String[rootNo.length];
        for(int j=0;j<rootNo.length;j++){
            finalDisplay[j] = rootNo[j] + " " + rootName[j];
        }
        setListAdapter(new ArrayAdapter<String>(DisplayBusList.this, android.R.layout.simple_expandable_list_item_1, finalDisplay));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater disp = getMenuInflater();
        disp.inflate(R.menu.busroute, menu);
        return true;
    }
}
