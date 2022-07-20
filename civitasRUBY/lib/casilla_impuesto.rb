# encoding:utf-8

module Civitas
  class CasillaImpuesto < Casilla
    attr_reader :importe, :nombre
    
    def initialize(_nombre,_importe)
      super(_nombre)
      @importe=_importe
    end
    
    def recibeJugador(actual,todos)
      if(jugadorCorrecto(actual,todos))
        informe(actual,todos)
        (todos[actual]).pagaImpuesto(@importe)
      end
    end
    
    def toString
      super
    end
    
    def self.constructorCasillaImpuesto(_nombre,_importe)
      new(_nombre,_importe)
    end

  end
end
