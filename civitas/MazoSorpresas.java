package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author manuel
 */
public class MazoSorpresas {
    
    private ArrayList<Sorpresa> sorpresas;
    private Boolean barajada;
    private int usadas;
    private Boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    private Diario diario;
    
    
    private void init(){
        this.sorpresas = new ArrayList<>();
        this.cartasEspeciales = new ArrayList<>();
        this.barajada = false;
        this.usadas = 0;
        
        this.diario = Diario.getInstance();
    }
    
    MazoSorpresas(){
        init();
        this.debug = false;
    }
    
    MazoSorpresas(Boolean modoDebug){
        init();
        this.debug = modoDebug;
        if(modoDebug) diario.ocurreEvento("MazoSorpresas: modo debug activado");
    }
    
    
    void alMazo(Sorpresa s){
        if(!this.barajada){
            this.sorpresas.add(s);
        }
    }
    
    
    Sorpresa siguiente(){
        if(!this.barajada && !debug && this.usadas == this.sorpresas.size()){
            this.usadas = 0;
            this.barajada = true;
            
            Collections.shuffle(this.sorpresas);
        }
        
        Sorpresa siguiente = this.sorpresas.get(0);
        this.usadas++;
        this.sorpresas.remove(0);
        this.sorpresas.add(siguiente);
            
        return siguiente;
    }
    
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        diario.ocurreEvento("MazoSorpresas: carta especial inhabilitada");
        this.sorpresas.remove(sorpresa); 
        this.cartasEspeciales.add(sorpresa);
    }
    
    
    void habilitarCartaEspecial(Sorpresa sorpresa){
         diario.ocurreEvento("MazoSorpresas: carta especial habilitada");
         this.cartasEspeciales.remove(sorpresa); 
         this.sorpresas.add(sorpresa);
    }

    void inicializar(Tablero tablero) {
        //CONVERTIRESPECULADOR
        this.alMazo(new SorpresaEspeculador(250));
        
        //PAGARCOBRAR
        this.alMazo(new SorpresaPago(30, "Cobras 30"));   
        this.alMazo(new SorpresaPago(-20, "Pagas 20"));   
        
        //IRCARCEL
        this.alMazo(new SorpresaCarcel(tablero));
        
        //IRCASILLA        
        this.alMazo(new SorpresaCasilla(tablero, tablero.numCasillas()-1, "Ve a la ultima casilla"));  
        this.alMazo(new SorpresaCasilla(tablero, tablero.numCasillas()-1, "Ve a la ultima casilla"));  
        this.alMazo(new SorpresaCasilla(tablero, 0, "Ve a la casilla inicial"));  
        
        //PORCASAHOTEL
        this.alMazo(new SorpresaPorCasaHotel(10, "Recibes dinero por casa y hotel"));
        this.alMazo(new SorpresaPorCasaHotel(-15, "Pagas por casa y hotel"));
          
        //PORJUGADOR
        this.alMazo(new SorpresaPorJugador(100, "Recibes de cada jugador"));
        this.alMazo(new SorpresaPorJugador(-100, "Pagas a cada jugador"));
        
        //SALIRCARCEL
        this.alMazo(new SorpresaSalvoconducto(this));
    }
    
}
