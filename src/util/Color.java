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
 * Simple color class.
 */
@SuppressWarnings("serial")
public class Color extends java.awt.Color {

    public final static Color darkBlue = new Color(0, 0, 139);
    public final static Color DARK_BLUE = darkBlue;
    public final static Color darkCyan = new Color(0, 139, 139);
    public final static Color DARK_CYAN = darkCyan;
    public final static Color darkGreen = new Color(0, 100, 0);
    public final static Color DARK_GREEN = darkGreen;
    public final static Color darkOrange = new Color(255, 140, 0);
    public final static Color DARK_ORANGE = darkOrange;
    public final static Color darkPink = new Color(255, 105, 180);
    public final static Color DARK_PINK = darkPink;
    public final static Color darkRed = new Color(139, 0, 0);
    public final static Color DARK_RED = darkRed;

    public Color(int red, int green, int blue) {
        super(red, green, blue);
    }

    public Color(int red, int green, int blue, int alpha) {
        super(red, green, blue, alpha);
    }

}
