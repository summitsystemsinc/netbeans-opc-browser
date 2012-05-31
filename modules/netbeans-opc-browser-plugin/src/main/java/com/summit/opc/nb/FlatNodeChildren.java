/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.SwingWorker;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openscada.opc.lib.da.browser.FlatBrowser;
import org.openscada.opc.lib.da.browser.Leaf;

/**
 *
 * @author justin
 */
public class FlatNodeChildren extends Children.Keys<Leaf> {

    AtomicReference<FlatBrowser> flatBrowser = new AtomicReference<FlatBrowser>();

    public FlatNodeChildren(FlatBrowser flatBrowser) {
        this.flatBrowser.set(flatBrowser);
    }

    @Override
    protected Node[] createNodes(Leaf key) {
        return new Node[]{new LeafNode(key)};
    }

    @Override
    protected void addNotify() {
        SwingWorker<List<Leaf>, Object> worker = new SwingWorker<List<Leaf>, Object>() {

            @Override
            protected List<Leaf> doInBackground() throws Exception {
                Collection<String> keys = flatBrowser.get().browse();
                List<Leaf> leaves = new LinkedList<Leaf>();
                for(String k : keys){
                    Leaf l = new Leaf(null, k, k);
                    leaves.add(l);
                }
                return leaves;
            }

            @Override
            protected void done() {
                try {
                    setKeys(get().toArray(new Leaf[]{}));
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        };
        worker.execute();
    }
}
