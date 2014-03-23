package com.msr.tools.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.ContactDetailBean;
import com.parser.DocumentsDetailBean;
import com.parser.UserXMLReader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
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
public class EditBankPanel extends javax.swing.JPanel {

    File dir = null;
    ToolsEditViewFrame tool = null;
    List bankConList = null;
    int rowNumber = 0;
    int docRowNumber = 0;
    BankDetailPanel bankDetailPanel = null;
    ToolsEditViewFrame toolsViewFrame = null;
    String userEmailId = null;

    /**
     *
     * @param bankId
     * @param toolsViewFrame
     * @param tempParent
     */
    // add the data from database according to bank id
    public EditBankPanel(String bankId, ToolsEditViewFrame toolsViewFrame, JPanel tempParent, String email, String aliasName) {
        this.userEmailId = email;
        this.tool = toolsViewFrame;
        this.toolsViewFrame = toolsViewFrame;
        this.bankDetailPanel = (BankDetailPanel) tempParent;
        initComponents();
        
        if(SingletonClass.designation.equals("RM")){
            setAllFieldsHidden();
        }
        HashMap<String, List> bankInfoMap = new UserXMLReader().extractBankDetailsXml(userEmailId, bankId, aliasName);
        List<HashMap> bankList = bankInfoMap.get("bankList");
        List<ContactDetailBean> contactList = bankInfoMap.get("contactList");
        List<DocumentsDetailBean> docList = bankInfoMap.get("docList");

        HashMap<String, String> bankMap = bankList.get(0);


        bankNameField.setText(SingletonClass.replaceEmptySpace(bankMap.get("bankName")));

        aliasField.setText(SingletonClass.replaceEmptySpace(bankMap.get("alias")));
        
        if(!SingletonClass.designation.equals("RM")){
        branchField.setText(SingletonClass.replaceEmptySpace(bankMap.get("branch")));
        bankTypeComboBox.setSelectedItem(bankMap.get("bankType"));
        clientVisitCombo.setSelectedItem(bankMap.get("visitStatus"));
        renewalDateCombo.setSelectedItem(bankMap.get("applicableRenewal"));
        addressField.setText(URLDecoder.decode(SingletonClass.replaceEmptySpace(bankMap.get("address"))));
        ifciCodeField.setText(SingletonClass.replaceEmptySpace(bankMap.get("ifscCode")));
        accountNoField.setText(SingletonClass.replaceEmptySpace(bankMap.get("accountNo")));
        faxNoField.setText(SingletonClass.replaceEmptySpace(bankMap.get("faxNo")));
            System.out.println("ALIAS NAME: "+bankMap.get("alias") +" TYPE: "+bankMap.get("bankType"));
        }
        bankNameField.setEditable(false);
        aliasField.setEditable(false);


        DefaultTableModel model = (DefaultTableModel) personTable.getModel();
        DefaultTableModel docModel = (DefaultTableModel) documentTable.getModel();
        TableColumn col1 = personTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
        TableColumn docScol = documentTable.getColumnModel().getColumn(0);
        docScol.setMaxWidth(50);
// set the delete button from tablerenderer
        TableColumn col6 = personTable.getColumnModel().getColumn(5);
        col6.setMaxWidth(50);
        col6.setCellRenderer(new IconTableCellRenderer("Delete"));



        System.out.println("list size is " + contactList.size() + " <<<>>> " + docList.size());
        
        if(!SingletonClass.designation.equals("RM")){
        Iterator itr = contactList.iterator();
        while (itr.hasNext()) {
            ContactDetailBean contactBean = (ContactDetailBean) itr.next();
            model.insertRow(rowNumber, new Object[]{});
            personTable.setRowHeight(30);
            personTable.setValueAt(rowNumber + 1, rowNumber, 0);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactName()), rowNumber, 1);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactDesignation()), rowNumber, 2);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactMobile()), rowNumber, 3);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactEmail()), rowNumber, 4);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactBean.getContactId()), rowNumber, 6);

            rowNumber++;
        }
        }
        requirementType.addItem("Required");
        requirementType.addItem("Optional");
        TableColumn col = documentTable.getColumnModel().getColumn(2);
        col.setCellEditor(new ComboBoxCellEditor(requirementType));

        Iterator utr = docList.iterator();
        while (utr.hasNext()) {
            DocumentsDetailBean docBean = (DocumentsDetailBean) utr.next();

            documentTable.setRowHeight(30);
            docModel.insertRow(docRowNumber, new Object[]{});
            documentTable.setRowHeight(30);
            documentTable.setValueAt(docRowNumber + 1, docRowNumber, 0);
            documentTable.setValueAt(SingletonClass.replaceEmptySpace(docBean.getDocName()), docRowNumber, 1);
            documentTable.setValueAt(docBean.getDocType(), docRowNumber, 2);
            documentTable.setValueAt(docBean.getDocFTPPath(), docRowNumber, 7);
            documentTable.setValueAt(docBean.getDocLocation(), docRowNumber, 8);
            docRowNumber++;
        }

        TableColumn docPath = documentTable.getColumnModel().getColumn(7);
        docPath.setWidth(0);
        docPath.setMinWidth(0);
        docPath.setMaxWidth(0);

        TableColumn docLocPath = documentTable.getColumnModel().getColumn(8);
        docLocPath.setWidth(0);
        docLocPath.setMinWidth(0);
        docLocPath.setMaxWidth(0);


        TableColumn docLocCol = documentTable.getColumnModel().getColumn(3);
        docLocCol.setMaxWidth(50);
        docLocCol.setCellRenderer(new IconTableCellRenderer("location"));

        TableColumn docViewCol = documentTable.getColumnModel().getColumn(4);
        docViewCol.setMaxWidth(50);
        docViewCol.setCellRenderer(new IconTableCellRenderer("View"));

        TableColumn docUploadCol = documentTable.getColumnModel().getColumn(5);
        docUploadCol.setMaxWidth(50);
        docUploadCol.setCellRenderer(new IconTableCellRenderer("upload"));
        TableColumn docDelCol = documentTable.getColumnModel().getColumn(6);
        docDelCol.setMaxWidth(50);
        docDelCol.setCellRenderer(new IconTableCellRenderer("Delete"));
        TableColumn col7 = personTable.getColumnModel().getColumn(6);
        col7.setWidth(0);
        col7.setMinWidth(0);
        col7.setMaxWidth(0);
        col7.setResizable(false);

    }
