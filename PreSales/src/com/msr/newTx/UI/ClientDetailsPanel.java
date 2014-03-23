/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import com.msr.tools.UI.IconTableCellRenderer;
import com.msr.tracking.UI.MainScreen;
import com.msr.util.SingletonClass;
import com.msr.util.callServerUrl;
import com.parser.ParserCaller;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
//import org.xm

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class ClientDetailsPanel extends javax.swing.JPanel {

    NewTxPanel newTxPanel = null;
    String email = "";
    int counter = 0;
    JTextField appNameTextField = null;
    JTextField benNameTextField = null;
    JComboBox benNameCombobox = null;
    List<String> clientList = null;
    List<String> allClientList = null;
    List<String> benList = null;
    List<String> contactList = null;
    Map<String, String> editViewTxnMap = null;
    boolean isContactEmail = false;
    boolean isContactTableAdd = false;
    boolean isFillClient = false;
    MainScreen maiScreen = null;

    /**
     * Creates new form ClientDetailsPanel
     */
    public ClientDetailsPanel(String email, String accName, NewTxPanel newTxPanel, MainScreen maiScreen) {
        this.newTxPanel = newTxPanel;
        this.email = email;
        this.maiScreen = maiScreen;
        initComponents();
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
//        appExpBusiness.setEnabled(false);
//        benExpBusiness.setEnabled(false);

        SingletonClass.contactPersons = new JComboBox();
        System.out.println("TrackingTabelPanel.isEdit: " + SingletonClass.isEdit);
        appNameTextField = new JTextField();
        appNameTextField.setSize(280, 28);
        appNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                appNameTextFieldFocusLost(evt);
            }
        });
        appNamePanel.add(appNameTextField);


        benNameTextField = new JTextField();
        benNameTextField.setSize(280, 28);
        benNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                benNameTextFieldFocusLost(evt);
            }
        });

        benNameCombobox = new JComboBox();
        benNameCombobox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                benNameTextFieldFocusLost(evt);
            }
        });
        // call  for edit or session .
        setTxnValuesInEdit();
        // call for view transaction.
        setValuesForView();
        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst")) {
            setValuesForNonEdit();
        }
        // add delete button for contact person deletion
        TableColumn col5 = contactsByCheckboxTable.getColumnModel().getColumn(6);
        col5.setMaxWidth(50);
        col5.setCellRenderer(new IconTableCellRenderer("Delete"));
        TableColumn col1 = contactsByCheckboxTable.getColumnModel().getColumn(0);
        col1.setMaxWidth(50);
        col1.setWidth(50);
        ParserCaller parserCaller = new ParserCaller();
        clientList = parserCaller.parseClientListXml(email);
        allClientList = parserCaller.parseAllClientListXml(email);
        if (applicantCheckbox.isSelected()) {
            benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
        }

        if (beneficiaryCheckbox.isSelected()) {
            benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
        }

        if (clientList != null) {

            Collections.sort(clientList);
            AutoCompleteDecorator.decorate(appNameTextField, clientList, false);

            AutoCompleteDecorator.decorate(benNameTextField, clientList, false);

            benNamePanel.add(benNameTextField);

        }

        System.out.println("clientList: " + clientList);
        if (SingletonClass.isEdit == false) {
            clearEditButton.setVisible(false);
            jScrollPane2.repaint();
        }
    }

    // set value when transaction is edited and also when transaction is new created and transaction has its session untill tab is changed.
    public void setTxnValuesInEdit() {
// check if transaction id is somthing or not..
        if (SingletonClass.txIdEdit != null && !SingletonClass.txIdEdit.equals("")) {

            ParserCaller parserCaller = new ParserCaller();
            editViewTxnMap = parserCaller.parseEditViewXML(email, SingletonClass.txIdEdit);

            if (editViewTxnMap.get("type") != null && !editViewTxnMap.get("type").equals("null")) {
                transactionType.setSelectedItem(editViewTxnMap.get("type").trim());
            }
            if (editViewTxnMap.get("limitFor").equals(editViewTxnMap.get("appName"))) {
                limitForApplicantCheck.setSelected(true);
            }
            if (editViewTxnMap.get("limitFor").equals(editViewTxnMap.get("benName"))) {
                limitForBeneficiaryCheck.setSelected(true);
            }
            if (editViewTxnMap.get("client") != null && !editViewTxnMap.get("client").equals("null")) {
                if (editViewTxnMap.get("client").equals(editViewTxnMap.get("appName"))) {
                    applicantCheckbox.setSelected(true);
                    benComprehensiveSpace.setEnabled(false);
                    benSolution.setEnabled(false);
                    contactList = parserCaller.parseClientContactDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(editViewTxnMap.get("appName").trim()) + "&" + SingletonClass.userName + "=" + email);
                    System.out.println("contactList: " + contactList.size() + "  contactList  " + contactList);
                    if (contactList.size() > 0) {

                        counter = 0;
                        Iterator it = contactList.iterator();
                        Map<String, String> contactMap = null;
                        while (it.hasNext()) {
                            contactMap = (Map) it.next();
                            if (contactMap != null && contactMap.size() > 0) //code changed by satya
                            {
                                TableColumn col1 = contactsByCheckboxTable.getColumnModel().getColumn(0);
                                col1.setMaxWidth(50);
                                col1.setWidth(50);
                                DefaultTableModel tableModel = (DefaultTableModel) contactsByCheckboxTable.getModel();
                                tableModel.insertRow(counter, new Object[]{});
                                contactsByCheckboxTable.setValueAt(counter + 1, counter, 0);
                                contactsByCheckboxTable.setValueAt(contactMap.get("ccontName"), counter, 1);
                                if (contactMap.get("ccontactDesignation") != null && !"null".equals(contactMap.get("ccontactDesignation"))) {
                                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactDesignation"), counter, 2);
                                }
                                if (contactMap.get("ccontactMobile") != null && !"null".equals(contactMap.get("ccontactMobile"))) {
                                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactMobile"), counter, 3);
                                }
                                if (contactMap.get("ccontactEmail") != null && !"null".equals(contactMap.get("ccontactEmail"))) {
                                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactEmail"), counter, 4);
                                }
                                contactsByCheckboxTable.setValueAt(contactMap.get("notifyStr"), counter, 5);
                                counter++;
                            }
                        }
                    }

                }
                if (editViewTxnMap.get("client").equals(editViewTxnMap.get("benName"))) {
                    beneficiaryCheckbox.setSelected(true);
                    appComprehensiveSpace.setEnabled(false);
                    appSolution.setEnabled(false);
                    contactList = parserCaller.parseClientContactDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(editViewTxnMap.get("benName").trim()) + "&" + SingletonClass.userName + "=" + email);
                    System.out.println("contactList size: " + contactList.size() + "  contactList  " + contactList);
                    if (contactList.size() > 0) {

                        counter = 0;
                        Iterator it = contactList.iterator();
                        Map<String, String> contactMap = null;
                        while (it.hasNext()) {
                            contactMap = (Map) it.next();
                            if (contactMap != null && contactMap.size() > 0) //code changed by satya
                            {
                                TableColumn col1 = contactsByCheckboxTable.getColumnModel().getColumn(0);
                                col1.setMaxWidth(50);
                                col1.setWidth(50);
                                DefaultTableModel tableModel = (DefaultTableModel) contactsByCheckboxTable.getModel();
                                tableModel.insertRow(counter, new Object[]{});
                                contactsByCheckboxTable.setValueAt(counter + 1, counter, 0);
                                contactsByCheckboxTable.setValueAt(contactMap.get("ccontName"), counter, 1);
                                if (contactMap.get("ccontactDesignation") != null && !"null".equals(contactMap.get("ccontactDesignation"))) {
                                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactDesignation"), counter, 2);
                                }
                                if (contactMap.get("ccontactMobile") != null && !"null".equals(contactMap.get("ccontactMobile"))) {
                                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactMobile"), counter, 3);
                                }
                                if (contactMap.get("ccontactEmail") != null && !"null".equals(contactMap.get("ccontactEmail"))) {
                                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactEmail"), counter, 4);
                                }
                                contactsByCheckboxTable.setValueAt(contactMap.get("notifyStr"), counter, 5);
                                counter++;
                            }
                        }
                    }

                }
            }
            if (editViewTxnMap.get("appName") != null && !editViewTxnMap.get("appName").equals("null")) {
                appNameTextField.setText(editViewTxnMap.get("appName").trim());
            }
            if (editViewTxnMap.get("appAddrs") != null && !editViewTxnMap.get("appAddrs").equals("null")) {
                applicantAddrs.setText(editViewTxnMap.get("appAddrs"));
            } else {
                applicantAddrs.setText("");
            }
            if (editViewTxnMap.get("appPhn") != null && !editViewTxnMap.get("appPhn").equals("null")) {
                appPhNo.setText(editViewTxnMap.get("appPhn"));
            } else {
                appPhNo.setText("");
            }
            if (editViewTxnMap.get("appRegion") != null && !editViewTxnMap.get("appRegion").equals("null")) {
                appRegion.setSelectedItem(editViewTxnMap.get("appRegion"));
            } else {
                appRegion.setSelectedItem("");
            }
            if (editViewTxnMap.get("appFax") != null && !editViewTxnMap.get("appFax").equals("null")) {
                appFax.setText(editViewTxnMap.get("appFax"));
            } else {
                appFax.setText("");
            }
            if (editViewTxnMap.get("appTurnOver") != null && !editViewTxnMap.get("appTurnOver").equals("null")) {
                appTurnOver.setText(editViewTxnMap.get("appTurnOver"));
            } else {
                appTurnOver.setText("");
            }
            if (editViewTxnMap.get("appcategory") != null && !editViewTxnMap.get("appcategory").equals("null")) {
                appCategory.setSelectedItem(editViewTxnMap.get("appcategory"));
            } else {
                appCategory.setSelectedIndex(-1);
            }
            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && applicantCheckbox.isSelected()) {
                appExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
//                appExpBusiness.setEnabled(true);
            } else {
                appExpBusiness.setText("");
            }
            if (editViewTxnMap.get("compSpace") != null && !editViewTxnMap.get("compSpace").equals("null") && applicantCheckbox.isSelected()) {
                appComprehensiveSpace.setText(editViewTxnMap.get("compSpace"));
                SingletonClass.clientCompSpace = editViewTxnMap.get("compSpace");
//                appExpBusiness.setEnabled(true);
            } else {
                appComprehensiveSpace.setText("");
            }
            if (editViewTxnMap.get("solution") != null && !editViewTxnMap.get("solution").equals("null") && applicantCheckbox.isSelected()) {
                appSolution.setText(editViewTxnMap.get("solution"));
                SingletonClass.clientSolution = editViewTxnMap.get("solution");
//                appExpBusiness.setEnabled(true);
            } else {
                appSolution.setText("");
            }
//            if (editViewTxnMap.get("applimitAmount") != null && !editViewTxnMap.get("applimitAmount").equals("null")) {
//                appLimitAmount.setText(editViewTxnMap.get("applimitAmount"));
//            }

            // set beneficiary details of that transaction which is edited or viewed

            if (editViewTxnMap.get("benName") != null && !editViewTxnMap.get("benName").equals("null")) {
                benNameTextField.setText(editViewTxnMap.get("benName").trim());
            }
            if (editViewTxnMap.get("benAddrs") != null && !editViewTxnMap.get("benAddrs").equals("null")) {
                beneficiaryAddrs.setText(editViewTxnMap.get("benAddrs"));
            } else {
                beneficiaryAddrs.setText("");
            }
            if (editViewTxnMap.get("benPhn") != null && !editViewTxnMap.get("benPhn").equals("null")) {
                beneficiaryPhNo.setText(editViewTxnMap.get("benPhn"));
            } else {
                beneficiaryPhNo.setText("");
            }
            if (editViewTxnMap.get("benRegion") != null && !editViewTxnMap.get("benRegion").equals("null")) {
                benRegion.setSelectedItem(editViewTxnMap.get("benRegion"));
            } else {
                benRegion.setSelectedItem("");
            }
            if (editViewTxnMap.get("benFax") != null && !editViewTxnMap.get("benFax").equals("null")) {
                beneficiaryFax.setText(editViewTxnMap.get("benFax"));
            } else {
                beneficiaryFax.setText("");
            }
            if (editViewTxnMap.get("benTurnOver") != null && !editViewTxnMap.get("benTurnOver").equals("null")) {
                beneficiaryTurnOver.setText(editViewTxnMap.get("benTurnOver"));
            } else {
                beneficiaryTurnOver.setText("");
            }
            if (editViewTxnMap.get("bencategory") != null && !editViewTxnMap.get("bencategory").equals("null")) {
                beneficiaryCategory.setSelectedItem(editViewTxnMap.get("bencategory"));
            } else {
                beneficiaryCategory.setSelectedIndex(-1);
            }
            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && beneficiaryCheckbox.isSelected()) {
                benExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
//                benExpBusiness.setEnabled(true);
            } else {
                benExpBusiness.setText("");
            }
            if (editViewTxnMap.get("compSpace") != null && !editViewTxnMap.get("compSpace").equals("null") && beneficiaryCheckbox.isSelected()) {
                benComprehensiveSpace.setText(editViewTxnMap.get("compSpace"));
                SingletonClass.clientCompSpace = editViewTxnMap.get("compSpace");
            } else {
                benComprehensiveSpace.setText("");
            }
            if (editViewTxnMap.get("solution") != null && !editViewTxnMap.get("solution").equals("null") && beneficiaryCheckbox.isSelected()) {
                benSolution.setText(editViewTxnMap.get("solution"));
                SingletonClass.clientSolution = editViewTxnMap.get("solution");
//                appExpBusiness.setEnabled(true);
            } else {
                benSolution.setText("");
            }
