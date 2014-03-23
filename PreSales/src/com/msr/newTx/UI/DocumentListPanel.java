/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.tools.UI.IconTableCellRenderer;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.DocumentsDetailBean;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class DocumentListPanel extends javax.swing.JPanel {

    NewTxPanel newTxPanel = null;
    String email = "";
    int counter = 0;
    File dir = null;
    JTabbedPane tabbedPane = null;
    MainScreen maiScreen = null;
    List<String> pathList = null;

    /**
     * Creates new form DocumentListPanel
     */
    public DocumentListPanel(String email, String accName, NewTxPanel newTxPanel,
            JTabbedPane tabbedPane, MainScreen maiScreen) {

        initComponents();

        pathList = new UserXMLReader().getDocPathListForClient(email);
        TableColumn docIdColumn = documentListTable.getColumnModel().getColumn(8);
        docIdColumn.setWidth(0);
        docIdColumn.setMinWidth(0);
        docIdColumn.setMaxWidth(0);

        TableColumn dirColumn = documentListTable.getColumnModel().getColumn(10);
        dirColumn.setWidth(0);
        dirColumn.setMinWidth(0);
        dirColumn.setMaxWidth(0);

        TableColumn dirPathColumn = documentListTable.getColumnModel().getColumn(11);
        dirPathColumn.setWidth(0);
        dirPathColumn.setMinWidth(0);
        dirPathColumn.setMaxWidth(0);

        TableColumn docLocCol = documentListTable.getColumnModel().getColumn(9);
        docLocCol.setMaxWidth(50);
        docLocCol.setCellRenderer(new IconTableCellRenderer("location"));

        TableColumn col10 = documentListTable.getColumnModel().getColumn(9);
        col10.setMaxWidth(40);
//        col10.setMinWidth(0);
//        col10.setMaxWidth(0);

        this.newTxPanel = newTxPanel;
        this.email = email;
        this.tabbedPane = tabbedPane;
        this.maiScreen = maiScreen;
        statusType.addItem("Not Required");
        statusType.addItem("Pending");
        statusType.addItem("Received");
        statusType.addItem("Required/Pending");
        statusType.addItem("Verified");
        requirementType.addItem("Required");
        requirementType.addItem("Optional");

        if (SingletonClass.txTypeEdit != null && (SingletonClass.txTypeEdit.equalsIgnoreCase("New Solution") || SingletonClass.txTypeEdit.equalsIgnoreCase("Existing-New Solution"))) {
            addListButton.setEnabled(false);
        }


        List<DocumentsDetailBean> docList = new UserXMLReader().getDocDataList(email, SingletonClass.txIdEdit);
        DefaultTableModel tableModel = (DefaultTableModel) documentListTable.getModel();
//        TableColumn col = documentListTable.getColumnModel().getColumn(2);
//        col.setCellEditor(new ComboBoxCellEditor(requirementType));
        TableColumn statusCol = documentListTable.getColumnModel().getColumn(3);
        statusCol.setCellEditor(new ComboBoxCellEditor(statusType));


        if (docList.size() > 0) {
            Iterator itr = docList.iterator();
            while (itr.hasNext()) {
                DocumentsDetailBean docBean = (DocumentsDetailBean) itr.next();

                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") && docBean.getDocName().contains("service_Agreement")) {
                } else {
                    tableModel.insertRow(counter, new Object[]{});
                    documentListTable.setValueAt(counter + 1, counter, 0);
                    documentListTable.setValueAt(docBean.getDocName(), counter, 1);
                    documentListTable.setValueAt(docBean.getDocType(), counter, 2);
                    documentListTable.setValueAt(docBean.getStatus(), counter, 3);
                    documentListTable.setValueAt(docBean.getDocFTPPath(), counter, 7);
                    System.out.println("documentListTable.getValueAt(row, 7)****************** " + documentListTable.getValueAt(counter, 7));
                    documentListTable.setValueAt(docBean.getDocId(), counter, 8);
                    documentListTable.setValueAt(docBean.getDocLocation(), counter, 10);
                    documentListTable.setValueAt(docBean.getDocName(), counter, 12);
                    getPathValue(docBean.getDocLocation(), counter, 11);
                    counter++;
                }
            }
        }

// set the width of serial no. column
        TableColumn col0 = documentListTable.getColumnModel().getColumn(0);
        col0.setMaxWidth(100);

// set the View button
        TableColumn col4 = documentListTable.getColumnModel().getColumn(4);
        col4.setMaxWidth(40);
        col4.setCellRenderer(new IconTableCellRenderer("View"));
// set the View button
        TableColumn col7 = documentListTable.getColumnModel().getColumn(7);
        col7.setMaxWidth(40);
        col7.setCellRenderer(new IconTableCellRenderer("View"));
// set the width of Alarm column button and also set the icon of Alarm        
        TableColumn col5 = documentListTable.getColumnModel().getColumn(5);
        col5.setMaxWidth(40);
        col5.setCellRenderer(new IconTableCellRenderer("upload"));
// set the delete button
//        TableColumn col6 = documentListTable.getColumnModel().getColumn(6);
//        col6.setMaxWidth(40);
//        col6.setCellRenderer(new IconTableCellRenderer("Delete"));


// set the delete button
        TableColumn hiddenColumn = documentListTable.getColumnModel().getColumn(7);
        hiddenColumn.setWidth(0);
        hiddenColumn.setMinWidth(0);
        hiddenColumn.setMaxWidth(0);

        TableColumn col12 = documentListTable.getColumnModel().getColumn(12);
        col12.setWidth(0);
        col12.setMinWidth(0);
        col12.setMaxWidth(0);
    }

    public void getPathValue(String value, int row, int col) {

        System.out.println("value is <<<<<<<<<<<<<<<<<<<<<<<,,, " + value + " and path is : " + pathList);
        System.out.println("SingletonClass.clientCompanyName: " + SingletonClass.clientCompanyName + " SingletonClass.clientBenName " + SingletonClass.clientBenName);
        boolean isCall = false;
        for (String path : pathList) {
            if (value.equalsIgnoreCase(path.split(":")[0])) {
                if (!SingletonClass.clientCompanyName.equals(SingletonClass.clientBenName)) {

                    if (path.split(":")[2].equals("N")) {
                        String pathValue = path.split(":")[1].replace("client-name", SingletonClass.clientCompanyName.replaceAll(" ", "_"));
                        documentListTable.setValueAt("/" + pathValue, row, col);
                        break;
                    } else if (path.split(":")[2].equals("Y")) {
                        String pathValue = path.split(":")[1].replace("client-name", SingletonClass.clientCompanyName.replaceAll(" ", "_")).replace("benf-name", SingletonClass.clientBenName.replaceAll(" ", "_"));
                        documentListTable.setValueAt("/" + pathValue, row, col);
                        break;
                    }
                } else {
                    if (path.split(":")[2].equals("N")) {
                        String pathValue = path.split(":")[1].replace("client-name", SingletonClass.clientCompanyName.replaceAll(" ", "_"));
                        documentListTable.setValueAt("/" + pathValue, row, col);
                        break;
                    } else if (path.split(":")[2].equals("Y")) {
                        String pathValue = path.split(":")[1].replace("client-name", SingletonClass.clientCompanyName.replaceAll(" ", "_")).replace("benf-name", SingletonClass.clientBenName.replaceAll(" ", "_"));
                        documentListTable.setValueAt("/" + pathValue, row, col);
                        break;
                    }
                }
            }
        }
    }

    // add bank list in table
    public void addBanks(JTable tableName) {
        JComboBox statusCombo = new JComboBox();
        statusCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Not Required", "Pending", "Received", "Verified"}));

        TableColumn statusColumn = tableName.getColumnModel().getColumn(2);
        statusCombo.removeAllItems();
        statusColumn.setCellEditor(new DefaultCellEditor(statusCombo));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        requirementType = new javax.swing.JComboBox();
        statusType = new javax.swing.JComboBox();
        documentListTable = new javax.swing.JTable();
        saveDocButton = new javax.swing.JButton();
        manageDocButton = new javax.swing.JButton();
        addListButton = new javax.swing.JButton();

        JTableHeader header = documentListTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        documentListTable.setRowHeight(30);
        documentListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] {
                "S.No.", "Document Name", "Type", "Status","","","","","","","","",""
            }
        ));
        // set the delete button
        TableColumn hiddenColumn = documentListTable.getColumnModel().getColumn(7);
        hiddenColumn.setWidth(0);
        hiddenColumn.setMinWidth(0);
        hiddenColumn.setMaxWidth(0);
        TableColumn docIdColumn = documentListTable.getColumnModel().getColumn(8);
        docIdColumn.setWidth(0);
        docIdColumn.setMinWidth(0);
        docIdColumn.setMaxWidth(0);
        TableColumn col6 = documentListTable.getColumnModel().getColumn(6);
        col6.setWidth(0);
        col6.setMinWidth(0);
        col6.setMaxWidth(0);
        TableColumn col9 = documentListTable.getColumnModel().getColumn(9);
        col9.setMaxWidth(40);
        col9.setResizable(false);
        col6.setResizable(false);

        TableColumn dirColumn = documentListTable.getColumnModel().getColumn(10);
        dirColumn.setWidth(0);
        dirColumn.setMinWidth(0);
        dirColumn.setMaxWidth(0);
        TableColumn col12 = documentListTable.getColumnModel().getColumn(12);
        col12.setWidth(0);
        col12.setMinWidth(0);
        col12.setMaxWidth(0);

        TableColumn dirPathColumn = documentListTable.getColumnModel().getColumn(11);
        dirPathColumn.setWidth(0);
        dirPathColumn.setMinWidth(0);
        dirPathColumn.setMaxWidth(0);
        documentListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                documentListTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(documentListTable);

        saveDocButton.setText("Save");
        saveDocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveDocButtonActionPerformed(evt);
            }
        });

        manageDocButton.setText("Manage Documents");
        manageDocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageDocButtonActionPerformed(evt);
            }
        });

        addListButton.setText("Add List");
        addListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageDocButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveDocButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveDocButton)
                    .addComponent(manageDocButton)
                    .addComponent(addListButton))
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveDocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveDocButtonActionPerformed
        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
            newTxPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            boolean isdocadded = false;
            boolean isDocUploadFail = false;

            DefaultTableModel docModel = (DefaultTableModel) documentListTable.getModel();
            int docRowCount = docModel.getRowCount();

            for (int doccount = 0; doccount < docRowCount; doccount++) {
                System.out.println("Document Name: " + docModel.getValueAt(doccount, 1));
                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") && docModel.getValueAt(doccount, 1).toString().contains("service_Agreement")) {
                } else {
                    if (docModel.getValueAt(doccount, 1) == null || docModel.getValueAt(doccount, 1).toString().equals("")) {

                        JOptionPane.showMessageDialog(this, "Please provide document Name");
                        newTxPanel.setCursor(Cursor.getDefaultCursor());
                        break;

                    } else if (docModel.getValueAt(doccount, 2) == null || docModel.getValueAt(doccount, 2).toString().equals("")) {

                        JOptionPane.showMessageDialog(this, "Please Select " + documentListTable.getValueAt(doccount, 1) + " document type");
                        newTxPanel.setCursor(Cursor.getDefaultCursor());
                        break;

                    } else {

                        isdocadded = true;
                        System.out.println("1111111111111111111111111111111111111111111111111");
                        if (SingletonClass.txStatusSelect.equals("Limit Sanctioned") || SingletonClass.txStatusSelect.equals("Portfolio Client") || SingletonClass.txStatusSelect.equals("Expiry Transition")) {
                            if (("Required").equals((String) docModel.getValueAt(doccount, 2)) && !("Verified".equals((String) docModel.getValueAt(doccount, 3)) || "Required/Pending".equals((String) docModel.getValueAt(doccount, 3)))) {
                                System.out.println("22222222222222222222222222222222");
                                isdocadded = false;
                                JOptionPane.showMessageDialog(this, "Please upload required document.");
                                newTxPanel.setCursor(Cursor.getDefaultCursor());
                                break;
//                            documentListTable.setValueAt(docServerPath, row, 7);
                            } else if (docModel.getValueAt(doccount, 2).toString().equals("Optional")) {

                                if (docModel.getValueAt(doccount, 7) == null) {
                                    documentListTable.setValueAt("", doccount, 7);
                                }
                                System.out.println("33333333333333333333333333333333333333");
                                isdocadded = true;
                            }
                        }
                        if (("Required").equals((String) docModel.getValueAt(doccount, 2)) && ("Verified".equals((String) docModel.getValueAt(doccount, 3)))
                                && (docModel.getValueAt(doccount, 7) == null || "".equals(docModel.getValueAt(doccount, 7)))) {
//                        if (docModel.getValueAt(doccount, 1).toString().contains("service_Agreement")) {
//                            JOptionPane.showMessageDialog(this, "Please upload the SLA document document.");
//                        } else {
                            JOptionPane.showMessageDialog(this, "Please upload the verified document.");
//                        }
                            isdocadded = false;
                            break;
                        }
//                    if (("Not Required").equals((String) docModel.getValueAt(doccount, 3)) || ("Verified").equals((String) docModel.getValueAt(doccount, 3))) {
                        if (("Not Required").equals((String) docModel.getValueAt(doccount, 3))) {
                            if (SingletonClass.designation.equalsIgnoreCase("RM")) {
                                isdocadded = false;
                                JOptionPane.showMessageDialog(this, "you can not change document status to Not Required and Verified.", "", 0);
                                newTxPanel.setCursor(Cursor.getDefaultCursor());
                                break;
                            } else {
                                isdocadded = true;
                            }
                        } else {
                            isdocadded = true;
                        }
                    }
                }
            }
            if (isdocadded) {
                for (int docCount = 0; docCount < docRowCount; docCount++) {
                    System.out.println("Document Name: " + docModel.getValueAt(docCount, 1));
                    if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") && docModel.getValueAt(docCount, 1).toString().contains("service_Agreement")) {
                    } else {
                        String dirName = "";
                        if (docModel.getValueAt(docCount, 10) != null) {
                            dirName = docModel.getValueAt(docCount, 10).toString();
                            if (dirName.contains("/")) {
                                dirName = dirName.split("/")[dirName.split("/").length - 1];
                            }
                        }
                        String newNameOfDoc = "";
                        if (!docModel.getValueAt(docCount, 1).toString().trim().equals(docModel.getValueAt(docCount, 12).toString().trim())) {
                            newNameOfDoc = docModel.getValueAt(docCount, 1).toString().toString().trim();
                        }

                        StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveClientDocs&").append(SingletonClass.docsName).append("=").append(URLEncoder.encode(docModel.getValueAt(docCount, 12).toString())).append("&").append(SingletonClass.newDocName).append("=").append(URLEncoder.encode(newNameOfDoc)).append("&").append(SingletonClass.docsType).append("=").append(docModel.getValueAt(docCount, 2).toString()).append("&").append(SingletonClass.docsPath).append("=").append(URLEncoder.encode((String) docModel.getValueAt(docCount, 7))).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName)).append("&").append(SingletonClass.userName).append("=").append(email).append("&").append("status").append("=").append(docModel.getValueAt(docCount, 3).toString()).append("&").append("docId").append("=").append(docModel.getValueAt(docCount, 8).toString()).append("&txnNo=").append(SingletonClass.txIdEdit).append("&dirName=").append(dirName);
                        String url1 = buffer1.toString();
                        String encodedUrl = url1.replace(" ", "%20");
                        System.out.println("doc upload url is " + encodedUrl);
                        String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                        if (urlStatus1.equals("invalid")) {
                            JOptionPane.showMessageDialog(null, "Error in saving document details of " + docModel.getValueAt(docCount, 1).toString() + " document.", "uploading document", JOptionPane.ERROR_MESSAGE);
                            isDocUploadFail = true;
                            newTxPanel.setCursor(Cursor.getDefaultCursor());
                            break;
                        }
                    }
                }


                if (!isDocUploadFail) {

                    newTxPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    jScrollPane1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    if (SingletonClass.txStatusSelect.equals("Identification Of Client") || SingletonClass.txStatusSelect.equals("Awaiting Internal Approval") || SingletonClass.txStatusSelect.equals("Send Offer Letter") || SingletonClass.txStatusSelect.equals("Awaiting Client Approval")) {
                        JOptionPane.showMessageDialog(null, "Document Details Saved Successfully, Please fill Next", "", 1);
                        SingletonClass.isSave = true;
                        SingletonClass.nextOpenAccordion = "PROPOSAL DETAILS";
                        newTxPanel.refreshScreen(this);
                        newTxPanel.repaint();
                    } else if (SingletonClass.txStatusSelect.equals("Awaiting Documents")) {
                        new DocumentListStatusChangeFrame(email, tabbedPane).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Document Details Saved Successfully, Please fill Next", "", 1);
                        tabbedPane.setSelectedIndex(0);
                        newTxPanel.refreshScreen(this);
                        newTxPanel.repaint();
                    }
//                    newTxPanel.refreshScreen();
//                    tabbedPane.setSelectedIndex(0);
                    newTxPanel.setCursor(Cursor.getDefaultCursor());
                    jScrollPane1.setCursor(Cursor.getDefaultCursor());
                }

            }





        } else {
            JOptionPane.showMessageDialog(null, "Please select the transaction.", "uploading document", JOptionPane.ERROR_MESSAGE);
            newTxPanel.setCursor(Cursor.getDefaultCursor());

        }

    }//GEN-LAST:event_saveDocButtonActionPerformed

