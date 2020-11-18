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
    
    private TipoSorpresa tipo;
    private MazoSorpresas mazo;
    private Tablero tablero;
    
    private void init() {
        this.valor = -1; //Este valor es muy malo para ser diferenciador, y si pagas -1?
        this.mazo = null;
        this.tablero = null;
    }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero){
        this.init();
        this.tipo = tipo;
        this.tablero = tablero;
    }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
       this.init();
       this.tipo = tipo;
       this.tablero = tablero;
       this.valor = valor;
       this.texto = texto;
    }
    
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        this.init();
        this.tipo = tipo;
        this.valor = valor;
        this.texto = texto;
    }
    
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
        this.init();
        this.tipo = tipo;
        this.mazo = mazo;
    }
    
    
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        switch(this.tipo){
            case IRCASILLA: this.aplicarAJugador_irACasilla(actual, todos); break;
            case IRCARCEL: this.aplicarAJugador_irCarcel(actual, todos); break;
            case PAGARCOBRAR: this.aplicarAJugador_pagarCobrar(actual, todos); break;
            case PORCASAHOTEL: this.aplicarAJugador_porCasaHotel(actual, todos); break;
            case PORJUGADOR: this.aplicarAJugador_porJugador(actual, todos); break;
            case SALIRCARCEL: this.aplicarAJugador_salirCarcel(actual, todos); break;
        }
    }
    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            int numCasillaActual = jugadorActual.getNumCasillaActual();
            this.tablero.calcularTirada(numCasillaActual, this.valor);
            jugadorActual.moverACasilla(this.valor);
            this.tablero.getCasilla(this.valor).recibeJugador(actual, todos);
        }
    }
    
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            jugadorActual.encarcelar(this.tablero.getCarcel());
        }
    }
    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            Jugador jugadorActual = todos.get(actual);
            jugadorActual.modificarSaldo(this.valor);
        }
    }
    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual,todos)){
            this.informe(actual,todos);
            Jugador jugadorActual = todos.get(actual);
            jugadorActual.modificarSaldo(jugadorActual.cantidadCasasHoteles()*this.valor);
        }
    }

    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos) {
        if(this.jugadorCorrecto(actual,todos)){
            this.informe(actual,todos);
            Sorpresa sorpresaTodos = new Sorpresa(TipoSorpresa.PAGARCOBRAR, -this.valor, "Sorpresa PORJUGADOR");
            Sorpresa sorpresaUnica = new Sorpresa(TipoSorpresa.PAGARCOBRAR, this.valor*(todos.size()-1), "Sorpresa PORJUGADOR");
            
            for(int i=0; i<todos.size(); i++){
                if(i == actual)
                    sorpresaUnica.aplicarAJugador(actual, todos);
                else
                    sorpresaTodos.aplicarAJugador(i, todos);
            }
        }
    }
    
    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos) {
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
    
    private void informe(int actual, ArrayList<Jugador> todos){
        Jugador jugadorActual = todos.get(actual);
        String mensajeValor="";
        if(this.valor != -1)
            mensajeValor = " El valor de la carta es: " + this.valor;
        Diario.getInstance().ocurreEvento("Sorpresas: se aplica una sorpresa de tipo " + this.tipo + " a " + jugadorActual.nombre +"." + mensajeValor);
    }
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return actual >= 0 && actual < todos.size();
    }
    
    void salirDelMazo(){
        if(this.tipo == TipoSorpresa.SALIRCARCEL){
            this.mazo.inhabilitarCartaEspecial(this);
        }
    }
    
    void usada(){
        if(this.tipo == TipoSorpresa.SALIRCARCEL)
            this.mazo.habilitarCartaEspecial(this);
    }

    @Override
    public String toString() {
        return this.texto;
    }  
    
}
    
