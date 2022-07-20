# encoding:utf-8

module Civitas
  class CasillaJuez < Casilla
    
    attr_reader :carcel, :nombre
    
    def initialize(_nombre,_carcel)
      super(_nombre)
      @carcel=_carcel
    end
    
    def recibeJugador(actual,todos)
        if(jugadorCorrecto(actual,todos))
            informe(actual,todos)
            todos[actual].encarcelar(@carcel)
        end
    end
    
    def toString
      super
    end
    
    def self.constructorCasillaJuez(_nombre,_carcel)
      new(_nombre,_carcel)
    end

  end
end
