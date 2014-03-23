package com.msr.newTx.UI;

import com.msr.tools.UI.*;
import com.msr.tracking.UI.*;
import com.msr.util.SingletonClass;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class AccordionRootItem extends AccordionItem {

    private JLabel nameLabel = null;
    private JLabel countLabel = null;
    private AccordionBranch branchPanel;
    MainScreen maiScreen = null;
    String userPriv = "";

    public AccordionRootItem(String text, NewTxPanel tempAccPanel, String email, JTabbedPane tabbedPane, MainScreen maiScreen) {
        super(text, tempAccPanel, email, tabbedPane, maiScreen);
        String space = " ";
        nameLabel = new JLabel("     " + text.split(",")[0] );
        nameLabel.setSize(1000, 20);
        nameLabel.setFont(null);
        nameLabel.setForeground(null);
        System.out.println("name label text: "+nameLabel.getText());
        if (nameLabel.getText().trim().equals("Client Details") && SingletonClass.clientCompanyName != null && !SingletonClass.clientCompanyName.equals("")) {
            nameLabel.setText(nameLabel.getText()+"     "+SingletonClass.clientCompanyName);
            System.out.println("&&&&&&&&%%%%%%%%%%$$$$$$$$$$$$#############");
        }
            super.add(nameLabel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        for (int sp = 0; sp < screenSize.width / 12; sp++) {
            space = space + " ";
        }
        countLabel = new JLabel(space + text.split(",")[1]);

        countLabel.setSize(24, 20);
        countLabel.setFont(null);
        countLabel.setForeground(null);
//        JButton fullExpandButton = new JButton("Expand");
//        fullExpandButton.setSize(screenSize.width/50,screenSize.height/100);
//        
        super.add(countLabel);
//        super.add(fullExpandButton);
        this.branchPanel = new AccordionBranch();

    }

    public MouseAdapter getDefaultMouseActions() {
        return new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        };
    }

    public ImageIcon getDefaultNormalIcon() {
        return new ImageIcon(this.getClass().getResource("/images/down-icon.png"));
    }

    public ImageIcon getDefaultSelectedIcon() {
        return new ImageIcon(this.getClass().getResource("/images/down-icon.png"));
    }

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

    public AccordionBranch getBranchPanel() {
        return branchPanel;
    }
}
