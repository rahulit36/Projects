package com.msr.newTx.UI;

import com.msr.tools.UI.*;
import com.msr.tracking.UI.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public abstract class AccordionItem extends JPanel implements Comparable {

    /**
     * Boolean parameter binds state of element. SELECTED or NOT.
     */
    protected boolean selected = false;
    /**
     * <
     * code>IconImage</code> used when item is not selected.
     */
    protected ImageIcon normalIcon;
    /**
     * <
     * code>IconImage</code> used when item is selected.
     */
    protected ImageIcon selectedIcon;
    /**
     * Index value for ordering items in menu tree
     */
    protected int index;
    JLabel icon;
    String accName = "";
    private AccordionBranch branchPanel;
    //String email = "";
    String email = "";
    NewTxPanel tempJpanel = null;
    AccordionRootItem accRootItem = null;
    JTabbedPane tabbedPane = null;
 MainScreen maiScreen= null;
        
    public AccordionItem(String accordionName, NewTxPanel tempAccPanel, String email, JTabbedPane tabbedPane, MainScreen maiScreen) {

        //the line below used for show panel as accordion
        this.accName = accordionName;
        //this.email = emailStr;
        this.tempJpanel = tempAccPanel;
        this.email = email;
        this.tabbedPane = tabbedPane;
        this.maiScreen = maiScreen;

        setOpaque(false);
        this.setLayout(new GridLayout(1, 2));
        addMouseListener(getDefaultMouseActions());
        setNormalIcon(getDefaultNormalIcon());
        setSelectedIcon(getDefaultSelectedIcon());
        setSelected(false);

        this.branchPanel = new AccordionBranch();
    }

    public abstract MouseAdapter getDefaultMouseActions();

    public abstract ImageIcon getDefaultNormalIcon();

    public abstract ImageIcon getDefaultSelectedIcon();

    public abstract Paint getDefaultBackgroundPaint();

    public final void switchState() {
        setSelected(!isSelected());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {

            this.setFont(new Font("verdana", Font.BOLD, 12));
            this.setForeground(new Color(255, 255, 255));
            this.setBackground(new Color(0, 103, 204));

            this.getBranchPanel().removeAll();
            InnerDetailPanel leafItem = new InnerDetailPanel(accName.split(",")[0], email, tempJpanel, tabbedPane, maiScreen);
            this.getBranchPanel().addItem(leafItem);


        } else {
            this.setFont(new Font("verdana", Font.BOLD, 12));
            this.setForeground(new Color(0, 0, 0));
            this.setBackground(new Color(229, 232, 235));
        }
    }

    protected void paintComponent(Graphics g) {
        if (getDefaultBackgroundPaint() != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(getDefaultBackgroundPaint());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paintComponent(g);
    }

    public ImageIcon getNormalIcon() {
        return normalIcon;
    }

    public void setNormalIcon(ImageIcon normalIcon) {
        this.normalIcon = normalIcon;
        setSelected(selected);
    }

    public ImageIcon getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(ImageIcon selectedIcon) {
        this.selectedIcon = selectedIcon;
        setSelected(selected);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int compareTo(Object o) {
        AccordionItem target = (AccordionItem) o;
        if (getIndex() == target.getIndex()) {
            return 0;
        } else if (getIndex() > target.getIndex()) {
            return 1;
        } else {
            return -1;
        }
    }

    public AccordionBranch getBranchPanel() {
        return branchPanel;
    }
}
