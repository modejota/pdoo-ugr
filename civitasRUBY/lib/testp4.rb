# encoding:utf-8

require_relative 'casilla'
require_relative 'mazo_sorpresas'
require_relative 'sorpresa'
require_relative 'diario'
require_relative 'titulo_propiedad'
require_relative 'jugador'
require_relative 'dado'
require_relative 'tablero'
require_relative 'civitas_juego'
require_relative 'gestor_estados'
require_relative 'estados_juego'
require_relative 'vista_textual'
require_relative 'controlador'


module Civitas
  
  vista=Vista_textual.new()
  
  nombres=["Kevin1","Kevin2","Kevin3","Kevin4"]
  civitas=CivitasJuego.new(nombres)
  Dado.instance.setDebug(true)
  
  #Si queremos poner el modo debug completamente, es decir, poner tb el modo debug de MazoSorpresas(cosa que el guion no dice), 
  #hay que irse al constructor de CivitasJuego y pasarle al constructor de mazo como parametro "true"
  
  controlador=Controlador.new(civitas,vista)
  
  
  controlador.juega
end
