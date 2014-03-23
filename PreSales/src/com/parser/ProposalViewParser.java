/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.newTx.dao.ProposalViewBean;
import com.msr.util.SingletonClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author rahul
 * @since 22 feb 2013
 * @version 1.0
 *
 */
public class ProposalViewParser extends DefaultHandler {

    ProposalViewBean proposalViewBean = new ProposalViewBean();

    public ProposalViewBean getProposalViewBean() {
        return proposalViewBean;
    }
    
    List<HashMap> ilcList = new ArrayList<HashMap>();
    List<HashMap> flcList = new ArrayList<HashMap>();
    HashMap<String, String> valuesMap = null;

    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: " + tagName);
        if (tagName.equals("proposal")) {
            valuesMap = new HashMap<String, String>();
            if (atts.getValue("txnNo") != null && !atts.getValue("txnNo").equals("")) {
                valuesMap.put("txnNo", atts.getValue("txnNo"));
            }
            if (atts.getValue("beneficiary") != null && !atts.getValue("beneficiary").equals("")) {
                valuesMap.put("beneficiary", atts.getValue("beneficiary"));
            }
            if (atts.getValue("proposalNo") != null && !atts.getValue("proposalNo").equals("")) {
                valuesMap.put("proposalNo", atts.getValue("proposalNo"));
            }
            if (atts.getValue("clientAddrs") != null && !atts.getValue("clientAddrs").equals("")) {
                valuesMap.put("clientAddrs", atts.getValue("clientAddrs"));
            }
            if (atts.getValue("contactPerson") != null && !atts.getValue("contactPerson").equals("")) {
                valuesMap.put("contactPerson", atts.getValue("contactPerson"));
            }
            if (atts.getValue("client") != null && !atts.getValue("client").equals("")) {
                valuesMap.put("client", atts.getValue("client"));
            }
            if (atts.getValue("bankName") != null && !atts.getValue("bankName").equals("")) {
                valuesMap.put("bankName", atts.getValue("bankName"));
            }

            if (atts.getValue("sanctionDate") != null && !atts.getValue("sanctionDate").equals("")) {
                valuesMap.put("sanctionDate", atts.getValue("sanctionDate"));
            }
            if (atts.getValue("actualLimit") != null && !atts.getValue("actualLimit").equals("")) {
                valuesMap.put("actualLimit", atts.getValue("actualLimit"));
            }
            if (atts.getValue("proposallimit") != null && !atts.getValue("proposallimit").equals("")) {
                valuesMap.put("proposallimit", atts.getValue("proposallimit"));
            }
            if (atts.getValue("limitType") != null && !atts.getValue("limitType").equals("") && !atts.getValue("limitType").equals("null") ) {
                valuesMap.put("limitType", atts.getValue("limitType"));
            }
            if (atts.getValue("usance") != null && !atts.getValue("usance").equals("")) {
                valuesMap.put("usance", atts.getValue("usance"));
            }
            if (atts.getValue("interestRate") != null && !atts.getValue("interestRate").equals("")) {
                valuesMap.put("interestRate", atts.getValue("interestRate"));
            }
            if (atts.getValue("marginDays") != null && !atts.getValue("marginDays").equals("")) {
                valuesMap.put("marginDays", atts.getValue("marginDays"));
            }
            if (atts.getValue("handlingCharges") != null && !atts.getValue("handlingCharges").equals("")) {
                valuesMap.put("handlingCharges", atts.getValue("handlingCharges"));
            }
            if (atts.getValue("ourCharges") != null && !atts.getValue("ourCharges").equals("")) {
                valuesMap.put("ourCharges", atts.getValue("ourCharges"));
            }
            if (atts.getValue("chargesType") != null && !atts.getValue("chargesType").equals("")) {
                valuesMap.put("chargesType", atts.getValue("chargesType"));
            }
            if (atts.getValue("txnStatus") != null && !atts.getValue("txnStatus").equals("")) {
                valuesMap.put("txnStatus", atts.getValue("txnStatus"));
            }
            if (atts.getValue("lcType") != null && !atts.getValue("lcType").equals("")) {
                valuesMap.put("lcType", atts.getValue("lcType"));
            }
            if (atts.getValue("lcStatus") != null && !atts.getValue("lcStatus").equals("")) {
                valuesMap.put("lcStatus", atts.getValue("lcStatus"));
            }
            if (atts.getValue("expBusiness") != null && !atts.getValue("expBusiness").equals("")) {
                valuesMap.put("expBusiness", atts.getValue("expBusiness"));
            }
            if (atts.getValue("visitDate") != null && !atts.getValue("visitDate").equals("")) {
                valuesMap.put("visitDate", atts.getValue("visitDate"));
            }
            if (atts.getValue("visitStatus") != null && !atts.getValue("visitStatus").equals("")) {
                valuesMap.put("visitStatus", atts.getValue("visitStatus"));
            }
            if (atts.getValue("applicableRenewal") != null && !atts.getValue("applicableRenewal").equals("")) {
                valuesMap.put("applicableRenewal", atts.getValue("applicableRenewal"));
            }
            System.out.println("valuesMap map is: " + valuesMap);

        }
    }

    public void endElement(String uri, String localName, String tagName) {
        if (tagName.equalsIgnoreCase("proposal")) {

            if (valuesMap.get("lcType").equalsIgnoreCase("ILC")) {
                ilcList.add(valuesMap);
            }
            if (valuesMap.get("lcType").equalsIgnoreCase("FLC")) {
                flcList.add(valuesMap);
            }
            valuesMap = null;
        }
        if (tagName.equalsIgnoreCase("presales")) {
           proposalViewBean.setIlcList(ilcList);
           proposalViewBean.setFlcList(flcList);
           ilcList = null;
           flcList = null;
        }
    }
}
