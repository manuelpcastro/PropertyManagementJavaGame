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
    
    private Jugador propietario;
    
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe){
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
    
    
    void actualizaPropietarioPorConversion(Jugador jugador){
        this.propietario = jugador;
    }
    
    
    boolean cancelarHipoteca(Jugador jugador){
        boolean result=false;
        if(this.hipotecado && this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.getImporteCancelarHipoteca());
            this.hipotecado = false;
            result = true;
        }
        return result;
    }
    
    
    int cantidadCasasHoteles(){
        return this.numCasas + this.numHoteles;
    }
    
    
    boolean comprar(Jugador jugador){
        boolean result = false;
        if(!this.tienePropietario()){
            jugador.paga(this.precioCompra);
            this.propietario = jugador;
            result = true;
        }
        return result;
    }
    
    
    boolean construirCasa(Jugador jugador){
        boolean result = false;
        if(this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.precioEdificar);
            this.numCasas++;
            result = true;
        }
        return result;
    }
    
    
    boolean construirHotel(Jugador jugador){
        boolean result = false;
        if(this.esEsteElPropietario(jugador)){
            this.propietario.paga(this.precioEdificar);
            this.numHoteles++;
            result = true;
        }
        return result;
    }
    
    
    boolean derruirCasas(int n, Jugador jugador){
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
    
    
    float getImporteCancelarHipoteca(){
       return (float) (this.getImporteHipoteca()*TituloPropiedad.FACTOR_INTERESES_HIPOTECA);
    }
    
    
    private float getImporteHipoteca(){
        float cantidad = (float) (this.hipotecaBase*(1+(this.numCasas*0.5)+(this.numHoteles*2.5)));
        return cantidad;
    }
    
    
    public String getNombre(){
        return this.nombre;
    }
    
    
    public int getNumCasas(){
        return this.numCasas;
    }
    
    
    public int getNumHoteles(){
        return this.numHoteles;
    }
    
    
    private float getPrecioAlquiler(){
        return this.alquilerBase;
    }
    
    
    float getPrecioCompra(){
        return this.precioCompra;
    }
    
    
    float getPrecioEdificar(){
        return this.precioEdificar;
    }
    
    
    private float getPrecioVenta(){
        return this.precioCompra + (this.precioEdificar*this.factorRevalorizacion);
    }
    
    
    Jugador getPropietario(){
        return this.propietario;
    }
    
    
    boolean hipotecar(Jugador jugador){
        boolean result = false;
        if(!hipotecado && this.esEsteElPropietario(jugador)){
            this.propietario.recibe(this.getImporteHipoteca());
            this.hipotecado = true;
            result = true;
        }
        return result;
    }
    
    
    private boolean propietarioEncarcelado(){
        return this.propietario.isEncarcelado();
    }
    
    
    boolean tienePropietario(){
        return this.propietario != null;
    }
    
    
    void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario()){
            if(!this.esEsteElPropietario(jugador)){
                float precio = this.getPrecioAlquiler();
                jugador.pagaAlquiler(precio);
                this.propietario.recibe(precio);
            }
        }
    }
    
    
    boolean vender(Jugador jugador){
        if(!this.esEsteElPropietario(jugador) || this.hipotecado) return false;
        
        jugador.recibe(this.getPrecioVenta());
        this.propietario = null;
        this.numCasas = 0;
        this.numHoteles = 0;
        
        return true;
    }

    @Override
    public String toString() {
        String nombreProp = propietario == null ? "nadie" : this.propietario.getNombre();
        String s_hipotecada = hipotecado == true ? "HIPOTECADA" : "";
       
        return "\n  TituloPropiedad{" + s_hipotecada + " nombre=" + nombre + ", propietario= " + nombreProp + ", numCasas=" + numCasas + ", numHoteles=" + numHoteles + ", precioCompra=" + precioCompra + ", precioEdificar=" + precioEdificar + "}\n";
    }
}
