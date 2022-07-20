package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    //Metodo privado para inicializar algunos atributos
    private void init(){
        barajada=false;
        usadas=0;
        sorpresas=new ArrayList<>(); 
        cartasEspeciales=new ArrayList<>();
    }
    
    //Constructor sin param
    MazoSorpresas(){
        init();
        debug=false;
    }
    
    //Constructor con param
    MazoSorpresas(boolean d){
        init();
        debug=d;
        if(d)
            Diario.getInstance().ocurreEvento("Se ha activado el modo debug del MazoSorpresas");
    }
    
    //Se añade la sorpresa si no está barajado
    void alMazo(Sorpresa s){
        if(!barajada)
            sorpresas.add(s);
    }
    
    Sorpresa getUltimaSorpresa() {
        return ultimaSorpresa;
    } 
    
    
    Sorpresa siguiente(){
            if(!barajada || usadas==sorpresas.size())
                if(!debug){
                Collections.shuffle(sorpresas);
                barajada=true;
                usadas=0;
            }
        usadas++;
        Collections.rotate(sorpresas, sorpresas.size()-1); //Rota los objetos dentro de la coleccion a una distancia de size-1 para dejar la primera sorpresa en la ultima posicion
        ultimaSorpresa=sorpresas.get(sorpresas.size()-1);
        
        return ultimaSorpresa; 
    }
    
    //Sacar la cartaespecial de un mazo a otro
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        if(sorpresas.contains(sorpresa)){
            sorpresas.remove(sorpresa);
            cartasEspeciales.add(sorpresa);
            Diario.getInstance().ocurreEvento("Una carta especial se ha añadido a cartasEspeciales");
        }
    }
    
    //Metodo complementario a inhabilitarCartaEspecial(Sorpresa sorpresa)
    void habilitarCartaEspecial(Sorpresa sorpresa){
        if(cartasEspeciales.contains(sorpresa)){
            cartasEspeciales.remove(sorpresa);
            sorpresas.add(sorpresa);
            Diario.getInstance().ocurreEvento("Una carta especial se ha añadido a sorpresas");
        }
            
    }
    
} //Fin clase

