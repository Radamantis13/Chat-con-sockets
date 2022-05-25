import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.ServerSocket;
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


public class jframeServidor extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTextField textEnviarservidor;
	private JTextField textRecibidoservidor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jframeServidor frame = new jframeServidor();
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
	public jframeServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Servidor");//establecer titulo de la ventana
		
		//julian
		
		JPanel panel = new JPanel(); // creacion de un panel
		panel.setLayout(null);//desabilitar el diseño por defecto
		//panel.setBackground(Color.green);// establecer el color del panel
		this.getContentPane().add(panel);
		
		textEnviarservidor = new JTextField();
		textEnviarservidor.setBounds(30, 26, 195, 45);
		panel.add(textEnviarservidor);
		textEnviarservidor.setColumns(10);
		
		textRecibidoservidor = new JTextField();
		textRecibidoservidor.setBounds(30, 143, 195, 45);
		panel.add(textRecibidoservidor);
		textRecibidoservidor.setColumns(10);
		
		JButton btnNuevoa = new JButton("Nuevo");
		btnNuevoa.setBounds(30, 83, 89, 23);
		panel.add(btnNuevoa);
		
		JButton btnNuevob = new JButton("Nuevo");
		btnNuevob.setBounds(30, 199, 89, 23);
		panel.add(btnNuevob);
		
		JButton btnConexion = new JButton("Iniciar conexion");
		btnConexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String textoservidor2 = textEnviarservidor.getText().toString();
				
					//servidor
					ServerSocket Servidor = new ServerSocket(5000);
					Socket clienteNuevo = Servidor.accept();
					ObjectInputStream entrada = new ObjectInputStream(clienteNuevo.getInputStream());
							
					//esperando mensaje	
					String mensaje = (String) entrada.readObject();
					textRecibidoservidor.setText(mensaje);
					
					
					//envio respuesta
					ObjectOutputStream respuesta = new ObjectOutputStream(clienteNuevo.getOutputStream());
					respuesta.writeObject(textoservidor2); //enviamos el mensaje
					clienteNuevo.close();
					Servidor.close();
					
				}catch (IOException ex){
					Logger.getLogger(jframeCliente.class.getName()).log(Level.SEVERE,null, ex);
					
				} catch (ClassNotFoundException ex) {
					// TODO Auto-generated catch block
					Logger.getLogger(jframeCliente.class.getName()).log(Level.SEVERE,null, ex);
				}
			
			}
		});
		btnConexion.setBounds(301, 83, 113, 23);
		panel.add(btnConexion);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(301, 134, 89, 23);
		panel.add(btnCerrar);
	}

}
