package Controlador;

import Controlador.GestionesInmobiliarias;

/**
 *
 * @author manuel
 */
public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInmobiliarias gestion;
    
    public OperacionInmobiliaria(GestionesInmobiliarias gest, int ip){
        
        this.gestion = gest;
        this.numPropiedad = ip;
    }
    
    public GestionesInmobiliarias getGestion(){
        return this.gestion;
    }
    
    public int getNumPropiedad(){
        return this.numPropiedad;
    }
}
