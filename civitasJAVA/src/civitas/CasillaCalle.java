package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class CasillaCalle extends Casilla{

    private TituloPropiedad tituloPropiedad;
    private float importe;
    
    public CasillaCalle(String nombre_introducido,TituloPropiedad titulo,float _importe){
        super(nombre_introducido);
        tituloPropiedad=titulo;
        importe=_importe;
    }
    
    @Override
    void recibeJugador(int actual,ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            Jugador nuevo=todos.get(actual);
            
            if(!tituloPropiedad.tienePropietario())
                nuevo.puedeComprarCasilla(); 
            else
                tituloPropiedad.tramitarAlquiler(nuevo);
        }
    }
    
    @Override
    public String toString(){
        return super.toString()
                + "\n\t Importe: " + importe
                + "\n\t TituloPropiedad: " + tituloPropiedad.getNombre();
    }
    
    @Override
    TituloPropiedad getTituloPropiedad(){
        return tituloPropiedad;
    }
    
} //Fin de la clase CasillaCalle
