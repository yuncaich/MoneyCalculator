package moneycalculator.View;

/**
 *
 * @author yuncai
 */
import moneycalculator.Controller.Controller;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import moneycalculator.Model.Currency;
import moneycalculator.Model.CurrencyList;
import moneycalculator.Model.Money;

public final class View extends JFrame {

    private String[] codMonedas;
    Map<String, Currency> currencies;

    private JComboBox<String> cbFrom;
    private JComboBox<String> cbTo;

    private JTextField textCantidad;
    private JTextField textResultado;

    private Controller control;

    public View() throws HeadlessException {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        panel1.setSize(400, 100);

        JLabel lbFrom = new JLabel("From: ");
        panel1.add(lbFrom);

        cbFrom = new JComboBox<>();
        panel1.add(cbFrom);

        JLabel lbTo = new JLabel("TO: ");
        panel1.add(lbTo);

        cbTo = new JComboBox<>();
        panel1.add(cbTo);

        CurrencyList listaMonedas = new CurrencyList();
        currencies = listaMonedas.getCurrencyList();

        Collection<Currency> monedas = currencies.values();
        codMonedas = new String[monedas.size()];

        int i = 0;
        for (Currency moneda : monedas) {
            String nombreMoneda = moneda.getName();
            codMonedas[i] = moneda.getCode();

            cbFrom.addItem(nombreMoneda);
            cbTo.addItem(nombreMoneda);
            i++;
        }

        this.getContentPane().add(panel1, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setSize(400, 100);

        JLabel lbCantidad = new JLabel("Introduzca cantidad: ");
        panel2.add(lbCantidad, BorderLayout.CENTER);

        textCantidad = new JTextField(8);
        panel2.add(textCantidad, BorderLayout.CENTER);

        this.getContentPane().add(panel2, BorderLayout.CENTER);

        JPanel panel3 = new JPanel();
        panel3.setSize(400, 100);

        JButton btnConvertir = new JButton("Convertir >>");
        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Currency from = currencies.get(codMonedas[cbFrom.getSelectedIndex()]);
                    Currency to = currencies.get(codMonedas[cbTo.getSelectedIndex()]);

                    if (from.getCode().equals(to.getCode())) {
                        DecimalFormat df = new DecimalFormat("#.00");
                        textResultado.setText(df.format(Double.parseDouble(textCantidad.getText())) + to.getSymbol());
                    } else {
                        Money dinero = new Money(Double.parseDouble(textCantidad.getText()), from);
                        control.execute(dinero, from, to);
                    }

                } catch (NumberFormatException ex) {
                    JDialog error = new JDialog();
                    error.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    error.setModal(true);
                    error.setType(Type.POPUP);
                    error.setTitle("Error");

                    JPanel auxPanel = new JPanel();
                    JLabel lbError = new JLabel("Introduzca una cantidad válida");
                    auxPanel.add(lbError, BorderLayout.CENTER);
                    
                    error.getContentPane().add(auxPanel);
                    error.setLocationRelativeTo(null);
                    error.pack();

                    error.setVisible(true);

                } catch (Exception ex) {
                    JDialog error = new JDialog();
                    error.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    error.setModal(true);
                    error.setType(Type.POPUP);
                    error.setTitle("Error");

                    JPanel auxPanel = new JPanel();
                    JLabel lbError = new JLabel("Error al conectar con el servidor");
                    auxPanel.add(lbError, BorderLayout.CENTER);

                    error.getContentPane().add(auxPanel);
                    error.setLocationRelativeTo(null);
                    error.pack();

                    error.setVisible(true);;
                }
            }
        });

        panel3.add(btnConvertir, BorderLayout.SOUTH);

        textResultado = new JTextField(8);
        textResultado.setEditable(false);
        panel3.add(textResultado, BorderLayout.SOUTH);

        this.getContentPane().add(panel3, BorderLayout.SOUTH);

        this.setLocationRelativeTo(null);
        this.setSize(450, 150);

        this.setVisible(true);
    }

    public void setControlador(Controller control) {
        this.control = control;
    }

    public void setTextResultado(String textResultado) {
        this.textResultado.setText(textResultado);
    }

}