package GUI;

import civitas.CivitasJuego;
import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class TestP5 {
    public static void main(String[] args) {
        
        CivitasView view = new CivitasView();
        Dado.createInstance(view);
        //Dado.getInstance().setDebug(true);
        
        CapturaNombres capturar = new CapturaNombres(view,true);
        ArrayList<String> nombres = new ArrayList<>();
        nombres = capturar.getNombres();
        
        CivitasJuego juego = new CivitasJuego(nombres);
        
        Controlador controlador = new Controlador(juego, view);
        view.setCivitasJuego(juego);
        
        //juego.getJugadorActual().paga(7600);
        
        controlador.juega();

    }
    
} // Fin de la clase TestP5
