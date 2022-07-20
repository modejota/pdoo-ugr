# encoding:utf-8

module Civitas
  class Sorpresa
    attr_reader :texto, :tablero
    
    def initialize(unTexto)
      @texto=unTexto
    end
    
    def jugadorCorrecto(actual, todos)
      return (actual >= 0 and actual < todos.size)
    end

    
    def toString
      return "\n\n *#{self.class.to_s}* 
              \n\t Texto: #{@texto}"
    end
    
    #Dejamos esto vacio, las clases hijas ya lo sobreescribirán
    def aplicarAJugador(actual, todos)
    end 
    
    protected
    
    def informe(actual, todos)
      Diario.instance.ocurre_evento("Se esta aplicando una sorpresa *#{self.class.to_s}* al jugador #{todos[actual].nombre} que dice: #{@texto}")
    end
    
    #Como Ruby no soprta las clases ni metodos abstractos, hacemos la clase Sorpresa que no sea instanciable poniendo privado el new aquí
    #Y en las hijas lo ponemos publico para que si se puedan instanciar
    private_class_method :new
     
  end
end