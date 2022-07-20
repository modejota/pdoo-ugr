# encoding:utf-8 

require_relative 'casilla'
require_relative 'casilla_calle'
require_relative 'casilla_sorpresa'
require_relative 'casilla_impuesto'
require_relative 'casilla_juez'
require_relative 'sorpresa'
require_relative 'sorpresa_por_jugador'
require_relative 'sorpresa_por_casa_hotel'
require_relative 'sorpresa_ir_casilla'
require_relative 'sorpresa_ir_carcel'
require_relative 'sorpresa_salir_carcel'
require_relative 'sorpresa_pagar_cobrar'
require_relative 'sorpresa_jugador_especulador'
require_relative 'titulo_propiedad'
require_relative 'tablero'
require_relative 'jugador'
require_relative 'gestor_estados'
require_relative 'Operaciones_juego'

module Civitas
  class CivitasJuego
    attr_reader :indiceJugadorActual
    
    def initialize(nombres)
      @indiceJugadorActual=0;
      @jugadores=[];
      @tablero=nil
      #Rellenamos el vector de jugadores con los nombres que se pasan
      i=0;
      while i<nombres.length()
          @jugadores << Jugador.new(nombres[i]);
          i=i+1;
      end
      
      @gestorEstados=Gestor_estados.new();
      @estado=@gestorEstados.estado_inicial();
      
      @indiceJugadorActual=Dado.instance.quienEmpieza(@jugadores.size());
      
      @mazo=MazoSorpresas.new();
      inicializaTablero(@mazo);
      inicializaMazoSorpresas(@tablero);
    end
    
    def actualizarInfo()
      puts ((@jugadores[@indiceJugadorActual]).toString)
      
      if(finalDelJuego())
        nombre_jugador_actual=getJugadorActual().nombre
        rank=[]
        rank=ranking()
            
        puts "\n ***Se ha acabado el juego, el jugador #{nombre_jugador_actual} esta en bancarrota***"
      
        tamanio=rank.size()
        case (tamanio)
              when 4
                    puts "\n\n *Ranking*
                 Primer puesto: #{(rank[tamanio-1]).nombre} con saldo: #{(rank[tamanio-1]).saldo}
                 Segundo puesto: #{(rank[tamanio-2]).nombre} con saldo: #{(rank[tamanio-2]).saldo}
                 Tercer puesto: #{(rank[tamanio-3]).nombre} con saldo: #{(rank[tamanio-3]).saldo}
                 Cuarto puesto: #{(rank[tamanio-4]).nombre} con saldo: #{(rank[tamanio-4]).saldo}"
                   
              when 3
                    puts "\n\n *Ranking*
                 Primer puesto: #{(rank[tamanio-1]).nombre} con saldo: #{(rank[tamanio-1]).saldo}
                 Segundo puesto: #{(rank[tamanio-2]).nombre} con saldo: #{(rank[tamanio-2]).saldo}
                 Tercer puesto: #{(rank[tamanio-3]).nombre} con saldo: #{(rank[tamanio-3]).saldo}"
                    
              when 2
                    puts "\n\n *Ranking*
                 Primer puesto: #{(rank[tamanio-1]).nombre} con saldo: #{(rank[tamanio-1]).saldo}
                 Segundo puesto: #{(rank[tamanio-2]).nombre} con saldo: #{(rank[tamanio-2]).saldo}"
                    
      end
      end
    end
    
    def salirCarcelPagando()
      return @jugadores[@indiceJugadorActual].salirCarcelPagando;
    end
    
    def salirCarcelTirando()
      return @jugadores[@indiceJugadorActual].salirCarcelTirando;
    end
    
    def siguientePaso()
      jugadorActual=@jugadores[@indiceJugadorActual]
      operacion=@gestorEstados.operaciones_permitidas(jugadorActual, @estado)
      puts operacion.to_s
      case(operacion)
        when Operaciones_juego::PASAR_TURNO
          pasarTurno()
          siguientePasoCompletado(operacion)
          
        when Operaciones_juego::AVANZAR
          avanzaJugador()
          siguientePasoCompletado(operacion)
      end
      return operacion
    end
    
    def siguientePasoCompletado(operacion)
      @estado=@gestorEstados.siguiente_estado(@jugadores[@indiceJugadorActual], @estado, operacion)
    end
    
    def construirCasa(ip)
      return (@jugadores[@indiceJugadorActual]).construirCasa(ip)
    end
    
    def construirHotel(ip)
      return (@jugadores[@indiceJugadorActual]).construirHotel(ip)
    end
    
    def vender(ip)
      return (@jugadores[@indiceJugadorActual].vender(ip))
    end
    
    def comprar()
      jugadorActual=@jugadores[@indiceJugadorActual]
      numCasillaActual=jugadorActual.numCasillaActual
      casilla=@tablero.getCasilla(numCasillaActual)
      titulo=casilla.titulo
      res=jugadorActual.comprar(titulo)
      
      return res
    end
    
    def hipotecar(ip)
      return (@jugadores[@indiceJugadorActual]).hipotecar(ip)
    end
    
    def cancelarHipoteca(ip)
      return (@jugadores[@indiceJugadorActual]).cancelarHipoteca(ip)
    end
    
    def finalDelJuego()
      haybancarrota_ya=false
      i=0
      while(i<@jugadores.size and !haybancarrota_ya)
        if(@jugadores[i].enBancarrota)
          haybancarrota_ya=true
        end
        i=i+1
      end
      return haybancarrota_ya
    end
    
    #En el guion se llama getCasillaActual
    def getCasillaActual()
      return @tablero.getCasilla(@jugadores[@indiceJugadorActual].numCasillaActual)
    end
    
    def getJugadorActual()
      return @jugadores[@indiceJugadorActual]
    end

    
    private    
    def contabilizarPasosPorSalida(jugadorActual)
      while(@tablero.getPorSalida()>0)        
            jugadorActual.pasaPorSalida();
      end
    end
    
    def pasarTurno()
      if(@indiceJugadorActual>=0 and @indiceJugadorActual<@jugadores.size()-1)
        @indiceJugadorActual=@indiceJugadorActual+1
      else
        @indiceJugadorActual=0
      end
    end
    
    
    def inicializaTablero(mazo)
        casillaCarcel = 4
        @tablero = Tablero.new(casillaCarcel) #En el initialize de tablero se aniade la casilla SALIDA en la posicion 0                                                                # Salida. Posicion 0
        
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("BAR DE LUCHO", TituloPropiedad.new("BAR DE LUCHO", 40, 1.1, 80, 120, 30), 120))                                   #Calle 1. Posicion 1
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CHUBBY-ESCUELA", TituloPropiedad.new("CHUBBY-ESCUELA", 45, 1.1, 90, 135, 30), 135))                               #Calle 2. Posicion 2
        @tablero.aniadeCasilla(CasillaSorpresa.constructorCasillaSorpresa("SORPRESA", mazo))                                                                                           #SORPRESA. Posicion 3
       #En la llamada anterior como la carcel es el 4 en el vector, el metodo lo comprueba y añade, no hay que hacerlo explicitamente.                                                 # Carcel. Posicion 4
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE DE LA LOLA",TituloPropiedad.new("CALLE DE LA LOLA", 50, 1.2, 100, 150, 40),150))                            #Calle 3. Posicion 5
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE KALASHNIKOV",TituloPropiedad.new("CALLE KALASHNIKOV", 55, 1.2, 110, 160, 40),160))                          #Calle 4. Posicion 6
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE DE LAS FICHAS",TituloPropiedad.new("CALLE DE LAS FICHAS", 60, 1.2, 120, 175, 45),175))                      #Calle 5. Posicion 7
        @tablero.aniadeCasilla(CasillaImpuesto.constructorCasillaImpuesto("IMPUESTO", 500))                                                                                            #IMPUESTO. Posicion 8
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE DE LOS PATOS",TituloPropiedad.new("CALLE DE LOS PATOS", 65, 1.3, 130, 180, 50),180))                        #Calle 6. Posicion 9
        @tablero.aniadeJuez()                                                                                                                                                          #JUEZ. Posicion 10
        @tablero.aniadeCasilla(CasillaSorpresa.constructorCasillaSorpresa("SORPRESA", mazo))                                                                                           #SORPRESA. Posicion 11
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE DEL RGB",TituloPropiedad.new("CALLE DEL RGB", 70, 1.3, 140, 200, 60),200))                                  #Calle 7. Posicion 12
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("AVENIDA DE ESPANHA",TituloPropiedad.new("AVENIDA DE ESPANHA", 80, 1.4, 150, 220, 75),220))                        #Calle 8. Posicion 13
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE DEL PINCHAZO",TituloPropiedad.new("CALLE DEL PINCHAZO", 90, 1.4, 160, 240, 85),240))                        #Calle 9. Posicion 14
        @tablero.aniadeCasilla(Casilla.constructorCasilla("PARKING"))                                                                                                                  #PARKING. Posicion 15
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("CALLE DE LEO",TituloPropiedad.new("CALLE DE LEO", 100, 1.4, 170, 250, 85),250))                                   #Calle 10. Posicion 16
        @tablero.aniadeCasilla(CasillaSorpresa.constructorCasillaSorpresa("SORPRESA", mazo))                                                                                           #SORPRESA. Posicion 17
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("FACULTAD DE BELLAS ARTES",TituloPropiedad.new("FACULTAD DE BELLAS ARTES", 120, 1.5, 200, 275, 100),275))          #Calle 11. Posicion 18
        @tablero.aniadeCasilla(CasillaCalle.constructorCasillaCalle("TERCER EDIFICIO DE LA ETSIIT",TituloPropiedad.new("TERCER EDIFICIO DE LA ETSIIT", 25, 1.5, 200, 300, 100),300))   #Calle 12. Posicion 19
    end
    
    def inicializaMazoSorpresas(unTablero)

        # Las dos casillas PAGARCOBRAR
        @mazo.alMazo(SorpresaPagarCobrar.constructorSorpresaPagarCobrar("Te han dado una subvención por tontico, recibes 500", 500))
        @mazo.alMazo(SorpresaPagarCobrar.constructorSorpresaPagarCobrar("Te robado el iPhone, pierdes 500",-500))
        
        # Las dos casillas IRACASILLA
        @mazo.alMazo(SorpresaIrCasilla.constructorSorpresaIrCasilla("Vas a construir el tercer edificio de la ETSIIT, ve a la casilla 19",unTablero,19))
        @mazo.alMazo(SorpresaIrCasilla.constructorSorpresaIrCasilla("Te han tirado full de fichas, sientente afortunado y muevete a la casilla 7",unTablero,7))

        # Para ir a la carecl y salir de ella
        @mazo.alMazo(SorpresaIrCarcel.constructorSorpresaIrCarcel("Te ha pillado el FBI, vas para la carcel",unTablero))
        @mazo.alMazo(SorpresaSalirCarcel.constructorSorpresaSalirCarcel("Conseguiste un pagare, tendras un favor con los cops",@mazo))

        # Pagar o cobrar por cada casa y hotel
        @mazo.alMazo(SorpresaPorCasaHotel.constructorSorpresaPorCasaHotel("Eres un fiera inmobiliario, cobras 200 por casa/hotel",200))
        @mazo.alMazo(SorpresaPorCasaHotel.constructorSorpresaPorCasaHotel("El seguro te ha estafado, pierdes 200 por casa/hotel",-200))
        
        # Para cobrar o pagar de/a cada jugador
        @mazo.alMazo(SorpresaPorJugador.constructorSorpresaPorJugador("Compartir es vivir, cada jugador te regala 100",100))
        @mazo.alMazo(SorpresaPorJugador.constructorSorpresaPorJugador("Perdiste una apuesta con tus amigos imaginarios, pierdes 100 por cada uno de ellos",-100))

        # Para cambiar a Jugador Especulador
        @mazo.alMazo(SorpresaJugadorEspeculador.constructorSorpresaJugadorEspeculador("Congratulaciones, se ha convertido usted en un especulador",200))
        
    end
    
    def ranking()
      @jugadores.sort! #Me ordena el array en el propio array(osea me lo modifica), usando el operador <=>
      return @jugadores
    end
    
    def avanzaJugador()
      jugadorActual=@jugadores[@indiceJugadorActual]
      posicionActual=jugadorActual.numCasillaActual
      tirada=Dado.instance.tirar
      
      #Esto no lo dice el guion, pero me parece util saber la tirada que ha salido
      Diario.instance.ocurre_evento("\tAl jugador #{getJugadorActual.nombre} le ha salido un #{tirada} en el dado")
      
      posicionNueva=@tablero.nuevaPosicion(posicionActual, tirada)
      casilla=@tablero.getCasilla(posicionNueva)
      contabilizarPasosPorSalida(jugadorActual)
      jugadorActual.moverACasilla(posicionNueva)
      casilla.recibeJugador(@indiceJugadorActual,@jugadores)
      contabilizarPasosPorSalida(jugadorActual)
    end
    
  end
end
