package com.msr.tools.UI;

import com.msr.tools.dao.DetailViewOfAccordion;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.parser.AllClientDetailBean;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
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
public class ClientDetailPanel extends javax.swing.JPanel {

    private MainScreen homePage = null;
    String emailStr = "";
    String accordionName = "";
    private String designation = "";
    com.msr.tools.UI.AccordionMenu toolMenu = null;
    private JPanel toolsPanel = null;

    /**
     *
     * @param email
     * @param accName
     * @param home
     */
    public ClientDetailPanel(String email, String accName, MainScreen home, JPanel toolsPanel, String designation) {
        this.designation = designation;
        initComponents();
        addCompanyButton.setEnabled(false);
        this.emailStr = email;
        this.accordionName = accName;
        this.homePage = home;
        this.toolsPanel = toolsPanel;

        DefaultTableModel tableModel = (DefaultTableModel) companyTable.getModel();

        TableColumn col1 = companyTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
// render the edit button icon from images package
        TableColumn col7 = companyTable.getColumnModel().getColumn(7);
        col7.setMaxWidth(40);
        col7.setCellRenderer(new IconTableCellRenderer("Meeting"));
        
// render the edit button icon from images package
        TableColumn col8 = companyTable.getColumnModel().getColumn(8);
        col8.setMaxWidth(40);
        col8.setCellRenderer(new IconTableCellRenderer("Edit"));

// render the view button icon from images package
        TableColumn col9 = companyTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("View"));
        //===================================================================================

        DetailViewOfAccordion clientDetail = new DetailViewOfAccordion();
        List allClientList = null;                                              // fetch from DetailViewOfAccordion class
        List allClientConts = null;                                          // fetch from DetailViewOfAccordion class
        int counter = 0;

        List<AllClientDetailBean> clientsInfoList = new UserXMLReader().getClientDataList(emailStr);
        Iterator itr = clientsInfoList.iterator();
        AllClientDetailBean clientBean = null;
        while (itr.hasNext()) {
            clientBean = (AllClientDetailBean) itr.next();

// here check client list data from  DetailViewOfAccordion class methods in tools.dao package
            tableModel.insertRow(counter, new Object[]{});
            companyTable.setValueAt(counter + 1, counter, 0);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getName()), counter, 1);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getAddress()), counter, 2);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getLandline()), counter, 3);

// here check contacts in contacts table, set value if not empty in tools.dao package                  
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getCategory()), counter, 4);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getApplimitAmount()), counter, 5);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getTurnOver()), counter, 6);
            counter++;
        }
    }

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

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        companyTable = new javax.swing.JTable();
        addCompanyButton = new javax.swing.JButton();
        clientSearchField = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        JTableHeader header = companyTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));
        header.setFont(font);
        companyTable.setRowHeight(35);
        companyTable.setAutoCreateRowSorter(true);
        companyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "", "Name", "Address", "Landline No.", "Category", "Client Limit","Turn Over","","",""
            }
        ));
        companyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                companyTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(companyTable);

        addCompanyButton.setBackground(new java.awt.Color(0, 103, 204));
        addCompanyButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addCompanyButton.setForeground(new java.awt.Color(255, 255, 255));
        addCompanyButton.setText("ADD COMPANY");
        addCompanyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCompanyButtonActionPerformed(evt);
            }
        });

        clientSearchField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        clientSearchField.setText("Search Client");
        clientSearchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientSearchFieldMouseClicked(evt);
            }
        });
        clientSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientSearchFieldActionPerformed(evt);
            }
        });

        refreshButton.setBackground(new java.awt.Color(0, 103, 204));
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(clientSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addCompanyButton)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clientSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton))
                    .addComponent(addCompanyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void companyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_companyTableMouseClicked

        int row = companyTable.rowAtPoint(evt.getPoint());
        int col = companyTable.columnAtPoint(evt.getPoint());

        int id = 0;
        boolean flag = false;
        // edit button clicked
        if (col == 7) {
        if (!SingletonClass.designation.equalsIgnoreCase("credit analyst")) {
            flag = false;
             com.msr.tools.UI.AddNewMeeting meetingDtls = new com.msr.tools.UI.AddNewMeeting(emailStr, accordionName,  this,companyTable.getValueAt(row, 1).toString());
            meetingDtls.setVisible(true);
            meetingDtls.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
        }
        }
        if (col == 8) {
            flag = false;
            ToolsEditViewFrame toolEditObj = new ToolsEditViewFrame(flag, accordionName, companyTable.getValueAt(row, 1).toString(), emailStr, this, "", "");
            toolEditObj.setVisible(true);
            toolEditObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));

        }
        // view button clicked
        if (col == 9) {
            flag = true;
            ToolsEditViewFrame toolViewObj = new ToolsEditViewFrame(flag, accordionName, companyTable.getValueAt(row, 1).toString(), emailStr, this, "", "");
            toolViewObj.setVisible(true);
            toolViewObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
        }
    }//GEN-LAST:event_companyTableMouseClicked

