package com.msr.newTx.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.*;

public class MyHeaderRenderer
        extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setText(value.toString());
        area.setBackground(new Color(192, 191, 200));
        area.setPreferredSize(new Dimension(600, 35));

        Border border = BorderFactory.createLineBorder(Color.WHITE);
        area.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));


        Font font = new Font("Serif", Font.BOLD, 14);
        area.setForeground(new Color(0, 103, 204));

        area.setFont(font);
        return area;
    }
}