/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clase que proporciona métodos para exportar datos de un JTable a un archivo de Excel.
 * Utiliza la biblioteca Apache POI para manipular archivos de Excel en formato XLS.
 * 
 * @author AdminOmarGuevara
 */
public class ExportarExcel {

    /**
     * Exporta los datos de un JTable a un archivo de Excel en formato XLS.
     * 
     * @param t El JTable del cual se exportarán los datos.
     * @throws IOException Si ocurre un error de entrada/salida durante la exportación.
     */
    public void exportarExcel(JTable t, String administrador) throws IOException {
        // Crear un cuadro de diálogo para elegir la ubicación y el nombre del archivo
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        // Si el usuario elige la ubicación y el nombre del archivo
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Construir la ruta del archivo y añadir la extensión .xls si no está presente
            String ruta = chooser.getSelectedFile().toString().concat(".xls");

            try {
                // Crear un nuevo archivo XLS
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();

                // Crear un libro de trabajo de Excel
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Mi hoja de trabajo 1");
                hoja.setDisplayGridlines(false);

                // Obtener la fecha actual
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String fechaActual = dateFormat.format(date);

                // Crear una fila especial para la fecha y la etiqueta "Reporte del día"
                Row filaFecha = hoja.createRow(0);
                Cell celdaFecha = filaFecha.createCell(0);
                celdaFecha.setCellValue("Reporte del día:");
                Cell celdaValorFecha = filaFecha.createCell(1);
                celdaValorFecha.setCellValue(fechaActual);

                // Crear una fila especial para el administrador
                Row filaAdministrador = hoja.createRow(1);
                Cell celdaReportePor = filaAdministrador.createCell(0);
                celdaReportePor.setCellValue("Hecho por el administrador indetificado :");
                Cell celdaValorAdministrador = filaAdministrador.createCell(1);
                celdaValorAdministrador.setCellValue(administrador);

                // Agregar encabezados de columnas a la hoja de trabajo
                for (int f = 2; f <= t.getRowCount() + 1; f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        celda.setCellValue(t.getColumnName(c));
                    }
                }

                // Llenar la hoja de trabajo con los datos del JTable
                int filaInicio = 3; // Iniciar desde la cuarta fila
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        // Manejar diferentes tipos de datos y convertirlos a un formato adecuado para Excel
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        }
                    }
                }

                // Escribir el libro de trabajo en el archivo
                libro.write(archivo);
                archivo.close();

                // Abrir el archivo XLS con la aplicación predeterminada
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                // Manejar la excepción aquí
                throw e;
            }
        }
    }
}