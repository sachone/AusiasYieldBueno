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
import com.jolbox.bonecp.BoneCPConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.daw.conexion.publicinterface.GenericConnectionInterface;
import net.daw.helper.ConnectionClass;

public class BoneConnectionPoolImpl implements GenericConnectionInterface {

    private BoneCP connectionPool = null;

    @Override
    public Connection newConnection() {
        Connection c = null;
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(ConnectionClass.getConnectionChain());
        config.setUsername("root");
        config.setPassword("bitnami");
        config.setMinConnectionsPerPartition(1);
        config.setMaxConnectionsPerPartition(2);
        config.setPartitionCount(1);
        try {
            connectionPool = new BoneCP(config); // setup the connection pool
        } catch (SQLException ex) {
            Logger.getLogger(BoneConnectionPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = connectionPool.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(BoneConnectionPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public void disposeConnection() {
        if (connectionPool != null) {
            connectionPool.close();
        }
    }
}
