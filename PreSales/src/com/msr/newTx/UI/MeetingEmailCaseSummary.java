/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.newTx.dao.MeetingDetailBean;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author piyush chawla
 * @version V1.0
 * @since feb 04, 2013
 *
 * this class is created to send mail i.e with attachments or without
 * attachments.......
 */
public class MeetingEmailCaseSummary extends javax.swing.JFrame {

    private String emailbodyText = null;
    private ArrayList<String> toEmailList = null;
    private ArrayList<String> ccEmailList = null;
    private ArrayList<String> bccEmailList = null;
    private String subjectTxt = "";
    private ArrayList<String> filepathList = null;
    private String txId = null;
    public String meetingSaveUrl = "";
    Vector<String> head = null;
    Vector<Vector> columData = null;
    NewTxPanel newTxPanel = null;
    String userId = "";
    String caseSummary = "";
    MeetingDetailBean meetingBean = null;

    public MeetingEmailCaseSummary(MeetingDetailBean meetingBean) {
        try {
            // set the icon of login frame
            setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));

        } catch (Exception ex) {
            Logger.getLogger(MeetingEmailCaseSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.meetingBean = meetingBean;
        head = new Vector<String>();
        columData = new Vector<Vector>();

        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

//        TableColumn sNoCol = this.meetingTable.getColumnModel().getColumn(0);
//        sNoCol.setWidth(50);
//        sNoCol.setMaxWidth(50);
//        sNoCol.setResizable(false);
//        
//        TableColumn documentTab = this.meetingTable.getColumnModel().getColumn(1);
//        documentTab.setResizable(false);
//        
//        TableColumn uploadTab = this.meetingTable.getColumnModel().getColumn(2);
//        uploadTab.setResizable(false);
    }

    /**
     * Creates new form EmailScreen1
     */
    public MeetingEmailCaseSummary(String txId, String subjectStr, NewTxPanel newTxPanel, String userId, String caseSummary, MeetingDetailBean meetingBean) {
        this.meetingBean = meetingBean;
        this.txId = txId;
        this.meetingSaveUrl = meetingSaveUrl;
        this.newTxPanel = newTxPanel;
        this.userId = userId;
        this.caseSummary = caseSummary;
        this.subjectTxt = subjectStr;
        //  StringBuffer emailListUrl = new StringBuffer();
        // emailListUrl.append(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userId).append("&").append(SingletonClass.txNo).append("=").append(txId).append("&").append(SingletonClass.operaionType).append("=").append("emailsList").append("&").append(SingletonClass.forMail).append("=").append("meeting");
        //  String urlResponse = new callServerUrl().urlProcessiong(emailListUrl.toString().replaceAll(" ", "%20"));
        // System.out.println("urlResponse:  " + urlResponse);
        //   String toStr = urlResponse.substring(urlResponse.lastIndexOf("to:") + 3, urlResponse.indexOf(";cc:"));
        // System.out.println("to list is " + toStr);
        System.out.println(" to list is  " + meetingBean.getTo());
        String[] toArray = meetingBean.getTo().split(",");
        toEmailList = new ArrayList<String>();
        head = new Vector<String>();
        columData = new Vector<Vector>();
        for (String emailId : toArray) {
            if (emailId != null && emailId.length() > 0) {
                toEmailList.add(emailId);
            }
        }

        String ccStr = meetingBean.getCc();
        //urlResponse.substring(urlResponse.lastIndexOf(";cc:") + 4, urlResponse.indexOf(";bcc:"));
        System.out.println("cc list is " + ccStr);
        if (ccStr != null && ccStr.length() > 0) {
            String[] ccArray = ccStr.split(",");
            ccEmailList = new ArrayList<String>();

            for (String emailId : ccArray) {
                if (emailId != null && emailId.length() > 0) {
                    ccEmailList.add(emailId);
                }
            }
        }
        String bccStr = meetingBean.getBcc();
        //urlResponse.substring(urlResponse.lastIndexOf(";bcc:") + 5, urlResponse.indexOf(";emailbody:"));
        if (bccStr != null && bccStr.length() > 0) {
            System.out.println("bcc list is " + bccStr);
            String[] bccArray = bccStr.split(",");
            bccEmailList = new ArrayList<String>();

            for (String emailId : bccArray) {
                if (emailId != null && emailId.length() > 0) {
                    bccEmailList.add(emailId);
                }
            }
        }



        filepathList = new ArrayList<String>();

//      ------------------------------------
        initComponents();
        //    emailbodyText = urlResponse.substring(urlResponse.lastIndexOf(";emailbody:") + 11, urlResponse.length());
        subjectTextField.setText(subjectTxt);

//String Message = "Dear Sir,<br><br>I have scheduled a meeting with my client and details are below\n\n Company Name\t\t: \n Turnover\t\t: \n Contact Person\t\t: \n Mobile Number	Date & Time	Place of Meeting	Purpose";
        String Message = "Dear Sir,<br><br>Below is the case summary of the meeting.<br><br>"
                + caseSummary
                + "<br> <br>  Regards,<br>"
                + "" + meetingBean.getRmDisName() + "<br> " + meetingBean.getRmNumber() + "";
//<img src=\"cid:imgPart\"/>
        bodyTextArea.setText(Message);

        //        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.repaint();

    }
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
        closeButton = new javax.swing.JButton();
        sendButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        bodyTextArea = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pre-Meeting Information");
        setAlwaysOnTop(true);

        outerMostPanel.setBackground(new java.awt.Color(12, 60, 111));

        toLabel.setText("To: ");

        ccLabel.setText("Cc:");

        bccLabel.setText("Bcc:");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        toIdPanel.setBackground(new java.awt.Color(12, 60, 111));
        if(toEmailList!=null && toEmailList.size()>0){
            for(int i = 0;i<toEmailList.size();i++){
                spaceLabel = new javax.swing.JLabel();
                toIdLabel = new javax.swing.JLabel();
                toDelButton = new javax.swing.JButton();
                toIdLabel.setText( toEmailList.get(i));
                spaceLabel.setText("  ");
                //      	deleteButton.setText("Del"+i);
                //      	toDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
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
        if(ccEmailList!=null && ccEmailList.size()>0){
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
        if(bccEmailList!=null && bccEmailList.size()>0){
            for(int i = 0;i<bccEmailList.size();i++){
                spaceLabel = new javax.swing.JLabel();
                bccIdLabel = new javax.swing.JLabel();
                bccDelButton = new javax.swing.JButton();
                bccIdLabel.setText( bccEmailList.get(i));
                spaceLabel.setText("  ");
                //  	deleteButton.setText("Del"+i);
                //  	bccDelButton.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
                //  	bccDelButton.setBackground(new java.awt.Color(184, 212, 239));
                //  	bccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
                //  	bccDelButton.setForeground(new java.awt.Color(12, 60, 111));
                bccDelButton.setText("X");
                bccDelButton.setBackground(new java.awt.Color(12,60,111));
                bccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                bccDelButton.setForeground(new java.awt.Color(255,0,0));
                bccDelButton.setBorder(BorderFactory.createEmptyBorder());
                //  	deleteButton.setText("Close");
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
                    .addComponent(bccLabel)
                    .addComponent(toLabel)
                    .addComponent(ccLabel))
                .addGap(18, 18, 18)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        emailPanelLayout.setVerticalGroup(
            emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(emailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(emailPanelLayout.createSequentialGroup()
                        .addGap(0, 18, Short.MAX_VALUE)
                        .addComponent(ccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(emailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bccLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        subjectLabel.setText("Subject:");

        javax.swing.GroupLayout subjectPanelLayout = new javax.swing.GroupLayout(subjectPanel);
        subjectPanel.setLayout(subjectPanelLayout);
        subjectPanelLayout.setHorizontalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(subjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(subjectTextField)
                .addContainerGap())
        );
        subjectPanelLayout.setVerticalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectPanelLayout.createSequentialGroup()
                .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subjectLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

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

        bodyTextArea.setContentType("text/html");
        jScrollPane5.setViewportView(bodyTextArea);

        javax.swing.GroupLayout outerMostPanelLayout = new javax.swing.GroupLayout(outerMostPanel);
        outerMostPanel.setLayout(outerMostPanelLayout);
        outerMostPanelLayout.setHorizontalGroup(
            outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outerMostPanelLayout.createSequentialGroup()
                        .addComponent(emailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(outerMostPanelLayout.createSequentialGroup()
                        .addGap(0, 537, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outerMostPanelLayout.createSequentialGroup()
                        .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(subjectPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5))
                        .addContainerGap())))
        );
        outerMostPanelLayout.setVerticalGroup(
            outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerMostPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(emailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subjectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(outerMostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerMostPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        emailbodyText = bodyTextArea.getText();
        System.out.println("text entered is " + emailbodyText);
//    	ArrayList emailId = new ArrayList();
////    	for(int i= 0;i<5;i++){
//    		emailId.add("piyush.c@msronline.in");
//    		
////    	}
        if (emailbodyText != null && !(emailbodyText.equals(""))) {


            boolean flag = false;
            try {
                new ThreadUtil(toEmailList, ccEmailList, bccEmailList, subjectTextField.getText(), txId, emailbodyText).start();
                flag = true;

            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
            }

            System.out.println("flag status is " + flag);
            if (flag) {

                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                System.out.println("clientDetailsBuffer: " + meetingSaveUrl);

                this.dispose();
                JOptionPane.showMessageDialog(this, "Case Summary sent successfully");

            } else {
                JOptionPane.showMessageDialog(null, "Error In Sending Mail. Please Contact Your Network Provider");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter Mail Content");
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_sendButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
//        JOptionPane.showMessageDialog(null, "Meeting Details Saved Successfully, Please fill Proposal details.", "", 1);
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
                 bccDelButton.setText("X");
                 bccDelButton.setBackground(new java.awt.Color(12, 60, 111));
                 bccDelButton.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
                 bccDelButton.setForeground(new java.awt.Color(255, 0, 0));
                 bccDelButton.setBorder(BorderFactory.createEmptyBorder());
//             	deleteButton.setText("Close");
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
            java.util.logging.Logger.getLogger(MeetingEmailCaseSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MeetingEmailCaseSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MeetingEmailCaseSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MeetingEmailCaseSummary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new EmailScreen1().setVisible(true);
                MeetingDetailBean meetingBean = new MeetingDetailBean();
                meetingBean.setCompanyName("abc");
                meetingBean.setTurnOver("");
                meetingBean.setContactPerson("Don");
                meetingBean.setMobNumber("100");
                meetingBean.setMeetingPlace("Green Park");
                meetingBean.setPurpose("timePass");
                new MeetingEmailCaseSummary(meetingBean).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bccIdPanel;
    private javax.swing.JLabel bccLabel;
    private javax.swing.JTextPane bodyTextArea;
    private javax.swing.JPanel ccIdPanel;
    private javax.swing.JLabel ccLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel emailPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel outerMostPanel;
    private javax.swing.JButton sendButton;
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
}
