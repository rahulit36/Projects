/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.newTx.dao.MeetingDetailBean;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author root
 */
public class EmailParser extends DefaultHandler {

    MeetingDetailBean meetingDetailBean = null;

    public EmailParser(MeetingDetailBean MeetingDetailBeanTemp) {
        this.meetingDetailBean = MeetingDetailBeanTemp;
    }

    public MeetingDetailBean getmeetingDetails() {
        return meetingDetailBean;
    }

    public void startElement(String uri, String localName, String tagName, Attributes atts) {
        System.out.println("tagName: " + tagName);
        if (tagName.equals("email")) {
            //  meetingDetailBean=new MeetingDetailBean();
            String client = atts.getValue("client");
            String cc = atts.getValue("cc");
            String bcc = atts.getValue("bcc");
            String turnover = atts.getValue("turnover");
            String rmName = atts.getValue("rmName");
            String to = atts.getValue("to");
            String mobileNo = atts.getValue("mob");
            //  String mobileNo=atts.getValue("mob");
            meetingDetailBean.setRmDisName(rmName);
            meetingDetailBean.setCc(cc);
            meetingDetailBean.setTo(to);
            meetingDetailBean.setBcc(bcc);
            meetingDetailBean.setTurnOver(turnover);
            meetingDetailBean.setMobileNo(mobileNo);
            meetingDetailBean.setCompanyName(client);


        }
    }
}
