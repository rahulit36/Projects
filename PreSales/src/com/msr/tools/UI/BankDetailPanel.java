package com.msr.tools.UI;

import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.parser.AllBankDetailBean;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class BankDetailPanel extends javax.swing.JPanel {

    com.msr.tools.UI.AccordionMenu toolMenu = null;
    private JPanel toolsPanel = null;
//    private IlcUserPrivileges userPriv = null;
//    private  CheckUserPrivilege  userType = null;
    private MainScreen homePage = null;
    String emailStr = "";
    String accordionName = "";
    private String designation = "";
//    DetailViewOfAccordion bankDetail = new DetailViewOfAccordion();

    /**
     *
     * @param email
     * @param accName
     * @param home
     */
    public BankDetailPanel(String email, String accName, MainScreen home, String designation, JPanel toolsPanel) {
        this.toolsPanel = toolsPanel;
        this.designation = designation;
        initComponents();
        this.emailStr = email;
        this.accordionName = accName;
        this.homePage = home;
        DefaultTableModel tableModel = (DefaultTableModel) bankDetailTable.getModel();

        TableColumn col1 = bankDetailTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(40);

        TableColumn col9 = bankDetailTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("Edit"));


        TableColumn col10 = bankDetailTable.getColumnModel().getColumn(10);
        col10.setMaxWidth(40);
        col10.setCellRenderer(new IconTableCellRenderer("View"));

        List allBankList = null;// fetch from DetailViewOfAccordion class
        List contlist = null;// fetch from DetailViewOfAccordion class
        int counter = 0;

        List<AllBankDetailBean> banksInfoList = new UserXMLReader().getBankDataList(emailStr);
        Iterator itr = banksInfoList.iterator();
        AllBankDetailBean bankBean = null;
        while (itr.hasNext()) {
            bankBean = (AllBankDetailBean) itr.next();

            // here check  list data from  DetailViewOfAccordion class methods in tools.dao package
//        "", "Bank Name", "Bank Type", "Alias ", "Account No", "IFCI Code", "Contact Person", "Mobile", "Email", "", ""

            if (!SingletonClass.designation.equals("RM")) {
                
                tableModel.insertRow(counter, new Object[]{});
                    bankDetailTable.setValueAt(counter + 1, counter, 0);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankName()), counter, 1);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankAlias()), counter, 2);

                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBranch()), counter, 3);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankType()), counter, 4);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getIfscCode()), counter, 5);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getAccountNo()), counter, 6);
                    bankDetailTable.setValueAt(URLDecoder.decode(SingletonClass.replaceEmptySpace(bankBean.getAddress())), counter, 7);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getFaxNo()), counter, 8);
                    counter++;
                    
            }else if (SingletonClass.designation.equals("RM") && bankBean.getBankType() != null && bankBean.getBankType().equals("Discounting")) {
                    tableModel.insertRow(counter, new Object[]{});
                    bankDetailTable.setValueAt(counter + 1, counter, 0);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankName()), counter, 1);
                    bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankAlias()), counter, 2);
                    counter++;
                
            }
        }
    }

    /**
     *
     */
    public void refresh() {
//     this.removeAll();
//     InnerDetailPanel leafItem = new InnerDetailPanel("Manage Bank", emailStr, homePage,designation);
//     this.add(leafItem);
        toolsPanel.removeAll();
        toolMenu = new com.msr.tools.UI.AccordionMenu(homePage.getMenuDescriptorTools(), homePage, toolsPanel, emailStr, designation);
        toolMenu.setBackground(new Color(229, 232, 235));
        toolMenu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        toolsPanel.add(toolMenu);
        toolsPanel.repaint();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bankDetailPanel = new javax.swing.JPanel();
        addBankButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bankDetailTable = new javax.swing.JTable();
        bankSearchField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();

        bankDetailPanel.setBackground(new java.awt.Color(255, 255, 255));

        addBankButton.setBackground(new java.awt.Color(0, 103, 204));
        addBankButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addBankButton.setForeground(new java.awt.Color(255, 255, 255));
        addBankButton.setText("ADD NEW BANK");
        if(SingletonClass.designation.equals("RM")){
            addBankButton.setEnabled(false);
        }
        addBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBankButtonActionPerformed(evt);
            }
        });

        JTableHeader header = bankDetailTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        bankDetailTable.setRowHeight(35);
        bankDetailTable.setAutoCreateRowSorter(true);
        bankDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Bank Name",  "Alias ", "Branch", "Bank Type", "IFCI Code", "Account No", "Address", "Fax No.", "", ""
            }
        ));
        bankDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bankDetailTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bankDetailTable);

        bankSearchField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        bankSearchField.setText("Search Bank");
        bankSearchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bankSearchFieldMouseClicked(evt);
            }
        });
        bankSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankSearchFieldActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(0, 103, 204));
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bankDetailPanelLayout = new javax.swing.GroupLayout(bankDetailPanel);
        bankDetailPanel.setLayout(bankDetailPanelLayout);
        bankDetailPanelLayout.setHorizontalGroup(
            bankDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bankDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bankDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addGroup(bankDetailPanelLayout.createSequentialGroup()
                        .addComponent(bankSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bankDetailPanelLayout.setVerticalGroup(
            bankDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bankDetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bankDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bankDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bankSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bankDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bankDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void bankDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bankDetailTableMouseClicked
    int row = bankDetailTable.rowAtPoint(evt.getPoint());
    int col = bankDetailTable.columnAtPoint(evt.getPoint());
    String bankId = bankDetailTable.getValueAt(row, 1).toString();
    String aliasName = bankDetailTable.getValueAt(row, 2).toString();
    boolean flag = false;
//    userType = new CheckUserPrivilege();
//    userPriv = userType.getPrivilege(emailStr);
    if (col == 9) {
        flag = false;

        ToolsEditViewFrame txEditObj = new ToolsEditViewFrame(flag, accordionName, bankId, emailStr, this, "", aliasName);
        txEditObj.setVisible(true);
        txEditObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));


    }
    if (col == 10) {
        flag = true;
        ToolsEditViewFrame txViewObj = new ToolsEditViewFrame(flag, accordionName, bankId, emailStr, this, "", aliasName);
        txViewObj.setVisible(true);
        txViewObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
    }

}//GEN-LAST:event_bankDetailTableMouseClicked

private void addBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBankButtonActionPerformed
//
    AddUserBankClientForm newBankForm = new AddUserBankClientForm("Bank", this, homePage, emailStr);
    newBankForm.setVisible(true);
    newBankForm.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
}//GEN-LAST:event_addBankButtonActionPerformed

