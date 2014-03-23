/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

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
 * @author root
 */
public class UserXMLReader {
    
    PreUserDetails usersBean =  new PreUserDetails();
    List<PreUserDetails> preUserList = new ArrayList<PreUserDetails>();
    
//    this method returns complete user details for displaying in view user details panel 
     public PreUserDetails parseUserDetails( String userName, String viewUserId) {
        try {
            System.out.println("userName: "+userName+" viewUserId: "+viewUserId);
//            http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=userDetailsXML&userEmail=rahul.ch@msronline.in 
            StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=userDetailsXML&userEmail=").append(viewUserId);
//            String urlString = "http://192.168.10.250:8080/PreSales/ServletController?uName="+userName+"&type=txnTrackingXML"+"&txStatus="+txStatus;
           
                String strBuffer = bufer.toString().replaceAll(" ", "%20");
              System.out.println(" url is "+ strBuffer);
            XMLReader xmlReader = null;
            viewUserParser parser = new viewUserParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            usersBean = parser.getUserDetailsMap();
            System.out.println("url is: "+strBuffer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usersBean;
    }
     
     public List<DocumentsDetailBean> getClientDocDataList(String userName, String clientName) {
         List<DocumentsDetailBean> docList = new ArrayList<DocumentsDetailBean>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allBankDetailsXML
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allClientDocsXML").append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(clientName);
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            DocParser parser = new DocParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            docList = parser.getdocList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return docList;
    }
     
     
//   this method returns complete user details to be display on manage user panel-----------------------  
     public List<PreUserDetails> parseUserDetailsList( String userName) {
        try {
            System.out.println("userName: "+userName);
//     http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=AllusersXML
            StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=AllusersXML");

           
                String strBuffer = bufer.toString().replaceAll(" ", "%20");
              System.out.println(" url is "+ strBuffer);
            XMLReader xmlReader = null;
            UserDetailParser parser = new UserDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            preUserList = parser.getUserList();
            System.out.println("presales parser Userlist size is "+parser.getUserList().size());
            System.out.println("url is: "+strBuffer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return preUserList;
    }
//     return complete details of a client including his company details, contact persons and client limit------
     public HashMap<String, List> extractClientDetailsXml(String userName,String compId) {
         HashMap<String, List> clientInfoMap = new HashMap<String, List>();
         
         //        http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=clientDetailsXML&clientName=piyush2000
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(compId).append("&").append(SingletonClass.operaionType).append("=clientDetailsXML");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            ClientDetailsParser parser = new ClientDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            clientInfoMap = parser.getClientInfoMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientInfoMap;
    }
     
     
     //     it return's bank's name so that they can be used in selecting bank in various locations------
     public List<String> getBankListFromXML(String userName) {
         List<String> bankList = new ArrayList<String>();
         
         //        http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=clientDetailsXML&clientName=piyush2000
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=bankList");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            BankListParser parser = new BankListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            bankList = parser.getBankList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankList;
    }
     
     
//     it return's Manager's name so that they can be used in selecting Manager assigned to the RM-------
     public HashMap<String, List<String>> getManagerListFromXML(String userName) {
         HashMap<String, List<String>> managerList = new HashMap<String, List<String>>();
         
         //        http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=managersList
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=managersList");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            ManagerListParser parser = new ManagerListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            managerList = parser.getManagerMap();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return managerList;
    }
     
     
     //     it return's RM's name so that they can be used in selecting RM assigned to the Clients-------
     public List<String> getRMListFromXML(String userName) {
         List<String> managerList = new ArrayList<String>();
         
         //        http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=managersList
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=usersList");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            ManagerListParser parser = new ManagerListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            managerList = parser.getManagerList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return managerList;
    }
     
     
//     return's  clients list to display bank in manage company panel-----------------------     
     public List<AllClientDetailBean> getClientDataList(String userName) {
         List<AllClientDetailBean> clientsInfoList = new ArrayList<AllClientDetailBean>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allClientList
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allClientList");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            AllClientXMLParser parser = new AllClientXMLParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            clientsInfoList = parser.getclientsList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientsInfoList;
    }
          
//     return's  clients list to display bank in manage company panel-----------------------     
     public List<AllClientDetailBean> getClientSearchedDataList(String userName, String searchText) {
         List<AllClientDetailBean> clientsInfoList = new ArrayList<AllClientDetailBean>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allClientList
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allClientSearchedList").append("&keyword=").append(searchText);
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            AllClientXMLParser parser = new AllClientXMLParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            clientsInfoList = parser.getclientsList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientsInfoList;
    }
     
     
//     return's banks list to display bank in manage bank panel-----------------------
     public List<AllBankDetailBean> getBankDataList(String userName) {
         List<AllBankDetailBean> banksInfoList = new ArrayList<AllBankDetailBean>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allBankDetailsXML
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allBankDetailsXML");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            AllBankDetailParser parser = new AllBankDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            banksInfoList = parser.getBanksDetailedList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return banksInfoList;
    }
          
     //     return complete details of a bank including his bank's details, contact persons and documents------
     public List<DocumentsDetailBean> getBankDocDetailsXml(String userName,String compId) {
        List<DocumentsDetailBean> bankDocList = null;
         
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.bankName).append("=").append(compId).append("&").append(SingletonClass.operaionType).append("=bankDetailsXML");

        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            BankDetailParser parser = new BankDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            bankDocList = parser.getBankDocList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankDocList;
    }
     
//     return's banks searched list to display bank in manage bank panel-----------------------
     public List<AllBankDetailBean> getBankSearchedDataList(String userName, String searchText) {
         List<AllBankDetailBean> banksInfoList = new ArrayList<AllBankDetailBean>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allBankDetailsXML
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=allBankSearchedDetailsXML").append("&keyword=").append(searchText);
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            AllBankDetailParser parser = new AllBankDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            banksInfoList = parser.getBanksDetailedList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return banksInfoList;
    }
     
     
     //     return's doc list of clients  for doc list panel panel-----------------------
     public List<DocumentsDetailBean> getDocDataList(String userName, String txId) {
         List<DocumentsDetailBean> docList = new ArrayList<DocumentsDetailBean>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allBankDetailsXML
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=clientDocsXML").append("&txnNo=").append(txId);
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            DocParser parser = new DocParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            docList = parser.getdocList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return docList;
    }
     
     
     //     return complete details of a bank including his bank's details, contact persons and documents------
     public HashMap<String, List> extractBankDetailsXml(String userName,String compId,String aliasName) {
         HashMap<String, List> bankInfoMap = new HashMap<String, List>();
         
         //        http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=clientDetailsXML&clientName=piyush2000
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.bankName).append("=").append(compId).append("&").append(SingletonClass.operaionType).append("=bankDetailsXML").append("&").append("alias").append("=").append(URLEncoder.encode(aliasName));
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            BankDetailParser parser = new BankDetailParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            bankInfoMap = parser.getBankDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bankInfoMap;
    }
     
//     this will generate Client list according to rmName-------------
     public List<String> parseClientListByRMXml(String userId,String userName) {
        List<String> clientList = new ArrayList<String>();
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userId).append("&").append(SingletonClass.rmName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=rmClientList");
         try {
             String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            NewTxParser parser = new NewTxParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            xmlReader.parse(new InputSource(strBuffer));
            clientList = parser.getClientList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientList;
    }
     
     public List<String> parseBenListXml(String userId,String clientName) {
        List<String> benList = new ArrayList<String>();
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userId).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(clientName).append("&").append(SingletonClass.operaionType).append("=beneficiaryList");
         try {
             String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            BenListParser parser = new BenListParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);           
            xmlReader.setEntityResolver(parser);
            xmlReader.parse(new InputSource(strBuffer));
            benList = parser.getBenList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return benList;
    }
     
       public List<String> getDocPathList(String userName) {
         List<String> docPathList = new ArrayList<String>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allBankDetailsXML
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=clientDirXML");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            DirectoryParser parser = new DirectoryParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            docPathList = parser.getDocPathList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return docPathList;
    }
       
        public List<String> getDocPathListForClient(String userName) {
         List<String> docPathList = new ArrayList<String>();
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allBankDetailsXML
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=clientDirXML");
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            DirectoryParser parser = new DirectoryParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            docPathList = parser.getDocPathList4Client();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return docPathList;
    }
     

     public static void main(String arg[])
     {
//         System.out.println(" users obj"+ new UserXMLReader().parseUserDetails("rahul.ch@msronline.in","rahul.ch@msronline.in"));
//         System.out.println(" users obj"+ new UserXMLReader().parseUserDetailsList("rahul.ch@msronline.in"));
         
         UserXMLReader xmlread = new UserXMLReader();
       HashMap<String, List> extractClient = xmlread.extractClientDetailsXml("rahul.ch@msronline.in", "piyush2000");
        List<ContactDetailBean> extractClient1 = extractClient.get("contactList");
         List clientlists = extractClient.get("clientList");
         System.out.println(" users obj"+extractClient1.size()+" -----  "+clientlists.size());
     }
     
     
     
     //     return's  clients comments list to display comments panel-----------------------     
     public List<commentBean> getClientCommentsList(String userName) {
         List<commentBean> commentsList = null;
         
//         http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=allClientList
        
        StringBuffer bufer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userName).append("&").append(SingletonClass.operaionType).append("=getCommentDetails").append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit);
        
        
        try {
            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            System.out.println("URL is: "+strBuffer);
            XMLReader xmlReader = null;
            CommentsParser parser = new CommentsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(parser);
            xmlReader.setErrorHandler(parser);
            xmlReader.parse(new InputSource(strBuffer));
            commentsList = parser.getCommentsList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return commentsList;
    }
}
