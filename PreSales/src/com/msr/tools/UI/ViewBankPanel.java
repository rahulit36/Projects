package com.msr.tools.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.util.SingletonClass;
import com.parser.ContactDetailBean;
import com.parser.DocumentsDetailBean;
import com.parser.UserXMLReader;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Rahul  * @version 1.0  * @since 29 jan 2013
 */
public class ViewBankPanel extends javax.swing.JPanel 
{

    ToolsEditViewFrame tool = null;
    List bankConList = null;    
    int rowNumber = 0;
    String userEmailId = null;
    /**
     *
     * @param bankId
     * @param toolsViewFrame
     */
    // set the data in fields from database
    public ViewBankPanel(String bankId, ToolsEditViewFrame toolsViewFrame, String userEmail,String aliasName) 
    {
        this.tool = toolsViewFrame;
        this.userEmailId = userEmail;
        initComponents();  
        
        HashMap<String, List> bankInfoMap = new UserXMLReader().extractBankDetailsXml(userEmail, bankId, aliasName);
        List<HashMap> bankList = bankInfoMap.get("bankList");
        List<ContactDetailBean> contactList = bankInfoMap.get("contactList");
        List<DocumentsDetailBean> docList = bankInfoMap.get("docList");
        
        HashMap<String, String> bankMap = bankList.get(0);
        
        
            bankNameField.setText(SingletonClass.replaceEmptySpace(bankMap.get("bankName")));
            bankNameField.setEditable(false);
            aliasField.setText(SingletonClass.replaceEmptySpace(bankMap.get("alias")));
            aliasField.setEditable(false);
            branchField.setText(SingletonClass.replaceEmptySpace(bankMap.get("branch")));
            branchField.setEditable(false);
            bankTypeComboBox.setSelectedItem(bankMap.get("bankType").toString());
            bankTypeComboBox.setEditable(false);
            addressField.setText(URLDecoder.decode(SingletonClass.replaceEmptySpace(bankMap.get("address"))));
            addressField.setEditable(false);
            ifciCodeField.setText(SingletonClass.replaceEmptySpace(bankMap.get("ifscCode")));
            ifciCodeField.setEditable(false);
            accountNoField.setText(SingletonClass.replaceEmptySpace(bankMap.get("accountNo")));
            accountNoField.setEditable(false);
      faxNoField.setText(SingletonClass.replaceEmptySpace(bankMap.get("faxNo")));
      faxNoField.setEditable(false);
            
            
            
            DefaultTableModel model = (DefaultTableModel)personTable.getModel();
            DefaultTableModel docModel = (DefaultTableModel)documentTable.getModel();
            TableColumn col1 = personTable.getColumnModel().getColumn(0);
            col1.setMaxWidth(50);
// set the delete button from tablerenderer
            TableColumn col6 = documentTable.getColumnModel().getColumn(3);
            col6.setMaxWidth(50);
            col6.setCellRenderer(new IconTableCellRenderer("location"));
            
             TableColumn col3 = documentTable.getColumnModel().getColumn(4);
            col3.setMaxWidth(50);
            col3.setCellRenderer(new IconTableCellRenderer("View"));
            
TableColumn docloc = documentTable.getColumnModel().getColumn(5);
            docloc.setWidth(0);
            docloc.setMinWidth(0);
            docloc.setMaxWidth(0);
//
//            TableColumn col7 = personTable.getColumnModel().getColumn(7);
//            col7.setWidth(0);
//            col7.setMinWidth(0);
//            col7.setMaxWidth(0);
            
            int counter = 0;
            System.out.println("list size is "+contactList.size()+" <<<>>> "+docList.size());
            Iterator itr = contactList.iterator();
            while (itr.hasNext()) {
                ContactDetailBean contactBean = (ContactDetailBean)itr.next();
                model.insertRow(counter, new Object[]{});
                personTable.setRowHeight(30);
                personTable.setValueAt(counter+1, counter, 0);
                personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactName()), counter, 1);
                personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactDesignation()), counter, 2);
                personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactMobile()), counter, 3);
                personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactEmail()), counter, 4);
