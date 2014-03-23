/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.newTx.dao.MeetingDetailBean;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class viewUserParser extends DefaultHandler{
    
    PreUserDetails preUserDetails=null;

    public PreUserDetails getUserDetailsMap(){
        return preUserDetails;
    }
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: "+tagName);
        if (tagName.equals("user")) {
            preUserDetails=new PreUserDetails();
            String name=atts.getValue("name");
            String emailId=atts.getValue("emailId");
//            String userPassword=atts.getValue("userPassword");
            String desingation=atts.getValue("desingation");   
            String mobNo=atts.getValue("mobNo");  
             char isActive=atts.getValue("isActive").charAt(0);   
            String emailNotification=atts.getValue("emailNotification");  
                       String manager=atts.getValue("manager");  
            preUserDetails.setUserName(name);
            preUserDetails.setUserEmail(emailId);
            preUserDetails.setIsActive(isActive);
            preUserDetails.setMobNo(mobNo);
            preUserDetails.setDesingation(desingation);
            preUserDetails.setEmailNotification(emailNotification);
            preUserDetails.setRmManager(manager);
            
            
        }
    }
}
