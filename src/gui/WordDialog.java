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

public class WordDialog extends javax.swing.JDialog {

    util.Word word;
    private final util.I18n i18n = new util.I18n(this);
    private final util.I18n i18nError = new util.I18n("ErrorMessages");

    public WordDialog(java.awt.Frame parent, String title, util.Word word) {
        super(parent, true);

        this.word = new util.Word(word);

        initComponents();

        // *** not implemented yet ***
        hintButton.setVisible(false);

        constructLanguageComboBox();
        constructWordClassComboBox();
        constructWordPanel();
        constructTranslationPanel();
        constructSynonymesPanel();
        constructOppositesPanel();
        constructLutWordsPanel();
        constructTransLutWordsPanel();
        constructWordStatusComboBox();

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

    public util.Word getWord() {
        word.setLanguage((util.Language) this.languageComboBox.getSelectedItem());
        word.setTransLanguage((util.Language) this.transLanguageComboBox.getSelectedItem());
        word.setWordClass((util.WordClass) this.wordClassComboBox.getSelectedItem());
        word.setWord(this.wordField.getText());
        word.setTranslations(
                this.translationsComboBox.getModel().getSize() == 0
                ? new util.WordTranslations() : getWordTranslations(this.translationsComboBox));
        word.setSynonymes(
                this.synonymesComboBox.getModel().getSize() == 0
                ? new util.Synonymes() : getSynonymes(this.synonymesComboBox));
        word.setOpposites(
                this.oppositesComboBox.getModel().getSize() == 0
                ? new util.Opposites() : getOpposites(this.oppositesComboBox));
        word.setLutWords(
                this.lutWordsComboBox.getModel().getSize() == 0
                ? new util.LutWords() : getLutWords(this.lutWordsComboBox));
        word.setTransLutWords(
                this.transLutWordsComboBox.getModel().getSize() == 0
                ? new util.LutWords() : getLutWords(this.transLutWordsComboBox));
        word.setStatus((util.WordStatus) this.wordStatusComboBox.getSelectedItem());

        return word;
    }

    private util.WordTranslations getWordTranslations(javax.swing.JComboBox<util.WordTranslation> comboBox) {
        javax.swing.ComboBoxModel<util.WordTranslation> model = comboBox.getModel();
        util.WordTranslations wordTranslations = new util.WordTranslations();

        for (int i = 0; i < model.getSize(); i++) {
            wordTranslations.add(model.getElementAt(i));
        }

        return wordTranslations;
    }

    private util.Synonymes getSynonymes(javax.swing.JComboBox<String> comboBox) {
        javax.swing.ComboBoxModel<String> model = comboBox.getModel();
        util.Synonymes synonymes = new util.Synonymes();

        for (int i = 0; i < model.getSize(); i++) {
            synonymes.add(model.getElementAt(i));
        }

        return synonymes;
    }

    private util.Opposites getOpposites(javax.swing.JComboBox<String> comboBox) {
        javax.swing.ComboBoxModel<String> model = comboBox.getModel();
        util.Opposites opposites = new util.Opposites();

        for (int i = 0; i < model.getSize(); i++) {
            opposites.add(model.getElementAt(i));
        }

        return opposites;
    }

    private util.LutWords getLutWords(javax.swing.JComboBox<String> comboBox) {
        javax.swing.ComboBoxModel<String> model = comboBox.getModel();
        util.LutWords lutWords = new util.LutWords();

        for (int i = 0; i < model.getSize(); i++) {
            lutWords.add(model.getElementAt(i));
        }

        return lutWords;
    }

    private void constructLanguageComboBox() {
        javax.swing.DefaultComboBoxModel<util.Language> languageModel = new javax.swing.DefaultComboBoxModel<>();
        javax.swing.DefaultComboBoxModel<util.Language> transLanguageModel = new javax.swing.DefaultComboBoxModel<>();

        for (util.Language language : util.Language.getValues()) {
            languageModel.addElement(language);
            transLanguageModel.addElement(language);
        }

        javax.swing.ListCellRenderer<util.Language> languageRenderer = new javax.swing.ListCellRenderer<util.Language>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<? extends util.Language> list, util.Language language,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(language.getI18nString());

                return labelItem;
            }
        };
        languageComboBox.setModel(languageModel);
        languageComboBox.setRenderer(languageRenderer);
        languageComboBox.setSelectedItem(word.getLanguage());
        transLanguageComboBox.setModel(transLanguageModel);
        transLanguageComboBox.setRenderer(languageRenderer);
        transLanguageComboBox.setSelectedItem(word.getTransLanguage());
    }

    private void constructWordClassComboBox() {
        javax.swing.DefaultComboBoxModel<util.WordClass> wordClassModel = new javax.swing.DefaultComboBoxModel<>();

        for (util.WordClass wordClass : util.WordClass.getValues()) {
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
        wordClassComboBox.setSelectedItem(word.getWordClass());
    }

    private void constructWordPanel() {
        wordField.setText(word.getWord());
    }

    private void constructTranslationPanel() {
        javax.swing.DefaultComboBoxModel<util.WordTranslation> model = new javax.swing.DefaultComboBoxModel<>();

        for (util.WordTranslation translation : word.getTranslations()) {
            model.addElement(translation);
        }

        javax.swing.ListCellRenderer<Object> renderer = new javax.swing.ListCellRenderer<Object>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object translation,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                if (translation != null) {
                    if (translation.getClass().equals(util.WordTranslation.class)) {
                        labelItem.setText(((util.WordTranslation) translation).getTransWord());
                    }
                }

                return labelItem;
            }
        };
        translationsComboBox.setModel(model);
        translationsComboBox.setRenderer(renderer);

        if (word.getTranslations().size() == 0) {
            editTranslationButton.setEnabled(false);
            removeTranslationButton.setEnabled(false);
        }
    }

    private void constructSynonymesPanel() {
        javax.swing.DefaultComboBoxModel<String> model = new javax.swing.DefaultComboBoxModel<>();

        for (String synonyme : word.getSynonymes()) {
            model.addElement(synonyme);
        }

        synonymesComboBox.setModel(model);

        if (word.getSynonymes().size() == 0) {
            editSynonymeButton.setEnabled(false);
            removeSynonymeButton.setEnabled(false);
        }
    }

    private void constructOppositesPanel() {
        javax.swing.DefaultComboBoxModel<String> model = new javax.swing.DefaultComboBoxModel<>();

        for (String opposite : word.getOpposites()) {
            model.addElement(opposite);
        }

        oppositesComboBox.setModel(model);

        if (word.getOpposites().size() == 0) {
            editOppositeButton.setEnabled(false);
            removeOppositeButton.setEnabled(false);
        }
    }

    private void constructLutWordsPanel() {
        javax.swing.DefaultComboBoxModel<String> model = new javax.swing.DefaultComboBoxModel<>();

        for (String lutWord : word.getLutWords()) {
            model.addElement(lutWord);
        }

        lutWordsComboBox.setModel(model);

        if (word.getLutWords().size() == 0) {
            editLutWordButton.setEnabled(false);
            removeLutWordButton.setEnabled(false);
        }
    }

    private void constructTransLutWordsPanel() {
        javax.swing.DefaultComboBoxModel<String> model = new javax.swing.DefaultComboBoxModel<>();

        for (String transLutWord : word.getTransLutWords()) {
            model.addElement(transLutWord);
        }

        transLutWordsComboBox.setModel(model);

        if (word.getTransLutWords().size() == 0) {
            editTransLutWordButton.setEnabled(false);
            removeTransLutWordButton.setEnabled(false);
        }
    }

    private void constructWordStatusComboBox() {
        javax.swing.DefaultComboBoxModel<util.WordStatus> model = new javax.swing.DefaultComboBoxModel<>();

        for (util.WordStatus wordStatus : util.WordStatus.getValues()) {
            model.addElement(wordStatus);
        }

        javax.swing.ListCellRenderer<util.WordStatus> renderer = new javax.swing.ListCellRenderer<util.WordStatus>() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<? extends util.WordStatus> list, util.WordStatus wordStatus,
                    int index, boolean isSelected, boolean cellHasFocus) {
                javax.swing.JLabel labelItem = new javax.swing.JLabel();

                labelItem.setText(wordStatus.getI18nString());

                return labelItem;
            }
        };
        wordStatusComboBox.setModel(model);
        wordStatusComboBox.setRenderer(renderer);
        wordStatusComboBox.setSelectedItem(word.getStatus());
    }

    private void editString(
            String title,
            String stringTitle,
            javax.swing.JComboBox<String> comboBox) {
        StringEditorDialog dialog = new StringEditorDialog(this, title, stringTitle, (String) comboBox.getSelectedItem());
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            javax.swing.DefaultComboBoxModel<String> model = (javax.swing.DefaultComboBoxModel<String>) comboBox.getModel();
            int index = comboBox.getSelectedIndex();
            model.insertElementAt(dialog.getString(), index);
            model.removeElementAt(index + 1);
        }
    }

    private void addString(
            String title,
            String stringTitle,
            javax.swing.JComboBox<String> comboBox,
            javax.swing.JButton editButton,
            javax.swing.JButton removeButton) {
        StringEditorDialog dialog = new StringEditorDialog(this, title, stringTitle, ""); //NOI18N
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            javax.swing.DefaultComboBoxModel<String> model = (javax.swing.DefaultComboBoxModel<String>) comboBox.getModel();
            String string = dialog.getString();

            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).equals(string)) {
                    return;
                }
            }

            model.addElement(string);

            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        }
    }

    private void removeString(
            javax.swing.JComboBox<String> comboBox,
            javax.swing.JButton editButton,
            javax.swing.JButton removeButton) {
        javax.swing.DefaultComboBoxModel<String> model = (javax.swing.DefaultComboBoxModel<String>) comboBox.getModel();

        model.removeElementAt(comboBox.getSelectedIndex());

        if (model.getSize() == 0) {
            editButton.setEnabled(false);
            removeButton.setEnabled(false);
        }
    }

    private void editTranslation(
            String title,
            javax.swing.JComboBox<util.WordTranslation> comboBox) {
        TranslationEditorDialog dialog = new TranslationEditorDialog(this, title, (util.WordTranslation) comboBox.getSelectedItem());
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            javax.swing.DefaultComboBoxModel<util.WordTranslation> model = (javax.swing.DefaultComboBoxModel<util.WordTranslation>) comboBox.getModel();
            int index = comboBox.getSelectedIndex();
            model.insertElementAt(dialog.getTranslation(), index);
            model.removeElementAt(index + 1);
        }
    }

    private void addTranslation(
            String title,
            javax.swing.JComboBox<util.WordTranslation> comboBox,
            javax.swing.JButton editButton,
            javax.swing.JButton removeButton) {
        TranslationEditorDialog dialog = new TranslationEditorDialog(this, title, new util.WordTranslation());
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            javax.swing.DefaultComboBoxModel<util.WordTranslation> model
                    = (javax.swing.DefaultComboBoxModel<util.WordTranslation>) comboBox.getModel();

            util.WordTranslation translation = dialog.getTranslation();
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).getTransWord().equals(translation.getTransWord())) {
                    return;
                }
            }

            model.addElement(translation);

            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        }
    }

    private void removeTranslation(
            javax.swing.JComboBox<util.WordTranslation> comboBox,
            javax.swing.JButton editButton,
            javax.swing.JButton removeButton) {
        javax.swing.DefaultComboBoxModel<util.WordTranslation> model = (javax.swing.DefaultComboBoxModel<util.WordTranslation>) comboBox.getModel();

        model.removeElementAt(comboBox.getSelectedIndex());

        if (model.getSize() == 0) {
            editButton.setEnabled(false);
            removeButton.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputPanel = new javax.swing.JPanel();
        dictionaryPanel = new javax.swing.JPanel();
        dictionaryLabel = new javax.swing.JLabel();
        languageComboBox = new javax.swing.JComboBox<>();
        swapLanguageButton = new ToolButton(IconStock.Swap);
        transLanguageComboBox = new javax.swing.JComboBox<>();
        wordClassPanel = new javax.swing.JPanel();
        wordClassLabel = new javax.swing.JLabel();
        wordClassComboBox = new javax.swing.JComboBox<>();
        wordPanel = new javax.swing.JPanel();
        wordLabel = new javax.swing.JLabel();
        wordField = new javax.swing.JTextField();
        translationsPanel = new javax.swing.JPanel();
        translationsLabel = new javax.swing.JLabel();
        translationsComboBox = new javax.swing.JComboBox<>();
        editTranslationButton = new ToolButton(IconStock.Edit);
        addTranslationButton = new ToolButton(IconStock.Add);
        removeTranslationButton = new ToolButton(IconStock.Remove);
        synonymesPanel = new javax.swing.JPanel();
        synonymesLabel = new javax.swing.JLabel();
        synonymesComboBox = new javax.swing.JComboBox<>();
        editSynonymeButton = new ToolButton(IconStock.Edit);
        addSynonymeButton = new ToolButton(IconStock.Add);
        removeSynonymeButton = new ToolButton(IconStock.Remove);
        oppositesPanel = new javax.swing.JPanel();
        oppositesLabel = new javax.swing.JLabel();
        oppositesComboBox = new javax.swing.JComboBox<>();
        editOppositeButton = new ToolButton(IconStock.Edit);
        addOppositeButton = new ToolButton(IconStock.Add);
        removeOppositeButton = new ToolButton(IconStock.Remove);
        lutWordsPanel = new javax.swing.JPanel();
        lutWordsLabel = new javax.swing.JLabel();
        lutWordsComboBox = new javax.swing.JComboBox<>();
        editLutWordButton = new ToolButton(IconStock.Edit);
        addLutWordButton = new ToolButton(IconStock.Add);
        removeLutWordButton = new ToolButton(IconStock.Remove);
        transLutWordsPanel = new javax.swing.JPanel();
        transLutWordsLabel = new javax.swing.JLabel();
        transLutWordsComboBox = new javax.swing.JComboBox<>();
        editTransLutWordButton = new ToolButton(IconStock.Edit);
        addTransLutWordButton = new ToolButton(IconStock.Add);
        removeTransLutWordButton = new ToolButton(IconStock.Remove);
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
        setName("WordDialog"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        inputPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new javax.swing.BoxLayout(inputPanel, javax.swing.BoxLayout.PAGE_AXIS));

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(0);
        flowLayout1.setAlignOnBaseline(true);
        dictionaryPanel.setLayout(flowLayout1);

        dictionaryLabel.setText(i18n.translate("Dictionary:")); // NOI18N
        dictionaryLabel.setToolTipText(i18n.translate("Select dictionary")); // NOI18N
        dictionaryLabel.setPreferredSize(new java.awt.Dimension(140, 19));
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

        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(0);
        flowLayout3.setAlignOnBaseline(true);
        wordClassPanel.setLayout(flowLayout3);

        wordClassLabel.setText(i18n.translate("Word class:")); // NOI18N
        wordClassLabel.setToolTipText(i18n.translate("Select word class")); // NOI18N
        wordClassLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        wordClassPanel.add(wordClassLabel);

        wordClassComboBox.setToolTipText(i18n.translate("Select word class")); // NOI18N
        wordClassComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
        wordClassPanel.add(wordClassComboBox);

        inputPanel.add(wordClassPanel);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(0);
        flowLayout2.setAlignOnBaseline(true);
        wordPanel.setLayout(flowLayout2);

        wordLabel.setText(i18n.translate("Word:")); // NOI18N
        wordLabel.setToolTipText(i18n.translate("Edit word")); // NOI18N
        wordLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        wordPanel.add(wordLabel);

        wordField.setToolTipText(i18n.translate("Set word to search or leave empty")); // NOI18N
        wordField.setPreferredSize(new java.awt.Dimension(220, 19));
        wordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wordFieldFocusGained(evt);
            }
        });
        wordPanel.add(wordField);

        inputPanel.add(wordPanel);

        java.awt.FlowLayout flowLayout5 = new java.awt.FlowLayout(0);
        flowLayout5.setAlignOnBaseline(true);
        translationsPanel.setLayout(flowLayout5);

        translationsLabel.setText(i18n.translate("Translations:")); // NOI18N
        translationsLabel.setToolTipText(i18n.translate("Edit translations")); // NOI18N
        translationsLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        translationsPanel.add(translationsLabel);

        translationsComboBox.setToolTipText(i18n.translate("Select translation")); // NOI18N
        translationsComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
        translationsPanel.add(translationsComboBox);

        editTranslationButton.setToolTipText(i18n.translate("Edit translation")); // NOI18N
        editTranslationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTranslationButtonActionPerformed(evt);
            }
        });
        translationsPanel.add(editTranslationButton);

        addTranslationButton.setToolTipText(i18n.translate("Add new translation")); // NOI18N
        addTranslationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTranslationButtonActionPerformed(evt);
            }
        });
        translationsPanel.add(addTranslationButton);

        removeTranslationButton.setToolTipText(i18n.translate("Remove translation")); // NOI18N
        removeTranslationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTranslationButtonActionPerformed(evt);
            }
        });
        translationsPanel.add(removeTranslationButton);

        inputPanel.add(translationsPanel);

        java.awt.FlowLayout flowLayout6 = new java.awt.FlowLayout(0);
        flowLayout6.setAlignOnBaseline(true);
        synonymesPanel.setLayout(flowLayout6);

        synonymesLabel.setText(i18n.translate("Synonymes:")); // NOI18N
        synonymesLabel.setToolTipText(i18n.translate("Edit synonymes")); // NOI18N
        synonymesLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        synonymesPanel.add(synonymesLabel);

        synonymesComboBox.setToolTipText(i18n.translate("Select synonyme")); // NOI18N
        synonymesComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
        synonymesPanel.add(synonymesComboBox);

        editSynonymeButton.setToolTipText(i18n.translate("Edit synonyme")); // NOI18N
        editSynonymeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSynonymeButtonActionPerformed(evt);
            }
        });
        synonymesPanel.add(editSynonymeButton);

        addSynonymeButton.setToolTipText(i18n.translate("Add new synonyme")); // NOI18N
        addSynonymeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSynonymeButtonActionPerformed(evt);
            }
        });
        synonymesPanel.add(addSynonymeButton);

        removeSynonymeButton.setToolTipText(i18n.translate("Remove synonyme")); // NOI18N
        removeSynonymeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSynonymeButtonActionPerformed(evt);
            }
        });
        synonymesPanel.add(removeSynonymeButton);

        inputPanel.add(synonymesPanel);

        java.awt.FlowLayout flowLayout7 = new java.awt.FlowLayout(0);
        flowLayout7.setAlignOnBaseline(true);
        oppositesPanel.setLayout(flowLayout7);

        oppositesLabel.setText(i18n.translate("Opposites:")); // NOI18N
        oppositesLabel.setToolTipText(i18n.translate("Edit opposites")); // NOI18N
        oppositesLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        oppositesPanel.add(oppositesLabel);

        oppositesComboBox.setToolTipText(i18n.translate("Select opposite")); // NOI18N
        oppositesComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
        oppositesPanel.add(oppositesComboBox);

        editOppositeButton.setToolTipText(i18n.translate("Edit opposite")); // NOI18N
        editOppositeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOppositeButtonActionPerformed(evt);
            }
        });
        oppositesPanel.add(editOppositeButton);

        addOppositeButton.setToolTipText(i18n.translate("Add new opposite")); // NOI18N
        addOppositeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOppositeButtonActionPerformed(evt);
            }
        });
        oppositesPanel.add(addOppositeButton);

        removeOppositeButton.setToolTipText(i18n.translate("Remove opposite")); // NOI18N
        removeOppositeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOppositeButtonActionPerformed(evt);
            }
        });
        oppositesPanel.add(removeOppositeButton);

        inputPanel.add(oppositesPanel);

        java.awt.FlowLayout flowLayout8 = new java.awt.FlowLayout(0);
        flowLayout8.setAlignOnBaseline(true);
        lutWordsPanel.setLayout(flowLayout8);

        lutWordsLabel.setText(i18n.translate("Search keys:")); // NOI18N
        lutWordsLabel.setToolTipText(i18n.translate("Edit search symbols for first language")); // NOI18N
        lutWordsLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        lutWordsPanel.add(lutWordsLabel);

        lutWordsComboBox.setToolTipText(i18n.translate("Select search key")); // NOI18N
        lutWordsComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
        lutWordsPanel.add(lutWordsComboBox);

        editLutWordButton.setToolTipText(i18n.translate("Edit search key")); // NOI18N
        editLutWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLutWordButtonActionPerformed(evt);
            }
        });
        lutWordsPanel.add(editLutWordButton);

        addLutWordButton.setToolTipText(i18n.translate("Add new search key")); // NOI18N
        addLutWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLutWordButtonActionPerformed(evt);
            }
        });
        lutWordsPanel.add(addLutWordButton);

        removeLutWordButton.setToolTipText(i18n.translate("Remove search key")); // NOI18N
        removeLutWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeLutWordButtonActionPerformed(evt);
            }
        });
        lutWordsPanel.add(removeLutWordButton);

        inputPanel.add(lutWordsPanel);

        java.awt.FlowLayout flowLayout9 = new java.awt.FlowLayout(0);
        flowLayout9.setAlignOnBaseline(true);
        transLutWordsPanel.setLayout(flowLayout9);

        transLutWordsLabel.setText(i18n.translate("T. search keys:")); // NOI18N
        transLutWordsLabel.setToolTipText(i18n.translate("Edit search symbols for second language")); // NOI18N
        transLutWordsLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        transLutWordsPanel.add(transLutWordsLabel);
        transLutWordsLabel.getAccessibleContext().setAccessibleName("Trans. search keys:");

        transLutWordsComboBox.setToolTipText(i18n.translate("Select translation search key")); // NOI18N
        transLutWordsComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
        transLutWordsPanel.add(transLutWordsComboBox);

        editTransLutWordButton.setToolTipText(i18n.translate("Edit translation search key")); // NOI18N
        editTransLutWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editTransLutWordButtonActionPerformed(evt);
            }
        });
        transLutWordsPanel.add(editTransLutWordButton);

        addTransLutWordButton.setToolTipText(i18n.translate("Add new translation search key")); // NOI18N
        addTransLutWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTransLutWordButtonActionPerformed(evt);
            }
        });
        transLutWordsPanel.add(addTransLutWordButton);

        removeTransLutWordButton.setToolTipText(i18n.translate("Remove translation search key")); // NOI18N
        removeTransLutWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTransLutWordButtonActionPerformed(evt);
            }
        });
        transLutWordsPanel.add(removeTransLutWordButton);

        inputPanel.add(transLutWordsPanel);

        java.awt.FlowLayout flowLayout4 = new java.awt.FlowLayout(0);
        flowLayout4.setAlignOnBaseline(true);
        wordStatusPanel.setLayout(flowLayout4);

        wordStatusLabel.setText(i18n.translate("Word status:")); // NOI18N
        wordStatusLabel.setToolTipText(i18n.translate("Select word status")); // NOI18N
        wordStatusLabel.setPreferredSize(new java.awt.Dimension(140, 19));
        wordStatusPanel.add(wordStatusLabel);

        wordStatusComboBox.setToolTipText(i18n.translate("Select word status")); // NOI18N
        wordStatusComboBox.setPreferredSize(new java.awt.Dimension(220, 24));
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
        setBounds((screenSize.width-707)/2, (screenSize.height-434)/2, 707, 434);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(IconStock.Cancel);
    }//GEN-LAST:event_closeDialog

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if ("".equals(wordField.getText())) {
            wordField.setBackground(java.awt.Color.yellow);
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

    private void languageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_languageComboBoxActionPerformed
        util.Language language = (util.Language) languageComboBox.getSelectedItem();
        util.Language transLanguage = (util.Language) transLanguageComboBox.getSelectedItem();
        javax.swing.DefaultComboBoxModel<util.Language> transLanguageModel
                = (javax.swing.DefaultComboBoxModel<util.Language>) transLanguageComboBox.getModel();

        transLanguageModel.removeAllElements();
        for (util.Language defLanguage : util.Language.getValues()) {
            if (language != defLanguage) {
                transLanguageModel.addElement(defLanguage);
            }
        }
        transLanguageComboBox.setSelectedItem(transLanguage);
    }//GEN-LAST:event_languageComboBoxActionPerformed

    private void swapLanguageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swapLanguageButtonActionPerformed
        javax.swing.DefaultComboBoxModel<util.Language> languageModel
                = (javax.swing.DefaultComboBoxModel<util.Language>) languageComboBox.getModel();
        languageComboBox.setModel(transLanguageComboBox.getModel());
        transLanguageComboBox.setModel(languageModel);
    }//GEN-LAST:event_swapLanguageButtonActionPerformed

    private void transLanguageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transLanguageComboBoxActionPerformed
        util.Language transLanguage = (util.Language) transLanguageComboBox.getSelectedItem();
        util.Language language = (util.Language) languageComboBox.getSelectedItem();
        javax.swing.DefaultComboBoxModel<util.Language> languageModel
                = (javax.swing.DefaultComboBoxModel<util.Language>) languageComboBox.getModel();

        languageModel.removeAllElements();
        for (util.Language defLanguage : util.Language.getValues()) {
            if (transLanguage != defLanguage) {
                languageModel.addElement(defLanguage);
            }
        }
        languageComboBox.setSelectedItem(language);
    }//GEN-LAST:event_transLanguageComboBoxActionPerformed

    private void editTranslationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTranslationButtonActionPerformed
        editTranslation(
                i18n.translate("Edit translation"),
                this.translationsComboBox);
    }//GEN-LAST:event_editTranslationButtonActionPerformed

    private void addTranslationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTranslationButtonActionPerformed
        addTranslation(
                i18n.translate("Add new translation"),
                this.translationsComboBox,
                this.editTranslationButton,
                this.removeTranslationButton);
    }//GEN-LAST:event_addTranslationButtonActionPerformed

    private void removeTranslationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTranslationButtonActionPerformed
        removeTranslation(
                this.translationsComboBox,
                this.editTranslationButton,
                this.removeTranslationButton);
    }//GEN-LAST:event_removeTranslationButtonActionPerformed

    private void editSynonymeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSynonymeButtonActionPerformed
        editString(
                i18n.translate("Edit synonyme"), i18n.translate("Synonyme:"),
                this.synonymesComboBox);
    }//GEN-LAST:event_editSynonymeButtonActionPerformed

    private void addSynonymeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSynonymeButtonActionPerformed
        addString(
                i18n.translate("Add new synonyme"), i18n.translate("Synonyme:"),
                this.synonymesComboBox,
                this.editSynonymeButton,
                this.removeSynonymeButton);
    }//GEN-LAST:event_addSynonymeButtonActionPerformed

    private void removeSynonymeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSynonymeButtonActionPerformed
        removeString(
                this.synonymesComboBox,
                this.editSynonymeButton,
                this.removeSynonymeButton);
    }//GEN-LAST:event_removeSynonymeButtonActionPerformed

    private void editOppositeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOppositeButtonActionPerformed
        editString(
                i18n.translate("Edit opposite"), i18n.translate("Opposite:"),
                this.oppositesComboBox);
    }//GEN-LAST:event_editOppositeButtonActionPerformed

    private void addOppositeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOppositeButtonActionPerformed
        addString(
                i18n.translate("Add new opposite"), i18n.translate("Opposite:"),
                this.oppositesComboBox,
                this.editOppositeButton,
                this.removeOppositeButton);
    }//GEN-LAST:event_addOppositeButtonActionPerformed

    private void removeOppositeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOppositeButtonActionPerformed
        removeString(
                this.oppositesComboBox,
                this.editOppositeButton,
                this.removeOppositeButton);
    }//GEN-LAST:event_removeOppositeButtonActionPerformed

    private void editLutWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLutWordButtonActionPerformed
        editString(
                i18n.translate("Edit search key"), i18n.translate("Search key:"),
                this.lutWordsComboBox);
    }//GEN-LAST:event_editLutWordButtonActionPerformed

    private void addLutWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLutWordButtonActionPerformed
        addString(
                i18n.translate("Add new search key"), i18n.translate("Search key:"),
                this.lutWordsComboBox,
                this.editLutWordButton,
                this.removeLutWordButton);
    }//GEN-LAST:event_addLutWordButtonActionPerformed

    private void removeLutWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeLutWordButtonActionPerformed
        removeString(
                this.lutWordsComboBox,
                this.editLutWordButton,
                this.removeLutWordButton);
    }//GEN-LAST:event_removeLutWordButtonActionPerformed

    private void editTransLutWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editTransLutWordButtonActionPerformed
        editString(
                i18n.translate("Edit translation search key"), i18n.translate("T. search key:"),
                this.transLutWordsComboBox);
    }//GEN-LAST:event_editTransLutWordButtonActionPerformed

    private void addTransLutWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTransLutWordButtonActionPerformed
        addString(
                i18n.translate("Add new translation search key"), i18n.translate("T. search key:"),
                this.transLutWordsComboBox,
                this.editTransLutWordButton,
                this.removeTransLutWordButton);
    }//GEN-LAST:event_addTransLutWordButtonActionPerformed

    private void removeTransLutWordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTransLutWordButtonActionPerformed
        removeString(
                this.transLutWordsComboBox,
                this.editTransLutWordButton,
                this.removeTransLutWordButton);
    }//GEN-LAST:event_removeTransLutWordButtonActionPerformed

    private void wordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordFieldFocusGained
        if (wordField.getBackground() != java.awt.Color.white) {
            wordField.setBackground(java.awt.Color.white);
        }
    }//GEN-LAST:event_wordFieldFocusGained

    private void doClose(IconStock retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addLutWordButton;
    private javax.swing.JButton addOppositeButton;
    private javax.swing.JButton addSynonymeButton;
    private javax.swing.JButton addTransLutWordButton;
    private javax.swing.JButton addTranslationButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel dictionaryLabel;
    private javax.swing.JPanel dictionaryPanel;
    private javax.swing.JButton editLutWordButton;
    private javax.swing.JButton editOppositeButton;
    private javax.swing.JButton editSynonymeButton;
    private javax.swing.JButton editTransLutWordButton;
    private javax.swing.JButton editTranslationButton;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JButton hintButton;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JComboBox<util.Language> languageComboBox;
    private javax.swing.JComboBox<String> lutWordsComboBox;
    private javax.swing.JLabel lutWordsLabel;
    private javax.swing.JPanel lutWordsPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> oppositesComboBox;
    private javax.swing.JLabel oppositesLabel;
    private javax.swing.JPanel oppositesPanel;
    private javax.swing.JButton removeLutWordButton;
    private javax.swing.JButton removeOppositeButton;
    private javax.swing.JButton removeSynonymeButton;
    private javax.swing.JButton removeTransLutWordButton;
    private javax.swing.JButton removeTranslationButton;
    private javax.swing.JPanel sepPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton swapLanguageButton;
    private javax.swing.JComboBox<String> synonymesComboBox;
    private javax.swing.JLabel synonymesLabel;
    private javax.swing.JPanel synonymesPanel;
    private javax.swing.JComboBox<util.Language> transLanguageComboBox;
    private javax.swing.JComboBox<String> transLutWordsComboBox;
    private javax.swing.JLabel transLutWordsLabel;
    private javax.swing.JPanel transLutWordsPanel;
    private javax.swing.JComboBox<util.WordTranslation> translationsComboBox;
    private javax.swing.JLabel translationsLabel;
    private javax.swing.JPanel translationsPanel;
    private javax.swing.JComboBox<util.WordClass> wordClassComboBox;
    private javax.swing.JLabel wordClassLabel;
    private javax.swing.JPanel wordClassPanel;
    private javax.swing.JTextField wordField;
    private javax.swing.JLabel wordLabel;
    private javax.swing.JPanel wordPanel;
    private javax.swing.JComboBox<util.WordStatus> wordStatusComboBox;
    private javax.swing.JLabel wordStatusLabel;
    private javax.swing.JPanel wordStatusPanel;
    // End of variables declaration//GEN-END:variables
    private IconStock returnStatus;
}
