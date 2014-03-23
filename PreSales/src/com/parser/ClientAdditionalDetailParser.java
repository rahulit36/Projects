/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class ClientAdditionalDetailParser extends DefaultHandler{

    public Map<String, String> additionalDetailsMap = new HashMap<String, String>();

    //    new map used by piyush chawla in manage client view details
    public Map<String, String> getAdditionalDetailsMap() {
        return additionalDetailsMap;
    }
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: " + tagName);
        if (tagName.equals("clientDetails")) {

            if (atts.getValue("name") != null && !atts.getValue("name").equals("")) {
                additionalDetailsMap.put("name", atts.getValue("name"));

            }
            if (atts.getValue("background") != null && !atts.getValue("background").equals("")) {
                additionalDetailsMap.put("background", atts.getValue("background"));
            }
            if (atts.getValue("bankArrange") != null && !atts.getValue("bankArrange").equals("")) {
                additionalDetailsMap.put("bankArrange", atts.getValue("bankArrange"));
            }

            if (atts.getValue("branchAddress") != null && !atts.getValue("branchAddress").equals("")) {
                additionalDetailsMap.put("branchAddress", atts.getValue("branchAddress"));

            }
            if (atts.getValue("clientele") != null && !atts.getValue("clientele").equals("")) {
                additionalDetailsMap.put("clientele", atts.getValue("clientele"));
            }
            if (atts.getValue("constitution") != null && !atts.getValue("constitution").equals("")) {
                additionalDetailsMap.put("constitution", atts.getValue("constitution"));
            }

            if (atts.getValue("creditRating") != null && !atts.getValue("creditRating").equals("")) {
                additionalDetailsMap.put("creditRating", atts.getValue("creditRating"));

            }
            if (atts.getValue("incorporation") != null && !atts.getValue("incorporation").equals("")) {
                additionalDetailsMap.put("incorporation", atts.getValue("incorporation"));
            }
            if (atts.getValue("endingBalanceSheet") != null && !atts.getValue("endingBalanceSheet").equals("")) {
                additionalDetailsMap.put("endingBalanceSheet", atts.getValue("endingBalanceSheet"));
            }

            if (atts.getValue("financialPerformance") != null && !atts.getValue("financialPerformance").equals("")) {
                additionalDetailsMap.put("financialPerformance", atts.getValue("financialPerformance"));

            }
            if (atts.getValue("headOfficeAddr") != null && !atts.getValue("headOfficeAddr").equals("")) {
                additionalDetailsMap.put("headOfficeAddr", atts.getValue("headOfficeAddr"));
            }
            if (atts.getValue("history") != null && !atts.getValue("history").equals("")) {
                additionalDetailsMap.put("history", atts.getValue("history"));
            }

            if (atts.getValue("industryOverview") != null && !atts.getValue("industryOverview").equals("")) {
                additionalDetailsMap.put("industryOverview", atts.getValue("industryOverview"));

            }
            if (atts.getValue("managementDetails") != null && !atts.getValue("managementDetails").equals("")) {
                additionalDetailsMap.put("managementDetails", atts.getValue("managementDetails"));
            }
            if (atts.getValue("manufacturingSetup") != null && !atts.getValue("manufacturingSetup").equals("")) {
                additionalDetailsMap.put("manufacturingSetup", atts.getValue("manufacturingSetup"));
            }

            if (atts.getValue("principalActivity") != null && !atts.getValue("principalActivity").equals("")) {
                additionalDetailsMap.put("principalActivity", atts.getValue("principalActivity"));

            }
            if (atts.getValue("productsandApps") != null && !atts.getValue("productsandApps").equals("")) {
                additionalDetailsMap.put("productsandApps", atts.getValue("productsandApps"));
            }
            if (atts.getValue("promoters") != null && !atts.getValue("promoters").equals("")) {
                additionalDetailsMap.put("promoters", atts.getValue("promoters"));
            }
            if (atts.getValue("suppliers") != null && !atts.getValue("suppliers").equals("")) {
                additionalDetailsMap.put("suppliers", atts.getValue("suppliers"));
            }

        }
    }
}
