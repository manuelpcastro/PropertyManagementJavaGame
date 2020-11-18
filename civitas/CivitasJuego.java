package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author manuel
 */
public class CivitasJuego {
    
    
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
       
        //Dejar claro como se elige el num_casilla de la carcel
        this.inicializarTablero(new Tablero(4));
        
        this.inicializarMazoSopresas(new MazoSorpresas());

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
    
    public String infoJugadorTexto(){return "";}
    
    private void inicializarMazoSopresas(MazoSorpresas mazo){
        this.mazo = mazo;
        
        //PAGARCOBRAR
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, 30, "Cobras 30"));   
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, -20, "Pagas 20"));   
        
        //IRCASILLA
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, this.tablero.getCarcel(), "Te diriges a la cárcel"));  
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, this.tablero.numCasillas()-1, "Ve a la ultima casilla"));  
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, 0, "Ve a la casilla inicial"));  
        
        //PORCASAHOTEL
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, 10, "Recibes dinero por casa y hotel"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, -15, "Pagas por casa y hotel"));
          
        //PORJUGADOR
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR, 100, "Recibes de cada jugador"));
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR, -100, "Pagas a cada jugador"));
        
        //SALIRCARCEL
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.SALIRCARCEL, this.tablero));
        
        //IRCARCEL
        this.mazo.alMazo(new Sorpresa(TipoSorpresa.IRCARCEL, this.tablero));
    }
    
    private void inicializarTablero(Tablero tablero){
       this.tablero = tablero;
       this.tablero.añadeCasilla(new Casilla("SALIDA"));
       this.tablero.añadeCasilla(new Casilla((float)30.0,"IMPUESTO"));
       this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Barcelona",30,30,30,30,30)));
       this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Jaen",30,30,30,30,30)));
       this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Granada",30,30,30,30,30)));
              this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Cordoba",30,30,30,30,30)));
                     this.tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Sevilla",30,30,30,30,30)));
    }
    
    private void pasarTurno(){
        this.indiceJugadorActual= (this.indiceJugadorActual+1)%this.jugadores.size();
    }
    
    private ArrayList<Jugador> ranking(){
        Collections.sort(this.jugadores);
        return this.jugadores;
    }
    
    public boolean salirCarcelPagando(){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.salirCarcelPagando();
    }
    
    public boolean salirCarcelTirando(){
        Jugador jugadorActual = this.getJugadorActual();
        return jugadorActual.salirCarcelPagando();
    }
    
    public OperacionesJuego siguientePaso(){
        Jugador jugadorActual = this.getJugadorActual();
        OperacionesJuego operacion = this.gestor.operacionesPermitidas(jugadorActual,this.estado);
        
        if(operacion == OperacionesJuego.PASAR_TURNO)
            this.pasarTurno();

        if(operacion == OperacionesJuego.AVANZAR)
            this.avanzaJugador();

        this.siguientePasoCompletado(operacion);
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
    
    //
    public ArrayList<String> getPropiedadesJugadorActual(){
        ArrayList<String> titulos = new ArrayList<>();
        for(TituloPropiedad p : this.getJugadorActual().propiedades){
           titulos.add(p.getNombre());
        }
        return titulos;
    }
    
    //
    public String infoPropiedades(){
        String titulos = "Propiedades: \n";
        for(TituloPropiedad p : this.getJugadorActual().propiedades){
           titulos+=p.toString()+"\n";
        }
        return titulos;      
    }
}
