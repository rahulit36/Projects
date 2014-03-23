/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.ftp.bean.MeetingsDetails;
import com.msr.newTx.dao.ProposalViewBean;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
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
 * @since 29 jan 2013
 */
class BankQueriesModel extends DefaultTableModel {

    public BankQueriesModel() {

        super(
                new Object[][]{},
                new String[]{
            "S.No.", "Query Date", "Raised By", "Details", "Status", "Closed On", "Closed By", "Comments", "", ""
        });
    }

    public boolean isCellEditable(int row, int col) {

        DefaultTableModel model = (DefaultTableModel) SingletonClass.bankQueriesTable.getModel();
        int r = model.getRowCount();
        if (r > 0) {
            System.out.println("inside ***$$$$$$$$$$***isCellEditable****$$$$$$$$$$**** "+SingletonClass.bankQueriesTable.getValueAt(row, 9));
            //        "Serial No.", "Query Date", "Raised By", "Details","Status","Closed On","Closed By",""
//            for (int c = 0; c < r; c++) {
            if (SingletonClass.bankQueriesTable.getValueAt(row, 4).toString().equals("Closed") && SingletonClass.bankQueriesTable.getValueAt(row, 9) != null
                    && !"".equals(SingletonClass.bankQueriesTable.getValueAt(row, 9).toString())) {
//                    if (row == c) {
                return false;
//                    }
            } else {
                return true;
            }
//            }
        }

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            if (col == 6 || col == 7) {
                return true;
            } else {
                return false;
            }
        } else if (SingletonClass.isView) {
            return false;
        } else {
            if (col == 10) {
                return false;
            } else {
                return true;
            }
        }
    }
}

public class BankQueriesPanel extends javax.swing.JPanel {

    NewTxPanel newTxPanel = null;
    String email = "";
    JTabbedPane tabbedPane = null;
    MainScreen maiScreen = null;
    int counter = 0;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat newDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//    private int getRow;

    /**
     * Creates new form BankQueriesPanel
     */
    public BankQueriesPanel(String email, String accName, NewTxPanel newTxPanel, JTabbedPane tabbedPane,
            MainScreen maiScreen) {

        this.email = email;
        this.newTxPanel = newTxPanel;
        this.tabbedPane = tabbedPane;
        this.maiScreen = maiScreen;
        initComponents();
        statusType.addItem("New Query");
        statusType.addItem("Not Required");
        statusType.addItem("In Progress");
        statusType.addItem("Required/Pending");
        statusType.addItem("Closed");


        statusType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {


                addCloseOnActionPerformed(evt);
            }
        });
        TableColumn statusCol = bankQueriesTable.getColumnModel().getColumn(4);
        statusCol.setCellEditor(new ComboBoxCellEditor(statusType));
        addDeleteColumn(bankQueriesTable, 8);
        setColumnWidth(bankQueriesTable);
        ParserCaller caller = new ParserCaller();
        List<Map> editViewQuerieList = caller.parseBankQueriesXML(email, SingletonClass.txIdEdit);
        if (SingletonClass.txIdEdit != null) {
            setQueryValues(editViewQuerieList);
        }
        addDateColumn();
        if ("rm".equalsIgnoreCase(SingletonClass.designation)) {
            addQueries.setEnabled(false);
        }


    }

    public void addDateColumn() {

        TableColumn queryDateColumn = bankQueriesTable.getColumnModel().getColumn(1);
        queryDateColumn.setMaxWidth(100);
        queryDateColumn.setMinWidth(100);
        queryDateColumn.setWidth(100);
        TableColumn closedOnColumn = bankQueriesTable.getColumnModel().getColumn(5);
        closedOnColumn.setMaxWidth(100);
        closedOnColumn.setMinWidth(100);
        closedOnColumn.setWidth(100);
//        dateColumn.setMinWidth(130);
        DatePickerCellEditor cellEditor = new DatePickerCellEditor(dateFormat);

        cellEditor.setFormats(dateFormat);
        cellEditor.setClickCountToStart(0);
        queryDateColumn.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(dateFormat), JLabel.LEFT));
        queryDateColumn.setCellEditor(cellEditor);
        closedOnColumn.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(dateFormat), JLabel.LEFT));
        closedOnColumn.setCellEditor(cellEditor);

    }
    // add the delete column to the tables

    private void addDeleteColumn(JTable tableName, int columnIndex) {
        // add delete button at column index postion of all rows
        TableColumn deleteColumn = tableName.getColumnModel().getColumn(columnIndex);
        deleteColumn.setWidth(50);
        deleteColumn.setMaxWidth(50);
        deleteColumn.setMinWidth(50);
        deleteColumn.setResizable(false);
        deleteColumn.setCellRenderer(new IconTableCellRenderer("Delete"));
        // set the minimum width  of that colum
        TableColumn queryIdColumn = tableName.getColumnModel().getColumn(columnIndex + 1);
        queryIdColumn.setWidth(0);
        queryIdColumn.setMaxWidth(0);
        queryIdColumn.setMinWidth(0);
        queryIdColumn.setResizable(false);
    }

