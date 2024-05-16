package ATM;
import javax.swing.JOptionPane;

public class CajeroAutomatico {
    private static final double[] denominaciones = {1000, 500, 200, 100, 50, 20, 10, 5, 2, 1, 0.50};
    private static final int[] cantidades = {2, 5, 10, 20, 30, 40, 50, 100, 200, 300, 100};
    private static double TOTAL_DINERO = 12550.0;

    public static void main(String[] args) {
        JOptionPane.showInputDialog(null, "Bienvenido al cajero automático\nSaldo disponible en el cajero: $" + TOTAL_DINERO);

        int opcion = 0;
        do {
            String cantidadRetirarStr = JOptionPane.showInputDialog("Ingrese la cantidad que desea retirar (o ingrese 0 para salir):");
            double cantidadRetirar;

            try {
                cantidadRetirar = Double.parseDouble(cantidadRetirarStr);
            } catch (NumberFormatException e) {
                JOptionPane.showInputDialog(null, "Por favor, ingrese un número válido.");
                continue;
            }

            if (cantidadRetirar == 0) {
                JOptionPane.showInputDialog(null, "Gracias por utilizar nuestro cajero automático. ¡Hasta luego!");
                break;
            }

            if (cantidadRetirar > TOTAL_DINERO) {
                JOptionPane.showInputDialog(null, "Lo sentimos, no hay suficiente saldo en el cajero para esta transacción.");
                continue;
            }

            if (cantidadRetirar % 0.50 != 0) {
                JOptionPane.showInputDialog(null, "Lo sentimos, solo podemos dispensar cantidades en múltiplos de 0.50.");
                continue;
            }

            if (cantidadRetirar > 0) {
                dispensarDinero(cantidadRetirar);
                TOTAL_DINERO -= cantidadRetirar;
                JOptionPane.showInputDialog(null, "Retire su dinero. Gracias por utilizar nuestro servicio.");
            } else {
                JOptionPane.showInputDialog(null, "Cantidad inválida. Por favor, ingrese un monto válido.");
            }

            opcion = JOptionPane.showConfirmDialog(null, "¿Desea realizar otra transacción?", "Confirmar", JOptionPane.YES_NO_OPTION);
        } while (opcion == JOptionPane.YES_OPTION);
    }

    private static void dispensarDinero(double cantidad) {
        StringBuilder mensaje = new StringBuilder("Dispensando la cantidad de $" + cantidad + " en los siguientes billetes y monedas:\n");
        for (int i = 0; i < denominaciones.length; i++) {
            int cantidadDenominacion = (int) (cantidad / denominaciones[i]);
            int billetes = Math.min(cantidadDenominacion, cantidades[i]);
            if (billetes > 0) {
                mensaje.append(billetes).append(" ");
                if (denominaciones[i] >= 20) {
                    mensaje.append("billete(s) de $");
                } else {
                    mensaje.append("moneda(s) de $");
                }
                mensaje.append(denominaciones[i]).append("\n");
                cantidad -= billetes * denominaciones[i];
            }
        }
        JOptionPane.showInputDialog(null, mensaje.toString());
    }
}
