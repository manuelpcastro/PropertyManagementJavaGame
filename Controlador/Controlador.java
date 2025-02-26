package Controlador;

import Vista.Respuestas;
import Vista.SalidasCarcel;
import Vista.VistaCivitas;

import civitas.CivitasJuego;
import civitas.OperacionesJuego;

/**
 *
 * @author manuel
 */
public class Controlador {
    CivitasJuego juego;
    VistaCivitas vista;
    
    public Controlador(CivitasJuego instancia, VistaCivitas view){
        juego = instancia;
        vista = view;
    }
    
    public void juega(){
        vista.setCivitasJuego(juego);
        
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            
            OperacionesJuego siguiente = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(siguiente);
            if(siguiente != OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()){
                switch (siguiente){
                    case COMPRAR:
                        if(vista.comprar() == Respuestas.SI)
                            juego.comprar();           
                        juego.siguientePasoCompletado(siguiente);     
                        break;
         
                    case GESTIONAR:
                        vista.gestionar();                       
                        OperacionInmobiliaria op = new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.getGestion()], vista.getPropiedad());
                        this.casosGestionar(op,siguiente);
                        break;
                        
                    case SALIR_CARCEL:
                        SalidasCarcel salida = vista.salirCarcel();
                        switch(salida){
                            case PAGANDO:
                                juego.salirCarcelPagando();
                                break;
                            case TIRANDO:
                                juego.salirCarcelTirando();
                                break;
                        }
                        juego.siguientePasoCompletado(siguiente); 
                        break;
                }
            }
        }
        vista.ranking();
    }
    
    
    //Por legibilidad de codigo en el metodo juega, saco esta parte fuera
    private void casosGestionar(OperacionInmobiliaria op, OperacionesJuego siguiente){
        int ip = op.getNumPropiedad();  
        switch(op.getGestion()){
            case VENDER:
                juego.vender(ip);
                break;
            case HIPOTECAR:
                juego.hipotecar(ip);
                break;
            case CANCELAR_HIPOTECA:
                juego.cancelarHipoteca(ip);
                break;
            case CONSTRUIR_CASA:
                juego.construirCasa(ip);
                break;
            case CONSTRUIR_HOTEL:
                juego.construirHotel(ip);
                break;
            case TERMINAR:
                juego.siguientePasoCompletado(siguiente);
                break;
        }
    }
}
