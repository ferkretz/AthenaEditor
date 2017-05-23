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

public class WordTranslation {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    protected String transWord;
    protected String examples;
    protected String transExamples;
    // </editor-fold>

    public WordTranslation() {
        transWord = "";
        examples = "";
        transExamples = "";
    }

    public WordTranslation(String transWord, String examples, String transExamples) {
        this.transWord = transWord;
        this.examples = examples;
        this.transExamples = transExamples;
    }

    public String getTransWord() {
        return transWord;
    }

    public String getExamples() {
        return examples;
    }

    public String getTransExamples() {
        return transExamples;
    }

    public boolean isExamples() {
        return (("".equals(examples)) && ("".equals(transExamples))) ? false : true;
    }

    public void setTransWord(String transWord) {
        this.transWord = transWord;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public void setTransExamples(String transExamples) {
        this.transExamples = transExamples;
    }

}
