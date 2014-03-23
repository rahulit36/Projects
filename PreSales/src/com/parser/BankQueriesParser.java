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
public class BankQueriesParser extends DefaultHandler {

    List<Map> queriesList = new ArrayList<Map>();

    public List<Map> getQueriesList() {
        return queriesList;
    }

    public void startElement(String uri, String localName, String tagName, Attributes atts) {

        if (tagName.equals("queries")) {

            Map<String, String> queryMap = new HashMap<String, String>();

//<?xml version="1.0" encoding="UTF-8"?><presales><queries queryText="me" status="nthng" isActive="Y" 
//        txnNo="8" userName="rahul" raisedBy="testing comp" closedBy="me" closedOn="13-03-2013" 
//                RaisedOn="04-03-2013" queryNo="1" /></presales>


            queryMap.put("queryText", atts.getValue("queryText"));
            queryMap.put("status", atts.getValue("status"));
            queryMap.put("isActive", atts.getValue("isActive"));
            queryMap.put("txnNo", atts.getValue("txnNo"));
            queryMap.put("raisedBy", atts.getValue("raisedBy"));
            queryMap.put("closedBy", atts.getValue("closedBy"));
            queryMap.put("closedOn", atts.getValue("closedOn"));
            queryMap.put("RaisedOn", atts.getValue("RaisedOn"));
            queryMap.put("queryNo", atts.getValue("queryNo"));
            queryMap.put("queryComments", atts.getValue("queryComments"));
            
            queriesList.add(queryMap);
            System.out.println("queryMap: " + queryMap);
        }
    }
}
