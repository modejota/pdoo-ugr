# encoding:utf-8

module Civitas
  class SorpresaIrCarcel < Sorpresa
    attr_reader :tablero, :texto
    
    def initialize(texto,_tablero)
      super(texto)
      @tablero=_tablero
    end
    
    def self.constructorSorpresaIrCarcel(texto,tablero)
      new(texto,tablero)
    end
    
    def aplicarAJugador(actual,todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        (todos[actual]).encarcelar(@tablero.numCasillaCarcel)
      end
    end
    
    def informe(actual,todos)
      super(actual,todos)
    end
    
    public_class_method :new
    
    
  end
end
