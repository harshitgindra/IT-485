package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.Arrays;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 3/2/2015.
 */
public class AllBusRoutes extends MainActivity {

    private static final String routeLink = "http://www.ctabustracker.com/bustime/api/v1/getroutes?key=RQkSsAPXCVt58mtjrqxAXpGXv";
    AllBusRouteDataHandler busHandler;
    Button tv1;
    ListView busroutelv;
    AutoCompleteTextView acbusroutes;
    String finalArray[];

    public AllBusRoutes() {
        busHandler = new AllBusRouteDataHandler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completebusroutes);
        initialize();
        FetchBusRoutes fetchBusRoutes = new FetchBusRoutes();
        fetchBusRoutes.execute();
    }

    private void initialize() {
        busroutelv = (ListView) findViewById(R.id.busroutelistview);
        acbusroutes = (AutoCompleteTextView) findViewById(R.id.acbusroutes);
    }

    public void GetBusDirection(View view) {
        String msg =acbusroutes.getText().toString();
        // Toast.makeText(AllBusRoutes.this, msg, Toast.LENGTH_LONG).show();
        int pos = Arrays.asList(finalArray).indexOf(msg);
        Intent i = new Intent("com.example.harshit.chicagotransit.GETROUTEDIRECTION");
        i.putExtra("rootNoSelected", busHandler.getData().get(pos).getRootNo());
        startActivity(i);
    }


    class FetchBusRoutes extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            StringBuilder url = new StringBuilder(routeLink);

            String finalurl = url.toString();
            try {
                URL website = new URL(finalurl);
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(busHandler);
                xmlReader.parse(new InputSource(website.openStream()));
                String a = busHandler.getData().get(0).getRootClr();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            finalArray = new String[busHandler.getData().size()];
            for (int i = 0; i < busHandler.getData().size(); i++){
                finalArray[i] = busHandler.getData().get(i).getRootName();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AllBusRoutes.this, android.R.layout.simple_dropdown_item_1line, finalArray );
            acbusroutes.setThreshold(1);
            acbusroutes.setAdapter(adapter);
        }
    }
}
