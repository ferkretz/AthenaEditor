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

public class Word {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    protected long wordId;
    protected long groupId;
    protected java.sql.Timestamp insertTime;
    protected java.sql.Timestamp updateTime;
    protected Member author;
    protected Member editor;
    protected WordStatus status;
    protected Language language;
    protected Language transLanguage;
    protected WordClass wordClass;
    protected String word;
    protected WordTranslations translations;
    protected Synonymes synonymes;
    protected Opposites opposites;
    protected LutWords lutWords;
    protected LutWords transLutWords;
    // </editor-fold>

    public Word() {
        wordId = 0;
        groupId = 0;
        insertTime = DateTime.now();
        updateTime = DateTime.now();
        author = new Member();
        editor = new Member();
        status = WordStatus.Draft;
        language = Language.English;
        transLanguage = Language.Hungarian;
        wordClass = WordClass.Unknown;
        word = "";
        translations = new WordTranslations();
        synonymes = new Synonymes();
        opposites = new Opposites();
        lutWords = new LutWords();
        transLutWords = new LutWords();
    }

    public Word(Word word) {
        this.wordId = word.wordId;
        this.groupId = word.groupId;
        this.insertTime = word.insertTime;
        this.updateTime = word.updateTime;
        this.author = word.author;
        this.editor = word.editor;
        this.status = word.status;
        this.language = word.language;
        this.transLanguage = word.transLanguage;
        this.wordClass = word.wordClass;
        this.word = word.word;
        translations = new WordTranslations(word.translations);
        synonymes = new Synonymes(word.synonymes);
        opposites = new Opposites(word.opposites);
        lutWords = new LutWords(word.lutWords);
        transLutWords = new LutWords(word.transLutWords);
    }

    @Override
    public String toString() {
        return String.format(
                "%s: \n\twordId=%d\n\tgroupId=%d\n\tword=%s\n\tauthor=%s\n\teditor=%s\n",
                super.toString(), wordId, groupId, word, author, editor);
    }

    public long getWordId() {
        return wordId;
    }

    public long getGroupId() {
        return groupId;
    }

    public java.sql.Timestamp getInsertTime() {
        return insertTime;
    }

    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public Member getAuthor() {
        return author;
    }

    public Member getEditor() {
        return editor;
    }

    public WordStatus getStatus() {
        return status;
    }

    public Language getLanguage() {
        return language;
    }

    public Language getTransLanguage() {
        return transLanguage;
    }

    public WordClass getWordClass() {
        return wordClass;
    }

    public String getWord() {
        return word;
    }

    public WordTranslations getTranslations() {
        return translations;
    }

    public Synonymes getSynonymes() {
        return synonymes;
    }

    public Opposites getOpposites() {
        return opposites;
    }

    public LutWords getLutWords() {
        return lutWords;
    }

    public LutWords getTransLutWords() {
        return transLutWords;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setInsertTime(java.sql.Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public void setEditor(Member editor) {
        this.editor = editor;
    }

    public void setStatus(WordStatus status) {
        this.status = status;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setTransLanguage(Language transLanguage) {
        this.transLanguage = transLanguage;
    }

    public void setWordClass(WordClass wordClass) {
        this.wordClass = wordClass;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTranslations(WordTranslations translations) {
        this.translations = translations;
    }

    public void setSynonymes(Synonymes synonymes) {
        this.synonymes = synonymes;
    }

    public void setOpposites(Opposites opposites) {
        this.opposites = opposites;
    }

    public void setLutWords(LutWords lutWords) {
        this.lutWords = lutWords;
    }

    public void setTransLutWords(LutWords transLutWords) {
        this.transLutWords = transLutWords;
    }

}
