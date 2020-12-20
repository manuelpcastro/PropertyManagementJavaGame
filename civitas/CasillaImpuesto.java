package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class CasillaImpuesto extends Casilla {
    
    private float importe;
    
    CasillaImpuesto(float cantidad, String nombre){
        super(nombre);
        this.importe = cantidad;
    }
    
    public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual,todos)){
            this.informar(iactual,todos);
            Jugador jugadorActual = todos.get(iactual);
            jugadorActual.pagaImpuesto(this.importe);
        }
    }
    
    private void informar(int iactual, ArrayList<Jugador> todos){
        String evento = this.informe(iactual, todos);
        String respuesta = "IMPUESTO, con importe " + this.importe;
        
        this.avisarDiario(evento + respuesta);
    }

    @Override
    public String toString() {
        return "CasillaImpuesto{nombre= " + this.getNombre() + ", importe=" + importe + '}';
    }
    
}
