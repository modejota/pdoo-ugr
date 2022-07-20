package civitas;

import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class Tablero {
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int porSalida;
    private boolean tieneJuez;
    
    
    //Constructor basico a partir de la casilla en la que está la carcel.
     public Tablero(int indiceCarcel) {
       if(indiceCarcel >= 1 && indiceCarcel<=19)
        numCasillaCarcel = indiceCarcel;
       else
        numCasillaCarcel = 1;
      
      casillas = new ArrayList<Casilla>();
      casillas.add(new Casilla("Salida"));
      
      porSalida = 0;
      tieneJuez = false;
    }
    
    //Comprobante de que hay un juez y de que la casilla carcel está en el tablero
    private boolean correcto() {
        return (tieneJuez && numCasillaCarcel < casillas.size());
    }
    
    //Comprobante de que hay juez y de que la casilla carcel está en el tablero
    //Comprobante de que la casilla se encuentra en el tablero
    private boolean correcto(int numCasilla) {
        return(correcto() && numCasilla < casillas.size());
    }
    
    int getCarcel() {
        return numCasillaCarcel;
    }
    
    int getPorSalida() {
        if(porSalida > 0) {
            porSalida--;
            return porSalida + 1;
        }
        else
            return porSalida;
    }
    
    //Si la casilla a añadir esta en la posicion de la carcel, creamos carcel y despues la casilla
    //Si no es la posicion de la carcel, creamos la casilla.
    void añadeCasilla(Casilla casilla) {
        if(casillas.size()==numCasillaCarcel){
            casillas.add(new Casilla("Carcel"));  
        }
        casillas.add(casilla); 
        
        if(casillas.size()==numCasillaCarcel){
            casillas.add(new Casilla("Carcel"));  
        }
        
    }
    
    //Si no hay casilla de juez la añadimos y actualizamos.
    void añadeJuez(){
        if(tieneJuez == false){
            casillas.add(new CasillaJuez("Juez",numCasillaCarcel));
            tieneJuez = true;
        }
    }
    
    //Devuelve la casilla de la posicion numCasilla
    public Casilla getCasilla(int numCasilla){
        if(correcto(numCasilla))
            return casillas.get(numCasilla);
        else
            return null;  
    }
    
    //Calcula la nueva posición en el tablero asumiendo que se parte de la posición actual y se avanza una tirada de unidades
    int nuevaPosicion(int actual, int tirada) {
        int nueva_posicion;
        if(!correcto())
            return -1;
        else{
            if(actual+tirada<casillas.size())
                nueva_posicion=actual+tirada;
            else{
                nueva_posicion=(actual+tirada)%casillas.size();
                porSalida++;
            }
        }
        return nueva_posicion;
    }
    
    //Devuelve la tirada de dado que se habría tenido que obtener para ir desde la casilla número origen a la casilla número destino
    int calcularTirada(int origen, int destino) {
        int tirada_res, tirada_aux=destino-origen;
        
        if(tirada_aux>=0)
            tirada_res=tirada_aux;
        else
            tirada_res=tirada_aux+casillas.size();
        
        return tirada_res;
    }
    
} //Final de la clase Tablero

