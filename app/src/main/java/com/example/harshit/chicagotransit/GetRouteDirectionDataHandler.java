package com.example.harshit.chicagotransit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshit on 3/4/2015.
 */
public class GetRouteDirectionDataHandler extends DefaultHandler {


    private GetRouteDirectionAttributes attribute;
    String elementValue;
    Boolean currentElement = false;
    private List<GetRouteDirectionAttributes> data;
    int counter=0;

    public GetRouteDirectionDataHandler() {
        this.data = new ArrayList<>();
        this.attribute = new GetRouteDirectionAttributes();
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

        if ((element.equalsIgnoreCase("dir"))&&(counter==0)) {
            getAttribute().setDir1(elementValue);
            counter++;
        }
        else{
            getAttribute().setDir2(elementValue);
            counter=0;
            data.add(getAttribute());
            //attribute=new GetRouteDirectionAttributes();
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement) {
            elementValue = elementValue + new String(ch, start, length);
        }
    }

    public List<GetRouteDirectionAttributes> getData() {
        return data;
    }

    public void setData(ArrayList<GetRouteDirectionAttributes> data) {
        this.data = data;
    }

    public GetRouteDirectionAttributes getAttribute() {
        return attribute;
    }

    public void setAttribute(GetRouteDirectionAttributes attribute) {
        this.attribute = attribute;
    }
}

