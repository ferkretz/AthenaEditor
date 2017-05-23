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

public class Language {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private int id;
    private String string;
    private static int idI;
    private static java.util.ArrayList<Language> values = new java.util.ArrayList<>();
    public static final Language Any = preDefineExtra(0xFFFFFF, "Any");
    public static final Language English = preDefine(0x100000, "English");
    public static final Language French = preDefine(0x200000, "French");
    public static final Language German = preDefine(0x400000, "German");
    public static final Language Hungarian = preDefine(0x800000, "Hungarian");
    private final I18n i18n = new I18n(this);
    // </editor-fold>

    private static Language preDefine(String string) {
        Language value = new Language(idI, string);
        values.add(value);
        idI++;
        return value;
    }

    private static Language preDefine(int id, String string) {
        idI = id + 1;
        Language value = new Language(id, string);
        values.add(value);
        return value;
    }

    private static Language preDefineExtra(int id, String string) {
        idI = id + 1;
        return new Language(id, string);
    }

    private Language(int id, String string) {
        this.id = id;
        this.string = string;
    }

    public boolean equals(Language values) {
        return id == values.id ? true : false;
    }

    static public Language valueOf(int id) {
        for (Language value : values) {
            if (value.id == id) {
                return value;
            }
        }
        return null;
    }

    static public Language valueOf(String string) {
        for (Language value : values) {
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
            values.add(0, new Language(id, string));
        }
    }

    static public java.util.ArrayList<Language> getValues() {
        java.util.ArrayList<Language> retValues = new java.util.ArrayList<>();

        for (Language value : values) {
            retValues.add(value);
        }

        return retValues;
    }

    static public java.util.ArrayList<Language> getAllValues() {
        java.util.ArrayList<Language> retValues = new java.util.ArrayList<>();

        retValues.add(Language.Any);
        for (Language value : values) {
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
