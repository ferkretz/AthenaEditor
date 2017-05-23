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
 * Allows to reach, create and manipulate MySQL databases.
 */
public final class SQLDatabase {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private java.sql.Connection conn = null;
    private boolean readOnly = false;
    private static boolean rootUser = false;
    private static String Driver;
    private static String Url;
    //private static String Host;
    private static String User;
    private static String Pass;
    //private static String RootPass;
    private static String Name;
    private static String MemberTableName;
    private static String WordTableName;
    private static String LookupTableName;
    private static String TranslationTableName;
    private static String SynonymeTableName;
    private static String OppositeTableName;
    private static final String Engine = "ENGINE=INNODB";
    private static final String CharacterSet = "CHARACTER SET utf8 COLLATE utf8_unicode_ci";
    private static int ViewRecordLimit;
    private static int ViewUserLimit;
    // </editor-fold>

    public SQLDatabase()
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        getSettings();
        open();
    }

    public SQLDatabase(boolean readOnly)
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        this.readOnly = readOnly;
        getSettings();
        open();
    }

    public SQLDatabase(SQLDatabase sqlDatabase)
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        conn = sqlDatabase.conn;
        readOnly = sqlDatabase.readOnly;
    }

    public static void setRootUser(boolean rootUser) {
        SQLDatabase.rootUser = rootUser;
        if (rootUser) {
            User = "root";
            Pass = Settings.SQLDBRootPass.getValue();
        } else {
            User = Settings.SQLDBUser.getValue();
            Pass = Settings.SQLDBPass.getValue();
        }
    }

    public static boolean databaseExists()
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        boolean ret;
        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        conn.setReadOnly(true);

        sql = "SHOW DATABASES LIKE ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Name);

        rs = stmt.executeQuery();

        if (rs.first()) {
            ret = true;
        } else {
            ret = false;
        }

        rs.close();
        stmt.close();
        conn.close();

        return ret;
    }

    public static boolean isValidDatabase()
            throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url + "/" + Name, User, Pass);

        sql = String.format("SHOW TABLES");
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            String tableName = rs.getString(1);
            if ((!tableName.equals(WordTableName)) && (!tableName.equals(LookupTableName)) && (!tableName.equals(MemberTableName))) {
                rs.close();
                stmt.close();
                conn.close();

                return false;
            }
        }

        rs.close();
        stmt.close();
        conn.close();

        return true;
    }

    public static boolean userExists()
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        boolean ret;
        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        conn.setReadOnly(true);

        sql = "SELECT Count(*) FROM mysql.user WHERE User=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Settings.SQLDBUser.getValue());

        rs = stmt.executeQuery();

        if (rs.first()) {
            ret = rs.getInt(1) != 0;
        } else {
            ret = false;
        }

        rs.close();
        stmt.close();
        conn.close();

        return ret;
    }

    public static void grantPrivileges()
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if ("root".equals(Settings.SQLDBUser.getValue())) {
            return;
        }

        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        sql = String.format("GRANT ALL ON %s.* TO ? IDENTIFIED BY ?", Name);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Settings.SQLDBUser.getValue());
        stmt.setString(2, Settings.SQLDBPass.getValue());
        stmt.executeUpdate();
        stmt.close();

        sql = String.format("GRANT ALL ON %s.* TO ?@'localhost' IDENTIFIED BY ?", Name);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Settings.SQLDBUser.getValue());
        stmt.setString(2, Settings.SQLDBPass.getValue());
        stmt.executeUpdate();
        stmt.close();

        conn.close();
    }

    public static void revokePrivileges()
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if ("root".equals(Settings.SQLDBUser.getValue())) {
            return;
        }

        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        sql = String.format("REVOKE ALL ON %s.* FROM ?", Name);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Settings.SQLDBUser.getValue());
        stmt.executeUpdate();
        stmt.close();
        sql = String.format("REVOKE ALL ON %s FROM ?@'localhost'", Name);
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Settings.SQLDBUser.getValue());
        stmt.executeUpdate();
        stmt.close();

        conn.close();
    }

    public static void dropUser()
            throws java.sql.SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if ("root".equals(Settings.SQLDBUser.getValue())) {
            return;
        }

        if (!userExists()) {
            return;
        }

        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        sql = "DROP USER ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Settings.SQLDBUser.getValue());
        stmt.executeUpdate();

        stmt.close();

        conn.close();
    }

    public static void dropDatabase()
            throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        java.sql.Connection conn;
        java.sql.PreparedStatement stmt;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        sql = String.format("DROP DATABASE IF EXISTS %s", Name);
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        stmt.close();

        conn.close();
    }

    public static void createDatabase()
            throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        java.sql.Connection conn;
        java.sql.Statement stmt;
        String sql;

        getSettings();

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url, User, Pass);

        stmt = conn.createStatement();

        sql = String.format("CREATE DATABASE IF NOT EXISTS %s", Name);
        stmt.executeUpdate(sql);

        sql = String.format("CREATE TABLE IF NOT EXISTS %s.%s("
                + "memberId smallint(5) unsigned NOT NULL auto_increment,"
                + "name varchar(255) NOT NULL default '',"
                + "insertTime datetime NOT NULL default '0000-00-00 00:00:00',"
                + "updateTime datetime NOT NULL default '0000-00-00 00:00:00',"
                + "PRIMARY KEY (memberId),"
                + "KEY (name)"
                + ") %s %s", Name, MemberTableName, Engine, CharacterSet);
        stmt.executeUpdate(sql);

        sql = String.format("CREATE TABLE IF NOT EXISTS %s.%s("
                + "wordId bigint(20) unsigned NOT NULL auto_increment,"
                + "groupId bigint(20) unsigned NOT NULL default 0,"
                + "languageId mediumint(8) unsigned NOT NULL default 0x100000,"
                + "transLanguageId mediumint(8) unsigned NOT NULL default 0x200000,"
                + "word varchar(255) NOT NULL default '',"
                + "insertTime datetime NOT NULL default '0000-00-00 00:00:00',"
                + "updateTime datetime NOT NULL default '0000-00-00 00:00:00',"
                + "authorId smallint(5) unsigned NOT NULL default 1,"
                + "editorId smallint(5) unsigned NOT NULL default 1,"
                + "statusId smallint(5) unsigned NOT NULL default 1,"
                + "wordClassId smallint(5) unsigned NOT NULL default 1,"
                + "PRIMARY KEY (wordId, groupId),"
                + "KEY (languageId),"
                + "KEY (transLanguageId),"
                + "KEY (authorId),"
                + "KEY (editorId),"
                + "KEY (statusId),"
                + "KEY (wordClassId),"
                + "FOREIGN KEY (authorId) REFERENCES " + Name + "." + MemberTableName + "(memberId) ON DELETE CASCADE,"
                + "FOREIGN KEY (editorId) REFERENCES " + Name + "." + MemberTableName + "(memberId) ON DELETE CASCADE"
                + ") %s %s", Name, WordTableName, Engine, CharacterSet);
        stmt.executeUpdate(sql);

        sql = String.format("CREATE TABLE IF NOT EXISTS %s.%s("
                + "wordId bigint(20) unsigned NOT NULL default 1,"
                + "languageId mediumint(5) unsigned NOT NULL default 0x100000,"
                + "transLanguageId mediumint(5) unsigned NOT NULL default 0x200000,"
                + "lutWord varchar(64) NOT NULL default '',"
                + "KEY (languageId),"
                + "KEY (transLanguageId),"
                + "KEY (lutWord),"
                + "FOREIGN KEY (wordId) REFERENCES " + Name + "." + WordTableName + "(wordId) ON DELETE CASCADE"
                + ") %s %s", Name, LookupTableName, Engine, CharacterSet);
        stmt.executeUpdate(sql);

        sql = String.format("CREATE TABLE IF NOT EXISTS %s.%s("
                + "transWord varchar(255) NOT NULL default '',"
                + "examples text,"
                + "transExamples text,"
                + "wordId bigint(20) unsigned NOT NULL default 1,"
                + "KEY (wordId),"
                + "FOREIGN KEY (wordId) REFERENCES " + Name + "." + WordTableName + "(wordId) ON DELETE CASCADE"
                + ") %s %s", Name, TranslationTableName, Engine, CharacterSet);
        stmt.executeUpdate(sql);

        sql = String.format("CREATE TABLE IF NOT EXISTS %s.%s("
                + "synonyme varchar(255) NOT NULL default '',"
                + "wordId bigint(20) unsigned NOT NULL default 1,"
                + "KEY (wordId),"
                + "FOREIGN KEY (wordId) REFERENCES " + Name + "." + WordTableName + "(wordId) ON DELETE CASCADE"
                + ") %s %s", Name, SynonymeTableName, Engine, CharacterSet);
        stmt.executeUpdate(sql);

        sql = String.format("CREATE TABLE IF NOT EXISTS %s.%s("
                + "opposite varchar(255) NOT NULL default '',"
                + "wordId bigint(20) unsigned NOT NULL default 1,"
                + "KEY (wordId),"
                + "FOREIGN KEY (wordId) REFERENCES " + Name + "." + WordTableName + "(wordId) ON DELETE CASCADE"
                + ") %s %s", Name, OppositeTableName, Engine, CharacterSet);
        stmt.executeUpdate(sql);

        stmt.close();

        conn.close();
    }

    public static void prepare()
            throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //clean();
        grantPrivileges();
        createDatabase();
    }

    public static void clean()
            throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        dropDatabase();
        dropUser();
    }

    public boolean isOpen() {
        return conn == null ? false : true;
    }

    public final void open()
            throws java.sql.SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (isOpen()) {
            return;
        }

        Class.forName(Driver).newInstance();
        conn = java.sql.DriverManager.getConnection(Url + "/" + Name, User, Pass);

        conn.setReadOnly(readOnly);
    }

    public void close()
            throws java.sql.SQLException {
        if (!isOpen()) {
            return;
        }

        conn.close();
        conn = null;
    }

    public void setReadOnly(boolean readOnly)
            throws java.sql.SQLException {
        this.readOnly = readOnly;

        if (isOpen()) {
            conn.setReadOnly(readOnly);
        }
    }

    public boolean isReadOnly() {
        return this.isReadOnly();
    }

    public void insertWord(Word word)
            throws java.sql.SQLException {
        word.setWordId(getAutoId(WordTableName));
        insertWordPart(word);
        insertLookupPart(word);
        insertTranslationPart(word);
        insertSynonymePart(word);
        insertOppositePart(word);
        if (word.getGroupId() != 0) {
            if ((word.getStatus().getId() & WordStatus.Hidden.getId()) == 0) {
                obsoleteOthers(word);
            }
        }
        updateMemberTime(word.getAuthor().getId() == 0 ? Settings.MemberId.getIntValue() : word.getAuthor().getId(), DateTime.now());
    }

    public void updateWord(Word word)
            throws java.sql.SQLException {
        updateWordPart(word);
        updateLookupPart(word);
        updateTranslationPart(word);
        updateSynonymePart(word);
        updateOppositePart(word);
        if ((word.getStatus().getId() & WordStatus.Hidden.getId()) == 0) {
            obsoleteOthers(word);
        }
        updateMemberTime(word.getEditor().getId() == 0 ? Settings.MemberId.getIntValue() : word.getEditor().getId(), DateTime.now());
    }

    public void trashWord(Word word)
            throws java.sql.SQLException {
        word.setStatus(WordStatus.Trash);
        updateWord(word);
    }

    public void deleteWord(Word word)
            throws java.sql.SQLException {
        deleteWordPart(word);
        updateMemberTime(word.getEditor().getId() == 0 ? Settings.MemberId.getIntValue() : word.getEditor().getId(), DateTime.now());
    }

    public Words search(SearchTerms terms)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql, language, lookupLanguage, wordClass, lutWord, author, editor, wordStatus;
        String wordId, lookupWhere, groupId, wordWhere;
        Words words = new Words();

        // dictionary
        if ((terms.getLanguage().getId() != Language.Any.getId()) && (terms.getTransLanguage().getId() != Language.Any.getId())) {
            language = String.format("(languageId=%d) AND (transLanguageId=%d)", terms.getLanguage().getId(), terms.getTransLanguage().getId());
        } else if (terms.getLanguage().getId() != Language.Any.getId()) {
            language = String.format("(languageId=%d)", terms.getLanguage().getId());
        } else if (terms.getTransLanguage().getId() != Language.Any.getId()) {
            language = String.format("(transLanguageId=%d)", terms.getTransLanguage().getId());
        } else {
            language = "";
        }

        if ("".equals(terms.getWord())) {
            lutWord = "";
            lookupLanguage = "";
        } else {
            if (terms.getMatch() == WordMatch.ExactMatch) {
                lutWord = String.format("(lutWord='%s')", terms.getWord());
            } else if (terms.getMatch() == WordMatch.PrefixMatch) {
                lutWord = String.format("(lutWord LIKE '%s%%')", terms.getWord());
            } else {
                lutWord = String.format("(lutWord LIKE '%%%s%%')", terms.getWord());
            }
            lookupLanguage = language;
            language = "";
        }

        if (terms.getWordClass().getId() == WordClass.Any.getId()) {
            wordClass = "";
        } else {
            wordClass = String.format("(wordClassId=%d)", terms.getWordClass().getId());
        }

        if ("".equals(terms.getAuthor())) {
            author = "";
        } else {
            if (terms.getInverseAuthor()) {
                author = String.format("(authorId<>ANY(SELECT memberId FROM %s %s))", MemberTableName, terms.getAuthor());
            } else {
                author = String.format("(authorId=ANY(SELECT memberId FROM %s %s))", MemberTableName, terms.getAuthor());
            }
        }

        if ("".equals(terms.getEditor())) {
            editor = "";
        } else {
            if (terms.getInverseEditor()) {
                editor = String.format("(editorId<>ANY(SELECT memberId FROM %s %s))", MemberTableName, terms.getEditor());
            } else {
                editor = String.format("(editorId=ANY(SELECT memberId FROM %s %s))", MemberTableName, terms.getEditor());
            }
        }

        if (terms.getWordStatus().getId() == WordStatus.Any.getId()) {
            wordStatus = "";
        } else {
            wordStatus = String.format("((statusId & %d)<>0)", terms.getWordStatus().getId());
        }

        if (terms.getGroupId() == 0) {
            groupId = "";
        } else {
            groupId = String.format("groupId=%d", terms.getGroupId());
        }

        if ("".equals(lutWord)) {
            wordId = "";
        } else {
            if ("".equals(lookupLanguage)) {
                lookupWhere = String.format("WHERE %s", lutWord);
            } else {
                lookupWhere = String.format("WHERE %s AND %s", lookupLanguage, lutWord);
            }
            wordId = String.format("wordId = ANY(SELECT wordId FROM %s %s)", LookupTableName, lookupWhere);
        }

        wordWhere = "";

        if ((!"".equals(wordWhere)) && (!"".equals(wordClass))) {
            wordWhere += " AND " + wordClass;
        } else {
            wordWhere += wordClass;
        }
        if ((!"".equals(wordWhere)) && (!"".equals(author))) {
            wordWhere += " AND " + author;
        } else {
            wordWhere += author;
        }
        if ((!"".equals(wordWhere)) && (!"".equals(editor))) {
            wordWhere += " AND " + editor;
        } else {
            wordWhere += editor;
        }
        if ((!"".equals(wordWhere)) && (!"".equals(wordStatus))) {
            wordWhere += " AND " + wordStatus;
        } else {
            wordWhere += wordStatus;
        }
        if ((!"".equals(wordWhere)) && (!"".equals(language))) {
            wordWhere += " AND " + language;
        } else {
            wordWhere += language;
        }
        if ((!"".equals(wordWhere)) && (!"".equals(groupId))) {
            wordWhere += " AND " + groupId;
        } else {
            wordWhere += groupId;
        }
        if ((!"".equals(wordWhere)) && (!"".equals(wordId))) {
            wordWhere += " AND " + wordId;
        } else {
            wordWhere += wordId;
        }
        if (!"".equals(wordWhere)) {
            wordWhere = "WHERE " + wordWhere;
        }

        sql = String.format("SELECT * FROM %s %s LIMIT ? OFFSET ?", WordTableName, wordWhere);

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ViewRecordLimit + 1);
        stmt.setLong(2, terms.getOffset());
        rs = stmt.executeQuery();
        for (int i = 0; rs.next(); i++) {
            if (i >= ViewRecordLimit) {
                words.setHasMoreWords(true);
                break;
            }
            words.add(getWord(rs));
        }
        rs.close();

        stmt.close();

        return words;
    }

    public Words getWords(Words words)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql, idxs = "";

        if (words.isEmpty()) {
            return words;
        }

        for (Word word : words) {
            idxs += word.getWordId() + ",";
        }
        idxs = idxs.substring(0, idxs.length() - 1);

        sql = String.format("SELECT * FROM %s "
                + "WHERE wordId IN(%s)", WordTableName, idxs);

        stmt = conn.prepareStatement(sql);

        rs = stmt.executeQuery();
        for (int i = 0; rs.next(); i++) {
            words.set(i, getWord(rs));
        }
        rs.close();

        stmt.close();

        return words;
    }

    public long getAutoId(String tableName)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql;
        long autoId;

        sql = "SELECT AUTO_INCREMENT "
                + "FROM information_schema.TABLES "
                + "WHERE TABLE_SCHEMA=? AND TABLE_NAME=?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, Name);
        stmt.setString(2, tableName);
        rs = stmt.executeQuery();
        rs.next();
        autoId = rs.getLong(1);
        rs.close();

        stmt.close();

        return autoId;
    }

    public long getLastInsertId()
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql;
        long lastInsertId;

        sql = "SELECT LAST_INSERT_ID()";

        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        rs.next();
        lastInsertId = rs.getLong(1);
        rs.close();

        stmt.close();

        return lastInsertId;
    }

    public int insertMember(String name)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;
        int memberId = (int) getAutoId(MemberTableName);

        sql = String.format("INSERT INTO %s "
                + "(name, insertTime, updateTime) "
                + "values (?, ?, ?)", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setTimestamp(2, DateTime.now());
        stmt.setTimestamp(3, DateTime.now());
        stmt.executeUpdate();

        stmt.close();

        return memberId;
    }

    public void updateMemberNameIfExists(int id, String name)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        sql = String.format("INSERT INTO %s "
                + "(memberId, name, insertTime, updateTime) values(?, ?, NOW(), NOW()) "
                + "ON DUPLICATE KEY UPDATE memberId=?, name=?", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setInt(3, id);
        stmt.setString(4, name);
        stmt.executeUpdate();

        stmt.close();
    }

    private void updateMemberName(int id, String name)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        sql = String.format("UPDATE %s "
                + "SET name=? "
                + "WHERE memberId=?", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setInt(2, id);
        stmt.executeUpdate();

        stmt.close();
    }

    private void updateMemberTime(int id, java.sql.Timestamp updateTime)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        sql = String.format("UPDATE %s "
                + "SET updateTime=? "
                + "WHERE memberId=?", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setTimestamp(1, updateTime);
        stmt.setInt(2, id);
        stmt.executeUpdate();

        stmt.close();
    }

    private void deleteMember(int id)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;

        String sql = String.format("DELETE FROM %s WHERE memberId=?", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
    }

    private void insertWordPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        sql = String.format("INSERT INTO %s "
                + "(groupId, insertTime, updateTime, authorId, editorId, statusId, "
                + "languageId, transLanguageId, wordClassId, word) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", WordTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getGroupId() == 0 ? word.getWordId() : word.getGroupId());
        stmt.setTimestamp(2, DateTime.now());
        stmt.setTimestamp(3, DateTime.now());
        stmt.setInt(4, word.getAuthor().getId() == 0 ? Settings.MemberId.getIntValue() : word.getAuthor().getId());
        stmt.setInt(5, word.getEditor().getId() == 0 ? Settings.MemberId.getIntValue() : word.getEditor().getId());
        stmt.setInt(6, word.getStatus().getId());
        stmt.setInt(7, word.getLanguage().getId());
        stmt.setInt(8, word.getTransLanguage().getId());
        stmt.setInt(9, word.getWordClass().getId());
        stmt.setString(10, word.getWord());
        stmt.executeUpdate();

        stmt.close();
    }

    private void updateWordPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        sql = String.format("UPDATE %s "
                + "SET updateTime=?, editorId=?, statusId=?, "
                + "languageId=?, transLanguageId=?, wordClassId=?, word=? "
                + "WHERE wordId=?", WordTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setTimestamp(1, DateTime.now());
        stmt.setInt(2, word.getEditor().getId() == 0 ? Settings.MemberId.getIntValue() : word.getEditor().getId());
        stmt.setInt(3, word.getStatus().getId());
        stmt.setInt(4, word.getLanguage().getId());
        stmt.setInt(5, word.getTransLanguage().getId());
        stmt.setInt(6, word.getWordClass().getId());
        stmt.setString(7, word.getWord());
        stmt.setLong(8, word.getWordId());
        stmt.executeUpdate();

        stmt.close();
    }

    private void deleteWordPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;

        String sql = String.format("DELETE FROM %s WHERE wordId=?", WordTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        stmt.executeUpdate();

        stmt.close();
    }

    private void insertLookupPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        if (word.getLutWords().isEmpty() && word.getTransLutWords().isEmpty()) {
            return;
        }

        sql = String.format("INSERT INTO %s "
                + "(lutWord, languageId, transLanguageId, wordId) "
                + "values (?, ?, ?, ?)", LookupTableName);

        stmt = conn.prepareStatement(sql);
        for (String lutWord : word.getLutWords()) {
            stmt.setString(1, lutWord);
            stmt.setInt(2, word.getLanguage().getId());
            stmt.setInt(3, word.getTransLanguage().getId());
            stmt.setLong(4, word.getWordId());
            stmt.addBatch();
        }
        for (String transLutWord : word.getTransLutWords()) {
            stmt.setString(1, transLutWord);
            stmt.setInt(2, word.getTransLanguage().getId());
            stmt.setInt(3, word.getLanguage().getId());
            stmt.setLong(4, word.getWordId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
    }

    private void updateLookupPart(Word word)
            throws java.sql.SQLException {
        deleteLookupPart(word);
        insertLookupPart(word);
    }

    private void deleteLookupPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;

        String sql = String.format("DELETE FROM %s WHERE wordId=?", LookupTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        stmt.executeUpdate();

        stmt.close();
    }

    private void insertTranslationPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        if (word.getTranslations().isEmpty()) {
            return;
        }

        sql = String.format("INSERT INTO %s "
                + "(transWord, examples, transExamples, wordId) "
                + "values (?, ?, ?, ?)", TranslationTableName);

        stmt = conn.prepareStatement(sql);
        for (WordTranslation translation : word.getTranslations()) {
            stmt.setString(1, translation.getTransWord());
            stmt.setString(2, translation.getExamples());
            stmt.setString(3, translation.getTransExamples());
            stmt.setLong(4, word.getWordId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
    }

    private void updateTranslationPart(Word word)
            throws java.sql.SQLException {
        deleteTranslationPart(word);
        insertTranslationPart(word);
    }

    private void deleteTranslationPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;

        String sql = String.format("DELETE FROM %s WHERE wordId=?", TranslationTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        stmt.executeUpdate();

        stmt.close();
    }

    private void insertSynonymePart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        if (word.getSynonymes().isEmpty()) {
            return;
        }

        sql = String.format("INSERT INTO %s "
                + "(synonyme, wordId) "
                + "values (?, ?)", SynonymeTableName);

        stmt = conn.prepareStatement(sql);
        for (String synonyme : word.getSynonymes()) {
            stmt.setString(1, synonyme);
            stmt.setLong(2, word.getWordId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
    }

    private void updateSynonymePart(Word word)
            throws java.sql.SQLException {
        deleteSynonymePart(word);
        insertSynonymePart(word);
    }

    private void deleteSynonymePart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;

        String sql = String.format("DELETE FROM %s WHERE wordId=?", SynonymeTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        stmt.executeUpdate();

        stmt.close();
    }

    private void insertOppositePart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        if (word.getOpposites().isEmpty()) {
            return;
        }

        sql = String.format("INSERT INTO %s "
                + "(opposite, wordId) "
                + "values (?, ?)", OppositeTableName);

        stmt = conn.prepareStatement(sql);
        for (String opposite : word.getOpposites()) {
            stmt.setString(1, opposite);
            stmt.setLong(2, word.getWordId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
    }

    private void updateOppositePart(Word word)
            throws java.sql.SQLException {
        deleteOppositePart(word);
        insertSynonymePart(word);
    }

    private void deleteOppositePart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;

        String sql = String.format("DELETE FROM %s WHERE wordId=?", OppositeTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        stmt.executeUpdate();

        stmt.close();
    }

    private void obsoleteOthers(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;

        sql = String.format("UPDATE %s "
                + "SET statusId=? "
                + "WHERE wordId<>? AND groupId=? AND (statusId & ?) = 0", WordTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, WordStatus.Obsolete.getId());
        stmt.setLong(2, word.getWordId());
        stmt.setLong(3, word.getGroupId());
        stmt.setInt(4, WordStatus.Hidden.getId());
        stmt.executeUpdate();

        stmt.close();
    }

    private Word getWord(java.sql.ResultSet rs)
            throws java.sql.SQLException {
        Word word = new Word();

        word.setWordId(rs.getLong("wordId"));
        word.setGroupId(rs.getLong("groupId"));
        word.setWord(rs.getString("word"));
        word.setAuthor(getMember(rs.getInt("authorId")));
        word.setEditor(getMember(rs.getInt("editorId")));
        word.setInsertTime(rs.getTimestamp("insertTime"));
        word.setUpdateTime(rs.getTimestamp("updateTime"));
        word.setStatus(WordStatus.valueOf(rs.getInt("statusId")));
        word.setLanguage(Language.valueOf(rs.getInt("languageId")));
        word.setTransLanguage(Language.valueOf(rs.getInt("transLanguageId")));
        word.setWordClass(WordClass.valueOf(rs.getInt("wordClassId")));

        word = getLookupPart(word);
        word = getTranslationPart(word);
        word = getSynonymePart(word);
        word = getOppositePart(word);

        return word;
    }

    public Member getMember(int memberId)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;
        java.sql.ResultSet rs;
        Member member = new Member();

        sql = String.format("SELECT * FROM %s WHERE memberId=?", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, memberId);
        rs = stmt.executeQuery();

        if (rs.next()) {
            member.setId(memberId);
            member.setName(rs.getString("name"));
            member.setInsertTime(rs.getTimestamp("insertTime"));
            member.setUpdateTime(rs.getTimestamp("updateTime"));
        }
        rs.close();
        stmt.close();

        return member;
    }

    private Word getLookupPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;
        java.sql.ResultSet rs;
        LutWords lutWords = new LutWords();
        LutWords transLutWords = new LutWords();

        sql = String.format("SELECT * FROM %s WHERE wordId=?", LookupTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        rs = stmt.executeQuery();

        while (rs.next()) {
            if (rs.getInt("languageId") == word.getLanguage().getId()) {
                lutWords.add(rs.getString("lutWord"));
            } else {
                transLutWords.add(rs.getString("lutWord"));
            }
        }
        rs.close();
        stmt.close();

        word.setLutWords(lutWords);
        word.setTransLutWords(transLutWords);

        return word;
    }

    private Word getTranslationPart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;
        java.sql.ResultSet rs;
        WordTranslations translations = new WordTranslations();

        sql = String.format("SELECT * FROM %s WHERE wordId=?", TranslationTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        rs = stmt.executeQuery();

        while (rs.next()) {
            translations.add(new WordTranslation(rs.getString("transWord"), rs.getString("examples"), rs.getString("transExamples")));
        }
        rs.close();
        stmt.close();

        word.setTranslations(translations);

        return word;
    }

    private Word getSynonymePart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;
        java.sql.ResultSet rs;
        Synonymes synonymes = new Synonymes();

        sql = String.format("SELECT * FROM %s WHERE wordId=?", SynonymeTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        rs = stmt.executeQuery();

        while (rs.next()) {
            synonymes.add(rs.getString("synonyme"));
        }
        rs.close();
        stmt.close();

        word.setSynonymes(synonymes);

        return word;
    }

    private Word getOppositePart(Word word)
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        String sql;
        java.sql.ResultSet rs;
        Opposites opposites = new Opposites();

        sql = String.format("SELECT * FROM %s WHERE wordId=?", OppositeTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setLong(1, word.getWordId());
        rs = stmt.executeQuery();

        while (rs.next()) {
            opposites.add(rs.getString("opposite"));
        }
        rs.close();
        stmt.close();

        word.setOpposites(opposites);

        return word;
    }

    public Members getMembers()
            throws java.sql.SQLException {
        java.sql.PreparedStatement stmt;
        java.sql.ResultSet rs;
        String sql;
        Members members = new Members();

        sql = String.format("SELECT * FROM %s LIMIT ? OFFSET ?", MemberTableName);

        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, ViewUserLimit + 1);
        stmt.setLong(2, 0);

        rs = stmt.executeQuery();
        for (int i = 0; rs.next(); i++) {
            if (i > ViewUserLimit) {
                members.setMore(true);
                break;
            }
            Member member = new Member();
            member.setId(rs.getInt("memberId"));
            member.setName(rs.getString("name"));
            member.setInsertTime(rs.getTimestamp("insertTime"));
            member.setUpdateTime(rs.getTimestamp("updateTime"));
            members.add(member);
        }
        rs.close();

        stmt.close();

        return members;
    }

    private static void getSettings() {
        Driver = Settings.SQLDBDriver.getValue();
        Url = Settings.SQLDBUrlPrefix.getValue() + Settings.SQLDBHost.getValue();
        //Host = Settings.SQLDBHost.getValue();
        if (rootUser) {
            User = "root";
            Pass = Settings.SQLDBRootPass.getValue();
        } else {
            User = Settings.SQLDBUser.getValue();
            Pass = Settings.SQLDBPass.getValue();
        }
        //RootPass = Settings.SQLDBRootPass.getValue();
        Name = Settings.SQLDBName.getValue();
        MemberTableName = Settings.SQLDBPrefix.getValue() + "_user";
        WordTableName = Settings.SQLDBPrefix.getValue() + "_word";
        LookupTableName = Settings.SQLDBPrefix.getValue() + "_lookup";
        TranslationTableName = Settings.SQLDBPrefix.getValue() + "_translation";
        SynonymeTableName = Settings.SQLDBPrefix.getValue() + "_synonyme";
        OppositeTableName = Settings.SQLDBPrefix.getValue() + "_opposite";
        ViewRecordLimit = Integer.parseInt(Settings.ViewRecordLimit.getValue());
        ViewUserLimit = Integer.parseInt(Settings.ViewUserLimit.getValue());
    }

}
