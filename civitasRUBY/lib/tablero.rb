# enconding:utf-8

require_relative 'casilla'

module Civitas
  class Tablero
    
    attr_reader :numCasillaCarcel, :casillas
    
    def initialize(indCarcel)
      if(indCarcel >= 1 and indCarcel <= 19)
      @numCasillaCarcel = indCarcel
      else
      @numCasillaCarcel = 1
      end
      @porSalida = 0
      @tieneJuez = false
      @casillas = Array.new
      @casillas << Casilla.constructorCasilla("SALIDA")
    end
    
    def getPorSalida
      antes=@porSalida
      if(@porSalida > 0)
        @porSalida = @porSalida - 1
      end
        return antes
    end
    
    def aniadeCasilla(casilla)
      if(@numCasillaCarcel==@casillas.length)
        @casillas << Casilla.constructorCasilla("CARCEL")
      end
      
        @casillas << casilla
        
      if(@numCasillaCarcel==@casillas.length)
        @casillas << Casilla.constructorCasilla("CARCEL")
      end
      
    end
    
    def aniadeJuez()
      if(@tieneJuez == false)
        @casillas << CasillaJuez.constructorCasillaJuez("JUEZ", @numCasillaCarcel)
        @tieneJuez = true
      end
    end
    
    def getCasilla(numCasilla)
      if(correcto(numCasilla))
        return @casillas[numCasilla]
      else
         return nil
      end
    end
    
    def nuevaPosicion(actual, tirada)
      nuevaposicion=0
      if(!correctoAux)
        return -1
      else
        nuevaposicion=(actual+tirada)%@casillas.length
         if ((actual+tirada)!=nuevaposicion)
           @porSalida=@porSalida+1
         end
      end
      return nuevaposicion
    end
    
    def calcularTirada(origen, destino)
      tirada_aux=destino-origen
      if (tirada_aux>=0)
        tirada_res=tirada_aux
      else
        tirada_res=tirada_aux+@casillas.length
      end
      return tirada_res
    end
    
    private 
    def correctoAux()
      return (@numCasillaCarcel < @casillas.size and @tieneJuez)
    end
    
     
    def correcto (numCasilla)
       return (correctoAux and numCasilla < @casillas.size)
    end
    

  end
end
