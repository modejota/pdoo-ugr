package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class CasillaSorpresa extends Casilla{

    private MazoSorpresas mazo;
    
    public CasillaSorpresa(MazoSorpresas _mazo,String nombre_introducido) {
        super(nombre_introducido);
        mazo=_mazo;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual,todos)){
            Sorpresa sorpresa=mazo.siguiente();
            informe(actual,todos);
            sorpresa.aplicarAJugador(actual, todos);
        }
    }
    
    @Override
    public String toString(){
        return super.toString();
    }
    
} //Fin de la clase CasillaSorpresa
