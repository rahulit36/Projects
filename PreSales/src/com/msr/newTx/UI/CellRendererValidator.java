/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

import javax.swing.JTable;

/**
 * CellRendererValidator - This is an interface that will be implemented by any
 * class that wishes <br>to specify whether a CellRenderer is valid for a
 * specific column and row. <br> This is pretty useful when specific rows of a
 * column will <i>NOT </i> the renderer set for the entire column
 *
 * @author rahul
 */
public interface CellRendererValidator {

    public  boolean isValid(JTable table, int rowIndex, int colIndex);
}