public void setAllFieldsHidden(){
    bankNameField.setEditable(false);
    aliasField.setEditable(false);
    branchField.setEditable(false);
    bankTypeComboBox.setEnabled(false);
    addressField.setEditable(false);
    ifciCodeField.setEditable(false);
    accountNoField.setEditable(false);
    faxNoField.setEditable(false);
    clientVisitCombo.setEnabled(false);
    renewalDateCombo.setEnabled(false);
    personTable.setEnabled(false);
    addDocButton.setEnabled(false);
    addPersonButton.setEnabled(false);
    saveBankButton.setEnabled(false);
    clientVisitCombo.setSelectedIndex(-1);
    renewalDateCombo.setSelectedIndex(-1);
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
        faxLabel = new javax.swing.JLabel();
        faxNoField = new javax.swing.JTextField();
        clientVisitCombo = new javax.swing.JComboBox();
        renewalDateCombo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personTable = new javax.swing.JTable();
        addPersonButton = new javax.swing.JButton();
        saveBankButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        requirementType = new javax.swing.JComboBox();
        documentTable = new javax.swing.JTable();
        addDocButton = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Edit  Bank");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 103, 204));
        jLabel2.setText("Alias Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        addressField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addressFieldKeyPressed(evt);
            }
        });
        addressScroll.setViewportView(addressField);

        accountNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountNoFieldKeyReleased(evt);
            }
        });

        addressLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(0, 103, 204));
        addressLabel.setText("Address");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 103, 204));
        jLabel4.setText("IFCI Code");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 103, 204));
        jLabel5.setText("Account No. ");

        faxLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        faxLabel.setForeground(new java.awt.Color(0, 103, 204));
        faxLabel.setText("Fax No. ");

        faxNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                faxNoFieldKeyReleased(evt);
            }
        });

        clientVisitCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Req-PreDisc", "Req-PostDisc", "Not-Req" }));

        renewalDateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Limit Sanctioned Date", "Limit Enhancement Date" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 103, 204));
        jLabel6.setText("Client Visit");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 103, 204));
        jLabel7.setText("Applicable Renewal Date");

        javax.swing.GroupLayout bank1PanelLayout = new javax.swing.GroupLayout(bank1Panel);
        bank1Panel.setLayout(bank1PanelLayout);
        bank1PanelLayout.setHorizontalGroup(
            bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bank1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(faxLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addressScroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(bankTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aliasField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(branchField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bank1PanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bankNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38))
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addComponent(ifciCodeField)
                        .addGap(39, 39, 39))
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(renewalDateCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clientVisitCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(accountNoField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(faxNoField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(39, 39, 39))))
        );
        bank1PanelLayout.setVerticalGroup(
            bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bank1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankNameLabel))
                .addGap(5, 5, 5)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(aliasField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(branchField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bankTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bankTypeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ifciCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(accountNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(faxNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(faxLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clientVisitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(renewalDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(6, 6, 6))
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))))
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
                "", "Contact Person", "Designation", "Landline No.", "E-mail Id", "",""
            }
        ));
        personTable.setRowHeight(35);
        personTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personTableMouseClicked(evt);
            }
        });
        personTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                personTableKeyReleased(evt);
            }
        });
        TableColumn col7 = personTable.getColumnModel().getColumn(6);
        col7.setWidth(0);
        col7.setMinWidth(0);
        col7.setMaxWidth(0);
        col7.setResizable(false);
        jScrollPane1.setViewportView(personTable);

        addPersonButton.setBackground(new java.awt.Color(0, 103, 204));
        addPersonButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addPersonButton.setForeground(new java.awt.Color(255, 255, 255));
        addPersonButton.setText("ADD PERSON");
        addPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonButtonActionPerformed(evt);
            }
        });

        saveBankButton.setBackground(new java.awt.Color(0, 103, 204));
        saveBankButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveBankButton.setForeground(new java.awt.Color(255, 255, 255));
        saveBankButton.setText("UPDATE");
        saveBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBankButtonActionPerformed(evt);
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
                "S.No.", "Document Name", "Type", "","","","","",""
            }
        ));
        TableColumn docPath = documentTable.getColumnModel().getColumn(7);
        docPath.setWidth(0);
        docPath.setMinWidth(0);
        docPath.setMaxWidth(0);

        TableColumn docLocPath = documentTable.getColumnModel().getColumn(8);
        docLocPath.setWidth(0);
        docLocPath.setMinWidth(0);
        docLocPath.setMaxWidth(0);
        documentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                documentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(documentTable);

        addDocButton.setBackground(new java.awt.Color(0, 103, 204));
        addDocButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addDocButton.setForeground(new java.awt.Color(255, 255, 255));
        addDocButton.setText("Add Documents");
        addDocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDocButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addDocButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(bank1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))))
            .addComponent(titalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bank1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addDocButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

private void bankNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankNameFieldActionPerformed
}//GEN-LAST:event_bankNameFieldActionPerformed

