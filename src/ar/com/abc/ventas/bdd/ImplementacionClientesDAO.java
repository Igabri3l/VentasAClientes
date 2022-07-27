package ar.com.abc.ventas.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ar.com.abc.ventas.Cliente;
import ar.com.abc.ventas.ExcepcionVentasAClientes;

/**
 *
 * @author Marcelo Samia
 */
public class ImplementacionClientesDAO implements IClientesDAO {

  private static Connection conexionBdd;

  /** Crea una instancia nueva de ImplementacionClientesDAO */
  public ImplementacionClientesDAO() throws Exception {
    this("localhost", "root", "root");
  }

  public ImplementacionClientesDAO(String nomServidorBdd) throws Exception {
    this(nomServidorBdd, "root", "root");
  }

  public ImplementacionClientesDAO(String nomServidorBdd, String usuario, String clave) throws Exception {
    String nombreBaseDeDatos = "VentasDeLibros";
    String url;
    try {

      // ** 1 Registrar el driver JDBC
      // ** El nombre de la clase del driver de MySQL Server es
      // ** "com.mysql.jdbc.Driver"
      Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();

      // ** 2 Construir el string para la url de acceso JDBC.
      // ** De esta manera se flexibiliza el acceso a la BDD
      // ** a trav�s de los par�metros del cnstructor
      url = "jdbc:mysql://" + nomServidorBdd + "/" + nombreBaseDeDatos + "?" + "user="
          + usuario + "&password=" + clave;

      // ** 3 Asignar al atributo conexionBdd un objeto
      // ** del tipo Connectioninvocando al m�todo
      // ** getConnection de la clase DriverManager
      conexionBdd = DriverManager.getConnection(url);
    } catch (Exception e) {
      e.toString();
      throw e;
    }
  }

  /**
   * Este es un m�todo de ayuda utilizado por muchos m�todos.
   * Devuelve un objeto Connection.
   * En un entorno con un �nico thread puede devolver un solo
   * objeto de conexi�n almacenada en el atributo conexionBdd.
   * En un entorno de m�ltiples subprocesos cada thread requerir�
   * su propia conexi�n. Una futura subclase de esta clase puede
   * reemplazar este m�todo y obtener el objeto de conexi�n
   * a partir de un reservorio (pool) de conexiones.
   */
  protected Connection getConexion() throws SQLException {
    // retorna el atributo conexionBdd
    return conexionBdd;
  }

  /**
   * Este es un m�todo de ayuda utilizado por muchos m�todos.
   * Retorna verdadero si el DNI (por ejemplo, el dni del cliente)
   * se encuentra en la tabla Cliente
   */
  public boolean existeDni(String dni) {
    try {
      Connection con = getConexion();
      Statement sentencia = con.createStatement();
      ResultSet resultado = sentencia.executeQuery("SELECT dni FROM Cliente WHERE dni=" + "'" + dni + "'");
      return resultado.next();
    } catch (SQLException e) {
      System.err.println("En existeDni, la consulta por " + dni + " fall�");
      e.toString();
      return false;
    }
  }

  /**
   * Agrega el Cliente al modelo
   */
  public void crearCliente(Cliente cliente)
      throws ExcepcionVentasAClientes {
    try {
      String dni = null;
      String nombre = null;
      String direccion = null;
      Connection con;
      Statement sentencia;
      int contadorFilas;
      String requerimiento;
      // ** 1 Asignar el DNI con el del objeto del tipo Cliente
      // ** a la variable local
      dni = cliente.getDni();
      // ** 2 Asignar nombre con el del objeto del tipo Cliente
      // ** a la variable local
      nombre = cliente.getNombre();
      // ** 3 Asigna direccion con el del objeto del tipo Cliente
      // ** a la variable local
      direccion = cliente.getDireccion();

      // ** 4 Asignar la conexi�n a con invocando al m�todo getConexion()
      // ** Por ejemplo, ver un paso similar en el m�todo existeDni
      con = getConexion();
      // ** 5 Asignar sentencia invocando al m�todo createStatement de con
      // ** Como ejemplo, ver un paso similar en el m�todo existeDNI
      sentencia = con.createStatement();
      // La siguiente sentencia crea el string de SQL requerido para el INSERT
      requerimiento = "INSERT INTO Cliente (DNI, NomCliente, Direccion) VALUES ("
          + "'" + dni + "'" + ","
          + "'" + nombre + "'" + ","
          + "'" + direccion + "'" + ")";
      // ** 6 Asignar a contadorFilas con el valor retornado por el
      // ** m�todo executeUpdate(requerimiento) en el objeto
      // ** al que apunta sentencia
      contadorFilas = sentencia.executeUpdate(requerimiento);
      System.out.println("Filas insertadas en Cliente: " + contadorFilas);
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes("ImplementacionClientesDAO.crearCliente\n" + e);
    }
  }

