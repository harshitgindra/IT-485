package com.example.harshit.chicagotransit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshit on 2/21/2015.
 */
public class InServiceTrainsDataHandler extends DefaultHandler {
    private InServiceTrainAttributes attribute;
    String elementValue;
    Boolean currentElement = false;
    private List<InServiceTrainAttributes> data;

    public InServiceTrainsDataHandler() {
        this.data = new ArrayList<>();
        this.attribute = new InServiceTrainAttributes();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        elementValue = "";
        currentElement = true;
        if (localName.equals("route")) {
            String city = attributes.getValue("name");
            getAttribute().setLastStop(city);
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {

        currentElement = false;
        // if end of book element add to list
        if (element.equalsIgnoreCase("rn")) {
            getAttribute().setRouteName(elementValue);
        } else if (element.equalsIgnoreCase("destNm")) {
            getAttribute().setLastStop(elementValue);
        } else if (element.equalsIgnoreCase("nextStaNm")) {
            getAttribute().setNextStop(elementValue);
        } else if (element.equalsIgnoreCase("arrT")) {
            attribute.setTime(elementValue);
        } else if (element.equalsIgnoreCase("heading")) {
            data.add(getAttribute());
            attribute = new InServiceTrainAttributes();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            elementValue = elementValue + new String(ch, start, length);
        }
    }

    public List<InServiceTrainAttributes> getData() {
        return data;
    }

    public void setData(ArrayList<InServiceTrainAttributes> data) {
        this.data = data;
    }

    public InServiceTrainAttributes getAttribute() {
        return attribute;
    }

    public void setAttribute(InServiceTrainAttributes attribute) {
        this.attribute = attribute;
    }
}