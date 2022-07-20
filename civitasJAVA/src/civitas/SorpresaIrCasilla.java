package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class SorpresaIrCasilla extends Sorpresa {

    private Tablero tablero;
    private int valor;    

    public SorpresaIrCasilla(String texto, Tablero _tablero, int _valor) {
        super(texto);
        tablero=_tablero;
        valor=_valor;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(jugadorCorrecto(actual,todos)){
           informe(actual,todos);
           //Obtenemos la casilla actual del jugador
           int casilla_actual=(todos.get(actual)).getNumCasillaActual();
           //Calculamos la tirada que habria que obtener para ir a la casilla que diga "valor"
           int tirada=tablero.calcularTirada(casilla_actual, valor);
           //Obtenemos esa nueva posicion del jugador
           int nueva_pos=tablero.nuevaPosicion(casilla_actual, tirada);
           //Movemos al jugador a esa nueva posicion
           (todos.get(actual)).moverACasilla(nueva_pos);
           //Ahora se le dice a la casilla que diga el valor de la sorpresa, que le estamos enviando un jugador, y que lo reciba
           (tablero.getCasilla(valor)).recibeJugador(actual, todos);
        }
    }
    
    @Override
    protected void informe (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
    }

} //Fin de clase SorpresaIrCasilla
