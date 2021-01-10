package Vista;

import civitas.CivitasJuego;
import civitas.OperacionesJuego;
import java.util.ArrayList;


/**
 *
 * @author manuel
 */
public interface VistaCivitas {

  SalidasCarcel salirCarcel();

  Respuestas comprar();

  void gestionar ();
  
  public int getGestion();
  
  public int getPropiedad();

  void mostrarSiguienteOperacion(OperacionesJuego operacion);

  void mostrarEventos();
  
  public void setCivitasJuego(CivitasJuego civitas);
  
  public void actualizarVista();
  
  void ranking();
}
