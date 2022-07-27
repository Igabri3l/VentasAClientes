/*
 * ImplementacionModeloCliente.java
 *
 * Created on December 20, 2006, 7:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ar.com.abc.ventas.bdd;

import java.util.ArrayList;

import ar.com.abc.ventas.Cliente;
import ar.com.abc.ventas.ExcepcionVentasAClientes;
import ar.com.abc.ventas.IModeloClientes;
import ar.com.abc.ventas.IVistaClientes;

/**
 *
 * @author Marcelo Samia
 */
public class ImplementacionModeloCliente implements IModeloClientes {

  ArrayList<IVistaClientes> oyentesDeCambio = new ArrayList<IVistaClientes>(10);
  IClientesDAO iClientesDAO;

  /** Crea una instancia nueva de ImplementacionModeloCliente */
  public ImplementacionModeloCliente(IClientesDAO iClientesDAO) throws java.rmi.RemoteException {
    this.iClientesDAO = iClientesDAO;
  }

  // M�todo de registro de oyente de cambio de estado para el modelo del
  // cliente
  // -----------------------------------------------------------------------------
  /**
   * Agregar un requirente a la lista de objetos que ser�n notificados cuando
   * un objeto (Cliente, Inventario, etc) alteren su estado en el modelo
   */
  public void agregarOyenteDelCambio(IVistaClientes iVistaClientes)
      throws ExcepcionVentasAClientes {
    // ** 1 Agregar vistaClientes a oyentesDeCambio usando el m�todo add
    oyentesDeCambio.add(iVistaClientes);
  }

  /**
   * Este m�todo notifica a todos los oyentes de IVistaClientes
   * registrados que un objeto cliente ha cambiado.
   */
  void disparaEventoCambioModelo(Cliente cliente) {
    for (IVistaClientes v : oyentesDeCambio) {
      try {
        v.manejarCambioEnElModeloDelCliente(cliente);
      } catch (Exception e) {
        e.toString();
      }
    }
  }

  // Iteraci�n 1 M�todos del segmento de Cliente en el modelo
  // M�todos de cambio de estado del cliente
  // ----------------------------------------------------------
  /**
   * Agrega el Cliente al modelo
   */
  public void agregarCliente(Cliente cliente) throws ExcepcionVentasAClientes {
    try {
      // ** 1 Verificar que no existe ya el registro que se quiere agregar
      if (!iClientesDAO.existeDni(cliente.getDni())) {
        // ** 2 Usar la instancia de iClientesDAO para agregar al cliente
        // ** en la correspondiente tabla.
        iClientesDAO.crearCliente(cliente);
        // ** 3 Invocar la m�todo disparaEventoCambioModelo
        // ** con el cliente como par�metro.
        disparaEventoCambioModelo(cliente);
      } else {
        throw new ExcepcionVentasAClientes("Dni duplicado: " + cliente.getDni());
      }
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes(
          "ImplementacionModeloCliente.agregarCliente\n" + e);
    }
  }

  /**
   * Borra al cliente del modelo
   */
  public void borrarCliente(Cliente cliente) throws ExcepcionVentasAClientes {
    try {
      // ** 1 Verificar si existe el registro que se quiere
      // ** borrar de la base de datos
      if (iClientesDAO.existeDni(cliente.getDni())) {
        // ** 2 Usar la instancia de iClientesDAO para borrar al cliente
        // ** en las correspondientes tablas.
        iClientesDAO.borrarCliente(cliente);
        // ** 3 Usando el m�todo setNombre ingresa el string
        // ** "- cliente borrado -" en el campo nombre
        // ** del objeto cliente.
        cliente.setNombre("- cliente borrado -");
        // ** 4 Invoca al m�todo disparaEventoCambioModelo con cli como
        // par�metro
        disparaEventoCambioModelo(cliente);
      } else {
        throw new ExcepcionVentasAClientes("El registro con dni "
            + cliente.getDni() + " no se encontr�");
      }
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes(
          "ImplementacionModeloCliente.borrarCliente\n" + e);
    }
  }

  /**
   * Actualizar el cliente en el modelo
   */
  public void actualizarCliente(Cliente cliente) throws ExcepcionVentasAClientes {
    try {
      // ** 1 Verificar si existe el registro que se quiere
      // ** actualizar en la base de datos
      if (iClientesDAO.existeDni(cliente.getDni())) {
        // ** 2 Usar la instancia de iClientesDAO para borrar al cliente
        // ** en la correspondientes tabla.
        iClientesDAO.cambiarDatosDelCliente(cliente);
        // ** 3 Invoca al m�todo disparaEventoCambioModelo con cliente
        // como par�metro
        disparaEventoCambioModelo(cliente);
      } else {
        throw new ExcepcionVentasAClientes("El registro con dni "
            + cliente.getDni() + " no se encontr�.");
      }
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes(
          "ImplementacionModeloCliente.actualizarCliente\n" + e);
    }
  }

  // M�todos de consulta del estado del Cliente
  /**
   * Dado un dni, retorna el Cliente del modelo
   */
  public Cliente obtenerCliente(String dni) throws ExcepcionVentasAClientes {
    try {
      Cliente cliente = null;
      // ** 1 Utilizar la instancia en iClientesDAO para recuperar el registro
      // ** que retornar� null si no se encuentra ninguna entidad cliente
      // ** o el objeto al cliente si se encuentra una coincidencia.
      cliente = iClientesDAO.getCliente(dni);
      // La siguiente sentencia verifica si la consulta fue exitosa
      if (cliente == null) {
        // La consulta fall�
        throw new ExcepcionVentasAClientes("El registro con dni " + dni + " no se encontr�.");
      }
      // Retornar el cliente
      return cliente;
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes(
          "ImplementacionModeloCliente.getCliente\n" + e);
    }
  }

  /**
   * Retorna todos los clientes que hay en el modelo
   */
  public Cliente[] obtenerTodosLosClientes() throws ExcepcionVentasAClientes {
    Cliente[] todos = null;
    try {
      // ** 1 Utilice instancia iClientesDAO para llamar a los getTodosLosClientes()
      // m�todo y poblar el vector que se retornar�
      todos = iClientesDAO.obtenerTodosLosClientes();
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes(
          "ImplementacionModeloCliente.getTodosLosClientes\n" + e);
    }
    return todos;
  }

}