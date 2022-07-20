package GUI;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Jugador;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class CivitasView extends javax.swing.JFrame {
    protected CivitasJuego juego;
    protected JugadorPanel jugadorPanel;
    protected GestionarDialog gestionarD;
    
    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
        jugadorPanel = new JugadorPanel();
        contenedorVistaJugador.add(jugadorPanel);
        gestionarD = new GestionarDialog(this);

        repaint();
        revalidate();
    }
    
    void setCivitasJuego(CivitasJuego juegoM) {
        juego = juegoM;
        this.setVisible(true);
    }
    
    void actualizarVista() {
        jugadorPanel.setJugador(juego.getJugadorActual());
        
        //Actualiza los "registros" que hay de los jugadores y sus posiocnes en el tablero
        jugador1.setText(juego.getJugadores().get(0).getNombre());
        jugador2.setText(juego.getJugadores().get(1).getNombre());
        jugador3.setText(juego.getJugadores().get(2).getNombre());
        jugador4.setText(juego.getJugadores().get(3).getNombre());
        
        posicionJugador1.setText(getCasillaActualDeJugador(0).getNombre() + "-" + Integer.toString(juego.getJugadores().get(0).getNumCasillaActual()));
        posicionJugador2.setText(getCasillaActualDeJugador(1).getNombre() + "-" + Integer.toString(juego.getJugadores().get(1).getNumCasillaActual()));
        posicionJugador3.setText(getCasillaActualDeJugador(2).getNombre() + "-" + Integer.toString(juego.getJugadores().get(2).getNumCasillaActual()));
        posicionJugador4.setText(getCasillaActualDeJugador(3).getNombre() + "-" + Integer.toString(juego.getJugadores().get(3).getNumCasillaActual()));
        
        //Pone visible o no el ranking, en funcion de si se acaba el juego
        ranking_textArea.setVisible(false);
        ranking_label.setVisible(false);
        
        if(juego.finalDelJuego()){
            ranking_textArea.setVisible(true);
            ranking_label.setVisible(true);
            
            ranking_textArea.setText(juego.stringRanking());
            
            repaint();
            revalidate();
        }
        
        actualizarBordes();
            
    }
    
    void setTablero(){
        imagenTablero1.setVisible(false);
        imagenTablero2.setVisible(false);
        imagenTablero3.setVisible(false);
        imagenTablero4.setVisible(false);
        imagenTablero5.setVisible(false);
        imagenTablero6.setVisible(false);
        imagenTablero7.setVisible(false);
        imagenTablero8.setVisible(false);
        imagenTablero9.setVisible(false);
        imagenTablero10.setVisible(false);
        imagenTablero11.setVisible(false);
        imagenTablero12.setVisible(false);
        imagenTablero13.setVisible(false);
        imagenTablero14.setVisible(false);
        imagenTablero15.setVisible(false);
        imagenTablero16.setVisible(false);
        imagenTablero17.setVisible(false);
        imagenTablero18.setVisible(false);
        imagenTablero19.setVisible(false);
        
        
    }
    
    Casilla getCasillaActualDeJugador(int num_jugador){
        return juego.getTablero().getCasilla(juego.getJugadores().get(num_jugador).getNumCasillaActual());
    }
    
    void mostrarSiguienteOperacion(OperacionesJuego operacion){
        //En el guion pone "actualizar" el textfield: ¿se refiere a "asociar" como en las anteriores veces, o hay otra manera de hacer esto?
        mostrar_siguienteOperacion.setText(operacion.toString());
        actualizarVista();
    }
    
    void mostrarEventos() {
        DiarioDialog diarioD= new DiarioDialog(this); //crea la ventana del diario
        //Aqui puede ser que necesitemos poner algo mas (solo he copiao el codigo del guion)
    }
    
    Respuestas comprar(){
       int opcion=JOptionPane.showConfirmDialog(null, "¿Quieres comprar la calle actual?", "Compra", JOptionPane.YES_NO_OPTION);
       
       if(opcion==0)
           return Respuestas.SI;
       else
           return Respuestas.NO;
    }
    
    void gestionar(Jugador jugador){
        gestionarD.gestionar(jugador);
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        gestionarD.setVisible(true);
    }
    
    SalidasCarcel salirCarcel(){
        String opciones[]={"Pagando","Tirando"};        
        
        int respuesta= JOptionPane.showOptionDialog(null, "¿Cómo quieres salir de la cárcel?",
        "Salir de la cárcel", JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,null, opciones, opciones[0]);
        
        return SalidasCarcel.values()[respuesta];
    }
    
    int getGestion(){
        return gestionarD.getGestion();
    }
    
    int getPropiedad(){
        return gestionarD.getPropiedad();
    }
    
    //Tenemos 20 imagenes del tablero superpuestas, cada una con una casilla distinta iluminada
    //En funcion de en que casilla esté el jugador actual, se pondrá "visible" el tablero (1 de los 20) que tenga
    //dicha casilla iluminada. Luego en el quitarBordes(), pondrá "no visible" ese tablero.
    void actualizarBordes(){
        
        Casilla casillaActual=juego.getCasillaActual();
        
        //Para que esto funcione, cada casilla DEBE tener un nombre distinto (todas las casillas sorpresas no se pueden llamar sorpresa)
        //Por supuesto que encajen mayusculas y minusculas
        switch(casillaActual.getNombre()){
            case "Salida":
                imagenTablero0.setVisible(true);
                break;
                
            case "Bar de Lucho":
                imagenTablero1.setVisible(true);
                break;
             
            case "Chubby-Escuela":
                imagenTablero2.setVisible(true);
                break;
                
            case "Sorpresa1":
                imagenTablero3.setVisible(true);
                break;
            
            case "Ayuntamiento de Jodar":
                imagenTablero4.setVisible(true);
                break;
                
            case "Carcel":
                imagenTablero5.setVisible(true);
                break;
             
            case "Calle Ubuntu":
                imagenTablero6.setVisible(true);
                break;
                
            case "Calle de las Fichas":
                imagenTablero7.setVisible(true);
                break;
            
            case "Impuesto":
                imagenTablero8.setVisible(true);
                break;
                
            case "Calle de los Patos":
                imagenTablero9.setVisible(true);
                break;
             
            case "Parking":
                imagenTablero10.setVisible(true);
                break;
                
            case "Calle del RGB":
                imagenTablero11.setVisible(true);
                break;
            
            case "Sorpresa2":
                imagenTablero12.setVisible(true);
                break;
                
            case "Avenida de España":
                imagenTablero13.setVisible(true);
                break;
             
            case "Calle del Pinchazo":
                imagenTablero14.setVisible(true);
                break;
                
            case "Juez":
                imagenTablero15.setVisible(true);
                break;
                
            case "Calle de Leo":
                imagenTablero16.setVisible(true);
                break;
             
            case "Sorpresa3":
                imagenTablero17.setVisible(true);
                break;
                
            case "Bellas Artes":
                imagenTablero18.setVisible(true);
                break;
                
            case "3º edificio Etsiit":
                imagenTablero19.setVisible(true);
                break; 
        }
        
        repaint();
        revalidate();
    }
    
    //Este metodo se llama 
    public void quitarBordes(){
        
        Casilla casillaActual=juego.getCasillaActual();
        
        //Para que esto funcione, cada casilla DEBE tener un nombre distinto (todas las casillas sorpresas no se pueden llamar sorpresa)
        //Por supuesto que encajen mayusculas y minusculas
        switch(casillaActual.getNombre()){
            case "Salida":
                imagenTablero0.setVisible(false);
                break;
                
            case "Bar de Lucho":
                imagenTablero1.setVisible(false);
                break;
             
            case "Chubby-Escuela":
                imagenTablero2.setVisible(false);
                break;
                
            case "Sorpresa1":
                imagenTablero3.setVisible(false);
                break;
            
            case "Ayto de Jodar":
                imagenTablero4.setVisible(false);
                break;
                
            case "Carcel":
                imagenTablero5.setVisible(false);
                break;
             
            case "Calle Ubuntu":
                imagenTablero6.setVisible(false);
                break;
                
            case "Calle de las Fichas":
                imagenTablero7.setVisible(false);
                break;
            
            case "Impuesto":
                imagenTablero8.setVisible(false);
                break;
                
            case "Calle de los Patos":
                imagenTablero9.setVisible(false);
                break;
             
            case "Parking":
                imagenTablero10.setVisible(false);
                break;
                
            case "Calle del RGB":
                imagenTablero11.setVisible(false);
                break;
            
            case "Sorpresa2":
                imagenTablero12.setVisible(false);
                break;
                
            case "Avenida de España":
                imagenTablero13.setVisible(false);
                break;
             
            case "Calle del Pinchazo":
                imagenTablero14.setVisible(false);
                break;
                
            case "Juez":
                imagenTablero15.setVisible(false);
                break;
                
            case "Calle de Leo":
                imagenTablero16.setVisible(false);
                break;
             
            case "Sorpresa3":
                imagenTablero17.setVisible(false);
                break;
                
            case "Bellas Artes":
                imagenTablero18.setVisible(false);
                break;
                
            case "3º edificio Etsiit":
                imagenTablero19.setVisible(false);
                break; 
        }
        repaint();
        revalidate();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedorVistaJugador = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mostrar_siguienteOperacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jugador1 = new javax.swing.JTextField();
        jugador2 = new javax.swing.JTextField();
        jugador3 = new javax.swing.JTextField();
        jugador4 = new javax.swing.JTextField();
        posicionJugador1 = new javax.swing.JTextField();
        posicionJugador2 = new javax.swing.JTextField();
        posicionJugador3 = new javax.swing.JTextField();
        posicionJugador4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ranking_textArea = new javax.swing.JTextArea();
        ranking_label = new javax.swing.JLabel();
        imagenTablero19 = new javax.swing.JLabel();
        imagenTablero18 = new javax.swing.JLabel();
        imagenTablero17 = new javax.swing.JLabel();
        imagenTablero16 = new javax.swing.JLabel();
        imagenTablero15 = new javax.swing.JLabel();
        imagenTablero14 = new javax.swing.JLabel();
        imagenTablero13 = new javax.swing.JLabel();
        imagenTablero12 = new javax.swing.JLabel();
        imagenTablero11 = new javax.swing.JLabel();
        imagenTablero10 = new javax.swing.JLabel();
        imagenTablero9 = new javax.swing.JLabel();
        imagenTablero8 = new javax.swing.JLabel();
        imagenTablero7 = new javax.swing.JLabel();
        imagenTablero6 = new javax.swing.JLabel();
        imagenTablero5 = new javax.swing.JLabel();
        imagenTablero4 = new javax.swing.JLabel();
        imagenTablero3 = new javax.swing.JLabel();
        imagenTablero2 = new javax.swing.JLabel();
        imagenTablero1 = new javax.swing.JLabel();
        imagenTablero0 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Civitas");
        setPreferredSize(new java.awt.Dimension(1366, 1000));

        jLabel2.setText("Siguiente Operacion: ");

        mostrar_siguienteOperacion.setEditable(false);
        mostrar_siguienteOperacion.setText("jTextField1");
        mostrar_siguienteOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrar_siguienteOperacionActionPerformed(evt);
            }
        });

        jLabel3.setText("Jugadores");

        jLabel4.setText("Posicion(casilla-numero)");

        jugador1.setEditable(false);
        jugador1.setText("jTextField1");

        jugador2.setEditable(false);
        jugador2.setText("jTextField2");

        jugador3.setEditable(false);
        jugador3.setText("jTextField3");

        jugador4.setEditable(false);
        jugador4.setText("jTextField4");

        posicionJugador1.setEditable(false);
        posicionJugador1.setText("jTextField5");

        posicionJugador2.setEditable(false);
        posicionJugador2.setText("jTextField6");

        posicionJugador3.setEditable(false);
        posicionJugador3.setText("jTextField7");

        posicionJugador4.setEditable(false);
        posicionJugador4.setText("jTextField8");

        ranking_textArea.setEditable(false);
        ranking_textArea.setColumns(20);
        ranking_textArea.setRows(5);
        jScrollPane1.setViewportView(ranking_textArea);

        ranking_label.setText("Ranking:");

        imagenTablero19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/18.jpg"))); // NOI18N

        imagenTablero18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/17.jpg"))); // NOI18N

        imagenTablero17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16.jpg"))); // NOI18N

        imagenTablero16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/15.jpg"))); // NOI18N

        imagenTablero15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/14.jpg"))); // NOI18N

        imagenTablero14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/13.jpg"))); // NOI18N

        imagenTablero13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/12.jpg"))); // NOI18N

        imagenTablero12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/11.jpg"))); // NOI18N

        imagenTablero11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/10.jpg"))); // NOI18N

        imagenTablero10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/9.jpg"))); // NOI18N

        imagenTablero9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/8.jpg"))); // NOI18N

        imagenTablero8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/7.jpg"))); // NOI18N

        imagenTablero7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6.jpg"))); // NOI18N

        imagenTablero6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/5.jpg"))); // NOI18N

        imagenTablero5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4.jpg"))); // NOI18N

        imagenTablero4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3.jpg"))); // NOI18N

        imagenTablero3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2.jpg"))); // NOI18N

        imagenTablero2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1.jpg"))); // NOI18N

        imagenTablero1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/0.jpg"))); // NOI18N

        imagenTablero0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/00.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imagenTablero16)
                            .addComponent(imagenTablero17)
                            .addComponent(imagenTablero3)
                            .addComponent(imagenTablero0)
                            .addComponent(imagenTablero11)
                            .addComponent(imagenTablero8)
                            .addComponent(imagenTablero18)
                            .addComponent(imagenTablero7)
                            .addComponent(imagenTablero13)
                            .addComponent(imagenTablero10)
                            .addComponent(imagenTablero9)
                            .addComponent(imagenTablero4)
                            .addComponent(imagenTablero2)
                            .addComponent(imagenTablero15)
                            .addComponent(imagenTablero14)
                            .addComponent(imagenTablero19)
                            .addComponent(imagenTablero6)
                            .addComponent(imagenTablero1)
                            .addComponent(imagenTablero5)
                            .addComponent(imagenTablero12))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ranking_label)
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(posicionJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(posicionJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(posicionJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(posicionJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mostrar_siguienteOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(293, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagenTablero16)
                    .addComponent(imagenTablero17)
                    .addComponent(imagenTablero3)
                    .addComponent(imagenTablero0)
                    .addComponent(imagenTablero11)
                    .addComponent(imagenTablero8)
                    .addComponent(imagenTablero18)
                    .addComponent(imagenTablero7)
                    .addComponent(imagenTablero13)
                    .addComponent(imagenTablero10)
                    .addComponent(imagenTablero9)
                    .addComponent(imagenTablero4)
                    .addComponent(imagenTablero2)
                    .addComponent(imagenTablero15)
                    .addComponent(imagenTablero14)
                    .addComponent(imagenTablero19)
                    .addComponent(imagenTablero6)
                    .addComponent(imagenTablero1)
                    .addComponent(imagenTablero5)
                    .addComponent(imagenTablero12)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(posicionJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(posicionJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(posicionJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(posicionJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ranking_label)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mostrar_siguienteOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrar_siguienteOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrar_siguienteOperacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostrar_siguienteOperacionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedorVistaJugador;
    private javax.swing.JLabel imagenTablero0;
    private javax.swing.JLabel imagenTablero1;
    private javax.swing.JLabel imagenTablero10;
    private javax.swing.JLabel imagenTablero11;
    private javax.swing.JLabel imagenTablero12;
    private javax.swing.JLabel imagenTablero13;
    private javax.swing.JLabel imagenTablero14;
    private javax.swing.JLabel imagenTablero15;
    private javax.swing.JLabel imagenTablero16;
    private javax.swing.JLabel imagenTablero17;
    private javax.swing.JLabel imagenTablero18;
    private javax.swing.JLabel imagenTablero19;
    private javax.swing.JLabel imagenTablero2;
    private javax.swing.JLabel imagenTablero3;
    private javax.swing.JLabel imagenTablero4;
    private javax.swing.JLabel imagenTablero5;
    private javax.swing.JLabel imagenTablero6;
    private javax.swing.JLabel imagenTablero7;
    private javax.swing.JLabel imagenTablero8;
    private javax.swing.JLabel imagenTablero9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jugador1;
    private javax.swing.JTextField jugador2;
    private javax.swing.JTextField jugador3;
    private javax.swing.JTextField jugador4;
    private javax.swing.JTextField mostrar_siguienteOperacion;
    private javax.swing.JTextField posicionJugador1;
    private javax.swing.JTextField posicionJugador2;
    private javax.swing.JTextField posicionJugador3;
    private javax.swing.JTextField posicionJugador4;
    private javax.swing.JLabel ranking_label;
    private javax.swing.JTextArea ranking_textArea;
    // End of variables declaration//GEN-END:variables
}
