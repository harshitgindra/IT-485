package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 2/21/2015.
 */
public class InServiceTrains extends MainActivity implements View.OnClickListener {


    static String apiLink = "http://lapi.transitchicago.com/api/1.0/ttpositions.aspx?key=60e684efe710400eafb7ed491201d370&rt=red";
    TextView laststop, routename, nextstop;
    EditText route;
    ListView lv;
    InServiceTrainsDataHandler calling;
    private ArrayList<InServiceTrainAttributes> g = new ArrayList<>();

    public InServiceTrains() {
        this.calling = new InServiceTrainsDataHandler();
    }

    private ArrayList<String> display = new ArrayList<String>();

    //TableLayout displayResults;
    Button getDetails;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inservicetrain);
        initilize();
        TableLayout table = (TableLayout) findViewById(R.id.displayResults);
        for (int i = 0; i < calling.getData().size(); i++) {
            TableRow row = new TableRow(this);
            String lStop = calling.getData().get(i).getLastStop();
            String nStop = calling.getData().get(i).getNextStop();
            TextView tvDebt = new TextView(this);
            tvDebt.setText("" + lStop);
            TextView tvFee = new TextView(this);
            tvFee.setText("" + nStop);
            row.addView(tvDebt);
            row.addView(tvFee);
            table.addView(row);
        }
    }

    private void initilize() {
        laststop = (TextView) findViewById(R.id.laststop);
        nextstop = (TextView) findViewById(R.id.nextstop);
        routename = (TextView) findViewById(R.id.routename);
        route = (EditText) findViewById(R.id.userroute);
        //lv = (ListView)findViewById(R.id.listView);
        //displayResults = (TableLayout)findViewById(R.id.displayResults);
        getDetails = (Button) findViewById(R.id.getroutedetails);
        getDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getroutedetails:
                GetRouteDetails getRouteDetails = new GetRouteDetails();
                getRouteDetails.execute();
                break;
        }

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
            //url.append(route.getText().toString());
            String finalurl = url.toString();
            try {
                URL website = new URL(finalurl);
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(calling);
                xmlReader.parse(new InputSource(website.openStream()));


                String[] lastStop = new String[calling.getData().size()];
                String[] nextStop = new String[calling.getData().size()];
                String[] time = new String[calling.getData().size()];
                String[] routenumber = new String[calling.getData().size()];
                String total = "";
                for (int i = 0; i < calling.getData().size(); i++) {
                    lastStop[i] = calling.getData().get(i).getLastStop();
                    nextStop[i] = calling.getData().get(i).getNextStop();
                    time[i] = calling.getData().get(i).getTime();
                    routenumber[i] = calling.getData().get(i).getRouteName();
                }
                String a  = calling.getData().get(0).toString();
                laststop.setText(a);

            } catch (Exception e) {
                laststop.setText("error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent jump = new Intent("com.example.harshit.chicagotransit.DISPLAYLIST");
            startActivity(jump);
        }
    }

}