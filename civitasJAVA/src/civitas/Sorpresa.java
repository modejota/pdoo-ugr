package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public abstract class Sorpresa {
    protected String texto;

    Sorpresa(String _texto){
        texto=_texto;
    }
    
    //comprueba si el primer parámetro es un índice válido para acceder a los elementos del segundo parámetro.
    public boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos){
        return (actual >= 0 && actual < todos.size()); 
    }
    
    //Informa al diario que se está aplicando una sorpresa a un jugador (se indica el nombre de este)
    protected void informe (int actual, ArrayList<Jugador> todos){ 
        Diario.getInstance().ocurreEvento("Se esta aplicando una sorpresa *" + this.getClass().getSimpleName() + "* al jugador " + todos.get(actual).nombre + " que dice: " + texto);
    }
    
    //Metodo abstracto para overridear
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    @Override
    public String toString (){
        return "\n\n" + "*" + this.getClass().getSimpleName() + "*"
             + "\n\t Texto: " + texto;
    }
  
} //Fin de clase Sorpresa




