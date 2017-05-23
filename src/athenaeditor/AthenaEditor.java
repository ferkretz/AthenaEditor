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
package athenaeditor;

public class AthenaEditor {

    public static void main(String[] args) {

        if (util.Settings.isSaved()) {
            try {
                util.Settings.loadFromFile();
                new gui.MainEditorFrame().setVisible(true);
            } catch (java.io.IOException ex) {
                java.util.logging.Logger.getLogger(AthenaEditor.class.getName()).
                        log(java.util.logging.Level.SEVERE, null, ex);
            }
        } else {
            new gui.SetupWizardFrame().setVisible(true);
        }
    }
}
