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
 * @author Piyush
 * @since feb20, 2013
 * @version V1.0
 */
public class BankListParser extends DefaultHandler {
    List<String> bankList = new ArrayList<String>();
    
     public void startElement(String uri, String localName, String tagName, Attributes atts) {
         if(tagName.equals("bank")){
             bankList.add(atts.getValue("alias"));
         }
     }
     
     
     public List<String> getBankList(){
         return bankList;
     }
    
}
