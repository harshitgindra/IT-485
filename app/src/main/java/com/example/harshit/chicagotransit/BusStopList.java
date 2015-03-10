package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 3/8/2015.
 */
public class BusStopList extends MainActivity {

    String root = "";
    String direction = "";
    private static final String apiLink = "http://www.ctabustracker.com/bustime/api/v1/getstops?key=RQkSsAPXCVt58mtjrqxAXpGXv&";
    BusStopListDataHandler busStopListDataHandler;

    public BusStopList() {
        busStopListDataHandler = new BusStopListDataHandler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        root = i.getStringExtra("rootSelected");
        direction = i.getStringExtra("direction");
        getStops fetch = new getStops();
        fetch.execute();

    }

    class getStops extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            String tempUrl = apiLink + "rt=" + root + "&dir=" + direction;
            StringBuilder url = new StringBuilder(tempUrl);
            String finalurl = url.toString();
            try {
                URL website = new URL(finalurl);
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(busStopListDataHandler);
                xmlReader.parse(new InputSource(website.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            String[] stopID = new String[busStopListDataHandler.getStopList().size()];
            String[] stopName = new String[busStopListDataHandler.getStopList().size()];
            for (int i = 0; i < busStopListDataHandler.getStopList().size(); i++) {
                stopID[i] = busStopListDataHandler.getStopList().get(i).getStopID();
                stopName[i] = busStopListDataHandler.getStopList().get(i).getStopName();
            }
            Intent i = new Intent("com.example.harshit.chicagotransit.BUSSTOPLISTDISPLAY");
            i.putExtra("stopid", stopID);
            i.putExtra("stopname", stopName);
            //i.putExtra("root", rootSelected );
            startActivity(i);
        }
    }
}
