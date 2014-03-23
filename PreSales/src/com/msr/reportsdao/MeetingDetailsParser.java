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
 * @author piyush
 * @Since march 06, 2013
 * @version V1.0
 */
public class MeetingDetailsParser extends DefaultHandler{
    
    MeetingDetailBean meetingBean = null;
    List<MeetingDetailBean> meetingList = new ArrayList<MeetingDetailBean>();
    public void startElement(String uri, String localName, String qName, Attributes atts) 
    {
        if(qName.equals("meeting")){
            meetingBean =  new MeetingDetailBean();
            meetingBean.setClientName(atts.getValue("clientName"));
            meetingBean.setContactPerson(atts.getValue("contactName"));
            meetingBean.setRmName(atts.getValue("rmName"));
            meetingBean.setTime(atts.getValue("scheduleTime"));
            meetingBean.setMobileNumber(atts.getValue("mobileNo"));
            meetingBean.setType(atts.getValue("txnType"));
            meetingBean.setComments(atts.getValue("meetingComment"));
            meetingBean.setCaseSummary(atts.getValue("caseSummary"));
            meetingBean.setDate(atts.getValue("scheduleDate"));
        }
    }
    
    
    public void endElement(String uri, String name, String qName)
    {
       
        if(qName.equals("meeting")){
            meetingList.add(meetingBean);
        }
           
        
    }
    
    public List<MeetingDetailBean> getMeetingList(){
        return meetingList;
    }
}