//            if (editViewTxnMap.get("benlimitAmount") != null && !editViewTxnMap.get("benlimitAmount").equals("null")) {
//                benlimitAmount.setText(editViewTxnMap.get("benlimitAmount"));
//            }
        } else {

            SingletonClass.txStatusSelect = "Identification Of Client";
        }
    }

    public void setValuesForView() {
        if (SingletonClass.isView == true) {

            if (SingletonClass.txIdEdit != null && !SingletonClass.txIdEdit.equals("")) {
                transactionType.setEnabled(false);
                transactionType.setFont(new java.awt.Font("Tahoma", 1, 13));
                applicantCheckbox.setEnabled(false);
                applicantCheckbox.setFont(new java.awt.Font("Tahoma", 1, 13));
                beneficiaryCheckbox.setEnabled(false);
                beneficiaryCheckbox.setFont(new java.awt.Font("Tahoma", 1, 13));
                appNameTextField.setEditable(false);
                appNameTextField.setFont(new java.awt.Font("Tahoma", 1, 13));
                applicantAddrs.setEditable(false);
                applicantAddrs.setFont(new java.awt.Font("Tahoma", 1, 13));
                appPhNo.setEditable(false);
                appPhNo.setFont(new java.awt.Font("Tahoma", 1, 13));
                appFax.setEditable(false);
                appFax.setFont(new java.awt.Font("Tahoma", 1, 13));
                appTurnOver.setEditable(false);
                appTurnOver.setFont(new java.awt.Font("Tahoma", 1, 13));
                appCategory.setEnabled(false);
                appCategory.setFont(new java.awt.Font("Tahoma", 1, 13));
//                appLimitAmount.setEditable(false);
//                appLimitAmount.setFont(new java.awt.Font("Tahoma", 1, 13));

                // set beneficiary details to default

                benNameTextField.setEditable(false);
                benNameTextField.setFont(new java.awt.Font("Tahoma", 1, 13));
                beneficiaryAddrs.setEditable(false);
                beneficiaryAddrs.setFont(new java.awt.Font("Tahoma", 1, 13));
                beneficiaryPhNo.setEditable(false);
                beneficiaryPhNo.setFont(new java.awt.Font("Tahoma", 1, 13));
                beneficiaryFax.setEditable(false);
                beneficiaryFax.setFont(new java.awt.Font("Tahoma", 1, 13));
                beneficiaryTurnOver.setEditable(false);
                beneficiaryTurnOver.setFont(new java.awt.Font("Tahoma", 1, 13));
                beneficiaryCategory.setEditable(false);
                beneficiaryCategory.setFont(new java.awt.Font("Tahoma", 1, 13));
//                benlimitAmount.setEditable(false);
//                benlimitAmount.setFont(new java.awt.Font("Tahoma", 1, 13));
                clearEditButton.setEnabled(false);
                clearEditButton.setFont(new java.awt.Font("Tahoma", 1, 13));
                contactsByCheckboxTable.setEnabled(false);
                contactsByCheckboxTable.setFont(new java.awt.Font("Tahoma", 1, 13));
                addContactButton.setEnabled(false);
                addContactButton.setFont(new java.awt.Font("Tahoma", 1, 13));
                clientDetailsSaveButton.setEnabled(false);
                clientDetailsSaveButton.setFont(new java.awt.Font("Tahoma", 1, 13));

// call parser for view the values of transaction.
                ParserCaller parserCaller = new ParserCaller();
                editViewTxnMap = parserCaller.parseEditViewXML(email, SingletonClass.txIdEdit);

                if (editViewTxnMap.get("type") != null && !editViewTxnMap.get("type").equals("null")) {
                    transactionType.setSelectedItem(editViewTxnMap.get("type").trim());
                }
                if (editViewTxnMap.get("client") != null && !editViewTxnMap.get("type").equals("null")) {
                    if (editViewTxnMap.get("client").equals(editViewTxnMap.get("appName"))) {
                        applicantCheckbox.setSelected(true);
                    }
                    if (editViewTxnMap.get("client").equals(editViewTxnMap.get("benName"))) {
                        beneficiaryCheckbox.setSelected(true);
                    }
                }
                if (editViewTxnMap.get("appName") != null && !editViewTxnMap.get("appName").equals("null")) {
                    appNameTextField.setText(editViewTxnMap.get("appName").trim());
                }
                if (editViewTxnMap.get("appAddrs") != null && !editViewTxnMap.get("appAddrs").equals("null")) {
                    applicantAddrs.setText(editViewTxnMap.get("appAddrs"));
                }
                if (editViewTxnMap.get("appPhn") != null && !editViewTxnMap.get("appPhn").equals("null")) {
                    appPhNo.setText(editViewTxnMap.get("appPhn"));
                }
                if (editViewTxnMap.get("appRegion") != null && !editViewTxnMap.get("appRegion").equals("null")) {
                    appRegion.setSelectedItem(editViewTxnMap.get("appRegion"));
                }
                if (editViewTxnMap.get("appFax") != null && !editViewTxnMap.get("appFax").equals("null")) {
                    appFax.setText(editViewTxnMap.get("appFax"));
                }
                if (editViewTxnMap.get("appTurnOver") != null && !editViewTxnMap.get("appTurnOver").equals("null")) {
                    appTurnOver.setText(editViewTxnMap.get("appTurnOver"));
                }
                if (editViewTxnMap.get("appcategory") != null && !editViewTxnMap.get("appcategory").equals("null")) {
                    appCategory.setSelectedItem(editViewTxnMap.get("appcategory"));
                }
                if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && applicantCheckbox.isSelected()) {
                    appExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
//                appExpBusiness.setEnabled(true);
                } else {
                    appExpBusiness.setText("");
                }
                if (editViewTxnMap.get("compSpace") != null && !editViewTxnMap.get("compSpace").equals("null") && applicantCheckbox.isSelected()) {
                    appComprehensiveSpace.setText(editViewTxnMap.get("compSpace"));
//                appExpBusiness.setEnabled(true);
                    SingletonClass.clientCompSpace = editViewTxnMap.get("compSpace");
                } else {
                    appComprehensiveSpace.setText("");
                }
                if (editViewTxnMap.get("solution") != null && !editViewTxnMap.get("solution").equals("null") && applicantCheckbox.isSelected()) {
                    appSolution.setText(editViewTxnMap.get("solution"));
                    SingletonClass.clientSolution = editViewTxnMap.get("solution");
//                appExpBusiness.setEnabled(true);
                } else {
                    appSolution.setText("");
                }
//                if (editViewTxnMap.get("applimitAmount") != null && !editViewTxnMap.get("applimitAmount").equals("null")) {
//                    appLimitAmount.setText(editViewTxnMap.get("applimitAmount"));
//                }

                // set beneficiary details of that transaction which is edited or viewed

                if (editViewTxnMap.get("benName") != null && !editViewTxnMap.get("benName").equals("null")) {
                    benNameTextField.setText(editViewTxnMap.get("benName").trim());
                }
                if (editViewTxnMap.get("benAddrs") != null && !editViewTxnMap.get("benAddrs").equals("null")) {
                    beneficiaryAddrs.setText(editViewTxnMap.get("benAddrs"));
                }
                if (editViewTxnMap.get("benPhn") != null && !editViewTxnMap.get("benPhn").equals("null")) {
                    beneficiaryPhNo.setText(editViewTxnMap.get("benPhn"));
                }
                if (editViewTxnMap.get("benFax") != null && !editViewTxnMap.get("benFax").equals("null")) {
                    beneficiaryFax.setText(editViewTxnMap.get("benFax"));
                }
                if (editViewTxnMap.get("benTurnOver") != null && !editViewTxnMap.get("benTurnOver").equals("null")) {
                    beneficiaryTurnOver.setText(editViewTxnMap.get("benTurnOver"));
                }
                if (editViewTxnMap.get("bencategory") != null && !editViewTxnMap.get("bencategory").equals("null")) {
                    beneficiaryCategory.setSelectedItem(editViewTxnMap.get("bencategory"));
                }
                if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && beneficiaryCheckbox.isSelected()) {
                    benExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
//                benExpBusiness.setEnabled(true);
                } else {
                    benExpBusiness.setText("");
                }
                if (editViewTxnMap.get("compSpace") != null && !editViewTxnMap.get("compSpace").equals("null") && beneficiaryCheckbox.isSelected()) {
                    benComprehensiveSpace.setText(editViewTxnMap.get("compSpace"));
                    SingletonClass.clientCompSpace = editViewTxnMap.get("solution");
                } else {
                    benComprehensiveSpace.setText("");
                }
                if (editViewTxnMap.get("solution") != null && !editViewTxnMap.get("solution").equals("null") && beneficiaryCheckbox.isSelected()) {
                    benSolution.setText(editViewTxnMap.get("solution"));
                    SingletonClass.clientSolution = editViewTxnMap.get("solution");
//                appExpBusiness.setEnabled(true);
                } else {
                    benSolution.setText("");
                }
//                if (editViewTxnMap.get("benlimitAmount") != null && !editViewTxnMap.get("benlimitAmount").equals("null")) {
//                    benlimitAmount.setText(editViewTxnMap.get("benlimitAmount"));
//                }

            }
        }
    }

    public void setValuesForNonEdit() {

        if (SingletonClass.txIdEdit != null && !SingletonClass.txIdEdit.equals("")) {
            transactionType.setEnabled(false);
            transactionType.setFont(new java.awt.Font("Tahoma", 1, 13));
            applicantCheckbox.setEnabled(false);
            applicantCheckbox.setFont(new java.awt.Font("Tahoma", 1, 13));
            beneficiaryCheckbox.setEnabled(false);
            beneficiaryCheckbox.setFont(new java.awt.Font("Tahoma", 1, 13));
            appNameTextField.setEditable(false);
            appNameTextField.setFont(new java.awt.Font("Tahoma", 1, 13));
            applicantAddrs.setEditable(false);
            applicantAddrs.setFont(new java.awt.Font("Tahoma", 1, 13));
            appPhNo.setEditable(false);
            appPhNo.setFont(new java.awt.Font("Tahoma", 1, 13));
            appRegion.setEditable(false);
            appRegion.setFont(new java.awt.Font("Tahoma", 1, 13));
            appFax.setEditable(false);
            appFax.setFont(new java.awt.Font("Tahoma", 1, 13));
            appTurnOver.setEditable(false);
            appTurnOver.setFont(new java.awt.Font("Tahoma", 1, 13));
            appCategory.setEnabled(false);
            appCategory.setFont(new java.awt.Font("Tahoma", 1, 13));
//                appLimitAmount.setEditable(false);
//                appLimitAmount.setFont(new java.awt.Font("Tahoma", 1, 13));

            // set beneficiary details to default

            benNameTextField.setEditable(false);
            benNameTextField.setFont(new java.awt.Font("Tahoma", 1, 13));
            beneficiaryAddrs.setEditable(false);
            beneficiaryAddrs.setFont(new java.awt.Font("Tahoma", 1, 13));
            beneficiaryPhNo.setEditable(false);
            beneficiaryPhNo.setFont(new java.awt.Font("Tahoma", 1, 13));
            benRegion.setEditable(false);
            benRegion.setFont(new java.awt.Font("Tahoma", 1, 13));
            beneficiaryFax.setEditable(false);
            beneficiaryFax.setFont(new java.awt.Font("Tahoma", 1, 13));
            beneficiaryTurnOver.setEditable(false);
            beneficiaryTurnOver.setFont(new java.awt.Font("Tahoma", 1, 13));
            beneficiaryCategory.setEditable(false);
            beneficiaryCategory.setFont(new java.awt.Font("Tahoma", 1, 13));
//                benlimitAmount.setEditable(false);
//                benlimitAmount.setFont(new java.awt.Font("Tahoma", 1, 13));
            clearEditButton.setEnabled(false);
            clearEditButton.setFont(new java.awt.Font("Tahoma", 1, 13));
            contactsByCheckboxTable.setEnabled(false);
            contactsByCheckboxTable.setFont(new java.awt.Font("Tahoma", 1, 13));
            addContactButton.setEnabled(false);
            addContactButton.setFont(new java.awt.Font("Tahoma", 1, 13));
            clientDetailsSaveButton.setEnabled(false);
            clientDetailsSaveButton.setFont(new java.awt.Font("Tahoma", 1, 13));

// call parser for view the values of transaction.
            ParserCaller parserCaller = new ParserCaller();
            editViewTxnMap = parserCaller.parseEditViewXML(email, SingletonClass.txIdEdit);

            if (editViewTxnMap.get("type") != null && !editViewTxnMap.get("type").equals("null")) {
                transactionType.setSelectedItem(editViewTxnMap.get("type").trim());
            }
            if (editViewTxnMap.get("client") != null && !editViewTxnMap.get("type").equals("null")) {
                if (editViewTxnMap.get("client").equals(editViewTxnMap.get("appName"))) {
                    applicantCheckbox.setSelected(true);
                }
                if (editViewTxnMap.get("client").equals(editViewTxnMap.get("benName"))) {
                    beneficiaryCheckbox.setSelected(true);
                }
            }
            if (editViewTxnMap.get("appName") != null && !editViewTxnMap.get("appName").equals("null")) {
                appNameTextField.setText(editViewTxnMap.get("appName").trim());
            }
            if (editViewTxnMap.get("appAddrs") != null && !editViewTxnMap.get("appAddrs").equals("null")) {
                applicantAddrs.setText(editViewTxnMap.get("appAddrs"));
            }
            if (editViewTxnMap.get("appPhn") != null && !editViewTxnMap.get("appPhn").equals("null")) {
                appPhNo.setText(editViewTxnMap.get("appPhn"));
            }
            if (editViewTxnMap.get("appRegion") != null && !editViewTxnMap.get("appRegion").equals("null")) {
                appRegion.setSelectedItem(editViewTxnMap.get("appRegion"));
            }
            if (editViewTxnMap.get("appFax") != null && !editViewTxnMap.get("appFax").equals("null")) {
                appFax.setText(editViewTxnMap.get("appFax"));
            }
            if (editViewTxnMap.get("appTurnOver") != null && !editViewTxnMap.get("appTurnOver").equals("null")) {
                appTurnOver.setText(editViewTxnMap.get("appTurnOver"));
            }
            if (editViewTxnMap.get("appcategory") != null && !editViewTxnMap.get("appcategory").equals("null")) {
                appCategory.setSelectedItem(editViewTxnMap.get("appcategory"));
            }
            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && applicantCheckbox.isSelected()) {
                appExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
//                appExpBusiness.setEnabled(true);
            } else {
                appExpBusiness.setText("");
            }
            if (editViewTxnMap.get("compSpace") != null && !editViewTxnMap.get("compSpace").equals("null") && applicantCheckbox.isSelected()) {
                appComprehensiveSpace.setText(editViewTxnMap.get("compSpace"));
                SingletonClass.clientCompSpace = editViewTxnMap.get("compSpace");
//                appExpBusiness.setEnabled(true);
            } else {
                appComprehensiveSpace.setText("");
            }
            if (editViewTxnMap.get("solution") != null && !editViewTxnMap.get("solution").equals("null") && applicantCheckbox.isSelected()) {
                appSolution.setText(editViewTxnMap.get("solution"));
                SingletonClass.clientSolution = editViewTxnMap.get("solution");
//                appExpBusiness.setEnabled(true);
            } else {
                appSolution.setText("");
            }
//                if (editViewTxnMap.get("applimitAmount") != null && !editViewTxnMap.get("applimitAmount").equals("null")) {
//                    appLimitAmount.setText(editViewTxnMap.get("applimitAmount"));
//                }

            // set beneficiary details of that transaction which is edited or viewed

            if (editViewTxnMap.get("benName") != null && !editViewTxnMap.get("benName").equals("null")) {
                benNameTextField.setText(editViewTxnMap.get("benName").trim());
            }
            if (editViewTxnMap.get("benAddrs") != null && !editViewTxnMap.get("benAddrs").equals("null")) {
                beneficiaryAddrs.setText(editViewTxnMap.get("benAddrs"));
            }
            if (editViewTxnMap.get("benPhn") != null && !editViewTxnMap.get("benPhn").equals("null")) {
                beneficiaryPhNo.setText(editViewTxnMap.get("benPhn"));
            }
            if (editViewTxnMap.get("benRegion") != null && !editViewTxnMap.get("benRegion").equals("null")) {
                benRegion.setSelectedItem(editViewTxnMap.get("benRegion"));
            }
            if (editViewTxnMap.get("benFax") != null && !editViewTxnMap.get("benFax").equals("null")) {
                beneficiaryFax.setText(editViewTxnMap.get("benFax"));
            }
            if (editViewTxnMap.get("benTurnOver") != null && !editViewTxnMap.get("benTurnOver").equals("null")) {
                beneficiaryTurnOver.setText(editViewTxnMap.get("benTurnOver"));
            }
            if (editViewTxnMap.get("bencategory") != null && !editViewTxnMap.get("bencategory").equals("null")) {
                beneficiaryCategory.setSelectedItem(editViewTxnMap.get("bencategory"));
            }
            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && beneficiaryCheckbox.isSelected()) {
                benExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
//                appExpBusiness.setEnabled(true);
            } else {
                benExpBusiness.setText("");
            }
            if (editViewTxnMap.get("compSpace") != null && !editViewTxnMap.get("compSpace").equals("null") && beneficiaryCheckbox.isSelected()) {
                benComprehensiveSpace.setText(editViewTxnMap.get("compSpace"));
                SingletonClass.clientCompSpace = editViewTxnMap.get("compSpace");
//                appExpBusiness.setEnabled(true);
            } else {
                benComprehensiveSpace.setText("");
            }
            if (editViewTxnMap.get("solution") != null && !editViewTxnMap.get("solution").equals("null") && beneficiaryCheckbox.isSelected()) {
                benSolution.setText(editViewTxnMap.get("solution"));
                SingletonClass.clientSolution = editViewTxnMap.get("solution");
//                appExpBusiness.setEnabled(true);
            } else {
                benSolution.setText("");
            }
