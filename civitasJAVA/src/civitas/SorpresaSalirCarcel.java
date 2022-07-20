package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class SorpresaSalirCarcel extends Sorpresa {

    private MazoSorpresas mazo;

    public SorpresaSalirCarcel(String texto, MazoSorpresas _mazo) {
        super(texto);
        mazo=_mazo;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        boolean hay_s = false;
        
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            //preguntar si alguien tiene salvonducto
            for(int i=0; i<todos.size() && !hay_s; i++){
                if(todos.get(i).tieneSalvoconducto()) {
                    hay_s = true;  
                }
            }
           if(!hay_s){
               (todos.get(actual)).obtenerSalvoconducto(this);
               this.salirDelMazo(); 
           }
        }
    }
    
    @Override
    protected void informe (int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
    }

    void salirDelMazo(){
        mazo.inhabilitarCartaEspecial(this);
    }

    void usada (){
        mazo.habilitarCartaEspecial(this);
    }
    
}
