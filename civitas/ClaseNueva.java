package civitas;

import java.util.ArrayList;

/**
 *
 * @author manuel
 */
public class ClaseNueva {
    
    private static int INSTANCIAS=0;
    
    int atributoA;
    String atributoB;
    
    private Jugador a;
    private Tablero m;
    private Casilla c;
    
    ClaseNueva(int atA, String atB, Jugador a, Tablero m, Casilla c){
        this.atributoA = atA;
        this.atributoB = atB;
        this.a = a;
        this.m = m;
        this.c = c;
        
        ClaseNueva.INSTANCIAS++;
    }
    
    ClaseNueva(ClaseNueva otro){
        this.atributoA = 7;
        this.atributoB = "Examen";
        
        this.a = otro.getA();
        this.c = otro.getC();
        this.m = otro.getM();
        
        ClaseNueva.INSTANCIAS++;
    }
    
    public static int getInstancias(){
        return INSTANCIAS;
    }

    public Jugador getA() {
        return a;
    }

    public void setA(Jugador a) {
        this.a = a;
    }

    public Tablero getM() {
        return m;
    }

    public void setM(Tablero m) {
        this.m = m;
    }

    public Casilla getC() {
        return c;
    }

    public void setC(Casilla c) {
        this.c = c;
    }
    
    
    public void recorre(ArrayList<Integer> numeros){
        System.out.println("_______________EXAMEN_______________-");
        for(int i =0; i<numeros.size(); i++){
            int n = Dado.getInstance().tirar();
            System.out.println("----ITERACION:  " + i);
            System.out.println("----DADO:  " + n);
            if(numeros.get(i) < n){
                
                //si es el ultimo
                if(i==numeros.size()-1){
                    System.out.println("----ES LA ULTIMA ITERACION: GET TITULO PROPIEDAD ");
                    this.c.getTituloPropiedad();
                }
                else{ //sino
                     //si estamos en par 
                    if(i%2==0){
                       System.out.println("----PAR:  ENCARCELAR AL JUGADOR");
                       this.a.encarcelar(this.m.getCarcel());
                       System.out.println("---ENCARCELADO? " + this.a.isEncarcelado());
                    }
                    else{
                        System.out.println("----INMPAR: AÑADE CASILLA JUEZ AL TABLERO ");
                        this.m.añadeJuez();
                    }
                }
            }   
        }
    }
    
    
}
