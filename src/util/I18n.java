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
 * Allows to localising strings.
 */
public class I18n {

    private java.util.ResourceBundle resource;
    static final String basePath = "resource/i18n/"; //NOI18N

    public I18n(String baseName) {
        try {
            resource = java.util.ResourceBundle.getBundle(basePath + baseName);
        } catch (Exception ex) {
        }
    }

    public I18n(Object object) {
        this(object.getClass().getName().replace('.', '_'));
    }

    public I18n(Class<?> klass) {
        this(klass.getName().replace('.', '_'));
    }

    public String translate(String originalText) {
        try {
            return resource.getString(originalText);
        } catch (Exception ex) {
            return originalText;
        }
    }

    public String translatef(String originalFormat, Object... args) {
        try {
            return String.format(resource.getString(originalFormat), args);
        } catch (Exception ex) {
            return String.format(originalFormat, args);
        }
    }

    public static String translate(Class<?> klass, String originalText) {
        try {
            return java.util.ResourceBundle.getBundle(
                    basePath + klass.getName().replace('.', '_')).getString(originalText);
        } catch (Exception ex) {
            return originalText;
        }
    }

    public static String translatef(Class<?> klass, String originalFormat, Object... args) {
        try {
            return String.format(java.util.ResourceBundle.getBundle(
                    basePath + klass.getName().replace('.', '_')).getString(originalFormat), args);
        } catch (Exception ex) {
            return originalFormat;
        }
    }

}
