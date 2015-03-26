package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 3/2/2015.
 */
public class ServiceTrains extends MainActivity {



    static String apiLink = "http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=60e684efe710400eafb7ed491201d370&rt=";
    final static String[] routesOnPage = {"Red Line", "Blue Line", "Brown Line", "Green Line", "Orange Line", "Purple Line", "Pink Line", "Yellow Line"};
    final static String [] routeCode = {"Red", "Blue", "Brn", "G", "Org", "P", "Pink", "Y"};
    String routeSelected="";
    ListView servicetrainlv;
    InServiceTrainsDataHandler calling;
    private ArrayList<String> display = new ArrayList<String>();
    Button getDetails;

    public ServiceTrains() {
        this.calling = new InServiceTrainsDataHandler();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inservicetrain);
        initilize();
        populateListView();
    }

    private void populateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ServiceTrains.this, android.R.layout.simple_dropdown_item_1line, routesOnPage);
        servicetrainlv.setAdapter(adapter);
    }

    private void initilize() {

        servicetrainlv = (ListView)findViewById(R.id.servicetrainslv);
        servicetrainlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                routeSelected = routeCode[position];
                GetRouteDetails getRouteDetails = new GetRouteDetails();
                getRouteDetails.execute();
            }
        });
    }

    public ArrayList<String> getDisplay() {
        return display;
    }

    public void setDisplay(ArrayList<String> display) {
        this.display = display;
    }

    class GetRouteDetails extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            StringBuilder url = new StringBuilder(apiLink);
            //adding the user input to the main link
            url.append(routeSelected.toString());
            String finalurl = url.toString();
            try {
                URL website = new URL(finalurl);
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(calling);
                xmlReader.parse(new InputSource(website.openStream()));
            } catch (Exception e) {
               e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String[] lastStop = new String[calling.getData().size()];
            String[] nextStop = new String[calling.getData().size()];
            String[] time = new String[calling.getData().size()];
            String[] routenumber = new String[calling.getData().size()];
            String[] stopid = new String[calling.getData().size()];
            String total = "";
            for (int i = 0; i < calling.getData().size(); i++) {
                lastStop[i] = calling.getData().get(i).getLastStop();
                nextStop[i] = calling.getData().get(i).getNextStop();
                String t = calling.getData().get(i).getTime().substring(9);
                time[i] = t;
                routenumber[i] = calling.getData().get(i).getRouteName();
                stopid[i] = calling.getData().get(i).getStpid();
            }
            Intent i = new Intent("com.example.harshit.chicagotransit.DISPLAYTRAINLIST");
            i.putExtra("laststop", lastStop);
            i.putExtra("nextstop", nextStop);
            i.putExtra("time", time);
            i.putExtra("routenumber", routenumber);
            i.putExtra("stopid", stopid);
            startActivity(i);

        }
    }

}
