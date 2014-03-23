/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.reportstab;

import com.msr.reportsdao.MeetingDetailBean;
import com.msr.reportsdao.RMTransactionBean;
import com.msr.util.SingletonClass;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author root
 */
public class RmTransactionPanel extends javax.swing.JPanel {

    /**
     * Creates new form RmTransactionPanel
     */
    List<RMTransactionBean> rmTxList = null;
    
    public RmTransactionPanel(String email , List<RMTransactionBean> rmTxList) {
       this.rmTxList= rmTxList;
        System.out.println("meeting list size is"+rmTxList.size());
        initComponents();
        int counter = 0;
        DefaultTableModel tableModel = (DefaultTableModel) RmTxTable.getModel();
        Iterator<RMTransactionBean> itr = rmTxList.iterator();
        while(itr.hasNext()){
         RMTransactionBean  rmTxBean = itr.next();
          tableModel.insertRow(counter, new Object[]{});
        RmTxTable.setValueAt(counter+1, counter, 0);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getTxnStatus()), counter, 1);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getTxnType()), counter, 2);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getRmName()), counter, 3);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getClientName()), counter, 4);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getBankName()), counter, 5);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getLimitType()), counter, 6);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getProposedLimit()), counter, 7);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getActualLimit()), counter, 8);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getUsance()), counter, 9);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getInterestRate()), counter, 10);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getMargin()), counter, 11);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getOurCharge()), counter, 12);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getOurChargetype()), counter, 13);
        RmTxTable.setValueAt(SingletonClass.replaceEmptySpace(rmTxBean.getHandlingCharges()), counter, 14);
        counter++;
        
//        "Proposed Limit", "usance","interestRate","margin"," handlingCharges"
        }
       TableColumn col0 = RmTxTable.getColumnModel().getColumn(0);
       col0.setWidth(60);
       col0.setMaxWidth(60);
//       col0.setResizable(false);
        TableColumn col1 = RmTxTable.getColumnModel().getColumn(1);
       col1.setMinWidth(200);
       col1.setWidth(200);
       col1.setMaxWidth(200);
//       col1.setResizable(false);
       TableColumn col2 = RmTxTable.getColumnModel().getColumn(2);
       col2.setMinWidth(200);
       col2.setWidth(200);
       col2.setMaxWidth(200);
//       col2.setResizable(false);
       TableColumn col3 = RmTxTable.getColumnModel().getColumn(3);
       col3.setMinWidth(200);
       col3.setWidth(200);
       col3.setMaxWidth(200);
//       col3.setResizable(false);
        TableColumn col4 = RmTxTable.getColumnModel().getColumn(4);
       col4.setMinWidth(200);
       col4.setWidth(200);
       col4.setMaxWidth(200);
//       col4.setResizable(false);
       TableColumn col5 = RmTxTable.getColumnModel().getColumn(5);
       col5.setMinWidth(200);
       col5.setWidth(200);
       col5.setMaxWidth(200);
//       col5.setResizable(false);
       TableColumn col6 = RmTxTable.getColumnModel().getColumn(6);
       col6.setMinWidth(200);
       col6.setWidth(200);
       col6.setMaxWidth(200);
//       col6.setResizable(false);
       TableColumn col7 = RmTxTable.getColumnModel().getColumn(7);
       col7.setMinWidth(200);
       col7.setWidth(200);
       col7.setMaxWidth(200);
//       col7.setResizable(false);
       TableColumn col8 = RmTxTable.getColumnModel().getColumn(8);
       col8.setMinWidth(200);
       col8.setWidth(200);
       col8.setMaxWidth(200);
//       col8.setResizable(false);
       TableColumn col9 = RmTxTable.getColumnModel().getColumn(9);
       col9.setMinWidth(200);
       col9.setWidth(200);
       col9.setMaxWidth(200);
//       col9.setResizable(false);
       TableColumn col10 = RmTxTable.getColumnModel().getColumn(10);
       col10.setMinWidth(200);
       col10.setWidth(200);
       col10.setMaxWidth(200);
//       col10.setResizable(false);
       TableColumn col11 = RmTxTable.getColumnModel().getColumn(11);
       col11.setMinWidth(200);
       col11.setWidth(200);
       col11.setMaxWidth(200);
//       col11.setResizable(false);
       TableColumn col12 = RmTxTable.getColumnModel().getColumn(12);
       col12.setMinWidth(200);
       col12.setWidth(200);
       col12.setMaxWidth(200);
//       col12.setResizable(false);
       TableColumn col13 = RmTxTable.getColumnModel().getColumn(13);
       col13.setMinWidth(200);
       col13.setWidth(200);
       col13.setMaxWidth(200);
       TableColumn col14 = RmTxTable.getColumnModel().getColumn(14);
       col14.setMinWidth(200);
       col14.setWidth(200);
       col14.setMaxWidth(200);
//       col13.setResizable(false);
       RmTxTable.setPreferredScrollableViewportSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-65,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 330));
//       RmTxTable.repaint();
       
//        ,"Nature of Transaction", "RM", "Client Name","Bank Name","ILC/FLC", "Proposed Limit", "Sanction Limit","usance","interestRate","margin"," handlingCharges"
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RmTxTable = new javax.swing.JTable();

        JTableHeader header = RmTxTable.getTableHeader();
        header.setBackground(new Color(192, 191, 200));
        //header.setPreferredSize(new Dimension(600,35));
        Font font = new Font("Serif", Font.BOLD, 14);
        header.setForeground(new Color(0, 103, 204));

        header.setFont(font);
        RmTxTable.setRowHeight(35);
        RmTxTable.setAutoCreateRowSorter(true);
        RmTxTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No.","Status","Nature of Transaction", "RM", "Client Name", "Bank Name","ILC/FLC", "Proposed Limit","Sanction Limit", "usance","interestRate","margin","Our Charges","Our Charges Type","handlingCharges"
            }
        ));
        RmTxTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(RmTxTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable RmTxTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