  /**
   * Borra al cliente del modelo
   */
  public void borrarCliente(Cliente cliente)
      throws ExcepcionVentasAClientes {
    try {
      String dni = null;
      Connection con;
      Statement sentencia;
      int contadorFilas;
      String requerimiento;
      // ** 1 Asignar el dni con el del objeto del tipo Cliente
      // ** a la variable local
      dni = cliente.getDni();

      // ** 2 Asignar la conexi�n a con invocando al m�todo getConexion()
      // ** Como ejemplo, ver un paso similar en el m�todo existeDni
      con = getConexion();
      // ** 3 Asignar a sentencia invocando al m�todo createStatement de con
      // ** Como ejemplo, ver un paso similar en el m�todo existeDni
      sentencia = con.createStatement();

      // La siguiente l�nea crea el string SQL DELETE requerido
      // para borrar filas de la tabla libros
      requerimiento = "DELETE FROM Libros WHERE dni=" + "'" + dni + "'";
      // ** 4 Asignar contadorFilas con el valor retornado por el
      // ** m�todo executeUpdate(requerimiento) en el objeto sentencia
      contadorFilas = sentencia.executeUpdate(requerimiento);
      System.out.println("Filas borradas en Libros: " + contadorFilas);

      // La siguiente l�nea crea el string SQL DELETE requerido
      // para borrar filas de la tabla Cliente
      requerimiento = "DELETE FROM Cliente WHERE dni=" + "'" + dni + "'";
      // ** 5 Asignar contadorFilas con el valor retornado por el
      // ** m�todo executeUpdate(requerimiento) en el objeto sentencia
      contadorFilas = sentencia.executeUpdate(requerimiento);
      System.out.println("Filas borradas en Cliente: " + contadorFilas);
      // ** 6 Usando el m�todo setNombre configura el string
      // ** "- cliente borrado -" en el campo nombre
      // ** del objeto del tipo Cliente.
      cliente.setNombre("- cliente borrado -");
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes("ImplementacionClientesDAO.borrarCliente\n" + e);
    }
  }

  /**
   * Actualizar el cliente en el modelo
   */
  public void cambiarDatosDelCliente(Cliente cliente)
      throws ExcepcionVentasAClientes {
    try {
      String dni = null;
      String nombre = null;
      String direccion = null;
      Connection con;
      Statement sentencia;
      String requerimiento;
      int contadorFilas;
      // ** 1 Asignar el dni con el del objeto del tipo Cliente
      dni = cliente.getDni();
      // ** 2 Asignar nombre con el del objeto del tipo Cliente
      nombre = cliente.getNombre();
      // ** 3 Asignar direccion con el del objeto del tipo Cliente
      direccion = cliente.getDireccion();
      // ** 4 Asignar la conexi�n a con invocando al m�todo getConexion()
      // ** Como ejemplo, ver un paso similar en el m�todo existeDni
      con = getConexion();
      // ** 5 Asignar a sentencia invocando al m�todo createStatement de con
      // ** Como ejemplo, ver un paso similar en el m�todo existeDni
      sentencia = con.createStatement();
      // La siguiente l�nea crea el string SQL UPDATE requerido
      requerimiento = "UPDATE Cliente SET "
          + " NomCliente =" + "'" + nombre + "'" + ","
          + " Direccion =" + "'" + direccion + "'"
          + " WHERE dni=" + "'" + dni + "'";
      // ** 6 Asignar contadorFila con el valor retornado por el
      // ** m�todo executeUpdate(requerimiento) en el objeto sentencia
      contadorFilas = sentencia.executeUpdate(requerimiento);
      System.out.println("Filas actualizadas en Cliente: " + contadorFilas);
    } catch (Exception e) {
      e.toString();
      throw new ExcepcionVentasAClientes("ImplementacionClientesDAO.cambiarDatosDelCliente\n" + e);
    }
  }

