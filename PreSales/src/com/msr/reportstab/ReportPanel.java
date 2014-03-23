
package com.msr.reportstab;



import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.parser.ParserCaller;
import com.parser.UserXMLReader;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.*;


import org.jdesktop.swingx.JXDatePicker;
/**
 *
 * @author piyush
 * @Since march 05, 2013
 * @version V1.0
 */

public class ReportPanel extends javax.swing.JPanel 
{

    
    private String email = null;
    private boolean isChecked = false;
    MainScreen mainScreen = null;
    
     //========================================================================     
        JXDatePicker dueDateFrom = new JXDatePicker();
        JXDatePicker dueDateTo = new JXDatePicker();        

    //======================================================================           
        SimpleDateFormat tempFormat = new SimpleDateFormat("dd-MM-yyyy");    
      
    public ReportPanel(String email, MainScreen mainScreen)
    {     this.email = email;
    this.mainScreen = mainScreen;
        initComponents();
       
     //======================================================================           
        System.out.println("pan2.getWidth() "+toDatePan.getWidth());
        dueDateTo.setBounds(0, 0, 130, 20);
        dueDateTo.setFont(new Font("Serif", Font.BOLD, 13));
        toDatePan.add(dueDateTo);
        dueDateTo.setDate(null);
        dueDateTo.setFormats(tempFormat);  
   
        dueDateFrom.setBounds(0, 0, 130, 20);
        dueDateFrom.setFont(new Font("Serif", Font.BOLD, 13));
        fromDatepan.add(dueDateFrom);
        dueDateFrom.setDate(null);
        dueDateFrom.setFormats(tempFormat); 
    //=======================================================================
     bankNameCB.addItem("--Select Bank--");
     rmNameCB.addItem("--Select RM--");
     clientNameCB.addItem("--Select Client--");
//     trnxTypeCB.addItem("--Select Transaction Type--");
     
        List<String> clientList = new ParserCaller().parseClientListXml(email);
        Iterator<String> lst = clientList.iterator();
        while(lst.hasNext()){
            clientNameCB.addItem(lst.next().toString());
        }
     
        List<String> bankList = new UserXMLReader().getBankListFromXML(email);
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+bankList.size());
        Iterator<String> itr = bankList.iterator();
        while(itr.hasNext()){
            bankNameCB.addItem(itr.next().toString());
        }
        
