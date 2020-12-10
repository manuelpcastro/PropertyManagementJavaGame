package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class CasillaSorpresa extends Casilla {
    
    private MazoSorpresas mazo;
    
    CasillaSorpresa(MazoSorpresas mazo, String nombre){
        super(nombre);
        this.mazo = mazo;
    }
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)) {
            this.informar(iactual, todos);
            Sorpresa sorpresa = this.mazo.siguiente();
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }
    
    private void informar(int iactual, ArrayList<Jugador> todos){
        String evento = this.informe(iactual, todos);
        String respuesta = "SORPRESA, asi que debe coger una carta";
        
        this.avisarDiario(evento + respuesta);
    }
}
