# encoding:utf-8

require 'singleton'
require_relative 'diario.rb'
module Civitas
  class Dado
    include Singleton
    
    @@SalidaCarcel=5
    
    attr_reader :ultimoResultado  
    
    def initialize
      @debug=false
      @ultimoResultado=0
    end
    
    # Para tirar del dado usamos el generador de numeros aleatorios rand.
    def tirar()
      if(@debug)
        @ultimoResultado=1
        return 1
        
      else
        aleatorio=rand(6)+1
        @ultimoResultado=aleatorio
        return aleatorio
      end
    end
    
    # Vemos si el valor de la tirada es mayor o igual a 5, si es así, salgo de la carcel
    def salgoDeLaCarcel()
      return (tirar>=5)
    end
    
    # Hacemos una tirada random para ver quien empieza.
    def quienEmpieza(n)
      return rand(n)
    end
    
    def setDebug(d)
      @debug=d
      if(d==true)
        Diario.instance.ocurre_evento("El modo debug del Dado se ha activado")
      else
        Diario.instance.ocurre_evento("El modo debug del Dado se ha desactivado")
      end
    end
    
  end
end
