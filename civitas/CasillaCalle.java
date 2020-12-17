package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class CasillaCalle extends Casilla {
    
    
    private TituloPropiedad titulo;
    
    CasillaCalle(TituloPropiedad titulo){
        super(titulo.getNombre());
        this.titulo = titulo;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
            Jugador jugador = todos.get(iactual);
            
            this.informar(iactual,todos);
            if(!this.titulo.tienePropietario()){
                jugador.puedeComprarCasilla();
            } else {
                this.titulo.tramitarAlquiler(jugador);
            }
        }
    }
    
    TituloPropiedad getTituloPropiedad(){
        return this.titulo;
    }
    
    private void informar(int iactual, ArrayList<Jugador> todos){
        String evento = this.informe(iactual, todos);
        String respuesta = "CALLE";
        if(titulo!=null){
            if(titulo.tienePropietario())
            respuesta += " con propietario " + titulo.getPropietario().getNombre();
        }  
        this.avisarDiario(evento + respuesta);
    }
}
