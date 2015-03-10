package com.example.harshit.chicagotransit;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;

/**
 * Created by harshit on 2/23/2015.
 */
public class DisplayList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String laststop[] = i.getStringArrayExtra("laststop");
        String nextstop[] = i.getStringArrayExtra("nextstop");
        String time[] = i.getStringArrayExtra("time");
        String routenumber[] = i.getStringArrayExtra("routenumber");
        String finalDisplay[] = new String[laststop.length];
        for (int j = 0; j < laststop.length; j++) {
            finalDisplay[j] = laststop[j] + " " + nextstop[j] + " " + time[j];
        }


        setListAdapter(new ArrayAdapter<String>(DisplayList.this, android.R.layout.simple_expandable_list_item_1, finalDisplay));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater disp = getMenuInflater();
        disp.inflate(R.menu.trainlist, menu);
        return true;
    }


}
