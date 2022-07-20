package civitas;

import java.util.ArrayList;
import GUI.*;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class CivitasJuego {
    private int indiceJugadorActual;
    private Tablero tablero;
    private MazoSorpresas mazo;
    private ArrayList<Jugador> jugadores;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    
    
    
    public CivitasJuego(ArrayList<String> nombres){
        jugadores= new ArrayList<>();
        for(int i=0; i<nombres.size(); i++)
            jugadores.add(new Jugador(nombres.get(i)));
        
        gestorEstados= new GestorEstados();
        estado=gestorEstados.estadoInicial();
        
        indiceJugadorActual=Dado.getInstance().quienEmpieza(jugadores.size());
        
        //Crear el mazo de sorpresas
        //Llamar al método de inicialización del tablero.
        //Llamar al método de inicialización del mazo de sorpresas.
        mazo= new MazoSorpresas();
        inicializaTablero(mazo);
        inicializaMazoSorpresas(tablero);
    }
    
    public String stringRanking(){
        ArrayList<Jugador> rank;
        //Esta variable local "nombre_jugador_actual" la creo porque dentro del metodo ranking() se llama a sort(), que me ordena
        //el ArrayList "jugadores" (sobre él mismo, osea que me lo modifica). Por tanto, si luego quisiera hacer un
        // "getJugadorActual().nombre" no me cogería el jugador que deberia ser
        String nombre_jugador_actual=getJugadorActual().nombre;
        rank=ranking();
            
        switch (rank.size()){
            case 4:
                return("\n ***Se ha acabado el juego, el jugador " + nombre_jugador_actual + " esta en bancarrota***"
                    + "\n\n *Ranking*"
                        + "\n\t Primer puesto: " + rank.get(rank.size()-1).nombre + " con saldo: " + rank.get(rank.size()-1).getSaldo()
                        + "\n\t Segundo puesto: " + rank.get(rank.size()-2).nombre + " con saldo: " + rank.get(rank.size()-2).getSaldo()
                        + "\n\t Tercer puesto: " + rank.get(rank.size()-3).nombre + " con saldo: " + rank.get(rank.size()-3).getSaldo()
                        + "\n\t Cuarto puesto: " + rank.get(rank.size()-4).nombre + " con saldo: " + rank.get(rank.size()-4).getSaldo());
     
            case 3:
                return("\n ***Se ha acabado el juego, el jugador " + nombre_jugador_actual + " esta en bancarrota***"
                    + "\n\n *Ranking*"
                        + "\n\t Primer puesto: " + rank.get(rank.size()-1).nombre + " con saldo: " + rank.get(rank.size()-1).getSaldo()
                        + "\n\t Segundo puesto: " + rank.get(rank.size()-2).nombre + " con saldo: " + rank.get(rank.size()-2).getSaldo()
                        + "\n\t Tercer puesto: " + rank.get(rank.size()-3).nombre + " con saldo: " + rank.get(rank.size()-3).getSaldo());
             
            case 2:
                return("\n ***Se ha acabado el juego, el jugador " + nombre_jugador_actual + " esta en bancarrota***"
                    + "\n\n *Ranking*"
                        + "\n\t Primer puesto: " + rank.get(rank.size()-1).nombre + " con saldo: " + rank.get(rank.size()-1).getSaldo()
                        + "\n\t Segundo puesto: " + rank.get(rank.size()-2).nombre + " con saldo: " + rank.get(rank.size()-2).getSaldo());
        }
        //Esto me lo dice el IDE que lo ponga sino da error
        return null;
    }
    
    
    private void inicializaTablero(MazoSorpresas mazo){
            int casillaCarcel = 5;
        tablero = new Tablero(casillaCarcel);   //Creamos la casilla de la salida (posicion 0) e indicamos el numero de la carcel.                                    //Salida.   Posicion 0
        
        tablero.añadeCasilla(new CasillaCalle("Bar de Lucho",new TituloPropiedad("Bar de Lucho", 80, (float) 1.1, 160, 240, 60),240));                                //Calle 1.  Posicion 1
        tablero.añadeCasilla(new CasillaCalle("Chubby-Escuela",new TituloPropiedad("Chubby-Escuela", 90, (float) 1.1, 180, 270, 60),270));                            //Calle 2.  Posicion 2
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "Sorpresa1"));                                                                                                 //Sorpresa1.Posicion 3                                                                                                                         //Carcel.   Posicion 4
        tablero.añadeCasilla(new CasillaCalle("Ayuntamiento de Jodar",new TituloPropiedad("Ayuntamiento de Jodar", 100, (float) 1.2, 200, 300, 80),300));                             //Calle 3.  Posicion 4
        //Al aniadir el Ayto de Jodar, como la carcel tiene la posicion 5, el metodo lo comprueba y aniade,                                                           //Carcel.   Posicion 5
        //no hay que hacerlo explicitamente.
        tablero.añadeCasilla(new CasillaCalle("Calle Ubuntu",new TituloPropiedad("Calle Ubuntu", 110, (float) 1.2, 220, 320, 80),320));                               //Calle 4.  Posicion 6
        tablero.añadeCasilla(new CasillaCalle("Calle de las Fichas",new TituloPropiedad("Calle de las Fichas", 120, (float) 1.2, 240, 350, 90),350));                 //Calle 5.  Posicion 7
        tablero.añadeCasilla(new CasillaImpuesto("Impuesto",(float) 500.0));                                                                                          //Impuesto. Posicion 8
        tablero.añadeCasilla(new CasillaCalle("Calle de los Patos",new TituloPropiedad("Calle de los Patos", 130, (float) 1.3, 260, 360, 100),360));                  //Calle 6.  Posicion 9
        tablero.añadeCasilla(new Casilla("Parking"));                                                                                                                 //Parking.  Posicion 10
        tablero.añadeCasilla(new CasillaCalle("Calle del RGB",new TituloPropiedad("Calle del RGB", 140, (float) 1.3, 280, 400, 120),400));                            //Calle 7.  Posicion 12
        tablero.añadeCasilla(new CasillaSorpresa(mazo,"Sorpresa2"));                                                                                                  //Sorpresa2.Posicion 11
        tablero.añadeCasilla(new CasillaCalle("Avenida de España",new TituloPropiedad("Avenida de España", 160, (float) 1.4, 300, 440, 150),440));                    //Calle 8.  Posicion 13
        tablero.añadeCasilla(new CasillaCalle("Calle del Pinchazo",new TituloPropiedad("Calle del Pinchazo", 180, (float) 1.4, 320, 480, 170),480));                  //Calle 9.  Posicion 14
        tablero.añadeJuez();                                                                                                                                          //Juez.     Posicion 15
        tablero.añadeCasilla(new CasillaCalle("Calle de Leo",new TituloPropiedad("Calle de Leo", 200, (float) 1.4, 240, 500, 170),500));                              //Calle 10. Posicion 16
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "Sorpresa3"));                                                                                                 //Sorpresa3.Posicion 17
        tablero.añadeCasilla(new CasillaCalle("Bellas Artes",new TituloPropiedad("Bellas Artes", 240, (float) 1.5, 400, 550, 200),550));                              //Calle 11. Posicion 18
        tablero.añadeCasilla(new CasillaCalle("3º edificio Etsiit",new TituloPropiedad("3º edificio Etsiit", 260, (float) 1.5, 400, 650, 250),650));                        //Calle 12. Posicion 19

        
    }
    
    //Se crea el mazo de sorpresas
    //LO DE ABAJO SON LAS REGLAS DEL JUEGO (MIRAR TABLA)
    //Cuando un jugador cae en una casilla de tipo SORPRESA debe coger una carta sorpresa y hacer lo
    //que se indique en ella. En el mazo de cartas Sorpresa hay 10 cartas de 5 tipos diferentes como
    //muestra la tabla siguiente. En la mayoría de ellas se necesita conocer un valor indicado en la propia
    //carta cuya interpretación es diferente según el tipo de carta.
    
    
    private void inicializaMazoSorpresas(Tablero tablero){ 

         //Las dos casillas PAGARCOBRAR
         mazo.alMazo(new SorpresaPagarCobrar("Te han dado una subvención por tontico, recibes 500",500));
         mazo.alMazo(new SorpresaPagarCobrar("Te robado el iPhone, pierdes 500",-500));
         
         //Las dos casillas IRACASILLA
         mazo.alMazo(new SorpresaIrCasilla("Vas a construir el 3º edificio de la ETSIIT, ve a la casilla 19",tablero,19));
         mazo.alMazo(new SorpresaIrCasilla("Te han tirado full de fichas, sientente afortunado y muevete a la casilla 7",tablero,7));
         
         //Para ir a la carcel y salir de ella
         mazo.alMazo(new SorpresaIrCarcel("Te ha pillado el FBI, vas para la carcel",tablero));
         mazo.alMazo(new SorpresaSalirCarcel("Conseguiste un pagare, tendras un favor con los cops",mazo)); 
                
         //Pagar o cobrar por cada casa y hotel
         mazo.alMazo(new SorpresaPorCasaHotel("Eres un fiera inmobiliario, cobras 200 por casa/hotel",200));
         mazo.alMazo(new SorpresaPorCasaHotel("El seguro te ha estafado, pierdes 200 por casa/hotel",-200));
 
         //Para cobrar o pagar a cada jugador
         mazo.alMazo(new SorpresaPorJugador("Compartir es vivir, cada jugador te regala 100",100));
         mazo.alMazo(new SorpresaPorJugador("Perdiste una apuesta con tus amigos imaginarios, pierdes 100 por cada uno de ellos",-100));   
         
         //Para cambiar a JugadorEspeculador
         mazo.alMazo(new SorpresaJugadorEspeculador("Congratulaciones, se ha convertido usted en un especulador",200));
    }
    
    private void contabilizarPasosPorSalida (Jugador jugadorActual){
        while(tablero.getPorSalida()>0){ 
            jugadorActual.pasaPorSalida();
        }
    }
    
    private void pasarTurno(){
        if(indiceJugadorActual >=0 && indiceJugadorActual<jugadores.size()-1)
            indiceJugadorActual++;
        else
            indiceJugadorActual=0;
    }
    
    //El parametro solo se lo paso para poder usar el quitarBordes(), porque sino no hay otra manera o al menos yo no sé
    public OperacionesJuego siguientePaso(CivitasView vista){
        Jugador jugadorActual=jugadores.get(indiceJugadorActual);
        OperacionesJuego operacion=gestorEstados.operacionesPermitidas(jugadorActual, estado);
        
        //Tanto al pasar turno como al avanzar de casilla, quitamos el tablero que corresponda (es decir, a la 
        //casilla en la que esté el jugador actual)
        switch(operacion){
            case PASAR_TURNO:
                vista.quitarBordes();
                pasarTurno();
                siguientePasoCompletado(operacion);
                break;
                
            case AVANZAR:
                //El quitarBordes() se lo meto dentro al avanzaJugador para que quite el tablero en el momento justo (y que parezca mas interactivo)
                avanzaJugador(vista);
                siguientePasoCompletado(operacion);
                break;
        }
        return operacion;
    }
    
    public void siguientePasoCompletado (OperacionesJuego operacion){
        estado=gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
    
    public boolean construirCasa (int ip){
        return (jugadores.get(indiceJugadorActual)).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        return (jugadores.get(indiceJugadorActual)).construirHotel(ip);
    }
    
    public boolean vender(int ip){
         return (jugadores.get(indiceJugadorActual)).vender(ip);
    }
    
    public boolean comprar(){
        Jugador jugadorActual=jugadores.get(indiceJugadorActual);
        int numCasillaActual=jugadorActual.getNumCasillaActual();
        Casilla casilla=tablero.getCasilla(numCasillaActual);
        TituloPropiedad titulo=casilla.getTituloPropiedad();
        boolean res=jugadorActual.comprar(titulo);
        
        return res;
    }
    
    public boolean hipotecar(int ip){
        return (jugadores.get(indiceJugadorActual)).hipotecar(ip);
    }
    
    public boolean cancelarHipoteca(int ip){
        return (jugadores.get(indiceJugadorActual)).cancelarHipoteca(ip);
    }
    
    public boolean salirCarcelPagando(){
        return (jugadores.get(indiceJugadorActual)).salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando(){
        return (jugadores.get(indiceJugadorActual)).salirCarcelTirando();
    }
    
    public boolean finalDelJuego(){
        boolean haybancarrota_ya=false;
        for(int i=0; i<jugadores.size() && !haybancarrota_ya; i++)
            if(jugadores.get(i).enBancarrota())
                haybancarrota_ya=true;
        return haybancarrota_ya;
    }
    
     private void avanzaJugador(CivitasView vista){
        Jugador jugadorActual=jugadores.get(indiceJugadorActual);       
        int posicionActual=jugadorActual.getNumCasillaActual();         
        int tirada=Dado.getInstance().tirar();                         
        
        //Esto no lo dice el guion, pero me parece util saber la tirada que ha salido
        Diario.getInstance().ocurreEvento("\t Al jugador " + getJugadorActual().nombre + " le ha salido un " + tirada + " en el dado");
        
        int posicionNueva=tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla=tablero.getCasilla(posicionNueva);                
        this.contabilizarPasosPorSalida(jugadorActual);
        
        //Lo de vista.quitarBordes() lo pongo aquí para que mientras está popeada la ventanita del dado (y portanto todavia no se haya clikado el boton, 
        //y portanto todavia no se haya movido al jugador a otra casilla distinta), se pueda ver el borde en la casilla en la que esté
        //el jugador actual
        
        //Como la siguiente linea, es la de moverACasilla(posicionNueva), le quito los bordes justo antes
        vista.quitarBordes();
        
        jugadorActual.moverACasilla(posicionNueva);
        
        //Esto va bien porq: el compilador ve que "casilla" es de la Clase Casilla y tiene el metodo recibeJugador (que en el caso de las casillas Descanso solo llama al informe)
        //Lo que pasa esq en tiempo de ejecucion, el interprete ve que le estamos metiendo una casillaCalle, casillaJuez, casillaImpuesto, o casillaSorpresa (en caso de que le metamos alguna de estas, claro)
        //y llamará al metodo recibe de cada uno
        casilla.recibeJugador(indiceJugadorActual, jugadores);   
        this.contabilizarPasosPorSalida(jugadorActual);                   
     }
     
    private ArrayList<Jugador> ranking(){
        jugadores.sort(null); //Me ordena el array usando el orden especificado. Si meto null como parametro, 
        //cogerá el orden de la interface Comparable<>, y como hemos overrideado el compareTo, ordenará por saldos
	
        return jugadores; //return clasificacion
    }
    
    public Jugador getJugadorActual(){
        return jugadores.get(indiceJugadorActual);
    }
    
    
    public Casilla getCasillaActual(){
        return tablero.getCasilla((jugadores.get(indiceJugadorActual)).getNumCasillaActual());
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }

}//Fin de clase



