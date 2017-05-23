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
public class WordsTable extends javax.swing.JTable {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private static final util.I18n i18n = new util.I18n(WordsTable.class);
    private static final String[] columnNames = {
        i18n.translate("Word"),
        i18n.translate("Dictionary"),
        i18n.translate("Status"),
        i18n.translate("Author"),
        i18n.translate("Update"),
        ""
    };
    public static final int Word = 0;
    public static final int Dictionary = 1;
    public static final int Status = 2;
    public static final int Author = 3;
    public static final int Update = 4;
    public static final int utilWord = 5;
    private boolean hasMoreWords;
    // </editor-fold>

    WordsTable() {
        super();

        setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, columnNames));

        getColumnModel().getColumn(utilWord).setMaxWidth(0);
        getColumnModel().getColumn(utilWord).setMinWidth(0);
        getColumnModel().getColumn(utilWord).setPreferredWidth(0);

        for (int i = 0; i < getColumnCount(); i++) {
            setDefaultRenderer(Object.class, new javax.swing.table.TableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                        Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    javax.swing.JLabel label = new javax.swing.JLabel((String) value);

                    util.WordStatus status = ((util.Word) table.getValueAt(row, utilWord)).getStatus();

                    if (status.getId() == util.WordStatus.Complete.getId()) {
                        label.setForeground(util.Color.DARK_ORANGE);
                    }
                    if (status.getId() == util.WordStatus.PublishComplete.getId()) {
                        label.setForeground(util.Color.BLUE);
                    }
                    if (status.getId() == util.WordStatus.PublishDraft.getId()) {
                        label.setForeground(util.Color.MAGENTA);
                    }
                    if ((status.getId() & util.WordStatus.Hidden.getId()) != 0) {
                        label.setForeground(util.Color.LIGHT_GRAY);
                    }

                    if (isSelected) {
                        label.setBackground(javax.swing.UIManager.getDefaults().getColor("Table.selectionBackground")); //NOI18N
                        if ((status.getId() & util.WordStatus.Hidden.getId()) != 0) {
                            label.setForeground(util.Color.WHITE);
                        }
                        if (status.getId() == util.WordStatus.Complete.getId()) {
                            label.setForeground(util.Color.RED);
                        }
                    } else {
                        label.setBackground(javax.swing.UIManager.getDefaults().getColor("Table.background")); //NOI18N
                    }
                    label.setOpaque(true);

                    label.setFont(new java.awt.Font(java.awt.Font.DIALOG, java.awt.Font.PLAIN, 12));
                    label.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                    return label;
                }
            });
        }
    }

    public static String getColumName(int columnIdx) {
        return columnNames[columnIdx];
    }

    public util.Word getSelectedWord() {
        return (util.Word) getValueAt(getSelectedRow(), utilWord);
    }

    public void removeSelectedWord() {
        this.removeRowSelectionInterval(getSelectedRow(), getSelectedRow());
    }

    public util.Words getWords() {
        util.Words words = new util.Words();
        words.setHasMoreWords(hasMoreWords);

        for (int i = 0; i < getModel().getRowCount(); i++) {
            words.add((util.Word) getValueAt(i, utilWord));
        }

        return words;
    }

    public void addWords(util.Words words) {
        hasMoreWords = words.hasMoreWords();

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) getModel();
        model.setRowCount(0);

        java.util.Iterator<util.Word> itr = words.iterator();
        while (itr.hasNext()) {
            util.Word word = itr.next();
            java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.MEDIUM, java.text.DateFormat.MEDIUM, java.util.Locale.getDefault());
            model.addRow(new Object[]{
                word.getWord(),
                word.getLanguage().getI18nString() + " - " + word.getTransLanguage().getI18nString(),
                word.getStatus().getI18nString(),
                word.getAuthor().getName(),
                df.format(word.getUpdateTime()),
                word
            });
        }

    }

    public void updateWords(util.SQLDatabase sqlDatabase)
            throws java.sql.SQLException {
        if (getModel().getRowCount() != 0) {
            addWords(sqlDatabase.getWords(getWords()));
        }
    }

    @Override
    public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {

        return super.prepareRenderer(renderer, row, column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {

        return true;
    }
}
