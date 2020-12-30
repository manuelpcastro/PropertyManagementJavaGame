package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaExamen extends SorpresaCasilla {
    
    private int pos;
    private Casilla casilla;
    private Tablero tablero;
    
    SorpresaExamen(int pos, Tablero tablero){
        super(tablero, pos, "SOY SORPRESA EXAMEN");
        this.pos = pos;
        
        if(pos < 1){
            this.pos = 1;
        }
        
        if(pos > 20){
            this.pos = 20;
        }
        this.setValor(pos);
        this.tablero = tablero;
        this.casilla = tablero.getCasilla(pos);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        Jugador jugadorActual = todos.get(actual);
        if(jugadorActual.getNumCasillaActual() < pos){
            super.aplicarAJugador(actual, todos);      
            //Metodos consultores
            this.casilla.getNombre();
            this.tablero.getCarcel();
        }
    }
    
    @Override
    public String toString(){
       return "Aplicando sorpresa examen. Tu posicion es menor a " + this.pos + ", por lo que se te mueve hasta alli";
    }
}
