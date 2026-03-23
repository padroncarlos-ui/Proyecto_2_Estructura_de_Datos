/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Clase que representa un nodo genérico para una lista simplemente enlazada.
 * Actúa como un contenedor de datos que mantiene una referencia al siguiente
 * elemento en la secuencia.
 * @author karlg
 * @param <T> El tipo de dato que el nodo almacenará (Usuario, Documento, etc.).
 */
public class Nodo<T> {
    
    private T contenido;/** El objeto o valor almacenado dentro del nodo */
    private Nodo<T> siguiente;/** Referencia (puntero) al siguiente nodo en la lista */

    /**
     * Constructor para crear un nuevo nodo.
     * Inicializa el contenido y establece el siguiente nodo como null por defecto.
     * @param contenido El dato de tipo T que se guardará en este nodo.
     */
    public Nodo(T contenido) {
        this.contenido = contenido;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     * @return El objeto de tipo T contenido.
     */
    public T getContenido() {
        return contenido;
    }

    /**
     * Permite modificar o actualizar el dato almacenado en el nodo.
     * @param contenido El nuevo objeto a almacenar.
     */
    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    /**
     * Obtiene la referencia al siguiente nodo en la estructura.
     * @return El objeto Nodo apuntado, o null si es el final de la lista.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el enlace hacia el siguiente nodo.
     * Es fundamental para las operaciones de inserción y eliminación en la lista.
     * @param siguiente El nodo que seguirá a este en el orden lógico.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
