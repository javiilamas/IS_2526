package es.unican.is2.segurosgui;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import es.unican.is2.seguroscommon.Cliente;
import es.unican.is2.seguroscommon.DataAccessException;
import es.unican.is2.seguroscommon.IInfoSeguros;
import es.unican.is2.seguroscommon.Seguro;

@SuppressWarnings("serial")
public class VistaAgente extends JFrame {

    private JTextField txtDniCliente;
    private JTextField txtTotalCliente;
    private JTextField txtNombreCliente;
    private DefaultListModel<String> listModel;

    private transient IInfoSeguros info;

    public VistaAgente(IInfoSeguros info) {
        this.info = info;
        init();
    }

    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 341);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        listModel = new DefaultListModel<>();

        txtTotalCliente = new JTextField();
        txtTotalCliente.setBounds(230, 251, 180, 20);
        contentPane.add(txtTotalCliente);
        txtTotalCliente.setColumns(10);
        txtTotalCliente.setName("txtTotalCliente");

        JLabel lblTotalCliente = new JLabel("Total A Pagar");
        lblTotalCliente.setBounds(137, 254, 180, 14);
        contentPane.add(lblTotalCliente);

        JList<String> listSeguros = new JList<>();
        listSeguros.setBounds(230, 98, 180, 116);
        listSeguros.setBorder(new LineBorder(new Color(0, 0, 0)));
        listSeguros.setModel(listModel);
        listSeguros.setVisible(true);
        contentPane.add(listSeguros);

        JLabel lblSeguros = new JLabel("Seguros");
        lblSeguros.setBounds(149, 93, 65, 14);
        contentPane.add(lblSeguros);

        JLabel lblNombreCliente = new JLabel("Nombre");
        lblNombreCliente.setBounds(155, 54, 65, 14);
        contentPane.add(lblNombreCliente);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(230, 51, 180, 20);
        txtNombreCliente.setColumns(10);
        txtNombreCliente.setName("txtNombreCliente");
        contentPane.add(txtNombreCliente);

        JLabel lblDatosCliente = new JLabel("Datos Cliente");
        lblDatosCliente.setBounds(230, 11, 149, 14);
        contentPane.add(lblDatosCliente);

        txtDniCliente = new JTextField();
        txtDniCliente.setBounds(10, 51, 113, 20);
        txtDniCliente.setColumns(10);
        txtDniCliente.setName("txtDNICliente");
        contentPane.add(txtDniCliente);

        JLabel lblDniCliente = new JLabel("DNI Cliente");
        lblDniCliente.setBounds(21, 27, 139, 14);
        lblDniCliente.setName("lblDniCliente");
        contentPane.add(lblDniCliente);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(arg0 -> rellenaDatosCliente(txtDniCliente.getText()));
        btnBuscar.setBounds(21, 122, 89, 23);
        btnBuscar.setName("btnBuscar");
        contentPane.add(btnBuscar);
    }

    private void rellenaDatosCliente(String dni) {
        try {
            Cliente c = info.cliente(dni);
            if (c != null) {
                txtNombreCliente.setText(c.getNombre());
                txtTotalCliente.setText(Double.toString(c.totalSeguros()));
                listModel.removeAllElements();
                for (Seguro v : c.getSeguros()) {
                    listModel.addElement(v.getMatricula() + " " + v.getCobertura());
                }
            } else {
                txtNombreCliente.setText("DNI No Válido");
                txtTotalCliente.setText("");
                listModel.removeAllElements();
            }
        } catch (DataAccessException e) {
            txtNombreCliente.setText("Error BBDD");
            txtTotalCliente.setText("");
            listModel.removeAllElements();
        }
    }
}