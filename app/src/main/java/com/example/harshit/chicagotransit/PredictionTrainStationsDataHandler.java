package com.example.harshit.chicagotransit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshit on 3/26/2015.
 */
public class PredictionTrainStationsDataHandler extends DefaultHandler {
    private PredictionTrainStationAttributes attribute;
    String elementValue;
    Boolean currentElement = false;
    private ArrayList<PredictionTrainStationAttributes> data;

    public PredictionTrainStationsDataHandler() {
        this.data = new ArrayList<>();
        this.attribute = new PredictionTrainStationAttributes();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = "";
        currentElement = true;
        if (localName.equals("route")) {
            String city = attributes.getValue("name");
            //getAttribute().setLastStop(city);
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {

        currentElement = false;
        // if end of book element add to list
        if (element.equalsIgnoreCase("stpId")) {
            getAttribute().setStopid(elementValue);
        } else if (element.equalsIgnoreCase("staNm")) {
            getAttribute().setStopName(elementValue);
        } else if (element.equalsIgnoreCase("arrT")) {
            getAttribute().setArrivalTime(elementValue);
        } else if (element.equalsIgnoreCase("isFlt")) {
            data.add(attribute);
            attribute = new PredictionTrainStationAttributes();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            elementValue = elementValue + new String(ch, start, length);
        }
    }

    public PredictionTrainStationAttributes getAttribute() {
        return attribute;
    }

    public void setAttribute(PredictionTrainStationAttributes attribute) {
        this.attribute = attribute;
    }

    public ArrayList<PredictionTrainStationAttributes> getData() {
        return data;
    }

    public void setData(ArrayList<PredictionTrainStationAttributes> data) {
        this.data = data;
    }
}
