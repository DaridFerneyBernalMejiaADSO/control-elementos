/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//import view.Formagregar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import view.FramePrincipal;


/**
 *
 * @author Propietario
 */

public class melemtos {
    
    //Formagregar age = new Formagregar();
    
   
  /**
 * Busca una identificación en la base de datos de personas y realiza acciones en la interfaz gráfica.
 *
 * @param identificacion La identificación a buscar en la base de datos.
 * @param jTabbedPane1 El JTabbedPane que representa las pestañas en la interfaz gráfica.
 */
public void buscarindentificacion(String identificacion, JTabbedPane jTabbedPane1) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Definir la consulta SQL para buscar la identificación en la tabla 'personas'
    String sql = "SELECT identificacion FROM tb_usuario WHERE identificacion = ?";

    try (Connection cn = db.conectar();
         PreparedStatement pst = cn.prepareStatement(sql)) {
        // Establecer el parámetro de la consulta con la identificación proporcionada
        pst.setString(1, identificacion);

        // Ejecutar la consulta y obtener el resultado
        ResultSet rs = pst.executeQuery();

        // Verificar si la identificación fue encontrada en la base de datos
        if (rs.next()) {
            // Mostrar un mensaje de éxito con la identificación encontrada
            JOptionPane.showMessageDialog(null, "Identificación correcta: " + identificacion);

            // Cambiar a una pestaña específica en el JTabbedPane (jTabbedPane1)
            jTabbedPane1.setSelectedIndex(4);

            // Puedes realizar otras acciones en la interfaz gráfica aquí, como actualizar campos, ocultar paneles, etc.
        } else {
            // Mostrar un mensaje indicando que la identificación no se encuentra en la base de datos
            JOptionPane.showMessageDialog(null, "No se encuentra en la base de datos, regístrate.");
        }
    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        JOptionPane.showMessageDialog(null, "Error al buscar : " + e.getMessage());
    }
}
    


public void buscarindentificacion2(String identificacion, JTabbedPane jTabbedPane1) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Definir la consulta SQL para buscar la identificación en la tabla 'personas'
    String sql = "SELECT identificacion FROM tb_usuario WHERE identificacion = ?";

    try (Connection cn = db.conectar();
         PreparedStatement pst = cn.prepareStatement(sql)) {
        // Establecer el parámetro de la consulta con la identificación proporcionada
        pst.setString(1, identificacion);

        // Ejecutar la consulta y obtener el resultado
        ResultSet rs = pst.executeQuery();

        // Verificar si la identificación fue encontrada en la base de datos
        if (rs.next()) {
            // Mostrar un mensaje de éxito con la identificación encontrada
            JOptionPane.showMessageDialog(null, "Identificación correcta: " + identificacion);

            // Cambiar a una pestaña específica en el JTabbedPane (jTabbedPane1)
            jTabbedPane1.setSelectedIndex(1);

            // Puedes realizar otras acciones en la interfaz gráfica aquí, como actualizar campos, ocultar paneles, etc.
        } else {
            // Mostrar un mensaje indicando que la identificación no se encuentra en la base de datos
            JOptionPane.showMessageDialog(null, "No se encuentra en la base de datos, regístrate.");
        }
    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        JOptionPane.showMessageDialog(null, "Error al buscar : " + e.getMessage());
    }
}
    
    
    //aca guarda el elemento 
   /**
 * Guarda un nuevo elemento en la base de datos.
 *
 * @param user El usuario que está realizando la acción.
 * @param identificacion La identificación de la persona asociada al elemento.
 * @param serial El serial del elemento a guardar.
 * @param elemento El nombre o tipo del elemento.
 * @param marca La marca del elemento.
 * @param descripcion La descripción del elemento.
 * @param activo El estado del elemento (puede ser 'ACTIVO' o 'INACTIVO').
 */
public void guardarelemento(String identificacion, String serial, String elemento, String marca, String descripcion, String activo, String Cantidad) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Declarar objetos para la ejecución de la consulta
    Statement st;
    Connection conexion = db.conectar();

    try {
        // Crear un objeto Statement y ejecutar la consulta para insertar el nuevo elemento en la tabla 'elementos'
        st = conexion.createStatement();
        st.executeUpdate("INSERT INTO tb_elementos(nombre_elemento, serial, descripcion, marca) VALUES ('"
                + elemento + "','" + serial + "','" + descripcion + "','" + marca + "')", Statement.RETURN_GENERATED_KEYS);

        // Recuperar el id_elemento generado
        ResultSet rsElemento = st.getGeneratedKeys();
        rsElemento.next();
        int idElemento = rsElemento.getInt(1);

        // Insertar en la tabla 'tb_reporte'
        st.executeUpdate("INSERT INTO tb_reporte(identificacion_fk, fecha_ingreso) VALUES ('"
                + identificacion + "', CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);

        // Recuperar el id_reporte generado
        ResultSet rsReporte = st.getGeneratedKeys();
        rsReporte.next();
        int idReporte = rsReporte.getInt(1);

        // Insertar en la tabla 'tb_detalle_reporte'
        st.executeUpdate("INSERT INTO tb_detalle_reporte(id_reporte_fk, id_elemento_fk, estado, cantidad) VALUES ('"
                + idReporte + "','" + idElemento + "','" + activo + "','" + Cantidad + "')");

        // Mostrar un mensaje de éxito al usuario
        JOptionPane.showMessageDialog(null, "Guardado correctamente");

        // Cerrar la conexión a la base de datos
        conexion.close();

    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "ERROR al guardar ");
    }
}
    
   /**
 * Guarda un nuevo registro de ingreso en la tabla 'registros_ingreso'.
 *
 * @param identificacion La identificación de la persona asociada al registro de ingreso.
 * @param serial El serial del elemento asociado al registro de ingreso.
 */
//public void guardarreporte(String identificacion, String serial) {
//    // Crear una instancia de la clase de conexión a la base de datos
//    ConexionDB db = new ConexionDB();
//
//    // Construir la consulta SQL para insertar el nuevo registro en la tabla 'registros_ingreso'
//    String sql = "INSERT INTO reporte(cedula_personas_fk, id_elemento_fk) VALUES ('" + identificacion + "','" + serial + "');";
//
//    // Declarar objetos para la ejecución de la consulta
//    Statement st;
//    Connection conexion = db.conectar();
//
//    try {
//        // Crear un objeto Statement y ejecutar la consulta
//        st = conexion.createStatement();
//        st.executeUpdate(sql);
//
//        // Mostrar un mensaje de éxito al usuario
//        JOptionPane.showMessageDialog(null, "Guardado correctamente");
//
//        // Cerrar la conexión a la base de datos
//        conexion.close();
//
//    } catch (SQLException e) {
//        // Manejar la excepción mostrando un mensaje de error
//        System.out.println(e);
//        JOptionPane.showMessageDialog(null, "ERROR");
//    }
//}
   
}