//            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && beneficiaryCheckbox.isSelected()) {
//                benExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
////                benExpBusiness.setEnabled(true);
//            } else {
//                benExpBusiness.setText("");
//            }
//                if (editViewTxnMap.get("benlimitAmount") != null && !editViewTxnMap.get("benlimitAmount").equals("null")) {
//                    benlimitAmount.setText(editViewTxnMap.get("benlimitAmount"));
//                }

        }
    }
// set all beneficiary values if beneficiary is existing client

    public void benNameTextFieldFocusLost(FocusEvent listener) {

        if (beneficiaryCheckbox.isSelected() && benNameTextField.getText().trim() != null && !benNameTextField.getText().trim().equals("")) {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(benNameTextField.getText().trim());
            boolean b = m.find();
//            if (benNameTextField.getText().trim().toLowerCase().contains("ltd") || benNameTextField.getText().trim().toLowerCase().contains("pvt")) {
//                                    benNameTextField.setText("");
//                 JOptionPane.showMessageDialog(this, "Please write limited or private instead of ltd or pvt in the client name.");
//            } else 
                if (b) {
                                    benNameTextField.setText("");
                 JOptionPane.showMessageDialog(this, "Please do not enter the special character in the client name.");
            }else{
                  String benPrivateName = benNameTextField.getText().trim().replaceAll("Pvt.", "Private").replaceAll("pvt.", " Private ").replaceAll("Ltd.", "Limited").replaceAll("ltd.", "Limited")
                    .replaceAll("Pvt", " Private ").replaceAll("pvt", " Private ").replaceAll("Ltd", "Limited").replaceAll("ltd", "Limited");
             
                    String benePvtLtdName = benNameTextField.getText().trim().replaceAll("Private", "PVT").replaceAll("Private", "PVT").replaceAll("Limited", "LTD").replaceAll("Limited", "LTD")
                            .replaceAll("Private", "PVT.").replaceAll("Private", "PVT.").replaceAll("Limited","LTD.").replaceAll("Limited", "LTD.");
                    
                if(SingletonClass.txIdEdit==null && transactionType.getSelectedItem()!=null && transactionType.getSelectedItem().equals("New Solution") 
                        && (clientList.contains(benNameTextField.getText().trim().toUpperCase()) || clientList.contains(benPrivateName.toUpperCase()) || clientList.contains(benePvtLtdName.toUpperCase()) ) )
                {                    
                                    benNameTextField.setText("");
                                    JOptionPane.showMessageDialog(this, "This client already exist in the system. Please contact your manager regarding the same.", "", 0);                                    
                }
                // for other RMs clients check
                else if(SingletonClass.txIdEdit==null && !clientList.contains(benNameTextField.getText().trim()) 
                        && (allClientList.contains(benNameTextField.getText().trim().toUpperCase())  || clientList.contains(benPrivateName.toUpperCase()) || clientList.contains(benePvtLtdName.toUpperCase()))){
                    
                                    benNameTextField .setText("");
                                    JOptionPane.showMessageDialog(this, "This client already exist in the system. Please contact your manager regarding the same.", "", 0);
                }
            }
                ParserCaller parserCaller = new ParserCaller();
                if (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal")) {
                    if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                        benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                        System.out.println("benlist sizeeee: " + benList);
                        if (benList.size() > 0) {
                            Iterator it = benList.iterator();
                            benNameCombobox.removeAllItems();
                            benNameCombobox.setSize(benNamePanel.getSize());
                            benNamePanel.removeAll();
                            while (it.hasNext()) {
                                benNameCombobox.addItem(it.next());
                            }
                            benNamePanel.add(benNameCombobox);
                            benNamePanel.validate();
                            benNamePanel.repaint();
                        }
                    }
                    if (beneficiaryCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                        benList = parserCaller.parseBenListXml(email, benNameTextField.getText().trim());
                        System.out.println("benlist sizeeee: " + benList);
                        if (benList.size() > 0) {
                            Iterator it = benList.iterator();
                            benNameCombobox.setSize(appNamePanel.getSize());
                            benNameCombobox.removeAllItems();
                            appNamePanel.removeAll();
                            while (it.hasNext()) {
                                benNameCombobox.addItem(it.next());
                            }
                            appNamePanel.add(benNameCombobox);
                            appNamePanel.validate();
                            appNamePanel.repaint();
                        }
                    }

                }
//                if ((appNameTextField.getText()!=null && clientList.contains(appNameTextField.getText().trim())) || (benNameCombobox.getSelectedItem()!=null && clientList.contains(benNameCombobox.getSelectedItem().toString()))) {
                    Map<String, String> clientDetailsMap = null;

//                    if (appNameTextField.getText()!=null && clientList.contains(appNameTextField.getText().trim())) {
//                        // call the parser for parsing client details xml and get all values in map by each value name key.
//                        clientDetailsMap = parserCaller.parseClientDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(appNameTextField.getText().trim()) + "&" + SingletonClass.userName + "=" + email);
//                    } else 
                  if ((benNameTextField.getText()!=null && clientList.contains(benNameTextField.getText()))) {
                        // call the parser for parsing client details xml and get all values in map by each value name key.
                        clientDetailsMap = parserCaller.parseClientDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(benNameTextField.getText()) + "&" + SingletonClass.userName + "=" + email);
                    }
                    if (clientDetailsMap.get("address") != null && !clientDetailsMap.get("address").equals("null")) {
                        beneficiaryAddrs.setText(clientDetailsMap.get("address"));
                    }
                    if (clientDetailsMap.get("landline") != null && !clientDetailsMap.get("landline").equals("null")) {
                        beneficiaryPhNo.setText(clientDetailsMap.get("landline"));
                    }
                    if (clientDetailsMap.get("region") != null && !clientDetailsMap.get("region").equals("null")) {
                        benRegion.setSelectedItem(clientDetailsMap.get("region"));
                    }
                    if (clientDetailsMap.get("faxNo") != null && !clientDetailsMap.get("faxNo").equals("null")) {
                        beneficiaryFax.setText(clientDetailsMap.get("faxNo"));
                    }
                    if (clientDetailsMap.get("turnOver") != null && !clientDetailsMap.get("turnOver").equals("null")) {
                        beneficiaryTurnOver.setText(clientDetailsMap.get("turnOver"));
                    }
                    if (clientDetailsMap.get("category") != null && !clientDetailsMap.get("category").equals("null")) {
                        beneficiaryCategory.setSelectedItem(clientDetailsMap.get("category"));
                    }
//            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && beneficiaryCheckbox.isSelected()) {
//                benExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
////                benExpBusiness.setEnabled(true);
//            } else {
//                benExpBusiness.setText("");
//            } //            if (!applicantCheckbox.isSelected() && !beneficiaryCheckbox.isSelected()) {
                    //                JOptionPane.showMessageDialog(this, "Please specify the client.", "", 0);
                    //            } 
                    if (beneficiaryCheckbox.isSelected()) {
                        // remove all row from client table
                        DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
                        int r = model.getRowCount();
                        System.out.println("row count in edit reset is: " + r);
                        if (r > 0) {
                            model.getDataVector().removeAllElements();
                            counter = 0;
                        }
                        SingletonClass.clientCompanyName = benNameTextField.getText().trim();
                            addContactsOnForcusLost(benNameTextField.getText().trim());

                    } else if (applicantCheckbox.isSelected()) {
                        // remove all row from client table
                        DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
                        int r = model.getRowCount();
                        System.out.println("row count in edit reset is: " + r);
                        if (r > 0) {
                            model.getDataVector().removeAllElements();
                            counter = 0;
                        }
                        SingletonClass.clientCompanyName = appNameTextField.getText().trim();
                        if (applicantCheckbox.isSelected()) {
                            addContactsOnForcusLost(appNameTextField.getText().trim());
                        }
                    }
                    parserCaller = null;
//                }
            }
    }

    public void addContactsOnForcusLost(String clientName) {
        ParserCaller parserCaller = new ParserCaller();
        contactList = parserCaller.parseClientContactDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(clientName) + "&" + SingletonClass.userName + "=" + email);
        System.out.println("contactList: " + contactList.size() + "  contactList  " + contactList);
        if (contactList.size() > 0) {
            counter = 0;
            Iterator it = contactList.iterator();
            Map<String, String> contactMap = null;
            while (it.hasNext()) {
                contactMap = (Map) it.next();
                if (contactMap != null && contactMap.size() > 0) //code changed by satya
                {
                    TableColumn col1 = contactsByCheckboxTable.getColumnModel().getColumn(0);
                    col1.setMaxWidth(50);
                    col1.setWidth(50);
                    DefaultTableModel tableModel = (DefaultTableModel) contactsByCheckboxTable.getModel();
                    tableModel.insertRow(counter, new Object[]{});
                    contactsByCheckboxTable.setValueAt(counter + 1, counter, 0);
                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontName"), counter, 1);
                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactDesignation"), counter, 2);
                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactMobile"), counter, 3);
                    contactsByCheckboxTable.setValueAt(contactMap.get("ccontactEmail"), counter, 4);
                    contactsByCheckboxTable.setValueAt(contactMap.get("notifyStr"), counter, 5);
                    counter++;
                }
            }
            contactsByCheckboxTable.repaint();
        }
    }
// set all applicant values if beneficiary is existing client

    public void appNameTextFieldFocusLost(FocusEvent listener) {
        System.out.println("************************appNameTextFieldFocusLost");
        if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(appNameTextField.getText().trim());
            boolean b = m.find();
            System.out.println("PATTERNnnnnn: "+b);
//            if (appNameTextField.getText().trim().toLowerCase().contains("ltd") || appNameTextField.getText().trim().toLowerCase().contains("pvt")) {
//                                    appNameTextField.setText("");
//                 JOptionPane.showMessageDialog(this, "Please write limited or private instead of ltd or pvt in the client name.");
//            } else 
                if (b) {
                                    appNameTextField.setText("");
                 JOptionPane.showMessageDialog(this, "Please do not enter the special character in the client name.");
            }else{
                    String appPrivateName = appNameTextField.getText().trim().replaceAll("Pvt.", "PRIVATE").replaceAll("pvt.", " PRIVATE ").replaceAll("Ltd.", "LIMITED").replaceAll("ltd.", "LIMITED")
                    .replaceAll("Pvt", " PRIVATE ").replaceAll("pvt", " PRIVATE ").replaceAll("Ltd", "LIMITED").replaceAll("ltd", "LIMITED");
                    
                    String appPvtLtdName = appNameTextField.getText().trim().replaceAll("Private", "PVT").replaceAll("Private", "PVT").replaceAll("Limited", "LTD").replaceAll("Limited", "LTD")
                            .replaceAll("Private", "PVT.").replaceAll("Private", "PVT.").replaceAll("Limited","LTD.").replaceAll("Limited", "LTD.");
                    System.out.println("appPvtLtdName: "+appPvtLtdName.toUpperCase());
                if(SingletonClass.txIdEdit==null && transactionType.getSelectedItem()!=null && transactionType.getSelectedItem().equals("New Solution") 
                        && (clientList.contains(appNameTextField.getText().trim().toUpperCase())  || clientList.contains(appPrivateName.toUpperCase()) || clientList.contains(appPvtLtdName.toUpperCase())))
                {                    
                                    appNameTextField.setText("");
                                    JOptionPane.showMessageDialog(this, "This client already exist in the system. Please contact your manager regarding the same.", "", 0);                                    
                }else if(SingletonClass.txIdEdit==null && !clientList.contains(appNameTextField.getText().trim()) 
                        && (allClientList.contains(appNameTextField.getText().trim().toUpperCase())  || clientList.contains(appPrivateName.toUpperCase()) || clientList.contains(appPvtLtdName.toUpperCase()))){                    
                                    appNameTextField.setText("");
                                    JOptionPane.showMessageDialog(this, "This client already exist in the system. Please contact your manager regarding the same.", "", 0);
                }
        }
        }
                        ParserCaller parserCaller = new ParserCaller();
                        if (transactionType.getSelectedItem().toString().equals("Enhancement") ||  transactionType.getSelectedItem().toString().equals("Existing-New Solution") ||  transactionType.getSelectedItem().toString().equals("Renewal")) {


                            if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {

                    StringBuffer clientDetailsBuffer = new StringBuffer();
                    clientDetailsBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=" + "txnClientDetails" + "&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.txStatus).append("=").append(SingletonClass.txStatusSelect).append("&").append(SingletonClass.txType).append("=").append(transactionType.getSelectedItem().toString());

                        benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());

                        System.out.println("benlist sizeeee: " + benList);
                        if (benList.size() > 0) {
                            Iterator it = benList.iterator();
                            benNameCombobox.setSize(benNamePanel.getSize());
                            benNameCombobox.removeAllItems();
                            benNamePanel.removeAll();
                            while (it.hasNext()) {
                                benNameCombobox.addItem(it.next());
                            }
                            benNamePanel.add(benNameCombobox);
                            benNamePanel.validate();
                            benNamePanel.repaint();
                        }
                    }
                    if (beneficiaryCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                        benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());

                        System.out.println("benlist sizeeee: " + benList);
                        if (benList.size() > 0) {
                            Iterator it = benList.iterator();
                            benNameCombobox.setSize(appNamePanel.getSize());
                            benNameCombobox.removeAllItems();
                            appNamePanel.removeAll();
                            while (it.hasNext()) {
                                benNameCombobox.addItem(it.next());
                            }
                            appNamePanel.add(benNameCombobox);
                            appNamePanel.validate();
                            appNamePanel.repaint();
                        }
                }

