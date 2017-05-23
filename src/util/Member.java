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
 * Simple user support for words.
 */
public class Member {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    protected int id;
    protected String name;
    protected java.sql.Timestamp insertTime;
    protected java.sql.Timestamp updateTime;
    // </editor-fold>

    public Member() {
        id = Settings.MemberId.getIntValue();
        name = Settings.MemberName.getStringValue();
        insertTime = DateTime.now();
        updateTime = DateTime.now();
    }

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
        insertTime = DateTime.now();
        updateTime = DateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public java.sql.Timestamp getInsertTime() {
        return insertTime;
    }

    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInsertTime(java.sql.Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public void setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }

}
