/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.consultas;
import model.m_elemento_DAO;
import view.FormLogin;
import model.personas;
import model.melemtos;

//import view.FormRegistro;
import view.FramePrincipal;

/**
 *
 * @author Propietario
 */
//OTRA FORMA DE hacerlo
//   public void iniciar(String user,String pass,FormLogin loginForm){
//   consultas con = new consultas();
//  user= loginForm.jTextUser.getText();
//  pass= loginForm.jTextPass.getText();
//   con.consultarUsuario(user, pass, loginForm);
//con.consultarUsuario(jTextUser.getText(), jTextPass.getText(),this);
// }
public class controlador1 {

    private consultas consultas;
    private personas personas;
    private melemtos melemtos;
    private FramePrincipal FramePrincipal;
    private m_elemento_DAO m_elemento_DAO;
    //private FormUsu FormUsu;
    // FramePrincipal frinterno= new FramePrincipal();
    //   m_elemento_DAO dao=new m_elemento_DAO();

    public controlador1() {
        consultas = new consultas();
        personas = new personas();
        melemtos = new melemtos();
       // FormUsu = new FormUsu();

        FramePrincipal = new FramePrincipal();
        m_elemento_DAO = new m_elemento_DAO();

    }

//    public void guardarUsuario(String usuario, String password) {
//        consultas.guardarUsuario(usuario, password);//trae la funcion de consultas llamada guardar usuario y le pasa los parametros
//    }

    public void iniciar(FormLogin loginForm) {

        // Obtener los valores de usuario y contraseña de los campos de texto del formulario
        String user = loginForm.jTextUser.getText();
        String pass = loginForm.jTextPass.getText();

        // consultas.consultarUsuario(user, pass, loginForm);
        consultas.consultarUsuario1(user, pass, loginForm);
    }
    
  
    
    
    

    public void limpiarAgregar() {
        FramePrincipal.InputCedula.setText(""); // Limpia el campo de cédula
        FramePrincipal.InputNombre.setText(""); // Limpia el campo de nombre
        FramePrincipal.InputApellido.setText(""); // Limpia el campo de apellido
        FramePrincipal.InputCorreo.setText(""); // Limpia el campo de correo
        FramePrincipal.InputTelefono.setText(""); // Limpia el campo de teléfono
        FramePrincipal.InputRol.setSelectedItem("");

    }

    public void agregar(String cedula, String nombre, String apellido, String correo, String telefono, String rol) {
        // vuelve a Verificar si alguno de los campos es nulo o está vacío
        if (cedula == null || cedula.isEmpty()
                || nombre == null || nombre.isEmpty()
                || apellido == null || apellido.isEmpty()
                || correo == null || correo.isEmpty()
                || telefono == null || telefono.isEmpty()
                || rol == null || rol.isEmpty()) {

            //JOptionPane.showMessageDialog(null, "Campos incompletos, Favor llenar todos los campos");
        } else if (!cedula.matches("\\d+")) {
            // Verificar si la cédula contiene solo números (expresión regular "\\d+" significa uno o más dígitos)
            //JOptionPane.showMessageDialog(null, "La cédula debe contener solo números");
        } else {
            // Llama al método guardarpersona si todos los campos no son nulos ni vacíos y la cédula es un número.
            personas.guardarpersona(cedula, nombre, apellido, correo, telefono, rol);
            limpiarAgregar();
            //slimpiarAgregar();

        }
    }

    public void buscar(String indetificacion, JTabbedPane jTabbedPane1) {

        melemtos.buscarindentificacion(indetificacion, jTabbedPane1);
        //FramePrincipal.idpersona.JLabel("indetificacion");

    }
    
    public void buscar2(String indetificacion, JTabbedPane jTabbedPane1) {

        melemtos.buscarindentificacion2(indetificacion, jTabbedPane1);
        //FramePrincipal.idpersona.JLabel("indetificacion");

    }
    
    


