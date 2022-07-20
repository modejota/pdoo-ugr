#encoding:utf-8
require_relative 'operaciones_juego'
require_relative 'respuestas'
require_relative 'salidas_carcel'
require 'io/console'

module Civitas

  class Vista_textual

    def initialize
      @juegoModel=nil
      @iGestion=-1
      @iPropiedad=-1
      @separador="====================="
    end
    
    def mostrar_estado(estado)
      puts estado
    end

    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end

    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l
        index += 1
      }

      opcion = lee_entero(lista.length,
                          "\n"+tab+"Elige una opcion: ",
                          tab+"Valor erroneo")
      return opcion
    end

    
    def comprar
      opciones=["SI","NO"]
      opcion = menu("Quieres comprar esta calle?",
                    opciones)
      return Respuestas::lista_Respuestas(opcion)
    end

     def salirCarcel
       opciones=["Pagando","Tirando"]
       opcion = menu("Elige la forma para intentar salir de la carcel", 
                     opciones)
        return Salidas_carcel::lista_carcel(opcion)
     end
     
    
    def gestionar
      opciones=["VENDER","HIPOTECAR","CANCELAR_HIPOTECA","CONSTRUIR_CASA","CONSTRUIR_HOTEL","TERMINAR"]
      opcion_gestion = menu("Que gestion inmobiliaria quieres realizar?",opciones)
      @iGestion = opcion_gestion
      
      case opcion_gestion
      when 0
        #----------Explicacion del porq de este @iPropiedad=-1-------
        #El problema es que solo hay un objeto VistaTextual en el main, luego se usa el mismo atributo @iPropiedad para todas las gestiones que se hagan.
        #La consecuencia de esto es que si por ejemplo hago una gestion exitosa, es decir, que llego a realizar la asignacion de @iPropiedad=opcion_loquesea 
        #al final del when, el atributo de modifica (como es logico)
        
        #Entonces, si en la siguiente gestion, nos equivocamos y queremos usar el "Atras", efectivamente no realizara la asignacion en ese when; 
        #pero el atributo ya habia sido modificado en la anterior gestion y guardaba su valor, luego realizara la operacion igualmente
        #Por eso reseteo el atributo a -1 cada vez que empiece una gestion, y asi es como si fuese la primera
        @iPropiedad=-1
        
        
         #--------Explicacion del porq de este ArrayList de indices--------
         #De la manera en la que estaba hecha el gestionar() (que nos creamos un ArrayList nuevo cada vez que el jugador escogia una gestion a realizar,
         #con los nombres de las propiedades correctas en cada caso), daba el problema de que muchas veces, no coincidian los indices del ArrayList original de propiedades
         #con los indices del ArrayList "nombres_a_loquesea" porque no metemos todas las propiedades(obviamente, porq sino no tendria sentido crearnos los "nombres_a_loquesea").
            
         #Esto provacaba que seleccionabas una propiedad en el menu, que a lo mejor no correspondia en el ArrayList original con la misma propiedad, luego
         #no se producia la gestion o se realizaba sobre otra
            
         #Con el ArrayList de enteros "indices_loquesea" arreglamos esto
            
        indices_vender=Array.new()
        
        
        nombres_vender_vacio=false
        nombres_a_vender = Array.new()
        i = 0
        while (i < ((@juegoModel.getJugadorActual).propiedades).size)
            if( (((@juegoModel.getJugadorActual).propiedades[i]).hipotecado == false))
              nombres_a_vender << (((@juegoModel.getJugadorActual).propiedades[i]).nombre)
              indices_vender << i
            end
            i = i+1
        end
        
        if(nombres_a_vender.empty?)
          puts "No tienes ninguna propiedad para poder venderla, cancela primero sus hipotecas \n"
          nombres_vender_vacio=true
        end
        
        if(!nombres_vender_vacio)
        nombres_a_vender << "Atras (volver a elegir la gestion a realizar por si elegiste una que no querias)"
        opcion_vender = menu("Que propiedad quieres vender?", nombres_a_vender)
        
        realizar_asignacion=true  
        if(opcion_vender == nombres_a_vender.size-1)
          realizar_asignacion=false
        end
        
        if(realizar_asignacion)
          @iPropiedad = indices_vender[opcion_vender]
        end
        end

        
      when 1
        @iPropiedad=-1
        
        indices_hipotecar=Array.new()
        nombres_hipotecar_vacio =false
        nombres_a_hipotecar = Array.new()
        i = 0
        while(i  < ((@juegoModel.getJugadorActual).propiedades).size)
          if( (((@juegoModel.getJugadorActual).propiedades[i]).hipotecado == false))
          nombres_a_hipotecar << (((@juegoModel.getJugadorActual).propiedades[i]).nombre)
          indices_hipotecar << i
          end
          i = i+1
        end
        
        if(nombres_a_hipotecar.empty?)
          puts "Ya tienes todas tus propiedades hipotecadas"
          nombres_hipotecar_vacio=true
        end
        
        #COMO EN RUBY LOS break TERMNINAN EL PROGRAMA(no se porq),NO PUEDO HACER LA MISMA DINAMICA QUE NE JAVA, OSEAQ USO BOOLEAN 
        if(!nombres_hipotecar_vacio)
        nombres_a_hipotecar << "Atras (volver a elegir la gestion a realizar por si elegiste una que no querias)" #Esto se aniade ir lo ultimo para que el if que comprueba si eliges "Atras" funcione
        opcion_hipotecar = menu("Que propiedad quieres hipotecar?", nombres_a_hipotecar)
        
        realizar_asignacion=true
        if(opcion_hipotecar == nombres_a_hipotecar.size-1)
          realizar_asignacion=false
        end
        #------Explicacion de porque (a mi juicio) no tiene que hacer la asignacion esta-------
        #Si selecciona "Atras", no nos interesa que haga esta asignacion. Por defecto el valor de iPropiedad es -1, 
        #entonces,(si seguimos un poco la cascada de metodos que se van llamando) llegamos al metodo vender de Jugador, que le pasamos como argumento 
        #el indice de la propiedad. Luego si le pasamos un -1, cuando devuelva false en el metodo existeLaPropiedad(ip), 
        #no entrará en el resto del codigo(que es lo que te hace la venta). 
        #Con esto conseguimos que aun entrando a la gestion "vender", si te equivocas y no querias escoger esa gestion, no haga nada. 
        #(En realidad no es que no haga nada, no es que vaya para "Atras", sino que sigue haciendo el bucle general de controlador y se vuelve a meter a gestionar)
        if(realizar_asignacion) 
          @iPropiedad = indices_hipotecar[opcion_hipotecar]
        end
        end
        
      when 2
        @iPropiedad=-1
        
        indices_cancelar=Array.new()
        nombres_cancelar_vacio=false
        nombres_a_cancelar = Array.new()
        i = 0
        while(i  < ((@juegoModel.getJugadorActual).propiedades).size)
          if( (((@juegoModel.getJugadorActual).propiedades[i]).hipotecado == true))
          nombres_a_cancelar << (((@juegoModel.getJugadorActual).propiedades[i]).nombre)
          indices_cancelar << i
          end
          i = i+1
        end
        
        if(nombres_a_cancelar.empty?)
          puts "No tienes ninguna propiedad hipotecada, no hay nada que cancelar"
          nombres_cancelar_vacio=true
        end
        
        if(!nombres_cancelar_vacio)
        nombres_a_cancelar << "Atras (volver a elegir la gestion a realizar por si elegiste una que no querias)"
        opcion_cancelar = menu("A que propiedad le quieres cancelar la hipoteca?", nombres_a_cancelar)
        
        realizar_asignacion=true
        if(opcion_cancelar == nombres_a_cancelar.size-1)
          realizar_asignacion=false
        end
        
        if(realizar_asignacion)
        @iPropiedad = indices_cancelar[opcion_cancelar]
        end
        end
        
       when 3
        @iPropiedad=-1 
        
         indices_construir_casa=Array.new()
         nombres_construir_casa_vacio=false
         nombres_a_construir_casa = Array.new()
         i = 0
         while(i  < ((@juegoModel.getJugadorActual).propiedades).size)
           if( (((@juegoModel.getJugadorActual).propiedades[i]).hipotecado == false))
            nombres_a_construir_casa << (((@juegoModel.getJugadorActual).propiedades[i]).nombre)
            indices_construir_casa << i
           end
            i = i+1
         end
         
         if(nombres_a_construir_casa.empty?)
           puts "Cancela primero las hipotecas de tus propiedades para poder construir casas \n"
           nombres_construir_casa_vacio=true
         end
        
        if(!nombres_construir_casa_vacio)
        nombres_a_construir_casa << "Atras (volver a elegir la gestion a realizar por si elegiste una que no querias)"
        opcion_casa = menu("A que propiedad le quieres construir una casa?", nombres_a_construir_casa)
        
        realizar_asignacion=true
        if(opcion_casa == nombres_a_construir_casa.size-1)
          realizar_asignacion=false
        end
        
        if(realizar_asignacion)
        @iPropiedad = indices_construir_casa[opcion_casa]
        end
        end
        
        
        when 4
        @iPropiedad=-1  
        
         indices_construir_hotel=Array.new()
         nombres_construir_hotel_vacio=false
         nombres_a_construir_hotel = Array.new()
         i = 0
         while(i  < ((@juegoModel.getJugadorActual).propiedades).size)
           if( (((@juegoModel.getJugadorActual).propiedades[i]).hipotecado == false))
            nombres_a_construir_hotel << (((@juegoModel.getJugadorActual).propiedades[i]).nombre)
            indices_construir_hotel << i
           end
           i = i+1
         end
         if(nombres_a_construir_hotel.empty?)
           puts "Cancela primero las hipotecas de tus propiedades para poder construir hoteles  \n"
           nombres_construir_hotel_vacio=true
         end
         
        if(!nombres_construir_hotel_vacio)
        nombres_a_construir_hotel << "Atras (volver a elegir la gestion a realizar por si elegiste una que no querias)"
        opcion_hotel = menu("A que propiedad le quieres construir un hotel?", nombres_a_construir_hotel)
        realizar_asignacion=true
        if(opcion_hotel == nombres_a_construir_hotel.size-1)
          realizar_asignacion=false
        end
        
        if(realizar_asignacion)
        @iPropiedad = indices_construir_hotel[opcion_hotel]
        end
        end

      end
    end

    def getGestion
      return @iGestion
    end

    def getPropiedad
      return @iPropiedad
    end

    def mostrarSiguienteOperacion(operacion)
      puts "La siguiente operacion a realizar es: " + operacion.to_s
    end

    def mostrarEventos
      while(Diario.instance.eventos_pendientes)
        puts Diario.instance.leer_evento
      end
    end

    def setCivitasJuego(civitas)
         @juegoModel=civitas
         self.actualizarVista
    end

    def actualizarVista
      puts "\n Jugador actual:#{(@juegoModel.getJugadorActual).toString}-#{(@juegoModel.getCasillaActual).nombre}"
    end

    
  end

end
