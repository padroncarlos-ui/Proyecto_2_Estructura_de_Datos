/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Implementación de una Tabla Hash con encadenamiento para la gestión de usuarios.
 * Utiliza un arreglo de Listas Enlazadas para resolver colisiones.
 * @author karlg
 */
public class TablaHash {
    private Nodo<EntradaHash>[] tabla; /** Arreglo de cubetas que almacenan los nodos de entradas */
    private int tamaño; /** Tamaño total del arreglo */
    private int cantidadElementos; /** Contador de elementos totales insertados */

    /**
     * Clase interna para representar el par clave-valor.
     */
    private class EntradaHash {
        String clave; //Nombre del usuario.
        Object valor; //El objeto Usuario completo.

        EntradaHash(String clave, Object valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    /**
     * Constructor de la Tabla Hash.
     * @param tamañoInicial Se recomienda un número primo para distribuir mejor los datos.
     */
    public TablaHash(int tamañoInicial) {
        this.tamaño = tamañoInicial;
        this.tabla = new Nodo[tamaño];
        this.cantidadElementos = 0;
    }

    /**
     * Algoritmo de dispersión (Hash Function). 
     * Calcula un índice basado en la representación numérica de los caracteres.
     * @param clave Texto a transformar en índice.
     * @return Posición dentro del arreglo (índice).
     */
    private int funcionHash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (31 * hash + clave.charAt(i)) % tamaño; //Se utiliza el multiplicador 31 para reducir colisiones.
        }
        return Math.abs(hash);
    }

    /**
     * Inserta un nuevo elemento. Si la clave ya existe, actualiza el valor.
     * Maneja colisiones mediante encadenamiento al final de la lista del índice.
     * @param clave Nombre del usuario.
     * @param valor Objeto Usuario.
     */
    public void insertar(String clave, Object valor) {
        int indice = funcionHash(clave);
        EntradaHash nuevaEntrada = new EntradaHash(clave, valor);
        Nodo<EntradaHash> nuevoNodo = new Nodo<>(nuevaEntrada);

        if (tabla[indice] == null) {
            tabla[indice] = nuevoNodo;
        } else {
            Nodo<EntradaHash> temp = tabla[indice];
            while (temp != null) {
                //El usuario ya existe, se actualiza su información.
                if (temp.getContenido().clave.equals(clave)) {
                    temp.getContenido().valor = valor;
                    return;
                }
                if (temp.getSiguiente() == null) break;
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo); //Se agrega al final de la lista en ese índice.
        }
        cantidadElementos++;
    }

    /**
     * Recupera un objeto basado en su clave.
     * @param clave Nombre a buscar.
     * @return El objeto asociado o null si no existe.
     */
    public Object buscar(String clave) {
        int indice = funcionHash(clave);
        Nodo<EntradaHash> temp = tabla[indice];

        while (temp != null) { //Recorrido de la lista vinculada en el índice calculado.
            if (temp.getContenido().clave.equals(clave)) {
                return temp.getContenido().valor;
            }
            temp = temp.getSiguiente();
        }
        return null; //No se encontró.
    }

    /**
     * Elimina una entrada de la tabla basándose en la clave.
     * @param clave Nombre del usuario a remover.
     */
    public void eliminar(String clave) {
        int indice = funcionHash(clave);
        Nodo<EntradaHash> actual = tabla[indice];
        Nodo<EntradaHash> anterior = null;

        while (actual != null) {
            if (actual.getContenido().clave.equals(clave)) {
                if (anterior == null) {
                    tabla[indice] = actual.getSiguiente(); //Es el primer elemento de la lista en ese índice.
                } else {
                    
                    anterior.setSiguiente(actual.getSiguiente()); //Se desenlaza el nodo intermedio.
                }
                cantidadElementos--;
                return;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }
}
