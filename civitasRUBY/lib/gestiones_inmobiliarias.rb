#encoding utf-8

module Civitas
  module GestionesInmobiliarias 
      VENDER = :VENDER
      HIPOTECAR = :HIPOTECAR
      CANCELAR_HIPOTECA = :CANCELAR_HIPOTECA
      CONSTRUIR_CASA = :CONSTRUIR_CASA  
      CONSTRUIR_HOTEL = :CONSTRUIR_HOTEL
      TERMINAR = :TERMINAR
      
      @@lista_gestiones=[GestionesInmobiliarias::VENDER, GestionesInmobiliarias::HIPOTECAR, GestionesInmobiliarias::CANCELAR_HIPOTECA,
                      GestionesInmobiliarias::CONSTRUIR_CASA, GestionesInmobiliarias::CONSTRUIR_HOTEL, GestionesInmobiliarias::TERMINAR]
      
      def self.lista_gestiones(indice)
        return @@lista_gestiones[indice]
      end
  end
end




