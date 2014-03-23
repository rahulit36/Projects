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
 * @author root
 */
public class BenListParser extends DefaultHandler{
        public List<String> benList = new ArrayList<String>();

    public List<String> getBenList() {
        return benList;
    }

    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: "+tagName);
        if (tagName.equals("beneficiary")) {
            benList.add(atts.getValue("name"));
        }
    }
    
}
