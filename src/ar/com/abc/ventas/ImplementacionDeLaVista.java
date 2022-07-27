package ar.com.abc.ventas;

import java.io.Serializable;

import ar.com.abc.ventas.igu.IguVentas;

public class ImplementacionDeLaVista implements IVistaClientes, Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 4776262097289492514L;
  private transient IguVentas igu;
  private IModeloClientes modelo;

  public ImplementacionDeLaVista()
      throws java.rmi.RemoteException {
    System.out.println("Creando ImplementacionDeLaVista");
  }

  public void setIgu(IguVentas igu) {
    // ** 3 Asignar el atributo igu a esta instancia de IguVdl
    this.igu = igu;
  }

  public void setModelo(IModeloClientes modelo) {
    this.modelo = modelo;
    try {
      // ** 1 Asignar el atributo modelo a esta instancia de IModeloClientes
      this.modelo = modelo;
      // ** 2 Invocar el m�todo agregarOyenteDelCambio con this
      // para pasar esta instancia de ImplVistaVentas
      // como par�metro
      this.modelo.agregarOyenteDelCambio(this);
    } catch (Exception e) {
      System.out.println("Error en el constructor de ImplementacionDeLaVista  " + e);
    }
  }

  // M�todos para registrar los observadores de acciones del usuario
  // ---------------------------------------------------------------
  /**
   * Agregar requirentes a la lista de objetos a ser notificados de
   * las acciones del usuario ingresadas a trav�s de una interfaz como la IGU
   * Las acciones del usuario para el segmento del cliente son agregar,
   * borrar, actualizar, obtener y obtener todos los clientes. Hay acciones
   * del usuario similares para los segmentos de cartera e inventario
   */
  public void agregarOyenteAccionUsuario(IControladorClientes con)
      throws ExcepcionVentasAClientes {
    System.out.println("ImplementacionDeLaVista.agregarOyenteAccionUsuario " + con);
    // ** 1 Agregar el controlador con utilizando el m�todo add
    // Pista: llamar al m�todo agregarControlador de la IGU
    igu.agregarControlador(con);
  }

  // M�todos de utilidad para mostrar en pantalla un requerimiento de
  // selecci�n del vendedor
  // ---------------------------------------------------------------
  /**
   * Muestra por pantalla la p�gina especificada por el controlador
   */
  public void mostrarEnPantalla(Object objAMostrar) throws ExcepcionVentasAClientes {
    System.out.println("ImplementacionDeLaVista.mostrarEnPantalla " + objAMostrar);
    // ** 1 Reenviar el par�metro recibido a la IGU usando el m�todo
    // mostrarEnLaPantallaElObjeto
    igu.mostrarEnLaPantallaElObjeto(objAMostrar);
  }

  // iteracion 1 -- M�todos del segmento del Cliente para la vista
  /*
   * ---------------------------------------------------------------
   * M�todos de retorno de llamada (callback) para manejar la notificaci�n de los
   * cambios
   * de estado del cliente para el modelo
   */
  public void manejarCambioEnElModeloDelCliente(Cliente cli)
      throws ExcepcionVentasAClientes {
    System.out.println("ImplementacionDeLaVista.manejarCambioEnElModeloDelCliente " + cli);
    // ** 1 Reenviar el par�metro recibido a la IGU usando el m�todo
    // mostrarEnLaPantallaElObjeto
    igu.mostrarEnLaPantallaElObjeto(cli);
  }
}