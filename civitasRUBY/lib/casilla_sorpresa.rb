# encoding:utf-8

module Civitas
  class CasillaSorpresa < Casilla
    
    attr_reader :nombre, :mazo
    
    def initialize(_nombre,_mazo)
      super(_nombre)
      @mazo=_mazo      
    end
    
    def recibeJugador(actual,todos)
      if(jugadorCorrecto(actual,todos))
        sorpresa=@mazo.siguiente
        informe(actual,todos)
        sorpresa.aplicarAJugador(actual,todos)
     end
    end
    
    def self.constructorCasillaSorpresa(_nombre,_mazo)
      new(_nombre,_mazo)
    end
    
    def toString
      super
    end
    
  end
end
