package com.example.harshit.chicagotransit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by harshit on 3/8/2015.
 */
public class BusStopListDataHandler extends DefaultHandler {

    private BusStopListAttributes busStopListAttributes;
    private ArrayList<BusStopListAttributes> stopList;
    String elementValue;
    boolean currentElement;

    public BusStopListDataHandler() {
        this.setBusStopListAttributes(new BusStopListAttributes());
        setStopList(new ArrayList<BusStopListAttributes>());
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = "";
        currentElement = true;
        if (localName.equals("route")) {
            String city = attributes.getValue("name");
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {

        currentElement = false;
        // if end of book element add to list
        if (element.equalsIgnoreCase("stpid")) {
            busStopListAttributes.setStopID(elementValue);
        } else if (element.equalsIgnoreCase("stpnm")) {
            busStopListAttributes.setStopName(elementValue);
        } else if (element.equalsIgnoreCase("lat")) {
            //busStopListAttributes.setLat(Double.parseDouble(elementValue));
        } else if (element.equalsIgnoreCase("lon")) {
            //busStopListAttributes.setLon(Double.parseDouble(elementValue));
            stopList.add(busStopListAttributes);
            busStopListAttributes = new BusStopListAttributes();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            elementValue = elementValue + new String(ch, start, length);
        }
    }


    public BusStopListAttributes getBusStopListAttributes() {
        return busStopListAttributes;
    }

    public void setBusStopListAttributes(BusStopListAttributes busStopListAttributes) {
        this.busStopListAttributes = busStopListAttributes;
    }

    public ArrayList<BusStopListAttributes> getStopList() {
        return stopList;
    }

    public void setStopList(ArrayList<BusStopListAttributes> stopList) {
        this.stopList = stopList;
    }
}
