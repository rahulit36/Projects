/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportstab;

import com.msr.reportsdao.QueryDetailBean;
import com.msr.util.SingletonClass;
import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author root
 */
public class QueryReportsPanel extends javax.swing.JPanel {

    /**
     * Creates new form QueryReportsPanel
     */
    List<QueryDetailBean> queryList = null;
    private String email = null;
    public QueryReportsPanel(String email , List<QueryDetailBean> queryList) {
        this.queryList = queryList;
        this.email = email;
        initComponents();
        int counter = 0;
        DefaultTableModel tableModel = (DefaultTableModel) QueryTable.getModel();
        Iterator<QueryDetailBean> itr = queryList.iterator();
        while(itr.hasNext()){
         QueryDetailBean  queryBean = itr.next();
          tableModel.insertRow(counter, new Object[]{});
        QueryTable.setValueAt(counter+1, counter, 0);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getRmName()), counter, 1);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getClientName()), counter, 2);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getBankName()), counter, 3);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getRaiseDate()), counter, 4);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getQueryDetails()), counter, 5);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getQueryStatus()), counter, 6);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getCloseDate()), counter, 7);
        QueryTable.setValueAt(SingletonClass.replaceEmptySpace(queryBean.getComments()), counter, 8);
        counter++;
        }
        
       TableColumn col0 = QueryTable.getColumnModel().getColumn(0);
       col0.setWidth(60);
       col0.setMaxWidth(60);
//       col0.setResizable(false);
        TableColumn col1 = QueryTable.getColumnModel().getColumn(1);
       col1.setMinWidth(200);
       col1.setWidth(200);
       col1.setMaxWidth(200);
//       col1.setResizable(false);
       TableColumn col2 = QueryTable.getColumnModel().getColumn(2);
       col2.setMinWidth(200);
       col2.setWidth(200);
       col2.setMaxWidth(200);
//       col2.setResizable(false);
       TableColumn col3 = QueryTable.getColumnModel().getColumn(3);
       col3.setMinWidth(200);
       col3.setWidth(200);
       col3.setMaxWidth(200);
//       col3.setResizable(false);
        TableColumn col4 = QueryTable.getColumnModel().getColumn(4);
       col4.setMinWidth(200);
       col4.setWidth(200);
       col4.setMaxWidth(200);
//       col4.setResizable(false);
       TableColumn col5 = QueryTable.getColumnModel().getColumn(5);
       col5.setMinWidth(200);
       col5.setWidth(200);
       col5.setMaxWidth(200);
//       col5.setResizable(false);
       TableColumn col6 = QueryTable.getColumnModel().getColumn(6);
       col6.setMinWidth(200);
       col6.setWidth(200);
       col6.setMaxWidth(200);
//       col6.setResizable(false);
       TableColumn col7 = QueryTable.getColumnModel().getColumn(7);
       col7.setMinWidth(200);
       col7.setWidth(200);
       col7.setMaxWidth(200);
//       col7.setResizable(false);
       TableColumn col8 = QueryTable.getColumnModel().getColumn(8);
       col8.setMinWidth(200);
       col8.setWidth(300);
       col8.setMaxWidth(500);
   
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
        QueryTable = new javax.swing.JTable();

        JTableHeader header = QueryTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        //header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        QueryTable.setRowHeight(35);
        QueryTable.setAutoCreateRowSorter(true);
        QueryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No.","RM","Client Name","Bank Name","Raise date","Details","Status","Close Date","Query Resolution/Comments"
            }
        ));
        QueryTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(QueryTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable QueryTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}