package GUI;

import civitas.Jugador;
import civitas.JugadorEspeculador;
import civitas.TituloPropiedad;
import java.util.ArrayList;

/**
 * @author Jesús Navarro Merino
 * @author José Alberto Gómez García
 */

public class JugadorPanel extends javax.swing.JPanel {
    private Jugador jugador;
    
    /**
     * Creates new form JugadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }
    
    public void setJugador(Jugador jug) {
        jugador = jug;
        
        String encarcelado;
        if(jugador.isEncarcelado())
            encarcelado = "Si";
        else
            encarcelado = "No";
        
        String saldo = Double.toString(jugador.getSaldo());
        String especulador;
        if (!(jugador instanceof JugadorEspeculador)) 
            especulador = "No";
        else
            especulador = "Si";
                  
        
        nombre_mostrar.setText(jugador.getNombre());
        saldo_mostrar.setText(saldo);
        encarcelado_mostrar.setText(encarcelado);
        especulador_mostrar.setText(especulador);
        
        repaint();
        rellenaPropiedades(jugador.getPropiedades());
        
    }
    
    
    private void rellenaPropiedades(ArrayList<TituloPropiedad> lista){
        // Se elimina la información antigua
        propiedades.removeAll();
        // Se recorre la lista de propiedades para ir creando sus vistas individuales y añadirlas al panel
        for (TituloPropiedad t : lista) {
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);

            propiedades.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
        }
        // Se fuerza la actualización visual del panel propiedades y del panel del jugador
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombre_mostrar = new javax.swing.JTextField();
        saldo_mostrar = new javax.swing.JTextField();
        encarcelado_mostrar = new javax.swing.JTextField();
        especulador_mostrar = new javax.swing.JTextField();
        propiedades = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Jugador:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, -1));

        jLabel2.setText("Saldo:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 40, 10));

        jLabel3.setText("Encarcelado:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, 10));

        jLabel4.setText("Especulador:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, -1, 10));

        nombre_mostrar.setEditable(false);
        nombre_mostrar.setText("jTextField2");
        nombre_mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_mostrarActionPerformed(evt);
            }
        });
        add(nombre_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        saldo_mostrar.setEditable(false);
        saldo_mostrar.setText("jTextField3");
        add(saldo_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        encarcelado_mostrar.setEditable(false);
        encarcelado_mostrar.setText("jTextField4");
        add(encarcelado_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        especulador_mostrar.setEditable(false);
        especulador_mostrar.setText("jTextField1");
        add(especulador_mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));
        add(propiedades, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void nombre_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_mostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_mostrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField encarcelado_mostrar;
    private javax.swing.JTextField especulador_mostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombre_mostrar;
    private javax.swing.JPanel propiedades;
    private javax.swing.JTextField saldo_mostrar;
    // End of variables declaration//GEN-END:variables
}
