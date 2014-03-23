/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.newTx.dao.MeetingDetailBean;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class LoginParser extends DefaultHandler {

    HashMap<String, String> loginMap = new HashMap<String, String>();

    public HashMap<String, String> getLoginMap() {
        return loginMap;
    }
    
    

    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: " + tagName);
        if (tagName.equals("user")) {
            loginMap.put("uName",atts.getValue("uName"));
            loginMap.put("designation",atts.getValue("designation"));
            String empDesgntion ="";
            if(!atts.getValue("empDesignation").equals("null") && !atts.getValue("empDesignation").equals(""))
                empDesgntion = atts.getValue("empDesignation");
            loginMap.put("empDesignation",empDesgntion);
            String mobNo ="";
            if(!atts.getValue("mobNo").equals("null") && !atts.getValue("mobNo").equals(""))
                mobNo = atts.getValue("mobNo");
            loginMap.put("mobNo",mobNo);
            loginMap.put("email",atts.getValue("email"));
            loginMap.put("previleges",atts.getValue("previleges"));
        }
    }
}
