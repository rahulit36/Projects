package com.msr.tools.UI;

import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.UserXMLReader;
import java.awt.Cursor;
import java.awt.Dimension;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class AddUserPanel extends javax.swing.JPanel {

    UserDetailPanel userDetailParent = null;
    AddUserBankClientForm addUserFrame = null;
    MainScreen home = null;
    private String emailString = "";
    HashMap<String, List<String>> managerMap = null;

    /**
     *
     * @param parent
     * @param home
     * @param frame
     */
    public AddUserPanel(UserDetailPanel parent, MainScreen home, AddUserBankClientForm frame, String emailString) {
        this.userDetailParent = parent;
        this.home = home;
        this.addUserFrame = frame;
        this.emailString = emailString;
        initComponents();
        managerCombo.addItem("--Select Manager--");
        isActiveCB.setSelected(true);
        managerMap = new UserXMLReader().getManagerListFromXML(emailString);
//         List<String> rmList = managerMap.get("All");
//        System.out.println("rmList >>>>>>"+rmList.size());
//        Iterator<String> rmItr = rmList.iterator();
//        while(rmItr.hasNext()){
//             managerCombo.addItem(rmItr.next().toString());
//        }
//        

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titalPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        nameValidator = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        isActiveCB = new javax.swing.JCheckBox();
        designationLabel = new javax.swing.JLabel();
        userTypeCB = new javax.swing.JComboBox();
        phoneNoLabel = new javax.swing.JLabel();
        phoneNoTextField = new javax.swing.JTextField();
        emailValidator = new javax.swing.JLabel();
        phoneValidator = new javax.swing.JLabel();
        designationLabel1 = new javax.swing.JLabel();
        managerCombo = new javax.swing.JComboBox();
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
        saveButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(750, 400));
        jPanel1.setPreferredSize(new Dimension(0, 0));

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add New User");

        javax.swing.GroupLayout titalPanelLayout = new javax.swing.GroupLayout(titalPanel);
        titalPanel.setLayout(titalPanelLayout);
        titalPanelLayout.setHorizontalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titalPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titalPanelLayout.setVerticalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
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

        nameValidator.setForeground(new java.awt.Color(212, 30, 30));

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(0, 103, 204));
        emailLabel.setText("E-mail");

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

        designationLabel.setFont(designationLabel.getFont().deriveFont(designationLabel.getFont().getStyle() | java.awt.Font.BOLD, 12));
        designationLabel.setForeground(new java.awt.Color(0, 103, 204));
        designationLabel.setText("Designation");

        userTypeCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userTypeCB.addItem("--Select Designation--");
        userTypeCB.addItem("Super Admin");
        userTypeCB.addItem("Vertical Head");
        userTypeCB.addItem("RM");
        userTypeCB.addItem("Credit Analyst");
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
        phoneNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNoTextFieldKeyReleased(evt);
            }
        });

        emailValidator.setForeground(new java.awt.Color(219, 37, 37));

        phoneValidator.setForeground(new java.awt.Color(212, 30, 30));

        designationLabel1.setFont(designationLabel1.getFont().deriveFont(designationLabel1.getFont().getStyle() | java.awt.Font.BOLD, 12));
        designationLabel1.setForeground(new java.awt.Color(0, 103, 204));
        designationLabel1.setText("Manager");

        managerCombo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        managerCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerComboActionPerformed(evt);
            }
        });

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
                .addGap(35, 35, 35)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(designationLabel1)
                    .addComponent(emailPrivilegeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(phoneNoLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addComponent(designationLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(nameValidator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userTypeCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 216, Short.MAX_VALUE)
                        .addComponent(managerCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailValidator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(phoneNoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addComponent(phoneValidator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(isActiveCB, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameValidator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailPrivilegeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(designationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(designationLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailValidator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneValidator, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(isActiveCB)
                    .addComponent(phoneNoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(210, 223, 219));

        clientidentificationCB.setBackground(new java.awt.Color(255, 255, 255));
        clientidentificationCB.setText("Identification Of Client");

        internalApprovalCB.setBackground(new java.awt.Color(255, 255, 255));
        internalApprovalCB.setText("Awaiting Internal Approval");

        sendOfferLetterCB.setBackground(new java.awt.Color(255, 255, 255));
        sendOfferLetterCB.setText("Send Offer letter");

        awaitingClientApprovalCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingClientApprovalCB.setText("Awaiting Client Approval");

        awaitingMainDateCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingMainDateCB.setText("Awaiting Maindate");
        awaitingMainDateCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                awaitingMainDateCBActionPerformed(evt);
            }
        });

        awaitingDocumentCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingDocumentCB.setText("Awaiting Documents");
        awaitingDocumentCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                awaitingDocumentCBActionPerformed(evt);
            }
        });

        createLimitCB.setBackground(new java.awt.Color(255, 255, 255));
        createLimitCB.setText("Create Limit");

        awaitingLcCB.setBackground(new java.awt.Color(255, 255, 255));
        awaitingLcCB.setText("Awaiting Limit Setup");

        limitSetupCB.setBackground(new java.awt.Color(255, 255, 255));
        limitSetupCB.setText("Limit Sanctioned");

        portfolioCB.setBackground(new java.awt.Color(255, 255, 255));
        portfolioCB.setText("Portfolio Client");

        expirylimitCB.setText("Expiry Transition");
        expirylimitCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expirylimitCBActionPerformed(evt);
            }
        });

        businesslostCB.setText("Opportunity Lost");
        businesslostCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                businesslostCBActionPerformed(evt);
            }
        });

        emailPrivilegeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailPrivilegeLabel.setForeground(new java.awt.Color(0, 103, 204));
        emailPrivilegeLabel.setText("E-mail Previleges");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailPrivilegeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sendOfferLetterCB)
                                    .addComponent(awaitingMainDateCB)
                                    .addComponent(clientidentificationCB))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(awaitingDocumentCB)
                                    .addComponent(awaitingClientApprovalCB)
                                    .addComponent(internalApprovalCB, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(awaitingLcCB)
                                    .addComponent(portfolioCB, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(businesslostCB))))
                        .addContainerGap(68, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createLimitCB)
                            .addComponent(limitSetupCB)
                            .addComponent(expirylimitCB))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(emailPrivilegeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientidentificationCB)
                    .addComponent(internalApprovalCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendOfferLetterCB)
                    .addComponent(awaitingClientApprovalCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expirylimitCB)
                    .addComponent(businesslostCB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        saveButton.setBackground(new java.awt.Color(0, 103, 204));
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        resetButton.setBackground(new java.awt.Color(0, 103, 204));
        resetButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        resetButton.setForeground(new java.awt.Color(255, 255, 255));
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
            .addComponent(titalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1066, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void nameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusLost
    if (nameTextField.getText().equals("")) {
        nameValidator.setText("Please , Fill Name Field");
    } else {
        nameValidator.setText("");
    }
}//GEN-LAST:event_nameTextFieldFocusLost

private void emailTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusLost
    boolean isValidEmail = false;
    if (emailTextField.getText().equals("")) {
        emailValidator.setText("Please , Fill Email Field");
    } else {
        emailValidator.setText("");
    }



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
        
} else if(userTypeCB.getSelectedIndex()==3){ 
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
    if (phoneNoTextField.getText().equals("")) {
        phoneValidator.setText("Please , Fill Phone Field");
    } else {
        phoneValidator.setText("");
    }
}//GEN-LAST:event_phoneNoTextFieldFocusLost

