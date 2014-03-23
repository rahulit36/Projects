/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.documents.PreliminaryOfferPdf;
import com.msr.ftp.bean.MeetingsDetails;
import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.newTx.dao.ProposalViewBean;
import com.msr.newTx.dao.SLADataBean;
import com.msr.tools.UI.IconTableCellRenderer;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.MeetingDetailsParser;
import com.parser.ParserCaller;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.FormatStringValue;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013 & 19 feb 2013
 */
class MyTableModel extends DefaultTableModel {

    public MyTableModel() {
        super(
                new Object[][]{},
                new String[]{
            "      Bank", "Proposed Limit(in Cr.)", "Usance     Period", "Interst    Rate", "Handling    Charges", "Margin    Days",
            "Sanction       Limit", "Sanction Date", "Visit    Date", "Visit           Status", "   Limit Type", "", "", "", "", ""
        });

    }

    public boolean isCellEditable(int row, int col) {
        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            if (col == 6 || col == 7 && (SingletonClass.txStatusSelect.equalsIgnoreCase("Create Limit")
                    || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Limit Setup"))) {
                return true;
            } else {
                return true;
            }
        } else if (SingletonClass.isView || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Maindate")
                || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Documents")) {
            return false;
        } else if (SingletonClass.designation.equalsIgnoreCase("RM")) {
            if (col == 13 || col == 9 || col == 6 || col == 7) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}

public class ProposalDetailsPanel extends javax.swing.JPanel {

    NewTxPanel newTxPanel = null;
    int counterILC = 0;
    int counterFLC = 0;
    String email = "";
    String emailText = "";
    String subToMnmAmount = "";
    List<SLADataBean> sLADataBeansILCList = null;
    List<SLADataBean> sLADataBeansFLCList = null;
    JTabbedPane tabbedPane = null;
    List<String> slaPdfFilePathList = null;
    List<String> slaPdfBankNameList = null;
    MainScreen maiScreen = null;

    /**
     * Creates new form ProposalDetailsPanel
     */
    public ProposalDetailsPanel(String email, String accName, NewTxPanel newTxPanel, JTabbedPane tabbedPane, MainScreen maiScreen) {
        initComponents();
        this.email = email;
        this.newTxPanel = newTxPanel;
        this.tabbedPane = tabbedPane;
        this.maiScreen = maiScreen;
        setTableColumnWidth(ilcBankTable, 11);
        setTableColumnWidth(flcBankTable, 11);
        addDeleteColumn(ilcBankTable, 12);
        addDeleteColumn(flcBankTable, 12);
        addBanks(ilcBankTable);
        addBanks(flcBankTable);
        if (SingletonClass.isView) {
            proposalSaveButton.setEnabled(false);
        }
        // call the method for set the values of proposal details if transaction id is not null, that means in case of edit view or after creating new transaction without change the tab.
        if (SingletonClass.txIdEdit != null) {
            callParsersForData();
        }
    }

    private void callParsersForData() {
        List<HashMap> ilcList = null;
        List<HashMap> flcList = null;
        ParserCaller parserCaller = new ParserCaller();
        ProposalViewBean proposalViewBean = parserCaller.parseProposalDataForView(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "txnProposalXML" + "&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&" + SingletonClass.userName + "=" + email);
        ilcList = proposalViewBean.getIlcList();
        flcList = proposalViewBean.getFlcList();
        setProposalDetailsDataInFields(ilcList, flcList);
        addCheckBoxColumn(ilcBankTable);
        addCheckBoxColumn(flcBankTable);

    }

    public void addCheckBoxColumn(JTable tableName) {
        JCheckBox checkBox = new javax.swing.JCheckBox();
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        tableName.getColumnModel().getColumn(11).setCellEditor(new DefaultCellEditor(checkBox));
        tableName.getColumnModel().getColumn(11).setCellRenderer(new CheckBoxRenderer());

        TableColumn dateColumn = tableName.getColumnModel().getColumn(7);
        dateColumn.setMaxWidth(120);
        dateColumn.setMinWidth(120);
        dateColumn.setWidth(120);

        TableColumn visitDate = tableName.getColumnModel().getColumn(8);
        visitDate.setMaxWidth(110);
        visitDate.setMinWidth(110);
        visitDate.setWidth(110);


//        dateColumn.setMinWidth(130);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DatePickerCellEditor cellEditor = new DatePickerCellEditor(dateFormat);

        cellEditor.setFormats(dateFormat);
        cellEditor.setClickCountToStart(0);


        dateColumn.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(dateFormat), JLabel.LEFT));
        dateColumn.setCellEditor(cellEditor);

        visitDate.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(dateFormat), JLabel.LEFT));
        visitDate.setCellEditor(cellEditor);


//        dateColumn.setMinWidth(130);

        cellEditor.setFormats(dateFormat);
        cellEditor.setClickCountToStart(0);

    }

    public void setProposalDetailsDataInFields(List<HashMap> ilcList, List<HashMap> flcList) {

        setILCValues(ilcList);
        setFLCValues(flcList);

    }
//"Bank", "Amount", "Actual Limit","Usance", "Interst Rate","Margin","",""

