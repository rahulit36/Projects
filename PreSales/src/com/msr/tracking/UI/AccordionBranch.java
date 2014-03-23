package com.msr.tracking.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rahul
 * @version 1.0
 * @since 29 jan 2013
 */
public class AccordionBranch extends javax.swing.JPanel {

    /**
     * Number of items in branch.
     */
    private int count = 0;
    /**
     * Fake void elements used to fill blank spaces.
     */
    private List<JLabel> fooItems;

    /**
     *
     */
    public AccordionBranch() {
        initComponents();
        this.fooItems = new ArrayList<JLabel>();
    }

    /**
     *
     * @param item
     */
    public void addItem(AccordionItem item) {
        this.add(item);
        count++;
    }

    /**
     *
     * @param item
     */
    public void addItem(JPanel item) {
        this.add(item);
        count++;
    }

    /**
     *
     * @param max
     */
    public void adjustItems(int max) {
        for (JLabel l : this.fooItems) {
            this.remove(l);
        }
        this.fooItems.clear();
        for (int i = max; i > getCount(); i--) {
            JLabel lab = new JLabel("");
            lab.setBackground(Color.green);
            this.add(lab);
            this.fooItems.add(lab);
        }
    }

    /**
     *
     * @return
     */
    public int getCount() {
        return count;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
