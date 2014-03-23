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
public class BankNameListParser extends DefaultHandler{
        public List<String> bankList = new ArrayList<String>();

    public List<String> getBankList() {
        bankList = getAnyOnTop();
        return bankList;
    }
    public void startElement(String uri, String localName, String tagName, Attributes atts) {
//        System.out.println("tagName: "+tagName);
        if (tagName.equals("bank")) {
            bankList.add(atts.getValue("alias"));
        }
    }
    
    public List getAnyOnTop(){
        List<String> bankList1 = new ArrayList<String>();
        for(int i= 0; i<bankList.size();i++){
            if(bankList.get(i).equalsIgnoreCase("any bank")){
                bankList1.add(bankList.get(i));
                bankList.remove(i);
            }
        }
        bankList1.addAll(bankList);
        return bankList1;
    }
}
