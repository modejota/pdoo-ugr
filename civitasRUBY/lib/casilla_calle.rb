# encoding:utf-8

module Civitas
  class CasillaCalle < Casilla
    
    attr_reader :importe, :nombre, :titulo
    
    def initialize(_nombre,_titulo,_importe)
      super(_nombre)
      @titulo=_titulo
      @importe=_importe
    end
    
    def self.constructorCasillaCalle(_nombre,_titulo,_importe)
      new(_nombre,_titulo,_importe)
    end
    
    def recibeJugador(actual,todos)
      if(jugadorCorrecto(actual,todos))
        informe(actual,todos)
        nuevo=todos[actual]
       
        if(!@titulo.tienePropietario)
          nuevo.puedeComprarCasilla
        else
          @titulo.tramitarAlquiler(nuevo)
        end
      end
    end
    
    def toString
      super
    end

  end
end
