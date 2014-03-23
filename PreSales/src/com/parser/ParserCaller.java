/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.newTx.dao.MeetingDetailBean;
import com.msr.newTx.dao.ProposalViewBean;
import com.msr.util.SingletonClass;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class ParserCaller {

    public Map<String, String> loginMap = new HashMap<String, String>();
    public Map<String, String> clientDetailsMap = new HashMap<String, String>();
    public Map<String, String> clientAdditionalDetailsMap = new HashMap<String, String>();
    public Map<String, String> editViewTxnMap = new HashMap<String, String>();
    public Map<String, String> emailMap = new HashMap<String, String>();
    public Map emailMapForClient = new HashMap();
    public Map<String, String> slaPdfDataMap = new HashMap<String, String>();
    public List<String> clientList = new ArrayList<String>();
    public List<String> allClientList = new ArrayList<String>();
    public List<String> benList = new ArrayList<String>();
    public List<Map> queriesList = new ArrayList<Map>();
    public List<String> bankList = new ArrayList<String>();
    public List<String> contactList = new ArrayList<String>();
    public List<HashMap> trackingTxList = new ArrayList<HashMap>();

    public List<String> parseClientListXml(String userName) {
        try {
            XMLReader xmlReader = null;
            NewTxParser parser = new NewTxParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&type=clientList"));
            clientList = parser.getClientList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientList;
    }

    public List<String> parseAllClientListXml(String userName) {
        try {
            System.out.println(SingletonClass.httpServerUrl+"uName=" + userName + "&type=allClientListForCheck");
            XMLReader xmlReader = null;
            NewTxParser parser = new NewTxParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&type=allClientListForCheck"));
            allClientList = parser.getClientList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allClientList;
    }


    public List<String> parseBenListXml(String userName, String clientName) {
        try {
            System.out.println("beneficiaryList: "+SingletonClass.httpServerUrl+SingletonClass.operaionType+"=beneficiaryList&"+"uName=" + userName + "&clientName="+ URLEncoder.encode(clientName));
            XMLReader xmlReader = null;
            BenListParser parser = new BenListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            System.out.println(" ull    00000000000000       "+SingletonClass.httpServerUrl+SingletonClass.operaionType+"=beneficiaryList&"+"uName=" + userName + "&clientName="+ URLEncoder.encode(clientName));
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+SingletonClass.operaionType+"=beneficiaryList&"+"uName=" + userName + "&clientName="+ URLEncoder.encode(clientName)));
            benList = parser.getBenList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return benList;
    }

  public List<String> parseUserListXml() {
        List<String> userList = new ArrayList<String>();
        try {
            XMLReader xmlReader = null;
            LoginUserParser parser = new LoginUserParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"type=loginUserList"));
            userList = parser.getUserList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }
    
    public String parseQuotesListXml() {
       String quotes = "";
        try {
            XMLReader xmlReader = null;
            QuotesStringParser parser = new QuotesStringParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"type=quotesList"));
            quotes = parser.getquotesStr();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return quotes;
    }

