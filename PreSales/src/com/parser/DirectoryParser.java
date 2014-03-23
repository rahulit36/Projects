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
 * @author root
 */
public class DirectoryParser extends DefaultHandler{
    
    List<String> docPathList = new ArrayList<String>();
    List<String> docPathList4Client = new ArrayList<String>();
    
    
    
     public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: " + tagName);
        if(tagName.equals("client")){
            if(!atts.getValue("path").equals("")&&!atts.getValue("path").equalsIgnoreCase("null")){
                String remvPath = atts.getValue("path").replaceFirst("/", "%");
                String path = remvPath.substring(remvPath.indexOf("%")+1)+"/"+atts.getValue("directory");
                docPathList.add(path);
//                docPathList4Client.add(path+":"+atts.getValue("isBen"));
                 docPathList4Client.add(atts.getValue("directory")+":"+path+":"+atts.getValue("isBen"));
            }
        }
     }
     
     public List<String> getDocPathList(){
         return docPathList;
     }
      public List<String> getDocPathList4Client(){
         return docPathList4Client;
     }
}
