    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import view.FramePrincipal;
import model.m_elemento_DAO;
import model.ExportarExcel;
import model.personas;
/**
 *
 * @author Propietario
 */
public  class controlador2 implements ActionListener{
    FramePrincipal  pricipal = new FramePrincipal();
    m_elemento_DAO elemento = new m_elemento_DAO();
    ExportarExcel exporexel = new ExportarExcel();
    personas personas = new personas();
    

   

    public controlador2(FramePrincipal pri) {
        
        
        this.pricipal = pri;
        
        
    }

   public void actualizar(MouseEvent e) {
       //esta funcion cuando seleciono un elemento en la tabla se me pone en los inpust 
    // Obtener la fila en la que se hizo clic en la tabla de elementos
    int fila = pricipal.tabla_elementos.rowAtPoint(e.getPoint());

    // Verificar si la fila es válida (mayor o igual a 0)
    if (fila >= 0) {
        // Obtener y establecer valores en los campos de texto de la interfaz gráfica
        pricipal.Txtserial.setText(pricipal.tabla_elementos.getValueAt(fila, 2).toString());
        pricipal.txtelemento.setText(pricipal.tabla_elementos.getValueAt(fila, 1).toString());
        pricipal.txtmarca.setText(pricipal.tabla_elementos.getValueAt(fila, 4).toString());
        pricipal.txtdescripcion.setText(pricipal.tabla_elementos.getValueAt(fila, 3).toString());
        pricipal.TxtCantidad.setText(pricipal.tabla_elementos.getValueAt(fila, 6).toString());
    }
}
    
    
      public void limpiarelemento()
    {
         pricipal.Txtserial.setText("");
         pricipal.txtelemento.setText("");
         pricipal.txtmarca.setText("");
         pricipal.txtdescripcion.setText("");
         pricipal.TxtCantidad.setText("");
                
                
    
    }
      
       public void limpiar(){
        pricipal.InputCedula.setText(""); // Limpia el campo de cédula
pricipal.InputNombre.setText(""); // Limpia el campo de nombre
pricipal.InputApellido.setText(""); // Limpia el campo de apellido
pricipal.InputCorreo.setText(""); // Limpia el campo de correo
pricipal.InputTelefono.setText(""); // Limpia el campo de teléfono
pricipal.InputRol.setSelectedItem(""); 

    }
       
       public void verificar1(){
               controlador1 con = new controlador1();
        //verifica que la cedula solo lleve numeros y todos los compos llenos
        String cedula = pricipal.InputCedula.getText();
String nombre = pricipal.InputNombre.getText();
String apellido = pricipal.InputApellido.getText();
String correo = pricipal.InputCorreo.getText();
String telefono = pricipal.InputTelefono.getText();
String rol = (String) pricipal.InputRol.getSelectedItem();
 if (!cedula.matches("\\d+")) {
        // Verificar si la cédula contiene solo números (expresión regular "\\d+" significa uno o más dígitos)
        JOptionPane.showMessageDialog(null, "La cédula debe contener solo números");
}
if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() || rol == null || rol.isEmpty()) {
    // Al menos uno de los campos está vacío, muestra un mensaje de error.
    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Favor llenarlos.");
}

else {
    // Todos los campos están completos, llama al método para agregar los datos.
    con.agregar(cedula, nombre, apellido, correo, telefono, rol);
    // También puedes agregar aquí la lógica para limpiar los campos después de agregar.
    
    this.limpiar();
}
     
       }
       
       
  
       
    public void exportarexel (JTable tabla, String administrador) throws IOException{
        
        exporexel.exportarExcel(tabla, administrador);

}   
     

      

       public void buscador(String buscar){
           
           DefaultTableModel model = elemento.buscarreporte(buscar);
           pricipal.tabla_registro.setModel(model);
       
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
      
pricipal.NombreActualizar.setText(""); // Limpia el campo de nombre
pricipal.ApellidoActualizar.setText(""); // Limpia el campo de apellido
pricipal.CorreoActualizar.setText(""); // Limpia el campo de correo
pricipal.TelefonoActualizar.setText(""); // Limpia el campo de teléfono
pricipal.RolActualizar.setSelectedItem(""); 

    }
       
       
       
         public void traerdatosActualizar(String identificacion) {
    // Crear una instancia de la clase de la interfaz gráfica
   //Usuarios usuarios = new Usuarios();

    // Obtener referencias a los campos de texto y ComboBox de la interfaz
    JTextField nombreField = pricipal.NombreActualizar;
    JTextField apellidoField = pricipal.ApellidoActualizar;
    JTextField correoField = pricipal.CorreoActualizar;
    JTextField telefonoField = pricipal.TelefonoActualizar;
    JComboBox<String> rolComboBox = pricipal.RolActualizar;

    // Llamar al método en el modelo para cargar los datos de la persona
    personas.cargarDatosPersona(identificacion, nombreField, apellidoField, correoField, telefonoField, rolComboBox);
}
       


       

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   
}




