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
public class MessageDialog extends javax.swing.JDialog {

    public MessageDialog(IconStock buttonStock, String message, javax.swing.JDialog parent) {
        this(IconStock.DialogInformation, buttonStock, message, null, parent, true);
    }

    public MessageDialog(IconStock buttonStock, String message, String subMessage, javax.swing.JDialog parent) {
        this(IconStock.DialogInformation, buttonStock, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock buttonStock, String message, javax.swing.JDialog parent) {
        this(dialogStock, buttonStock, message, null, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock buttonStock, String message, String subMessage, javax.swing.JDialog parent) {
        this(dialogStock, buttonStock, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock buttonStock, String message, String subMessage, javax.swing.JDialog parent, boolean modal) {
        this(dialogStock, new IconStock[]{buttonStock}, message, subMessage, parent, modal);
    }

    public MessageDialog(IconStock[] buttonStocks, String message, javax.swing.JDialog parent) {
        this(IconStock.DialogInformation, buttonStocks, message, null, parent, true);
    }

    public MessageDialog(IconStock[] buttonStocks, String message, String subMessage, javax.swing.JDialog parent) {
        this(IconStock.DialogInformation, buttonStocks, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, javax.swing.JDialog parent) {
        this(dialogStock, buttonStocks, message, null, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, String subMessage, javax.swing.JDialog parent) {
        this(dialogStock, buttonStocks, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, String subMessage, javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        constructDialog(dialogStock, buttonStocks, message, subMessage);
        setLocationRelativeTo(parent);
        if (parent == null) {
            setModal(false);
        }
    }

    public MessageDialog(IconStock buttonStock, String message, java.awt.Frame parent) {
        this(IconStock.DialogInformation, buttonStock, message, null, parent, true);
    }

    public MessageDialog(IconStock buttonStock, String message, String subMessage, java.awt.Frame parent) {
        this(IconStock.DialogInformation, buttonStock, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock buttonStock, String message, java.awt.Frame parent) {
        this(dialogStock, buttonStock, message, null, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock buttonStock, String message, String subMessage, java.awt.Frame parent) {
        this(dialogStock, buttonStock, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock buttonStock, String message, String subMessage, java.awt.Frame parent, boolean modal) {
        this(dialogStock, new IconStock[]{buttonStock}, message, subMessage, parent, modal);
    }

    public MessageDialog(IconStock[] buttonStocks, String message, java.awt.Frame parent) {
        this(IconStock.DialogInformation, buttonStocks, message, null, parent, true);
    }

    public MessageDialog(IconStock[] buttonStocks, String message, String subMessage, java.awt.Frame parent) {
        this(IconStock.DialogInformation, buttonStocks, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, java.awt.Frame parent) {
        this(dialogStock, buttonStocks, message, null, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, String subMessage, java.awt.Frame parent) {
        this(dialogStock, buttonStocks, message, subMessage, parent, true);
    }

    public MessageDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, String subMessage, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        constructDialog(dialogStock, buttonStocks, message, subMessage);
        setLocationRelativeTo(parent);
        if (parent == null) {
            setModal(false);
        }
    }

    private void constructDialog(IconStock dialogStock, IconStock[] buttonStocks, String message, String subMessage) {
        initComponents();

        setTitle(dialogStock.getText() + " - " + util.Settings.AppName.getValue()); // NOI18N

        msgLabel.setIcon(dialogStock.getImageIcon());
        if ((subMessage != null) && (!"".equals(subMessage))) {
            msgLabel.setText(String.format("<html><b>%s</b><br/>%s</html>", message, subMessage));
        } else {
            msgLabel.setText(String.format("<html><b>%s</b></html>", message));
        }

        for (IconStock buttonStock : buttonStocks) {
            Button button = new Button(buttonStock);

            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    doClose(((Button) evt.getSource()).getStock());
                }
            });

            buttonPanel.add(button);

            defaultStatus = buttonStock;
        }

        javax.swing.InputMap inputMap = getRootPane().getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), defaultStatus.toString());
        javax.swing.ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(defaultStatus.toString(), new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doClose(defaultStatus);
            }
        });

        pack();
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public IconStock getReturnStatus() {
        return returnStatus;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgPanel = new javax.swing.JPanel();
        msgLabel = new javax.swing.JLabel();
        sepPanel = new javax.swing.JPanel();
        separator = new javax.swing.JSeparator();
        buttonPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("MessageDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        msgPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        msgPanel.setLayout(new java.awt.FlowLayout(0, 0, 0));

        msgLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        msgPanel.add(msgLabel);

        getContentPane().add(msgPanel);

        sepPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        sepPanel.setLayout(new javax.swing.BoxLayout(sepPanel, javax.swing.BoxLayout.LINE_AXIS));

        separator.setPreferredSize(null);
        sepPanel.add(separator);

        getContentPane().add(sepPanel);

        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new java.awt.FlowLayout(2, 5, 0));
        getContentPane().add(buttonPanel);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-439)/2, (screenSize.height-162)/2, 439, 162);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(defaultStatus);
    }//GEN-LAST:event_closeDialog

    private void doClose(IconStock retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JPanel msgPanel;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables
    private IconStock defaultStatus;
    private IconStock returnStatus;
}
