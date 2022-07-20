package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class SorpresaJugadorEspeculador extends Sorpresa{

    private int valor;

    public SorpresaJugadorEspeculador(String texto,int _valor) {
        super(texto);
        valor=_valor;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
         if(jugadorCorrecto(actual,todos)){
           informe(actual,todos);
           todos.set(actual, new JugadorEspeculador(todos.get(actual),valor));
        }
    }
    
    @Override
    protected void informe (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
    }
    
} //Fin de clase SorpresaJugadorEspeculador
