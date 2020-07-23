package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.font.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import clases.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class Capturista extends JFrame {

	private JPanel contentPane;
	private String user, nombre_usuario;
	private int sesion_usuario;
	public static int actualizar;
	private RegistrarClientes registrarClientes;
	private GestionarClientes gestionarClientes;

	public Capturista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Capturista.class.getResource("/images/icon.png")));		
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
		
		JButton jButton_GestionarUsuarios = new JButton("");
		jButton_GestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if((RegistrarClientes.actualizar==0)){
					registrarClientes = new RegistrarClientes();
					registrarClientes.setVisible(true);
				}
			else{
				registrarClientes.setVisible(true);
				registrarClientes.toFront();
				registrarClientes.requestFocus();
			}
			}
		});
		jButton_GestionarUsuarios.setIcon(new ImageIcon(Capturista.class.getResource("/images/add.png")));
		jButton_GestionarUsuarios.setFocusPainted(false);
		jButton_GestionarUsuarios.setBounds(25, 76, 120, 100);
		contentPane.add(jButton_GestionarUsuarios);
		
		JLabel lblInformacionUsuario = new JLabel("A\u00F1adir clientes");
		lblInformacionUsuario.setForeground(Color.BLACK);
		lblInformacionUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario.setBounds(25, 182, 120, 14);
		contentPane.add(lblInformacionUsuario);
		
		JButton jButton_GestionarUsuarios_1 = new JButton("");
		jButton_GestionarUsuarios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if((GestionarClientes.actualizar==0)){
					gestionarClientes = new GestionarClientes();
					gestionarClientes.setVisible(true);
				}
			else{
				gestionarClientes.setVisible(true);
				gestionarClientes.toFront();
				gestionarClientes.requestFocus();
			}
			}
		});
		jButton_GestionarUsuarios_1.setIcon(new ImageIcon(Capturista.class.getResource("/images/informationuser.png")));
		jButton_GestionarUsuarios_1.setFocusPainted(false);
		jButton_GestionarUsuarios_1.setBounds(185, 76, 120, 100);
		contentPane.add(jButton_GestionarUsuarios_1);
		
		JLabel lblInformacionUsuario_1 = new JLabel("Gestionar clientes");
		lblInformacionUsuario_1.setForeground(Color.BLACK);
		lblInformacionUsuario_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario_1.setBounds(185, 182, 120, 14);
		contentPane.add(lblInformacionUsuario_1);
		
		JButton jButton_GestionarUsuarios_2 = new JButton("");
		jButton_GestionarUsuarios_2.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {				
				Document documento = new Document();
				try {
					String ruta= System.getProperty("user.home");
					PdfWriter.getInstance(documento,new FileOutputStream(ruta + "/Desktop/"+ "usuarios_y" + "_equipos.pdf"));
					com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance(Capturista.class.getResource("/images/BannerPDF.jpg"));
					header.scaleToFit(650,1000);
					header.setAlignment(Chunk.ALIGN_CENTER);			
					documento.open();
					documento.add(header);							
					try {
						Connection cn0 = Conexion.conectar();
						PreparedStatement pst0 = cn0.prepareStatement("select id_cliente from clientes");
						ResultSet rs0 = pst0.executeQuery();
						while(rs0.next())
						{{
						int ID_0= Integer.parseInt(rs0.getString("id_cliente"));
						Paragraph parrafo = new Paragraph();
						parrafo.setAlignment(Paragraph.ALIGN_CENTER);
						parrafo.add("Informacion del cliente \n \n");
						parrafo.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,BaseColor.DARK_GRAY));
						documento.add(parrafo);						
						PdfPTable tablaCliente = new PdfPTable(5);
						tablaCliente.addCell("ID");
						tablaCliente.addCell("Nombre");
						tablaCliente.addCell("email");
						tablaCliente.addCell("Telefono");
						tablaCliente.addCell("Direccion");						
						try {							
							Connection cn =  Conexion.conectar();
							PreparedStatement pst = cn.prepareStatement(
									"select * from clientes where id_cliente = ?");
									  pst.setInt(1, ID_0);
							          int h=0;
							          ResultSet rs = pst.executeQuery();
							        	  while(rs.next()) {
							        		  tablaCliente.addCell(rs.getString(1));
							        		  tablaCliente.addCell(rs.getString(2));
							        		  tablaCliente.addCell(rs.getString(3));
							        		  tablaCliente.addCell(rs.getString(4));
							        		  tablaCliente.addCell(rs.getString(5));
							        		   h=1;
							        	  }
							         if(h==1)  {
							        	 documento.add(tablaCliente); }	         
							     	Paragraph parrafo1 = new Paragraph();
									parrafo1.setAlignment(Paragraph.ALIGN_CENTER);
									parrafo1.add("\n \n Equipos registrados \n \n");
									parrafo1.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,BaseColor.DARK_GRAY));
									documento.add(parrafo1);
									
									PdfPTable tablaEquipos = new PdfPTable(4);
									tablaEquipos.addCell("ID Equipo");
									tablaEquipos.addCell("Tipo");
									tablaEquipos.addCell("Marca");
									tablaEquipos.addCell("Estatus");									
									try {
										Connection cn1=  Conexion.conectar();
										PreparedStatement pst1 = cn1.prepareStatement(
												"select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = ?");
												  pst1.setInt(1, ID_0);
												    ResultSet rs1 = pst1.executeQuery();
												    if(rs1.next())  {
												   do  {
													    tablaEquipos.addCell(rs1.getString(1));
												    	tablaEquipos.addCell(rs1.getString(2));
												    	tablaEquipos.addCell(rs1.getString(3));
												    	tablaEquipos.addCell(rs1.getString(4));
												   }   while(rs1.next()); 
										        	 documento.add(tablaEquipos);
												   }
												    cn1.close();
									}
									catch(SQLException e0){
										System.out.print("error en base de datos informacion de capturista pdf 1 " + e0);
										JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
									}
									cn.close();
							     	Paragraph parrafo3 = new Paragraph();
									parrafo3.setAlignment(Paragraph.ALIGN_CENTER);
									parrafo3.add("\n \n");
									parrafo3.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,BaseColor.DARK_GRAY));
									documento.add(parrafo3);
									LineSeparator line = new LineSeparator();
									line.setLineColor(new BaseColor(100,100,100));
									line.setLineWidth(5);
									documento.add(line);
							}
							catch(SQLException e0)	{
								System.out.print("error en base de datos informacion de capturista pdf 2 " + e0);
								JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
							}
						}}
						cn0.close();
					}
					catch(SQLException e3)	{
						System.out.print("error en base de datos informacion de capturista pdf 3 " + e3);
						JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
					}
					documento.close();
					JOptionPane.showMessageDialog(null, "se creo el documento");
				} catch (DocumentException | IOException e0) {
					System.out.print("error hacer el pdf " + e0);
					JOptionPane.showMessageDialog(null, "Error en hacer el pdf contactar con un superior");
				}
			}
		});
		jButton_GestionarUsuarios_2.setIcon(new ImageIcon(Capturista.class.getResource("/images/impresora.png")));
		jButton_GestionarUsuarios_2.setFocusPainted(false);
		jButton_GestionarUsuarios_2.setBounds(343, 76, 120, 100);
		contentPane.add(jButton_GestionarUsuarios_2);
		
		JLabel lblInformacionUsuario_2 = new JLabel("Imprimir");
		lblInformacionUsuario_2.setForeground(Color.BLACK);
		lblInformacionUsuario_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblInformacionUsuario_2.setBounds(375, 182, 98, 14);
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
					System.out.print("error en base de datos capturista " + e3);
						JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
				}
		
		setTitle("Sesión de " + nombre_usuario);
		setLocationRelativeTo(null);
		setResizable(false);

		ImageIcon wallpaper = new ImageIcon(Capturista.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		this.repaint();
		
	}
}
