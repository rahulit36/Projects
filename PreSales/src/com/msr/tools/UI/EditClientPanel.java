package com.msr.tools.UI;

import com.msr.ftp.impl.DownloadUploadFile;
import com.msr.newTx.UI.ProposalDetailsPanel;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.ClientLimitBean;
import com.parser.ContactDetailBean;
import com.parser.ParserCaller;
import com.parser.UserXMLReader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.FormatStringValue;
import org.jdesktop.swingx.table.DatePickerCellEditor;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
class ClientLimitTableModel extends DefaultTableModel {

    public ClientLimitTableModel() {

        super(
                new Object[][]{},
                new String[]{
            "", "Discounting Bank ", "Beneficiary", "Client Limit", "Available Limit", "Sanction Date", "Limit Type", "", ""
        });

    }

    public boolean isCellEditable(int row, int col) {

        if (SingletonClass.designation.equalsIgnoreCase("RM")) {
            return false;
        } else {
            return true;
        }
    }
}

class OurChargesTable extends DefaultTableModel {

    public OurChargesTable() {

        super(
                new Object[][]{},
                new String[]{
            "", "Our Charges", "Last Updated Date"
        });

    }

    public boolean isCellEditable(int row, int col) {
            return false;        
    }
}

public class EditClientPanel extends javax.swing.JPanel {

    ClientDetailPanel clientDetailPanel = null;
    EditClientPanel editClientPanel = null;
    MainScreen home = null;
    int counter = 0;
    int bankCount = 0;
    boolean tempFlag = false;
    HashMap bankObjMap = null;
    HashMap bothBankMap = null;
    JComboBox jcomb = null;
    DefaultTableModel tableModel = null;
    DefaultTableModel limitTableModel = null;
    int rowNumber = 0;
    int clientRowNumber = 0;
    int tempCount = 0;
    private String userEmail = null;
    String serverPath = "";
    String oldOurCharges = "";
    //============================================================
    ToolsEditViewFrame toolsViewFrame = null;
    List conList = null;
    List clientLimitList = null;
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
    DecimalFormat formatter = new DecimalFormat("##,##0.00", symbols);
//    
//    BigDecimal tempClientLimit = null;
//    BigDecimal varClientLimit = null;

    private String convertDecimalViewStr(BigDecimal vaulue) {
        String str = null;
        if (vaulue != null) {
            str = formatter.format(vaulue.doubleValue()).toString();
        }
        return str;
    }
//=============================================================

    /**
     *
     * @param compId
     * @param toolsViewFrame
     * @param tempParent
     */
    public EditClientPanel(String compId, ToolsEditViewFrame toolsViewFrame, JPanel tempParent, String emailStr) {
        counter = 0;
        bankCount = 0;
        this.userEmail = emailStr;
        initComponents();
        this.clientDetailPanel = (ClientDetailPanel) tempParent;
        if(SingletonClass.designation!= null && !SingletonClass.designation.equals("Vertical Head") &&  !SingletonClass.designation.equals("Super Admin")){
            jButton2.setVisible(false);
            jButton3.setVisible(false);
            jPanel4.repaint();
        }

        List<String> rmList = new UserXMLReader().getRMListFromXML(userEmail);
        System.out.println("rmList >>>>>>" + rmList.size());
        Iterator<String> rmItr = rmList.iterator();
        while (rmItr.hasNext()) {
            RmComboBox.addItem(rmItr.next().toString());
        }

        JComboBox limitTypeCombo = new JComboBox();
        limitTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Applicant", "Beneficiary", "Applicant+Beneficiary"}));


        TableColumn colLimit6 = clientLimitTable.getColumnModel().getColumn(6);
        colLimit6.setCellEditor(new DefaultCellEditor(limitTypeCombo));


        List<String> bankList = new UserXMLReader().getBankListFromXML(userEmail);
        System.out.println("bank list >>>>>>>>>>" + bankList.size());
        Iterator<String> itr1 = bankList.iterator();
        while (itr1.hasNext()) {
            bankSelection.addItem(itr1.next().toString());
        }

        
        TableColumn col = clientLimitTable.getColumnModel().getColumn(1);
        col.setCellEditor(new ComboBoxCellEditor(bankSelection));
        


        symbols.setGroupingSeparator(',');
        editClientPanel = this;
        this.toolsViewFrame = toolsViewFrame;
        if (tempParent instanceof ClientDetailPanel) {
            this.clientDetailPanel = (ClientDetailPanel) tempParent;
        }
//        else if (tempParent instanceof ClientSearchResultPanel) {
//            this.searchClientDetailPanel = (ClientSearchResultPanel) tempParent;
//        }

//  make key listener for setting the client limit

        clientLimitTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        HashMap<String, List> extractClient = new UserXMLReader().extractClientDetailsXml(emailStr, compId);
        List<ContactDetailBean> contactList = extractClient.get("contactList");
        List<Map<String, String>> clientlist = extractClient.get("clientList");
        List<ClientLimitBean> newClientLimitList = extractClient.get("clientLimit");
        HashMap<String, String> newClientDetails = (HashMap<String, String>) clientlist.get(0);


        companyNameField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("name")));
        addressField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("address")));
        phoneNoField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("landline")));
        faxNoField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("faxNo")));
        categoryCB.setSelectedItem(newClientDetails.get("category"));
        turnOverField.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("turnOver")));
        compSpace.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("compSpace")));
        solution.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("solution")));
        if (newClientDetails.get("lastMeetDate")!=null && !newClientDetails.get("lastMeetDate").equals("")) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                SimpleDateFormat dateFormatFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String getLastMeetDate = dateFormat.format(dateFormatFull.parse(newClientDetails.get("lastMeetDate")));
                lastMeetDate.setText(getLastMeetDate);
            } catch (ParseException ex) {
                Logger.getLogger(EditClientPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("OurCharges from backend: "+newClientDetails.get("ourCharges"));
        if(newClientDetails.get("ourCharges")!=null && !"null".equals(newClientDetails.get("ourCharges"))&& !"".equals(newClientDetails.get("ourCharges"))&& newClientDetails.get("ourCharges").split(";").length>=0){
            if(newClientDetails.get("ourCharges").split(";")[0].contains(":")){
                oldOurCharges = newClientDetails.get("ourCharges").split(";")[0].split(":")[1];
                ourChrgesText.setText(oldOurCharges);
            }
        }
        if(newClientDetails.get("tenureLastUpdate")!=null && !"null".equals(newClientDetails.get("tenureLastUpdate")))
        tenureLastUpdate.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("tenureLastUpdate")));
        if(newClientDetails.get("fsaServerPath")!=null && !"".equals(newClientDetails.get("fsaServerPath")))
            serverPath = newClientDetails.get("fsaServerPath");
        yearOfAccFiled.setText(SingletonClass.replaceEmptySpace(newClientDetails.get("accusation")));
        RmComboBox.setSelectedItem(newClientDetails.get("rmName"));
        DefaultTableModel model = (DefaultTableModel) personTable.getModel();
