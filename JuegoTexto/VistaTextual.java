package JuegoTexto;

import civitas.GestionesInmobiliarias;
import civitas.Respuestas;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class VistaTextual {
  
  private CivitasJuego juegoModel; 
  private int iGestion=-1;
  private int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar() {
      System.out.println(this.juegoModel.getCasillaActual().toString());
      System.out.println("¿Quieres comprar la calle? S/N");
      String respuesta="";
      boolean ok = false;
      
      Respuestas result = Respuestas.NO;
      while(!ok){
          respuesta = in.nextLine();
          ok = true;
          switch(respuesta){
              case "N": result = Respuestas.NO; break;
              case "n": result = Respuestas.NO; break;
              case "S": result = Respuestas.SI; break;
              case "s": result = Respuestas.SI; break;
              default: ok = false; break;
          }
      }
      return result;
  }

  void gestionar () {
      System.out.println(juegoModel.infoPropiedades());
      
      this.iPropiedad = this.menu("Elige la propiedad que quieres gestionar", juegoModel.getPropiedadesJugadorActual());
      
      ArrayList<String> lista = new ArrayList<>();
      lista.add(GestionesInmobiliarias.VENDER.toString());
      lista.add(GestionesInmobiliarias.HIPOTECAR.toString());
      lista.add(GestionesInmobiliarias.CANCELAR_HIPOTECA.toString());
      lista.add(GestionesInmobiliarias.CONSTRUIR_CASA.toString());
      lista.add(GestionesInmobiliarias.CONSTRUIR_HOTEL.toString());
      lista.add(GestionesInmobiliarias.TERMINAR.toString());
      this.iGestion = this.menu("Elige la operacion que quieres realizar", lista);
        
  }
  
  public int getGestion(){ return this.iGestion; }
  
  public int getPropiedad(){ return this.iPropiedad; }
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
      System.out.println("La siguiente operacion es " + operacion.toString());
  }


  void mostrarEventos() {
      Diario diario = Diario.getInstance();
      while(diario.eventosPendientes())
        System.out.println("\033[35m DIARIO:" + diario.leerEvento());
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
    }
  
  public void actualizarVista(){
      String info = juegoModel.infoJugadorTexto();
      
      System.out.println(info);
  } 
  
  void ranking(){
      menu("----Ranking----", juegoModel.infoRanking());
  }
}
