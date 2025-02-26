package Vista.GUI;

import civitas.Jugador;
import civitas.TituloPropiedad;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class JugadorPanel extends javax.swing.JPanel {

    private Jugador jugadorActual;
    /**
     * Creates new form JugadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }
    
    public void setJugador(Jugador j){
        this.jugadorActual = j;
        this.nombreJugador.setText(this.jugadorActual.getNombre());
        this.saldoJugador.setText(Float.toString(this.jugadorActual.getSaldo()));
        this.encarceladoJugador.setText(Boolean.toString(this.jugadorActual.isEncarcelado()));
        this.rellenaPropiedades(jugadorActual.getPropiedades());
        this.repaint();
        this.setVisible(true);
    }

    private void rellenaPropiedades(ArrayList<TituloPropiedad> lista){
        this.propiedades.removeAll();
        for(TituloPropiedad t:lista){
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);
            
            propiedades.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
        }
        
        this.repaint();
        this.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Jugador = new javax.swing.JLabel();
        nombreJugador = new javax.swing.JLabel();
        Saldo = new javax.swing.JLabel();
        saldoJugador = new javax.swing.JLabel();
        Encarcelado = new javax.swing.JLabel();
        encarceladoJugador = new javax.swing.JLabel();
        propiedades = new javax.swing.JPanel();

        Jugador.setText("JUGADOR");
        Jugador.setEnabled(false);

        nombreJugador.setText("Nombre");

        Saldo.setText("SALDO");
        Saldo.setEnabled(false);

        saldoJugador.setText("0");

        Encarcelado.setText("ENCARCELADO");
        Encarcelado.setEnabled(false);

        encarceladoJugador.setText("false");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Encarcelado)
                                    .addComponent(Saldo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saldoJugador, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(encarceladoJugador, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(propiedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(Jugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreJugador)))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jugador)
                    .addComponent(nombreJugador))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Saldo)
                    .addComponent(saldoJugador))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Encarcelado)
                    .addComponent(encarceladoJugador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(propiedades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Encarcelado;
    private javax.swing.JLabel Jugador;
    private javax.swing.JLabel Saldo;
    private javax.swing.JLabel encarceladoJugador;
    private javax.swing.JLabel nombreJugador;
    private javax.swing.JPanel propiedades;
    private javax.swing.JLabel saldoJugador;
    // End of variables declaration//GEN-END:variables
}
