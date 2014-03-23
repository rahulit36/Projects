/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

/**
 *
 * @author piyush chawla
 * @version V1.0
 * @since feb 04, 2013
 *
 * this class is created to send mail i.e with attachments or without
 * attachments.......
 */
public class EmailScreen1 extends javax.swing.JFrame {

    private String emailbodyText = null;
    private ArrayList<String> toEmailList = null;
    private ArrayList<String> ccEmailList = null;
    private ArrayList<String> bccEmailList = null;
    private String subjectTxt = "";
    private List<String> filepathList = null;
    private String txId = null;
    public String meetingSaveUrl = "";
    ProposalDetailsPanel proposalDetails = null;
    String userId = "";
    String emailText = "";

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
//    public EmailScreen1(String txId, String subjectStr, ProposalDetailsPanel proposalDetails, String userId, List<String> slaPdfFilePathList, Map<String, String> emailMap, String emailText) {
//        filepathList = new ArrayList();
//                initComponents();
//        jScrollPane4.getVerticalScrollBar().setUnitIncrement(20);
//
//    }
    public EmailScreen1(String txId, String subjectStr, ProposalDetailsPanel proposalDetails, String userId, List<String> slaPdfFilePathList, Map<String, String> emailMap, String emailText) {

        try {
        proposalDetails.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            // set the icon of login frame
            setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));

        } catch (Exception ex) {
            Logger.getLogger(EmailScreen1.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txId = txId;
        this.meetingSaveUrl = meetingSaveUrl;
        this.proposalDetails = proposalDetails;
        this.userId = userId;
        this.emailText = emailText;
//        StringBuffer emailListUrl = new StringBuffer();
//        emailListUrl.append(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userId).append("&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.operaionType).append("=").append("emailsList").append("&").append(SingletonClass.forMail).append("=").append("meeting");
//        String urlResponse = new callServerUrl().urlProcessiong(emailListUrl.toString().replaceAll(" ", "%20"));
//        System.out.println("urlResponse:  " + urlResponse);

        if (emailMap.get("to") != null) {
            String[] toArray = emailMap.get("to").split(",");
            System.out.println("to array is " + toArray);
            toEmailList = new ArrayList<String>();
            for (String emailId : toArray) {
                if (emailId != null && emailId.length() > 0) {
                    toEmailList.add(emailId);
                }
            }
        }

        if (emailMap.get("cc") != null) {
            String[] ccArray = emailMap.get("cc").split(",");
            System.out.println("cc array is " + ccArray);
            ccEmailList = new ArrayList<String>();
            for (String emailId : ccArray) {
                if (emailId != null && emailId.length() > 0) {
                    ccEmailList.add(emailId);
                }
            }
        }

        if (emailMap.get("bcc") != null) {
            String[] bccArray = emailMap.get("bcc").split(",");
            System.out.println("bcc array is " + bccArray);
            bccEmailList = new ArrayList<String>();

            for (String emailId : bccArray) {
                if (emailId != null && emailId.length() > 0) {
                    bccEmailList.add(emailId);
                }
            }
        }
        
            if(emailMap.get("productTrade")!=null&& !"null".equals("productTrade"))
                trade.setText(emailMap.get("productTrade"));
            if(emailMap.get("industry")!=null&& !"null".equals("industry"))
                industry.setText(emailMap.get("industry"));
            if(emailMap.get("yearOfIncorporation")!=null&& !"null".equals("yearOfIncorporation"))
                estiTime.setText(emailMap.get("yearOfIncorporation"));
            
            if(emailMap.get("extCreditRating")!=null&& !"null".equals("extCreditRating"))
                trade.setText(emailMap.get("extCreditRating"));
            
            if(emailMap.get("turnover1")!=null&& !"null".equals("turnover1"))
                trade.setText(emailMap.get("turnover1"));
            
            if(emailMap.get("turnover2")!=null&& !"null".equals("turnover2"))
                trade.setText(emailMap.get("turnover2"));
            
            if(emailMap.get("turnover3")!=null&& !"null".equals("turnover3"))
                trade.setText(emailMap.get("turnover3"));
            
            if(emailMap.get("purchase1")!=null&& !"null".equals("purchase1"))
                trade.setText(emailMap.get("purchase1"));
            
            if(emailMap.get("purchase2")!=null&& !"null".equals("purchase2"))
                trade.setText(emailMap.get("purchase2"));
            
            if(emailMap.get("purchase3")!=null&& !"null".equals("purchase3"))
                trade.setText(emailMap.get("purchase3"));
            
            if(emailMap.get("domestic1")!=null&& !"null".equals("domestic1"))
                trade.setText(emailMap.get("domestic1"));
            
            if(emailMap.get("domestic2")!=null&& !"null".equals("domestic2"))
                trade.setText(emailMap.get("domestic2"));
            
            if(emailMap.get("domestic3")!=null&& !"null".equals("domestic3"))
                trade.setText(emailMap.get("domestic3"));
            
            if(emailMap.get("termLoan1")!=null&& !"null".equals("termLoan1"))
                trade.setText(emailMap.get("termLoan1"));
            
            if(emailMap.get("termLoan2")!=null&& !"null".equals("termLoan2"))
                trade.setText(emailMap.get("termLoan2"));
            
            if(emailMap.get("fbccLimit1")!=null&& !"null".equals("fbccLimit1"))
                trade.setText(emailMap.get("fbccLimit1"));
            
            if(emailMap.get("fbccLimit2")!=null&& !"null".equals("fbccLimit2"))
                trade.setText(emailMap.get("fbccLimit2"));
            
            if(emailMap.get("nfbLCLimit1")!=null&& !"null".equals("nfbLCLimit1"))
                trade.setText(emailMap.get("nfbLCLimit1"));
            
            if(emailMap.get("nfbLCLimit2")!=null&& !"null".equals("nfbLCLimit2"))
                trade.setText(emailMap.get("nfbLCLimit2"));
            
            if(emailMap.get("nfbBGLimit1")!=null&& !"null".equals("nfbBGLimit1"))
                trade.setText(emailMap.get("nfbBGLimit1"));
            
            if(emailMap.get("nfbBGLimit2")!=null&& !"null".equals("nfbBGLimit2"))
                trade.setText(emailMap.get("nfbBGLimit2"));
            
            if(emailMap.get("totalWC1")!=null&& !"null".equals("totalWC1"))
                trade.setText(emailMap.get("totalWC1"));
            
            if(emailMap.get("totalWC2")!=null&& !"null".equals("totalWC2"))
                trade.setText(emailMap.get("totalWC2"));
            
            if(emailMap.get("total1")!=null&& !"null".equals("total1"))
                trade.setText(emailMap.get("total1"));
            
            if(emailMap.get("total2")!=null&& !"null".equals("total2"))
                trade.setText(emailMap.get("total2"));
            
            if(emailMap.get("total3")!=null&& !"null".equals("total3"))
                trade.setText(emailMap.get("total3"));
            
            if(emailMap.get("total4")!=null&& !"null".equals("total4"))
                trade.setText(emailMap.get("total4"));
            
            if(emailMap.get("total5")!=null&& !"null".equals("total5"))
                trade.setText(emailMap.get("total5"));
            
            if(emailMap.get("specialTxt")!=null&& !"null".equals("specialTxt"))
                trade.setText(emailMap.get("specialTxt"));

//        emailbodyText = urlResponse.substring(urlResponse.lastIndexOf(";emailbody:") + 11, urlResponse.length());
        emailbodyText = "hi..";
        this.subjectTxt = subjectStr;


        filepathList = slaPdfFilePathList;
//        filepathList.add("/root/Desktop/netCORE_API_SMS_Services_1.18.pdf");



        initComponents();
        subjectTextField.setText(subjectTxt);
//        bodyTextArea.setText(emailText);
//        bodyTextArea.setText(emailText.replaceAll("<br>", "\n")
//                .replaceAll("<table border=\"1\">", "") .replaceAll("<html><body>", "") .replaceAll("<table>", "").replaceAll("<tr>", "").replaceAll("<th>", "").replaceAll("<td>", "")
//                .replaceAll("<b>", "").replaceAll("</td>", "").replaceAll("</tr>", "\n").replaceAll("</table>", "\n"));
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        proposalDetails.setCursor(Cursor.getDefaultCursor());
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
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        proBankPerc = new javax.swing.JTextField();
        proposaedBank = new javax.swing.JTextField();
        usanceDays = new javax.swing.JTextField();
        proAmount1 = new javax.swing.JTextField();
        estiTime = new javax.swing.JTextField();
        trade = new javax.swing.JTextField();
        industry = new javax.swing.JTextField();
        companyName = new javax.swing.JTextField();
        extCreditRating = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        proAmount2 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        rev1 = new javax.swing.JTextField();
        vol1 = new javax.swing.JTextField();
        projections = new javax.swing.JTextField();
        ourCharges = new javax.swing.JTextField();
        vol2 = new javax.swing.JTextField();
        rev2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        turn1 = new javax.swing.JTextField();
        turn2 = new javax.swing.JTextField();
        purch1 = new javax.swing.JTextField();
        purch2 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        turn3 = new javax.swing.JTextField();
        purch3 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        domestic1 = new javax.swing.JTextField();
        domestic2 = new javax.swing.JTextField();
        domestic3 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        totalWC1 = new javax.swing.JTextField();
        NFBBG1 = new javax.swing.JTextField();
        termLoan1 = new javax.swing.JTextField();
        total1 = new javax.swing.JTextField();
        total4 = new javax.swing.JTextField();
        total5 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        fbLimitCC1 = new javax.swing.JTextField();
        total2 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        termLoan2 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        fbLimitCC2 = new javax.swing.JTextField();
        totalWC2 = new javax.swing.JTextField();
        NFBBG2 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        total3 = new javax.swing.JTextField();
        NFBLC2 = new javax.swing.JTextField();
        NFBLC1 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        specialNote = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();

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
                    .addComponent(bccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        emailPanelLayout.setVerticalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(toLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(emailPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(bccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(emailPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(18, 18, 18)
                .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        subjectPanelLayout.setVerticalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(subjectLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel40.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel40.setText("1.       Company at a Glance:");

        jLabel41.setBackground(new java.awt.Color(75, 172, 198));
        jLabel41.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel41.setText("Particulars");
        jLabel41.setOpaque(true);

        jLabel42.setBackground(new java.awt.Color(75, 172, 198));
        jLabel42.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel42.setText("Company Name");
        jLabel42.setOpaque(true);

        jLabel43.setBackground(new java.awt.Color(75, 172, 198));
        jLabel43.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel43.setText("Address");
        jLabel43.setOpaque(true);

        jLabel44.setBackground(new java.awt.Color(75, 172, 198));
        jLabel44.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel44.setText("Background/ ESTD./ Principal Activity");
        jLabel44.setOpaque(true);

        jLabel45.setBackground(new java.awt.Color(75, 172, 198));
        jLabel45.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel45.setText("External Credit Rating (If Any)");
        jLabel45.setOpaque(true);

        jLabel46.setBackground(new java.awt.Color(75, 172, 198));
        jLabel46.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel46.setText("Proposed Facility");
        jLabel46.setOpaque(true);

        jLabel47.setBackground(new java.awt.Color(75, 172, 198));
        jLabel47.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel47.setText("Proposed Amount");
        jLabel47.setOpaque(true);

        jLabel48.setBackground(new java.awt.Color(75, 172, 198));
        jLabel48.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel48.setText("Usance Days");
        jLabel48.setOpaque(true);

        jLabel49.setBackground(new java.awt.Color(75, 172, 198));
        jLabel49.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel49.setText("Proposed Bank");
        jLabel49.setOpaque(true);

        jLabel50.setBackground(new java.awt.Color(75, 172, 198));
        jLabel50.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel50.setText("Product Manufacture/Trade");
        jLabel50.setOpaque(true);

        jLabel51.setBackground(new java.awt.Color(75, 172, 198));
        jLabel51.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel51.setText("Industry");
        jLabel51.setOpaque(true);

        jLabel52.setBackground(new java.awt.Color(75, 172, 198));
        jLabel52.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel52.setText("Proposed Bank Rate (%)");
        jLabel52.setOpaque(true);

        proBankPerc.setBackground(new java.awt.Color(210, 234, 241));

        proposaedBank.setBackground(new java.awt.Color(210, 234, 241));

        usanceDays.setBackground(new java.awt.Color(210, 234, 241));

        proAmount1.setBackground(new java.awt.Color(210, 234, 241));

        estiTime.setBackground(new java.awt.Color(210, 234, 241));

        trade.setBackground(new java.awt.Color(210, 234, 241));

        industry.setBackground(new java.awt.Color(210, 234, 241));

        companyName.setBackground(new java.awt.Color(210, 234, 241));

        extCreditRating.setBackground(new java.awt.Color(210, 234, 241));

        address.setBackground(new java.awt.Color(210, 234, 241));
        address.setColumns(20);
        address.setRows(5);
        jScrollPane6.setViewportView(address);

        proAmount2.setBackground(new java.awt.Color(210, 234, 241));

        jLabel82.setBackground(new java.awt.Color(75, 172, 198));
        jLabel82.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Purchase");
        jLabel82.setOpaque(true);

        jLabel83.setBackground(new java.awt.Color(75, 172, 198));
        jLabel83.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Sale");
        jLabel83.setOpaque(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel40)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(proBankPerc)
                            .addComponent(proposaedBank)
                            .addComponent(usanceDays)
                            .addComponent(companyName)
                            .addComponent(industry)
                            .addComponent(trade)
                            .addComponent(estiTime)
                            .addComponent(extCreditRating)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(proAmount1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(proAmount2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trade, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(industry, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estiTime, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(extCreditRating, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usanceDays, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proposaedBank, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proBankPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setBackground(new java.awt.Color(75, 172, 198));
        jLabel53.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel53.setText("Projections");
        jLabel53.setOpaque(true);

        jLabel54.setBackground(new java.awt.Color(75, 172, 198));
        jLabel54.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel54.setText("Volume");
        jLabel54.setOpaque(true);

        jLabel55.setBackground(new java.awt.Color(75, 172, 198));
        jLabel55.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel55.setText("Revenue (INR)");
        jLabel55.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel55.setOpaque(true);

        jLabel56.setBackground(new java.awt.Color(75, 172, 198));
        jLabel56.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel56.setText("Our Charges");
        jLabel56.setOpaque(true);

        rev1.setBackground(new java.awt.Color(210, 234, 241));

        vol1.setBackground(new java.awt.Color(210, 234, 241));

        projections.setBackground(new java.awt.Color(210, 234, 241));

        ourCharges.setBackground(new java.awt.Color(210, 234, 241));

        vol2.setBackground(new java.awt.Color(210, 234, 241));

        rev2.setBackground(new java.awt.Color(210, 234, 241));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ourCharges, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(projections, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rev1)
                            .addComponent(vol1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rev2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(vol2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ourCharges, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(projections, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vol1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rev1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(vol2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rev2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel57.setBackground(new java.awt.Color(75, 172, 198));
        jLabel57.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel57.setOpaque(true);

        jLabel58.setBackground(new java.awt.Color(75, 172, 198));
        jLabel58.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Turnover");
        jLabel58.setOpaque(true);

        jLabel59.setBackground(new java.awt.Color(75, 172, 198));
        jLabel59.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Purchases");
        jLabel59.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel59.setOpaque(true);

        jLabel60.setBackground(new java.awt.Color(75, 172, 198));
        jLabel60.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel60.setText("Particulars");
        jLabel60.setOpaque(true);

        jTextField53.setBackground(new java.awt.Color(210, 234, 241));
        jTextField53.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jTextField53.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField53.setText("Audited");

        jTextField54.setBackground(new java.awt.Color(210, 234, 241));
        jTextField54.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jTextField54.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField54.setText("Audited");

        turn1.setBackground(new java.awt.Color(210, 234, 241));

        turn2.setBackground(new java.awt.Color(210, 234, 241));

        purch1.setBackground(new java.awt.Color(210, 234, 241));

        purch2.setBackground(new java.awt.Color(210, 234, 241));

        jTextField60.setBackground(new java.awt.Color(210, 234, 241));
        jTextField60.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jTextField60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField60.setText("Provisional");

        turn3.setBackground(new java.awt.Color(210, 234, 241));

        purch3.setBackground(new java.awt.Color(210, 234, 241));

        jLabel67.setBackground(new java.awt.Color(75, 172, 198));
        jLabel67.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("Domestic ( %)");
        jLabel67.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel67.setOpaque(true);

        domestic1.setBackground(new java.awt.Color(210, 234, 241));

        domestic2.setBackground(new java.awt.Color(210, 234, 241));

        domestic3.setBackground(new java.awt.Color(210, 234, 241));

        jLabel68.setBackground(new java.awt.Color(75, 172, 198));
        jLabel68.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("FY 2013-14");
        jLabel68.setOpaque(true);

        jLabel69.setBackground(new java.awt.Color(75, 172, 198));
        jLabel69.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("FY 2012-13");
        jLabel69.setOpaque(true);

        jLabel70.setBackground(new java.awt.Color(75, 172, 198));
        jLabel70.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("FY 2011-12");
        jLabel70.setOpaque(true);

        jLabel66.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel66.setText("2.       Financials");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(purch1)
                    .addComponent(turn1)
                    .addComponent(jTextField53)
                    .addComponent(domestic1))
                .addGap(3, 3, 3)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField54, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(purch2)
                            .addComponent(turn2)
                            .addComponent(domestic2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(turn3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(purch3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(domestic3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField60, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(turn3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(purch3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addComponent(domestic3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(purch1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(purch2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(turn1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(turn2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(domestic1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(domestic2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel61.setBackground(new java.awt.Color(75, 172, 198));
        jLabel61.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel61.setOpaque(true);

        jLabel62.setBackground(new java.awt.Color(75, 172, 198));
        jLabel62.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel62.setOpaque(true);

        jLabel63.setBackground(new java.awt.Color(75, 172, 198));
        jLabel63.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel63.setText("Total");
        jLabel63.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel63.setOpaque(true);

        jLabel64.setBackground(new java.awt.Color(75, 172, 198));
        jLabel64.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel64.setText("Bank Name");
        jLabel64.setOpaque(true);

        totalWC1.setBackground(new java.awt.Color(210, 234, 241));

        NFBBG1.setBackground(new java.awt.Color(210, 234, 241));

        termLoan1.setBackground(new java.awt.Color(210, 234, 241));

        total1.setBackground(new java.awt.Color(210, 234, 241));

        total4.setBackground(new java.awt.Color(210, 234, 241));

        total5.setBackground(new java.awt.Color(210, 234, 241));
        total5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total5ActionPerformed(evt);
            }
        });

        jLabel72.setBackground(new java.awt.Color(75, 172, 198));
        jLabel72.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("Term Loan");
        jLabel72.setOpaque(true);

        jLabel73.setBackground(new java.awt.Color(75, 172, 198));
        jLabel73.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("NFB Limit");
        jLabel73.setOpaque(true);

        jLabel74.setBackground(new java.awt.Color(75, 172, 198));
        jLabel74.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Total");
        jLabel74.setOpaque(true);

        fbLimitCC1.setBackground(new java.awt.Color(210, 234, 241));

        total2.setBackground(new java.awt.Color(210, 234, 241));

        jLabel75.setBackground(new java.awt.Color(75, 172, 198));
        jLabel75.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("FB Limit");
        jLabel75.setOpaque(true);

        termLoan2.setBackground(new java.awt.Color(210, 234, 241));

        jLabel76.setBackground(new java.awt.Color(75, 172, 198));
        jLabel76.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel76.setOpaque(true);

        fbLimitCC2.setBackground(new java.awt.Color(210, 234, 241));

        totalWC2.setBackground(new java.awt.Color(210, 234, 241));

        NFBBG2.setBackground(new java.awt.Color(210, 234, 241));

        jLabel77.setBackground(new java.awt.Color(75, 172, 198));
        jLabel77.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel77.setOpaque(true);

        jLabel78.setBackground(new java.awt.Color(75, 172, 198));
        jLabel78.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel78.setText("CC");
        jLabel78.setOpaque(true);

        jLabel79.setBackground(new java.awt.Color(75, 172, 198));
        jLabel79.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel79.setText("LC");
        jLabel79.setOpaque(true);

        jLabel80.setBackground(new java.awt.Color(75, 172, 198));
        jLabel80.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel80.setText("BG");
        jLabel80.setOpaque(true);

        jLabel81.setBackground(new java.awt.Color(75, 172, 198));
        jLabel81.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel81.setText("WC");
        jLabel81.setOpaque(true);

        total3.setBackground(new java.awt.Color(210, 234, 241));

        NFBLC2.setBackground(new java.awt.Color(210, 234, 241));

        NFBLC1.setBackground(new java.awt.Color(210, 234, 241));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(termLoan1)
                            .addComponent(termLoan2)
                            .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(fbLimitCC2)
                                .addComponent(fbLimitCC1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(total1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(total3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(NFBBG2))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(NFBLC2, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NFBLC1))
                                        .addGap(4, 4, 4)
                                        .addComponent(NFBBG1)))))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(totalWC1)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalWC2)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(total4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(total5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(7, 7, 7)
                                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel8Layout.createSequentialGroup()
                                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(totalWC1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(termLoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(termLoan2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fbLimitCC1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fbLimitCC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NFBBG2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalWC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(total4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(total5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(total1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NFBBG1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NFBLC1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(NFBLC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );

        jLabel65.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel65.setText("3.       Working Capital Arrangement ");

        jLabel71.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel71.setText("4.       Special Note:-");

        specialNote.setColumns(20);
        specialNote.setRows(5);
        jScrollPane7.setViewportView(specialNote);

        jButton1.setText("Save");

        jButton2.setText("Cancel");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("\nDear Sir,\n\nKindly approve the below mentioned client for LCBD limit setup.");
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTextArea1.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 477, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(656, Short.MAX_VALUE)))
        );

        jScrollPane4.setViewportView(jPanel4);

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

        javax.swing.GroupLayout outerMostPanelLayout = new javax.swing.GroupLayout(outerMostPanel);
        outerMostPanel.setLayout(outerMostPanelLayout);
        outerMostPanelLayout.setHorizontalGroup(
            outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(outerMostPanelLayout.createSequentialGroup()
                        .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subjectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        outerMostPanelLayout.setVerticalGroup(
            outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(emailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(sendButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerMostPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(outerMostPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
         companyNameStr = companyName.getText();
         addString = address.getText();
         tradText=          trade.getText();
         industryText =          industry.getText();
         estd = estiTime.getText();
         extStringTxt = extCreditRating.getText();
         proAmount1Txt = proAmount1.getText();
         proAmount2Txt = proAmount2.getText();
         usString = usanceDays.getText();
         propoStringBank = proposaedBank.getText();
         proBankPercTxt = proBankPerc.getText();
         ourChrgsText = ourCharges.getText();
         projectionText    = projections.getText();
         volumeTxt =  vol1.getText();
         vol2Txt = vol2.getText();
         rev1Txt = rev1.getText();
         rev2Txt = rev2.getText();
         turn1Txt = turn1.getText();
         turn2Txt = turn2.getText();
         turn3Txt = turn3.getText();
         purchases1= purch1.getText();
         purchases2 = purch2.getText();
         purchases3 = purch3.getText();
         domesticTxt1= domestic1.getText();
         domesticTxt2 =domestic2.getText();
         domesticTxt3 =domestic3.getText();
         termLoan1Txt = termLoan1.getText();
         termLoan2Txt = termLoan2.getText();
         fbString1 = fbLimitCC1.getText();
         fbString2 = fbLimitCC2.getText();
         nfString1 = NFBLC1.getText();
         nfString2 = NFBLC2.getText();
         NFBBGTxt1 = NFBBG1.getText();
         NFBBGTxt2 = NFBBG2.getText();
         totalWC1Txt = totalWC1.getText();
         totalWC2Txt = totalWC2.getText();
         total1Txt = total1.getText();
         total2Txt = total2.getText();
         total3Txt = total3.getText();
         total4Txt = total4.getText();
         total5Txt = total5.getText();
         specialTxt = specialNote.getText();
        
        
        
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        emailbodyText = bodyTextArea.getText();
        System.out.println("text entered is " + emailbodyText);

        ApprovalEmailText approvalEmailText = new ApprovalEmailText();
        String emailText = approvalEmailText.getApprovalText(this);
        String subjectText = subjectTextField.getText();
        
        
        boolean flag = false;
//        if (filepathList.size() > 0) {
//            flag = new SendMail().sendMail(toEmailList, ccEmailList, bccEmailList, filepathList, emailText, subjectTextField.getText(), userId);
//
//
//        } else {
            flag = new SendMail().sendMail(toEmailList, ccEmailList, bccEmailList, emailText, subjectText, userId);
//        }
        System.out.println("flag status is " + flag);
        if (flag) {
            new callServerUrl().urlProcessiong(SingletonClass.approvalURL);

            try {
//                serverUrl1.urlProcessiong("http://localhost:8180/EmailReminder/ReminderSetup?Client=" + SingletonClass.clientNameEmail + "&remindTime=" + rmndrTime + "&RmName=" + userId + "&Location=" + location + "&emailId=" + userId + "&meetingSchedule=" + meetingTime);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            for (int i = 0; i < filepathList.size(); i++) {
                if (new File(filepathList.get(i)).isFile()) {
                    new File(filepathList.get(i)).delete();
                }
            }
            this.dispose();
                JOptionPane.showMessageDialog(this, "E-Mail sent successfully.");

        } else {
            JOptionPane.showMessageDialog(this, "Error In Sending Mail. Please Contact Your Network Provider");
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_sendButtonActionPerformed

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
//            	deleteButton.setText("Close");
                toDelButton.setText("X");
                toDelButton.setBackground(new java.awt.Color(12, 60, 111));
                toDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                toDelButton.setForeground(new java.awt.Color(255, 0, 0));
                toDelButton.setBorder(BorderFactory.createEmptyBorder());
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
//             	deleteButton.setText("Close");
                ccDelButton.setText("X");
                ccDelButton.setBackground(new java.awt.Color(12, 60, 111));
                ccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                ccDelButton.setForeground(new java.awt.Color(255, 0, 0));
                ccDelButton.setBorder(BorderFactory.createEmptyBorder());
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

    private void total5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total5ActionPerformed

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

                new EmailScreen1("1", "subject", null, "", null, null, "").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NFBBG1;
    private javax.swing.JTextField NFBBG2;
    private javax.swing.JTextField NFBLC1;
    private javax.swing.JTextField NFBLC2;
    private javax.swing.JTextArea address;
    private javax.swing.JPanel bccIdPanel;
    private javax.swing.JLabel bccLabel;
    private javax.swing.JPanel ccIdPanel;
    private javax.swing.JLabel ccLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField companyName;
    private javax.swing.JTextField domestic1;
    private javax.swing.JTextField domestic2;
    private javax.swing.JTextField domestic3;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JTextField estiTime;
    private javax.swing.JTextField extCreditRating;
    private javax.swing.JTextField fbLimitCC1;
    private javax.swing.JTextField fbLimitCC2;
    private javax.swing.JTextField industry;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField ourCharges;
    private javax.swing.JPanel outerMostPanel;
    private javax.swing.JTextField proAmount1;
    private javax.swing.JTextField proAmount2;
    private javax.swing.JTextField proBankPerc;
    private javax.swing.JTextField projections;
    private javax.swing.JTextField proposaedBank;
    private javax.swing.JTextField purch1;
    private javax.swing.JTextField purch2;
    private javax.swing.JTextField purch3;
    private javax.swing.JTextField rev1;
    private javax.swing.JTextField rev2;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea specialNote;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JPanel subjectPanel;
    private javax.swing.JTextField subjectTextField;
    private javax.swing.JTextField termLoan1;
    private javax.swing.JTextField termLoan2;
    private javax.swing.JPanel toIdPanel;
    private javax.swing.JLabel toLabel;
    private javax.swing.JTextField total1;
    private javax.swing.JTextField total2;
    private javax.swing.JTextField total3;
    private javax.swing.JTextField total4;
    private javax.swing.JTextField total5;
    private javax.swing.JTextField totalWC1;
    private javax.swing.JTextField totalWC2;
    private javax.swing.JTextField trade;
    private javax.swing.JTextField turn1;
    private javax.swing.JTextField turn2;
    private javax.swing.JTextField turn3;
    private javax.swing.JTextField usanceDays;
    private javax.swing.JTextField vol1;
    private javax.swing.JTextField vol2;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JButton toDelButton;
    private javax.swing.JButton ccDelButton;
    private javax.swing.JButton bccDelButton;
    private javax.swing.JLabel toIdLabel;
    private javax.swing.JLabel ccIdLabel;
    private javax.swing.JLabel bccIdLabel;
    private javax.swing.JLabel spaceLabel;
    private javax.swing.JButton attachmentButton;
    public String companyNameStr = "";
        public String addString = "";
        public String tradText= "";
        public String industryText =  "";
        public String estd = "";
        public String extStringTxt = "";
        public String proAmount1Txt = "";
        public String proAmount2Txt = "";
        public String usString = "";
        public String propoStringBank = "";
        public String proBankPercTxt = "";
        public String ourChrgsText = "";
        public String projectionText    = "";
        public String volumeTxt =  "";
        public String vol2Txt = "";
        public String rev1Txt = "";
        public String rev2Txt = "";
        public String turn1Txt = "";
        public String turn2Txt = "";
        public String turn3Txt = "";
        public String purchases1= "";
        public String purchases2 = "";
        public String purchases3 = "";
        public String domesticTxt1= "";
        public String domesticTxt2 ="";
        public String domesticTxt3 ="";
        public String termLoan1Txt = "";
        public String termLoan2Txt = "";
        public String fbString1 = "";
        public String fbString2 = "";
        public String nfString1 = "";
        public String nfString2 = "";
        public String NFBBGTxt1 = "";
        public String NFBBGTxt2 = "";
        public String totalWC1Txt = "";
        public String totalWC2Txt = "";
        public String total1Txt = "";
        public String total2Txt = "";
        public String total3Txt = "";
        public String total4Txt = "";
        public String total5Txt = "";
        public String specialTxt = "";
}
