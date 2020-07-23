package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import clases.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tecnico extends JFrame {

	private JPanel contentPane;
	private JButton jButton_Graficamarcas;
	public static int actualizar;
	private String user, nombre_usuario;
	private int sesion_usuario;
	private GestionarEquipos gestionarEquipos;
	private TecnicoGraficoUno tecnicoGraficoUno;
	private TecnicoGraficoDos tecnicoGraficoDos;

	public Tecnico() {
		actualizar++;
		this.addWindowListener( new WindowAdapter()
		 {
			   public void windowClosing(WindowEvent e)
			    {
				  actualizar=0;
			     }
			  });
		user= Login.user;
		sesion_usuario=Administrador.sesion_usuario;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLabel_NombreUsuario = new JLabel("");
		jLabel_NombreUsuario.setForeground(Color.BLACK);
		jLabel_NombreUsuario.setFont(new Font("Arial", Font.BOLD, 20));
		jLabel_NombreUsuario.setBounds(10, 11, 285, 35);
		contentPane.add(jLabel_NombreUsuario);
		
		JButton jButton_Gestionarequipos = new JButton("");
		jButton_Gestionarequipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((GestionarEquipos.actualizar==0)){
					gestionarEquipos = new GestionarEquipos();
					gestionarEquipos.setVisible(true);
				}
			else{
				gestionarEquipos.setVisible(true);
				gestionarEquipos.toFront();
				gestionarEquipos.requestFocus();
			}
			}
		});
		jButton_Gestionarequipos.setIcon(new ImageIcon(Tecnico.class.getResource("/images/apoyo-tecnico.png")));
		jButton_Gestionarequipos.setFocusPainted(false);
		jButton_Gestionarequipos.setBounds(25, 76, 120, 100);
		contentPane.add(jButton_Gestionarequipos);
		
		JLabel lblGestionDeEquipo = new JLabel("Gestion de equipo");
		lblGestionDeEquipo.setForeground(Color.BLACK);
		lblGestionDeEquipo.setFont(new Font("Arial", Font.BOLD, 13));
		lblGestionDeEquipo.setBounds(25, 182, 131, 14);
		contentPane.add(lblGestionDeEquipo);
		
		JButton jButton_Graficaestado = new JButton("");
		jButton_Graficaestado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TecnicoGraficoUno.actualizar==0){
				tecnicoGraficoUno  = new TecnicoGraficoUno();
				tecnicoGraficoUno.h();
				}}
		});
		jButton_Graficaestado.setIcon(new ImageIcon(Tecnico.class.getResource("/images/grafica.png")));
		jButton_Graficaestado.setFocusPainted(false);
		jButton_Graficaestado.setBounds(185, 76, 120, 100);
		contentPane.add(jButton_Graficaestado);
		
		JLabel lblInformacionUsuario_1 = new JLabel("Grafica de estado");
		lblInformacionUsuario_1.setForeground(Color.BLACK);
		lblInformacionUsuario_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario_1.setBounds(185, 182, 120, 14);
		contentPane.add(lblInformacionUsuario_1);
		
		jButton_Graficamarcas = new JButton("");
		jButton_Graficamarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TecnicoGraficoDos.actualizar==0){
					tecnicoGraficoDos  = new TecnicoGraficoDos();
					tecnicoGraficoDos.h();
					}}
		});
		jButton_Graficamarcas.setIcon(new ImageIcon(Tecnico.class.getResource("/images/grafica.png")));
		jButton_Graficamarcas.setFocusPainted(false);
		jButton_Graficamarcas.setBounds(343, 76, 120, 100);
		contentPane.add(jButton_Graficamarcas);
		
		JLabel lblInformacionUsuario_2 = new JLabel("Grafica de marcas");
		lblInformacionUsuario_2.setForeground(Color.BLACK);
		lblInformacionUsuario_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario_2.setBounds(343, 182, 130, 14);
		contentPane.add(lblInformacionUsuario_2);

		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 494, 271);
		contentPane.add(jLabel_Wallpaper);

		if(sesion_usuario==1) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		}
		else {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_usuario from usuarios where username = ?");
			pst.setString(1, user);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				nombre_usuario=rs.getString("nombre_usuario");
				jLabel_NombreUsuario.setText("Sesion de: "+nombre_usuario);
			}
		}
				catch(SQLException e3)	{
					System.out.print("error en base de datos Tecnico " + e3);
						JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
				}
		
		setTitle("Tecnico sesión de " + nombre_usuario);
		setLocationRelativeTo(null);
		setResizable(false);

		ImageIcon wallpaper = new ImageIcon(Tecnico.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tecnico.class.getResource("/images/icon.png")));
		
		this.repaint();
		
	}
}
