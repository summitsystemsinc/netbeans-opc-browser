/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.Collection;
import javax.swing.event.ChangeListener;
import org.jinterop.dcom.core.JISession;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openscada.opc.dcom.common.Categories;
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.list.Category;
import org.openscada.opc.lib.list.ServerList;

public class AddOPCServerWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private AddOPCServerVisualPanel1 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public AddOPCServerVisualPanel1 getComponent() {
        if (component == null) {
            component = new AddOPCServerVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        //TODO implement this
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        component.setUsername((String) wiz.getProperty("username"));
        component.setPassword((String) wiz.getProperty("password"));
        component.setHost((String) wiz.getProperty("host"));
        component.setDomain((String) wiz.getProperty("domain"));
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        String username = component.getUsername();
        String password = component.getPassword();
        String host = component.getHost();
        String domain = component.getDomain();

        wiz.putProperty("username", username);
        wiz.putProperty("password", password);
        wiz.putProperty("domain", domain);
        wiz.putProperty("host", host);



        JISession session = JISession.createSession(domain, username, password);
        try {
            ServerList list = new ServerList(session, host);
            Collection<ClassDetails> classDetails = list.listServersWithDetails(new Category[]{new Category(Categories.OPCDAServer10), new Category(Categories.OPCDAServer20), new Category(Categories.OPCDAServer30)}, new Category[]{});
            wiz.putProperty("classDetails", classDetails);
            
        } catch (Exception ex) {
            wiz.setMessage("Error connecting to OPC server: " + ex.getMessage());
            wiz.setMessageType(WizardDescriptor.ERROR_MESSAGE);
        }
    }
}
