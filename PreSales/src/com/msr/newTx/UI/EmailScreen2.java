/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

/**
 *
 * @author piyush chawla
 * @version V1.0
 * @since feb 04, 2013
 *
 * this class is created to send mail i.e with attachments or without
 * attachments.......
 */
public class EmailScreen2 extends javax.swing.JFrame {

    private String emailbodyText = null;
    private ArrayList<String> toEmailList = null;
    private ArrayList<String> ccEmailList = null;
    private ArrayList<String> bccEmailList = null;
    private String subjectTxt = "";
    private List<String> filepathList = null;
    private String txId = null;
    public String meetingSaveUrl = "";
    NewTxPanel newTxPanel = null;
    JTabbedPane tabbedPane = null;
    String userId = "";
    boolean isPreSla = false;
    boolean isSLA = false;
    boolean isDocs = false;

    /**
     * Creates new form EmailScreen1
     */
//    public EmailScreen1() {
////      shows recievers list in to fileds......
//      toEmailList = new ArrayList<String>();
//      toEmailList.add("piyushchawla1@in.com");
//      toEmailList.add("rahul.ch@msronline.in");
//      toEmailList.add("satyendea");
//      toEmailList.add("piyushchawla1@in.com");
//      toEmailList.add("rahul.ch@msronline.in");
//      toEmailList.add("satyendea");
//        
//
////      shows recievers list of CC fields......
//      ccEmailList = new ArrayList<String>();
//      ccEmailList.add("piyushchawla1@in.com");
//      ccEmailList.add("rahul.ch@msronline.in");
//      ccEmailList.add("satyendea");
//      ccEmailList.add("piyushchawla1@in.com");
//      ccEmailList.add("rahul.ch@msronline.in");
//      ccEmailList.add("satyendea");
//        
//
////      shows recievers list of BCC fileds......
//      bccEmailList = new ArrayList<String>();
//      bccEmailList.add("piyushchawla1@in.com");
//      bccEmailList.add("rahul.ch@msronline.in");
//      bccEmailList.add("satyendea");
//      bccEmailList.add("piyushchawla1@in.com");
//      bccEmailList.add("rahul.ch@msronline.in");
//      bccEmailList.add("satyendea");
//        initComponents();
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//    }
//    
//    public EmailScreen1(String subjectStr, String messageStr, ArrayList<String> toArrayList,ArrayList<String> ccArrayList, ArrayList<String> bccArrayList ) {
//    	this.emailbodyText = messageStr;
//    	this.toEmailList = toArrayList;
//    	this.ccEmailList = ccArrayList;
//    	this.bccEmailList = bccArrayList;
//    	
//    	initComponents();
//    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//    }
//    
    public EmailScreen2(String txId, String subjectStr, NewTxPanel newTxPanel, String userId, List<String> slaPdfFilePathList, Map emailMap, List<String> toPersonsList, boolean isPreSla, boolean isSLA, boolean isDocs, JTabbedPane tabbedPane) {

        try {
            // set the icon of login frame
            setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));

        } catch (Exception ex) {
            Logger.getLogger(EmailScreen1.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txId = txId;
        this.meetingSaveUrl = meetingSaveUrl;
        this.newTxPanel = newTxPanel;
        this.tabbedPane = tabbedPane;
        this.userId = userId;
//        this.isIlcDocAll = isIlcDocAll;
//        this.isFlcDocAll = isFlcDocAll;
        this.isPreSla = isPreSla;
        this.isSLA = isSLA;
        this.isDocs = isDocs;

//        StringBuffer emailListUrl = new StringBuffer();
//        emailListUrl.append(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userId).append("&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.operaionType).append("=").append("emailsList").append("&").append(SingletonClass.forMail).append("=").append("meeting");
//        String urlResponse = new callServerUrl().urlProcessiong(emailListUrl.toString().replaceAll(" ", "%20"));
//        System.out.println("urlResponse:  " + urlResponse);

        if (toPersonsList != null && toPersonsList.size() > 0) {
            toEmailList = new ArrayList<String>();
            for (String emailId : toPersonsList) {
                if (emailId != null && emailId.length() > 0) {
                    toEmailList.add(emailId);
                }
            }
        }

        if (emailMap.get("cc") != null) {
            String[] ccArray = emailMap.get("cc").toString().split(",");
            System.out.println("cc array is " + ccArray);
            ccEmailList = new ArrayList<String>();
            for (String emailId : ccArray) {
                if (emailId != null && emailId.length() > 0) {
                    ccEmailList.add(emailId);
                }
            }
        }

        if (emailMap.get("bcc") != null) {
            String[] bccArray = emailMap.get("bcc").toString().split(",");
            System.out.println("bcc array is " + bccArray);
            bccEmailList = new ArrayList<String>();

            for (String emailId : bccArray) {
                if (emailId != null && emailId.length() > 0) {
                    bccEmailList.add(emailId);
                }
            }
        }

//        emailbodyText = urlResponse.substring(urlResponse.lastIndexOf(";emailbody:") + 11, urlResponse.length());
        emailbodyText = "hi..";
        this.subjectTxt = subjectStr;


        filepathList = slaPdfFilePathList;
//        filepathList.add("/root/Desktop/netCORE_API_SMS_Services_1.18.pdf");



        initComponents();
        subjectTextField.setText(subjectTxt);
        if (isPreSla && isSLA && isDocs) {
            bodyTextArea.setText("Please find the Service Letter Agreement and document list as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards,\n"  
                    + "\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
//                    + SingletonClass.loginUserName
////                    + "\nMob: " + SingletonClass.uNameEmail
//                    + "\nEmail: " + SingletonClass.uNameEmail);
        } else if (isPreSla && isDocs ) {
            bodyTextArea.setText("Please find the Preliminary Offer Letter and document list as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards,\n"  
                    + "\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
        }  else if (isSLA && isDocs ) {
            bodyTextArea.setText("Please find the Service Letter Agreement and document list as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards,\n"  
                    + "\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
        }  else if (isPreSla && isSLA ) {
            bodyTextArea.setText("Please find the Preliminary Offer Letter as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards,\n"  
                    + "\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
        } else if (isPreSla) {
            bodyTextArea.setText("Please find the Preliminary Offer Letter as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards,\n"  
                    + "\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
        }else if (isSLA) {
            bodyTextArea.setText("Please find the Service Letter Agreement as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards," 
                    
                    + "\n\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
        }else if (isDocs) {
            bodyTextArea.setText("Please find the document list as per our discussion. If you have any questions, please feel free to contact me."
                    + "\n\nRegards,\n"  
                    + "\n\nCorporate Off:S-26,27, 28 3rd Floor, Veera Tower Green Park Ext., New Delhi-110016"
                    + "\nOperational Off:8th Floor 818, Indrapraksh Building, Barakhamba Road,New Delhi-110001"
                    + "\nVoice: +91-2652 2441/42 I Fax: +91-2651 2226"
                    + "\nwww.investeurs.com");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outerMostPanel = new javax.swing.JPanel();
        emailPanel = new javax.swing.JPanel();
        toLabel = new javax.swing.JLabel();
        ccLabel = new javax.swing.JLabel();
        bccLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        toIdPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ccIdPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bccIdPanel = new javax.swing.JPanel();
        subjectPanel = new javax.swing.JPanel();
        subjectLabel = new javax.swing.JLabel();
        subjectTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        bodyTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        attachmentPanel = new javax.swing.JPanel();
        attachmentLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        sirMadam = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        outerMostPanel.setBackground(new java.awt.Color(12, 60, 111));

        toLabel.setText("To: ");

        ccLabel.setText("cc:");

        bccLabel.setText("Bcc:");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        toIdPanel.setBackground(new java.awt.Color(12, 60, 111));
        if(toEmailList != null){
            for(int i = 0;i<toEmailList.size();i++){
                spaceLabel = new javax.swing.JLabel();
                toIdLabel = new javax.swing.JLabel();
                toDelButton = new javax.swing.JButton();
                toIdLabel.setText( toEmailList.get(i));
                spaceLabel.setText("  ");
                //      	deleteButton.setText("Del"+i);
                //      	toDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
                //      	toDelButton.setBackground(new java.awt.Color(184, 212, 239));
                //      	toDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
                //      	toDelButton.setForeground(new java.awt.Color(12, 60, 111));
                toDelButton.setText("X");
                toDelButton.setBackground(new java.awt.Color(12,60,111));
                toDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                toDelButton.setForeground(new java.awt.Color(255,0,0));
                toDelButton.setBorder(BorderFactory.createEmptyBorder());
                //      	deleteButton.setText("Close");
                toDelButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        toDelActionPerformed(evt);
                    }
                });
                toIdLabel.setForeground(new java.awt.Color(255,255,255));
                toIdLabel.setName("labelDel"+i);
                toDelButton.setName(""+i);
                toIdPanel.add(toIdLabel);
                toIdPanel.add(toDelButton);
                toIdPanel.add(spaceLabel);
            }
        }
        toIdPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        jScrollPane1.setViewportView(toIdPanel);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ccIdPanel.setBackground(new java.awt.Color(12, 60, 111));
        if(ccEmailList != null){
            for(int i = 0;i<ccEmailList.size();i++){
                spaceLabel = new javax.swing.JLabel();
                ccIdLabel = new javax.swing.JLabel();
                ccDelButton = new javax.swing.JButton();
                ccIdLabel.setText( ccEmailList.get(i));
                spaceLabel.setText("  ");
                //    	deleteButton.setText("Del"+i);
                //    	ccDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
                //    	ccDelButton.setBackground(new java.awt.Color(184, 212, 239));
                //    	ccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
                //    	ccDelButton.setForeground(new java.awt.Color(12, 60, 111));
                ccDelButton.setText("X");
                ccDelButton.setBackground(new java.awt.Color(12,60,111));
                ccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                ccDelButton.setForeground(new java.awt.Color(255,0,0));
                ccDelButton.setBorder(BorderFactory.createEmptyBorder());
                //    	deleteButton.setText("Close");
                ccDelButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ccDelActionPerformed(evt);
                    }
                });
                ccIdLabel.setForeground(new java.awt.Color(255,255,255));
                ccIdLabel.setName("labelDel"+i);
                ccDelButton.setName(""+i);
                ccIdPanel.add(ccIdLabel);
                ccIdPanel.add(ccDelButton);
                ccIdPanel.add(spaceLabel);
            }
        }
        ccIdPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        jScrollPane2.setViewportView(ccIdPanel);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        bccIdPanel.setBackground(new java.awt.Color(12, 60, 111));
        bccIdPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        if(bccEmailList != null){
            for(int i = 0;i<bccEmailList.size();i++){
                spaceLabel = new javax.swing.JLabel();
                bccIdLabel = new javax.swing.JLabel();
                bccDelButton = new javax.swing.JButton();
                bccIdLabel.setText( bccEmailList.get(i));
                spaceLabel.setText("  ");
                bccDelButton.setText("X");
                bccDelButton.setBackground(new java.awt.Color(12,60,111));
                bccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                bccDelButton.setForeground(new java.awt.Color(255,0,0));
                bccDelButton.setBorder(BorderFactory.createEmptyBorder());
                bccDelButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        bccDelActionPerformed(evt);
                    }
                });
                bccIdLabel.setForeground(new java.awt.Color(255,255,255));
                bccIdLabel.setName("labelDel"+i);
                bccDelButton.setName(""+i);
                bccIdPanel.add(bccIdLabel);
                bccIdPanel.add(bccDelButton);
                bccIdPanel.add(spaceLabel);
            }
        }
        jScrollPane3.setViewportView(bccIdPanel);

        javax.swing.GroupLayout emailPanelLayout = new javax.swing.GroupLayout(emailPanel);
        emailPanel.setLayout(emailPanelLayout);
        emailPanelLayout.setHorizontalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ccLabel)
                    .addComponent(toLabel)
                    .addComponent(bccLabel))
                .addGap(17, 17, 17)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1)))
        );
        emailPanelLayout.setVerticalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(toLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        subjectLabel.setText("Subject:");

        javax.swing.GroupLayout subjectPanelLayout = new javax.swing.GroupLayout(subjectPanel);
        subjectPanel.setLayout(subjectPanelLayout);
        subjectPanelLayout.setHorizontalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(subjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectTextField))
        );
        subjectPanelLayout.setVerticalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(subjectLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bodyTextArea.setColumns(20);
        bodyTextArea.setRows(5);
        jScrollPane4.setViewportView(bodyTextArea);

        closeButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        closeButton.setForeground(new java.awt.Color(12, 60, 111));
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        sendButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        sendButton.setForeground(new java.awt.Color(12, 60, 111));
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        attachmentLabel.setText("Attachments:");

        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        for(int k = 0;k<filepathList.size();k++){
            attachmentButton = new javax.swing.JButton();
            attachmentButton.setBackground(new java.awt.Color(184, 212, 239));
            attachmentButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
            attachmentButton.setForeground(new java.awt.Color(12, 60, 111));
            attachmentButton.setText(new File(filepathList.get(k)).getName());
            attachmentButton.setName(""+k);
            attachmentButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    showAttachmentButtonActionPerformed(evt);
                }
            });
            jPanel1.add(attachmentButton);
        }
        jPanel1.setBackground(new java.awt.Color(12, 60, 111));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        jScrollPane5.setViewportView(jPanel1);

        javax.swing.GroupLayout attachmentPanelLayout = new javax.swing.GroupLayout(attachmentPanel);
        attachmentPanel.setLayout(attachmentPanelLayout);
        attachmentPanelLayout.setHorizontalGroup(
            attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attachmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(attachmentLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );
        attachmentPanelLayout.setVerticalGroup(
            attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attachmentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(attachmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attachmentPanelLayout.createSequentialGroup()
                        .addComponent(attachmentLabel)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, attachmentPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        sirMadam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sir", "Madam"}));
        sirMadam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sirMadamActionPerformed(evt);
            }
        });

        jTextField1.setText("Dear,");

        javax.swing.GroupLayout outerMostPanelLayout = new javax.swing.GroupLayout(outerMostPanel);
        outerMostPanel.setLayout(outerMostPanelLayout);
        outerMostPanelLayout.setHorizontalGroup(
            outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(emailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subjectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outerMostPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(attachmentPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(outerMostPanelLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(sirMadam, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        outerMostPanelLayout.setVerticalGroup(
            outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(emailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attachmentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sirMadam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(closeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerMostPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerMostPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
            // TODO add your handling code here:
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            newTxPanel.maiScreen.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            String sirMadamString = "Dear "
                    + sirMadam.getSelectedItem().toString()+",\n";
            emailbodyText = sirMadamString+bodyTextArea.getText().replace("Corporate Off", "<img src=\"cid:imgPart\"/>\nCorporate Off");
            emailbodyText = emailbodyText.replaceAll("\n", "<br>");
            System.out.println("text entered is " + emailbodyText);
//    	ArrayList emailId = new ArrayList();
////    	for(int i= 0;i<5;i++){
//    		emailId.add("piyush.c@msronline.in");
//    		
////    	}
            boolean flag = false;
            if (filepathList.size() > 0) {
                flag = new SendMail().sendMail(toEmailList, ccEmailList, bccEmailList, filepathList, emailbodyText, subjectTextField.getText(), userId);
//                new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverClientDocPath+"/"+SingletonClass.clientCompanyName, txId, filepathList);
            } else {
                flag = new SendMail().sendMail(toEmailList, ccEmailList, bccEmailList, emailbodyText, subjectTextField.getText(), userId);
            }
            System.out.println("flag status is " + flag);
            if (flag) {
                for (int i = 0; i < filepathList.size(); i++) {
                    if (new File(filepathList.get(i)).isFile()) {
                        new File(filepathList.get(i)).delete();
                    }
                }
                changeStatusAndRefresh(isPreSla, isSLA, isDocs);
                System.out.println("clientDetailsBuffer: " + meetingSaveUrl);

                try {
//                serverUrl1.urlProcessiong("http://localhost:8180/EmailReminder/ReminderSetup?Client=" + SingletonClass.clientNameEmail + "&remindTime=" + rmndrTime + "&RmName=" + userId + "&Location=" + location + "&emailId=" + userId + "&meetingSchedule=" + meetingTime);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                for (int y = 0; y < filepathList.size(); y++) {
                    new File(filepathList.get(y)).delete();
                }
                this.dispose();
                JOptionPane.showMessageDialog(this, "E-Mail sent successfully.");

            } else {
                JOptionPane.showMessageDialog(null, "Error In Sending Mail. Please Contact Your Network Provider");
            }
            this.setCursor(Cursor.getDefaultCursor());
            newTxPanel.maiScreen.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            this.setCursor(Cursor.getDefaultCursor());
            newTxPanel.maiScreen.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_sendButtonActionPerformed
    public void changeStatusAndRefresh(boolean isPreSla, boolean isSLA, boolean isDocs) {

        StringBuffer statusChangeBuffer = new StringBuffer();
        if (isPreSla && !isSLA && !isDocs) {
            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Client Approval").append("&").append(SingletonClass.userName).append("=").append(userId);
            System.out.println("statusChangeBuffer1: " + statusChangeBuffer);
        } 
        else if ((isPreSla && !isSLA && isDocs) || (!isPreSla && !isSLA && isDocs)) {
            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Client Approval").append("&").append(SingletonClass.userName).append("=").append(userId);
            System.out.println("statusChangeBuffer2: " + statusChangeBuffer);
        } 
        else if (!isPreSla && isSLA && !isDocs) {
            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Mandate").append("&").append(SingletonClass.userName).append("=").append(userId);
            System.out.println("statusChangeBuffer3: " + statusChangeBuffer);
        } else if (isPreSla && isSLA && !isDocs) {
            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Mandate").append("&").append(SingletonClass.userName).append("=").append(userId);
            System.out.println("statusChangeBuffer3: " + statusChangeBuffer);
        } else if (isPreSla && isSLA && isDocs) {
            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Documents").append("&").append(SingletonClass.userName).append("=").append(userId);
            System.out.println("statusChangeBuffer3: " + statusChangeBuffer);
        } else if (!isPreSla && isSLA && isDocs) {
            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.txStatus).append("=").append("Awaiting Documents").append("&").append(SingletonClass.userName).append("=").append(userId);
            System.out.println("statusChangeBuffer3: " + statusChangeBuffer);
        }
        System.out.println("statusChangeBuffer outside: " + statusChangeBuffer);
        callServerUrl callUrl = new callServerUrl();
        callUrl.urlProcessiong(statusChangeBuffer.toString().replaceAll(" ", "%20"));
        newTxPanel.refreshScreen(newTxPanel);
        tabbedPane.setSelectedIndex(0);

    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        for (int i = 0; i < filepathList.size(); i++) {
            if (new File(filepathList.get(i)).isFile()) {
                new File(filepathList.get(i)).delete();
            }
        }
        this.dispose();
    }

    private void toDelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String buttonName = evt.getActionCommand();
        Component cmp = (Component) evt.getSource();
        buttonName = cmp.getName();
        System.out.println("button name is " + buttonName);
        int tempNum = Integer.parseInt(buttonName);
        ArrayList<String> tempList = new ArrayList<String>();
        toIdPanel.removeAll();
        toIdPanel.setLayout(new FlowLayout());
        toIdPanel.repaint();
        int j = 0;
        for (int i = 0; i < toEmailList.size(); i++) {

            if (i != tempNum) {

                spaceLabel = new javax.swing.JLabel();
                toIdLabel = new javax.swing.JLabel();
                toDelButton = new javax.swing.JButton();
                tempList.add(toEmailList.get(i));
                toIdLabel.setText(toEmailList.get(i));
                spaceLabel.setText("  ");
//            	deleteButton.setText("Del"+i);
//                toDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
//                toDelButton.setBackground(new java.awt.Color(184, 212, 239));
//                toDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
//                toDelButton.setForeground(new java.awt.Color(12, 60, 111));
                toDelButton.setText("X");
                toDelButton.setBackground(new java.awt.Color(12, 60, 111));
                toDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                toDelButton.setForeground(new java.awt.Color(255, 0, 0));
                toDelButton.setBorder(BorderFactory.createEmptyBorder());
//            	deleteButton.setText("Close");
                toDelButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        toDelActionPerformed(evt);
                    }
                });
                toIdLabel.setForeground(new java.awt.Color(255, 255, 255));
                toIdLabel.setName("labelDel" + j);
                toDelButton.setName("" + j);

                toIdPanel.add(toIdLabel);
                toIdPanel.add(toDelButton);
                toIdPanel.add(spaceLabel);
                System.out.println("tempList size is " + tempList.size());
                j++;
                //break;
            }

        }
//    	toIdPanel.repaint();
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        toIdPanel.setBackground(new java.awt.Color(12, 60, 111));
        toIdPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        jScrollPane1.setViewportView(toIdPanel);
        toEmailList = tempList;
        System.out.println("************ email List size is " + toEmailList.size());

    }

    private void ccDelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String buttonName = evt.getActionCommand();
        Component cmp = (Component) evt.getSource();
        buttonName = cmp.getName();
        System.out.println("button name is " + buttonName);
        int tempNum = Integer.parseInt(buttonName);
        ArrayList<String> tempList = new ArrayList<String>();
        ccIdPanel.removeAll();
        ccIdPanel.setLayout(new FlowLayout());
        ccIdPanel.repaint();
        int j = 0;
        for (int i = 0; i < ccEmailList.size(); i++) {

            if (i != tempNum) {

                spaceLabel = new javax.swing.JLabel();
                ccIdLabel = new javax.swing.JLabel();
                ccDelButton = new javax.swing.JButton();
                tempList.add(ccEmailList.get(i));
                ccIdLabel.setText(ccEmailList.get(i));
                spaceLabel.setText("  ");
//             	deleteButton.setText("Del"+i);
//                ccDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
//                ccDelButton.setBackground(new java.awt.Color(184, 212, 239));
//                ccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
//                ccDelButton.setForeground(new java.awt.Color(12, 60, 111));
                ccDelButton.setText("X");
                ccDelButton.setBackground(new java.awt.Color(12, 60, 111));
                ccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                ccDelButton.setForeground(new java.awt.Color(255, 0, 0));
                ccDelButton.setBorder(BorderFactory.createEmptyBorder());
//             	deleteButton.setText("Close");
                ccDelButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ccDelActionPerformed(evt);
                    }
                });
                ccIdLabel.setForeground(new java.awt.Color(255, 255, 255));
                ccIdLabel.setName("labelDel" + j);
                ccDelButton.setName("" + j);

                ccIdPanel.add(ccIdLabel);
                ccIdPanel.add(ccDelButton);
                ccIdPanel.add(spaceLabel);
                System.out.println("tempList size is " + tempList.size());
                j++;
                //break;
            }

        }
