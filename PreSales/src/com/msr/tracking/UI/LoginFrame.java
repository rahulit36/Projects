package com.msr.tracking.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */

public class LoginFrame extends javax.swing.JFrame {

    protected String userId = "";
    protected String userPwd = "";
    /**
     *
     */
    protected int m_w = 6;
    /**
     *
     */
    protected int m_h = 6;
    /**
     *
     */
    protected Color m_topColor = Color.white;
    /**
     *
     */
    protected Color m_bottomColor = Color.gray;

    /**
     *
     */
    public LoginFrame(String useremail, String password) {

        this.userId = useremail;
        this.userPwd = password;
        initComponents();

        try {
            // set the icon of login frame
            setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/investeurs_Icon.png")));

        } catch (Exception ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        // set the size of login frame
        this.setSize(scrSize);
        // add the login panel to the frame
        LoginPanel loginPanel = new LoginPanel(this, userId, userPwd);
        this.add(loginPanel);
        m_w = 6;
        m_h = 6;
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    /**
     *
     * @param w
     * @param h
     */
    public LoginFrame(int w, int h) {
        m_w = w;
        m_h = h;
    }

    /**
     *
     * @param w
     * @param h
     * @param topColor
     * @param bottomColor
     */
    public LoginFrame(int w, int h, Color topColor, Color bottomColor) {
        m_w = w;
        m_h = h;
        m_topColor = topColor;
        m_bottomColor = bottomColor;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BUSINESS DEVELOPMENT");
        setBackground(new java.awt.Color(255, 255, 255));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        String uId = "";
        String pwd = "";

        try {
            //set the look and feel as default in system or Nimbus
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>
        if (args.length > 0) {
            System.out.println("args length is >>>> " + args.length);
            uId = args[0];
            pwd = args[1];
        }
        LoginFrame loginFrame = new LoginFrame(uId, pwd);
        if (uId.equals("")) {
            loginFrame.setVisible(true);
        } else {
            loginFrame.setVisible(false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param c
     * @return
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(m_h, m_w, m_h, m_w);
    }

    /**
     *
     * @return
     */
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     *
     * @param c
     * @param g
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        w--;
        h--;
        g.drawArc(x, y, 2 * m_w, 2 * m_h, 180, -90);
        g.drawLine(x + m_w, y, x + w - m_w, y);
        g.drawArc(x + w - 2 * m_w, y, 2 * m_w, 2 * m_h, 90, -90);

        g.drawLine(x + w, y + m_h, x + w, y + h - m_h);
        g.drawArc(x + w - 2 * m_w, y + h - 2 * m_h, 2 * m_w, 2 * m_h, 0, -90);
        g.drawLine(x + m_w, y + h, x + w - m_w, y + h);
        g.drawArc(x, y + h - 2 * m_h, 2 * m_w, 2 * m_h, -90, -90);
    }
}
