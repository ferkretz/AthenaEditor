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

/**
 * Simple String manipulating class.
 */
public class Strings {

    public static String[] explode(String string) {
        return string.split(",");
    }

    public static String[] explode(String delimiter, String string) {
        return string.split(delimiter);
    }

    public static String implode(String[] pieces) {
        return implode(",", pieces, 0, pieces.length);
    }

    public static String implode(String[] pieces, int from) {
        return implode(",", pieces, from, pieces.length);
    }

    public static String implode(String[] pieces, int from, int limit) {
        return implode(",", pieces, from, limit);
    }

    public static String implode(String glue, String[] pieces) {
        return implode(glue, pieces, 0, pieces.length);
    }

    public static String implode(String glue, String[] pieces, int from) {
        return implode(glue, pieces, from, pieces.length);
    }

    public static String implode(String glue, String[] pieces, int from, int limit) {
        String string = "";
        int i;

        if (pieces == null) {
            return null;
        }

        limit += from;
        if (limit > pieces.length) {
            limit = pieces.length;
        }

        for (i = from; i < limit - 1; i++) {
            string += pieces[i] + glue;
        }
        string += pieces[i];

        return string;
    }

}
