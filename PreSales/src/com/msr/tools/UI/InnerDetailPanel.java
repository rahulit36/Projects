package com.msr.tools.UI;

import com.msr.tracking.UI.MainScreen;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class InnerDetailPanel extends javax.swing.JPanel {

    /**
     *
     */
    protected boolean selected = false;
    /**
     *
     */
    public String emailStr = "";
    private MainScreen homePage = null;

    /**
     *
     * @param accodionName
     * @param email
     * @param home
     */
    // select the accordion panel according to selection of accordion click
    public InnerDetailPanel(String accodionName, String email, MainScreen home, JPanel toolsPanel, String designation) {
        initComponents();
        this.emailStr = email;
        this.homePage = home;
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        if (accodionName.equals("Manage User")) {
            this.removeAll();
            UserDetailPanel indDetailObj = new UserDetailPanel(emailStr, accodionName, homePage, toolsPanel,designation);
            indDetailObj.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 430);
            indDetailObj.setVisible(true);
            this.add(indDetailObj);
            this.repaint();
        }
        if (accodionName.equals("Manage Bank")) {
            this.removeAll();
            BankDetailPanel bankDetailObj = new BankDetailPanel(emailStr, accodionName, homePage, "",toolsPanel);
            bankDetailObj.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 430);
            bankDetailObj.setVisible(true);
            this.add(bankDetailObj);
            this.repaint();
        }
        if (accodionName.equals("Manage Company")) {
            this.removeAll();
            ClientDetailPanel clientDetailObj = new ClientDetailPanel(emailStr, accodionName, homePage,toolsPanel,"");
            clientDetailObj.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 430);
            clientDetailObj.setVisible(true);
            this.add(clientDetailObj);
            this.repaint();
        }
        if (accodionName.equals("Meeting Mail")) {
           this.removeAll();
            com.msr.tools.UI.MeetingDetailsPanel meetingDtls = new com.msr.tools.UI.MeetingDetailsPanel(emailStr, accodionName, homePage,toolsPanel);
            meetingDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 370);
            meetingDtls.setVisible(true);
            this.add(meetingDtls);
            this.repaint();
        }

    }
//    public InnerDetailPanel(String accodionName, String email, MainScreen home, List resultList, String searchStr)
//    {    
//        initComponents(); 
//        this.emailStr = email; 
//        this.homePage = home;
//        this.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
//        if(accodionName.equals("Manage LC"))
//        {            
//            this.removeAll();
//            LCSearchResultPanel newLcDetailObj = new LCSearchResultPanel(emailStr, accodionName, homePage, resultList, searchStr);
//            newLcDetailObj.setVisible(true);
//            newLcDetailObj.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-20,Toolkit.getDefaultToolkit().getScreenSize().height-430);
//            this.add(newLcDetailObj);
//            this.repaint();            
//        }  
//        if(accodionName.equals("Manage Bank"))
//        {
//            this.removeAll();
//            BankSearchResultPanel newBankDetailObj = new BankSearchResultPanel(emailStr, accodionName, homePage, resultList, searchStr);
//            newBankDetailObj.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-20,Toolkit.getDefaultToolkit().getScreenSize().height-430);
//            newBankDetailObj.setVisible(true);
//            this.add(newBankDetailObj);
//            this.repaint();
//        }
//        if(accodionName.equals("Manage Company"))
//        {
//            this.removeAll();
//            ClientSearchResultPanel newClientDetailObj = new ClientSearchResultPanel(emailStr, accodionName, homePage, resultList, searchStr);
//            newClientDetailObj.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-20,Toolkit.getDefaultToolkit().getScreenSize().height-430);
//            newClientDetailObj.setVisible(true);
//            this.add(newClientDetailObj);
//            this.repaint();
//        }
//    }

    InnerDetailPanel() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     *
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
//            setIcon(selectedIcon);
            setFont(getFont().deriveFont(Font.BOLD));
        } else {
//            setIcon(normalIcon);
            setFont(getFont().deriveFont(Font.PLAIN));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        innerDetailPanel = new javax.swing.JPanel();

        javax.swing.GroupLayout innerDetailPanelLayout = new javax.swing.GroupLayout(innerDetailPanel);
        innerDetailPanel.setLayout(innerDetailPanelLayout);
        innerDetailPanelLayout.setHorizontalGroup(
            innerDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        innerDetailPanelLayout.setVerticalGroup(
            innerDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(innerDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(innerDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel innerDetailPanel;
    // End of variables declaration//GEN-END:variables
}
