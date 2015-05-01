package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by harshit on 3/8/2015.
 */
public class GetRouteDirectionDisplay extends ListActivity {


    String[] directionList = new String [2];
    String rootSelected = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        directionList[0]= i.getStringExtra("dir1");
        directionList[1]=i.getStringExtra("dir2");
        rootSelected = i.getStringExtra("root");
        setListAdapter(new ArrayAdapter<String>(GetRouteDirectionDisplay.this, android.R.layout.simple_expandable_list_item_1, directionList));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater disp = getMenuInflater();
        disp.inflate(R.menu.busdirection, menu);
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
       Intent i = new Intent("com.example.harshit.chicagotransit.BUSSTOPLIST");
        i.putExtra("rootSelected", rootSelected);
        i.putExtra("direction", directionList[position]);
        startActivity(i);
    }

}
