/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openscada.opc.lib.common.ConnectionInformation;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.summit.opc.nb//TreeBrowser//EN",
autostore = false)
@TopComponent.Description(preferredID = "TreeBrowserTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.summit.opc.nb.TreeBrowserTopComponent")
@ActionReference(path = "Menu/OPC", position = 100)
@TopComponent.OpenActionRegistration(displayName = "#CTL_TreeBrowserAction",
preferredID = "TreeBrowserTopComponent")
@Messages({
    "CTL_TreeBrowserAction=TreeBrowser",
    "CTL_TreeBrowserTopComponent=TreeBrowser Window",
    "HINT_TreeBrowserTopComponent=OPC Tree Browser"
})
public final class TreeBrowserTopComponent extends TopComponent implements ExplorerManager.Provider {

    public static final String CLS_ID_KEY = "clsId";
    public static final String DOMAIN_KEY = "domain";
    public static final String HOST_KEY = "host";
    
    public static final String PASSWORD_KEY = "password";
    public static final String PROG_ID_KEY = "progId";
    public static final String PROPERTY_VERSION = "1.0";
    public static final String PROPERTY_VERSION_KEY = "version";
    public static final String NUM_CONNECTION_KEY = "numConnections." + PROPERTY_VERSION_KEY;
    public static final String USER_KEY = "user";
    private transient ExplorerManager explorerManager = new ExplorerManager();
    private ServersChildren serversChildren = new ServersChildren();
    public static TreeBrowserTopComponent INSTANCE = null;

    public TreeBrowserTopComponent() {
        initComponents();
        setName(Bundle.CTL_TreeBrowserTopComponent());
        setToolTipText(Bundle.HINT_TreeBrowserTopComponent());

        associateLookup(ExplorerUtils.createLookup(explorerManager, getActionMap()));
        explorerManager.setRootContext(new ServersNode(getServersChildren()));

        ConnectionInformation info = new ConnectionInformation();
        info.setDomain("");

        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new BeanTreeView();

        setToolTipText(org.openide.util.NbBundle.getMessage(TreeBrowserTopComponent.class, "TreeBrowserTopComponent.toolTipText")); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon("/home/justin/Programs/GITClones/github/netbeans-opc-browser/modules/netbeans-opc-browser-plugin/src/main/resources/com/summit/opc/nb/serverIcon.png")); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(TreeBrowserTopComponent.class, "TreeBrowserTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(212, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new AddOPCServerWizardAction().actionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public String getHtmlDisplayName() {
        return "<html>OPC Browser</html>";
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty(PROPERTY_VERSION_KEY, PROPERTY_VERSION);
        // TODO store your settings
        List<ConnectionInformation> connections = new ArrayList<ConnectionInformation>(getServersChildren().getConnections());
        p.setProperty(NUM_CONNECTION_KEY, String.valueOf(connections.size()));
        for (int i = 0; i < connections.size(); i++) {
            p.setProperty(USER_KEY + "." + i + "." + PROPERTY_VERSION, connections.get(i).getUser());
            p.setProperty(HOST_KEY + "." + i + "." + PROPERTY_VERSION, connections.get(i).getHost());
            p.setProperty(CLS_ID_KEY + "." + i + "." + PROPERTY_VERSION, connections.get(i).getClsid());
            p.setProperty(PASSWORD_KEY + "." + i + "." + PROPERTY_VERSION, connections.get(i).getPassword());
            p.setProperty(PROG_ID_KEY + "." + i + "." + PROPERTY_VERSION, connections.get(i).getProgId());
            p.setProperty(DOMAIN_KEY + "." + i + "." + PROPERTY_VERSION, connections.get(i).getDomain());
        }
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
        if (version != null && version.equals(PROPERTY_VERSION)) {
            List<ConnectionInformation> connections = new ArrayList<ConnectionInformation>();
            int count = Integer.valueOf(p.getProperty(NUM_CONNECTION_KEY, "0"));
            for(int i = 0; i < count; i++){
                ConnectionInformation connInfo = new ConnectionInformation();
                connInfo.setClsid(p.getProperty(CLS_ID_KEY+ "." + i + "." + PROPERTY_VERSION,"ERROR"));
                connInfo.setProgId(p.getProperty(PROG_ID_KEY + "." + i + "." + PROPERTY_VERSION,"ERROR"));
                connInfo.setUser(p.getProperty(USER_KEY + "." + i + "." + PROPERTY_VERSION,"ERROR"));
                connInfo.setHost(p.getProperty(HOST_KEY + "." + i + "." + PROPERTY_VERSION,"ERROR"));
                connInfo.setDomain(p.getProperty(DOMAIN_KEY + "." + i + "." + PROPERTY_VERSION,"ERROR"));
                connInfo.setPassword(p.getProperty(PASSWORD_KEY + "." + i + "." + PROPERTY_VERSION,"ERROR"));
                connections.add(connInfo);
            }
            serversChildren.setConnections(connections);
        }
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    /**
     * @return the serversChildren
     */
    public ServersChildren getServersChildren() {
        return serversChildren;
    }
}
