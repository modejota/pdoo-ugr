#encoding utf-8

module Civitas
  module Respuestas 
    NO = :NO  
    SI = :SI
    
    @@lista_Respuestas=[Respuestas::SI,Respuestas::NO]
    
    def self.lista_Respuestas(indice)
      return @@lista_Respuestas[indice]
    end
  end
end