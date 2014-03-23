/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class QuotesStringParser extends DefaultHandler{
    String quotes = "";
     public String getquotesStr() {
        return quotes;
    }
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: "+tagName);
        if (tagName.equals("quotes")) {
            quotes = atts.getValue("text");
        }
    }
    
}
