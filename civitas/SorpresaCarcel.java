package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaCarcel extends Sorpresa{
    
    private Tablero tablero;
    
    SorpresaCarcel(Tablero tablero){
        super();
        this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            jugadorActual.encarcelar(this.tablero.getCarcel());
        }
    }
}
