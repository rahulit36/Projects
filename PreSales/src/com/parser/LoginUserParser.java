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
 * @author piyush chawla
 * @version 1.0
 * @since 25 march 2013
 */
public class LoginUserParser extends DefaultHandler {
    public List<String> clientList = new ArrayList<String>();

    public List<String> getUserList() {
        return clientList;
    }
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: "+tagName);
        if (tagName.equals("user")) {
            clientList.add(atts.getValue("name"));
        }
    }
    
}
