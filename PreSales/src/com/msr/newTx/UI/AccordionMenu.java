package com.msr.newTx.UI;

import com.msr.tracking.UI.MainScreen;
import com.msr.tracking.UI.TrackingTabelPanel;
import com.msr.util.SingletonClass;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class AccordionMenu extends javax.swing.JPanel {

    /**
     * Menu Root Item default minimum size.
     */
    public static int MINIMUM_SIZE = 25;
    /**
     * Menu Root Item height.
     */
    private int menusSize = MINIMUM_SIZE;
    /**
     * Menu item count
     */
    private int menuCount;
    /**
     * This parameters stores the value of free vertical space that can be used
     * to display a menu branch
     */
    private int branchAvaiableSpace;
    /**
     * This parameters counts how many rows is void in a menu branch
     */
    private int freeAvaiableRows;
    /**
     * Animation time pause. Higher value -> Slower animation
     */
    private int timeStep = 5;
    /**
     * Color of item HIGHLIGHT on
     * <code>mouseover</code>. NULL is allowed.
     */
    private Color selectionColor = null;
    /**
     * Handle to selected menu. It is the menu with the opened branch.
     */
    private AccordionRootItem selectedMenu;
    /**
     * Handle to the last selected Menu Item. Used for animation.
     */
    private AccordionRootItem lastSelectedMenu;
    /**
     * Handle to selected Leaf Item.
     */
    //private AccordionLeafItem selectedLeaf;
    /**
     * It is value of vertical size of branch that is opening. It is simply
     * <code>(branchAvaiableSpace - hidingSize)</code>.
     */
    private int showingSize;
    /**
     * It is value of vertical size of branch that is closing. It is simply
     * <code>(branchAvaiableSpace - showingSize)</code>.
     */
    private int hidingSize;
    /**
     * A Map representing the menu tree with handle to each menu items.
     */
    private ArrayList<AccordionRootItem> leafMap;
    String email = "";
    JTabbedPane tabbedPane = null;
    MainScreen maiScreen = null;

    public AccordionMenu() {
        initComponents();
        this.setVisible(true);
        this.addComponentListener(getDefaultComponentAdapter());
        this.setLayout(null);

        this.leafMap = new ArrayList<AccordionRootItem>();
    }

    public ComponentAdapter getDefaultComponentAdapter() {
        return new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                calculateAvaiableSpace();
            }
        };
    }

    public void calculateAvaiableSpace() {
        int height = getHeight();
        double scale = menusSize / 20;
        branchAvaiableSpace = height - (menuCount * menusSize);
        freeAvaiableRows = (int) (Math.ceil(height / (menusSize)) * scale) - menuCount;
        showingSize = branchAvaiableSpace;
        hidingSize = 0;
        update();
    }

    public void update() {
        for (AccordionRootItem menu : getMenus()) {
            menu.getBranchPanel().updateUI();
        }
    }

    public List<AccordionRootItem> getMenus() {
        //return new ArrayList<AccordionRootItem>(leafMap.keySet());
        return new ArrayList<AccordionRootItem>(leafMap);
    }

    public AccordionMenu(String menuDescriptor, NewTxPanel accPanel, String email, JTabbedPane tabbedPane,MainScreen maiScreen) {
        this();
        this.email = email;
        this.tabbedPane = tabbedPane;
        this.maiScreen = maiScreen;
        createMenusFromDescriptor(menuDescriptor, accPanel);
    }

    public void createMenusFromDescriptor(String menuDescriptor, NewTxPanel tempAccPanel) {
        this.leafMap = new ArrayList<AccordionRootItem>();
        String[] menus = menuDescriptor.split("!");
        boolean first = false;
        menuCount = 0;
        int i = 0;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        for (String menu : menus) {

            AccordionRootItem menuItem = createRootItem(menu, "MSR", tempAccPanel);
            menuItem.addMouseListener(getDefaultMenuMouseAdapter());
//            if (first) {
//            System.out.println("SingletonClass.isSave: " + SingletonClass.isSave + " menu.split(\",\")[0]: " + menu.split(",")[0] + " SingletonClass.nextOpenAccordion " + SingletonClass.nextOpenAccordion);
            if (!SingletonClass.isSave && SingletonClass.isFirstAcc && menu.split(",")[0].equalsIgnoreCase("Document List")  && (SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Documents") || SingletonClass.txStatusSelect.equalsIgnoreCase("Create Limit"))) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$$ 1 "+SingletonClass.isSave);
            } else if (!SingletonClass.isSave && SingletonClass.isFirstAcc && menu.split(",")[0].equalsIgnoreCase("PROPOSAL DETAILS") && (SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Internal Approval") || SingletonClass.txStatusSelect.equalsIgnoreCase("Limit Sanctioned") || SingletonClass.txStatusSelect.equalsIgnoreCase("Portfolio Client"))) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$$ 2 "+SingletonClass.isSave);
            } else if (!SingletonClass.isSave && SingletonClass.isFirstAcc && menu.split(",")[0].equalsIgnoreCase("MEETING DETAILS") && (SingletonClass.txStatusSelect.equalsIgnoreCase("Identification Of Client"))) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$ 3 "+SingletonClass.isSave);
            } else if (!SingletonClass.isSave && SingletonClass.isFirstAcc && menu.split(",")[0].equalsIgnoreCase("Document List") && (SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Documents"))) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$ 3 "+SingletonClass.isSave);
            }  else if (!SingletonClass.isSave && SingletonClass.isFirstAcc && menu.split(",")[0].equalsIgnoreCase("Bank Queries") && (SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Limit Setup"))) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$ 3 "+SingletonClass.isSave);
            } else if (!SingletonClass.isSave && SingletonClass.isFirstAcc && menu.split(",")[0].equalsIgnoreCase("Send E-Mail") && (SingletonClass.txStatusSelect.equalsIgnoreCase("Send Offer Letter") || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Client Approval") || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Mandate"))) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$ 4 "+SingletonClass.isSave);
            } else if (SingletonClass.isSave && menu.split(",")[0].equalsIgnoreCase(SingletonClass.nextOpenAccordion)) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$ 5 "+SingletonClass.isSave);
            } else if (menu.split(",")[0].equalsIgnoreCase("CLIENT DETAILS") && !SingletonClass.isFirstAcc && !SingletonClass.isSave) {
                first = true;
//                System.out.println("$$$$$$$$$$$$$$$$ 6 "+SingletonClass.isSave );
            } 
