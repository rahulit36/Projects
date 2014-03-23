package com.msr.tools.UI;

import com.msr.util.SingletonClass;
import com.parser.PreUserDetails;
import com.parser.UserXMLReader;
import java.util.*;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class ViewUserPanel extends javax.swing.JPanel {

    List userList = null;// fetch from DetailViewOfAccordion class
    private ToolsEditViewFrame tools = null;
HashMap<String, List<String>> managerMap = null;
    /**
     *
     */
    public ViewUserPanel() {
    }

    /**
     *
     * @param userId
     * @param toolsViewFrame
     */
    // fetch user details from database and according to them select the prvileges in checkbox
    public ViewUserPanel(String uName, int userId, ToolsEditViewFrame toolsViewFrame, String userEmail) {
        initComponents();
        this.setVisible(true);
        this.tools = toolsViewFrame;
       
        userTypeCB.addItem("---Select Designation---");
        userTypeCB.addItem("Super Admin");
        userTypeCB.addItem("Vertical Head");
        userTypeCB.addItem("RM");
        userTypeCB.addItem("Credit Analyst");
        String[] str = new String[10];

         managerCombo.addItem("--Select Manager--");
         managerMap = new UserXMLReader().getManagerListFromXML(uName);
//        System.out.println("rmList >>>>>>"+rmList.size());
//        Iterator<String> rmItr = rmList.iterator();
//        while(rmItr.hasNext()){
//             managerCombo.addItem(rmItr.next().toString());
//        }
        
        PreUserDetails preuserBean = new UserXMLReader().parseUserDetails(uName, userEmail);
        
        
        nameTextField.setText(SingletonClass.replaceEmptySpace(preuserBean.getUserName()));
        nameTextField.setEditable(false);
        emailTextField.setText(SingletonClass.replaceEmptySpace(preuserBean.getUserEmail()));
        emailTextField.setEditable(false);
        phoneNoTextField.setText(SingletonClass.replaceEmptySpace(preuserBean.getMobNo()));
        phoneNoTextField.setEditable(false);
        userTypeCB.setSelectedItem(preuserBean.getDesingation().toString());
        managerCombo.setSelectedItem(preuserBean.getRmManager().toString());
        userTypeCB.setEnabled(false);
        managerCombo.setEnabled(false);

        if(preuserBean.getIsActive()=='y'||preuserBean.getIsActive()=='Y'){
                    isActiveCB.setSelected(true);
        }else{
        isActiveCB.setSelected(false);
        }
        String emailnotify = SingletonClass.replaceEmptySpace(preuserBean.getEmailNotification());
        System.out.println("email notify list is "+emailnotify);
        String[] emailnotifyArr = emailnotify.split(",");
        clientidentificationCB.setSelected(false);
        internalApprovalCB.setSelected(false);
        sendOfferLetterCB.setSelected(false);
        awaitingClientApprovalCB.setSelected(false);
        awaitingMainDateCB.setSelected(false);
        awaitingDocumentCB.setSelected(false);
        createLimitCB.setSelected(false);
        awaitingLcCB.setSelected(false);
        portfolioCB.setSelected(false);
        expirylimitCB.setSelected(false);
        limitSetupCB.setSelected(false);
        businesslostCB.setSelected(false);
        
         for(String notifyStr:emailnotifyArr){
             notifyStr.trim();
            if(notifyStr.equals(clientidentificationCB.getText())){
                 clientidentificationCB.setSelected(true);
            }else if(notifyStr.equals(internalApprovalCB.getText())){
                 internalApprovalCB.setSelected(true);
            }else if(notifyStr.equals(sendOfferLetterCB.getText())){
                 sendOfferLetterCB.setSelected(true);
            }else if(notifyStr.equals(awaitingClientApprovalCB.getText())){
                 awaitingClientApprovalCB.setSelected(true);
            }else if(notifyStr.equals(awaitingMainDateCB.getText())){
                 awaitingMainDateCB.setSelected(true);
            }else if(notifyStr.equals(awaitingDocumentCB.getText())){
                 awaitingDocumentCB.setSelected(true);
            }else if(notifyStr.equals(createLimitCB.getText())){
                 createLimitCB.setSelected(true);
            }else if(notifyStr.equals(awaitingLcCB.getText())){
                 awaitingLcCB.setSelected(true);
            }else if(notifyStr.equals(limitSetupCB.getText())){
                 limitSetupCB.setSelected(true);
            }else if(notifyStr.equals(portfolioCB.getText())){
                 portfolioCB.setSelected(true);
            }else if(notifyStr.equals(expirylimitCB.getText())){
                 expirylimitCB.setSelected(true);
            }else if(notifyStr.equals(businesslostCB.getText())){
                 businesslostCB.setSelected(true);
            }
        }


        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        innerPanel = new javax.swing.JPanel();
        titalPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        isActiveCB = new javax.swing.JCheckBox();
        designationLabel = new javax.swing.JLabel();
        userTypeCB = new javax.swing.JComboBox();
        phoneNoLabel = new javax.swing.JLabel();
        phoneNoTextField = new javax.swing.JTextField();
        managerCombo = new javax.swing.JComboBox();
        designationLabel1 = new javax.swing.JLabel();
        emailPrivilegeLabel1 = new javax.swing.JLabel();
        phoneNoLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        clientidentificationCB = new javax.swing.JCheckBox();
        internalApprovalCB = new javax.swing.JCheckBox();
        sendOfferLetterCB = new javax.swing.JCheckBox();
        awaitingClientApprovalCB = new javax.swing.JCheckBox();
        awaitingMainDateCB = new javax.swing.JCheckBox();
        awaitingDocumentCB = new javax.swing.JCheckBox();
        createLimitCB = new javax.swing.JCheckBox();
        awaitingLcCB = new javax.swing.JCheckBox();
        limitSetupCB = new javax.swing.JCheckBox();
        portfolioCB = new javax.swing.JCheckBox();
        expirylimitCB = new javax.swing.JCheckBox();
        businesslostCB = new javax.swing.JCheckBox();
        emailPrivilegeLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();

        innerPanel.setBackground(new java.awt.Color(255, 255, 255));

        titalPanel1.setBackground(new java.awt.Color(21, 21, 142));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("View User");

        javax.swing.GroupLayout titalPanel1Layout = new javax.swing.GroupLayout(titalPanel1);
        titalPanel1.setLayout(titalPanel1Layout);
        titalPanel1Layout.setHorizontalGroup(
            titalPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titalPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titalPanel1Layout.setVerticalGroup(
            titalPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        bodyPanel.setBackground(new java.awt.Color(210, 223, 219));

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(0, 103, 204));
        nameLabel.setText("Name");

        nameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTextFieldFocusLost(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(0, 103, 204));
        emailLabel.setText("E-mail Id");

        emailTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusLost(evt);
            }
        });

        isActiveCB.setBackground(new java.awt.Color(255, 255, 255));
        isActiveCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        isActiveCB.setForeground(new java.awt.Color(0, 103, 204));
        isActiveCB.setToolTipText("Block / Unblock User");
        isActiveCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isActiveCBActionPerformed(evt);
            }
        });

        designationLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        designationLabel.setForeground(new java.awt.Color(0, 103, 204));
        designationLabel.setText("Designation");

        userTypeCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userTypeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTypeCBActionPerformed(evt);
            }
        });

        phoneNoLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phoneNoLabel.setForeground(new java.awt.Color(0, 103, 204));
        phoneNoLabel.setText("Phone No.");

        phoneNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNoTextFieldActionPerformed(evt);
            }
        });
        phoneNoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneNoTextFieldFocusLost(evt);
            }
        });

        managerCombo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        managerCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerComboActionPerformed(evt);
            }
        });

        designationLabel1.setFont(designationLabel1.getFont().deriveFont(designationLabel1.getFont().getStyle() | java.awt.Font.BOLD, designationLabel1.getFont().getSize()+1));
        designationLabel1.setForeground(new java.awt.Color(0, 103, 204));
        designationLabel1.setText("Manager");

        emailPrivilegeLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailPrivilegeLabel1.setForeground(new java.awt.Color(0, 103, 204));
        emailPrivilegeLabel1.setText("Employee's details");

        phoneNoLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        phoneNoLabel1.setForeground(new java.awt.Color(0, 103, 204));
        phoneNoLabel1.setText("Allow Access");

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailPrivilegeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createSequentialGroup()
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(designationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(designationLabel1)
                            .addComponent(emailLabel)
                            .addComponent(phoneNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNoLabel1))
                        .addGap(96, 96, 96)
                        .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nameTextField)
                                .addComponent(userTypeCB, 0, 228, Short.MAX_VALUE)
                                .addComponent(managerCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailTextField))
                            .addComponent(isActiveCB))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(emailPrivilegeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(designationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(designationLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isActiveCB)
                    .addComponent(phoneNoLabel1))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(210, 223, 219));

        clientidentificationCB.setBackground(new java.awt.Color(255, 255, 255));
        clientidentificationCB.setText("Identification Of Client");

        internalApprovalCB.setBackground(new java.awt.Color(255, 255, 255));
        internalApprovalCB.setText("Awaiting Interval Approval");

        sendOfferLetterCB.setBackground(new java.awt.Color(255, 255, 255));
        sendOfferLetterCB.setText("Send Offer letter");

        awaitingClientApprovalCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingClientApprovalCB.setText("Awaiting Client Approval");

        awaitingMainDateCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingMainDateCB.setText("Awaiting Main Date");

        awaitingDocumentCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingDocumentCB.setText("Awaiting Document");

        createLimitCB.setBackground(new java.awt.Color(255, 255, 255));
        createLimitCB.setText("Create Limit");

        awaitingLcCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingLcCB.setText("Awaiting LC Setup");

        limitSetupCB.setBackground(new java.awt.Color(255, 255, 255));
        limitSetupCB.setText("Limit Sanctioned");

        portfolioCB.setBackground(new java.awt.Color(255, 255, 255));
        portfolioCB.setText("Process or Provisional Client");

        expirylimitCB.setBackground(new java.awt.Color(255, 255, 255));
        expirylimitCB.setText("Expire Limit Time");
        expirylimitCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expirylimitCBActionPerformed(evt);
            }
        });

        businesslostCB.setText("Business Lost");

        emailPrivilegeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailPrivilegeLabel.setForeground(new java.awt.Color(0, 103, 204));
        emailPrivilegeLabel.setText("E-mail Privilege");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(awaitingMainDateCB)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(limitSetupCB)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(clientidentificationCB)
                                .addGap(30, 30, 30)
                                .addComponent(internalApprovalCB))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sendOfferLetterCB)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(emailPrivilegeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(createLimitCB, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(67, 67, 67))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(expirylimitCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(52, 52, 52)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(businesslostCB)
                                    .addComponent(awaitingDocumentCB, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(awaitingClientApprovalCB)
                                    .addComponent(awaitingLcCB, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(portfolioCB))))
                        .addContainerGap(109, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(emailPrivilegeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientidentificationCB)
                    .addComponent(internalApprovalCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sendOfferLetterCB)
                    .addComponent(awaitingClientApprovalCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(awaitingMainDateCB)
                    .addComponent(awaitingDocumentCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createLimitCB)
                    .addComponent(awaitingLcCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limitSetupCB)
                    .addComponent(portfolioCB))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expirylimitCB)
                    .addComponent(businesslostCB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        closeButton.setBackground(new java.awt.Color(0, 103, 204));
        closeButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("CLOSE");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout innerPanelLayout = new javax.swing.GroupLayout(innerPanel);
        innerPanel.setLayout(innerPanelLayout);
        innerPanelLayout.setHorizontalGroup(
            innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(innerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(innerPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(innerPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(titalPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        innerPanelLayout.setVerticalGroup(
            innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(innerPanelLayout.createSequentialGroup()
                .addComponent(titalPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(innerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(innerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(innerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void nameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusLost
}//GEN-LAST:event_nameTextFieldFocusLost

private void emailTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusLost
}//GEN-LAST:event_emailTextFieldFocusLost

private void isActiveCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isActiveCBActionPerformed
}//GEN-LAST:event_isActiveCBActionPerformed

private void userTypeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTypeCBActionPerformed

    if(userTypeCB.getSelectedIndex()==1){
        managerCombo.removeAllItems();
    managerCombo.addItem("--Select Manager--");
    managerCombo.setEnabled(false);
} else if(userTypeCB.getSelectedIndex()==2){
    managerCombo.setEnabled(true);
    managerCombo.removeAllItems();
    managerCombo.addItem("--Select Manager--");
    List<String> rmList = managerMap.get("Admin");
        System.out.println("rmList >>>>>>"+rmList.size());
        Iterator<String> rmItr = rmList.iterator();
        while(rmItr.hasNext()){
             managerCombo.addItem(rmItr.next().toString());
        }
        
} else if(userTypeCB.getSelectedIndex()==3||userTypeCB.getSelectedIndex()==4){
    managerCombo.setEnabled(true);
    managerCombo.removeAllItems();
    managerCombo.addItem("--Select Manager--");
    List<String> rmList = managerMap.get("All");
        System.out.println("rmList >>>>>>"+rmList.size());
        Iterator<String> rmItr = rmList.iterator();
        while(rmItr.hasNext()){
             managerCombo.addItem(rmItr.next().toString());
        }
        
}

}//GEN-LAST:event_userTypeCBActionPerformed

private void phoneNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNoTextFieldActionPerformed
}//GEN-LAST:event_phoneNoTextFieldActionPerformed

private void phoneNoTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneNoTextFieldFocusLost
}//GEN-LAST:event_phoneNoTextFieldFocusLost

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    tools.dispose();
}//GEN-LAST:event_closeButtonActionPerformed

    private void expirylimitCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expirylimitCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expirylimitCBActionPerformed

    private void managerComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_managerComboActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox awaitingClientApprovalCB;
    private javax.swing.JCheckBox awaitingDocumentCB;
    private javax.swing.JCheckBox awaitingLcCB;
    private javax.swing.JCheckBox awaitingMainDateCB;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JCheckBox businesslostCB;
    private javax.swing.JCheckBox clientidentificationCB;
    private javax.swing.JButton closeButton;
    private javax.swing.JCheckBox createLimitCB;
    private javax.swing.JLabel designationLabel;
    private javax.swing.JLabel designationLabel1;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailPrivilegeLabel;
    private javax.swing.JLabel emailPrivilegeLabel1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JCheckBox expirylimitCB;
    private javax.swing.JPanel innerPanel;
    private javax.swing.JCheckBox internalApprovalCB;
    private javax.swing.JCheckBox isActiveCB;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox limitSetupCB;
    private javax.swing.JComboBox managerCombo;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel phoneNoLabel;
    private javax.swing.JLabel phoneNoLabel1;
    private javax.swing.JTextField phoneNoTextField;
    private javax.swing.JCheckBox portfolioCB;
    private javax.swing.JCheckBox sendOfferLetterCB;
    private javax.swing.JPanel titalPanel1;
    private javax.swing.JComboBox userTypeCB;
    // End of variables declaration//GEN-END:variables
}