//    class TypeOfFile extends FileFilter {
//        //Type of file that should be display in JFileChooser will be set here  
//        //We choose to display only directory and text file  
//
//        public boolean accept(File f) {
//            return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
//        }
//
//        @Override
//        public String getDescription() {
//            // TODO Auto-generated method stub
//            return "pdf files";
//        }
//    }
    public void getDocLocation(String directoryName, String directoryPath, int row, int col) {
        System.out.println("directoryName: " + directoryName + " row: " + row + "  col: " + col);
        documentListTable.setValueAt(directoryName, row, col + 1);
        documentListTable.setValueAt(directoryPath, row, col);
    }
    private void documentListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentListTableMouseClicked


        int row = documentListTable.rowAtPoint(evt.getPoint());
        int col = documentListTable.columnAtPoint(evt.getPoint());

        if (col == 1 && !documentListTable.getValueAt(row, 1).toString().equals("FSA") && !documentListTable.getValueAt(row, 1).toString().equals("Company Profile")) {
            String whatTheUserEntered = JOptionPane.showInputDialog(null, "", "Document Name", JOptionPane.PLAIN_MESSAGE);
            if (whatTheUserEntered == null) {
            } else {
                documentListTable.setValueAt(whatTheUserEntered, row, 1);
            }
        }
        if ((col == 1&& documentListTable.getValueAt(row, 1).toString().equals("FSA")) ||(col == 1&& documentListTable.getValueAt(row, 1).toString().equals("Company Profile"))){
            
                JOptionPane.showMessageDialog(null, "You can not edit the name.", "Document Name", JOptionPane.ERROR_MESSAGE);
        }
        if (col == 4) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (documentListTable.getValueAt(row, 7) == null || documentListTable.getValueAt(row, 7).toString().equals("")) {
                JOptionPane.showMessageDialog(null, "No document uploaded", "View Document", JOptionPane.ERROR_MESSAGE);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                Desktop d = Desktop.getDesktop();
                try {
//                    System.out.println("documentListTable.getValueAt(row, 7): "+documentListTable.getValueAt(row, 7));
//                    System.out.println("documentListTable.getValueAt(row, 1).toString().replace(\" \", \"_\") + \".pdf\" "+documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ".pdf");
//                    System.out.println("SingletonClass.sysFilePath: "+SingletonClass.sysFilePath);
//                    System.out.println("documentListTable.getValueAt(row, 7): "+documentListTable.getValueAt(row, 7));
//                    boolean flag = new DownloadUploadFile().getFTPFile(documentListTable.getValueAt(row, 7).toString(), "", documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ".pdf", SingletonClass.sysFilePath);
                    System.out.println("PATH: "+documentListTable.getValueAt(row, 7));
                    boolean flag = new DownloadUploadFile().getFTPFile(documentListTable.getValueAt(row, 7).toString(), "", SingletonClass.sysFilePath);
                    if (flag) {
                        this.setCursor(Cursor.getDefaultCursor());
//                        d.open(new File(SingletonClass.sysFilePath + documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ".pdf"));
                        System.out.println("doc path: " + SingletonClass.sysFilePath + documentListTable.getValueAt(row, 7).toString().substring(documentListTable.getValueAt(row, 7).toString().lastIndexOf("/")));
                        d.open(new File(SingletonClass.sysFilePath + documentListTable.getValueAt(row, 7).toString().substring(documentListTable.getValueAt(row, 7).toString().lastIndexOf("/"))));
                    } else {
                        JOptionPane.showMessageDialog(null, "File not found", "View Document", JOptionPane.ERROR_MESSAGE);
                        this.setCursor(Cursor.getDefaultCursor());
                    }

                } catch (IOException e) {
                    this.setCursor(Cursor.getDefaultCursor());
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        if (col == 5) {
            try {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (documentListTable.getValueAt(row, 1) == null || documentListTable.getValueAt(row, 1).toString().equals("")) {

                    JOptionPane.showMessageDialog(this, "Please provide document Name");
                    this.setCursor(Cursor.getDefaultCursor());

                } else {
                    JFileChooser filechooser = new JFileChooser();
                    filechooser.setCurrentDirectory(dir);
//                    filechooser.setFileFilter(new TypeOfFile());
                    filechooser.setAcceptAllFileFilterUsed(false);
                    int status = filechooser.showDialog(filechooser, "Upload");
                    dir = filechooser.getCurrentDirectory();

                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    if (status == filechooser.CANCEL_OPTION) {
                    } else {
                        File file = filechooser.getSelectedFile();
                        String path = file.getPath();
                        String ext = path.substring(path.lastIndexOf("."));
                        System.out.println("File extension is" + ext);
                        System.out.println("file path is " + path);
                        if (documentListTable.getValueAt(row, 11) != null && !"".equals(documentListTable.getValueAt(row, 11))) {
                            String serverFilePath = SingletonClass.serverClientDocPath + documentListTable.getValueAt(row, 11).toString().replaceFirst("/", "");
//                        boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverClientDocPath, SingletonClass.clientCompanyName.toString().replace(" ", "_"), path, documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ".pdf");
//                        boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverClientDocPath, SingletonClass.clientCompanyName.toString().replace(" ", "_"), path, documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ext);
                            boolean flag = new DownloadUploadFile().setUploadFTPFileNew(serverFilePath, path, documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ext);
//                this will check whether document uploaded or not and if not uploaded notify about which document is not uplaoded 
                            if (flag) {
                                String docServerPath = serverFilePath + "/" + documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ext;
                                documentListTable.setValueAt(docServerPath, row, 7);
                                System.out.println("doc path is " + docServerPath);
                                JOptionPane.showMessageDialog(null, "File uploaded successfully");
//                            if (row == 0 || row == 1) {
                                documentListTable.setValueAt("Received", row, 3);
//                            }
                                this.setCursor(Cursor.getDefaultCursor());

                            } else {
                                JOptionPane.showMessageDialog(null, "Error in Uploading " + documentListTable.getValueAt(row, 1).toString(), "uploading document", JOptionPane.ERROR_MESSAGE);
                                this.setCursor(Cursor.getDefaultCursor());
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Please select the directory to upload file.");

                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                this.setCursor(Cursor.getDefaultCursor());
            }
        }

        if (col == 6) {
            try {
                if (documentListTable.getValueAt(row, 7) == null) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DefaultTableModel tableModel = (DefaultTableModel) documentListTable.getModel();
                    if (counter > 0) {
                        counter--;
                    }
                    tableModel.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(null, "Document cannot be Deleted.", "", 0);
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                this.setCursor(Cursor.getDefaultCursor());
                JOptionPane.showMessageDialog(null, "Problem Occured! Try agian later.", "", 0);
            }
        }
        if (col == 9) {
            System.out.println("location at col 11: " + documentListTable.getValueAt(row, 11) + " and at col 10 " + documentListTable.getValueAt(row, 10));
            System.out.println("");
            String dirName = "";
            if (documentListTable.getValueAt(row, 10) != null) {
                dirName = documentListTable.getValueAt(row, 10).toString();
                if (dirName.contains("/")) {
                    dirName = dirName.split("/")[dirName.split("/").length - 1];
                }
            }
            ClientDocsLocationFrame cldFrame = new ClientDocsLocationFrame(email, row, 10, this, dirName, pathList);
            cldFrame.setVisible(true);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_documentListTableMouseClicked

    private void manageDocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageDocButtonActionPerformed
        //        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
//            DefaultTableModel tableModel = (DefaultTableModel) documentListTable.getModel();
//            tableModel.insertRow(counter, new Object[]{});
//            TableColumn col = documentListTable.getColumnModel().getColumn(2);
//            col.setCellEditor(new ComboBoxCellEditor(requirementType));
//            TableColumn statusCol = documentListTable.getColumnModel().getColumn(3);
//            statusCol.setCellEditor(new ComboBoxCellEditor(statusType));
//            documentListTable.setValueAt(counter + 1, counter, 0);
//            counter++;
//        } else {
//            JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
//        }
        new ClientDocFrame(email, SingletonClass.clientCompanyName, newTxPanel).setVisible(true);

    }//GEN-LAST:event_manageDocButtonActionPerformed

    private void addListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addListButtonActionPerformed
        new CreateListFrame(email, newTxPanel).setVisible(true);
    }//GEN-LAST:event_addListButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addListButton;
    private javax.swing.JTable documentListTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton manageDocButton;
    private javax.swing.JButton saveDocButton;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox requirementType;
    private javax.swing.JComboBox statusType;
}
