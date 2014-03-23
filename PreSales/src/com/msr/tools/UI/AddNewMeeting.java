/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.tools.UI;


import com.msr.newTx.UI.*;
import com.msr.ftp.bean.MeetingsDetails;
import com.msr.newTx.dao.MeetingDetailBean;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.FormatStringValue;
import org.jdesktop.swingx.table.DatePickerCellEditor;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

  class AddMyDefaultTableModel extends DefaultTableModel {

    public AddMyDefaultTableModel() {
        super(new Object[][]{},
                new String[]{
            "Contact Name", "Schedule Date(dd-MM-yyyy)", "Schedule Time(HH:mm)", "Place", "Comments", "Case Summary", "", "", "", "",""
        });
    }

    public boolean isCellEditable(int row, int col) {
        if (col == 8) {
            return false;
        } else {
            return true;
        }
    }
}

/**
 *
 * @author root
 */
public class AddNewMeeting extends javax.swing.JFrame {

    public JComboBox jContactBox = new JComboBox();
    JPanel clientDetailsPanel = null;
    int counter = 0;
    String email = "";
    String meetingTime = "";
    String clientName = "";
    JComboBox jcomb = new JComboBox();
    Map<String, String> mailScreenCheckMap = null;
    /**
     * Creates new form AddNewMeeting
     */
  
    
    public AddNewMeeting(String email, String accName,  JPanel clientDetailsPanel, String clientName) {
             initComponents();
        this.email = email;
        this.clientName = clientName;
        this.clientDetailsPanel = clientDetailsPanel;
//        SingletonClass.isSave = false;
        jContactBox = SingletonClass.contactPersons;

        //  set client name column.
        TableColumn tableColumn0 = meetingDetailsTable.getColumnModel().getColumn(0);
        tableColumn0.setMaxWidth(200);
        tableColumn0.setMinWidth(200);
        tableColumn0.setWidth(200);
//        if (SingletonClass.isEdit == true) {
        tableColumn0.setCellEditor(new DefaultCellEditor(SingletonClass.contactPersons));

        TableColumn dateColumn = meetingDetailsTable.getColumnModel().getColumn(1);
        dateColumn.setMaxWidth(240);
        dateColumn.setMinWidth(240);
        dateColumn.setWidth(240);
//        dateColumn.setMinWidth(130);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DatePickerCellEditor cellEditor = new DatePickerCellEditor(dateFormat);

        cellEditor.setFormats(dateFormat);
        cellEditor.setClickCountToStart(0);
        dateColumn.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(dateFormat), JLabel.LEFT));
        dateColumn.setCellEditor(cellEditor);

        // Apply for a single column
        TableColumn timeColumn = meetingDetailsTable.getColumnModel().getColumn(2);
        timeColumn.setMaxWidth(200);
        timeColumn.setMinWidth(200);
        timeColumn.setWidth(200);

        Date today = new Date();
        JSpinner s = new JSpinner(new SpinnerDateModel(today, null, null,
                Calendar.HOUR_OF_DAY));
        JSpinner.DateEditor de = new JSpinner.DateEditor(s, "HH:mm");
        s.setEditor(de);

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        timeColumn.setCellRenderer(new DefaultTableRenderer(new FormatStringValue(timeFormat), JLabel.LEFT));
        timeColumn.setCellEditor(new SpinnerEditorColumn.SpinnerEditor());


        TableColumn col6 = meetingDetailsTable.getColumnModel().getColumn(7);
        col6.setMaxWidth(40);
        col6.setCellRenderer(new IconTableCellRenderer("Delete"));

        TableColumn reminderCol3 = meetingDetailsTable.getColumnModel().getColumn(6);
        reminderCol3.setMaxWidth(40);
        reminderCol3.setCellRenderer(new IconTableCellRenderer("Alarm"));

//        // Create our cell editor
//        System.out.println("txID: " + SingletonClass.txIdEdit);
//        if (SingletonClass.txIdEdit != null) {
            setMeetingDetails(SingletonClass.txIdEdit);
