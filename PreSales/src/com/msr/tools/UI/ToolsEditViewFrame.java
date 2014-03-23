package com.msr.tools.UI;


import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author Rahul  * @version 1.0  * @since 29 jan 2013
 */
public class ToolsEditViewFrame extends javax.swing.JFrame {

    /**
     *
     */
    public String emailId;
    private String accordionNameString = "";
    private String email = "";
    private int id = 0;  
    
    /**
     *
     */
    public JPanel parentPanel = null;   
    /**
     *
     */
    public ToolsEditViewFrame() 
    {  
        initComponents();         
    }   
     
    /**
     *
     * @param flag
     * @param accordionName
     * @param id
     * @param emailStr
     * @param parent
     * @param userEmail
     */
    
    // here select the accordoin and display corresponding panel
    
    public ToolsEditViewFrame(boolean flag, String accordionName,int id, String emailStr, JPanel parent, String userEmail) {

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));
        this.setVisible(true);
        this.emailId = emailStr;
        this.accordionNameString = accordionName;        
        this.setVisible(true);
        this.id = id;     
        this.email = userEmail;
        if(flag)
        {    
            if(accordionName.equals("User"))
            { 
                this.setTitle("User View");
                ViewUserPanel viewuser = new ViewUserPanel(emailId,id, this, email);
                this.add(viewuser);
            }
            
            
        }
        else
        {
            if(accordionName.equals("User"))
            { 
                this.setTitle("User Edit");        
                System.out.println("euser id is "+emailStr);
                EditUserPanel edituser = new EditUserPanel(emailStr, this, id, (UserDetailPanel)parent, email);
                this.add(edituser);
            }
            
            
        }
    }  
//    constructor for viewing and editing client...................
      public ToolsEditViewFrame(boolean flag, String accordionName,String id, String emailStr, JPanel parent, String userEmail,String aliasName) {

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));
        this.setVisible(true);
        this.emailId = emailStr;
        this.accordionNameString = accordionName;        
        this.setVisible(true);
          
//        this.email = userEmail;
        if(flag)
        {    
            
            if(accordionName.equals("Manage Company"))
            { 
                this.setTitle("Company View");
                ViewCompanyPanel viewCompany = new ViewCompanyPanel(id,this,emailStr);
                this.add(viewCompany);
            }
            if(accordionName.equals("Manage Bank"))
            { 
                this.setTitle("Bank View");
                ViewBankPanel viewBank = new ViewBankPanel(id,this, emailId,aliasName);
                this.add(viewBank);
            }
        }
        else
        {
            
            if(accordionName.equals("Manage Company"))
            { 
                this.setTitle("Company Edit");
                EditClientPanel editCompany = new EditClientPanel(id,this, parent,emailStr);
                this.add(editCompany);
            }
            if(accordionName.equals("Manage Bank"))
            { 
                this.setTitle("Bank Edit");
                EditBankPanel viewBank = new EditBankPanel(id,this, parent, emailStr,aliasName);
                this.add(viewBank);
            }
            
        }
    }  
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        viewPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                new ToolsEditViewFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables

}