//     	toIdPanel.repaint();
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ccIdPanel.setBackground(new java.awt.Color(12, 60, 111));
        ccIdPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
        jScrollPane2.setViewportView(ccIdPanel);
        ccEmailList = tempList;
        System.out.println("************ email List size is " + ccEmailList.size());

    }

     private void bccDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
         // TODO add your handling code here:
         String buttonName = evt.getActionCommand();
         Component cmp = (Component) evt.getSource();
         buttonName = cmp.getName();
         System.out.println("button name is " + buttonName);
         int tempNum = Integer.parseInt(buttonName);
         ArrayList<String> tempList = new ArrayList<String>();
         bccIdPanel.removeAll();
         bccIdPanel.setLayout(new FlowLayout());
         bccIdPanel.repaint();
         int j = 0;
         for (int i = 0; i < bccEmailList.size(); i++) {

             if (i != tempNum) {

                 spaceLabel = new javax.swing.JLabel();
                 bccIdLabel = new javax.swing.JLabel();
                 bccDelButton = new javax.swing.JButton();
                 tempList.add(bccEmailList.get(i));
                 bccIdLabel.setText(bccEmailList.get(i));
                 spaceLabel.setText("  ");
//             	deleteButton.setText("Del"+i);
//                 bccDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
//                 bccDelButton.setBackground(new java.awt.Color(184, 212, 239));
//                 bccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
//                 bccDelButton.setForeground(new java.awt.Color(12, 60, 111));
//             	deleteButton.setText("Close");
                 bccDelButton.setText("X");
                 bccDelButton.setBackground(new java.awt.Color(12, 60, 111));
                 bccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                 bccDelButton.setForeground(new java.awt.Color(255, 0, 0));
                 bccDelButton.setBorder(BorderFactory.createEmptyBorder());
                 bccDelButton.addActionListener(new java.awt.event.ActionListener() {
                     public void actionPerformed(java.awt.event.ActionEvent evt) {
                         bccDelActionPerformed(evt);
                     }
                 });
                 bccIdLabel.setForeground(new java.awt.Color(255, 255, 255));
                 bccIdLabel.setName("labelDel" + j);
                 bccDelButton.setName("" + j);

                 bccIdPanel.add(bccIdLabel);
                 bccIdPanel.add(bccDelButton);
                 bccIdPanel.add(spaceLabel);
                 System.out.println("tempList size is " + tempList.size());
                 j++;
                 //break;
             }

         }
