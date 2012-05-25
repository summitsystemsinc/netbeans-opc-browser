/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.util.Collection;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openscada.opc.dcom.list.ClassDetails;

public class AddOPCServerWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor> {

    String username;
    String password;
    String host; 
    String domain;
    Collection<ClassDetails> classDetails;
    
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private AddOPCServerVisualPanel2 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public AddOPCServerVisualPanel2 getComponent() {
        if (component == null) {
            component = new AddOPCServerVisualPanel2();
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
        username = (String) wiz.getProperty("username");
        password = (String) wiz.getProperty("password");
        host = (String) wiz.getProperty("host");
        domain = (String) wiz.getProperty("domain");
        classDetails = (Collection<ClassDetails>) wiz.getProperty("classDetails");
        
        component.setClassDetails(classDetails);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty("selectedClassDetail", component.getSelectedClassDetails());
    }
}
