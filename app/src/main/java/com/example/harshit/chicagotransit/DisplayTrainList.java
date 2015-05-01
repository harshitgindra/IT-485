package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by harshit on 3/12/2015.
 */
public class DisplayTrainList extends MainActivity {

    ListView trainsonselectedroute;
    String stopid[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disptrainlistonroute);
        initialize();
        Intent i = getIntent();
        String[] laststop = i.getStringArrayExtra("laststop");
        String[] nextstop = i.getStringArrayExtra("nextstop");
        String[] time = i.getStringArrayExtra("time");
        String[] routenumber = i.getStringArrayExtra("routenumber");
        stopid = i.getStringArrayExtra("stopid");
        String[] finalDisplay = new String[laststop.length];
        for (int j = 0; j < laststop.length; j++) {
            finalDisplay[j] = nextstop[j] + "(" + laststop[j] + ")";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayTrainList.this, android.R.layout.simple_dropdown_item_1line, finalDisplay);
        trainsonselectedroute.setAdapter(adapter);
    }

    private void initialize() {
        trainsonselectedroute = (ListView) findViewById(R.id.trainsonselectedroute);
        trainsonselectedroute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stop = stopid[position];
                Intent i = new Intent("com.example.harshit.chicagotransit.TRAINARRIVALSATSTOP");
                i.putExtra("stopid", stop);
                startActivity(i);
            }
        });
    }
}
