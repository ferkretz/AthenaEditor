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

public enum WordMatch {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    PrefixMatch("Prefix match"),
    ExactMatch("Exact match"),
    ContainMatch("Contain match");
    private String wordMatch;
    private final I18n i18n = new I18n(this);
    // </editor-fold>

    WordMatch(String wordMatch) {
        this.wordMatch = i18n.translate(wordMatch);
    }

    public String getSQLString() {
        return toString();
    }

    public String getI18nString() {
        return wordMatch;
    }

}
