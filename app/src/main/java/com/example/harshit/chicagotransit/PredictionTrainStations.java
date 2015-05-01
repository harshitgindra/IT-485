package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
public class PredictionTrainStations extends MainActivity {


    String runnumber = "";
    final static String apiLink = "http://lapi.transitchicago.com/api/1.0/ttfollow.aspx?key=60e684efe710400eafb7ed491201d370&runnumber=";
    PredictionTrainStationsDataHandler calling;
    ListView lvpredictiontrainStops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predictiontrainstops);
        Intent i = getIntent();
        runnumber = i.getStringExtra("runnumber");
        initialize();
        GetPredictions getPredictions = new GetPredictions();
        getPredictions.execute();
    }

    private void initialize() {
        lvpredictiontrainStops = (ListView) findViewById(R.id.lvpredictiontrainStops);
    }


    public PredictionTrainStations() {
        this.calling = new PredictionTrainStationsDataHandler();
    }

    class GetPredictions extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            StringBuilder url = new StringBuilder(apiLink);
            //adding the user input to the main link
            url.append(runnumber.toString());
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
            String[] stopid = new String[calling.getData().size()];
            String[] time = new String[calling.getData().size()];
            String[] finaDisplay = new String[calling.getData().size()];
            for (int i = 0; i < calling.getData().size(); i++) {
                stopName[i] = calling.getData().get(i).getStopName();
                stopid[i] = calling.getData().get(i).getStopid();
                time[i] = calling.getData().get(i).getArrivalTime().substring(9);
                finaDisplay[i] = stopName[i] + " -- " + time[i];
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(PredictionTrainStations.this, android.R.layout.simple_dropdown_item_1line, finaDisplay);
            lvpredictiontrainStops.setAdapter(adapter);

        }
    }

}
