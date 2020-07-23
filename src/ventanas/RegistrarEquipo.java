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
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import clases.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;


public class RegistrarEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_modelo;
	private JTextField txt_num_serie;
	private int IDcliente_update=0;
	private String user="",nom_cliente="";
	private InformacionCliente IC_;

	public RegistrarEquipo(	InformacionCliente gu1, int ID_0) {	
		IC_=gu1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacinDelCliente = new JLabel("Informaci\u00F3n del cliente");
		lblInformacinDelCliente.setForeground(Color.WHITE);
		lblInformacinDelCliente.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente.setBounds(10, 11, 309, 29);
		contentPane.add(lblInformacinDelCliente);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		lblNombreDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDelCliente.setBounds(10, 64, 125, 16);
		contentPane.add(lblNombreDelCliente);
		
		txt_nombre = new JTextField();
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setColumns(10);
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 86, 225, 29);
		contentPane.add(txt_nombre);
		
		JLabel lblTipoDeEquipo = new JLabel("Tipo de equipo:");
		lblTipoDeEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoDeEquipo.setBounds(10, 117, 140, 16);
		contentPane.add(lblTipoDeEquipo);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(10, 170, 67, 16);
		contentPane.add(lblMarca);
		
		JLabel lblNumeroDeSerie = new JLabel("Numero de serie:");
		lblNumeroDeSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroDeSerie.setBounds(10, 290, 183, 16);
		contentPane.add(lblNumeroDeSerie);
		
		JLabel lblDireccin_1 = new JLabel("Modelo:");
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 226, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_modelo = new JTextField();
		txt_modelo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_modelo.setForeground(Color.WHITE);
		txt_modelo.setFont(new Font("Arial", Font.BOLD, 16));
		txt_modelo.setColumns(10);
		txt_modelo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_modelo.setBackground(new Color(155, 155, 255));
		txt_modelo.setBounds(10, 250, 225, 29);
		contentPane.add(txt_modelo);
		
		txt_num_serie = new JTextField();
		txt_num_serie.setHorizontalAlignment(SwingConstants.CENTER);
		txt_num_serie.setForeground(Color.WHITE);
		txt_num_serie.setFont(new Font("Arial", Font.BOLD, 16));
		txt_num_serie.setColumns(10);
		txt_num_serie.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_num_serie.setBackground(new Color(155, 155, 255));
		txt_num_serie.setBounds(10, 310, 225, 29);
		contentPane.add(txt_num_serie);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 73, 309, 233);
		contentPane.add(scrollPane);
		
		JTextPane txtp_descripcion = new JTextPane();
		scrollPane.setViewportView(txtp_descripcion);
		
		JComboBox cmb_tipoEquipo = new JComboBox();
		cmb_tipoEquipo.setModel(new DefaultComboBoxModel(new String[] {"Laptop", "Desktop", "Impresora", "Multifuncional"}));
		cmb_tipoEquipo.setBounds(10, 139, 183, 29);
		contentPane.add(cmb_tipoEquipo);
		
		JComboBox cmb_marca = new JComboBox();
		cmb_marca.setModel(new DefaultComboBoxModel(new String[] {"Acer", "Alienware", "Apple", "Asus", "Dell", "HP", "Lenovo", "Toshiba", "Samsung"}));
		cmb_marca.setBounds(10, 195, 183, 29);
		contentPane.add(cmb_marca);
		
		JLabel lblDescripcionDelEquipo = new JLabel("Descripcion del equipo:");
		lblDescripcionDelEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescripcionDelEquipo.setBounds(285, 46, 175, 16);
		contentPane.add(lblDescripcionDelEquipo);
		
		user = Login.user;
		IDcliente_update = ID_0;
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(RegistrarEquipo.class.getResource("/images/wallpaperPrincipal.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarEquipo.class.getResource("/images/icon.png")));
		this.repaint();
		try {
			Connection cn =  Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_cliente from clientes where id_cliente = ?");
					  pst.setInt(1, IDcliente_update);
			            ResultSet rs = pst.executeQuery();
			            if(rs.next()) {
			            	nom_cliente= rs.getString("nombre_cliente");
			            }
		} catch (SQLException e) {
			System.out.print("error en base de datos registro de equipos 1" + e);
			JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
		}	
		setTitle("cliente: " + nom_cliente);
		txt_nombre.setText(nom_cliente);		
		
		JButton jButto_registrar = new JButton("Registrar Equipo");
		jButto_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				int permisos_cmb,validacion =0;
				String tipo_equipo, marca, modelo, num_serie, dia_ingreso, mes_ingreso, anio_ingreso,estatus,observaciones;
				estatus = "Nuevo ingreso";
				num_serie = txt_num_serie.getText().trim();
				observaciones = txtp_descripcion.getText().trim();
				modelo = txt_modelo.getText().trim();
				tipo_equipo = cmb_tipoEquipo.getSelectedItem().toString();
				marca= cmb_marca.getSelectedItem().toString();				
				Calendar calendar = Calendar.getInstance();				
				dia_ingreso = Integer.toString(calendar.get(Calendar.DATE));
				mes_ingreso = Integer.toString(calendar.get(Calendar.MONTH));
				anio_ingreso = Integer.toString(calendar.get(Calendar.YEAR));					
				
			if (modelo.equals("")||observaciones.equals("")||num_serie.equals("")){
				validacion++;
				JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				
			} else {
				try {
					Connection cn1 =  Conexion.conectar();
					PreparedStatement pst1 = cn1.prepareStatement(
							"insert into equipos values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					  pst1.setInt(1, 0);
					  pst1.setInt(2, IDcliente_update);
					  pst1.setString(3, tipo_equipo);
					  pst1.setString(4, marca);
					  pst1.setString(5, modelo);
					  pst1.setString(6, num_serie);
					  pst1.setString(7, dia_ingreso);
					  pst1.setString(8, mes_ingreso);
					  pst1.setString(9, anio_ingreso);
					  pst1.setString(10, observaciones);
					  pst1.setString(11, estatus);
					  pst1.setString(12, user);
					  pst1.setString(13, "");
					  pst1.setString(14, "");
			          pst1.executeUpdate();
			          cn1.close();
			          JOptionPane.showMessageDialog(null, "registro exitoso");
			          IC_.actualizar();
			          dispose();				
					}				
				catch (SQLException e1) {
					System.out.print("error en base de datos registro de equpos 2" + e1);
					JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
				}	
			}			
			}			
		});
		jButto_registrar.setFocusPainted(false);
		jButto_registrar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_registrar.setBounds(419, 331, 175, 39);
		contentPane.add(jButto_registrar);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 624, 421);
		contentPane.add(jLabel_Wallpaper);
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono); 
		lblInformacinDelCliente.setText("");
		}
	
}
