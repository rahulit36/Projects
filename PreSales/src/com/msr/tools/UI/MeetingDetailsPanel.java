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
import javax.swing.*;
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
 * @since 29 jan 2013
 */
class MyDefaultTableModel extends DefaultTableModel {

    public MyDefaultTableModel() {
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

public class MeetingDetailsPanel extends javax.swing.JPanel {

    public JComboBox jContactBox = new JComboBox();
    MainScreen newTxPanel = null;
    int counter = 0;
    String email = "";
    String meetingTime = "";
    JComboBox jcomb = new JComboBox();
    Map<String, String> mailScreenCheckMap = null;
    JTabbedPane tabbedPane = null;
    MainScreen maiScreen = null;

    /**
     * Creates new form MeetingDetailsPanel
     */
    public MeetingDetailsPanel(String email, String accName, MainScreen newTxPanel, JPanel toolsPanel) {
        initComponents();
        this.email = email;
        this.newTxPanel = newTxPanel;
        this.tabbedPane = tabbedPane;
        this.maiScreen = maiScreen;
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
        col6.setWidth(0);
        col6.setMaxWidth(0);
        col6.setMinWidth(0);
        col6.setResizable(false);

        TableColumn reminderCol3 = meetingDetailsTable.getColumnModel().getColumn(6);
        reminderCol3.setMaxWidth(40);
        reminderCol3.setCellRenderer(new IconTableCellRenderer("Alarm"));

//        // Create our cell editor
        System.out.println("email: " + email);
        if (email != null) {
            setMeetingDetails();
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        meetingDetailsTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        meetingOfCombo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        JTableHeader header = meetingDetailsTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        meetingDetailsTable.setRowHeight(30);
        DefaultTableModel defaultTableModel = new MyDefaultTableModel();
        meetingDetailsTable.setModel(defaultTableModel);
        // set the minimum width  of that colum
        TableColumn reminderHideColumn = meetingDetailsTable.getColumnModel().getColumn(8);
        reminderHideColumn.setWidth(0);
        reminderHideColumn.setMaxWidth(0);
        reminderHideColumn.setMinWidth(0);
        reminderHideColumn.setResizable(false);

        TableColumn col6 = meetingDetailsTable.getColumnModel().getColumn(7);
        col6.setWidth(0);
        col6.setMaxWidth(0);
        col6.setMinWidth(0);
        col6.setResizable(false);
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

        jButton1.setText("Send Case Summary");

        jButton2.setText("Send Meeting Mail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        meetingOfCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Today", "After 1 Day", "After 2 Days", "After 3 Days","After 4 Days" }));
        meetingOfCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                meetingOfComboItemStateChanged(evt);
            }
        });

        jLabel1.setText("Meetings Of:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(meetingOfCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetingOfCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents
//get meeting data in map form (code by satya)

    private List<MeetingsDetails> setMeetingDetails() {
        MeetingDetailsParser meetingDetailsParser = null;
        XMLReader xmlReader = null;
        List<MeetingsDetails> meetingList = null;
        StringBuffer meetingUrl = null;
        List<String> contactList = null;
        try {
 
            
            meetingUrl = new StringBuffer();

//            ParserCaller parserCaller = new ParserCaller();
//            contactList = parserCaller.parseClientContactDetailsXmlOnly(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(cli) + "&" + SingletonClass.userName + "=" + email);
            meetingUrl.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=").append(SingletonClass.toolsMeetingDetailsXML).append("&").append(SingletonClass.meetingOf).append("=")
                    .append(URLEncoder.encode(meetingOfCombo.getSelectedItem().toString())).append("&").append(SingletonClass.userName).append("=").append(email);
            System.out.println("meetingUrl: " + meetingUrl);
            meetingDetailsParser = new MeetingDetailsParser();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(meetingDetailsParser);
            xmlReader.setErrorHandler(meetingDetailsParser);
            xmlReader.parse(new InputSource(meetingUrl.toString()));
            meetingList = meetingDetailsParser.MeetingDataList();
            setMeetingDataIntoFields(meetingList, contactList);
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
    List<MeetingsDetails> meetingDataListClassVar = null;

    private void setMeetingDataIntoFields(List<MeetingsDetails> meetingDataList, List<String> contactList) {
//        System.out.println(" list size " + meetingDataList.size() + " contactList " + contactList.size());
        DefaultTableModel tableModel = (DefaultTableModel) meetingDetailsTable.getModel();
//        //  counter=0;
        MeetingsDetails meetingsDetails = null;
//        TableColumn col0 = meetingDetailsTable.getColumnModel().getColumn(0);
//        System.out.println(" label is " + col0.getHeaderValue());
//        jContactBox.removeAllItems();
//        col0.setCellEditor(new DefaultCellEditor(setcomboListItems(contactList)));
        
        
//        TableColumn col3 = meetingDetailsTable.getColumnModel().getColumn(3);
//        JComboBox places= new JComboBox(new String[]{"South Delhi","West Delhi","North Delhi","East Delhi","Central Delhi","Gurgaon","Faridabad","Gaziabad","Noida","Others"});
//        col3.setCellEditor(new DefaultCellEditor(places));
        
        if (meetingDataList.size() > 0) {
            meetingDataListClassVar = meetingDataList;
            mailScreenCheckMap = null;
            mailScreenCheckMap = new HashMap<String, String>();
            linkedMap = null;
            linkedMap = new LinkedHashMap<String, String>();
            for (int listCount = 0; listCount < meetingDataList.size(); listCount++) {
                try {
                    tableModel.insertRow(listCount, new Object[]{});
                    meetingsDetails = meetingDataList.get(listCount);
                    meetingDetailsTable.setValueAt(meetingsDetails.getContactPerson(), listCount, 0);
                    meetingDetailsTable.setValueAt(meetingsDetails.getScheduleDate(), listCount, 1);
                    meetingDetailsTable.setValueAt(meetingsDetails.getScheduleTime(), listCount, 2);
                    mailScreenCheckMap.put(meetingsDetails.getScheduleDate() + " " + meetingsDetails.getScheduleTime(), "_" + meetingsDetails.getContactPerson() + "_" + meetingsDetails.getPlace() + "_");

                    if (meetingsDetails.getRemienderTime() != null) {
                        meetingDetailsTable.setValueAt(minutesConversion(meetingsDetails.getRemienderTime().trim()), listCount, 8);
                    }
                    linkedMap.put(listCount + "", minutesConversion(meetingsDetails.getRemienderTime().trim()));
                    meetingDetailsTable.setValueAt(meetingsDetails.getPlace(), listCount, 3);

//                    Calendar meetingTimeCal = Calendar.getInstance();
//                    Calendar currentDateCal = Calendar.getInstance();
//
//                    DateFormat meetingTimeCheckFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//                    meetingTimeCal.setTime(meetingTimeCheckFormat.parse(meetingsDetails.getScheduleDate() + " " + meetingsDetails.getScheduleTime()));
//                    currentDateCal.setTime(meetingTimeCheckFormat.parse(meetingTimeCheckFormat.format(new Date())));
//                    System.out.println("isAfterCurrentDate: " + meetingTimeCal);
//                    if (meetingTimeCal.after(currentDateCal)) {
//                        meetingDateAndTimeList.add(meetingsDetails.getContactPerson());
//                        meetingDateAndTimeList.add(meetingsDetails.getPlace());
//                    }

                    meetingDetailsTable.setValueAt(meetingsDetails.getMeetingComment(), listCount, 4);
                    meetingDetailsTable.setValueAt(meetingsDetails.getCaseSummry(), listCount, 5);
                    meetingDetailsTable.setValueAt(meetingsDetails.getMeetingComment(), listCount, 9);
                    meetingDetailsTable.setValueAt(meetingsDetails.getMeetingId(), listCount, 10);
                    System.out.println(" counter " + listCount + " reminder time is: " + meetingsDetails.getRemienderTime());
   
                    counter++;
                } catch (Exception ex) {
                    Logger.getLogger(MeetingDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            mailScreenCheckMap = new HashMap<String, String>();
        }
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

           MeetingEmailScreen x = new MeetingEmailScreen("", email  ,meetingDataListClassVar);
           x.setVisible(true);
            x.setExtendedState(JFrame.MAXIMIZED_BOTH);  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void meetingOfComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_meetingOfComboItemStateChanged
        DefaultTableModel dm = (DefaultTableModel) meetingDetailsTable.getModel();
        dm.getDataVector().removeAllElements();
        meetingDetailsTable.revalidate();
        setMeetingDetails();
    }//GEN-LAST:event_meetingOfComboItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable meetingDetailsTable;
    private javax.swing.JComboBox meetingOfCombo;
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

//    private void callMeetingEmail(String contactPerson, String rmndrTime, String comment, String location) {
//        try {
//
//            MeetingDetailBean meetingDetailBean = new MeetingDetailBean();
//            meetingDetailBean.setContactPerson(contactPerson);
//            meetingDetailBean.setRmName(email);
//            meetingDetailBean.setReminderTime(rmndrTime);
//            meetingDetailBean.setPurpose(comment);
//            meetingDetailBean.setMeetingPlace(location);
//            meetingDetailBean.setDateTime(meetingTime);
//            meetingDetailBean = new ParserCaller().parseEmailXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=emailsList&" + SingletonClass.txNo + "=" + SingletonClass.txIdEdit + "&contactName=" + contactPerson + "&for=meeting&" + SingletonClass.userName + "=" + email, meetingDetailBean);
////            Date meetingDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(meetingTime);
//
//
////                        Calendar meetingTimeCal = Calendar.getInstance();
////                        Calendar currentDateCal = Calendar.getInstance();
////
////                        DateFormat meetingTimeCheckFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
////                        meetingTimeCal.setTime(meetingTimeCheckFormat.parse(meetingTime));
////                        currentDateCal.setTime(meetingTimeCheckFormat.parse(meetingTimeCheckFormat.format(new Date())));
////                        System.out.println("isAfterCurrentDate: " + meetingTimeCal);
////                        if (meetingTimeCal.after(currentDateCal)) {
////                            
////                        }
//           
//            if (!mailScreenCheckMap.containsKey(meetingTime)) {
//                System.out.println("first if");
//                new MeetingEmailScreen(SingletonClass.txnIdentity, SingletonClass.meetingMailSubject, newTxPanel, email, rmndrTime, meetingTime + ":00", location, meetingDataListClassVar).setVisible(true);
//                isEmailScreenOpen = true;
//            } else if (!mailScreenCheckMap.get(meetingTime).contains("_" + contactPerson + "_") || !mailScreenCheckMap.get(meetingTime).contains("_" + URLDecoder.decode(location) + "_")) {
//                //  System.out.println("mailScreenCheckMap "+mailScreenCheckMap +"++++++++++++++++++++++++"+contactPerson +"============"+URLDecoder.decode(location));
//                System.out.println("else if");
//                new MeetingEmailScreen(SingletonClass.txnIdentity, SingletonClass.meetingMailSubject, newTxPanel, email, rmndrTime, meetingTime + ":00", location,meetingDataListClassVar).setVisible(true);
//                isEmailScreenOpen = true;
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(MeetingDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

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
