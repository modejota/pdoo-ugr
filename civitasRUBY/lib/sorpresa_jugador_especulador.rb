# encoding:utf-8

require_relative 'jugador'
require_relative 'jugador_especulador'


module Civitas
  class SorpresaJugadorEspeculador < Sorpresa
    
    attr_reader :valor, :texto
    
    def initialize(texto,_valor)
      super(texto)
      @valor=_valor      
    end
    
    def self.constructorSorpresaJugadorEspeculador(texto,valor)
      new(texto,valor)
    end
    
    def aplicarAJugador(actual,todos)
        if(jugadorCorrecto(actual,todos))
          informe(actual,todos)
          todos[actual]=JugadorEspeculador.nuevoEspeculador(todos[actual],@valor)
        end
    end
    
    def informe(actual,todos)
      super(actual,todos)
    end
    
    public_class_method :new
    
  end
end
