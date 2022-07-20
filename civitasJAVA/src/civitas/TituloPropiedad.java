package civitas;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class TituloPropiedad {
    
    float alquilerBase;
    float factorInteresesHipoteca = (float) 1.1;
    float factorRevalorizacion;
    float hipotecaBase;
    boolean hipotecado;
    String nombre;
    int numCasas;
    int numHoteles;
    float precioCompra;
    float precioEdificar;
    Jugador propietario = null;
    
    //Constructor de la clase
    TituloPropiedad(String _nombre, float _alquilerBase, float _factor_rev, float _hipotecaBase, float _precioCompra, float _precioEdificar) {
        nombre = _nombre;
        alquilerBase = _alquilerBase;
        factorRevalorizacion = _factor_rev;
        hipotecaBase = _hipotecaBase;
        precioCompra = _precioCompra;
        precioEdificar = _precioEdificar;
        hipotecado = false;
        numCasas = 0;
        numHoteles = 0;
        
    }
    
    //Probablemente sea demasiada información, teniendo en cuenta que se imprime cada vez que se cae en una casilla sin dueño, `pero es lo que se nos dice
    @Override
    public String toString() {
         return "\n\n *TituloPropiedad* " 
                + "\n\t Calle: " + nombre 
                + "\n\t PrecioCompra:" + precioCompra 
                + "\n\t AlquilerBase: " + alquilerBase 
                + "\n\t HipotecaBase:" + hipotecaBase 
                + "\n\t PrecioEdificar:" + precioEdificar 
                + "\n\t FactorRevalorizacion:" + factorRevalorizacion
                + "\n\t NumHoteles:" + numHoteles 
                + "\n\t NumCasas:" + numCasas 
                + "\n\t Hipotecada: " + hipotecado 
                + "\n\t Propietario: " + propietario; 
    }
    
    private float getPrecioAlquiler () {
        if(hipotecado || this.propietarioEncarcelado())
            return 0;
        
        else 
            return (float) (alquilerBase * (1+(numCasas*0.5) + (numHoteles * 2.5)));
    }
    
    float getImporteCancelarHipoteca() {
       return (float) (factorInteresesHipoteca *(hipotecaBase*(1+(numCasas*0.5)+(numHoteles*2.5))));
    }
    
    private float getImporteHipoteca() {
        return (float) (hipotecaBase*(1+(numCasas*0.5)+(numHoteles*2.5)));
    }
    
    boolean cancelarHipoteca(Jugador jugador) {
       boolean result = false;
        
        if(hipotecado) {
            if(esEsteElPropietario(jugador)) {
                propietario.paga(getImporteCancelarHipoteca());
                hipotecado = false;
                result = true;
            }
        }
        
       return result;
    }
    
    boolean hipotecar(Jugador jugador) {
        boolean salida=false;
        
        if(!hipotecado && esEsteElPropietario(jugador)) {
            propietario.recibe(getImporteHipoteca());
            hipotecado = true;
            salida = true;
        }
        return salida;
}
    
    void tramitarAlquiler(Jugador jugador) {
        if(tienePropietario()){
            if(!esEsteElPropietario(jugador)){
             float precio=this.getPrecioAlquiler();
             jugador.pagaAlquiler(precio);
             propietario.recibe(precio);
            }
        }
    }
    
    private boolean propietarioEncarcelado() {
        return propietario.isEncarcelado();
    }
    
    int cantidadCasaHoteles() {
        return (numCasas + numHoteles);
    }
    
    //Este deberia estar bien
    boolean derruirCasas(int n, Jugador jugador) {
        boolean operando=false;
       
        if(esEsteElPropietario(jugador)) {
            if(numCasas>=n) {
                numCasas=numCasas-n;
                operando=true;
            }
        }
        return operando;
    }
    
    private float getPrecioVenta() {
        return (precioCompra + (precioEdificar * (numCasas + numHoteles) * factorRevalorizacion));
    }
    
    
    boolean construirCasa(Jugador jugador) {
        boolean resultado = false;
        if(esEsteElPropietario(jugador) && !hipotecado) {
            jugador.paga(precioEdificar);
            numCasas++;     
            resultado=true;
        }
        return resultado;
    }
    
    
    boolean construirHotel(Jugador jugador) {
        boolean result=false;
        if(esEsteElPropietario(jugador) && !hipotecado) {
            jugador.paga(precioEdificar);
            numHoteles++;
            result=true;
        }
        return result;
    }
    
    boolean comprar(Jugador jugador) {
        boolean resultado=false;
        if(!tienePropietario()){
            propietario=jugador;
            resultado=true;
            propietario.paga(precioCompra);
        }
        return resultado;
    }
    
    //Cambia el propietario de una propiedad, necesario para la conversion a JugadorEspeculador
    void actualizaPropietarioPorConversion(JugadorEspeculador otro) {
        propietario = otro;
    }
  
    boolean vender(Jugador jugador) {
        boolean resultado;
        if(esEsteElPropietario(jugador) && !hipotecado){
            jugador.recibe(getPrecioVenta());
            propietario=null;
            numCasas=0;
            numHoteles=0;
            resultado=true;
        }
        else
            resultado=false;
       
        return resultado;
    }
    

    private boolean esEsteElPropietario(Jugador jugador) {
        boolean es_mia=false; 
                for(int i=0; i<jugador.propiedades.size() && !es_mia;i++){
                    if(jugador.propiedades.get(i)==this)
                        es_mia=true;
                }
        return es_mia;
    }
    
    void actualizaPropietarioPorConversion(Jugador jugador) {
        propietario=jugador;
    }
    
    //CONSULTORES
    public boolean getHipotecado() {
         return hipotecado;
     }

    public String getNombre() {
        return nombre;
    }
    
    public int getNumCasas() {
        return numCasas;
    }
    
    public int getNumHoteles() {
        return numHoteles;
    }
    
    Jugador getPropietario() {
        return propietario;
    }
    
    float getPrecioCompra() {
        return precioCompra;
    }
    
    float getPrecioEdificar() {
        return precioEdificar;
    }
    
    boolean tienePropietario() {
        return propietario != null;
    }

} //Fin clase Propiedad