//        if (clientList.contains(appNameTextField.getText().trim()) || clientList.contains(benNameCombobox.getSelectedItem().toString())) {
//            Map<String, String> clientDetailsMap = null;
//        }
                if (clientList.contains(appNameTextField.getText().trim()) || clientList.contains(benNameCombobox.getSelectedItem())) {

                    SingletonClass.clientCompanyName = appNameTextField.getText().trim();
                    Map<String, String> clientDetailsMap = null;
                    clientDetailsMap = parserCaller.parseClientDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(appNameTextField.getText().trim()) + "&" + SingletonClass.userName + "=" + email);

//            if (clientList.contains(appNameTextField.getText().trim())) {
//                // call the parser for parsing client details xml and get all values in map by each value name key.
//                clientDetailsMap = parserCaller.parseClientDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(appNameTextField.getText().trim()) + "&" + SingletonClass.userName + "=" + email);
//            } else if (clientList.contains(benNameCombobox.getSelectedItem())) {
//                // call the parser for parsing client details xml and get all values in map by each value name key.
//                clientDetailsMap = parserCaller.parseClientDetailsXml(SingletonClass.httpServerUrl + SingletonClass.operaionType + "=" + "clientDetailsXML" + "&" + SingletonClass.clientIsBenOrApp + "=" + URLEncoder.encode(benNameCombobox.getSelectedItem().toString()) + "&" + SingletonClass.userName + "=" + email);
//            }

                    if (clientDetailsMap.get("address") != null && !clientDetailsMap.get("address").equals("null")) {
                        applicantAddrs.setText(clientDetailsMap.get("address"));
                    }
                    if (clientDetailsMap.get("landline") != null && !clientDetailsMap.get("landline").equals("null")) {
                        appPhNo.setText(clientDetailsMap.get("landline"));
                    }
                    if (clientDetailsMap.get("region") != null && !clientDetailsMap.get("region").equals("null")) {
                        appRegion.setSelectedItem(clientDetailsMap.get("region"));
                    }
                    if (clientDetailsMap.get("faxNo") != null && !clientDetailsMap.get("faxNo").equals("null")) {
                        appFax.setText(clientDetailsMap.get("faxNo"));
                    }
                    if (clientDetailsMap.get("turnOver") != null && !clientDetailsMap.get("turnOver").equals("null")) {
                        appTurnOver.setText(clientDetailsMap.get("turnOver"));
                    }
                    if (clientDetailsMap.get("category") != null && !clientDetailsMap.get("category").equals("null")) {
                        appCategory.setSelectedItem(clientDetailsMap.get("category"));
                    }
