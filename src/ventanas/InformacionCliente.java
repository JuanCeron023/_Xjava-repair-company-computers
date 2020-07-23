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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

public class InformacionCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_email;
	private JTextField txt_telefono;
	private JTextField txt_direccion;
	private JTextField txt_ultimaModificacion;
	private String user="";
	private int IDcliente_update=0;
	public static int IDequipo=0;
	private DefaultTableModel model = new DefaultTableModel();
	private InformacionCliente gu;
	private GestionarClientes gc;
	private InformacionEquipo informacion_Equipo;
	private ArrayList<Integer> actualz = new ArrayList<Integer>();
	private HashMap<Integer, InformacionEquipo> pruebas = new HashMap<Integer, InformacionEquipo>();
	private JTable jTableEquipos= new JTable(){
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false; 
			}};

	public InformacionCliente(int ID_0, GestionarClientes gc1) {

		gc=gc1;
		gc.anadir(ID_0);
		this.addWindowListener( new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
					gc.quitar(ID_0); 
		    }
		  });
		gu=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInformacinDelCliente000 = new JLabel("");
		lblInformacinDelCliente000.setForeground(Color.WHITE);
		lblInformacinDelCliente000.setFont(new Font("Arial", Font.PLAIN, 24));
		lblInformacinDelCliente000.setBounds(10, 11, 40, 39);
		contentPane.add(lblInformacinDelCliente000);
		JLabel lblInformacinDelCliente = new JLabel("Informacion del cliente");
		lblInformacinDelCliente.setForeground(Color.WHITE);
		lblInformacinDelCliente.setFont(new Font("Arial", Font.BOLD, 24));
		lblInformacinDelCliente.setBounds(71, 7, 477, 37);
		contentPane.add(lblInformacinDelCliente);
		
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
		txt_nombre.setBounds(10, 86, 225, 29);
		contentPane.add(txt_nombre);
		
		JLabel lblEmil = new JLabel("Em@il:");
		lblEmil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmil.setBounds(10, 117, 67, 16);
		contentPane.add(lblEmil);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefono.setBounds(10, 170, 67, 16);
		contentPane.add(lblTelefono);
		
		JLabel lblDireccin = new JLabel("Ultima modificaci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin.setBounds(10, 290, 183, 16);
		contentPane.add(lblDireccin);
		
		JButton jButto_imprimirReporte = new JButton("");
		jButto_imprimirReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Document documento = new Document();
				try {
					String ruta= System.getProperty("user.home");
					PdfWriter.getInstance(documento,new FileOutputStream(ruta + "/Desktop/"+ txt_nombre.getText().trim() + "_equipos.pdf"));
					
					com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance(InformacionCliente.class.getResource("/images/BannerPDF.jpg"));
					header.scaleToFit(650,1000);
					header.setAlignment(Chunk.ALIGN_CENTER);
					
				Paragraph parrafo = new Paragraph();
				parrafo.setAlignment(Paragraph.ALIGN_CENTER);
				parrafo.add("Informacion del cliente \n \n");
				parrafo.setFont(FontFactory.getFont("Tahoma",14, Font.BOLD,BaseColor.DARK_GRAY));
				
				documento.open();
				documento.add(header);
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
							}
							catch(SQLException e0){
								System.out.print("error en base de datos informacion de cliente pdf 1 " + e0);
								JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
							}
					}
					catch(SQLException e0)	{
						System.out.print("error en base de datos informacion de cliente pdf " + e0);
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
		jButto_imprimirReporte.setIcon(new ImageIcon(InformacionCliente.class.getResource("/images/impresora.png")));
		jButto_imprimirReporte.setFocusPainted(false);
		jButto_imprimirReporte.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_imprimirReporte.setBounds(470, 252, 134, 88);
		contentPane.add(jButto_imprimirReporte);
		
		JLabel lblDireccin_1 = new JLabel("Direcci\u00F3n:");
		lblDireccin_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDireccin_1.setBounds(10, 226, 88, 16);
		contentPane.add(lblDireccin_1);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.CENTER);
		txt_email.setForeground(Color.WHITE);
		txt_email.setFont(new Font("Arial", Font.BOLD, 16));
		txt_email.setColumns(10);
		txt_email.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_email.setBackground(new Color(155, 155, 255));
		txt_email.setBounds(10, 135, 225, 29);
		contentPane.add(txt_email);
		
		txt_telefono = new JTextField();
		txt_telefono.setHorizontalAlignment(SwingConstants.CENTER);
		txt_telefono.setForeground(Color.WHITE);
		txt_telefono.setFont(new Font("Arial", Font.BOLD, 16));
		txt_telefono.setColumns(10);
		txt_telefono.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_telefono.setBackground(new Color(155, 155, 255));
		txt_telefono.setBounds(10, 186, 225, 29);
		contentPane.add(txt_telefono);
		
		txt_direccion = new JTextField();
		txt_direccion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_direccion.setForeground(Color.WHITE);
		txt_direccion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_direccion.setColumns(10);
		txt_direccion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_direccion.setBackground(new Color(155, 155, 255));
		txt_direccion.setBounds(10, 250, 225, 29);
		contentPane.add(txt_direccion);
		
		txt_ultimaModificacion = new JTextField();
		txt_ultimaModificacion.setEnabled(false);
		txt_ultimaModificacion.setEditable(false);
		txt_ultimaModificacion.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ultimaModificacion.setForeground(Color.WHITE);
		txt_ultimaModificacion.setFont(new Font("Arial", Font.BOLD, 16));
		txt_ultimaModificacion.setColumns(10);
		txt_ultimaModificacion.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_ultimaModificacion.setBackground(new Color(155, 155, 255));
		txt_ultimaModificacion.setBounds(10, 310, 225, 29);
		contentPane.add(txt_ultimaModificacion);
		
		JButton jButto_reparar = new JButton("Registrar Equipo");
		jButto_reparar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				RegistrarEquipo registrarEquipo = new RegistrarEquipo(gu,IDcliente_update);
				registrarEquipo.setVisible(true);
			}
		});
		jButto_reparar.setFocusPainted(false);
		jButto_reparar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_reparar.setBounds(285, 251, 175, 39);
		contentPane.add(jButto_reparar);
		
		JButton jButto_actualizar = new JButton("Actualizar cliente");
		jButto_actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				String nombre,mail,telefono,direccion;
				nombre = txt_nombre.getText().trim();
				mail = txt_email.getText().trim();
				telefono = txt_telefono.getText().trim();
				direccion = txt_direccion.getText().trim();
				if (nombre.equals("")||mail.equals("")||direccion.equals("")||
						telefono.equals("")){
					JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
				} else {
					try {	
					Connection cn =  Conexion.conectar();
					PreparedStatement pst = cn.prepareStatement(
							"UPDATE clientes SET nombre_cliente=?, mail_cliente=?, tel_cliente=?, dir_cliente=?, ultima_modificacion=? WHERE id_cliente = ?");
							  pst.setString(1, nombre);
							  pst.setString(2, mail);
							  pst.setString(3, telefono);
							  pst.setString(4, direccion);
							  pst.setString(5, user);
							  pst.setInt(6, ID_0);					  
					          pst.executeUpdate();
					          cn.close();
					          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
					          txt_ultimaModificacion.setText(user);
					          Limpiar();
					          gc.actualizar();
					         gc.quitar(ID_0);
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
		jButto_actualizar.setFocusPainted(false);
		jButto_actualizar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_actualizar.setBounds(285, 301, 175, 39);
		contentPane.add(jButto_actualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 56, 309, 159);
		contentPane.add(scrollPane);
		
		user = Login.user;
		IDcliente_update = GestionarClientes.ID_ClienteUpdate;
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(InformacionCliente.class.getResource("/images/wallpaperPrincipal.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformacionCliente.class.getResource("/images/icon.png")));
		this.repaint();
		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from clientes where id_cliente = ?");
			pst.setInt(1, IDcliente_update);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				setTitle("Sesion de "+user+ ". Informacion del cliente " + rs.getString("nombre_cliente"));
				lblInformacinDelCliente.setText("Informacion del cliente " + rs.getString("nombre_cliente"));
				txt_email.setText(rs.getString("mail_cliente"));
				txt_nombre.setText(rs.getString("nombre_cliente"));
				txt_direccion.setText(rs.getString("dir_cliente"));
				txt_ultimaModificacion.setText(rs.getString("ultima_modificacion"));
				txt_telefono.setText(rs.getString("tel_cliente"));
			}
			cn.close();
			
		} catch (SQLException e) {
			System.out.print("error en bd de informacion cliente: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
		actualizar();
		scrollPane.setViewportView(jTableEquipos);
		
		
		
			JLabel jLabel_Wallpaper = new JLabel("");
			jLabel_Wallpaper.setForeground(Color.WHITE);
			jLabel_Wallpaper.setBounds(0, 0, 624, 421);
			contentPane.add(jLabel_Wallpaper);
			Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
					jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
			jLabel_Wallpaper.setIcon(icono);
		jTableEquipos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				int fila_point = jTableEquipos.rowAtPoint(e.getPoint());
				int columna_point =0;
				if(fila_point > -1){
					IDequipo = (int) model.getValueAt(fila_point, columna_point);
					if((!actualz.contains(IDequipo)))	{
						informacion_Equipo = new InformacionEquipo(ID_0,IDequipo,gu);
						informacion_Equipo.setVisible(true);
						pruebas.put(IDequipo,informacion_Equipo);
					}
				else	{
					InformacionEquipo informacion_Equipo2;
					informacion_Equipo2 = pruebas.get(IDequipo);
					informacion_Equipo2.setVisible(true);
					informacion_Equipo2.toFront();
					informacion_Equipo2.requestFocus();}}}
			});
	}
	public void anadir(int dato){
	actualz.add(dato);
	}public void quitar(int dato){
		  for (int i = 0; i < actualz.size(); i++) {
				if(actualz.get(i)== dato) {	
					actualz.remove(i);pruebas.remove(dato);}}
		  }	
	public void actualizar()
	{
		try {
			Connection cn1 = Conexion.conectar();
			PreparedStatement pst1 = cn1.prepareStatement("select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = ?");
			pst1.setInt(1, IDcliente_update);
			ResultSet rs1 = pst1.executeQuery();
			while (model.getRowCount()>0) {
				model.removeRow(0);
	          }
			   while (model.getColumnCount()!=0)  {                
		        model.setColumnCount(0);
		      }
			model.addColumn(" ");
			model.addColumn("Tipo equipo");
			model.addColumn("Marca");
			model.addColumn("estatus");
			while(rs1.next()){
				Object[] fila = new Object[4];
				for (int i = 0; i < 4; i++) {
					fila[i] = rs1.getObject(i+1);
				}
				model.addRow(fila);
			}
			cn1.close();
			jTableEquipos.setModel(model);
			jTableEquipos.getColumnModel().getColumn(2).setPreferredWidth(16);
			jTableEquipos.getColumnModel().getColumn(0).setMinWidth(20);
			jTableEquipos.getColumnModel().getColumn(0).setMaxWidth(20);
			jTableEquipos.getColumnModel().getColumn(0).setPreferredWidth(20);
			jTableEquipos.getTableHeader().setResizingAllowed(false);
			jTableEquipos.getTableHeader().setReorderingAllowed(false);		
		} catch (SQLException e) {
			System.out.print("error en bd de informacion cliente: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		}
	}
	
	public void Limpiar()
	{
		txt_direccion.setText("");
		txt_email.setText("");
		txt_nombre.setText("");
		txt_telefono.setText("");
	}
}
