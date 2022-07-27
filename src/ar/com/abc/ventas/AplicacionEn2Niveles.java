package ar.com.abc.ventas;

import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.abc.ventas.igu.IguVentas;

public class AplicacionEn2Niveles {

  public static void main(String args[]) {
    ApplicationContext contexto = new ClassPathXmlApplicationContext("contexto.xml");

    final IguVentas igu1 = (IguVentas) contexto.getBean("iguVentas1");

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        igu1.setVisible(true);
      }
    });

    final IguVentas igu2 = (IguVentas) contexto.getBean("iguVentas2");

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        igu2.setVisible(true);
      }
    });

    ((ClassPathXmlApplicationContext) contexto).close();
  }
}
