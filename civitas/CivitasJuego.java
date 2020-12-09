package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author manuel
 */
public class CivitasJuego {
    
    //MODO DEBUG DADO ACTIVADO EN LINEA 34
    private static int NUM_CASILLAS_SORPRESA=3;
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
        this.tablero = new Tablero(4);
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
       
        Casilla casilla = getCasillaActual();
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
        
        //PAGARCOBRAR
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, 30, "Cobras 30"));   
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, -20, "Pagas 20"));   
        
        //IRCARCEL
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCARCEL, tablero));
        
        //IRCASILLA        
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, this.tablero, this.tablero.numCasillas()-1, "Ve a la ultima casilla"));  
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, this.tablero, this.tablero.numCasillas()-1, "Ve a la ultima casilla"));  
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, this.tablero, 0, "Ve a la casilla inicial"));  
        
        //PORCASAHOTEL
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, 10, "Recibes dinero por casa y hotel"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, -15, "Pagas por casa y hotel"));
          
        //PORJUGADOR
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR, 100, "Recibes de cada jugador"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR, -100, "Pagas a cada jugador"));
        
        //SALIRCARCEL
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.SALIRCARCEL, this.mazo));
        
    }
    
    //Los valores son totalmente arbitrarios, si queremos forzar el final del juego, bastaria con poner 7500 a todos los titulos en el pago de alquiler
    private void inicializarTablero(MazoSorpresas mazo){
        
       ArrayList<Casilla> casillas = new ArrayList<>();
       
       //12 casillas calle
       String[] nombres = {"Barcelona", "Jaen", "Granada", "Cordoba", "Sevilla", "Madrid", "Malaga", "Cadiz", "Huelva", "Almeria", "Valencia", "Bilbao"};
       for(int i=0; i<nombres.length; i++){
        casillas.add(new Casilla(new TituloPropiedad("Calle " + nombres[i],30,30,30,30,30)));
       }
       
       //4 casillas sorpresa
       for(int j=0; j<CivitasJuego.NUM_CASILLAS_SORPRESA; j++){
        casillas.add(new Casilla(mazo, "Casilla Sorpresa"));
       }
       
       //1 casilla impuesto
       casillas.add(new Casilla((float)30.0,"IMPUESTO"));
       
       //1 casilla parking
       casillas.add(new Casilla("PARKING"));
       
       //1 casilla juez
       casillas.add(new Casilla("JUEZ",tablero.getCarcel()));
       
       Collections.shuffle(casillas);
       
       casillas.add(0, new Casilla("SALIDA"));
       
       for(Casilla c : casillas){
           this.tablero.añadeCasilla(c);
       }
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
