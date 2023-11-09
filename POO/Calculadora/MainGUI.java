import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JTextField textField;
    private JLabel resultadoTextLabel;
    private JLabel resultadoValueLabel;

    public MainGUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel expresionLabel = new JLabel("Expresión:");
        expresionLabel.setHorizontalAlignment(JLabel.CENTER);
        add(expresionLabel);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, textField.getPreferredSize().height));
        add(textField);

        JButton calcularButton = new JButton("Calcular");
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Expresion expresion = new Expresion();
                try {
                    expresion.reconocer(textField.getText());
                    double resultado = expresion.evaluar();

                    String resultadoTexto;
                    // Si el resultado es un número entero, mostrarlo sin decimales
                    if (resultado % 1 == 0) {
                        resultadoTexto = String.format("%.0f", resultado);
                    } else { // Si no, mostrar los decimales
                        resultadoTexto = String.valueOf(resultado);
                    }
                    resultadoValueLabel.setText(resultadoTexto);
                } catch (InvalidCharException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //excepcion de reconocer
                } catch (EvaluationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //excepcion de evaluar
                }
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {//para habilitar/deshabilitar el boton de calcular segun si hay texto en el campo de texto
            private void updateButtonState() {
                calcularButton.setEnabled(!textField.getText().isEmpty());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }
        });

        // Deshabilitar el botón de calcular al inicio si el campo de texto está vacío
        calcularButton.setEnabled(!textField.getText().isEmpty());

        add(calcularButton);

        JPanel resultadoPanel = new JPanel(new FlowLayout());
        resultadoTextLabel = new JLabel("Resultado: ");
        resultadoPanel.add(resultadoTextLabel);

        resultadoValueLabel = new JLabel("");
        resultadoValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultadoValueLabel.setForeground(Color.BLUE);
        resultadoPanel.add(resultadoValueLabel);

        add(resultadoPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //estilo de diseño Nimbus
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new MainGUI().setVisible(true);
            }
        });
    }
}