//        model.insertRow(0, new Object[]{});
        personTable.setRowHeight(30);
        System.out.println("clientlimit list size is " + newClientLimitList.size());
        DefaultTableModel limitModel = (DefaultTableModel) clientLimitTable.getModel();
        DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
 
                
        Iterator utr = newClientLimitList.iterator();
        while (utr.hasNext()) {
            ClientLimitBean clBean = (ClientLimitBean) utr.next();
            limitModel.insertRow(clientRowNumber, new Object[]{});
            clientLimitTable.setValueAt(clientRowNumber + 1, clientRowNumber, 0);
            clientLimitTable.setValueAt(clBean.getBank(), clientRowNumber, 1);
            bankSelection.removeItem(clBean.getBank());
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getBeneficiary()), clientRowNumber, 2);
            if(!"".equals(clBean.getClientLimit()) && !"null".equals(clBean.getClientLimit()))
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(df.format(new Double(clBean.getClientLimit()).doubleValue())), clientRowNumber, 3);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getAvailableLimit()), clientRowNumber, 4);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getSanctionDate()), clientRowNumber, 5);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getLimitType()), clientRowNumber, 6);
            clientLimitTable.setValueAt(SingletonClass.replaceEmptySpace(clBean.getLimitId()), clientRowNumber, 8);
            clientRowNumber++;
        }
        tempCount = clientRowNumber;

        Iterator itr = contactList.iterator();
        while (itr.hasNext()) {
            ContactDetailBean contactDetailBean = (ContactDetailBean) itr.next();
            model.insertRow(rowNumber, new Object[]{});
            personTable.setValueAt(rowNumber + 1, rowNumber, 0);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactName()), rowNumber, 1);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactDesignation()), rowNumber, 2);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactMobile()), rowNumber, 3);
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactEmail()), rowNumber, 4);
            if (contactDetailBean.getNotifyStr() != null && contactDetailBean.getNotifyStr().length() > 0) {
                personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getNotifyStr().replaceFirst(",", "")), rowNumber, 5);
            }
            personTable.setValueAt(SingletonClass.replaceEmptySpace(contactDetailBean.getContactId()), rowNumber, 7);
            rowNumber++;


        }
        int rowCounter = 0;
        System.out.println("********   "+newClientDetails.get("ourCharges"));
        if(newClientDetails.get("ourCharges")!=null && !"null".equals(newClientDetails.get("ourCharges"))&& !"".equals(newClientDetails.get("ourCharges")) && newClientDetails.get("ourCharges").split(";").length>1){
        String[] oldCharges = newClientDetails.get("ourCharges").split(";")[1].split(",");
        while (oldCharges.length>rowCounter) {
            ContactDetailBean contactDetailBean = (ContactDetailBean) itr.next();
            model.insertRow(rowCounter, new Object[]{});
            ourChargesTable.setValueAt(rowCounter + 1, rowCounter, 0);
            ourChargesTable.setValueAt(SingletonClass.replaceEmptySpace(oldCharges[rowCounter].split(":")[0]), rowCounter, 1);
            ourChargesTable.setValueAt(SingletonClass.replaceEmptySpace(oldCharges[rowCounter].split(":")[1]), rowCounter, 2);
            rowCounter++;

        }
        }


        TableColumn col3 = clientLimitTable.getColumnModel().getColumn(7);
        col3.setMaxWidth(40);
        col3.setCellRenderer(new IconTableCellRenderer("Delete"));

        TableColumn col6 = personTable.getColumnModel().getColumn(6);
        col6.setMaxWidth(50);
        col6.setCellRenderer(new IconTableCellRenderer("Delete"));

        TableColumn dateColumn = clientLimitTable.getColumnModel().getColumn(5);
