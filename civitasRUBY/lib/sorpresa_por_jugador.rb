# encoding:utf-8

module Civitas
  class SorpresaPorJugador < Sorpresa
    
    attr_reader :texto, :valor
    
    def initialize(texto,_valor)
      super(texto)
      @valor=_valor      
    end
    
    def self.constructorSorpresaPorJugador(texto,valor)
      new(texto,valor)
    end
    
    def aplicarAJugador(actual,todos)
      if(jugadorCorrecto(actual,todos))
            informe(actual,todos);
            sorpresa_menos_actual=SorpresaPagarCobrar.constructorSorpresaPagarCobrar("Dando dinero(si valor positivo)/Recibiendo dinero(si valor negativo)",(@valor)*-1)  
            
            for i in 0..todos.size-1
              if(i!=actual)
                sorpresa_menos_actual.aplicarAJugador(i,todos) 
              end                                                          
            end
            
            sorpresa_del_actual=SorpresaPagarCobrar.constructorSorpresaPagarCobrar("Recibiendo dinero(si vlaor positivo)/Dando dinero(si valor negativo)",(@valor)*(todos.size-1))  
          
            sorpresa_del_actual.aplicarAJugador(actual, todos) 
        end
    end
    
    def informe(actual,todos)
      super(actual,todos)
    end
    
    public_class_method :new
    
  end
end
