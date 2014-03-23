///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.msr.newTx.UI;
//
//import java.util.Calendar;
//import java.util.Date;
//import javax.swing.AbstractCellEditor;
//import javax.swing.JSpinner;
//import javax.swing.SpinnerDateModel;
//import javax.swing.table.TableCellEditor;
//
///**
// *
// * @author root
// */
//class MonthSpinner extends AbstractCellEditor implements TableCellEditor {
//
//    public MonthSpinner() {
//        Date today = new Date();
//        // Start the spinner today, but don't set a min or max date
//        // The increment should be a month
//        JSpinner s = new JSpinner(new SpinnerDateModel(today, null, null,
//                Calendar.DAY_OF_WEEK));
//        JSpinner.DateEditor de = new JSpinner.DateEditor(s, "HH:mm");
////    JSpinner.DateEditor de = new JSpinner.DateEditor(s, "dd/MM/yyyy");
//        s.setEditor(de);
//        System.out.println(" DATE IS VALUE OF TEXTFIELD " + de.getTextField().getText());
//        
//        
//        
//        
//    
//
//    public Component getTableCellEditorComponent(JTable table,
//            Object value, boolean isSelected, int row, int column) {
//        spinner.setValue(value);
//        currentTable = table;
//        selectedRow = row;
//        selectedColumn = column;
//        return spinner;
//    }
//
//    public Object getCellEditorValue() {
//        return spinner.getValue();
//    }
//}
//public static void main(String arg[]) {
//        new MonthSpinner();
//
//    }
//}