private void bankSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankSearchFieldActionPerformed

    bankDetailTable.removeAll();
//     bankDetailTable.repaint();
//     bankDetailTable.revalidate();

    DefaultTableModel tableModel = (DefaultTableModel) bankDetailTable.getModel();
    int counting = tableModel.getRowCount();
    for (int j = counting; j > 0; j--) {
        System.out.println("row number is " + j);
        tableModel.removeRow(j - 1);

    }

    TableColumn col1 = bankDetailTable.getColumnModel().getColumn(0);
    col1.setMaxWidth(40);

    TableColumn col9 = bankDetailTable.getColumnModel().getColumn(9);
    col9.setMaxWidth(40);
    col9.setCellRenderer(new IconTableCellRenderer("Edit"));


    TableColumn col10 = bankDetailTable.getColumnModel().getColumn(10);
    col10.setMaxWidth(40);
    col10.setCellRenderer(new IconTableCellRenderer("View"));

    List allBankList = null;// fetch from DetailViewOfAccordion class
    List contlist = null;// fetch from DetailViewOfAccordion class
    int counter = 0;

    List<AllBankDetailBean> banksInfoList = new UserXMLReader().getBankSearchedDataList(emailStr, bankSearchField.getText());
    Iterator itr = banksInfoList.iterator();
    System.out.println("bank list size is " + banksInfoList.size());
    AllBankDetailBean bankBean = null;
    while (itr.hasNext()) {
        bankBean = (AllBankDetailBean) itr.next();

        // here check  list data from  DetailViewOfAccordion class methods in tools.dao package
//        "", "Bank Name", "Bank Type", "Alias ", "Account No", "IFCI Code", "Contact Person", "Mobile", "Email", "", ""
        tableModel.insertRow(counter, new Object[]{});
        bankDetailTable.getModel().setValueAt(counter + 1, counter, 0);
        System.out.println(bankBean.getBankName());
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankName()), counter, 1);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankAlias()), counter, 2);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBranch()), counter, 3);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankType()), counter, 4);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getIfscCode()), counter, 5);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getAccountNo()), counter, 6);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getAddress()), counter, 7);
        bankDetailTable.getModel().setValueAt(SingletonClass.replaceEmptySpace(bankBean.getFaxNo()), counter, 8);
        counter++;
    }

}//GEN-LAST:event_bankSearchFieldActionPerformed

    private void bankSearchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bankSearchFieldMouseClicked

        bankSearchField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_bankSearchFieldMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        bankDetailTable.removeAll();

        DefaultTableModel tableModel = (DefaultTableModel) bankDetailTable.getModel();
        int counting = tableModel.getRowCount();
        for (int j = counting; j > 0; j--) {
            System.out.println("row number is " + j);
            tableModel.removeRow(j - 1);

        }

        TableColumn col1 = bankDetailTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(40);

        TableColumn col9 = bankDetailTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("Edit"));


        TableColumn col10 = bankDetailTable.getColumnModel().getColumn(10);
        col10.setMaxWidth(40);
        col10.setCellRenderer(new IconTableCellRenderer("View"));

        List allBankList = null;// fetch from DetailViewOfAccordion class
        List contlist = null;// fetch from DetailViewOfAccordion class
        int counter = 0;

        List<AllBankDetailBean> banksInfoList = new UserXMLReader().getBankSearchedDataList(emailStr, "");
        Iterator itr = banksInfoList.iterator();
        System.out.println("bank list size is " + banksInfoList.size());
        AllBankDetailBean bankBean = null;
        while (itr.hasNext()) {
            bankBean = (AllBankDetailBean) itr.next();

            // here check  list data from  DetailViewOfAccordion class methods in tools.dao package
//        "", "Bank Name", "Bank Type", "Alias ", "Account No", "IFCI Code", "Contact Person", "Mobile", "Email", "", ""
            tableModel.insertRow(counter, new Object[]{});
            bankDetailTable.setValueAt(counter + 1, counter, 0);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankName()), counter, 1);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankAlias()), counter, 2);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBranch()), counter, 3);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getBankType()), counter, 4);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getIfscCode()), counter, 5);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getAccountNo()), counter, 6);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getAddress()), counter, 7);
            bankDetailTable.setValueAt(SingletonClass.replaceEmptySpace(bankBean.getFaxNo()), counter, 8);
            counter++;
        }
        bankSearchField.setText("Search Bank");

    }//GEN-LAST:event_refreshButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBankButton;
    private javax.swing.JPanel bankDetailPanel;
    private javax.swing.JTable bankDetailTable;
    private javax.swing.JTextField bankSearchField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
