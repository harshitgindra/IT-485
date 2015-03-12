package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by harshit on 3/3/2015.
 */
public class DisplayBusList extends MainActivity {

    String rootNo[];
    String rootName[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        rootNo= i.getStringExtra("rootNo").split("z");
        rootName= i.getStringExtra("rootName").split("1z");
        String finalDisplay[] = new String[rootNo.length];
        for(int j=0;j<rootNo.length;j++){
            finalDisplay[j] = rootNo[j] + " " + rootName[j];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayBusList.this, android.R.layout.simple_dropdown_item_1line, finalDisplay);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater disp = getMenuInflater();
        //disp.inflate(R.menu.busroute, menu);
        return true;
    }

  //  @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //super.onListItemClick(l, v, position, id);
        String rtSelected = rootNo[position];
        Intent i = new Intent("com.example.harshit.chicagotransit.GETROUTEDIRECTION");
        i.putExtra("rootNoSelected", rtSelected);
        startActivity(i);
    }


}
