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

@SuppressWarnings("serial")
public class Words extends java.util.ArrayList<Word> {

    private boolean hasMoreWords;

    public Words() {
        super();
        hasMoreWords = false;
    }

    public boolean hasMoreWords() {
        return hasMoreWords;
    }

    public void setHasMoreWords(boolean hasMoreWords) {
        this.hasMoreWords = hasMoreWords;
    }

}
