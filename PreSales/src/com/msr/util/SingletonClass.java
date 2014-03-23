/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.util;

//import static com.msr.util.SingletonClass.serverIpPort;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author rahul
 * @version V1.0
 * @since V1.0
 */
public class SingletonClass {

//    public static String httpServerUrl = "http://180.151.100.54:80/PreSales/ServletController?";
//    public static String httpServerUrl = "http://192.168.10.250:8080/PreSales/ServletController?";
    public static String httpServerUrl = "http://localhost:8080/PreSales/ServletController?";
    public static String userName = "uName";
    public static String password = "uPass";
    public static String operaionType = "type";
    public static String txNo = "txnNo";
    public static String txStatus = "txStatus";
    public static String forMail = "for";
    public static boolean isEdit = false;
    public static boolean isView = false;
    public static boolean isFirstAcc = false;
    public static String txIdEdit = "";
    public static String expBusiness = "";
    public static String txStatusSelect;
    public static String txTypeEdit;
    // Transaction Type of new transaction
    public static String txType = "txType";
    public static String clientIsBenOrApp = "clientName";
    public static String limitFor = "limitFor";
    // applicant details from new transaction client details
    public static String appNmae = "appName";
    public static String appAddrs = "address";
    public static String appPhn = "landline";
    public static String appRegion = "region";
    public static String appFax = "faxNo";
    public static String appTurnOver = "turnOver";
    public static String appCategory = "category";
    public static String limitType = "limitType";
    
    public static String limitAmount = "limitAmount";
    public static String accYear = "accYear";
    public static String exBusiness = "exBusiness";
    public static String compSpace = "compSpace";
    public static String solution = "solution";
    public static String trade = "trade";
    public static String industry = "industry";
    public static String yearOfIncorp = "yearOfIncorp";
    public static String extCreditRating = "extCreditRating";
    // beneficiary  details from new transaction client details   
    public static String benNmae = "benName";
    public static String benAddrs = "benAddrs";
    public static String benPhn = "benPhn";
    public static String benRegion = "region";
    public static String benFax = "benFax";
    public static String benTurnOver = "benTurnOver";
    public static String benCategory = "bencategory";
    public static String benLimitAmount = "benlimitAmount";
    public static String benAccYear = "benAccYear";
// client contact details from new transaction client contacts table
    public static String clientContactName = "ccontName";
    public static String clientContactDesignation = "ccontactDesignation";
    public static String clientContactMobile = "ccontactMobile";
    public static String clientContactEmail = "ccontactEmail";
    public static String notifyStr = "notifyStr";
// meeting details from new transaction client contacts table
    public static String contactPerson = "contactPerson";
    public static String meetingClientName = "";
    public static String scheduleTime = "scheduleTime";
    public static String meetingComment = "meetingComment";
    public static String caseSummry = "caseSummry";
    public static String place = "place";
    public static String region = "region";
    public static String remienderTime = "remienderTime";
    public static String meetingId = "meetingId";
    public static String meetingOf = "meetingOf";
    // static fields are using
    public static String txnIdentity;
    public static String TxnClientName;
    public static String clientCompanyName;
    public static String clientBenName;
    public static String clientRegion;
    public static String clientCompSpace;
    public static String clientSolution;
    public static String ourChargesAutoFill;
    public static JComboBox contactPersons = new JComboBox();
    //code add by satya
    public static String meetingDetailsType = "meetingDetailsXML";
    public static String toolsMeetingDetailsXML = "toolsMeetingDetailsXML";
    public static String meetingMailSubject = "Pre-Meeting Information";
    public static String meetingReminderMailSubject = "Meeting Reminder with ";
    // static fields if any save button cliecked of new transaction accordion.
    public static boolean isSave = false;
    public static String nextOpenAccordion = null;
    //
   
    // static field for email reminder
//    public static String serverIpPort = "http://180.151.100.54:80/";
    
    
    public static String serverIpPort = "http://192.168.10.250:8080/";
     public static String approvalURL =serverIpPort+"PreSalesApprovalAcceptor/ActivateTriggerServlet";
    // static fields for tools section
     
     
//    ftp connection details---------------------------------
//    public static String ipAddress = "180.151.100.54";
//    public static String ftpUserName = "client";
//    public static String ftpPassWord = "client123";
//    public static String port = "2304";
    
    
//    ftp connection details---------------------------------
    public static String ipAddress = "192.168.10.251";
    public static String ftpUserName = "ftp_user";
    public static String ftpPassWord = "ftp2013";
    public static String port = "21";
    
    
    // bank's contact details from manage bank contacts table
    public static String bankContactName = "bcontName";
    public static String bankContactDesignation = "bcontactDesignation";
    public static String bankContactMobile = "bcontactMobile";
    public static String bankContactEmail = "bcontactEmail";
//    beneficiary=piyush&bankName=abc%20bank&clntlimit=18888&avlLimit=894756
    public static String beneficiary = "beneficiary";
    public static String bankName = "bankName";
    public static String clntlimit = "clntlimit";
    public static String avlLimit = "avlLimit";
//    public static String sysFilePath = "/root/";
    
