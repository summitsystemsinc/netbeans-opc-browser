/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.Collection;
import java.util.LinkedList;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openscada.opc.lib.da.browser.Branch;
import org.openscada.opc.lib.da.browser.Leaf;

/**
 *
 * @author justin
 */
public class BranchChildren extends Children.Keys{

    Branch parent;
    
    public BranchChildren(Branch parent) {
        this.parent = parent;
    }    
    
    @Override
    protected Node[] createNodes(Object key) {
        if(key instanceof Branch){
            Branch b = (Branch) key;
            return new Node[]{new BranchNode(b)};
        }else if(key instanceof Leaf){
            Leaf l = (Leaf) key;
            return new Node[]{new LeafNode(l)};
        }else{
            return new Node[]{};
        }
        
    }

    @Override
    protected void addNotify() {
        Collection newKeys = new LinkedList();
        newKeys.addAll(parent.getBranches());
        newKeys.addAll(parent.getLeaves());
        setKeys(newKeys.toArray());
    }
    
}