private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
    addUserFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (nameTextField.getText() != null && !(nameTextField.getText().equals(""))) {
        if (emailTextField.getText() != null && !(emailTextField.getText().equals(""))) {
            String emailNotify = getEmailNotifyList();
            boolean isValidEmail = false;
             Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*[._-]*[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*");
        Matcher matchPattern = p.matcher(emailTextField.getText());
        isValidEmail = matchPattern.matches();
        if (isValidEmail) {
        
            if (userTypeCB.getSelectedIndex() != 0) {
              
                 if (managerCombo.getSelectedIndex() != 0 || userTypeCB.getSelectedIndex()==1) {
                String activeUser = "N";
                if(isActiveCB.isSelected()){
                    activeUser = "Y";
                }
//            http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&userEmail=&userPassword=&userName=&emailNotification=&designation=&mobNo=&type=saveUserDetails 
                String url = SingletonClass.httpServerUrl + SingletonClass.operaionType + "=saveUserDetails&userName=" + URLEncoder.encode(nameTextField.getText()) + "&userEmail=" + emailTextField.getText() + "&designation=" + userTypeCB.getSelectedItem() + "&mobNo=" + phoneNoTextField.getText() + "&emailNotification=" + emailNotify + "&" + SingletonClass.userName + "=" + URLEncoder.encode(emailString) +"&isActive="+activeUser+ "&userPassword=123456&dbType=save&manager="+managerCombo.getSelectedItem().toString();
                url = url.replace(" ", "%20");
                System.out.println("url is " + url);
                String urlStatus = new callServerUrl().urlProcessiong(url);
                if (urlStatus.equals("invalid")) {
                    JOptionPane.showMessageDialog(this, "Error in Saving User, Please Try Again.");
                    addUserFrame.setCursor(Cursor.getDefaultCursor());
                } else if(urlStatus.equals("duplicate")){
                    JOptionPane.showMessageDialog(this, "User Name already exist.");
                    addUserFrame.setCursor(Cursor.getDefaultCursor());
                
                }else {

                    JOptionPane.showMessageDialog(this, "Successfully Saved");
                    addUserFrame.setCursor(Cursor.getDefaultCursor());
                    addUserFrame.dispose();
                    userDetailParent.refresh();
                     userDetailParent.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Select Manager of " + nameTextField.getText(), "Add User", JOptionPane.ERROR_MESSAGE);
                addUserFrame.setCursor(Cursor.getDefaultCursor());
            }
                  } else {
                JOptionPane.showMessageDialog(null, "Please Select Designation of " + nameTextField.getText(), "Add User", JOptionPane.ERROR_MESSAGE);
                addUserFrame.setCursor(Cursor.getDefaultCursor());
            }
            
            } else {
            JOptionPane.showMessageDialog(null, "Invalid contact email.", "Add User", 0);
            addUserFrame.setCursor(Cursor.getDefaultCursor());
        }
        } else {
            JOptionPane.showMessageDialog(null, "Please Enter Email Id", "Add User", JOptionPane.ERROR_MESSAGE);
            addUserFrame.setCursor(Cursor.getDefaultCursor());
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please Enter User Name", "Add User", JOptionPane.ERROR_MESSAGE);
        addUserFrame.setCursor(Cursor.getDefaultCursor());
    }




}//GEN-LAST:event_saveButtonActionPerformed

    private String getEmailNotifyList() {
        StringBuffer emailNotifyStr = new StringBuffer(",");

        if (clientidentificationCB.isSelected()) {
            emailNotifyStr.append(clientidentificationCB.getText()).append(",");
        }
        if (internalApprovalCB.isSelected()) {
            emailNotifyStr.append(internalApprovalCB.getText()).append(",");
        }
        if (sendOfferLetterCB.isSelected()) {
            emailNotifyStr.append(sendOfferLetterCB.getText()).append(",");
        }
        if (awaitingClientApprovalCB.isSelected()) {
            emailNotifyStr.append(awaitingClientApprovalCB.getText()).append(",");
        }
        if (awaitingMainDateCB.isSelected()) {
            emailNotifyStr.append(awaitingMainDateCB.getText()).append(",");
        }
        if (awaitingDocumentCB.isSelected()) {
            emailNotifyStr.append(awaitingDocumentCB.getText()).append(",");
        }
        if (createLimitCB.isSelected()) {
            emailNotifyStr.append(createLimitCB.getText()).append(",");
        }
        if (awaitingLcCB.isSelected()) {
            emailNotifyStr.append(awaitingLcCB.getText()).append(",");
        }
        if (limitSetupCB.isSelected()) {
            emailNotifyStr.append(limitSetupCB.getText()).append(",");
        }
        if (portfolioCB.isSelected()) {
            emailNotifyStr.append(portfolioCB.getText()).append(",");
        }
        if (expirylimitCB.isSelected()) {
            emailNotifyStr.append(expirylimitCB.getText()).append(",");
        }
        if (businesslostCB.isSelected()) {
            emailNotifyStr.append(businesslostCB.getText()).append(",");
        }



        return emailNotifyStr.toString();
    }

private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
    nameTextField.setText("");
    nameValidator.setText("");
    emailTextField.setText("");
    emailValidator.setText("");
    phoneNoTextField.setText("");
    phoneValidator.setText("");
    userTypeCB.setSelectedItem("---Select Designation---");
    managerCombo.setSelectedItem("--Select Manager--");
    isActiveCB.setSelected(false);
    clientidentificationCB.setSelected(false);
    internalApprovalCB.setSelected(false);
    sendOfferLetterCB.setSelected(false);
    awaitingClientApprovalCB.setSelected(false);
    awaitingMainDateCB.setSelected(false);
    awaitingDocumentCB.setSelected(false);
    createLimitCB.setSelected(false);
    awaitingLcCB.setSelected(false);
    limitSetupCB.setSelected(false);
    portfolioCB.setSelected(false);
    expirylimitCB.setSelected(false);
    businesslostCB.setSelected(false);

}//GEN-LAST:event_resetButtonActionPerformed

private void expirylimitCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expirylimitCBActionPerformed
}//GEN-LAST:event_expirylimitCBActionPerformed

