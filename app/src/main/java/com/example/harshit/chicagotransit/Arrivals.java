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
 * Created by harshit on 3/2/2015.
 */
public class Arrivals extends MainActivity implements View.OnClickListener {


    static String apiLink = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?key=60e684efe710400eafb7ed491201d370&max=10&mapid=41400";
    ArrivalsDataHandler arrival;

    public Arrivals() {
        arrival = new ArrivalsDataHandler();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inservicetrain);
    }

    @Override
    public void onClick(View v) {

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
                xmlReader.setContentHandler(arrival);
                xmlReader.parse(new InputSource(website.openStream()));

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

}