//                personTable.setValueAt(contactBean.getContactId(), counter, 5);
                
             counter++;
            }
           
            int count = 0;
            Iterator utr = docList.iterator();
                while(utr.hasNext()){
                    DocumentsDetailBean docBean = (DocumentsDetailBean)utr.next();
                    
                    documentTable.setRowHeight(30);
                    docModel.insertRow(count, new Object[]{});
                documentTable.setRowHeight(30);
                documentTable.setValueAt(count+1, count, 0);
                documentTable.setValueAt(SingletonClass.replaceEmptySpace(docBean.getDocName()), count, 1);
                documentTable.setValueAt(docBean.getDocType(), count, 2);
                documentTable.setValueAt(docBean.getDocFTPPath(), count, 5);
                documentTable.setValueAt(docBean.getDocLocation(), count, 6);
                System.out.println("docBean.getDocFTPPath() "+docBean.getDocFTPPath());
                count++;
                }
                
                TableColumn docPath = documentTable.getColumnModel().getColumn(5);
            docPath.setWidth(0);
            docPath.setMinWidth(0);
            docPath.setMaxWidth(0);
            
             TableColumn col11 = documentTable.getColumnModel().getColumn(0);
            col11.setMaxWidth(50);
            
            documentTable.setEnabled(false);
            personTable.setEnabled(false);
                
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        titalPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bank1Panel = new javax.swing.JPanel();
        bankNameLabel = new javax.swing.JLabel();
        bankNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        aliasField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        branchField = new javax.swing.JTextField();
        bankTypeComboBox = new javax.swing.JComboBox();
        bankTypeLabel = new javax.swing.JLabel();
        addressScroll = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        ifciCodeField = new javax.swing.JTextField();
        accountNoField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        FanNoLabel = new javax.swing.JLabel();
        faxNoField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        personTable = new javax.swing.JTable();
        resetBankButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        documentTable = new javax.swing.JTable();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("View  Bank");

        javax.swing.GroupLayout titalPanelLayout = new javax.swing.GroupLayout(titalPanel);
        titalPanel.setLayout(titalPanelLayout);
        titalPanelLayout.setHorizontalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titalPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titalPanelLayout.setVerticalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        bank1Panel.setBackground(new java.awt.Color(210, 223, 219));

        bankNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bankNameLabel.setForeground(new java.awt.Color(0, 103, 204));
        bankNameLabel.setText("Bank Name");

        bankNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankNameFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 103, 204));
        jLabel2.setText("Alias Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 103, 204));
        jLabel3.setText("Branch ");

        branchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchFieldActionPerformed(evt);
            }
        });

        bankTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Select---", "Opening", "Discounting" ,"Both"}));
        bankTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankTypeComboBoxActionPerformed(evt);
            }
        });

        bankTypeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bankTypeLabel.setForeground(new java.awt.Color(0, 102, 204));
        bankTypeLabel.setText("Bank Type");

        addressScroll.setHorizontalScrollBar(null);

        addressField.setColumns(20);
        addressField.setRows(5);
        addressScroll.setViewportView(addressField);

        addressLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(0, 103, 204));
        addressLabel.setText("Address");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 103, 204));
        jLabel4.setText("IFCI CODE");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 103, 204));
        jLabel5.setText("Account No. ");

        FanNoLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FanNoLabel.setForeground(new java.awt.Color(0, 103, 204));
        FanNoLabel.setText("Fax No.");

        javax.swing.GroupLayout bank1PanelLayout = new javax.swing.GroupLayout(bank1Panel);
        bank1Panel.setLayout(bank1PanelLayout);
        bank1PanelLayout.setHorizontalGroup(
            bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bank1PanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bankNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FanNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(faxNoField)
                    .addComponent(accountNoField)
                    .addComponent(ifciCodeField)
                    .addComponent(addressScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bankTypeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE)
                    .addComponent(bankNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(aliasField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(branchField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        bank1PanelLayout.setVerticalGroup(
            bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bank1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aliasField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ifciCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(faxNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FanNoLabel))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        JTableHeader header = personTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        personTable.setFont(new java.awt.Font("Tahoma", 1, 12));
        personTable.setFont(new java.awt.Font("Tahoma", 1, 12));
        personTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Contact Person", "Designation", "Mobile Number", "E-mail Id"
            }
        ));
        personTable.setRowHeight(35);
        jScrollPane1.setViewportView(personTable);

        resetBankButton.setBackground(new java.awt.Color(0, 103, 204));
        resetBankButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        resetBankButton.setForeground(new java.awt.Color(255, 255, 255));
        resetBankButton.setText("CLOSE");
        resetBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBankButtonActionPerformed(evt);
            }
        });

        JTableHeader header1 = documentTable.getTableHeader();
        header1.setBackground(new Color(192, 191, 200));
        header1.setPreferredSize(new Dimension(600,35));
        Font font1 = new Font("Serif", Font.BOLD, 14);
        header1.setForeground(new Color(0, 103, 204));

        header1.setFont(font1);
        documentTable.setRowHeight(30);
        documentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No.", "Document Name", "Type", "","","",""
            }
        ));
        TableColumn docloc = documentTable.getColumnModel().getColumn(6);
        docloc.setWidth(0);
        docloc.setMinWidth(0);
        docloc.setMaxWidth(0);

        TableColumn docPath = documentTable.getColumnModel().getColumn(5);
        docPath.setWidth(0);
        docPath.setMinWidth(0);
        docPath.setMaxWidth(0);
        documentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                documentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(documentTable);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(bank1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(titalPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bank1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents

private void bankNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankNameFieldActionPerformed

}//GEN-LAST:event_bankNameFieldActionPerformed

private void branchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchFieldActionPerformed

}//GEN-LAST:event_branchFieldActionPerformed

