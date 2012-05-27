/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.concurrent.Executors;
import javax.swing.SwingWorker;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.TreeBrowser;

/**
 *
 * @author justin
 */
public class ServerChildren extends Children.Keys<Branch> {

    private ConnectionInformation connInfo;
    private TreeBrowser treeBrowser;
    private Server server;

    public ServerChildren(ConnectionInformation connInfo) {
        this.connInfo = connInfo;
    }

    @Override
    protected Node[] createNodes(Branch key) {
        key.setName("Root");
        return new Node[]{new BranchNode(key, server)};
    }

    @Override
    protected void addNotify() {
        SwingWorker<Branch, Branch> sw = new SwingWorker<Branch, Branch>() {

            @Override
            protected Branch doInBackground() throws Exception {
                server = new Server(connInfo, Executors.newScheduledThreadPool(4));
                server.connect();
                treeBrowser = server.getTreeBrowser();
                Branch b = treeBrowser.browse();

                return b;
            }

            @Override
            protected void done() {
                try {
                    setKeys(new Branch[]{get()});
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                } 
            }
        };

        sw.execute();
    }
}
