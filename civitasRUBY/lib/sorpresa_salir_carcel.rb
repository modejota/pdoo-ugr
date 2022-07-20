# encoding:utf-8

module Civitas
  class SorpresaSalirCarcel < Sorpresa
    
    attr_reader :texto, :carcel
    
    def initialize(texto,_mazo)
      super(texto)
      @mazo=_mazo      
    end
    
    def self.constructorSorpresaSalirCarcel(texto,mazo)
      new(texto,mazo)
    end
    
    def aplicarAJugador(actual,todos)
      hay_s = false
        
        if(jugadorCorrecto(actual, todos))
          informe(actual, todos)
          i = 0
          while (i < todos.size and !hay_s)
            if (todos[i].tieneSalvoconducto)
              hay_s = true
            end
            i = i+1
          end
          
          if(!hay_s)
            todos[actual].obtenerSalvoconducto(self)
            salirDelMazo
          end
          
        end
      
    end
    
    def informe(actual,todos)
      super(actual,todos)
    end
    
    def salirDelMazo()
      @mazo.inhabilitarCartaEspecial(self)
    end
    
    def usada()
      @mazo.habilitarCartaEspecial(self)
    end
    
    public_class_method :new
    
  end
end
