package GUI;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Jugador;
import civitas.Tablero;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class TableroFrame extends javax.swing.JFrame {
    private Tablero tablero;
    private CivitasJuego juego;

    /**
     * Creates new form TableroFrame
     */
    public TableroFrame() {
        initComponents();
        
        
        repaint();
        revalidate();
    }
    
    void setTablero(Tablero _tablero, CivitasJuego _juego){
        tablero=_tablero;
        juego=_juego;
        this.setVisible(true);
    }
    
    //Esto actualizará todo el frame (la posicion de los jugadores principalmente), y dentro llamaré al actualizarTablero(que cambiará el resaltado de las casillas en funcion 
    //del jugador actual y de que posicion este)
    void actualizarFrame(){
        //Actualizamos el nombre de los jugadores y su posicion
        jugador1.setText(juego.getJugadores().get(0).getNombre());
        jugador2.setText(juego.getJugadores().get(1).getNombre());
        jugador3.setText(juego.getJugadores().get(2).getNombre());
        jugador4.setText(juego.getJugadores().get(3).getNombre());
        
        posicionJugador1.setText(getCasillaActualDeJugador(0).getNombre() + "-" + Integer.toString(juego.getJugadores().get(0).getNumCasillaActual()));
        posicionJugador2.setText(getCasillaActualDeJugador(1).getNombre() + "-" + Integer.toString(juego.getJugadores().get(1).getNumCasillaActual()));
        posicionJugador3.setText(getCasillaActualDeJugador(2).getNombre() + "-" + Integer.toString(juego.getJugadores().get(2).getNumCasillaActual()));
        posicionJugador4.setText(getCasillaActualDeJugador(3).getNombre() + "-" + Integer.toString(juego.getJugadores().get(3).getNumCasillaActual()));
        
        repaint();
        revalidate();

        //Actualizamos el "resalte" de la casilla en la que este el jugador actual
        actualizarTablero();
        
    }
    
    void actualizarTablero(){
        javax.swing.JLabel casilla_aux;
        
        Border lineBorder;
        lineBorder=BorderFactory.createLineBorder(Color.BLACK, 2, true);
        //label.setBorder(lineBorder);
        
        Casilla casillaActual=juego.getCasillaActual();
        
        //casilla_aux=casillaActual.getLabel();
        //casilla_aux.setText("nos vamos");
        //casilla_aux.setBorder(lineBorder);
        
        //Para que esto funcione, cada casilla DEBE tener un nombre distinto (todas las casillas sorpresas no se pueden lamar sorpresa)
        switch(casillaActual.getNombre()){
            case "SALIDA":
                jLabel3.setBorder(lineBorder);
                break;
                
            case "BAR DE LUCHO":
                jLabel4.setBorder(lineBorder);
                break;
             
            case "CHUBBY-ESCUELA":
                jLabel5.setBorder(lineBorder);
                break;
                
            case "SORPRESA1":
                jLabel6.setBorder(lineBorder);
                break;
            
            case "CARCEL":
                jLabel7.setBorder(lineBorder);
                break;
                
            case "CALLE DE LA LOLA":
                jLabel8.setBorder(lineBorder);
                break;
             
            case "CALLE KALASHNIKOV":
                jLabel9.setBorder(lineBorder);
                break;
                
            case "CALLE DE LAS FICHAS":
                jLabel10.setBorder(lineBorder);
                break;
            
            case "IMPUESTO":
                jLabel11.setBorder(lineBorder);
                break;
                
            case "CALLE DE LOS PATOS":
                jLabel12.setBorder(lineBorder);
                break;
             
            case "JUEZ":
                jLabel13.setBorder(lineBorder);
                break;
                
            case "SORPRESA2":
                jLabel14.setBorder(lineBorder);
                break;
            
            case "CALLE DEL RGB":
                jLabel15.setBorder(lineBorder);
                break;
                
            case "AVENIDA DE ESPAÑA":
                jLabel16.setBorder(lineBorder);
                break;
             
            case "CALLE DEL PINCHAZO":
                jLabel17.setBorder(lineBorder);
                break;
                
            case "PARKING":
                jLabel18.setBorder(lineBorder);
                break;
                
            case "CALLE DE LEO":
                jLabel19.setBorder(lineBorder);
                break;
             
            case "SORPRESA3":
                jLabel20.setBorder(lineBorder);
                break;
                
            case "FACULTAD DE BELLAS ARTES":
                jLabel21.setBorder(lineBorder);
                break;
                
            case "3º EDIFICIO DE LA ETSIIT":
                jLabel22.setBorder(lineBorder);
                break;
            
        }
        
        repaint();
        revalidate();
            
    }
    
    public void quitarBorde(){
        Border borde_nulo;
        borde_nulo=BorderFactory.createLineBorder(Color.yellow, 0); //El color da igual, lo que importa es el thickness (como esta a cero, no se ve)
        Casilla casillaActual=juego.getCasillaActual();
        
        switch(casillaActual.getNombre()){
            case "SALIDA":
                jLabel3.setBorder(borde_nulo);
                break;
                
            case "BAR DE LUCHO":
                jLabel4.setBorder(borde_nulo);
                break;
             
            case "CHUBBY-ESCUELA":
                jLabel5.setBorder(borde_nulo);
                break;
                
            case "SORPRESA1":
                jLabel6.setBorder(borde_nulo);
                break;
            
            case "CARCEL":
                jLabel7.setBorder(borde_nulo);
                break;
                
            case "CALLE DE LA LOLA":
                jLabel8.setBorder(borde_nulo);
                break;
             
            case "CALLE KALASHNIKOV":
                jLabel9.setBorder(borde_nulo);
                break;
                
            case "CALLE DE LAS FICHAS":
                jLabel10.setBorder(borde_nulo);
                break;
            
            case "IMPUESTO":
                jLabel11.setBorder(borde_nulo);
                break;
                
            case "CALLE DE LOS PATOS":
                jLabel12.setBorder(borde_nulo);
                break;
             
            case "JUEZ":
                jLabel13.setBorder(borde_nulo);
                break;
                
            case "SORPRESA2":
                jLabel14.setBorder(borde_nulo);
                break;
            
            case "CALLE DEL RGB":
                jLabel15.setBorder(borde_nulo);
                break;
                
            case "AVENIDA DE ESPAÑA":
                jLabel16.setBorder(borde_nulo);
                break;
             
            case "CALLE DEL PINCHAZO":
                jLabel17.setBorder(borde_nulo);
                break;
                
            case "PARKING":
                jLabel18.setBorder(borde_nulo);
                break;
                
            case "CALLE DE LEO":
                jLabel19.setBorder(borde_nulo);
                break;
             
            case "SORPRESA3":
                jLabel20.setBorder(borde_nulo);
                break;
                
            case "FACULTAD DE BELLAS ARTES":
                jLabel21.setBorder(borde_nulo);
                break;
                
            case "3º EDIFICIO DE LA ETSIIT":
                jLabel22.setBorder(borde_nulo);
                break;
        }
    }
    
    Casilla getCasillaActualDeJugador(int num_jugador){
        return tablero.getCasilla(juego.getJugadores().get(num_jugador).getNumCasillaActual());
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jugador1 = new javax.swing.JTextField();
        jugador2 = new javax.swing.JTextField();
        jugador3 = new javax.swing.JTextField();
        jugador4 = new javax.swing.JTextField();
        posicionJugador1 = new javax.swing.JTextField();
        posicionJugador2 = new javax.swing.JTextField();
        posicionJugador3 = new javax.swing.JTextField();
        posicionJugador4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tablero");

        jLabel1.setText("Jugadores");

        jLabel2.setText("Posicion(nombre-casilla)");

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/57e6680d-dbe2-4d1d-9fb0-d9cd5cd231f9.png"))); // NOI18N
        jLabel3.setText("Importe: 120");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setIconTextGap(10);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        jLabel10.setText("jLabel10");

        jLabel11.setText("jLabel11");

        jLabel12.setText("jLabel12");

        jLabel13.setText("jLabel13");

        jLabel14.setText("jLabel14");

        jLabel15.setText("jLabel15");

        jLabel16.setText("jLabel16");

        jLabel17.setText("jLabel17");

        jLabel18.setText("jLabel18");

        jLabel19.setText("jLabel19");

        jLabel20.setText("jLabel20");

        jLabel21.setText("jLabel21");

        jLabel22.setText("jLabel22");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(383, 383, 383)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(posicionJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(posicionJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(posicionJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(posicionJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(440, 440, 440)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel22)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel14)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLabel15))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel6)
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel7)))
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel16))
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(35, 35, 35)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel19)))))))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel20))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(posicionJugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(posicionJugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(posicionJugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(posicionJugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(50, 50, 50)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jLabel3.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TableroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableroFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableroFrame().setVisible(true);
            }
        });
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jugador1;
    private javax.swing.JTextField jugador2;
    private javax.swing.JTextField jugador3;
    private javax.swing.JTextField jugador4;
    private javax.swing.JTextField posicionJugador1;
    private javax.swing.JTextField posicionJugador2;
    private javax.swing.JTextField posicionJugador3;
    private javax.swing.JTextField posicionJugador4;
    // End of variables declaration//GEN-END:variables
}
