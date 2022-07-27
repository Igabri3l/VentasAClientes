package ar.com.abc.ventas.igu;

import javax.swing.JPanel;

import ar.com.abc.ventas.IControladorClientes;
import ar.com.abc.ventas.IModeloClientes;

public interface IPanelesDeVentas {
  void registrarControlador(IControladorClientes controlador);

  void registrarModelo(IModeloClientes modelo);

  void mostrar(Object obj);

  void refrescar();

  JPanel getPanel();
}
