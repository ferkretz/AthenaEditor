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
public class SettingsDialog extends javax.swing.JDialog {

    private final util.I18n i18n = new util.I18n(this);
    private final util.I18n i18nError = new util.I18n("ErrorMessages");

    public SettingsDialog(javax.swing.JFrame parent) {
        super(parent, true);

        initComponents();

        constructPanels();

        // *** not implemented yet ***
        hintButton.setVisible(false);

        String title = i18n.translate("Settings");
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

    private void constructPanels() {
        fileToolBarCheckBox.setSelected(util.Settings.TBShowFile.getBooleanValue());
        editToolBarCheckBox.setSelected(util.Settings.TBShowEdit.getBooleanValue());
        helpToolBarCheckBox.setSelected(util.Settings.TBShowHelp.getBooleanValue());

        numUsersSpinner.setValue(util.Settings.ViewUserLimit.getIntValue());
        numWordsSpinner.setValue(util.Settings.ViewRecordLimit.getIntValue());
        usernameTextField.setText(util.Settings.MemberName.getStringValue());
    }

    private void updateSettings() {
        util.Settings.TBShowFile.setBooleanValue(fileToolBarCheckBox.isSelected());
        util.Settings.TBShowEdit.setBooleanValue(editToolBarCheckBox.isSelected());
        util.Settings.TBShowHelp.setBooleanValue(helpToolBarCheckBox.isSelected());

        util.Settings.ViewUserLimit.setIntValue((int) numUsersSpinner.getValue());
        util.Settings.ViewRecordLimit.setIntValue((int) numWordsSpinner.getValue());
        util.Settings.MemberName.setStringValue(usernameTextField.getText());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        viewPanel = new javax.swing.JPanel();
        fileToolBarPanel = new javax.swing.JPanel();
        fileToolBarCheckBox = new javax.swing.JCheckBox();
        editToolBarPanel = new javax.swing.JPanel();
        editToolBarCheckBox = new javax.swing.JCheckBox();
        helpToolBarPanel = new javax.swing.JPanel();
        helpToolBarCheckBox = new javax.swing.JCheckBox();
        databasePanel = new javax.swing.JPanel();
        numUsersPanel = new javax.swing.JPanel();
        numUsersLabel = new javax.swing.JLabel();
        numUsersSpinner = new javax.swing.JSpinner();
        numWordsPanel = new javax.swing.JPanel();
        numWordsLabel = new javax.swing.JLabel();
        numWordsSpinner = new javax.swing.JSpinner();
        usernamePanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        wizardPanel = new javax.swing.JPanel();
        runWizardPanel = new javax.swing.JPanel();
        recoveryButton = new Button(IconStock.Recovery);
        uninstallButton = new Button(IconStock.Uninstall);
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
        setName("SettingsDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        contentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.PAGE_AXIS));

        viewPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, i18n.translate("viewPanel"), 0, 0, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        viewPanel.setLayout(new javax.swing.BoxLayout(viewPanel, javax.swing.BoxLayout.PAGE_AXIS));

        fileToolBarPanel.setLayout(new java.awt.FlowLayout(0));

        fileToolBarCheckBox.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        fileToolBarCheckBox.setText(i18n.translate("fileToolBarCheckBox")); // NOI18N
        fileToolBarPanel.add(fileToolBarCheckBox);

        viewPanel.add(fileToolBarPanel);

        editToolBarPanel.setLayout(new java.awt.FlowLayout(0));

        editToolBarCheckBox.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        editToolBarCheckBox.setText(i18n.translate("editToolBarCheckBox")); // NOI18N
        editToolBarPanel.add(editToolBarCheckBox);

        viewPanel.add(editToolBarPanel);

        helpToolBarPanel.setLayout(new java.awt.FlowLayout(0));

        helpToolBarCheckBox.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        helpToolBarCheckBox.setText(i18n.translate("helpToolBarCheckBox")); // NOI18N
        helpToolBarPanel.add(helpToolBarCheckBox);

        viewPanel.add(helpToolBarPanel);

        contentPanel.add(viewPanel);

        databasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, i18n.translate("databasePanel"), 0, 0, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        databasePanel.setLayout(new javax.swing.BoxLayout(databasePanel, javax.swing.BoxLayout.PAGE_AXIS));

        numUsersPanel.setLayout(new java.awt.FlowLayout(0));

        numUsersLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        numUsersLabel.setText(i18n.translate("numUsersLabel")); // NOI18N
        numUsersPanel.add(numUsersLabel);

        numUsersSpinner.setModel(new javax.swing.SpinnerNumberModel(20, 5, 50, 5));
        numUsersSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(numUsersSpinner, ""));
        numUsersSpinner.setPreferredSize(new java.awt.Dimension(40, 20));
        numUsersPanel.add(numUsersSpinner);

        databasePanel.add(numUsersPanel);

        numWordsPanel.setLayout(new java.awt.FlowLayout(0));

        numWordsLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        numWordsLabel.setText(i18n.translate("numWordsLabel")); // NOI18N
        numWordsPanel.add(numWordsLabel);

        numWordsSpinner.setModel(new javax.swing.SpinnerNumberModel(20, 5, 50, 5));
        numWordsSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(numWordsSpinner, ""));
        numWordsSpinner.setPreferredSize(new java.awt.Dimension(40, 20));
        numWordsPanel.add(numWordsSpinner);

        databasePanel.add(numWordsPanel);

        usernamePanel.setLayout(new java.awt.FlowLayout(0));

        usernameLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        usernameLabel.setText(i18n.translate("usernameLabel")); // NOI18N
        usernamePanel.add(usernameLabel);

        usernameTextField.setPreferredSize(new java.awt.Dimension(160, 19));
        usernameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameTextFieldFocusGained(evt);
            }
        });
        usernamePanel.add(usernameTextField);

        databasePanel.add(usernamePanel);

        contentPanel.add(databasePanel);

        wizardPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, i18n.translate("wizardPanel"), 0, 0, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        wizardPanel.setLayout(new javax.swing.BoxLayout(wizardPanel, javax.swing.BoxLayout.PAGE_AXIS));

        runWizardPanel.setLayout(new java.awt.FlowLayout(0));

        recoveryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recoveryButtonActionPerformed(evt);
            }
        });
        runWizardPanel.add(recoveryButton);

        uninstallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uninstallButtonActionPerformed(evt);
            }
        });
        runWizardPanel.add(uninstallButton);

        wizardPanel.add(runWizardPanel);

        contentPanel.add(wizardPanel);

        getContentPane().add(contentPanel);

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
        setBounds((screenSize.width-504)/2, (screenSize.height-396)/2, 504, 396);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_closeDialog

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if ("".equals(usernameTextField.getText())) {
            usernameTextField.setBackground(java.awt.Color.yellow);
            new MessageDialog(
                    IconStock.DialogError,
                    IconStock.Ok,
                    i18nError.translate("FieldRequired"), //NOI18N
                    this).setVisible(true);
        } else {
            updateSettings();
            doClose(IconStock.Ok);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void recoveryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recoveryButtonActionPerformed
        doClose(IconStock.Recovery);
    }//GEN-LAST:event_recoveryButtonActionPerformed

    private void uninstallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uninstallButtonActionPerformed
        doClose(IconStock.Uninstall);
    }//GEN-LAST:event_uninstallButtonActionPerformed

    private void usernameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFieldFocusGained
        if (usernameTextField.getBackground() != java.awt.Color.white) {
            usernameTextField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_usernameTextFieldFocusGained

    private void doClose(IconStock retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel databasePanel;
    private javax.swing.JCheckBox editToolBarCheckBox;
    private javax.swing.JPanel editToolBarPanel;
    private javax.swing.JCheckBox fileToolBarCheckBox;
    private javax.swing.JPanel fileToolBarPanel;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JCheckBox helpToolBarCheckBox;
    private javax.swing.JPanel helpToolBarPanel;
    private javax.swing.JButton hintButton;
    private javax.swing.JLabel numUsersLabel;
    private javax.swing.JPanel numUsersPanel;
    private javax.swing.JSpinner numUsersSpinner;
    private javax.swing.JLabel numWordsLabel;
    private javax.swing.JPanel numWordsPanel;
    private javax.swing.JSpinner numWordsSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JButton recoveryButton;
    private javax.swing.JPanel runWizardPanel;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton uninstallButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JPanel usernamePanel;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JPanel viewPanel;
    private javax.swing.JPanel wizardPanel;
    // End of variables declaration//GEN-END:variables
    private IconStock returnStatus;
}
