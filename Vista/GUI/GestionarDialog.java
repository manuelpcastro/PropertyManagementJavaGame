package Vista.GUI;

import Controlador.GestionesInmobiliarias;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author manuel
 */
public class GestionarDialog extends javax.swing.JDialog {

    private int gestionElegida;
    private int propiedadElegida;

    
    int getGestion(){
        return this.gestionElegida;
    }
    
    int getPropiedad(){
        return this.propiedadElegida;
    }
    
    public void gestionar(ArrayList<String> titulos){
        
        if(titulos.isEmpty()){
            dispose();
        }
        
        gestionElegida = -1;
        propiedadElegida = -1;
        
        setGestiones();   
        setPropiedades(titulos);
        
        pack();
        repaint();
        revalidate();
        setVisible(true);
    }
    
    private void setGestiones(){
        DefaultListModel gestiones = new DefaultListModel<>();
        gestiones.addElement(GestionesInmobiliarias.VENDER.toString());
        gestiones.addElement(GestionesInmobiliarias.HIPOTECAR.toString());
        gestiones.addElement(GestionesInmobiliarias.CANCELAR_HIPOTECA.toString());
        gestiones.addElement(GestionesInmobiliarias.CONSTRUIR_CASA.toString());
        gestiones.addElement(GestionesInmobiliarias.CONSTRUIR_HOTEL.toString());
        gestiones.addElement(GestionesInmobiliarias.TERMINAR.toString());
        
        this.gestionesLista.setModel(gestiones);
    }
    
    private void setPropiedades(ArrayList<String> titulos){
        DefaultListModel propiedades = new DefaultListModel<>();
        for (String propiedad : titulos)
            propiedades.addElement(propiedad);
        
        this.propiedadesLista.setModel(propiedades);
    }
    
    
    /**
     * Creates new form GestionarDialog
     */
    public GestionarDialog(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        propiedadesTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gestionesLista = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        propiedadesLista = new javax.swing.JList<>();
        gestionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        title.setText("GESTION PROPIEDADES");

        propiedadesTitle.setText("Propiedades");

        gestionesLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        gestionesLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gestionesListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gestionesLista);

        jLabel1.setText("Gestiones");

        propiedadesLista.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        propiedadesLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                propiedadesListaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(propiedadesLista);

        gestionButton.setText("REALIZAR");
        gestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(propiedadesTitle)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(265, 265, 265))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(gestionButton)
                        .addGap(253, 253, 253))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(60, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(46, 46, 46)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(title)
                .addGap(39, 39, 39)
                .addComponent(propiedadesTitle)
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(gestionButton)
                .addGap(35, 35, 35))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(305, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gestionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionButtonActionPerformed
        propiedadElegida = propiedadesLista.getSelectedIndex();
        gestionElegida = gestionesLista.getSelectedIndex();
        
        if(propiedadElegida != -1 && gestionElegida != -1)
            dispose();
    }//GEN-LAST:event_gestionButtonActionPerformed

    private void propiedadesListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propiedadesListaMouseClicked
        propiedadElegida = propiedadesLista.getSelectedIndex();
    }//GEN-LAST:event_propiedadesListaMouseClicked

    private void gestionesListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gestionesListaMouseClicked
        gestionElegida = gestionesLista.getSelectedIndex();
    }//GEN-LAST:event_gestionesListaMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GestionarDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                GestionarDialog dialog = new GestionarDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gestionButton;
    private javax.swing.JList<String> gestionesLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> propiedadesLista;
    private javax.swing.JLabel propiedadesTitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
