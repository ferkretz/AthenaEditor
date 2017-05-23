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

public enum LanguageCountry {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    en_CA("English (Canada)", "CA"),
    en_GB("English (United Kingdom)", "GB"),
    en_US("English (United States)", "US"),
    //fr_CA("French (Canada)", "CA"),
    //fr_FR("French (France)"),
    //de_DE("German (Germany)"),
    hu_HU("Hungarian (Hungary)", "HU");
    private String languageCountry;
    private String flag;
    // </editor-fold>

    LanguageCountry(String languageCountry, String flag) {
        this.languageCountry = languageCountry;
        this.flag = flag;
    }

    public String getText() {
        return languageCountry;
    }

    public String getLC() {
        return toString();
    }

    public String getLanguage() {
        return toString().split("_")[0];
    }

    public String getCountry() {
        return toString().split("_")[1];
    }

    public java.util.Locale getLocale() {
        return new java.util.Locale(getLanguage(), getCountry());
    }

    public javax.swing.ImageIcon getImageIcon() {
        return new javax.swing.ImageIcon(getClass().getResource(String.format("/resource/image/flag_%s.png", flag))); //NOI18N
    }
}
