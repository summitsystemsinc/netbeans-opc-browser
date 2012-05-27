/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Image;
import java.awt.image.BufferedImage;
import org.openide.nodes.AbstractNode;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openscada.opc.lib.common.ConnectionInformation;

/**
 *
 * @author justin
 */
public class ServerNode extends AbstractNode {

    ConnectionInformation connInfo;
    public static final Image ICON = ImageUtilities.loadImage("com/summit/opc/nb/singleServerIcon.png").getScaledInstance(24, 24, BufferedImage.SCALE_SMOOTH);

    public ServerNode(ConnectionInformation connInfo) {
        super(new ServerChildren(connInfo),Lookups.singleton(connInfo));

        this.connInfo = connInfo;
    }

    @Override
    public String getHtmlDisplayName() {
        return "<html>" + connInfo.getHost() + " <i>(" + connInfo.getProgId() + ")</i></html>";
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
