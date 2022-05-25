import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class jframeCliente extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField textEnviarcliente;
	private JTextField textRecibircliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jframeCliente frame = new jframeCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jframeCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Cliente");//establecer titulo de la ventana
		
		//julian
		
		JPanel panel = new JPanel(); // creacion de un panel
		panel.setLayout(null);//desabilitar el diseño por defecto
		//panel.setBackground(Color.green);// establecer el color del panel
		this.getContentPane().add(panel);
		
		textEnviarcliente = new JTextField();
		textEnviarcliente.setBounds(28, 25, 175, 51);
		panel.add(textEnviarcliente);
		textEnviarcliente.setColumns(10);
		
		textRecibircliente = new JTextField();
		textRecibircliente.setBounds(27, 132, 175, 51);
		panel.add(textRecibircliente);
		textRecibircliente.setColumns(10);
		
		JButton btnNuevoa = new JButton("Nuevo");
		btnNuevoa.setBounds(28, 87, 89, 23);
		panel.add(btnNuevoa);
		
		JButton btnNuebob = new JButton("Nuevo");
		btnNuebob.setBounds(28, 192, 89, 23);
		panel.add(btnNuebob);
		
		JButton btnConexion = new JButton("Iniciar Conexion");
		btnConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		
				
				try {
					String texto = textEnviarcliente.getText().toString();
					String texto2 = textRecibircliente.getText().toString();
					
					//Conexion con el servidor
					Socket Cliente = new Socket("192.168.0.25", 5000);
					ObjectOutputStream mensaje = new ObjectOutputStream(Cliente.getOutputStream());
					mensaje.writeObject(texto); //enviamos el mensaje
					
					//llegada de la respuesta del servidor
					ObjectInputStream entrada = new ObjectInputStream(Cliente.getInputStream());
					String mensaje2 = (String) entrada.readObject();
					textRecibircliente.setText(mensaje2);
					Cliente.close();
					
				} catch (IOException ex) {
					Logger.getLogger(jframeCliente.class.getName()).log(Level.SEVERE,null, ex);
				} catch (ClassNotFoundException ex) {
					Logger.getLogger(jframeCliente.class.getName()).log(Level.SEVERE,null, ex);
				}
			}
		});
		btnConexion.setBounds(286, 87, 128, 23);
		panel.add(btnConexion);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(286, 146, 89, 23);
		panel.add(btnCerrar);
		
	}
}