        List<String> rmList = new UserXMLReader().getRMListFromXML(email);
        System.out.println("rmList >>>>>>"+rmList.size());
        Iterator<String> rmItr = rmList.iterator();
        while(rmItr.hasNext()){
             rmNameCB.addItem(rmItr.next().toString());
        }
        if(SingletonClass.designation.equals("RM")){
            rmNameCB.setSelectedItem(SingletonClass.loginUserName);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outerPanel1 = new javax.swing.JPanel();
        leftPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        clientNameCB = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        rmNameCB = new javax.swing.JComboBox();
        categoryCB = new javax.swing.JComboBox();
        trnxTypeCB = new javax.swing.JComboBox();
        bankNameCB = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        statusCB = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fromDatepan = new javax.swing.JPanel();
        toDatePan = new javax.swing.JPanel();
        generateButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        meetingCheckBox = new javax.swing.JCheckBox();
        trnxCheckBox = new javax.swing.JCheckBox();
        queryCheckBox = new javax.swing.JCheckBox();
        historyCheckBox = new javax.swing.JCheckBox();

        setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N

        outerPanel1.setBackground(new java.awt.Color(255, 255, 255));
        outerPanel1.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-230));

        leftPanel1.setPreferredSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width/2)-20,Toolkit.getDefaultToolkit().getScreenSize().height-275));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 103, 204));
        jLabel9.setText("Client Name");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 103, 204));
        jLabel16.setText("Status");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 103, 204));
        jLabel19.setText("Category");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 103, 204));
        jLabel20.setText("Bank Name");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 103, 204));
        jLabel21.setText("Transaction Type");

        rmNameCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmNameCBActionPerformed(evt);
            }
        });

        categoryCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Category--","Bullion" , "Emerging Corporate", "Large Corporate","Mid Corporate", "SME Client" }));

        trnxTypeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Transaction Type--","New Solution" ,"Existing-New Solution","Existing Enhancement","Existing- Renewal" }));
        trnxTypeCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                trnxTypeCBItemStateChanged(evt);
            }
        });

        bankNameCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bankNameCBMouseClicked(evt);
            }
        });
        bankNameCB.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bankNameCBFocusGained(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 103, 204));
        jLabel22.setText("RM Name");

        statusCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Status--","Identification Of Client" ,"Awaiting Internal Approval", "Send Offer Letter","Awaiting Client Approval","Awaiting Mandate", "Awaiting Documents", "Create Limit", "Awaiting Limit Setup","Limit Sanctioned", "Portfolio Client", "Expiry Transition", "Opportunity Lost"  }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 103, 204));
        jLabel10.setText("Date");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 103, 204));
        jLabel7.setText("From");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 103, 204));
        jLabel1.setText("To");

        fromDatepan.setBackground(new java.awt.Color(204, 204, 204));
        fromDatepan.setPreferredSize(new java.awt.Dimension(175, 30));

        javax.swing.GroupLayout fromDatepanLayout = new javax.swing.GroupLayout(fromDatepan);
        fromDatepan.setLayout(fromDatepanLayout);
        fromDatepanLayout.setHorizontalGroup(
            fromDatepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        fromDatepanLayout.setVerticalGroup(
            fromDatepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        toDatePan.setBackground(new java.awt.Color(204, 204, 204));
        toDatePan.setPreferredSize(new java.awt.Dimension(175, 30));

        javax.swing.GroupLayout toDatePanLayout = new javax.swing.GroupLayout(toDatePan);
        toDatePan.setLayout(toDatePanLayout);
        toDatePanLayout.setHorizontalGroup(
            toDatePanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );
        toDatePanLayout.setVerticalGroup(
            toDatePanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout leftPanel1Layout = new javax.swing.GroupLayout(leftPanel1);
        leftPanel1.setLayout(leftPanel1Layout);
        leftPanel1Layout.setHorizontalGroup(
            leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(fromDatepan, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(toDatePan, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                    .addComponent(clientNameCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rmNameCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trnxTypeCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bankNameCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        leftPanel1Layout.setVerticalGroup(
            leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanel1Layout.createSequentialGroup()
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanel1Layout.createSequentialGroup()
                        .addContainerGap(45, Short.MAX_VALUE)
                        .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(37, 37, 37))
                    .addGroup(leftPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fromDatepan, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toDatePan, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusCB, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientNameCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rmNameCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankNameCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trnxTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        generateButton.setBackground(new java.awt.Color(0, 103, 204));
        generateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        generateButton.setForeground(new java.awt.Color(204, 204, 204));
        generateButton.setText("GENERATE REPORT");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        resetButton.setBackground(new java.awt.Color(0, 103, 204));
        resetButton.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        resetButton.setForeground(new java.awt.Color(204, 204, 204));
        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("REPORTS");

        meetingCheckBox.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        meetingCheckBox.setForeground(new java.awt.Color(0, 103, 204));
        meetingCheckBox.setText("Meetings");
        meetingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetingCheckBoxActionPerformed(evt);
            }
        });

        trnxCheckBox.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        trnxCheckBox.setForeground(new java.awt.Color(0, 103, 204));
        trnxCheckBox.setText("Transactions");
        trnxCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trnxCheckBoxActionPerformed(evt);
            }
        });

        queryCheckBox.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        queryCheckBox.setForeground(new java.awt.Color(0, 103, 204));
        queryCheckBox.setText("Queries");
        queryCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryCheckBoxActionPerformed(evt);
            }
        });

        historyCheckBox.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        historyCheckBox.setForeground(new java.awt.Color(0, 103, 204));
        historyCheckBox.setText("History");
        historyCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(meetingCheckBox)
                .addGap(18, 18, 18)
                .addComponent(queryCheckBox)
                .addGap(18, 18, 18)
                .addComponent(trnxCheckBox)
                .addGap(18, 18, 18)
                .addComponent(historyCheckBox)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(queryCheckBox)
                    .addComponent(meetingCheckBox)
                    .addComponent(trnxCheckBox)
                    .addComponent(historyCheckBox))
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout outerPanel1Layout = new javax.swing.GroupLayout(outerPanel1);
        outerPanel1.setLayout(outerPanel1Layout);
        outerPanel1Layout.setHorizontalGroup(
            outerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outerPanel1Layout.createSequentialGroup()
                        .addComponent(leftPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(outerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(outerPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(generateButton)
                                .addGap(18, 18, 18)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        outerPanel1Layout.setVerticalGroup(
            outerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outerPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(outerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(outerPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245)
                        .addGroup(outerPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(leftPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1184, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        mainScreen.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String SelectedValue = "";
        if(queryCheckBox.isSelected()){
            SelectedValue = "query";
        } else if(meetingCheckBox.isSelected()){
            SelectedValue = "meeting";
        } else if(historyCheckBox.isSelected()){
            SelectedValue = "history";
        } else{
            SelectedValue = "transaction";
        }
        String status = "";
        String bank = "";
        String category = "";
        String client = "";
        String rmName = "";
        String trnxType = "";
        String toDate = "";
        String fromDate = "";
        System.out.println("Duedate to is "+dueDateFrom.getDate());
        if(dueDateFrom.getDate()!=null ){
            fromDate = SingletonClass.replaceEmptySpace(new SimpleDateFormat("dd-MM-yyyy").format(dueDateFrom.getDate()));
        }
        if(dueDateTo.getDate()!=null){
            toDate = SingletonClass.replaceEmptySpace(new SimpleDateFormat("dd-MM-yyyy").format(dueDateTo.getDate()));
        }
        
        if(statusCB.getSelectedIndex()!=0){
            status = statusCB.getSelectedItem().toString();
        }
        if(bankNameCB.getSelectedIndex()!=0){
            bank = bankNameCB.getSelectedItem().toString();
        }
        if(categoryCB.getSelectedIndex()!=0){
            category = categoryCB.getSelectedItem().toString();
        }
        if(clientNameCB.getSelectedIndex()!=0){
            client = clientNameCB.getSelectedItem().toString();
        }
        if(rmNameCB.getSelectedIndex()!=0){
            rmName = rmNameCB.getSelectedItem().toString();
        }
        if(trnxTypeCB.getSelectedIndex()!=0){
            trnxType = trnxTypeCB.getSelectedItem().toString();
        }
        System.out.println(" todate is "+toDate +" form "+fromDate);
        toDate = SingletonClass.replaceEmptySpace(toDate);
        fromDate = SingletonClass.replaceEmptySpace(fromDate);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status", status);
        map.put("bank", bank);
        map.put("category", category);
        map.put("client", client);
        map.put("rmName", rmName);
        map.put("trnxType", trnxType);
        map.put("toDate", toDate);
        map.put("fromDate", fromDate);
        ViewReportsFrame reportsFrame = new ViewReportsFrame(SelectedValue, email, map);
        reportsFrame.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-30,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 200);
        reportsFrame.setVisible(true);
        
        mainScreen.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_generateButtonActionPerformed

private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed

    clientNameCB.removeAllItems();
            clientNameCB.addItem("--Select Client--");
        List<String> clientList = new ParserCaller().parseClientListXml(email);
        Iterator<String> lst = clientList.iterator();
        while(lst.hasNext()){
            clientNameCB.addItem(lst.next().toString());
        }
        dueDateFrom.setDate(null);
        dueDateTo.setDate(null);
        statusCB.setSelectedIndex(0);
        bankNameCB.setSelectedIndex(0);
        rmNameCB.setSelectedIndex(0);
        categoryCB.setSelectedIndex(0);
        trnxTypeCB.setSelectedIndex(0);
        meetingCheckBox.setSelected(false);
        trnxCheckBox.setSelected(false);
        queryCheckBox.setSelected(false);
              
}//GEN-LAST:event_resetButtonActionPerformed

    private void trnxTypeCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_trnxTypeCBItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_trnxTypeCBItemStateChanged

    private void bankNameCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bankNameCBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bankNameCBMouseClicked

    private void bankNameCBFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bankNameCBFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_bankNameCBFocusGained

    private void rmNameCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmNameCBActionPerformed
        // TODO add your handling code here:
    clientNameCB.removeAllItems();
        if(rmNameCB.getSelectedIndex()==0){
    
            clientNameCB.addItem("--Select Client--");
        List<String> clientList = new ParserCaller().parseClientListXml(email);
        Iterator<String> lst = clientList.iterator();
        while(lst.hasNext()){
            clientNameCB.addItem(lst.next().toString());
        }
        }else {
            
        clientNameCB.addItem("--Select Client--");
        System.out.println(" rmNameCB.getSelectedItem() "+rmNameCB.getSelectedItem() +" rmNameCB "+rmNameCB);
        List<String> clientList = new UserXMLReader().parseClientListByRMXml(email,rmNameCB.getSelectedItem().toString());
        Iterator<String> lst = clientList.iterator();
        while(lst.hasNext()){
            clientNameCB.addItem(lst.next().toString());
        }
        }
    }//GEN-LAST:event_rmNameCBActionPerformed

    private void meetingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetingCheckBoxActionPerformed
        // TODO add your handling code here:
        if(meetingCheckBox.isSelected()){
            isChecked = true;
            statusCB.setEnabled(true);
            categoryCB.setEnabled(true);
            rmNameCB.setEnabled(true);
            trnxTypeCB.setEnabled(true);
        }
        else if(!meetingCheckBox.isSelected()){
            isChecked = false;
        }
        trnxCheckBox.setSelected(false);
        queryCheckBox.setSelected(false);
        historyCheckBox.setSelected(false);
    }//GEN-LAST:event_meetingCheckBoxActionPerformed

    private void queryCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryCheckBoxActionPerformed
        // TODO add your handling code here:
        if(queryCheckBox.isSelected()){
            isChecked = true;
            statusCB.setEnabled(true);
            categoryCB.setEnabled(true);
            rmNameCB.setEnabled(true);
            trnxTypeCB.setEnabled(true);
        }
        else if(!queryCheckBox.isSelected()){
            isChecked = false;
        }
        trnxCheckBox.setSelected(false);
        meetingCheckBox.setSelected(false);
        historyCheckBox.setSelected(false);
    }//GEN-LAST:event_queryCheckBoxActionPerformed

    private void trnxCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trnxCheckBoxActionPerformed
        // TODO add your handling code here:
        if(trnxCheckBox.isSelected()){
            isChecked = true;
            statusCB.setEnabled(true);
            categoryCB.setEnabled(true);
            rmNameCB.setEnabled(true);
            trnxTypeCB.setEnabled(true);
        }
        else if(!trnxCheckBox.isSelected()){
            isChecked = false;
        }
        meetingCheckBox.setSelected(false);
        queryCheckBox.setSelected(false);
        historyCheckBox.setSelected(false);
    }//GEN-LAST:event_trnxCheckBoxActionPerformed

    private void historyCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyCheckBoxActionPerformed
        // TODO add your handling code here:
        if(historyCheckBox.isSelected()){
            isChecked = true;
            statusCB.setEnabled(false);
            categoryCB.setEnabled(false);
            rmNameCB.setEnabled(false);
            trnxTypeCB.setEnabled(false);
        }
        else if(!historyCheckBox.isSelected()){
            isChecked = false;
            statusCB.setEnabled(true);
            categoryCB.setEnabled(true);
            rmNameCB.setEnabled(true);
            trnxTypeCB.setEnabled(true);
        }
        meetingCheckBox.setSelected(false);
        queryCheckBox.setSelected(false);
        trnxCheckBox.setSelected(false);
    }//GEN-LAST:event_historyCheckBoxActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox bankNameCB;
    private javax.swing.JComboBox categoryCB;
    private javax.swing.JComboBox clientNameCB;
    private javax.swing.JPanel fromDatepan;
    private javax.swing.JButton generateButton;
    private javax.swing.JCheckBox historyCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel leftPanel1;
    private javax.swing.JCheckBox meetingCheckBox;
    private javax.swing.JPanel outerPanel1;
    private javax.swing.JCheckBox queryCheckBox;
    private javax.swing.JButton resetButton;
    private javax.swing.JComboBox rmNameCB;
    private javax.swing.JComboBox statusCB;
    private javax.swing.JPanel toDatePan;
    private javax.swing.JCheckBox trnxCheckBox;
    private javax.swing.JComboBox trnxTypeCB;
    // End of variables declaration//GEN-END:variables
}
