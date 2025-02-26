package Vista.GUI;

import Vista.Respuestas;
import Vista.VistaCivitas;
import civitas.CivitasJuego;
import civitas.OperacionesJuego;
import Vista.SalidasCarcel;
import javax.swing.JOptionPane;

/**
 *
 * @author manuel
 */
public class VistaGrafica extends javax.swing.JFrame implements VistaCivitas {

    private CivitasJuego juego;
    private JugadorPanel jugadorPanel;
    private CasillaPanel casillaPanel;
    private GestionarDialog gestionarD;
    private DiarioDialog diarioD;
    
    /**
     * Creates new form CivitasView
     */
    public VistaGrafica() {
        initComponents();
        
        this.rankingArea.setVisible(false);
        this.rankingLabel.setVisible(false);
        this.jScrollPane1.setVisible(false);
        
        this.jugadorPanel = new JugadorPanel();
        this.contenedorVistaJugador.add(jugadorPanel);
        
        this.casillaPanel = new CasillaPanel();
        this.contenedorCasilla.add(casillaPanel);
        
        pack();
        repaint();
        revalidate();
        this.setLocationRelativeTo(null);
    }
    
    @Override
    public void setCivitasJuego(CivitasJuego j){
        this.juego = j;
        setVisible(true);
    }
    
    @Override
    public void actualizarVista(){
        this.jugadorPanel.setJugador(this.juego.getJugadorActual());
        this.casillaPanel.setCasilla(juego.getCasillaActual());
        
        pack();
        repaint();
        revalidate();
    }
    
    @Override
    public void mostrarSiguienteOperacion(OperacionesJuego operacion){
        this.operacion.setText(operacion.toString());
        this.actualizarVista();
    }

    @Override
    public void mostrarEventos(){
        diarioD = new DiarioDialog(this);
        diarioD.mostrarEventos();
    }
    
    @Override
    public Respuestas comprar(){
        int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres comprar la casilla actual?", "Compra", JOptionPane.YES_NO_OPTION);
        Respuestas result = opcion == 0 ? Respuestas.SI : Respuestas.NO;
        return result;
    }

    @Override
    public SalidasCarcel salirCarcel() {
        String[] opciones = {"PAGANDO", "TIRANDO"};
        int opcion = JOptionPane.showOptionDialog(null, "Como quieres salir de la carcel", "Salir de la carcel", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    
        SalidasCarcel result = opcion == 0 ? SalidasCarcel.PAGANDO : SalidasCarcel.TIRANDO;
        
        return result;
    }

    @Override
    public void gestionar() {       
       gestionarD = new GestionarDialog(this);
       gestionarD.gestionar(this.juego.getPropiedadesJugadorActual());    
    }

    @Override
    public int getGestion() {
        return this.gestionarD.getGestion();
    }

    @Override
    public int getPropiedad() {
        return this.gestionarD.getPropiedad();
    }

    @Override
    public void ranking() {
        
        String infoRanking = "";
        for(String s : juego.infoRanking()){
            infoRanking += "\n\n" + s;
        }
        
        this.rankingArea.setText(infoRanking);
        
        this.rankingLabel.setVisible(true);
        this.jScrollPane1.setVisible(true);
        this.rankingArea.setVisible(true);
        
       
        this.rankingArea.repaint();
        this.rankingArea.revalidate();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        contenedorVistaJugador = new javax.swing.JPanel();
        contenedorCasilla = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        operacion = new javax.swing.JLabel();
        rankingLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rankingArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Titulo.setText("CIVITAS");

        jLabel1.setText("SIGUIENTE OPERACION:");
        jLabel1.setEnabled(false);

        operacion.setText("operacion");

        rankingLabel.setText("RANKING");

        rankingArea.setColumns(20);
        rankingArea.setRows(5);
        jScrollPane1.setViewportView(rankingArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(Titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(269, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(operacion)
                        .addGap(83, 83, 83))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contenedorCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(rankingLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(operacion))
                .addGap(39, 39, 39)
                .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                .addComponent(contenedorCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(rankingLabel)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

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
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel contenedorCasilla;
    private javax.swing.JPanel contenedorVistaJugador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel operacion;
    private javax.swing.JTextArea rankingArea;
    private javax.swing.JLabel rankingLabel;
    // End of variables declaration//GEN-END:variables

    
}