//            else if (SingletonClass.txStatusSelect.equalsIgnoreCase("Create Limit")
//                    || SingletonClass.txStatusSelect.equalsIgnoreCase("Awaiting Limit Setup")
//                    || SingletonClass.txStatusSelect.equalsIgnoreCase("Limit Sanctioned")
//                    || SingletonClass.txStatusSelect.equalsIgnoreCase("Portfolio Client")
//                    || SingletonClass.txStatusSelect.equalsIgnoreCase("Expiry Transition")
//                    || SingletonClass.txStatusSelect.equalsIgnoreCase("Opportunity Lost")) {
////                System.out.println("$$$$$$$$$$$$$$$$ 7 "+SingletonClass.isSave);
//                first = false;
//            } 
            else {
//                System.out.println("$$$$$$$$$$$$$$$$ 7 "+SingletonClass.isSave);
                first = false;
            }
            menuItem.setSelected(first);
            lastSelectedMenu = menuItem;
//            }
            menuItem.setIndex(menuCount);
            menuCount++;
            leafMap.add(menuItem);
            this.add(menuItem.getBranchPanel());
            i++;
        }
        this.setCursor(Cursor.getDefaultCursor());
        menuCount = leafMap.size();
        calculateAvaiableSpace();
    }

    private AccordionRootItem createRootItem(String title, String name, NewTxPanel tempAccPanel) {
        AccordionRootItem menu = new AccordionRootItem(title, tempAccPanel, email, tabbedPane,maiScreen);
        menu.setName(name);
        add(menu);
        return menu;
    }

    private MouseAdapter getDefaultMenuMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AccordionRootItem item = (AccordionRootItem) e.getSource();
                item.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                for (AccordionRootItem menu : getMenus()) {
                    if (menu.isSelected()) {
                        lastSelectedMenu = menu;
                        menu.setSelected(false);
                    }
                }
                item.setSelected(true);
                if (lastSelectedMenu == item) {
                    return;
                }
                item.setCursor(Cursor.getDefaultCursor());
            }
        };
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int currentY = 0;
        for (AccordionRootItem menu : getMenus()) {
            menu.setSize(this.getWidth(), this.menusSize);
            menu.setLocation(0, currentY);

            if (menu == lastSelectedMenu && !menu.isSelected()) {
                currentY += this.menusSize;
                menu.getBranchPanel().setSize(this.getWidth(), this.hidingSize);
                menu.getBranchPanel().setLocation(0, currentY);
                currentY += this.hidingSize;
            }
            if (menu.isSelected()) {
                currentY += this.menusSize;
//                System.out.println("freeAvaiableRows: "+freeAvaiableRows);
                menu.getBranchPanel().adjustItems(freeAvaiableRows);
                menu.getBranchPanel().setSize(this.getWidth(), this.showingSize);
                menu.getBranchPanel().setLocation(0, currentY);
                currentY += this.showingSize;
            } else if (!menu.isSelected() && menu != lastSelectedMenu) {
                menu.getBranchPanel().setSize(0, 0);
                currentY += this.menusSize;
            }
        }
        update();
    }

    public AccordionRootItem getMenu(String name) {
        for (AccordionRootItem menu : leafMap) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public void setMenuBorders(Border border) {
        for (AccordionRootItem menu : getMenus()) {
            menu.setBorder(border);
        }
    }

    public Color getSelectionColor() {
        return selectionColor;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public int getMenusSize() {
        return menusSize;
    }

    public void setMenusSize(int menusSize) {
        if (menusSize < MINIMUM_SIZE) {
            setMenusSize(MINIMUM_SIZE);
        }
        this.menusSize = menusSize;
        calculateAvaiableSpace();
        repaint();
    }

    public int getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(int timeStep) {
        this.timeStep = timeStep;
    }

    public AccordionRootItem getSelectedMenu() {
        return selectedMenu;
    }

    public List<JPanel> getAllLeafs() {
        List<JPanel> leafs = new ArrayList<JPanel>();
        for (AccordionRootItem menu : leafMap) {
            //leafs.addAll(leafMap.get(menu));
        }
        return leafs;
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
