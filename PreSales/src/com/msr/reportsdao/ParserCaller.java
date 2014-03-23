/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportsdao;

import com.msr.util.SingletonClass;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author piyush chawla
 * @since march 06, 2013
 * @version  V1.0
 */
public class ParserCaller {
    
    
    public List<MeetingDetailBean> getMeetingListFromXML(String userName,HashMap<String, String> map) {
         List<MeetingDetailBean> meetingList = new ArrayList<MeetingDetailBean>();         
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allMeetingsXML").append("&").append(SingletonClass.toDate).append("=").append(map.get("toDate")).append("&").append(SingletonClass.fromDate).append("=").append(map.get("fromDate")).append("&").append(SingletonClass.status).append("=").append(map.get("status")).append("&").append(SingletonClass.bank).append("=").append(URLEncoder.encode(map.get("bank"))).append("&").append(SingletonClass.category).append("=").append(map.get("category")).append("&").append(SingletonClass.client).append("=").append(URLEncoder.encode(map.get("client"))).append("&").append(SingletonClass.rmName).append("=").append(map.get("rmName")).append("&").append(SingletonClass.trnxType).append("=").append(map.get("trnxType"));
         try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            MeetingDetailsParser parser = new MeetingDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            meetingList = parser.getMeetingList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return meetingList;
    }
//    here we parse xml for the details of query so that report can be generated----------
    public List<QueryDetailBean> getQueryListFromXML(String userName,HashMap<String, String> map) {
         List<QueryDetailBean> queryList = new ArrayList<QueryDetailBean>();
         
         
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allBankQueryReportXML").append("&").append(SingletonClass.toDate).append("=").append(map.get("toDate")).append("&").append(SingletonClass.fromDate).append("=").append(map.get("fromDate")).append("&").append(SingletonClass.status).append("=").append(map.get("status")).append("&").append(SingletonClass.bank).append("=").append(URLEncoder.encode(map.get("bank"))).append("&").append(SingletonClass.category).append("=").append(map.get("category")).append("&").append(SingletonClass.client).append("=").append(URLEncoder.encode(map.get("client"))).append("&").append(SingletonClass.rmName).append("=").append(map.get("rmName")).append("&").append(SingletonClass.trnxType).append("=").append(map.get("trnxType"));
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            QueryDetailsParser parser = new QueryDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            queryList = parser.getQueryList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return queryList;
    }
    
    //    here we parse xml for the details of query so that report can be generated----------
    public List<HistoryBean> getHistoryListFromXML(String userName,HashMap<String, String> map) {
         List<HistoryBean> historyList = new ArrayList<HistoryBean>();        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=clientHistoryXML").append("&").append(SingletonClass.toDate).append("=").append(map.get("toDate")).append("&").append(SingletonClass.fromDate).append("=").append(map.get("fromDate")).append("&").append(SingletonClass.bank).append("=").append(URLEncoder.encode(map.get("bank"))).append("&").append(SingletonClass.client).append("=").append(URLEncoder.encode(map.get("client"))).append("&").append(SingletonClass.rmName).append("=").append(map.get("rmName"));
      
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            HistoryDetailParser parser = new HistoryDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            historyList = parser.getHistoryList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return historyList;
    }
    
    
    //    here we parse xml for the details of rm transaction so that report can be generated----------
    public List<RMTransactionBean> getRmTxListFromXML(String userName,HashMap<String, String> map) {
         List<RMTransactionBean> rmTxList = new ArrayList<RMTransactionBean>();
         
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=alltxnReportXML").append("&").append(SingletonClass.toDate).append("=").append(map.get("toDate")).append("&").append(SingletonClass.fromDate).append("=").append(map.get("fromDate")).append("&").append(SingletonClass.status).append("=").append(map.get("status")).append("&").append(SingletonClass.bank).append("=").append(URLEncoder.encode(map.get("bank"))).append("&").append(SingletonClass.category).append("=").append(map.get("category")).append("&").append(SingletonClass.client).append("=").append(URLEncoder.encode(map.get("client"))).append("&").append(SingletonClass.rmName).append("=").append(map.get("rmName")).append("&").append(SingletonClass.trnxType).append("=").append(map.get("trnxType"));
                
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            TransactionByRmParser parser = new TransactionByRmParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            rmTxList = parser.getRmTxList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rmTxList;
    }
    
    
      //    here we parse xml for the details of Bank transaction so that report can be generated----------
    public List<BankTransactionBean> getBankTxListFromXML(String userName,HashMap<String, String> map) {
         List<BankTransactionBean> bankTxList = new ArrayList<BankTransactionBean>();
         
       
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=alltxnReportXML").append("&").append(SingletonClass.toDate).append("=").append(map.get("toDate")).append("&").append(SingletonClass.fromDate).append("=").append(map.get("fromDate")).append("&").append(SingletonClass.status).append("=").append(map.get("status")).append("&").append(SingletonClass.bank).append("=").append(map.get("bank")).append("&").append(SingletonClass.category).append("=").append(map.get("category")).append("&").append(SingletonClass.client).append("=").append(map.get("client")).append("&").append(SingletonClass.rmName).append("=").append(map.get("rmName")).append("&").append(SingletonClass.trnxType).append("=").append(map.get("trnxType"));
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            TransactionByBankParser parser = new TransactionByBankParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            bankTxList = parser.getBankTxList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankTxList;
    }
}
