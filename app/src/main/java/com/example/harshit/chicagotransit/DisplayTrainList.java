package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by harshit on 3/12/2015.
 */
public class DisplayTrainList extends MainActivity {

    ListView trainsonselectedroute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disptrainlistonroute);
        initialize();
        Intent i = getIntent();
        String[]laststop = i.getStringArrayExtra("laststop");
        String[]nextstop =  i.getStringArrayExtra("nextstop");
        String[]time=i.getStringArrayExtra("time");
        String[]routenumber=i.getStringArrayExtra("routenumber");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayTrainList.this, android.R.layout.simple_dropdown_item_1line, laststop);
        trainsonselectedroute.setAdapter(adapter);
    }

    private void initialize() {
        trainsonselectedroute = (ListView)findViewById(R.id.trainsonselectedroute);
    }
}
