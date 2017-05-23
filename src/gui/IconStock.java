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

public enum IconStock {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    Abort("Abort", "16x16_No"), //NOI18N
    About("About", "16x16_About"), //NOI18N
    Add("Add", "16x16_Add"), //NOI18N
    Add2("Add", "16x16_Add2"), //NOI18N
    Apply("Apply", "16x16_Apply"), //NOI18N
    Cancel("Cancel", "16x16_Cancel"), //NOI18N
    Close("Close", "16x16_Close"), //NOI18N
    Defaults("Defaults", null), //NOI18N
    DialogError("Error", "64x64_DialogError"), //NOI18N
    DialogInformation("Information", "64x64_DialogInformation"), //NOI18N
    DialogWarning("Warning", "64x64_DialogWarning"), //NOI18N
    Discard("Discard", null), //NOI18N
    Edit("Edit", "16x16_Edit"), //NOI18N
    Exec("Exec", "16x16_Exec"), //NOI18N
    Exit("Exit", "16x16_Exit"), //NOI18N
    Finish("Finish", null), //NOI18N
    Help("Help", "16x16_Help"), //NOI18N
    Hint("Hint", "16x16_Hint"), //NOI18N
    Ignore("Ignore", null), //NOI18N
    List("List", "16x16_List"), //NOI18N
    List2("List", "16x16_List2"), //NOI18N
    Next("Next", "16x16_Next"), //NOI18N
    No("No", "16x16_No"), //NOI18N
    Ok("Ok", "16x16_Ok"), //NOI18N
    Open("Open", null), //NOI18N
    Preview("Preview", "16x16_Preview"), //NOI18N
    Previous("Previous", "16x16_Previous"), //NOI18N
    Recovery("Recovery", "16x16_Recovery"), //NOI18N
    Remove("Remove", "16x16_Remove"), //NOI18N
    Remove2("Remove", "16x16_Remove2"), //NOI18N
    Reset("Reset", null), //NOI18N
    Save("Save", "16x16_Save"), //NOI18N
    SaveAll("Save all", "16x16_SaveAll"), //NOI18N
    SaveAs("Save as...", "16x16_SaveAs"), //NOI18N
    Search("Search", "16x16_Search"), //NOI18N
    Settings("Settings", "16x16_Settings"), //NOI18N
    Swap("Swap", "16x16_Swap"), //NOI18N
    Trash("Trash", "16x16_Trash"), //NOI18N
    Uninstall("Uninstall", "16x16_Uninstall"), //NOI18N
    Yes("Yes", "16x16_Apply"); //NOI18N
    private String text;
    private String icon;
    // </editor-fold>

    IconStock(String text, String icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return new util.I18n(this).translate(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconPath() {
        if (icon == null) {
            return null;
        }

        return String.format("/resource/image/%s.png", icon); //NOI18N
    }

    public javax.swing.ImageIcon getImageIcon() {
        if (icon == null) {
            return null;
        }

        return new javax.swing.ImageIcon(getClass().getResource(getIconPath()));
    }

    public boolean hasIcon() {
        return icon != null;
    }
}