    public void agregarelemento( String indetificacion, String serial, String elemento, String marca, String descripcion, String Cantidad) {
        // Validación: Verificar que los argumentos no sean nulos o vacíos
        String activo = "ACTIVADO";
        // String indetificacion = 
        try {
            //Integer.parseInt(serial); // Intenta convertir el serial a un número
            if (elemento != null && !elemento.isEmpty()
                    && marca != null && !marca.isEmpty()
                    && Cantidad !=null && !Cantidad.isEmpty()
                    && descripcion != null && !descripcion.isEmpty()) {
                melemtos.guardarelemento( indetificacion, serial, elemento, marca, descripcion, activo, Cantidad);
            } else {
                // Manejar el caso de datos inválidos o faltantes aquí
                JOptionPane.showMessageDialog(null, "Campos incompletos, Favor llenar todos los campos");
                System.out.println("Error: Los datos son inválidos o faltantes.");
            }
        } catch (NumberFormatException e) {
            // Manejar el caso en el que el serial no sea un número
            //JOptionPane.showMessageDialog(null, "El campo Serial debe ser un número.");
            System.out.println("Error: El campo Serial debe ser un número.");
        }
    }

    //public void reporte(String indetificacion, String serial){
    //}
    public void limpiarelemento() {
        FramePrincipal.Txtserial.setText("");
        FramePrincipal.txtelemento.setText("");
        FramePrincipal.txtmarca.setText("");
        FramePrincipal.txtdescripcion.setText("");

    }

    //DefaultTableModel cargartabla = this.cargartabla();
    //  this.tabla_elementos.setModel(cargartabla);
    public void tablas(JTable tla, String indetificacion) {
        //String identificacion = FramePrincipal.idpersona.getText(); // Corregido el nombre de la variable
        //cargar la tabla actualizada
        DefaultTableModel cargar = m_elemento_DAO.cargartabla(indetificacion);
        // m_elemento_DAO es una instancia de una clase que se utiliza para acceder a la base de datos y recuperar los datos para mostrar en la tabla.

        // Establecer el modelo de la tabla (JTable) con los datos cargados.
        tla.setModel(cargar);
        // Esto establece el modelo de la tabla JTable "tla" con los datos cargados desde la base de datos.
    }

    public void reporte(JTable tabla) {
        DefaultTableModel cargar = m_elemento_DAO.cargarreporte();
        tabla.setModel(cargar);
    }
    
    


    //tla.setModel(cargar);
    public void cambioestado(JTable tabla) {
        
         int row = tabla.getSelectedRow();

    // Verificar si hay una fila seleccionada
    if (row >= 0) {
        String estado = (String) tabla.getValueAt(row, 5); // La columna 5 está en el índice 4 (considerando que los índices comienzan desde 0)

        // Verificar si el estado ya está inactivo
        if (!estado.equals("ACTIVADO")) {
            String numero = (String) tabla.getValueAt(row, 0); // Reemplaza 0 con el índice de la columna que contiene el ID
            int id = Integer.parseInt(numero);
             m_elemento_DAO.cambioactivo(id );
        } else {
            JOptionPane.showMessageDialog(null, "La fila ya está ACTIVADO");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione una fila");
    }
}
        
       
         

    public void cambioInactivo(JTable tabla) {
    int row = tabla.getSelectedRow();

    // Verificar si hay una fila seleccionada
    if (row >= 0) {
        String estado = (String) tabla.getValueAt(row, 5); // La columna 5 está en el índice 4 (considerando que los índices comienzan desde 0)

        // Verificar si el estado ya está inactivo
        if (!estado.equals("DESHABILITADO")) {
            String numero = (String) tabla.getValueAt(row, 0); // Reemplaza 0 con el índice de la columna que contiene el ID
            int id = Integer.parseInt(numero);
            m_elemento_DAO.cambioInactivo(id);
        } else {
            JOptionPane.showMessageDialog(null, "La fila ya está DESHABILITADO");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione una fila");
    }
}
    


    public void Conactualizar(JTable tabla,  String serial, String elemento, String marca, String descripcion, String Cantidad) {
        int row = tabla.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (row >= 0) {
            //obtenemos el id
            String numero = (String) tabla.getValueAt(row, 0);
            System.out.println(numero);
            int id = Integer.parseInt(numero);
            m_elemento_DAO.actualizar( serial, elemento, marca, descripcion, Cantidad, id );
        } else {
            JOptionPane.showMessageDialog(null, "selecione una fila");
        }
    }


}
