package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class SorpresaPorCasaHotel extends Sorpresa {

    private int valor;

    public SorpresaPorCasaHotel(String texto, int _valor) {
        super(texto);
        valor=_valor;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
         if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            (todos.get(actual)).modificarSaldo(valor*(todos.get(actual)).cantidadCasasHoteles()); //PERO ESTO SERIA PERDER DINERO NO?
        }
    }
    
    @Override
    protected void informe (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
    }
    
}