    public static String sysFilePath = "d:\\";
    public static String docsName = "docsName";
    public static String newDocName = "newDocName";
    public static String docsPath = "docsPath";
    public static String docsType = "docsType";
    
    // p3 docs path
    public static String serverBankDocPath = "/home/client/banks/";
    public static String serverClientDocPath = "/home/client/clientDocs/";
    public static String serverEmailDocPath = "/home/client/emails/";
    
    
// investeurs docs path
//     public static String serverBankDocPath = "/pre_sales/client/banks/";
//    public static String serverClientDocPath = "/pre_sales/client/clientDocs/";
//    public static String serverEmailDocPath = "/pre_sales/client/emails/";
    public static String rmName = "rmName";
    public static JTable bankQueriesTable =null;
    //login variables  
    public static String privStr;
    public static String designation;
    public static String uNameEmail;
    public static String empDesignation;
    public static String mobNo;
    public static String uPassword;
    public static String loginUserName;
    
    // clientadditional info url parameters
//    http://180.151.100.54:8080/PreSales/ServletController?type=saveAdditionalInfo&clientName=clientName&constitution=constitution&
//dateOfIncorporation=dateOfIncorporation&headAddrs=headAddrs&branchaddrs=branchaddrs&
//principleActivity=principleActivity&promoters=promoters&creditRating=creditRating&background=background&
//        prodNapps=prodNapps&suppliers=suppliers&clientele=clientele&manufactSetup=manufactSetup&mgntDetails=mgntDetails&
//        indstOverview=indstOverview&financialPerf=financialPerf&bankArrange=bankArrange
    public static String constitution = "constitution";
    public static String dateOfIncorporation = "dateOfIncorporation";
    public static String headAddrs = "headAddrs";
    public static String branchaddrs = "branchaddrs";
    public static String principleActivity = "principleActivity";
    public static String promoters = "promoters";
    public static String creditRating = "creditRating";
    public static String background = "background";
    public static String prodNapps = "prodNapps";
    public static String suppliers = "suppliers";
    public static String clientele = "clientele";
    public static String manufactSetup = "manufactSetup";
    public static String mgntDetails = "mgntDetails";
    public static String indstOverview = "indstOverview";
    public static String financialPerf = "financialPerf";
    public static String bankArrange = "bankArrange";
    public static String balSheetEnding = "balSheetEnding";
    // proposal details parameter
    public static String hndlCharges = "hndlCharges";
    public static String chargeType = "chargeType";
    public static String amount = "amount";
    public static String actualLimit = "actualLimit";
    public static String sanctionDate = "sanctionDate";
    public static String usance = "usance";
    public static String interestRate = "interestRate";
    public static String margin = "margin";
    public static String ourCharges = "ourCharges";
    public static String subjToMinAmnt = "subjToMinAmnt";
    public static String lcStatus = "lcStatus";
    public static String proposalNo = "proposalNo";

    // bank queries parameter
    
    public static String queryStatus = "queryStatus";
    public static String queryText = "queryText";
    public static String raisedBy = "raisedBy";
    public static String closedBy = "closedBy";
    public static String closedOn = "closedOn";
    public static String queryNo = "queryNo";
    public static String queryComments = "queryComments";
    
    //meeting dupliacte check parametr
    
    public static String meetingWarn = "meetingWarn";

    
    public static String toDate = "toDate";
    public static String fromDate = "fromDate";
    public static String status = "status";
    public static String bank = "bank";
    public static String category = "category";
    public static String client = "client";
    public static String trnxType = "trnxType";
    public static String clientDir = "clientDir";    
    public static String serverUploadLocation="/root/CRMData";    
//    public static String serverUploadLocation = "d:\\";

    public static String replaceEmptySpace(String text) {

        if (text == null || ("null").equals(text)) {
            return "";
        } else {
            return text;
        }
    }
}