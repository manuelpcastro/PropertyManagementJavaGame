package Vista.JuegoTexto;

import Controlador.Controlador;
import civitas.CivitasJuego;
import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class PruebaTextual {
    
    
    
    public static void main (String[] args){
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Manuel"); //nombres.add("Juan"); nombres.add("Maria");
        
        CivitasJuego civitas = new CivitasJuego(nombres);
        VistaTextual vista = new VistaTextual();
        Controlador controlador = new Controlador(civitas, vista);
        controlador.juega();
    }
    
}
