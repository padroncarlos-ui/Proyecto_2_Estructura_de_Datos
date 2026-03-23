/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Implementación de un Montículo Mínimo (MinHeap) para gestionar la cola de prioridad.
 * Los elementos se ordenan en base a la etiqueta de tiempo del RegistroImpresion,
 * garantizando que el elemento con el valor menor siempre esté en la raíz (índice 0).
 * @author karlg
 */
public class MinHeap {
    private Interfaz principal;/** Referencia a la interfaz para mostrar errores de desbordamiento */
    private RegistroImpresion[] heap;/** Arreglo que almacena los elementos del montículo */
    private int tamaño; /** Cantidad de elementos actualmente almacenados */
    private int capacidad;  /** Límite máximo de elementos que puede contener el arreglo */

    /**
     * Crea un MinHeap con una capacidad fija.
     * @param capacidad Tamaño máximo del arreglo del heap.
     */
    public MinHeap(int capacidad) {
        this.capacidad = capacidad;
        this.tamaño = 0;
        this.heap = new RegistroImpresion[capacidad];
    }

    /**
     * Inserta un nuevo registro en el heap y restaura la propiedad de montículo.
     * Si el registro tiene un tiempo menor que su padre, "flota" hacia arriba.
     * @param nuevoRegistro El objeto RegistroImpresion a encolar.
     */
    public void insertar(RegistroImpresion nuevoRegistro) {
        if (tamaño == capacidad) {
            String error = "Error: Cola de impresión llena.";
            principal.setMensajeProceso(error);
            return;
        }
        heap[tamaño] = nuevoRegistro; //Se inserta al final del arreglo
        int actual = tamaño;
        tamaño++;
        while (actual > 0 && heap[actual].getEtiquetaTiempo() < heap[padre(actual)].getEtiquetaTiempo()) { //Proceso de Up-Heap (Flotar)
            intercambiar(actual, padre(actual));
            actual = padre(actual);
        }
    }

    /**
     * Extrae y retorna el elemento con la menor etiqueta de tiempo (la raíz).
     * @return El RegistroImpresion con menor tiempo, o null si el heap está vacío.
     */
    public RegistroImpresion eliminarMin() {
        if (tamaño == 0) return null;
        RegistroImpresion raiz = heap[0];
        heap[0] = heap[tamaño - 1]; //Se mueve el último elemento a la raíz y se reduce el tamaño.
        heap[tamaño - 1] = null;
        tamaño--;
        
        // Proceso de Down-Heap (Hundir) para reordenar.
        if (tamaño > 0) {
            hundir(0);
        }

        return raiz;
    }

    /**
     * Mueve un elemento hacia arriba en el árbol hasta que esté en la posición correcta.
     * @param i Índice del elemento a mover.
     */
    public void flotar(int i) {
        while (i > 0 && heap[i].getEtiquetaTiempo() < heap[padre(i)].getEtiquetaTiempo()) {
            intercambiar(i, padre(i));
            i = padre(i);
        }
    }

    /**
     * Método recursivo que mueve un elemento hacia abajo comparándolo con sus hijos.
     * Asegura que el padre siempre sea menor que sus hijos izquierdo y derecho.
     * @param i Índice del elemento que debe hundirse.
     */
    private void hundir(int i) {
        int menor = i;
        int izq = hijoIzquierdo(i);
        int der = hijoDerecho(i);

        if (izq < tamaño && heap[izq].getEtiquetaTiempo() < heap[menor].getEtiquetaTiempo()) {
            menor = izq;
        }

        if (der < tamaño && heap[der].getEtiquetaTiempo() < heap[menor].getEtiquetaTiempo()) {
            menor = der;
        }

        if (menor != i) {
            intercambiar(i, menor);
            hundir(menor);
        }
    }   
    
    /**
     * Genera una representación en cadena del montículo como un árbol binario rotado.
     * Útil para depuración visual en consola o áreas de texto.
     * @param indice Nodo actual.
     * @param nivel Nivel de profundidad para la indentación.
     * @return String con el dibujo del árbol.
     */
    public String ArbolVisual(int indice, int nivel) {       
        if (indice >= tamaño){
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(ArbolVisual(2 * indice + 2, nivel + 1));
        for (int i = 0; i < nivel; i++) {
            sb.append("      "); 
        }
        
        sb.append("Node[").append(heap[indice].getEtiquetaTiempo()).append("]\n");
        sb.append(ArbolVisual(2 * indice + 1, nivel + 1));

        return sb.toString();
    }

    // Métodos de navegación de índices.
    private int padre(int i) { 
        return (i - 1) / 2; 
    }
    private int hijoIzquierdo(int i) { 
        return (2 * i) + 1; 
    }
    private int hijoDerecho(int i) { 
        return (2 * i) + 2; 
    }

    /**
     * Realiza un swap entre dos posiciones del arreglo.
     */
    private void intercambiar(int i, int j) {
        RegistroImpresion temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public RegistroImpresion[] getHeap() { return heap; }
    public int getTamaño() { return tamaño; }
}
