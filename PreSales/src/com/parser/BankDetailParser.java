/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.util.SingletonClass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class BankDetailParser extends DefaultHandler {
    
    HashMap<String, String> bankMap = new HashMap<String, String>();
    HashMap<String, List> completeMap = new HashMap<String, List>();
    List<HashMap> bankList = new ArrayList<HashMap>();
    ContactDetailBean contactBean = null;
    List<ContactDetailBean> contactList = new ArrayList<ContactDetailBean>();
    DocumentsDetailBean docBean = null;
    List<DocumentsDetailBean> docList = new ArrayList<DocumentsDetailBean>();
    
    public HashMap<String, List> getBankDetails(){
        bankList.add(bankMap);
        completeMap.put("bankList", bankList);
        completeMap.put("contactList", contactList);
        completeMap.put("docList", docList);
        return completeMap;
    }
    
     public void startElement(String uri, String localName, String tagName, Attributes atts) {
System.out.println("tag name is "+tagName);
        if(tagName.equalsIgnoreCase("bank")){
          bankMap.put("bankName", SingletonClass.replaceEmptySpace(atts.getValue("bankName")));
          bankMap.put("accountNo",SingletonClass.replaceEmptySpace( atts.getValue("accountNo")));
          bankMap.put("address", SingletonClass.replaceEmptySpace(atts.getValue("address")));
          bankMap.put("alias",SingletonClass.replaceEmptySpace( atts.getValue("alias")));
          bankMap.put("branch", SingletonClass.replaceEmptySpace(atts.getValue("branch")));
          bankMap.put("faxNo", SingletonClass.replaceEmptySpace(atts.getValue("faxNo")));
          bankMap.put("bankType", SingletonClass.replaceEmptySpace(atts.getValue("bankType")));
          bankMap.put("visitStatus", SingletonClass.replaceEmptySpace(atts.getValue("visitStatus")));
          bankMap.put("applicableRenewal", SingletonClass.replaceEmptySpace(atts.getValue("applicableRenewal")));
          bankMap.put("ifscCode", SingletonClass.replaceEmptySpace(atts.getValue("ifscCode")));
            
        }

        if(tagName.equalsIgnoreCase("contact")){
          contactBean = new ContactDetailBean();
          contactBean.setContactDesignation(SingletonClass.replaceEmptySpace(atts.getValue("contactDesignation")));
          contactBean.setContactEmail(SingletonClass.replaceEmptySpace(atts.getValue("contactEmail")));
          contactBean.setContactId(SingletonClass.replaceEmptySpace(atts.getValue("contactId")));
          contactBean.setContactMobile(SingletonClass.replaceEmptySpace(atts.getValue("contactMobile")));
          contactBean.setContactName(SingletonClass.replaceEmptySpace(atts.getValue("ccontName")));
            
        }
        if(tagName.equalsIgnoreCase("doc")){
          docBean = new DocumentsDetailBean();
          docBean.setDocFTPPath(SingletonClass.replaceEmptySpace(atts.getValue("path")));
          docBean.setDocName(SingletonClass.replaceEmptySpace(atts.getValue("name")));
          docBean.setDocType(SingletonClass.replaceEmptySpace(atts.getValue("docType")));
          docBean.setDocLocation(SingletonClass.replaceEmptySpace(atts.getValue("clientDir")));
            
        }
    }
     
     
    public void endElement(String uri, String localName, String tagName) {
      if(tagName.equalsIgnoreCase("contact")){
          contactList.add(contactBean);
      }
      else if(tagName.equalsIgnoreCase("doc")){
          docList.add(docBean);
      }
    }
    
    public List<DocumentsDetailBean> getBankDocList(){
        return docList;
    }
}
