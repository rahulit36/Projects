/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parser;

import com.msr.ftp.bean.MeetingsDetails;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @author Satya
 * @version V1.0
 * @since V1.0
 * @details read meeting XML doc
 */
public class MeetingDetailsParser extends DefaultHandler {
   
    List<MeetingsDetails> meetingDatalist =null;
    List<String> contactlist=null;
    MeetingsDetails meetingsDetails=null;

    public List<MeetingsDetails> MeetingDataList() {
        return meetingDatalist;
    }
    //list of contact
     public List<String> ContactDataList() {
        return contactlist;
    }
    
   public void startDocument()
   {	
       meetingDatalist=new ArrayList<MeetingsDetails>();
       contactlist=new ArrayList<String>();
      
   }
//reading start tag of SML docs
    public void startElement(String uri, String localName, String qName, Attributes atts) 
    {

        if (qName.equals("meeting")) {
             
            meetingsDetails =  new MeetingsDetails();
            meetingsDetails.setScheduleDate(atts.getValue("scheduleDate"));
            meetingsDetails.setScheduleTime(atts.getValue("scheduleTime"));
            meetingsDetails.setClientName( atts.getValue("clientName"));
            meetingsDetails.setMeetingComment( atts.getValue("meetingComment"));
            if(atts.getValue("caseSummry").equals("null")){
            meetingsDetails.setCaseSummry("");
                
            }else{
                
            meetingsDetails.setCaseSummry(atts.getValue("caseSummry"));
            }
            meetingsDetails.setPlace( atts.getValue("place"));
            meetingsDetails.setTurnOver( atts.getValue("turnOver"));
            meetingsDetails.setConDesgnation( atts.getValue("conDesignation"));
            meetingsDetails.setRegion(atts.getValue("region"));
            meetingsDetails.setRemienderTime(atts.getValue("remienderTime"));            
            meetingsDetails.setContactPerson(atts.getValue("contactName"));     
            meetingsDetails.setMeetingId(atts.getValue("meetingId"));  
            System.out.println("meetingsDetails: " + meetingsDetails);
        }
         if (qName.equals("contact"))
         {
             contactlist.add(atts.getValue("name"));             
         }
    }
    
    
    public void endElement(String uri, String name, String qName)
    {
       
        if(qName.equals("meeting"))        
            meetingDatalist.add(meetingsDetails);
        
    }
    
    
	    
}
