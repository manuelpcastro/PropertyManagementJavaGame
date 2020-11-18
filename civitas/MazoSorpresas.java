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
    
    protected MazoSorpresas(){
        init();
        this.debug = false;
    }
    
    protected MazoSorpresas(Boolean modoDebug){
        init();
        this.debug = modoDebug;
        if(modoDebug) diario.ocurreEvento("MazoSorpresas: modo debug activado");
    }
    
    
    protected void alMazo(Sorpresa s){
        if(!this.barajada){
            this.sorpresas.add(s);
        }
    }
    
    
    protected Sorpresa siguiente(){
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
    
    
    protected void inhabilitarCartaEspecial(Sorpresa sorpresa){
        diario.ocurreEvento("MazoSorpresas: carta especial inhabilitada");
        this.sorpresas.remove(sorpresa); 
        this.cartasEspeciales.add(sorpresa);
    }
    
    
    protected void habilitarCartaEspecial(Sorpresa sorpresa){
         diario.ocurreEvento("MazoSorpresas: carta especial habilitada");
         this.cartasEspeciales.remove(sorpresa); 
         this.sorpresas.add(sorpresa);
    }
    
}