//        dateColumn.setMaxWidth(240);
//        dateColumn.setWidth(240);
//        dateColumn.setMinWidth(130);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DatePickerCellEditor cellEditor = new DatePickerCellEditor(dateFormat);

        cellEditor.setFormats(dateFormat);
        cellEditor.setClickCountToStart(0);
        dateColumn.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(dateFormat), JLabel.LEFT));
        dateColumn.setCellEditor(cellEditor);



        TableColumn bankIdcol = this.clientLimitTable.getColumnModel().getColumn(8);
        bankIdcol.setWidth(0);
        bankIdcol.setMaxWidth(0);
        bankIdcol.setMinWidth(0);
        bankIdcol.setResizable(false);

        TableColumn contactIdcol = this.personTable.getColumnModel().getColumn(7);
        contactIdcol.setWidth(0);
        contactIdcol.setMaxWidth(0);
        contactIdcol.setMinWidth(0);
        contactIdcol.setResizable(false);

//==============================================================================Get all bank to put in comboBox 

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        companyNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        phoneNoField = new javax.swing.JTextField();
        faxNoField = new javax.swing.JTextField();
        turnOverField = new javax.swing.JTextField();
        yearOfAccFiled = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        categoryCB = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        RmComboBox = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        compSpace = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        tenureLastUpdate = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        solution = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        titalPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        personTable = new javax.swing.JTable();
        addPersonButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bankSelection = new javax.swing.JComboBox();
        clientLimitTable = new javax.swing.JTable();
        ourChrgesText = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        bankSelection = new javax.swing.JComboBox();
        ourChargesTable = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        lastMeetDate = new javax.swing.JLabel();
        addBank = new javax.swing.JButton();
        manageDocButton = new javax.swing.JButton();
        workingCaptital = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(210, 223, 219));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 103, 204));
        jLabel1.setText("Company Name");

        companyNameField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 103, 204));
        jLabel2.setText("Address ");

        addressField.setColumns(20);
        addressField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addressField.setRows(5);
        addressField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addressFieldKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(addressField);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 103, 204));
        jLabel3.setText("Phone No.");

        phoneNoField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        phoneNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNoFieldKeyReleased(evt);
            }
        });

        faxNoField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        faxNoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                faxNoFieldKeyReleased(evt);
            }
        });

        turnOverField.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        turnOverField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                turnOverFieldKeyReleased(evt);
            }
        });

        yearOfAccFiled.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        yearOfAccFiled.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearOfAccFiledKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 103, 204));
        jLabel5.setText("Fax No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 103, 204));
        jLabel4.setText("Company Turn Over (in crore(s)):");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 103, 204));
        jLabel6.setText("Year of Acquisition");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 103, 204));
        jLabel9.setText("Category");

        categoryCB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        categoryCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Category--", "SME Client","Mid Corporate","Large Corporate"}));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 103, 204));
        jLabel10.setText("RM Assigned");

        RmComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select RM--" }));

        compSpace.setColumns(20);
        compSpace.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        compSpace.setRows(5);
        compSpace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                compSpaceKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(compSpace);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 103, 204));
        jLabel11.setText("Comprehensive Space:");

        tenureLastUpdate.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 103, 204));
        jLabel12.setText("Last Updated Date");

        solution.setColumns(20);
        solution.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        solution.setRows(5);
        solution.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                solutionKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(solution);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 103, 204));
        jLabel13.setText("Solution:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenureLastUpdate)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(categoryCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yearOfAccFiled)
                    .addComponent(companyNameField)
                    .addComponent(faxNoField)
                    .addComponent(phoneNoField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(RmComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(turnOverField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(companyNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(faxNoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(turnOverField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearOfAccFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenureLastUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel13)))
                .addContainerGap())
        );

        titalPanel.setBackground(new java.awt.Color(21, 21, 142));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Edit  Company");

        javax.swing.GroupLayout titalPanelLayout = new javax.swing.GroupLayout(titalPanel);
        titalPanel.setLayout(titalPanelLayout);
        titalPanelLayout.setHorizontalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titalPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(915, Short.MAX_VALUE))
        );
        titalPanelLayout.setVerticalGroup(
            titalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        JTableHeader header = personTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,30));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        personTable.setFont(new java.awt.Font("Tahoma", 1, 12));
        personTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Contact Person", "Designation", "Mobile Number", "E-mail Id","Email Previlages", "",""
            }
        ));
        personTable.setRowHeight(30);
        TableColumn sNoCol = this.personTable.getColumnModel().getColumn(0);
        sNoCol.setWidth(50);
        sNoCol.setMaxWidth(50);
        sNoCol.setResizable(false);
        personTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                personTableMouseClicked(evt);
            }
        });
        TableColumn contactIdcol = this.personTable.getColumnModel().getColumn(7);
        contactIdcol.setWidth(0);
        contactIdcol.setMaxWidth(0);
        contactIdcol.setMinWidth(0);
        contactIdcol.setResizable(false);
        jScrollPane2.setViewportView(personTable);

        addPersonButton.setBackground(new java.awt.Color(0, 103, 204));
        addPersonButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addPersonButton.setForeground(new java.awt.Color(255, 255, 255));
        addPersonButton.setText("Add Person");
        addPersonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(0, 103, 204));
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(210, 223, 219));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 103, 204));
        jLabel8.setText("Add Discounting Bank & Client Limit");

        JTableHeader head = clientLimitTable.getTableHeader();
        head.setBackground(new Color(192, 191, 200));
        head.setPreferredSize(new Dimension(200,30));
        Font font1 = new Font("Serif", Font.BOLD, 14);
        head.setForeground(new Color(0, 103, 204));

        head.setFont(font1);
        clientLimitTable.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        clientLimitTable.setModel(new ClientLimitTableModel());
        clientLimitTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                return;
                int selectedRow = e.getLastIndex();
                System.out.println("selected row is "+selectedRow+"  clientRowCount "+clientRowNumber);
                if(selectedRow<clientRowNumber){

                    if(clientLimitTable.getValueAt(selectedRow, 3)!=null && clientLimitTable.getValueAt(selectedRow, 4)==null)
                    clientLimitTable.setValueAt(clientLimitTable.getValueAt(selectedRow, 3), selectedRow, 4);
                }
            }
        });
        clientLimitTable.setRowHeight(30);
        clientLimitTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientLimitTableMouseClicked(evt);
            }
        });
        TableColumn sNoTab = this.clientLimitTable.getColumnModel().getColumn(0);
        sNoTab.setWidth(50);
        sNoTab.setMaxWidth(50);
        sNoTab.setResizable(false);
        TableColumn bankIdcol = this.clientLimitTable.getColumnModel().getColumn(8);
        bankIdcol.setWidth(0);
        bankIdcol.setMaxWidth(0);
        bankIdcol.setMinWidth(0);
        bankIdcol.setResizable(false);
        jScrollPane3.setViewportView(clientLimitTable);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 103, 204));
        jLabel14.setText("Our Charges:");

        jButton2.setText("Approve");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reject");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        JTableHeader head1 = ourChargesTable.getTableHeader();
        head1.setBackground(new Color(192, 191, 200));
        head1.setPreferredSize(new Dimension(200,30));
        Font font11 = new Font("Serif", Font.BOLD, 14);
        head1.setForeground(new Color(0, 103, 204));

        head1.setFont(font11);
        ourChargesTable.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12));
        ourChargesTable.setModel(new OurChargesTable());
        ourChargesTable.setRowHeight(30);
        ourChargesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ourChargesTableMouseClicked(evt);
            }
        });
        TableColumn sNo1 = this.ourChargesTable.getColumnModel().getColumn(0);
        sNo1.setWidth(50);
        sNo1.setMaxWidth(50);
        sNo1.setResizable(false);
        jScrollPane6.setViewportView(ourChargesTable);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 103, 204));
        jLabel15.setText("Last Meeting Date:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ourChrgesText, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastMeetDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastMeetDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ourChrgesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );

        addBank.setBackground(new java.awt.Color(0, 103, 204));
        addBank.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addBank.setForeground(new java.awt.Color(255, 255, 255));
        addBank.setText("Add Bank");
        addBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBankActionPerformed(evt);
            }
        });

        manageDocButton.setBackground(new java.awt.Color(0, 103, 204));
        manageDocButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        manageDocButton.setForeground(new java.awt.Color(255, 255, 255));
        manageDocButton.setText("Manage Documents");
        manageDocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageDocButtonActionPerformed(evt);
            }
        });

        workingCaptital.setBackground(new java.awt.Color(0, 103, 204));
        workingCaptital.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        workingCaptital.setForeground(new java.awt.Color(255, 255, 255));
        workingCaptital.setText("Working Capital");
        workingCaptital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workingCaptitalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(titalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(workingCaptital)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manageDocButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addBank, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addPersonButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateButton)
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBank, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPersonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageDocButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workingCaptital, javax.swing.GroupLayout.DEFAULT_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
// add the person row in table
    private void addPersonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPersonButtonActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
        tableModel.insertRow(rowNumber, new Object[]{});
        personTable.setValueAt(rowNumber + 1, rowNumber, 0);
        rowNumber++;
    }//GEN-LAST:event_addPersonButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        toolsViewFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));


        boolean isCompanyDetails = false;
        boolean isContactDetails = false;
        boolean isBankLimit = false;

        tableModel = (DefaultTableModel) personTable.getModel();
        int rowCount = tableModel.getRowCount();

        limitTableModel = (DefaultTableModel) clientLimitTable.getModel();
        int limitRowCount = limitTableModel.getRowCount();
        if (rowCount < 1) {
            JOptionPane.showMessageDialog(null, "please add contact details");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
            isContactDetails = false;
        }
        if (limitRowCount == 0) {
            isBankLimit = true;
        }


        if (companyNameField.getText() == null || companyNameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Provide company Name.");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else if (addressField.getText() == null || addressField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Provide Company Address.");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else if (phoneNoField.getText() == null || phoneNoField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Provide company's Phone Number.");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else if (turnOverField.getText() == null || turnOverField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Provide Company TurnOver.");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else if (RmComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please Select RM For this Client.");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else if (categoryCB.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select Category.");
            toolsViewFrame.setCursor(Cursor.getDefaultCursor());
        } else {
            isCompanyDetails = true;
        }


        if (isCompanyDetails) {
            for (int i = 0; i < rowCount; i++) {
                if (tableModel.getValueAt(i, 1) == null || tableModel.getValueAt(i, 1).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter Contact Person's Name");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } else if (tableModel.getValueAt(i, 2) == null || tableModel.getValueAt(i, 2).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter Contact Person's Designation");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } else if (tableModel.getValueAt(i, 3) == null || tableModel.getValueAt(i, 3).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter Contact Person's Mobile Number");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } else if (tableModel.getValueAt(i, 4) == null || tableModel.getValueAt(i, 4).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please provide Contact Person's Email");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } else if (tableModel.getValueAt(i, 5) == null || tableModel.getValueAt(i, 5).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select email priveledge for " + tableModel.getValueAt(i, 1).toString());
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } else {
                    isContactDetails = true;
                }

//                boolean isValidEmail = false;
//                Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*[._-]*[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*");
//                Matcher matchPattern = p.matcher(personTable.getValueAt(i, 4).toString());
//                isValidEmail = matchPattern.matches();
//                if (!isValidEmail) {
//                    JOptionPane.showMessageDialog(null, "Invalid email id of " + personTable.getValueAt(i, 1).toString(), "", 0);
//                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                    isContactDetails = false;
//                    break;
//                } else {
//                    isContactDetails = true;
//                }
//
//                boolean isInt = isInt(personTable.getValueAt(i, 3).toString());
//                System.out.println("phnPattern.matches(): " + personTable.getValueAt(i, 3).toString() + "   " + isInt);
//
//                // get the applicant values from UI, and append for URL.
//                if (!isInt) {
//                    JOptionPane.showMessageDialog(null, "invalid mobile no. of " + personTable.getValueAt(i, 1).toString(), "", 0);
//                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                    isContactDetails = false;
//                    break;
//                } else {
//                    isContactDetails = true;
//                }

            }
        }

        if (isCompanyDetails && isContactDetails) {
            for (int i = 0; i < limitRowCount; i++) {
                if (limitTableModel.getValueAt(i, 1) == null || limitTableModel.getValueAt(i, 1).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select Bank");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } 
//                else if (limitTableModel.getValueAt(i, 2) == null || limitTableModel.getValueAt(i, 2).toString().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Please enter Beneficiary");
//                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                    break;
//                } 
                else if (limitTableModel.getValueAt(i, 3) == null || limitTableModel.getValueAt(i, 3).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter Credit Limit");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                } else if (limitTableModel.getValueAt(i, 4) == null || limitTableModel.getValueAt(i, 4).toString().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter Available Limit");
                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                    break;
                }
//                else if (limitTableModel.getValueAt(i, 5) == null || limitTableModel.getValueAt(i, 5).toString().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Please enter Sanction Date");
//                    toolsViewFrame.setCursor(Cursor.getDefaultCursor());
//                    break;
//                } 
                else {
                    isBankLimit = true;
                }
            }

        }
        if (isCompanyDetails && isContactDetails && isBankLimit) {
            boolean iscontactAdded = false;
            boolean isClientLimitAdded = false;
//    limitAmount

            StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userEmail)
                    .append("&").append(SingletonClass.operaionType).append("=saveClientDetails&")
                    .append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(companyNameField.getText()))
                    .append("&").append(SingletonClass.appPhn).append("=").append(phoneNoField.getText())
                    .append("&").append(SingletonClass.ourCharges).append("=").append(ourChrgesText.getText())
                    .append("&").append(SingletonClass.appFax).append("=").append(faxNoField.getText())
                    .append("&").append(SingletonClass.appTurnOver).append("=").append(turnOverField.getText())
                    .append("&").append(SingletonClass.compSpace).append("=").append(URLEncoder.encode(compSpace.getText()))
                    .append("&").append(SingletonClass.solution).append("=").append(URLEncoder.encode(solution.getText()))
                    .append("&").append(SingletonClass.appAddrs).append("=").append(URLEncoder.encode(addressField.getText()))
                    .append("&").append(SingletonClass.accYear).append("=").append(yearOfAccFiled.getText())
                    .append("&").append(SingletonClass.appCategory).append("=").append(categoryCB.getSelectedItem().toString())
                    .append("&").append(SingletonClass.rmName).append("=").append(RmComboBox.getSelectedItem().toString());
            String url = buffer.toString();
            url = url.replace(" ", "%20");
            System.out.println("url is " + url);
            String urlStatus = new callServerUrl().urlProcessiong(url);
