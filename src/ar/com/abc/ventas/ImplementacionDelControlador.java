package ar.com.abc.ventas;

public class ImplementacionDelControlador implements IControladorClientes {
  private IModeloClientes modelo;
  private IVistaClientes vista;

  public ImplementacionDelControlador() {
    System.out.println("Creando Controlador");
  }

  public void setModelo(IModeloClientes modelo) {
    try {
      // ** 1 Asigna a la variable de instancia modelo la instancia de
      // IModeloClientes
      this.modelo = modelo;
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }

  public void setVista(IVistaClientes vista) {
    try {
      // ** 2 Asigna a la variable de instancia vista la instancia de
      // IVistaClientes
      this.vista = vista;
      // ** 3 Registra este objeto como un listener de las acciones del
      // ** usuarioen el objeto vista
      // ** Pista: invocar al m�todo agregarOyenteAccionUsuario
      vista.agregarOyenteAccionUsuario(this);
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }

  private void reportarExcepcion(Object o) {
    // La responsabilidad de este m�todo es reportar excepciones.
    // Se llama al m�todo mostrarEnPantalla de la vista
    try {
      vista.mostrarEnPantalla(o);
    } catch (Exception e) {
      System.out
          .println("ImplementacionDelControlador.reportarExcepcion "
              + e);
    }
  }

  // M�todos de retorno de llamado (callback) para las acciones del usuario
  // ---------------------------------------------------------------
  /**
   * M�todo para obtener el cliente debido a una acci�n del usuario llamado
   * por la vista en respuesta al clic del bot�n para obtener el cliente en la
   * interfaz gr�fica o su equivalente en la interfaz del usuario acci�n -
   * indicar el tipo presentaci�n en pantalla para el cliente en la interfaz
   * gr�fica a trav�s del m�todo mostrarEnPantalla de la vista
   */
  public void manejadorAccionObtenerCliente(String dni) {
    System.out.println("manejadorAccionObtenerCliente " + dni);
    Cliente cliente = null;
    try {
      // ** 1 Inicializar cliente con el objeto retornado como resultado
      // ** de invocar el m�todo obtenerCliente en el modelo
      cliente = modelo.obtenerCliente(dni);
      // ** 2 Invocar el m�todo mostrarEnPantalla de la vista
      // ** con cliente como par�metro
      vista.mostrarEnPantalla(cliente);
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }

  /*
   * /* ---------------------------------------------------------------
   * M�todo manejador de la acci�n del usuario para agregar el Cliente
   * llamado por la vista en respuesta al clic sobre el bot�n para
   * agregar un cliente en la interfaz gr�fica o su equivalente en la
   * interfaz del usuario
   * acci�n - agregar el (nuevo) cliente del modelo
   */
  public void manejadorAccionAgregarCliente(Cliente c) {
    System.out.println("manejadorAccionAgregarCliente " + c);
    try {
      // ** 1 Invocar al m�todo agregarCliente del modelo con c
      // ** como par�metro
      modelo.agregarCliente(c);
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }

  /*
   * ---------------------------------------------------------------
   * M�todo manejador de la acci�n del usuario para borrar al cliente
   * llamado por ImplVistaVentas en respuesta al clic sobre el bot�n para
   * borrar un cliente en la interfaz gr�fica o su equivalente en la
   * interfaz del usuario
   * acci�n - borrar el cliente del modelo
   */
  public void manejadorAccionBorrarCliente(Cliente c) {
    System.out.println("manejadorAccionBorrarCliente " + c);
    try {
      // ** 1 Invocar al m�todo borrarCliente de modeloVentas con c
      // ** como par�metro
      modelo.borrarCliente(c);
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }

  /*
   * ---------------------------------------------------------------
   * M�todo manejador de la acci�n del usuario para actualizar al Cliente
   * llamado por la vista en respuesta al clic sobre el bot�n para
   * actualizar un cliente en la interfaz gr�fica o su equivalente en la
   * interfaz del usuario
   * acci�n - actualizar el cliente del modelo
   */
  public void manejadorAccionAcualizarCliente(Cliente c) {
    System.out.println("manejadorAccionAcualizarCliente " + c);
    try {
      // ** 1 Invocar al m�todo actualizarCliente del modelo con c
      // ** como par�metro
      modelo.actualizarCliente(c);
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }

  /*
   * ---------------------------------------------------------------
   * M�todo manejador de la acci�n del usuario de obtener todos los clientes
   * llamado por la vista en respuesta al clic del bot�n
   * para obtener todos los clientes en la interfaz gr�fica
   * o su equivalente en la interfaz del usuario
   * acci�n - configurar la presentaci�n en pantalla de todos los clientes
   * en la interfaz gr�fica a trav�s del m�todo mostrarEnPantalla de de la vista
   */
  public void manejadorAccionObtenesTodosLosClientes() {
    System.out.println("manejadorAccionObtenesTodosLosClientes ");
    Cliente clientes[];
    try {
      // ** 1 Invocar al m�todo getTodosLosClientes del modelo
      // ** Asignar el valor retornado a clientes
      clientes = modelo.obtenerTodosLosClientes();
      // ** 2 Invocar al m�todo mostrarEnPantalla de la vista
      // ** con clientes como par�metro

      vista.mostrarEnPantalla(clientes);
    } catch (Exception e) {
      reportarExcepcion(e);
    }
  }
}