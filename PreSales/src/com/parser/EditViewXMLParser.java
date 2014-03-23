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
public class EditViewXMLParser extends DefaultHandler {

    public List<String> txnDetailsList = new ArrayList<String>();
    public Map<String, String> txnMap = new HashMap<String, String>();

    public Map<String, String> getTxnMap() {
        return txnMap;
    }

    public void startElement(String uri, String localName, String tagName, Attributes atts) {

        if (tagName.equals("txn")) {

            txnMap.put("id", atts.getValue("id"));
            txnMap.put("type", atts.getValue("type"));
            txnMap.put("limitFor", atts.getValue("limitFor"));
            txnMap.put("client", atts.getValue("client"));
            txnMap.put("appName", atts.getValue("appName"));
            txnMap.put("benName", atts.getValue("benName"));
            txnMap.put("benAddrs", atts.getValue("benAddrs"));
            txnMap.put("benPhn", atts.getValue("benPhn"));
            txnMap.put("benRegion", atts.getValue("benRegion"));
            txnMap.put("benFax", atts.getValue("benFax"));
            txnMap.put("bencategory", atts.getValue("bencategory"));
            txnMap.put("benTurnOver", atts.getValue("benTurnOver"));
            txnMap.put("benlimitAmount", atts.getValue("benlimitAmount"));
            txnMap.put("appAddrs", atts.getValue("appAddrs"));
            txnMap.put("appPhn", atts.getValue("appPhn"));
            txnMap.put("appRegion", atts.getValue("appRegion"));
            txnMap.put("appFax", atts.getValue("appFax"));
            txnMap.put("appcategory", atts.getValue("appcategory"));
            txnMap.put("appTurnOver", atts.getValue("appTurnOver"));
            txnMap.put("applimitAmount", atts.getValue("applimitAmount"));
            txnMap.put("expactedBusiness", atts.getValue("expactedBusiness"));
            txnMap.put("compSpace", atts.getValue("compSpace"));
            txnMap.put("solution", atts.getValue("solution"));

            System.out.println("txnMap: " + txnMap);
        }
    }
}
