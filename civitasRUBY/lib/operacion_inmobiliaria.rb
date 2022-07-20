# encoding:utf-8

module Civitas
  class OperacionInmobiliaria
    attr_reader :gestion, :numPropiedad
    
    def initialize(gest,ip)
      @gestion=gest
      @numPropiedad=ip      
    end
    
  end
end
