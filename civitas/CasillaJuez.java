package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class CasillaJuez extends Casilla {
    
    private static int CARCEL;
    
     CasillaJuez(String nombre,int numCasillaCarcel){
        super(nombre);
        CasillaJuez.CARCEL = numCasillaCarcel;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual,todos)){
            this.informar(iactual, todos);
            Jugador jugadorActual = todos.get(iactual);
            jugadorActual.encarcelar(this.CARCEL);
        }
    }
    
    private void informar(int iactual, ArrayList<Jugador> todos){
        String evento = this.informe(iactual, todos);
        String respuesta = "JUEZ, por lo que debe ir a la carcel";
        
        this.avisarDiario(evento + respuesta);
    }

    @Override
    public String toString() {
        return "CasillaJuez{nombre= " + this.getNombre() + '}';
    }
    
    
}
