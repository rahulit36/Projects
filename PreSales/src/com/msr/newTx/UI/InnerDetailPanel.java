package com.msr.newTx.UI;

import com.msr.tracking.UI.MainScreen;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import java.util.List;
import javax.swing.JTabbedPane;

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
    private NewTxPanel homePage = null;
 MainScreen maiScreen = null;
    /**
     *
     * @param accodionName
     * @param email
     * @param home
     */
    public InnerDetailPanel(String accodionName, String email, NewTxPanel home, JTabbedPane tabbedPane, MainScreen maiScreen) {
        initComponents();
        this.emailStr = email;
        this.homePage = home;
        this.maiScreen = maiScreen;
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));

        // add Client Details Accordion Panel
        if (accodionName.equalsIgnoreCase("CLIENT DETAILS")) {
            this.removeAll();
            ClientDetailsPanel clientDtls = new ClientDetailsPanel(emailStr, accodionName, homePage, maiScreen);
            clientDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
            clientDtls.setVisible(true);
            this.add(clientDtls);
            this.repaint();
        }
        // add MEETING Details Accordion Panel
        if (accodionName.equalsIgnoreCase("MEETING DETAILS")) {
            this.removeAll();
            MeetingDetailsPanel meetingDtls = new MeetingDetailsPanel(emailStr, accodionName, homePage,tabbedPane, maiScreen);
            meetingDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 370);
            meetingDtls.setVisible(true);
            this.add(meetingDtls);
            this.repaint();
        }

        // add PROPOSAL Details Accordion Panel

        if (accodionName.equalsIgnoreCase("PROPOSAL DETAILS")) {
            this.removeAll();
            ProposalDetailsPanel meetingDtls = new ProposalDetailsPanel(emailStr, accodionName, homePage,tabbedPane, maiScreen);
            meetingDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 350);
            meetingDtls.setVisible(true);
            this.add(meetingDtls);
            this.repaint();
        }

        // add DOCUMENT LIST Accordion Panel

        if (accodionName.equalsIgnoreCase("DOCUMENT LIST")) {
            this.removeAll();
            DocumentListPanel meetingDtls = new DocumentListPanel(emailStr, accodionName, homePage,tabbedPane, maiScreen);
            meetingDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 350);
            meetingDtls.setVisible(true);
            this.add(meetingDtls);
            this.repaint();
        }

        // add BANK QUERIES Accordion Panel

        if (accodionName.equalsIgnoreCase("BANK QUERIES")) {
            try{
            this.removeAll();
            BankQueriesPanel meetingDtls = new BankQueriesPanel(emailStr, accodionName, homePage,tabbedPane, maiScreen);
            meetingDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 350);
            meetingDtls.setVisible(true);
            this.add(meetingDtls);
            this.repaint();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        // add SEND E-EMAIL Accordion Panel
        
        if (accodionName.equalsIgnoreCase("SEND E-MAIL")) {
            this.removeAll();
            SendEmailsPanel meetingDtls = new SendEmailsPanel(emailStr, accodionName, homePage,tabbedPane, maiScreen);
            meetingDtls.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 400);
            meetingDtls.setVisible(true);
            this.add(meetingDtls);
            this.repaint();
        }

	 // add comments Accordion Panel
        
        if (accodionName.equalsIgnoreCase("Comments")) {
            this.removeAll();
            CommentsPanel commentDtl = new CommentsPanel(emailStr, accodionName, homePage,tabbedPane);
            commentDtl.setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 20, Toolkit.getDefaultToolkit().getScreenSize().height - 400);
            commentDtl.setVisible(true);
            this.add(commentDtl);
            this.repaint();
        }

    }

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
            setFont(getFont().deriveFont(Font.BOLD));
        } else {
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
