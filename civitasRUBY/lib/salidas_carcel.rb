#encoding utf-8

module Civitas
  module Salidas_carcel 
      PAGANDO = :PAGANDO
      TIRANDO = :TIRANDO
      @@lista_carcel=[Salidas_carcel::PAGANDO, Salidas_carcel::TIRANDO]
      
    def self.lista_carcel(indice)
      return @@lista_carcel[indice]
    end
  end
end