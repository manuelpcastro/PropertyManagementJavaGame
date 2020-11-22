package civitas;

import java.util.Random;

/**
 *
 * @author manuel
 */
public class Dado {
    
    private static final Dado instance = new Dado();
    private static final int SalidaCarcel = 5;
    
    private Random random;
    private int ultimoResultado;
    private Boolean debug;
    
    private Dado(){
        this.debug = false;
        this.random = new Random();
        tirar();  
    }
    
    
    static public Dado getInstance(){
        return instance;
    }
    
    
    int tirar(){
        ultimoResultado = debug ? 1 : random.nextInt(6) + 1;
        return ultimoResultado;
    }
    
    
    Boolean salgoDeLaCarcel(){
        Boolean salgo = tirar() == SalidaCarcel;
        return salgo;
    }
    
    
    int quienEmpieza(int numJugadores){
        return random.nextInt(numJugadores);
    }
    
    
    void setDebug(Boolean d){
        this.debug = d;
        String estado = d ? "activado" : "desactivado";
        Diario.getInstance().ocurreEvento("Dado: modo debug " + estado);
    }
    
    
    int getUltimoResultado(){
        return this.ultimoResultado;
    }
}
