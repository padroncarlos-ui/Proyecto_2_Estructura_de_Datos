/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Representa a un usuario dentro del sistema de impresión.
 * Cada usuario posee un nombre único, un nivel de prioridad (tipo) 
 * y una colección personal de documentos que puede gestionar.
 * @author karlg
 */
public class Usuario {
    private String nombre; /** Identificador único del usuario (usado como clave en la TablaHash) */
    private String tipo; /** Tipo de prioridad de los usuarios.Nivel de prioridad asignado (ej: "prioridad_alta", "prioridad_media", "prioridad_baja") */
    private Lista<Documentos> documentos;/** Lista simplemente enlazada que contiene los documentos creados por este usuario */

    /**
     * Constructor para crear un nuevo usuario.
     * Inicializa una lista vacía de documentos específica para este perfil.
     * * @param nombre El nombre o ID del usuario.
     * @param tipo El rango de prioridad que determinará el orden en la cola.
     */
    public Usuario(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.documentos = new Lista<>(); //Inicia la lista vacía de documentos.
    }

    /**
     * Registra un nuevo documento en el repositorio personal del usuario.
     * @param doc El objeto Documentos a añadir.
     */
    public void agregarDocumento(Documentos doc) {
        this.documentos.añadir(doc);
    }

    /**
     * Elimina un documento específico de la lista del usuario.
     * @param doc El documento que se desea remover.
     */
    public void eliminarDocumento(Documentos doc) {
        this.documentos.eliminar(doc);
    }

    /**
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return La cadena que representa el nivel de prioridad.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return La lista completa de documentos del usuario.
     */
    public Lista<Documentos> getDocumentos() {
        return documentos;
    }
}
