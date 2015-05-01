package com.example.harshit.chicagotransit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by harshit on 3/4/2015.
 */
public class GetRouteDirection extends MainActivity {

    private final String directionLink = "http://www.ctabustracker.com/bustime/api/v1/getdirections?key=RQkSsAPXCVt58mtjrqxAXpGXv&rt=";
    String rootSelected = "";
    private GetRouteDirectionDataHandler direction;
    ListView busroutedirection;

    public GetRouteDirection() {
        direction = new GetRouteDirectionDataHandler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busroutedirection);
        initialize();
        Intent i = getIntent();
        rootSelected = i.getStringExtra("rootNoSelected");
        FetchBusDirection fetchBusDirection = new FetchBusDirection();
        fetchBusDirection.execute();

    }

    private void initialize() {
        busroutedirection = (ListView) findViewById(R.id.busroutedirectionlv);
        busroutedirection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] finalDisplay = {direction.getAttribute().dir1, direction.getAttribute().dir2};
                String msg = finalDisplay[position];
                //Toast.makeText(GetRouteDirection.this, msg, Toast.LENGTH_LONG).show();
                Intent i = new Intent("com.example.harshit.chicagotransit.BUSSTOPLIST");
                i.putExtra("rootSelected", rootSelected);
                i.putExtra("direction", finalDisplay[position]);
                startActivity(i);
            }
        });
    }

    class FetchBusDirection extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String routeLink = directionLink + rootSelected;
            StringBuilder url = new StringBuilder(routeLink);
            String finalurl = url.toString();
            try {
                URL website = new URL(finalurl);
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser saxParser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(direction);
                xmlReader.parse(new InputSource(website.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String[] finalDisplay = {direction.getAttribute().dir1, direction.getAttribute().dir2};

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(GetRouteDirection.this, android.R.layout.simple_dropdown_item_1line, finalDisplay);
            busroutedirection.setAdapter(adapter);
//            Intent i = new Intent("com.example.harshit.chicagotransit.GETROUTEDIRECTIONDISPLAY");
//            i.putExtra("dir1", direction.getAttribute().dir1);
//            i.putExtra("dir2", direction.getAttribute().dir2);
//            i.putExtra("root", rootSelected );
//            startActivity(i);

        }
    }
}
