package ar.com.abc.ventas;

public interface IVistaClientes {
  // M�todos para registrar los oyentes de acciones del usuario
  // ---------------------------------------------------------------
  /**
   * Agregar requirentes a la lista de objetos para ser notificados de
   * las acciones del usuario ingresadas a trav�s de una interfaz como la IGU
   * Las acciones del usuario para el segmento del cliente son agregar,
   * borrar, actualizar, obtener uno y todos los clientes. Hay acciones
   * del usuario similares para los segmentos de cartera e inventario
   */
  void agregarOyenteAccionUsuario(IControladorClientes con)
      throws ExcepcionVentasAClientes;

  // M�todos de utilidad para mostrar en pantalla el resultado de una
  // acci�n del usuario
  // ---------------------------------------------------------------
  /**
   * Muestra por pantalla la p�gina especificada por el controlador
   */
  void mostrarEnPantalla(Object objAMostrar)
      throws ExcepcionVentasAClientes;

  // iteraci�n 1 - Segmento del Cliente -- M�todos para la vista
  // ---------------------------------------------------------------
  /**
   * M�todos de devoluci�n de llamada (callback) para manejar la
   * notificaci�n de los cambios de estado del cliente desde el modelo
   */
  void manejarCambioEnElModeloDelCliente(Cliente cliente)
      throws ExcepcionVentasAClientes;

  // Segmento Cartera - Se completar� el desarrollo en una iteraci�n futura
  // Agregar un m�todo para manejar las notificaciones de cambio de cartera
  // desde el modelo

  // Segmento Inventario - Se completar� el desarrollo en una iteraci�n futura
  // Agregar un m�todo para manejar las notificaciones de cambio de inventario
  // desde el modelo
}