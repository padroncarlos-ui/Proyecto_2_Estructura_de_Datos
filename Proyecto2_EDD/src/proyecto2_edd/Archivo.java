/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase encargada de la persistencia y procesamiento de archivos de texto.
 * Permite la lectura masiva de usuarios y su integración al sistema de impresión.
 * @author karlg
 */
public class Archivo {
    private Interfaz principal; //Referencia a la interfaz gráfica para notificar estados del proceso.

    
    /**
     * Constructor de la clase Archivo.
     * @param principal Instancia de la interfaz de usuario para reportar mensajes.
     */
    public Archivo(Interfaz principal) {
        this.principal = principal;
    }

     /**
     * Lee un archivo de texto, valida su contenido y registra nuevos usuarios en el sistema.
     * El formato esperado del archivo es: "nombre_usuario, prioridad".
     * @param archivoSeleccionado El objeto File que apunta al archivo de texto a cargar.
     * @param sistema El motor del sistema de impresión donde se registrarán los datos.
     * @return int La cantidad total de usuarios nuevos cargados con éxito.
     */
    public int CargarUsuario(File archivoSeleccionado, SistemaImpresion sistema) {
        String linea;
        int usuariosCargados = 0;
        int duplicados = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoSeleccionado))) { //Uso de try-with-resources para asegurar el cierre del lector de archivos.

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                
                if (linea.isEmpty()) continue; //Ignora las líneas vacías para evitar errores de parseo.

                String[] datos = linea.split(","); //Separar el nombre y la prioridad por el delimitador coma.

                if (datos.length == 2) {
                    String nombre = datos[0].trim();
                    String prioridad = datos[1].trim();

                    if (sistema.getTablaUsuarios().buscar(nombre) != null) { //Validación de duplicados consultando la Tabla Hash de usuarios.

                        duplicados++;
                        continue; 
                    }

                    //Validación rigurosa del tipo de prioridad permitido.
                    if (prioridad.equals("prioridad_alta") || 
                        prioridad.equals("prioridad_media") || 
                        prioridad.equals("prioridad_baja")) {

                        sistema.registrarUsuario(nombre, prioridad);
                        usuariosCargados++;
                    }
                }
            }

            if (usuariosCargados > 0) { //Retroalimentación al usuario según el resultado de la operación.
                principal.setMensajeProceso("Carga exitosa de los usuarios: " + usuariosCargados + " nuevos. ");
            } else if (duplicados > 0) {
                principal.setMensajeProceso("No se añadieron usuarios: todos los del archivo ya existen.");
            }

        } catch (IOException e) {
            principal.setMensajeProceso("Error al leer el archivo."); //Captura de errores de lectura (archivo corrupto o sin permisos)
        }
        return usuariosCargados; //Devuelve la cantidad cargada.
    }
}
