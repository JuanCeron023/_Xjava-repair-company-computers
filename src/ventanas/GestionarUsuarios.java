package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.WindowConstants;

import java.sql.*;
import clases.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class GestionarUsuarios extends JFrame {

	private String user;
	private JPanel contentPane;
	public static String user_update0="";
	public static int actualizar =0;
	private DefaultTableModel model = new DefaultTableModel();
	private GestionarUsuarios gu; 
	private InformacionUsuario informacion_usuario;
	private String user_up1="";
	private int ID_actual=0;
	private JTable jTable_Usuarios = new JTable(){
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;}};
	
	public GestionarUsuarios() {
		actualizar++;
		this.addWindowListener( new WindowAdapter() {
			   public void windowClosing(WindowEvent e)  {
				  if((InformacionUsuario.actualizar.isEmpty()))  {
					  		dispose();
							 actualizar=0;
					  }  else {
					  JOptionPane.showMessageDialog(null, "Para salir, primero cierra todas las ventanas abiertas desde este panel");
				  }
			 }
			  });
		gu= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuarios Registrados");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(220, 10, 272, 35);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 80, 530, 193);
		contentPane.add(scrollPane);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 624, 301);
		contentPane.add(jLabel_Wallpaper);
		user = Login.user;
		
		setTitle("Usuarios registrados - Sesión de " + user);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(GestionarUsuarios.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionarUsuarios.class.getResource("/images/icon.png")));
		this.repaint();

		actualizar();
		
		jTable_Usuarios.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e){
			int fila_point = jTable_Usuarios.rowAtPoint(e.getPoint());
			int columna_point =2;
			if(fila_point > -1){
				user_up1 = (String) model.getValueAt(fila_point, columna_point);
				try {
					Connection cn0 = Conexion.conectar();
					PreparedStatement pst0 = cn0.prepareStatement("select id_usuario from usuarios where username = ?");
					pst0.setString(1, user_up1);
					ResultSet rs0 = pst0.executeQuery();
					if(rs0.next()){
						ID_actual = rs0.getInt("id_usuario"); }
					cn0.close();} 
				catch (SQLException e2) {
					System.out.print("error en bd de informacion usuario: " + e2);
					JOptionPane.showMessageDialog(null, "Error al mostrar la informacion"); }
			if((!InformacionUsuario.actualizar.contains(ID_actual)))	{
				informacion_usuario = new InformacionUsuario(gu,ID_actual);
				informacion_usuario.setVisible(true);
			}
			}
		}
		});
		scrollPane.setViewportView(jTable_Usuarios);		
	}
	
	public void actualizar()
	{
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select id_usuario, nombre_usuario, username, tipo_nivel, estatus from usuarios");
			ResultSet rs = pst.executeQuery();
			while (model.getRowCount()>0) {
				model.removeRow(0);
	          }
			   while (model.getColumnCount()!=0) {                
		        model.setColumnCount(0);
		      }
			model.addColumn("");
			model.addColumn("Nombre");
			model.addColumn("Username");
			model.addColumn("Permisos");
			model.addColumn("Estatus");
			
			while(rs.next()){
				Object[] fila = new Object[5];
				for (int i = 0; i < 5; i++) {
					fila[i] = rs.getObject(i+1);
				}
				model.addRow(fila);
			}
			cn.close();
			jTable_Usuarios.setModel(model);
			jTable_Usuarios.getColumnModel().getColumn(0).setPreferredWidth(5);
			jTable_Usuarios.getTableHeader().setResizingAllowed(false);
			jTable_Usuarios.getTableHeader().setReorderingAllowed(false);
			
		} catch (SQLException e) {
			System.out.print("error en bd de gestionar usarios: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		}
	}
	
}
