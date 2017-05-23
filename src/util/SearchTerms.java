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
 * Search terms for util.Words.
 */
public class SearchTerms {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    protected Language language;
    protected Language transLanguage;
    protected String word;
    protected WordMatch wordMatch;
    protected WordClass wordClass;
    protected String author;
    protected String editor;
    protected boolean inverseAuthor;
    protected boolean inverseEditor;
    protected WordStatus wordStatus;
    protected long groupId;
    protected long offset;
    // </editor-fold>

    public SearchTerms() {
        language = Language.Any;
        transLanguage = Language.Any;
        word = "";
        wordMatch = WordMatch.PrefixMatch;
        wordClass = WordClass.Any;
        author = "";
        editor = "";
        inverseAuthor = false;
        inverseEditor = false;
        wordStatus = WordStatus.ExcludeHidden;
        groupId = 0;
        offset = 0;
    }

    @Override
    public String toString() {
        return String.format(
                "%s: \n\tword=%s\n\toffset=%d\n",
                super.toString(), word, offset);
    }

    public Language getLanguage() {
        return language;
    }

    public Language getTransLanguage() {
        return transLanguage;
    }

    public String getWord() {
        return word;
    }

    public WordMatch getMatch() {
        return wordMatch;
    }

    public WordClass getWordClass() {
        return wordClass;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public boolean getInverseAuthor() {
        return inverseAuthor;
    }

    public boolean getInverseEditor() {
        return inverseEditor;
    }

    public WordStatus getWordStatus() {
        return wordStatus;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getOffset() {
        return offset;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setTransLanguage(Language transLanguage) {
        this.transLanguage = transLanguage;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWordMatch(WordMatch wordMatch) {
        this.wordMatch = wordMatch;
    }

    public void setWordClass(WordClass wordClass) {
        this.wordClass = wordClass;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setInverseAuthor(boolean inverseAuthor) {
        this.inverseAuthor = inverseAuthor;
    }

    public void setInverseEditor(boolean inverseEditor) {
        this.inverseEditor = inverseEditor;
    }

    public void setWordStatus(WordStatus wordStatus) {
        this.wordStatus = wordStatus;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

}
