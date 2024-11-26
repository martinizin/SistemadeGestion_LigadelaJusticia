import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeroesGUI {
    private JPanel pGeneral;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JButton REGISTRARHEROEButton;
    private JButton MODIFICARHEROEButton;
    private JButton CALCULARDETALLESButton;
    private JTextArea textArea1;
    private JComboBox <Integer> comboBox1;;
    private JButton MOSTRARHEROESREGISTRADOSButton;
    private JButton LIMPIARFORMULARIOButton;
    private GestionHeroes gestion = new GestionHeroes();


    public HeroesGUI() {
        for (int i = 1; i <= 5; i++) {
            comboBox1.addItem(i);
        }

        REGISTRARHEROEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = textField1.getText();
                    String nombre = textField2.getText();
                    String superpoder = textField3.getText();
                    String mision = textField4.getText();
                    int nivelDificultad = (Integer) comboBox1.getSelectedItem();
                    double pagoMensual = Double.parseDouble(textField6.getText());

                Heroes heroe = new Heroes(id, nombre, superpoder, mision, nivelDificultad, pagoMensual);
                gestion.registrarHeroe(heroe);

                textArea1.setText("Héroe registrado exitosamente.\n");
            } catch (Exception ex) {
                textArea1.setText("Error al registrar el héroe: " + ex.getMessage());
                }
            }
        });
        MODIFICARHEROEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                if (id.isEmpty()) {
                    textArea1.setText("Por favor, ingrese el ID del héroe a modificar.\n");
                    return;
                }
                try {
                    double nuevoPago = Double.parseDouble(textField6.getText());
                    gestion.modificarHeroe(id, nuevoPago);
                    textArea1.setText("Pago mensual del héroe actualizado.\n");
                } catch (Exception ex) {
                    textArea1.setText("Error al modificar el héroe: " + ex.getMessage());
                }

            }
        });
        CALCULARDETALLESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                if (id.isEmpty()) {
                    textArea1.setText("Por favor, ingrese el ID del héroe para calcular detalles.\n");
                    return;
                }

                try {
                    String detalles = gestion.calcularAporteImpuestos(id);
                    textArea1.setText(detalles);
                } catch (Exception ex) {
                    textArea1.setText("Error al calcular los detalles: " + ex.getMessage());
                }
            }
        });
        MOSTRARHEROESREGISTRADOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder("Héroes Registrados:\n");
                for (Heroes heroe : gestion.listarHeroes()) {
                    sb.append(String.format("ID: %s, Nombre: %s, Superpoder: %s, Misión: %s, Nivel: %d, Pago: %.2f\n",
                            heroe.getId(), heroe.getNombre(), heroe.getSuperpoder(),
                            heroe.getMision(), heroe.getNivelDificultad(), heroe.getPagoMensual()));
                }
                textArea1.setText(sb.toString());
            }
        });

        LIMPIARFORMULARIOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(""); // ID
                textField2.setText(""); // Nombre
                textField3.setText(""); // Superpoder
                textField4.setText(""); // Misión
                comboBox1.setSelectedIndex(0); // Nivel de dificultad
                textField6.setText(""); // Pago mensual
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("HeroesGUI");
        frame.setContentPane(new HeroesGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