//    <proposal txnNo="1" beneficiary="piyush2004" client="piyush2000" bankName="abc bank" 
//            actualLimit="456.00" proposallimit="765.00" usance="456" interestRate="65.00" marginDays="456" 
//                    handlingCharges="3423.00" chargesType="%annual" txnStatus="Send Offer Letter"/>
    public void setILCValues(List<HashMap> ilcList) {

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") || SingletonClass.isView) {
            ilcOurChargeTextF.setEditable(false);
            addBankILC.setEnabled(false);
            ilcOurChargeCombo.setEnabled(false);
        }
                           String ourCharges = "";
                    System.out.println("****************************"+SingletonClass.ourChargesAutoFill);
                        if(SingletonClass.ourChargesAutoFill!=null && !SingletonClass.ourChargesAutoFill.equals("null") && !SingletonClass.ourChargesAutoFill.equals("")){            
//                            .split(";")[0].split(":")[1]
                            ourCharges = SingletonClass.ourChargesAutoFill.split(";")[0].split(":")[0];
                            ilcOurChargeTextF.setText(ourCharges);
                        }
        DefaultTableModel ilcTableModel = (DefaultTableModel) ilcBankTable.getModel();
        if (ilcList.size() > 0) {
            Map<String, String> ilcMap = null;
            for (int listCount = counterILC; listCount < ilcList.size(); listCount++) {
                ilcTableModel.insertRow(listCount, new Object[]{});
                ilcMap = ilcList.get(listCount);
                ilcBankTable.setValueAt(ilcMap.get("proposalNo"), listCount, 15);


                if (ilcMap.get("visitStatus") != null && !ilcMap.get("visitStatus").equals("null")) {
                    ilcBankTable.setValueAt(ilcMap.get("visitStatus"), listCount, 9);
                }
                if (ilcMap.get("visitDate") != null && !ilcMap.get("visitDate").equals("null")) {
                    ilcBankTable.setValueAt(ilcMap.get("visitDate"), listCount, 8);
                }
                ilcBankTable.setValueAt(ilcMap.get("proposalNo"), listCount, 15);
                ilcBankTable.setValueAt(ilcMap.get("proposalNo"), listCount, 15);


                ilcBankTable.setValueAt(ilcMap.get("bankName"), listCount, 0);
                double proLimit = 0.0;
                double actuLimit = 0.0;
                if(!ilcMap.get("proposallimit").equals("null") && !"".equals(ilcMap.get("proposallimit")))
                {
                   proLimit =  new Double(ilcMap.get("proposallimit")).doubleValue()/10000000;
                }
                ilcBankTable.setValueAt(proLimit, listCount, 1);
                if (ilcMap.get("actualLimit") == null || ilcMap.get("actualLimit").equals("null")) {
                    ilcBankTable.setValueAt("", listCount, 6);
                } else {      
                   actuLimit =  new Double(ilcMap.get("actualLimit")).doubleValue()/10000000;
                    ilcBankTable.setValueAt(actuLimit, listCount, 6);
                }
                if (ilcMap.get("sanctionDate") == null && "null".equals(ilcMap.get("sanctionDate"))) {
                    ilcBankTable.setValueAt("", listCount, 7);
                } else {
                    ilcBankTable.setValueAt(ilcMap.get("sanctionDate"), listCount, 7);
                }
                ilcBankTable.setValueAt(ilcMap.get("usance"), listCount, 2);
                ilcBankTable.setValueAt(ilcMap.get("interestRate"), listCount, 3);
                ilcBankTable.setValueAt(ilcMap.get("handlingCharges"), listCount, 4);
                ilcBankTable.setValueAt(ilcMap.get("marginDays"), listCount, 5);
                ilcBankTable.setValueAt(ilcMap.get("limitType"), listCount, 10);



                boolean isLc = false;
                if (ilcMap.get("lcStatus").equalsIgnoreCase("y")) {
                    isLc = true;
                }
                ilcBankTable.setValueAt(isLc, listCount, 11);
                ilcBankTable.setValueAt(ilcMap.get("beneficiary"), listCount, 13);
                
                if (listCount == 0) {
                    if (SingletonClass.expBusiness == null || "".equals(SingletonClass.expBusiness) || "null".equals(SingletonClass.expBusiness)) {

                        SingletonClass.expBusiness = ilcMap.get("expBusiness");
                    }
         
                    if(ilcMap.get("ourCharges")!=null && !ilcMap.get("ourCharges").equals("null") && !ilcMap.get("ourCharges").equals("")){
                        ourCharges = ilcMap.get("ourCharges");
                    }
                    System.out.println("ourCharges: "+ourCharges);
          
                }          if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                        ilcOurChargeTextF.setText("NA");
                        subMinINR.setText("NA");
                        ilcBankTable.setValueAt(ourCharges, listCount, 14);
                           System.out.println(" counter set ourCharges " + listCount);
                        addBankILC.setFont(new java.awt.Font("Tahoma", 1, 14));
                        System.out.println("charges type: " + ilcMap.get("chargesType"));
                        ilcOurChargeCombo.setSelectedItem(ilcMap.get("chargesType"));
                    } else if (SingletonClass.isView) {
                        ilcOurChargeTextF.setText(ourCharges);
                        System.out.println("charges type: " + ilcMap.get("chargesType"));
                        ilcOurChargeCombo.setSelectedItem(ilcMap.get("chargesType"));
                    } else {
                        ilcOurChargeTextF.setText(ourCharges);
                        System.out.println("charges type: " + ilcMap.get("chargesType"));
                        ilcOurChargeCombo.setSelectedItem(ilcMap.get("chargesType"));
                    }
                counterILC++;
            }
        }
    }

    public void setFLCValues(List<HashMap> flcList) {

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") || SingletonClass.isView) {
            flcOurChargeTextF.setEditable(false);
            addBankFLC.setEnabled(false);
            flcOurChargeCombo.setEnabled(false);
        }
        DefaultTableModel flcTableModel = (DefaultTableModel) flcBankTable.getModel();
        if (flcList.size() > 0) {
            Map<String, String> flcMap = null;
            for (int listCount = counterFLC; listCount < flcList.size(); listCount++) {
                flcTableModel.insertRow(listCount, new Object[]{});
                flcMap = flcList.get(listCount);
                flcBankTable.setValueAt(flcMap.get("proposalNo"), listCount, 15);


                if (flcMap.get("visitStatus") != null && !flcMap.get("visitStatus").equals("null")) {
                    flcBankTable.setValueAt(flcMap.get("visitStatus"), listCount, 9);
                }
                if (flcMap.get("visitDate") != null && !flcMap.get("visitDate").equals("null")) {
                    flcBankTable.setValueAt(flcMap.get("visitDate"), listCount, 8);
                }
 double proLimit = 0.0;
                double actuLimit = 0.0;
                if(!flcMap.get("proposallimit").equals("null") && !"".equals(flcMap.get("proposallimit")))
                {
                   proLimit =  new Double(flcMap.get("proposallimit")).doubleValue()/10000000;
                }
                flcBankTable.setValueAt(flcMap.get("bankName"), listCount, 0);
                flcBankTable.setValueAt(proLimit, listCount, 1);
                if (flcMap.get("actualLimit") == null || flcMap.get("actualLimit").equals("null")) {
                    flcBankTable.setValueAt("", listCount, 6);
                } else {
                   actuLimit =  new Double(flcMap.get("actualLimit")).doubleValue()/10000000;
                    flcBankTable.setValueAt(actuLimit, listCount, 6);
                }
                if (flcMap.get("sanctionDate") == null && "null".equals(flcMap.get("sanctionDate"))) {
                    flcBankTable.setValueAt("", listCount, 7);
                } else {
                    flcBankTable.setValueAt(flcMap.get("sanctionDate"), listCount, 7);
                }
                flcBankTable.setValueAt(flcMap.get("usance"), listCount, 2);
                flcBankTable.setValueAt(flcMap.get("interestRate"), listCount, 3);
                flcBankTable.setValueAt(flcMap.get("handlingCharges"), listCount, 4);
                flcBankTable.setValueAt(flcMap.get("marginDays"), listCount, 5);
                flcBankTable.setValueAt(flcMap.get("limitType"), listCount, 10);
                boolean isLc = false;
                if (flcMap.get("lcStatus").equalsIgnoreCase("y")) {
                    isLc = true;
                }
                flcBankTable.setValueAt(isLc, listCount, 11);
                flcBankTable.setValueAt(flcMap.get("beneficiary"), listCount, 13);
                
                    String ourCharges = "";
                    if(flcMap.get("ourCharges")!=null && !flcMap.get("ourCharges").equals("null") && !flcMap.get("ourCharges").equals("")){
                        ourCharges = flcMap.get("ourCharges");
                    }else{
                        if(SingletonClass.ourChargesAutoFill!=null && !SingletonClass.ourChargesAutoFill.equals("null") && !SingletonClass.ourChargesAutoFill.equals("")){                            
                            ourCharges = SingletonClass.ourChargesAutoFill.split(";")[0].split(":")[0];
                        }
                    }
                
                if (listCount == 0) {
                    if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                        flcOurChargeTextF.setText("NA");
                        flcBankTable.setValueAt(ourCharges, listCount, 14);
                        addBankFLC.setFont(new java.awt.Font("Tahoma", 1, 14));
                        System.out.println("charges type: " + flcMap.get("chargesType"));
                        flcOurChargeCombo.setSelectedItem(flcMap.get("chargesType"));

                    } else if (SingletonClass.isView) {
                        flcOurChargeTextF.setText(ourCharges);
                        flcOurChargeCombo.setSelectedItem(flcMap.get("chargesType"));
                    } else {
                        flcOurChargeTextF.setText(ourCharges);
                        flcOurChargeCombo.setSelectedItem(flcMap.get("chargesType"));
                    }
                }
                System.out.println(" counter " + listCount);
                counterFLC++;
            }
        }
    }

    // add bank list in table
    private void addBanks(JTable tableName) {
        List bankList = null;
        ParserCaller parserCaller = new ParserCaller();
        JComboBox bankComboBox = new JComboBox();

        JComboBox limitTypeCombo = new JComboBox();
        limitTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Applicant", "Beneficiary", "Applicant+Beneficiary"}));

        bankList = parserCaller.parseBankNameListXml(email);

        TableColumn col0 = tableName.getColumnModel().getColumn(0);
        bankComboBox.removeAllItems();
        col0.setCellEditor(new DefaultCellEditor(setcomboListItems(bankList, bankComboBox)));

        TableColumn col10 = tableName.getColumnModel().getColumn(10);
        col10.setCellEditor(new DefaultCellEditor(limitTypeCombo));

    }

    // set the values in combobox.
    private JComboBox setcomboListItems(List bankList, JComboBox bankComboBox) {
        if (bankList.size() > 0) {
            for (int listCount = 0; listCount < bankList.size(); listCount++) {
                if (bankList.get(listCount) != null && ((String) bankList.get(listCount)).length() > 0) {

                    bankComboBox.addItem(bankList.get(listCount));
                }
            }
        }
        return bankComboBox;
    }

    // add the delete column to the tables
    private void addDeleteColumn(JTable tableName, int columnIndex) {
        // add delete button at column index postion of all rows
        TableColumn deleteColumn = tableName.getColumnModel().getColumn(columnIndex);
        deleteColumn.setCellRenderer(new IconTableCellRenderer("Delete"));
    }

// set the delete and choose bank proposal by client column size
    public final void setTableColumnWidth(JTable tableName, int columnIndex) {
        TableColumn selectedBank = tableName.getColumnModel().getColumn(columnIndex);
        selectedBank.setMaxWidth(20);
        selectedBank.setWidth(20);

        TableColumn delColumn = tableName.getColumnModel().getColumn(columnIndex + 1);
        delColumn.setMaxWidth(20);
        delColumn.setWidth(20);

        TableColumn bankColumn = tableName.getColumnModel().getColumn(0);
        bankColumn.setMaxWidth(150);
        bankColumn.setMinWidth(150);
        bankColumn.setWidth(150);

        TableColumn col3 = tableName.getColumnModel().getColumn(3);
        col3.setMaxWidth(90);
        col3.setMinWidth(90);
        col3.setWidth(90);

        TableColumn amountColumn = tableName.getColumnModel().getColumn(1);
        amountColumn.setMaxWidth(98);
        amountColumn.setMinWidth(98);
        amountColumn.setWidth(98);

        TableColumn usanceColumn = tableName.getColumnModel().getColumn(2);
        usanceColumn.setMaxWidth(90);
        usanceColumn.setMinWidth(90);
        usanceColumn.setWidth(90);


        TableColumn hndlngCol = tableName.getColumnModel().getColumn(4);
        hndlngCol.setMaxWidth(100);
        hndlngCol.setMinWidth(100);
        hndlngCol.setWidth(100);

        TableColumn marginCol = tableName.getColumnModel().getColumn(5);
        marginCol.setMaxWidth(90);
        marginCol.setMinWidth(90);
        marginCol.setWidth(90);

        TableColumn limitCol = tableName.getColumnModel().getColumn(6);
        limitCol.setMaxWidth(110);
        limitCol.setMinWidth(110);
        limitCol.setWidth(110);


        TableColumn visitStatusColumn = tableName.getColumnModel().getColumn(columnIndex - 3);
        visitStatusColumn.setMaxWidth(110);
        visitStatusColumn.setWidth(110);


        TableColumn visitDateColumn = tableName.getColumnModel().getColumn(9);
        visitDateColumn.setMaxWidth(100);
        visitDateColumn.setMinWidth(100);
        visitDateColumn.setWidth(100);

// set the minimum width  of that colum
        TableColumn benColumn = tableName.getColumnModel().getColumn(columnIndex + 2);
        benColumn.setWidth(0);
        benColumn.setMaxWidth(0);
        benColumn.setMinWidth(0);
        benColumn.setResizable(false);
// set the minimum width  of that colum
        TableColumn ourChrgColumn = tableName.getColumnModel().getColumn(columnIndex + 3);
        ourChrgColumn.setWidth(0);
        ourChrgColumn.setMaxWidth(0);
        ourChrgColumn.setMinWidth(0);
        ourChrgColumn.setResizable(false);
// set the minimum width  of that colum
        TableColumn proposalIdColumn = tableName.getColumnModel().getColumn(columnIndex + 4);
        proposalIdColumn.setWidth(0);
        proposalIdColumn.setMaxWidth(0);
        proposalIdColumn.setMinWidth(0);
        proposalIdColumn.setResizable(false);

//// set the minimum width  of that colum
//        TableColumn vistStatusCol = tableName.getColumnModel().getColumn(columnIndex + 5);
//        vistStatusCol.setWidth(0);
//        vistStatusCol.setMaxWidth(0);
//        vistStatusCol.setMinWidth(0);
//        vistStatusCol.setResizable(false);

    }

