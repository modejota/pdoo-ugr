# encoding:utf-8

require_relative 'dado'
require_relative 'titulo_propiedad'
module Civitas
  
  class Casilla
    attr_reader :nombre
    
    def initialize(unNombre)
      @nombre = unNombre
    end
    
    #Para crear estos constructores tenemos que llamar al NEW ya que si no no estamos reservando memoria ni nada
    #El NEW llama al INITIALIZE, ponemos valores a lo que queremos, y a lo que no pues unos no válidos o por defecto
    
    def self.constructorCasilla(_nombre)
      new(_nombre)
    end
    
    def toString
      return "\n\n *#{self.class.to_s}* 
      Nombre: #{@nombre} \n"
    end
    
    def jugadorCorrecto(actual, todos)
      return (actual >= 0 and actual < todos.size)
    end
    
    def recibeJugador(actual,todos)
      informe(actual,todos)
    end
    
    
    private
  
    def informe(actual, todos)
      Diario.instance.ocurre_evento("El jugador #{todos[actual].nombre} ha caido en la casilla #{self.toString} " )
    end

  end
end
 