// parse bank names xml.
    public List<String> parseBankNameListXml(String userName) {
        try {
            XMLReader xmlReader = null;
            BankNameListParser parser = new BankNameListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&type=bankList"));
            bankList = parser.getBankList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankList;
    }

    public MeetingDetailBean parseEmailXml(String url, MeetingDetailBean meetingDetailBeanObj) {

        try {
            System.out.println("parseEmailXml URL is: " + url);
            XMLReader xmlReader = null;
            EmailParser parser = new EmailParser(meetingDetailBeanObj);
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(url));
            meetingDetailBeanObj = parser.getmeetingDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return meetingDetailBeanObj;
    }

    public Map<String, String> parseClientDetailsXml(String url) {

        try {
            System.out.println("parseClientDetailsXml URL is: " + url);
            XMLReader xmlReader = null;
            ClientDetailsParser parser = new ClientDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);  
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(url));
            clientDetailsMap = parser.getClientDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientDetailsMap;
    }


    public Map<String, String> parseClientAdditionalDetailsXml(String url) {

        try {
            System.out.println("parseClientAdditionalDetailsXml URL is: " + url);
            XMLReader xmlReader = null;
            ClientAdditionalDetailParser parser = new ClientAdditionalDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);  
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(url));
            clientAdditionalDetailsMap = parser.getAdditionalDetailsMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientAdditionalDetailsMap;
    }

    public Map<String, String> parseLoginXml(String xmlString) {

        try {
//            System.out.println("Login URL: "+SingletonClass.httpServerUrl + SingletonClass.userName + "=" + userName + "&" + SingletonClass.password + "=" + password + "&" + SingletonClass.operaionType + "=login");
            XMLReader xmlReader = null;
            LoginParser parser = new LoginParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(new StringReader(xmlString)));
            loginMap = parser.getLoginMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return loginMap;
    }

    public ProposalViewBean parseProposalDataForView(String url) {
        ProposalViewBean proposalViewBean = null;
        try {
            System.out.println("parseProposalDataForView URL is: " + url);
            XMLReader xmlReader = null;
            ProposalViewParser parser = new ProposalViewParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);  
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(url));
            proposalViewBean = parser.getProposalViewBean();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return proposalViewBean;
    }

    public List<String> parseClientContactDetailsXml(String url) {
        // public List<Map> contactListMap= new ArrayList<Map>();
        try {
            System.out.println("parseClientContactDetailsXml URL is: " + url);
            XMLReader xmlReader = null;
            ClientDetailsParser parser = new ClientDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(url));
            contactList = parser.getContactList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contactList;
    }

    //get clientList only
    public List<String> parseClientContactDetailsXmlOnly(String url) {
        List<String> contactListOnly = new ArrayList<String>();
        try {
            System.out.println("parseClientContactDetailsXmlOnly URL is: " + url);
            XMLReader xmlReader = null;
            ClientDetailsParser parser = new ClientDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(url));
            contactListOnly = parser.getContactListOnly();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contactListOnly;
    }

    public List<HashMap> parseTrackingDetails(String userName, String txStatus) {
        try {
            System.out.println("userName: " + userName + " txStatus: " + txStatus);
            String urlString = SingletonClass.httpServerUrl+"uName=" + userName + "&type=txnTrackingXML" + "&txStatus=" + txStatus;
            XMLReader xmlReader = null;
            TrackingDetailsParser parser = new TrackingDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(urlString));
            trackingTxList = parser.getTxList();
            System.out.println("parseTrackingDetails url is: " + urlString);
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return trackingTxList;
    }

    public Map<String, String> parseEditViewXML(String userName, String txNo) {
        try {
            System.out.println("TXEDIT URL: "+SingletonClass.httpServerUrl+"uName=" + userName + "&type=txnDetailsXML&txnNo=" + txNo);
            XMLReader xmlReader = null;
            EditViewXMLParser parser = new EditViewXMLParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);

            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&type=txnDetailsXML&txnNo=" + txNo));
            editViewTxnMap = parser.getTxnMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return editViewTxnMap;
    }


    public List<Map> parseBankQueriesXML(String userName, String txNo) {
        try {
            System.out.println(SingletonClass.httpServerUrl+"uName=" + userName + "&type=bankQueriesXML&txnNo=" + txNo);
            XMLReader xmlReader = null;
            BankQueriesParser parser = new BankQueriesParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setEntityResolver(parser);
            xmlReader.setErrorHandler(parser);

            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&type=bankQueriesXML&txnNo=" + txNo));
            queriesList = parser.getQueriesList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return queriesList;
    }

    public Map<String, String> parseEmailSForProposal(String userName, String txNo) {
        try {
            XMLReader xmlReader = null;
            ProposalInternalEmailParser parser = new ProposalInternalEmailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&for=proposal&type=emailsList&txnNo="+txNo));
            emailMap = parser.getEmailMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emailMap;
    }
    public Map<String, String> parseEmailSForOurChargesApproval(String userName, String clientName) {
        try {
            XMLReader xmlReader = null;
            EmailListParser parser = new EmailListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            System.out.println(SingletonClass.httpServerUrl+"uName=" + userName + "&for=ourChargesApproval&type=emailsList&clientName="+URLEncoder.encode(clientName));
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&for=ourChargesApproval&type=emailsList&clientName="+URLEncoder.encode(clientName)));
            emailMap = parser.getEmailMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emailMap;
    }


    public Map parseEmailSForDocs(String userName, String txNo) {
        try {
            System.out.println("send email panel URL is: "+SingletonClass.httpServerUrl+"uName=" + userName + "&for=clientApproval&type=emailsList&txnNo="+txNo );
            XMLReader xmlReader = null;
            EmailListParser parser = new EmailListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            
            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&for=clientApproval&type=emailsList&txnNo="+txNo));
            emailMapForClient = parser.getEmailMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return emailMapForClient;
    }

    public Map<String, String> parseForSLAPdfData(String userName, String txNo, String clientName) {
        try {
            XMLReader xmlReader = null;
            SLAPdfDataParser parser = new SLAPdfDataParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);

            xmlReader.parse(new InputSource(SingletonClass.httpServerUrl+"uName=" + userName + "&type=txnDetailsXML&txnNo=" + txNo + "&clientName=" + clientName));
            slaPdfDataMap = parser.getSlaPdfDataMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return slaPdfDataMap;
    }
}
