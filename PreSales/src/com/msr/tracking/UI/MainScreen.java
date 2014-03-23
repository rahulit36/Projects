/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.tracking.UI;

import com.msr.tracking.dao.GetTrackingStatusAndCount;
import com.msr.newTx.UI.NewTxPanel;
import com.msr.reportstab.ReportPanel;
import com.msr.tools.dao.GetNoOfEntryInTable;
import com.msr.util.SingletonClass;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class MainScreen extends javax.swing.JFrame {

    AccordionMenu menu = null;
    MainScreen maiScreen = null;
    String email = "";

    /**
     * Creates new form MainScreen
     */
    // add only tracking screen accordion when MainScreen frame is displayed.
    public MainScreen(String email, String userName) {
        try {
            // set the icon of login frame
            setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));

        } catch (Exception ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try {
        this.email = email;
        maiScreen = this;
        // maximized the main window default
        //        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        String userText = userName.toUpperCase().charAt(0) + "";
        StringBuffer strBuff = new StringBuffer(userName);
        userName = userText + strBuff.deleteCharAt(0).toString();
        welcomeLabel.setText("Welcome " + userName);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            tabbedPane.setEnabledAt(1, false);
//            JOptionPane.showMessageDialog(null, "Sorry! you do not have priviledge to create new oportunity.", "", 0);

        }
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            UIManager.put(tabbedPane, Font.BOLD);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

// get the status of tracking screen and return status string to constructor above
    /**
     *
     * @param userPriv
     * @return
     */
    public String getMenuDescriptorTracking(String userPriv) {

        StringBuilder menuDescripter = new StringBuilder();
        GetTrackingStatusAndCount countObjMap = new GetTrackingStatusAndCount();
        HashMap statusMap = new LinkedHashMap();
        statusMap = countObjMap.getCounts(email);
        Set setOfStatus = statusMap.entrySet();
        String[] accordion = new String[setOfStatus.size()];
        String[] counter = new String[setOfStatus.size()];
        Iterator accMap = setOfStatus.iterator();
        int s = 0;
        while (accMap.hasNext()) {
            Map.Entry me = (Map.Entry) accMap.next();
            accordion[s] = (String) me.getKey();
            counter[s] = (String) me.getValue();
            s++;
        }

        for (int r = 0; r < accordion.length; r++) {
            menuDescripter.append(accordion[r] + "," + counter[r] + "!");
        }
        String menuDescriptor = menuDescripter.toString();
        return menuDescriptor;
    }

    /**
     *
     * @return
     */
// get the accordion names of Tools Screen
    public String getMenuDescriptorTools() {

        StringBuilder menuDescripter = new StringBuilder();
        GetNoOfEntryInTable countObjMap = new GetNoOfEntryInTable();
        HashMap statusMap = new LinkedHashMap();
        statusMap = countObjMap.getCounts(email);
        Set setOfStatus = statusMap.entrySet();
        String[] accordion = new String[setOfStatus.size()];
        String[] counter = new String[setOfStatus.size()];
        Iterator accMap = setOfStatus.iterator();
        int s = 0;
        while (accMap.hasNext()) {
            Map.Entry me = (Map.Entry) accMap.next();
            accordion[s] = (String) me.getKey();
            counter[s] = (String) me.getValue();
            s++;
        }

        for (int r = 0; r < accordion.length; r++) {
            menuDescripter.append(accordion[r] + "," + counter[r] + "!");
        }
        String menuDescriptor = menuDescripter.toString();
        return menuDescriptor;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        trackingPanel = new javax.swing.JPanel();
        newTxPanel = new javax.swing.JPanel();
        reportsPanel = new javax.swing.JPanel();
        toolsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        logoutLabel = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BUSINESS DEVELOPMENT");
        setBackground(new java.awt.Color(41, 25, 209));
        setLocationByPlatform(true);

        tabbedPane.setBackground(new java.awt.Color(237, 236, 235));
        tabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneMouseClicked(evt);
            }
        });
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });

        javax.swing.GroupLayout trackingPanelLayout = new javax.swing.GroupLayout(trackingPanel);
        trackingPanel.setLayout(trackingPanelLayout);
        trackingPanelLayout.setHorizontalGroup(
            trackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
        trackingPanelLayout.setVerticalGroup(
            trackingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trackingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trackingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Tracking", jPanel4);

        javax.swing.GroupLayout newTxPanelLayout = new javax.swing.GroupLayout(newTxPanel);
        newTxPanel.setLayout(newTxPanelLayout);
        newTxPanelLayout.setHorizontalGroup(
            newTxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
        newTxPanelLayout.setVerticalGroup(
            newTxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabbedPane.addTab("New Opportunity", newTxPanel);

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Reports", reportsPanel);

        javax.swing.GroupLayout toolsPanelLayout = new javax.swing.GroupLayout(toolsPanel);
        toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
            toolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
        );
        toolsPanelLayout.setVerticalGroup(
            toolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Tools", toolsPanel);

        jPanel1.setBackground(new java.awt.Color(21, 21, 142));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/investeurs_logo.png"))); // NOI18N
        jLabel1.setText(" ");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        logoutLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        logoutLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoutLabel.setText("Logout");
        logoutLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutLabelMouseClicked(evt);
            }
        });

        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutLabel)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(logoutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        com.msr.tools.UI.AccordionMenu toolMenu = null;
        com.msr.reportstab.ReportPanel reportPanel = null;
        try {
            if (tabbedPane.getSelectedIndex() == 0 ) {
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SingletonClass.txIdEdit = null;
                SingletonClass.isEdit = false;
                SingletonClass.isView = false;
                SingletonClass.isSave = false;
                SingletonClass.isFirstAcc = false;
                SingletonClass.txStatusSelect = null;
                SingletonClass.txTypeEdit = null;
                SingletonClass.clientCompanyName = null;

                tabbedPane.setFocusable(true);

                trackingPanel.removeAll();
                menu = new AccordionMenu(getMenuDescriptorTracking(""), this, trackingPanel, email, tabbedPane);
                menu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
                menu.setBackground(new Color(229, 232, 235));
                trackingPanel.add(menu);
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                isTrackingTabChanged = true;
            }

            // if tab selection is New Transaction
            if (tabbedPane.getSelectedIndex() == 1) {
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                newTxPanel.removeAll();

                // call the New Transaction panel where each different panels for each accordion is added dynamically
                NewTxPanel txPanel = new NewTxPanel(email, trackingPanel, maiScreen, tabbedPane);
                txPanel.setBackground(new Color(229, 232, 235));
                txPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
                newTxPanel.add(txPanel);
                newTxPanel.repaint();
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            // if tab selection is Reports window
            if (tabbedPane.getSelectedIndex() == 2) {
                SingletonClass.txIdEdit = null;
                SingletonClass.isSave = false;
                SingletonClass.isEdit = false;
                SingletonClass.isView = false;
                SingletonClass.isFirstAcc = false;
                SingletonClass.txStatusSelect = null;
                SingletonClass.txTypeEdit = null;
                SingletonClass.clientCompanyName = null;
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                reportsPanel.removeAll();
                reportPanel = new ReportPanel(email, this);
                reportPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
                reportsPanel.add(reportPanel);

                reportsPanel.repaint();
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            if (tabbedPane.getSelectedIndex() == 3) {
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SingletonClass.txIdEdit = null;
                SingletonClass.isSave = false;
                SingletonClass.isEdit = false;
                SingletonClass.isView = false;
                SingletonClass.isFirstAcc = false;
                SingletonClass.clientCompanyName = null;
                toolsPanel.removeAll();

                // get the accordion name and counts from getMenuDescriptorTools() method
                toolMenu = new com.msr.tools.UI.AccordionMenu(getMenuDescriptorTools(), this, toolsPanel, email, "");
                toolMenu.setBackground(new Color(229, 232, 235));
                toolMenu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
                toolsPanel.add(toolMenu);
                toolsPanel.repaint();
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_tabbedPaneStateChanged
// call this method when user clicks on logout button
    private void logoutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseClicked
        maiScreen.dispose();
        LoginFrame loginFrmae = new LoginFrame("", "");
        loginFrmae.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        loginFrmae.setVisible(true);
        SingletonClass.txIdEdit = null;
        SingletonClass.clientCompanyName = null;
        SingletonClass.designation = null;
        SingletonClass.loginUserName = null;
        SingletonClass.uNameEmail = null;
    }//GEN-LAST:event_logoutLabelMouseClicked
boolean isTrackingTabChanged = false;
    private void tabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneMouseClicked
        // TODO add your handling code here:
        try {
             if (tabbedPane.getSelectedIndex() == 0 && !isTrackingTabChanged) {
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SingletonClass.txIdEdit = null;
                SingletonClass.isEdit = false;
                SingletonClass.isView = false;
                SingletonClass.isSave = false;
                SingletonClass.isFirstAcc = false;
                SingletonClass.txStatusSelect = null;
                SingletonClass.txTypeEdit = null;
                SingletonClass.clientCompanyName = null;
                 System.out.println("");
                tabbedPane.setFocusable(true);

                trackingPanel.removeAll();
                menu = new AccordionMenu(getMenuDescriptorTracking(""), this, trackingPanel, email, tabbedPane);
                menu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
                menu.setBackground(new Color(229, 232, 235));
                trackingPanel.add(menu);
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                trackingPanel.repaint();
                trackingPanel.validate();
            }
                isTrackingTabChanged = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_tabbedPaneMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new MainScreen("rahul.ch@msronline.in", "Rahul").setVisible(true);
        SingletonClass.designation = "RM";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel logoutLabel;
    private javax.swing.JPanel newTxPanel;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JPanel toolsPanel;
    private javax.swing.JPanel trackingPanel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
