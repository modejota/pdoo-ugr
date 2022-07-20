package GUI;

import civitas.CivitasJuego;
import civitas.OperacionesJuego;
import civitas.GestionesInmobiliarias;
import civitas.OperacionInmobiliaria;
import civitas.SalidasCarcel;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class Controlador {
    private CivitasJuego juego;
    private CivitasView vista;
    
    Controlador(CivitasJuego _juego,CivitasView _vista){
        juego=_juego;
        vista=_vista;
    }
    
    void juega(){
        vista.setCivitasJuego(juego);
        vista.setTablero();
        
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            
            OperacionesJuego operacion=juego.siguientePaso(vista);
            vista.mostrarSiguienteOperacion(operacion);
            
            if(operacion!=OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()){
                switch(operacion){
                    case COMPRAR:
                        Respuestas eleccion=vista.comprar();
                        if(eleccion==Respuestas.SI){
                            juego.comprar();
                        }
                        juego.siguientePasoCompletado(operacion);
                        break;
                    case GESTIONAR:
                        vista.gestionar(juego.getJugadorActual());
                        
                        GestionesInmobiliarias gestion=GestionesInmobiliarias.values()[vista.getGestion()];
                        int propiedad=vista.getPropiedad();
                        OperacionInmobiliaria operacion_inmobiliaria=new OperacionInmobiliaria(gestion,propiedad);
                        
                        switch(gestion){
                            case VENDER:
                                juego.vender(propiedad);
                                break;
                                
                            case HIPOTECAR:
                                juego.hipotecar(propiedad);
                                break;
                                
                            case CANCELAR_HIPOTECA:
                                juego.cancelarHipoteca(propiedad);
                                break;
                                
                            case CONSTRUIR_CASA:
                                juego.construirCasa(propiedad);
                                break;
                                
                            case CONSTRUIR_HOTEL:
                                juego.construirHotel(propiedad);
                                break;
                                
                            case TERMINAR:
                                juego.siguientePasoCompletado(operacion);
                                break;
                        }
                        break;
                        
                    case SALIR_CARCEL:
                        SalidasCarcel metodo_salida=vista.salirCarcel();
                        if(metodo_salida==SalidasCarcel.PAGANDO){
                            juego.salirCarcelPagando();
                        }
                        else
                            juego.salirCarcelTirando();
                        
                        juego.siguientePasoCompletado(operacion);
                        break;
                }
            }
        }
        vista.actualizarVista(); //Cuando se salga del while es porque se acabo el juego, luego muestro el ranking
    } 


}   //Fin clase Controlador
