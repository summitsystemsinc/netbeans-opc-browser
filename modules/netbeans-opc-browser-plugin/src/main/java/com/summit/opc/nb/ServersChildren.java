/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.Collection;
import java.util.HashSet;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openscada.opc.lib.common.ConnectionInformation;

/**
 *
 * @author justin
 */
public class ServersChildren extends Children.Keys {

    Collection<ConnectionInformation> serverKeys = new HashSet<ConnectionInformation>();
    
    
    @Override
    protected Node[] createNodes(Object t) {
        return new Node[] {new ServerNode((ConnectionInformation) t)};
    }

    
    public void addConnection(ConnectionInformation server){
        serverKeys.add(server);
        setKeys(serverKeys);
    }
}
