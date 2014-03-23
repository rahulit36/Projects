/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.tracking.UI;

import com.msr.newTx.UI.OpportunityCommentFrame;
import com.msr.newTx.UI.SLANotVerifiedFrame;
import com.msr.newTx.dao.ProposalViewBean;
import com.msr.tracking.dao.GetTrackingStatusAndCount;
import com.msr.tools.UI.IconTableCellRenderer;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.DocumentsDetailBean;
import com.parser.ParserCaller;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author root
 */
public class TrackingTabelPanel extends javax.swing.JPanel {

    Vector<String> head = new Vector();
    Vector<Vector> columData = new Vector();
    String email = "";
    String accName = "";
    MainScreen mainScreen = null;
    JPanel trackingPanel = null;
    JTabbedPane tabbedPane = null;
    JComboBox statusCombo = new JComboBox();
    TrackingTabelPanel tablelPanel = null;
    ChangeEvent changeEvent;
    int r = 0;

    /**
     * Creates new form TrackingTabelPanel
     */
    public TrackingTabelPanel(String email, String accName, MainScreen mainScreen, JPanel trackingPanel, JTabbedPane tabbedPane) {

        this.tabbedPane = tabbedPane;
        this.trackingPanel = trackingPanel;
        this.email = email;
        this.accName = accName;
        this.mainScreen = mainScreen;
        this.tablelPanel = this;
        System.out.println("accName: " + accName);

        if ("Identification Of Client".equalsIgnoreCase(accName.split(",")[0])) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Identification Of Client");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Internal Approval".equalsIgnoreCase(accName.split(",")[0]) && SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Internal Approval");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Internal Approval".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Identification Of Client");
            statusCombo.addItem("Awaiting Internal Approval");
            statusCombo.addItem("Send Offer Letter");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Send Offer Letter".equalsIgnoreCase(accName.split(",")[0])) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Send Offer Letter");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Client Approval".equalsIgnoreCase(accName.split(",")[0])) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Client Approval");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Mandate".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Mandate");
            statusCombo.addItem("Awaiting Documents");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Documents".equalsIgnoreCase(accName.split(",")[0]) && SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Documents");
        } else if ("Awaiting Documents".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Documents");
            statusCombo.addItem("Create Limit");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Create Limit".equalsIgnoreCase(accName.split(",")[0]) && SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Create Limit");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Create Limit".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Documents");
            statusCombo.addItem("Create Limit");
            statusCombo.addItem("Awaiting Limit Setup");
//            statusCombo.addItem("Limit Sanctioned");
//            statusCombo.addItem("Portfolio Client");
//            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Limit Setup".equalsIgnoreCase(accName.split(",")[0]) && SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Limit Setup");
//            statusCombo.addItem("Portfolio Client");
//            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Awaiting Limit Setup".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Awaiting Documents");
            statusCombo.addItem("Awaiting Limit Setup");
            statusCombo.addItem("Limit Sanctioned");
//            statusCombo.addItem("Portfolio Client");
//            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Limit Sanctioned".equalsIgnoreCase(accName.split(",")[0]) && SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Limit Sanctioned");
//            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Limit Sanctioned".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Limit Sanctioned");
            statusCombo.addItem("Portfolio Client");
