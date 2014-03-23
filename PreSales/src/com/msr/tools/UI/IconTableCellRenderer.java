package com.msr.tools.UI;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class IconTableCellRenderer extends DefaultTableCellRenderer {

    JLabel lbl = new JLabel();
    ImageIcon icon = null;

    public IconTableCellRenderer(String iconName) {
        if (iconName.equals("Edit")) {
            icon = new ImageIcon(getClass().getResource("/images/edit_icon.png"));
        }
        if (iconName.equals("View")) {
            icon = new ImageIcon(getClass().getResource("/images/view_icon.png"));
        }
        if (iconName.equals("Delete")) {
            icon = new ImageIcon(getClass().getResource("/images/delete.png"));
        }
        if (iconName.equals("Copy")) {
            icon = new ImageIcon(getClass().getResource("/images/copy.png"));
        }
        if (iconName.equals("upload")) {
            icon = new ImageIcon(getClass().getResource("/images/upload_icon.png"));
        }
        if (iconName.equals("Meeting")) {
            icon = new ImageIcon(getClass().getResource("/images/upload_icon.png"));
        }
        if (iconName.equals("Alarm")) {
            icon = new ImageIcon(getClass().getResource("/images/alram.png"));
        }
        if (iconName.equals("AlarmSet")) {
            icon = new ImageIcon(getClass().getResource("/images/alarm_set.png"));
        }
        if (iconName.equals("location")) {
            icon = new ImageIcon(getClass().getResource("/images/select_location.png"));
        }
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        lbl.setIcon(icon);
        return lbl;
    }
}