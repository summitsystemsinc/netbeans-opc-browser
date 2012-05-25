/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.openide.nodes.AbstractNode;
import org.openide.util.ImageUtilities;
import org.openscada.opc.lib.da.browser.Branch;

/**
 *
 * @author justin
 */
public class BranchNode extends AbstractNode{

    Branch branch;
    
    public static final Image ICON = ImageUtilities.loadImage("com/summit/opc/nb/branch_icon.png").getScaledInstance(24, 24, BufferedImage.SCALE_SMOOTH);
    public static final Image ICON_OPEN = ImageUtilities.loadImage("com/summit/opc/nb/branch_icon_open.png").getScaledInstance(24, 24, BufferedImage.SCALE_SMOOTH);
    
    public BranchNode(Branch b) {
        super(new BranchChildren(b));
        this.branch = b;
    }

    @Override
    public String getHtmlDisplayName() {
        return "<html>" + branch.getName() + "</html>";
    }

    @Override
    public Image getIcon(int type) {
        return ICON;
    }

    @Override
    public Image getOpenedIcon(int type) {
        return ICON_OPEN;
    }
}
