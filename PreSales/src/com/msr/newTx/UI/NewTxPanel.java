/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.tracking.dao.GetTrackingStatusAndCount;
import com.msr.tracking.UI.MainScreen;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class NewTxPanel extends javax.swing.JPanel {

    AccordionMenu menu = null;
    com.msr.tracking.UI.AccordionMenu menu1 = null;
    String email = "";
    JPanel trackingPanel = null;
    MainScreen maiScreen = null;
    JTabbedPane tabbedPane = null;

    /**
     * Creates new form NewTxPanel
     */
    // add the accordion panel to NewTxPanel.
    public NewTxPanel(String email, JPanel trackingPanel, MainScreen maiScreen, JTabbedPane tabbedPane) {
        initComponents();
        this.email = email;
        this.trackingPanel = trackingPanel;
        this.maiScreen = maiScreen;
        this.tabbedPane = tabbedPane;
        accPanel.removeAll();
        System.out.println(new Date() + "-----------------------------Befor-----------------");
        menu = new AccordionMenu(getMenuDescriptorNewTx(), this, email, tabbedPane, maiScreen);
        accPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        menu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        System.out.println(new Date() + "-----------------------------After-----------------");
        menu.setBackground(new Color(229, 232, 235));
        accPanel.add(menu);
        accPanel.repaint();
        accPanel.validate();
    }

    public void refreshScreen( JPanel jPanel) {
        jPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        accPanel.removeAll();
        System.out.println(new Date() + "-----------------------------Befor---refreshing--------------");
        menu = new AccordionMenu(getMenuDescriptorNewTx(), this, email, tabbedPane, maiScreen);
        accPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        menu.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
        menu.setBackground(new Color(229, 232, 235));
        accPanel.add(menu);
        accPanel.repaint();
        accPanel.validate();
        System.out.println(new Date() + "-----------------------------After----refreshing-------------");


//        trackingPanel.removeAll();
//        System.out.println(new Date() + "-----------------------------Befor---tracking refreshing--------------");
//        menu1 = new com.msr.tracking.UI.AccordionMenu(getMenuDescriptorTracking(""), maiScreen, null, email, tabbedPane);
//        menu1.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
//        menu1.setBackground(new Color(229, 232, 235));
//        trackingPanel.add(menu1);
//        trackingPanel.repaint();
//        trackingPanel.validate();
//        System.out.println(new Date() + "-----------------------------After---tracking refreshing--------------");
//        jPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

// get the accordion name and count from GetNoOfEntryInTable class.
    public String getMenuDescriptorNewTx() {

        StringBuilder menuDescripter = new StringBuilder();
        com.msr.newTx.UI.GetNoOfEntryInTable countObjMap = new com.msr.newTx.UI.GetNoOfEntryInTable();
        HashMap statusMap = new LinkedHashMap();
        statusMap = countObjMap.getCounts();
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

        jPanel5 = new javax.swing.JPanel();
        accPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout accPanelLayout = new javax.swing.GroupLayout(accPanel);
        accPanel.setLayout(accPanelLayout);
        accPanelLayout.setHorizontalGroup(
            accPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );
        accPanelLayout.setVerticalGroup(
            accPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(accPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(accPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accPanel;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
