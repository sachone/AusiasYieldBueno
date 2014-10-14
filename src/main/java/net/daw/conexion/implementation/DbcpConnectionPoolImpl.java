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
package net.daw.conexion.implementation;

import com.jolbox.bonecp.BoneCP;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.daw.conexion.publicinterface.GenericConnectionInterface;
import org.apache.commons.dbcp.BasicDataSource;

public class DbcpConnectionPoolImpl implements GenericConnectionInterface {

    BasicDataSource basicDataSource = null;

    @Override
    public Connection newConnection() {
        Connection c = null;
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("bitnami");
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ausiaxContent");
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setMaxActive(100);
        basicDataSource.setMaxWait(10000);
        basicDataSource.setMaxIdle(10);

        return c;
    }

    @Override
    public void disposeConnection() {
        try {
            if (basicDataSource != null) {
                basicDataSource.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbcpConnectionPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