// set the delete and choose bank proposal by client column size
//    public final void setFLCTableColumnWidth() {
//        TableColumn selectedBank = flcBankTable.getColumnModel().getColumn(5);
//        selectedBank.setMaxWidth(50);
//        selectedBank.setWidth(50);
//        TableColumn delColumn = flcBankTable.getColumnModel().getColumn(6);
//        delColumn.setMaxWidth(50);
//        delColumn.setWidth(50);
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        proposalSaveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ilcBankTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        flcBankTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        flcOurChargeTextF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ilcOurChargeTextF = new javax.swing.JTextField();
        flcOurChargeCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        ilcOurChargeCombo = new javax.swing.JComboBox();
        addBankILC = new javax.swing.JButton();
        addBankFLC = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        subMinINR = new javax.swing.JTextField();

        jButton1.setText("Save");

        setAutoscrolls(true);

        jPanel2.setBackground(new java.awt.Color(215, 215, 216));

        proposalSaveButton.setText("Save");
        proposalSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proposalSaveButtonActionPerformed(evt);
            }
        });

        JTableHeader header = ilcBankTable.getTableHeader();
        header.setDefaultRenderer(new MyHeaderRenderer());
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,50));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        ilcBankTable.setRowHeight(30);
        ilcBankTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ilcBankTable.setModel(new MyTableModel());
        ilcBankTable.getTableHeader().setResizingAllowed(false);
        ilcBankTable.getTableHeader().setReorderingAllowed(false);
        TableColumn benColumn = ilcBankTable.getColumnModel().getColumn(13);
        benColumn.setWidth(0);
        benColumn.setMaxWidth(0);
        benColumn.setMinWidth(0);
        benColumn.setResizable(false);
        TableColumn ourChargeColumn = ilcBankTable.getColumnModel().getColumn(14);
        ourChargeColumn.setWidth(0);
        ourChargeColumn.setMaxWidth(0);
        ourChargeColumn.setMinWidth(0);
        ourChargeColumn.setResizable(false);
        // set the minimum width  of that colum
        TableColumn proposalIdColumn = ilcBankTable.getColumnModel().getColumn(15);
        proposalIdColumn.setWidth(0);
        proposalIdColumn.setMaxWidth(0);
        proposalIdColumn.setMinWidth(0);
        proposalIdColumn.setResizable(false);
        ilcBankTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ilcBankTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ilcBankTable);

        JTableHeader header1 = flcBankTable.getTableHeader();
        header1.setDefaultRenderer(new MyHeaderRenderer());
        header1.setBackground(new Color(192, 191, 200));
        header1.setPreferredSize(new Dimension(600,50));
        Font font1 = new Font("Serif", Font.BOLD, 14);
        header1.setForeground(new Color(0, 103, 204));

        header1.setFont(font1);
        flcBankTable.setRowHeight(30);
        flcBankTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        flcBankTable.setModel(new MyTableModel());
        flcBankTable.getTableHeader().setResizingAllowed(false);
        flcBankTable.getTableHeader().setReorderingAllowed(false);
        TableColumn benColumnFLC = flcBankTable.getColumnModel().getColumn(13);
        benColumnFLC.setWidth(0);
        benColumnFLC.setMaxWidth(0);
        benColumnFLC.setMinWidth(0);
        benColumnFLC.setResizable(false);
        TableColumn ourChargesColumnFLC = flcBankTable.getColumnModel().getColumn(14);
        ourChargesColumnFLC.setWidth(0);
        ourChargesColumnFLC.setMaxWidth(0);
        ourChargesColumnFLC.setMinWidth(0);
        ourChargesColumnFLC.setResizable(false);
        // set the minimum width  of that colum
        TableColumn proposalIdColumFlc = flcBankTable.getColumnModel().getColumn(15);
        proposalIdColumFlc.setWidth(0);
        proposalIdColumFlc.setMaxWidth(0);
        proposalIdColumFlc.setMinWidth(0);
        proposalIdColumFlc.setResizable(false);
        flcBankTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                flcBankTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(flcBankTable);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel1.setText("FLC:");

        jLabel2.setText("Our Charges:");

        flcOurChargeTextF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                flcOurChargeTextFKeyReleased(evt);
            }
        });

        jLabel3.setText("Our Charges:");

        ilcOurChargeTextF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ilcOurChargeTextFKeyReleased(evt);
            }
        });

        flcOurChargeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Select Rate", "%Annual Rate", "%Rate Amount", "Flate Amount" }));
        flcOurChargeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flcOurChargeComboActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel4.setText("ILC:");

        ilcOurChargeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Select Rate", "%Annual Rate", "%Rate Amount", "Flate Amount" }));
        ilcOurChargeCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ilcOurChargeComboItemStateChanged(evt);
            }
        });
        ilcOurChargeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ilcOurChargeComboActionPerformed(evt);
            }
        });

        addBankILC.setText("Add Proposal");
        addBankILC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBankILCActionPerformed(evt);
            }
        });

        addBankFLC.setText("Add Proposal");
        addBankFLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBankFLCActionPerformed(evt);
            }
        });

        jLabel5.setText("Subject to a minimum of INR:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addBankFLC)
                                .addGap(31, 31, 31)
                                .addComponent(proposalSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(ilcOurChargeTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ilcOurChargeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(flcOurChargeTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(flcOurChargeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(2, 2, 2)
                                .addComponent(subMinINR, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBankILC)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ilcOurChargeTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ilcOurChargeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(subMinINR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addBankILC)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flcOurChargeTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flcOurChargeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBankFLC)
                    .addComponent(proposalSaveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
// save button process goes here
    private void proposalSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proposalSaveButtonActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {

//            StringBuffer addInfoCheck = new StringBuffer();
//            addInfoCheck.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=getAdditionalDetails&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName));
//            addInfoCheck.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=checkClientDocs&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName)).append("&").append(SingletonClass.txNo).append("=").append(URLEncoder.encode(SingletonClass.txIdEdit));
//            callServerUrl serverUrl12 = new callServerUrl();
//            String addInfoCheckUrl = addInfoCheck.toString().replaceAll(" ", "%20");
//            String response1 = serverUrl12.urlProcessiong(addInfoCheckUrl);
//            if (!response1.equalsIgnoreCase("invalid")) {
            try {
                List<String> ilcURLList = new ArrayList<String>();
                List<String> flcURLList = new ArrayList<String>();
                sLADataBeansILCList = new ArrayList<SLADataBean>();
                sLADataBeansFLCList = new ArrayList<SLADataBean>();
                boolean isIlcHandling = validateOnSaveIlcHandling();
                boolean isFlcHandling = validateOnSaveFlcHandling();
                boolean isILCTableValidate = true;
                boolean isFLCTableValidate = true;
                boolean isSave = false;

                if (!isIlcHandling && !isFlcHandling) {
                    JOptionPane.showMessageDialog(null, "Please fill at least one proposal detail of ILC or FLC.", "", 0);
                } else {
                    if (isIlcHandling) {
                        isILCTableValidate = validateAndGetILCTable(ilcURLList);
                    }
                    if (isFlcHandling) {
                        isFLCTableValidate = validateAndGetFLCTable(flcURLList);
                    }
                    if (isILCTableValidate && isFLCTableValidate) {
                        if (isILCTableValidate) {
                            Iterator<String> ilcUrlIterator = ilcURLList.iterator();
                            while (ilcUrlIterator.hasNext()) {
                                callServerUrl serverUrl1 = new callServerUrl();
                                String ilcUrlString = ilcUrlIterator.next().replaceAll(" ", "%20");
                                String response = serverUrl1.urlProcessiong(ilcUrlString);
                                if (response.equalsIgnoreCase("valid")) {
                                    isSave = true;
                                } else {
                                    isSave = false;
                                }
                            }
                        }

                        if (isFLCTableValidate) {
                            Iterator<String> flcUrlIterator = flcURLList.iterator();
                            while (flcUrlIterator.hasNext()) {
                                callServerUrl serverUrl1 = new callServerUrl();
                                String flcUrlString = flcUrlIterator.next().replaceAll(" ", "%20");
                                String response = serverUrl1.urlProcessiong(flcUrlString);
                                if (response.equalsIgnoreCase("valid")) {
                                    isSave = true;
                                } else {
                                    isSave = false;
                                }
                            }
                        }
                        if (isSave) {
                            emailText = "Dear Sir,<br><br>" + "Kindly approve the below mentioned client for LCBD limit setup.  " + SingletonClass.clientCompanyName;
                            boolean isSelected = makeAndSendSLA();
                            if (isSelected) {
                                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                SingletonClass.isSave = true;
                                SingletonClass.nextOpenAccordion = "PROPOSAL DETAILS";
                                newTxPanel.refreshScreen(this);
                                if (SingletonClass.txStatusSelect.equalsIgnoreCase("Identification Of Client") || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Internal Approval") || SingletonClass.txStatusSelect.equalsIgnoreCase("Send Offer Letter") || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Client Approval")) {
                                    Map<String, String> emailMap = null;
                                    ParserCaller parserCaller = new ParserCaller();
                                    emailMap = parserCaller.parseEmailSForProposal(email, SingletonClass.txIdEdit);
                                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                    
                                    new EmailScreen1(SingletonClass.txIdEdit, SingletonClass.txTypeEdit + ":" + SingletonClass.clientCompanyName + " TXNO-" + SingletonClass.txIdEdit, this, email, slaPdfFilePathList, emailMap, emailText).setVisible(true);
                                }
                                tabbedPane.setSelectedIndex(0);
                            }
                            emailText = "";
//                    JOptionPane.showMessageDialog(null, "Proposal Details Saved Successfully, Please fill Next", "", 1);
                        } else if (!isSave) {
                            JOptionPane.showMessageDialog(null, "Problem occured, try again later.", "", 0);
                            newTxPanel.refreshScreen(this);
                            newTxPanel.repaint();
                        }
                    }
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            } catch (Exception ex) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                ex.printStackTrace();
            }
//            } else {
////                JOptionPane.showMessageDialog(null, "Please save the addition information of the client.", "", 0);
//                JOptionPane.showMessageDialog(null, "Please Upload FSA and Company Profile docs in Document List.", "", 0);
//            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
        }
    }//GEN-LAST:event_proposalSaveButtonActionPerformed

    public boolean makeAndSendSLA() {
        boolean isSelectedILC = true;
        boolean isSelectedFLC = true;
        boolean isSelected = true;
        slaPdfFilePathList = new ArrayList<String>();
        slaPdfBankNameList = new ArrayList<String>();

        if ((sLADataBeansILCList != null && sLADataBeansILCList.size() > 0) || (sLADataBeansFLCList != null && sLADataBeansFLCList.size() > 0)) {
            if (sLADataBeansILCList.size() == 1 && sLADataBeansFLCList.size() == 1) {
                try {
                    SLADataBean sLADataBeanILC = sLADataBeansILCList.get(0);
                    SLADataBean sLADataBeanFLC = sLADataBeansFLCList.get(0);
                    System.out.println("sLADataBean: " + sLADataBeanILC.getUsance() + " intr" + sLADataBeanILC.getIntRate());
                    System.out.println("sLADataBeanFLC: " + sLADataBeanFLC.getUsance() + " intr" + sLADataBeanFLC.getIntRate());
                    String path1 = SingletonClass.sysFilePath + "service_Agreement_" + SingletonClass.clientCompanyName.replace(" ", "_") + ".pdf";
                    slaPdfFilePathList.add(path1);


                    emailText = emailText + "<br><br><br><b>ILC Details:</b><br><br>"
                            + "<table border=\"1\">"
                            //                            + "<tr><th>Particulars</th><th>Terms</th></tr>"
                            + "<tr><td><b>Negotiating Bank                    <b></td><td>" + sLADataBeanILC.getScheduleBank() + "</td></tr>"
                            + "<tr><td><b>Our Charges                          <b></td><td>" + sLADataBeanILC.getOurChrge() + "</td></tr>"
                            + "<tr><td><b>Amount per Month                <b></td><td>" + SingletonClass.expBusiness + "</td></tr>"
                            + "<tr><td><b>Usance                                   <b></td><td>" + sLADataBeanILC.getUsance() + " days</td></tr>"
                            + "<tr><td><b>Interest rate*                        <b></td><td>Best Rate as per the market, presently around " + sLADataBeanILC.getIntRate() + " pa.</td></tr>"
                            + "<tr><td><b>Handling Charges                  <b></td><td>" + sLADataBeanILC.getHndlingChrge() + " per bill of exchange. (+S.Tax)</td></tr>"
                            + "<tr><td><b>Margin<b>                            </td><td>" + sLADataBeanILC.getMargin() + " days over the usance of the L.C.</td></tr>"
                            + "</table>";


                    emailText = emailText + "<br><br><html><body><br>FLC Details:<br><br>"
                            + "<table border=\"1\">"
                            //                            + "<tr><th>Particulars</th><th>Terms</th></tr>"
                            + "<tr><td><b>Negotiating Bank                    <b></td><td>" + sLADataBeanFLC.getScheduleBank() + "</td></tr>"
                            + "<tr><td><b>Our Charges                          <b></td><td>" + sLADataBeanFLC.getOurChrge() + "</td></tr>"
                            + "<tr><td><b>Amount per Month                <b></td><td>" + SingletonClass.expBusiness + "</td></tr>"
                            + "<tr><td><b>Usance                                   <b></td><td>" + sLADataBeanFLC.getUsance() + " days</td></tr>"
                            + "<tr><td><b>Interest rate*                        <b></td><td>Best Rate as per the market, presently around " + sLADataBeanFLC.getIntRate() + " pa.</td></tr>"
                            + "<tr><td><b>Handling Charges                  <b></td><td>" + sLADataBeanFLC.getHndlingChrge() + " per bill of exchange. (+S.Tax)</td></tr>"
                            + "<tr><td><b>Margin                                   <b></td><td>" + sLADataBeanFLC.getMargin() + " days over the usance of the L.C.</td></tr>"
                            + "</table>";
                    emailText = emailText + "<br><br><br>Regards,<br>" + SingletonClass.loginUserName;
                    System.out.println(";sLADataBeanILC.getHndlingChrge();::::::" + sLADataBeanILC.getHndlingChrge());
                    new PreliminaryOfferPdf(sLADataBeanILC, sLADataBeanFLC, path1);
                    isSelectedILC = true;
                    isSelectedFLC = true;
                } catch (Exception ex) {
                    isSelectedILC = false;
                    isSelectedFLC = false;
                    ex.printStackTrace();
                }
            } else {
                SLADataBean sLADataBeanILC = null;
                SLADataBean sLADataBeanFLC = null;
                String path1 = null;
                for (int listCount = 0; listCount < sLADataBeansILCList.size(); listCount++) {
                    try {
                        sLADataBeanILC = sLADataBeansILCList.get(listCount);
                        if (!sLADataBeanILC.getApprovedBankCheck().equals("true") && sLADataBeansILCList.size() != 1) {
                            isSelectedILC = false;
                        } else {
                            System.out.println("sLADataBean: " + sLADataBeanILC.getUsance() + " intr" + sLADataBeanILC.getIntRate());
//                        String path1 = SingletonClass.sysFilePath + "service_Agreement_" + sLADataBeanILC.getCompanyName().replace(" ", "_") + "_" + sLADataBeanILC.getScheduleBank().replace(" ", "_") + ".pdf";
                            path1 = SingletonClass.sysFilePath + "service_Agreement_" + sLADataBeanILC.getCompanyName().replace(" ", "_") + ".pdf";
                            //slaPdfFilePathList.add(path1);
                            System.out.println(";sLADataBeanILC.getHndlingChrge();::::::" + sLADataBeanILC.getHndlingChrge());

                            emailText = emailText + "<br><br><html><body><br>ILC Details:<br><br>"
                                    + "<table border=\"1\">"
                                    //                            + "<tr><th>Particulars</th><th>Terms</th></tr>"
                                    + "<tr><td><b>Negotiating Bank                   <b></td><td>" + sLADataBeanILC.getScheduleBank() + "</td></tr>"
                                    + "<tr><td><b>Our Charges                          <b></td><td>" + sLADataBeanILC.getOurChrge() + "</td></tr>"
                                    + "<tr><td><b>Amount per Month                <b></td><td>" + SingletonClass.expBusiness + "</td></tr>"
                                    + "<tr><td><b>Usance                                   <b></td><td>" + sLADataBeanILC.getUsance() + " days</td></tr>"
                                    + "<tr><td><b>Interest rate*                        <b></td><td>Best Rate as per the market, presently around " + sLADataBeanILC.getIntRate() + " pa.</td></tr>"
                                    + "<tr><td><b>Handling Charges                  <b></td><td>" + sLADataBeanILC.getHndlingChrge() + " per bill of exchange. (+S.Tax)</td></tr>"
                                    + "<tr><td><b>Margin                                   <b></td><td>" + sLADataBeanILC.getMargin() + " days over the usance of the L.C.</td></tr>"
                                    + "</table>";
                            isSelectedILC = true;
                            break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                for (int listCount = 0; listCount < sLADataBeansFLCList.size(); listCount++) {

                    try {
                        sLADataBeanFLC = sLADataBeansFLCList.get(listCount);
                        if (!sLADataBeanFLC.getApprovedBankCheck().equals("true") && sLADataBeansFLCList.size() != 1) {
                            isSelectedFLC = false;
                        } else {
                            System.out.println("sLADataBean: " + sLADataBeanFLC.getUsance() + " intr" + sLADataBeanFLC.getIntRate());
//                        String path1 = SingletonClass.sysFilePath + "service_Agreement_" + sLADataBeanFLC.getCompanyName().replace(" ", "_") + "_" + sLADataBeanFLC.getScheduleBank().replace(" ", "_") + ".pdf";
                            path1 = SingletonClass.sysFilePath + "service_Agreement_" + sLADataBeanFLC.getCompanyName().replace(" ", "_") + ".pdf";

                            //new ServiceAgreementPdf(sLADataBeanILC, sLADataBeanFLC, path1);
                            //slaPdfFilePathList.add(path1);
                            emailText = emailText + "<br><br><html><body><br><b>FLC Details:<b><br><br>"
                                    + "<table border=\"1\">"
                                    //                            + "<tr><th>Particulars</th><th>Terms</th></tr>"
                                    + "<tr><td><b>Negotiating Bank                  <b></td><td>" + sLADataBeanFLC.getScheduleBank() + "</td></tr>"
                                    + "<tr><td><b>Our Charges                          <b></td><td>" + sLADataBeanFLC.getOurChrge() + "</td></tr>"
                                    + "<tr><td><b>Amount per Month                <b></td><td>" + SingletonClass.expBusiness + "</td></tr>"
                                    + "<tr><td><b>Usance                                   <b></td><td>" + sLADataBeanFLC.getUsance() + " days</td></tr>"
                                    + "<tr><td><b>Interest rate*                        <b></td><td>Best Rate as per the market, presently around " + sLADataBeanFLC.getIntRate() + " pa.</td></tr>"
                                    + "<tr><td><b>Handling Charges                  <b></td><td>" + sLADataBeanFLC.getHndlingChrge() + " per bill of exchange. (+S.Tax)</td></tr>"
                                    + "<tr><td><b>Margin                                   <b></td><td>" + sLADataBeanFLC.getMargin() + " days over the usance of the L.C.</td></tr>"
                                    + "</table>";
                            isSelectedFLC = true;
                            break;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (isSelectedILC && isSelectedFLC) {
                    System.out.println("*************************************" + sLADataBeanILC);
                    new PreliminaryOfferPdf(sLADataBeanILC, sLADataBeanFLC, path1);
                    slaPdfFilePathList.add(path1);
                    emailText = emailText + "<br><br><br>Regards,<br>" + SingletonClass.loginUserName;
                    isSelected = true;
                } 
            }
//              Saving SLA on Server---------------
//            new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverClientDocPath, SingletonClass.clientCompanyName.toString().replace(" ", "_"), slaPdfFilePathList);
            if (slaPdfFilePathList.size() > 0) {
                new DownloadUploadFile().setUploadFTPFile(SingletonClass.serverClientDocPath, SingletonClass.clientCompanyName.toString().replace(" ", "_"), "Proposal", slaPdfFilePathList);
            }
//              saving SLA details In DataBase----------------
            for (int i = 0; i < slaPdfFilePathList.size(); i++) {
                File filee = new File(slaPdfFilePathList.get(i));
                if (!(filee.getName().contains("FSA")) && !(filee.getName().contains("Company_Profile"))) {
                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   " + SingletonClass.designation + "999999999999999" + filee.getName().replace(".pdf", ""));
                    if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") && filee.getName().replace(".pdf", "").contains("service_Agreement")) {
                    } else {
                        String docServerPath = SingletonClass.serverClientDocPath + SingletonClass.clientCompanyName.toString().replace(" ", "_") + "/Proposal/" + filee.getName();

                        StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveClientDocs&").append(SingletonClass.docsName).append("=").append(URLEncoder.encode(filee.getName().replace(".pdf", ""))).append("&").append(SingletonClass.docsType).append("=").append("Required").append("&").append(SingletonClass.docsPath).append("=").append(URLEncoder.encode(docServerPath)).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName)).append("&").append(SingletonClass.userName).append("=").append(email).append("&").append("status").append("=").append("Pending").append("&").append("dirName").append("=").append("Proposal").append("&txnNo=").append(SingletonClass.txIdEdit);
                        String url1 = buffer1.toString();
                        String encodedUrl = url1.replace(" ", "%20");
                        System.out.println("doc upload url is " + encodedUrl);
                        String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);
                        if (urlStatus1.equalsIgnoreCase("invalid")) {
                            System.out.println("file uploaded but path not saved.....");
                        }
                    }
                }
            }
        }
        sLADataBeansILCList = null;
        sLADataBeansFLCList = null;
        return isSelected;
    }

    // validate and get ilc proposal details table data
    public boolean validateAndGetILCTable(List<String> ilcURLList) {
        // get the total row count and then iterate the total rows data for saving the details
        boolean isFilled = true;
        boolean isRowAdd = false;
        DefaultTableModel model = (DefaultTableModel) ilcBankTable.getModel();
        int r = model.getRowCount();
        boolean isSelect = false;
        for (int c = 0; c < r; c++) {
            if(r==1 ){
                isSelect = true;
                break;
            }
            if (r > 1 && (ilcBankTable.getValueAt(c, 11) != null && ilcBankTable.getValueAt(c, 11).toString().equals("true"))) {
                isSelect = true;
                break;
            }
        }
        if (r > 0) {
            if (isSelect) {
                for (int c = 0; c < r; c++) {
                    System.out.println("row countiiiiiiiiii before click: " + ilcBankTable.getValueAt(c, 11));
                    isRowAdd = true;
                    if (ilcBankTable.getValueAt(c, 0) == null || ilcBankTable.getValueAt(c, 0).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please select the bank name.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 1) == null || ilcBankTable.getValueAt(c, 1).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the amount.", "", 0);
                        isFilled = false;
                        break;
                    } else if ((ilcBankTable.getValueAt(c, 6) == null || ilcBankTable.getValueAt(c, 6).toString().equals("")) && SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                        System.out.println("++++++++++++++++++++ " + ilcBankTable.getValueAt(c, 6));
                        JOptionPane.showMessageDialog(null, "Please fill the Sanction limit.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 9) != null && "Req-PreDisc".equalsIgnoreCase(ilcBankTable.getValueAt(c, 9).toString().trim()) && ilcBankTable.getValueAt(c, 8) == null) {
                        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*******************************");
                        JOptionPane.showMessageDialog(null, "Please fill the visit date.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 2) == null || ilcBankTable.getValueAt(c, 2).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the usance days.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 3) == null || ilcBankTable.getValueAt(c, 3).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the interst rate.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 4) == null || ilcBankTable.getValueAt(c, 4).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the handling charges.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 5) == null || ilcBankTable.getValueAt(c, 5).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the margin days.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 10) == null || ilcBankTable.getValueAt(c, 10).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the Limit Type.", "", 0);
                        isFilled = false;
                        break;
                    } else if (ilcBankTable.getValueAt(c, 6) != null && !isFloat(ilcBankTable.getValueAt(c, 6).toString())) {
                        JOptionPane.showMessageDialog(null, "invalid actual limit.", "", 0);
                        isFilled = false;
                        break;
                    }
                }
            } else {
                isFilled = false;
                JOptionPane.showMessageDialog(null, "Please Select the bank to send the SLA.", "", 0);
            }
            // call data fetching method if and only if all table row data is filled
            if (isFilled) {
                getIlcTableData(r, ilcURLList);
            }
        } else {
            isRowAdd = false;
            JOptionPane.showMessageDialog(null, "Please add ILC proposal details.", "", 0);
        }
        if (isRowAdd && isFilled) {
            return true;
        } else {
            return false;
        }
    }

// validate and get flc proposal details table data
    public boolean validateAndGetFLCTable(List<String> flcURLList) {
        // get the total row count and then iterate the total rows data for saving the details
        boolean isFilled = true;
        boolean isRowAdd = false;
        DefaultTableModel model = (DefaultTableModel) flcBankTable.getModel();
        int r = model.getRowCount();
        System.out.println("row count before click in method validateAndGetFLCTable(): " + r);

        boolean isSelect = false;
        for (int c = 0; c < r; c++) {      
            if(r==1 ){
                isSelect = true;
                break;
            }
            if (r > 1 && (flcBankTable.getValueAt(c, 11) != null && flcBankTable.getValueAt(c, 11).toString().equals("true"))) {
                isSelect = true;
                break;
            }
        }

        if (r > 0) {
            if (isSelect) {
                for (int c = 0; c < r; c++) {
                    isRowAdd = true;
                    if (flcBankTable.getValueAt(c, 0) == null || flcBankTable.getValueAt(c, 0).toString().equals("")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please select the bank name.", "", 0);
                    } else if (flcBankTable.getValueAt(c, 1) == null || flcBankTable.getValueAt(c, 1).toString().equals("")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please fill the amount.", "", 0);
                    } else if ((flcBankTable.getValueAt(c, 6) == null || flcBankTable.getValueAt(c, 6).toString().equals("")) && SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please fill the Sanction limit.", "", 0);

                    } else if (flcBankTable.getValueAt(c, 9) != null && "Req-PreDisc".equalsIgnoreCase(flcBankTable.getValueAt(c, 9).toString().trim()) && flcBankTable.getValueAt(c, 8) == null) {
                        JOptionPane.showMessageDialog(null, "Please fill the visit date.", "", 0);
                        isFilled = false;
                    } else if (flcBankTable.getValueAt(c, 2) == null || flcBankTable.getValueAt(c, 2).toString().equals("")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please fill the usance days.", "", 0);
                    } else if (flcBankTable.getValueAt(c, 3) == null || flcBankTable.getValueAt(c, 3).toString().equals("")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please fill the interst rate.", "", 0);
                    } else if (flcBankTable.getValueAt(c, 4) == null || flcBankTable.getValueAt(c, 4).toString().equals("")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please fill the handling charges.", "", 0);
                    } else if (flcBankTable.getValueAt(c, 5) == null || flcBankTable.getValueAt(c, 5).toString().equals("")) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "Please fill the margin days.", "", 0);
                    } else if (flcBankTable.getValueAt(c, 10) == null || flcBankTable.getValueAt(c, 10).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the Limit Type.", "", 0);
                        isFilled = false;
                        break;
                    } else if (flcBankTable.getValueAt(c, 6) != null && !isFloat(flcBankTable.getValueAt(c, 6).toString())) {
                        isFilled = false;
                        JOptionPane.showMessageDialog(null, "invalid actual limit.", "", 0);
                    }
                }
            } else {
                isFilled = false;
                JOptionPane.showMessageDialog(null, "Please Select the bank to send the SLA.", "", 0);
            }
            // call data fetching method if and only if all table row data is filled
            if (isFilled) {
                getFlcTableData(r, flcURLList);
            }
        } else {
            isRowAdd = false;
            JOptionPane.showMessageDialog(null, "Please add FLC proposal details.", "", 0);
        }
        if (isRowAdd && isFilled) {
            return true;
        } else {
            return false;
        }
    }

// get ilc table data
    public boolean getIlcTableData(int r, List<String> ilcURLList) {
        boolean isAddUrl = false;
        String sanctionDate = "";
        String visitDate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat newDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            for (int c = 0; c < r; c++) {

                // add data in bean for SLA pdf
                SLADataBean sLADataBean = new SLADataBean();
                sLADataBean.setCompanyName(SingletonClass.clientCompanyName);
                sLADataBean.setScheduleBank(ilcBankTable.getValueAt(c, 0).toString());
                
                
                sLADataBean.setAmntPerMnth(ilcBankTable.getValueAt(c, 1).toString());
                sLADataBean.setUsance(ilcBankTable.getValueAt(c, 2).toString());
                sLADataBean.setIntRate(ilcBankTable.getValueAt(c, 3).toString());
                sLADataBean.setOurChrgeType(ilcOurChargeCombo.getSelectedItem().toString());
                sLADataBean.setExpBusiness(SingletonClass.expBusiness);

                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                           System.out.println(" getIlcTableData " + c);
                    sLADataBean.setOurChrge(ilcBankTable.getValueAt(c, 14).toString());
                } else {
                    sLADataBean.setOurChrge(ilcOurChargeTextF.getText());
                }
                sLADataBean.setHndlingChrge(ilcBankTable.getValueAt(c, 4).toString());
                sLADataBean.setMargin(ilcBankTable.getValueAt(c, 5).toString());
                sLADataBean.setIsFlc(false);
                if (ilcBankTable.getValueAt(c, 11) != null) {
                    sLADataBean.setApprovedBankCheck(ilcBankTable.getValueAt(c, 11).toString());
                } else {
                    sLADataBean.setApprovedBankCheck("");
                }
                sLADataBean.setFrgnIntRate("");
                sLADataBean.setProfileInfo("");
                sLADataBean.setSolutionInfo("");
                sLADataBeansILCList.add(sLADataBean);

// add value in url for calling
                StringBuffer urlBuffer = new StringBuffer();
                urlBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveProposalDetails").append("&").append("lcType=ILC").append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit).append("&").append(SingletonClass.userName).append("=").append(email);

                urlBuffer.append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName));

                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                    urlBuffer.append("&").append(SingletonClass.subjToMinAmnt).append("=").append(subToMnmAmount);
                    urlBuffer.append("&").append(SingletonClass.ourCharges).append("=").append(ilcBankTable.getValueAt(c, 14).toString());
                } else {
                    urlBuffer.append("&").append(SingletonClass.ourCharges).append("=").append(ilcOurChargeTextF.getText());
                    urlBuffer.append("&").append(SingletonClass.subjToMinAmnt).append("=").append(subMinINR.getText());
                }
                urlBuffer.append("&").append(SingletonClass.proposalNo).append("=").append(ilcBankTable.getValueAt(c, 15).toString());
                urlBuffer.append("&").append(SingletonClass.limitType).append("=").append(ilcBankTable.getValueAt(c, 10).toString());



                urlBuffer.append("&").append(SingletonClass.chargeType).append("=").append(URLEncoder.encode(ilcOurChargeCombo.getSelectedItem().toString()));
                urlBuffer.append("&").append(SingletonClass.bankName).append("=").append(ilcBankTable.getValueAt(c, 0).toString());
                
 double proLimit = 0.0;
                if(ilcBankTable.getValueAt(c, 1) !=null && !"".equals(ilcBankTable.getValueAt(c, 1).toString()))
                {
                   proLimit =  new Double(ilcBankTable.getValueAt(c, 1).toString()).doubleValue()*10000000;
                }
                
                urlBuffer.append("&").append(SingletonClass.amount).append("=").append(proLimit);
                try {
                    
                double actuLimit = 0.0;
                if(ilcBankTable.getValueAt(c, 6) !=null && !"".equals(ilcBankTable.getValueAt(c, 6).toString()))
                {
                   actuLimit =  new Double(ilcBankTable.getValueAt(c, 6).toString()).doubleValue()*10000000;
                }
                
                    urlBuffer.append("&").append(SingletonClass.actualLimit).append("=").append(ilcBankTable.getValueAt(c, 6).toString());
                    if (ilcBankTable.getValueAt(c, 7) != null) {
                        System.out.println("flcBankTable.getValueAt(c, 7) ++++++++++++++++++++++++++++++ not null" + ilcBankTable.getValueAt(c, 7));
                        if (isddmmyyyyDateFormat(ilcBankTable.getValueAt(c, 7).toString())) {
                            try {
                                System.out.println("=======================");
                                sanctionDate = dateFormat.format(dateFormat.parse(ilcBankTable.getValueAt(c, 7).toString()));
                                urlBuffer.append("&").append(SingletonClass.sanctionDate).append("=").append(sanctionDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("else part===========================");
                            try {
                                sanctionDate = dateFormat.format(newDateFormat.parse(ilcBankTable.getValueAt(c, 7).toString()));
                                urlBuffer.append("&").append(SingletonClass.sanctionDate).append("=").append(sanctionDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if (ilcBankTable.getValueAt(c, 8) != null && !"".equals(ilcBankTable.getValueAt(c, 8))) {
                        System.out.println("flcBankTable.getValueAt(c, 7) ++++++++++++++++++++++++++++++ not null" + ilcBankTable.getValueAt(c, 7));
                        if (isddmmyyyyDateFormat(ilcBankTable.getValueAt(c, 8).toString())) {
                            try {
                                System.out.println("=======================");
                                visitDate = dateFormat.format(dateFormat.parse(ilcBankTable.getValueAt(c, 8).toString()));
                                urlBuffer.append("&").append("visitDate=").append(visitDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("else part===========================");
                            try {
                                visitDate = dateFormat.format(newDateFormat.parse(ilcBankTable.getValueAt(c, 8).toString()));
                                urlBuffer.append("&").append("visitDate=").append(visitDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } catch (Exception ex) {
                }
                urlBuffer.append("&").append(SingletonClass.usance).append("=").append(URLEncoder.encode(ilcBankTable.getValueAt(c, 2).toString()));
                urlBuffer.append("&").append(SingletonClass.interestRate).append("=").append(URLEncoder.encode(ilcBankTable.getValueAt(c, 3).toString()));
                urlBuffer.append("&").append(SingletonClass.hndlCharges).append("=").append(URLEncoder.encode(ilcBankTable.getValueAt(c, 4).toString()));
                urlBuffer.append("&").append(SingletonClass.margin).append("=").append(URLEncoder.encode(ilcBankTable.getValueAt(c, 5).toString()));
                String isChecked = "";

//                if (!ilcBankTable.getValueAt(c, 0).toString().equalsIgnoreCase("any bank")) {
                if (ilcBankTable.getValueAt(c, 11) != null && ilcBankTable.getValueAt(c, 11).toString().equals("true")) {
                    isChecked = "Y";
                } else {
                    isChecked = "N";
                }
//                } else {
//                    isChecked = "A";
//                }
                urlBuffer.append("&").append(SingletonClass.lcStatus).append("=").append(isChecked);
                isAddUrl = true;
                ilcURLList.add(urlBuffer.toString());
            }
        } catch (Exception ex) {
            isAddUrl = false;
            ex.printStackTrace();
        }
        return isAddUrl;
    }

//get flc table data
    public boolean getFlcTableData(int r, List<String> flcURLList) {
        boolean isAddUrl = false;
        String sanctionDate = "";
        String visitDate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat newDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            for (int c = 0; c < r; c++) {

                // add data in bean for SLA pdf
                SLADataBean sLADataBean = new SLADataBean();
                sLADataBean.setCompanyName(SingletonClass.clientCompanyName);
                sLADataBean.setScheduleBank(flcBankTable.getValueAt(c, 0).toString());
                sLADataBean.setAmntPerMnth(flcBankTable.getValueAt(c, 1).toString());
                sLADataBean.setUsance(flcBankTable.getValueAt(c, 2).toString());
                sLADataBean.setIntRate(flcBankTable.getValueAt(c, 3).toString());
                sLADataBean.setOurChrgeType(flcOurChargeCombo.getSelectedItem().toString());
                sLADataBean.setExpBusiness(SingletonClass.expBusiness);


                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                    sLADataBean.setOurChrge(flcBankTable.getValueAt(c, 14).toString());
                } else {
                    sLADataBean.setOurChrge(flcOurChargeTextF.getText());
                }
                sLADataBean.setHndlingChrge(flcBankTable.getValueAt(c, 4).toString());
                sLADataBean.setMargin(flcBankTable.getValueAt(c, 5).toString());
                sLADataBean.setIsFlc(true);
                if (flcBankTable.getValueAt(c, 11) != null) {
                    sLADataBean.setApprovedBankCheck(flcBankTable.getValueAt(c, 11).toString());
                } else {
                    sLADataBean.setApprovedBankCheck("");
                }


                sLADataBean.setFrgnIntRate("");
                sLADataBean.setProfileInfo("");
                sLADataBean.setSolutionInfo("");
                sLADataBeansFLCList.add(sLADataBean);
// add value in url for calling
                StringBuffer urlBuffer = new StringBuffer();
                urlBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveProposalDetails").append("&").append("lcType=FLC").append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit).append("&").append(SingletonClass.userName).append("=").append(email);

                urlBuffer.append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName));
                urlBuffer.append("&").append(SingletonClass.ourCharges).append("=").append(flcOurChargeTextF.getText());

                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
                    urlBuffer.append("&").append(SingletonClass.ourCharges).append("=").append(flcBankTable.getValueAt(c, 14).toString());
                } else {
                    urlBuffer.append("&").append(SingletonClass.ourCharges).append("=").append(flcOurChargeTextF.getText());
                }
                urlBuffer.append("&").append(SingletonClass.proposalNo).append("=").append(flcBankTable.getValueAt(c, 15).toString());
                urlBuffer.append("&").append(SingletonClass.limitType).append("=").append(flcBankTable.getValueAt(c, 10).toString());
                urlBuffer.append("&").append(SingletonClass.chargeType).append("=").append(URLEncoder.encode(flcOurChargeCombo.getSelectedItem().toString()));
                urlBuffer.append("&").append(SingletonClass.bankName).append("=").append(flcBankTable.getValueAt(c, 0).toString());
                urlBuffer.append("&").append(SingletonClass.amount).append("=").append(flcBankTable.getValueAt(c, 1).toString());
                try {
                    if (flcBankTable.getValueAt(c, 6) == null) {
                        urlBuffer.append("&").append(SingletonClass.actualLimit).append("=").append("");
                    } else {
                        urlBuffer.append("&").append(SingletonClass.actualLimit).append("=").append(flcBankTable.getValueAt(c, 6).toString());
                    }

                    if (flcBankTable.getValueAt(c, 7) != null) {
                        System.out.println("flcBankTable.getValueAt(c, 7) ++++++++++++++++++++++++++++++ not null" + flcBankTable.getValueAt(c, 7));
                        if (isddmmyyyyDateFormat(flcBankTable.getValueAt(c, 7).toString())) {
                            try {
                                System.out.println("=======================");
                                sanctionDate = dateFormat.format(dateFormat.parse(flcBankTable.getValueAt(c, 7).toString()));
                                urlBuffer.append("&").append(SingletonClass.sanctionDate).append("=").append(sanctionDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("else part===========================");
                            try {
                                sanctionDate = dateFormat.format(newDateFormat.parse(flcBankTable.getValueAt(c, 7).toString()));
                                urlBuffer.append("&").append(SingletonClass.sanctionDate).append("=").append(sanctionDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }


                    if (flcBankTable.getValueAt(c, 8) != null && !"".equals(flcBankTable.getValueAt(c, 8))) {
                        System.out.println("flcBankTable.getValueAt(c, 7) ++++++++++++++++++++++++++++++ not null" + flcBankTable.getValueAt(c, 7));
                        if (isddmmyyyyDateFormat(flcBankTable.getValueAt(c, 8).toString())) {
                            try {
                                System.out.println("=======================");
                                visitDate = dateFormat.format(dateFormat.parse(flcBankTable.getValueAt(c, 8).toString()));
                                urlBuffer.append("&").append("visitDate=").append(visitDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            System.out.println("else part===========================");
                            try {
                                visitDate = dateFormat.format(newDateFormat.parse(flcBankTable.getValueAt(c, 8).toString()));
                                urlBuffer.append("&").append("visitDate=").append(visitDate.toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } catch (Exception ex) {
                }
                urlBuffer.append("&").append(SingletonClass.usance).append("=").append(URLEncoder.encode(flcBankTable.getValueAt(c, 2).toString()));
                urlBuffer.append("&").append(SingletonClass.interestRate).append("=").append(URLEncoder.encode(flcBankTable.getValueAt(c, 3).toString()));
                urlBuffer.append("&").append(SingletonClass.hndlCharges).append("=").append(URLEncoder.encode(flcBankTable.getValueAt(c, 4).toString()));
                urlBuffer.append("&").append(SingletonClass.margin).append("=").append(URLEncoder.encode(flcBankTable.getValueAt(c, 5).toString()));

                String isChecked = "";
                if (flcBankTable.getValueAt(c, 11) != null && flcBankTable.getValueAt(c, 11).toString().equals("true")) {
                    isChecked = "Y";
                } else {
                    isChecked = "N";
                }
                urlBuffer.append("&").append(SingletonClass.lcStatus).append("=").append(isChecked);
                isAddUrl = true;
                flcURLList.add(urlBuffer.toString());
            }
        } catch (Exception ex) {
            isAddUrl = false;
            ex.printStackTrace();
        }
        return isAddUrl;
    }

// validate ilc handling charge
    public boolean validateOnSaveIlcHandling() {
        boolean isFilled = false;
        if (ilcOurChargeTextF == null || ilcOurChargeTextF.getText().equals("")) {
            isFilled = false;
        } else if ("Select Rate".equals(ilcOurChargeCombo.getSelectedItem().toString())) {
            isFilled = false;
        } else {
            isFilled = true;
        }
        return isFilled;
    }

// validate flc handling charge
    public boolean validateOnSaveFlcHandling() {
        boolean isFilled = false;
        if (flcOurChargeTextF == null || flcOurChargeTextF.getText().equals("")) {
            isFilled = false;
        } else if ("Select Rate".equals(flcOurChargeCombo.getSelectedItem().toString())) {
            isFilled = false;
        } else {
            isFilled = true;
        }
        return isFilled;
    }

// validate ilc handling charge
    public boolean checkIlcHandlingCharge() {
        boolean isFilled = false;
        if (ilcOurChargeTextF == null || ilcOurChargeTextF.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill our charges.", "", 0);
        } else if ("Select Rate".equals(ilcOurChargeCombo.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Please select the type of our charges.", "", 0);
        } else {
            isFilled = true;
        }
        return isFilled;
    }

// validate flc handling charge
    public boolean checkFlcHandlingCharge() {
        boolean isFilled = false;
        if (flcOurChargeTextF == null || flcOurChargeTextF.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill our charges.", "", 0);
        } else if ("Select Rate".equals(flcOurChargeCombo.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Please select the type of our charges.", "", 0);
        } else {
            isFilled = true;
        }
        return isFilled;
    }

    // add a row in ilc table if and only if handling charges are filled and combox of handling charges is selected.
    private void addBankILCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBankILCActionPerformed

        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
//            StringBuffer addInfoCheck = new StringBuffer();
////            addInfoCheck.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=getAdditionalDetails&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName));
//            addInfoCheck.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=checkClientDocs&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName)).append("&").append(SingletonClass.txNo).append("=").append(URLEncoder.encode(SingletonClass.txIdEdit));
//            callServerUrl serverUrl12 = new callServerUrl();
//            String addInfoCheckUrl = addInfoCheck.toString().replaceAll(" ", "%20");
//            String response1 = serverUrl12.urlProcessiong(addInfoCheckUrl);
//            if (response1.equalsIgnoreCase("invalid")) {
////                JOptionPane.showMessageDialog(null, "Please save the addition information of the client.", "", 0);
//                JOptionPane.showMessageDialog(null, "Please Upload FSA and Company Profile docs in Document List.", "", 0);
//            } else
            if (!checkmeetingDetailsIsFilled()) {
                JOptionPane.showMessageDialog(null, "Please save the meeting information of the client.", "", 0);
            } else {
                try {
                    if (checkIlcHandlingCharge()) {
                        DefaultTableModel tableModel = (DefaultTableModel) ilcBankTable.getModel();
                        tableModel.insertRow(counterILC, new Object[]{});
                        ilcBankTable.setValueAt("", counterILC, 15);
                        counterILC++;
                    }
                } catch (Exception ex) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
        }
    }//GEN-LAST:event_addBankILCActionPerformed

    public boolean checkmeetingDetailsIsFilled() {
        boolean isMeet = false;
        if (SingletonClass.txTypeEdit.equals("New Solution")) {
            MeetingDetailsParser meetingDetailsParser = null;
            XMLReader xmlReader = null;
            List<MeetingsDetails> meetingList = null;
            StringBuffer meetingUrl = null;
            try {
                meetingUrl = new StringBuffer();

                meetingUrl.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append(SingletonClass.meetingDetailsType).append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit);
                meetingDetailsParser = new MeetingDetailsParser();
                xmlReader = XMLReaderFactory.createXMLReader();
                xmlReader.setContentHandler(meetingDetailsParser);
                xmlReader.setErrorHandler(meetingDetailsParser);
                xmlReader.parse(new InputSource(meetingUrl.toString()));
                meetingList = meetingDetailsParser.MeetingDataList();
                if (meetingList != null && meetingList.size() > 0) {
                    isMeet = true;
                } else {
                    isMeet = false;
                }
            } catch (Exception ex) {
            }
        } else {
            isMeet = true;
        }
        return isMeet;

    }

// add a row in flc table if and only if handling charges are filled and combox of handling charges is selected.
    private void addBankFLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBankFLCActionPerformed

        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
//            StringBuffer addInfoCheck = new StringBuffer();
////            addInfoCheck.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=getAdditionalDetails&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName));
//            addInfoCheck.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=checkClientDocs&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName)).append("&").append(SingletonClass.txNo).append("=").append(URLEncoder.encode(SingletonClass.txIdEdit));
//            callServerUrl serverUrl12 = new callServerUrl();
//            String addInfoCheckUrl = addInfoCheck.toString().replaceAll(" ", "%20");
//            String response1 = serverUrl12.urlProcessiong(addInfoCheckUrl);
//            if (response1.equalsIgnoreCase("invalid")) {
////                JOptionPane.showMessageDialog(null, "Please save the addition information of the client.", "", 0);
//                JOptionPane.showMessageDialog(null, "Please Upload FSA and Company Profile docs in Document List.", "", 0);
//            } else
            if (!checkmeetingDetailsIsFilled()) {
                JOptionPane.showMessageDialog(null, "Please save the meeting information of the client.", "", 0);
            } else {
                try {
                    if (checkFlcHandlingCharge()) {
                        DefaultTableModel tableModel = (DefaultTableModel) flcBankTable.getModel();
                        tableModel.insertRow(counterFLC, new Object[]{});
                        flcBankTable.setValueAt("", counterFLC, 15);
                        counterFLC++;
                    }

                } catch (Exception ex) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
        }
    }//GEN-LAST:event_addBankFLCActionPerformed

    private void ilcBankTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ilcBankTableMouseClicked

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") || SingletonClass.isView) {
        } else {
            // delete the contact row if extra added
            DefaultTableModel tableModel = (DefaultTableModel) ilcBankTable.getModel();
            int row = ilcBankTable.rowAtPoint(evt.getPoint());
            int col = ilcBankTable.columnAtPoint(evt.getPoint());
            StringBuffer deleteBankBuffer = new StringBuffer();

            if (col == 12) {
                try {
                    // check that there is no data entered in table
                    if (ilcBankTable.getValueAt(row, 1) != null && !ilcBankTable.getValueAt(row, 1).equals("")) {
                        deleteBankBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deletetxnProposal").append("&")
                                .append(SingletonClass.userName).append("=").append(email).append("&beneficiary=").append(ilcBankTable.getValueAt(row, 13))
                                .append("&bankName=").append(ilcBankTable.getValueAt(row, 0)).append("&lcType=ilc&").append(SingletonClass.txNo + "=").append(SingletonClass.txIdEdit);
                        callServerUrl serverUrl1 = new callServerUrl();
                        String urlString = deleteBankBuffer.toString().replaceAll(" ", "%20");
                        serverUrl1.urlProcessiong(urlString);
                        urlString = null;
                    }
                    if (row == 0) {
                        tableModel.removeRow(row);
                        ilcOurChargeTextF.setText("");
                        ilcOurChargeCombo.setSelectedItem("Select Rate");
                    } else {
                        tableModel.removeRow(row);
                    }
                    if (counterILC != 0) {
                        counterILC--;
                    }
                    deleteBankBuffer = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (col == 11) {

                DefaultTableModel model = (DefaultTableModel) ilcBankTable.getModel();
                int r = model.getRowCount();
                System.out.println("row count  in check box clieck ilc: " + r);
                if (r > 0) {
                    for (int c = 0; c < r; c++) {
                        if (c != row) {
                            if (ilcBankTable.getValueAt(c, 11) != null) {
                                if ((Boolean) ilcBankTable.getValueAt(c, 11) == true) {
                                    ilcBankTable.setValueAt(false, c, 11);
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_ilcBankTableMouseClicked

    private void flcBankTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_flcBankTableMouseClicked

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") || SingletonClass.isView) {
        } else {

            // delete the contact row if extra added
            DefaultTableModel tableModel = (DefaultTableModel) flcBankTable.getModel();
            int row = flcBankTable.rowAtPoint(evt.getPoint());
            int col = flcBankTable.columnAtPoint(evt.getPoint());
            StringBuffer deleteBankBuffer = new StringBuffer();

            if (col == 12) {
                try {
                    // check that there is no data entered in table
                    if (flcBankTable.getValueAt(row, 1) != null) {
                        deleteBankBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deletetxnProposal").append("&")
                                .append(SingletonClass.userName).append("=").append(email).append("&beneficiary=").append(flcBankTable.getValueAt(row, 13))
                                .append("&bankName=").append(flcBankTable.getValueAt(row, 0)).append("&lcType=flc&").append(SingletonClass.txNo + "=").append(SingletonClass.txIdEdit);
                        callServerUrl serverUrl1 = new callServerUrl();
                        String urlString = deleteBankBuffer.toString().replaceAll(" ", "%20");
                        serverUrl1.urlProcessiong(urlString);
                        urlString = null;
                    }
                    if (row == 0) {
                        tableModel.removeRow(row);
                        flcOurChargeTextF.setText("");
                        flcOurChargeCombo.setSelectedItem("Select Rate");
                    } else {
                        tableModel.removeRow(row);
                    }
                    if (counterFLC != 0) {
                        counterFLC--;
                    }
                    deleteBankBuffer = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (col == 11) {

                DefaultTableModel model = (DefaultTableModel) flcBankTable.getModel();
                int r = model.getRowCount();
                System.out.println("row count in check box clieck flc: " + r);
                if (r > 0) {
                    for (int c = 0; c < r; c++) {
                        if (c != row) {
                            if (flcBankTable.getValueAt(c, 11) != null) {
                                if ((Boolean) flcBankTable.getValueAt(c, 11) == true) {
                                    flcBankTable.setValueAt(false, c, 11);
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_flcBankTableMouseClicked

    private void ilcOurChargeTextFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ilcOurChargeTextFKeyReleased


        callServerUrl serverUrl = new callServerUrl();
        String urlString = new StringBuilder(SingletonClass.httpServerUrl).append(SingletonClass.operaionType)
                .append("=" + "isContactAllDetails" + "&").append("txnNo=")
                .append(SingletonClass.txIdEdit).toString().replaceAll(" ", "%20");
        String resp = serverUrl.urlProcessiong(urlString);
        if (resp.equals("invalid")) {
            ilcOurChargeTextF.setText("");
            JOptionPane.showMessageDialog(null, "Please fill all the details of client contacts.", "", 0);
        } else {

            if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
                boolean isFloat = isFloat(ilcOurChargeTextF.getText());
                System.out.println("ilcHandlingChargeTextF: " + ilcOurChargeTextF.getText() + "   " + isFloat);

                // get the applicant values from UI, and append for URL.
                if (isFloat != true) {
                    JOptionPane.showMessageDialog(null, "invalid charges.", "", 0);
                    ilcOurChargeTextF.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
                ilcOurChargeTextF.setText("");
            }
        }
    }//GEN-LAST:event_ilcOurChargeTextFKeyReleased

    private void flcOurChargeTextFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_flcOurChargeTextFKeyReleased

        callServerUrl serverUrl = new callServerUrl();
        String urlString = new StringBuilder(SingletonClass.httpServerUrl).append(SingletonClass.operaionType)
                .append("=" + "isContactAllDetails" + "&").append("txnNo=")
                .append(SingletonClass.txIdEdit).toString().replaceAll(" ", "%20");
        String resp = serverUrl.urlProcessiong(urlString);
        if (resp.equals("invalid")) {
            flcOurChargeTextF.setText("");
            JOptionPane.showMessageDialog(null, "Please fill all the details of client contacts.", "", 0);
        } else {
            if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
                boolean isFloat = isFloat(flcOurChargeTextF.getText());
                System.out.println("flcHandlingChargeTextF: " + flcOurChargeTextF.getText() + "   " + isFloat);

                // get the applicant values from UI, and append for URL.
                if (isFloat != true) {
                    JOptionPane.showMessageDialog(null, "invalid charges.", "", 0);
                    flcOurChargeTextF.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
                flcOurChargeTextF.setText("");
            }
        }
    }//GEN-LAST:event_flcOurChargeTextFKeyReleased

    private void ilcOurChargeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ilcOurChargeComboActionPerformed
        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
        } else {
            JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
            ilcOurChargeCombo.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ilcOurChargeComboActionPerformed

    private void flcOurChargeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flcOurChargeComboActionPerformed
        if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
        } else {
            JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
            flcOurChargeCombo.setSelectedIndex(0);
        }
    }//GEN-LAST:event_flcOurChargeComboActionPerformed

    private void ilcOurChargeComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ilcOurChargeComboItemStateChanged
        if(ilcOurChargeCombo.getSelectedItem().toString().equals("Flat Rate")){
            subMinINR.setEditable(false);
        }
    }//GEN-LAST:event_ilcOurChargeComboItemStateChanged

    public boolean isFloat(String floatNo) {
        boolean isFloat = false;
        if (floatNo.equals("")) {
            isFloat = true;
        } else {
            try {
                Double.parseDouble(floatNo);
                isFloat = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                isFloat = false;
            }
        }
        return isFloat;
    }

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
//date validation for update

    private boolean isddmmyyyyDateFormat(String dateStr) {
        boolean isDate = false;
        DateFormat sdFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdFormat.parse(dateStr);
            isDate = true;
            sdFormat = null;
        } catch (Exception ex) {
            sdFormat = null;
            isDate = false;
        }
        return isDate;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBankFLC;
    private javax.swing.JButton addBankILC;
    private javax.swing.JTable flcBankTable;
    private javax.swing.JComboBox flcOurChargeCombo;
    private javax.swing.JTextField flcOurChargeTextF;
    private javax.swing.JTable ilcBankTable;
    private javax.swing.JComboBox ilcOurChargeCombo;
    private javax.swing.JTextField ilcOurChargeTextF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton proposalSaveButton;
    private javax.swing.JTextField subMinINR;
    // End of variables declaration//GEN-END:variables
}
