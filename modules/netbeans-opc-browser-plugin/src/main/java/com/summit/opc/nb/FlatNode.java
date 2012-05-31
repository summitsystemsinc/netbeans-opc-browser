/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import org.openide.nodes.AbstractNode;
import org.openscada.opc.lib.da.browser.FlatBrowser;

/**
 *
 * @author justin
 */
public class FlatNode extends AbstractNode{
    FlatBrowser flatBrowser;
    public FlatNode(FlatBrowser flatBrowser) {
        super(new FlatNodeChildren(flatBrowser));
        this.flatBrowser = flatBrowser;
    }

    @Override
    public String getHtmlDisplayName() {
        return "<html>Flat</html>";
    }
}
