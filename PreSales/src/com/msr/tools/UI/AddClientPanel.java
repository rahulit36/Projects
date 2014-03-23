package com.msr.tools.UI;

import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class AddClientPanel extends javax.swing.JPanel {

    ClientDetailPanel clientDetailPanel = null;
    MainScreen home = null;
    AddUserBankClientForm adClientFrame = null;
    int counter = 0;
    int bankCount = 0;
    HashMap bankObjMap = null;
    HashMap bothBankMap = null;
    JComboBox jcomb = null;
    DefaultTableModel tableModel = null;
    DefaultTableModel limitTableModel= null;
    private String uName = "";

    /**
     *
     * @param tempParent
     * @param home
     * @param frame
     * @param Uname
     */
    // add the keylistener and other fields to the panel
    public AddClientPanel(JPanel tempParent, MainScreen home, AddUserBankClientForm frame, String uName) {
        this.uName = uName;
        initComponents();
        
//       gives list of banks from db-------
        List<String> bankList = new UserXMLReader().getBankListFromXML(uName);
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+bankList.size());
        Iterator<String> itr = bankList.iterator();
        while(itr.hasNext()){
            bankSelection.addItem(itr.next().toString());
        }
        
        List<String> rmList = new UserXMLReader().getRMListFromXML(uName);
        System.out.println("rmList >>>>>>"+rmList.size());
        Iterator<String> rmItr = rmList.iterator();
        while(rmItr.hasNext()){
             rmNameCB.addItem(rmItr.next().toString());
        }
        
        this.home = home;
        this.adClientFrame = frame;
        if (tempParent instanceof ClientDetailPanel) {
            this.clientDetailPanel = (ClientDetailPanel) tempParent;
        }
//        else if (tempParent instanceof ClientSearchResultPanel) {
//
//            this.searchClientDetailPanel = (ClientSearchResultPanel) tempParent;
//        }
//=======================================================
        TableColumn txIDcol = clientLimitTable.getColumnModel().getColumn(0);
        txIDcol.setWidth(0);
        txIDcol.setMinWidth(0);
        txIDcol.setMaxWidth(0);



       TableColumn col3 = clientLimitTable.getColumnModel().getColumn(5);
        col3.setMaxWidth(40);
        col3.setCellRenderer(new IconTableCellRenderer("Delete"));

        TableColumn col6 = personTable.getColumnModel().getColumn(6);
        col6.setMaxWidth(50);
        col6.setCellRenderer(new IconTableCellRenderer("Delete"));
        
        TableColumn col1 = personTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
        col1.setWidth(50);

//==================================================================

        System.out.println("bothBankMap in add client: " + bothBankMap);

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
        rmNameCB = new javax.swing.JComboBox();
        titalPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        personTable = new javax.swing.JTable();
        addPersonButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bankSelection =  new javax.swing.JComboBox();
        clientLimitTable = new javax.swing.JTable();
        addBank = new javax.swing.JButton();

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
        addressField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addressFieldKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(addressField);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 103, 204));
        jLabel3.setText("Phone No.");

        phoneNoField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        phoneNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNoFieldKeyReleased(evt);
            }
        });

        faxNoField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        faxNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                faxNoFieldKeyReleased(evt);
            }
        });

        turnOverField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        turnOverField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                turnOverFieldKeyReleased(evt);
            }
        });

        yearOfAccFiled.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        yearOfAccFiled.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearOfAccFiledKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 103, 204));
        jLabel5.setText("Fax No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 103, 204));
        jLabel4.setText("Company Turn Over (in crore(s)):");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 103, 204));
        jLabel6.setText("Year of Acquisition");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 103, 204));
        jLabel9.setText("Category");

        categoryCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        categoryCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Category--","SME Client","Mid Corporate","Large Corporate"}));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 103, 204));
        jLabel10.setText("RM Assigned");

        rmNameCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rmNameCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select RM--" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rmNameCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryCB, 0, 325, Short.MAX_VALUE)
                    .addComponent(faxNoField)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(companyNameField)
                    .addComponent(jScrollPane1)
                    .addComponent(yearOfAccFiled)
                    .addComponent(turnOverField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(companyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(faxNoField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(turnOverField, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearOfAccFiled, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryCB, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rmNameCB, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(13, 13, 13))
        );

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Add New Company");

        javax.swing.GroupLayout titalPanelLayout = new javax.swing.GroupLayout(titalPanel);
        titalPanel.setLayout(titalPanelLayout);
        titalPanelLayout.setHorizontalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titalPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "", "Contact Person", "Designation", "Mobile Number", "E-mail","Email Previlages",""
            }
        ));
        personTable.setRowHeight(30);
        personTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(personTable);

        addPersonButton.setBackground(new java.awt.Color(0, 103, 204));
        addPersonButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addPersonButton.setForeground(new java.awt.Color(255, 255, 255));
        addPersonButton.setText("ADD PERSON");
        addPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 103, 204));
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        resetButton.setBackground(new java.awt.Color(0, 103, 204));
        resetButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resetButton.setForeground(new java.awt.Color(255, 255, 255));
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
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
                "","Discounting Bank ", "Beneficiary","Client Limit", "Available Limit",""
            }
        ));
        clientLimitTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                return;

                int selectedRow = e.getLastIndex();
                System.out.println("selected row is "+selectedRow+"  clientRowCount "+bankCount);
                if(selectedRow<bankCount){

                    if(clientLimitTable.getValueAt(selectedRow, 3)!=null && clientLimitTable.getValueAt(selectedRow, 4)==null)
                    clientLimitTable.setValueAt(clientLimitTable.getValueAt(selectedRow, 3), selectedRow, 4);
                }

                //int selectedRow = e.getLastIndex();
                //System.out.println("selected row is "+selectedRow);
                //clientLimitTable.setValueAt(clientLimitTable.getValueAt(selectedRow, 3), selectedRow, 4);
            }
        });
        clientLimitTable.setRowHeight(30);
        clientLimitTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientLimitTableMouseClicked(evt);
            }
        });
        TableColumn col7 = clientLimitTable.getColumnModel().getColumn(5);
        col7.setMaxWidth(50);
        col7.setCellRenderer(new IconTableCellRenderer("Delete"));
        jScrollPane3.setViewportView(clientLimitTable);
        jScrollPane3.setViewportView(clientLimitTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
        );

        addBank.setBackground(new java.awt.Color(0, 103, 204));
        addBank.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBank.setForeground(new java.awt.Color(255, 255, 255));
        addBank.setText("Add Bank");
        addBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBankActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(titalPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addBank, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addPersonButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBank, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPersonButtonActionPerformed
        tableModel = (DefaultTableModel) personTable.getModel();
        TableColumn col1 = personTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
        col1.setWidth(50);
        tableModel.insertRow(counter, new Object[]{});
        personTable.setValueAt(counter + 1, counter, 0);
        counter++;
    }//GEN-LAST:event_addPersonButtonActionPerformed
    int row = 0;
    int col = 0;
    private void clientLimitTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientLimitTableMouseClicked

        row = clientLimitTable.rowAtPoint(evt.getPoint());
        col = clientLimitTable.columnAtPoint(evt.getPoint());
        int row = clientLimitTable.rowAtPoint(evt.getPoint());
        int col = clientLimitTable.columnAtPoint(evt.getPoint());
        if (col == 5) {
            DefaultTableModel tableModel1 = (DefaultTableModel) clientLimitTable.getModel();
            
                    if(clientLimitTable.getValueAt(row, 1) != null)
             bankSelection.addItem(tableModel.getValueAt(row, 1).toString());
                    bankCount--;
            tableModel1.removeRow(row);
//            JOptionPane.showMessageDialog(this, "Successfully Deleted Extra Row");
        }
    }//GEN-LAST:event_clientLimitTableMouseClicked

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        adClientFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        boolean isCompanyDetails = false;
        boolean isContactDetails = false;
        boolean isBankLimit = false;
        
        tableModel =  (DefaultTableModel) personTable.getModel();
        int rowCount = tableModel.getRowCount();
        
        limitTableModel =  (DefaultTableModel) clientLimitTable.getModel();
        int limitRowCount = limitTableModel.getRowCount();
        if(rowCount<1){
            JOptionPane.showMessageDialog(null, "please add contact details");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            isContactDetails = false;
        }
        if(limitRowCount==0){
            isBankLimit = true;
        }
        if(companyNameField.getText() == null || companyNameField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Provide company Name.");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            
        }else if(addressField.getText()==null || addressField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Provide Company Address.");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            
        }else if(phoneNoField.getText()==null || phoneNoField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Provide company's Phone Number.");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            
        }else if(turnOverField.getText()==null || turnOverField.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Please Provide Company TurnOver.");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            
//        }else if(yearOfAccFiled.getText()==null || yearOfAccFiled.getText().equals("")){
//            
//            JOptionPane.showMessageDialog(null, "Please Provide Year of Accusation.");
//            adClientFrame.setCursor(Cursor.getDefaultCursor());
//            
        }else if(categoryCB.getSelectedIndex()==0){
            
            JOptionPane.showMessageDialog(null, "Please select Category.");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            
        }else if(rmNameCB.getSelectedIndex()==0){
            
            JOptionPane.showMessageDialog(null, "Please select RM Assigned to the client.");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            
        }else{
            isCompanyDetails = true;
        }
        
        
        if(isCompanyDetails){
        for(int i = 0;i<rowCount;i++){
            if(tableModel.getValueAt(i, 1)==null || tableModel.getValueAt(i, 1).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Contact Person's Name");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else if(tableModel.getValueAt(i, 2)==null || tableModel.getValueAt(i, 2).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Contact Person's Designation");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else if(tableModel.getValueAt(i, 3)==null || tableModel.getValueAt(i, 3).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Contact Person's Mobile Number");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else if(tableModel.getValueAt(i, 4)==null || tableModel.getValueAt(i, 4).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please provide Contact Person's Email");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else {
                isContactDetails = true;
            }
            
            
//            boolean isValidEmail = false;
//             Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*[._-]*[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*");
//        Matcher matchPattern = p.matcher(personTable.getValueAt(i, 4).toString());
//        isValidEmail = matchPattern.matches();
//        if(!isValidEmail){
//            JOptionPane.showMessageDialog(null, "Invalid email id of "+personTable.getValueAt(i, 1).toString(), "", 0);
//            adClientFrame.setCursor(Cursor.getDefaultCursor());
//            isContactDetails = false;
//                break;
//        }else{
//            isContactDetails = true;
//        }
        
            boolean isInt = isInt(personTable.getValueAt(i, 3).toString());
        System.out.println("phnPattern.matches(): " + personTable.getValueAt(i, 3).toString() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid mobile no. of "+personTable.getValueAt(i, 1).toString(), "", 0);
           adClientFrame.setCursor(Cursor.getDefaultCursor());
            isContactDetails = false;
                break;
        }else{
            isContactDetails = true;
        }
        }
        }
        
        
        
        
        if(isContactDetails&& isCompanyDetails){
        for(int i = 0;i<limitRowCount;i++){
            if(limitTableModel.getValueAt(i, 1)==null || limitTableModel.getValueAt(i, 1).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please select Bank");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else if(limitTableModel.getValueAt(i, 2)==null || limitTableModel.getValueAt(i, 2).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Beneficiary");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else if(limitTableModel.getValueAt(i, 3)==null || limitTableModel.getValueAt(i, 3).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Credit Limit");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            } else if(limitTableModel.getValueAt(i, 4)==null || limitTableModel.getValueAt(i, 4).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Available Limit");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            }else if(limitTableModel.getValueAt(i, 5)==null || limitTableModel.getValueAt(i, 5).toString().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Sanctioned Date");
                adClientFrame.setCursor(Cursor.getDefaultCursor());
                break;
            }else {
                isBankLimit = true;
            }
            
            boolean isInt = isInt(clientLimitTable.getValueAt(i, 3).toString());
        System.out.println("phnPattern.matches(): " + personTable.getValueAt(i, 3).toString() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid client limit value of "+clientLimitTable.getValueAt(i, 1).toString(), "", 0);
           adClientFrame.setCursor(Cursor.getDefaultCursor());
            isBankLimit = false;
                break;
        }else{
            isBankLimit = true;
        }
        }
        }
        
        if(isCompanyDetails && isContactDetails && isBankLimit){
        boolean iscontactAdded = false;
        boolean isClientLimitAdded = false;
        
        


//    limitAmount


        StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(uName).append("&").append(SingletonClass.operaionType).append("=saveClientDetails&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(companyNameField.getText())).append("&").append(SingletonClass.appPhn).append("=").append(phoneNoField.getText()).append("&").append(SingletonClass.appFax).append("=").append(faxNoField.getText()).append("&").append(SingletonClass.appTurnOver).append("=").append(turnOverField.getText()).append("&").append(SingletonClass.appAddrs).append("=").append(URLEncoder.encode(addressField.getText())).append("&").append(SingletonClass.accYear).append("=").append(yearOfAccFiled.getText()).append("&").append(SingletonClass.appCategory).append("=").append(categoryCB.getSelectedItem().toString()).append("&dbType=save").append("&").append(SingletonClass.rmName).append("=").append(rmNameCB.getSelectedItem().toString());;
          String url = buffer.toString();
                        url=url.replace(" ", "%20");
            System.out.println("url is "+url);
            String urlStatus = new callServerUrl().urlProcessiong(url);
//            uploading of document on server and updating in database.........................
            
//            saving client contacts
            
//            clientName

            if(!urlStatus.equals("invalid")&&!urlStatus.equals("duplicate")){
            for(int i = 0;i<rowCount;i++){
                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(uName).append("&").append(SingletonClass.operaionType).append("=saveClientContacts&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(companyNameField.getText())).append("&").append(SingletonClass.clientContactName).append("=").append(tableModel.getValueAt(i, 1).toString()).append("&").append(SingletonClass.clientContactDesignation).append("=").append(tableModel.getValueAt(i, 2).toString()).append("&").append(SingletonClass.clientContactMobile).append("=").append(tableModel.getValueAt(i, 3).toString()).append("&").append(SingletonClass.clientContactEmail).append("=").append(tableModel.getValueAt(i, 4).toString()).append("&notifyStr=,").append(tableModel.getValueAt(i, 5).toString());;
                String url1 = buffer1.toString();
                     String encodedUrl = url1.replace(" ","%20");
            System.out.println("client contact url is "+encodedUrl);
            String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);
            
            if(urlStatus1.equals("invalid")){
                iscontactAdded = true;
                break;
            }
         }
            }
//            saving clients bank limit........
            if(!urlStatus.equals("invalid")&&!urlStatus.equals("duplicate")&&!iscontactAdded){
            for(int i = 0;i<limitRowCount;i++){
//                http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=saveClientLimit&clientName=piyush2000&beneficiary=piyush&bankName=abc%20bank&clntlimit=18888&avlLimit=894756
                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(uName).append("&").append(SingletonClass.operaionType).append("=saveClientLimit&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(companyNameField.getText())).append("&").append(SingletonClass.beneficiary).append("=").append(limitTableModel.getValueAt(i, 2).toString()).append("&").append(SingletonClass.bankName).append("=").append(limitTableModel.getValueAt(i, 1).toString()).append("&").append(SingletonClass.clntlimit).append("=").append(limitTableModel.getValueAt(i, 3).toString()).append("&").append(SingletonClass.avlLimit).append("=").append(limitTableModel.getValueAt(i, 4).toString()).append("&").append(SingletonClass.sanctionDate).append("=").append(limitTableModel.getValueAt(i, 5).toString()).append("&").append(SingletonClass.rmName).append("=").append(rmNameCB.getSelectedItem().toString());
                String url1 = buffer1.toString();
                     String encodedUrl = url1.replace(" ","%20");
            System.out.println("client limit url is "+encodedUrl);
            String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);
            
            if(urlStatus1.equals("invalid")){
                isClientLimitAdded = true;
                break;
            }
         }
            }
         if(urlStatus.equals("invalid")||urlStatus.equals("duplicate") ||iscontactAdded || isClientLimitAdded){
             if(urlStatus.equals("invalid")){
                   JOptionPane.showMessageDialog(this, "client already exist.");
             }else{
                JOptionPane.showMessageDialog(this, "Error in Saving Client, Please Try Again.");
             }adClientFrame.setCursor(Cursor.getDefaultCursor());
            }else{
            
            JOptionPane.showMessageDialog(this, "Successfully Saved");
            adClientFrame.setCursor(Cursor.getDefaultCursor());
            adClientFrame.dispose();
            clientDetailPanel.refresh();
            
            }   
            
    
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void addBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBankActionPerformed

        tableModel = (DefaultTableModel) clientLimitTable.getModel();
        tableModel.insertRow(bankCount, new Object[]{});
        if(bankCount>0){
            for(int j = 0;j<bankCount;j++){
                bankSelection.removeItem(tableModel.getValueAt(j, 1).toString());
            }
        }
        TableColumn col = clientLimitTable.getColumnModel().getColumn(1);
        col.setCellEditor(new ComboBoxCellEditor(bankSelection));
       bankCount++;
    }//GEN-LAST:event_addBankActionPerformed

private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed

    DefaultTableModel model = (DefaultTableModel) personTable.getModel();
    int r = model.getRowCount();

    DefaultTableModel tableModel = (DefaultTableModel) clientLimitTable.getModel();
    int row = tableModel.getRowCount();

    companyNameField.setText("");
    addressField.setText("");
    phoneNoField.setText("");
    faxNoField.setText("");
    categoryCB.setSelectedIndex(0);
    rmNameCB.setSelectedIndex(0);
    yearOfAccFiled.setText("");
    turnOverField.setText("");
    bankSelection.removeAllItems();
    List<String> bankList = new UserXMLReader().getBankListFromXML(uName);
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+bankList.size());
        Iterator<String> itr = bankList.iterator();
        while(itr.hasNext()){
            bankSelection.addItem(itr.next().toString());
        }
    //===================================      
    counter = 0;
    for (int i = 0; i < r; i++) {
        model.removeRow(0);
    }
    //===================================                        
    bankCount = 0;
    for (int i = 0; i < row; i++) {
        tableModel.removeRow(0);
    }
}//GEN-LAST:event_resetButtonActionPerformed

