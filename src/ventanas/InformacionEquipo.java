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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;

import com.itextpdf.text.pdf.ICachedColorSpace;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import java.util.ArrayList;

import clases.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformacionEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_numero_serie;
	private JTextField txt_modelo;
	private JTextField txt_ultima_modificacion;
	private JTextField txt_fechas;
	private JTextPane txtp_dañoobservaciones;
	private JTextPane txtp_comentariotecnico;
	private JComboBox cmb_estatus;
	private JComboBox cmb_tipoEquipo;
	private JComboBox cmb_marca;
	private JLabel lblDaoReportadoY = new JLabel();
	private JLabel lblComentarioYActualizacion = new JLabel();
	private int IDCliente_Update=0, IDequipo=0;
	private String user="",nom_cliente="";
	private InformacionCliente IC;
	private GestionarEquipos GE;
	
	public InformacionEquipo(int ID_cliente, int ID_equipotro, InformacionCliente ic) {
		IC=ic;
		IC.anadir(ID_equipotro);
		this.addWindowListener( new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
					IC.quitar(ID_equipotro);}
		  });
		user= Login.user;
		IDequipo= ID_equipotro;
		IDCliente_Update = ID_cliente;
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacinDelCliente000 = new JLabel("");
		lblInformacinDelCliente000.setForeground(Color.WHITE);
		lblInformacinDelCliente000.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente000.setBounds(10, 11, 40, 39);
		contentPane.add(lblInformacinDelCliente000);
		
		JLabel lblInformacionDelEquipo = new JLabel("Informacion del equipo");
		lblInformacionDelEquipo.setForeground(Color.WHITE);
		lblInformacionDelEquipo.setFont(new Font("Arial", Font.BOLD, 24));
		lblInformacionDelEquipo.setBounds(10, 0, 518, 37);
		contentPane.add(lblInformacionDelEquipo);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		lblNombreDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDelCliente.setBounds(10, 64, 136, 16);
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
		
		JLabel lblTipoEquipo = new JLabel("Tipo equipo:");
		lblTipoEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoEquipo.setBounds(10, 132, 110, 16);
		contentPane.add(lblTipoEquipo);
		
		JLabel lblNumeroDeSerie = new JLabel("Numero de serie:");
		lblNumeroDeSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroDeSerie.setBounds(10, 257, 156, 16);
		contentPane.add(lblNumeroDeSerie);
		
		JLabel lblDireccin = new JLabel("Ultima modificaci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(10, 377, 183, 16);
		contentPane.add(lblDireccin);
		
		JLabel lblDireccin_1 = new JLabel("Modelo:");
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 313, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_numero_serie = new JTextField();
		txt_numero_serie.setHorizontalAlignment(SwingConstants.CENTER);
		txt_numero_serie.setForeground(Color.WHITE);
		txt_numero_serie.setFont(new Font("Arial", Font.BOLD, 16));
		txt_numero_serie.setColumns(10);
		txt_numero_serie.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_numero_serie.setBackground(new Color(155, 155, 255));
		txt_numero_serie.setBounds(10, 273, 225, 29);
		contentPane.add(txt_numero_serie);
		
		txt_modelo = new JTextField();
		txt_modelo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_modelo.setForeground(Color.WHITE);
		txt_modelo.setFont(new Font("Arial", Font.BOLD, 16));
		txt_modelo.setColumns(10);
		txt_modelo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_modelo.setBackground(new Color(155, 155, 255));
		txt_modelo.setBounds(10, 337, 225, 29);
		contentPane.add(txt_modelo);
		
		txt_ultima_modificacion = new JTextField();
		txt_ultima_modificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ultima_modificacion.setForeground(Color.WHITE);
		txt_ultima_modificacion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_ultima_modificacion.setEnabled(false);
		txt_ultima_modificacion.setEditable(false);
		txt_ultima_modificacion.setColumns(10);
		txt_ultima_modificacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_ultima_modificacion.setBackground(new Color(155, 155, 255));
		txt_ultima_modificacion.setBounds(10, 397, 225, 29);
		contentPane.add(txt_ultima_modificacion);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 137, 309, 127);
		contentPane.add(scrollPane);
		
	    txtp_dañoobservaciones = new JTextPane();
		scrollPane.setViewportView(txtp_dañoobservaciones);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(10, 190, 156, 16);
		contentPane.add(lblMarca);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(282, 301, 309, 135);
		contentPane.add(scrollPane_1);
		
	    txtp_comentariotecnico = new JTextPane();
		txtp_comentariotecnico.setEditable(false);
		scrollPane_1.setViewportView(txtp_comentariotecnico);
		
		lblComentarioYActualizacion = new JLabel("Comentario y actualizacion del tecnico:");
		lblComentarioYActualizacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComentarioYActualizacion.setBounds(282, 275, 294, 16);
		contentPane.add(lblComentarioYActualizacion);
		
		lblDaoReportadoY = new JLabel("Da\u00F1o reportado y observaciones:");
		lblDaoReportadoY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDaoReportadoY.setBounds(282, 117, 294, 16);
		contentPane.add(lblDaoReportadoY);
		
		txt_fechas = new JTextField();
		txt_fechas.setHorizontalAlignment(SwingConstants.CENTER);
		txt_fechas.setForeground(Color.WHITE);
		txt_fechas.setFont(new Font("Arial", Font.BOLD, 16));
		txt_fechas.setColumns(10);
		txt_fechas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_fechas.setBackground(new Color(155, 155, 255));
		txt_fechas.setBounds(282, 77, 146, 29);
		contentPane.add(txt_fechas);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de ingreso:");
		lblFechaDeIngreso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeIngreso.setBounds(282, 55, 136, 16);
		contentPane.add(lblFechaDeIngreso);
		
		JLabel lblFechaDeIngreso_1 = new JLabel("Estatus:");
		lblFechaDeIngreso_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeIngreso_1.setBounds(461, 55, 136, 16);
		contentPane.add(lblFechaDeIngreso_1);
		
		cmb_marca = new JComboBox();
		cmb_marca.setModel(new DefaultComboBoxModel(new String[] {"Acer", "Alienware", "Apple", "Asus", "Dell", "HP", "Lenovo", "Toshiba", "Samsung"}));
		cmb_marca.setBounds(10, 217, 183, 29);
		contentPane.add(cmb_marca);
		
		cmb_tipoEquipo = new JComboBox();
		cmb_tipoEquipo.setModel(new DefaultComboBoxModel(new String[] {"Laptop", "Desktop", "Impresora", "Multifuncional"}));
		cmb_tipoEquipo.setBounds(10, 159, 183, 29);
		contentPane.add(cmb_tipoEquipo);
		
		cmb_estatus = new JComboBox();
		cmb_estatus.setModel(new DefaultComboBoxModel(new String[] {"Nuevo ingreso", "No reparado", "En revision", "Reparado", "Entregado"}));
		cmb_estatus.setBounds(461, 79, 130, 29);
		contentPane.add(cmb_estatus);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(InformacionEquipo.class.getResource("/images/wallpaperPrincipal.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformacionEquipo.class.getResource("/images/icon.png")));	
		this.repaint();	
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select nombre_cliente from clientes where id_cliente = ?");
			pst.setInt(1, IDCliente_Update);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				setTitle("Sesion de "+user+ ". Informacion del cliente " + rs.getString("nombre_cliente"));
				txt_nombre.setText(rs.getString("nombre_cliente"));
				nom_cliente= rs.getString("nombre_cliente");
				lblInformacionDelEquipo.setText("Informacion del equipo del cliente" + nom_cliente);
			}
			cn.close();
			
		} catch (SQLException e0) {
			System.out.print("error en bd de informacion equipos: " + e0);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
		
		actualizar();
		
		JButton jButto_equipo = new JButton("Actualizar equipo");
		jButto_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				String tipo_equipo,marca,estatus,modelo,numero_serie,observaciones;		
				
				tipo_equipo = cmb_tipoEquipo.getSelectedItem().toString();
				estatus = cmb_estatus.getSelectedItem().toString();
				marca = cmb_marca.getSelectedItem().toString();
				modelo= txt_modelo.getText().trim();
				numero_serie= txt_numero_serie.getText().trim();
				observaciones= txtp_dañoobservaciones.getText().trim();
				if (tipo_equipo.equals("")||estatus.equals("")||marca.equals("")||numero_serie.equals("")||observaciones.equals("")||
						modelo.equals("")){
					JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				} else {
					try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"UPDATE equipos SET tipo_equipo=?, marca=?, modelo=?, num_serie=?, observaciones=?, estatus=?, ultima_modificacion=? WHERE id_equipo = ?");
							  pst.setString(1, tipo_equipo);
							  pst.setString(2, marca);
							  pst.setString(3, modelo);
							  pst.setString(4, numero_serie);
							  pst.setString(5, observaciones);
							  pst.setString(6, estatus);
							  pst.setString(7, user);
							  pst.setInt(8, IDequipo);						  
					          pst.executeUpdate();
					          cn.close();
					          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
					          Limpiar();
					          actualizar();
					          IC.actualizar();
					          IC.quitar(IDequipo);
					          dispose();     
					}
					catch(SQLException e0)
					{
						System.out.print("error en base de datos informacion de cliente " + e0);
						JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
					}	
			}
			}
		});
		jButto_equipo.setFocusPainted(false);
		jButto_equipo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_equipo.setBounds(469, 447, 175, 39);
		contentPane.add(jButto_equipo);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 664, 501);	
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		contentPane.add(jLabel_Wallpaper);
		
	}
	
	public InformacionEquipo( int ID_equipotro, GestionarEquipos ge) {
		GE=ge;
		GE.anadir(ID_equipotro);
		this.addWindowListener( new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
				 GE.quitar(ID_equipotro);
				   }
		  });
		user= Login.user;
		IDequipo= ID_equipotro;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacinDelCliente000 = new JLabel("");
		lblInformacinDelCliente000.setForeground(Color.WHITE);
		lblInformacinDelCliente000.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente000.setBounds(10, 11, 40, 39);
		contentPane.add(lblInformacinDelCliente000);
		
		JLabel lblInformacionDelEquipo = new JLabel("Informacion del equipo");
		lblInformacionDelEquipo.setForeground(Color.WHITE);
		lblInformacionDelEquipo.setFont(new Font("Arial", Font.BOLD, 24));
		lblInformacionDelEquipo.setBounds(10, 0, 518, 37);
		contentPane.add(lblInformacionDelEquipo);
		
		JLabel lblNombreDelCliente = new JLabel("Nombre del cliente:");
		lblNombreDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombreDelCliente.setBounds(10, 64, 136, 16);
		contentPane.add(lblNombreDelCliente);
		
		txt_nombre = new JTextField();
		txt_nombre.setEditable(false);
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setForeground(Color.WHITE);
		txt_nombre.setFont(new Font("Arial", Font.BOLD, 16));
		txt_nombre.setColumns(10);
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setBackground(new Color(155, 155, 255));
		txt_nombre.setBounds(10, 86, 225, 29);
		contentPane.add(txt_nombre);
		
		JLabel lblTipoEquipo = new JLabel("Tipo equipo:");
		lblTipoEquipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipoEquipo.setBounds(10, 132, 110, 16);
		contentPane.add(lblTipoEquipo);
		
		JLabel lblNumeroDeSerie = new JLabel("Numero de serie:");
		lblNumeroDeSerie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroDeSerie.setBounds(10, 257, 156, 16);
		contentPane.add(lblNumeroDeSerie);
		
		JLabel lblDireccin = new JLabel("Ultima modificaci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(10, 377, 183, 16);
		contentPane.add(lblDireccin);
		
		JLabel lblDireccin_1 = new JLabel("Modelo:");
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 313, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_numero_serie = new JTextField();
		txt_numero_serie.setEditable(false);
		txt_numero_serie.setHorizontalAlignment(SwingConstants.CENTER);
		txt_numero_serie.setForeground(Color.WHITE);
		txt_numero_serie.setFont(new Font("Arial", Font.BOLD, 16));
		txt_numero_serie.setColumns(10);
		txt_numero_serie.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_numero_serie.setBackground(new Color(155, 155, 255));
		txt_numero_serie.setBounds(10, 273, 225, 29);
		contentPane.add(txt_numero_serie);
		
		txt_modelo = new JTextField();
		txt_modelo.setEditable(false);
		txt_modelo.setHorizontalAlignment(SwingConstants.CENTER);
		txt_modelo.setForeground(Color.WHITE);
		txt_modelo.setFont(new Font("Arial", Font.BOLD, 16));
		txt_modelo.setColumns(10);
		txt_modelo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_modelo.setBackground(new Color(155, 155, 255));
		txt_modelo.setBounds(10, 337, 225, 29);
		contentPane.add(txt_modelo);
		
		txt_ultima_modificacion = new JTextField();
		txt_ultima_modificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ultima_modificacion.setForeground(Color.WHITE);
		txt_ultima_modificacion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_ultima_modificacion.setEnabled(false);
		txt_ultima_modificacion.setEditable(false);
		txt_ultima_modificacion.setColumns(10);
		txt_ultima_modificacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_ultima_modificacion.setBackground(new Color(155, 155, 255));
		txt_ultima_modificacion.setBounds(10, 397, 225, 29);
		contentPane.add(txt_ultima_modificacion);
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 137, 309, 127);
		contentPane.add(scrollPane);
		
	    txtp_dañoobservaciones = new JTextPane();
	    txtp_dañoobservaciones.setEditable(false);
		scrollPane.setViewportView(txtp_dañoobservaciones);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMarca.setBounds(10, 190, 156, 16);
		contentPane.add(lblMarca);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(282, 301, 309, 135);
		contentPane.add(scrollPane_1);
		
	    txtp_comentariotecnico = new JTextPane();
		scrollPane_1.setViewportView(txtp_comentariotecnico);
		
		lblComentarioYActualizacion = new JLabel("Comentario y actualizacion del tecnico:");
		lblComentarioYActualizacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComentarioYActualizacion.setBounds(282, 275, 294, 16);
		contentPane.add(lblComentarioYActualizacion);
		
		lblDaoReportadoY = new JLabel("Da\u00F1o reportado y observaciones:");
		lblDaoReportadoY.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDaoReportadoY.setBounds(282, 117, 294, 16);
		contentPane.add(lblDaoReportadoY);
		
		txt_fechas = new JTextField();
		txt_fechas.setEnabled(false);
		txt_fechas.setHorizontalAlignment(SwingConstants.CENTER);
		txt_fechas.setForeground(Color.WHITE);
		txt_fechas.setFont(new Font("Arial", Font.BOLD, 16));
		txt_fechas.setColumns(10);
		txt_fechas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_fechas.setBackground(new Color(155, 155, 255));
		txt_fechas.setBounds(282, 77, 146, 29);
		contentPane.add(txt_fechas);
		
		JLabel lblFechaDeIngreso = new JLabel("Fecha de ingreso:");
		lblFechaDeIngreso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeIngreso.setBounds(282, 55, 136, 16);
		contentPane.add(lblFechaDeIngreso);
		
		JLabel lblFechaDeIngreso_1 = new JLabel("Estatus:");
		lblFechaDeIngreso_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeIngreso_1.setBounds(461, 55, 136, 16);
		contentPane.add(lblFechaDeIngreso_1);
		
		cmb_marca = new JComboBox();
		cmb_marca.setEnabled(false);
		cmb_marca.setModel(new DefaultComboBoxModel(new String[] {"Acer", "Alienware", "Apple", "Asus", "Dell", "HP", "Lenovo", "Toshiba", "Samsung"}));
		cmb_marca.setBounds(10, 217, 183, 29);
		contentPane.add(cmb_marca);
		
		cmb_tipoEquipo = new JComboBox();
		cmb_tipoEquipo.setEnabled(false);
		cmb_tipoEquipo.setModel(new DefaultComboBoxModel(new String[] {"Laptop", "Desktop", "Impresora", "Multifuncional"}));
		cmb_tipoEquipo.setBounds(10, 159, 183, 29);
		contentPane.add(cmb_tipoEquipo);
		
		cmb_estatus = new JComboBox();
		cmb_estatus.setModel(new DefaultComboBoxModel(new String[] {"Nuevo ingreso", "No reparado", "En revision", "Reparado", "Entregado"}));
		cmb_estatus.setBounds(461, 79, 130, 29);
		contentPane.add(cmb_estatus);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(InformacionEquipo.class.getResource("/images/wallpaperPrincipal.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformacionEquipo.class.getResource("/images/icon.png")));	
		this.repaint();	
				setTitle("Sesion de "+user+ ". Informacion del equipo de ID: " + IDequipo);
				lblInformacionDelEquipo.setText("Informacion del equipo: " + IDequipo);

		actualizar();
		
		JButton jButto_equipo = new JButton("Actualizar equipo");
		jButto_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {					
				String estatus,observacionesTecnico;		
				estatus = cmb_estatus.getSelectedItem().toString();
				observacionesTecnico= txtp_comentariotecnico.getText().trim();
				if (estatus.equals("")||estatus.equals("")||observacionesTecnico.equals("")){
					JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				} else {
					try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"UPDATE equipos SET estatus=?, comentarios_tecnicos=?, revision_tecnica=? WHERE id_equipo = ?");
							  pst.setString(1, estatus);
							  pst.setString(2, observacionesTecnico);
							  pst.setString(3, user);
							  pst.setInt(4, IDequipo);						  
					          pst.executeUpdate();
					          cn.close();
					          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
					          Limpiar();
					          actualizar();
					        GE.actualizar();
					        GE.quitar(IDequipo);
					          dispose();     
					}
					catch(SQLException e0)
					{
						System.out.print("error en base de datos informacion de cliente " + e0);
						JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
					}	
			}
			}
		});
		jButto_equipo.setFocusPainted(false);
		jButto_equipo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_equipo.setBounds(469, 447, 175, 39);
		contentPane.add(jButto_equipo);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 664, 501);	
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		contentPane.add(jLabel_Wallpaper);
		
	}
	
	public void actualizar()
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from equipos where id_equipo = ?");
			pst.setInt(1, IDequipo);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				cmb_tipoEquipo.setSelectedItem(rs.getString("tipo_equipo"));
				cmb_marca.setSelectedItem(rs.getString("marca"));
				cmb_estatus.setSelectedItem(rs.getString("estatus"));
				txt_modelo.setText(rs.getString("modelo"));
				txt_numero_serie.setText(rs.getString("num_serie"));
				txt_ultima_modificacion.setText(rs.getString("ultima_modificacion"));
				String dia="",anio="";
				int mes=0;
				dia= rs.getString("dia_ingreso");
				mes= rs.getInt("mes_ingreso");
				anio= rs.getString("annio_ingreso");
				txt_fechas.setText(dia+"/"+(mes+1)+"/"+anio);
				txtp_dañoobservaciones.setText(rs.getString("observaciones"));
				txtp_comentariotecnico.setText(rs.getString("comentarios_tecnicos"));
				lblComentarioYActualizacion.setText("Comentario y actualizacion del tecnico: " + rs.getString("revision_tecnica"));
			}
			cn.close();
			
		} catch (SQLException e) {
			System.out.print("error en bd de informacion equipo: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
	}
	
	public void Limpiar()
	{
		txt_fechas.setText("");
		txt_modelo.setText("");
		txt_nombre.setText("");
		txt_numero_serie.setText("");
		txt_ultima_modificacion.setText("");
		txtp_comentariotecnico.setText("");
		txtp_dañoobservaciones.setText("");
	}
	
}
