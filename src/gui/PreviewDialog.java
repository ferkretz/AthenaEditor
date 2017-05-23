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
public class PreviewDialog extends javax.swing.JDialog {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private final util.I18n i18n = new util.I18n(this);
    private static final String htmlHeader = "<html>\n"
            + "    <head>\n"
            + "        <style>\n"
            + "            body{font-size: 9px; margin: 3px; background: #F0F0F7;}\n"
            + "            .block {margin-bottom: 10px;}\n"
            + "            .oword{font-size: 115%; padding: 5px 0 5px 0;}\n"
            + "            .oword .content{font-weight: bold; color: #0000C0;}\n"
            + "            .oword .class{font-weight: normal; font-style: italic; color: #000000;}\n"
            + "            .translation{margin: 0; padding: 2px 7px 2px 7px;}\n"
            + "            .translation .word{margin: 0 0 2px 0; font-weight: bold; color: #8000C0;}\n"
            + "            .examples{padding: 2px 7px 3px 7px;}\n"
            + "            .examples .title{font-weight: bold; color: #008000;}\n"
            + "            .examples .content1{margin: 2px 7px 2px 7px;}\n"
            + "            .examples .content2{margin: 2px 7px 2px 7px; font-style: italic;}\n"
            + "            .synonymes, .opposites{padding: 2px 7px 2px 7px; font-weight: bold;}\n"
            + "            .synonymes .title, .opposites .title{color: #008000;}\n"
            + "            .synonymes .content, .opposites .content{color: #8000C0;}\n"
            + "        </style>\n"
            + "    </head>\n"
            + "    <body>\n";
    private static final String htmlFooter = "    </body>\n"
            + "</html>\n";
    private static final String wordHeader = "        <div class=\"block\">\n"
            + "            <div class=\"oword\">\n"
            + "                <span class=\"content\">%word</span>\n"
            + "                <span class=\"class\">(%wordClass)</span>\n"
            + "            </div>\n";
    private static final String wordFooter = "        </div>\n";
    private static final String translationContent = "            <div class=\"translation\">\n"
            + "                <div class=\"word\">%transWord</div>\n"
            + "                <div class=\"examples\">\n"
            + "                    <div class=\"title\">%Examples:</div>\n"
            + "                    <div class=\"content1\">%examples</div>\n"
            + "                    <div class=\"content2\">%transExamples</div>\n"
            + "                </div>\n"
            + "            </div>\n";
    private static final String synonymesContent = "            <div class=\"synonymes\">\n"
            + "                <span class=\"title\">%Synonymes:</span>\n"
            + "                <span class=\"content\">%synonymes</span>\n"
            + "            </div>\n";
    private static final String oppositesContent = "            <div class=\"opposites\">\n"
            + "                <span class=\"title\">%Opposites:</span>\n"
            + "                <span class=\"content\">%opposites</span>\n"
            + "            </div>\n";
// </editor-fold>

    public PreviewDialog(java.awt.Frame parent, util.Word word) {
        super(parent, true);

        initComponents();

        String title = i18n.translate("Preview");
        setTitle(title + " - " + util.Settings.AppName.getValue()); // NOI18N

        String info = htmlHeader;
        info += wordHeader.replaceFirst("%word", word.getWord())
                .replaceFirst("%wordClass", word.getWordClass().getI18nString());
        for (util.WordTranslation translation : word.getTranslations()) {
            if (translation.isExamples()) {
                info += translationContent.replaceFirst("%transWord", translation.getTransWord()).
                        replaceFirst("%Examples:", i18n.translate("Examples")).
                        replaceFirst("%examples", translation.getExamples()).
                        replaceFirst("%transExamples", translation.getTransExamples());
            }
        }
        String synonymes = "";
        for (String synonyme : word.getSynonymes()) {
            synonymes += synonyme + ", ";
        }
        if (word.getSynonymes().size() != 0) {
            info += synonymesContent.replaceFirst("%Synonymes:", i18n.translate("Synonymes")).
                    replaceFirst("%synonymes", synonymes.replaceAll(", $", ""));
        }
        String opposites = "";
        for (String opposite : word.getOpposites()) {
            opposites += opposite + ", ";
        }
        if (word.getOpposites().size() != 0) {
            info += oppositesContent.replaceFirst("%Opposites:", i18n.translate("Opposites")).
                    replaceFirst("%opposites", opposites.replaceAll(", $", ""));
        }
        info += wordFooter;
        info += htmlFooter;

        infoTextPanel.setText(info);

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
        infoScrollPanel = new javax.swing.JScrollPane();
        infoTextPanel = new javax.swing.JTextPane();
        buttonPanel = new javax.swing.JPanel();
        closeButton = new Button(gui.IconStock.Close);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 420));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("PreviewDialog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 420));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        msgPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        msgPanel.setLayout(new javax.swing.BoxLayout(msgPanel, javax.swing.BoxLayout.Y_AXIS));

        infoScrollPanel.setMinimumSize(new java.awt.Dimension(22, 165));

        infoTextPanel.setEditable(false);
        infoTextPanel.setBackground(new java.awt.Color(240, 240, 247));
        infoTextPanel.setContentType("text/html"); // NOI18N
        infoScrollPanel.setViewportView(infoTextPanel);

        msgPanel.add(infoScrollPanel);

        getContentPane().add(msgPanel);

        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setAlignmentX(0.0F);
        buttonPanel.setMaximumSize(new java.awt.Dimension(1000, 50));
        buttonPanel.setMinimumSize(new java.awt.Dimension(100, 50));
        buttonPanel.setPreferredSize(new java.awt.Dimension(100, 50));
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 0));

        closeButton.setActionCommand("closeButton");
        closeButton.setAlignmentX(1.0F);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel);

        setSize(new java.awt.Dimension(502, 334));
        setLocationRelativeTo(null);
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

    private void doClose() {
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane infoScrollPanel;
    private javax.swing.JTextPane infoTextPanel;
    private javax.swing.JPanel msgPanel;
    // End of variables declaration//GEN-END:variables
}