//            if (editViewTxnMap.get("expactedBusiness") != null && !editViewTxnMap.get("expactedBusiness").equals("null") && applicantCheckbox.isSelected()) {
//                appExpBusiness.setText(editViewTxnMap.get("expactedBusiness"));
////                appExpBusiness.setEnabled(true);
//            } else {
//                appExpBusiness.setText("");
//            }
                    if (applicantCheckbox.isSelected()) {
                        // remove all row from client table
                        DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
                        int r = model.getRowCount();
                        System.out.println("row count in edit reset is: " + r);
                        if (r > 0) {
                            model.getDataVector().removeAllElements();
                            counter = 0;
                        }
                    }
                    if (applicantCheckbox.isSelected()) {
                        addContactsOnForcusLost(appNameTextField.getText().trim());
                    }
                    parserCaller = null;
                }
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

        jScrollPane2 = new javax.swing.JScrollPane();
        clientDetailsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        beneficiaryCheckbox = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        beneficiaryAddrs = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        beneficiaryFax = new javax.swing.JTextField();
        beneficiaryPhNo = new javax.swing.JTextField();
        beneficiaryTurnOver = new javax.swing.JTextField();
        benNamePanel = new javax.swing.JPanel();
        beneficiaryCategory = new javax.swing.JComboBox();
        limitForBeneficiaryCheck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        benExpBusiness = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        benComprehensiveSpace = new javax.swing.JTextArea();
        benComprehensiveSpaceLabe = new javax.swing.JLabel();
        benRegion = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        benSolution = new javax.swing.JTextArea();
        benComprehensiveSpaceLabe1 = new javax.swing.JLabel();
        indTextBen = new javax.swing.JTextField();
        tradeTextBen = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        extCreditRatingBen = new javax.swing.JTextField();
        yearOfIncorpBen = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        applicantCheckbox = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        applicantAddrs = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        appPhNo = new javax.swing.JTextField();
        appFax = new javax.swing.JTextField();
        appTurnOver = new javax.swing.JTextField();
        appNamePanel = new javax.swing.JPanel();
        appCategory = new javax.swing.JComboBox();
        limitForApplicantCheck = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        appExpBusiness = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        appComprehensiveSpace = new javax.swing.JTextArea();
        appComprehensiveSpaceLabel = new javax.swing.JLabel();
        appRegion = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        appSolution = new javax.swing.JTextArea();
        appComprehensiveSpaceLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tradeTextApp = new javax.swing.JTextField();
        indTextApp = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        yearOfIncorpApp = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        extCreditRatingApp = new javax.swing.JTextField();
        clientdetailsScrollPane = new javax.swing.JScrollPane();
        contactsByCheckboxTable = new javax.swing.JTable();
        clientDetailsSaveButton = new javax.swing.JButton();
        transactionType = new javax.swing.JComboBox();
        addContactButton = new javax.swing.JButton();
        clearEditButton = new javax.swing.JButton();
        cAdditionalDetailsButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel1.setText("Transaction Type:");

        jPanel1.setBackground(new java.awt.Color(215, 215, 215));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(215, 215, 215));

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel9.setText("Beneficiary:");

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel10.setText("Name:");

        beneficiaryCheckbox.setNextFocusableComponent(benNamePanel);
        beneficiaryCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beneficiaryCheckboxActionPerformed(evt);
            }
        });

        beneficiaryAddrs.setColumns(20);
        beneficiaryAddrs.setRows(5);
        beneficiaryAddrs.setNextFocusableComponent(benRegion);
        beneficiaryAddrs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                beneficiaryAddrsKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(beneficiaryAddrs);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel11.setText("Address:");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel12.setText("Phone:");

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel13.setText("Fax:");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel14.setText("Turnover(cr.):");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel8.setText("Category");

        beneficiaryFax.setNextFocusableComponent(beneficiaryTurnOver);
        beneficiaryFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                beneficiaryFaxKeyReleased(evt);
            }
        });

        beneficiaryPhNo.setNextFocusableComponent(beneficiaryFax);
        beneficiaryPhNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                beneficiaryPhNoKeyReleased(evt);
            }
        });

        beneficiaryTurnOver.setNextFocusableComponent(beneficiaryCategory);
        beneficiaryTurnOver.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                beneficiaryTurnOverFocusLost(evt);
            }
        });
        beneficiaryTurnOver.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                beneficiaryTurnOverKeyReleased(evt);
            }
        });

        benNamePanel.setNextFocusableComponent(beneficiaryAddrs);

        javax.swing.GroupLayout benNamePanelLayout = new javax.swing.GroupLayout(benNamePanel);
        benNamePanel.setLayout(benNamePanelLayout);
        benNamePanelLayout.setHorizontalGroup(
            benNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        benNamePanelLayout.setVerticalGroup(
            benNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        beneficiaryCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"SME Client","Mid Corporate","Large Corporate"}));
        beneficiaryCategory.setSelectedIndex(-1);

        limitForBeneficiaryCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitForBeneficiaryCheckActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel2.setText("Limit For Beneficiary:");

        benExpBusiness.setNextFocusableComponent(benComprehensiveSpace);
        benExpBusiness.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                benExpBusinessFocusLost(evt);
            }
        });
        benExpBusiness.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                benExpBusinessKeyReleased(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel24.setText("Expected Business:");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel5.setText("Region:");

        benComprehensiveSpace.setColumns(20);
        benComprehensiveSpace.setRows(5);
        benComprehensiveSpace.setNextFocusableComponent(benSolution);
        jScrollPane5.setViewportView(benComprehensiveSpace);

        benComprehensiveSpaceLabe.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        benComprehensiveSpaceLabe.setText("Comprehensive Space:");

        benRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "South Delhi","West Delhi","North Delhi","East Delhi","Central Delhi","Gurgaon","Faridabad","Gaziabad","Noida","Others" }));
        benRegion.setNextFocusableComponent(beneficiaryPhNo);
        benRegion.setSelectedIndex(-1);

        benSolution.setColumns(20);
        benSolution.setRows(5);
        benSolution.setNextFocusableComponent(addContactButton);
        jScrollPane7.setViewportView(benSolution);

        benComprehensiveSpaceLabe1.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        benComprehensiveSpaceLabe1.setText("Solution:");

        indTextBen.setNextFocusableComponent(appComprehensiveSpace);
        indTextBen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                indTextBenFocusLost(evt);
            }
        });
        indTextBen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                indTextBenKeyReleased(evt);
            }
        });

        tradeTextBen.setNextFocusableComponent(appComprehensiveSpace);
        tradeTextBen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tradeTextBenFocusLost(evt);
            }
        });
        tradeTextBen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tradeTextBenKeyReleased(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel29.setText("Trade:");

        jLabel30.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel30.setText("Industry:");

        jLabel31.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel31.setText("Year Of Incorporation:");

        jLabel32.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel32.setText("External Credit Rating: ");

        extCreditRatingBen.setNextFocusableComponent(appComprehensiveSpace);
        extCreditRatingBen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                extCreditRatingBenFocusLost(evt);
            }
        });
        extCreditRatingBen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                extCreditRatingBenKeyReleased(evt);
            }
        });

        yearOfIncorpBen.setNextFocusableComponent(appComprehensiveSpace);
        yearOfIncorpBen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearOfIncorpBenFocusLost(evt);
            }
        });
        yearOfIncorpBen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearOfIncorpBenKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(beneficiaryFax)
                                    .addComponent(beneficiaryPhNo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(benRegion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                    .addComponent(benNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(beneficiaryTurnOver)
                                    .addComponent(tradeTextBen)
                                    .addComponent(indTextBen)
                                    .addComponent(yearOfIncorpBen)
                                    .addComponent(extCreditRatingBen)
                                    .addComponent(jScrollPane5)
                                    .addComponent(jScrollPane7))
                                .addGap(29, 29, 29))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(beneficiaryCheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(limitForBeneficiaryCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(benComprehensiveSpaceLabe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(benComprehensiveSpaceLabe1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(387, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(beneficiaryCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(benExpBusiness, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(beneficiaryCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limitForBeneficiaryCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(benNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(benRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beneficiaryPhNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel13))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beneficiaryFax, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beneficiaryTurnOver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beneficiaryCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(benExpBusiness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tradeTextBen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indTextBen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearOfIncorpBen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extCreditRatingBen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(benComprehensiveSpaceLabe)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(benComprehensiveSpaceLabe1)
                        .addGap(15, 15, 15))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(215, 215, 215));

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel16.setText("Name:");

        applicantCheckbox.setNextFocusableComponent(appNamePanel);
        applicantCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applicantCheckboxActionPerformed(evt);
            }
        });

        applicantAddrs.setColumns(20);
        applicantAddrs.setRows(5);
        applicantAddrs.setNextFocusableComponent(appRegion);
        applicantAddrs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                applicantAddrsKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(applicantAddrs);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel17.setText("Address:");

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel18.setText("Phone:");

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel19.setText("Fax:");

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel20.setText("Turnover(cr.):");

        jLabel21.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel21.setText("Category");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel23.setText("Applicant:");

        appPhNo.setNextFocusableComponent(appFax);
        appPhNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                appPhNoKeyReleased(evt);
            }
        });

        appFax.setNextFocusableComponent(appTurnOver);
        appFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                appFaxKeyReleased(evt);
            }
        });

        appTurnOver.setNextFocusableComponent(appCategory);
        appTurnOver.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                appTurnOverFocusLost(evt);
            }
        });
        appTurnOver.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                appTurnOverKeyReleased(evt);
            }
        });

        appNamePanel.setNextFocusableComponent(applicantAddrs);

        javax.swing.GroupLayout appNamePanelLayout = new javax.swing.GroupLayout(appNamePanel);
        appNamePanel.setLayout(appNamePanelLayout);
        appNamePanelLayout.setHorizontalGroup(
            appNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        appNamePanelLayout.setVerticalGroup(
            appNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        appCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SME Client","Mid Corporate","Large Corporate"}));
        appCategory.setSelectedIndex(-1);

        limitForApplicantCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitForApplicantCheckActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel3.setText("Limit For Applicant:");

        appExpBusiness.setNextFocusableComponent(appComprehensiveSpace);
        appExpBusiness.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                appExpBusinessFocusLost(evt);
            }
        });
        appExpBusiness.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                appExpBusinessKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel22.setText("Expected Business:");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel4.setText("Region:");

        appComprehensiveSpace.setColumns(20);
        appComprehensiveSpace.setRows(5);
        appComprehensiveSpace.setNextFocusableComponent(appSolution);
        appComprehensiveSpace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                appComprehensiveSpaceKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(appComprehensiveSpace);

        appComprehensiveSpaceLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        appComprehensiveSpaceLabel.setText("Comprehensive Space:");

        appRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "South Delhi","West Delhi","North Delhi","East Delhi","Central Delhi","Gurgaon","Faridabad","Gaziabad","Noida","Others" }));
        appRegion.setSelectedIndex(-1);

        appSolution.setColumns(20);
        appSolution.setRows(5);
        appSolution.setNextFocusableComponent(beneficiaryCheckbox);
        appSolution.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                appSolutionKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(appSolution);

        appComprehensiveSpaceLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        appComprehensiveSpaceLabel1.setText("Solution:");

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel25.setText("Trade:");

        tradeTextApp.setNextFocusableComponent(appComprehensiveSpace);
        tradeTextApp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tradeTextAppFocusLost(evt);
            }
        });
        tradeTextApp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tradeTextAppKeyReleased(evt);
            }
        });

        indTextApp.setNextFocusableComponent(appComprehensiveSpace);
        indTextApp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                indTextAppFocusLost(evt);
            }
        });
        indTextApp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                indTextAppKeyReleased(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel26.setText("Industry:");

        yearOfIncorpApp.setNextFocusableComponent(appComprehensiveSpace);
        yearOfIncorpApp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearOfIncorpAppFocusLost(evt);
            }
        });
        yearOfIncorpApp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearOfIncorpAppKeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel27.setText("Year Of Incorporation:");

        jLabel28.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel28.setText("External Credit Rating: ");

        extCreditRatingApp.setNextFocusableComponent(appComprehensiveSpace);
        extCreditRatingApp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                extCreditRatingAppFocusLost(evt);
            }
        });
        extCreditRatingApp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                extCreditRatingAppKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(401, 401, 401))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(appComprehensiveSpaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(appComprehensiveSpaceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(94, 94, 94)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                        .addComponent(jScrollPane6)))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(yearOfIncorpApp, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                        .addComponent(extCreditRatingApp)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addGap(208, 208, 208)
                                    .addComponent(applicantCheckbox)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(indTextApp)
                                    .addComponent(tradeTextApp)
                                    .addComponent(appExpBusiness)
                                    .addComponent(appCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(appTurnOver)
                                    .addComponent(appFax)
                                    .addComponent(appPhNo)
                                    .addComponent(appRegion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                    .addComponent(limitForApplicantCheck)
                                    .addComponent(appNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(28, 28, 28))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(applicantCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(limitForApplicantCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(appNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appPhNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(appFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appTurnOver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appExpBusiness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tradeTextApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indTextApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearOfIncorpApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(extCreditRatingApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(appComprehensiveSpaceLabel)
                        .addGap(56, 56, 56)
                        .addComponent(appComprehensiveSpaceLabel1)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        clientdetailsScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JTableHeader header = contactsByCheckboxTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        contactsByCheckboxTable.setRowHeight(30);
        contactsByCheckboxTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Name", "Designation", "Mobile", "Email", "Email-Privileges",""
            }
        ));
        contactsByCheckboxTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactsByCheckboxTableMouseClicked(evt);
            }
        });
        contactsByCheckboxTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contactsByCheckboxTableKeyPressed(evt);
            }
        });
        clientdetailsScrollPane.setViewportView(contactsByCheckboxTable);

        clientDetailsSaveButton.setText("Save");
        clientDetailsSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientDetailsSaveButtonActionPerformed(evt);
            }
        });

        transactionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New Solution" ,"Existing-New Solution","Enhancement","Renewal"}));
        transactionType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                transactionTypeItemStateChanged(evt);
            }
        });
        transactionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionTypeActionPerformed(evt);
            }
        });

        addContactButton.setText("Add Contact");
        addContactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContactButtonActionPerformed(evt);
            }
        });

        clearEditButton.setText("Clear Edit");
        clearEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearEditButtonActionPerformed(evt);
            }
        });

        cAdditionalDetailsButton.setText("Client Additional Details");
        cAdditionalDetailsButton.setEnabled(false);
        cAdditionalDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cAdditionalDetailsButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientDetailsPanelLayout = new javax.swing.GroupLayout(clientDetailsPanel);
        clientDetailsPanel.setLayout(clientDetailsPanelLayout);
        clientDetailsPanelLayout.setHorizontalGroup(
            clientDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(clientDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cAdditionalDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(510, 510, 510)
                        .addComponent(clearEditButton, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientDetailsPanelLayout.createSequentialGroup()
                        .addComponent(clientdetailsScrollPane)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addContactButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clientDetailsSaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        clientDetailsPanelLayout.setVerticalGroup(
            clientDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(transactionType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearEditButton)
                    .addComponent(cAdditionalDetailsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientdetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientDetailsSaveButton)
                    .addComponent(addContactButton)
                    .addComponent(clearButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(clientDetailsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
//delete all row of tables

    private void removeAllRows() {
        // remove all row from client table
        DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
        int r = model.getRowCount();
        System.out.println("row count in edit reset is: " + r);
        if (r > 0) {
            model.getDataVector().removeAllElements();
            contactsByCheckboxTable.repaint();
            counter = 0;
        }
        System.out.println("row count in edit reset is:  after " + r);
    }
    private void beneficiaryCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beneficiaryCheckboxActionPerformed
        if (applicantCheckbox.isSelected()) {
            applicantCheckbox.setSelected(false);
        }
        if (beneficiaryCheckbox.isSelected()) {
            benComprehensiveSpace.setEnabled(true);
            benSolution.setEnabled(true);
            appComprehensiveSpace.setEnabled(false);
            appSolution.setEnabled(false);
            if (clientList != null) {

                benNamePanel.removeAll();
                Collections.sort(clientList);
                AutoCompleteDecorator.decorate(benNameTextField, clientList, false);

                benNamePanel.add(benNameTextField);
                benNamePanel.validate();
                benNamePanel.repaint();

            }

        }
        removeAllRows();
        if (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal")) {
            ParserCaller parserCaller = new ParserCaller();
//            benNameTextField.setText("");

            if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                System.out.println("benlist sizeeee: " + benList);
                Iterator it = benList.iterator();
                benNameCombobox.setSize(benNamePanel.getSize());
                benNameCombobox.removeAllItems();
                benNamePanel.removeAll();
                while (it.hasNext()) {
                    benNameCombobox.addItem(it.next());
                }
                benNamePanel.add(benNameCombobox);
                benNamePanel.validate();
                benNamePanel.repaint();
            }
            if (beneficiaryCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                System.out.println("benlist sizeeee: " + benList);
                if (benList.size() > 0) {
                    Iterator it = benList.iterator();
                    benNameCombobox.setSize(appNamePanel.getSize());
                    benNameCombobox.removeAllItems();
                    appNamePanel.removeAll();
                    while (it.hasNext()) {
                        benNameCombobox.addItem(it.next());
                    }
                    appNamePanel.add(benNameCombobox);
                    appNamePanel.validate();
                    appNamePanel.repaint();
                }
            }

        }
        if (beneficiaryCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
            SingletonClass.clientCompanyName = appNameTextField.getText().trim();
            addContactsOnForcusLost(appNameTextField.getText().trim());
        }
    }//GEN-LAST:event_beneficiaryCheckboxActionPerformed

    private void applicantCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applicantCheckboxActionPerformed
        if (beneficiaryCheckbox.isSelected()) {
            beneficiaryCheckbox.setSelected(false);
        }
        if (applicantCheckbox.isSelected()) {
            benComprehensiveSpace.setEnabled(false);
            appComprehensiveSpace.setEnabled(true);
            benSolution.setEnabled(false);
            appSolution.setEnabled(true);
            if (clientList != null) {

                appNamePanel.removeAll();
                Collections.sort(clientList);
                AutoCompleteDecorator.decorate(appNameTextField, clientList, false);

                appNamePanel.add(appNameTextField);
                appNamePanel.validate();
                appNamePanel.repaint();

            }

        }
        if (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal")) {
            ParserCaller parserCaller = new ParserCaller();
//            appNameTextField.setText("");

            if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                System.out.println("benlist sizeeee: " + benList);
                if (benList.size() > 0) {
                    Iterator it = benList.iterator();
                    benNameCombobox.setSize(benNamePanel.getSize());
                    benNameCombobox.removeAllItems();
                    benNamePanel.removeAll();
                    while (it.hasNext()) {
                        benNameCombobox.addItem(it.next());
                    }
                    benNamePanel.add(benNameCombobox);
                    benNamePanel.validate();
                    benNamePanel.repaint();
                }
            }
            if (beneficiaryCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                System.out.println("benlist sizeeee: " + benList);
                if (benList.size() > 0) {
                    Iterator it = benList.iterator();
                    benNameCombobox.setSize(appNamePanel.getSize());
                    benNameCombobox.removeAllItems();
                    appNamePanel.removeAll();
                    while (it.hasNext()) {
                        benNameCombobox.addItem(it.next());
                    }
                    appNamePanel.add(benNameCombobox);
                    appNamePanel.validate();
                    appNamePanel.repaint();
                }
            }

        }
//        if(applicantCheckbox.isSelected()){
//            benExpBusiness.setEnabled(false);  
//            appExpBusiness.setEnabled(true);          
//        }
        removeAllRows();
        if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {

            SingletonClass.clientCompanyName = appNameTextField.getText().trim();
            addContactsOnForcusLost(appNameTextField.getText().trim());
        }
    }//GEN-LAST:event_applicantCheckboxActionPerformed
// call this methodon save button clicked action
    private void clientDetailsSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientDetailsSaveButtonActionPerformed
        try {

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            StringBuffer clientDetailsBuffer = new StringBuffer();
            boolean isCalled = false;
            boolean isClient = false;
            boolean isAppBenPhone = true;
            boolean isContactPhone = true;
            StringBuffer clientContactDetailsBuffer = new StringBuffer();
            clientDetailsBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=" + "txnClientDetails" + "&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.txStatus).append("=").append(SingletonClass.txStatusSelect).append("&").append(SingletonClass.txType).append("=").append(transactionType.getSelectedItem().toString());
            boolean isPopedUp = false;
            System.out.println("SingletonClass.txIdEdit at save click: " + SingletonClass.txIdEdit);
            if (SingletonClass.txIdEdit != null && !SingletonClass.txIdEdit.equals("")) {
                clientDetailsBuffer.append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit);
            }

            if (benNameTextField!=null && benNameTextField.getText() != null && !"".equals(benNameTextField.getText()) && limitForBeneficiaryCheck.isSelected()
                    && (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal"))) {
                clientDetailsBuffer.append("&").append(SingletonClass.benNmae).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
//                clientDetailsBuffer.append("&").append(SingletonClass.appNmae).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
            }
            else if (appNameTextField!=null && appNameTextField.getText() != null && !"".equals(appNameTextField.getText()) && limitForApplicantCheck.isSelected()
                    && (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal"))) {
                clientDetailsBuffer.append("&").append(SingletonClass.appNmae).append("=").append(URLEncoder.encode(appNameTextField.getText().trim()));
//                clientDetailsBuffer.append("&").append(SingletonClass.appNmae).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
            }
            if (benNameCombobox.getSelectedItem() != null && !"".equals(benNameCombobox.getSelectedItem()) && limitForApplicantCheck.isSelected()
                    && (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal"))) {
                clientDetailsBuffer.append("&").append(SingletonClass.benNmae).append("=").append(URLEncoder.encode(benNameCombobox.getSelectedItem().toString()));
                clientDetailsBuffer.append("&").append(SingletonClass.appNmae).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
            } else if(!transactionType.getSelectedItem().toString().equals("Enhancement") && !transactionType.getSelectedItem().toString().equals("Renewal")){
                clientDetailsBuffer.append("&").append(SingletonClass.appNmae).append("=").append(URLEncoder.encode(appNameTextField.getText().trim()));
            }
            clientDetailsBuffer.append("&").append(SingletonClass.appAddrs).append("=").append(URLEncoder.encode(applicantAddrs.getText()));
//        System.out.println("URLEncoder.encode(applicantAddrs.getText()): "+URLEncoder.encode(applicantAddrs.getText()));
            clientDetailsBuffer.append("&").append(SingletonClass.appPhn).append("=").append(appPhNo.getText().replaceAll(" ", ""));
            System.out.println("SingletonClass.appRegion: " + appRegion.getSelectedItem());

            clientDetailsBuffer.append("&").append(SingletonClass.appFax).append("=").append(appFax.getText().replaceAll(" ", ""));
            clientDetailsBuffer.append("&").append(SingletonClass.appTurnOver).append("=").append(appTurnOver.getText().replaceAll(" ", ""));
            if (appCategory.getSelectedItem() != null) {
                clientDetailsBuffer.append("&").append(SingletonClass.appCategory).append("=").append(appCategory.getSelectedItem().toString());
            }
//            clientDetailsBuffer.append("&").append(SingletonClass.limitAmount).append("=").append(appLimitAmount.getText().replaceAll(" ", ""));

// get beneficiary values from UI, and append for URL.
            if (benNameCombobox.getSelectedItem() != null && !"".equals(benNameCombobox.getSelectedItem()) && limitForBeneficiaryCheck.isSelected()
                    && (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal"))) {
                clientDetailsBuffer.append("&").append(SingletonClass.benNmae).append("=").append(URLEncoder.encode(benNameCombobox.getSelectedItem().toString()));
                clientDetailsBuffer.append("&").append(SingletonClass.appNmae).append("=").append(URLEncoder.encode(appNameTextField.getText().trim()));
            } else if(!transactionType.getSelectedItem().toString().equals("Enhancement") && !transactionType.getSelectedItem().toString().equals("Renewal")){
                clientDetailsBuffer.append("&").append(SingletonClass.benNmae).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
            }
            clientDetailsBuffer.append("&").append(SingletonClass.benAddrs).append("=").append(URLEncoder.encode(beneficiaryAddrs.getText()));
            clientDetailsBuffer.append("&").append(SingletonClass.benPhn).append("=").append(beneficiaryPhNo.getText().replaceAll(" ", ""));

            clientDetailsBuffer.append("&").append(SingletonClass.benFax).append("=").append(beneficiaryFax.getText().replaceAll(" ", ""));
            clientDetailsBuffer.append("&").append(SingletonClass.benTurnOver).append("=").append(beneficiaryTurnOver.getText().replaceAll(" ", ""));
            System.out.println("beneficiaryCategory.getSelectedItem(): " + beneficiaryCategory.getSelectedItem());
            if (beneficiaryCategory.getSelectedItem() != null) {
                clientDetailsBuffer.append("&").append(SingletonClass.benCategory).append("=").append(beneficiaryCategory.getSelectedItem().toString());
            }

            if (applicantCheckbox.isSelected()) {
                clientDetailsBuffer.append("&").append(SingletonClass.exBusiness).append("=").append(appExpBusiness.getText().replaceAll(" ", ""));
                clientDetailsBuffer.append("&").append(SingletonClass.compSpace).append("=").append(URLEncoder.encode(appComprehensiveSpace.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.solution).append("=").append(URLEncoder.encode(appSolution.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.trade).append("=").append(URLEncoder.encode(tradeTextApp.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.industry).append("=").append(URLEncoder.encode(indTextApp.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.yearOfIncorp).append("=").append(URLEncoder.encode(yearOfIncorpApp.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.extCreditRating).append("=").append(URLEncoder.encode(extCreditRatingApp.getText()));
                if (appRegion.getSelectedItem() != null) {
                    clientDetailsBuffer.append("&").append(SingletonClass.appRegion).append("=").append(URLEncoder.encode(appRegion.getSelectedItem().toString()));
                }
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^URL is: " + clientDetailsBuffer);
            } else {
                clientDetailsBuffer.append("&").append(SingletonClass.exBusiness).append("=").append(benExpBusiness.getText().replaceAll(" ", ""));
                clientDetailsBuffer.append("&").append(SingletonClass.compSpace).append("=").append(URLEncoder.encode(benComprehensiveSpace.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.solution).append("=").append(URLEncoder.encode(benSolution.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.trade).append("=").append(URLEncoder.encode(tradeTextBen.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.industry).append("=").append(URLEncoder.encode(indTextBen.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.yearOfIncorp).append("=").append(URLEncoder.encode(yearOfIncorpBen.getText()));
                clientDetailsBuffer.append("&").append(SingletonClass.extCreditRating).append("=").append(URLEncoder.encode(extCreditRatingBen.getText()));
                if (benRegion.getSelectedItem() != null) {
                    clientDetailsBuffer.append("&").append(SingletonClass.benRegion).append("=").append(URLEncoder.encode(benRegion.getSelectedItem().toString()));
                }
            }

//            clientDetailsBuffer.append("&").append(SingletonClass.benLimitAmount).append("=").append(benlimitAmount.getText().replaceAll(" ", ""));
            // check applicant is client or beneficiary is client and get the value from textfield  if not blank or null
            boolean isValidateClient = true;
            if (!applicantCheckbox.isSelected() && !beneficiaryCheckbox.isSelected()) {
                if ((appNameTextField.getText().trim() == null || appNameTextField.getText().trim().equals("")) && (appNameTextField.getText().trim() == null || appNameTextField.getText().trim().equals(""))) {
                    if (!isPopedUp) {
                        JOptionPane.showMessageDialog(this, "Please fill the applicant.", "", 0);
                        isPopedUp = true;
                    }
                }
            }
            if (applicantCheckbox.isSelected()) {
                if (appCategory.getSelectedIndex() == -1) {
                    isPopedUp = true;
                    JOptionPane.showMessageDialog(this, "Please select the category of applicant.", "", 0);
                } else if (appRegion.getSelectedIndex() == -1) {
                    isPopedUp = true;
                    JOptionPane.showMessageDialog(this, "Please select the region of applicant.", "", 0);
                }
            }

            if (beneficiaryCheckbox.isSelected()) {
                if (beneficiaryCategory.getSelectedIndex() == -1) {
                    isPopedUp = true;
                    JOptionPane.showMessageDialog(this, "Please select the category of beneficiary.", "", 0);
                } else if (benRegion.getSelectedIndex() == -1) {
                    isPopedUp = true;
                    JOptionPane.showMessageDialog(this, "Please select the region of beneficiary.", "", 0);
                }
            }
            if (applicantCheckbox.isSelected()) {
                isClient = true;
                if (appNameTextField.getText().trim() == null || appNameTextField.getText().trim().equals("")) {
                    isValidateClient = false;
                    JOptionPane.showMessageDialog(this, "Please fill the applicant.", "", 0);
                    isPopedUp = true;
                } else {
                    isFillClient = true;
                    SingletonClass.clientCompanyName = appNameTextField.getText().trim();
                    SingletonClass.clientRegion = appRegion.getSelectedItem().toString();
                    SingletonClass.clientCompSpace = appComprehensiveSpace.getText();
                    SingletonClass.clientSolution = appSolution.getText();
                    clientDetailsBuffer.append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(appNameTextField.getText().trim()));
                }
            } else if (beneficiaryCheckbox.isSelected()) {
                isClient = true;
                if (benNameTextField.getText().trim() == null || benNameTextField.getText().trim().equals("")) {
                    isValidateClient = false;
                    if (!isPopedUp) {
                        JOptionPane.showMessageDialog(this, "Please fill the beneficiary.", "", 0);
                        isPopedUp = true;
                    }
                } else {
                    isFillClient = true;
                    SingletonClass.clientCompanyName = benNameTextField.getText().trim();
                    if (benRegion.getSelectedItem() != null) {
                        SingletonClass.clientRegion = benRegion.getSelectedItem().toString();
                    }
                    SingletonClass.clientCompSpace = benComprehensiveSpace.getText();
                    SingletonClass.clientSolution = benSolution.getText();
                    clientDetailsBuffer.append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
//                    appCategory.set
                }
            } // check app phone no. length
            else if (appPhNo.getText() != null && !appPhNo.getText().equals("")) {
                if (appPhNo.getText().length() < 8 || appPhNo.getText().length() > 14) {
                    //set alert mesage
                    isAppBenPhone = false;
                    System.out.println("appppp " + appPhNo.getText());
                    isValidateClient = false;
                    if (!isPopedUp) {
                        JOptionPane.showMessageDialog(this, "invalid applicant phone no.", "", 0);
                        isPopedUp = true;
                    }
                }
            } // check ben phone no. length
            else if (beneficiaryPhNo.getText() != null && !beneficiaryPhNo.getText().equals("")) {
                if (beneficiaryPhNo.getText().length() < 8 || beneficiaryPhNo.getText().length() > 14) {
                    //set alert mesage
                    isAppBenPhone = false;
                    System.out.println("appppp " + beneficiaryPhNo.getText());
                    isValidateClient = false;
                    if (!isPopedUp) {
                        JOptionPane.showMessageDialog(this, "invalid beneficiary phone no.", "", 0);
                        isPopedUp = true;
                    }
                }
            }
// get the total row count and then iterate the total rows data for saving the details
            DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
            int r = model.getRowCount();
            System.out.println("row count before click: " + r);
            if (r > 0) {
                for (int c = 0; c < r; c++) {
                    clientContactDetailsBuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=" + "saveClientContacts" + "&").append(SingletonClass.userName).append("=").append(email).append("&").append(SingletonClass.txStatus).append("=").append(SingletonClass.txStatusSelect);

                    if (SingletonClass.isEdit == true) {
                        clientContactDetailsBuffer.append("&").append(SingletonClass.txNo).append("=").append(SingletonClass.txIdEdit);
                    }
                    clientContactDetailsBuffer.append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName));

                    if (contactsByCheckboxTable.getValueAt(c, 1) != null) {
                        clientContactDetailsBuffer.append("&" + SingletonClass.clientContactName + "=" + ((String) contactsByCheckboxTable.getValueAt(c, 1)).trim());
                    }
                    if (contactsByCheckboxTable.getValueAt(c, 2) != null) {
                        clientContactDetailsBuffer.append("&" + SingletonClass.clientContactDesignation + "=" + ((String) contactsByCheckboxTable.getValueAt(c, 2)).trim());
                    }
                    if (contactsByCheckboxTable.getValueAt(c, 3) != null) {
                        clientContactDetailsBuffer.append("&" + SingletonClass.clientContactMobile + "=" + ((String) contactsByCheckboxTable.getValueAt(c, 3)).trim());
                    }
                    if (contactsByCheckboxTable.getValueAt(c, 5) != null) {
                        clientContactDetailsBuffer.append("&" + SingletonClass.notifyStr + "=").append(contactsByCheckboxTable.getValueAt(c, 5).toString());
                    }

                    if (contactsByCheckboxTable.getValueAt(c, 4) != null) {
//                        Pattern p = Pattern.compile("[a-zA-Z]*[0-9]*[._-]*[a-zA-Z]*[0-9]*@[a-zA-Z]*.[a-zA-Z]*");
//                        Matcher matchPattern = p.matcher(contactsByCheckboxTable.getValueAt(c, 4).toString());
//                        isContactEmail = matchPattern.matches();
//                        if (isContactEmail) {
                        clientContactDetailsBuffer.append("&").append(SingletonClass.clientContactEmail).append("=").append(((String) contactsByCheckboxTable.getValueAt(c, 4)).trim());
//                        } else if (isContactEmail == false) {
//                            if (!isPopedUp) {
//                                JOptionPane.showMessageDialog(this, "Invalid contact email.", "", 0);
//                                isPopedUp = true;
//                            }
//                        }
                    }

                    if (model.getRowCount() == 0) {
                        isContactTableAdd = false;
                    } else {
                        isContactTableAdd = true;
                    }
                    System.out.println("contact no: " + contactsByCheckboxTable.getValueAt(c, 3));
                    if (isValidateClient) {
                        if (contactsByCheckboxTable.getValueAt(c, 1) == null || contactsByCheckboxTable.getValueAt(c, 1).toString().equals("")) {
                            if (!isPopedUp) {
                                JOptionPane.showMessageDialog(this, "Please enter the name of contact person.", "", 0);
                                isPopedUp = true;
                            }
                        } //                        else if (contactsByCheckboxTable.getValueAt(c, 2) == null || contactsByCheckboxTable.getValueAt(c, 2).toString().equals("")) {
                        //                            if (!isPopedUp) {
                        //                                JOptionPane.showMessageDialog(this, "Please enter the designation of contact person.", "", 0);
                        //                                isPopedUp = true;
                        //                            }
                        //                        } else if (contactsByCheckboxTable.getValueAt(c, 3) == null || contactsByCheckboxTable.getValueAt(c, 3).toString().equals("")) {
                        //                            if (!isPopedUp) {
                        //                                JOptionPane.showMessageDialog(this, "Please enter the contact no of contact person.", "", 0);
                        //                                isPopedUp = true;
                        //                            }
                        //                        } else if (contactsByCheckboxTable.getValueAt(c, 4) == null || contactsByCheckboxTable.getValueAt(c, 4).toString().equals("")) {
                        //                            if (!isPopedUp) {
                        //                                JOptionPane.showMessageDialog(this, "Please enter the email of contact person.", "", 0);
                        //                                isPopedUp = true;
                        //                            }
                        //                        } 
                        //                        else if (contactsByCheckboxTable.getValueAt(c, 5) == null || contactsByCheckboxTable.getValueAt(c, 5).toString().equals("")) {
                        //                            if (!isPopedUp) {
                        //                                JOptionPane.showMessageDialog(this, "Please select the email privileges of contact person.", "", 0);
                        //                                isPopedUp = true;
                        //                            }
                        //                        } // check contact phone no. length
                        else if (contactsByCheckboxTable.getValueAt(c, 3) != null && contactsByCheckboxTable.getValueAt(c, 3).toString().length() > 0) {
                            if (contactsByCheckboxTable.getValueAt(c, 3).toString().length() < 8 || contactsByCheckboxTable.getValueAt(c, 3).toString().length() > 50) {
                                //set alert mesage
                                isContactPhone = false;
                                if (!isPopedUp) {
                                    JOptionPane.showMessageDialog(this, "invalid contact mobile no.", "", 0);
                                    isPopedUp = true;
                                }
                                break;
                            }
                        }
                    }

// save this transaction if and only if all below booleans are true.
                    if (!limitForApplicantCheck.isSelected() && !limitForBeneficiaryCheck.isSelected()) {
                        if (!isPopedUp) {
                            isPopedUp = true;
                            JOptionPane.showMessageDialog(this, "Please select 'Limit For' of applicant or beneficiary.", "", 0);
                        }
                    } else if (limitForApplicantCheck.isSelected() && (appNameTextField.getText().trim() == null || "".equals(appNameTextField.getText().trim())) && (benNameCombobox.getSelectedItem() == null || "".equals(benNameCombobox.getSelectedItem().toString()))) {
                        if (!isPopedUp) {
                            isPopedUp = true;
                            JOptionPane.showMessageDialog(this, "Please fill the applicant details for limit.", "", 0);
                        }
                    } else if (limitForBeneficiaryCheck.isSelected() && (benNameTextField.getText().trim() == null || "".equals(benNameTextField.getText().trim())) && (benNameCombobox.getSelectedItem() == null || "".equals(benNameCombobox.getSelectedItem().toString()))) {
                        if (!isPopedUp) {
                            isPopedUp = true;
                            JOptionPane.showMessageDialog(this, "Please fill the beneficiary details for limit.", "", 0);
                        }
                    } else if (applicantCheckbox.isSelected() && (appExpBusiness.getText() == null || "".equals(appExpBusiness.getText()))) {
                        if (!isPopedUp) {
                            isPopedUp = true;
                            JOptionPane.showMessageDialog(this, "Please fill the Expected Business.", "", 0);
                        }
                    } else if (beneficiaryCheckbox.isSelected() && (benExpBusiness.getText() == null || "".equals(benExpBusiness.getText()))) {
                        if (!isPopedUp) {
                            isPopedUp = true;
                            JOptionPane.showMessageDialog(this, "Please fill the Expected Business.", "", 0);
                        }
                    } else if (isContactTableAdd && isClient && isFillClient) {
                        if (limitForApplicantCheck.isSelected()) {
                            clientDetailsBuffer.append("&").append(SingletonClass.limitFor).append("=").append(URLEncoder.encode(appNameTextField.getText().trim()));
                        }
                        if (limitForBeneficiaryCheck.isSelected()) {
                            clientDetailsBuffer.append("&").append(SingletonClass.limitFor).append("=").append(URLEncoder.encode(benNameTextField.getText().trim()));
                        }
                        System.out.println("buffer client: " + clientDetailsBuffer);
                        String urlString = clientDetailsBuffer.toString().replaceAll(" ", "%20");
                        String ccUrlString = clientContactDetailsBuffer.toString().replaceAll(" ", "%20");
                        callServerUrl serverUrl = new callServerUrl();

                        if (!isCalled && !isPopedUp) {

                            if (isAppBenPhone && isContactPhone) {
                                System.out.println("clientDetailsBuffer: " + urlString.toString());
                                SingletonClass.txIdEdit = serverUrl.urlProcessiong(urlString);
                                System.out.println("client details saved response: " + SingletonClass.txIdEdit + " SingletonClass.benNmae: " + SingletonClass.benNmae);
                            }
                        }
                        if (SingletonClass.txIdEdit != null && SingletonClass.txIdEdit.equalsIgnoreCase("invalid")) {
                            if (!isCalled) {
                                if (!isPopedUp) {
                                    isPopedUp = true;
                                    JOptionPane.showMessageDialog(this, "Problem Occured! Try agian later.", "", 0);
                                    SingletonClass.txIdEdit = "";
                                }
                            }
                        } else if (SingletonClass.txIdEdit != null && SingletonClass.txIdEdit.equalsIgnoreCase("duplicate")) {
                            if (!isCalled) {
                                if (!isPopedUp) {
                                    isPopedUp = true;
                                    JOptionPane.showMessageDialog(this, "This client already exist in the system. Please contact your manager regarding the same.", "", 0);
                                    SingletonClass.txIdEdit = "";
                                }
                            }
                        }


//                    if (isAppBenPhone && isContactPhone) {
                        if (!isPopedUp) {
                            serverUrl.urlProcessiong(ccUrlString);
                        }

//                        System.out.println("clientDetailsBuffer:111 " + ccUrlString.toString());
                        clientContactDetailsBuffer.delete(0, clientContactDetailsBuffer.length());
//                    }

//                    else {
//                        SingletonClass.contactPersons.addItem(contactsByCheckboxTable.getValueAt(c, 1).toString());                     
//                    }
                        isCalled = true;
                        serverUrl = null;
                    } else {
                        if (isClient == false) {
                            if (!isPopedUp) {
                                JOptionPane.showMessageDialog(this, "Please specify the client.", "", 0);
                                isPopedUp = true;
                            }

                        }
                    }
                }
                if (isCalled && isAppBenPhone && isContactPhone) {

                    if (!isPopedUp) {
//                    JOptionPane.showMessageDialog(this, "Client Details Saved Successfully, Please fill the Meeting Details", "", 1);
                        isPopedUp = true;
                        SingletonClass.isSave = true;
                        SingletonClass.txTypeEdit = (String) transactionType.getSelectedItem();
                        if (limitForApplicantCheck.isSelected()) {
                            SingletonClass.clientBenName = appNameTextField.getText().trim();
                        } else if (limitForBeneficiaryCheck.isSelected()) {
                            SingletonClass.clientBenName = benNameTextField.getText().trim();
                        }
                        SingletonClass.nextOpenAccordion = "MEETING DETAILS";
                        newTxPanel.refreshScreen(this);
                        newTxPanel.repaint();
                    }

                }
            } else {
                if (isClient == false) {
                    if (!isPopedUp) {
                        JOptionPane.showMessageDialog(this, "Please specify the client.", "", 0);
                        isPopedUp = true;
                    }

                } else if (!isPopedUp) {
                    JOptionPane.showMessageDialog(this, "Please add the contact details.", "", 0);
                    isPopedUp = true;
                }

            }
            this.setCursor(Cursor.getDefaultCursor());
            System.out.println("try finished......");
        } catch (Exception ex) {
            ex.printStackTrace();
            this.setCursor(Cursor.getDefaultCursor());
            System.out.println("catch finished......");

        } finally {
            System.out.println("in finally....");
        }
    }//GEN-LAST:event_clientDetailsSaveButtonActionPerformed
// add the row in contacts table.
    private void addContactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContactButtonActionPerformed
        if (!applicantCheckbox.isSelected() && !beneficiaryCheckbox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please specify the client.", "", 0);
        } else {
            TableColumn col1 = contactsByCheckboxTable.getColumnModel().getColumn(0);
            col1.setMaxWidth(50);
            col1.setWidth(50);
            DefaultTableModel tableModel = (DefaultTableModel) contactsByCheckboxTable.getModel();
            tableModel.insertRow(counter, new Object[]{});
            contactsByCheckboxTable.setValueAt(counter + 1, counter, 0);
            counter++;
        }
    }//GEN-LAST:event_addContactButtonActionPerformed

    private void contactsByCheckboxTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactsByCheckboxTableMouseClicked
// delete the contact row if extra added

        if (SingletonClass.designation.equalsIgnoreCase("Credit Analyst") || SingletonClass.isView) {
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) contactsByCheckboxTable.getModel();
            int row = contactsByCheckboxTable.rowAtPoint(evt.getPoint());
            int col = contactsByCheckboxTable.columnAtPoint(evt.getPoint());
            StringBuffer contactDeletebuffer = new StringBuffer();

            if (col == 6) {
                try {
                    // check that there is no data entered in table
                    if (contactsByCheckboxTable.getValueAt(row, 1) != null) {
                        contactDeletebuffer.append(SingletonClass.httpServerUrl).append(SingletonClass.operaionType).append("=deleteClientContacts").append("&").append(SingletonClass.clientIsBenOrApp).append("=").append(URLEncoder.encode(SingletonClass.clientCompanyName)).append("&").append(SingletonClass.clientContactName).append("=").append(contactsByCheckboxTable.getValueAt(row, 1).toString()).append("&").append(SingletonClass.clientContactEmail)
                                .append("=").append(contactsByCheckboxTable.getValueAt(row, 4).toString()).append("&").append(SingletonClass.userName).append("=").append(email);
                        callServerUrl serverUrl1 = new callServerUrl();
                        String urlString = contactDeletebuffer.toString().replaceAll(" ", "%20");
                        serverUrl1.urlProcessiong(urlString);
                        urlString = null;
                    }
                    tableModel.removeRow(row);
                    counter--;
                    contactDeletebuffer = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // here user on clicking view a pop up where he can select clients privelege to recieve emails.
            if (col == 5) {
                System.out.println("<<<<<<<<<");
                JCheckBox[] checkbox;
                final JCheckBox senPre = new JCheckBox("Send Preliminary Offer");
                final JCheckBox sendSla = new JCheckBox("Send SLA");
                final JCheckBox sendDocList = new JCheckBox("Send Document List");
                final JCheckBox sendCreditAd = new JCheckBox("Send Credit Advice");
                final JCheckBox sendBill = new JCheckBox("Send Bills");
                final JCheckBox selectAllCheck = new JCheckBox("Select All");
                checkbox = new JCheckBox[]{senPre, sendSla, sendDocList, sendCreditAd, sendBill, selectAllCheck};

                selectAllCheck.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (selectAllCheck.isSelected()) {
                            senPre.setSelected(true);
                            sendSla.setSelected(true);
                            sendDocList.setSelected(true);
                            sendCreditAd.setSelected(true);
                            sendBill.setSelected(true);
                        }
                    }
                });
                if (contactsByCheckboxTable.getValueAt(row, 5) != null && !contactsByCheckboxTable.getValueAt(row, 5).toString().equals("")) {
                    String getPTText = contactsByCheckboxTable.getValueAt(row, 5).toString();
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
                int status = JOptionPane.showConfirmDialog(this, params, "Client's Email Priveledge", JOptionPane.OK_CANCEL_OPTION);
                if (status == JOptionPane.CANCEL_OPTION) {
                } else {
                    int count = 0;
                    StringBuffer privFor = new StringBuffer("");

                    if (checkbox[5].isSelected()) {

                        while (count < checkbox.length) {
//                            if (checkbox[count].isSelected()) {
                            if (count != 5) {
                                privFor.append(checkbox[count].getText()).append(",");
                            }
//                            }
                            count++;
                        }
                    } else {

                        while (count < checkbox.length) {
                            if (checkbox[count].isSelected()) {
                                privFor.append(checkbox[count].getText()).append(",");
                            }
                            count++;
                        }
                    }
                    String emailPriv = privFor.toString();
                    contactsByCheckboxTable.setValueAt(emailPriv, row, 5);
                }
            }
        }
    }//GEN-LAST:event_contactsByCheckboxTableMouseClicked

    public void clearValues() {
        // remove all row from client table
        DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
        int r = model.getRowCount();
        System.out.println("row count in edit reset is: " + r);
        if (r > 0) {
            model.getDataVector().removeAllElements();
            contactsByCheckboxTable.repaint();
            contactsByCheckboxTable.validate();
            counter = 0;
        }
        // set applicant details to default
        transactionType.setSelectedIndex(0);
        applicantCheckbox.setSelected(false);
        beneficiaryCheckbox.setSelected(false);
        appNameTextField.setText("");
        applicantAddrs.setText("");
        appPhNo.setText("");
        appRegion.setSelectedItem("");
        appFax.setText("");
        appTurnOver.setText("");
        appCategory.setSelectedIndex(-1);
//        appLimitAmount.setText("");

        // set beneficiary details to default

        benNameTextField.setText("");
        beneficiaryAddrs.setText("");
        beneficiaryPhNo.setText("");
        benRegion.setSelectedItem("");
        beneficiaryFax.setText("");
        beneficiaryTurnOver.setText("");
        beneficiaryCategory.setSelectedIndex(-1);
//        benlimitAmount.setText("");

        SingletonClass.isEdit = false;
        SingletonClass.isView = false;
        SingletonClass.txIdEdit = "";
        SingletonClass.isFirstAcc = false;
        SingletonClass.txStatusSelect = "Identification Of Client";

    }
    private void clearEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearEditButtonActionPerformed


        // remove all row from client table
        DefaultTableModel model = (DefaultTableModel) contactsByCheckboxTable.getModel();
        int r = model.getRowCount();
        System.out.println("row count in edit reset is: " + r);
        if (r > 0) {
            model.getDataVector().removeAllElements();
            counter = 0;
        }
        // set applicant details to default
        transactionType.setSelectedIndex(0);
        applicantCheckbox.setSelected(false);
        beneficiaryCheckbox.setSelected(false);
        appNameTextField.setText("");
        applicantAddrs.setText("");
        appPhNo.setText("");
        appRegion.setSelectedItem("");
        appFax.setText("");
        appTurnOver.setText("");
        appCategory.setSelectedIndex(-1);
//        appLimitAmount.setText("");

        // set beneficiary details to default

        benNameTextField.setText("");
        beneficiaryAddrs.setText("");
        beneficiaryPhNo.setText("");
        benRegion.setSelectedItem("");
        beneficiaryFax.setText("");
        beneficiaryTurnOver.setText("");
        beneficiaryCategory.setSelectedIndex(-1);
//        benlimitAmount.setText("");

        SingletonClass.isEdit = false;
        SingletonClass.isView = false;
        SingletonClass.txIdEdit = "";
        SingletonClass.isFirstAcc = false;
        SingletonClass.txStatusSelect = "Identification Of Client";

    }//GEN-LAST:event_clearEditButtonActionPerformed

    private void beneficiaryPhNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beneficiaryPhNoKeyReleased

        boolean isInt = isInt(beneficiaryPhNo.getText());
        System.out.println("phnPattern.matches(): " + beneficiaryPhNo.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (isInt != true) {
            JOptionPane.showMessageDialog(this, "invalid phone no.", "", 0);
            beneficiaryPhNo.setText("");
        }
    }//GEN-LAST:event_beneficiaryPhNoKeyReleased

    private void appPhNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appPhNoKeyReleased

        boolean isInt = isInt(appPhNo.getText());
        System.out.println("phnPattern.matches(): " + appPhNo.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (isInt != true) {
            JOptionPane.showMessageDialog(this, "invalid phone no.", "", 0);
            appPhNo.setText("");
        }
    }//GEN-LAST:event_appPhNoKeyReleased

    private void appFaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appFaxKeyReleased

        boolean isInt = isInt(appFax.getText());
        System.out.println("appFaxPattern.matches(): " + appFax.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (isInt != true) {
            JOptionPane.showMessageDialog(this, "invalid fax no.", "", 0);
            appFax.setText("");
        }
    }//GEN-LAST:event_appFaxKeyReleased

    private void appTurnOverKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appTurnOverKeyReleased

        boolean isFloat = isFloat(appTurnOver.getText());
        System.out.println("appTurnOverPattern.matches(): " + appTurnOver.getText() + "   " + isFloat);

        // get the applicant values from UI, and append for URL.
        if (isFloat != true) {
            JOptionPane.showMessageDialog(this, "invalid turnover amount.", "", 0);
            appTurnOver.setText("");
        }
    }//GEN-LAST:event_appTurnOverKeyReleased

    private void beneficiaryFaxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beneficiaryFaxKeyReleased

        boolean isInt = isInt(beneficiaryFax.getText());
        System.out.println("beneficiaryFaxPattern.matches(): " + beneficiaryFax.getText() + "   " + isInt);

        // get the applicant values from UI, and append for URL.
        if (isInt != true) {
            JOptionPane.showMessageDialog(this, "invalid fax no.", "", 0);
            beneficiaryFax.setText("");
        }
    }//GEN-LAST:event_beneficiaryFaxKeyReleased

    private void beneficiaryTurnOverKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beneficiaryTurnOverKeyReleased

        boolean isFloat = isFloat(beneficiaryTurnOver.getText());
        System.out.println("beneficiaryTurnOverPattern.matches(): " + beneficiaryTurnOver.getText() + "   " + isFloat);

        // get the applicant values from UI, and append for URL.
        if (isFloat != true) {
            JOptionPane.showMessageDialog(this, "invalid turnover  no.", "", 0);
            beneficiaryTurnOver.setText("");
        }
    }//GEN-LAST:event_beneficiaryTurnOverKeyReleased

    public boolean isFloat(String floatNo) {
        boolean isFloat = false;
        if (floatNo.equals("")) {
            isFloat = true;
        } else {
            try {
                Double.parseDouble(floatNo);
                isFloat = true;
            } catch (Exception ex) {
//                ex.printStackTrace();
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
//                ex.printStackTrace();
                isInt = false;
            }
        }
        return isInt;
    }

    private String replaceEnterKey(String stringText) {
        if (stringText != null && stringText.length() > 0) {
            stringText = stringText.replace("\n", " ");
        }
        return stringText;

    }
    private void applicantAddrsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_applicantAddrsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            System.out.println(evt.getModifiers());
            if (evt.getModifiers() > 0) {
                applicantAddrs.transferFocusBackward();
            } else {
                applicantAddrs.transferFocus();
            }
            evt.consume();
        }
    }//GEN-LAST:event_applicantAddrsKeyPressed
    private void addNewConactTable() {
//        contactsByCheckboxTable
        contactsByCheckboxTable = null;
        contactsByCheckboxTable = new javax.swing.JTable();
        JTableHeader header = contactsByCheckboxTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        header.setPreferredSize(new Dimension(600, 35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        contactsByCheckboxTable.setRowHeight(30);
        contactsByCheckboxTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
            "", "Name", "Designation", "Mobile", "Email", ""
        }));
        contactsByCheckboxTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactsByCheckboxTableMouseClicked(evt);
            }
        });
        clientdetailsScrollPane.setViewportView(contactsByCheckboxTable);

    }
    private void beneficiaryAddrsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beneficiaryAddrsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            System.out.println(evt.getModifiers());
            if (evt.getModifiers() > 0) {
                beneficiaryAddrs.transferFocusBackward();
            } else {
                beneficiaryAddrs.transferFocus();
            }
            evt.consume();
        }
    }//GEN-LAST:event_beneficiaryAddrsKeyPressed

    private void cAdditionalDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cAdditionalDetailsButtonActionPerformed
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (SingletonClass.clientCompanyName != null && !"".equals(SingletonClass.clientCompanyName)) {
                new ClientAdditionalInfo(email).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill the client details first..", "", 0);
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_cAdditionalDetailsButtonActionPerformed

    private void appTurnOverFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appTurnOverFocusLost
//        "Bullion", "Large Corporate: To- 1000 Cr.+", "Mid Corporate: To: 500-1000 Cr.", "Emerging Corporate: To: 100-500 Cr.", "SME Client: To: Less than 100 Cr."     
//        "Bullion" , "Emerging Corporate", "Large Corporate","Mid Corporate", "SME Client"
        if (appTurnOver.getText() != null && !appTurnOver.equals("")) {
            double appTurnOverInFloat = Double.parseDouble(appTurnOver.getText());
            if (appTurnOverInFloat > 0 && appTurnOverInFloat <= Double.parseDouble("2000000000")) {
                appCategory.setSelectedIndex(0);
            } else if (appTurnOverInFloat > 2000000000 && appTurnOverInFloat <= Double.parseDouble("10000000000")) {
                appCategory.setSelectedIndex(1);
            } else if (appTurnOverInFloat > Double.parseDouble("10000000000")) {
                appCategory.setSelectedIndex(2);
            }
        }
    }//GEN-LAST:event_appTurnOverFocusLost

    private void beneficiaryTurnOverFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_beneficiaryTurnOverFocusLost
        if (beneficiaryTurnOver.getText() != null && !beneficiaryTurnOver.equals("")) {
            double appTurnOverInFloat = Double.parseDouble(beneficiaryTurnOver.getText());
            if (appTurnOverInFloat > 0 && appTurnOverInFloat <= Double.parseDouble("2000000000")) {
                beneficiaryCategory.setSelectedIndex(0);
            } else if (appTurnOverInFloat > 2000000000 && appTurnOverInFloat <= Double.parseDouble("10000000000")) {
                beneficiaryCategory.setSelectedIndex(1);
            } else if (appTurnOverInFloat > Double.parseDouble("10000000000")) {
                beneficiaryCategory.setSelectedIndex(2);
            }
        }
    }//GEN-LAST:event_beneficiaryTurnOverFocusLost

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearValues();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void limitForApplicantCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitForApplicantCheckActionPerformed
        if (limitForApplicantCheck.isSelected()) {
            if (limitForBeneficiaryCheck.isSelected()) {
                limitForBeneficiaryCheck.setSelected(false);
            }
        }
    }//GEN-LAST:event_limitForApplicantCheckActionPerformed

    private void limitForBeneficiaryCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitForBeneficiaryCheckActionPerformed
        if (limitForBeneficiaryCheck.isSelected()) {
            if (limitForApplicantCheck.isSelected()) {
                limitForApplicantCheck.setSelected(false);
            }
        }
    }//GEN-LAST:event_limitForBeneficiaryCheckActionPerformed

    private void contactsByCheckboxTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactsByCheckboxTableKeyPressed
        // here user on clicking view a pop up where he can select clients privelege to recieve emails.
