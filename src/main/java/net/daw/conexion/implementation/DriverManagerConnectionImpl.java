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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.daw.conexion.publicinterface.GenericConnectionInterface;
import net.daw.helper.ConnectionClass;

public class DriverManagerConnectionImpl implements GenericConnectionInterface {

    @Override
    public Connection newConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + ConnectionClass.getDatabaseHost() + ":" + ConnectionClass.getDatabasePort() + "/" + ConnectionClass.getDatabaseName(), ConnectionClass.getDatabaseLogin(), ConnectionClass.getDatabasePassword());
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void disposeConnection() {
    }

}