private void clientSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientSearchFieldActionPerformed
    DefaultTableModel tableModel = (DefaultTableModel) companyTable.getModel();

    int counting = tableModel.getRowCount();
    for (int j = counting; j > 0; j--) {
        System.out.println("row number is " + j);
        tableModel.removeRow(j - 1);

    }

    TableColumn col1 = companyTable.getColumnModel().getColumn(0);
    col1.setMaxWidth(50);
// render the edit button icon from images package
        TableColumn col7 = companyTable.getColumnModel().getColumn(7);
        col7.setMaxWidth(40);
        col7.setCellRenderer(new IconTableCellRenderer("Meeting"));
        
// render the edit button icon from images package
        TableColumn col8 = companyTable.getColumnModel().getColumn(8);
        col8.setMaxWidth(40);
        col8.setCellRenderer(new IconTableCellRenderer("Edit"));

// render the view button icon from images package
        TableColumn col9 = companyTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("View"));
    //===================================================================================

    DetailViewOfAccordion clientDetail = new DetailViewOfAccordion();
    List allClientList = null;                                              // fetch from DetailViewOfAccordion class
    List allClientConts = null;                                          // fetch from DetailViewOfAccordion class
    int counter = 0;

    List<AllClientDetailBean> clientsInfoList = new UserXMLReader().getClientSearchedDataList(emailStr, clientSearchField.getText());
    Iterator itr = clientsInfoList.iterator();
    AllClientDetailBean clientBean = null;
    while (itr.hasNext()) {
        clientBean = (AllClientDetailBean) itr.next();

// here check client list data from  DetailViewOfAccordion class methods in tools.dao package
        tableModel.insertRow(counter, new Object[]{});
        companyTable.setValueAt(counter + 1, counter, 0);
        companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getName()), counter, 1);
        companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getAddress()), counter, 2);
        companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getLandline()), counter, 3);

// here check contacts in contacts table, set value if not empty in tools.dao package                  
        companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getCategory()), counter, 4);
        companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getApplimitAmount()), counter, 5);
        companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getTurnOver()), counter, 6);
        counter++;
    }
}//GEN-LAST:event_clientSearchFieldActionPerformed

    private void clientSearchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientSearchFieldMouseClicked

        clientSearchField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_clientSearchFieldMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) companyTable.getModel();

        int counting = tableModel.getRowCount();
        for (int j = counting; j > 0; j--) {
            System.out.println("row number is " + j);
            tableModel.removeRow(j - 1);

        }

        TableColumn col1 = companyTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
// render the edit button icon from images package
        TableColumn col7 = companyTable.getColumnModel().getColumn(7);
        col7.setMaxWidth(40);
        col7.setCellRenderer(new IconTableCellRenderer("Meeting"));
        
