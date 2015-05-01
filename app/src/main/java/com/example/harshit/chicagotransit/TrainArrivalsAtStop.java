package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 3/26/2015.
 */
public class TrainArrivalsAtStop extends MainActivity {
    //Redirection from DisplayTrainList on selecting a train
    String stopid = "";
    private final String apiLink = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=60e684efe710400eafb7ed491201d370&stpid=";
    TrainsArrivalDataHandler calling;
    ListView lvtrainsstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainsstopatstop);
        Intent i = getIntent();
        stopid = i.getStringExtra("stopid");
        initialize();
        GetTrainsSchedule getRouteDetails = new GetTrainsSchedule();
        getRouteDetails.execute();
    }

    public TrainArrivalsAtStop() {
        this.calling = new TrainsArrivalDataHandler();
    }


    private void initialize() {
        lvtrainsstop = (ListView) findViewById(R.id.lvtrainsstop);
    }

    class GetTrainsSchedule extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            StringBuilder url = new StringBuilder(apiLink);
            //adding the user input to the main link
            url.append(stopid.toString());
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
            String[] stopName = new String[calling.getData().size()];
            final String[] runnumber = new String[calling.getData().size()];
            String[] predictionTime = new String[calling.getData().size()];
            String[] destinationSt = new String[calling.getData().size()];
            String[] destStnName = new String[calling.getData().size()];
            String total = "";
            for (int i = 0; i < calling.getData().size(); i++) {
                stopName[i] = calling.getData().get(i).getStopName();
                runnumber[i] = calling.getData().get(i).getRunnumber();
                predictionTime[i] = calling.getData().get(i).getPredictionTime().substring(9);
                destinationSt[i] = calling.getData().get(i).getDestinationSt();
                destStnName[i] = calling.getData().get(i).getDestStnName();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TrainArrivalsAtStop.this, android.R.layout.simple_dropdown_item_1line, runnumber);
            lvtrainsstop.setAdapter(adapter);

            lvtrainsstop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String rn = runnumber[position];
                    Intent i = new Intent("com.example.harshit.chicagotransit.PREDICTIONTRAINSTATIONS");
                    i.putExtra("runnumber", rn);
                    startActivity(i);
                }
            });

        }
    }
}
