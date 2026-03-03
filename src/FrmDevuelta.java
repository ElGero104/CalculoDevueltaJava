import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmDevuelta extends JFrame {
    JComboBox cmbDenominacion; /*ComboBox para seleccionar la denominación del billete o moneda Variable Global*/
    JTextField txtExistencia; /*Campo de texto para ingresar la cantidad de existencias de la denominación seleccionada Variable Global*/
    int[] denominaciones= {100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50}; /*Array con las denominaciones de billetes y monedas*/
    int[] existencias=new int[denominaciones.length]; /*Array para almacenar las existencias de cada denominación*/
    JTextField txtDevuelta; /*Campo de texto para mostrar la devuelta calculada Variable Global*/
    
    public FrmDevuelta() {
        setSize(400, 300);
        setTitle("Calculo de devuelta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        JLabel lblDenominacion = new JLabel("Denominación:"); /*Etiqueta para la denominación del billete o moneda*/
        lblDenominacion.setBounds(10, 10, 100, 25); /*Ubicación de la etiqueta*/
        add(lblDenominacion); /*Agrega la etiqueta al formulario*/  


        cmbDenominacion = new JComboBox(); /*ComboBox para seleccionar la denominación del billete o moneda*/
        cmbDenominacion.setBounds(120, 10, 100, 25); /*Ubicación del ComboBox*/
        add(cmbDenominacion); /*Agrega el ComboBox al formulario*/


        
        for (int denominacion : denominaciones) { /*Itera sobre el array de denominaciones*/
            cmbDenominacion.addItem(denominacion); /*Agrega cada denominación al ComboBox*/
        }


        JButton btnExistencia=new JButton("Actualizar existencias"); /*Botón para actualizar las existencias de la denominación seleccionada*/
        btnExistencia.setBounds(10, 45, 180, 25); /*Ubicación del botón*/
        add(btnExistencia); /*Agrega el botón al formulario*/


        txtExistencia=new JTextField(); /*Campo de texto para ingresar la cantidad de existencias de la denominación seleccionada*/
        txtExistencia.setBounds(200, 45, 50, 25); /*Ubicación del campo de texto*/
        add(txtExistencia); /*Agrega el campo de texto al formulario*/


        JLabel lblDevuelta=new JLabel("Valor a devolver:"); /*Etiqueta para mostrar la devuelta calculada*/
        lblDevuelta.setBounds(10, 80, 100, 25); /*Ubicación de la etiqueta*/
        add(lblDevuelta); /*Agrega la etiqueta al formulario*/


        txtDevuelta=new JTextField(); /*Campo de texto para mostrar la devuelta calculada*/
        txtDevuelta.setBounds(120, 80, 100, 25); /*Ubicación del campo de texto*/
        add(txtDevuelta); /*Agrega el campo de texto al formulario*/


        JButton btnDevuelta=new JButton("Calcular"); /*Botón para calcular y mostrar la devuelta*/
        btnDevuelta.setBounds(240, 80, 100, 25); /*Ubicación del botón*/
        add(btnDevuelta); /*Agrega el botón al formulario*/


        JTable tblDevuelta=new JTable(); /*Tabla para mostrar el desglose de la devuelta por denominación*/
        JScrollPane scrollPane=new JScrollPane(tblDevuelta); /*ScrollPane para contener la tabla*/
        scrollPane.setBounds(10, 120, 370, 200); /*Ubicación del ScrollPane*/
        add(scrollPane); /*Agrega el ScrollPane (y la tabla) al formulario*/
        String[] encabezados={"Cantidad","Presentación","Denominación"}; /*Encabezados para la tabla*/
        DefaultTableModel modelo=new DefaultTableModel(null,encabezados); /*Modelo de tabla para manejar los datos de la tabla*/
        tblDevuelta.setModel(modelo); /*Asigna el modelo a la tabla*/
        


        /*Eventos*/
        btnExistencia.addActionListener(e -> {
            actualizarExistencia(); /*Llama al método para actualizar la existencia de la denominación seleccionada cuando se hace clic en el botón*/
        });

        btnDevuelta.addActionListener(e -> {
           calcularDevuelta(); /*Llama al método para calcular la devuelta y actualizar la tabla con el desglose por denominación cuando se hace clic en el botón*/
        });

        cmbDenominacion.addActionListener(e -> {
            consultarExistencia();
        });
        
    }

    

        /*Metodos*/
        private void consultarExistencia() {
            if (cmbDenominacion.getSelectedIndex() >= 0) { /*Verifica si se ha seleccionado una denominación en el ComboBox*/
                // Lógica para consultar la existencia de la denominación seleccionada
                txtExistencia.setText(String.valueOf(existencias[cmbDenominacion.getSelectedIndex()])); /*Actualiza el campo de texto con la existencia consultada*/
            }
        }  

        

        private void actualizarExistencia() {
            if (cmbDenominacion.getSelectedIndex() >= 0){
                existencias[cmbDenominacion.getSelectedIndex()]=Integer.parseInt(txtExistencia.getText()); /*Actualiza el array de existencias con el valor ingresado en el campo de texto para la denominación seleccionada*/
                JOptionPane.showMessageDialog(null, "Existencia actualizada correctamente."); /*Muestra un mensaje de confirmación al usuario*/
            }
        }

        private void calcularDevuelta() {
            int valorDevuelta= Integer.parseInt(txtDevuelta.getText()); /*Limpia el campo de texto de la devuelta antes de calcularla*/

            int [] devuelta=new int[denominaciones.length]; /*Array para almacenar la cantidad de cada denominación que se devolverá*/

            for (int i = 0; i < denominaciones.length; i++){ /*Itera sobre el array de denominaciones para calcular la cantidad de cada una que se devolverá*/
                if (valorDevuelta >= denominaciones[i]){
                    int cantidadNecesaria = (int) valorDevuelta / denominaciones[i]; /*Calcula la cantidad necesaria de la denominación actual para cubrir el valor de la devuelta*/
                    devuelta[i]=existencias[i] >= cantidadNecesaria? cantidadNecesaria : existencias[i]; /*Determina la cantidad de la denominación actual que se devolverá, considerando las existencias disponibles*/
                    valorDevuelta -= devuelta[i] * denominaciones[i]; /*Actualiza el valor de la devuelta restando el valor de la cantidad de la denominación actual que se devolverá*/

                }
            }
        }

} 