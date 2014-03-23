package com.msr.tools.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

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
public class AddBankPanel extends javax.swing.JPanel {

    // BankDetailPanel parent = null;
    BankDetailPanel bankDetailPanel = null;
    AddUserBankClientForm addBankFrame = null;
    MainScreen home = null;
    int counter = 0;
    int bankCounter = 0;
    private String emailStr = "";
    File dir = null;

    /**
     *
     * @param tempParent
     * @param home
     * @param frame
     */
    public AddBankPanel(JPanel tempParent, MainScreen home, AddUserBankClientForm frame, String emailStr) {
        this.emailStr = emailStr;
        this.home = home;
        this.addBankFrame = frame;
        initComponents();
        if (tempParent instanceof BankDetailPanel) {
            this.bankDetailPanel = (BankDetailPanel) tempParent;
        }

        addDocumentLis();
        // add the delete button 

        TableColumn col6 = personTable.getColumnModel().getColumn(5);
        col6.setMaxWidth(50);
        col6.setCellRenderer(new IconTableCellRenderer("Delete"));

        TableColumn pathCol = this.documentTable.getColumnModel().getColumn(7);
        pathCol.setWidth(0);
        pathCol.setMaxWidth(0);
        pathCol.setMinWidth(0);


        TableColumn locCol = this.documentTable.getColumnModel().getColumn(8);
        locCol.setWidth(0);
        locCol.setMaxWidth(0);
        locCol.setMinWidth(0);
        locCol.setResizable(false);

        requirementType.addItem("Required");
        requirementType.addItem("Optional");

    }

