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
public class EmailListParser extends DefaultHandler {

    public Map emailMap = new HashMap();
    public List cPersonList = new ArrayList();

    public List getcPersonList() {
        return cPersonList;
    }

    public Map getEmailMap() {
        return emailMap;
    }

    @Override
    public void startElement(String uri, String localName, String tagName, Attributes atts) {

        if (tagName.equals("email")) {

            emailMap.put("client", atts.getValue("client"));
            emailMap.put("companyaddr", atts.getValue("companyaddr"));
            emailMap.put("contactName", atts.getValue("contactName"));
            emailMap.put("to", atts.getValue("to"));
            emailMap.put("cc", atts.getValue("cc"));
            emailMap.put("bcc", atts.getValue("bcc"));
            emailMap.put("txnType", atts.getValue("txnType"));

            System.out.println("emailMap: " + emailMap);
        }
        if (tagName.equals("contact")) {

            Map emailMap1 = new HashMap();
            emailMap1.put("person", atts.getValue("person"));
            emailMap1.put("notifystr", atts.getValue("notifystr"));
            cPersonList.add(emailMap1);

            System.out.println("emailMap: " + emailMap1);
        }
    }

    @Override
    public void endElement(String uri, String localName, String tagName) {
        if (tagName.equalsIgnoreCase("presales")) {
            emailMap.put("listOfCPersons", cPersonList);
        }
    }
}
