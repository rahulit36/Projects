package com.msr.tools.UI;

import com.msr.util.SingletonClass;
import com.parser.ClientLimitBean;
import com.parser.ContactDetailBean;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class ViewCompanyPanel extends javax.swing.JPanel {

    ToolsEditViewFrame toolsViewFrame = null;
    List conList = null;
    List clientLimitList = null;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
    DecimalFormat formatter = new DecimalFormat("##,##0.00", symbols);

    private String convertDecimalViewStr(BigDecimal vaulue) {
        String str = null;
        if (vaulue != null) {
            str = formatter.format(vaulue.doubleValue()).toString();
        }
        return str;
    }

    /**
     *
     * @param compId
     * @param toolsViewFrame
     */
    
    // set the value from database in fields
    public ViewCompanyPanel(String compId, ToolsEditViewFrame toolsViewFrame, String emailStr) {
        initComponents();
        this.toolsViewFrame = toolsViewFrame;

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        clientLimitTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        int counter = 0;
        int clientRowNumber = 0;

        HashMap<String, List> extractClient = new UserXMLReader().extractClientDetailsXml(emailStr, compId);
        List<ContactDetailBean> contactList = extractClient.get("contactList");
        List<Map<String, String>> clientlist = extractClient.get("clientList");
        List<ClientLimitBean> newClientLimitList = extractClient.get("clientLimit");
        HashMap<String, String> newClientDetails = (HashMap<String, String>) clientlist.get(0);
       
        System.out.println("&&&&&&&&&&&&&&&&&&&&"+newClientDetails.get("category"));
        companyNameField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("name")));
        companyNameField.setEditable(false);
        addressField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("address")));
        addressField.setEditable(false);
        phoneNoField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("landline")));
        phoneNoField.setEditable(false);
        faxNoField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("faxNo")));
        faxNoField.setEditable(false);
        categoryCB.setSelectedItem(SingletonClass.replaceEmptySpace(newClientDetails.get("category")));
        categoryCB.setEditable(false);
        turnOverField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("turnOver")));
        turnOverField.setEditable(false);
        yearOfAccFiled.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("accusation")));
        yearOfAccFiled.setEditable(false);
        rmNameField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("rmName")));
        DefaultTableModel model = (DefaultTableModel) personTable.getModel();
//        model.insertRow(0, new Object[]{});
        personTable.setRowHeight(30);
        
        DefaultTableModel limitModel = (DefaultTableModel) clientLimitTable.getModel();
        Iterator utr = newClientLimitList.iterator();
        while(utr.hasNext()){
            ClientLimitBean clBean = (ClientLimitBean)utr.next();
            limitModel.insertRow(clientRowNumber, new Object[]{});
            clientLimitTable.setValueAt(clientRowNumber+1, clientRowNumber, 0);
            clientLimitTable.setEnabled(false);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getBank()), clientRowNumber, 1);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getBeneficiary()), clientRowNumber, 2);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getClientLimit()), clientRowNumber, 3);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getAvailableLimit()), clientRowNumber, 4);
            
        }
        
        Iterator itr = contactList.iterator();
        while(itr.hasNext()){
            ContactDetailBean contactDetailBean = (ContactDetailBean)itr.next();
            model.insertRow(counter, new Object[]{});
            personTable.setValueAt(counter + 1, counter, 0);
            personTable.setEnabled(false);
        personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactName()), counter, 1);
        personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactDesignation()), counter, 2);
        personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactMobile()), counter, 3);
        personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactEmail()), counter, 4);
        personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactEmailPriv()).replaceFirst(",", ""), counter, 5);
       
        counter++;
        
            
        }
        
        
//        personTable.setValueAt(counter + 1, counter, 0);
//        personTable.setValueAt("", counter, 1);
//        personTable.setValueAt("", counter, 2);
//        personTable.setValueAt("", counter, 3);
//        personTable.setValueAt("", counter, 4);
//        personTable.setValueAt("", counter, 5);
//        counter++;
//        model.insertRow(counter, new Object[]{});
        clientLimitTable.setRowHeight(30);
