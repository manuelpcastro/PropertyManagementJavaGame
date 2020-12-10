package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class SorpresaSalvoconducto extends Sorpresa{
    
    private MazoSorpresas mazo;
    
    SorpresaSalvoconducto(MazoSorpresas mazo){
        super();
        this.mazo = mazo;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual,todos);
            boolean recibe = true;
            for(Jugador j : todos){
                if(j.tieneSalvoconducto()){
                    recibe = true;
                    break;
                }
            }
            if(recibe){
                Jugador jugadorActual = todos.get(actual);
                jugadorActual.obtenerSalvoConducto(this);
            }
        }
    }
    
    
    void salirDelMazo(){
        this.mazo.inhabilitarCartaEspecial(this);
    }
    
    void usada(){
        this.mazo.habilitarCartaEspecial(this);
    }
}
