
package model;
import com.sun.org.apache.xml.internal.utils.res.XResourceBundleBase;
import controller.controlador1;
import view.FormLogin;

import view.FramePrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import view.FramePrincipal;


public class personas {
 private FramePrincipal FramePrincipal;    

  
    /**
 * Guarda una nueva persona en la base de datos.
 *
 * @param identificacion La cédula de la persona.
 * @param nombre El nombre de la persona.
 * @param apellido El apellido de la persona.
 * @param correo El correo de la persona.
 * @param telefono El teléfono de la persona.
 * @param rol El rol de la persona.
 */
public void guardarpersona(String identificacion, String nombre, String apellido, String correo, String telefono, String rol) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Construir la consulta SQL para insertar la nueva persona en la tabla 'personas'
    String sql = "INSERT INTO tb_usuario (identificacion , nombre, apellido, correo, telefono, rol) VALUES ('"
        + identificacion + "','" + nombre + "','" + apellido + "','" + correo + "','" + telefono + "','" + rol + "')";

    // Declarar objetos para la ejecución de la consulta
    Statement st;
    Connection conexion = db.conectar();

    try {
        // Crear un objeto Statement y ejecutar la consulta
        st = conexion.createStatement();
        st.executeUpdate(sql);

        // Mostrar un mensaje de éxito al usuario
        JOptionPane.showMessageDialog(null, "Guardado correctamente");

        // Cerrar la conexión a la base de datos
        conexion.close();

    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        //System.out.println(e);
        JOptionPane.showMessageDialog(null, "ERROR IDENTIFICACION YA ESTÁ REGISTRADA" );
        System.out.println(e);
    }
}

public void guardarpersona2(String identificacion, String nombre, String apellido, String correo, String telefono, String rol,JTabbedPane jTabbedPane1) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Construir la consulta SQL para insertar la nueva persona en la tabla 'personas'
    String sql = "INSERT INTO tb_usuario (identificacion , nombre, apellido, correo, telefono, rol) VALUES ('"
        + identificacion + "','" + nombre + "','" + apellido + "','" + correo + "','" + telefono + "','" + rol + "')";

    // Declarar objetos para la ejecución de la consulta
    Statement st;
    Connection conexion = db.conectar();

    try {
        // Crear un objeto Statement y ejecutar la consulta
        st = conexion.createStatement();
        st.executeUpdate(sql);

        // Mostrar un mensaje de éxito al usuario
        JOptionPane.showMessageDialog(null, "Guardado correctamente");
        jTabbedPane1.setSelectedIndex(0);

        // Cerrar la conexión a la base de datos
        conexion.close();

    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        //System.out.println(e);
        JOptionPane.showMessageDialog(null, "ERROR IDENTIFICACION YA ESTÁ REGISTRADA" );
        System.out.println(e);
    }
}




 




public void actualizarPersona(String identificacion, String nombre, String apellido, String correo, String telefono, String rol) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Construir la consulta SQL para actualizar la persona en la tabla 'personas'
    String sql = "UPDATE tb_usuario SET nombre='" + nombre + "', apellido='" + apellido + "', correo='" + correo + "', telefono='" + telefono + "', rol='" + rol + "' WHERE identificacion='" + identificacion + "'";

    // Declarar objetos para la ejecución de la consulta
    Statement st;
    Connection conexion = db.conectar();

    try {
        // Crear un objeto Statement y ejecutar la consulta
        st = conexion.createStatement();
        int filasActualizadas = st.executeUpdate(sql);

        // Verificar si se realizaron actualizaciones
        if (filasActualizadas > 0) {
            // Mostrar un mensaje de éxito al usuario
            JOptionPane.showMessageDialog(null, "Actualizado correctamente");
        } else {
            // Mostrar un mensaje si no se encontró la persona con la identificación proporcionada
            JOptionPane.showMessageDialog(null, "No se encontró ninguna persona con la identificación: " + identificacion);
        }

        // Cerrar la conexión a la base de datos
        conexion.close();

    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        JOptionPane.showMessageDialog(null, "Error al intentar actualizar la persona: " + e.getMessage());
        e.printStackTrace();
    }
}


public void cargarDatosPersona(String identificacion, JTextField nombreField, JTextField apellidoField, JTextField correoField, JTextField telefonoField, JComboBox<String> rolComboBox) {
    // Crear una instancia de la clase de conexión a la base de datos
    ConexionDB db = new ConexionDB();

    // Construir la consulta SQL para obtener los datos de la persona
    String sql = "SELECT nombre, apellido, telefono, correo,  rol FROM tb_usuario WHERE identificacion='" + identificacion + "'";

    // Declarar objetos para la ejecución de la consulta
    Statement st;
    Connection conexion = db.conectar();

    try {
        // Crear un objeto Statement y ejecutar la consulta
        st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        // Verificar si se encontró la persona
        if (rs.next()) {
            // Obtener los datos de la consulta
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String telefono = rs.getString("telefono");
            String correo = rs.getString("correo");
            String rol = rs.getString("rol");

            // Llenar los campos de la interfaz gráfica
            nombreField.setText(nombre);
            apellidoField.setText(apellido);
            telefonoField.setText(telefono);
            correoField.setText(correo);
            // Aquí debes establecer el valor seleccionado en el ComboBox para el rol
            rolComboBox.setSelectedItem(rol);
        } else {
            // Mostrar un mensaje si no se encontró la persona con la identificación proporcionada
            JOptionPane.showMessageDialog(null, "No se encontró ninguna persona con la identificación: " + identificacion);
        }

        // Cerrar la conexión a la base de datos
        conexion.close();

    } catch (SQLException e) {
        // Manejar la excepción mostrando un mensaje de error
        JOptionPane.showMessageDialog(null, "Error al intentar cargar los datos de la persona: " + e.getMessage());
        e.printStackTrace();
    }
}


}