//            uploading of document on server and updating in database.

//            saving client contacts

//            clientName

            if (!urlStatus.equals("invalid")) {
                for (int i = 0; i < rowCount; i++) {
                    StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userEmail).append("&").append(SingletonClass.operaionType).append("=saveClientContacts&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(companyNameField.getText())).append("&").append(SingletonClass.clientContactName).append("=").append(tableModel.getValueAt(i, 1).toString()).append("&").append(SingletonClass.clientContactDesignation).append("=").append(tableModel.getValueAt(i, 2).toString()).append("&").append(SingletonClass.clientContactMobile).append("=").append(tableModel.getValueAt(i, 3).toString()).append("&").append(SingletonClass.clientContactEmail).append("=").append(tableModel.getValueAt(i, 4).toString()).append("&notifyStr=,").append(tableModel.getValueAt(i, 5).toString());
                    String url1 = buffer1.toString();
                    String encodedUrl = url1.replace(" ", "%20");
                    System.out.println("client contact url is " + encodedUrl);
                    String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                    if (urlStatus1.equals("invalid")) {
                        iscontactAdded = true;
                        break;
                    }
                }
            }
//            saving clients bank limit........
            if (!urlStatus.equals("invalid") && !iscontactAdded) {
                for (int i = 0; i < limitRowCount; i++) {
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    DateFormat newDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    String sanctionDate = "";
                    if (isddmmyyyyDateFormat(clientLimitTable.getValueAt(i, 5).toString())) {
                        try {
                            System.out.println("=======================");
                            sanctionDate = dateFormat.format(dateFormat.parse(clientLimitTable.getValueAt(i, 5).toString()));
                        } catch (ParseException ex) {
                            Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("else part===========================");
                        try {
                            sanctionDate = dateFormat.format(newDateFormat.parse(clientLimitTable.getValueAt(i, 5).toString()));
                        } catch (ParseException ex) {
                            Logger.getLogger(ProposalDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userEmail)
                            .append("&").append(SingletonClass.operaionType).append("=saveClientLimit&").append(SingletonClass.clientIsBenOrApp).append("=")
                            .append(URLEncoder.encode(companyNameField.getText()))
                            .append("&").append(SingletonClass.beneficiary).append("=").append(limitTableModel.getValueAt(i, 2).toString())
                            .append("&").append(SingletonClass.bankName).append("=").append(limitTableModel.getValueAt(i, 1).toString())
                            .append("&").append(SingletonClass.clntlimit).append("=").append(limitTableModel.getValueAt(i, 3).toString().replaceAll(",", ""))
                            .append("&").append(SingletonClass.avlLimit).append("=").append(limitTableModel.getValueAt(i, 4).toString().replaceAll(",", ""))
                            .append("&").append(SingletonClass.limitType).append("=").append(limitTableModel.getValueAt(i, 6).toString())
                            .append("&").append(SingletonClass.sanctionDate).append("=").append(sanctionDate);
                    String url1 = buffer1.toString();
                    String encodedUrl = url1.replace(" ", "%20");
                    System.out.println("client limit url is " + encodedUrl);
                    String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                    if (urlStatus1.equals("invalid")) {
                        isClientLimitAdded = true;
                        break;
                    }
                }
            }

            if (urlStatus.equals("invalid") || iscontactAdded || isClientLimitAdded) {
                JOptionPane.showMessageDialog(this, "Error in Saving Client, Please Try Again.");
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
            } else {

                JOptionPane.showMessageDialog(this, "Successfully Saved ");
                if(!oldOurCharges.equals(ourChrgesText.getText())){
                                    Map<String, String> emailMap = null;
                                    ParserCaller parserCaller = new ParserCaller();
                                    emailMap = parserCaller.parseEmailSForOurChargesApproval(this.userEmail, companyNameField.getText());
                                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                    String emailText = "Dear Sir/Madam,<br>\n" + "Kindly approve the below our charges for  " + companyNameField.getText()+": \nOur Charges: "+ourChrgesText.getText();
                                    new com.msr.tools.UI.EmailScreen1(SingletonClass.txIdEdit,"Our Charges Approval-"+companyNameField.getText(),  this, this.userEmail,  emailMap, emailText).setVisible(true);
                }
                toolsViewFrame.setCursor(Cursor.getDefaultCursor());
                toolsViewFrame.dispose();
                clientDetailPanel.refresh();
            }
        }

    }//GEN-LAST:event_updateButtonActionPerformed
    int row = 0;
    int col = 0;

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

    private void clientLimitTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientLimitTableMouseClicked

        DefaultTableModel tableModel = (DefaultTableModel) clientLimitTable.getModel();
        int row = clientLimitTable.rowAtPoint(evt.getPoint());
        int col = clientLimitTable.columnAtPoint(evt.getPoint());
        boolean flag = false;
        if (col == 7) {
            try {

                if (clientLimitTable.getValueAt(row, 8) == null || clientLimitTable.getValueAt(row, 8).toString().equals("")) {
                    if (clientLimitTable.getValueAt(row, 1) != null) {
                        bankSelection.addItem(tableModel.getValueAt(row, 1));
                    }
                    clientRowNumber--;
                    tableModel.removeRow(row);
                    JOptionPane.showMessageDialog(this, "Successfully Deleted Extra Row");
                } else {

//StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deleteClientLimit&").append("limitId=").append(tableModel.getValueAt(row, 6)).append("&").append(SingletonClass.userName).append("=").append(userEmail);
//                    String strBuffer = buffer.toString().replaceAll(" ", "%20");
//                    System.out.println("client Limit url is " + strBuffer);
//                    String urlStatus1 = new callServerUrl().urlProcessiong(strBuffer);
//
//                    if (urlStatus1.equals("invalid")) {
                    JOptionPane.showMessageDialog(this, "Existing Limit can not be deleted.", "Delete Limit", JOptionPane.ERROR_MESSAGE);
//                    } else {
//                        tableModel.removeRow(row);
//                        clientRowNumber--;
//                        JOptionPane.showMessageDialog(this, "Client Limit has been Deleted.");
//                    }

                }

                //clientDetailPanel.refresh();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }//GEN-LAST:event_clientLimitTableMouseClicked

    private void addBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBankActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) clientLimitTable.getModel();
        tableModel.insertRow(clientRowNumber, new Object[]{});
        clientLimitTable.setValueAt(clientRowNumber + 1, clientRowNumber, 0);
        if (clientRowNumber > 0) {
            for (int j = tempCount; j < clientRowNumber; j++) {
                bankSelection.removeItem(tableModel.getValueAt(j, 1).toString());
            }
        }
        TableColumn col = clientLimitTable.getColumnModel().getColumn(1);
        col.setCellEditor(new ComboBoxCellEditor(bankSelection));
        clientRowNumber++;
    }//GEN-LAST:event_addBankActionPerformed
// delete the extra row
private void personTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_personTableMouseClicked
    DefaultTableModel tableModel = (DefaultTableModel) personTable.getModel();
    int row = personTable.rowAtPoint(evt.getPoint());
    int col = personTable.columnAtPoint(evt.getPoint());
    boolean flag = false;
    if (col == 6) {
        try {

            if (personTable.getValueAt(row, 7) == null || personTable.getValueAt(row, 7).toString().equals("")) {
                tableModel.removeRow(row);
                rowNumber--;
                JOptionPane.showMessageDialog(this, "Successfully Deleted Extra Row");
            } else {



//                StringBuffer buffer = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deleteClientContacts&").append(SingletonClass.clientContactName).append("=").append(tableModel.getValueAt(row, 1).toString()).append("&").append(SingletonClass.clientContactEmail).append("=").append(tableModel.getValueAt(row, 4).toString()).append("&clientName=").append(companyNameField.getText()).append("&").append(SingletonClass.userName).append("=").append(userEmail);
//
//                String strBuffer = buffer.toString().replaceAll(" ", "%20");
//                System.out.println("bank contact url is " + strBuffer);
//                String urlStatus1 = new callServerUrl().urlProcessiong(strBuffer);
//
//                if (urlStatus1.equals("invalid")) {
                JOptionPane.showMessageDialog(this, "Existing contact can not be deleted.", "Delete Contact", JOptionPane.ERROR_MESSAGE);
//                } else {
//                    tableModel.removeRow(row);
//                    rowNumber--;
//                    JOptionPane.showMessageDialog(this, "Contact has been Deleted.");
//                }

            }

            //clientDetailPanel.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }


    } else if (col == 5) {
        System.out.println("<<<<<<<<<");
        JCheckBox[] checkbox = new JCheckBox[]{new JCheckBox("Send Preliminary Offer"), new JCheckBox("Send SLA"), new JCheckBox("Send Document List"), new JCheckBox("Send Credit Advice"), new JCheckBox("Send Bills")};


        if (personTable.getValueAt(row, 5) != null && !personTable.getValueAt(row, 5).toString().equals("")) {
            String getPTText = personTable.getValueAt(row, 5).toString();
            String[] ptArr = getPTText.split(",");
            for (int privCount = 0; privCount < ptArr.length; privCount++) {
                for (int checkCount = 0; checkCount < checkbox.length; checkCount++) {
                    if (checkbox[checkCount].getText().equals(ptArr[privCount])) {
                        checkbox[checkCount].setSelected(true);
                    }
                }
            }
        }


        String message = "Client is allowed to recieve email for:";
        Object[] params = {message, checkbox};
        int status = JOptionPane.showConfirmDialog(toolsViewFrame, params, "Client's Email Priveledge", JOptionPane.OK_CANCEL_OPTION);
        if (status == JOptionPane.CANCEL_OPTION) {
        } else {
            int count = 0;
            StringBuffer privFor = new StringBuffer("");
            while (count < checkbox.length) {
                if (checkbox[count].isSelected()) {
                    privFor.append(checkbox[count].getText()).append(",");
                }
                count++;
            }
            String emailPriv = privFor.toString();
            personTable.setValueAt(emailPriv, row, 5);
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

    private void phoneNoFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNoFieldKeyReleased

        // TODO add your handling code here:
        boolean isInt = isInt(phoneNoField.getText());
        System.out.println("phnPattern.matches(): " + phoneNoField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid phone no.", "", 0);
            phoneNoField.setText("");
        }
    }//GEN-LAST:event_phoneNoFieldKeyReleased

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

    private void turnOverFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_turnOverFieldKeyReleased
        // TODO add your handling code here:
        boolean isInt = isInt(turnOverField.getText());
        System.out.println("phnPattern.matches(): " + turnOverField.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid turnOver value.", "", 0);
            turnOverField.setText("");
        }
    }//GEN-LAST:event_turnOverFieldKeyReleased

    private void yearOfAccFiledKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearOfAccFiledKeyReleased
        // TODO add your handling code here:
        boolean isInt = isInt(yearOfAccFiled.getText());
        System.out.println("phnPattern.matches(): " + yearOfAccFiled.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (!isInt) {
            JOptionPane.showMessageDialog(null, "invalid value.", "", 0);
            yearOfAccFiled.setText("");
        }
    }//GEN-LAST:event_yearOfAccFiledKeyReleased

    private void manageDocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageDocButtonActionPerformed
        // TODO add your handling code here:
        ClientDocFrameTools cdFrame = new ClientDocFrameTools(userEmail, companyNameField.getText());
        cdFrame.setVisible(true);
    }//GEN-LAST:event_manageDocButtonActionPerformed

    private void compSpaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_compSpaceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_compSpaceKeyPressed

    private void workingCaptitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workingCaptitalActionPerformed
        if("".equals(serverPath)) {
                        JOptionPane.showMessageDialog(null, "FSA not uploaded", "View Document", JOptionPane.ERROR_MESSAGE);            
        }else{
        boolean flag = new DownloadUploadFile().getFTPFile(serverPath, "", SingletonClass.sysFilePath);
                Desktop d = Desktop.getDesktop();
              if (flag) {
              try {
                  this.setCursor(Cursor.getDefaultCursor());
//                        d.open(new File(SingletonClass.sysFilePath + documentListTable.getValueAt(row, 1).toString().replace(" ", "_") + ".pdf"));
                  System.out.println("doc path: "+serverPath);
                  d.open(new File(SingletonClass.sysFilePath + serverPath.substring(serverPath.lastIndexOf("/"))));
              } catch (IOException ex) {
                  Logger.getLogger(EditClientPanel.class.getName()).log(Level.SEVERE, null, ex);
              }
                    } else {
                        JOptionPane.showMessageDialog(null, "FSA not uploaded", "View Document", JOptionPane.ERROR_MESSAGE);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
        }
    }//GEN-LAST:event_workingCaptitalActionPerformed

    private void solutionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_solutionKeyPressed

    }//GEN-LAST:event_solutionKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                            StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userEmail)
                            .append("&").append(SingletonClass.operaionType).append("=ourChargesApproval&").append(SingletonClass.clientIsBenOrApp).append("=")
                            .append(URLEncoder.encode(companyNameField.getText()))
                            .append("&").append("isApprove=").append("approved");
                    String url1 = buffer1.toString();
                    String encodedUrl = url1.replace(" ", "%20");
                    System.out.println("client limit url is " + encodedUrl);
                    String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                    if (urlStatus1.equals("invalid")) {
                        JOptionPane.showMessageDialog(null, "Problem Occured in approving the our charges.", "Approving Failed", JOptionPane.ERROR_MESSAGE);                        
                    }  
                    if (urlStatus1.equals("valid")) {
                        JOptionPane.showMessageDialog(null, "Our Charges updated successfully.", "Approving Failed", JOptionPane.ERROR_MESSAGE);                        
                    }  
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                                    StringBuffer buffer1 = new StringBuffer(SingletonClass.httpServerUrl).append(SingletonClass.userName).append("=").append(userEmail)
                            .append("&").append(SingletonClass.operaionType).append("=ourChargesApproval&").append(SingletonClass.clientIsBenOrApp).append("=")
                            .append(URLEncoder.encode(companyNameField.getText()))
                            .append("&").append("isApprove=").append("rejected");
                    String url1 = buffer1.toString();
                    String encodedUrl = url1.replace(" ", "%20");
                    System.out.println("client limit url is " + encodedUrl);
                    String urlStatus1 = new callServerUrl().urlProcessiong(encodedUrl);

                    if (urlStatus1.equals("invalid")) {
                        JOptionPane.showMessageDialog(null, "Problem Occured! Please try again.", "Reject Our Charges.", JOptionPane.ERROR_MESSAGE);                        
                    }  
                    if (urlStatus1.equals("valid")) {
                        JOptionPane.showMessageDialog(null, "Our Charges rejected.", "Reject Our Charges", JOptionPane.ERROR_MESSAGE);                        
                    }  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void ourChargesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ourChargesTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ourChargesTableMouseClicked
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox RmComboBox;
    private javax.swing.JButton addBank;
    private javax.swing.JButton addPersonButton;
    private javax.swing.JTextArea addressField;
    private javax.swing.JComboBox categoryCB;
    private javax.swing.JTable clientLimitTable;
    private javax.swing.JTextArea compSpace;
    private javax.swing.JTextField companyNameField;
    private javax.swing.JTextField faxNoField;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lastMeetDate;
    private javax.swing.JButton manageDocButton;
    private javax.swing.JTable ourChargesTable;
    private javax.swing.JTextField ourChrgesText;
    private javax.swing.JTable personTable;
    private javax.swing.JTextField phoneNoField;
    private javax.swing.JTextArea solution;
    private javax.swing.JTextField tenureLastUpdate;
    private javax.swing.JPanel titalPanel;
    private javax.swing.JTextField turnOverField;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton workingCaptital;
    private javax.swing.JTextField yearOfAccFiled;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JComboBox bankSelection;

    /**
     *
     * @param strBigDec
     * @return
     */
    public BigDecimal convertStrToBigDecimal(String strBigDec) {
        BigDecimal bigDec = null;
        if (strBigDec != null && !strBigDec.equals("")) {
            if (strBigDec.indexOf(',') >= 0) {
                bigDec = new BigDecimal(strBigDec.replaceAll(",", ""));
            } else {
                bigDec = new BigDecimal(strBigDec);
            }
        } else {
            bigDec = null;
        }

        return bigDec;
    }
}
