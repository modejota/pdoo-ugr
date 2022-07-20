package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */
public class SorpresaPorJugador extends Sorpresa {

    private int valor;

    public SorpresaPorJugador(String texto, int _valor) {
        super(texto);
        valor=_valor;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
         if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            Sorpresa sorpresa_menos_actual=new SorpresaPagarCobrar("Dando dinero(si valor positivo)/Recibiendo dinero(si valor negativo)",(this.valor)*-1);
            //Ahora hay que aplicarselo con aplicarAJugador_pagarCobrar a todos los jugadores menos al actual;
            for(int i=0; i<todos.size();i++){
                if(i!=actual) //Esto es para que no se lo aplique al jugador actual
                    sorpresa_menos_actual.aplicarAJugador(i, todos);
            }
                
            Sorpresa sorpresa_del_actual=new SorpresaPagarCobrar("Recibiendo dinero(si valor positivo)/Dando dinero(si valor negativo)",(this.valor)*(todos.size()-1));
            //Ahora hay que aplicarselo solo al jugador actual 
            sorpresa_del_actual.aplicarAJugador(actual, todos);

        }
    }
    
    @Override
    protected void informe (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
    }

} //Fin de clase SorpresaPorJugador