private void branchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchFieldActionPerformed
}//GEN-LAST:event_branchFieldActionPerformed

private void bankTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankTypeComboBoxActionPerformed
}//GEN-LAST:event_bankTypeComboBoxActionPerformed

private void addPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPersonButtonActionPerformed

    DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
    tableModel.insertRow(rowNumber, new Object[]{});
    personTable.setValueAt(rowNumber + 1, rowNumber, 0);
    rowNumber++;
}//GEN-LAST:event_addPersonButtonActionPerformed

private void saveBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBankButtonActionPerformed

    toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    DefaultTableModel model = (DefaultTableModel) personTable.getModel();
    int rowCount = model.getRowCount();

    DefaultTableModel docModel = (DefaultTableModel) documentTable.getModel();
    int docRowCount = docModel.getRowCount();
    boolean isBankDetailsFilled = false;
    boolean isBankContactsFilled = false;
    boolean isdocadded = false;




    if (bankNameField.getText() == null || bankNameField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Enter bank Name", "Add Bank Name", JOptionPane.ERROR_MESSAGE);
        toolsViewFrame.setCursor(Cursor.getDefaultCursor());

    } else if (branchField.getText() == null || branchField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Enter Your Bank Branch", "Bank Branch", JOptionPane.ERROR_MESSAGE);
        toolsViewFrame.setCursor(Cursor.getDefaultCursor());

    } else if (addressField.getText() == null || addressField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Provide Bank's Address", "Bank Type", JOptionPane.ERROR_MESSAGE);
        toolsViewFrame.setCursor(Cursor.getDefaultCursor());
    } else if (aliasField.getText() == null || aliasField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Provide Bank's Alias Name", "Bank Type", JOptionPane.ERROR_MESSAGE);
        toolsViewFrame.setCursor(Cursor.getDefaultCursor());

    } else if (bankTypeComboBox.getSelectedIndex() == 0) {

        JOptionPane.showMessageDialog(null, "Please select your Bank type", "Bank Type", JOptionPane.ERROR_MESSAGE);
        toolsViewFrame.setCursor(Cursor.getDefaultCursor());

    } else {

        isBankDetailsFilled = true;

    }