//        clientLimitTable.setValueAt("", counter, 1);
//        clientLimitTable.setValueAt(convertDecimalViewStr(new BigDecimal(1)), counter, 2);
        counter++;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        companyNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        phoneNoField = new javax.swing.JTextField();
        faxNoField = new javax.swing.JTextField();
        turnOverField = new javax.swing.JTextField();
        yearOfAccFiled = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        categoryCB = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        rmNameField = new javax.swing.JTextField();
        titalPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        personTable = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        clientLimitTable = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(210, 223, 219));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 103, 204));
        jLabel1.setText("Company Name");

        companyNameField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 103, 204));
        jLabel2.setText("Address ");

        addressField.setColumns(20);
        addressField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addressField.setRows(5);
        jScrollPane1.setViewportView(addressField);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 103, 204));
        jLabel3.setText("Phone No.");

        phoneNoField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        faxNoField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        turnOverField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        yearOfAccFiled.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 103, 204));
        jLabel5.setText("Fax No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 103, 204));
        jLabel4.setText("Company Turn Over (in crore(s)):");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 103, 204));
        jLabel6.setText("Year of Accusation");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 103, 204));
        jLabel9.setText("Category");

        categoryCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        categoryCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Category--","Bullion" , "Emerging Corporate", "Large Corporate","Mid Corporate", "SME Client"}));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 103, 204));
        jLabel10.setText("RM Assigned");

        rmNameField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rmNameField)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(turnOverField)
                            .addComponent(faxNoField)
                            .addComponent(phoneNoField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(companyNameField)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(yearOfAccFiled)))
                    .addComponent(categoryCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, 221, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(companyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(faxNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(turnOverField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearOfAccFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rmNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("View  Company");

        javax.swing.GroupLayout titalPanelLayout = new javax.swing.GroupLayout(titalPanel);
        titalPanel.setLayout(titalPanelLayout);
        titalPanelLayout.setHorizontalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titalPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1092, Short.MAX_VALUE))
        );
        titalPanelLayout.setVerticalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        JTableHeader header = personTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,30));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        personTable.setFont(new java.awt.Font("Tahoma", 1, 12));
        personTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Contact Person", "Designation", "Mobile Number", "E-mail Id","Email Previlages"
            }
        ));
        personTable.setRowHeight(30);
        TableColumn sNoCol = this.personTable.getColumnModel().getColumn(0);
        sNoCol.setWidth(50);
        sNoCol.setMaxWidth(50);
        sNoCol.setResizable(false);
        personTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(personTable);

        closeButton.setBackground(new java.awt.Color(0, 103, 204));
        closeButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(210, 223, 219));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 103, 204));
        jLabel8.setText("Add Discounting Bank & Client Limit");

        JTableHeader head = clientLimitTable.getTableHeader();
        head.setBackground(new Color(192, 191, 200));
        head.setPreferredSize(new Dimension(600,30));
        Font font1 = new Font("Serif", Font.BOLD, 14);
        head.setForeground(new Color(0, 103, 204));

        head.setFont(font1);
        clientLimitTable.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        clientLimitTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "","Discounting Bank ", "Beneficiary" , "Client Limit", "Available Limit"
            }
        ));
        TableColumn sNoTab = this.clientLimitTable.getColumnModel().getColumn(0);
        sNoTab.setWidth(50);
        sNoTab.setMaxWidth(50);
        sNoTab.setResizable(false);
        clientLimitTable.setRowHeight(30);
        clientLimitTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientLimitTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(clientLimitTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(378, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
                    .addComponent(titalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(627, 627, 627)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clientLimitTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientLimitTableMouseClicked
    }//GEN-LAST:event_clientLimitTableMouseClicked

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        toolsViewFrame.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void personTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personTableMouseClicked

         int row = personTable.rowAtPoint(evt.getPoint());
    int col = personTable.columnAtPoint(evt.getPoint());
    if (col == 5) {
        System.out.println("<<<<<<<<<");
        JCheckBox[] checkbox = new JCheckBox[]{new JCheckBox("Send Preliminary Offer"),new JCheckBox( "Send SLA"),new JCheckBox( "Send Document List"),new JCheckBox( "Send Credit Advice"),new JCheckBox( "Send Bills" )};
        
       
        if(personTable.getValueAt(row, 5) !=null && !personTable.getValueAt(row, 5).toString().equals("")){
            String getPTText = personTable.getValueAt(row, 5).toString();
        String[] ptArr = getPTText.split(",");
        for(int privCount = 0;privCount<ptArr.length;privCount++){
         for(int checkCount = 0;checkCount<checkbox.length;checkCount++){
             if(checkbox[checkCount].getText().equals(ptArr[privCount])){
                 checkbox[checkCount].setSelected(true);
             }
         }
            }
        }
        String message = "Client is allowed to recieve email for:";
                Object[] params = { message, checkbox };
          JOptionPane.showMessageDialog(toolsViewFrame, params, "Client's Email Priveledge", JOptionPane.PLAIN_MESSAGE);
        
        
    
    }
    }//GEN-LAST:event_personTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addressField;
    private javax.swing.JComboBox categoryCB;
    private javax.swing.JTable clientLimitTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextField companyNameField;
    private javax.swing.JTextField faxNoField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable personTable;
    private javax.swing.JTextField phoneNoField;
    private javax.swing.JTextField rmNameField;
    private javax.swing.JPanel titalPanel;
    private javax.swing.JTextField turnOverField;
    private javax.swing.JTextField yearOfAccFiled;
    // End of variables declaration//GEN-END:variables
}
