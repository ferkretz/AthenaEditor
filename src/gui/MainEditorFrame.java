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
public class MainEditorFrame extends javax.swing.JFrame {

    private util.SQLDatabase sqlDatabase;
    private util.SearchTerms searchTerms;
    private final util.I18n i18n = new util.I18n(this);
    private final util.I18n i18nError = new util.I18n("ErrorMessages");

    /**
     * Creates new form MainEditorFrame
     */
    public MainEditorFrame() {

        initComponents();

        // *** not implemented yet ***
        hintButton.setVisible(false);
        helpToolBarSeparator.setVisible(false);
        helpButton.setVisible(false);
        helpMenuItem.setVisible(false);

        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/resource/image/athena_Icon.png")).getImage()); // NOI18N
        constructToolBar();
        constructPopupMenu();
        constructWordsTable();

        String title = i18n.translate("Dictionary Editor");
        setTitle(title + " - " + util.Settings.AppName.getValue()); // NOI18N

        try {
            sqlDatabase = new util.SQLDatabase();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
            System.exit(-1);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            new ExceptionDialog(i18nError.translate("LoadMySQLDriver"), ex, this).setVisible(true); //NOI18N
            System.exit(-1);
        }
    }

    private void constructToolBar() {
        updateToolBar(true);
    }

    private void updateToolBar() {
        updateToolBar(false);
    }

    private void updateToolBar(boolean forceCheckBox) {
        boolean isFileToolBar = Boolean.parseBoolean(util.Settings.TBShowFile.getValue());
        boolean isEditToolBar = Boolean.parseBoolean(util.Settings.TBShowEdit.getValue());
        boolean isHelpToolBar = Boolean.parseBoolean(util.Settings.TBShowHelp.getValue());

        if (isFileToolBar | isEditToolBar | isHelpToolBar) {
            fileToolBar.setVisible(isFileToolBar);
            editToolBar.setVisible(isEditToolBar);
            helpToolBar.setVisible(isHelpToolBar);
            if (forceCheckBox) {
                fileToolBarCheckBoxMenuItem.setSelected(isFileToolBar);
                editToolBarCheckBoxMenuItem.setSelected(isEditToolBar);
                helpToolBarCheckBoxMenuItem.setSelected(isHelpToolBar);
            }
            toolBarPanel.setVisible(true);
        } else {
            toolBarPanel.setVisible(false);
        }
    }

