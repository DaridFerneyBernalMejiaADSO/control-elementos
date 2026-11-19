/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import model.ExportarExcel;
import model.m_elemento_DAO;
import model.melemtos;
import model.personas;

import view.FramePrincipal;
import view.Usuarios;

/**
 *
 * @author 57318
 */
public class Controlador3 implements ActionListener {
     FramePrincipal  pricipal = new FramePrincipal();
    m_elemento_DAO elemento = new m_elemento_DAO();
    ExportarExcel exporexel = new ExportarExcel();
    personas personas = new personas();
  
    melemtos melementos = new melemtos();
    
    Usuarios  Usuarios = new Usuarios();
    
    
    public  Controlador3(Usuarios Usu){
        
        this.Usuarios = Usu;
    
    }
    
    
   
            
            public void agregar(String cedula, String nombre, String apellido, String correo, String telefono, String rol, JTabbedPane jTabbedPane1) {
        // vuelve a Verificar si alguno de los campos es nulo o está vacío
        if (cedula == null || cedula.isEmpty()
                || nombre == null || nombre.isEmpty()
                || apellido == null || apellido.isEmpty()
                || correo == null || correo.isEmpty()
                || telefono == null || telefono.isEmpty()
                || rol == null || rol.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Campos incompletos, Favor llenar todos los campos");
        } else if (!cedula.matches("\\d+")) {
            // Verificar si la cédula contiene solo números (expresión regular "\\d+" significa uno o más dígitos)
            //JOptionPane.showMessageDialog(null, "La cédula debe contener solo números");
        } else {
            // Llama al método guardarpersona si todos los campos no son nulos ni vacíos y la cédula es un número.
            personas.guardarpersona2(cedula, nombre, apellido, correo, telefono, rol, jTabbedPane1);
            //limpiarAgregar();
            //slimpiarAgregar();
            this.limpiar();

        }
    }
            
            
              public void limpiarelemento()
    {
         Usuarios.Txtserial.setText("");
         Usuarios.txtelemento.setText("");
         Usuarios.txtmarca.setText("");
         Usuarios.txtdescripcion.setText("");
         Usuarios.TxtCantidad.setText("");
                
                
    
    }
      
            
 public void actualizar(MouseEvent e) {
       //esta funcion cuando seleciono un elemento en la tabla se me pone en los inpust 
    // Obtener la fila en la que se hizo clic en la tabla de elementos
    int fila = Usuarios.tabla_elementos.rowAtPoint(e.getPoint());

    // Verificar si la fila es válida (mayor o igual a 0)
    if (fila >= 0) {
        // Obtener y establecer valores en los campos de texto de la interfaz gráfica
        Usuarios.Txtserial.setText(Usuarios.tabla_elementos.getValueAt(fila, 2).toString());
        Usuarios.txtelemento.setText(Usuarios.tabla_elementos.getValueAt(fila, 1).toString());
        Usuarios.txtmarca.setText(Usuarios.tabla_elementos.getValueAt(fila, 4).toString());
        Usuarios.txtdescripcion.setText(Usuarios.tabla_elementos.getValueAt(fila, 3).toString());
        Usuarios.TxtCantidad.setText(Usuarios.tabla_elementos.getValueAt(fila, 6).toString());
    }
}
              
              
      public void limpiar(){
        Usuarios.InputCedula.setText(""); // Limpia el campo de cédula
Usuarios.InputNombre.setText(""); // Limpia el campo de nombre
Usuarios.InputApellido.setText(""); // Limpia el campo de apellido
Usuarios.InputCorreo.setText(""); // Limpia el campo de correo
Usuarios.InputTelefono.setText(""); // Limpia el campo de teléfono
Usuarios.InputRol.setSelectedItem(""); 

    }
      
      
       public void ActualizarPerfil(String identificacion, String nombre, String apellido, String correo, String telefono, String rol) {
    // Validar que los campos obligatorios no sean nulos o vacíos
    if (identificacion == null || identificacion.isEmpty() ||
        nombre == null || nombre.isEmpty() ||
        apellido == null || apellido.isEmpty() ||
        correo == null || correo.isEmpty() ||
        telefono == null || telefono.isEmpty() ||
        rol == null || rol.isEmpty()) {
        
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
        return; // Salir del método si algún campo es nulo o vacío
    }

    // Llamar al método actualizarPersona solo si las validaciones son exitosas
    personas.actualizarPersona(identificacion, nombre, apellido, correo, telefono, rol);
    Actualizarlimpiar();
    //aca limpiar
}
       
         public void Actualizarlimpiar(){
      
Usuarios.NombreActualizar.setText(""); // Limpia el campo de nombre
Usuarios.ApellidoActualizar.setText(""); // Limpia el campo de apellido
Usuarios.CorreoActualizar.setText(""); // Limpia el campo de correo
Usuarios.TelefonoActualizar.setText(""); // Limpia el campo de teléfono
Usuarios.RolActualizar.setSelectedItem(""); 

    }
         
         
   public void traerdatosActualizar(String identificacion) {
    // Crear una instancia de la clase de la interfaz gráfica
   //Usuarios usuarios = new Usuarios();

    // Obtener referencias a los campos de texto y ComboBox de la interfaz
    JTextField nombreField = Usuarios.NombreActualizar;
    JTextField apellidoField = Usuarios.ApellidoActualizar;
    JTextField correoField = Usuarios.CorreoActualizar;
    JTextField telefonoField = Usuarios.TelefonoActualizar;
    JComboBox<String> rolComboBox = Usuarios.RolActualizar;

    // Llamar al método en el modelo para cargar los datos de la persona
    personas.cargarDatosPersona(identificacion, nombreField, apellidoField, correoField, telefonoField, rolComboBox);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
