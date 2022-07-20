package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class CasillaJuez extends Casilla{

    private static int carcel;
    
    public CasillaJuez(String nombre_introducido,int num_casilla_carcel){
        super(nombre_introducido);
        carcel=num_casilla_carcel;
    }
    
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            super.informe(actual, todos);
            todos.get(actual).encarcelar(carcel);
        }
    }
    
    //El super indica que usamos un metodo de la clase padre; pero realmente actua como this: es el propio objeto, 
    //por lo que al usar el toString() de la clase padre, donde pone "this.getClass().getName()" saldrá el nombre de esta clase (CasillaJuez)
    @Override
    public String toString(){ 
        return super.toString()     
               + "\n\t Carcel: " + carcel;
    }

} //Fin de la clase CasillaJuez
