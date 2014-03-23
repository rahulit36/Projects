/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.util.SingletonClass;
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
public class ClientDetailsParser extends DefaultHandler {

    
//    new map used by piyush chawla in manage client view details------
    public HashMap<String, String> newClientDetails = new HashMap<String, String>();
    ContactDetailBean contactDetailBean = null;
    public List<ContactDetailBean> newContactList = new ArrayList<ContactDetailBean>();
    public List<ClientLimitBean> newClientLimitList = new ArrayList<ClientLimitBean>();
    public List<Map<String, String>> newClientList = new ArrayList();
    public HashMap<String, List> newMap = new HashMap<String, List>();
    ClientLimitBean clBean = null;
    public HashMap<String, List> getClientInfoMap(){
        newClientList.add(newClientDetails);
        newMap.put("clientList", newClientList);
        newMap.put("contactList", newContactList);
        newMap.put("clientLimit", newClientLimitList);
        return newMap;
    }
//    ----------------------------------------------
    
    
    
    
    public Map<String, String> clientDetails = new HashMap<String, String>();
    public Map<String, String> getClientDetails() {
        return clientDetails;
    }
    public Map<String, String> contactMap = null;

    public Map<String, String> getContactMap() {
        return contactMap;
    }
    public List contactList = new ArrayList();
    public List contactListOnly = new ArrayList();

    public List getContactList() {
        return contactList;
    }
    
    public List<String> getContactListOnly(){
        return contactListOnly;
    }



