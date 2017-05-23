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
package gui;

@SuppressWarnings("serial")
public class ToolButton extends javax.swing.JButton {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private IconStock stock;
    // </editor-fold>

    public ToolButton(IconStock stock) {
        super();

        this.stock = stock;

        if (stock.hasIcon()) {
            setIcon(stock.getImageIcon());
        }
        super.setToolTipText(stock.getText());
    }

    public ToolButton(IconStock stock, String toolTipText) {
        this(stock);
        super.setToolTipText(toolTipText);
    }

    public IconStock getStock() {
        return stock;
    }
}
