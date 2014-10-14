/*
 * Copyright (C) July 2014 Rafael Aznar
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package net.daw.data.publicinterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.helper.FilterBean;


public interface DataInterface {

    public int removeOne(int intId, String strTabla) throws Exception;

    public int insertOne(String strTabla) throws Exception;

    public int setNull(int intId, String strTabla, String strCampo) throws Exception;

    public int updateOne(int intId, String strTabla, String strCampo, String strValor) throws Exception;

    public String getId(String strTabla, String strCampo, String strValor) throws Exception;

    public String getOneFromTable(String strTabla, String strCampo, int id) throws Exception;
    
    public String getOneFromSql(String strSQL) throws Exception;

    public Boolean existsOne(String strTabla, int id) throws Exception;

    public int getPages(String strTabla, int intRegsPerPage, ArrayList<FilterBean> alFilter) throws Exception;

    public int getCount(String strTabla, ArrayList<FilterBean> alFilter) throws Exception;

    public ArrayList<Integer> getPage(String strTabla, int intRegsPerPage, int intPagina, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception;

    public ArrayList<String> getColumnsName(String strTabla) throws Exception;

    public ArrayList<String> getPrettyColumns(String strTabla) throws Exception;

    public void removeSomeId(String strTabla, ArrayList<Integer> Ids) throws SQLException;

    public void removeSomeCondition(String strTabla, String campo, String valor) throws Exception;

}