//*************************************************************************************************************
//    if (isBankDetailsFilled) {
//        for (int counter = 0; counter < rowCount; counter++) {
//
//            if (model.getValueAt(counter, 1) == null || model.getValueAt(counter, 1).toString().equals("")) {
//
//                JOptionPane.showMessageDialog(this, "Please provide Contact Person's Name.");
//                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                break;
//
//            } else if (model.getValueAt(counter, 2) == null || model.getValueAt(counter, 2).toString().equals("")) {
//
//                JOptionPane.showMessageDialog(this, "Please provide Contact Person's Designation.");
//                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                break;
//
//            } else if (model.getValueAt(counter, 3) == null || model.getValueAt(counter, 3).toString().equals("")) {
//
//                JOptionPane.showMessageDialog(this, "Please provide Mobile number of Contact Person.");
//                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                break;
//
//            } else if (model.getValueAt(counter, 4) == null || model.getValueAt(counter, 4).toString().equals("")) {
//
//                JOptionPane.showMessageDialog(this, "Please provide Contact Person's Designation.");
//                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                break;
//
//            } else {
//
//                isBankContactsFilled = true;
//
//            }
//            
//
//        }
//    }
    //***************************************************************************************************
//    if (rowCount == 0) {
        isBankContactsFilled = true;
