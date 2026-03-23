/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 * Controlador central del sistema de impresión. 
 * Coordina el registro de usuarios, la gestión de la cola de prioridad 
 * y la lógica de negocio para la asignación de etiquetas de tiempo.
 * @author karlg
 */
public class SistemaImpresion {
    private MinHeap colaImpresion;/** Montículo mínimo que gestiona el orden de salida de los documentos */
    private TablaHash tablaUsuarios;/** Estructura de búsqueda rápida para validar y obtener usuarios por nombre */
    
    private Lista<Usuario> listaUsuarios; //Para mostrar en tablas de la interfaz. Lista secuencial para facilitar el recorrido y visualización en la interfaz */
    private Reloj reloj;/** Referencia al reloj global para marcar la entrada de documentos */

    /**
     * Inicializa los componentes principales del sistema.
     * @param reloj Instancia del reloj que provee el tiempo lógico del sistema.
     */
    public SistemaImpresion(Reloj reloj) {
        this.colaImpresion = new MinHeap(100); //Capacidad inicial de 100 documentos.
        this.tablaUsuarios = new TablaHash(127); //Tamaño primo para reducir colisiones.
        this.listaUsuarios = new Lista<>();
        this.reloj = reloj;
    }

    /**
     * Registra un nuevo usuario tanto en la tabla de búsqueda como en la lista visual.
     * @param nombre Identificador único del usuario.
     * @param tipo Nivel de prioridad (ej: prioridad_alta, media, baja).
     */
    public void registrarUsuario(String nombre, String tipo) {
        Usuario nuevo = new Usuario(nombre, tipo);
        listaUsuarios.añadir(nuevo);
        tablaUsuarios.insertar(nombre, nuevo);
    }

    /**
     * Procesa el envío de un documento a la cola de impresión.
     * Implementa la lógica de prioridad restando una constante al tiempo actual
     * para que los documentos prioritarios "suban" en el MinHeap.
     * @param doc El objeto documento a imprimir.
     * @param user El usuario que solicita la impresión.
     * @param prioritario true si el documento debe saltar el orden cronológico normal.
     */
    public void enviarACola(Documentos doc, Usuario user, boolean prioritario) {
        long etiqueta = reloj.getTiempoActual();

        // Lógica de prioridad: Un tiempo menor en el MinHeap significa mayor prioridad.
        if (prioritario) {
            etiqueta -= 1000000; //Valor lo suficientemente grande para asegurar el primer lugar. 
        }

        RegistroImpresion registro = new RegistroImpresion(doc, etiqueta, user.getNombre());
        colaImpresion.insertar(registro);
        doc.setEnCola(true); //Bloquea el documento para evitar envíos duplicados.
    }

    /**
     * Extrae el documento al frente de la cola (el de menor etiqueta de tiempo).
     * @return El RegistroImpresion procesado o null si la cola está vacía.
     */
    public RegistroImpresion liberarImpresora() {
        RegistroImpresion impreso = colaImpresion.eliminarMin();
        if (impreso != null) {
            impreso.getDocumento().setEnCola(false); //Libera el documento para futuras ediciones.
        }
        return impreso;
    }

    /**
     * Elimina un usuario del sistema de forma integral.
     * @param nombre El nombre del usuario a remover.
     */
    public void eliminarUsuario(String nombre) {
        Usuario u = (Usuario) tablaUsuarios.buscar(nombre);
        if (u != null) {
            listaUsuarios.eliminar(u);
            tablaUsuarios.eliminar(nombre);
        }
    }
    
    // Getters
    public Lista<Usuario> getListaUsuarios() { 
        return listaUsuarios; 
    }
    public MinHeap getColaImpresion() { 
        return colaImpresion; 
    }
    
    public TablaHash getTablaUsuarios() {
        return tablaUsuarios;
    }  
}
