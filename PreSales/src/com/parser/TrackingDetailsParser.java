/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class TrackingDetailsParser extends DefaultHandler {

    private String txId = "";
    private String type = "";
    private String client = "";
    private String beneficiary = "";
    private String bank = "";
    private String limitSanctioned = "";
    private String expBusiness = "";
    private String region = "";
    private String compSpace = "";
    private String solution = "";
    private String ourCharges = "";
    private String rmName = "";
    List<HashMap> txList = new ArrayList<HashMap>();

    public List<HashMap> getTxList() {
        return txList;
    }

    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("****** IN TrackingDetailsParser ");
        if (tagName.equals("txn")) {
            txId = atts.getValue("id");
            type = atts.getValue("type");
            client = atts.getValue("client");
            beneficiary = atts.getValue("beneficiary");
            bank = atts.getValue("bankName");
            limitSanctioned = atts.getValue("limitSanctioned");
            expBusiness = atts.getValue("expBusiness");
            region = atts.getValue("region");
            compSpace = atts.getValue("compSpace");
            solution = atts.getValue("solution");
            ourCharges = atts.getValue("ourCharges");
            rmName = atts.getValue("rmName");

        }

    }

    public void endElement(String uri, String localName, String tagName) {
        if (tagName.equalsIgnoreCase("txn")) {
            HashMap<String, String> txMap = new HashMap<String, String>();
            txMap.put("txId", txId);
            txMap.put("type", type);
            txMap.put("client", client);
            if (beneficiary == null || beneficiary .equals("null")) {
                txMap.put("beneficiary", "");
            } else {
                txMap.put("beneficiary", beneficiary);
            }
            if (bank == null || bank .equals("null")) {
                txMap.put("bank", "");
            } else {
                txMap.put("bank", bank);
            }
            if (limitSanctioned == null || limitSanctioned .equals("null")) {
                txMap.put("limitSanctioned", "");
            } else {
                txMap.put("limitSanctioned", limitSanctioned);
            }
            if (expBusiness == null || expBusiness .equals("null")) {
                txMap.put("expBusiness", "");
            } else {
                txMap.put("expBusiness", expBusiness);
            }
            if (region == null || region .equals("null")) {
                txMap.put("region", "");
            } else {
                txMap.put("region", region);
            }
            if (compSpace == null || compSpace .equals("null")) {
                txMap.put("compSpace", "");
            } else {
                txMap.put("compSpace", compSpace);
            }
            if (solution == null || solution .equals("null")) {
                txMap.put("solution", "");
            } else {
                txMap.put("solution", solution);
            }
            if (ourCharges == null || ourCharges .equals("null")) {
                txMap.put("ourCharges", "");
            } else {
                txMap.put("ourCharges", ourCharges);
            }
            if (rmName == null || rmName.equals("null")) {
                txMap.put("rmName", "");
            } else {
                txMap.put("rmName", rmName);
            }
            txList.add(txMap);
            System.out.println("****** list size: " + txList.size() + " " + txId + " " + type + " " + client + " " + beneficiary + " " + bank + " " + limitSanctioned);
        }
    }
}
