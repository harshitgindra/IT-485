package com.example.harshit.chicagotransit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshit on 3/2/2015.
 */
public class AllBusRouteDataHandler extends DefaultHandler {

    private AllBusRouteDataAttributes attribute;
    String elementValue;
    Boolean currentElement = false;
    private List<AllBusRouteDataAttributes> data;

    public AllBusRouteDataHandler() {
        this.data = new ArrayList<>();
        this.attribute = new AllBusRouteDataAttributes();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = "";
        currentElement = true;

    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {

        currentElement = false;
        // if end of book element add to list
        if (element.equalsIgnoreCase("rt")) {
            getAttribute().setRootNo(elementValue);
        } else if (element.equalsIgnoreCase("rtnm")) {
            getAttribute().setRootName(elementValue);
        } else if (element.equalsIgnoreCase("rtclr")) {
            getAttribute().setRootClr(elementValue);
            data.add(getAttribute());
            attribute = new AllBusRouteDataAttributes();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            elementValue = elementValue + new String(ch, start, length);
        }
    }

    public List<AllBusRouteDataAttributes> getData() {
        return data;
    }

    public void setData(ArrayList<AllBusRouteDataAttributes> data) {
        this.data = data;
    }

    public AllBusRouteDataAttributes getAttribute() {
        return attribute;
    }

    public void setAttribute(AllBusRouteDataAttributes attribute) {
        this.attribute = attribute;
    }
}
