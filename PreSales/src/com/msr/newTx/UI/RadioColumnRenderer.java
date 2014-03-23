/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msr.newTx.UI;

/**
 *
 * @author root
 */
import java.awt.Color;
import java.awt.Component;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class RadioColumnRenderer extends JRadioButton
        implements TableCellRenderer {

    private CellRendererValidator validator = null;

    /**
     *
     */
    public RadioColumnRenderer(CellRendererValidator validator) {
        this.validator = validator;
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        // this is necessary because we may have 
        // values that are not boolean for
        // som of the columns
        this.setSelected(value != null
                && value.getClass().getName().equals(
                Boolean.class.getName())
                && ((Boolean) value).booleanValue());

        this.setHorizontalAlignment(
                SwingConstants.CENTER);
        this.setOpaque(false);
        this.setBackground(Color.WHITE);
        // request the validator to concur that this 
        // column can be rendered as a JRadioButton
        if (validator != null) {
            if (validator.isValid(table, row, column)) {
                return this;
            }
            return null;
        }
        return this;
    }
}
