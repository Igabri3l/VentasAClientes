package ar.com.abc.ventas;

public interface IModeloClientes {
  // M�todo de registro de oyente de cambio de estado para el modelo del
  // cliente
  // -----------------------------------------------------------------------------
  /**
   * Agregar un requirente a la lista de objetos que ser�n notificados cuando
   * un objeto (Cliente, Inventario, etc) alteren su estado en el modelo
   */
  public void agregarOyenteDelCambio(IVistaClientes iVistaClientes)
      throws ExcepcionVentasAClientes;

  // iteraci�n 1 M�todos del segmento Cliente para el modelo de VDL
  // M�todos del segmento cliente de cambio de estado
  // -----------------------------------------------------------------------------
  /**
   * Agregar el Cliente al modelo
   */
  public void agregarCliente(Cliente cliente)
      throws ExcepcionVentasAClientes;

  /**
   * Borrar el cliente del modelo
   */
  public void borrarCliente(Cliente cliente)
      throws ExcepcionVentasAClientes;

  /**
   * Actualizar el cliente en el modelo
   */
  public void actualizarCliente(Cliente cliente)
      throws ExcepcionVentasAClientes;

  // M�todos del segmento Cliente para consulta del estado
  /**
   * Dado un dni, retorna el cliente del modelo
   */
  public Cliente obtenerCliente(String dni)
      throws ExcepcionVentasAClientes;

  /**
   * Retorna todos los clientes en el modelo
   */
  public Cliente[] obtenerTodosLosClientes()
      throws ExcepcionVentasAClientes;

  // Segmento Cartera - Se completar� en una iteraci�n futura
  // Agregar m�todos de cambio de estado al segmento Cartera
  // Agregar m�todos de consulta de estado al segmento Cartera

  // Segmento Inventario - Se completar� en una iteraci�n futura
  // Agregar m�todos de cambio de estado al segmento Inventario
  // Agregar m�todos de consulta de estado al segmento Inventario
}
