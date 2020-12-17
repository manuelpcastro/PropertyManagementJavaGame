/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Sorpresa {
    
    private String texto;
    private int valor;
    
    Sorpresa(int valor, String texto){
       this.valor = valor;
       this.texto = texto;
    }
    
    Sorpresa(){
        this.valor = -1;
        this.texto = "";
    }
   
    
    int getValor(){
      return this.valor;
    }
    
    String getTexto(){
        return this.texto;
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
    }
    
    
    protected void informe(int actual, ArrayList<Jugador> todos){
        Jugador jugadorActual = todos.get(actual);
        String mensajeValor="";
     
            mensajeValor = " El valor de la carta es: ";
        Diario.getInstance().ocurreEvento("Sorpresas: se aplica una sorpresa a " + jugadorActual.getNombre() +"." + mensajeValor);
    }
    
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return actual >= 0 && actual < todos.size();
    }

    
    @Override
    public String toString() {
        return this.texto;
    }  
    
}
    
