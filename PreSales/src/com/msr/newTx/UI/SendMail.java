package com.msr.newTx.UI;

import com.msr.util.SingletonClass;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author piyush chawla
 * @version V1.0
 * @since feb01,2013 this class sends mail to the RM's heads ----
 */
public class SendMail {

    public boolean sendMail(ArrayList toEmailIdList, ArrayList ccEmailIdList, ArrayList bccEmailIdList, String messageText, String subjectText, String rmMailId) {
        boolean flag = false;
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.investeurs.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
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
                System.out.println("email address is " + toAddresses[i]);
            }

//                InternetAddress[] toAddresses = new InternetAddress[1];
//                    toAddresses[0] = new InternetAddress("rahulit36@gmail.com");
//                    System.out.println("Receipnt is: rahulit36@gmail.com");
//            message.setRecipients(Message.RecipientType.TO, toAddresses);
//	            adding cc to mail list-----------
            if (ccEmailIdList.size() > 0) {
                InternetAddress[] ccAddresses = new InternetAddress[ccEmailIdList.size()];
                for (int i = 0; i < ccEmailIdList.size(); i++) {
                    ccAddresses[i] = new InternetAddress((ccEmailIdList.get(i).toString()));
                    System.out.println("email address is " + ccAddresses[i]);
                }
//
                message.setRecipients(Message.RecipientType.CC, ccAddresses);

            }
//	            adding bcc to mail list-----------
            if (bccEmailIdList.size() > 0) {
                InternetAddress[] bccAddresses = new InternetAddress[bccEmailIdList.size()];
                for (int i = 0; i < bccEmailIdList.size(); i++) {
                    bccAddresses[i] = new InternetAddress((bccEmailIdList.get(i).toString()));
                    System.out.println("email address is " + bccAddresses[i]);
                }

                message.setRecipients(Message.RecipientType.BCC, bccAddresses);
            }


            Multipart multipart = new MimeMultipart();
                  
            message.setFrom(new InternetAddress("support@investeurs.com", "rahul.ch@msronline.in"));
            message.setSubject(subjectText);
            message.setContent(multipart);
            
          MimeBodyPart  messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(messageText, "text/html");
            multipart.addBodyPart(messageBodyPart);
            
            
            MimeBodyPart imgPart = new MimeBodyPart();
            String imgStr ="/root/investeurs_logo.png";
            System.out.println("    url to accces image is "+imgStr);
            DataSource ds = new FileDataSource(imgStr);
            imgPart.setDataHandler(new DataHandler(ds));
            imgPart.setHeader("Content-ID", "<imgPart>");
            multipart.addBodyPart(imgPart);

            Transport.send(message);
            flag = true;


        } catch (Exception e) {
            flag = false;
            System.out.println("Exception occur to send mail to user " + e);


            // TODO: handle exception
        }

        return flag;
    }

