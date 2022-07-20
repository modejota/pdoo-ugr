# encoding:utf-8
require_relative 'gestiones_inmobiliarias'
require_relative 'operacion_inmobiliaria'
require_relative 'salidas_carcel'

module Civitas
  class Controlador
    def initialize(_juego, _vista)
        @juego = _juego
        @vista = _vista
    end
    
    def juega
      @vista.setCivitasJuego(@juego)
      
      while(!@juego.finalDelJuego)
        @vista.actualizarVista
        @vista.pausa
        operacion = @juego.siguientePaso
        @vista.mostrarSiguienteOperacion(operacion)
        
        if(operacion != Operaciones_juego::PASAR_TURNO)
          @vista.mostrarEventos
        end
        
        if(!@juego.finalDelJuego)
          case(operacion)
            when Operaciones_juego::COMPRAR
              eleccion = @vista.comprar
              
              if(eleccion == Respuestas::SI)
                @juego.comprar
              end
              @juego.siguientePasoCompletado(operacion)
          
            when Operaciones_juego::GESTIONAR
              @vista.gestionar
              gestion = GestionesInmobiliarias::lista_gestiones(@vista.getGestion)
              propiedad = @vista.getPropiedad
              operacion_inmobiliaria = OperacionInmobiliaria.new(gestion, propiedad)
              
              case(gestion)
                when GestionesInmobiliarias::VENDER
                  @juego.vender(propiedad)
                when GestionesInmobiliarias::HIPOTECAR
                  @juego.hipotecar(propiedad)
                  
                when GestionesInmobiliarias::CANCELAR_HIPOTECA
                  @juego.cancelarHipoteca(propiedad)
                  
                when GestionesInmobiliarias::CONSTRUIR_CASA
                  @juego.construirCasa(propiedad)
                  
                when GestionesInmobiliarias::CONSTRUIR_HOTEL
                  @juego.construirHotel(propiedad)
                  
               when GestionesInmobiliarias::TERMINAR
                 @juego.siguientePasoCompletado(operacion)
              end
              
          when Operaciones_juego::SALIR_CARCEL
            metodo_salida = @vista.salirCarcel
            if(metodo_salida == Salidas_carcel::PAGANDO)
              @juego.salirCarcelPagando
            else
              @juego.salirCarcelTirando
              
            end
            @juego.siguientePasoCompletado(operacion)
          end
        end
      end
      @juego.actualizarInfo
    end
    
    
  end #Fin de clase
end #Fin de modulo
