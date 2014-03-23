package com.msr.newTx.UI;

import com.msr.newTx.dao.MeetingDetailBean;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * 
 * @author piyush chawla
 * @version V1.0
 * @since feb01,2013
 *this class sends mail to the RM's heads ----
 */

public class SendMeetingMail {

	public boolean sendMail(ArrayList toEmailIdList,ArrayList ccEmailIdList,ArrayList bccEmailIdList,String subjectText, String bodyText){
			boolean flag = false;
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.investeurs.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
       props.put("mail.smtp.starttls.enable","true");
       props.put("mail.smtp.socketFactory.port", "22");
       props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("support@investeurs.com", "123456");
                    }
                });
        
        try {
        	MimeMessage message = new MimeMessage(session);
//        	adding to list in mail list-----------
	            InternetAddress[] toAddresses = new InternetAddress[toEmailIdList.size()];
	            for (int i = 0; i < toEmailIdList.size(); i++) {
	            	toAddresses[i] = new InternetAddress((toEmailIdList.get(i).toString()));
	                System.out.println("email address is "+toAddresses[i]);
	            }
	            
	            message.setRecipients(Message.RecipientType.TO, toAddresses);
//	            adding cc to mail list-----------
	            if(ccEmailIdList.size()>0){
	                	InternetAddress[] ccAddresses = new InternetAddress[ccEmailIdList.size()];
	    	            for (int i = 0; i < ccEmailIdList.size(); i++) {
	    	            	ccAddresses[i] = new InternetAddress((ccEmailIdList.get(i).toString()));
	    	                System.out.println("email address is "+ccAddresses[i]);
	                }
	    	            
	    	            message.setRecipients(Message.RecipientType.CC, ccAddresses);
	                
	            }
//	            adding bcc to mail list-----------
	            if(bccEmailIdList.size()>0){
	                	InternetAddress[] bccAddresses = new InternetAddress[bccEmailIdList.size()];
	    	            for (int i = 0; i < bccEmailIdList.size(); i++) {
	    	            	bccAddresses[i] = new InternetAddress((bccEmailIdList.get(i).toString()));
	    	                System.out.println("email address is "+bccAddresses[i]);
	                }
	    	            
	    	            message.setRecipients(Message.RecipientType.BCC, bccAddresses);
	            }
	            
        	
        	
            message.setFrom(new InternetAddress("support@investeurs.com","Investeurs_Support"));
            message.setSubject(subjectText);
            message.setContent(bodyText, "text/html");
            Transport.send(message);
            flag = true;
            
        	
		} catch (Exception e) {
			flag = false;
            System.out.println("Exception occur to send mail to user " + e);
           
			
			// TODO: handle exception
		}
		
		return flag;
	}
	

	public static void main(String[] args){
		
                ArrayList toEmailIdList = new ArrayList();
                toEmailIdList.add("piyush.c@msronline.in");
                ArrayList ccEmailIdList = new ArrayList();
                ArrayList bccEmailIdList = new ArrayList();
                String messageText ="" ;
                String subjectText ="aaaa";
                MeetingDetailBean meetingBeantemp = new MeetingDetailBean();
                meetingBeantemp.setCompanyName("aa");
                meetingBeantemp.setContactPerson("dsf");
                meetingBeantemp.setDateTime("fd");
                meetingBeantemp.setMeetingPlace("delhi");
                meetingBeantemp.setMobNumber("98989898");
                meetingBeantemp.setPurpose("dsfs");
                meetingBeantemp.setTurnOver("gfh");
                new SendMeetingMail().sendMail(toEmailIdList, ccEmailIdList, bccEmailIdList,  subjectText, "Dear Sir,\n\nI have scheduled a meeting with my client and details are below");
//		boolean status = new SendMeetingMail();
//		if(status){
//			System.out.println("mail sent");
//			
//		}
	}
}