//    public boolean sendMail(ArrayList toEmailIdList, ArrayList ccEmailIdList, ArrayList bccEmailIdList, List<String> fileList, String messageText, String subjectText, String rmEmail) {
//
//        boolean flag = false;
//
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "mail.investeurs.com");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "25");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.socketFactory.port", "22");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("support@investeurs.com", "123456");
//            }
//        });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
////        	adding to list in mail list-----------
//            if (toEmailIdList != null) {
//                InternetAddress[] toAddresses = new InternetAddress[toEmailIdList.size()];
//                for (int i = 0; i < toEmailIdList.size(); i++) {
//                    toAddresses[i] = new InternetAddress((toEmailIdList.get(i).toString()));
//                    System.out.println("email address is " + toAddresses[i]);
//                }
//                message.setRecipients(Message.RecipientType.TO, toAddresses);
//            }
//
//
////	            adding cc to mail list-----------
//            if (ccEmailIdList != null) {
//                if (ccEmailIdList.size() > 0) {
//                    InternetAddress[] ccAddresses = new InternetAddress[ccEmailIdList.size()];
//                    for (int i = 0; i < ccEmailIdList.size(); i++) {
//                        ccAddresses[i] = new InternetAddress((ccEmailIdList.get(i).toString()));
//                        System.out.println("email address is " + ccAddresses[i]);
//                    }
//
//                    message.setRecipients(Message.RecipientType.CC, ccAddresses);
//                }
//            }
////	            adding bcc to mail list-----------
//            if (bccEmailIdList != null) {
//                if (bccEmailIdList.size() > 0) {
//                    InternetAddress[] bccAddresses = new InternetAddress[bccEmailIdList.size()];
//                    for (int i = 0; i < bccEmailIdList.size(); i++) {
//                        bccAddresses[i] = new InternetAddress((bccEmailIdList.get(i).toString()));
//                        System.out.println("email address is " + bccAddresses[i]);
//                    }
//
//                    message.setRecipients(Message.RecipientType.BCC, bccAddresses);
//                }
//            }
//
//
//
//            message.setFrom(new InternetAddress("support@investeurs.com", rmEmail));
//            message.setSubject(subjectText);
//            message.setText(messageText);
//            BodyPart messageBodyPart = null;
//            Multipart multipart = new MimeMultipart();
//            DataSource source = null;
//            for (Object fileName : fileList.toArray()) {
//                try {
//                    File file = new File(fileName.toString());
//                    if (file.exists()) {
//                        messageBodyPart = new MimeBodyPart();
//                        source = new FileDataSource(fileName.toString());
//                        messageBodyPart.setDataHandler(new DataHandler(source));
//                        messageBodyPart.setFileName(file.getName());
//                        multipart.addBodyPart(messageBodyPart);
//                        System.out.println("fileName-----------" + fileName.toString());
//                    }
//                } catch (Exception ex) {
//                }
//            }
//
//            message.setContent(multipart);
//            messageBodyPart = new MimeBodyPart();
//
//            messageBodyPart.setContent(messageText, "text/html");
//            multipart.addBodyPart(messageBodyPart);
//
//            MimeBodyPart imgPart = new MimeBodyPart();
//            InputStream in = getClass().getResourceAsStream("adv_btm.jpg");
////            String imgStr ="c:\\\\images\\investeurs_logo.png";
//            String imgStr ="/root/investeurs_logo.png";
//            System.out.println("    url to accces image is "+imgStr);
//            DataSource ds = new FileDataSource(imgStr);
//            imgPart.setDataHandler(new DataHandler(ds));
//            imgPart.setHeader("Content-ID", "<imgPart>");
//            multipart.addBodyPart(imgPart);
//            
//            Transport.send(message);
//            flag = true;
//
//
//        } catch (Exception e) {
//            flag = false;
//            e.printStackTrace();
//
//
//            // TODO: handle exception
//        }
//
//        return flag;
//
//    }
    public boolean sendMail(ArrayList toEmailIdList, ArrayList ccEmailIdList, ArrayList bccEmailIdList, List<String> fileList, String messageText, String subjectText, String rmEmail) {

        boolean flag = false;


        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.investeurs.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
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
//            if (toEmailIdList != null) {
//                InternetAddress[] toAddresses = new InternetAddress[1];
////                for (int i = 0; i < toEmailIdList.size(); i++) {
//                    toAddresses[0] = new InternetAddress("rahul.ch@msronline.in");
////                    System.out.println("email address is " + toAddresses[i]);
////                }
//                message.setRecipients(Message.RecipientType.TO, toAddresses);
//            }


                InternetAddress[] toAddresses = new InternetAddress[1];
                    toAddresses[0] = new InternetAddress("rahul.ch@msronline.in");
                    
            message.setFrom(new InternetAddress("support@investeurs.com", "rahul.ch@msronline.in"));
            message.setSubject(subjectText);
            message.setText(messageText);
            BodyPart messageBodyPart = null;
            Multipart multipart = new MimeMultipart();
            DataSource source = null;
            if(fileList!=null){
            for (Object fileName : fileList.toArray()) {
                try {
                    File file = new File(fileName.toString());
                    if (file.exists()) {
                        messageBodyPart = new MimeBodyPart();
                        source = new FileDataSource(fileName.toString());
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(file.getName());
                        multipart.addBodyPart(messageBodyPart);
                        System.out.println("fileName-----------" + fileName.toString());
                    }
                } catch (Exception ex) {
                }
            }
            }
            message.setContent(multipart);
            messageBodyPart = new MimeBodyPart();

            messageBodyPart.setContent(messageText, "text/html");
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart imgPart = new MimeBodyPart();
            InputStream in = getClass().getResourceAsStream("adv_btm.jpg");
//            String imgStr ="c:\\\\images\\investeurs_logo.png";
            String imgStr ="/root/investeurs_logo.png";
            System.out.println("    url to accces image is "+imgStr);
            DataSource ds = new FileDataSource(imgStr);
            imgPart.setDataHandler(new DataHandler(ds));
            imgPart.setHeader("Content-ID", "<imgPart>");
            multipart.addBodyPart(imgPart);
            
            Transport.send(message);
            flag = true;


        } catch (Exception e) {
            flag = false;
            e.printStackTrace();


            // TODO: handle exception
        }

        return flag;

    }

    public static void main(String[] args) {
        String recieverId = "piyush.c@msronline.in";
//		boolean status = new SendMail();
//		if(status){
//			System.out.println("mail sent");
//			
//		}
    }
}
