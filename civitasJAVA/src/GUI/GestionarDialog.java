package GUI;

import civitas.Jugador;
import civitas.TituloPropiedad;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class GestionarDialog extends javax.swing.JDialog {
    int propiedadElegida;
    int gestionElegida;
    /**
     * Creates new form GestionarDialog
     */
    public GestionarDialog(java.awt.Frame parent) {
        super(parent,true);
        initComponents();
        this.setLocationRelativeTo(null); //establece el centro de la pantalla como el lugar para colocar la ventana
        propiedadElegida = -1;
        gestionElegida = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gestiones_label = new javax.swing.JLabel();
        propiedades_label = new javax.swing.JLabel();
        titulo_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaGestiones = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_propiedades = new javax.swing.JList<>();
        Realizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        gestiones_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gestiones_label.setText("Gestiones:");

        propiedades_label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        propiedades_label.setText("Propiedades:");

        titulo_label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        titulo_label.setText("Menú de gestiones");

        listaGestiones.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaGestiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaGestionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaGestiones);

        lista_propiedades.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista_propiedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_propiedadesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lista_propiedades);

        Realizar.setText("REALIZAR");
        Realizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gestiones_label, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(propiedades_label, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(titulo_label))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(Realizar)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo_label, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(gestiones_label, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(propiedades_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(Realizar)
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaGestionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaGestionesMouseClicked
        //Simplemente al clickear en una opcion, el atributo cambia
        gestionElegida = listaGestiones.getSelectedIndex();
    }//GEN-LAST:event_listaGestionesMouseClicked

    private void lista_propiedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_propiedadesMouseClicked
        propiedadElegida = lista_propiedades.getSelectedIndex();
    }//GEN-LAST:event_lista_propiedadesMouseClicked

    private void RealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarActionPerformed
        //La funcion del botón realmente es solo cerrar la ventana del menu solo cuando haya escogido
        //algunas de las gestiones (no actualiza los atributos, porq se hace del tiron cuando cliakeas una)
        //No comprueba si escoge una propiedad, porq internamente (con el codigo de anteriores practicas) si ve que 
        //la propiedad no es valida, no hace nada
        if(gestionElegida !=-1){
             this.dispose();
        }
    }//GEN-LAST:event_RealizarActionPerformed

    int getGestion() { 
        return gestionElegida;
    } 
    
    int getPropiedad() { 
        return propiedadElegida;
    }
    
    public void gestionar(Jugador jugador) {
           //Estos digamos "resetea" a -1 de los atributos, es para que una vez clikees sobre una gestion y una propiedad, la proxima vez que se vaya a
           //gestionar, si le da directamente al boton, no mantenga lo clikeado anteriormente y tenga que volver a clikear lo que quiera hacer
           gestionElegida=-1;
           propiedadElegida=-1;
           setGestiones();
           setPropiedades(jugador);
           repaint();
       }
    
       public void setGestiones() {
           //Aqui parece que se crea un listModel auxiliar, para luego en el for de abajo meterle las posibles grstiones a realizar; 
           //y luego con el setModel(gestiones), se lo metes a la lista que tenemos en "Design"
           DefaultListModel gestiones = new DefaultListModel<>(); 
           ArrayList<String> opciones = new ArrayList<>(Arrays.asList(
           "Vender", "Hipotecar", "Cancelar Hipoteca", "Constuir casa", 
           "Construir Hotel", "Terminar"));
           for (String s: opciones){
            gestiones.addElement(s);} //se completan los datos
            
           listaGestiones.setModel(gestiones); //se le dice a la lista cuáles son esos datos
           }
       
       public void setPropiedades(Jugador jugador) {
           DefaultListModel propiedades = new DefaultListModel<>();
           
           for(TituloPropiedad t: jugador.getPropiedades()){
               propiedades.addElement(t.getNombre());
           }

           lista_propiedades.setModel(propiedades);
       }
       
           
       
    /**
     * @param args the command line arguments
     */
       
    //Dice el guion que lo comentemos 
       
       //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
          For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        */
        /* Create and display the dialog */
       /* 
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarDialog dialog = new GestionarDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Realizar;
    private javax.swing.JLabel gestiones_label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaGestiones;
    private javax.swing.JList<String> lista_propiedades;
    private javax.swing.JLabel propiedades_label;
    private javax.swing.JLabel titulo_label;
    // End of variables declaration//GEN-END:variables
}