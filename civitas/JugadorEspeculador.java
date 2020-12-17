package civitas;

/**
 *
 * @author manuel
 */
public class JugadorEspeculador extends Jugador{
    
    private static int FACTOR_ESPECULADOR=2;
    private static float IMPUESTOS=0.5f;
    private  int fianza;
    
    JugadorEspeculador(Jugador jugador, int fianza){
        super(jugador);
        this.fianza = fianza;
    }
    
    @Override
    protected int getCasasMax(){
        return Jugador.CASAS_MAX*FACTOR_ESPECULADOR;
    }
    
    @Override
    protected int getHotelesMax(){
        return Jugador.HOTELES_MAX*FACTOR_ESPECULADOR;
    }
    
    @Override
    public boolean encarcelar(int numCasillaCarcel){
        
        if(!this.debeSerEncarcelado())
            return false;
              
        if(this.puedoGastar(fianza)){
            this.paga(fianza);
            Diario.getInstance().ocurreEvento("Jugador " + this.getNombre() + " ha evitado ir a la carcel pagando una fianza de " + fianza);
            return false;
        }
        
        this.moverACasilla(numCasillaCarcel);
        this.encarcelado = true;
        return true;
    }
    
    @Override
    public boolean pagaImpuesto(float cantidad){
        return this.paga(cantidad*IMPUESTOS);
    }
    
    @Override
    public String toString(){
        return "¡JugadorEspeculador!{" + "nombre=" + this.getNombre() + ", fianza=" + fianza + ", encarcelado=" + encarcelado + ", numCasillaActual=" + this.getNumCasillaActual() + ", saldo=" + this.getSaldo() + ", propiedades=" + this.getPropiedades() + '}';
    }
}
