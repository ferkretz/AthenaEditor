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
public class SearchDialog extends javax.swing.JDialog {

    util.SearchTerms searchTerms = null;
    util.Members users;
    private final util.I18n i18n = new util.I18n(this);

    public SearchDialog(java.awt.Frame parent, util.Members users) {
        super(parent, true);

        this.users = users;
        initComponents();

        // *** not implemented yet ***
        hintButton.setVisible(false);

        constructLanguageComboBox();
        constructWordMatchComboBox();
        constructWordClassComboBox();
        constructWordStatusComboBox();
        constructUserComboBox();

        String title = i18n.translate("Search");
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

    public util.SearchTerms getSearchTerms() {
        return searchTerms;
    }

    private void constructLanguageComboBox() {
        javax.swing.DefaultComboBoxModel<util.Language> languageModel = new javax.swing.DefaultComboBoxModel<>();
        javax.swing.DefaultComboBoxModel<util.Language> transLanguageModel = new javax.swing.DefaultComboBoxModel<>();

        for (util.Language language : util.Language.getAllValues()) {
            languageModel.addElement(language);
            transLanguageModel.addElement(language);
        }

        javax.swing.ListCellRenderer<util.Language> languageRenderer = new javax.swing.ListCellRenderer<util.Language>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, util.Language language,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(language.getI18nString());

                return labelItem;
            }
        };
        languageComboBox.setModel(languageModel);
        languageComboBox.setRenderer(languageRenderer);
        languageComboBox.setSelectedItem(util.Language.Any);
        transLanguageComboBox.setModel(transLanguageModel);
        transLanguageComboBox.setRenderer(languageRenderer);
        transLanguageComboBox.setSelectedItem(util.Language.Any);
    }

    private void constructWordMatchComboBox() {
        javax.swing.DefaultComboBoxModel<util.WordMatch> wordMatchModel = new javax.swing.DefaultComboBoxModel<>();

        for (util.WordMatch wordMatch : util.WordMatch.values()) {
            wordMatchModel.addElement(wordMatch);
        }

        javax.swing.ListCellRenderer<util.WordMatch> wordMatchRenderer = new javax.swing.ListCellRenderer<util.WordMatch>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, util.WordMatch wordMatch,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(wordMatch.getI18nString());

                return labelItem;
            }
        };
        wordMatchComboBox.setModel(wordMatchModel);
        wordMatchComboBox.setRenderer(wordMatchRenderer);
        wordMatchComboBox.setSelectedItem(util.WordMatch.PrefixMatch);
    }

    private void constructWordClassComboBox() {
        javax.swing.DefaultComboBoxModel<util.WordClass> wordClassModel = new javax.swing.DefaultComboBoxModel<>();

        for (util.WordClass wordClass : util.WordClass.getAllValues()) {
            wordClassModel.addElement(wordClass);
        }

        javax.swing.ListCellRenderer<util.WordClass> wordClassRenderer = new javax.swing.ListCellRenderer<util.WordClass>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, util.WordClass wordClass,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(wordClass.getI18nString());

                return labelItem;
            }
        };
        wordClassComboBox.setModel(wordClassModel);
        wordClassComboBox.setRenderer(wordClassRenderer);
        wordClassComboBox.setSelectedItem(util.WordClass.Any);
    }

    private void constructUserComboBox() {
        javax.swing.DefaultComboBoxModel<util.Member> authorModel = new javax.swing.DefaultComboBoxModel<>();
        javax.swing.DefaultComboBoxModel<util.Member> editorModel = new javax.swing.DefaultComboBoxModel<>();

        authorModel.addElement(new util.Member(0, ""));
        editorModel.addElement(new util.Member(0, ""));

        for (util.Member user : users) {
            authorModel.addElement(user);
            editorModel.addElement(user);
        }

        javax.swing.ListCellRenderer<util.Member> userRenderer = new javax.swing.ListCellRenderer<util.Member>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, util.Member user,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(user.getName());

                return labelItem;
            }
        };
        authorComboBox.setModel(authorModel);
        authorComboBox.setRenderer(userRenderer);
        editorComboBox.setModel(editorModel);
        editorComboBox.setRenderer(userRenderer);
    }

    private void constructWordStatusComboBox() {
        javax.swing.DefaultComboBoxModel<util.WordStatus> wordStatusModel = new javax.swing.DefaultComboBoxModel<>();

        for (util.WordStatus wordStatus : util.WordStatus.getAllValues()) {
            wordStatusModel.addElement(wordStatus);
        }

        javax.swing.ListCellRenderer<util.WordStatus> wordStatusRenderer = new javax.swing.ListCellRenderer<util.WordStatus>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList list, util.WordStatus wordStatus,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(wordStatus.getI18nString());

                return labelItem;
            }
        };
        wordStatusComboBox.setModel(wordStatusModel);
        wordStatusComboBox.setRenderer(wordStatusRenderer);
        wordStatusComboBox.setSelectedItem(util.WordStatus.ExcludeHidden);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputPanel = new javax.swing.JPanel();
        dictionaryPanel = new javax.swing.JPanel();
        dictionaryLabel = new javax.swing.JLabel();
        languageComboBox = new javax.swing.JComboBox<>();
        swapLanguageButton = new ToolButton(IconStock.Swap);
        transLanguageComboBox = new javax.swing.JComboBox<>();
        wordPanel = new javax.swing.JPanel();
        wordLabel = new javax.swing.JLabel();
        wordField = new javax.swing.JTextField();
        wordMatchComboBox = new javax.swing.JComboBox<>();
        wordClassPanel = new javax.swing.JPanel();
        wordClassLabel = new javax.swing.JLabel();
        wordClassComboBox = new javax.swing.JComboBox<>();
        authorPanel = new javax.swing.JPanel();
        authorLabel = new javax.swing.JLabel();
        authorComboBox = new javax.swing.JComboBox<>();
        authorToggleButton = new javax.swing.JToggleButton();
        editorPanel = new javax.swing.JPanel();
        editorLabel = new javax.swing.JLabel();
        editorComboBox = new javax.swing.JComboBox<>();
        editorToggleButton = new javax.swing.JToggleButton();
        wordStatusPanel = new javax.swing.JPanel();
        wordStatusLabel = new javax.swing.JLabel();
        wordStatusComboBox = new javax.swing.JComboBox<>();
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
        setName("SearchDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        inputPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new javax.swing.BoxLayout(inputPanel, javax.swing.BoxLayout.PAGE_AXIS));

        java.awt.FlowLayout flowLayout9 = new java.awt.FlowLayout(0);
        flowLayout9.setAlignOnBaseline(true);
        dictionaryPanel.setLayout(flowLayout9);

        dictionaryLabel.setText(i18n.translate("Dictionary:")); // NOI18N
        dictionaryLabel.setPreferredSize(new java.awt.Dimension(110, 19));
        dictionaryPanel.add(dictionaryLabel);

        languageComboBox.setToolTipText(i18n.translate("Select first language")); // NOI18N
        languageComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        languageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                languageComboBoxActionPerformed(evt);
            }
        });
        dictionaryPanel.add(languageComboBox);

        swapLanguageButton.setToolTipText(i18n.translate("Swap language")); // NOI18N
        swapLanguageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swapLanguageButtonActionPerformed(evt);
            }
        });
        dictionaryPanel.add(swapLanguageButton);

        transLanguageComboBox.setToolTipText(i18n.translate("Select second language")); // NOI18N
        transLanguageComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        transLanguageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transLanguageComboBoxActionPerformed(evt);
            }
        });
        dictionaryPanel.add(transLanguageComboBox);

        inputPanel.add(dictionaryPanel);

        java.awt.FlowLayout flowLayout5 = new java.awt.FlowLayout(0);
        flowLayout5.setAlignOnBaseline(true);
        wordPanel.setLayout(flowLayout5);

        wordLabel.setText(i18n.translate("Word:")); // NOI18N
        wordLabel.setPreferredSize(new java.awt.Dimension(110, 19));
        wordPanel.add(wordLabel);

        wordField.setToolTipText(i18n.translate("Set word to search or leave empty")); // NOI18N
        wordField.setPreferredSize(new java.awt.Dimension(220, 19));
        wordPanel.add(wordField);

        wordMatchComboBox.setToolTipText(i18n.translate("Set search method")); // NOI18N
        wordMatchComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        wordPanel.add(wordMatchComboBox);

        inputPanel.add(wordPanel);

        java.awt.FlowLayout flowLayout10 = new java.awt.FlowLayout(0);
        flowLayout10.setAlignOnBaseline(true);
        wordClassPanel.setLayout(flowLayout10);

        wordClassLabel.setText(i18n.translate("Word class:")); // NOI18N
        wordClassLabel.setPreferredSize(new java.awt.Dimension(110, 19));
        wordClassPanel.add(wordClassLabel);

        wordClassComboBox.setToolTipText(i18n.translate("Select word class")); // NOI18N
        wordClassComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        wordClassPanel.add(wordClassComboBox);

        inputPanel.add(wordClassPanel);

        java.awt.FlowLayout flowLayout11 = new java.awt.FlowLayout(0);
        flowLayout11.setAlignOnBaseline(true);
        authorPanel.setLayout(flowLayout11);

        authorLabel.setText(i18n.translate("Author:")); // NOI18N
        authorLabel.setPreferredSize(new java.awt.Dimension(110, 19));
        authorPanel.add(authorLabel);

        authorComboBox.setToolTipText(i18n.translate("Select author or leave empty")); // NOI18N
        authorComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        authorPanel.add(authorComboBox);

        authorToggleButton.setText(i18n.translate("Exclude")); // NOI18N
        authorToggleButton.setToolTipText(i18n.translate("Omit author")); // NOI18N
        authorPanel.add(authorToggleButton);

        inputPanel.add(authorPanel);

        java.awt.FlowLayout flowLayout12 = new java.awt.FlowLayout(0);
        flowLayout12.setAlignOnBaseline(true);
        editorPanel.setLayout(flowLayout12);

        editorLabel.setText(i18n.translate("Editor:")); // NOI18N
        editorLabel.setPreferredSize(new java.awt.Dimension(110, 19));
        editorPanel.add(editorLabel);

        editorComboBox.setToolTipText(i18n.translate("Select editor or leave empty")); // NOI18N
        editorComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        editorPanel.add(editorComboBox);

        editorToggleButton.setText(i18n.translate("Exclude")); // NOI18N
        editorToggleButton.setToolTipText(i18n.translate("Omit editor")); // NOI18N
        editorPanel.add(editorToggleButton);

        inputPanel.add(editorPanel);

        java.awt.FlowLayout flowLayout13 = new java.awt.FlowLayout(0);
        flowLayout13.setAlignOnBaseline(true);
        wordStatusPanel.setLayout(flowLayout13);

        wordStatusLabel.setText(i18n.translate("Word status:")); // NOI18N
        wordStatusLabel.setPreferredSize(new java.awt.Dimension(110, 19));
        wordStatusPanel.add(wordStatusLabel);

        wordStatusComboBox.setToolTipText(i18n.translate("Select word status")); // NOI18N
        wordStatusComboBox.setPreferredSize(new java.awt.Dimension(160, 24));
        wordStatusPanel.add(wordStatusComboBox);

        inputPanel.add(wordStatusPanel);

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
        setBounds((screenSize.width-676)/2, (screenSize.height-321)/2, 676, 321);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_closeDialog

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        searchTerms = new util.SearchTerms();

        searchTerms.setLanguage((util.Language) languageComboBox.getSelectedItem());
        searchTerms.setTransLanguage((util.Language) transLanguageComboBox.getSelectedItem());
        searchTerms.setWord(wordField.getText());
        searchTerms.setWordMatch((util.WordMatch) wordMatchComboBox.getSelectedItem());
        searchTerms.setWordClass((util.WordClass) wordClassComboBox.getSelectedItem());
        searchTerms.setAuthor(((util.Member) authorComboBox.getSelectedItem()).getName());
        searchTerms.setInverseAuthor(authorToggleButton.isSelected());
        searchTerms.setEditor(((util.Member) editorComboBox.getSelectedItem()).getName());
        searchTerms.setInverseEditor(editorToggleButton.isSelected());
        searchTerms.setWordStatus((util.WordStatus) wordStatusComboBox.getSelectedItem());

        doClose(IconStock.Ok);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void languageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageComboBoxActionPerformed
        util.Language language = (util.Language) languageComboBox.getSelectedItem();
        util.Language transLanguage = (util.Language) transLanguageComboBox.getSelectedItem();
        javax.swing.DefaultComboBoxModel<util.Language> transLanguageModel
                = (javax.swing.DefaultComboBoxModel<util.Language>) transLanguageComboBox.getModel();

        transLanguageModel.removeAllElements();
        for (util.Language defLanguage : util.Language.getAllValues()) {
            if ((language != defLanguage) || (util.Language.Any == defLanguage)) {
                transLanguageModel.addElement(defLanguage);
            }
        }
        transLanguageComboBox.setSelectedItem(transLanguage);
    }//GEN-LAST:event_languageComboBoxActionPerformed

    private void transLanguageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transLanguageComboBoxActionPerformed
        util.Language transLanguage = (util.Language) transLanguageComboBox.getSelectedItem();
        util.Language language = (util.Language) languageComboBox.getSelectedItem();
        javax.swing.DefaultComboBoxModel<util.Language> languageModel
                = (javax.swing.DefaultComboBoxModel<util.Language>) languageComboBox.getModel();

        languageModel.removeAllElements();
        for (util.Language defLanguage : util.Language.getAllValues()) {
            if ((transLanguage != defLanguage) || (util.Language.Any == defLanguage)) {
                languageModel.addElement(defLanguage);
            }
        }
        languageComboBox.setSelectedItem(language);
    }//GEN-LAST:event_transLanguageComboBoxActionPerformed

    private void swapLanguageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swapLanguageButtonActionPerformed
        javax.swing.DefaultComboBoxModel<util.Language> languageModel
                = (javax.swing.DefaultComboBoxModel<util.Language>) languageComboBox.getModel();
        languageComboBox.setModel(transLanguageComboBox.getModel());
        transLanguageComboBox.setModel(languageModel);
    }//GEN-LAST:event_swapLanguageButtonActionPerformed

    private void doClose(IconStock retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<util.Member> authorComboBox;
    private javax.swing.JLabel authorLabel;
    private javax.swing.JPanel authorPanel;
    private javax.swing.JToggleButton authorToggleButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel dictionaryLabel;
    private javax.swing.JPanel dictionaryPanel;
    private javax.swing.JComboBox<util.Member> editorComboBox;
    private javax.swing.JLabel editorLabel;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JToggleButton editorToggleButton;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JButton hintButton;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JComboBox<util.Language> languageComboBox;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton swapLanguageButton;
    private javax.swing.JComboBox<util.Language> transLanguageComboBox;
    private javax.swing.JComboBox<util.WordClass> wordClassComboBox;
    private javax.swing.JLabel wordClassLabel;
    private javax.swing.JPanel wordClassPanel;
    private javax.swing.JTextField wordField;
    private javax.swing.JLabel wordLabel;
    private javax.swing.JComboBox<util.WordMatch> wordMatchComboBox;
    private javax.swing.JPanel wordPanel;
    private javax.swing.JComboBox<util.WordStatus> wordStatusComboBox;
    private javax.swing.JLabel wordStatusLabel;
    private javax.swing.JPanel wordStatusPanel;
    // End of variables declaration//GEN-END:variables
    private IconStock returnStatus;
}
