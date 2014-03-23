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
public class ProposalInternalEmailParser  extends DefaultHandler {
    
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
            emailMap.put("productTrade", atts.getValue("productTrade"));
            emailMap.put("industry", atts.getValue("industry"));
            emailMap.put("yearOfIncorporation", atts.getValue("yearOfIncorporation"));
            emailMap.put("extCreditRating", atts.getValue("extCreditRating"));
            emailMap.put("turnover1", atts.getValue("turnover1"));
            emailMap.put("turnover2", atts.getValue("turnover2"));
            emailMap.put("turnover3", atts.getValue("turnover3"));
            emailMap.put("purchase1", atts.getValue("purchase1"));
            emailMap.put("purchase2", atts.getValue("purchase2"));
            emailMap.put("purchase3", atts.getValue("purchase3"));
            emailMap.put("domestic1", atts.getValue("domestic1"));
            emailMap.put("domestic2", atts.getValue("domestic2"));
            emailMap.put("domestic3", atts.getValue("domestic3"));
            emailMap.put("termLoan1", atts.getValue("termLoan1"));
            emailMap.put("termLoan2", atts.getValue("termLoan2"));
            emailMap.put("fbccLimit1", atts.getValue("fbccLimit1"));
            emailMap.put("fbccLimit2", atts.getValue("fbccLimit2"));
            emailMap.put("nfbLCLimit1", atts.getValue("nfbLCLimit1"));
            emailMap.put("nfbLCLimit2", atts.getValue("nfbLCLimit2"));
            emailMap.put("nfbBGLimit1", atts.getValue("nfbBGLimit1"));
            emailMap.put("nfbBGLimit2", atts.getValue("nfbBGLimit2"));
            emailMap.put("totalWC1", atts.getValue("totalWC1"));
            emailMap.put("totalWC2", atts.getValue("totalWC2"));
            emailMap.put("total1", atts.getValue("total1"));
            emailMap.put("total2", atts.getValue("total2"));
            emailMap.put("total3", atts.getValue("total3"));
            emailMap.put("total4", atts.getValue("total4"));
            emailMap.put("total5", atts.getValue("total5"));
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
