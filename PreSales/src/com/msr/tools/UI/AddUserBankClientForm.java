package com.msr.tools.UI;

import com.msr.tracking.UI.MainScreen;
import javax.swing.JPanel;
import java.awt.Toolkit;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class AddUserBankClientForm extends javax.swing.JFrame {

    MainScreen homePage = null;
    private String emailString = "";

    /**
     *
     */
    public AddUserBankClientForm() {
        initComponents();
        this.setVisible(true);
    }

    /**
     *
     * @param formType
     * @param parent
     * @param home
     */
    
    // add user, bank, client accrding to the button clicked, which button is clicked.
    public AddUserBankClientForm(String formType, JPanel parent, MainScreen home, String emailString) {
        this.homePage = home;
        this.emailString = emailString;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));
        if (formType.equals("Company")) {
            this.setTitle("Add New Company");
            AddClientPanel clientPanel = new AddClientPanel(parent, homePage, this,emailString);
            clientPanel.setVisible(true);
            this.add(clientPanel);
        }
        if (formType.equals("User")) {
            this.setTitle("Add New User");
            AddUserPanel userPanel = new AddUserPanel((UserDetailPanel) parent, homePage, this,emailString);
            userPanel.setVisible(true);
            this.add(userPanel);
        }
        if (formType.equals("Bank")) {
            this.setTitle("Add New Bank");
            AddBankPanel bankPanel = new AddBankPanel(parent, homePage, this,emailString);
            bankPanel.setVisible(true);
            this.add(bankPanel);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddUserBankClientForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
