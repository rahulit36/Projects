/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.tools.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.newTx.UI.SendMail;
import com.msr.newTx.UI.SendMeetingMail;
import com.msr.newTx.dao.MeetingDetailBean;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class ThreadUtil extends Thread {

    boolean flag = false;
    ArrayList toEmailIdListTemp;
    ArrayList ccEmailIdListTemp;
    ArrayList bccEmailIdListTemp;
    String messageTextTemp;
    String subjectTextTemp;
    JFrame emailScreen1;
    String isMeeting = "";

    public ThreadUtil(ArrayList toEmailIdList, ArrayList ccEmailIdList, ArrayList bccEmailIdList, 
            String subjectText, String isMeeting, String bodyText, JFrame emailScreen1) {

        this.toEmailIdListTemp = toEmailIdList;
        this.ccEmailIdListTemp = ccEmailIdList;
        this.bccEmailIdListTemp = bccEmailIdList;

        this.messageTextTemp = bodyText;
        this.subjectTextTemp = subjectText;
        this.isMeeting = isMeeting;
        this.emailScreen1 = emailScreen1;

    }

    public void run() {
        SendMail sendMail = new SendMail();
        DownloadUploadFile downloadUploadFile = new DownloadUploadFile();
        try {
            flag = sendMail.sendMail(toEmailIdListTemp, ccEmailIdListTemp, bccEmailIdListTemp, messageTextTemp,subjectTextTemp, "");
            if (flag && !isMeeting.equals("isMeeting")) {
                new callServerUrl().urlProcessiong(SingletonClass.approvalURL);
                
            } else {
//                JOptionPane.showMessageDialog(emailScreen1, "Error In Sending Mail. Please Contact Your Network Provider");
            }

                emailScreen1.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            downloadUploadFile = null;
            sendMail = null;
        }
    }
}
