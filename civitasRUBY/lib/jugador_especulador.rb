# encoding:utf-8

module Civitas
  class JugadorEspeculador < Jugador
    
    @@FactorEspeculador=2
    
    def initialize(_fianza)
      @fianza=_fianza
      #super(_nombre)
    end

    
    def self.nuevoEspeculador(jugador,_fianza)
      especulador=new(_fianza)
      especulador.auxCopia(jugador)
      
      (especulador.propiedades).each do |var|
        var.actualizaPropietarioPorConversion(self)
      end
      
      return especulador
    end
    
    def debeSerEncarcelado()
      if(@encarcelado)
        return false
      else
        if(tieneSalvoconducto())
          perderSalvoconducto()
          Diario.instance.ocurre_evento("El jugador especulador se ha librado de ir a la carcel. Ha usado el salvoconducto")
          return false
          
        else
          if(@saldo>=@fianza)
            paga(@fianza)
            Diario.instance.ocurre_evento("El jugador especulador se ha librado de ir a la carcel pagando la fianza")
            return false
            
          else
            return true
          end
        end
      end
      
    end
    
    def pagaImpuesto(cantidad)
      if(@encarcelado)
        return false
        
      else
        return paga(cantidad/2)
      end
    end
    
    def toString()
      nombres_propiedades=""
      
      @propiedades.each do |var|
        nombres_propiedades+="#{var.nombre}(casas=#{var.numCasas})(hoteles=#{var.numHoteles})(hipotecado=#{var.hipotecado})--" 
      end
      
      return "\n\n *Jugador Especulador* 
              \t Nombre: #{@nombre}  
              \t Saldo: #{@saldo}
              \t FactorEspeculador: #{@@FactorEspeculador}
              \t Fianza: #{@fianza}
              \t Encarcelado: #{@encarcelado}
              \t Numero de propiedades: #{@propiedades.length()}
              \t Propiedades: #{nombres_propiedades}
              \t Casilla actual(numero-nombre): #{@numCasillaActual}" #El numero lo pongo aqui, y el nomrbe de la casilla lo pone la otra parte de actualizarVista(que es quien me llama)
    end
    
    def getCasasMax()
      return @@CasasMax*@@FactorEspeculador
    end
    
    def getHotelesMax()
      return @@HotelesMax*@@FactorEspeculador
    end

  end
end
