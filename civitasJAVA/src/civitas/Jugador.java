package civitas;

import java.util.ArrayList;
import GUI.*;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class Jugador implements Comparable<Jugador>{ 
    
    protected static final int CasasMax = 4;
    protected static final int CasasPorHotel = 4;
    protected static final int HotelesMax = 4;
    protected static final float PasoPorSalida = 1000;
    protected static final float PrecioLibertad = 200;
    protected boolean encarcelado;
    protected boolean puedeComprar;
    protected String nombre;
    protected int numCasillaActual;
    protected float saldo;
    private static final float SaldoInicial = 7500;
    protected ArrayList<TituloPropiedad> propiedades = new ArrayList<>();
    protected SorpresaSalirCarcel salvoconducto = null; //Si el jugador cae en la sorpresa de SALIRCARCEL, se la da el salvoconducto(y sale del mazo). 
    //Si luego ese jugador va a la carcel, el salvoconducto lo gasta y se libra de ir a la carcel(ni si quiera va a la casilla de la carcel, sigue por donde va. Y el salvoconducto vuelve al mazo) 
    
    //Constructor de la clase
    Jugador(String _nombre) {
        nombre = _nombre;
        encarcelado = false;
        numCasillaActual = 0;
        saldo = SaldoInicial;
        puedeComprar = true;
    }
    
    //Constructor de copia de la clase
    protected Jugador (Jugador otro) {
        encarcelado =  otro.encarcelado;
        nombre = new String(otro.nombre);
        numCasillaActual = otro.numCasillaActual;
        puedeComprar = otro.puedeComprar;
        saldo = otro.saldo;
        propiedades = otro.propiedades;
    }

    
    protected boolean debeSerEncarcelado() {
       if(encarcelado)
           return false;
       else{
            if(tieneSalvoconducto()){
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("Se ha librado de ir a la carcel. Ha usado el salvoconducto");
                return false;
            }
            else
                return true;
       }
    }
    
    boolean enBancarrota() {
        return (saldo < 0);
    }
   
    boolean encarcelar(int numCasillaCarcel) {
        if(debeSerEncarcelado()) {
            moverACasilla(numCasillaCarcel);
            encarcelado = true;
            Diario.getInstance().ocurreEvento("Se ha encarcelado al jugador");
        }
        return encarcelado;
    }
    
    boolean obtenerSalvoconducto(SorpresaSalirCarcel s) {
        if(!encarcelado) {
            salvoconducto = s;
            return true;
        }
        else
            return false;
    }
    
    void perderSalvoconducto() {
        salvoconducto.usada();
        salvoconducto = null;
    }
    
    boolean tieneSalvoconducto(){
        return salvoconducto != null;
    }
 
    boolean puedeComprarCasilla() {
       puedeComprar = !encarcelado; //puedecomprar es el contrario de encarcelado
       return puedeComprar;
   }
   
    public boolean paga(float cantidad) {
       return modificarSaldo(-1*cantidad);
    }
    
    boolean pagaImpuesto(float cantidad) {
        if(encarcelado)
            return false;
        else
            return paga(cantidad);
    }
    
    boolean pagaAlquiler(float cantidad) {
        if(encarcelado)
            return false;
        else
            return paga(cantidad);
    }
    
    boolean recibe(float cantidad) {
        if(encarcelado)
            return false;
        else
            return modificarSaldo(cantidad);
    }
    
    boolean modificarSaldo(float cantidad) {
        saldo=saldo+cantidad;
        Diario.getInstance().ocurreEvento("Ahora el saldo de " + this.nombre + " es: " + saldo);
        return true;     
    }
    
   boolean moverACasilla(int numCasilla) {
        if(encarcelado)
            return false;
        else {
            numCasillaActual = numCasilla;
            puedeComprar = false;
            Diario.getInstance().ocurreEvento("Se ha movido al jugador " + nombre + " a la casilla " + numCasilla);
            return true;
        }
    }
    
    private boolean puedoGastar(float precio) {
        if(encarcelado)
            return false;
        else
            return (saldo>=precio);
    }


    boolean vender (int ip) {
        if(!encarcelado){
            if(existeLaPropiedad(ip)) {    
                if((propiedades.get(ip)).vender(this)){
                    propiedades.remove(ip);
                    Diario.getInstance().ocurreEvento("Se ha vendido el título de propiedad");
                    return true; 
                }
            }
            else 
             return false; 
        }
        return false;
    }
    

    boolean tieneAlgoQueGestionar(){
        return !propiedades.isEmpty();
    }
    
    private boolean puedeSalirCarcelPagando() {
        return (saldo>=PrecioLibertad);
    }
    
    boolean salirCarcelPagando() {
        if(puedeSalirCarcelPagando() && encarcelado) {
            paga(PrecioLibertad);
            encarcelado = false;
            Diario.getInstance().ocurreEvento("El jugador ha salido de la cárcel pagando");
            return true;
        } 
        else
            return false;
    }
    
    boolean salirCarcelTirando() {
        boolean sale=Dado.getInstance().salgoDeLaCarcel();
        if(sale){ 
            encarcelado = false;
            Diario.getInstance().ocurreEvento("Se ha salido de la carcel.");
        }
        return sale;
    }
    
    boolean pasaPorSalida() {
        modificarSaldo(PasoPorSalida);
        Diario.getInstance().ocurreEvento("Se ha pasado por la salida.");
        return true;
    }
    
    //Reescrito de acuerdo a los diagramas que se proporcionan en la practica 3
    boolean cancelarHipoteca(int ip) {
        boolean result = false;
        if (encarcelado)
            return result;
         
        if(existeLaPropiedad(ip)) {
            TituloPropiedad propiedad = propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            boolean puedoGastar = puedoGastar(cantidad);
            
            if(puedoGastar) {
                result = propiedad.cancelarHipoteca(this);
                
                if(result)
                   Diario.getInstance().ocurreEvento("El jugador " +nombre+ " cancela la hipoteca de la propiedad " + ip);
            }
        }
        
        return result;
    }
    
    //Reescrito a partir de los diagramas de la practica 3
    boolean construirCasa(int ip){
        boolean result=false, puedoEdificarCasa=false;
        
        if(encarcelado)                                          //1
            return result;
        else{                                                    //2, es como if(!encarcelado)
            boolean existe=existeLaPropiedad(ip);                //2
            if(existe){
                TituloPropiedad propiedad=propiedades.get(ip);   //3
                puedoEdificarCasa=puedoEdificarCasa(propiedad);  //4
                
                if(puedoEdificarCasa){
                    result=propiedad.construirCasa(this);        //5
                    
                    if(result){                                  //6
                        Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye una casa en su propiedad " + ip);
                    }
                }  
            }
        }
        return result;
    }
  
    boolean construirHotel(int ip) {
        boolean result = false;
        if(encarcelado)
            return result;
        
        if(existeLaPropiedad(ip)) {
            TituloPropiedad propiedad = propiedades.get(ip);
            boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
            
            if(puedoEdificarHotel) {
                result = propiedad.construirHotel(this);
                int casasPorHotel = getCasasPorHotel();
                propiedad.derruirCasas(casasPorHotel, this);
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye un hotel en su propiedad " + ip);
            }
        }
        return result;
    }
    
    boolean hipotecar(int ip) {
        boolean result = false;
        if(encarcelado) 
            return result;
        
        if(existeLaPropiedad(ip)) {
            TituloPropiedad propiedad = propiedades.get(ip);
            result = propiedad.hipotecar(this);
            
            if(result)
                Diario.getInstance().ocurreEvento("El jugador " + nombre + " hipoteca la propiedad " + ip);
         }
        return result;
    }
    
    boolean comprar(TituloPropiedad titulo){
        boolean result=false;
        if(encarcelado)
            return result;
        if(puedeComprar){
            float precio=titulo.getPrecioCompra();
            
            if(puedoGastar(precio)){
                result=titulo.comprar(this);
                
                if(result){
                    propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador " + this.nombre+" compra la propiedad " + titulo.toString());
                }
                puedeComprar=false;
            }
        }
        return result;
    }
    
    
    @Override //Sobreescribimos el compareTo de Comparable<>
    //Devuelve 1 si el saldo del jugador que llama al metodo es mayor que el jugador parametro; Da un -1 si es menor; 
    //Y 0 si son iguales. Lo saco de aqui: https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html
    public int compareTo(Jugador otro){
      
      //Compara en funcion del saldo en una unica "sentencia".
      return new Float((this.saldo)).compareTo(otro.getSaldo()); 

    }

    protected int getCasasMax() {
        return CasasMax;
    }
    
    int getCasasPorHotel() {
        return CasasPorHotel;
    }
    
    protected int getHotelesMax() {
        return HotelesMax;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getNumCasillaActual() {
        return numCasillaActual;
    }
    
    private float getPrecioLibertad() {
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida() {
        return PasoPorSalida;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades () {
        return propiedades;
    }
    
    boolean getPuedeComprar() {
        return puedeComprar;
    }
    
    public float getSaldo() {
        return saldo;
    }
    
    public boolean isEncarcelado() {
        return encarcelado;
    }
    
    int cantidadCasasHoteles() {
        int suma=0;
        for(int i=0;i<propiedades.size();i++) {
            suma+=(propiedades.get(i)).cantidadCasaHoteles();
        }
    return suma;
    }
    
    //Probablemente haya demasiada informacion, teniendo en cuenta que se muestra cada vez que pasa el turno y se cambia de jugador, pero es lo que se dice que hagamos
    @Override
    public String toString() {
        String nombres_propiedades="";
        for(int i=0; i<propiedades.size();i++){ //Esto es para crear un string con los nombres de las propiedades del jugador, y no tener que poner abajo un chorizo de sentencia
            nombres_propiedades+=(propiedades.get(i)).getNombre() + "(casas=" +(propiedades.get(i)).numCasas+ ")(hoteles=" +(propiedades.get(i)).numHoteles + ")(hipotecado=" + (propiedades.get(i)).hipotecado + ")--" ;
        }
        return "\n\n *Jugador* " 
                + "\n\t Nombre: " + nombre 
                + "\n\t Saldo:" + saldo 
                + "\n\t Encarcelado: " + encarcelado
                + "\n\t Numero de propiedades: " + propiedades.size()
                + "\n\t\t Propiedades: " + nombres_propiedades
                + "\n\t Casilla actual(numero-nombre): " + numCasillaActual; //El numero lo pongo aqui, y el nombre de la casilla lo pone la otra parte de actualizarVista(que es quien me llama)
    }

    private boolean existeLaPropiedad(int ip) {
        return (ip>=0 && ip<propiedades.size());
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad propiedad){
        boolean puedoEdificarCasa=false;
        float precio=propiedad.getPrecioEdificar();
        if(puedoGastar(precio)){
            if(propiedad.numCasas<getCasasMax()){
                    puedoEdificarCasa=true;
            }
        }
       return puedoEdificarCasa;   
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad propiedad){
        boolean puedoEdificarHotel=false;
        float precio = propiedad.getPrecioEdificar();
        
        if(puedoGastar(precio)){
            if(propiedad.getNumHoteles() < getHotelesMax()) 
                if(propiedad.getNumCasas() >= getCasasPorHotel())
                    puedoEdificarHotel = true;
            
        }
        return puedoEdificarHotel;
     }
    
    
}   //Fin de la clase Jugador
   












