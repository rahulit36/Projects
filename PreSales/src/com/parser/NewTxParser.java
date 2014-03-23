/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class NewTxParser extends DefaultHandler {
    public List<String> clientList = new ArrayList<String>();

    public List<String> getClientList() {
        return clientList;
    }
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
//        System.out.println("tagName: "+tagName);
        if (tagName.equals("client")) {
        clientList.add(atts.getValue("name"));
        }
    }
    
}
