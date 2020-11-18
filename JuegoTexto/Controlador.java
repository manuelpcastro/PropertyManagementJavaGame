package JuegoTexto;

import civitas.OperacionInmobiliaria;
import JuegoTexto.VistaTextual;
import civitas.Respuestas;
import civitas.CivitasJuego;
import civitas.GestionesInmobiliarias;
import civitas.OperacionesJuego;

/**
 *
 * @author manuel
 */
public class Controlador {
    CivitasJuego juego;
    VistaTextual vista;
    
    public Controlador(CivitasJuego instancia, VistaTextual vistaTextual){
        juego = instancia;
        vista = vistaTextual;
    }
    
    public void juega(){
        vista.setCivitasJuego(juego);
        
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            vista.pausa();
            
            OperacionesJuego siguiente = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(siguiente);
            if(siguiente != OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()){
                switch (siguiente){
                    case COMPRAR:
                        if(vista.comprar() == Respuestas.SI){
                            juego.comprar();                
                            juego.siguientePasoCompletado(siguiente);
                        }
                        break;
                    case GESTIONAR:
                        vista.gestionar();
                        OperacionInmobiliaria op = new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.getGestion()], vista.getPropiedad());
                        this.gestionar(op);
                        break;
                        
                    case SALIR_CARCEL:
                        switch(vista.salirCarcel()){
                            case PAGANDO:
                                juego.salirCarcelPagando();
                                break;
                            case TIRANDO:
                                juego.salirCarcelTirando();
                                break;
                        }
                        juego.siguientePasoCompletado(juego.siguientePaso()); 
                        break;
                }
            }
        }
        
        System.out.println("SE TERMINA");
    }
    
    public void gestionar(OperacionInmobiliaria op){
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
                juego.siguientePasoCompletado(juego.siguientePaso());
                break;
        }
    }
}
