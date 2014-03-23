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
 * @author piyush
 * @since feb21, 2013
 * @version V1.0
 */
public class AllBankDetailParser extends DefaultHandler{
    
    AllBankDetailBean bankBean = null;
    List<AllBankDetailBean> bankList = new ArrayList<AllBankDetailBean>();
    public void startElement(String uri, String localName, String tagName, Attributes atts) {

        if(tagName.equalsIgnoreCase("bank")){
            bankBean = new AllBankDetailBean();
            bankBean.setBankName(atts.getValue("bankName"));
            bankBean.setBankAlias(atts.getValue("alias"));
            bankBean.setAccountNo(atts.getValue("accountNo"));
            bankBean.setAddress(atts.getValue("address"));
            bankBean.setBankType(atts.getValue("bankType"));
            bankBean.setBranch(atts.getValue("branch"));
            bankBean.setFaxNo(atts.getValue("faxNo"));
            bankBean.setIfscCode(atts.getValue("ifscCode"));
            
        }
    }
    
    public void endElement(String uri, String localName, String tagName) {
      if(tagName.equalsIgnoreCase("bank")){
          bankList.add(bankBean);
      }
    }
    
    public List<AllBankDetailBean> getBanksDetailedList(){
        return bankList;
    }
}
