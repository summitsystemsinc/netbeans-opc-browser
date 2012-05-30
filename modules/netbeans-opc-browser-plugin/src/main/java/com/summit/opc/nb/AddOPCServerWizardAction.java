/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.common.ConnectionInformation;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
@ActionID(category = "Window", id = "com.summit.opc.nb.AddOPCServerWizardAction")
@ActionRegistration(displayName = "Add OPC Server Wizard")
@ActionReference(path = "Menu/OPC", position = 120)
public final class AddOPCServerWizardAction implements ActionListener {

    public String host = "localhost";
    public String domain = "localhost";
    
    @Override
    public void actionPerformed(ActionEvent e) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new AddOPCServerWizardPanel1());
        panels.add(new AddOPCServerWizardPanel2());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
        wiz.putProperty("host", host);
        wiz.putProperty("domain", domain);
        
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle("Add OPC Server");
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            TreeBrowserTopComponent instance = TreeBrowserTopComponent.INSTANCE;
            if (instance != null) {
                ServersChildren serversChildren = instance.getServersChildren();

                ConnectionInformation connInfo = new ConnectionInformation();
                connInfo.setDomain((String) wiz.getProperty("domain"));
                connInfo.setHost((String) wiz.getProperty("host"));
                connInfo.setUser((String) wiz.getProperty("username"));
                connInfo.setPassword((String) wiz.getProperty("password"));
                connInfo.setClsid(((ClassDetails) wiz.getProperty("selectedClassDetail")).getClsId());
                connInfo.setProgId(((ClassDetails) wiz.getProperty("selectedClassDetail")).getProgId());
                
                
                serversChildren.addConnection(connInfo);
            }
        }
    }
}