private void addressFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressFieldKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_TAB) {
        System.out.println(evt.getModifiers());
        if (evt.getModifiers() > 0) {
            addressField.transferFocusBackward();
        } else {
            addressField.transferFocus();
        }
        evt.consume();
    }
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    }
}//GEN-LAST:event_addressFieldKeyPressed

private void personTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personTableMouseClicked

    int row = personTable.rowAtPoint(evt.getPoint());
    int col = personTable.columnAtPoint(evt.getPoint());
// here user on clicking view a pop up where he can select clients privelege to recieve emails.
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
          int status = JOptionPane.showConfirmDialog(adClientFrame, params, "Client's Email Priveledge", JOptionPane.OK_CANCEL_OPTION);
        if(status == JOptionPane.CANCEL_OPTION){
            
        } else{
        int count = 0;
        StringBuffer privFor = new StringBuffer("");
        while(count<checkbox.length){
            if(checkbox[count].isSelected())
            {
                privFor.append(checkbox[count].getText()).append(",");
            }
            count++;
        }
        String emailPriv = privFor.toString();
        personTable.setValueAt(emailPriv, row, 5);
    }
    }if(col ==6){
        
        DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
        tableModel.removeRow(row);
        counter--;
    }
}//GEN-LAST:event_personTableMouseClicked

    private void phoneNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNoFieldKeyReleased

        // TODO add your handling code here:
          boolean isInt = isInt(phoneNoField.getText());
        System.out.println("phnPattern.matches(): " + phoneNoField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid phone no.", "", 0);
            phoneNoField.setText("");
        }
    }//GEN-LAST:event_phoneNoFieldKeyReleased

    private void faxNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_faxNoFieldKeyReleased
        // TODO add your handling code here:
         boolean isInt = isInt(faxNoField.getText());
        System.out.println("phnPattern.matches(): " + faxNoField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid Fax no.", "", 0);
            faxNoField.setText("");
        }
    }//GEN-LAST:event_faxNoFieldKeyReleased

    private void turnOverFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_turnOverFieldKeyReleased
        // TODO add your handling code here:
        
         boolean isInt = isInt(turnOverField.getText());
        System.out.println("phnPattern.matches(): " + turnOverField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid turnOver value.", "", 0);
            turnOverField.setText("");
        }
    }//GEN-LAST:event_turnOverFieldKeyReleased

    private void yearOfAccFiledKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearOfAccFiledKeyReleased
        // TODO add your handling code here:
          boolean isInt = isInt(yearOfAccFiled.getText());
        System.out.println("phnPattern.matches(): " + yearOfAccFiled.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid value.", "", 0);
            yearOfAccFiled.setText("");
        }
    }//GEN-LAST:event_yearOfAccFiledKeyReleased
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
    private javax.swing.JButton addBank;
    private javax.swing.JButton addPersonButton;
    private javax.swing.JTextArea addressField;
    private javax.swing.JComboBox categoryCB;
    private javax.swing.JTable clientLimitTable;
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
    private javax.swing.JButton resetButton;
    private javax.swing.JComboBox rmNameCB;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel titalPanel;
    private javax.swing.JTextField turnOverField;
    private javax.swing.JTextField yearOfAccFiled;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox bankSelection;
    /**
     *
     * @param strBigDec
     * @return
     */
    public BigDecimal convertStrToBigDecimal(String strBigDec) {
        BigDecimal bigDec = null;
        if (strBigDec != null && !strBigDec.equals("")) {
            if (strBigDec.indexOf(',') >= 0) {
                bigDec = new BigDecimal(strBigDec.replaceAll(",", ""));
            } else {
                bigDec = new BigDecimal(strBigDec);
            }
        } else {
            bigDec = null;
        }

        return bigDec;
    }
}
