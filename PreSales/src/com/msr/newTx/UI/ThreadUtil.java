/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.newTx.dao.MeetingDetailBean;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class ThreadUtil extends Thread
{
    boolean flag = false;
    ArrayList toEmailIdListTemp;
    ArrayList ccEmailIdListTemp;
    ArrayList bccEmailIdListTemp;
    
    String messageTextTemp;
    String subjectTextTemp;
    private String txId = null;
    
    
 public ThreadUtil(ArrayList toEmailIdList,ArrayList ccEmailIdList,ArrayList bccEmailIdList, String subjectText,String txId, String bodyText)
  {
    
        this.toEmailIdListTemp=toEmailIdList;
        this.ccEmailIdListTemp = ccEmailIdList;
        this.bccEmailIdListTemp = bccEmailIdList;
       
        this.messageTextTemp = bodyText;
        this.subjectTextTemp = subjectText;
        this.txId = txId;
        
 }
    
    public void run()
    {
        SendMeetingMail sendMail=new SendMeetingMail();
          DownloadUploadFile downloadUploadFile=new DownloadUploadFile();
        try{    
                      flag = sendMail.sendMail(toEmailIdListTemp, ccEmailIdListTemp, ccEmailIdListTemp, subjectTextTemp,messageTextTemp);
           
        
        }catch(Exception ex){
                   ex.printStackTrace();
               }finally{
            downloadUploadFile=null;
            sendMail=null;
        }
    }
      
    }
    
