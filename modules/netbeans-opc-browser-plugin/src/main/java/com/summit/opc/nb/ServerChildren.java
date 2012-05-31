/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.SwingWorker;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.browser.BaseBrowser;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.FlatBrowser;
import org.openscada.opc.lib.da.browser.TreeBrowser;

/**
 *
 * @author justin
 */
public class ServerChildren extends Children.Keys<BaseBrowser> {

    private ConnectionInformation connInfo;
    private AtomicReference<TreeBrowser> treeBrowser = new AtomicReference<TreeBrowser>();
    private AtomicReference<FlatBrowser> flatBrowser = new AtomicReference<FlatBrowser>();
    private AtomicReference<Server> server = new AtomicReference<Server>();

    public ServerChildren(ConnectionInformation connInfo) {
        this.connInfo = connInfo;
    }

    @Override
    protected Node[] createNodes(BaseBrowser key) {
        AbstractNode errorNode = new AbstractNode(LEAF);
        if (key instanceof TreeBrowser) {
            try {
                Branch b = ((TreeBrowser) key).browse();
                b.setName("Tree");
                return new Node[]{new BranchNode(b, server.get())};
            } catch (Exception ex) {
                errorNode.setDisplayName("Error getting tree browser.");
                errorNode.setShortDescription(ex.getLocalizedMessage());
                return new Node[]{errorNode};
            }
        } else if (key instanceof FlatBrowser) {
            return new Node[]{new FlatNode((FlatBrowser)key)};
        } else {
            errorNode.setDisplayName("Unknown OPC Browser");
            errorNode.setShortDescription("Unknown OPC Browser: " + key.getClass().getName());
            return new Node[]{errorNode};
        }
    }

    @Override
    protected void addNotify() {
        SwingWorker<BaseBrowser[], Branch> sw = new SwingWorker<BaseBrowser[], Branch>() {

            @Override
            protected BaseBrowser[] doInBackground() throws Exception {
                server.set(new Server(connInfo, Executors.newScheduledThreadPool(4)));
                server.get().connect();
                treeBrowser.set(server.get().getTreeBrowser());
                flatBrowser.set(server.get().getFlatBrowser());
                return new BaseBrowser[]{treeBrowser.get(), flatBrowser.get()};
            }

            @Override
            protected void done() {
                try {
                    setKeys(get());
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        };

        sw.execute();
    }
   
    
}
