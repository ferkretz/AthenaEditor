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
public class AboutDialog extends javax.swing.JDialog {

    private final util.I18n i18n = new util.I18n(this);
    private final util.I18n i18nError = new util.I18n("ErrorMessages");

    public AboutDialog(java.awt.Frame parent) {
        super(parent, true);

        initComponents();

        String title = i18n.translate("About");
        setTitle(title + " - " + util.Settings.AppName.getValue()); // NOI18N
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/resource/image/athena_Icon.png")).getImage()); // NOI18N

        msgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/image/athena_Logo.png"))); // NOI18N

        try {
            infoTextPanel.setText("");
            infoTextPanel.setPage(getClass().getResource(i18n.translate("AboutPath"))); //NOI18N
        } catch (java.io.IOException ex) {
            infoTextPanel.setText(i18nError.translatef("LoadInfo", i18n.translate("AboutPath"))); //NOI18N
        }

        javax.swing.InputMap inputMap = getRootPane().getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "close");
        javax.swing.ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put("close", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                doClose();
            }
        });

        pack();
        this.setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgPanel = new javax.swing.JPanel();
        msgLabel = new javax.swing.JLabel();
        infoScrollPanel = new javax.swing.JScrollPane();
        infoTextPanel = new javax.swing.JTextPane();
        buttonPanel = new javax.swing.JPanel();
        closeButton = new Button(gui.IconStock.Close);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("AboutDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        msgPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        msgPanel.setLayout(new javax.swing.BoxLayout(msgPanel, javax.swing.BoxLayout.Y_AXIS));

        msgLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgLabel.setAlignmentX(0.5F);
        msgLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        msgLabel.setIconTextGap(0);
        msgPanel.add(msgLabel);

        infoScrollPanel.setMinimumSize(new java.awt.Dimension(22, 165));
        infoScrollPanel.setPreferredSize(new java.awt.Dimension(9, 165));

        infoTextPanel.setEditable(false);
        infoTextPanel.setBackground(new java.awt.Color(240, 240, 247));
        infoTextPanel.setContentType("text/html");
        infoTextPanel.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                infoTextPanelHyperlinkUpdate(evt);
            }
        });
        infoScrollPanel.setViewportView(infoTextPanel);

        msgPanel.add(infoScrollPanel);

        getContentPane().add(msgPanel);

        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new java.awt.FlowLayout(2, 5, 0));

        closeButton.setActionCommand("closeButton");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-706)/2, (screenSize.height-528)/2, 706, 528);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose();
    }//GEN-LAST:event_closeDialog

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        doClose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void infoTextPanelHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_infoTextPanelHyperlinkUpdate
        if (evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
            javax.swing.JEditorPane pane = (javax.swing.JEditorPane) evt.getSource();
            if (evt instanceof javax.swing.text.html.HTMLFrameHyperlinkEvent) {
                javax.swing.text.html.HTMLFrameHyperlinkEvent evt2 = (javax.swing.text.html.HTMLFrameHyperlinkEvent) evt;
                javax.swing.text.html.HTMLDocument doc = (javax.swing.text.html.HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt2);
            } else {
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec("xdg-open " + evt.getURL());
                } catch (java.io.IOException e) {
                    MessageDialog dialog = new MessageDialog(
                            IconStock.DialogError,
                            IconStock.Ok,
                            i18nError.translatef("UrlOpen", evt.getURL()),
                            this);
                    dialog.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_infoTextPanelHyperlinkUpdate

    private void doClose() {
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane infoScrollPanel;
    private javax.swing.JTextPane infoTextPanel;
    private javax.swing.JLabel msgLabel;
    private javax.swing.JPanel msgPanel;
    // End of variables declaration//GEN-END:variables
}
