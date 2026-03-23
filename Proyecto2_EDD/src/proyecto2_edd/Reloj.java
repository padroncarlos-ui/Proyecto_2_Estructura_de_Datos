/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Gestiona el tiempo dentro de la aplicación mediante un hilo de ejecución secundario.
 * Controla tanto el reloj de pared (hora actual) como un cronómetro interno 
 * que sirve de base para las etiquetas de tiempo de la cola de impresión.
 * * @author karlg
 * @version 1.0
 */
public class Reloj {
    /** Contador incremental que representa los segundos transcurridos desde el inicio del programa */
    public long contadorSegundos; 
    /** Objeto Timer de Swing que ejecuta una acción cada intervalo definido (1000ms) */
    private Timer timer;
    /** Formateador para presentar la hora en formato de 24 horas (HH:mm:ss) */
    private SimpleDateFormat formatoHora;

    /**
     * Constructor del Reloj. Configura el Timer para actualizar la interfaz cada segundo.
     * @param labelHora Etiqueta de la interfaz donde se mostrará la hora real.
     * @param labelCronometro Etiqueta donde se mostrará el tiempo lógico acumulado en segundos.
     */
    public Reloj(JLabel labelHora, JLabel labelCronometro) {
        this.contadorSegundos = 0;
        this.formatoHora = new SimpleDateFormat("HH:mm:ss");

        this.timer = new Timer(1000, new ActionListener() { //Inicialización del Timer con un retardo de 1 segundo.
            @Override
            public void actionPerformed(ActionEvent e) {
 
                labelHora.setText(formatoHora.format(new Date())); //Obtiene la hora del sistema y la formatea para el JLabel.

                contadorSegundos++; //Incrementa el cronómetro interno.
                labelCronometro.setText("Tiempo transcurrido: " + contadorSegundos + "s"); //Actualiza el contador visual en la interfaz.
            }
        });
    }

    /**
     * Inicia la ejecución del cronómetro y las actualizaciones de la interfaz.
     */
    public void iniciar() {
        timer.start();
    }

    /**
     * Detiene el cronómetro de ser necesario.
     */
    public void detener() {
        timer.stop();
    }

    /**
     * Obtiene el valor actual del contador de segundos.
     * Este valor es crítico para registrar el momento exacto en que un documento entra a la cola.
     * @return Segundos totales transcurridos desde que se inició el reloj.
     */
    public long getTiempoActual() {
        return contadorSegundos;
    }
}
