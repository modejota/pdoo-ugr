# encoding:utf-8 

module Civitas
  class Jugador
    # Metodos GET para los atributos de instancia. Recordemos que con los de clase no podemos usar los attr_X
    attr_reader :nombre, :encarcelado, :numCasillaActual, :saldo, :puede_comprar, :propiedades, :salvoconducto
    
    # Metodos de clase, comunes a todos los jugadores. Podriamos ponerlos como constantes, pero como no
    # se intentan modificar en ningun método pues nos lo hemos ahorrado.
    @@CasasMax=4
    @@HotelesMax=4
    @@CasasPorHotel=4
    @@PasoPorSalida=1000
    @@PrecioLibertad=200
    @@SaldoInicial=7500
    
    def initialize(_nombre)
        @nombre=_nombre
        @encarcelado=false
        @numCasillaActual=0
        @saldo=@@SaldoInicial
        @puede_comprar=true
        @salvoconducto=nil
        @propiedades=[]
    end
    
    #Esto lo hago para poder acceder a los atributos de instancia. Si lo hubiera puesto todo en el self.copia, serian atributos de instancia de clase y no estaria haciendo nada
    def auxCopia(otro)
      @nombre=otro.nombre
      @encarcelado=otro.encarcelado
      @numCasillaActual=otro.numCasillaActual
      @saldo=otro.saldo
      @puede_comprar=otro.puede_comprar
      @salvoconducto=otro.salvoconducto
      @propiedades=otro.propiedades
    end
    
    #Constructor de copia
    def self.copia(otro)
      player=Jugador.new(otro.nombre)
      player.auxCopia(otro)
      return player
    end
    
    def debeSerEncarcelado()
      if(@encarcelado)
           return false
      else 
          if(tieneSalvoconducto())
            perderSalvoConducto()
            Diario.instance.ocurre_evento("Se ha librado de ir a la carcel. Ha usado el salvoconducto")
            return false
          
          else
            return true
          end
      end
    end
    
    
    def isEncarcelado()
      return @encarcelado
    end
    
    def encarcelar(numCasillaCarcel)
      if(debeSerEncarcelado)
            moverACasilla(numCasillaCarcel)
            @encarcelado=true
            Diario.instance.ocurre_evento("Se ha encarcelado al jugador")
      end
        return @encarcelado
    end
    
    def tieneSalvoconducto()
      return @salvoconducto!=nil
    end
    
    def obtenerSalvoconducto(s)
      if(!@encarcelado)
        @salvoconducto=s
        return true
        
      else
        return false
      end
    end
    
    def enBancarrota()
      return @saldo<0
    end
    
    def puedeComprarCasilla()
      @puede_comprar = !@encarcelado
      return @puede_comprar
    end
    
    def paga(cantidad)
      return modificarSaldo(-1*cantidad)
    end
    
    def pagaImpuesto(cantidad)
      if(@encarcelado)
            return false
        else
            return paga(cantidad)
      end
    end
    
    def pagaAlquiler(cantidad)
      if(@encarcelado)
            return false
        else
            return paga(cantidad)
      end
    end
    
    def recibe(cantidad)
      if(@encarcelado)
            return false
        else
            return modificarSaldo(cantidad)
      end
    end
    
    def modificarSaldo(cantidad)
        @saldo=@saldo+cantidad;
        Diario.instance.ocurre_evento("Ahora el saldo de #{@nombre} es #{@saldo}")
        return true
    end
    
    def moverACasilla(numCasilla)
      if(@encarcelado)
            return false
        else 
            @numCasillaActual = numCasilla
            @puede_comprar = false
            Diario.instance.ocurre_evento("Se ha movido al jugador #{@nombre} a la casilla #{@numCasillaActual}")
            return true
      end
    end
    
    def comprar(titulo)
      result=false
      if(@encarcelado)
        return result
      end
      
      if(@puede_comprar)
        precio=titulo.precioCompra
        
        if(puedoGastar(precio))
          result=titulo.comprar(self)
          
          if(result)
            @propiedades << titulo
            Diario.instance.ocurre_evento("El jugador #{@nombre} compra la propiedad #{titulo.nombre}")
          end
          @puede_comprar=false;
        end
      end
      return result
    end
    
    def vender(ip)
      if(!@encarcelado)
            if(existeLaPropiedad(ip))
                if((@propiedades[ip]).vender(self))
                    @propiedades.delete_at(ip)
                    Diario.instance.ocurre_evento("Se ha vendido el titulo de propiedad")
                    return true
                end
            else
                return false
            end
      end
        return false
    end
    
    def tiene_algo_que_gestionar()   
      return !@propiedades.empty?   
    end
    
    def salirCarcelPagando()
      if(puedeSalirCarcelPagando and @encarcelado)
            paga(@@PrecioLibertad)
            @encarcelado=false
            Diario.instance.ocurre_evento("El jugador #{@nombre} ha salido de la carcel pagando")
            return true
    
        else
            return false
      end
    end
    
    def salirCarcelTirando()
      sale=Dado.instance.salgoDeLaCarcel
        if(sale)
            @encarcelado=false
            Diario.instance.ocurre_evento("El jugador #{@nombre} ha salido de la carcel tirando")
        end
        return sale
    end
    
    def pasaPorSalida()
        modificarSaldo(@@PasoPorSalida);
        Diario.instance.ocurre_evento("Se ha pasado por la salida")
        return true
    end
    
    def <=>(otro) #En Ruby no hay metodo compareTo, está el metodo <=>, que a su vez usa el operador <=> que ya tiene Ruby(es como un Override)
      #Explica como funciona y como crear el metodo aqui: https://stackoverflow.com/questions/827649/what-is-the-ruby-spaceship-operator
      return (@saldo<=>otro.saldo)
    end
    
    def cantidadCasasHoteles()
      suma=0
      i=0
        while(i<@propiedades.length())   
            suma=suma+@propiedades[i].cantidadCasasHoteles
            i=i+1
        end
    return suma
    end
    
    def toString()
      nombres_propiedades=""
      i=0
      while(i <@propiedades.size)
        nombres_propiedades+="#{(@propiedades[i]).nombre}(casas=#{(@propiedades[i]).numCasas})(hoteles=#{(@propiedades[i]).numHoteles})(hipotecado=#{(@propiedades[i]).hipotecado})--"
      i=i+1
      end
      return "\n\n *Jugador* 
              \t Nombre: #{@nombre}  
              \t Saldo: #{@saldo}
              \t Encarcelado: #{@encarcelado}
              \t Numero de propiedades: #{@propiedades.length()}
              \t Propiedades: #{nombres_propiedades}
              \t Casilla actual(numero-nombre): #{@numCasillaActual}" #El numero lo pongo aqui, y el nomrbe de la casilla lo pone la otra parte de actualizarVista(que es quien me llama)
              
    end
    
    def hipotecar(ip)
      result = false
      if(@encarcelado)
        return result
      end
      
      if(existeLaPropiedad(ip))
        propiedad = @propiedades[ip]
        result = propiedad.hipotecar(self)
        
        if(result)
          Diario.instance.ocurre_evento("El jugador #{@nombre} hipoteca la propiedad #{ip}")
        end
      end
      return result
    end
    
    def cancelarHipoteca(ip)
      result = false
      if(@encarcelado)
        return result
      end
      
      if(existeLaPropiedad(ip))
        propiedad = @propiedades[ip]
        cantidad = propiedad.getImporteCancelarHipoteca
        puedoGastar = puedoGastar(cantidad)
        
        if(puedoGastar)
          result = propiedad.cancelarHipoteca(self)
          
          if(result)
            Diario.instance.ocurre_evento("El jugador #{@nombre} cancela la hipoteca de la propiedad #{ip}")
          end
        end
      end
      return result
    end
    
    def construirCasa(ip)
      result = false
      puedoEdificarCasa = false
      if(@encarcelado)
        return result
         
      else
      existe = existeLaPropiedad(ip)
      if(existe)
        propiedad = @propiedades[ip]
        puedoEdificarCasa = puedoEdificarCasa(propiedad)
        
        if(puedoEdificarCasa)
          result = propiedad.construirCasa(self)
          
          if(result)
            Diario.instance.ocurre_evento("El jugador #{@nombre} construye casa en su propiedad #{ip}")
          end
        end
      end
      end
    return result
    end
    
    def construirHotel(ip)
      result = false
      if(@encarcelado)
        return result
      end
      
      if(existeLaPropiedad(ip))
        propiedad = @propiedades[ip]
        puedoEdificarHotel = puedoEdificarHotel(propiedad)
        
        if(puedoEdificarHotel)
          result = propiedad.construirHotel(self)
          casasPorHotel = @@CasasPorHotel
          propiedad.derruirCasas(casasPorHotel, self)
          Diario.instance.ocurre_evento("El jugador #{@nombre} construye hotel en la propiedad #{ip}")
        end
      end
      return result
    end
    
    def getCasasMax()
      return @@CasasMax
    end
    
    def getHotelesMax()
      return @@HotelesMax
    end
    private
    
    def perderSalvoConducto()
        @salvoconducto.usada
        @salvoconducto=nil
    end
    
    def puedoGastar(precio)
      if(@encarcelado)
            return false
        else
            return (@saldo>=precio)
      end
    end
    
    def puedeSalirCarcelPagando()
      return (@saldo>=@@PrecioLibertad)
    end
    
    def existeLaPropiedad(ip)
      return (ip>=0 && ip<@propiedades.length)
    end

    def self.casasPorHotel()
      @@CasasPorHotel
    end
    
    def self.precioLibertad()
      @@PrecioLibertad
    end
    
    def self.pasoPorSalida()
      @@PasoPorSalida
    end
    
    def puedoEdificarCasa(propiedad)
      puedoEdificarCasa=false;
      precio = propiedad.precioEdificar
      
      if(puedoGastar(precio))
        if(propiedad.numCasas<getCasasMax())
          puedoEdificarCasa = true
        end
      end
        
       return puedoEdificarCasa
    end
    
    def puedoEdificarHotel(propiedad)
      puedoEdificarHotel = false
      precio = propiedad.precioEdificar
      
      if(puedoGastar(precio))
        if(propiedad.numHoteles < getHotelesMax())
          if(propiedad.numCasas >= @@CasasPorHotel)
            puedoEdificarHotel = true
          end
        end
      end
      return puedoEdificarHotel
    end
    
  end
end
