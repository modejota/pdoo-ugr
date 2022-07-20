package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class CasillaImpuesto extends Casilla{

    private float importe;
    
    public CasillaImpuesto(String nombre_introducido,float _importe) {
        super(nombre_introducido);
        importe=_importe;
    }
    
    @Override
    void recibeJugador(int actual,ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos); 
            todos.get(actual).pagaImpuesto(importe);
        }
    }
    
    @Override
    public String toString(){
        return super.toString() 
                + "\n\t Importe: " + importe;
    }
    
} //Fin de la clase CasillaImpuesto
