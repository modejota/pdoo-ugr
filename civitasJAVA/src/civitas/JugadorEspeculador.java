package civitas;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class JugadorEspeculador extends Jugador{
    private static final int FactorEspeculador = 2; 
    private int fianza;
    
    //Constructor de copia, para cuando se convierte el jugador tira del constructor de la clase Jugador, le coloca la fianza
    //y debera actualizar sus propiedades
    JugadorEspeculador(Jugador otro, int _fianza){
        super(otro);
        fianza = _fianza;
        //Para cada propiedad que tenga el jugador: Mandar esto al metodo sorpresa que procede a cambiar, eso me ha dicho.
        for(int i=0;i<otro.propiedades.size();i++) {
            //Le decimos a la propiedad que ha cambiado de dueño
            (otro.propiedades.get(i)).actualizaPropietarioPorConversion(this);
        }
    }
    
    //Sobrecarga del metodo debeSerEncarcelado, primeramente comprueba si tiene el salvonducto, si es asi lo usa
    //Si no puede usar el salvoconducto, vemos si puede pagar la fianza. Si tiene saldo suficiente, se libra. 
    //Si no entra en ninguno de los casos anteriores, ha de entrar a la carcel
    @Override
    protected boolean debeSerEncarcelado(){
        if(encarcelado)
           return false;
        else{
            if(tieneSalvoconducto()){
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador especulador se ha librado de ir a la carcel. Ha usado el salvoconducto");
                return false;
            }
            else{
                if(saldo >= fianza){
                   paga(fianza);
                   Diario.getInstance().ocurreEvento("El jugador especulador se ha librado de ir a la carcel pagando la fianza");
                   return false;
                }
                else
                    return true;
            }
        }
    }
                   
    //Sobrecarga del metodo pagaImpuesto, utiliza el metodo paga, el cual no cambiamos
    //Como los especuladores pagan la mitad, mandamos a ese metodo la mitad de lo que
    //deberia pagar de normal
    @Override
    boolean pagaImpuesto(float cantidad){
        if(encarcelado)
            return false;
        else
            return paga(cantidad/2);
    }
    
    //Sobrecarga del toString, respecto al original de Jugador lo que añade es el titulo "JugadorEspeculador" y el FactorEspeculador
    //No se tira del super porque los mensajes se entremezclan y tal
    @Override
        public String toString(){
        String nombres_propiedades="";
        for(int i=0; i<propiedades.size();i++){ //Esto es para crear un string con los nombres de las propiedades del jugador, y no tener que poner abajo un chorizo de sentencia
            nombres_propiedades+=(propiedades.get(i)).getNombre() + "(casas=" +(propiedades.get(i)).numCasas+ ")(hoteles=" +(propiedades.get(i)).numHoteles + ")(hipotecado=" + (propiedades.get(i)).hipotecado + ")--" ;
        }
        return "\n\n *Jugador Especulador* " 
                + "\n\t Nombre: " + nombre 
                + "\n\t Saldo: " + saldo 
                + "\n\t FactorEspeculador: " + FactorEspeculador
                + "\n\t Fianza: " + fianza
                + "\n\t Encarcelado: " + encarcelado
                + "\n\t Numero de propiedades: " + propiedades.size()
                + "\n\t\t Propiedades: " + nombres_propiedades
                + "\n\t Casilla actual(numero-nombre): " + numCasillaActual; //El numero lo pongo aqui, y el nombre de la casilla lo pone la otra parte de actualizarVista(que es quien me llama)
    }
  
    //CONSULTORES SOBREESCRITOS DEBIDOS AL FACTOR DE ESPECULACION
    @Override
    protected int getCasasMax(){
        return CasasMax*FactorEspeculador;
    }
    
    @Override
    protected int getHotelesMax(){
        return HotelesMax*FactorEspeculador;
    }

} //Fin de clase
