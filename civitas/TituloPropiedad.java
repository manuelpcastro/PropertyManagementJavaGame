package civitas;

/**
 *
 * @author manuel
 */
public class TituloPropiedad {
    
    private float alquilerBase;
    private static float FACTOR_INTERESES_HIPOTECA = (float) 1.1;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private Boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    
    Jugador propietario;
    
    protected TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe){
        this.nombre = nom;
        this.alquilerBase = ab;
        this.factorRevalorizacion =fr;
        this.hipotecaBase = hb;
        this.precioCompra = pc;
        this.precioEdificar = pe;
        
        this.propietario = null;
        this.hipotecado = false;
        this.numCasas = 0;
        this.numHoteles = 0;
    }
    
    
    protected void actualizaPropietarioPorConversion(Jugador jugador){
        this.propietario = jugador;
    }
    
    
    protected boolean cancelarHipoteca(Jugador jugador){
        boolean result=false;
        if(this.hipotecado && this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.getImporteCancelarHipoteca());
            this.hipotecado = false;
            result = true;
        }
        return result;
    }
    
    
    protected int cantidadCasasHoteles(){
        return this.numCasas + this.numHoteles;
    }
    
    
    protected boolean comprar(Jugador jugador){
        boolean result = false;
        if(!this.tienePropietario()){
            jugador.paga(this.precioCompra);
            this.propietario = jugador;
            result = true;
        }
        return result;
    }
    
    
    protected boolean construirCasa(Jugador jugador){
        boolean result = false;
        if(this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.precioEdificar);
            this.numCasas++;
            result = true;
        }
        return result;
    }
    
    
    protected boolean construirHotel(Jugador jugador){
        boolean result = false;
        if(this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.precioEdificar);
            this.numHoteles++;
            result = true;
        }
        return result;
    }
    
    
    protected boolean derruirCasas(int n, Jugador jugador){
        if(!this.esEsteElPropietario(jugador) || this.numCasas < n) return false;
        
        this.numCasas -= n;
        return true;
    }
    
    
    private boolean esEsteElPropietario(Jugador jugador){
        return this.propietario.equals(jugador);
    }
    
    
    public boolean getHipotecado(){
        return this.hipotecado;
    }
    
    
    protected float getImporteCancelarHipoteca(){
       return (float) (this.getImporteHipoteca()*1.1);
    }
    
    
    private float getImporteHipoteca(){
        float cantidad = (float) (this.hipotecaBase*(1+(this.numCasas*0.5)+(this.numHoteles*2.5)));
        return cantidad;
    }
    
    
    protected String getNombre(){
        return this.nombre;
    }
    
    
    protected int getNumCasas(){
        return this.numCasas;
    }
    
    
    protected int getNumHoteles(){
        return this.numHoteles;
    }
    
    
    private float getPrecioAlquiler(){
        return this.alquilerBase;
    }
    
    
    protected float getPrecioCompra(){
        return this.precioCompra;
    }
    
    
    protected float getPrecioEdificar(){
        return this.precioEdificar;
    }
    
    
    private float getPrecioVenta(){
        return this.precioCompra + (this.precioEdificar*this.factorRevalorizacion);
    }
    
    
    protected Jugador getPropietario(){
        return this.propietario;
    }
    
    
    protected boolean hipotecar(Jugador jugador){
        boolean result = false;
        if(!hipotecado && this.esEsteElPropietario(jugador)){
            this.propietario.recibe(this.getImporteHipoteca());
            this.hipotecado = true;
            result = true;
        }
        return result;
    }
    
    
    private boolean propietarioEncarcelado(){
        return this.propietario.encarcelado;
    }
    
    
    protected boolean tienePropietario(){
        return this.propietario != null;
    }
    
    
    protected void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario()){
            if(!this.esEsteElPropietario(jugador)){
                float precio = this.getPrecioAlquiler();
                jugador.pagaAlquiler(precio);
                this.propietario.recibe(precio);
            }
        }
    }
    
    
    protected boolean vender(Jugador jugador){
        if(!this.esEsteElPropietario(jugador) || this.hipotecado) return false;
        
        jugador.recibe(this.getPrecioVenta());
        this.propietario = null;
        this.numCasas = 0;
        this.numHoteles = 0;
        
        return true;
    }

    @Override
    public String toString() {
        return "TituloPropiedad{" + "alquilerBase=" + alquilerBase + ", factorRevalorizacion=" + factorRevalorizacion + ", hipotecaBase=" + hipotecaBase + ", hipotecado=" + hipotecado + ", nombre=" + nombre + ", numCasas=" + numCasas + ", numHoteles=" + numHoteles + ", precioCompra=" + precioCompra + ", precioEdificar=" + precioEdificar + ", propietario=" + propietario + '}';
    }
}
