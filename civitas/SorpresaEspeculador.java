package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaEspeculador extends Sorpresa {
    
    SorpresaEspeculador(int valor){
        super(valor,"Te transformas en un especulador!!");
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            JugadorEspeculador especulador = new JugadorEspeculador(jugadorActual, this.getValor());
            todos.remove(jugadorActual);
            todos.add(actual, especulador);
        }
    }
}
