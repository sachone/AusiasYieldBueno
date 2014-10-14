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
package net.daw.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.conexion.implementation.BoneConnectionPoolImpl;
import net.daw.conexion.publicinterface.GenericConnectionInterface;
import net.daw.data.implementation.DaoPersona;
import net.daw.data.implementation.MysqlDataImpl;
import net.daw.pojos.pojoPersona;

/**
 *
 * @author rafa
 */
public class ControlJson extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void retardo(Integer iLast) {
        try {
            Thread.sleep(iLast);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        GenericConnectionInterface DataConnectionSource = null;
        Connection connection = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            DataConnectionSource = new BoneConnectionPoolImpl();
            connection = DataConnectionSource.newConnection();

            //----------------------------------------------------------------------          
            retardo(0); //debug delay
            // get parameters
            String operation = request.getParameter("op");
            String object = request.getParameter("ob");
            // prepare parameters

            //DocumentoProcess p=new DocumentoProcess("ff",connection);
            MysqlDataImpl dataInterface = new MysqlDataImpl(connection);

            /* String jsonResult = "{ ";

             ArrayList<Integer> ids = dataInterface.getPage("persona", 10, 1, null, null);
             ArrayList<String> columnsName = dataInterface.getColumnsName("persona");

             Integer i, j, numero;
             numero = 0;
             for (i = 0; i < ids.size(); i++) {
             jsonResult += "\"persona" + numero + "\" : [ ";
             for (j = 0; j < columnsName.size(); j++) {
             String registro = dataInterface.getOneFromTable("persona", columnsName.get(j), ids.get(i));
             jsonResult += " {\"" + columnsName.get(j) + "\" : \"" + registro + "\"}";

             if (j < (columnsName.size() - 1)) {
             jsonResult += ",";
             }
             }
             jsonResult += "]";
             if (i < (ids.size() - 1)) {
             jsonResult += ", ";
             }
             numero++;
             }
             jsonResult += "}";*/
            DaoPersona oPersona = new DaoPersona(connection);
            
            pojoPersona persona = new pojoPersona();

            persona.setId(0);
            persona.setNombre("Luis");
            persona.setEdad(42);
            persona.setTelefono(655421259);
            persona.setDireccion("C/de la constitucion");

            pojoPersona persona1 = oPersona.set(persona);
            
            Integer id = oPersona.get(persona1).getId();
            String nombre = oPersona.get(persona1).getNombre();
            Integer edad = oPersona.get(persona1).getEdad();
            Integer telefono = oPersona.get(persona1).getTelefono();
            String direccion = oPersona.get(persona1).getDireccion();
            
            
             String jsonResult = "{ " + id + " }, {" + nombre + "}, {" + edad + "}, {" + telefono + "}, {" + direccion + "}";
                   

            
            
            
            
            //send the result to the client
            request.setAttribute("contenido", jsonResult);
            getServletContext().getRequestDispatcher("/jsp/messageAjax.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ControlJson.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //important to close connection
            if (connection != null) {
                connection.close();
            }
            DataConnectionSource.disposeConnection();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ControlJson.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ControlJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
