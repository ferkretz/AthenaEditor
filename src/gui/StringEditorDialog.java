/*
 * The Athena project is an open source dictionary database development tool.
 *
 * Copyright (C) 2017 Ferenc Kretz <ferkretz@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gui;

@SuppressWarnings("serial")
public class StringEditorDialog extends javax.swing.JDialog {

    private final util.I18n i18nError = new util.I18n("ErrorMessages");

    public StringEditorDialog(javax.swing.JDialog parent, String title, String stringTitle, String string) {
        super(parent, true);

        initComponents();

        // *** not implemented yet ***
        hintButton.setVisible(false);

        constructStringPanel(stringTitle, string);

        setTitle(title + " - " + util.Settings.AppName.getValue()); // NOI18N

        javax.swing.InputMap inputMap = getRootPane().getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), IconStock.Cancel.toString());
        javax.swing.ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(IconStock.Cancel.toString(), new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doClose(IconStock.Cancel);
            }
        });

        pack();

        setLocationRelativeTo(parent);
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public IconStock getReturnStatus() {
        return returnStatus;
    }

    public String getString() {
        return this.stringField.getText();
    }

    private void constructStringPanel(String title, String string) {
        stringLabel.setText(title);
        stringField.setText(string);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputPanel = new javax.swing.JPanel();
        stringPanel = new javax.swing.JPanel();
        stringLabel = new javax.swing.JLabel();
        stringField = new javax.swing.JTextField();
        sepPanel = new javax.swing.JPanel();
        separator = new javax.swing.JSeparator();
        buttonPanel = new javax.swing.JPanel();
        helpPanel = new javax.swing.JPanel();
        hintButton = new Button(IconStock.Hint);
        controlPanel = new javax.swing.JPanel();
        okButton = new Button(IconStock.Ok);
        cancelButton = new Button(IconStock.Cancel);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("StringEditorDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        inputPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new javax.swing.BoxLayout(inputPanel, javax.swing.BoxLayout.PAGE_AXIS));

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(0);
        flowLayout2.setAlignOnBaseline(true);
        stringPanel.setLayout(flowLayout2);

        stringLabel.setToolTipText("");
        stringLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        stringPanel.add(stringLabel);

        stringField.setToolTipText("");
        stringField.setPreferredSize(new java.awt.Dimension(220, 19));
        stringField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                stringFieldFocusGained(evt);
            }
        });
        stringPanel.add(stringField);

        inputPanel.add(stringPanel);

        getContentPane().add(inputPanel);

        sepPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        sepPanel.setLayout(new javax.swing.BoxLayout(sepPanel, javax.swing.BoxLayout.LINE_AXIS));

        separator.setPreferredSize(null);
        sepPanel.add(separator);

        getContentPane().add(sepPanel);

        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.LINE_AXIS));

        helpPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        helpPanel.setLayout(new java.awt.FlowLayout(0, 5, 0));

        hintButton.setActionCommand("hintButton");
        helpPanel.add(hintButton);

        buttonPanel.add(helpPanel);

        controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setLayout(new java.awt.FlowLayout(2, 5, 0));

        okButton.setActionCommand("okButton");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        controlPanel.add(okButton);

        cancelButton.setActionCommand("cancelButton");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        controlPanel.add(cancelButton);

        buttonPanel.add(controlPanel);

        getContentPane().add(buttonPanel);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-447)/2, (screenSize.height-131)/2, 447, 131);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_closeDialog

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if ("".equals(stringField.getText())) {
            stringField.setBackground(java.awt.Color.yellow);
            new MessageDialog(
                    IconStock.DialogError,
                    IconStock.Ok,
                    i18nError.translate("FieldRequired"), //NOI18N
                    this).setVisible(true);
        } else {
            doClose(IconStock.Ok);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void stringFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stringFieldFocusGained
        if (stringField.getBackground() != java.awt.Color.white) {
            stringField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_stringFieldFocusGained

    private void doClose(IconStock retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JButton hintButton;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JTextField stringField;
    private javax.swing.JLabel stringLabel;
    private javax.swing.JPanel stringPanel;
    // End of variables declaration//GEN-END:variables
    private IconStock returnStatus;
}
