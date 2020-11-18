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
    
    private Diario diario;
    
    private Dado(){
        this.debug = false;
        this.random = new Random();
        this.diario = Diario.getInstance();
        tirar();  
    }
    
    
    static public Dado getInstance(){
        return instance;
    }
    
    
    protected int tirar(){
        ultimoResultado = debug ? 1 : random.nextInt(6) + 1;
        return ultimoResultado;
    }
    
    
    protected Boolean salgoDeLaCarcel(){
        Boolean salgo = tirar() == SalidaCarcel;
        return salgo;
    }
    
    
    protected int quienEmpieza(int numJugadores){
        return random.nextInt(numJugadores);
    }
    
    
    protected void setDebug(Boolean d){
        this.debug = d;
        String estado = d ? "activado" : "desactivado";
        diario.ocurreEvento("Dado: modo debug " + estado);
    }
    
    
    protected int getUltimoResultado(){
        return this.ultimoResultado;
    }
}