//        }
        if (SingletonClass.designation.equalsIgnoreCase("credit analyst")) {
            setEnableFalseAllFields();
        }

        if (SingletonClass.isView) {
            setEnableFalseAllFields();
        }

        TableColumn col4 = meetingDetailsTable.getColumnModel().getColumn(4);
        col4.setMaxWidth(230);
        col4.setMinWidth(230);
        col4.setWidth(230);
        

// set the minimum width  of that colum
        TableColumn reminderHideColumn = meetingDetailsTable.getColumnModel().getColumn(8);
        reminderHideColumn.setWidth(0);
        reminderHideColumn.setMaxWidth(0);
        reminderHideColumn.setMinWidth(0);
        reminderHideColumn.setResizable(false);
// set the minimum width  of that colum
        TableColumn reminderHideColumn8 = meetingDetailsTable.getColumnModel().getColumn(9);
        reminderHideColumn8.setWidth(0);
        reminderHideColumn8.setMaxWidth(0);
        reminderHideColumn8.setMinWidth(0);
        reminderHideColumn8.setResizable(false);
// set the minimum width  of meeting id
        TableColumn meetingId = meetingDetailsTable.getColumnModel().getColumn(10);
        meetingId.setWidth(0);
        meetingId.setMaxWidth(0);
        meetingId.setMinWidth(0);
        meetingId.setResizable(false);

