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
public class ServersChildren extends Children.Keys<ConnectionInformation> {

    private Collection<ConnectionInformation> connections = new HashSet<ConnectionInformation>();

    @Override
    protected Node[] createNodes(ConnectionInformation t) {
        return new Node[]{new ServerNode(t)};
    }

    public void addConnection(ConnectionInformation server) {
        getConnections().add(server);
        setKeys(getConnections());
    }

    /**
     * @return the connections
     */
    public Collection<ConnectionInformation> getConnections() {
        return connections;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(Collection<ConnectionInformation> connections) {
        this.connections = connections;
        setKeys(connections);
    }
}
