/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Representa una entrada individual en la cola de impresión.
 * Esta clase funciona como un contenedor que vincula un objeto Documento 
 * con su prioridad (etiqueta de tiempo) y el usuario que solicitó la acción.
 * @author karlg
 */
public class RegistroImpresion {
    private Documentos documento; /** El objeto documento que contiene los datos del archivo (nombre, tamaño, etc.) */
    private long etiquetaTiempo; /** Valor numérico que determina la prioridad en el MinHeap (a menor tiempo, mayor prioridad) */
    private String nombreUsuario; /** Identificador o nombre del usuario propietario del documento */

    /**
     * Constructor para crear un registro de impresión listo para ser encolado.
     * @param documento El objeto de la clase Documentos a imprimir.
     * @param etiquetaTiempo El valor calculado de prioridad según el sistema.
     * @param nombreUsuario El nombre del usuario que envía a imprimir.
     */
    public RegistroImpresion(Documentos documento, long etiquetaTiempo, String nombreUsuario) {
        this.documento = documento;
        this.etiquetaTiempo = etiquetaTiempo;
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return El objeto Documentos asociado a este registro.
     */
    public Documentos getDocumento() {
        return documento;
    }

    /**
     * @return El valor de la etiqueta de tiempo utilizada por el MinHeap.
     */
    public long getEtiquetaTiempo() {
        return etiquetaTiempo;
    }

    /**
     * Permite actualizar la etiqueta de tiempo si el sistema requiere 
     * una re-priorización del documento.
     * @param etiquetaTiempo El nuevo valor de tiempo.
     */
    public void setEtiquetaTiempo(long etiquetaTiempo) {
        this.etiquetaTiempo = etiquetaTiempo;
    }

    /**
     * @return El nombre del usuario que originó la solicitud.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
}
