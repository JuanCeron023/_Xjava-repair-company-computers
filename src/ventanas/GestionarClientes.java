package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.Conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class GestionarClientes extends JFrame {

	private String user;
	private JPanel contentPane;
	public static int ID_ClienteUpdate;
	public static int actualizar =0;
	private DefaultTableModel model = new DefaultTableModel();
	private GestionarClientes gc; 
	private InformacionCliente informacion_cliente;
	private ArrayList<Integer> actualz = new ArrayList<Integer>();
	private HashMap<Integer, InformacionCliente> pruebas = new HashMap<Integer, InformacionCliente>();
	private JTable jTable_Clientes = new JTable(){
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;}};

	public GestionarClientes() {
		actualizar++;
		this.addWindowListener( new WindowAdapter() {
			   public void windowClosing(WindowEvent e) {
				  actualizar=0;
			     }
			  });
		gc= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblClientesRegistrados = new JLabel("Clientes Registrados");
		lblClientesRegistrados.setForeground(Color.WHITE);
		lblClientesRegistrados.setFont(new Font("Arial", Font.BOLD, 24));
		lblClientesRegistrados.setBounds(217, 10, 272, 35);
		contentPane.add(lblClientesRegistrados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 56, 530, 193);
		contentPane.add(scrollPane);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 624, 301);
		contentPane.add(jLabel_Wallpaper);
		
		user = Login.user;
		setTitle("Clientes registrados - Sesión de " + user);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(GestionarClientes.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionarClientes.class.getResource("/images/icon.png")));
		this.repaint();
		
		actualizar();
		scrollPane.setViewportView(jTable_Clientes);	
		jTable_Clientes.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			int fila_point = jTable_Clientes.rowAtPoint(e.getPoint());
			int columna_point =0;
			if(fila_point > -1){	
				ID_ClienteUpdate = (int) model.getValueAt(fila_point, columna_point);
			if((!actualz.contains(ID_ClienteUpdate)))	{
				informacion_cliente = new InformacionCliente(ID_ClienteUpdate,gc);
				informacion_cliente.setVisible(true);
				pruebas.put(ID_ClienteUpdate,informacion_cliente);
			}
		else	{
			InformacionCliente informacion_cliente2;
			informacion_cliente2 = pruebas.get(ID_ClienteUpdate);
			informacion_cliente2.setVisible(true);
			informacion_cliente2.toFront();
			informacion_cliente2.requestFocus();}}}
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
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select id_cliente, nombre_cliente, mail_cliente, tel_cliente, dir_cliente,ultima_modificacion from clientes");
			ResultSet rs = pst.executeQuery();
			while (model.getRowCount()>0) {
				model.removeRow(0);
	          }
			   while (model.getColumnCount()!=0)  {                
		        model.setColumnCount(0);
		      }
			model.addColumn(" ");
			model.addColumn("Nombre");
			model.addColumn("em@ail");
			model.addColumn("Telefono");
			model.addColumn("Direccion");
			model.addColumn("Modificado por");
			
			while(rs.next()){
				Object[] fila = new Object[6];
				for (int i = 0; i < 6; i++) {
					fila[i] = rs.getObject(i+1);
				}
				model.addRow(fila);
			}
			cn.close();
			jTable_Clientes.setModel(model);
			jTable_Clientes.getColumnModel().getColumn(0).setPreferredWidth(5);
			jTable_Clientes.getTableHeader().setResizingAllowed(false);
			jTable_Clientes.getTableHeader().setReorderingAllowed(false);
			
		} catch (SQLException e) {
			System.out.print("error en bd de gestionar clientes: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
	}

}