//    set width of columns of table
    private void setColumnWidth(JTable tableName) {
        TableColumn snoCol = tableName.getColumnModel().getColumn(0);
        snoCol.setWidth(60);
        snoCol.setMaxWidth(60);
        snoCol.setMinWidth(10);


        TableColumn col2 = tableName.getColumnModel().getColumn(2);
        col2.setWidth(140);
        col2.setMaxWidth(150);
        col2.setMinWidth(100);
//        TableColumn col3 = tableName.getColumnModel().getColumn(3);
//        col3.setWidth(200);
        TableColumn col4 = tableName.getColumnModel().getColumn(4);
        col4.setWidth(150);
        col4.setMaxWidth(150);
        col4.setMinWidth(100);
//        TableColumn col73 = tableName.getColumnModel().getColumn(7);
//        col73.setWidth(200);
        TableColumn col6 = tableName.getColumnModel().getColumn(6);
        col6.setWidth(140);
        col6.setMaxWidth(150);
        col6.setMinWidth(100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bankQueriesSaveButton = new javax.swing.JButton();
        addQueries = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bankQueriesTable = new javax.swing.JTable();

        bankQueriesSaveButton.setText("Save");
        bankQueriesSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bankQueriesSaveButtonActionPerformed(evt);
            }
        });

        addQueries.setText("Add Query");
        addQueries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQueriesActionPerformed(evt);
            }
        });

        statusType = new javax.swing.JComboBox();
        JTableHeader header = bankQueriesTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(bankQueriesTable.getWidth(),35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        bankQueriesTable.setRowHeight(30);
        SingletonClass.bankQueriesTable = bankQueriesTable;
        bankQueriesTable.setModel(new BankQueriesModel());
        // set the minimum width  of that colum
        TableColumn queryIdColumn = bankQueriesTable.getColumnModel().getColumn(9);
        queryIdColumn.setWidth(0);
        queryIdColumn.setMaxWidth(0);
        queryIdColumn.setMinWidth(0);
        queryIdColumn.setResizable(false);

        TableColumn deleteColumn = bankQueriesTable.getColumnModel().getColumn(8);
        deleteColumn.setWidth(50);
        deleteColumn.setMaxWidth(50);
        deleteColumn.setMinWidth(50);
        deleteColumn.setResizable(false);
        bankQueriesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bankQueriesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bankQueriesTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 225, Short.MAX_VALUE)
                        .addComponent(addQueries)
                        .addGap(27, 27, 27)
                        .addComponent(bankQueriesSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bankQueriesSaveButton)
                    .addComponent(addQueries))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    // set the values in table of bank queries
    public void setQueryValues(List<Map> queryList) {
        DefaultTableModel ilcTableModel = (DefaultTableModel) bankQueriesTable.getModel();
        try{
        if (queryList.size() > 0) {
            Map<String, String> ilcMap = null;
            for (int listCount = 0; listCount < queryList.size(); listCount++) {
                ilcTableModel.insertRow(listCount, new Object[]{});
                ilcMap = queryList.get(listCount);
                bankQueriesTable.setValueAt(listCount + 1, listCount, 0);


                bankQueriesTable.setValueAt(ilcMap.get("RaisedOn"), listCount, 1);
                bankQueriesTable.setValueAt(ilcMap.get("raisedBy"), listCount, 2);
                bankQueriesTable.setValueAt(ilcMap.get("queryText"), listCount, 3);
                bankQueriesTable.setValueAt(ilcMap.get("status"), listCount, 4);
                bankQueriesTable.setValueAt(ilcMap.get("closedOn"), listCount, 5);
                bankQueriesTable.setValueAt(ilcMap.get("closedBy"), listCount, 6);
                bankQueriesTable.setValueAt(ilcMap.get("queryComments"), listCount, 7);
                bankQueriesTable.setValueAt(ilcMap.get("queryNo"), listCount, 9);
                System.out.println(" counter " + listCount);
                counter++;
            }
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void addCloseOnActionPerformed(java.awt.event.ActionEvent evt) {


        if (statusType.getSelectedItem().toString().equalsIgnoreCase("Closed")) {
            int getRow = bankQueriesTable.getEditingRow();
            System.out.println("print getrow value " + getRow);
            bankQueriesTable.setValueAt(dateFormat.format(new Date()), getRow, 5);
            bankQueriesTable.setValueAt(SingletonClass.loginUserName, getRow, 6);
//        }else if(!statusType.getSelectedItem().toString().equalsIgnoreCase("Closed")){
//            int getRow = bankQueriesTable.getEditingRow();
//            bankQueriesTable.setValueAt(null, getRow, 5);
        }
    }
    private void bankQueriesSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bankQueriesSaveButtonActionPerformed
        try {
            boolean iAllRowSave = true;
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DefaultTableModel model = (DefaultTableModel) bankQueriesTable.getModel();
            int r = model.getRowCount();
            System.out.println("row count before click: " + r);
            if (r > 0) {
                System.out.println("inside if*************************");
                //        "Serial No.", "Query Date", "Raised By", "Details","Status","Closed On","Closed By",""
                for (int c = 0; c < r; c++) {
//                if (bankQueriesTable.getValueAt(c, 0) == null || bankQueriesTable.getValueAt(c, 0).toString().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Please fill the serial no.", "", 0);
//                    isFilled = false;
//                } else 
                    System.out.println("inside for'************************** " + c);
                    if (bankQueriesTable.getValueAt(c, 1) == null || bankQueriesTable.getValueAt(c, 1).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the query date.", "", 0);
                        iAllRowSave = false;
                    } else if (bankQueriesTable.getValueAt(c, 2) == null || bankQueriesTable.getValueAt(c, 2).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the raised by.", "", 0);
                        iAllRowSave = false;
                    } else if (bankQueriesTable.getValueAt(c, 3) == null || bankQueriesTable.getValueAt(c, 3).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the details.", "", 0);
                        iAllRowSave = false;
                    } else if (bankQueriesTable.getValueAt(c, 4) == null || bankQueriesTable.getValueAt(c, 4).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the status.", "", 0);
                        iAllRowSave = false;
                    } else if (bankQueriesTable.getValueAt(c, 4).toString().equalsIgnoreCase("closed")) {
                        if (bankQueriesTable.getValueAt(c, 5) == null || bankQueriesTable.getValueAt(c, 5).toString().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please fill the closed on.", "", 0);
                            iAllRowSave = false;
                        } else if (bankQueriesTable.getValueAt(c, 6) == null || bankQueriesTable.getValueAt(c, 6).toString().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please fill the closed by.", "", 0);
                            iAllRowSave = false;
                        } else {
                            iAllRowSave = saveValues(c, iAllRowSave);
                        }
                    } else {
                        iAllRowSave = saveValues(c, iAllRowSave);
                    }
                }
            }
            if (iAllRowSave) {
                newTxPanel.refreshScreen(this);
                tabbedPane.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Bank Queries Saved Successfully.", "", 1);

            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception ex) {
            ex.printStackTrace();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_bankQueriesSaveButtonActionPerformed
    public boolean saveValues(int c, boolean iAllRow) {
        boolean iAllRowSave = iAllRow;
        try {
            // add value in url for calling
            StringBuffer urlBuffer = new StringBuffer();
            urlBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=saveBankQueries").append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit).append("&").append(SingletonClass.userName).append("=").append(email);

            urlBuffer.append("&").append(SingletonClass.raisedBy).append("=").append(bankQueriesTable.getValueAt(c, 2).toString());
            urlBuffer.append("&").append(SingletonClass.queryText).append("=").append(bankQueriesTable.getValueAt(c, 3).toString());
            urlBuffer.append("&").append(SingletonClass.queryStatus).append("=").append(bankQueriesTable.getValueAt(c, 4).toString());
            if (bankQueriesTable.getValueAt(c, 5) != null) {
                if (isddmmyyyyDateFormat((bankQueriesTable.getValueAt(c, 5).toString()))) {
                    urlBuffer.append("&").append(SingletonClass.closedOn).append("=").append(dateFormat.format(dateFormat.parse(bankQueriesTable.getValueAt(c, 5).toString())));
//                            } else {
//                                urlBuffer.append("&").append(SingletonClass.closedOn).append("=").append(dateFormat.format(newDateFormat.parse(bankQueriesTable.getValueAt(c, 5).toString())));
                }
            }
            if (bankQueriesTable.getValueAt(c, 6) != null) {
                urlBuffer.append("&").append(SingletonClass.closedBy).append("=").append(bankQueriesTable.getValueAt(c, 6).toString());
            } else {
                urlBuffer.append("&").append(SingletonClass.closedBy).append("=").append("");
            }
            if (bankQueriesTable.getValueAt(c, 7) != null) {
                urlBuffer.append("&").append(SingletonClass.queryComments).append("=").append(SingletonClass.replaceEmptySpace(bankQueriesTable.getValueAt(c, 7).toString()));
            } else {
                urlBuffer.append("&").append(SingletonClass.queryComments).append("=").append("");
            }
            if (bankQueriesTable.getValueAt(c, 9) != null) {
                urlBuffer.append("&").append(SingletonClass.queryNo).append("=").append(SingletonClass.replaceEmptySpace(bankQueriesTable.getValueAt(c, 9).toString()));
            } else {
                urlBuffer.append("&").append(SingletonClass.queryNo).append("=").append("");
            }

            String urlForCall = urlBuffer.toString().replaceAll(" ", "%20");


            callServerUrl serverUrl1 = new callServerUrl();
            String response = serverUrl1.urlProcessiong(urlForCall);
            if (response.equalsIgnoreCase("valid")) {
                System.out.println(" counter " + c);
            } else {
                iAllRowSave = false;
                JOptionPane.showMessageDialog(null, "Problem occured! try again later.", "", 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            this.setCursor(Cursor.getDefaultCursor());
        }
        return iAllRowSave;
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
    private void addQueriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQueriesActionPerformed

        try {
            if (SingletonClass.txIdEdit != null && !"".equals(SingletonClass.txIdEdit)) {
                if (checkProposalDetails()) {
                    DefaultTableModel tableModel = (DefaultTableModel) bankQueriesTable.getModel();
                    tableModel.insertRow(counter, new Object[]{});
                    bankQueriesTable.setValueAt(counter + 1, counter, 0);
                    bankQueriesTable.setValueAt(dateFormat.format(new Date()), counter, 1);
                    bankQueriesTable.setValueAt("New Query", counter, 4);
//                    bankQueriesTable.setValueAt("", counter, 9);
                    counter++;
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill the proposal details..", "", 0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select the transaction.", "", 0);
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_addQueriesActionPerformed

    private void bankQueriesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bankQueriesTableMouseClicked

        // delete the contact row if extra added
        DefaultTableModel tableModel = (DefaultTableModel) bankQueriesTable.getModel();
        int row = bankQueriesTable.rowAtPoint(evt.getPoint());
        int col = bankQueriesTable.columnAtPoint(evt.getPoint());
        //        getRow = row;

        System.out.println("table evenmt == " + row + "col==" + col);

        StringBuffer deleteBankBuffer = new StringBuffer();
        if (col == 8) {
            System.out.println("col no ==" + col);
            try {
                // check that there is no data entered in table
                if (bankQueriesTable.getValueAt(row, 9) != null) {
                    JOptionPane.showMessageDialog(null, "Query Can't be Removed.");
                } else {
                    tableModel.removeRow(row);
                    counter--;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //        else if(col ==4){
        //            System.out.println("Selected the combo box"+row +"values changes is =="+bankQueriesTable.getValueAt(row, 4).toString());
        //            if(bankQueriesTable.getValueAt(row, 4).toString().equalsIgnoreCase("Closed")){
        //                bankQueriesTable.setValueAt(dateFormat.format(new Date()), row, 5);
        //            }
        //        }
    }//GEN-LAST:event_bankQueriesTableMouseClicked
    public boolean checkmeetingDetailsIsFilled() {
        boolean isMeet = false;
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
        return isMeet;

    }

    public boolean checkProposalDetails() {
        List<HashMap> queryList = null;
        List<HashMap> flcList = null;
        ParserCaller parserCaller = new ParserCaller();
        ProposalViewBean proposalViewBean = parserCaller.parseProposalDataForView(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "txnProposalXML" + "&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&" + SingletonClass.userName + "=" + email);
        queryList = proposalViewBean.getIlcList();
        flcList = proposalViewBean.getFlcList();

        System.out.println("queryList.size() in bank queries: " + queryList.size());
        System.out.println("flcList.size() in bank queries: " + flcList.size());
        if (queryList.size() > 0 || flcList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addQueries;
    private javax.swing.JButton bankQueriesSaveButton;
    private javax.swing.JTable bankQueriesTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox statusType;
}
