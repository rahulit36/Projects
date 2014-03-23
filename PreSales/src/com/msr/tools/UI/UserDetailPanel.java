package com.msr.tools.UI;

//import com.dao.CheckUserPrivilege;
//import com.dao.DetailViewOfAccordion;
//import com.hbm.IlcUserDetails;
//import com.hbm.IlcUserPrivileges;
//import com.msr.view.MainScreen;
import com.msr.tools.dao.DetailViewOfAccordion;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.parser.PreUserDetails;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
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
public class UserDetailPanel extends javax.swing.JPanel {

    /**
     *
     */
    public String emailString = "";
    private JPanel toolsPanel = null;
//      private IlcUserPrivileges userPriv = null;
//      private  CheckUserPrivilege  userType = null;
    private MainScreen homePage = null;
    private String designation = "";
    com.msr.tools.UI.AccordionMenu toolMenu = null;

    /**
     *
     * @param email
     * @param accName
     * @param home
     * @param designation
     */
    public UserDetailPanel(String email, String accName, MainScreen home, JPanel toolsPanel, String designation) {
        this.toolsPanel = toolsPanel;
        this.emailString = email;
        this.homePage = home;
        this.designation = designation;
        initComponents();


        DefaultTableModel tableModel = (DefaultTableModel) userDetailTable.getModel();
        TableColumn col1 = userDetailTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);

// add edit button icon
        TableColumn col5 = userDetailTable.getColumnModel().getColumn(5);
        col5.setMaxWidth(0);
        col5.setWidth(0);
        col5.setMinWidth(0);
col5.setResizable(false);

        TableColumn col7 = userDetailTable.getColumnModel().getColumn(7);
        col7.setMaxWidth(40);
        col7.setCellRenderer(new IconTableCellRenderer("Edit"));


// add view button icon
        TableColumn col8 = userDetailTable.getColumnModel().getColumn(8);
        col8.setMaxWidth(40);
        col8.setCellRenderer(new IconTableCellRenderer("View"));

        DetailViewOfAccordion userDetail = new DetailViewOfAccordion();
        List userList = null;// fetch from DetailViewOfAccordion class
        int counter = 0;
        List<PreUserDetails> userDetailList = new UserXMLReader().parseUserDetailsList(emailString);
        Iterator itr = userDetailList.iterator();
        while (itr.hasNext()) {
            tableModel.insertRow(counter, new Object[]{});
            PreUserDetails preUserDetails = (PreUserDetails) itr.next();
            userDetailTable.setValueAt(counter + 1, counter, 0);
            userDetailTable.setValueAt(SingletonClass.replaceEmptySpace(preUserDetails.getUserName()), counter, 1);
            userDetailTable.setValueAt(SingletonClass.replaceEmptySpace(preUserDetails.getUserEmail()), counter, 2);
            userDetailTable.setValueAt(SingletonClass.replaceEmptySpace(preUserDetails.getMobNo()), counter, 3);
            userDetailTable.setValueAt(SingletonClass.replaceEmptySpace(preUserDetails.getDesingation()), counter, 4);
            userDetailTable.setValueAt(preUserDetails.getIsActive(), counter, 5);
            userDetailTable.setValueAt(SingletonClass.replaceEmptySpace(preUserDetails.getEmailNotification()), counter, 6);
            counter++;



        }




        // here check  list data from  DetailViewOfAccordion class methods in tools.dao package

//"","NAME", "E-MAIL", "MOBILE No.", "DESIGNATION", "Is-ACTIVE", "E-MAIL NOTIFY","",""

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userDetailPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        userTableScroll = new javax.swing.JScrollPane();
        userDetailTable = new javax.swing.JTable();//};
    addUserButton = new javax.swing.JButton();

    userDetailPanel.setBackground(new java.awt.Color(255, 255, 255));

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));

    JTableHeader header = userDetailTable.getTableHeader();
    header.setBackground(new Color(192, 191, 200));
    header.setPreferredSize(new Dimension(600,35));
    Font font = new Font("Serif", Font.BOLD, 14);
    header.setForeground(new Color(0, 103, 204));

    header.setFont(font);
    userDetailTable.setRowHeight(35);
    userDetailTable.setAutoCreateRowSorter(true);
    userDetailTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "","NAME", "E-MAIL", "MOBILE No.", "DESIGNATION", "Is-ACTIVE", "E-MAIL NOTIFY","",""
        }
    ));
    userDetailTable.setRowHeight(35);
    TableColumn col5 = userDetailTable.getColumnModel().getColumn(5);
    col5.setMaxWidth(0);
    col5.setWidth(0);
    col5.setMinWidth(0);
    col5.setResizable(false);
    userDetailTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            userDetailTableMouseClicked(evt);
        }
    });
    userTableScroll.setViewportView(userDetailTable);

    addUserButton.setBackground(new java.awt.Color(0, 103, 204));
    addUserButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    addUserButton.setForeground(new java.awt.Color(255, 255, 255));
    addUserButton.setText("ADD NEW USER");
    addUserButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addUserButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(userTableScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(userTableScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(addUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout userDetailPanelLayout = new javax.swing.GroupLayout(userDetailPanel);
    userDetailPanel.setLayout(userDetailPanelLayout);
    userDetailPanelLayout.setHorizontalGroup(
        userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userDetailPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    userDetailPanelLayout.setVerticalGroup(
        userDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(userDetailPanelLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(userDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(userDetailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     */
    public void refresh() {
        toolsPanel.removeAll();
        toolMenu = new com.msr.tools.UI.AccordionMenu(homePage.getMenuDescriptorTools(), homePage, toolsPanel, emailString,designation);
        toolMenu.setBackground(new Color(229, 232, 235));
        toolMenu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        toolsPanel.add(toolMenu);
        toolsPanel.repaint();
    }
    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed

//        if (!designation.equals("RM")) {
            AddUserBankClientForm newApplicantForm = new AddUserBankClientForm("User", this, homePage, emailString);
            newApplicantForm.setVisible(true);
            newApplicantForm.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,570));
//            newApplicantForm.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
//        } else {
//            JOptionPane.showMessageDialog(null, "You Don't Have Priviledge to Add New User.");
//        }

}//GEN-LAST:event_addUserButtonActionPerformed

private void userDetailTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userDetailTableMouseClicked
    int row = userDetailTable.rowAtPoint(evt.getPoint());
    int col = userDetailTable.columnAtPoint(evt.getPoint());

    int id = 0;
    String useremail = (String) userDetailTable.getValueAt(row, 2);
    boolean flag = false;
    if (col == 7) {
        flag = false;
        ToolsEditViewFrame toolViewObj = new ToolsEditViewFrame(flag, "User", id, emailString, this, useremail);
        toolViewObj.setVisible(true);
//        toolViewObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
        toolViewObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width-30,570));
    }
    if (col == 8) {
        flag = true;
        ToolsEditViewFrame toolViewObj = new ToolsEditViewFrame(flag, "User", id, emailString, this, useremail);
        toolViewObj.setVisible(true);
        toolViewObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width-50,570));
//        toolViewObj.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 50));
    }
}//GEN-LAST:event_userDetailTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUserButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel userDetailPanel;
    private javax.swing.JTable userDetailTable;
    private javax.swing.JScrollPane userTableScroll;
    // End of variables declaration//GEN-END:variables
}
