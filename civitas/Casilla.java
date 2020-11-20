package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Casilla {
    
    private static int CARCEL;
    private String nombre;
    private float importe;
    
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    private MazoSorpresas mazo;
    
       
    private void init(){
        this.nombre = "Casilla";
        this.tipo = null;
        this.titulo = null;
        this.importe = 0;
        this.mazo = null;
    }
    
    
    protected Casilla(String nombre){
        this.init();
        this.tipo = TipoCasilla.DESCANSO;
        this.nombre = nombre;
    }
    
    
    protected Casilla(TituloPropiedad titulo){
        this.init();
        this.tipo = TipoCasilla.CALLE;
        this.nombre = titulo.getNombre();
        this.titulo = titulo;
    }
    
    
    protected Casilla(float cantidad, String nombre){
        this.init();
        this.tipo = TipoCasilla.IMPUESTO;
        this.importe = cantidad;
        this.nombre = nombre;
    }
    
    
    protected Casilla(int numCasillaCarcel, String nombre){
        this.init();
        this.tipo = TipoCasilla.JUEZ;
        Casilla.CARCEL = numCasillaCarcel;
        this.nombre = nombre;
    }
    
    
    protected Casilla(MazoSorpresas mazo, String nombre){
        this.init();
        this.tipo = TipoCasilla.SORPRESA;
        this.mazo = mazo;
        this.nombre = nombre;
    }
   
    
    public String getNombre(){
        return this.nombre;
    }
    
    
    protected TituloPropiedad getTituloPropiedad(){
        return this.titulo;
    }
   
    
    private void informe(int iactual, ArrayList<Jugador> todos){
        Jugador jugadorActual = todos.get(iactual);
        Diario.getInstance().ocurreEvento("Casilla: el jugador " + jugadorActual.getNombre() + " se encuentra en la casilla " + jugadorActual.getNumCasillaActual() + " de tipo " + this.tipo);
    }
    
    
    public Boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){return iactual >= 0 && iactual < todos.size();}
    
    
    protected void recibeJugador(int iactual, ArrayList<Jugador> todos){
        switch(this.tipo){
            case CALLE: this.recibeJugador_calle(iactual,todos); break;
            case IMPUESTO: this.recibeJugador_impuesto(iactual, todos); break;
            case JUEZ: this.recibeJugador_juez(iactual, todos); break;
            case SORPRESA: this.recibeJugador_sorpresa(iactual, todos); break;
            //default: this.informe(iactual, todos); break; Diagrama P3
            //Como va a ser otro? Tendra que informar siempre
        }
        this.informe(iactual,todos);
    }
    
    
    private void recibeJugador_calle(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)){
           
            Jugador jugador = todos.get(iactual);
            
            if(!this.titulo.tienePropietario()){
                jugador.puedeComprarCasilla();
            } else {
                this.titulo.tramitarAlquiler(jugador);
            }
        }
    }
    
    
    private void recibeJugador_impuesto(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual,todos)){
            
            Jugador jugadorActual = todos.get(iactual);
            jugadorActual.pagaImpuesto(this.importe);
        }
    }
        
        
    private void recibeJugador_juez(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual,todos)){
           
            Jugador jugadorActual = todos.get(iactual);
            jugadorActual.encarcelar(CARCEL);
        }
    }

    
    private void recibeJugador_sorpresa(int iactual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(iactual, todos)) {
            Sorpresa sorpresa = this.mazo.siguiente();
            sorpresa.aplicarAJugador(iactual, todos);
        }
    }

    @Override
    public String toString() {
        String s_importe = importe == 0 ? "" : ", importe= " + importe;
        String s_titulo = titulo == null ? "" : ", titulo=" + titulo;
        String s_mazo = mazo == null ? "":", mazo= " + mazo;
        return "Casilla{" + "nombre=" + nombre + ", tipo= " + tipo + s_importe + s_titulo + s_mazo + '}';
    }

    
    
}
