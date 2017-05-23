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
 * Allows to load, save, set configuration properties.
 */
public enum Settings {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    AppName("Athena", true),
    Version("v0.2", true),
    ConfigDir(System.getProperty("user.home") + java.io.File.separatorChar
            + AppName.getValue() + java.io.File.separatorChar, true),
    ConfigFile("settings.xml", true),
    ConfigPath(Settings.ConfigDir.getValue() + Settings.ConfigFile.getValue(), true),
    I18nLanguage(""),
    I18nCountry(""),
    SQLDBDriver("com.mysql.jdbc.Driver"),
    SQLDBUrlPrefix("jdbc:mysql://"),
    SQLDBHost("localhost"),
    SQLDBName("athena"),
    SQLDBUser("athena"),
    SQLDBPass(""),
    SQLDBRootPass("", false, true),
    SQLDBPrefix("ath"),
    ViewRecordLimit("20"),
    ViewUserLimit("20"),
    TBShowFile("true"),
    TBShowEdit("true"),
    TBShowHelp("true"),
    MemberId("0"),
    MemberName(System.getProperty("user.name"), false, true);
    private String defaultValue;
    private String value;
    private boolean isStatic = false;
    private boolean isSkip = false;
    // </editor-fold>

    /**
     * Constructs settings property with a default value.
     *
     * @param defaultValue default value
     */
    Settings(String defaultValue) {
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    /**
     * Constructs settings property with a default value, which can not be
     * saved.
     *
     * @param defaultValue default value
     * @param isStatic If true, sets the access permission to allow read only
     * operations; if false to disallow read only operations
     */
    Settings(String defaultValue, boolean isStatic) {
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.isStatic = isStatic;
        this.isSkip = true;
    }

    /**
     * Constructs settings property with a default value.
     *
     * @param defaultValue default value
     * @param isStatic if true, sets the access permission to allow read only
     * operations; if false to disallow read only operations
     * @param isSkip if true, user defined value will not be saved; if false
     * sets to allow to save
     */
    Settings(String defaultValue, boolean isStatic, boolean isSkip) {
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.isStatic = isStatic;
        this.isSkip = isSkip;
    }

    /**
     * Constructs settings property with a default and an user specified value.
     *
     * @param defaultValue default value
     * @param value user specified value
     */
    Settings(String defaultValue, String value) {
        this.defaultValue = defaultValue;
        this.value = value;
    }

    /**
     * Returns the default value of the property.
     *
     * @return default value
     */
    public String getDefault() {
        return defaultValue;
    }

    /**
     * Returns the user specified value of the property.
     *
     * @return user specified value
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the user specified value of the property.
     *
     * @return user specified (boolean) value
     */
    public boolean getBooleanValue() {
        return Boolean.parseBoolean(value);
    }

    /**
     * Returns the user specified value of the property.
     *
     * @return user specified (int) value
     */
    public int getIntValue() {
        return Integer.parseInt(value);
    }

    /**
     * Returns the user specified value of the property.
     *
     * @return user specified (long) value
     */
    public long getLongValue() {
        return Long.parseLong(value);
    }

    /**
     * Returns the user specified value of the property.
     *
     * @return user specified (String) value
     */
    public String getStringValue() {
        return value;
    }

    /**
     * Defines the user specified value of the property.
     *
     * @param value user specified value
     */
    public void setValue(String value) {
        if (!isStatic) {
            this.value = value;
        }
    }

    /**
     * Defines the user specified value of the property.
     *
     * @param value user specified (boolean)value
     */
    public void setBooleanValue(boolean value) {
        if (!isStatic) {
            this.value = Boolean.toString(value);
        }
    }

    /**
     * Defines the user specified value of the property.
     *
     * @param value user specified (int) value
     */
    public void setIntValue(int value) {
        if (!isStatic) {
            this.value = Integer.toString(value);
        }
    }

    /**
     * Defines the user specified value of the property.
     *
     * @param value user specified (long) value
     */
    public void setIntValue(long value) {
        if (!isStatic) {
            this.value = Long.toString(value);
        }
    }

    /**
     * Defines the user specified value of the property.
     *
     * @param value user specified (String) value
     */
    public void setStringValue(String value) {
        if (!isStatic) {
            this.value = value;
        }
    }

    /**
     * Tests whether the configuration file is exists.
     *
     * @return true if the configuration file is exists; false otherwise
     */
    public static boolean isSaved() {
        java.io.File path = new java.io.File(Settings.ConfigPath.getValue());

        if (path.exists()) {
            return true;
        }

        return false;
    }

    /**
     * Saves to configuration file.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void saveToFile()
            throws java.io.FileNotFoundException, java.io.IOException {
        java.util.Properties prop = new java.util.Properties();

        for (Settings set : Settings.values()) {
            if (!set.isSkip) {
                prop.setProperty(set.toString(), set.getValue());
            }
        }

        java.io.File dir = new java.io.File(Settings.ConfigDir.getValue());
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (java.io.FileOutputStream stream = new java.io.FileOutputStream(Settings.ConfigPath.getValue())) {
            prop.storeToXML(stream, "");
        }
    }

    /**
     * Loads user defined configuration file.
     *
     * @throws java.io.FileNotFoundException
     * @throws java.util.InvalidPropertiesFormatException
     * @throws java.io.IOException
     */
    public static void loadFromFile()
            throws java.util.InvalidPropertiesFormatException, java.io.FileNotFoundException, java.io.IOException {
        java.util.Properties prop = new java.util.Properties();

        if (new java.io.File(Settings.ConfigPath.getValue()).isFile()) {
            prop.loadFromXML(new java.io.FileInputStream(Settings.ConfigPath.getValue()));
        }

        for (java.util.Map.Entry<Object, Object> entry : prop.entrySet()) {
            Settings.valueOf((String) entry.getKey()).setValue((String) entry.getValue());
        }
    }

    /**
     * Removes configuration file.
     */
    public static void removeFile() {
        java.io.File file = new java.io.File(Settings.ConfigPath.getValue());
        if (file.exists()) {
            file.delete();
            new java.io.File(Settings.ConfigDir.getValue()).delete();
        }
    }

}