// set time format in meeting time text field
        meetingDetailsTable.getTableHeader().setReorderingAllowed(false);


    }

    public void setEnableFalseAllFields() {
        meetingDetailsTable.setEnabled(false);
        meetingDetailsTable.setFont(new java.awt.Font("Tahoma", 1, 13));
    }

    private List<MeetingsDetails> setMeetingDetails(String txnNo) {
        MeetingDetailsParser meetingDetailsParser = null;
        XMLReader xmlReader = null;
        List<MeetingsDetails> meetingList = null;
        StringBuffer meetingUrl = null;
        List<String> contactList = null;
        try {
            meetingUrl = new StringBuffer();

            ParserCaller parserCaller = new ParserCaller();
            contactList = parserCaller.parseClientContactDetailsXmlOnly(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(clientName) + "&" + SingletonClass.userName + "=" + email);
                   System.out.println("contactList Size " + contactList.size());
                   
        TableColumn col0 = meetingDetailsTable.getColumnModel().getColumn(0);
        System.out.println(" label is " + col0.getHeaderValue());
        jContactBox.removeAllItems();
        col0.setCellEditor(new DefaultCellEditor(setcomboListItems(contactList)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            meetingDetailsParser = null;
            meetingUrl = null;
            xmlReader = null;
        }
        return meetingList;

    }
    LinkedHashMap<String, String> linkedMap = null;
    //set meeting data into meeting table cell (code by satya)


    private JComboBox setcomboListItems(List contactItem) {

        if (contactItem.size() > 0) {
            for (int listCount = 0; listCount < contactItem.size(); listCount++) {
                if (contactItem.get(listCount) != null && ((String) contactItem.get(listCount)).length() > 0) {
                    jContactBox.addItem(contactItem.get(listCount));
                }

            }
        }
        return jContactBox;
    }
    //day/hour to minutes conversion

    private String minutesConversion(String remindTime) {
        String remindText = "";
        if (remindTime != null && remindTime.length() > 0 && !"null".equalsIgnoreCase(remindTime)) {
            int minutes = Integer.parseInt(remindTime);
            remindText = (minutes / 60 == 0) ? minutes + " minutes" : (((minutes / 60) >= 24) ? "" + (minutes / 1440) + " day" : +minutes / 60 + " hour");
        } else {
            remindText = remindTime;
        }
        return remindText;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        meetingDetailsTable = new javax.swing.JTable();
        meetingSaveButton = new javax.swing.JButton();
        addMeetingButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        JTableHeader header = meetingDetailsTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        meetingDetailsTable.setRowHeight(30);
        DefaultTableModel defaultTableModel = new AddMyDefaultTableModel();
        meetingDetailsTable.setModel(defaultTableModel);
        // set the minimum width  of that colum
        TableColumn reminderHideColumn = meetingDetailsTable.getColumnModel().getColumn(8);
        reminderHideColumn.setWidth(0);
        reminderHideColumn.setMaxWidth(0);
        reminderHideColumn.setMinWidth(0);
        reminderHideColumn.setResizable(false);

        // set the minimum width  of that colum
        TableColumn reminderHideColumn8 = meetingDetailsTable.getColumnModel().getColumn(9);
        reminderHideColumn8.setWidth(0);
        reminderHideColumn8.setMaxWidth(0);
        reminderHideColumn8.setMinWidth(0);
        reminderHideColumn8.setResizable(false);

        // set the minimum width  of meeting id
        TableColumn meetingId = meetingDetailsTable.getColumnModel().getColumn(10);
        meetingId.setWidth(0);
        meetingId.setMaxWidth(0);
        meetingId.setMinWidth(0);
        meetingId.setResizable(false);
        meetingDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meetingDetailsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(meetingDetailsTable);

        meetingSaveButton.setText("Save");
        meetingSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetingSaveButtonActionPerformed(evt);
            }
        });

        addMeetingButton.setText("Add Meeting");
        addMeetingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMeetingButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setText("Add New Meeting:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(addMeetingButton)
                .addGap(27, 27, 27)
                .addComponent(meetingSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetingSaveButton)
                    .addComponent(addMeetingButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meetingDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meetingDetailsTableMouseClicked

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") || SingletonClass.isView) {
        } else {
            // delete the contact row if extra added
            DefaultTableModel tableModel = (DefaultTableModel) meetingDetailsTable.getModel();
            int row = meetingDetailsTable.rowAtPoint(evt.getPoint());
            int col = meetingDetailsTable.columnAtPoint(evt.getPoint());

            //            StringBuffer meetingDeletebuffer = new StringBuffer();
            if (col == 7) {
                try {
                    if (counter > 0) {
                        counter--;
                    }

                    //******************************* meeting delete url calling
                    //                    meetingTime = meetingDetailsTable.getValueAt(row, 1) + " " + meetingDetailsTable.getValueAt(row, 2);
                    //                    meetingDeletebuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deleteMeeting").append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit).append("&").append(SingletonClass.meetingId).append("=").append( meetingDetailsTable.getValueAt(row, 9)).append("&").append(SingletonClass.userName).append("=").append(email);
                    //                    callServerUrl serverUrl1 = new callServerUrl();
                    //                    String urlString = meetingDeletebuffer.toString().replaceAll(" ", "%20");
                    //                    serverUrl1.urlProcessiong(urlString);
                    //                    tableModel.removeRow(row);
                    //                    urlString = null;
                    //                    meetingDeletebuffer = null;
                    // **************************************************************
                    if ("".equals(meetingDetailsTable.getValueAt(row, 9)) || meetingDetailsTable.getValueAt(row, 9) == null) {
                        tableModel.removeRow(row);
                    } else {
                        JOptionPane.showMessageDialog(null, "You can not delete existing meeting", "", 0);

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problem Occured! Try agian later.", "", 0);
                }
            }
            if (col == 6) {
                String selectReminderTime = "";
                String[] sport = new String[]{"Select when you want to be reminded", "5 minutes", "15 minutes", "30 minutes", "1 hour", "2 hour", "1 day"};
                if (meetingDetailsTable.getValueAt(row, 7) == null || meetingDetailsTable.getValueAt(row, 7).toString().equals("")) {
                    selectReminderTime = (String) JOptionPane.showInputDialog(this, "Reminder", "", JOptionPane.INFORMATION_MESSAGE, null, sport, "Select when you want to be reminded");
                } else {
                    selectReminderTime = (String) JOptionPane.showInputDialog(this, "Reminder", "", JOptionPane.INFORMATION_MESSAGE, null, sport, meetingDetailsTable.getValueAt(row, 7));
                }
                if (!"Select when you want to be reminded".equals(selectReminderTime) && selectReminderTime.length() > 0) {
                    meetingDetailsTable.setValueAt(selectReminderTime, row, 7);
                }
                //            JOptionPane.showMessageDialog(null, "You have selected: " + selectReminderTime);
            }

            // here user on clicking view a pop up where he can select clients privelege to recieve emails.
            if (col == 5) {
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setPreferredSize(new Dimension(200,100));
                //                JTextPane caseSumrryArea = new JTextPane();
                //                caseSumrryArea.setPreferredSize(new Dimension(100,50));
                //                caseSumrryArea.setAutoscrolls(true);
                //                scrollPane.getViewport().setView(caseSumrryArea);
                String caseSummary = "";
                if (meetingDetailsTable.getValueAt(row, 5) != null ){
                    caseSummary = meetingDetailsTable.getValueAt(row, 5).toString();
                }
                String contactname = meetingDetailsTable.getValueAt(row, 0).toString();
                CaseSummaryFrame caseSummaryFrame = new CaseSummaryFrame(caseSummary, contactname, email, meetingDetailsTable, row);
                caseSummaryFrame.setVisible(true);

                //                String message = "Case Summary:";
                //                Object[] params = {message, scrollPane};
                //                int status = JOptionPane.showConfirmDialog(this, params, " Meeting Case Summary  ", JOptionPane.OK_CANCEL_OPTION);
                //                if (status == JOptionPane.CANCEL_OPTION) {
                    //                } else {
                    //                     MeetingDetailBean meetingDetailBean = new MeetingDetailBean();
                    //                     meetingDetailBean = new ParserCaller().parseEmailXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=emailsList&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&contactName="+ meetingDetailsTable.getValueAt(row, 0)+ "&for=meeting&" + SingletonClass.userName + "=" + email, meetingDetailBean);
                    //            Date meetingDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(meetingTime);

                    //                        Calendar meetingTimeCal = Calendar.getInstance();
                    //                        Calendar currentDateCal = Calendar.getInstance();
                    //
                    //                        DateFormat meetingTimeCheckFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    //                        meetingTimeCal.setTime(meetingTimeCheckFormat.parse(meetingTime));
                    //                        currentDateCal.setTime(meetingTimeCheckFormat.parse(meetingTimeCheckFormat.format(new Date())));
                    //                        System.out.println("isAfterCurrentDate: " + meetingTimeCal);
                    //                        if (meetingTimeCal.after(currentDateCal)) {
                        //
                        //                        }
                    //                    System.out.println("first if");
                    //                    if(meetingDetailsTable.getValueAt(row, 0)!=null){
                        //                    new MeetingEmailCaseSummary(SingletonClass.txnIdentity,"Case Summary OF"+SingletonClass.clientCompanyName, newTxPanel, email, caseSumrryArea.getText(), meetingDetailBean).setVisible(true);
                        //                    String emailPriv = caseSumrryArea.getText();
                        //                    meetingDetailsTable.setValueAt(emailPriv, row, 5);
                        //                    }
                    //                }
            }
        }
    }//GEN-LAST:event_meetingDetailsTableMouseClicked

    private void meetingSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetingSaveButtonActionPerformed
            try {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                StringBuffer meetingDetailsbufferMain = new StringBuffer();
                StringBuffer meetingDetailsbuffer = null;
                boolean isDate = true;
                boolean isTime = true;
                boolean isAllFill = false;
                String location = "";
                String comment = "";
                String caseSummry = "";
                String rmndrTime = "";
                meetingDetailsbufferMain.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append("meetingDetailsTools").append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(clientName).append("&").append(SingletonClass.userName).append("=").append(email);//
                // get the total row count and then iterate the total rows data for saving the details
                DefaultTableModel model = (DefaultTableModel) meetingDetailsTable.getModel();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String meetingdate = "";
                int r = model.getRowCount();
                System.out.println("row count of table model " + r);
                boolean isSubmit = false;
                boolean isDupMeetingNotify = false;
                int dialogResult = 0;
                String contactPerson = "";
                for (int c = 0; c < r; c++) {
                    System.out.println(" counter is  ++++++++++++++++++++ " + c);
                    meetingDetailsbuffer = null;
                    meetingDetailsbuffer = new StringBuffer(meetingDetailsbufferMain); //code changed by satya
                    if (meetingDetailsTable.getValueAt(c, 0) != null) {
                        contactPerson = (String) meetingDetailsTable.getValueAt(c, 0);
                        meetingDetailsbuffer.append("&").append(SingletonClass.contactPerson).append("=").append(meetingDetailsTable.getValueAt(c, 0));//jContactBox.getSelectedItem()
                        meetingDetailsbuffer.append("&").append(SingletonClass.meetingId).append("=").append(meetingDetailsTable.getValueAt(c, 10));//jContactBox.getSelectedItem()
                        System.out.println(" selected jbox value " + jContactBox.getSelectedItem());
                    }
                    if (meetingDetailsTable.getValueAt(c, 1) != null) {
                        try {
                            DateFormat newDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

                            //                    try {
                                //                        DateFormat saveDataFormat = new SimpleDateFormat("dd-MM-yyyy");
                                //                        meetingDetailsTable.setValueAt(newDateFormat.format(saveDataFormat.parse(meetingsDetails.getScheduleDate())), listCount, 1);
                                //                    } catch (Exception ex) {
                                //                        ex.printStackTrace();
                                //                    }

                            System.out.println("meeting date: " + meetingDetailsTable.getValueAt(c, 1).toString());
                            if (isddmmyyyyDateFormat(meetingDetailsTable.getValueAt(c, 1).toString())) {
                                meetingdate = dateFormat.format(dateFormat.parse(meetingDetailsTable.getValueAt(c, 1).toString()));
                            } else {
                                meetingdate = dateFormat.format(newDateFormat.parse(meetingDetailsTable.getValueAt(c, 1).toString()));
                            }
                            dateFormat.parse(meetingdate);
                            newDateFormat = null;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            isDate = false;
                        }
                        // meetingTime = meetingDetailsTable.getValueAt(c, 1).toString();
                        //meetingDetailsbuffer.append("&").append(SingletonClass.scheduleTime).append("=").append(((String) meetingDetailsTable.getValueAt(c, 1)).trim());

                    }
                    if (meetingDetailsTable.getValueAt(c, 2) != null) {
                        try {
                            DateFormat newDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                            //timeFormat.parse(meetingDetailsTable.getValueAt(c, 2).toString());
                            if (isTimeFormat(meetingDetailsTable.getValueAt(c, 2).toString())) {
                                meetingTime = timeFormat.format(timeFormat.parse(meetingDetailsTable.getValueAt(c, 2).toString()));
                            } else {
                                meetingTime = timeFormat.format(newDateFormat.parse(meetingDetailsTable.getValueAt(c, 2).toString()));
                            }
                            meetingDetailsbuffer.append("&").append(SingletonClass.scheduleTime).append("=").append(meetingdate).append(" ").append(meetingTime.trim());
                            meetingTime = meetingdate + " " + meetingTime;
                            newDateFormat = null;
                        } catch (Exception ex) {

                            ex.printStackTrace();
                            isTime = false;
                        }

                    }
                    // read reminder time according to drop-down selected.
                    if (meetingDetailsTable.getValueAt(c, 8) != null) {

                        if (meetingDetailsTable.getValueAt(c, 8).toString().equalsIgnoreCase("5 minutes")) {
                            rmndrTime = "5";
                        }
                        if (meetingDetailsTable.getValueAt(c, 8).toString().equalsIgnoreCase("2 hour")) {
                            rmndrTime = "120";
                        }
                        if (meetingDetailsTable.getValueAt(c, 8).toString().equalsIgnoreCase("1 day")) {
                            rmndrTime = "1440";
                        }
                        if (meetingDetailsTable.getValueAt(c, 8).toString().equalsIgnoreCase("1 hour")) {
                            rmndrTime = "60";
                        }
                        if (meetingDetailsTable.getValueAt(c, 8).toString().equalsIgnoreCase("30 minutes")) {
                            rmndrTime = "30";
                        }
                        if (meetingDetailsTable.getValueAt(c, 8).toString().equalsIgnoreCase("15 minutes")) {
                            rmndrTime = "15";
                        }
                        meetingDetailsbuffer.append("&").append(SingletonClass.remienderTime).append("=").append(rmndrTime);
                    }
                    if (meetingDetailsTable.getValueAt(c, 3) != null) {
                        meetingDetailsbuffer.append("&").append(SingletonClass.place).append("=").append((URLEncoder.encode((String) meetingDetailsTable.getValueAt(c, 3))).trim());
                        location = meetingDetailsTable.getValueAt(c, 3).toString().trim();
                    }
                    if (meetingDetailsTable.getValueAt(c, 4) != null) {
                        if (meetingDetailsTable.getValueAt(c, 9) != null) {

                            if (!meetingDetailsTable.getValueAt(c, 9).toString().equals(meetingDetailsTable.getValueAt(c, 4).toString())) {
                                comment = (String) meetingDetailsTable.getValueAt(c, 9) + "," + (String) meetingDetailsTable.getValueAt(c, 4);
                            } else {
                                comment = (String) meetingDetailsTable.getValueAt(c, 4);
                            }
                        } else {
                            comment = (String) meetingDetailsTable.getValueAt(c, 4);
                        }
                        meetingDetailsbuffer.append("&").append(SingletonClass.meetingComment).append("=").append(URLEncoder.encode((comment).trim()));
                    }
                    if (meetingDetailsTable.getValueAt(c, 5) != null) {
                        caseSummry = (String) meetingDetailsTable.getValueAt(c, 5);
                        meetingDetailsbuffer.append("&").append(SingletonClass.caseSummry).append("=").append(URLEncoder.encode((caseSummry).trim()));
                    }
                    if (meetingDetailsTable.getValueAt(c, 0) == null || meetingDetailsTable.getValueAt(c, 0).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please select the name of contact person.", "", 0);
                        isAllFill = false;
                    } else if (meetingDetailsTable.getValueAt(c, 1) == null || meetingDetailsTable.getValueAt(c, 1).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter the meeting date.", "", 0);
                        isAllFill = false;
                    } else if (meetingDetailsTable.getValueAt(c, 2) == null || meetingDetailsTable.getValueAt(c, 2).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please enter the meeting time.", "", 0);
                        isAllFill = false;
                    } //            else if (meetingDetailsTable.getValueAt(c, 3) == null || meetingDetailsTable.getValueAt(c, 3).toString().equals("")) {
                        //                JOptionPane.showMessageDialog(null, "Please please select the reminder time.", "", 0);
                        //            }
                    else if (meetingDetailsTable.getValueAt(c, 3) == null || meetingDetailsTable.getValueAt(c, 3).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill the meeting place.", "", 0);
                        isAllFill = false;
                    } else if (meetingDetailsTable.getValueAt(c, 4) == null || meetingDetailsTable.getValueAt(c, 4).toString().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please give  your comment.", "", 0);
                        isAllFill = false;
                    } else {
                        isAllFill = true;
                    }
                    if (isAllFill) {
                        if (isDate) {
                            String urlString = meetingDetailsbuffer.toString().replaceAll(" ", "%20");

                            callServerUrl serverUrl1 = new callServerUrl();

                            //**************************
                            // save url calling
                            String responseDuplicate = "valid";
                            if ((meetingDetailsTable.getValueAt(c, 10).toString()==null ||
                                meetingDetailsTable.getValueAt(c, 10).toString().equals("") || meetingDetailsTable.getValueAt(c, 10).toString().equals("null"))
                            || (meetingDetailsTable.getValueAt(c, 10).toString()!=null &&
                                !meetingDetailsTable.getValueAt(c, 10).toString().equals("") && !meetingDetailsTable.getValueAt(c, 10).toString().equals("null") && !mailScreenCheckMap.containsKey(meetingTime) )  ) {
                            StringBuffer meetingDuplicateBuffer = new StringBuffer();
                            meetingDuplicateBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append("meetingWarn").append("&").append(SingletonClass.userName).append("=").append(email);//
                            meetingDuplicateBuffer.append("&").append(SingletonClass.meetingId).append("=").append(meetingDetailsTable.getValueAt(c, 10));
                            meetingDuplicateBuffer.append("&").append(SingletonClass.scheduleTime).append("=").append(meetingTime.trim());
                            responseDuplicate = serverUrl1.urlProcessiong(meetingDuplicateBuffer.toString().replaceAll(" ", "%20"));
                            System.out.println("responseDuplicate: " + responseDuplicate);
                        }
                        //                            String responseDuplicate = "Already";
                        if (responseDuplicate.equals("valid")) {

                            //**********************
                            String response = serverUrl1.urlProcessiong(urlString);
                            System.out.println("response: " + response);
                            if (response.equalsIgnoreCase("invalid")) {
                                isSubmit = false;
                                serverUrl1 = null;
                                JOptionPane.showMessageDialog(null, "Problem Occured! Try agian later.", "", 0);
                                break;
                            } else {
//                                callMeetingEmail(contactPerson, rmndrTime, comment, location);
                                System.out.println("*************************!1111111111111111");
                                    if (meetingDetailsTable.getValueAt(c, 8) != null && !meetingDetailsTable.getValueAt(c, 8).toString().equals("")
                                        && !meetingDetailsTable.getValueAt(c, 8).toString().equals("null")) {
                                        callEmailReminder(contactPerson, rmndrTime, URLEncoder.encode(comment), URLEncoder.encode(location), meetingTime, email, c);
                                }
                                isSubmit = true;
                                System.out.println("clientDetailsBuffer in MeetingDetailsPanel: " + urlString + "   and txId:  " + SingletonClass.txIdEdit);
                                serverUrl1 = null;
                            }
                            //***********************************
                        } else if (responseDuplicate.equalsIgnoreCase("Already")) {
                            if (!isDupMeetingNotify && dialogResult != JOptionPane.NO_OPTION) {
                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                dialogResult = JOptionPane.showConfirmDialog(null, "Another meeting is scheduled at this time. would you like to schedule this meeting.", "Warning", dialogButton);
                            }
                            if ((dialogResult == JOptionPane.YES_OPTION || !isDupMeetingNotify) && dialogResult != JOptionPane.NO_OPTION) {
                                String response = serverUrl1.urlProcessiong(urlString);
                                System.out.println("response: " + response);
                                if (response.equalsIgnoreCase("invalid")) {
                                    isSubmit = false;
                                    serverUrl1 = null;
                                    JOptionPane.showMessageDialog(null, "Problem Occured! Try agian later.", "", 0);
                                    break;
                                } else {
//                                    callMeetingEmail(contactPerson, rmndrTime, comment, location);
//                                    if (!mailScreenCheckMap.containsKey(meetingTime)) {
                                        if (meetingDetailsTable.getValueAt(c, 8) != null && !meetingDetailsTable.getValueAt(c, 8).toString().equals("")
                                            && !meetingDetailsTable.getValueAt(c, 8).toString().equals("null")) {
                                            callEmailReminder(contactPerson, rmndrTime, URLEncoder.encode(comment), URLEncoder.encode(location), meetingTime, email, c);
                                        }
//                                    }
                                    isSubmit = true;
                                    isDupMeetingNotify = true;
                                    System.out.println("clientDetailsBuffer in MeetingDetailsPanel: " + urlString + "   and txId:  " + SingletonClass.txIdEdit);
                                    serverUrl1 = null;
                                }
                            } else if (dialogResult == JOptionPane.NO_OPTION) {
                                isSubmit = false;
                                break;
                            }
                        }
                        //*******************************************
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill the date in dd-MM-yyyy format.", "", 0);
                        break;
                    }
                    if (!isTime) {
                        JOptionPane.showMessageDialog(null, "Please fill the Time in HH:mm format.", "", 0);
                        break;
                    }
                }

            }
            if (isSubmit) {
                    this.setCursor(Cursor.getDefaultCursor());
                    JOptionPane.showMessageDialog(null, "Meeting Details Saved Successfully.", "", 1);
                    this.dispose();
             
                try {
                    SingletonClass.contactPersons.removeAllItems();
                } catch (Exception exception) {
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception ex) {
            ex.printStackTrace();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_meetingSaveButtonActionPerformed

    private void addMeetingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMeetingButtonActionPerformed
            DefaultTableModel tableModel = (DefaultTableModel) meetingDetailsTable.getModel();
            System.out.println("counter: " + counter);
            tableModel.insertRow(counter, new Object[]{});
            meetingDetailsTable.setValueAt(SingletonClass.TxnClientName, counter, 0);
            meetingDetailsTable.setValueAt(SingletonClass.clientRegion, counter, 3);
            meetingDetailsTable.setValueAt("", counter, 10);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            meetingDetailsTable.setValueAt(dateFormat.format(new Date()).toString(), counter, 1);
            counter++;
    }//GEN-LAST:event_addMeetingButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddNewMeeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewMeeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewMeeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewMeeting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new AddNewMeeting().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMeetingButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable meetingDetailsTable;
    private javax.swing.JButton meetingSaveButton;
    // End of variables declaration//GEN-END:variables

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
//time validation for update

    private boolean isTimeFormat(String dateStr) {
        boolean isTime = false;
        DateFormat sdFormat = new SimpleDateFormat("HH:mm");
        try {
            sdFormat.parse(dateStr);
            isTime = true;
            sdFormat = null;
        } catch (Exception ex) {
            sdFormat = null;
            isTime = false;
        }
        return isTime;

    }
//call email window
    boolean isEmailScreenOpen = false;

    //call email reminder 
    private void callEmailReminder(String contactPerson, String rmndrTime, String comment, String location, String meetingsTime, String emailId, int listCount) {
        SimpleDateFormat dateFormat, userdateFormat = null;
        MeetingDetailBean meetingDetailBean = null;
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userdateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            meetingDetailBean = new MeetingDetailBean();
            meetingDetailBean.setContactPerson(contactPerson);
            meetingDetailBean.setRmName(emailId);
            meetingDetailBean.setReminderTime(rmndrTime);
            meetingDetailBean.setPurpose(comment);
            meetingDetailBean.setMeetingPlace(location);
            meetingDetailBean.setDateTime(dateFormat.format(userdateFormat.parse(meetingsTime)));
            meetingDetailBean = new ParserCaller().parseEmailXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=emailsList&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&contactName=" + contactPerson + "&for=meeting&" + SingletonClass.userName + "=" + email, meetingDetailBean);

            StringBuffer bufer = new StringBuffer(SingletonClass.serverIpPort + "EmailReminder/ReminderSetup?Client=" + URLEncoder.encode(SingletonClass.clientCompanyName) + "&remindTime=" + rmndrTime + "&RmName=" + email + "&Location=" + location + "&emailId=" + email + "&meetingSchedule=" + meetingDetailBean.getDateTime() + "&companyName=" + meetingDetailBean.getCompanyName() + "&turnover=" + meetingDetailBean.getTurnOver() + "&contactPerson=" + meetingDetailBean.getContactPerson() + "&mobNumber=" + meetingDetailBean.getMobileNo() + "&purpose=" + meetingDetailBean.getPurpose());
            System.out.println("reminder URL: " + bufer);

            String strBuffer = bufer.toString().replaceAll(" ", "%20");
            if (SingletonClass.isEdit) {
                if (!(linkedMap.get(listCount) != null && linkedMap.get(listCount).equals(rmndrTime))) {
                    new ReminderSettingThread(strBuffer).start();
                }
            } else {
                new ReminderSettingThread(strBuffer).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        meetingDetailBean = null;
    }
}

