package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_mail;
	private JTextField txt_telefono;
	private JTextField txt_username;
	private JPasswordField txt_password;
	private String user;

	public RegistrarUsuarios() {
		user = Login.user;
		setBounds(100, 100, 630, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro de usuarios");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(200, 10, 240, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomvbre = new JLabel("Nombre:");
		lblNomvbre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNomvbre.setBounds(10, 64, 67, 16);
		contentPane.add(lblNomvbre);
		
		txt_nombre = new JTextField();
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 86, 225, 20);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		ImageIcon wallpaper = new ImageIcon(RegistrarUsuarios.class.getResource("/images/wallpaperPrincipal.jpg"));
		
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
		
		JLabel lblPermisosDe = new JLabel("Permisos de:");
		lblPermisosDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPermisosDe.setBounds(10, 239, 112, 16);
		contentPane.add(lblPermisosDe);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(350, 64, 88, 16);
		contentPane.add(lblUsername);
		
		txt_username = new JTextField();
		txt_username.setHorizontalAlignment(SwingConstants.CENTER);
		txt_username.setForeground(Color.WHITE);
		txt_username.setFont(new Font("Arial", Font.BOLD, 16));
		txt_username.setColumns(10);
		txt_username.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_username.setBackground(new Color(155, 155, 255));
		txt_username.setBounds(350, 86, 183, 20);
		contentPane.add(txt_username);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(350, 117, 88, 16);
		contentPane.add(lblPassword);
		
		txt_password = new JPasswordField();
		txt_password.setHorizontalAlignment(SwingConstants.CENTER);
		txt_password.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_password.setBackground(new Color(153, 153, 255));
		txt_password.setFont(new Font("Arial", Font.PLAIN, 16));
		txt_password.setBounds(350, 139, 183, 20);
		contentPane.add(txt_password);
		
		JComboBox cmb_niveles = new JComboBox();
		cmb_niveles.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Capturista", "Tecnico"}));
		cmb_niveles.setBounds(10, 259, 166, 20);
		contentPane.add(cmb_niveles);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				int permisos_cmb,validacion =0;
				String nombre, mail, telefono, username, pass, permisos_string="";
				mail = txt_mail.getText().trim();
				username = txt_username.getText().trim();
				pass = txt_password.getText().trim();
				nombre = txt_nombre.getText().trim();
				telefono = txt_telefono.getText().trim();
				permisos_cmb = cmb_niveles.getSelectedIndex() + 1;
		
			if (mail.equals("")||
				username.equals("")||pass.equals("")||nombre.equals("")||telefono.equals("") 
			 	||permisos_cmb == 0){
				validacion++;
				JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				
			} else {
				if (permisos_cmb==1) {
					permisos_string = "Administrador";
				} else if(permisos_cmb==2) {
					permisos_string = "Capturista";
				} else if(permisos_cmb==3) {
					permisos_string = "Tecnico";
				}
				
				try {
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"select username from usuarios where username = ?");
							  pst.setString(1, username);
					            ResultSet rs = pst.executeQuery();
					            if (rs.next()) {
									JOptionPane.showMessageDialog(null, "este username ya existe");
									cn.close();
								} else {
									try {
									cn.close();
									Connection cn1 =  Conexion.conectar();
									PreparedStatement pst1 = cn1.prepareStatement(
											"insert into usuarios values (?,?,?,?,?,?,?,?,?)");
											  pst1.setInt(1, 0);
											  pst1.setString(2, nombre);
											  pst1.setString(3, mail);
											  pst1.setString(4, telefono);
											  pst1.setString(5, username);
											  pst1.setString(6, pass);
											  pst1.setString(7, permisos_string);
											  pst1.setString(8, "Activo");
											  pst1.setString(9, user);
											  Limpiar(); 
									          pst1.executeUpdate();
									          cn1.close();
									          JOptionPane.showMessageDialog(null, "registro exitoso");
									          dispose();
									}
									catch(SQLException e){
										System.out.print("error en base de datos registro de usuarios " + e);
										JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
									}
									}				
				} catch (SQLException e) {
					System.out.print("error en base de datos registro de usuarios " + e);
					JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
				}	
			}			
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setIcon(new ImageIcon(RegistrarUsuarios.class.getResource("/images/add.png")));
		btnNewButton.setBounds(350, 181, 136, 129);
		contentPane.add(btnNewButton);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 624, 321);
		contentPane.add(jLabel_Wallpaper);
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		this.repaint();
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Administrador - Sesión de " + user);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarUsuarios.class.getResource("/images/icon.png")));	
	}
	public void Limpiar()
	{
		txt_mail.setText("");
		txt_nombre.setText("");
		txt_password.setText("");
		txt_telefono.setText("");
		txt_username.setText("");
	}
}
