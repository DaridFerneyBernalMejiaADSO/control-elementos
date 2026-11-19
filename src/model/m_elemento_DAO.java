/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List; // Importa la clase java.util.List en lugar de java.awt.List

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class m_elemento_DAO {


   

    public void actualizar(String serial, String elemento, String marca, String descripcion, String cantidad,  int id_elemento) {
        // Crear una instancia de la clase ConexionDB para manejar la conexión a la base de datos.
        ConexionDB db = new ConexionDB();

        // Declarar dos objetos PreparedStatement para ejecutar consultas SQL parametrizadas.
        PreparedStatement psElementos = null;
        PreparedStatement psRegistrosIngreso = null;

        // Definir las sentencias SQL de actualización para las tablas 'elementos' y 'registros_ingreso'.
        String updateElementosSql = "UPDATE tb_elementos SET nombre_elemento = ?, serial = ?, descripcion = ?, marca = ? WHERE id_elemento = ?";
        String updateRegistrosIngresoSql = "UPDATE  tb_detalle_reporte  SET  cantidad = ? WHERE id_elemento_fk = ?";

        try (Connection conexion = db.conectar()) {
            // Actualizar la tabla 'elementos'
            psElementos = conexion.prepareStatement(updateElementosSql);
            psElementos.setString(1, elemento);
            psElementos.setString(2, serial);
            psElementos.setString(3, descripcion);
            psElementos.setString(4, marca);
            psElementos.setInt(5, id_elemento);

            // Ejecutar la actualización en la tabla 'elementos' y obtener el número de filas actualizadas.
            int rowsUpdatedElementos = psElementos.executeUpdate();

            // Actualizar la tabla 'registros_detallado'
            psRegistrosIngreso = conexion.prepareStatement(updateRegistrosIngresoSql);
            psRegistrosIngreso.setString(1, cantidad);
            psRegistrosIngreso.setInt(2, id_elemento);
            

            // Ejecutar la actualización en la tabla 'registros_ingreso' y obtener el número de filas actualizadas.
            int rowsUpdatedRegistrosIngreso = psRegistrosIngreso.executeUpdate();

            // Verificar si se realizaron actualizaciones en ambas tablas y mostrar mensajes apropiados.
            if (rowsUpdatedElementos > 0 && rowsUpdatedRegistrosIngreso > 0) {
                JOptionPane.showMessageDialog(null, "Actualización exitosa ");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el registro a actualizar en una o ambas tablas");
            }
        } catch (SQLException e) {
            // Capturar excepciones de SQL, imprimir el error en la consola y mostrar un mensaje de error.
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al actualizar fdfdgf");
        } finally {
            // Cerrar los recursos (PreparedStatement)
            try {
                if (psElementos != null) {
                    psElementos.close();
                }
                if (psRegistrosIngreso != null) {
                    psRegistrosIngreso.close();
                }
            } catch (SQLException ex) {
                // Capturar excepciones al cerrar recursos y mostrar un mensaje de error.
                System.out.println("Error al cerrar los recursos: " + ex.getMessage());
            }
        }
    }


 public static boolean cambioactivo(int idelemento) {
    Connection conexion = null;
    PreparedStatement psDetalleReporte = null;
    PreparedStatement psReporte = null;

    try {
        ConexionDB db = new ConexionDB();
        conexion = db.conectar();

        // Iniciar la transacción
        conexion.setAutoCommit(false);

        // Actualizar estado en tb_detalle_reporte
        String sqlDetalleReporte = "UPDATE tb_detalle_reporte SET estado = 'ACTIVADO' WHERE id_elemento_fk = ?";
        psDetalleReporte = conexion.prepareStatement(sqlDetalleReporte);
        psDetalleReporte.setInt(1, idelemento);

        int filasAfectadasDetalleReporte = psDetalleReporte.executeUpdate();

        // Actualizar fechas en tb_reporte
        String sqlReporte = "UPDATE tb_reporte SET fecha_ingreso = CURRENT_TIMESTAMP, fecha_salida = null WHERE id_reporte IN (SELECT id_reporte_fk FROM tb_detalle_reporte WHERE id_elemento_fk = ?)";
        psReporte = conexion.prepareStatement(sqlReporte);
        psReporte.setInt(1, idelemento);

        int filasAfectadasReporte = psReporte.executeUpdate();

        // Confirmar la transacción si ambas actualizaciones fueron exitosas
        if (filasAfectadasDetalleReporte == 1 && filasAfectadasReporte == 1) {
            conexion.commit();
            JOptionPane.showMessageDialog(null, "Se cambió el estado a ACTIVADO");
            return true; // Devolver true para indicar éxito
        } else {
            // Deshacer la transacción si alguna actualización falló
            conexion.rollback();
            
            JOptionPane.showMessageDialog(null, "No se cambió el estado o no se actualizaron las fechas.");
            return false; // Devolver false si no se actualizaron todas las filas
        }
    } catch (Exception e) {
        System.out.println(e);
        // Manejar la excepción
        e.printStackTrace(); // Imprimir la pila de excepción para propósitos de depuración
        return false; // Devolver false para indicar falla
    } finally {
        // Restaurar el modo de autocommit y cerrar recursos
        try {
            if (conexion != null) {
                conexion.setAutoCommit(true);
                conexion.close();
            }
            if (psDetalleReporte != null) {
                psDetalleReporte.close();
            }
            if (psReporte != null) {
                psReporte.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

   public static boolean cambioInactivo(int idelemento) {
    Connection conexion = null;
    PreparedStatement psDetalleReporte = null;
    PreparedStatement psReporte = null;

    try {
        ConexionDB db = new ConexionDB();
        conexion = db.conectar();

        // Iniciar la transacción
        conexion.setAutoCommit(false);

        // Actualizar estado en tb_detalle_reporte
        String sqlDetalleReporte = "UPDATE tb_detalle_reporte SET estado = 'DESHABILITADO' WHERE id_elemento_fk = ?";
        psDetalleReporte = conexion.prepareStatement(sqlDetalleReporte);
        psDetalleReporte.setInt(1, idelemento);

        int filasAfectadasDetalleReporte = psDetalleReporte.executeUpdate();

        // Actualizar fechas en tb_reporte
        String sqlReporte = "UPDATE tb_reporte SET fecha_salida = CURRENT_TIMESTAMP WHERE id_reporte IN (SELECT id_reporte_fk FROM tb_detalle_reporte WHERE id_elemento_fk = ?)";
        
//      
       // String insrep ="INSERT INTO tb_reporte()VALUES() "
        psReporte = conexion.prepareStatement(sqlReporte);
        psReporte.setInt(1, idelemento);

        int filasAfectadasReporte = psReporte.executeUpdate();

        // Confirmar la transacción si ambas actualizaciones fueron exitosas
        if (filasAfectadasDetalleReporte == 1 && filasAfectadasReporte == 1) {
            conexion.commit();
            JOptionPane.showMessageDialog(null, "Se cambió el estado a DESHABILITADO ");
            return true; // Devolver true para indicar éxito
        } else {
            // Deshacer la transacción si alguna actualización falló
            conexion.rollback();
            
            JOptionPane.showMessageDialog(null, "No se cambió el estado o no se actualizaron las fechas.");
            return false; // Devolver false si no se actualizaron todas las filas
        }
    } catch (Exception e) {
        System.out.println(e);
        // Manejar la excepción
        e.printStackTrace(); // Imprimir la pila de excepción para propósitos de depuración
        return false; // Devolver false para indicar falla
    } finally {
        // Restaurar el modo de autocommit y cerrar recursos
        try {
            if (conexion != null) {
                conexion.setAutoCommit(true);
                conexion.close();
            }
            if (psDetalleReporte != null) {
                psDetalleReporte.close();
            }
            if (psReporte != null) {
                psReporte.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
   
   
   
   
   

    /**
     * Carga los datos de la tabla 'elementos' relacionados con una persona
     * identificada por su cédula.
     *
     * @param identificacion La cédula de la persona para la cual se cargan los
     * elementos.
     * @return DefaultTableModel que contiene los datos de la tabla 'elementos'.
     */
  public DefaultTableModel cargartabla(String identificacion) {
    // Crear un modelo de tabla predeterminado
    DefaultTableModel modo = new DefaultTableModel();

    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();
    Connection conexion = db.conectar();

    // Agregar columnas al modelo
    modo.addColumn("id_elemento ");
    modo.addColumn("nombre_elemento");
    modo.addColumn("serial");
    modo.addColumn("descripcion");
    modo.addColumn("marca");
    modo.addColumn("estado");
    modo.addColumn("cantidad");

    // Crear la consulta SQL que une las tablas tb_elementos y tb_detalle_reporte
    String sql = "SELECT e.id_elemento, e.nombre_elemento, e.serial, e.descripcion, e.marca, d.estado, d.cantidad " +
                 "FROM tb_elementos e " +
                 "JOIN tb_detalle_reporte d ON e.id_elemento = d.id_elemento_fk " +
                 "JOIN tb_reporte r ON d.id_reporte_fk = r.id_reporte " +
                 "WHERE r.identificacion_fk = ?";

    try {
        // Crear una declaración preparada y establecer el parámetro de la identificación
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, identificacion);

        // Ejecutar la consulta
        ResultSet rs = ps.executeQuery();

        // Recorrer el conjunto de resultados y agregar filas al modelo
        while (rs.next()) {
            String[] datos = new String[7]; // Un arreglo para almacenar los datos de una fila

            // Recorrer las columnas y obtener los datos
            for (int i = 0; i < 7; i++) {
                datos[i] = rs.getString(i + 1);
            }

            // Agregar la fila al modelo de tabla
            modo.addRow(datos);
        }

        // Cerrar la conexión a la base de datos
        conexion.close();

    } catch (Exception e) {
        // Manejar la excepción de manera significativa para tu aplicación
        e.printStackTrace();
    }

    // Devolver el modelo de tabla lleno de datos
    return modo;
}

public DefaultTableModel cargarreporte() {
    // Crear un modelo de tabla predeterminado
    DefaultTableModel modo = new DefaultTableModel();

    // Agregar columnas al modelo
    modo.addColumn("id_detalle_reporte");
    modo.addColumn("nombre_elemento");
    modo.addColumn("serial");
    modo.addColumn("descripcion");
    modo.addColumn("marca");
    modo.addColumn("estado");
    modo.addColumn("cantidad");
    modo.addColumn("identificacion");
    modo.addColumn("nombre");
    modo.addColumn("apellido");
    modo.addColumn("telefono");
    modo.addColumn("correo"); // Agregamos la columna de correo
    modo.addColumn("rol");
    modo.addColumn("fecha_ingreso");
    modo.addColumn("fecha_salida");

    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();
    String sql = "SELECT d.id_detalle_reporte, e.nombre_elemento, e.serial, e.descripcion, e.marca, d.estado, d.cantidad, " +
                 "u.identificacion, u.nombre, u.apellido, u.telefono, u.correo, u.rol, r.fecha_ingreso, r.fecha_salida " +
                 "FROM tb_elementos e " +
                 "JOIN tb_detalle_reporte d ON e.id_elemento = d.id_elemento_fk " +
                 "JOIN tb_reporte r ON d.id_reporte_fk = r.id_reporte " +
                 "JOIN tb_usuario u ON r.identificacion_fk = u.identificacion";

    try (
        // Establecer la conexión a la base de datos y crear una declaración preparada para la consulta
        Connection conexion = db.conectar(); 
        PreparedStatement ps = conexion.prepareStatement(sql);
    ) {
        // Ejecutar la consulta
        ResultSet rs = ps.executeQuery();

        // Recorrer el conjunto de resultados y agregar filas al modelo
        while (rs.next()) {
            String[] datos = new String[15];

            // Recorrer las columnas y obtener los datos
            for (int i = 1; i <= 15; i++) { // Corregir el índice de inicio y el número total de columnas
                datos[i - 1] = rs.getString(i);
            }

            // Agregar la fila al modelo
            modo.addRow(datos);
        }

    } catch (Exception e) {
        // Manejar la excepción de manera significativa para tu aplicación
        e.printStackTrace();
    }

    // Devolver el modelo de tabla lleno de datos
    return modo;
} 

    
    //arreglado
public DefaultTableModel buscarreporte(String buscar) {
    // Crear un modelo de tabla predeterminado
    DefaultTableModel modo = new DefaultTableModel();

    // Agregar columnas al modelo
    modo.addColumn("id_detalle_reporte");
    modo.addColumn("nombre_elemento");
    modo.addColumn("serial");
    modo.addColumn("descripcion");
    modo.addColumn("marca");
    modo.addColumn("estado");
    modo.addColumn("cantidad");
    modo.addColumn("identificacion");
    modo.addColumn("nombre");
    modo.addColumn("apellido");
    modo.addColumn("telefono");
    modo.addColumn("correo");  // Agregamos la columna de correo
    modo.addColumn("rol");
    modo.addColumn("fecha_ingreso");
    modo.addColumn("fecha_salida");

    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();
    String sql = "SELECT d.id_detalle_reporte, e.nombre_elemento, e.serial, e.descripcion, e.marca, d.estado, d.cantidad, " +
                 "r.identificacion_fk, u.nombre, u.apellido, u.telefono, u.correo, u.rol, r.fecha_ingreso, r.fecha_salida " +
                 "FROM tb_elementos e " +
                 "JOIN tb_detalle_reporte d ON e.id_elemento = d.id_elemento_fk " +
                 "JOIN tb_reporte r ON d.id_reporte_fk = r.id_reporte " +
                 "JOIN tb_usuario u ON r.identificacion_fk = u.identificacion " +
                 "WHERE d.id_detalle_reporte LIKE ? OR e.nombre_elemento LIKE ? OR e.serial LIKE ? OR e.descripcion LIKE ? OR e.marca LIKE ? OR d.estado LIKE ? OR d.cantidad LIKE ? OR r.identificacion_fk LIKE ? OR u.nombre LIKE ? OR u.apellido LIKE ? OR u.telefono LIKE ? OR u.correo LIKE ? OR u.rol LIKE ? OR r.fecha_ingreso LIKE ? OR r.fecha_salida LIKE ?";

    try (
        // Establecer la conexión a la base de datos y crear una declaración preparada para la consulta
        Connection conexion = db.conectar();
        PreparedStatement ps = conexion.prepareStatement(sql);
    ) {
        // Configuración de los parámetros de la consulta preparada
        for (int i = 1; i <= 15; i++) { // Ajustar el índice y el número total de parámetros
            ps.setString(i, "%" + buscar + "%");
        }

        // Ejecutar la consulta
        try (ResultSet rs = ps.executeQuery()) {
            // Recorrer el conjunto de resultados y agregar filas al modelo
            while (rs.next()) {
                String[] datos = new String[15]; // Ajustar el tamaño del array según el número de columnas

                // Recorrer las columnas y obtener los datos
                for (int i = 1; i <= 15; i++) { // Ajustar el índice y el número total de columnas
                    datos[i - 1] = rs.getString(i);
                }

                // Agregar la fila al modelo
                modo.addRow(datos);
            }
        }
    } catch (Exception e) {
        // Manejar la excepción de manera significativa para tu aplicación
        e.printStackTrace();
    }

    // Devolver el modelo de tabla lleno de datos
    return modo;
}
 
}
