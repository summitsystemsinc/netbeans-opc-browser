/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.Leaf;

/**
 *
 * @author justin
 */
public class BranchNode extends AbstractNode {

    Branch branch;
    public static final Image ICON = ImageUtilities.loadImage("com/summit/opc/nb/branch_icon.png").getScaledInstance(16, 16, BufferedImage.SCALE_SMOOTH);
    public static final Image ICON_OPEN = ImageUtilities.loadImage("com/summit/opc/nb/branch_icon_open.png").getScaledInstance(16, 16, BufferedImage.SCALE_SMOOTH);
    Group readGroup;
    Server opcServer;

    public BranchNode(Branch b, Server connection) {
        super(new BranchChildren(b, connection), Lookups.singleton(b));
        this.branch = b;
        opcServer = connection;
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

    @Override
    protected Sheet createSheet() {
        try {
            if (readGroup != null) {
                readGroup.remove();
            }
            Group g = opcServer.addGroup();
            readGroup = g;

            Sheet sheet = Sheet.createDefault();
            final Sheet.Set set = Sheet.createPropertiesSet();
            Branch selectedBranch = branch;

            Collection<Leaf> leaves = selectedBranch.getLeaves();
            set.setDisplayName("Node Leaves");

            for (Leaf l : leaves) {
                Property leafProperty = OPCLeafProperty.opcLeafPropertyFactory(l, opcServer, readGroup);
                //leafProperty.
                set.put(leafProperty);

            }
            sheet.put(set);
            return sheet;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            return Sheet.createDefault();
        }
    }
}
