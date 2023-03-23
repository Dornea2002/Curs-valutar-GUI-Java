import com.sun.jdi.IntegerValue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame {
    private JLabel titluCursValutar;
    private JLabel titluValoareMoneda;

    private JTextField fromTextField;
    private JTextField toTextField;

    private JList toJList;
    private JList fromJList;
    private JButton convertTo;
    private JLabel sumaLabel;
    private JLabel rezultatFromLabel;

    private JLabel rezultatLabel;
    private JLabel rezultatToLabel;

    private ArrayList<Moneda> monedeList = new ArrayList<Moneda>();

    private double rezultat;

    private Moneda monedaFrom;
    private Moneda monedaTo;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        titluCursValutar = new JLabel();
        titluCursValutar.setText("Convertor valutar");
        titluCursValutar.setBounds(400, 100, 200, 30);
        titluCursValutar.setFont(new Font(Font.DIALOG, Font.ITALIC, 18));
        getContentPane().add(titluCursValutar);

        titluValoareMoneda = new JLabel();
        titluValoareMoneda.setBounds(400, 200, 200, 30);
        titluValoareMoneda.setText("----------");

        fromJList = new JList();
        fromJList.setBounds(20, 372, 298, 222);
        getContentPane().add(fromJList);

        convertTo = new JButton();
        convertTo.setText(">>Convert to>>");
        convertTo.setBounds(338, 483, 200, 20);


        toJList = new JList();
        toJList.setBounds(558, 372, 298, 222);
        getContentPane().add(toJList);

        sumaLabel = new JLabel();
        sumaLabel.setText("Suma: ");
        sumaLabel.setBounds(338, 604, 100, 20);
        getContentPane().add(sumaLabel);

        fromTextField = new JTextField();
        fromTextField.setBounds(375, 604, 100, 20);
        getContentPane().add(fromTextField);
        fromTextField.setColumns(10);

        rezultatFromLabel = new JLabel();
        rezultatFromLabel.setText("---");
        rezultatFromLabel.setBounds(480, 604, 100, 20);
        getContentPane().add(rezultatFromLabel);


        rezultatLabel = new JLabel();
        rezultatLabel.setText("Rezultat: ");
        rezultatLabel.setBounds(325, 634, 100, 20);
        getContentPane().add(rezultatLabel);

        toTextField = new JTextField();
        toTextField.setBounds(375, 634, 100, 20);
        getContentPane().add(toTextField);
        toTextField.setColumns(10);

        rezultatToLabel = new JLabel();
        rezultatToLabel.setText("---");
        rezultatToLabel.setBounds(480, 634, 100, 20);
        getContentPane().add(rezultatToLabel);


        //---------------------
        Moneda euro = new Moneda("Euro", 4.9314);
        Moneda dolar = new Moneda("USD", 4.5583);
        Moneda leu = new Moneda("RON", 1.0);

        monedeList.add(euro);
        monedeList.add(leu);
        monedeList.add(dolar);

        this.addMondeda(toJList);
        this.addMondeda(fromJList);

        fromJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                monedaFrom = (Moneda) fromJList.getSelectedValue();
                if (monedaFrom != null && monedaTo != null) {
                    titluValoareMoneda.setText("1 " + monedaFrom.getName() + " = " + monedaFrom.getValue() / monedaTo.getValue() + " " + monedaTo.getName());
                }
            }
        });

        toJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                monedaTo = (Moneda) toJList.getSelectedValue();
                titluValoareMoneda.setText("1 " + monedaFrom.getName() + " = " + monedaFrom.getValue() / monedaTo.getValue() + " " + monedaTo.getName());
            }
        });

        convertTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (monedaFrom != null && monedaTo != null){
                    double rezultat=monedaFrom.getValue()/monedaTo.getValue();
                    int suma= Integer.parseInt(fromTextField.getText());
                    rezultat*=suma;
                    toTextField.setText(String.valueOf(rezultat));

                    rezultatFromLabel.setText(monedaFrom.getName());
                    rezultatToLabel.setText(monedaTo.getName());
                }
            }
        });

        getContentPane().add(titluValoareMoneda);
        getContentPane().add(convertTo);
        this.setSize(900, 800);
        this.setVisible(true);
    }

    public void addMondeda(JList list) {
        DefaultListModel listModel = new DefaultListModel<>();
        for (Moneda m : monedeList) {
            listModel.addElement(m);
        }
        list.setModel(listModel);
    }

    public JList getFromJList() {
        return this.fromJList;
    }

    public JList getToJlist() {
        return this.toJList;
    }
}
