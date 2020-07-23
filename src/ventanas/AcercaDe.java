package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class AcercaDe extends JFrame {

	private JPanel contentPane;
	private String user="";

	public AcercaDe() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		user = Login.user;
		setTitle("Administrador - Sesión de " + user);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon wallpaper = new ImageIcon(AcercaDe.class.getResource("/images/wallpaperPrincipal.jpg"));
		
		JLabel lblNewLabel = new JLabel("Sistema basado en el representado en el curso de Java de");
		lblNewLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		lblNewLabel.setBounds(37, 45, 397, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblElCanalDe = new JLabel("el canal de Youtube: La Geekipedia de Ernesto\u00AE");
		lblElCanalDe.setFont(new Font("Arial Narrow", Font.PLAIN, 16));
		lblElCanalDe.setBounds(57, 72, 387, 24);
		contentPane.add(lblElCanalDe);
		
		JLabel lblGraciasPorTu = new JLabel("Gracias");
		lblGraciasPorTu.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		lblGraciasPorTu.setBounds(175, 191, 87, 24);
		contentPane.add(lblGraciasPorTu);
		
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setForeground(Color.WHITE);
		jLabel_Wallpaper.setBounds(0, 0, 444, 271);
		contentPane.add(jLabel_Wallpaper);
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AcercaDe.class.getResource("/images/icon.png")));	
		this.repaint();	
	}

}
