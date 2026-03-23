/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Representa la entidad Documento dentro del sistema de gestión de impresión.
 * Esta clase almacena las propiedades físicas del archivo y su estado actual
 * dentro del flujo de trabajo (cola de impresión y prioridad).
 * @author karlg
 */
public class Documentos {
    private String nombre;/** Nombre o identificador del archivo */
    private int tamaño; /**Cantidad de Páginas o peso del un documento.*/
    private String tipo;    /** Extensión o formato del archivo (ej: .pdf, .docx, .txt) */
    private boolean enCola; /**Para saber si ya se mandó a imprimir, indica si el documento ya ha sido enviado a la cola de impresión */
    private boolean prioritario;    /**Indica si el documento tiene una marca de prioridad para el sistema de impresión */

    /**
     * Constructor para inicializar un nuevo documento.
     * Por defecto, un documento nuevo no está en cola y no es prioritario.
     * @param nombre El nombre descriptivo del documento.
     * @param tamaño La capacidad que ocupa el archivoo las páginas.
     * @param tipo El formato del archivo.
     */
    public Documentos(String nombre, int tamaño, String tipo) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.tipo = tipo;
        this.enCola = false; //Por defecto, al crearse no está en la cola
        this.prioritario = false;// Estado inicial: sin prioridad especial.
    }

    /**
     * @return true si el documento es considerado de alta prioridad.
     */
    public boolean isPrioritario() {
        return prioritario;
    }

    /**
     * Define si el documento debe ser tratado con prioridad en el sistema.
     * @param prioritario valor booleano de prioridad.
     */
    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    /**
     * @return El nombre del archivo.
     */
    public String getNombre() {    
        return nombre;
    }

    /**
     * @param nombre El nuevo nombre para el archivo.
     */
    public void setNombre(String nombre) {    
        this.nombre = nombre;
    }

    /**
     * @return El tamaño actual del documento en KB.
     */
    public int getTamaño() {   
        return tamaño;
    }

    /**
     * @param tamaño El tamaño a asignar al documento.
     */
    public void setTamaño(int tamaño) {    
        this.tamaño = tamaño;
    }

    /**
     * @return El tipo o extensión del archivo.
     */
    public String getTipo() {    
        return tipo;
    }

    /**
     * @param tipo El formato del archivo (ej: "PDF").
     */
    public void setTipo(String tipo) {    
        this.tipo = tipo;
    }

    /**
     * Consulta el estado de impresión del documento.
     * @return true si el documento ya está esperando en la cola.
     */
    public boolean isEnCola() {    
        return enCola;
    }

    /**
     * Cambia el estado de permanencia en la cola de impresión.
     * @param enCola true para marcar que ya está en cola.
     */
    public void setEnCola(boolean enCola) {    
        this.enCola = enCola;
    }
}
