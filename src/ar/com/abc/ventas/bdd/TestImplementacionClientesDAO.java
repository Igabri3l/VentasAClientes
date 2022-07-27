package ar.com.abc.ventas.bdd;

import ar.com.abc.ventas.Cliente;

public class TestImplementacionClientesDAO {
  public static void main(String args[]) {
    System.out.println("TestImplementacionClientesDAO.main: verificando ImplementacionClientesDAO");

    ImplementacionClientesDAO bdd = null;
    Cliente cliente = null;

    try {
      bdd = new ImplementacionClientesDAO();
    } catch (Exception e) {
      System.out.println(e);
    }

    try {
      // Verificando la recuperaci�n de todos los clientes
      System.out.println("TestImplementacionClientesDAO.main: recuperando de todos los clientes");
      Cliente[] todosLosClientes = bdd.obtenerTodosLosClientes();
      System.out.println("TestImplementacionClientesDAO.main: imprimiendo todos los clientes");
      for (int i = 0; i < todosLosClientes.length; i++) {
        System.out.println(todosLosClientes[i]);
      }

    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {
      // Verificando obtener un cliente v�lido
      System.out.println("TestImplementacionClientesDAO.main: recuperando un cliente v�lido");
      cliente = bdd.getCliente("16828325");
      System.out.println("TestImplementacionClientesDAO.main: " + cliente);

    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {
      // Verificando obtener un cliente inv�lido
      System.out.println("TestImplementacionClientesDAO.main: recuperando un cliente inv�lido BASURA");
      cliente = bdd.getCliente("BASURA");
      System.out.println(cliente);
    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {

      // Verificando actualizar un cliente v�lido
      System.out.println("TestImplementacionClientesDAO.main: actualizando un cliente que existe ");
      cliente = bdd.getCliente("16828325");
      cliente.setDireccion("Balcarce 50, 2� piso");

      bdd.cambiarDatosDelCliente(cliente);
      // Verificando obtener el cliente modificado
      System.out.println("TestImplementacionClientesDAO.main: recuperando el cliente modificado");
      cliente = bdd.getCliente("16828325");
      System.out.println("TestImplementacionClientesDAO.main: " + cliente);

      System.out.println("TestImplementacionClientesDAO.main: actualizando el cliente al valor original ");
      cliente = bdd.getCliente("16828325");
      cliente.setDireccion("Av. San Mart�n 3333, Ciudad Aut�noma de Buenos Aires, BA");

      bdd.cambiarDatosDelCliente(cliente);

    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {

      // Verificando la inserci�n de un nuevo cliente v�lido
      System.out.println("TestImplementacionClientesDAO.main:  insertando un nuevo cliente");
      cliente = new Cliente("16828326", "Otro Cliente", "Alsina 1111");

      bdd.crearCliente(cliente);

    } catch (Exception e) {
      System.out.println("TestImplementacionClientesDAO.main:  excepci�n");
      System.out.println(e);
      System.out
          .println("TestImplementacionClientesDAO.main:  la inserci�n falla si el cliente ya se encuentra en la BDD");
    }

    System.out.println();
    System.out.println();

    try {

      // Verificando el borrado del nuevo cliente insertado anteriormente
      System.out.println("TestImplementacionClientesDAO.main:  borrando el nuevo cliente");
      cliente = new Cliente("16828326", "Otro Cliente", "Alsina 1111");

      bdd.borrarCliente(cliente);

    } catch (Exception e) {
      System.out.println("TestImplementacionClientesDAO.main:  excepci�n");
      System.out.println(e);
    }
  }
}