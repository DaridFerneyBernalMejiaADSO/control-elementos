package model;
import view.FormLogin;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import view.FramePrincipal;

public class consultas {
    
    
    public static int id;
    
//    public void guardarUsuario(String usuario, String password){
//        ConexionDB db = new ConexionDB();
//        String sql = "insert into usuarios(nombre, clave) values ('" + usuario +"', '" + password +"');";
//        Statement st; 
//        Connection conexion = db. conectar();
//        try
//        {
//            st = conexion.createStatement();
//            st.executeUpdate(sql);
//            JOptionPane.showMessageDialog(null, "Guardado correctamente");
//        }catch(SQLException e)
//        {
//            System.out.println(e);
//        }
//    }

   public int consultarUsuario1(String identificacion, String pass, FormLogin loginForm) {
    ConexionDB db = new ConexionDB();
    String sql = "SELECT identificacion_fk, clave FROM tb_logeo WHERE identificacion_fk = ? AND clave = ?";
    
    try (Connection cn = db.conectar();
         PreparedStatement pst = cn.prepareStatement(sql)) {
        pst.setString(1, identificacion);
        pst.setString(2, pass);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            id = rs.getInt("identificacion_fk");
            JOptionPane.showMessageDialog(null, "Login correcto. Bienvenido " + id);

            // Crea una instancia de FramePrincipal sin pasar idUsuario
            FramePrincipal framePrincipal = new FramePrincipal();
            //framePrincipal.mostrarIDUsuario(idUsuario); // Llama a la función para mostrar el idUsuario
            framePrincipal.setVisible(true);

            loginForm.dispose();
            return id;
        } else {
            JOptionPane.showMessageDialog(null, "identificacion  o contraseña incorrectos");
        }
    } catch (SQLException e) {
        System.out.println(e);
        JOptionPane.showMessageDialog(null, "Error al consultar: " + e.getMessage());
    }
    return id;
}

    
    

    
}
