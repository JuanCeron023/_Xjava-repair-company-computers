package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.awt.event.ActionEvent;
import java.sql.*;
import clases.Conexion;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txt_user;
	private JPasswordField txt_password;
	public static String user = "";
	private String pass = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		if(!trueesunico()){
			JOptionPane.showMessageDialog(null,"Ya esta corriendo el programa");
	    } 
		else
		{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setTitle("Acceso al sistema");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel jLabel_Logo = new JLabel("");
		jLabel_Logo.setBounds(36, -12, 330, 342);
		contentPane.add(jLabel_Logo);
		
		txt_user = new JTextField();
		txt_user.setHorizontalAlignment(SwingConstants.CENTER);
		txt_user.setFont(new Font("Arial", Font.PLAIN, 18));
		txt_user.setBackground(new Color(102, 153, 255));
		txt_user.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_user.setForeground(Color.WHITE);
		txt_user.setBounds(95, 330, 210, 25);
		contentPane.add(txt_user);
		txt_user.setColumns(10);
		
		txt_password = new JPasswordField();
		txt_password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_password.setHorizontalAlignment(SwingConstants.CENTER);
		txt_password.setBackground(new Color(51, 153, 255));
		txt_password.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		txt_password.setForeground(Color.WHITE);
		txt_password.setBounds(128, 375, 150, 25);
		contentPane.add(txt_password);
		
		JButton jButton_Acceder = new JButton("Acceder");
		jButton_Acceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user = txt_user.getText().trim().toLowerCase();
				pass = txt_password.getText().trim();
				if (!user.equals("") || !pass.equals("")) {
					try {
						Connection cn = Conexion.conectar();
						PreparedStatement pst = cn.prepareStatement(
								"select tipo_nivel, estatus from usuarios where username = ? AND password = ?");
						  pst.setString(1, user);
				            pst.setString(2, pass);
				            ResultSet rs = pst.executeQuery();
				            if (rs.next()) {
				            	  String tipo_nivel = rs.getString("tipo_nivel");
				                  String estatus = rs.getString("estatus");
				                  
				            if (tipo_nivel.equalsIgnoreCase("Administrador") && estatus.equalsIgnoreCase("Activo")) {
								dispose();
								new Administrador().setVisible(true);
							} else if(tipo_nivel.equalsIgnoreCase("Capturista") && estatus.equalsIgnoreCase("Activo")) {
								dispose();
								new Capturista().setVisible(true);					
							}
				            else if(tipo_nivel.equalsIgnoreCase("Tecnico") && estatus.equalsIgnoreCase("Activo")) {
				            	dispose();
								new Tecnico().setVisible(true);
							}
							} else {
								JOptionPane.showMessageDialog(null, "Error al inciar sesion datos incorrectos");
							txt_user.setText("");
							txt_password.setText("");
							}
				            cn.close();
					} catch (SQLException e) {
						System.out.print("error en acceder: " + e);
						JOptionPane.showMessageDialog(null, "Error al inciar sesion");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Se debe rellenar los dos campos.");
				}
			}
		});
		jButton_Acceder.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButton_Acceder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jButton_Acceder.setBackground(Color.WHITE);
		jButton_Acceder.setBounds(150, 426, 115, 30);
		contentPane.add(jButton_Acceder);
		
		txt_password.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  jButton_Acceder.doClick();
            }});
		txt_user.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                  txt_password.requestFocusInWindow();
            }});
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 394, 521);
		contentPane.add(jLabel_Wallpaper);
		ImageIcon wallpaper = new ImageIcon(Login.class.getResource("/images/wallpaperPrincipal.jpg"));
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
													jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		this.repaint();
	
		ImageIcon wallpaper_logo = new ImageIcon(Login.class.getResource("/images/DS.png"));
		Icon icono_logo = new ImageIcon(wallpaper_logo.getImage().getScaledInstance(jLabel_Logo.getWidth(), 
											jLabel_Logo.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Logo.setIcon(icono_logo);
		this.repaint();
		}
	}
	private static boolean trueesunico() {
	    try {
	        final File file = new File("EjemploArchivo.txt");
	        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
	        final FileLock fileLock = randomAccessFile.getChannel().tryLock();
	        if (fileLock != null) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                public void run() {
	                    try {
	                        fileLock.release();
	                        randomAccessFile.close();
	                        file.delete();
	                    } catch (Exception e) {
	                    	JOptionPane.showMessageDialog(null, "error en el lock: " + "EjemploArchivo.txt" + ", "+ e);
	                    }
	                }
	            });
	            return true;
	        }
	    } catch (Exception e) {
	       	JOptionPane.showMessageDialog(null, "error en el lock: " + "EjemploArchivo.txt" + ", "+ e);
             }
	    return false;
	}
}
