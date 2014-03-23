package com.msr.tools.UI;

import com.msr.tracking.UI.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rahul  * @version 1.0  * @since 29 jan 2013
 */
public class AccordionRootItem extends AccordionItem {

    private JLabel nameLabel = null;
    private JLabel countLabel = null;
    private AccordionBranch branchPanel;
    JPanel toolsPanel = null;

    /**
     *
     * @param text
     * @param tempAccPanel
     * @param designation
     * @param email
     */
    public AccordionRootItem(String text, MainScreen tempAccPanel, JPanel toolsPanel, String email, String designation) {
        super(text, tempAccPanel, toolsPanel, email,designation);
        String space = " ";
        this.toolsPanel = toolsPanel;
        nameLabel = new JLabel("     " + text.split(",")[0]);
        nameLabel.setSize(1000, 20);
        nameLabel.setFont(null);
        nameLabel.setForeground(null);
        super.add(nameLabel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        for (int sp = 0; sp < screenSize.width / 12; sp++) {
            space = space + " ";
        }
        countLabel = new JLabel(space + text.split(",")[1]);

        countLabel.setSize(24, 20);
        countLabel.setFont(null);
        countLabel.setForeground(null);
        super.add(countLabel);
        this.branchPanel = new AccordionBranch();

    }

    /**
     *
     * @return
     */
    public MouseAdapter getDefaultMouseActions() {
        return new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        };
    }

    /**
     *
     * @return
     */
    public ImageIcon getDefaultNormalIcon() {
        return new ImageIcon(this.getClass().getResource("/images/down-icon.png"));
    }

    /**
     *
     * @return
     */
    public ImageIcon getDefaultSelectedIcon() {
        return new ImageIcon(this.getClass().getResource("/images/down-icon.png"));
    }

    /**
     *
     * @return
     */
    public Paint getDefaultBackgroundPaint() {
        Color c1, c2;
        if (isSelected()) {
            c2 = getBackground();
            c1 = c2.darker();
        } else {
            c1 = getBackground();
            c2 = c1.darker();
        }
        return new GradientPaint(0, 0, c1, 0, getHeight(), c2);
    }

    /**
     *
     * @return
     */
    public AccordionBranch getBranchPanel() {
        return branchPanel;
    }
}
