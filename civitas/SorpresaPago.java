package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaPago extends Sorpresa {
    
    SorpresaPago(int valor, String texto){
        super(valor,texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            jugadorActual.modificarSaldo(this.getValor());
        }
    }
}
