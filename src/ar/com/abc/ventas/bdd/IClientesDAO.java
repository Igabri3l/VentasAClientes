package ar.com.abc.ventas.bdd;

import ar.com.abc.ventas.Cliente;
import ar.com.abc.ventas.ExcepcionVentasAClientes;

public interface IClientesDAO {
  void crearCliente(Cliente cliente) throws ExcepcionVentasAClientes;

  void borrarCliente(Cliente cliente) throws ExcepcionVentasAClientes;

  void cambiarDatosDelCliente(Cliente cliente) throws ExcepcionVentasAClientes;

  Cliente getCliente(String dni) throws ExcepcionVentasAClientes;

  Cliente[] obtenerTodosLosClientes() throws ExcepcionVentasAClientes;

  public boolean existeDni(String dni);
}
