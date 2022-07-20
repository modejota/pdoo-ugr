package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class SorpresaIrCarcel extends Sorpresa{

    private Tablero tablero;

    public SorpresaIrCarcel(String texto, Tablero _tablero) {
        super(texto);
        tablero=_tablero;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(jugadorCorrecto(actual,todos)){
           informe(actual,todos);
           (todos.get(actual)).encarcelar(tablero.getCarcel());
        }
    }
    
    @Override
    protected void informe (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
    }
    
} //Fin de clase SorpresaIrCarcel
