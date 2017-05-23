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
public class TranslationEditorDialog extends javax.swing.JDialog {

    private final util.I18n i18n = new util.I18n(this);
    private final util.I18n i18nError = new util.I18n("ErrorMessages");

    public TranslationEditorDialog(javax.swing.JDialog parent, String title, util.WordTranslation translation) {
        super(parent, true);

        initComponents();

        // *** not implemented yet ***
        hintButton.setVisible(false);

        constructStringPanels(translation);

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

    public util.WordTranslation getTranslation() {
        return new util.WordTranslation(
                this.transWordField.getText(),
                this.examplesTextArea.getText(),
                this.transExamplesTextArea.getText());
    }

    private void constructStringPanels(util.WordTranslation translation) {
        this.transWordField.setText(translation.getTransWord());
        this.examplesTextArea.setText(translation.getExamples());
        this.transExamplesTextArea.setText(translation.getTransExamples());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputPanel = new javax.swing.JPanel();
        transWordPanel = new javax.swing.JPanel();
        transWordLabel = new javax.swing.JLabel();
        transWordField = new javax.swing.JTextField();
        examplesPanel = new javax.swing.JPanel();
        examplesLabel = new javax.swing.JLabel();
        examplesScrollPanel = new javax.swing.JScrollPane();
        examplesTextArea = new javax.swing.JTextArea();
        transExamplesPanel = new javax.swing.JPanel();
        transExamplesLabel = new javax.swing.JLabel();
        transExamplesScrollPanel = new javax.swing.JScrollPane();
        transExamplesTextArea = new javax.swing.JTextArea();
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
        setName("TranslationEditorDialog"); // NOI18N
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
        transWordPanel.setLayout(flowLayout2);

        transWordLabel.setText(i18n.translate("Trans. word:")); // NOI18N
        transWordLabel.setToolTipText("");
        transWordLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        transWordPanel.add(transWordLabel);

        transWordField.setPreferredSize(new java.awt.Dimension(280, 19));
        transWordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                transWordFieldFocusGained(evt);
            }
        });
        transWordPanel.add(transWordField);

        inputPanel.add(transWordPanel);

        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(0);
        flowLayout3.setAlignOnBaseline(true);
        examplesPanel.setLayout(flowLayout3);

        examplesLabel.setText(i18n.translate("Examples:")); // NOI18N
        examplesLabel.setToolTipText("");
        examplesLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        examplesPanel.add(examplesLabel);

        examplesScrollPanel.setPreferredSize(new java.awt.Dimension(280, 100));

        examplesTextArea.setColumns(20);
        examplesTextArea.setLineWrap(true);
        examplesTextArea.setRows(5);
        examplesTextArea.setPreferredSize(null);
        examplesScrollPanel.setViewportView(examplesTextArea);

        examplesPanel.add(examplesScrollPanel);

        inputPanel.add(examplesPanel);

        java.awt.FlowLayout flowLayout5 = new java.awt.FlowLayout(0);
        flowLayout5.setAlignOnBaseline(true);
        transExamplesPanel.setLayout(flowLayout5);

        transExamplesLabel.setText(i18n.translate("Trans. examples:")); // NOI18N
        transExamplesLabel.setToolTipText("");
        transExamplesLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        transExamplesPanel.add(transExamplesLabel);

        transExamplesScrollPanel.setPreferredSize(new java.awt.Dimension(280, 100));

        transExamplesTextArea.setColumns(20);
        transExamplesTextArea.setLineWrap(true);
        transExamplesTextArea.setRows(5);
        transExamplesTextArea.setPreferredSize(null);
        transExamplesScrollPanel.setViewportView(transExamplesTextArea);

        transExamplesPanel.add(transExamplesScrollPanel);

        inputPanel.add(transExamplesPanel);

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
        setBounds((screenSize.width-504)/2, (screenSize.height-351)/2, 504, 351);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_closeDialog

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if ("".equals(transWordField.getText())) {
            transWordField.setBackground(java.awt.Color.yellow);
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

    private void transWordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_transWordFieldFocusGained
        if (transWordField.getBackground() != java.awt.Color.white) {
            transWordField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_transWordFieldFocusGained

    private void doClose(IconStock retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel examplesLabel;
    private javax.swing.JPanel examplesPanel;
    private javax.swing.JScrollPane examplesScrollPanel;
    private javax.swing.JTextArea examplesTextArea;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JButton hintButton;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel transExamplesLabel;
    private javax.swing.JPanel transExamplesPanel;
    private javax.swing.JScrollPane transExamplesScrollPanel;
    private javax.swing.JTextArea transExamplesTextArea;
    private javax.swing.JTextField transWordField;
    private javax.swing.JLabel transWordLabel;
    private javax.swing.JPanel transWordPanel;
    // End of variables declaration//GEN-END:variables
    private IconStock returnStatus;
}
