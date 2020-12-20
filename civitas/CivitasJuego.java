package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author manuel
 */
public class CivitasJuego {
    
    private static int CARCEL = 4;
    //MODO DEBUG DADO ACTIVADO EN LINEA 34
    private static Boolean debug=true;
    private int indiceJugadorActual;
    ArrayList<Jugador> jugadores;
    
    Tablero tablero;
    MazoSorpresas mazo;
    
    GestorEstados gestor;
    EstadosJuego estado;
    
    public CivitasJuego(ArrayList<String> nombres){
        this.jugadores = new ArrayList<>();
        for (String n : nombres){
            this.jugadores.add(new Jugador(n));
        }
        
        this.gestor = new GestorEstados();
        this.estado = this.gestor.estadoInicial();
        
        this.indiceJugadorActual = Dado.getInstance().quienEmpieza(this.jugadores.size());
        
        Dado.getInstance().setDebug(CivitasJuego.debug);
        
        this.mazo = new MazoSorpresas();
        this.tablero = new Tablero(CARCEL);
        //Dejar claro como se elige el num_casilla de la carcel
        this.inicializarTablero(this.mazo);
        
        this.inicializarMazoSopresas(this.tablero);

    }
    
    private void avanzaJugador(){
        Jugador jugadorActual = this.getJugadorActual();
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = Dado.getInstance().tirar();
        
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva);
        
        this.contabilizarPasosPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(this.indiceJugadorActual, this.jugadores);
        this.contabilizarPasosPorSalida(jugadorActual);
    }
    
    public boolean cancelarHipoteca(int ip){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.cancelarHipoteca(ip);
    }
    
    public boolean comprar(){ 
        Jugador jugadorActual = this.getJugadorActual();
       
        CasillaCalle casilla = (CasillaCalle) getCasillaActual();
        TituloPropiedad titulo = casilla.getTituloPropiedad();
    
        return jugadorActual.comprar(titulo);
    }
    
    public boolean construirCasa(int ip){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        while(this.tablero.getPorSalida() > 0)
            jugadorActual.pasaPorSalida();
    }
    
    public boolean finalDelJuego(){
        for(Jugador jugador : this.jugadores){
            if(jugador.enBancarrota())
                return true;
        }
        return false;
    }
    
    public Casilla getCasillaActual(){
        Jugador jugadorActual = this.getJugadorActual();
        int numCasillaActual = jugadorActual.getNumCasillaActual();
        return this.tablero.getCasilla(numCasillaActual);
    }
    
    public Jugador getJugadorActual(){return this.jugadores.get(this.indiceJugadorActual);}
    
    public boolean hipotecar(int ip){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.hipotecar(ip);
    }
    
   
    public String infoJugadorTexto(){
      String info ="\n\033[31m --JUGADOR ACTUAL: \n";
      info += "     " + this.getJugadorActual().toString();
      info +="\n\033[31m ---CASILLA ACTUAL: \n";
      info += "     " + this.getCasillaActual().toString() + "\n"; 
      return info;
    }
    
    private void inicializarMazoSopresas(Tablero tablero){
        this.mazo.inicializar(tablero);
    }
    
    //Los valores son totalmente arbitrarios, si queremos forzar el final del juego, bastaria con poner 7500 a todos los titulos en el pago de alquiler
    private void inicializarTablero(MazoSorpresas mazo){
        this.tablero.inicializar(mazo);
    }
    
    private void pasarTurno(){
        this.indiceJugadorActual= (this.indiceJugadorActual+1)%this.jugadores.size();
    }
    
    private ArrayList<Jugador> ranking(){
        Collections.sort(this.jugadores);
        Collections.reverse(this.jugadores);
        return this.jugadores;
    }
    
    public boolean salirCarcelPagando(){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando(){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.salirCarcelTirando();
    }
    
    public OperacionesJuego siguientePaso(){
        Jugador jugadorActual = this.getJugadorActual();
        OperacionesJuego operacion = this.gestor.operacionesPermitidas(jugadorActual,this.estado);
        
        switch(operacion){
            case PASAR_TURNO:
              this.pasarTurno();
              this.siguientePasoCompletado(operacion);
            break;
            case AVANZAR:
              this.avanzaJugador();
              this.siguientePasoCompletado(operacion);
            break;
        }

        return operacion;
    }
    
    public void siguientePasoCompletado(OperacionesJuego operacion){
        Jugador jugadorActual = this.getJugadorActual();
      
        this.estado = this.gestor.siguienteEstado(jugadorActual, this.estado, operacion);
    }
    
    public boolean vender(int ip){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.vender(ip);
    }
    
    //Devuelve los nombres de los titulos
    public ArrayList<String> getPropiedadesJugadorActual(){
        return this.getJugadorActual().getPropiedadesNombre();
    }
    
    //Solo le devolvemos un String
    public String infoPropiedades(){
       return this.getJugadorActual().infoPropiedades();
    }
    
    //No debemos devolver el array de jugadores tal cual a la vista
    public ArrayList<String> infoRanking(){
        ArrayList<String> info = new ArrayList<>();
        for(Jugador j : this.ranking()){
            info.add("Jugador: " + j.getNombre() + " - Saldo: " + j.getSaldo());
        }
        return info;
    }
}
