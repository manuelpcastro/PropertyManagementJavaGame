package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Tablero {
    
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int porSalida;
    private Boolean tieneJuez;
    
    protected Tablero(int casillaCarcel){
                    
        this.numCasillaCarcel = casillaCarcel > 0 ? casillaCarcel : -casillaCarcel;
        
        this.casillas = new ArrayList<>();
        //Añadir casilla salida
        
        this.porSalida = 0;
        this.tieneJuez = false;
    }
    
    //Por que no unificar ambas correcto?
    private Boolean correcto(){
        Boolean valido = true;
        
        if(this.casillas.size() >= this.numCasillaCarcel) valido = true;
        
        return valido;
    }
    
    
    private Boolean correcto(int numCasilla){
        Boolean valido = false;
        
        if(numCasilla < this.casillas.size()) valido = true;
        
        return correcto() && valido;
    }
    
    
    protected int getCarcel(){
        return this.numCasillaCarcel;
    }
    
    
    protected int getPorSalida(){
        int numeroVeces = this.porSalida;
        this.porSalida--;
        return numeroVeces;
    }
    
    
    protected void añadeCasilla(Casilla nuevaCasilla){
        
        if(this.casillas.size() == this.numCasillaCarcel){
            Casilla casillaCarcel = new Casilla("Carcel");
            this.casillas.add(casillaCarcel);
        }
        
        this.casillas.add(nuevaCasilla);
        
        //Esto es redundante?????????!!!!!
        if(this.casillas.size() == this.numCasillaCarcel){
            Casilla casillaCarcel = new Casilla("Carcel");
            this.casillas.add(casillaCarcel);
        }
    }
    
    
    protected void añadeJuez(){
        
        if(!this.tieneJuez){
            Casilla casillaJuez = new Casilla("Juez");
            this.casillas.add(casillaJuez);
            this.tieneJuez = true;
        }
    }
    
    
    protected Casilla getCasilla(int numCasilla){
        if(numCasilla >= 0 && numCasilla < this.casillas.size()){
            return this.casillas.get(numCasilla);
        }
           
        return null;
    }
    
    
    protected int nuevaPosicion(int actual, int tirada){
        //Que comprobamos? el % nos asegura que esta dentro
        if(!correcto(actual)) return -1;
        int nuevaPosicion = (actual + tirada)%this.casillas.size();
        
        if(actual+tirada != nuevaPosicion) this.porSalida++;
        
        return nuevaPosicion;
    }
    
    
    protected int calcularTirada(int origen, int destino){
        int tirada = destino - origen;
        if(tirada < 0) tirada += this.casillas.size();
        
        return tirada;
    }
    
    int numCasillas(){
        return this.casillas.size();
    }
    
    
}