//    }

    if (isBankDetailsFilled && isBankContactsFilled) {
        for (int doccount = 0; doccount < docRowCount; doccount++) {
            if (docModel.getValueAt(doccount, 1) == null || docModel.getValueAt(doccount, 1).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide document Name");
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else if (docModel.getValueAt(doccount, 2) == null || docModel.getValueAt(doccount, 2).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide " + documentTable.getValueAt(doccount, 1) + " document type");
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                break;
//                
//        } else if(docModel.getValueAt(doccount, 6)==null || docModel.getValueAt(doccount, 6).toString().equals("")){
//            
//            JOptionPane.showMessageDialog(this, "Please upload "+documentTable.getValueAt(doccount, 1)+" file" );
//                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                break;

            } else if (docModel.getValueAt(doccount, 8) == null || docModel.getValueAt(doccount, 8).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please Select " + documentTable.getValueAt(doccount, 1) + " location");
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else {

                isdocadded = true;

//            if(docModel.getValueAt(doccount, 2).toString().equals("Required") &&docModel.getValueAt(doccount, 6).toString().equals("") ){
//                isdocadded = false;
//                JOptionPane.showMessageDialog(this, "Please upload required document.");
//                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//            }else if(docModel.getValueAt(doccount, 2).toString().equals("Optional")) {
//                isdocadded = true;
                if (docModel.getValueAt(doccount, 7) == null) {
                    documentTable.setValueAt("", doccount, 7);
                }
//            }    
            }



        }
    }

    if (docRowCount == 0) {
        isdocadded = true;
    }

    if (isBankContactsFilled && isBankDetailsFilled && isdocadded) {
        boolean iscontactAdded = false;
        boolean isdocAdded = false;
        StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveBanks&bankName=")
                .append(URLEncoder.encode(bankNameField.getText())).append("&alias=").append(URLEncoder.encode(aliasField.getText()))
                .append("&bankType=").append(bankTypeComboBox.getSelectedItem()).append("&branch=").append(branchField.getText())
                .append("&ifscCode=").append(ifciCodeField.getText()).append("&").append(SingletonClass.userName).append("=").append(userEmailId)
                .append("&accountNo=").append(accountNoField.getText())
                .append("&visitStatus=").append(URLEncoder.encode(clientVisitCombo.getSelectedItem().toString()))
                .append("&applicableRenewal=").append(URLEncoder.encode(renewalDateCombo.getSelectedItem().toString()))
                .append("&address=").append(URLEncoder.encode(addressField.getText())).append("&faxNo=").append(URLEncoder.encode(faxNoField.getText()));
        String url = buffer.toString();
        url = url.replace(" ", "%20");
        System.out.println("url is " + url);
        String urlStatus = new callServerUrl().urlProcessiong(url);


        if (!urlStatus.equals("invalid")) {
//            uploading of document on server and updating in database.........................
            for (int docCount = 0; docCount < docRowCount; docCount++) {
                String doclocationstr = "";
                if (documentTable.getValueAt(docCount, 8) != null) {
                    doclocationstr = documentTable.getValueAt(docCount, 8).toString();
                }
                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveBankDocs&").append(SingletonClass.docsName).append("=").append(docModel.getValueAt(docCount, 1).toString()).append("&").append(SingletonClass.docsType).append("=").append(docModel.getValueAt(docCount, 2).toString()).append("&").append(SingletonClass.docsPath).append("=").append(URLEncoder.encode(documentTable.getValueAt(docCount, 7).toString())).append("&").append(SingletonClass.clientDir).append("=").append(URLEncoder.encode(doclocationstr)).append("&").append(SingletonClass.bankName).append("=").append(URLEncoder.encode(bankNameField.getText())).append("&").append(SingletonClass.userName).append("=").append(userEmailId).append("&").append("alias").append("=").append(URLEncoder.encode(aliasField.getText()));;
                String url1 = buffer1.toString();
                String encodedUrl = url1.replace(" ", "%20");
                System.out.println("bank contact url is " + encodedUrl);
                String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                if (urlStatus1.equals("invalid")) {
//                JOptionPane.showMessageDialog(null, "Error in saving document details of "+documentTable.getValueAt(docCount, 1).toString()+" document.","uploading document",JOptionPane.ERROR_MESSAGE);
                    isdocAdded = true;
                    break;
                }

            }
        }
//***************************************************************************************************

//        if (!urlStatus.equals("invalid") && !isdocAdded) {
//            //            for uploading contacts details
//            for (int counter = 0; counter < rowCount; counter++) {
//                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveBankContacts&").append(SingletonClass.bankContactName).append("=").append(model.getValueAt(counter, 1).toString()).append("&").append(SingletonClass.bankContactDesignation).append("=").append(model.getValueAt(counter, 2).toString()).append("&").append(SingletonClass.bankContactMobile).append("=").append(model.getValueAt(counter, 3).toString()).append("&").append(SingletonClass.bankContactEmail).append("=").append(model.getValueAt(counter, 4).toString()).append("&").append(SingletonClass.bankName).append("=").append(URLEncoder.encode(bankNameField.getText())).append("&").append("alias").append("=").append(URLEncoder.encode(aliasField.getText()));;
//                String url1 = buffer1.toString();
//                String encodedUrl = url1.replace(" ", "%20");
//                System.out.println("bank contact url is " + encodedUrl);
//                String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);
//
//                if (urlStatus1.equals("invalid")) {
//                    iscontactAdded = true;
//                    break;
//                }
//            }
//        }
        //***************************************************************************************************

        if (urlStatus.equals("invalid") || iscontactAdded || isdocAdded) {
            JOptionPane.showMessageDialog(null, "Error in saving Bank details.", "Update Bank", JOptionPane.ERROR_MESSAGE);
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else {

            JOptionPane.showMessageDialog(this, "Successfully Saved");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
            toolsViewFrame.dispose();
            bankDetailPanel.refresh();

        }
    }






//    toolsViewFrame.dispose();
//    JOptionPane.showMessageDialog(this, "Successfully Updated");
}//GEN-LAST:event_saveBankButtonActionPerformed

private void personTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personTableMouseClicked

    DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
    int row = personTable.rowAtPoint(evt.getPoint());
    int col = personTable.columnAtPoint(evt.getPoint());
    boolean flag = false;
    if (col == 5 && !SingletonClass.designation.equals("RM")) {
        try {

            if (personTable.getValueAt(row, 6) == null || personTable.getValueAt(row, 6).toString().equals("")) {
                tableModel.removeRow(row);
                rowNumber--;
//             JOptionPane.showMessageDialog(null, "Successfully Deleted Extra Row");
            } else {


//            http://localhost:8180/PreSales/ServletController?type=deleteClientContacts&clientName=piyush2000&ccontName=rahul%20bhargva&ccontactEmail=ahul@msronline.in 

                StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deleteBankContacts&").append(SingletonClass.bankContactName).append("=").append(tableModel.getValueAt(row, 1).toString()).append("&bContId=").append(tableModel.getValueAt(row, 6).toString()).append("&").append(SingletonClass.userName).append("=").append(userEmailId);

                String strBuffer = buffer.toString().replaceAll(" ", "%20");
                System.out.println("bank contact url is " + strBuffer);
                String urlStatus1 = new callServerUrl().urlProcessiong(strBuffer);

                if (urlStatus1.equals("invalid")) {
                    JOptionPane.showMessageDialog(this, "Error in Deleting Contact.", "Delete Contact", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.removeRow(row);
                    rowNumber--;
                    JOptionPane.showMessageDialog(this, "Contact has been Deleted.");
                }

            }

            //clientDetailPanel.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}//GEN-LAST:event_personTableMouseClicked

    private void addressFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            System.out.println(evt.getModifiers());
            if (evt.getModifiers() > 0) {
                addressField.transferFocusBackward();
            } else {
                addressField.transferFocus();
            }
            evt.consume();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }//GEN-LAST:event_addressFieldKeyPressed

    private void documentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentTableMouseClicked
        toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DefaultTableModel tableModel = (DefaultTableModel) documentTable.getModel();
        int docRow = documentTable.rowAtPoint(evt.getPoint());
        int docCol = documentTable.columnAtPoint(evt.getPoint());
        if (docCol == 3 && !SingletonClass.designation.equals("RM")) {
            String location = "";
            if (documentTable.getValueAt(docRow, 8) != null) {
                location = documentTable.getValueAt(docRow, 8).toString();
            }
            DocLocationFrame locFrame = new DocLocationFrame(userEmailId, docRow, 8, this, null, null, location);
            locFrame.setVisible(true);
        } else if (docCol == 4) {
            toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (documentTable.getValueAt(docRow, 7) == null || documentTable.getValueAt(docRow, 7).toString().equals("")) {
                JOptionPane.showMessageDialog(null, "No document uploaded", "View Document", JOptionPane.INFORMATION_MESSAGE);
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
            } else {
                Desktop d = Desktop.getDesktop();
                try {

//              boolean flag = new DownloadUploadFile().getFTPFile(documentTable.getValueAt(row, 6).toString(), "", documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf", SingletonClass.sysFilePath);
                    boolean flag = new DownloadUploadFile().getFTPFile(documentTable.getValueAt(docRow, 7).toString(), "", SingletonClass.sysFilePath);
                    if (flag) {
                        toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                        System.out.println(";;;;"+documentTable.getValueAt(docRow, 7)+"olol"+documentTable.getValueAt(docRow, 6));
//            d.open(new File(SingletonClass.sysFilePath+documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf"));
                        d.open(new File(SingletonClass.sysFilePath + documentTable.getValueAt(docRow, 7).toString().substring(documentTable.getValueAt(docRow, 7).toString().lastIndexOf("/"))));
                    } else {
                        JOptionPane.showMessageDialog(null, "File not found", "View Document", JOptionPane.ERROR_MESSAGE);
                        toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                }
            }
        } else if (docCol == 5 && !SingletonClass.designation.equals("RM")) {
            toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (bankNameField.getText() == null || bankNameField.getText().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide bank Name");
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());

            } else {
                if (documentTable.getValueAt(docRow, 1) == null || documentTable.getValueAt(docRow, 1).toString().equals("")) {

                    JOptionPane.showMessageDialog(this, "Please provide document Name");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());

                } else {
                    JFileChooser filechooser = new JFileChooser();
                    filechooser.setCurrentDirectory(dir);
//	   filechooser.setFileFilter(new TypeOfFile());
//	   filechooser.setAcceptAllFileFilterUsed(false);
                    int status = filechooser.showDialog(filechooser, "Upload");
                    dir = filechooser.getCurrentDirectory();

                    if (status == filechooser.CANCEL_OPTION) {
                        toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    } else {
                        toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        File file = filechooser.getSelectedFile();
                        String path = file.getPath();
                        String ext = path.substring(path.lastIndexOf("."));
                        System.out.println("extension is " + ext);
                        System.out.println("file path is " + path);

//            boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverBankDocPath, bankNameField.getText().toString().replace(" ", "_"),path, documentTable.getValueAt(docRow, 1).toString().replace(" ", "_")+".pdf");
                        boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverBankDocPath, bankNameField.getText().toString().replace(" ", "_") + branchField.getText().replace(" ", "_"), path, documentTable.getValueAt(docRow, 1).toString().replace(" ", "_") + ext);
//                this will check whether document uploaded or not and if not uploaded notify about which document is not uplaoded 
                        if (flag) {
//                    String docServerPath = SingletonClass.serverBankDocPath +bankNameField.getText().toString().replace(" ", "_");
                            String docServerPath = SingletonClass.serverBankDocPath + bankNameField.getText().toString().replace(" ", "_") + branchField.getText().replace(" ", "_") + "/" + documentTable.getValueAt(docRow, 1).toString().replace(" ", "_") + ext;
                            documentTable.setValueAt(docServerPath, docRow, 7);
                            System.out.println("doc path is " + docServerPath);
                            JOptionPane.showMessageDialog(null, "file uploaded Successfully");
                            toolsViewFrame.setCursor(Cursor.getDefaultCursor());

                        } else {
                            JOptionPane.showMessageDialog(null, "Error in Uploading " + documentTable.getValueAt(docRow, 1).toString(), "uploading document", JOptionPane.ERROR_MESSAGE);
                            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                }
            }
        } else if (docCol == 6 && !SingletonClass.designation.equals("RM")) {
            try {
                toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if (documentTable.getValueAt(docRow, 7) == null || documentTable.getValueAt(docRow, 7).toString().equals("")) {
                    tableModel.removeRow(docRow);
                    docRowNumber--;
//             JOptionPane.showMessageDialog(this, "Successfully Deleted Extra Row");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                } else {



//            http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=deleteBankDocs&bankName=abc%20bank&docsName=docsName

                    StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deleteBankDocs&").append(SingletonClass.docsName).append("=").append(documentTable.getValueAt(docRow, 1).toString()).append("&bankName=").append(aliasField.getText()).append("&").append(SingletonClass.userName).append("=").append(userEmailId);

                    String strBuffer = buffer.toString().replaceAll(" ", "%20");
                    System.out.println("bank document delete url is " + strBuffer);
                    String urlStatus1 = new callServerUrl().urlProcessiong(strBuffer);

                    if (urlStatus1.equals("invalid")) {
                        JOptionPane.showMessageDialog(null, "Error in Deleting Document.", "Delete Document", JOptionPane.ERROR_MESSAGE);
                        toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    } else {
                        tableModel.removeRow(docRow);
                        docRowNumber--;
                        JOptionPane.showMessageDialog(null, "Document has been Removed.");
                        toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    }

                }

                //clientDetailPanel.refresh();
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                e.printStackTrace();
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
            }


        } else {
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_documentTableMouseClicked

    private void addDocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDocButtonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tableModel = (DefaultTableModel) documentTable.getModel();
        tableModel.insertRow(docRowNumber, new Object[]{});
        TableColumn col = documentTable.getColumnModel().getColumn(2);
        col.setCellEditor(new ComboBoxCellEditor(requirementType));
        documentTable.setValueAt(docRowNumber + 1, docRowNumber, 0);
        docRowNumber++;
    }//GEN-LAST:event_addDocButtonActionPerformed

    private void personTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_personTableKeyReleased

        DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
        int docRow = personTable.getEditingRow();
        int docCol = personTable.getEditingColumn();
        if (docCol == 3) {
            boolean isInt = isInt(personTable.getValueAt(docRow, docCol).toString());
            System.out.println("phnPattern.matches(): " + personTable.getValueAt(docRow, docCol).toString() + "   " + isInt);

            // get the applicant values from UI, and append for URL.
            if (!isInt) {
                JOptionPane.showMessageDialog(null, "invalid Mobile no.", "", 0);
                personTable.setValueAt("", docRow, docCol);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_personTableKeyReleased

    private void accountNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountNoFieldKeyReleased

        boolean isInt = isInt(accountNoField.getText());
        System.out.println("phnPattern.matches(): " + accountNoField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid Account no.", "", 0);
            accountNoField.setText("");
            // TODO add your handling code here:
        }
    }//GEN-LAST:event_accountNoFieldKeyReleased

    private void faxNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_faxNoFieldKeyReleased
        // TODO add your handling code here:
        boolean isInt = isInt(faxNoField.getText());
        System.out.println("phnPattern.matches(): " + faxNoField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid Fax no.", "", 0);
            faxNoField.setText("");
        }
    }//GEN-LAST:event_faxNoFieldKeyReleased

    public boolean isInt(String integer) {
        boolean isInt = false;
        if (integer.equals("")) {
            isInt = true;
        } else {
            try {
                Long.parseLong(integer);
                isInt = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                isInt = false;
            }
        }
        return isInt;
    }

    public void getDocLocation(String docLocation, int row, int col) {
        System.out.println("row: " + row + " col: " + col);
        documentTable.setValueAt(docLocation, row, col);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNoField;
    private javax.swing.JButton addDocButton;
    private javax.swing.JButton addPersonButton;
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
    private javax.swing.JComboBox clientVisitCombo;
    private javax.swing.JTable documentTable;
    private javax.swing.JLabel faxLabel;
    private javax.swing.JTextField faxNoField;
    private javax.swing.JTextField ifciCodeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable personTable;
    private javax.swing.JComboBox renewalDateCombo;
    private javax.swing.JButton saveBankButton;
    private javax.swing.JPanel titalPanel;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox requirementType;

    class TypeOfFile extends FileFilter {
        //Type of file that should be display in JFileChooser will be set here  
        //We choose to display only directory and text file  

        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
        }

        @Override
        public String getDescription() {
            // TODO Auto-generated method stub
            return "pdf files";
        }
    }
}