// render the edit button icon from images package
        TableColumn col8 = companyTable.getColumnModel().getColumn(8);
        col8.setMaxWidth(40);
        col8.setCellRenderer(new IconTableCellRenderer("Edit"));

// render the view button icon from images package
        TableColumn col9 = companyTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("View"));
        //===================================================================================

        DetailViewOfAccordion clientDetail = new DetailViewOfAccordion();
        List allClientList = null;                                              // fetch from DetailViewOfAccordion class
        List allClientConts = null;                                          // fetch from DetailViewOfAccordion class
        int counter = 0;

        List<AllClientDetailBean> clientsInfoList = new UserXMLReader().getClientDataList(emailStr);
        Iterator itr = clientsInfoList.iterator();
        AllClientDetailBean clientBean = null;
        while (itr.hasNext()) {
            clientBean = (AllClientDetailBean) itr.next();

// here check client list data from  DetailViewOfAccordion class methods in tools.dao package
            tableModel.insertRow(counter, new Object[]{});
            companyTable.setValueAt(counter + 1, counter, 0);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getName()), counter, 1);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getAddress()), counter, 2);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getLandline()), counter, 3);

// here check contacts in contacts table, set value if not empty in tools.dao package                  
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getCategory()), counter, 4);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getApplimitAmount()), counter, 5);
            companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getTurnOver()), counter, 6);
            counter++;
        }
        clientSearchField.setText("Search Client");
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void addCompanyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCompanyButtonActionPerformed

        AddUserBankClientForm newClientForm = new AddUserBankClientForm("Company", this, homePage, emailStr);
        newClientForm.setVisible(true);
        newClientForm.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
    }//GEN-LAST:event_addCompanyButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
            DefaultTableModel tableModel = (DefaultTableModel) companyTable.getModel();

            int counting = tableModel.getRowCount();
            for (int j = counting; j > 0; j--) {
                System.out.println("row number is " + j);
                tableModel.removeRow(j - 1);

            }

            TableColumn col1 = companyTable.getColumnModel().getColumn(0);
            col1.setMaxWidth(50);
// render the edit button icon from images package
        TableColumn col7 = companyTable.getColumnModel().getColumn(7);
        col7.setMaxWidth(40);
        col7.setCellRenderer(new IconTableCellRenderer("Meeting"));
        
// render the edit button icon from images package
        TableColumn col8 = companyTable.getColumnModel().getColumn(8);
        col8.setMaxWidth(40);
        col8.setCellRenderer(new IconTableCellRenderer("Edit"));

// render the view button icon from images package
        TableColumn col9 = companyTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("View"));
            //===================================================================================

            DetailViewOfAccordion clientDetail = new DetailViewOfAccordion();
            List allClientList = null;                                              // fetch from DetailViewOfAccordion class
            List allClientConts = null;                                          // fetch from DetailViewOfAccordion class
            int counter = 0;

            List<AllClientDetailBean> clientsInfoList = new UserXMLReader().getClientSearchedDataList(emailStr, clientSearchField.getText());
            Iterator itr = clientsInfoList.iterator();
            AllClientDetailBean clientBean = null;
            while (itr.hasNext()) {
                clientBean = (AllClientDetailBean) itr.next();

// here check client list data from  DetailViewOfAccordion class methods in tools.dao package
                tableModel.insertRow(counter, new Object[]{});
                companyTable.setValueAt(counter + 1, counter, 0);
                companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getName()), counter, 1);
                companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getAddress()), counter, 2);
                companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getLandline()), counter, 3);

// here check contacts in contacts table, set value if not empty in tools.dao package                  
                companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getCategory()), counter, 4);
                companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getApplimitAmount()), counter, 5);
                companyTable.setValueAt(SingletonClass.replaceEmptySpace(clientBean.getTurnOver()), counter, 6);
                counter++;
            }
    }//GEN-LAST:event_searchButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCompanyButton;
    private javax.swing.JTextField clientSearchField;
    private javax.swing.JTable companyTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
