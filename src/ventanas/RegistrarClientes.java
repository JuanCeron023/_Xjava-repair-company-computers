package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import clases.Conexion;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarClientes extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_mail;
	private JTextField txt_telefono;
	private JTextField txt_direccion;
	private String user;
	public static int actualizar;

	public RegistrarClientes() {
		actualizar++;
		this.addWindowListener( new WindowAdapter()
		 {
			   public void windowClosing(WindowEvent e)
			    {
				  actualizar=0;
			     }
			  });
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeClientes = new JLabel("Registro de Clientes");
		lblRegistroDeClientes.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistroDeClientes.setBounds(200, 10, 240, 29);
		contentPane.add(lblRegistroDeClientes);
		
		JLabel lblNomvbre = new JLabel("Nombre:");
		lblNomvbre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomvbre.setBounds(10, 64, 67, 16);
		contentPane.add(lblNomvbre);
		
		txt_nombre = new JTextField();
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setColumns(10);
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 86, 225, 20);
		contentPane.add(txt_nombre);
		
		JLabel lblEmil = new JLabel("Em@il:");
		lblEmil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmil.setBounds(10, 117, 67, 16);
		contentPane.add(lblEmil);
		
		txt_mail = new JTextField();
		txt_mail.setHorizontalAlignment(SwingConstants.CENTER);
		txt_mail.setForeground(Color.WHITE);
		txt_mail.setFont(new Font("Arial", Font.BOLD, 16));
		txt_mail.setColumns(10);
		txt_mail.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_mail.setBackground(new Color(155, 155, 255));
		txt_mail.setBounds(10, 139, 225, 20);
		contentPane.add(txt_mail);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(10, 170, 67, 16);
		contentPane.add(lblTelefono);
		
		txt_telefono = new JTextField();
		txt_telefono.setHorizontalAlignment(SwingConstants.CENTER);
		txt_telefono.setForeground(Color.WHITE);
		txt_telefono.setFont(new Font("Arial", Font.BOLD, 16));
		txt_telefono.setColumns(10);
		txt_telefono.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_telefono.setBackground(new Color(155, 155, 255));
		txt_telefono.setBounds(10, 192, 225, 20);
		contentPane.add(txt_telefono);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccion.setBounds(10, 233, 112, 16);
		contentPane.add(lblDireccion);
		
		txt_direccion = new JTextField();
		txt_direccion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_direccion.setForeground(Color.WHITE);
		txt_direccion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_direccion.setColumns(10);
		txt_direccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_direccion.setBackground(new Color(155, 155, 255));
		txt_direccion.setBounds(10, 260, 225, 20);
		contentPane.add(txt_direccion);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre, mail, telefono, direccion;
				mail = txt_mail.getText().trim();
				nombre = txt_nombre.getText().trim();
				telefono = txt_telefono.getText().trim();
				direccion = txt_direccion.getText().trim();
				
			if (mail.equals("")||direccion.equals("")||nombre.equals("")||telefono.equals("")){
				JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
			} else {
				try {
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement("select nombre_cliente from clientes where nombre_cliente = ?");
							  pst.setString(1, nombre);
					            ResultSet rs = pst.executeQuery();
					            if (rs.next()) {
									JOptionPane.showMessageDialog(null, "este cliente ya existe");
									cn.close();
								} else {
									try {
									cn.close();
									Connection cn1 =  Conexion.conectar();
									PreparedStatement pst1 = cn1.prepareStatement(
											"insert into clientes values (?,?,?,?,?,?)");
											  pst1.setInt(1, 0);
											  pst1.setString(2, nombre);
											  pst1.setString(3, mail);
											  pst1.setString(4, telefono);
											  pst1.setString(5, direccion);
											  pst1.setString(6, user);
									          pst1.executeUpdate();
											  Limpiar(); 
									          cn1.close();
									          JOptionPane.showMessageDialog(null, "registro exitoso");
									          dispose();
									}
									catch(SQLException e1){
										System.out.print("error en base de datos registro de clientes " + e1);
										JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
									}
									}				
				} catch (SQLException e2) {
					System.out.print("error en base de datos registro de clientes " + e2);
					JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
				}	
			}			
			}
		});
		btnNewButton.setIcon(new ImageIcon(RegistrarClientes.class.getResource("/images/add.png")));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBounds(308, 127, 136, 129);
		contentPane.add(btnNewButton);
		
		JLabel lblRegistrarUsuario = new JLabel("Registrar usuario");
		lblRegistrarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRegistrarUsuario.setBounds(308, 263, 179, 16);
		contentPane.add(lblRegistrarUsuario);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 624, 321);
		contentPane.add(jLabel_Wallpaper);
		
		user= Login.user;
		setTitle("Registro clientes, Sesión de " + user);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(RegistrarClientes.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		this.repaint();
	}
	public void Limpiar()
	{
		txt_mail.setText("");
		txt_nombre.setText("");
		txt_direccion.setText("");
		txt_telefono.setText("");
	}
}
