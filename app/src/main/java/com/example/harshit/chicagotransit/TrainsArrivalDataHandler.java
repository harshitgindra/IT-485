package com.example.harshit.chicagotransit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by harshit on 3/26/2015.
 */
public class TrainsArrivalDataHandler extends DefaultHandler {
    private TrainsArrivalAttributes attribute;
    String elementValue;
    Boolean currentElement = false;
    private ArrayList<TrainsArrivalAttributes> data;

    public TrainsArrivalDataHandler() {
        this.attribute = new TrainsArrivalAttributes();
        this.data = new ArrayList<>();
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
        if (element.equalsIgnoreCase("staNm")) {
            getAttribute().setStopName(elementValue);
        } else if (element.equalsIgnoreCase("rn")) {
            getAttribute().setRunnumber(elementValue);
        } else if (element.equalsIgnoreCase("prdt")) {
            getAttribute().setPredictionTime(elementValue);
        } else if (element.equalsIgnoreCase("destSt")) {
            getAttribute().setDestinationSt(elementValue);
        } else if (element.equalsIgnoreCase("destNm")) {
            getAttribute().setDestStnName(elementValue);
        } else if (element.equalsIgnoreCase("heading")) {
            data.add(attribute);
            attribute = new TrainsArrivalAttributes();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            elementValue = elementValue + new String(ch, start, length);
        }
    }

    public TrainsArrivalAttributes getAttribute() {
        return attribute;
    }

    public void setAttribute(TrainsArrivalAttributes attribute) {
        this.attribute = attribute;
    }

    public ArrayList<TrainsArrivalAttributes> getData() {
        return data;
    }

    public void setData(ArrayList<TrainsArrivalAttributes> data) {
        this.data = data;
    }
}