private void bankTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankTypeComboBoxActionPerformed

}//GEN-LAST:event_bankTypeComboBoxActionPerformed

private void resetBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBankButtonActionPerformed
      tool.dispose();
}//GEN-LAST:event_resetBankButtonActionPerformed

    private void documentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentTableMouseClicked
        tool.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DefaultTableModel tableModel = (DefaultTableModel) documentTable.getModel();
    int row = documentTable.rowAtPoint(evt.getPoint());
    int col = documentTable.columnAtPoint(evt.getPoint());
    if(col ==3){
         String location = "";
         if(documentTable.getValueAt(row, 6)!=null)
             location = documentTable.getValueAt(row, 6).toString();
         DocLocationFrame locFrame = new DocLocationFrame(userEmailId,row,6,null,null,this, location);
         locFrame.setVisible(true);
     }else if(col ==4){
      if(documentTable.getValueAt(row, 5)==null||documentTable.getValueAt(row, 5).toString().equals(""))  {
            JOptionPane.showMessageDialog(null, "No document uploaded", "View Document", JOptionPane.INFORMATION_MESSAGE);
            tool.setCursor(Cursor.getDefaultCursor());
        }else{    
        tool.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
           Desktop d = Desktop.getDesktop();
        try {
             
//             boolean flag = new DownloadUploadFile().getFTPFile(documentTable.getValueAt(row, 6).toString(), "", documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf", SingletonClass.sysFilePath);
            boolean flag = new DownloadUploadFile().getFTPFile(documentTable.getValueAt(row, 5).toString(), "", SingletonClass.sysFilePath);
            if(flag){
                tool.setCursor(Cursor.getDefaultCursor());
//            d.open(new File(SingletonClass.sysFilePath+documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf"));
                 d.open(new File(SingletonClass.sysFilePath + documentTable.getValueAt(row, 5).toString().substring(documentTable.getValueAt(row, 5).toString().lastIndexOf("/"))));
            }else{
                JOptionPane.showMessageDialog(null, "File not found", "View Document", JOptionPane.ERROR_MESSAGE);
                 tool.setCursor(Cursor.getDefaultCursor());
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }
    }
    tool.setCursor(Cursor.getDefaultCursor());
        // TODO add your handling code here:
    }//GEN-LAST:event_documentTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FanNoLabel;
    private javax.swing.JTextField accountNoField;
    private javax.swing.JTextArea addressField;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JScrollPane addressScroll;
    private javax.swing.JTextField aliasField;
    private javax.swing.JPanel bank1Panel;
    private javax.swing.JTextField bankNameField;
    private javax.swing.JLabel bankNameLabel;
    private javax.swing.JComboBox bankTypeComboBox;
    private javax.swing.JLabel bankTypeLabel;
    private javax.swing.JTextField branchField;
    private javax.swing.JTable documentTable;
    private javax.swing.JTextField faxNoField;
    private javax.swing.JTextField ifciCodeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable personTable;
    private javax.swing.JButton resetBankButton;
    private javax.swing.JPanel titalPanel;
    // End of variables declaration//GEN-END:variables
}
