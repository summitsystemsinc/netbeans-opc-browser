/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Collection;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.Leaf;

/**
 *
 * @author justin
 */
public class ServerNode extends AbstractNode {

    ConnectionInformation connInfo;
    public static final Image ICON = ImageUtilities.loadImage("com/summit/opc/nb/singleServerIcon.png").getScaledInstance(24, 24, BufferedImage.SCALE_SMOOTH);

    public ServerNode(ConnectionInformation connInfo) {
        super(new ServerChildren(connInfo), Lookups.singleton(connInfo));

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
    
    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        final Sheet.Set set = Sheet.createPropertiesSet();
        try {
            set.put(new PropertySupport.Reflection<String>(connInfo, String.class, "getDomain", null));
            set.put(new PropertySupport.Reflection<String>(connInfo, String.class, "getHost", null));
            set.put(new PropertySupport.Reflection<String>(connInfo, String.class, "getUser", null));
            set.put(new PropertySupport.Reflection<String>(connInfo, String.class, "getClsid", null));
            set.put(new PropertySupport.Reflection<String>(connInfo, String.class, "getProgid", null));
        } catch (NoSuchMethodException ex) {
            Exceptions.printStackTrace(ex);
        }
        sheet.put(set);
        return sheet;
    }
}
