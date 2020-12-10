package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaPorJugador extends Sorpresa{
    
    SorpresaPorJugador(int valor, String texto){
        super(valor,texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual,todos)){
            this.informe(actual,todos);
            SorpresaPago sorpresaTodos = new SorpresaPago(-this.getValor(), "Sorpresa PORJUGADOR");
            SorpresaPago sorpresaUnica = new SorpresaPago(this.getValor()*(todos.size()-1), "Sorpresa PORJUGADOR");
            
            for(int i=0; i<todos.size(); i++){
                if(i == actual)
                    sorpresaUnica.aplicarAJugador(actual, todos);
                else
                    sorpresaTodos.aplicarAJugador(i, todos);
            }
        }
    }
}
