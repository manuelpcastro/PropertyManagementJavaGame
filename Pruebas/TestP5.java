package Pruebas;

import Controlador.Controlador;
import Vista.GUI.CapturaNombres;
import Vista.GUI.Dado;
import Vista.GUI.VistaGrafica;
import civitas.CivitasJuego;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class TestP5 {
    
    
    public static void main (String[] args){
        VistaGrafica view = new VistaGrafica();
        
        Dado.createInstance(view);
        Dado.getInstance().setDebug(true);
        
        CapturaNombres cn = new CapturaNombres(view, true);
        
        ArrayList<String> nombres = new ArrayList<>();
        nombres = cn.getNombres();
        
        CivitasJuego civitas = new CivitasJuego(nombres);
        Controlador controlador = new Controlador(civitas, view);
        view.setCivitasJuego(civitas);
        controlador.juega();
    }
}
