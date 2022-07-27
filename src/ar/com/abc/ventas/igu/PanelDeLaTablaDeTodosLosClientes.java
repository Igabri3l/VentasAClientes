package ar.com.abc.ventas.igu;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import ar.com.abc.ventas.Cliente;
import ar.com.abc.ventas.IControladorClientes;
import ar.com.abc.ventas.IModeloClientes;

public class PanelDeLaTablaDeTodosLosClientes extends
    javax.swing.JPanel implements IPanelesDeVentas {

  /**
   * 
   */
  private static final long serialVersionUID = 8281531357635502317L;
  IModeloClientes modelo;
  IControladorClientes controlador;
  String[] cabecerasTabla = { "DNI del Cliente", "Nombre", "Direcci�n" };
  private javax.swing.table.DefaultTableModel modeloDeLaTabla;

  public PanelDeLaTablaDeTodosLosClientes() {
    initComponents();
  }

  public void refrescar() {
    try {
      Cliente[] clientes = modelo.obtenerTodosLosClientes();
      refrescarPanelDeTodosLosClientes(clientes);
    } catch (Exception e) {
      if (modelo == null) {
      } else {
        System.out.println(e.toString());
      }
    }
  }

  public void registrarControlador(IControladorClientes controlador) {
    this.controlador = controlador;
  }

  public void registrarModelo(IModeloClientes modelo) {
    this.modelo = modelo;
    modeloDeLaTabla = (DefaultTableModel) jtblTodosLosClientes.getModel();
    refrescar();
  }

  public void mostrar(Object obj) {
    if (obj instanceof Cliente)
      refrescar();
  }

  public JPanel getPanel() {
    return this;
  }

  public void refrescarPanelDeTodosLosClientes(Cliente[] clientes) {
    String datosNuevos[][];
    // ** 1 Crear un vector de 2 dimensiones de cadenas con un n�mero de filas
    // ** igual a clientes.length y el n�mero de las columnas configurada en 3, i
    // ** y asignar a datosNuevos.
    datosNuevos = new String[clientes.length][3];
    // ** 2 Escribir un ciclo for para rellenar la matriz datosNuevos
    // ** con el dni del cliente, nombre y direcci�n
    // ** que se obtiene de clientes
    for (int i = 0; i < clientes.length; i++) {
      datosNuevos[i][0] = clientes[i].getDni();
      datosNuevos[i][1] = clientes[i].getNombre();
      datosNuevos[i][2] = clientes[i].getDireccion();
    }
    // ** 3 Invocar el m�todo setDataVector en el modeloDeLaTabla
    // pas�ndole los vectores datosNuevos y cabecerasTabla.
    modeloDeLaTabla.setDataVector(datosNuevos, cabecerasTabla);
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code
  // ">//GEN-BEGIN:initComponents
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jtblTodosLosClientes = new javax.swing.JTable();

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Tabla De Todos Los Clientes");

    jtblTodosLosClientes.setModel(new DefaultTableModel(
        new Object[][] {
            { null, null, null },
            { null, null, null },
            { null, null, null },
            { null, null, null },
        },
        new String[] {
            "DNI Cliente", "Nombre", "Direcci\u00F3n"
        }));
    jtblTodosLosClientes.setTableHeader(null);
    jScrollPane1.setViewportView(jtblTodosLosClientes);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    layout.setHorizontalGroup(
        layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE)));
    this.setLayout(layout);
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jtblTodosLosClientes;
}