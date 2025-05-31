import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraIMC extends JFrame {

    private JTextField campoPeso;
    private JTextField campoAltura;
    private JLabel resultadoLabel;

    public CalculadoraIMC() {
        setTitle("Calculadora de IMC");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Peso (kg):"));
        campoPeso = new JTextField();
        add(campoPeso);

        add(new JLabel("Altura (cm):"));
        campoAltura = new JTextField();
        add(campoAltura);

        JButton calcularButton = new JButton("Calcular IMC");
        add(calcularButton);

        resultadoLabel = new JLabel("");
        resultadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultadoLabel);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(campoPeso.getText());
            double alturaCm = Double.parseDouble(campoAltura.getText());
            double alturaM = alturaCm / 100.0;

            double imc = peso / (alturaM * alturaM);
            String classificacao = classificarIMC(imc);

            resultadoLabel.setText(String.format("IMC: %.2f - %s", imc, classificacao));
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Por favor, insira valores válidos.");
        }
    }

    private String classificarIMC(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        else if (imc < 24.9) return "Peso normal";
        else if (imc < 29.9) return "Sobrepeso";
        else if (imc < 34.9) return "Obesidade grau I";
        else if (imc < 39.9) return "Obesidade grau II";
        else return "Obesidade grau III (mórbida)";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraIMC().setVisible(true);
        });
    }
}
