package com.msr.tools.UI;

import com.msr.tracking.UI.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Rahul  * @version 1.0  * @since 29 jan 2013
 */
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
    private int menuCount=3;
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
    JPanel toolsPanel = null;
    String designation = "";
    String email = "";

    /**
     *
     */
    public AccordionMenu() {
        initComponents();
        this.setVisible(true);
        this.addComponentListener(getDefaultComponentAdapter());
        this.setLayout(null);

        this.leafMap = new ArrayList<AccordionRootItem>();
    }

    /**
     *
     * @return
     */
    public ComponentAdapter getDefaultComponentAdapter() {
        return new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                calculateAvaiableSpace();
            }
        };
    }

    /**
     *
     */
    public void calculateAvaiableSpace() {
        int height = getHeight()-27;
        double scale = menusSize / 20;
        branchAvaiableSpace = height - (menuCount * menusSize);
        freeAvaiableRows = (int) (Math.ceil(height / (menusSize)) * scale) - menuCount;
        showingSize = branchAvaiableSpace;
        hidingSize = 0;
        update();
    }

    /**
     *
     */
    public void update() {
        for (AccordionRootItem menu : getMenus()) {
            menu.getBranchPanel().updateUI();
        }
    }

    /**
     *
     * @return
     */
    public List<AccordionRootItem> getMenus() {
        //return new ArrayList<AccordionRootItem>(leafMap.keySet());
        return new ArrayList<AccordionRootItem>(leafMap);
    }

    /**
     *
     * @param menuDescriptor
     * @param accPanel
     * @param designation
     * @param email
     */
    public AccordionMenu(String menuDescriptor, MainScreen accPanel, JPanel toolsPanel, String email, String designation) {
        this();
          this.toolsPanel = toolsPanel;
          this.email = email;
          this.designation =designation;
        createMenusFromDescriptor(menuDescriptor, accPanel);
    }

    /**
     *
     * @param menuDescriptor
     * @param tempAccPanel
     */
    public void createMenusFromDescriptor(String menuDescriptor, MainScreen tempAccPanel) {
        this.leafMap = new ArrayList<AccordionRootItem>();
        String[] menus = menuDescriptor.split("!");
        boolean first = true;
        menuCount = 0;
        int i = 0;
        for (String menu : menus) {

            AccordionRootItem menuItem = createRootItem(menu, "MSR", tempAccPanel);
            menuItem.addMouseListener(getDefaultMenuMouseAdapter());
            if (first) {
                menuItem.setSelected(false);
                lastSelectedMenu = menuItem;
                first = false;
            }
            menuItem.setIndex(menuCount);
            menuCount++;
            leafMap.add(menuItem);
            this.add(menuItem.getBranchPanel());
            i++;
        }
        menuCount = leafMap.size();
        calculateAvaiableSpace();
    }

    private AccordionRootItem createRootItem(String title, String name, MainScreen tempAccPanel) {
        AccordionRootItem menu = new AccordionRootItem(title, tempAccPanel, toolsPanel, email,designation);
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

    /**
     *
     * @param g
     */
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

    /**
     *
     * @param name
     * @return
     */
    public AccordionRootItem getMenu(String name) {
        for (AccordionRootItem menu : leafMap) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    /**
     *
     * @param border
     */
    public void setMenuBorders(Border border) {
        for (AccordionRootItem menu : getMenus()) {
            menu.setBorder(border);
        }
    }

    /**
     *
     * @return
     */
    public Color getSelectionColor() {
        return selectionColor;
    }

    /**
     *
     * @return
     */
    public int getMenuCount() {
        return menuCount;
    }

    /**
     *
     * @return
     */
    public int getMenusSize() {
        return menusSize;
    }

    /**
     *
     * @param menusSize
     */
    public void setMenusSize(int menusSize) {
        if (menusSize < MINIMUM_SIZE) {
            setMenusSize(MINIMUM_SIZE);
        }
        this.menusSize = menusSize;
        calculateAvaiableSpace();
        repaint();
    }

    /**
     *
     * @return
     */
    public int getTimeStep() {
        return timeStep;
    }

    /**
     *
     * @param timeStep
     */
    public void setTimeStep(int timeStep) {
        this.timeStep = timeStep;
    }

    /**
     *
     * @return
     */
    public AccordionRootItem getSelectedMenu() {
        return selectedMenu;
    }

    /**
     *
     * @return
     */
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
