# encoding:utf-8 

module Civitas
  class MazoSorpresas
    attr_reader :sorpresas
    
    def initialize(*v) #He "sobrecargado" el initialize pasando como vector los parametros que entren y quedandome con el 1º
      if(v.size !=0)   #En el guion pone que podemos poner un unico parametro por defecto a false
        d=v[0]
      else
        d=false
      end
      init
      @debug=d
      if(d)
        Diario.instance.ocurre_evento("Se ha activado el modo debug del MazoSorpresas")
      end
    end
    
    private
    def init
      @sorpresas=[]  
      @cartasEspeciales=[]
      @barajada=false
      @usadas=0
      @ultimaSorpresa
    end
    
    public
    def alMazo(s)
      if(!@barajada)
        @sorpresas << s
      end
    end
    
    def siguiente
        if(!@barajada || @usadas==@sorpresas.length)
          if(!@debug)
          @sorpresas.shuffle!
          @barajada=true
          @usadas=0
        end
      end
      
      @usadas=@usadas+1
      @ultimaSorpresa=@sorpresas[0]
      @sorpresas.rotate!(1)
      return @ultimaSorpresa
    end
    
    
    def inhabilitarCartaEspecial(sorpresa)
      if(@sorpresas.include?(sorpresa))
        @sorpresas.delete(sorpresa)
        @cartasEspeciales << sorpresa
        Diario.instance.ocurre_evento("Una carta especial se ha aniadido a cartasEspeciales")
      end
    end
    
    def habilitarCartaEspecial(sorpresa)
      if(@cartasEspeciales.include?(sorpresa))
        @cartasEspeciales.delete(sorpresa)
        @sorpresas << sorpresa
        Diario.instance.ocurre_evento("Una carta sorpresa se ha aniadido a sorpresas")
      end
    end
    
  end
end
