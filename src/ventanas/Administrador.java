package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Administrador extends JFrame {

	private JPanel contentPane;
	private String user, nombre_usuario;
	public static int sesion_usuario;
	private GestionarUsuarios gestionarUsuarios;
	private Capturista capturista;
	private Tecnico tecnico;

	public Administrador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Administrador.class.getResource("/images/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLabel_NombreUsuario = new JLabel("");
		jLabel_NombreUsuario.setFont(new Font("Arial", Font.BOLD, 20));
		jLabel_NombreUsuario.setForeground(Color.WHITE);
		jLabel_NombreUsuario.setBounds(10, 10, 248, 28);
		contentPane.add(jLabel_NombreUsuario);
		
		JButton jButton_RegistrarUsuario = new JButton("");
		jButton_RegistrarUsuario.setFocusPainted(false);
		jButton_RegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				RegistrarUsuarios registrarUusario = new RegistrarUsuarios();
				registrarUusario.setVisible(true);		
			}
		});
		jButton_RegistrarUsuario.setIcon(new ImageIcon(Administrador.class.getResource("/images/addUser.png")));
		jButton_RegistrarUsuario.setBounds(60, 70, 120, 100);
		contentPane.add(jButton_RegistrarUsuario);
		
		JButton jButton_GestionarUsuarios = new JButton("");
		jButton_GestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
					if((GestionarUsuarios.actualizar==0)){
				    gestionarUsuarios = new GestionarUsuarios();
					gestionarUsuarios.setVisible(true);
					}
				else{
					gestionarUsuarios.setVisible(true);
					gestionarUsuarios.toFront();
					gestionarUsuarios.requestFocus();
				}
			}
		});
		jButton_GestionarUsuarios.setFocusPainted(false);
		jButton_GestionarUsuarios.setIcon(new ImageIcon(Administrador.class.getResource("/images/informationuser.png")));
		jButton_GestionarUsuarios.setBounds(270, 70, 120, 100);
		contentPane.add(jButton_GestionarUsuarios);
		
		JButton jButton_Capturista = new JButton("");
		jButton_Capturista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((Capturista.actualizar==0))	{
					capturista = new Capturista();
					capturista.setVisible(true);
				}
			else{
				capturista.setVisible(true);
				capturista.toFront();
				capturista.requestFocus();
			}
			}
		});
		jButton_Capturista.setFocusPainted(false);
		jButton_Capturista.setIcon(new ImageIcon(Administrador.class.getResource("/images/capturista.png")));
		jButton_Capturista.setBounds(170, 219, 120, 100);
		contentPane.add(jButton_Capturista);
		
		JButton jButton_Tecnico = new JButton("");
		jButton_Tecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if((Tecnico.actualizar==0))	{
						tecnico = new Tecnico();
						tecnico.setVisible(true);
					}
				else{
					tecnico.setVisible(true);
					tecnico.toFront();
					tecnico.requestFocus();
				}}
		});
		jButton_Tecnico.setFocusPainted(false);
		jButton_Tecnico.setIcon(new ImageIcon(Administrador.class.getResource("/images/tecnico.png")));
		jButton_Tecnico.setBounds(380, 221, 120, 100);
		contentPane.add(jButton_Tecnico);		
		
		JButton jButton_AcercaDe = new JButton("");
		jButton_AcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acercade = new AcercaDe();
				acercade.setVisible(true);
			}
		});
		jButton_AcercaDe.setFocusPainted(false);
		ImageIcon icon1 = new ImageIcon(Administrador.class.getResource("/images/logo_transparent.png"));
		jButton_AcercaDe.setIcon(new ImageIcon(icon1.getImage().getScaledInstance(90, 
				100, Image.SCALE_SMOOTH)));
		jButton_AcercaDe.setBounds(470, 70, 120, 100);
		contentPane.add(jButton_AcercaDe);
		
		JLabel lblInformacionUsuario = new JLabel("Gestionar usuarios");
		lblInformacionUsuario.setForeground(Color.BLACK);
		lblInformacionUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario.setBounds(270, 176, 120, 14);
		contentPane.add(lblInformacionUsuario);
		
		JLabel lblBasadoEnLa = new JLabel("Basado en la geekipedia de ernesto");
		lblBasadoEnLa.setBounds(230, 376, 202, 14);
		contentPane.add(lblBasadoEnLa);
		
		JLabel lblRegistrarUsuario = new JLabel("Registrar usuario");
		lblRegistrarUsuario.setForeground(Color.BLACK);
		lblRegistrarUsuario.setFont(new Font("Arial", Font.BOLD, 13));
	 
		lblRegistrarUsuario.setBounds(60, 176, 120, 14);
		contentPane.add(lblRegistrarUsuario);
		
		JLabel lblAcercaDe = new JLabel("Acerca de...");
		lblAcercaDe.setForeground(Color.BLACK);
		lblAcercaDe.setFont(new Font("Arial", Font.BOLD, 13));
		lblAcercaDe.setBounds(470, 176, 120, 14);
		contentPane.add(lblAcercaDe);
		
		JLabel lblPanelCapturista = new JLabel("Panel capturista");
		lblPanelCapturista.setForeground(Color.BLACK);
		lblPanelCapturista.setFont(new Font("Arial", Font.BOLD, 13));
		lblPanelCapturista.setBounds(170, 330, 120, 14);
		contentPane.add(lblPanelCapturista);
		
		JLabel lblPanelTecnico = new JLabel("Panel tecnico");
		lblPanelTecnico.setForeground(Color.BLACK);
		lblPanelTecnico.setFont(new Font("Arial", Font.BOLD, 13));
		lblPanelTecnico.setBounds(380, 330, 120, 14);
		contentPane.add(lblPanelTecnico);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 644, 401);
		contentPane.add(jLabel_Wallpaper);
	
	user = Login.user;
	sesion_usuario=1;
	
	
	setTitle("Administrador - Sesión de " + user);
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	ImageIcon wallpaper = new ImageIcon(Administrador.class.getResource("/images/wallpaperPrincipal.jpg"));
	Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
											jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
	jLabel_Wallpaper.setIcon(icono);
	this.repaint();

	try {
		Connection cn =  Conexion.conectar();
		PreparedStatement pst = cn.prepareStatement(
				"select nombre_usuario from usuarios where username = ?");
				  pst.setString(1, user);
		            ResultSet rs = pst.executeQuery();
		            if (rs.next()) {
						nombre_usuario = rs.getString("nombre_usuario");
						jLabel_NombreUsuario.setText(nombre_usuario);
					} else {}
		            cn.close();
	} catch (Exception e) {
		System.out.print("error en conexion adminsitrador" + e);
	}
	}

}
