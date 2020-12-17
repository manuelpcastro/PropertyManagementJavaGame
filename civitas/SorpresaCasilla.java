package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaCasilla extends Sorpresa{
    
    private Tablero tablero;
    
    SorpresaCasilla(Tablero tablero, int valor, String texto){
       super(valor,texto);
       this.tablero = tablero;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            int numCasillaActual = jugadorActual.getNumCasillaActual();
            this.tablero.calcularTirada(numCasillaActual, this.getValor());
            jugadorActual.moverACasilla(this.getValor());
            this.tablero.getCasilla(this.getValor()).recibeJugador(actual, todos);
        }
    }
}
