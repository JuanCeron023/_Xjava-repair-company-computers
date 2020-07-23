package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.sql.*;
import java.util.ArrayList;

import clases.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class InformacionUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_email;
	private JTextField txt_telefono;
	private JTextField txt_username;
	private JTextField txt_RegistradoPor;
	private String user_u="";
	private String user="";
	private GestionarUsuarios gu;
	public static ArrayList<Integer> actualizar = new ArrayList<Integer>();
	private int ID_0=0;

	public InformacionUsuario(GestionarUsuarios gu1, int id_) {
		ID_0=id_;
		gu=gu1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informacion Usuario");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setBounds(52, 11, 477, 37);
		contentPane.add(lblNewLabel);
		
		JLabel jLabel_Nombre = new JLabel("Nombre:");
		jLabel_Nombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_Nombre.setBounds(21, 52, 82, 17);
		contentPane.add(jLabel_Nombre);
		
		JLabel jLabel_email = new JLabel("Em@il:");
		jLabel_email.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_email.setBounds(21, 144, 82, 17);
		contentPane.add(jLabel_email);
		
		JLabel jLabel_Telefono = new JLabel("Telefono:");
		jLabel_Telefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_Telefono.setBounds(21, 232, 82, 17);
		contentPane.add(jLabel_Telefono);
		
		JLabel jLabel_Nombre_3 = new JLabel("Username:");
		jLabel_Nombre_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_Nombre_3.setBounds(21, 317, 82, 17);
		contentPane.add(jLabel_Nombre_3);
		
		JLabel jLabel_Nombre_4 = new JLabel("Registrado Por:");
		jLabel_Nombre_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_Nombre_4.setBounds(305, 59, 158, 17);
		contentPane.add(jLabel_Nombre_4);
		
		JLabel jLabel_Nombre_5 = new JLabel("Tipo de usuario:");
		jLabel_Nombre_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_Nombre_5.setBounds(305, 147, 119, 17);
		contentPane.add(jLabel_Nombre_5);
		
		JLabel jLabel_Nombre_6 = new JLabel("Estatus:");
		jLabel_Nombre_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabel_Nombre_6.setBounds(305, 235, 82, 17);
		contentPane.add(jLabel_Nombre_6);
		
		txt_nombre = new JTextField();
		txt_nombre.setBackground(new Color(153, 102, 255));
		txt_nombre.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_nombre.setHorizontalAlignment(SwingConstants.CENTER);
		txt_nombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_nombre.setForeground(new Color(0, 0, 0));
		txt_nombre.setBounds(21, 90, 202, 27);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setHorizontalAlignment(SwingConstants.CENTER);
		txt_email.setForeground(Color.BLACK);
		txt_email.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_email.setColumns(10);
		txt_email.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_email.setBackground(new Color(153, 102, 255));
		txt_email.setBounds(21, 179, 202, 27);
		contentPane.add(txt_email);
		
		txt_telefono = new JTextField();
		txt_telefono.setHorizontalAlignment(SwingConstants.CENTER);
		txt_telefono.setForeground(Color.BLACK);
		txt_telefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_telefono.setColumns(10);
		txt_telefono.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_telefono.setBackground(new Color(153, 102, 255));
		txt_telefono.setBounds(21, 267, 202, 27);
		contentPane.add(txt_telefono);
		
		txt_username = new JTextField();
		txt_username.setHorizontalAlignment(SwingConstants.CENTER);
		txt_username.setForeground(Color.BLACK);
		txt_username.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_username.setColumns(10);
		txt_username.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_username.setBackground(new Color(153, 102, 255));
		txt_username.setBounds(21, 355, 202, 27);
		contentPane.add(txt_username);
		
		txt_RegistradoPor = new JTextField();
		txt_RegistradoPor.setEnabled(false);
		txt_RegistradoPor.setEditable(false);
		txt_RegistradoPor.setHorizontalAlignment(SwingConstants.CENTER);
		txt_RegistradoPor.setForeground(Color.BLACK);
		txt_RegistradoPor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txt_RegistradoPor.setColumns(10);
		txt_RegistradoPor.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_RegistradoPor.setBackground(new Color(153, 102, 255));
		txt_RegistradoPor.setBounds(305, 87, 202, 27);
		contentPane.add(txt_RegistradoPor);
		
		JComboBox cmb_niveles = new JComboBox();
		cmb_niveles.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Capturista", "Tecnico"}));
		cmb_niveles.setBounds(305, 183, 202, 27);
		contentPane.add(cmb_niveles);
		
		JButton jButton_Actualizar = new JButton("Cambiar Contrase\u00F1a");
		jButton_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				RestaurarPassword restaurarPassword = new RestaurarPassword(ID_0);
				restaurarPassword.setVisible(true);			
			}
		});
		jButton_Actualizar.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButton_Actualizar.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		jButton_Actualizar.setForeground(new Color(0, 0, 0));
		jButton_Actualizar.setBounds(305, 308, 202, 37);
		contentPane.add(jButton_Actualizar);
		
		JComboBox cmb_estatus = new JComboBox();
		cmb_estatus.setModel(new DefaultComboBoxModel(new String[] {"Activo", "Inactivo"}));
		cmb_estatus.setBounds(305, 271, 202, 23);
		contentPane.add(cmb_estatus);
		
		JButton jButton_Actualizar_1 = new JButton("Actualizar");
		jButton_Actualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int permisos_cmb,estatus_cmb,validacion =0;
				String nombre, mail, telefono, username,pass, permisos_string="",estatus_string="";
				mail = txt_email.getText().trim();
				username = txt_username.getText().trim();
				nombre = txt_nombre.getText().trim();
				telefono = txt_telefono.getText().trim();
				permisos_cmb = cmb_niveles.getSelectedIndex() + 1;
				estatus_cmb = cmb_estatus.getSelectedIndex() + 1;
				
			if (mail.equals("")||username.equals("")||nombre.equals("")||telefono.equals("") ||permisos_cmb == 0){
				JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios");
			} else {
				if (permisos_cmb==1) {
					permisos_string = "Administrador";
				} else if(permisos_cmb==2) {
					permisos_string = "Capturista";
				} else if(permisos_cmb==3) {
					permisos_string = "Tecnico";
				}
		
				if (estatus_cmb==1) {
					estatus_string = "Activo";
				} else if(estatus_cmb==2) {
					estatus_string = "Inactivo";
				}		
				try {
					Connection cn0 =  Conexion.conectar();				
					PreparedStatement pst0 = cn0.prepareStatement(
							"select username from usuarios where username=? and not id_usuario=?");
							  pst0.setString(1, username);
							  pst0.setInt(2, ID_0);
					          ResultSet rs = pst0.executeQuery();
					            if (rs.next()) {
					            	JOptionPane.showMessageDialog(null, "Ese username no esta disponible ");
									cn0.close();
					            }
					            else  {
									try {
										cn0.close();
									Connection cn1 =  Conexion.conectar();
									PreparedStatement pst1 = cn1.prepareStatement(
											"update usuarios set nombre_usuario=?, email=?, "
											+ "telefono=?, username=?, tipo_nivel=?, estatus=? where id_usuario=?");
											  pst1.setString(1, nombre);
											  pst1.setString(2, mail);
											  pst1.setString(3, telefono);
											  pst1.setString(4, username);
											  pst1.setString(5, permisos_string);
											  pst1.setString(6, estatus_string);
											  pst1.setInt(7, ID_0);						  
									          pst1.executeUpdate();
									          cn1.close();
									          JOptionPane.showMessageDialog(null, "Modificacion exitosa");
									          gu.actualizar();
									          dispose();
									          for (int i = 0; i < actualizar.size(); i++) {
													if(actualizar.get(i)== ID_0) {	
														  actualizar.remove(i);	}
									          }
									}
									catch(SQLException e){
										System.out.print("error en base de datos registro de informacion de usuarios " + e);
										JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
									}
									}
					            
				} catch (SQLException e) {
					System.out.print("error en base de datos registro de informacion de usuarios " + e);
					JOptionPane.showMessageDialog(null, "Error en la base de datos contactar con un superior");
				}	
			}
			}
		});
		jButton_Actualizar_1.setForeground(Color.BLACK);
		jButton_Actualizar_1.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		jButton_Actualizar_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButton_Actualizar_1.setBounds(412, 373, 202, 37);
		contentPane.add(jButton_Actualizar_1);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 624, 421);
		contentPane.add(jLabel_Wallpaper);
		user = Login.user;
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ImageIcon wallpaper = new ImageIcon(InformacionUsuario.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
				jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformacionUsuario.class.getResource("/images/icon.png")));
		this.repaint();	
		

		
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select * from usuarios where id_usuario = ?");
			pst.setInt(1, ID_0);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				ID_0 = rs.getInt("id_usuario");
				user_u=rs.getString("username");
				txt_email.setText(rs.getString("email"));
				txt_nombre.setText(rs.getString("nombre_usuario"));
				txt_RegistradoPor.setText(rs.getString("registrado_por"));
				txt_telefono.setText(rs.getString("telefono"));
				txt_username.setText(rs.getString("username"));
				cmb_niveles.setSelectedItem(rs.getString("tipo_nivel"));
				cmb_estatus.setSelectedItem(rs.getString("estatus"));
			}
			cn.close();
			
		} catch (SQLException e) {
			System.out.print("error en bd de informacion usuario: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		
		}
		setTitle("Sesión de " + user + ". Información de: " + user_u);
		lblNewLabel.setText("Informacion del usuario " + user_u);
		actualizar.add(ID_0);
		this.addWindowListener( new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
				   for (int i = 0; i < actualizar.size(); i++) {
					if(actualizar.get(i)== ID_0) {	
						  actualizar.remove(i);	}
			}
		    }
		  });
	}
}
