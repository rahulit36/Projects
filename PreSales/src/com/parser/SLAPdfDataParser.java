/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class SLAPdfDataParser extends DefaultHandler{

    private Map<String, String> slaPdfDataMap = new HashMap<String, String>();

    /**
     * @return the slaPdfDataMap
     */
    public Map<String, String> getSlaPdfDataMap() {
        return slaPdfDataMap;
    }

    public void startElement(String uri, String localName, String tagName, Attributes atts) {

        if (tagName.equals("txn")) {

            slaPdfDataMap.put("companyAddrs", atts.getValue("companyAddres"));
            slaPdfDataMap.put("contactPerson", atts.getValue("contactPerson"));

            System.out.println("slaPdfDataMap: " + slaPdfDataMap);
        }
    }
}
