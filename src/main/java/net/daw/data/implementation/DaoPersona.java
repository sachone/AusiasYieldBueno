/*
 * Copyright (C) 2014 al037542
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
package net.daw.data.implementation;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.data.publicinterface.DaoInterface;
import net.daw.data.publicinterface.DataInterface;
import net.daw.helper.FilterBean;
import net.daw.pojos.pojoPersona;

/**
 *
 * @author al037542
 */
public class DaoPersona implements DaoInterface{
    
    Connection connection = null;
    MysqlDataImpl oMysql;
    Integer id; 
    
    
    public DaoPersona(Connection pooledConnection) {
        connection = pooledConnection;
        oMysql = new MysqlDataImpl(connection);
        
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        return oMysql.getColumnsName("persona");
    }

    @Override
    public ArrayList<String> getPrettyColumnsNames() throws Exception {
       return  oMysql.getPrettyColumns("persona");
    }

    @Override
    public pojoPersona set(pojoPersona oPersona) throws Exception {
        ArrayList<String>columnsName = oMysql.getColumnsName("persona");
        if(oPersona.getId() > 0){
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(1), oPersona.getNombre());
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(2), oPersona.getEdad().toString());
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(3), oPersona.getTelefono().toString());
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(4), oPersona.getDireccion());
           
        }else{
            id = oMysql.insertOne("persona");
            oPersona.setId(id);
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(1), oPersona.getNombre());
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(2), oPersona.getEdad().toString());
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(3), oPersona.getTelefono().toString());
            oMysql.updateOne(oPersona.getId(), "persona", columnsName.get(4), oPersona.getDireccion());
        }
        return oPersona;
    }

    @Override
    public void remove(pojoPersona oPersona) throws Exception {
        oMysql.removeOne(oPersona.getId(), "persona");
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<pojoPersona> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public pojoPersona get(pojoPersona oPersona) throws Exception {
        if(oPersona.getId() > 0){
            ArrayList<String>columnsName = oMysql.getColumnsName("persona");
            
            oPersona.setNombre(oMysql.getOneFromTable("persona", columnsName.get(1), oPersona.getId()));
            oPersona.setEdad(Integer.parseInt(oMysql.getOneFromTable("persona", columnsName.get(2), oPersona.getId())));
            oPersona.setTelefono(Integer.parseInt(oMysql.getOneFromTable("persona", columnsName.get(3), oPersona.getTelefono())));
            oPersona.setNombre(oMysql.getOneFromTable("persona", columnsName.get(4), oPersona.getId()));
        }
        return oPersona;
    }
    
}
