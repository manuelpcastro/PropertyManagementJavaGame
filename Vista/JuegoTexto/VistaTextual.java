package Vista.JuegoTexto;

import Controlador.GestionesInmobiliarias;
import Vista.Respuestas;
import Vista.VistaCivitas;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import Vista.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VistaTextual implements VistaCivitas{
  
  private CivitasJuego juegoModel; 
  private int iGestion=-1;
  private int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  public VistaTextual () {
    in = new Scanner (System.in);
  }
  
  public void mostrarEstado(String estado) {
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

  public int menu (String titulo, ArrayList<String> lista) {
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

  @Override
  public SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  @Override
  public Respuestas comprar() {
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

  @Override
  public void gestionar () {
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
    

  @Override
  public void mostrarSiguienteOperacion(OperacionesJuego operacion) {
      System.out.println("La siguiente operacion es " + operacion.toString());
  }


  @Override
  public void mostrarEventos() {
      Diario diario = Diario.getInstance();
      while(diario.eventosPendientes())
        System.out.println("\033[35m DIARIO:" + diario.leerEvento());
  }
  
  @Override
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
    }
  
  @Override
  public void actualizarVista(){
      String info = juegoModel.infoJugadorTexto();
      
      System.out.println(info);
  } 
  
  @Override
  public void ranking(){
      menu("----Ranking----", juegoModel.infoRanking());
  }
}
