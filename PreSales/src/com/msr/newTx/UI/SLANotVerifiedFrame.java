/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTabbedPane;

/**
 *
 * @author root
 */
public class SLANotVerifiedFrame extends javax.swing.JFrame {

    JTabbedPane tabbedPane = null;
    String email = "";
    /**
     * Creates new form SLANotVerifiedFrame
     */
    public SLANotVerifiedFrame(String email, JTabbedPane tabbedPane) {
        this.email = email;
        this.tabbedPane = tabbedPane;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - dim.width / 5, dim.height / 2 - dim.height /8);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Yes = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("SLA document is not verified. Do you want to change status to Create Limit without verifying?");

        Yes.setText("Yes");
        Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YesActionPerformed(evt);
            }
        });

        jButton2.setText("No");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Yes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(Yes))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YesActionPerformed
        try {
//            boolean isCreateLimit = false;
            StringBuffer statusChangeBuffer = new StringBuffer();
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            // write the code for change the status to oporunity lost.

//            List<DocumentsDetailBean> docList = new UserXMLReader().getDocDataList(email, SingletonClass.txIdEdit);
//            if (docList.size() > 0) {
//                Iterator itr = docList.iterator();
//                while (itr.hasNext()) {
//                    DocumentsDetailBean docBean = (DocumentsDetailBean) itr.next();
//                    if (docBean.getDocName().contains("service_Agreement") && !docBean.getStatus().equals("Verified")) {
//                        this.dispose();
//                        JOptionPane.showMessageDialog(null, "SLA document is not Verified, status cannot be changed.", "", 0);
//                        isCreateLimit = false;
//                        break;
//                    } else {
//                        isCreateLimit = true;
//                    }
//                }
//            }
//            if (isCreateLimit) {
                statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType)
                .append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit).append("&")
                .append(SingletonClass.txStatus).append("=").append("Create Limit").append("&").append(SingletonClass.userName).append("=").append(email);

                callServerUrl callUrl = new callServerUrl();
                String status = callUrl.urlProcessiong(statusChangeBuffer.toString().replaceAll(" ", "%20"));
                System.out.println("Status of change in status is " + status);
                this.dispose();
                tabbedPane.setSelectedIndex(0);
//            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception ex) {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_YesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            tabbedPane.setSelectedIndex(0);
            this.dispose();
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception ex) {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SLANotVerifiedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SLANotVerifiedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SLANotVerifiedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SLANotVerifiedFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SLANotVerifiedFrame("", null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Yes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}