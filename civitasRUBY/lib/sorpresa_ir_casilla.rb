# encoding:utf-8

module Civitas
  class SorpresaIrCasilla < Sorpresa
    
    attr_reader :texto, :valor, :tablero
    
    def initialize(texto,_tablero,_valor)
      super(texto)
      @tablero=_tablero
      @valor=_valor
    end
    
    def self.constructorSorpresaIrCasilla(texto,tablero,valor)
      new(texto,tablero,valor)
    end
    
    def aplicarAJugador(actual,todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        casilla_actual = (todos[actual]).numCasillaActual
        tirada = @tablero.calcularTirada(casilla_actual, @valor)
        nueva_pos = @tablero.nuevaPosicion(casilla_actual, tirada)
        todos[actual].moverACasilla(nueva_pos)
        (@tablero.getCasilla(@valor)).recibeJugador(actual, todos)
      end
    end
    
    def informe(actual,todos)
      super(actual,todos)
    end
    
    public_class_method :new
  end
end