    public void startElement(String uri, String localName, String tagName, Attributes atts) {
//        System.out.println("tagName: " + tagName);
        if (tagName.equals("client")) {
         
            if (atts.getValue("name") != null && !atts.getValue("name").equals("")) {
                clientDetails.put("name", atts.getValue("name"));
                newClientDetails.put("name", atts.getValue("name"));

            }
            if (atts.getValue("address") != null && !atts.getValue("address").equals("")) {
                clientDetails.put("address", atts.getValue("address"));
                newClientDetails.put("address", atts.getValue("address"));
            }
            if (atts.getValue("landline") != null && !atts.getValue("landline").equals("")) {
                clientDetails.put("landline", atts.getValue("landline"));
                newClientDetails.put("landline", atts.getValue("landline"));
            }
            if (atts.getValue("faxNo") != null && !atts.getValue("faxNo").equals("")) {
                clientDetails.put("faxNo", atts.getValue("faxNo"));
                newClientDetails.put("faxNo", atts.getValue("faxNo"));
            }
            if (atts.getValue("category") != null && !atts.getValue("category").equals("")) {
                clientDetails.put("category", atts.getValue("category"));
                newClientDetails.put("category", atts.getValue("category"));
            }
            if (atts.getValue("turnOver") != null && !atts.getValue("turnOver").equals("")) {
                clientDetails.put("turnOver", atts.getValue("turnOver"));
                newClientDetails.put("turnOver", atts.getValue("turnOver"));
            }
            if (atts.getValue("lastMeetDate") != null && !atts.getValue("lastMeetDate").equals("")) {
                clientDetails.put("lastMeetDate", atts.getValue("lastMeetDate"));
                newClientDetails.put("lastMeetDate", atts.getValue("lastMeetDate"));
            }
            if (atts.getValue("compSpace") != null && !atts.getValue("compSpace").equals("")) {
                clientDetails.put("compSpace", atts.getValue("compSpace"));
                newClientDetails.put("compSpace", atts.getValue("compSpace"));
            }
            if (atts.getValue("solution") != null && !atts.getValue("solution").equals("")) {
                clientDetails.put("solution", atts.getValue("solution"));
                newClientDetails.put("solution", atts.getValue("solution"));
            }
            if (atts.getValue("region") != null && !atts.getValue("region").equals("")) {
                clientDetails.put("region", atts.getValue("region"));
                newClientDetails.put("region", atts.getValue("region"));
            }
            if (atts.getValue("tenureLastUpdate") != null && !atts.getValue("tenureLastUpdate").equals("")) {
                clientDetails.put("tenureLastUpdate", atts.getValue("tenureLastUpdate"));
                newClientDetails.put("tenureLastUpdate", atts.getValue("tenureLastUpdate"));
            }
            if (atts.getValue("fsaServerPath") != null && !atts.getValue("fsaServerPath").equals("")) {
                clientDetails.put("fsaServerPath", atts.getValue("fsaServerPath"));
                newClientDetails.put("fsaServerPath", atts.getValue("fsaServerPath"));
            }
//            added by piyush chawla
            if (atts.getValue("rmName") != null && !atts.getValue("rmName").equals("")) {
                 newClientDetails.put("rmName", atts.getValue("rmName"));
            }
            if (atts.getValue("accusation") != null && !atts.getValue("accusation").equals("")) {
                 newClientDetails.put("accusation", atts.getValue("accusation"));
            }
            if (atts.getValue("ourCharges") != null && !atts.getValue("ourCharges").equals("")) {
                 newClientDetails.put("ourCharges", atts.getValue("ourCharges"));
//                clientDetails.put("ourCharges", atts.getValue("ourCharges"));
            }
            if (atts.getValue("limitType") != null && !atts.getValue("limitType").equals("")) {
                 newClientDetails.put("limitType", atts.getValue("limitType"));
            }
        }
        System.out.println("clientDetails map is: " + clientDetails+" ** "+atts.getValue("ourCharges"));
        if(tagName.equals("contact"))
        {
               contactMap = new HashMap<String, String>();
               contactDetailBean = new ContactDetailBean();
            if (atts.getValue("ccontName") != null && !atts.getValue("ccontName").equals("")) {
             //   clientDetails.put("ccontName", atts.getValue("ccontName"));
                contactMap.put("ccontName", atts.getValue("ccontName"));
                contactDetailBean.setContactName(atts.getValue("ccontName"));
                contactListOnly.add(atts.getValue("ccontName"));
            }
            if (atts.getValue("ccontactDesignation") != null && !atts.getValue("ccontactDesignation").equals("")) {
              //  clientDetails.put("ccontactDesignation", atts.getValue("ccontactDesignation"));
                contactMap.put("ccontactDesignation", atts.getValue("ccontactDesignation"));
                contactDetailBean.setContactDesignation(atts.getValue("ccontactDesignation"));
            }
            if (atts.getValue("ccontactMobile") != null && !atts.getValue("ccontactMobile").equals("")) {
               // clientDetails.put("ccontactMobile", atts.getValue("ccontactMobile"));
                contactMap.put("ccontactMobile", atts.getValue("ccontactMobile"));
                contactDetailBean.setContactMobile(atts.getValue("ccontactMobile"));
            }
            if (atts.getValue("ccontactEmail") != null && !atts.getValue("ccontactEmail").equals("")) {
              //  clientDetails.put("ccontactEmail", atts.getValue("ccontactEmail"));
                contactMap.put("ccontactEmail", atts.getValue("ccontactEmail"));
                contactDetailBean.setContactEmail(atts.getValue("ccontactEmail"));
            }
            if (atts.getValue("notifyStr") != null && !atts.getValue("notifyStr").equals("")) {
              //  clientDetails.put("ccontactEmail", atts.getValue("ccontactEmail"));
                contactMap.put("notifyStr", atts.getValue("notifyStr"));
                contactDetailBean.setNotifyStr(atts.getValue("notifyStr"));
            }
            
//            added by piyush chawla
            if (atts.getValue("ccontactId") != null && !atts.getValue("ccontactId").equals("")) {
             
               // contactMap.put("ccontactId", atts.getValue("ccontactId"));
               contactDetailBean.setContactId(atts.getValue("ccontactId"));
            }
//            ------------------------------------------
            contactDetailBean.getContactName();
        }
        

        if(tagName.equals("limit")){
            clBean = new ClientLimitBean();
            clBean.setBeneficiary(SingletonClass.replaceEmptySpace(atts.getValue("beneficiary")));
            clBean.setBank(SingletonClass.replaceEmptySpace(atts.getValue("bankName")));
            clBean.setAvailableLimit(SingletonClass.replaceEmptySpace(atts.getValue("avaiableLimit")));
            clBean.setClientLimit(SingletonClass.replaceEmptySpace(atts.getValue("clientLimit")));
            clBean.setClientName(SingletonClass.replaceEmptySpace(atts.getValue("clientName")));
            clBean.setSanctionDate(SingletonClass.replaceEmptySpace(atts.getValue("sanctionDate")));
            clBean.setLimitType(SingletonClass.replaceEmptySpace(atts.getValue("limitType")));
            clBean.setLimitId(SingletonClass.replaceEmptySpace(atts.getValue("limitId")));
        }
        
    }
  public void endElement(String uri, String localName, String tagName) {
      if(tagName.equalsIgnoreCase("contact")){
            contactList.add(contactMap);
      newContactList.add(contactDetailBean);
      }
       if(tagName.equals("limit")){
           newClientLimitList.add(clBean);
       }
  }
    
    public static void main(String arg[])
    {
        System.out.println(""+ new ParserCaller().parseClientContactDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + "piyush2000"));
    }
}
