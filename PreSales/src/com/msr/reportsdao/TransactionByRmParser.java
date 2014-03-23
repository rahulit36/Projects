/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;

import com.msr.util.SingletonClass;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author piyush chawla
 * @since  march 08, 2013
 * @version V1.0
 */
public class TransactionByRmParser  extends DefaultHandler{
        RMTransactionBean rmBean = null;
        List<RMTransactionBean> rmTxList = new ArrayList<RMTransactionBean>();
    public void startElement(String uri, String localName, String qName, Attributes atts) 
    {
        if(qName.equals("txn")){
            rmBean = new RMTransactionBean();
            rmBean.setTxnStatus(SingletonClass.replaceEmptySpace(atts.getValue("txnStatus")));
            rmBean.setTxnType(SingletonClass.replaceEmptySpace(atts.getValue("txnType")));
            rmBean.setBankName(SingletonClass.replaceEmptySpace(atts.getValue("bankName")));
            rmBean.setClientName(SingletonClass.replaceEmptySpace(atts.getValue("clientName")));
            rmBean.setRmName(SingletonClass.replaceEmptySpace(atts.getValue("rm")));
            rmBean.setLimitType(SingletonClass.replaceEmptySpace(atts.getValue("limitType")));
            rmBean.setProposedLimit(SingletonClass.replaceEmptySpace(atts.getValue("proposedLimit")));
            rmBean.setActualLimit(SingletonClass.replaceEmptySpace(atts.getValue("sanctionLimit")));
            rmBean.setUsance(SingletonClass.replaceEmptySpace(atts.getValue("usance")));
            rmBean.setInterestRate(SingletonClass.replaceEmptySpace(atts.getValue("interestRate")));
            rmBean.setMargin(SingletonClass.replaceEmptySpace(atts.getValue("margin")));
            rmBean.setHandlingCharges(SingletonClass.replaceEmptySpace(atts.getValue("handlingCharges")));
            rmBean.setOurCharge(SingletonClass.replaceEmptySpace(atts.getValue("ourCharges")));
            rmBean.setOurChargetype(SingletonClass.replaceEmptySpace(atts.getValue("ourChargesType")));
        }
    }
    
    public void endElement(String uri, String name, String qName)
    {
       
        if(qName.equals("txn")){
            rmTxList.add(rmBean);
        }
           
        
    }
    
    public List<RMTransactionBean> getRmTxList(){
        return rmTxList;
    }

}
