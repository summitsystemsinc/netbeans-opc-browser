/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.openide.nodes.AbstractNode;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
/**
 *
 * @author justin
 */
public class ServersNode extends AbstractNode{

    public static final Image ICON = ImageUtilities.loadImage("com/summit/opc/nb/serverIcon.png").getScaledInstance(16, 16, BufferedImage.SCALE_SMOOTH);
    
        
    public ServersNode(ServersChildren children) {
        super(children);
    }

    public ServersNode(ServersChildren children, Lookup lookup) {
        super(children, lookup);
    }

    @Override
    public String getName() {
        return "OPC Servers";
    }

    @Override
    public Image getIcon(int type) {
    return ICON;
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }    
}
