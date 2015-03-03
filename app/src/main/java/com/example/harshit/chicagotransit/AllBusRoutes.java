package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 3/2/2015.
 */
public class AllBusRoutes extends MainActivity {

    private static final String routeLink = "http://www.ctabustracker.com/bustime/api/v1/getroutes?key=RQkSsAPXCVt58mtjrqxAXpGXv";
    AllBusRouteDataHandler busHandler;
    Button tv1;

    public AllBusRoutes() {
        busHandler = new AllBusRouteDataHandler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FetchBusRoutes fetchBusRoutes = new FetchBusRoutes();
        fetchBusRoutes.execute();

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
            String a = "";
            String b = "";
            for (int i = 0; i < busHandler.getData().size(); i++) {

                a += busHandler.getData().get(i).getRootNo() + "z";
                b += busHandler.getData().get(i).getRootName() + "1z";
            }
            Intent i = new Intent("com.example.harshit.chicagotransit.DISPLAYBUSLIST");
            i.putExtra("rootNo", a);
            i.putExtra("rootName", b);

            startActivity(i);

        }
    }
}
