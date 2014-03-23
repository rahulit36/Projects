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
 * @author Piyush Chawla
 * @since  feb15, 2013
 * @version V1.0
 * this class id to pass user data coming in xml file from this class we can get a list of preuserDetails object
 * which can be used to fetch user details and to display them in userDetail Table.....
 */
public class UserDetailParser extends DefaultHandler{
    
    
    PreUserDetails preUserDetails=null;
    List<PreUserDetails> userList = new ArrayList<PreUserDetails>();
    
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
            
            
             userList.add(preUserDetails);
        }
    }
    
//    public void endElement(String uri, String localName, String tagName){
//        if (tagName.equals("user")) {
//            userList.add(preUserDetails);
//        }
//    }
    
    public List<PreUserDetails> getUserList(){
        return userList;
    }
}
