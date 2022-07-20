# encoding:utf-8

require_relative 'jugador'

module Civitas
  class TituloPropiedad
    attr_reader :nombre, :hipotecado, :numCasas, :numHoteles, :precioCompra, :precioEdificar, :propietario
    
    @@factorInteresesHipoteca=1.1    #Por lo que dicen las normas del juego entendemos que esto es fijo a todas las calles.
    
    def initialize(_nombre, _alquilerbase, _factorrevalorizacion, _hipotecabase, _preciocompra, _precioedificar)
      @alquilerBase=_alquilerbase;
      @factorRevalorizacion=_factorrevalorizacion;
      @hipotecaBase=_hipotecabase;
      @hipotecado=false;
      @nombre=_nombre;
      @numCasas=0;
      @numHoteles=0;
      @precioCompra=_preciocompra;
      @precioEdificar=_precioedificar;
      @propietario=nil;
    end
    
    # Todos los atributos de instancia que tienen attr_reader no necesitan de la @ para acceder a su contenido, como en este caso, en el 
    # que no se le pone, aunque yo se la pondría por si acaso.
    
    def toString()
      return "\n\n *TituloPropiedad* 
            \n\t Calle: #{nombre}
            \n\t PrecioCompra: #{precioCompra} 
            \n\t AlquilerBase:  #{@alquilerBase} 
            \n\t HipotecaBase: #{@hipotecaBase} 
            \n\t PrecioEdificar: #{precioEdificar} 
            \n\t FactorRevalorizacion: #{@factorRevalorizacion} 
            \n\t NumHoteles: #{numHoteles} 
            \n\t NumCasas: #{numCasas} 
            \n\t Hipotecado:  #{hipotecado}";
                
    end
    
    def getImporteCancelarHipoteca()
      return (@@factorInteresesHipoteca *(@hipotecaBase*(1+(@numCasas*0.5)+(@numHoteles*2.5))));
    end
    
    def cancelarHipoteca(jugador)
        result = false
        
        if(@hipotecado)
          if(esEsteElPropietario(jugador))
            @propietario.paga(getImporteCancelarHipoteca())
            @hipotecado = false
            result = true
          end
        end
        
        return result
    end
    
    def hipotecar(jugador)
        salida=false
        
        if(!@hipotecado and esEsteElPropietario(jugador))
            @propietario.recibe(getImporteHipoteca())
            @hipotecado=true
            salida = true
        end
        
        return salida
    end
    
    def tramitarAlquiler(jugador)
      if(tienePropietario) 
            if(!esEsteElPropietario(jugador))
             precio=getPrecioAlquiler
             jugador.pagaAlquiler(precio)
             @propietario.recibe(precio)
            end
      end
    end
    
    def cantidadCasasHoteles()
      return (@numCasas + @numHoteles)
    end
    
    def derruirCasas(n,jugador)
      operando=false;
       
        if(esEsteElPropietario(jugador)) 
            if(@numCasas>=n) 
                @numCasas=@numCasas-n
                operando=true
            end
        end
        
        return operando;
    end
    
    def construirCasa(jugador)
        result = false
        if(esEsteElPropietario(jugador) and !@hipotecado) 
            jugador.paga(@precioEdificar)
            @numCasas=@numCasas+1
            result = true
        end
        
        return result
    end
    
    def construirHotel(jugador)
      result= false
        if(esEsteElPropietario(jugador) and !@hipotecado) 
            jugador.paga(@precioEdificar)
            @numHoteles=@numHoteles+1
            result=true
        end
        
        return result
    end
    
    def comprar(jugador)
      resultado=false
        if(!tienePropietario) 
            @propietario=jugador
            resultado=true
            @propietario.paga(@precioCompra)
        end
        return resultado
    end
    
    def actualizaPropietarioPorConversion(jugador)
      @propietario=jugador
    end
    
    def tienePropietario()
      return (@propietario!=nil)
    end
    
    def vender(jugador)
      resultado=false
        if(esEsteElPropietario(jugador) and !@hipotecado)
            jugador.recibe(getPrecioVenta())
            @propietario=nil
            @numCasas=0
            @numHoteles=0
            resultado=true
       
        else
            resultado=false
        end
        
        return resultado
    end
    
    
    private
    
      def getPrecioAlquiler()
        if(@hipotecado || propietarioEncarcelado())
            return 0;
        
        else 
            return (@alquilerBase * (1+(@numCasas*0.5) + (@numHoteles * 2.5)))
        end
      end
      
      
      def propietarioEncarcelado()
        return @propietario.isEncarcelado();
      end
      
      def getImporteHipoteca()
        return (@hipotecaBase*(1+(@numCasas*0.5)+(@numHoteles*2.5)));
      end
      
      def getPrecioVenta()
        return (@precioCompra + (@precioEdificar * (@numCasas + @numHoteles) * @factorRevalorizacion));
      end
      
      def esEsteElPropietario(jugador)
        es_mia=false 
        i=0
        while (i<jugador.propiedades.length() and !es_mia)
            if(jugador.propiedades[i]==self)
                es_mia=true
            end
            i=i+1
        end
        
        return es_mia
      end
      
      
  end
end