    private void constructPopupMenu() {
        for (util.WordStatus status : util.WordStatus.getValues()) {
            javax.swing.JMenuItem statusMenuItem = new javax.swing.JMenuItem();

            statusMenuItem.setText(status.getI18nString());
            statusMenuItem.setName(status.getString());
            statusMenuItem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    statusMenuItemActionPerformed(evt);
                }
            });

            statusPopupMenu.add(statusMenuItem);
        }
    }

    private void constructWordsTable() {
        javax.swing.ListSelectionModel model = wordsTable.getSelectionModel();

        model.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent lse) {
                wordsTableValueChanged(lse);
            }
        });

    }

    private void updateWordsTable()
            throws java.sql.SQLException {
        updateWordsTable(false);
    }

    private void updateWordsTable(boolean withSearchTerms)
            throws java.sql.SQLException {
        if (withSearchTerms) {
            ((WordsTable) wordsTable).addWords(sqlDatabase.search(searchTerms));
        } else {
            ((WordsTable) wordsTable).updateWords(sqlDatabase);
        }

        if (((WordsTable) wordsTable).getWords().hasMoreWords()) {
            this.nextButton.setEnabled(true);
        } else {
            this.nextButton.setEnabled(false);
        }

        if (this.searchTerms.getOffset() > 0) {
            this.previousButton.setEnabled(true);
        } else {
            this.previousButton.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wordPopupMenu = new javax.swing.JPopupMenu();
        newRevisionPopupMenuItem = new MenuItem(IconStock.Add2);
        editWordPopupMenuItem = new MenuItem(IconStock.Edit);
        trashWordPopupMenuItem = new MenuItem(IconStock.Trash);
        removeWordPopupMenuItem = new MenuItem(IconStock.Remove);
        previewPopupMenuItem = new MenuItem(IconStock.Preview);
        statusPopupMenu = new javax.swing.JPopupMenu();
        toolBarPanel = new javax.swing.JPanel();
        fileToolBar = new javax.swing.JToolBar();
        settingsButton = new ToolButton(IconStock.Settings);
        fileToolBarSeparator = new javax.swing.JToolBar.Separator();
        exitButton = new ToolButton(IconStock.Exit);
        editToolBar = new javax.swing.JToolBar();
        addWordButton = new ToolButton(IconStock.Add);
        newRevisionButton = new ToolButton(IconStock.Add2);
        editWordButton = new ToolButton(IconStock.Edit);
        trashWordButton = new ToolButton(IconStock.Trash);
        removeWordButton = new ToolButton(IconStock.Remove);
        previewButton = new ToolButton(IconStock.Preview);
        editToolBarSeparator = new javax.swing.JToolBar.Separator();
        listWordsButton = new ToolButton(IconStock.List);
        listRevisionsButton = new ToolButton(IconStock.List2);
        searchButton = new ToolButton(IconStock.Search);
        helpToolBar = new javax.swing.JToolBar();
        helpButton = new ToolButton(IconStock.Help);
        helpToolBarSeparator = new javax.swing.JToolBar.Separator();
        aboutButton = new ToolButton(IconStock.About);
        wordsScrollPanel = new javax.swing.JScrollPane();
        wordsTable = new WordsTable();
        buttonPanel = new javax.swing.JPanel();
        helpPanel = new javax.swing.JPanel();
        hintButton = new Button(IconStock.Hint);
        controlPanel = new javax.swing.JPanel();
        previousButton = new Button(IconStock.Previous);
        nextButton = new Button(IconStock.Next);
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        settingsMenuItem = new MenuItem(IconStock.Settings);
        exitSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new MenuItem(IconStock.Exit);
        editMenu = new javax.swing.JMenu();
        addWordMenuItem = new MenuItem(IconStock.Add);
        newRevisionMenuItem = new MenuItem(IconStock.Add2);
        editWordMenuItem = new MenuItem(IconStock.Edit);
        trashWordMenuItem = new MenuItem(IconStock.Trash);
        removeWordMenuItem = new MenuItem(IconStock.Remove);
        previewMenuItem = new MenuItem(IconStock.Preview);
        editSeparator = new javax.swing.JPopupMenu.Separator();
        listWordsMenuItem = new MenuItem(IconStock.List);
        listRevisionsMenuItem = new MenuItem(IconStock.List2);
        searchMenuItem = new MenuItem(IconStock.Search);
        viewMenu = new javax.swing.JMenu();
        fileToolBarCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        editToolBarCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpToolBarCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new MenuItem(IconStock.Help);
        aboutMenuItem = new MenuItem(IconStock.About);

        newRevisionPopupMenuItem.setText(i18n.translate("New revision")); // NOI18N
        newRevisionPopupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRevisionActionPerformed(evt);
            }
        });
        wordPopupMenu.add(newRevisionPopupMenuItem);

        editWordPopupMenuItem.setText(i18n.translate("Edit word")); // NOI18N
        editWordPopupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editWordActionPerformed(evt);
            }
        });
        wordPopupMenu.add(editWordPopupMenuItem);

        trashWordPopupMenuItem.setText(i18n.translate("Trash word")); // NOI18N
        trashWordPopupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trashWordActionPerformed(evt);
            }
        });
        wordPopupMenu.add(trashWordPopupMenuItem);

        removeWordPopupMenuItem.setText(i18n.translate("Remove word")); // NOI18N
        removeWordPopupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWordActionPerformed(evt);
            }
        });
        wordPopupMenu.add(removeWordPopupMenuItem);

        previewPopupMenuItem.setText(i18n.translate("Preview")); // NOI18N
        previewPopupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewActionPerformed(evt);
            }
        });
        wordPopupMenu.add(previewPopupMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));
        setName("MainEditorFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        toolBarPanel.setLayout(new java.awt.FlowLayout(0));

        fileToolBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        fileToolBar.setFloatable(false);
        fileToolBar.setRollover(true);

        settingsButton.setToolTipText(i18n.translate("Settings")); // NOI18N
        settingsButton.setFocusable(false);
        settingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        settingsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        fileToolBar.add(settingsButton);
        fileToolBar.add(fileToolBarSeparator);

        exitButton.setToolTipText(i18n.translate("Exit")); // NOI18N
        exitButton.setFocusable(false);
        exitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        fileToolBar.add(exitButton);

        toolBarPanel.add(fileToolBar);

        editToolBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        editToolBar.setFloatable(false);
        editToolBar.setRollover(true);

        addWordButton.setToolTipText(i18n.translate("Add new word")); // NOI18N
        addWordButton.setFocusable(false);
        addWordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addWordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWordActionPerformed(evt);
            }
        });
        editToolBar.add(addWordButton);

        newRevisionButton.setToolTipText(i18n.translate("Add new revision")); // NOI18N
        newRevisionButton.setEnabled(false);
        newRevisionButton.setFocusable(false);
        newRevisionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newRevisionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newRevisionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRevisionActionPerformed(evt);
            }
        });
        editToolBar.add(newRevisionButton);

        editWordButton.setToolTipText(i18n.translate("Edit word")); // NOI18N
        editWordButton.setEnabled(false);
        editWordButton.setFocusable(false);
        editWordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editWordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editWordActionPerformed(evt);
            }
        });
        editToolBar.add(editWordButton);

        trashWordButton.setToolTipText(i18n.translate("Trash word")); // NOI18N
        trashWordButton.setEnabled(false);
        trashWordButton.setFocusable(false);
        trashWordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        trashWordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        trashWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trashWordActionPerformed(evt);
            }
        });
        editToolBar.add(trashWordButton);

        removeWordButton.setToolTipText(i18n.translate("Remove word")); // NOI18N
        removeWordButton.setEnabled(false);
        removeWordButton.setFocusable(false);
        removeWordButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeWordButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeWordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWordActionPerformed(evt);
            }
        });
        editToolBar.add(removeWordButton);

        previewButton.setToolTipText(i18n.translate("Preview")); // NOI18N
        previewButton.setEnabled(false);
        previewButton.setFocusable(false);
        previewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        previewButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        previewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewActionPerformed(evt);
            }
        });
        editToolBar.add(previewButton);
        editToolBar.add(editToolBarSeparator);

        listWordsButton.setToolTipText(i18n.translate("List words")); // NOI18N
        listWordsButton.setFocusable(false);
        listWordsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        listWordsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        listWordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listWordsActionPerformed(evt);
            }
        });
        editToolBar.add(listWordsButton);

        listRevisionsButton.setToolTipText(i18n.translate("List revisions")); // NOI18N
        listRevisionsButton.setEnabled(false);
        listRevisionsButton.setFocusable(false);
        listRevisionsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        listRevisionsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        listRevisionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listRevisionsActionPerformed(evt);
            }
        });
        editToolBar.add(listRevisionsButton);

        searchButton.setToolTipText(i18n.translate("Search for words")); // NOI18N
        searchButton.setFocusable(false);
        searchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        editToolBar.add(searchButton);

        toolBarPanel.add(editToolBar);

        helpToolBar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)));
        helpToolBar.setFloatable(false);
        helpToolBar.setRollover(true);

        helpButton.setToolTipText(i18n.translate("Help")); // NOI18N
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpToolBar.add(helpButton);
        helpToolBar.add(helpToolBarSeparator);

        aboutButton.setToolTipText(i18n.translate("About")); // NOI18N
        aboutButton.setFocusable(false);
        aboutButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aboutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        helpToolBar.add(aboutButton);

        toolBarPanel.add(helpToolBar);

        getContentPane().add(toolBarPanel);

        wordsTable.setAutoCreateRowSorter(true);
        wordsTable.setDragEnabled(true);
        wordsTable.setRowHeight(18);
        wordsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        wordsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wordsTableMouseClicked(evt);
            }
        });
        wordsScrollPanel.setViewportView(wordsTable);

        getContentPane().add(wordsScrollPanel);

        buttonPanel.setLayout(new javax.swing.BoxLayout(buttonPanel, javax.swing.BoxLayout.LINE_AXIS));

        helpPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        helpPanel.setLayout(new java.awt.FlowLayout(0, 5, 0));

        hintButton.setActionCommand("hintButton");
        helpPanel.add(hintButton);

        buttonPanel.add(helpPanel);

        controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setLayout(new java.awt.FlowLayout(2, 5, 0));

        previousButton.setActionCommand("okButton");
        previousButton.setEnabled(false);
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });
        controlPanel.add(previousButton);

        nextButton.setActionCommand("cancelButton");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        controlPanel.add(nextButton);

        buttonPanel.add(controlPanel);

        getContentPane().add(buttonPanel);

        fileMenu.setText(i18n.translate("File")); // NOI18N

        settingsMenuItem.setText(i18n.translate("Settings")); // NOI18N
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        fileMenu.add(settingsMenuItem);
        fileMenu.add(exitSeparator);

        exitMenuItem.setText(i18n.translate("Exit")); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText(i18n.translate("Edit")); // NOI18N

        addWordMenuItem.setText(i18n.translate("Add word")); // NOI18N
        addWordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWordActionPerformed(evt);
            }
        });
        editMenu.add(addWordMenuItem);

        newRevisionMenuItem.setText(i18n.translate("New revision")); // NOI18N
        newRevisionMenuItem.setEnabled(false);
        newRevisionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRevisionActionPerformed(evt);
            }
        });
        editMenu.add(newRevisionMenuItem);

        editWordMenuItem.setText(i18n.translate("Edit word")); // NOI18N
        editWordMenuItem.setEnabled(false);
        editWordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editWordActionPerformed(evt);
            }
        });
        editMenu.add(editWordMenuItem);

        trashWordMenuItem.setText(i18n.translate("Trash word")); // NOI18N
        trashWordMenuItem.setEnabled(false);
        trashWordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trashWordActionPerformed(evt);
            }
        });
        editMenu.add(trashWordMenuItem);

        removeWordMenuItem.setText(i18n.translate("Remove word")); // NOI18N
        removeWordMenuItem.setEnabled(false);
        removeWordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWordActionPerformed(evt);
            }
        });
        editMenu.add(removeWordMenuItem);

        previewMenuItem.setText(i18n.translate("Preview")); // NOI18N
        previewMenuItem.setEnabled(false);
        previewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewActionPerformed(evt);
            }
        });
        editMenu.add(previewMenuItem);
        editMenu.add(editSeparator);

        listWordsMenuItem.setText(i18n.translate("List words")); // NOI18N
        listWordsMenuItem.setToolTipText("");
        listWordsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listWordsActionPerformed(evt);
            }
        });
        editMenu.add(listWordsMenuItem);

        listRevisionsMenuItem.setText(i18n.translate("List revisions")); // NOI18N
        listRevisionsMenuItem.setToolTipText("");
        listRevisionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listRevisionsActionPerformed(evt);
            }
        });
        editMenu.add(listRevisionsMenuItem);

        searchMenuItem.setText(i18n.translate("Search")); // NOI18N
        searchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        editMenu.add(searchMenuItem);

        menuBar.add(editMenu);

        viewMenu.setText(i18n.translate("View")); // NOI18N

        fileToolBarCheckBoxMenuItem.setText(i18n.translate("File toolbar")); // NOI18N
        fileToolBarCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileToolBarCheckBoxMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(fileToolBarCheckBoxMenuItem);

        editToolBarCheckBoxMenuItem.setText(i18n.translate("Edit toolbar")); // NOI18N
        editToolBarCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editToolBarCheckBoxMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(editToolBarCheckBoxMenuItem);

        helpToolBarCheckBoxMenuItem.setText(i18n.translate("Help toolbar")); // NOI18N
        helpToolBarCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpToolBarCheckBoxMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(helpToolBarCheckBoxMenuItem);

        menuBar.add(viewMenu);

        helpMenu.setText(i18n.translate("Help")); // NOI18N

        helpMenuItem.setText(i18n.translate("Help")); // NOI18N
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        aboutMenuItem.setText(i18n.translate("About")); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents

    private void wordsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wordsTableMouseClicked
        if (WordsTable.getColumName(WordsTable.Word).
                equals(wordsTable.getColumnName(wordsTable.getSelectedColumn()))) {

            wordPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }

        if (WordsTable.getColumName(WordsTable.Status).
                equals(wordsTable.getColumnName(wordsTable.getSelectedColumn()))) {
            statusPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_wordsTableMouseClicked

    private void statusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
        String itemName = ((javax.swing.JMenuItem) evt.getSource()).getName();
        util.Word selectedWord = ((WordsTable) wordsTable).getSelectedWord();

        selectedWord.setStatus(util.WordStatus.valueOf(itemName));

        try {
            sqlDatabase.updateWord(selectedWord);
            updateWordsTable();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }

    private void wordsTableValueChanged(javax.swing.event.ListSelectionEvent lse) {
        if (wordsTable.getSelectedRow() == -1) {
            this.newRevisionMenuItem.setEnabled(false);
            this.editWordMenuItem.setEnabled(false);
            this.trashWordMenuItem.setEnabled(false);
            this.removeWordMenuItem.setEnabled(false);
            this.previewMenuItem.setEnabled(false);
            this.listRevisionsMenuItem.setEnabled(false);
            this.newRevisionButton.setEnabled(false);
            this.editWordButton.setEnabled(false);
            this.trashWordButton.setEnabled(false);
            this.removeWordButton.setEnabled(false);
            this.previewButton.setEnabled(false);
            this.listRevisionsButton.setEnabled(false);
        } else {
            this.newRevisionMenuItem.setEnabled(true);
            this.editWordMenuItem.setEnabled(true);
            this.trashWordMenuItem.setEnabled(true);
            this.removeWordMenuItem.setEnabled(true);
            this.previewMenuItem.setEnabled(true);
            this.listRevisionsMenuItem.setEnabled(true);
            this.newRevisionButton.setEnabled(true);
            this.editWordButton.setEnabled(true);
            this.trashWordButton.setEnabled(true);
            this.removeWordButton.setEnabled(true);
            this.previewButton.setEnabled(true);
            this.listRevisionsButton.setEnabled(true);
        }
    }

    private void editWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editWordActionPerformed
        WordDialog dialog = new WordDialog(this, i18n.translate("Edit word"), ((WordsTable) this.wordsTable).getSelectedWord());
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            try {
                sqlDatabase.updateWord(dialog.getWord());
                updateWordsTable();
            } catch (java.sql.SQLException ex) {
                new ExceptionDialog(
                        i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                        ex,
                        this).setVisible(true);
            }
        }
    }//GEN-LAST:event_editWordActionPerformed

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        SettingsDialog dialog = new SettingsDialog(this);
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            updateToolBar(true);
            try {
                util.Settings.saveToFile();
            } catch (java.io.IOException ex) {
                new ExceptionDialog(
                        i18nError.translatef("ConfigSave", util.Settings.ConfigPath.getValue()), //NOI18N
                        ex,
                        this).setVisible(true);
            }
        } else if (dialog.getReturnStatus() == IconStock.Recovery) {
            doClose();
            new SetupWizardFrame(SetupWizardFrame.RecoveryCommand).setVisible(true);
        } else if (dialog.getReturnStatus() == IconStock.Uninstall) {
            doClose();
            new SetupWizardFrame(SetupWizardFrame.UnInstallCommand).setVisible(true);
        }
    }//GEN-LAST:event_settingsActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        new AboutDialog(this).setVisible(true);
    }//GEN-LAST:event_aboutActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        doClose();
    }//GEN-LAST:event_exitActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        doClose();
    }//GEN-LAST:event_formWindowClosing

    private void listWordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listWordsActionPerformed
        try {
            searchTerms = new util.SearchTerms();
            //searchTerms.setWordStatus(util.WordStatus.Any);
            updateWordsTable(true);
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_listWordsActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        try {
            SearchDialog dialog = new SearchDialog(this, sqlDatabase.getMembers());
            dialog.setVisible(true);
            if (dialog.getReturnStatus() == IconStock.Ok) {
                searchTerms = dialog.getSearchTerms();
                updateWordsTable(true);
            }
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_searchActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        this.searchTerms.setOffset(
                this.searchTerms.getOffset()
                - Integer.parseInt(util.Settings.ViewRecordLimit.getValue()));
        try {
            updateWordsTable(true);
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        this.searchTerms.setOffset(
                this.searchTerms.getOffset()
                + Integer.parseInt(util.Settings.ViewRecordLimit.getValue()));
        try {
            updateWordsTable(true);
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void newRevisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRevisionActionPerformed
        //util.Word word = new util.Word();
        //word.setGroupId(((WordsTable) this.wordsTable).getSelectedWord().getGroupId());
        util.Word word = new util.Word(((WordsTable) this.wordsTable).getSelectedWord());

        WordDialog dialog = new WordDialog(this, i18n.translate("New revision"), word);
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            try {
                sqlDatabase.insertWord(dialog.getWord());
                updateWordsTable();
            } catch (java.sql.SQLException ex) {
                new ExceptionDialog(
                        i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                        ex,
                        this).setVisible(true);
            }
        }
    }//GEN-LAST:event_newRevisionActionPerformed

    private void removeWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeWordActionPerformed
        try {
            sqlDatabase.deleteWord(((WordsTable) wordsTable).getSelectedWord());
            ((WordsTable) wordsTable).removeSelectedWord();
            updateWordsTable();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_removeWordActionPerformed

    private void fileToolBarCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileToolBarCheckBoxMenuItemActionPerformed
        util.Settings.TBShowFile.setBooleanValue(fileToolBarCheckBoxMenuItem.getState());
        updateToolBar();
    }//GEN-LAST:event_fileToolBarCheckBoxMenuItemActionPerformed

    private void editToolBarCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editToolBarCheckBoxMenuItemActionPerformed
        util.Settings.TBShowEdit.setBooleanValue(editToolBarCheckBoxMenuItem.getState());
        updateToolBar();
    }//GEN-LAST:event_editToolBarCheckBoxMenuItemActionPerformed

    private void helpToolBarCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpToolBarCheckBoxMenuItemActionPerformed
        util.Settings.TBShowHelp.setBooleanValue(helpToolBarCheckBoxMenuItem.getState());
        updateToolBar();
    }//GEN-LAST:event_helpToolBarCheckBoxMenuItemActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpActionPerformed

    private void addWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWordActionPerformed
        WordDialog dialog = new WordDialog(this, i18n.translate("New word"), new util.Word());
        dialog.setVisible(true);

        if (dialog.getReturnStatus() == IconStock.Ok) {
            try {
                sqlDatabase.insertWord(dialog.getWord());
            } catch (java.sql.SQLException ex) {
                new ExceptionDialog(
                        i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                        ex,
                        this).setVisible(true);
            }
        }
    }//GEN-LAST:event_addWordActionPerformed

    private void trashWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trashWordActionPerformed
        try {
            sqlDatabase.trashWord(((WordsTable) wordsTable).getSelectedWord());
            updateWordsTable();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_trashWordActionPerformed

    private void listRevisionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listRevisionsActionPerformed
        try {
            searchTerms = new util.SearchTerms();
            searchTerms.setWordStatus(util.WordStatus.Any);
            searchTerms.setGroupId(((WordsTable) wordsTable).getSelectedWord().getGroupId());
            updateWordsTable(true);
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(
                    i18nError.translatef("DatabaseConnect", util.Settings.SQLDBName.getValue()), //NOI18N
                    ex,
                    this).setVisible(true);
        }
    }//GEN-LAST:event_listRevisionsActionPerformed

    private void previewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewActionPerformed
        new PreviewDialog(this, ((WordsTable) wordsTable).getSelectedWord()).setVisible(true);
    }//GEN-LAST:event_previewActionPerformed

    private void doClose() {
        try {
            sqlDatabase.close();
        } catch (java.sql.SQLException ex) {
            new ExceptionDialog(ex, this).setVisible(true);
        }
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addWordButton;
    private javax.swing.JMenuItem addWordMenuItem;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JPopupMenu.Separator editSeparator;
    private javax.swing.JToolBar editToolBar;
    private javax.swing.JCheckBoxMenuItem editToolBarCheckBoxMenuItem;
    private javax.swing.JToolBar.Separator editToolBarSeparator;
    private javax.swing.JButton editWordButton;
    private javax.swing.JMenuItem editWordMenuItem;
    private javax.swing.JMenuItem editWordPopupMenuItem;
    private javax.swing.JButton exitButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JPopupMenu.Separator exitSeparator;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JToolBar fileToolBar;
    private javax.swing.JCheckBoxMenuItem fileToolBarCheckBoxMenuItem;
    private javax.swing.JToolBar.Separator fileToolBarSeparator;
    private javax.swing.JButton helpButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JToolBar helpToolBar;
    private javax.swing.JCheckBoxMenuItem helpToolBarCheckBoxMenuItem;
    private javax.swing.JToolBar.Separator helpToolBarSeparator;
    private javax.swing.JButton hintButton;
    private javax.swing.JButton listRevisionsButton;
    private javax.swing.JMenuItem listRevisionsMenuItem;
    private javax.swing.JButton listWordsButton;
    private javax.swing.JMenuItem listWordsMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton newRevisionButton;
    private javax.swing.JMenuItem newRevisionMenuItem;
    private javax.swing.JMenuItem newRevisionPopupMenuItem;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previewButton;
    private javax.swing.JMenuItem previewMenuItem;
    private javax.swing.JMenuItem previewPopupMenuItem;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton removeWordButton;
    private javax.swing.JMenuItem removeWordMenuItem;
    private javax.swing.JMenuItem removeWordPopupMenuItem;
    private javax.swing.JButton searchButton;
    private javax.swing.JMenuItem searchMenuItem;
    private javax.swing.JButton settingsButton;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JPopupMenu statusPopupMenu;
    private javax.swing.JPanel toolBarPanel;
    private javax.swing.JButton trashWordButton;
    private javax.swing.JMenuItem trashWordMenuItem;
    private javax.swing.JMenuItem trashWordPopupMenuItem;
    private javax.swing.JMenu viewMenu;
    private javax.swing.JPopupMenu wordPopupMenu;
    private javax.swing.JScrollPane wordsScrollPanel;
    private javax.swing.JTable wordsTable;
    // End of variables declaration//GEN-END:variables
}