//            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Portfolio Client".equalsIgnoreCase(accName.split(",")[0]) && SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Portfolio Client");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Portfolio Client".equalsIgnoreCase(accName.split(",")[0]) && !SingletonClass.designation.equalsIgnoreCase("RM")) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Portfolio Client");
            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Expiry Transition".equalsIgnoreCase(accName.split(",")[0])) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        } else if ("Opportunity Lost".equalsIgnoreCase(accName.split(",")[0])) {
            statusCombo.removeAllItems();
            statusCombo.addItem("Opportunity Lost");
        } else {
            statusCombo.removeAllItems();
            statusCombo.addItem("Identification Of Client");
            statusCombo.addItem("Awaiting Internal Approval");
            statusCombo.addItem("Send Offer Letter");
            statusCombo.addItem("Awaiting Client Approval");
            statusCombo.addItem("Awaiting Mandate");
            statusCombo.addItem("Awaiting Documents");
            statusCombo.addItem("Create Limit");
            statusCombo.addItem("Awaiting Limit Setup");
            statusCombo.addItem("Limit Sanctioned");
            statusCombo.addItem("Portfolio Client");
            statusCombo.addItem("Expiry Transition");
            statusCombo.addItem("Opportunity Lost");
        }

        statusCombo.addItemListener(new StateChange());
        initComponents();
        TableColumn expBusColumn = trackingTable.getColumnModel().getColumn(11);
        expBusColumn.setWidth(0);
        expBusColumn.setMinWidth(0);
        expBusColumn.setMaxWidth(0);
        TableColumn regionColumn = trackingTable.getColumnModel().getColumn(12);
        regionColumn.setWidth(0);
        regionColumn.setMinWidth(0);
        regionColumn.setMaxWidth(0);
        TableColumn compSapceClmn = this.trackingTable.getColumnModel().getColumn(13);
        compSapceClmn.setWidth(0);
        compSapceClmn.setMinWidth(0);
        compSapceClmn.setMaxWidth(0);

        TableColumn soluSapceClmn = this.trackingTable.getColumnModel().getColumn(14);
        soluSapceClmn.setWidth(0);
        soluSapceClmn.setMinWidth(0);
        soluSapceClmn.setMaxWidth(0);
        
        TableColumn ourChrgsClmn = this.trackingTable.getColumnModel().getColumn(15);
        ourChrgsClmn.setWidth(0);
        ourChrgsClmn.setMinWidth(0);
        ourChrgsClmn.setMaxWidth(0);
        
        trackingTable.getTableHeader().setReorderingAllowed(false);

        if (!"Opportunity Lost".equalsIgnoreCase(accName.split(",")[0])) {
            TableColumn copyColumn = trackingTable.getColumnModel().getColumn(10);
            copyColumn.setWidth(0);
            copyColumn.setMinWidth(0);
            copyColumn.setMaxWidth(0);
        }
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
        trackingTable = new javax.swing.JTable();

        ParserCaller caller = new ParserCaller();

        HashMap<String, String> resultEventMap = null;
        Iterator it = caller.parseTrackingDetails(email, accName.split(",")[0]).iterator();
        while (it.hasNext()) {
            resultEventMap = (HashMap<String, String>) it.next();
            System.out.println("in while loop " + resultEventMap.size());
            if (resultEventMap.size() > 0) {

                Vector data = new Vector();

                System.out.println(resultEventMap.get("txId"));
                System.out.println(accName.split(",")[0]);
                System.out.println(resultEventMap.get("type"));
                System.out.println(resultEventMap.get("client"));
                System.out.println("exp business: " + resultEventMap.get("expBusiness"));
                if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {

                    System.out.println(resultEventMap.get("Credit Analyst status: " + accName));

                    if (accName.split(",")[0].equalsIgnoreCase("Identification Of Client")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Internal Approval")
                        || accName.split(",")[0].equalsIgnoreCase("Send Offer Letter")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Client Approval")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Mandate")) {

                        if (!"New Solution".equalsIgnoreCase(resultEventMap.get("type")) && !"Existing-New Solution".equalsIgnoreCase(resultEventMap.get("type"))) {
                            data.add(resultEventMap.get("txId"));
                            data.add(accName.split(",")[0]);
                            data.add(resultEventMap.get("type"));
                            data.add(resultEventMap.get("client"));
                            data.add(resultEventMap.get("beneficiary"));
                            data.add(resultEventMap.get("bank"));

                            if (accName.split(",")[0].equalsIgnoreCase("Identification Of Client")
                                || accName.split(",")[0].equalsIgnoreCase("Awaiting Internal Approval")
                                || accName.split(",")[0].equalsIgnoreCase("Send Offer Letter")
                                || accName.split(",")[0].equalsIgnoreCase("Awaiting Client Approval")
                                || accName.split(",")[0].equalsIgnoreCase("Awaiting Mandate")
                                || accName.split(",")[0].equalsIgnoreCase("Awaiting Documents")) {

                                data.add(resultEventMap.get("expBusiness"));
                            } else if (accName.split(",")[0].equalsIgnoreCase("Create Limit")
                                || accName.split(",")[0].equalsIgnoreCase("Awaiting Limit Setup")) {
                                data.add(resultEventMap.get("amount"));

                            } else {

                                data.add(resultEventMap.get("limitSanctioned"));

                            }

                            data.add(resultEventMap.get("rmName"));
                            data.add(resultEventMap.get(""));
                            data.add(resultEventMap.get(""));
                            data.add(resultEventMap.get(""));
                            data.add(resultEventMap.get("expBusiness"));
                            data.add(resultEventMap.get("region"));
                            data.add(resultEventMap.get("compSpace"));
                            data.add(resultEventMap.get("solution"));
                            columData.add(data);
                        }
                    } else {
                        data.add(resultEventMap.get("txId"));
                        data.add(accName.split(",")[0]);
                        data.add(resultEventMap.get("type"));
                        data.add(resultEventMap.get("client"));
                        data.add(resultEventMap.get("beneficiary"));
                        data.add(resultEventMap.get("bank"));
                        if (accName.split(",")[0].equalsIgnoreCase("Identification Of Client")
                            || accName.split(",")[0].equalsIgnoreCase("Awaiting Internal Approval")
                            || accName.split(",")[0].equalsIgnoreCase("Send Offer Letter")
                            || accName.split(",")[0].equalsIgnoreCase("Awaiting Client Approval")
                            || accName.split(",")[0].equalsIgnoreCase("Awaiting Mandate")
                            || accName.split(",")[0].equalsIgnoreCase("Awaiting Documents")) {

                            data.add(resultEventMap.get("expBusiness"));
                        } else if (accName.split(",")[0].equalsIgnoreCase("Create Limit")
                            || accName.split(",")[0].equalsIgnoreCase("Awaiting Limit Setup")) {
                            data.add(resultEventMap.get("amount"));

                        } else {

                            data.add(resultEventMap.get("limitSanctioned"));

                        }

                        data.add(resultEventMap.get("rmName"));
                        data.add(resultEventMap.get(""));
                        data.add(resultEventMap.get(""));
                        data.add(resultEventMap.get(""));
                        data.add(resultEventMap.get("expBusiness"));
                        data.add(resultEventMap.get("region"));
                        data.add(resultEventMap.get("compSpace"));
                        data.add(resultEventMap.get("solution"));
                        columData.add(data);
                    }
                } else {
                    data.add(resultEventMap.get("txId"));
                    data.add(accName.split(",")[0]);
                    data.add(resultEventMap.get("type"));
                    data.add(resultEventMap.get("client"));
                    data.add(resultEventMap.get("beneficiary"));
                    data.add(resultEventMap.get("bank"));
                    if (accName.split(",")[0].equalsIgnoreCase("Identification Of Client")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Internal Approval")
                        || accName.split(",")[0].equalsIgnoreCase("Send Offer Letter")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Client Approval")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Mandate")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Documents")) {

                        data.add(resultEventMap.get("expBusiness"));
                    } else if (accName.split(",")[0].equalsIgnoreCase("Create Limit")
                        || accName.split(",")[0].equalsIgnoreCase("Awaiting Limit Setup")) {
                        data.add(resultEventMap.get("amount"));

                    } else {

                        data.add(resultEventMap.get("limitSanctioned"));

                    }

                    data.add(resultEventMap.get("rmName"));
                    data.add(resultEventMap.get(""));
                    data.add(resultEventMap.get(""));
                    data.add(resultEventMap.get(""));
                    data.add(resultEventMap.get("expBusiness"));
                    data.add(resultEventMap.get("region"));
                    data.add(resultEventMap.get("compSpace"));
                    data.add(resultEventMap.get("solution"));
                    data.add(resultEventMap.get("ourCharges"));
                    columData.add(data);
                }
            }

        }

        //            statusCombo.addItem("Identification Of Client");
        //            statusCombo.addItem("Awaiting Internal Approval");
        //            statusCombo.addItem("Send Offer Letter");
        //            statusCombo.addItem("Awaiting Client Approval");
        //            statusCombo.addItem("Awaiting Maindate");
        //            statusCombo.addItem("Awaiting Documents");
        //            statusCombo.addItem("Create Limit");
        //            statusCombo.addItem("Awaiting Limit Setup");
        //            statusCombo.addItem("Limit Sanctioned");
        //            statusCombo.addItem("Portfolio Client");
        //            statusCombo.addItem("Expiry Transition");
        //            statusCombo.addItem("Opportunity Lost");
        head.add("TxId");
        head.add("Status");
        head.add("Type");
        head.add("Client");
        head.add("Beneficiery");
        head.add("Bank");

        if(accName.split(",")[0].equalsIgnoreCase("Identification Of Client")||
            accName.split(",")[0].equalsIgnoreCase("Awaiting Internal Approval")||
            accName.split(",")[0].equalsIgnoreCase("Send Offer Letter")||
            accName.split(",")[0].equalsIgnoreCase("Awaiting Client Approval")||
            accName.split(",")[0].equalsIgnoreCase("Awaiting Mandate")||
            accName.split(",")[0].equalsIgnoreCase("Awaiting Documents"))

        {

            head.add("Expected Business");
        }else if(accName.split(",")[0].equalsIgnoreCase("Create Limit")||
            accName.split(",")[0].equalsIgnoreCase("Awaiting Limit Setup"))

        {
            head.add("Proposal Limit");

        }else{
            head.add("Sanctioned Limit");

        }
        head.add("RM Name");
        head.add("");
        head.add("");
        head.add("");
        head.add("");
        head.add("");
        head.add("");
        head.add("");
        head.add("");

        JTableHeader header = trackingTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(1024, 30));
        Font font = new Font("Serif", 1, 14);
        header.setForeground(new Color(0, 103, 204));
        header.setFont(font);

        trackingTable.setRowHeight(30);
        trackingTable.setAutoResizeMode(2);
        DefaultTableModel defaultTableModel = new DefaultTableModel(columData, head);
        trackingTable.setAutoCreateRowSorter(true);
        //        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel);
        //        trackingTable.setRowSorter(sorter);
        //        final Comparator<Object> ascendingColumn0 = new Comparator<Object>() {
            //            @Override
            //            public int compare(Object o1, Object o2) {
                //                if (o1 instanceof NoSort<?> || o2 instanceof NoSort<?>) {
                    //                    //maintain order
                    //                    return -1;
                    //                }
                //
                //                Integer int1 = (Integer) o1;
                //                Integer int2 = (Integer) o2;
                //
                //                return int1.compareTo(int2);
                //            }
            //        };
        //
        //sorter.setComparator(0, ascendingColumn0);
        //sorter.toggleSortOrder(0);
        trackingTable.setModel(defaultTableModel);
        setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        TableColumn testColumn = this.trackingTable.getColumnModel().getColumn(0);
        testColumn.setWidth(40);
        testColumn.setMinWidth(40);
        testColumn.setMaxWidth(40);
        TableColumn expBusColumn = this.trackingTable.getColumnModel().getColumn(11);
        expBusColumn.setWidth(0);
        expBusColumn.setMinWidth(0);
        expBusColumn.setMaxWidth(0);
        TableColumn regionColumn = this.trackingTable.getColumnModel().getColumn(12);
        regionColumn.setWidth(0);
        regionColumn.setMinWidth(0);
        regionColumn.setMaxWidth(0);
        TableColumn compSapceClmn = this.trackingTable.getColumnModel().getColumn(13);
        compSapceClmn.setWidth(0);
        compSapceClmn.setMinWidth(0);
        compSapceClmn.setMaxWidth(0);

        TableColumn soluSapceClmn = this.trackingTable.getColumnModel().getColumn(14);
        soluSapceClmn.setWidth(0);
        soluSapceClmn.setMinWidth(0);
        soluSapceClmn.setMaxWidth(0);

        TableColumn ourChrgsClmn = this.trackingTable.getColumnModel().getColumn(15);
        ourChrgsClmn.setWidth(0);
        ourChrgsClmn.setMinWidth(0);
        ourChrgsClmn.setMaxWidth(0);

        TableColumn typeColumn = this.trackingTable.getColumnModel().getColumn(2);
        typeColumn.setWidth(200);
        typeColumn.setMinWidth(200);
        typeColumn.setMaxWidth(200);
        TableColumn tableColumn1 = trackingTable.getColumnModel().getColumn(1);
        tableColumn1.setCellEditor(new DefaultCellEditor(statusCombo));

        TableColumn col4 = trackingTable.getColumnModel().getColumn(8);
        col4.setMaxWidth(40);
        col4.setCellRenderer(new IconTableCellRenderer("Edit"));
        TableColumn col5 = trackingTable.getColumnModel().getColumn(9);
        col5.setMaxWidth(40);
        col5.setCellRenderer(new IconTableCellRenderer("View"));

        TableColumn col9 = trackingTable.getColumnModel().getColumn(10);
        col9.setMaxWidth(40);
        col9.setCellRenderer(new IconTableCellRenderer("Copy"));

        if (!"Opportunity Lost".equalsIgnoreCase(accName.split(",")[0])) {
            TableColumn copyColumn = trackingTable.getColumnModel().getColumn(11);
            copyColumn.setWidth(0);
            copyColumn.setMinWidth(0);
            copyColumn.setMaxWidth(0);
        }
        trackingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trackingTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                trackingTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(trackingTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // inner class for status change 
    class StateChange implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent ie) {
            // state change shows whether item is selected or not
            try {
                System.gc();
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    StringBuffer statusChangeBuffer = new StringBuffer();
                    Object o = trackingTable.getValueAt(r, 0);
                    Object objTxStatus = trackingTable.getValueAt(r, 1);
                    String txNo = o.toString();
                    SingletonClass.txIdEdit = txNo;
                    String statusForChange = (String) statusCombo.getSelectedItem();
                    boolean isCreateLimit = false;
                    if (statusForChange.equalsIgnoreCase("Limit Sanctioned")) {
                        List<DocumentsDetailBean> docList = new UserXMLReader().getDocDataList(email, txNo);
                        if (docList.size() > 0) {
                            Iterator itr = docList.iterator();
                            while (itr.hasNext()) {
                                DocumentsDetailBean docBean = (DocumentsDetailBean) itr.next();
                                //***********************temp
//                                if (docBean.getDocName().contains("service_Agreement")) {
//
//                                    if (!docBean.getStatus().equals("Verified")) {
//                                        StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType)
//                                                .append("=saveClientDocs&").append(SingletonClass.docsName).append("=")
//                                                .append(URLEncoder.encode(docBean.getDocName())).append("&")
//                                                .append(SingletonClass.docsType).append("=").append(docBean.getDocType())
//                                                .append("&").append(SingletonClass.docsPath).append("=").append(URLEncoder.encode(docBean.getDocFTPPath()))
//                                                .append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode((String) trackingTable.getValueAt(r, 3)))
//                                                .append("&").append(SingletonClass.userName).append("=").append(email).append("&").append("status").append("=")
//                                                .append("Verified").append("&txnNo=").append(SingletonClass.txIdEdit);
//                                        String url1 = buffer1.toString();
//                                        String encodedUrl = url1.replace(" ", "%20");
//                                        System.out.println("doc upload url is " + encodedUrl);
//                                        String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);
//                                    }
//                                }
                                //***********************temp close***************
//                                if (docBean.getDocType().equals("Required") && !docBean.getDocName().contains("service_Agreement")) {
                                if (docBean.getDocType().equals("Required")) {
                                    System.out.println("Check Required document");
                                    if (docBean.getStatus().equals("Verified") || "Required/Pending".equalsIgnoreCase(docBean.getStatus())) {
                                        System.out.println("ifffff");
                                        isCreateLimit = true;
                                    } else {
                                        System.out.println("elseeee");
                                        isCreateLimit = false;
                                        if (docBean.getDocName().contains("service_Agreement")) {
                                            JOptionPane.showMessageDialog(null, "SLA document is not verified, status cannot be changed.Please contact to RM");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "All required documents are not verified, status cannot be changed.");
                                        }
                                        mainScreen.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                        refreshTrackingScreen();
                                        mainScreen.setCursor(Cursor.getDefaultCursor());
                                        break;
                                    }
                                } else {
                                    isCreateLimit = true;
                                }
                            }
                        } else {
                            isCreateLimit = false;
                        }
                        if (isCreateLimit) {
                            List<HashMap> ilcList = null;
                            List<HashMap> flcList = null;
                            ParserCaller parserCaller = new ParserCaller();
                            ProposalViewBean proposalViewBean = parserCaller.parseProposalDataForView(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "txnProposalXML" + "&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&" + SingletonClass.userName + "=" + email);
                            ilcList = proposalViewBean.getIlcList();
                            flcList = proposalViewBean.getFlcList();
                            System.out.println("ilcList:   *****8 " + ilcList);
                            System.out.println("flcList:   *****8 " + flcList);
                            if (ilcList.size() > 0) {
                                Map<String, String> ilcMap = null;
                                for (int listCount = 0; listCount < ilcList.size(); listCount++) {
                                    ilcMap = ilcList.get(listCount);
                                    System.out.println("^^^^^^^^^^%%%%%%%%%%%%%%%   " + ilcMap.get("actualLimit") + "-------------" + ilcMap.get("sanctionDate"));
                                    if (ilcMap.get("lcStatus").equalsIgnoreCase("y") || ilcList.size()==1) {
                                        if (ilcMap.get("actualLimit") != null && !ilcMap.get("actualLimit").equals("") && !ilcMap.get("actualLimit").equals("null'")
                                                && ilcMap.get("sanctionDate") != null && !ilcMap.get("sanctionDate").equals("") && !ilcMap.get("sanctionDate").equals("null'")) {
                                        } else {
                                            isCreateLimit = false;
                                            JOptionPane.showMessageDialog(null, "Please fill the sanction limit and sanction date in proposal details.");
                                            break;
                                        }
                                    }
                                    System.out.println(" listCount " + listCount);
                                }
                            }

                            if (flcList.size() > 0 && isCreateLimit) {
                                Map<String, String> flcMap = null;
                                for (int listCount = 0; listCount < flcList.size(); listCount++) {
                                    flcMap = flcList.get(listCount);
                                    if (flcMap.get("lcStatus").equalsIgnoreCase("y") || flcList.size()==1) {
                                        if (!flcMap.get("actualLimit").equals("") && !flcMap.get("actualLimit").equals("null'")
                                                && !flcMap.get("sanctionDate").equals("") && !flcMap.get("sanctionDate").equals("null'")) {
                                        } else {
                                            isCreateLimit = false;
                                            JOptionPane.showMessageDialog(null, "Please fill the sanction limit and sanction date in proposal details.");
                                            break;
                                        }
                                    }
                                    System.out.println(" counter " + listCount);
                                }
                            }
                        }
                        if (isCreateLimit) {
                            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                            List<Map> editViewQuerieList = new ParserCaller().parseBankQueriesXML(email, txNo);
                            if (editViewQuerieList.size() > 0) {
                                Map<String, String> ilcMap = null;
                                for (int listCount = 0; listCount < editViewQuerieList.size(); listCount++) {
                                    ilcMap = editViewQuerieList.get(listCount);
                                    String status = ilcMap.get("status");
                                    if (status.equalsIgnoreCase("Closed") || "Required/Pending".equalsIgnoreCase(status)) {
                                        isCreateLimit = true;
                                    } else {
                                        isCreateLimit = false;
                                        JOptionPane.showMessageDialog(null, "All Queries are not Closed, Status Cannot be changed.");
                                        mainScreen.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                                        refreshTrackingScreen();
                                        mainScreen.setCursor(Cursor.getDefaultCursor());
                                        break;
                                    }
                                }
                            }
                        }
                    } else if (statusForChange.equalsIgnoreCase("Create Limit") && !accName.split(",")[0].equalsIgnoreCase("Create Limit")) {

                            List<HashMap> ilcList = null;
                            List<HashMap> flcList = null;
                            boolean isCheckedNotify = false;
                            ParserCaller parserCaller = new ParserCaller();
                            ProposalViewBean proposalViewBean = parserCaller.parseProposalDataForView(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "txnProposalXML" + "&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&" + SingletonClass.userName + "=" + email);
                            ilcList = proposalViewBean.getIlcList();
                            flcList = proposalViewBean.getFlcList();
                            System.out.println("ilcList:   *****in Create limit status changed " + ilcList.size());
                            System.out.println("flcList:   *****in Create limit status changed " + flcList.size());
                            int checkedCounter =0;
                            if (ilcList.size() > 0) {
                                Map<String, String> ilcMap = null;
                                for (int listCount = 0; listCount < ilcList.size(); listCount++) {
                                    ilcMap = ilcList.get(listCount);
                                    if (ilcMap.get("lcStatus").equalsIgnoreCase("y")) {
                                        checkedCounter++;
                                    }
                                    System.out.println(" listCount " + listCount);
                                }
                                if(checkedCounter>1){
                                    isCheckedNotify= true;
                                        JOptionPane.showMessageDialog(null, "Please Checked Only one proposaed limit In Proposal Details.");
                                }
                            }
                            if(!isCheckedNotify){
                            if (flcList.size() > 0) {
                                Map<String, String> flcMap = null;
                                for (int listCount = 0; listCount < flcList.size(); listCount++) {
                                    flcMap = flcList.get(listCount);
                                    if (flcMap.get("lcStatus").equalsIgnoreCase("y")) {
                                        checkedCounter++;
                                    }
                                    System.out.println(" counter " + listCount);
                                }
                                
                                if(checkedCounter>1){
                                    isCheckedNotify= true;
                                        JOptionPane.showMessageDialog(null, "Please Checked Only one proposaed limit In Proposal Details.");
                                }
                             }
                            }
                        
                        if(!isCheckedNotify){
                        List<DocumentsDetailBean> docList = new UserXMLReader().getDocDataList(email, txNo);
                        if (docList.size() > 0) {
                            Iterator itr = docList.iterator();
                            while (itr.hasNext()) {
                                DocumentsDetailBean docBean = (DocumentsDetailBean) itr.next();
                                if (docBean.getDocName().contains("service_Agreement") && !docBean.getStatus().equals("Verified")) {
//                                    JOptionPane.showMessageDialog(null, "SLA document is not Verified, status cannot be changed.", "", 0);

                                    new SLANotVerifiedFrame(email, tabbedPane).setVisible(true);
                                    isCreateLimit = false;
                                    break;

                                } else {
                                    isCreateLimit = true;
                                }
                            }
                        }
                    }
                    } else if (statusForChange.equalsIgnoreCase("Opportunity Lost")) {
                        JPanel userPanel = new JPanel();
                        userPanel.setLayout(new GridLayout(1, 0));

//Labels for the textfield components        
                        JLabel commentLbl = new JLabel("Comment:");
                        JTextField commentText = new JTextField();

//Add the components to the JPanel        
                        userPanel.add(commentLbl);
                        userPanel.add(commentText);

//As the JOptionPane accepts an object as the message
//it allows us to use any component we like - in this case 
//a JPanel containing the dialog components we want
//                        int input = JOptionPane.showConfirmDialog(null, userPanel, "Enter the comment for opportunity lost:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        OpportunityCommentFrame frame = new OpportunityCommentFrame(tablelPanel, mainScreen, email);

                        frame.setVisible(true);
                        isCreateLimit = false;

                    } else {
                        isCreateLimit = true;
                    }
                    if (isCreateLimit) {
                        // check if same item is selected                    
                        if (!statusForChange.equals(objTxStatus.toString())) {
                            mainScreen.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            statusChangeBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=changeTrackingStatus&").append(SingletonClass.txNo).append("=").append(txNo).append("&").append(SingletonClass.txStatus).append("=").append(statusForChange).append("&").append(SingletonClass.userName).append("=").append(email);
                            callServerUrl callUrl = new callServerUrl();
                            String status = callUrl.urlProcessiong(statusChangeBuffer.toString().replaceAll(" ", "%20"));
                            System.out.println("Status of change in status is " + status);
                            refreshTrackingScreen();
                            mainScreen.setCursor(Cursor.getDefaultCursor());
                        }
                    }
                }
            } catch (Exception ex) {
                mainScreen.setCursor(Cursor.getDefaultCursor());
                ex.printStackTrace();
            }
        }

        protected void fireEditingStopped() {
            Object[] listeners = tablelPanel.listenerList.getListenerList();

            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == CellEditorListener.class) {
                    if (tablelPanel.changeEvent == null) {
                        tablelPanel.changeEvent = new ChangeEvent(this);
                    }
                    ((CellEditorListener) listeners[(i + 1)]).editingStopped(tablelPanel.changeEvent);
                }
            }
        }
    }
    private void trackingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trackingTableMouseClicked
        // delete the contact row if extra added
        int row = trackingTable.rowAtPoint(evt.getPoint());
        int col = trackingTable.columnAtPoint(evt.getPoint());
        System.out.println("value before edit if or view if block: " + SingletonClass.isEdit + " " + SingletonClass.isView);
        if (col == 8) {
            if ((trackingTable.getValueAt(row, 1).equals("Awaiting Internal Approval") && SingletonClass.designation.equalsIgnoreCase("RM"))
                    || (trackingTable.getValueAt(row, 1).equals("Awaiting Documents") && SingletonClass.designation.equalsIgnoreCase("Credit Analyst")
                    && (!trackingTable.getValueAt(row, 2).equals("Enhancement") || !trackingTable.getValueAt(row, 2).equals("Renewal")))) {
                JOptionPane.showMessageDialog(null, "Transaction can not be edited.", "", 0);
            } else {
                tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                SingletonClass.txStatusSelect = null;
                SingletonClass.txIdEdit = (String) trackingTable.getValueAt(row, 0);
                SingletonClass.txStatusSelect = (String) trackingTable.getValueAt(row, 1);
                SingletonClass.txTypeEdit = (String) trackingTable.getValueAt(row, 2);
                SingletonClass.clientCompanyName = (String) trackingTable.getValueAt(row, 3);
                SingletonClass.clientBenName = (String) trackingTable.getValueAt(row, 4);
                SingletonClass.expBusiness = (String) trackingTable.getValueAt(row, 11);
                SingletonClass.clientRegion = (String) trackingTable.getValueAt(row, 12);
                SingletonClass.clientCompSpace = (String) trackingTable.getValueAt(row, 13);
                SingletonClass.clientSolution = (String) trackingTable.getValueAt(row, 14);
                SingletonClass.ourChargesAutoFill = (String) trackingTable.getValueAt(row, 15);
                System.out.println("SingletonClass.clientSolution***********************&&&&&&&&&&&&&&&&&& " + SingletonClass.clientSolution);
                SingletonClass.isEdit = true;
                SingletonClass.isFirstAcc = true;
                SingletonClass.isView = false;
                tabbedPane.setSelectedIndex(1);
                tabbedPane.setCursor(Cursor.getDefaultCursor());
            }
        }
        if (col == 9) {
            tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            SingletonClass.txStatusSelect = null;
            SingletonClass.txIdEdit = (String) trackingTable.getValueAt(row, 0);
            SingletonClass.txStatusSelect = (String) trackingTable.getValueAt(row, 1);
            SingletonClass.txTypeEdit = (String) trackingTable.getValueAt(row, 2);
            SingletonClass.clientCompanyName = (String) trackingTable.getValueAt(row, 3);
            SingletonClass.clientBenName = (String) trackingTable.getValueAt(row, 4);
            SingletonClass.expBusiness = (String) trackingTable.getValueAt(row, 11);
            SingletonClass.clientRegion = (String) trackingTable.getValueAt(row, 12);
            SingletonClass.clientCompSpace = (String) trackingTable.getValueAt(row, 13);
            SingletonClass.clientSolution = (String) trackingTable.getValueAt(row, 14);
            SingletonClass.ourChargesAutoFill = (String) trackingTable.getValueAt(row, 15);
            System.out.println("SingletonClass.clientSolution***********************&&&&&&&&&&&&&&&&&& " + SingletonClass.clientSolution);
            SingletonClass.isFirstAcc = true;
            SingletonClass.isView = true;
            SingletonClass.isEdit = false;
            tabbedPane.setSelectedIndex(1);
            tabbedPane.setCursor(Cursor.getDefaultCursor());
        }
        if (col == 10) {
            tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Object o = trackingTable.getValueAt(row, 0);
            String txNo = o.toString();
            StringBuffer strBuffer = new StringBuffer();
            strBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=copylost&").append(SingletonClass.txNo).append("=").append(txNo).append("&").append(SingletonClass.userName).append("=").append(email);
            callServerUrl callUrl = new callServerUrl();
            String status = callUrl.urlProcessiong(strBuffer.toString().replaceAll(" ", "%20"));
            refreshTrackingScreen();
            tabbedPane.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_trackingTableMouseClicked
    public void refreshTrackingScreen() {
        try {
            if (trackingPanel == null) {
                trackingPanel = new JPanel();
            }
            trackingPanel.removeAll();
            boolean istablePanel = true;
            System.out.println(new Date() + "-----------------------------Befor---tracking refreshing in tablePanel--------------");
            com.msr.tracking.UI.AccordionMenu menu1 = new com.msr.tracking.UI.AccordionMenu(getMenuDescriptorTracking(""), mainScreen, trackingPanel, email, tabbedPane);
            menu1.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 200);
            System.out.println(new Date() + "-----------------------------After---tracking refreshing in tablePanel--------------");
            menu1.setBackground(new Color(229, 232, 235));
            menu1.repaint();
            menu1.validate();
            trackingPanel.add(menu1);
            trackingPanel.repaint();
            trackingPanel.validate();
            mainScreen.repaint();
            mainScreen.validate();
            mainScreen.setCursor(Cursor.getDefaultCursor());

        } catch (Exception e) {
            e.printStackTrace();
            mainScreen.setCursor(Cursor.getDefaultCursor());
        }
    }

    // get the status of tracking screen and return status string to constructor above
    /**
     *
     * @param userPriv
     * @return
     */
    public String getMenuDescriptorTracking(String userPriv) {

        StringBuilder menuDescripter = new StringBuilder();
        GetTrackingStatusAndCount countObjMap = new GetTrackingStatusAndCount();
        HashMap statusMap = new LinkedHashMap();
        statusMap = countObjMap.getCounts(email);
        Set setOfStatus = statusMap.entrySet();
        String[] accordion = new String[setOfStatus.size()];
        String[] counter = new String[setOfStatus.size()];
        Iterator accMap = setOfStatus.iterator();
        int s = 0;
        while (accMap.hasNext()) {
            Map.Entry me = (Map.Entry) accMap.next();
            accordion[s] = (String) me.getKey();
            counter[s] = (String) me.getValue();
            s++;
        }
        for (int r = 0; r < accordion.length; r++) {
            menuDescripter.append(accordion[r] + "," + counter[r] + "!");
        }
        String menuDescriptor = menuDescripter.toString();
        return menuDescriptor;

    }

    private void trackingTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trackingTableMousePressed
        this.r = this.trackingTable.rowAtPoint(evt.getPoint());
        System.out.println("evt.getPoint() " + evt.getPoint());
    }//GEN-LAST:event_trackingTableMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable trackingTable;
    // End of variables declaration//GEN-END:variables
}
