# encoding:utf-8

module Civitas
  class SorpresaPorCasaHotel < Sorpresa
    
    attr_reader :texto, :valor
    
    def initialize(texto,_valor)
      super(texto)
      @valor=_valor      
    end
    
    def self.constructorSorpresaPorCasaHotel(texto,valor)
      new(texto,valor)
    end
    
    def aplicarAJugador(actual,todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].modificarSaldo(@valor*(todos[actual].cantidadCasasHoteles))
      end
    end
    
    def informe(actual,todos)
      super(actual,todos)
    end
    
    public_class_method :new
    
  end
end