//        
//            int row = contactsByCheckboxTable.rowAtPoint(evt.get);
//            int col = contactsByCheckboxTable.columnAtPoint(evt.getPoint());
//            if (col == 5) {
//                System.out.println("<<<<<<<<<");
//                JCheckBox[] checkbox = new JCheckBox[]{new JCheckBox("Send Preliminary Offer"), new JCheckBox("Send SLA"), new JCheckBox("Send Document List"), new JCheckBox("Send Credit Advice"), new JCheckBox("Send Bills")};
//
//                if (contactsByCheckboxTable.getValueAt(row, 5) != null && !contactsByCheckboxTable.getValueAt(row, 5).toString().equals("")) {
//                    String getPTText = contactsByCheckboxTable.getValueAt(row, 5).toString();
//                    String[] ptArr = getPTText.split(",");
//                    for (int privCount = 0; privCount < ptArr.length; privCount++) {
//                        for (int checkCount = 0; checkCount < checkbox.length; checkCount++) {
//                            if (checkbox[checkCount].getText().equals(ptArr[privCount])) {
//                                checkbox[checkCount].setSelected(true);
//                            }
//                        }
//                    }
//                }
//
//                String message = "Client is allowed to recieve email for:";
//                Object[] params = {message, checkbox};
//                int status = JOptionPane.showConfirmDialog(this, params, "Client's Email Priveledge", JOptionPane.OK_CANCEL_OPTION);
//                if (status == JOptionPane.CANCEL_OPTION) {
//                } else {
//                    int count = 0;
//                    StringBuffer privFor = new StringBuffer("");
//                    while (count < checkbox.length) {
//                        if (checkbox[count].isSelected()) {
//                            privFor.append(checkbox[count].getText()).append(",");
//                        }
//                        count++;
//                    }
//                    String emailPriv = privFor.toString();
//                    contactsByCheckboxTable.setValueAt(emailPriv, row, 5);
//                }
//            }
    }//GEN-LAST:event_contactsByCheckboxTableKeyPressed

    private void transactionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionTypeActionPerformed
        if (editViewTxnMap != null) {
            if (editViewTxnMap.get("type") != null && !editViewTxnMap.get("type").equals("null")) {
                if (!transactionType.getSelectedItem().toString().equals(editViewTxnMap.get("type"))) {
                    JOptionPane.showMessageDialog(this, "Transaction type can not be changed..", "", 0);
                    transactionType.setSelectedItem(editViewTxnMap.get("type").trim());
                }
            }
        }
    }//GEN-LAST:event_transactionTypeActionPerformed

    private void appExpBusinessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_appExpBusinessFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_appExpBusinessFocusLost

    private void appExpBusinessKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appExpBusinessKeyReleased
        // check expected business

        boolean isFloat = isFloat(appExpBusiness.getText());
        System.out.println("beneficiaryTurnOverPattern.matches(): " + appExpBusiness.getText() + "   " + isFloat);

        // get the applicant values from UI, and append for URL.
        if (isFloat != true) {
            JOptionPane.showMessageDialog(this, "invalid amount of expected business.", "", 0);
            appExpBusiness.setText("");
        }

    }//GEN-LAST:event_appExpBusinessKeyReleased

    private void benExpBusinessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_benExpBusinessFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_benExpBusinessFocusLost

    private void benExpBusinessKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_benExpBusinessKeyReleased

        boolean isFloat = isFloat(benExpBusiness.getText());
        System.out.println("beneficiaryTurnOverPattern.matches(): " + benExpBusiness.getText() + "   " + isFloat);

        // get the applicant values from UI, and append for URL.
        if (isFloat != true) {
            JOptionPane.showMessageDialog(this, "invalid amount of expected business.", "", 0);
            benExpBusiness.setText("");
        }

    }//GEN-LAST:event_benExpBusinessKeyReleased

    private void transactionTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_transactionTypeItemStateChanged
        if (transactionType.getSelectedItem().toString().equals("Enhancement") || transactionType.getSelectedItem().toString().equals("Renewal")) {
            ParserCaller parserCaller = new ParserCaller();

            if (applicantCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                System.out.println("benlist sizeeee: " + benList);
                if (benList.size() > 0) {
                    Iterator it = benList.iterator();
                    benNameCombobox.setSize(benNamePanel.getSize());
                    benNameCombobox.removeAllItems();
                    benNamePanel.removeAll();
                    while (it.hasNext()) {
                        benNameCombobox.addItem(it.next());
                    }
                    benNamePanel.add(benNameCombobox);
                    benNamePanel.validate();
                    benNamePanel.repaint();
                }
            }
            if (beneficiaryCheckbox.isSelected() && appNameTextField.getText().trim() != null && !appNameTextField.getText().trim().equals("")) {
                benList = parserCaller.parseBenListXml(email, appNameTextField.getText().trim());
                System.out.println("benlist sizeeee: " + benList);
                if (benList.size() > 0) {
                    Iterator it = benList.iterator();
                    benNameCombobox.setSize(appNamePanel.getSize());
                    benNameCombobox.removeAllItems();
                    appNamePanel.removeAll();
                    while (it.hasNext()) {
                        benNameCombobox.addItem(it.next());
                    }
                    appNamePanel.add(benNameCombobox);
                    appNamePanel.validate();
                    appNamePanel.repaint();
                }
            }

        }
    }//GEN-LAST:event_transactionTypeItemStateChanged

    private void appComprehensiveSpaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appComprehensiveSpaceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//        System.out.println(evt.getModifiers());
            if (evt.getModifiers() > 0) {
                appComprehensiveSpace.transferFocusBackward();
            } else {
                appComprehensiveSpace.transferFocus();
            }
            evt.consume();
        }
    }//GEN-LAST:event_appComprehensiveSpaceKeyPressed

    private void appSolutionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_appSolutionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
