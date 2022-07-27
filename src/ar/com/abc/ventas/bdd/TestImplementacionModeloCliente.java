package ar.com.abc.ventas.bdd;

import ar.com.abc.ventas.Cliente;
import ar.com.abc.ventas.IModeloClientes;

public class TestImplementacionModeloCliente {
  public static void main(String args[]) {
    System.out.println("TestImplementacionModeloCliente.main: verificando TestImplementacionModeloCliente");

    IModeloClientes modelo = null;
    IClientesDAO bdd = null;
    Cliente cliente = null;

    try {
      bdd = new ImplementacionClientesDAO();
      modelo = new ImplementacionModeloCliente(bdd);
    } catch (Exception e) {
      System.out.println(e);
    }

    try {
      // Verificando la recuperaci�n de todos los clientes
      System.out.println("TestImplementacionModeloCliente.main: recuperando de todos los clientes");
      Cliente[] todosLosClientes = modelo.obtenerTodosLosClientes();
      System.out.println("TestImplementacionModeloCliente.main: imprimiendo todos los clientes");
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
      System.out.println("TestImplementacionModeloCliente.main: recuperando un cliente v�lido");
      cliente = modelo.obtenerCliente("16828325");
      System.out.println("TestImplementacionModeloCliente.main: " + cliente);

    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {
      // Verificando obtener un cliente inv�lido
      System.out.println("TestImplementacionModeloCliente.main: recuperando un cliente inv�lido BASURA");
      cliente = modelo.obtenerCliente("BASURA");
      System.out.println(cliente);
    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {
      // Verificando actualizar un cliente v�lido
      System.out.println("TestImplementacionModeloCliente.main: actualizando un cliente que existe ");
      cliente = modelo.obtenerCliente("16828325");
      cliente.setDireccion("Balcarce 50, 2� piso");

      modelo.actualizarCliente(cliente);

      System.out.println("TestImplementacionModeloCliente.main: recuperando al cliente modificado");
      cliente = modelo.obtenerCliente("16828325");
      System.out.println(cliente);

      System.out.println("TestImplementacionModeloCliente.main: actualizando el cliente al valor original");
      cliente = modelo.obtenerCliente("16828325");
      cliente.setDireccion("Av. San Mart�n 3333, Ciudad Aut�noma de Buenos Aires, BA");

      modelo.actualizarCliente(cliente);

      System.out.println("TestImplementacionModeloCliente.main: recuperando al cliente modificado");
      cliente = modelo.obtenerCliente("16828325");
      System.out.println(cliente);

    } catch (Exception e) {
      System.out.println(e);
    }

    System.out.println();
    System.out.println();

    try {
      // Verificando la inserci�n de un nuevo cliente v�lido
      System.out.println("TestImplementacionModeloCliente.main:  insertando un nuevo cliente");
      cliente = new Cliente("16828326", "Otro Cliente", "Alsina 1111");

      modelo.agregarCliente(cliente);
    } catch (Exception e) {
      System.out.println("TestImplementacionModeloCliente.main: excepci�n");
      System.out.println(e);
      System.out
          .println("TestImplementacionModeloCliente.main: la inserci�n falla si el cliente ya se encuentra en la BDD");
    }

    System.out.println();
    System.out.println();

    try {
      // Verificando el borrado del nuevo cliente insertado anteriormente
      System.out.println("TestImplementacionModeloCliente.main:  borrando el nuevo cliente");
      cliente = new Cliente("16828326", "Otro Cliente", "Alsina 1111");

      modelo.borrarCliente(cliente);
    } catch (Exception e) {
      System.out.println("TestImplementacionModeloCliente.main: excepci�n");
      System.out.println(e);
    }
  }
}