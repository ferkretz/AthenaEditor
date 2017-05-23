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
package util;

public class WordStatus {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private int id;
    private String string;
    private static int idI;
    private static java.util.ArrayList<WordStatus> values = new java.util.ArrayList<>();
    public static final WordStatus Any = preDefineExtra(0, "Any");
    public static final WordStatus Trash = preDefine(1, "Trash");
    public static final WordStatus Obsolete = preDefine(2, "Obsolete");
    public static final WordStatus Hidden = preDefineExtra(3, "Hidden");
    public static final WordStatus ExcludeHidden = preDefineExtra(28, "Exclude hidden");
    public static final WordStatus Draft = preDefine(4, "Draft");
    public static final WordStatus Complete = preDefine(8, "Complete");
    public static final WordStatus Publish = preDefineExtra(16, "Publish");
    public static final WordStatus PublishDraft = preDefine(20, "Draft (Publish)");
    public static final WordStatus PublishComplete = preDefine(24, "Complete (Publish)");
    private final I18n i18n = new I18n(this);
    // </editor-fold>

    private static WordStatus preDefine(String string) {
        WordStatus value = new WordStatus(idI, string);
        values.add(value);
        idI++;
        return value;
    }

    private static WordStatus preDefine(int id, String string) {
        idI = id + 1;
        WordStatus value = new WordStatus(id, string);
        values.add(value);
        return value;
    }

    private static WordStatus preDefineExtra(String string) {
        WordStatus value = new WordStatus(idI, string);
        idI++;
        return value;
    }

    private static WordStatus preDefineExtra(int id, String string) {
        idI = id + 1;
        return new WordStatus(id, string);
    }

    private WordStatus(int id, String string) {
        this.id = id;
        this.string = string;
    }

    public boolean equals(WordStatus values) {
        return id == values.id ? true : false;
    }

    static public WordStatus valueOf(int id) {
        for (WordStatus value : values) {
            if (value.id == id) {
                return value;
            }
        }
        return null;
    }

    static public WordStatus valueOf(String string) {
        for (WordStatus value : values) {
            if (value.string.equals(string)) {
                return value;
            }
        }
        return null;
    }

    static public void add(int id, String string) {
        if (valueOf(string) == null) {
            values.add(0, new WordStatus(id, string));
        }
    }

    static public java.util.ArrayList<WordStatus> getValues() {
        java.util.ArrayList<WordStatus> retValues = new java.util.ArrayList<>();

        for (WordStatus value : values) {
            retValues.add(value);
        }

        return retValues;
    }

    static public java.util.ArrayList<WordStatus> getAllValues() {
        java.util.ArrayList<WordStatus> retValues = new java.util.ArrayList<>();

        retValues.add(WordStatus.Any);
        retValues.add(WordStatus.ExcludeHidden);
        retValues.add(WordStatus.Publish);
        retValues.add(WordStatus.Hidden);
        for (WordStatus value : values) {
            retValues.add(value);
        }

        return retValues;
    }

    public int getId() {
        return id;
    }

    public String getString() {
        return string;
    }

    public String getSQLString() {
        return string;
    }

    public String getI18nString() {
        return i18n.translate(string);
    }

}
