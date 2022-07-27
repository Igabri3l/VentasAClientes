package ar.com.abc.ventas;

public interface IControladorClientes {
  // M�todos de devoluci�n de llamado (callback) para las acciones del usuario
  // ---------------------------------------------------------------
  /**
   * Obtener el Cliente por el m�todo manejador de la acci�n del
   * usuario, llamado por la vista en ImplementacionDeLaVista en
   * respuesta al clic del bot�n para obtener el cliente en la interfaz
   * gr�fica o su equivalente en la interfaz del usuario
   * Acci�n - indicar el tipo presentaci�n en pantalla para el cliente
   * en la interfaz gr�fica a trav�s del m�todo mostrarEnPantalla de la vista
   */
  void manejadorAccionObtenerCliente(String id);

  // ---------------------------------------------------------------
  /**
   * Agregar el m�todo manejador de la acci�n del usuario para un nuevo
   * Cliente llamado por ImplementacionDeLaVista en respuesta al clic sobre
   * el bot�n para agregar un cliente en la interfaz gr�fica o su equivalente
   * en la interfaz del usuario
   * Acci�n - agregar el (nuevo) cliente del modelo
   */
  void manejadorAccionAgregarCliente(Cliente c);

  // ---------------------------------------------------------------
  /**
   * Agregar el m�todo manejador de la acci�n del usuario para borrar un Cliente
   * llamado por ImplementacionDeLaVista en respuesta al clic sobre el bot�n para
   * borrar un cliente en la interfaz gr�fica o su equivalente en la interfaz del
   * usuario
   * Acci�n - borrar el cliente del modelo
   */
  void manejadorAccionBorrarCliente(Cliente c);

  // ---------------------------------------------------------------
  /**
   * Agregar el m�todo manejador de la acci�n del usuario para actualizar un
   * Cliente
   * llamado por ImplementacionDeLaVista en respuesta al clic sobre el bot�n para
   * actualizar cliente en la interfaz gr�fica o su equivalente en la
   * interfaz del usuario
   * Acci�n - actualizar el cliente del modelo
   */
  void manejadorAccionAcualizarCliente(Cliente c);

  // ---------------------------------------------------------------
  /**
   * Agregar el m�todo manejador de la acci�n del usuario para Obtener todos los
   * Clientes llamado por la vista de ImplVistaVentas en respuesta al clic del
   * bot�n
   * para obtener todos los clientes en la interfaz gr�fica o su equivalente en la
   * interfaz del usuario
   * Acci�n - indicar el tipo presentaci�n en pantalla para el cliente
   * en la interfaz gr�fica a trav�s del m�todo mostrarEnPantalla de la vista
   */
  void manejadorAccionObtenesTodosLosClientes();

  // Segmento Cartera - Se completar� el desarrollo en una iteraci�n futura
  // Agregar un m�todo para manejar las notificaciones de las acciones del usuario
  // relacionadas con la cartera desde la vista

  // Segmento Inventario - Se completar� el desarrollo en una iteraci�n futura
  // Agregar un m�todo para manejar las notificaciones de las acciones del usuario
  // relacionadas con el inventario desde la vista

}