private void awaitingMainDateCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_awaitingMainDateCBActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_awaitingMainDateCBActionPerformed

private void awaitingDocumentCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_awaitingDocumentCBActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_awaitingDocumentCBActionPerformed

    private void businesslostCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_businesslostCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_businesslostCBActionPerformed

    private void phoneNoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNoTextFieldKeyReleased
        
        
        boolean isInt = isInt(phoneNoTextField.getText());
        System.out.println("phnPattern.matches(): " + phoneNoTextField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid phone no.", "", 0);
            phoneNoTextField.setText("");
        }
    }//GEN-LAST:event_phoneNoTextFieldKeyReleased

    private void managerComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_managerComboActionPerformed

    public boolean isInt(String integer) {
        boolean isInt = false;
        if (integer.equals("")) {
            isInt = true;
        } else {
            try {
                Long.parseLong(integer);
                isInt = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                isInt = false;
            }
        }
        return isInt;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox awaitingClientApprovalCB;
    private javax.swing.JCheckBox awaitingDocumentCB;
    private javax.swing.JCheckBox awaitingLcCB;
    private javax.swing.JCheckBox awaitingMainDateCB;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JCheckBox businesslostCB;
    private javax.swing.JCheckBox clientidentificationCB;
    private javax.swing.JCheckBox createLimitCB;
    private javax.swing.JLabel designationLabel;
    private javax.swing.JLabel designationLabel1;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailPrivilegeLabel;
    private javax.swing.JLabel emailPrivilegeLabel1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel emailValidator;
    private javax.swing.JCheckBox expirylimitCB;
    private javax.swing.JCheckBox internalApprovalCB;
    private javax.swing.JCheckBox isActiveCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JCheckBox limitSetupCB;
    private javax.swing.JComboBox managerCombo;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel nameValidator;
    private javax.swing.JLabel phoneNoLabel;
    private javax.swing.JLabel phoneNoLabel1;
    private javax.swing.JTextField phoneNoTextField;
    private javax.swing.JLabel phoneValidator;
    private javax.swing.JCheckBox portfolioCB;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox sendOfferLetterCB;
    private javax.swing.JPanel titalPanel;
    private javax.swing.JComboBox userTypeCB;
    // End of variables declaration//GEN-END:variables
}
