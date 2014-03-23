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
public class ManagerListParser extends DefaultHandler{
    
     List<String> managerList = new ArrayList<String>();
     List<String> adminList = new ArrayList<String>();
    
     public void startElement(String uri, String localName, String tagName, Attributes atts) {
         if(tagName.equals("user")){
             managerList.add(atts.getValue("name"));
             if("Super Admin".equals(atts.getValue("designation"))){
                 adminList.add(atts.getValue("name"));
             }
         }
     }
     
     public HashMap<String, List<String>> getManagerMap(){
         HashMap<String, List<String>> map = new HashMap<String, List<String>>();
         map.put("All", managerList);
         map.put("Admin", adminList);
         
         return map;
     }
     
     public List<String> getManagerList(){
         return managerList;
     }
}
