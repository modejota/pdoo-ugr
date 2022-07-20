package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class Casilla{
    //Esta clase "padre" se usará para las casillas descanso. A partir de esta, heredan las demás casillas.
    
    protected String nombre;

    //Este es para las casillas DESCANSO
    Casilla(String nombre_introducido){
        nombre = nombre_introducido;
    }

    protected void informe(int actual, ArrayList<Jugador> todos){ 
        Diario.getInstance().ocurreEvento("El jugador " + (todos.get(actual)).nombre + " ha caido en la casilla: " + this.toString());
    }

    //Ahora, este recibe de descanso no hace nada en esta clase: se redefinira en cada clase hija para que hagan lo especifico 
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        informe(actual,todos);
    }

    @Override
    public String toString() {
         return "\n\n" + "*" + this.getClass().getSimpleName() + "*"
              + "\n\t Nombre: " + nombre;
    }
    
    
    public boolean jugadorCorrecto (int actual, ArrayList<Jugador> todos) {
        return (actual >= 0 && actual < todos.size());
    }

    public String getNombre() {
        return nombre;
    }
    
    TituloPropiedad getTituloPropiedad(){
        return null;
    }
    
} //Fin de la clase Casilla





