/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.summit.opc.nb;

import java.awt.Component;
import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;
import org.openscada.opc.dcom.list.ClassDetails;

public final class AddOPCServerVisualPanel2 extends JPanel {

    private Collection<ClassDetails> classDetails;
    
    /**
     * Creates new form AddOPCServerVisualPanel2
     */
    public AddOPCServerVisualPanel2() {
        initComponents();
        classDetailsComboBox.setRenderer(new ClassDetailsComboBoxRendered());
    }

    @Override
    public String getName() {
        return "Step #2";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        classDetailsComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        serverLabel = new javax.swing.JLabel();
        progIdLabel = new javax.swing.JLabel();
        clsIdLabel = new javax.swing.JLabel();

        classDetailsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        classDetailsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classDetailsComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.jLabel2.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.jLabel3.text")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(serverLabel, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.progIdLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(progIdLabel, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.progIdLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(clsIdLabel, org.openide.util.NbBundle.getMessage(AddOPCServerVisualPanel2.class, "AddOPCServerVisualPanel2.progIdLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classDetailsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(serverLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clsIdLabel)
                                    .addComponent(progIdLabel))))
                        .addGap(0, 337, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classDetailsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(serverLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(progIdLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(clsIdLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void classDetailsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classDetailsComboBoxActionPerformed
        ClassDetails details = (ClassDetails) classDetailsComboBox.getSelectedItem();
        clsIdLabel.setText(details.getClsId());
        progIdLabel.setText(details.getProgId());
        serverLabel.setText(details.getDescription());
    }//GEN-LAST:event_classDetailsComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox classDetailsComboBox;
    private javax.swing.JLabel clsIdLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel progIdLabel;
    private javax.swing.JLabel serverLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the classDetails
     */
    public Collection<ClassDetails> getClassDetails() {
        return classDetails;
    }

    /**
     * @param classDetails the classDetails to set
     */
    public void setClassDetails(Collection<ClassDetails> classDetails) {
        this.classDetails = classDetails;
        this.classDetailsComboBox.setModel(new DefaultComboBoxModel(classDetails.toArray()));
        
        if(!classDetails.isEmpty()){
            classDetailsComboBox.setSelectedItem(0);
            classDetailsComboBoxActionPerformed(null);
        }
    }
    
    public ClassDetails getSelectedClassDetails(){
        return (ClassDetails) classDetailsComboBox.getSelectedItem();
    }
    
    private static class ClassDetailsComboBoxRendered extends DefaultListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            ClassDetails classDetails = (ClassDetails)value;
            return super.getListCellRendererComponent(list, classDetails.getDescription(), index, isSelected, cellHasFocus);
        }
        
    }
}
