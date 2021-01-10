package civitas;

import Vista.GUI.Dado;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class Jugador implements Comparable<Jugador>{
    
    protected static int CASAS_MAX = 4;
    protected static int CASAS_POR_HOTEL = 4;
    
    protected static int HOTELES_MAX = 4;
    
    protected static float PASO_POR_SALIDA = 1000;
    protected static float PRECIO_LIBERTAD = 200;
    
    protected static float SALDO_INICIAL = 7500;
    
    private String nombre;
    private int numCasillaActual;
    protected boolean encarcelado;
    
    private boolean puedeComprar;
    private float saldo;
    
    
    SorpresaSalvoconducto salvoconducto;
    private ArrayList<TituloPropiedad> propiedades;
  
    Jugador(String nombre){
        this.nombre = nombre;
        this.numCasillaActual = 0;
        this.puedeComprar = false;
        this.saldo = Jugador.SALDO_INICIAL;
        this.encarcelado = false;
        this.propiedades = new ArrayList<>();
        this.salvoconducto = null;
    }
    
    protected Jugador(Jugador otro){
        this.nombre = otro.nombre;
        this.numCasillaActual = otro.numCasillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.getSaldo();
        this.encarcelado = otro.isEncarcelado();
        this.propiedades = otro.propiedades;
        this.salvoconducto = otro.salvoconducto;
    }
    
  
    
    boolean cancelarHipoteca(int ip){ 
        boolean result = false;
        
        if(this.encarcelado) return result;
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = this.propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            if(this.puedoGastar(cantidad)){
                result = propiedad.cancelarHipoteca(this);
                if(result)
                    this.informarDiario("cancela la hipoteca de la propiedad " + ip);
            }
        }
        return result;
    }
    
    int cantidadCasasHoteles(){
        int cantidadTotal = 0;
        for (TituloPropiedad propiedad : propiedades){
            cantidadTotal += propiedad.cantidadCasasHoteles();
        }
        return cantidadTotal;
    }
    
    @Override
    public int compareTo(Jugador otro){
        return Float.compare(this.saldo, otro.getSaldo());
    }
    
    boolean comprar(TituloPropiedad titulo){
        boolean result = false;
        if(this.encarcelado) return result;
        
        if(this.puedeComprar){
            float precio = titulo.getPrecioCompra();
            if(this.puedoGastar(precio)){
                result = titulo.comprar(this);
                
                if(result){
                    this.propiedades.add(titulo);
                    this.informarDiario("compra la propiedad " + titulo.getNombre());
                    this.puedeComprar = false;
                }
            }
        }
        return result;
    }
    
    boolean construirCasa(int ip){
        boolean result=false;
        
        if(encarcelado) return result;
        
        boolean existe = this.existeLaPropiedad(ip);
        if(existe) {
            TituloPropiedad titulo = this.propiedades.get(ip);
            boolean puedoEdificarCasa = this.puedoEdificarCasa(titulo);
            if(puedoEdificarCasa){
                result = titulo.construirCasa(this);
                this.informarDiario("construye casa en la propiedad" + ip);
            }
            
        }
        return result;
    }
    
    boolean construirHotel(int ip){
        boolean result=false;
        if(encarcelado) return result;
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = this.propiedades.get(ip);
            boolean puedoEdificarHotel = this.puedoEdificarHotel(propiedad);
            if(puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                int casasPorHotel = this.getCasasPorHotel();
                propiedad.derruirCasas(casasPorHotel, this);
                this.informarDiario("construye hotel en la propiedad " + ip);
            }
        }
        return result;
    }
    
    protected boolean debeSerEncarcelado() {
        if (this.encarcelado) return false;
        
        if(!this.tieneSalvoconducto()) return true;
 
        this.perderSalvoconducto();
        this.informarDiario("evita ir a la cárcel, usa Salvoconducto.");
        return false;     
    }
    
    boolean enBancarrota(){
        return this.saldo < 0;
    }
    
    boolean encarcelar(int numCasillaCarcel){
        if(this.debeSerEncarcelado()){
            this.moverACasilla(numCasillaCarcel);
            this.encarcelado = true;
        }
        return this.encarcelado;
    }
    
    private boolean existeLaPropiedad(int ip){
        return this.propiedades.size() > ip && ip >= 0;
    }
    
    protected int getCasasMax(){
        return Jugador.CASAS_MAX;
    }
    
    private int getCasasPorHotel(){
        return Jugador.CASAS_POR_HOTEL;
    }
    
    protected int getHotelesMax(){
        return Jugador.HOTELES_MAX;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    int getNumCasillaActual(){
        return this.numCasillaActual;
    }
    
    private float getPrecioLibertad(){
        return Jugador.PRECIO_LIBERTAD;
    }
    
    private float getPremioPasoSalida(){
        return Jugador.PASO_POR_SALIDA;
    }
   
    public ArrayList<TituloPropiedad> getPropiedades(){
        return this.propiedades;
    }
    
    boolean getPuedeComprar(){
        return this.puedeComprar;
    }
    
    public float getSaldo(){
        return this.saldo;
    }
    
    boolean hipotecar(int ip){
        boolean result = false;
        
        if(this.encarcelado) return result;
        
        if(this.existeLaPropiedad(ip)){
            TituloPropiedad propiedad = this.propiedades.get(ip);
            result = propiedad.hipotecar(this);
            if(result)
                this.informarDiario("hipoteca la propiedad " + ip);
        }
            
        return result;
    }
    
    public boolean isEncarcelado(){
        return this.encarcelado;
    }
    
    boolean modificarSaldo(float cantidad){
        this.saldo += cantidad;
        return this.enBancarrota();
    }
    
    boolean moverACasilla(int numCasilla){
        if(this.encarcelado) return false;
        
        this.numCasillaActual = numCasilla;
        this.puedeComprar = false;
        this.informarDiario("ha avanzado a la casilla " + this.numCasillaActual);
        return true;
    }
    
    boolean obtenerSalvoConducto(Sorpresa sorpresa){
        if(this.encarcelado) return false;
        
        this.salvoconducto = (SorpresaSalvoconducto) sorpresa;
        return true;
    }
    
    boolean paga(float cantidad){
        return this.modificarSaldo(-cantidad);
    }
    
    boolean pagaAlquiler(float cantidad){
        if(this.encarcelado) return false;
        
        return this.paga(cantidad);
    }
    
    boolean pagaImpuesto(float cantidad){
        if(this.encarcelado) return false;
        
        return this.paga(cantidad);
    }
    
    boolean pasaPorSalida(){
        this.modificarSaldo(this.getPremioPasoSalida());
        this.informarDiario("ha pasado por la salida y ha recibido el pago");
        return true;
    }
    
    private void perderSalvoconducto(){
        this.salvoconducto.usada();
        this.salvoconducto = null;
    }
    
    boolean puedeComprarCasilla(){
        this.puedeComprar = !this.encarcelado;
        
        return this.puedeComprar;
    }
    
    private boolean puedeSalirCarcelPagando(){
        return this.saldo > this.getPrecioLibertad();
    }
    
    private boolean puedoEdificarCasa(TituloPropiedad titulo){
        boolean puedoEdificarCasa = titulo.getNumCasas() < this.getCasasMax() && this.puedoGastar(titulo.getPrecioEdificar());
        return puedoEdificarCasa;
    }
    
    private boolean puedoEdificarHotel(TituloPropiedad titulo){
        boolean puedoEdificarHotel = false;
        float precio = titulo.getPrecioEdificar();
        puedoEdificarHotel = this.puedoGastar(precio) && titulo.getNumHoteles() < this.getHotelesMax() && titulo.getNumCasas() >= this.getCasasPorHotel();
        return puedoEdificarHotel;
    }
    
    protected boolean puedoGastar(float precio){
        if(this.encarcelado) return false;    
        return this.saldo >= precio;
    }
     
    boolean recibe(float cantidad){
        if(this.encarcelado) return false;
        return this.modificarSaldo(cantidad);
    }
     
    boolean salirCarcelPagando(){
        if (this.puedeSalirCarcelPagando()){
           this.paga(this.getPrecioLibertad());
           this.encarcelado = false;
           this.informarDiario("ha abandonado la carcel pagando");
           return true;
        }
        return false;
    }
     
    boolean salirCarcelTirando(){
        encarcelado = !Dado.getInstance().salgoDeLaCarcel();
        if(!encarcelado)
            this.informarDiario("ha salido de la cárcel gracias al dado");
        else
            this.informarDiario("no ha salido de la cárcel usando al dado"); 
        return !encarcelado;
    }
     
    boolean tieneAlgoQueGestionar(){
        return this.propiedades.size() > 0;
    }
     
    boolean tieneSalvoconducto(){
        return this.salvoconducto != null;
    }
    
    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", encarcelado=" + encarcelado + ", numCasillaActual=" + numCasillaActual + ", saldo=" + saldo + ", propiedades=" + propiedades + '}';
    }

    boolean vender(int ip){
        if(this.encarcelado) return false;
         
        if(this.existeLaPropiedad(ip)){
            //Asegurar que esto es lo que se pide
            if(this.propiedades.get(ip).vender(this)){
                this.informarDiario("ha vendido la propiedad " + this.propiedades.get(ip).getNombre());
                this.propiedades.remove(ip);
                return true;
            }
        }
        return false;
    }
     
    private void informarDiario(String mensaje){
        Diario.getInstance().ocurreEvento("Jugador " + this.nombre + ": " + mensaje);
    }

    public String infoPropiedades(){
        String titulos = "\n    Propiedades: \n";
        for(TituloPropiedad p : this.propiedades){
           titulos+="   "
                   + "" + p.toString()+"\n";
        }
        return titulos;     
    }

    ArrayList<String> getPropiedadesNombre() {
        ArrayList<String> titulos = new ArrayList<>();
        for(TituloPropiedad p : this.propiedades){
           titulos.add(p.getNombre());
        }
        return titulos;
    }
    
    
}
