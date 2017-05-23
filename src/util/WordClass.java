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

public class WordClass {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private int id;
    private String string;
    private static int idI;
    private static java.util.ArrayList<WordClass> values = new java.util.ArrayList<>();
    public static final WordClass Any = preDefineExtra(0, "Any");
    public static final WordClass Adjective = preDefine(10000, "Adjective");
    //public static final WordClass Adposition = preDefine("Adposition");
    public static final WordClass Adverb = preDefine("Adverb");
    public static final WordClass AuxiliaryVerb = preDefine("Auxiliary verb");
    public static final WordClass Clitic = preDefine("Clitic");
    public static final WordClass Conjunction = preDefine("Conjunction");
    public static final WordClass Contraction = preDefine("Contraction");
    //public static final WordClass Coverb = preDefine("Coverb");
    //public static final WordClass Determiner = preDefine("Determiner");
    //public static final WordClass Interjection = preDefine("Interjection");
    public static final WordClass Measure = preDefine("Measure");
    public static final WordClass Noun = preDefine("Noun");
    //public static final WordClass Preverb = preDefine("Preverb");
    public static final WordClass Pronoun = preDefine("Pronoun");
    public static final WordClass Verb = preDefine("Verb");
    public static final WordClass Unknown = preDefine("Unknown");
    private final I18n i18n = new I18n(this);
    // </editor-fold>

    private static WordClass preDefine(String string) {
        WordClass value = new WordClass(idI, string);
        values.add(value);
        idI++;
        return value;
    }

    private static WordClass preDefine(int id, String string) {
        idI = id + 1;
        WordClass value = new WordClass(id, string);
        values.add(value);
        return value;
    }

    private static WordClass preDefineExtra(String string) {
        WordClass value = new WordClass(idI, string);
        idI++;
        return value;
    }

    private static WordClass preDefineExtra(int id, String string) {
        idI = id + 1;
        return new WordClass(id, string);
    }

    private WordClass(int id, String string) {
        this.id = id;
        this.string = string;
    }

    public boolean equals(WordClass values) {
        return id == values.id ? true : false;
    }

    static public WordClass valueOf(int id) {
        for (WordClass value : values) {
            if (value.id == id) {
                return value;
            }
        }
        return null;
    }

    static public WordClass valueOf(String string) {
        for (WordClass value : values) {
            if (value.string.equals(string)) {
                return value;
            }
        }
        return null;
    }

    static public void add(int id, String string) {
        if ((id < 0) || (id >= 10000)) {
            return;
        }
        if (valueOf(string) == null) {
            values.add(0, new WordClass(id, string));
        }
    }

    static public java.util.ArrayList<WordClass> getValues() {
        java.util.ArrayList<WordClass> retValues = new java.util.ArrayList<>();

        for (WordClass value : values) {
            retValues.add(value);
        }

        return retValues;
    }

    static public java.util.ArrayList<WordClass> getAllValues() {
        java.util.ArrayList<WordClass> retValues = new java.util.ArrayList<>();

        retValues.add(WordClass.Any);
        for (WordClass value : values) {
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
