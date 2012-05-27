/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openscada.opc.lib.da.browser.Leaf;

/**
 *
 * @author justin
 */
public class LeafNode extends AbstractNode{

    private Leaf leaf;
    
    public static final Image ICON = ImageUtilities.loadImage ("com/summit/opc/nb/tag_icon.png").getScaledInstance(16, 16, BufferedImage.SCALE_SMOOTH);
    
    public LeafNode(Leaf leaf) {
        super(Children.LEAF,Lookups.singleton(leaf));
        this.leaf = leaf;
    }

    @Override
    public String getHtmlDisplayName() {
        return "<html>" + leaf.getName() + "</html>";
    }

    @Override
    public Image getIcon(int type) {
        return ICON;
    }  
    
}