    public void addDocumentLis() {
        DefaultTableModel tableModel = (DefaultTableModel) documentTable.getModel();
        tableModel.insertRow(bankCounter, new Object[]{});
        TableColumn col = documentTable.getColumnModel().getColumn(2);
        col.setCellEditor(new ComboBoxCellEditor(requirementType));
        documentTable.setValueAt(bankCounter + 1, bankCounter, 0);
        documentTable.setValueAt(SingletonClass.replaceEmptySpace("Document List"), bankCounter, 1);
        documentTable.setValueAt("Optional", bankCounter, 2);
        bankCounter++;
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
        jLabel6 = new javax.swing.JLabel();
        faxNoTextField = new javax.swing.JTextField();
        clientVisitCombo = new javax.swing.JComboBox();
        renewalDateCombo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        personTable = new javax.swing.JTable();
        addPersonButton = new javax.swing.JButton();
        saveBankButton = new javax.swing.JButton();
        resetBankButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        requirementType = new javax.swing.JComboBox();
        documentTable = new javax.swing.JTable();
        addDocumentButton = new javax.swing.JButton();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add New Bank");

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

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 103, 204));
        jLabel6.setText("Fax No.");

        faxNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                faxNoTextFieldKeyReleased(evt);
            }
        });

        clientVisitCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Req-PreDisc", "Req-PostDisc", "Not-Req" }));
        clientVisitCombo.setSelectedIndex(2);

        renewalDateCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Limit Sanctioned Date", "Limit Enhancement Date" }));
        renewalDateCombo.setSelectedIndex(0);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 103, 204));
        jLabel7.setText("Applicable Renewal Date");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 103, 204));
        jLabel8.setText("Client Visit");

        javax.swing.GroupLayout bank1PanelLayout = new javax.swing.GroupLayout(bank1Panel);
        bank1Panel.setLayout(bank1PanelLayout);
        bank1PanelLayout.setHorizontalGroup(
            bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bank1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(bank1PanelLayout.createSequentialGroup()
                                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bankNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bankTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(accountNoField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ifciCodeField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addressScroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(bankTypeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(branchField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(aliasField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bankNameField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(faxNoTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bank1PanelLayout.createSequentialGroup()
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(34, 34, 34)
                        .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientVisitCombo, 0, 246, Short.MAX_VALUE)
                            .addComponent(renewalDateCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        bank1PanelLayout.setVerticalGroup(
            bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bank1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bankNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aliasField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bank1PanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(bankTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bank1PanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(branchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bankTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ifciCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(faxNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientVisitCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bank1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renewalDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "", "Contact Person", "Designation", "Mobile Number", "E-mail", ""
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
        saveBankButton.setText("SAVE");
        saveBankButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBankButtonActionPerformed(evt);
            }
        });

        resetBankButton.setBackground(new java.awt.Color(0, 103, 204));
        resetBankButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        resetBankButton.setForeground(new java.awt.Color(255, 255, 255));
        resetBankButton.setText("RESET");
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
                "S.No.", "Document Name", "Type", "","","","","",""
            }
        ));
        TableColumn sNoColumn = this.documentTable.getColumnModel().getColumn(0);
        sNoColumn.setMaxWidth(50);
        sNoColumn.setResizable(false);

        TableColumn loccolumn = this.documentTable.getColumnModel().getColumn(3);
        loccolumn.setCellRenderer(new IconTableCellRenderer("location"));
        loccolumn.setMaxWidth(40);
        loccolumn.setResizable(false);
        TableColumn column = this.documentTable.getColumnModel().getColumn(4);
        column.setCellRenderer(new IconTableCellRenderer("View"));
        column.setMaxWidth(40);
        column.setResizable(false);

        TableColumn viewCol = this.documentTable.getColumnModel().getColumn(5);
        viewCol.setCellRenderer(new IconTableCellRenderer("upload"));
        viewCol.setMaxWidth(40);
        viewCol.setResizable(false);

        TableColumn delCol = this.documentTable.getColumnModel().getColumn(6);
        delCol.setCellRenderer(new IconTableCellRenderer("Delete"));
        delCol.setMaxWidth(40);
        delCol.setResizable(false);
        TableColumn pathCol = this.documentTable.getColumnModel().getColumn(7);
        pathCol.setWidth(0);
        pathCol.setMaxWidth(0);
        pathCol.setMinWidth(0);
        pathCol.setResizable(false);
        TableColumn locCol = this.documentTable.getColumnModel().getColumn(8);
        locCol.setWidth(0);
        locCol.setMaxWidth(0);
        locCol.setMinWidth(0);
        locCol.setResizable(false);
        documentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                documentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(documentTable);

        addDocumentButton.setBackground(new java.awt.Color(0, 103, 204));
        addDocumentButton.setForeground(new java.awt.Color(255, 255, 255));
        addDocumentButton.setText("ADD DOCUMENT");
        addDocumentButton.setFont(new java.awt.Font("Tahoma", 1, 12));
        addDocumentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDocumentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(bank1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(addDocumentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(saveBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(resetBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(bank1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(resetBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveBankButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addDocumentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void bankNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankNameFieldActionPerformed
}//GEN-LAST:event_bankNameFieldActionPerformed
private void addPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPersonButtonActionPerformed

    // add the person on the click of add person button

    DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
    TableColumn col1 = personTable.getColumnModel().getColumn(0);
    col1.setMaxWidth(50);
    col1.setWidth(50);
    tableModel.insertRow(counter, new Object[]{});
    personTable.setValueAt(counter + 1, counter, 0);
    counter++;
}//GEN-LAST:event_addPersonButtonActionPerformed
private void saveBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBankButtonActionPerformed

    addBankFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    DefaultTableModel model = (DefaultTableModel) personTable.getModel();
    int rowCount = model.getRowCount();

    DefaultTableModel docModel = (DefaultTableModel) documentTable.getModel();
    int docRowCount = docModel.getRowCount();
    boolean isBankDetailsFilled = false;
    boolean isBankContactsFilled = false;
    boolean isdocadded = false;




    if (bankNameField.getText() == null || bankNameField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Enter Bank Name", "Add Bank Name", JOptionPane.ERROR_MESSAGE);
        addBankFrame.setCursor(Cursor.getDefaultCursor());

    } else if (branchField.getText() == null || branchField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Enter Your Bank Branch", "Bank Branch", JOptionPane.ERROR_MESSAGE);
        addBankFrame.setCursor(Cursor.getDefaultCursor());

    } else if (addressField.getText() == null || addressField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Provide Bank's Address", "Bank Address", JOptionPane.ERROR_MESSAGE);
        addBankFrame.setCursor(Cursor.getDefaultCursor());

    } else if (aliasField.getText() == null || aliasField.getText().equals("")) {

        JOptionPane.showMessageDialog(null, "Please Provide Bank's Alias Name", "Bank Type", JOptionPane.ERROR_MESSAGE);
        addBankFrame.setCursor(Cursor.getDefaultCursor());

    } else if (bankTypeComboBox.getSelectedIndex() == 0) {

        JOptionPane.showMessageDialog(null, "Please select your Bank type", "Bank Type", JOptionPane.ERROR_MESSAGE);
        addBankFrame.setCursor(Cursor.getDefaultCursor());

    } else {

        isBankDetailsFilled = true;

    }

//    for(int docCounter = 0;docCounter<docRowCount;docCounter++){
//        if(model.getValueAt(counter, 0)==null && model.getValueAt(counter, 0).toString().equals("")){
//            
//            JOptionPane.showMessageDialog(this, "Please provide Contact Person's Name.");
//                addBankFrame.setCursor(Cursor.getDefaultCursor());
//                
//        } else{
//            
//        }
//    }
    if (isBankDetailsFilled) {
        for (int counter = 0; counter < rowCount; counter++) {

            if (model.getValueAt(counter, 1) == null || model.getValueAt(counter, 1).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide Contact Person's Name.");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else if (model.getValueAt(counter, 2) == null || model.getValueAt(counter, 2).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide Contact Person's Designation.");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else if (model.getValueAt(counter, 3) == null || model.getValueAt(counter, 3).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide Mobile number of Contact Person.");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else if (model.getValueAt(counter, 4) == null || model.getValueAt(counter, 4).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide Contact Person's Email.");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else {

                isBankContactsFilled = true;

            }

//         boolean isValidEmail = false;
//             Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*[._-]*[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*");
//        Matcher matchPattern = p.matcher(model.getValueAt(counter, 4).toString());
//        isValidEmail = matchPattern.matches();
//        if(!isValidEmail){
//            JOptionPane.showMessageDialog(null, "Invalid email id of "+personTable.getValueAt(counter, 1).toString(), "", 0);
//            addBankFrame.setCursor(Cursor.getDefaultCursor());
//            isBankContactsFilled = false;
//                break;
//        }else{
//            isBankContactsFilled = true;
//        }
//        
//            boolean isInt = isInt(personTable.getValueAt(counter, 3).toString());
//        System.out.println("phnPattern.matches(): " + personTable.getValueAt(counter, 3).toString() + "   " + isInt);
//
//        // get the applicant values from UI, and append for URL.
//        if (!isInt) {
//            JOptionPane.showMessageDialog(null, "invalid mobile no. of "+personTable.getValueAt(counter, 1).toString(), "", 0);
//           addBankFrame.setCursor(Cursor.getDefaultCursor());
//            isBankContactsFilled = false;
//                break;
//        }else{
//            isBankContactsFilled = true;
//        }
        }
    }
    if (rowCount < 1) {
        JOptionPane.showMessageDialog(null, "please add contact details");
        addBankFrame.setCursor(Cursor.getDefaultCursor());
        isBankContactsFilled = false;
    }


    if (isBankDetailsFilled && isBankContactsFilled) {
        for (int doccount = 0; doccount < docRowCount; doccount++) {
            if (docModel.getValueAt(doccount, 1) == null || docModel.getValueAt(doccount, 1).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide document Name");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else if (docModel.getValueAt(doccount, 2) == null || docModel.getValueAt(doccount, 2).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please Select " + documentTable.getValueAt(doccount, 1) + " document type");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;

            } else if (docModel.getValueAt(doccount, 8) == null || docModel.getValueAt(doccount, 8).toString().equals("")) {

                JOptionPane.showMessageDialog(this, "Please Select " + documentTable.getValueAt(doccount, 1) + " location");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;
//&&(docModel.getValueAt(doccount,1).equals("Document List"))
            } else if ((docModel.getValueAt(doccount, 7) == null || docModel.getValueAt(doccount, 7).toString().equals("")) && (docModel.getValueAt(doccount, 1).equals("Document List"))) {

                JOptionPane.showMessageDialog(this, "Please Upload " + documentTable.getValueAt(doccount, 1) + ".");
                addBankFrame.setCursor(Cursor.getDefaultCursor());
                break;
//
            } else {

                isdocadded = true;
//                if(docModel.getValueAt(doccount, 2).toString().equals("Required") &&docModel.getValueAt(doccount, 6).toString().equals("") ){
//                isdocadded = false;
//                JOptionPane.showMessageDialog(this, "Please upload required document.");
//                addBankFrame.setCursor(Cursor.getDefaultCursor());
//            }else if(docModel.getValueAt(doccount, 2).toString().equals("Optional")) {
                if (docModel.getValueAt(doccount, 7) == null) {
                    documentTable.setValueAt("", doccount, 7);
                }
                isdocadded = true;
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
                .append("&ifscCode=").append(ifciCodeField.getText()).append("&").append(SingletonClass.userName).append("=").append(emailStr)
                .append("&accountNo=").append(accountNoField.getText())
                .append("&address=").append(URLEncoder.encode(addressField.getText()))
                .append("&visitStatus=").append(URLEncoder.encode(clientVisitCombo.getSelectedItem().toString()))
                .append("&applicableRenewal=").append(URLEncoder.encode(renewalDateCombo.getSelectedItem().toString()))
                .append("&faxNo=").append(URLEncoder.encode(faxNoTextField.getText())).append("&dbType=save");
        String url = buffer.toString();
        url = url.replace(" ", "%20");
        System.out.println("url is " + url);
        String urlStatus = new callServerUrl().urlProcessiong(url);


        if (!urlStatus.equals("invalid") && !urlStatus.equals("duplicate")) {





//            uploading of document on server and updating in database.........................
            for (int docCount = 0; docCount < docRowCount; docCount++) {

                String doclocationstr = "";
                if (documentTable.getValueAt(docCount, 8) != null) {
                    doclocationstr = documentTable.getValueAt(docCount, 8).toString();
                }
                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveBankDocs&")
                        .append(SingletonClass.docsName).append("=").append(docModel.getValueAt(docCount, 1).toString()).append("&").append(SingletonClass.docsType)
                        .append("=").append(docModel.getValueAt(docCount, 2).toString()).append("&").append(SingletonClass.docsPath)
                        .append("=").append(URLEncoder.encode(documentTable.getValueAt(docCount, 7).toString())).append("&").append(SingletonClass.bankName)
                        .append("=").append(URLEncoder.encode(bankNameField.getText())).append("&").append(SingletonClass.userName)
                        .append("=").append(emailStr).append("&").append(SingletonClass.clientDir).append("=").append(URLEncoder.encode(doclocationstr)).append("&")
                        .append("alias").append("=").append(URLEncoder.encode(aliasField.getText()));
                String url1 = buffer1.toString();
                String encodedUrl = url1.replace(" ", "%20");
                System.out.println("bank doc url is " + encodedUrl);
                String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                if (urlStatus1.equals("invalid")) {
//                JOptionPane.showMessageDialog(null, "Error in saving document details of "+documentTable.getValueAt(docCount, 1).toString()+" document.","uploading document",JOptionPane.ERROR_MESSAGE);
                    isdocAdded = true;
                    break;
                }

            }

            String pathDocList = SingletonClass.sysFilePath + "DocumentList.pdf";
            boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverBankDocPath, bankNameField.getText().toString().replace(" ", "_")
                    + branchField.getText().replace(" ", "_"), pathDocList, "Document List".replace(" ", "_") + ".pdf");
//                this will check whether document uploaded or not and if not uploaded notify about which document is not uplaoded 
            if (flag) {
//                    String docServerPath = SingletonClass.serverBankDocPath +bankNameField.getText().toString().replace(" ", "_");
                String docServerPath = SingletonClass.serverBankDocPath + bankNameField.getText().toString().replace(" ", "_")
                        + branchField.getText().replace(" ", "_") + "/Document List" + ".pdf";
//                            documentTable.setValueAt(docServerPath, row, 7);
                System.out.println("doc path is " + docServerPath);
                addBankFrame.setCursor(Cursor.getDefaultCursor());

                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl)
                        .append(SingletonClass.operaionType).append("=saveBankDocs&")
                        .append(SingletonClass.docsName).append("=").append("Document List").append("&")
                        .append(SingletonClass.docsType).append("=").append("Optional").append("&")
                        .append(SingletonClass.docsPath).append("=").append(URLEncoder.encode(docServerPath))
                        .append("&").append(SingletonClass.bankName).append("=").append(URLEncoder.encode(bankNameField.getText()))
                        .append("&").append(SingletonClass.userName).append("=").append(emailStr).append("&")
                        .append(SingletonClass.clientDir).append("=").append(URLEncoder.encode("Financial")).append("&")
                        .append("alias").append("=").append(URLEncoder.encode(aliasField.getText()));
                String url1 = buffer1.toString();
                String encodedUrl = url1.replace(" ", "%20");
                System.out.println("bank doc url is " + encodedUrl);
                String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

            }



        }
        if (!urlStatus.equals("invalid") && !isdocAdded && !urlStatus.equals("duplicate")) {
            //            for uploading contacts details
            for (int counter = 0; counter < rowCount; counter++) {
//                http://192.168.10.250:8080/PreSales/ServletController?uName=rahul.ch@msronline.in&type=saveBankContacts&bankName=abc%20bank&contName=satya&contactDesignation=contactDesignation&contactMobile=contactMobile&contactEmail=contactEmail&contactPhoneNo=contactPhoneNo
                StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType)
                        .append("=saveBankContacts&").append(SingletonClass.bankContactName).append("=").append(model.getValueAt(counter, 1).toString())
                        .append("&").append(SingletonClass.bankContactDesignation).append("=").append(model.getValueAt(counter, 2).toString()).append("&")
                        .append(SingletonClass.bankContactMobile).append("=").append(model.getValueAt(counter, 3).toString()).append("&")
                        .append(SingletonClass.bankContactEmail).append("=").append(model.getValueAt(counter, 4).toString()).append("&")
                        .append(SingletonClass.bankName).append("=").append(URLEncoder.encode(bankNameField.getText())).append("&")
                        .append("alias").append("=").append(URLEncoder.encode(aliasField.getText()));;
                String url1 = buffer1.toString();
                String encodedUrl = url1.replace(" ", "%20");
                System.out.println("bank contact url is " + encodedUrl);
                String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                if (urlStatus1.equals("invalid")) {
                    iscontactAdded = true;
                    break;
                }
            }
        }

        if (urlStatus.equals("invalid") || iscontactAdded || isdocAdded || urlStatus.equals("duplicate")) {
            if (urlStatus.equals("duplicate")) {
                JOptionPane.showMessageDialog(this, bankNameField.getText() + " Already exist");
            } else {
                JOptionPane.showMessageDialog(this, "Error in Saving bank Details, Please Try Again.");
            }
            addBankFrame.setCursor(Cursor.getDefaultCursor());
        } else {

            JOptionPane.showMessageDialog(this, bankNameField.getText() + " Saved Successfully");
            addBankFrame.setCursor(Cursor.getDefaultCursor());
            addBankFrame.dispose();
            bankDetailPanel.refresh();

        }

    }

}//GEN-LAST:event_saveBankButtonActionPerformed

private void resetBankButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBankButtonActionPerformed

    bankNameField.setText("");
    aliasField.setText("");
    branchField.setText("");
    bankTypeComboBox.setSelectedIndex(0);
    addressField.setText("");
    ifciCodeField.setText("");
    accountNoField.setText("");

    DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
    int rowCount = tableModel.getRowCount();
    DefaultTableModel docTableModel = (DefaultTableModel) documentTable.getModel();
    int docRowCount = docTableModel.getRowCount();
    counter = 0;
    bankCounter = 0;
    for (int i = 0; i < docRowCount; i++) {
        docTableModel.removeRow(0);
    }
    for (int i = 0; i < rowCount; i++) {
        tableModel.removeRow(0);
    }
}//GEN-LAST:event_resetBankButtonActionPerformed

private void bankTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankTypeComboBoxActionPerformed
}//GEN-LAST:event_bankTypeComboBoxActionPerformed

private void branchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchFieldActionPerformed
}//GEN-LAST:event_branchFieldActionPerformed

private void personTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personTableMouseClicked
// delete extra row by clicking on delete button
    int row = personTable.rowAtPoint(evt.getPoint());
    int col = personTable.columnAtPoint(evt.getPoint());
    if (col == 5) {
        DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
        tableModel.removeRow(row);
        counter--;
//        JOptionPane.showMessageDialog(this, "Successfully Deleted Extra Row");
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

    private void addDocumentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDocumentButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) documentTable.getModel();
        TableColumn col1 = documentTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
        col1.setWidth(50);
        tableModel.insertRow(bankCounter, new Object[]{});
        TableColumn col = documentTable.getColumnModel().getColumn(2);
        col.setCellEditor(new ComboBoxCellEditor(requirementType));
        documentTable.setValueAt(bankCounter + 1, bankCounter, 0);
        bankCounter++;
    }//GEN-LAST:event_addDocumentButtonActionPerformed

    private void documentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentTableMouseClicked
        // TODO add your handling code here:

        int row = documentTable.rowAtPoint(evt.getPoint());
        int col = documentTable.columnAtPoint(evt.getPoint());

        if (col == 3) {
            String location = "";
            if (documentTable.getValueAt(row, 8) != null) {
                location = documentTable.getValueAt(row, 8).toString();
            }
            DocLocationFrame locFrame = new DocLocationFrame(emailStr, row, 8, null, this, null, location);
            locFrame.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else if (col == 4) {
            addBankFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (documentTable.getValueAt(row, 7) == null || documentTable.getValueAt(row, 7).toString().equals("")) {
                JOptionPane.showMessageDialog(null, "No document uploaded", "View Document", JOptionPane.ERROR_MESSAGE);
                addBankFrame.setCursor(Cursor.getDefaultCursor());
            } else {
                Desktop d = Desktop.getDesktop();
                try {

//            boolean flag = new DownloadUploadFile().getFTPFile(documentTable.getValueAt(row, 6).toString(), "", documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf", SingletonClass.sysFilePath);
                    boolean flag = new DownloadUploadFile().getFTPFile(documentTable.getValueAt(row, 7).toString(), "", SingletonClass.sysFilePath);
                    if (flag) {
                        addBankFrame.setCursor(Cursor.getDefaultCursor());
//            d.open(new File(SingletonClass.sysFilePath+documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf"));
                        d.open(new File(SingletonClass.sysFilePath + documentTable.getValueAt(row, 7).toString().substring(documentTable.getValueAt(row, 7).toString().lastIndexOf("/"))));
                    } else {
                        JOptionPane.showMessageDialog(null, "File not found", "View Document", JOptionPane.ERROR_MESSAGE);
                        addBankFrame.setCursor(Cursor.getDefaultCursor());
                    }

                } catch (IOException e) {
                    addBankFrame.setCursor(Cursor.getDefaultCursor());
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else if (col == 6 && !"Document List".equals(documentTable.getValueAt(row, 1))) {
            addBankFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DefaultTableModel tableModel = (DefaultTableModel) documentTable.getModel();
            tableModel.removeRow(row);
            bankCounter--;
            addBankFrame.setCursor(Cursor.getDefaultCursor());
//        JOptionPane.showMessageDialog(this, "Successfully Deleted Extra Row");
        } else if (col == 5) {
            addBankFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (bankNameField.getText() == null || bankNameField.getText().equals("")) {

                JOptionPane.showMessageDialog(this, "Please provide bank Name");
                addBankFrame.setCursor(Cursor.getDefaultCursor());

            } else {
                if (documentTable.getValueAt(row, 1) == null || documentTable.getValueAt(row, 1).toString().equals("")) {

                    JOptionPane.showMessageDialog(this, "Please provide document Name");
                    addBankFrame.setCursor(Cursor.getDefaultCursor());

                } else {
                    JFileChooser filechooser = new JFileChooser();
                    filechooser.setCurrentDirectory(dir);
//	   filechooser.setFileFilter(new TypeOfFile());
//	   filechooser.setAcceptAllFileFilterUsed(false);
                    int status = filechooser.showDialog(filechooser, "Upload");
                    dir = filechooser.getCurrentDirectory();

                    if (status == filechooser.CANCEL_OPTION) {
                    } else {
                        File file = filechooser.getSelectedFile();
                        String path = file.getPath();
                        String ext = path.substring(path.lastIndexOf("."));
                        System.out.println("extension is " + ext);
                        System.out.println("file path is " + path);

//            boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverBankDocPath, bankNameField.getText().toString().replace(" ", "_"),path, documentTable.getValueAt(row, 1).toString().replace(" ", "_")+".pdf");
                        boolean flag = new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverBankDocPath, bankNameField.getText().toString().replace(" ", "_") + branchField.getText().replace(" ", "_"), path, documentTable.getValueAt(row, 1).toString().replace(" ", "_") + ext);
//                this will check whether document uploaded or not and if not uploaded notify about which document is not uplaoded 
                        if (flag) {
//                    String docServerPath = SingletonClass.serverBankDocPath +bankNameField.getText().toString().replace(" ", "_");
                            String docServerPath = SingletonClass.serverBankDocPath + bankNameField.getText().toString().replace(" ", "_") + branchField.getText().replace(" ", "_") + "/" + documentTable.getValueAt(row, 1).toString().replace(" ", "_") + ext;
                            documentTable.setValueAt(docServerPath, row, 7);
                            System.out.println("doc path is " + docServerPath);
                            JOptionPane.showMessageDialog(null, "file uploaded Successfully");
                            addBankFrame.setCursor(Cursor.getDefaultCursor());

                        } else {
                            JOptionPane.showMessageDialog(null, "Error in Uploading " + documentTable.getValueAt(row, 1).toString(), "uploading document", JOptionPane.ERROR_MESSAGE);
                            addBankFrame.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                }
            }
            addBankFrame.setCursor(Cursor.getDefaultCursor());
        }


    }//GEN-LAST:event_documentTableMouseClicked

    private void accountNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountNoFieldKeyReleased
        // TODO add your handling code here:
        boolean isInt = isInt(accountNoField.getText());
        System.out.println("phnPattern.matches(): " + accountNoField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid Account no.", "", 0);
            accountNoField.setText("");
            // TODO add your handling code here:
        }
    }//GEN-LAST:event_accountNoFieldKeyReleased

    private void faxNoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_faxNoTextFieldKeyReleased
        // TODO add your handling code here:
        boolean isInt = isInt(faxNoTextField.getText());
        System.out.println("phnPattern.matches(): " + faxNoTextField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid Fax no.", "", 0);
            faxNoTextField.setText("");
        }
    }//GEN-LAST:event_faxNoTextFieldKeyReleased

    private void personTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_personTableKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_personTableKeyReleased

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

    public void getDocLocation(String docLocation, int row, int col) {
        documentTable.setValueAt(docLocation, row, col);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNoField;
    private javax.swing.JButton addDocumentButton;
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
    private javax.swing.JTextField faxNoTextField;
    private javax.swing.JTextField ifciCodeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable personTable;
    private javax.swing.JComboBox renewalDateCombo;
    private javax.swing.JButton resetBankButton;
    private javax.swing.JButton saveBankButton;
    private javax.swing.JPanel titalPanel;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox requirementType;
}