//     	toIdPanel.repaint();
         jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

         bccIdPanel.setBackground(new java.awt.Color(12, 60, 111));
         bccIdPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));
         jScrollPane3.setViewportView(bccIdPanel);
         bccEmailList = tempList;
         System.out.println("************ email List size is " + bccEmailList.size());

     }//GEN-LAST:event_closeButtonActionPerformed

    private void sirMadamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sirMadamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sirMadamActionPerformed

    private void showAttachmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        Component cmp = (Component) evt.getSource();
        int fileNum = Integer.parseInt(cmp.getName());
        Desktop d = Desktop.getDesktop();
        try {

            d.open(new File(filepathList.get(fileNum)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmailScreen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmailScreen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmailScreen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmailScreen1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new EmailScreen1().setVisible(true);

                new EmailScreen2("1", "subject", null, "", null, null, null, false, false, false, null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attachmentLabel;
    private javax.swing.JPanel attachmentPanel;
    private javax.swing.JPanel bccIdPanel;
    private javax.swing.JLabel bccLabel;
    private javax.swing.JTextArea bodyTextArea;
    private javax.swing.JPanel ccIdPanel;
    private javax.swing.JLabel ccLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel outerMostPanel;
    private javax.swing.JButton sendButton;
    private javax.swing.JComboBox sirMadam;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JPanel subjectPanel;
    private javax.swing.JTextField subjectTextField;
    private javax.swing.JPanel toIdPanel;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JButton toDelButton;
    private javax.swing.JButton ccDelButton;
    private javax.swing.JButton bccDelButton;
    private javax.swing.JLabel toIdLabel;
    private javax.swing.JLabel ccIdLabel;
    private javax.swing.JLabel bccIdLabel;
    private javax.swing.JLabel spaceLabel;
    private javax.swing.JButton attachmentButton;
}