  // Segmento de los m�todos de consulta del estado del Cliente
  /**
   * Dado un dni, retorna el Cliente del modelo
   */
  public Cliente getCliente(String dni)
      throws ExcepcionVentasAClientes {
    try {
      String nombre;
      String direccion;
      Connection con;
      Statement sentencia;
      ResultSet resultado = null;
      String requerimiento;
      Cliente cliente = null;
      // ** 1 Asignar la conexi�n a con invocando al m�todo getConexion()
      // ** Como ejemplo, ver un paso similar en el m�todo existeDNI
      con = getConexion();
      // ** 2 Asignar a sentencia invocando al m�todo createStatement de con
      // ** Como ejemplo, ver un paso similar en el m�todo existeDNI
      sentencia = con.createStatement();
      // La siguiente l�nea crea el string SQL SELECT requerido
      requerimiento = "SELECT NomCliente, Direccion FROM Cliente"
          + " WHERE dni=" + "'" + dni + "'";
      // ** 3 Asignar resultado con el valor retornado por el
      // ** m�todo executeQuery(requerimiento) en el objeto sentencia
      resultado = sentencia.executeQuery(requerimiento);

      // La siguiente sentencia revisa si la consulta fue exitosa
      if (resultado.next()) {
        // ** 4 Asignar nombre con el valor retornado por el
        // ** m�todo getString(1), lo que implica el string de la
        // ** primera columna en el objeto resultado
        nombre = resultado.getString(1);
        // ** 5 Asignar direccion con el valor retornado por el
        // ** m�todo getString(2) , lo que implica el string de la
        // ** segunda columna en el objeto resultado
        direccion = resultado.getString(2);
        // ** 6 Crea un objeto Cliente usando (dni, nombre, direccion)
        // ** y asigna este objeto a la referencia cliente del tipo Cliente
        cliente = new Cliente(dni, nombre, direccion);
      } else {
        // si la consulta falla
        throw new ExcepcionVentasAClientes("No se encontr� el registro con DNI " + dni);
      }
      // retorna el objeto del tipo cliente
      return cliente;
    } catch (SQLException e) {
      e.toString();
      throw new ExcepcionVentasAClientes("ImplementacionClientesDAO.getCliente\n" + e);
    }
  }

  /**
   * Retorna todos los clientes que hay en el modelo
   */
  public Cliente[] obtenerTodosLosClientes()
      throws ExcepcionVentasAClientes {
    String dni;
    String nombre;
    String direccion;
    Connection con;
    Statement sentencia;
    ResultSet resultado = null;
    String requerimiento;
    Cliente cliente = null;
    Cliente[] todos;
    Cliente[] temp = new Cliente[1];
    ArrayList<Cliente> listaClientes = new ArrayList<Cliente>(1);
    try {
      // ** 1 Asignar con invocando al m�todo getConexion()
      // ** Como ejemplo, ver un paso similar en el m�todo existeDni
      con = getConexion();
      // ** 2 Asignar sentencia invocando al m�todo createStatement de con
      // ** Como ejemplo, ver un paso similar en el m�todo existeDni
      sentencia = con.createStatement();
      // La siguiente l�nea crea el string SQL SELECT requerido
      requerimiento = "SELECT * FROM Cliente";
      // ** 3 Asignar resultado con el valor retornado por el
      // ** m�todo executeQuery(requerimiento) en el objeto sentencia
      resultado = sentencia.executeQuery(requerimiento);
      while (resultado.next()) {
        // ** 4 Asignar dni con el valor retornado por el
        // ** m�todo getString(1), lo que implica el string de la
        // ** primera columna en el objeto resultado
        dni = resultado.getString(1);
        // ** 5 Asignar nombre con el valor retornado por el
        // ** m�todo getString(2), lo que implica el string de la
        // ** segunda columna en el objeto resultado
        nombre = resultado.getString(2);
        // ** 6 Asignar direccion con el valor retornado por el
        // ** m�todo getString(3), lo que implica el string de la
        // ** tercera columna en el objeto resultado
        direccion = resultado.getString(3);
        // ** 7 Crea un objeto Cliente usando ((dni, nombre, direccion)
        // ** y asigna este objeto a cliente
        cliente = new Cliente(dni, nombre, direccion);
        // ** 8 Utiliza el m�todo add del ArrayList para agregar cliente a la lista
        listaClientes.add(cliente);
      }
      if (listaClientes.size() > 0) {
        todos = listaClientes.toArray(temp);
      } else {
        todos = null;
      }
      return todos;
    } catch (SQLException e) {
      todos = null;
      e.toString();
      throw new ExcepcionVentasAClientes("ImplementacionClientesDAO.getTodosLosClientes\n" + e);
    }
  }
}