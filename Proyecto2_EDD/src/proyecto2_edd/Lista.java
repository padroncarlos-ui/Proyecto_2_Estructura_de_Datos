/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Estructura de datos lineal de tipo Lista Simplemente Enlazada.
 * Utiliza genéricos para permitir el almacenamiento de cualquier tipo de objeto.
 * @author karlg
 * @param <T> El tipo de elemento almacenado en la lista.
 */
public class Lista<T>{
    private Nodo<T> cabeza; /**Referencia al primer nodo de la lista */    
    private int tamaño; /**Contador de elementos presentes en la lista */

    /**
     * Constructor que inicializa una lista vacía.
     */
    public Lista() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un nuevo elemento al final de la lista.
     * Si la lista está vacía, el nuevo nodo se convierte en la cabeza.
     * @param contenido El objeto de tipo T que se desea almacenar.
     */
    public void añadir(T contenido) {
        Nodo<T> nuevoNodo = new Nodo<>(contenido);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> temp = cabeza;
            while (temp.getSiguiente() != null) { //Recorrido hasta el último nodo.
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    /**
     * Busca y elimina la primera aparición de un elemento específico.
     * Utiliza el método .equals() para comparar el contenido.
     * @param contenido El objeto que se desea remover de la lista.
     */
    public void eliminar(T contenido) {
        if (cabeza == null) return;

        if (cabeza.getContenido().equals(contenido)) { //Caso especial: el elemento a eliminar es la cabeza.
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return;
        }

        Nodo<T> temp = cabeza;
        while (temp.getSiguiente() != null && !temp.getSiguiente().getContenido().equals(contenido)) { //Búsqueda del nodo anterior al que se desea eliminar.
            temp = temp.getSiguiente();
        }

        if (temp.getSiguiente() != null) { //Si se encontró el elemento, se "salta" el nodo para desenlazarlo.
            temp.setSiguiente(temp.getSiguiente().getSiguiente());
            tamaño--;
        }
    }

    /**
     * Recupera el contenido de un nodo basándose en su posición de índice.
     * @param indice Posición del elemento (empezando desde 0).
     * @return El contenido del nodo en esa posición, o null si el índice es inválido.
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) return null;
        Nodo<T> temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.getSiguiente();
        }
        return temp.getContenido();
    }

    /**
     * @return La cantidad de elementos actuales en la lista.
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @return true si la lista no contiene elementos, false en caso contrario.
     */
    public boolean esVacia() {
        return cabeza == null;
    }
}
