<%-- 
 Copyright (C) July 2014 Rafael Aznar

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
--%>



<%@page import="net.daw.helper.AppInformation"%>


<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
    <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Ajax Yield 2014-2015</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <!--[if lt IE 7]>
        <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->



        <div class="container">
            <div class="jumbotron">
                <h1>Bienvenidos a <%=AppInformation.getAppName()%> 2014</h1>
                <h3>Versión (v.01) de 1/10/2014</h3>
                <h5>Desarrollo de aplicaciones web. CPIFP Ausiàs March. Curso 2014-2015</h5>    
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="contenido">Hola mundo!!</div>                  
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="contenido2"></div>   
            </div>
            <div class="row">
                <div class="col-md-12"><hr><footer><p>&copy; Rafael Aznar (2014)</p></footer></div>   
            </div>
        </div>  



        <!-- carga de javascript   -->

        <script type="text/javascript"  src="./js/vendor/jquery-1.11.1.min.js"></script>
        <script type="text/javascript"  src="./js/vendor/bootstrap.min.js"></script>
        <script type="text/javascript"  src="./js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>




        <script type="text/javascript">

            var path = '<%=request.getContextPath()%>';



            $(document).ready(function () {

                $.getJSON('/ausiasYieldDatabase/json', {format: "json"}, function (data) {

                    tabla = "<table class='table'><thead><tr><th>id</th><th>nombre</th>\n\
            <th>edad</th><th>telefono</th><th>direccion</th></tr></thead><tbody>";


                    $.each(data, function (posicion, persona) {
                        tabla += "<tr>";
                        tabla += "<td>" + persona[0].id + "</td>";
                        tabla += "<td>" + persona[1].nombre + "</td>";
                        tabla += "<td>" + persona[2].edad + "</td>";
                        tabla += "<td>" + persona[3].telefono + "</td>";
                        tabla += "<td>" + persona[4].direccion + "</td>";
                        tabla += "</tr>";
                    })
                    tabla += "</tbody></table>";
                    document.getElementById("contenido2").innerHTML = tabla;


                });

            });

            /*  $.ajax({
             url: "/ausiasYieldDatabase/json",
             type: "GET",
             dataType: "json",
             succes: function (source) {
             tabla = "<table class='table'><thead><tr><th>id</th><th>nombre</th>\n\
             <th>edad</th><th>telefono</th><th>direccion</th></tr></thead><tbody>";
             
             
             $.each(source, function (posicion, persona) {
             tabla += "<tr>";
             tabla += "<td>" + persona[0].id + "</td>";
             tabla += "<td>" + persona[1].nombre + "</td>";
             tabla += "<td>" + persona[2].edad + "</td>";
             tabla += "<td>" + persona[3].telefono + "</td>";
             tabla += "<td>" + persona[4].direccion + "</td>";
             tabla += "</tr>";
             })
             tabla += "</tbody></table>";
             document.getElementById("contenido2").innerHTML = tabla;
             }
             
             });
             
             });*/


        </script>
    </body>
</html>

