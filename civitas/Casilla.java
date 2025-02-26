package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Casilla {
    
    private String nombre;   
    private int numero;
    
    Casilla(String nombre){
        this.nombre = nombre;
    }
    
    //Simplemente información adicional para el panel
    void setNumero(int numero){
        this.numero = numero;
    }
    
    public int getNumero(){
        return numero;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    
    String informe(int iactual, ArrayList<Jugador> todos){
        Jugador jugadorActual = todos.get(iactual);
        return "Casilla: el jugador " + jugadorActual.getNombre() + " se encuentra en la casilla " + jugadorActual.getNumCasillaActual() + " de tipo ";
    }
    
    void avisarDiario(String evento){
        Diario.getInstance().ocurreEvento(evento);
    }

    
    
    public Boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){return iactual >= 0 && iactual < todos.size();}
    
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){}

    @Override
    public String toString(){
        return "Casilla{nombre= " + nombre + "}";
    }  
}
