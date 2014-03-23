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
public class TransactionByBankParser  extends DefaultHandler{
        BankTransactionBean bankBean = null;
        List<BankTransactionBean> BankTxList = new ArrayList<BankTransactionBean>();
    public void startElement(String uri, String localName, String qName, Attributes atts) 
    {
        if(qName.equals("txn")){
            bankBean = new BankTransactionBean();
            bankBean.setTxnStatus(SingletonClass.replaceEmptySpace(atts.getValue("txnStatus")));
            bankBean.setTxnType(SingletonClass.replaceEmptySpace(atts.getValue("txnType")));
            bankBean.setClientName(SingletonClass.replaceEmptySpace(atts.getValue("clientName")));
            bankBean.setRmName(SingletonClass.replaceEmptySpace(atts.getValue("rm")));
            bankBean.setLimitType(SingletonClass.replaceEmptySpace(atts.getValue("limitType")));
            bankBean.setProposedLimit(SingletonClass.replaceEmptySpace(atts.getValue("proposedLimit")));
            bankBean.setActualLimit(SingletonClass.replaceEmptySpace(atts.getValue("sanctionLimit")));
            bankBean.setLimitInitiated(SingletonClass.replaceEmptySpace(atts.getValue("limitInitiated")));
            bankBean.setLimitCompleted(SingletonClass.replaceEmptySpace(atts.getValue("")));
            
        }
    }
    
    public void endElement(String uri, String name, String qName)
    {
       
        if(qName.equals("txn")){
            BankTxList.add(bankBean);
        }
           
        
    }
    
    public List<BankTransactionBean> getBankTxList(){
        return BankTxList;
    }

}
