/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class HistoryDetailParser extends DefaultHandler{
    
    HistoryBean historyBean = null;
    List<HistoryBean> historyList = new ArrayList<HistoryBean>();
    public void startElement(String uri, String localName, String qName, Attributes atts) 
    {
     
  if(qName.equals("history")){
            historyBean =  new HistoryBean();
            historyBean.setTxNo(atts.getValue("txNo"));
            historyBean.setTxStatus(atts.getValue("txStatus"));
            historyBean.setTxType(atts.getValue("txType"));
            historyBean.setClientName(atts.getValue("clientName"));
            historyBean.setBankName(atts.getValue("bankName"));
            historyBean.setBeneficiary(atts.getValue("beneficiary"));
            historyBean.setLimitSanctioned(atts.getValue("limitSanctioned"));
            historyBean.setSantionDate(atts.getValue("santionDate"));
            historyBean.setAvailableLimit(atts.getValue("availableLimit"));
        }
    }

    public void endElement(String uri, String name, String qName)
    {
        if(qName.equals("history")){
            historyList.add(historyBean);
        }
    }
    
    public List<HistoryBean> getHistoryList(){
        return historyList;
    }
}