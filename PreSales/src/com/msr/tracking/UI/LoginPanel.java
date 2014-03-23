package com.msr.tracking.UI;

import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.ParserCaller;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class LoginPanel extends javax.swing.JPanel {

    LoginFrame loginFrame = null;
    List<String> userList = null;

    /**
     *
     * @param loginFrame
     */
    public LoginPanel(LoginFrame loginFrame, String userMail, String password) {
        if (userMail.equals("") && password.contains("")) {
            this.loginFrame = loginFrame;
            ParserCaller parserCaller = new ParserCaller();
            userList = parserCaller.parseUserListXml();
            initComponents();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//            userList = new ArrayList<String>();
            if (userList != null) {

                Collections.sort(userList);
                AutoCompleteDecorator.decorate(emailField, userList, false);

            }
//            String thought = "For beautiful eyes, look for the good in others; for beautiful lips, speak only words of kindness; and for poise, walk with the knowledge that you are never alone.";
            String thought = parserCaller.parseQuotesListXml();
            System.out.println("quote is " + thought);
            StringBuffer strBuffer = new StringBuffer("<html>").append(thought).append("</html>");
            String msg = strBuffer.toString();
//            quoteLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // N
            quoteLabel.setText(msg);

            //===========================================================Used this code set as dafault button 
            loginFrame.getRootPane().setDefaultButton(loginButton);
            //=================================================================
            this.setSize(dim.width - 10, dim.height - 50);
            this.setVisible(true);
        } else {
            callServerUrl serverUrl = new callServerUrl();
            String response = serverUrl.urlProcessiong(SingletonClass.httpServerUrl + SingletonClass.userName + "=" + userMail + "&" + SingletonClass.password + "=" + password + "&" + SingletonClass.operaionType + "=login");

            if (!"invalid".equalsIgnoreCase(response)) {
                Map<String, String> loginMap = null;
                ParserCaller parserCaller = new ParserCaller();
                loginMap = parserCaller.parseLoginXml(response);
//        JOptionPane.showMessageDialog(null, "Successfully Login", "", 1);
                SingletonClass.designation = loginMap.get("designation");
                SingletonClass.loginUserName = loginMap.get("uName");
                SingletonClass.uNameEmail = loginMap.get("email");
                SingletonClass.empDesignation = loginMap.get("empDesignation");
                SingletonClass.mobNo = loginMap.get("mobNo");
                if (SingletonClass.uNameEmail.equalsIgnoreCase(userMail)) {
                    new MainScreen(userMail, SingletonClass.loginUserName).setVisible(true);
                    loginFrame.dispose();
                    serverUrl = null;
                    this.setVisible(false);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        logoLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginBoxPanel = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pwdLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();
        forgetPasswordLabel = new javax.swing.JLabel();
        displayLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        quoteLabel = new javax.swing.JLabel();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel8 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(12, 60, 111));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/investeurs_logo.png"))); // NOI18N
        logoLabel.setOpaque(true);
        logoLabel.setBounds(10, 0, 265, 50);
        jLayeredPane1.add(logoLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_header.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setBounds(0, 0, 1366, 70);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("BUSINESS");
        jLabel4.setBounds(30, 10, 130, 30);
        jLayeredPane2.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel7.setText("|");
        jLabel7.setBounds(190, 40, 20, 40);
        jLayeredPane2.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("LOGIN");
        jLabel5.setBounds(210, 40, 90, 40);
        jLayeredPane2.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DEVELOPMENT");
        jLabel6.setBounds(0, 50, 190, 30);
        jLayeredPane2.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_txt_bg_shine.png"))); // NOI18N
        jLabel3.setBounds(0, 0, 349, 100);
        jLayeredPane2.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        loginBoxPanel.setBackground(new java.awt.Color(11, 69, 130));
        loginBoxPanel.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);

        userNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        userNameLabel.setText("USER NAME:");

        emailField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/txt_box_saprator_line.jpg"))); // NOI18N

        pwdLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pwdLabel.setForeground(new java.awt.Color(255, 255, 255));
        pwdLabel.setText("PASSWORD:");

        loginButton.setBackground(new java.awt.Color(204, 204, 204));
        loginButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        loginButton.setForeground(new java.awt.Color(0, 103, 204));
        loginButton.setText("LOGIN");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        loginButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonKeyPressed(evt);
            }
        });

        jPasswordField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        forgetPasswordLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        forgetPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        forgetPasswordLabel.setText("FORGOT PASSWORD?");
        forgetPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPasswordLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgetPasswordLabelMouseEntered(evt);
            }
        });

        displayLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        displayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("THOUGHT OF THE DAY");
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(6, 94, 171));
        jLabel10.setText("\"");

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(6, 94, 171));
        jLabel11.setText("\"");

        quoteLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        quoteLabel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginBoxPanelLayout = new javax.swing.GroupLayout(loginBoxPanel);
        loginBoxPanel.setLayout(loginBoxPanelLayout);
        loginBoxPanelLayout.setHorizontalGroup(
            loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBoxPanelLayout.createSequentialGroup()
                .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginBoxPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(loginBoxPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quoteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBoxPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101)))
                .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pwdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginBoxPanelLayout.createSequentialGroup()
                        .addComponent(forgetPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(84, 84, 84))
            .addGroup(loginBoxPanelLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(displayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginBoxPanelLayout.setVerticalGroup(
            loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginBoxPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginBoxPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(loginBoxPanelLayout.createSequentialGroup()
                                .addComponent(pwdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(loginBoxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(forgetPasswordLabel))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(loginBoxPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginBoxPanelLayout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addComponent(quoteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/footer_shine.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.setBounds(180, 0, 1070, 50);
        jLayeredPane3.add(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(loginBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(loginBoxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width-50),(Toolkit.getDefaultToolkit().getScreenSize().height-50)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
}//GEN-LAST:event_emailFieldActionPerformed

private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
    try {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        loginBoxPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*[._-]*[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*");
        Matcher m = p.matcher(emailField.getText());
        if (m.matches() == true) {
            callServerUrl serverUrl = new callServerUrl();
            String response = serverUrl.urlProcessiong(SingletonClass.httpServerUrl + SingletonClass.userName + "=" + emailField.getText() + "&" + SingletonClass.password + "=" + jPasswordField.getText() + "&" + SingletonClass.operaionType + "=login");

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            loginBoxPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (!response.equalsIgnoreCase("invalid")) {
                Map<String, String> loginMap = null;
                ParserCaller parserCaller = new ParserCaller();
                loginMap = parserCaller.parseLoginXml(response);
//        JOptionPane.showMessageDialog(null, "Successfully Login", "", 1);
                SingletonClass.designation = loginMap.get("designation");
                SingletonClass.loginUserName = loginMap.get("uName");
                SingletonClass.uNameEmail = loginMap.get("email");
                SingletonClass.privStr = loginMap.get("previleges");
                SingletonClass.empDesignation = loginMap.get("empDesignation");
                SingletonClass.mobNo = loginMap.get("mobNo");
                if (SingletonClass.uNameEmail.equalsIgnoreCase(emailField.getText())) {
                    new MainScreen(emailField.getText(), SingletonClass.loginUserName).setVisible(true);
//        JOptionPane.showMessageDialog(null, "Successfully Login", "", 1);
                    this.setCursor(Cursor.getDefaultCursor());
                    loginBoxPanel.setCursor(Cursor.getDefaultCursor());
                    loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    loginFrame.dispose();
                    serverUrl = null;
                } else {
                    this.setCursor(Cursor.getDefaultCursor());
                    loginBoxPanel.setCursor(Cursor.getDefaultCursor());
                    loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    JOptionPane.showMessageDialog(null, "Invalid email id or password", "", 0);
                    serverUrl = null;
                }
            } else {
                this.setCursor(Cursor.getDefaultCursor());
                loginBoxPanel.setCursor(Cursor.getDefaultCursor());
                loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                JOptionPane.showMessageDialog(null, "Invalid email id or password", "", 0);
                serverUrl = null;
            }
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            loginBoxPanel.setCursor(Cursor.getDefaultCursor());
            loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            JOptionPane.showMessageDialog(null, "Please Enter Valid email id", "", 0);
        }
    } catch (Exception ex) {
        this.setCursor(Cursor.getDefaultCursor());
        loginBoxPanel.setCursor(Cursor.getDefaultCursor());
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ex.printStackTrace();
    }
}//GEN-LAST:event_loginButtonActionPerformed
// call when user cliecks on forgot password field 
private void forgetPasswordLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPasswordLabelMouseClicked
    new ForgotPasswordFrame(loginFrame, emailField.getText()).setVisible(true);
}//GEN-LAST:event_forgetPasswordLabelMouseClicked

private void forgetPasswordLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPasswordLabelMouseEntered
    forgetPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
}//GEN-LAST:event_forgetPasswordLabelMouseEntered

private void loginButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonKeyPressed
}//GEN-LAST:event_loginButtonKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel displayLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel forgetPasswordLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JPanel loginBoxPanel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel pwdLabel;
    private javax.swing.JLabel quoteLabel;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