//        System.out.println(evt.getModifiers());
            if (evt.getModifiers() > 0) {
                appSolution.transferFocusBackward();
            } else {
                appSolution.transferFocus();
            }
            evt.consume();
        }
    }//GEN-LAST:event_appSolutionKeyPressed

    private void tradeTextAppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tradeTextAppFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tradeTextAppFocusLost

    private void tradeTextAppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tradeTextAppKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tradeTextAppKeyReleased

    private void indTextAppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indTextAppFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indTextAppFocusLost

    private void indTextAppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indTextAppKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_indTextAppKeyReleased

    private void yearOfIncorpAppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearOfIncorpAppFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_yearOfIncorpAppFocusLost

    private void yearOfIncorpAppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearOfIncorpAppKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_yearOfIncorpAppKeyReleased

    private void extCreditRatingAppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_extCreditRatingAppFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_extCreditRatingAppFocusLost

    private void extCreditRatingAppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_extCreditRatingAppKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_extCreditRatingAppKeyReleased

    private void indTextBenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_indTextBenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_indTextBenFocusLost

    private void indTextBenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indTextBenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_indTextBenKeyReleased

    private void tradeTextBenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tradeTextBenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tradeTextBenFocusLost

    private void tradeTextBenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tradeTextBenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tradeTextBenKeyReleased

    private void extCreditRatingBenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_extCreditRatingBenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_extCreditRatingBenFocusLost

    private void extCreditRatingBenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_extCreditRatingBenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_extCreditRatingBenKeyReleased

    private void yearOfIncorpBenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearOfIncorpBenFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_yearOfIncorpBenFocusLost

    private void yearOfIncorpBenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearOfIncorpBenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_yearOfIncorpBenKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addContactButton;
    private javax.swing.JComboBox appCategory;
    private javax.swing.JTextArea appComprehensiveSpace;
    private javax.swing.JLabel appComprehensiveSpaceLabel;
    private javax.swing.JLabel appComprehensiveSpaceLabel1;
    private javax.swing.JTextField appExpBusiness;
    private javax.swing.JTextField appFax;
    private javax.swing.JPanel appNamePanel;
    private javax.swing.JTextField appPhNo;
    private javax.swing.JComboBox appRegion;
    private javax.swing.JTextArea appSolution;
    private javax.swing.JTextField appTurnOver;
    private javax.swing.JTextArea applicantAddrs;
    private javax.swing.JCheckBox applicantCheckbox;
    private javax.swing.JTextArea benComprehensiveSpace;
    private javax.swing.JLabel benComprehensiveSpaceLabe;
    private javax.swing.JLabel benComprehensiveSpaceLabe1;
    private javax.swing.JTextField benExpBusiness;
    private javax.swing.JPanel benNamePanel;
    private javax.swing.JComboBox benRegion;
    private javax.swing.JTextArea benSolution;
    private javax.swing.JTextArea beneficiaryAddrs;
    private javax.swing.JComboBox beneficiaryCategory;
    private javax.swing.JCheckBox beneficiaryCheckbox;
    private javax.swing.JTextField beneficiaryFax;
    private javax.swing.JTextField beneficiaryPhNo;
    private javax.swing.JTextField beneficiaryTurnOver;
    private javax.swing.JButton cAdditionalDetailsButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton clearEditButton;
    private javax.swing.JPanel clientDetailsPanel;
    private javax.swing.JButton clientDetailsSaveButton;
    private javax.swing.JScrollPane clientdetailsScrollPane;
    private javax.swing.JTable contactsByCheckboxTable;
    private javax.swing.JTextField extCreditRatingApp;
    private javax.swing.JTextField extCreditRatingBen;
    private javax.swing.JTextField indTextApp;
    private javax.swing.JTextField indTextBen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JCheckBox limitForApplicantCheck;
    private javax.swing.JCheckBox limitForBeneficiaryCheck;
    private javax.swing.JTextField tradeTextApp;
    private javax.swing.JTextField tradeTextBen;
    private javax.swing.JComboBox transactionType;
    private javax.swing.JTextField yearOfIncorpApp;
    private javax.swing.JTextField yearOfIncorpBen;
    // End of variables declaration//GEN-END:variables
}
