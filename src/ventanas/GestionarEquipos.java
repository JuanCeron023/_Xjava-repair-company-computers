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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GestionarEquipos extends JFrame {

	private JPanel contentPane;
	private String user;
	public static int actualizar =0;
	private DefaultTableModel model = new DefaultTableModel();
	private GestionarEquipos GE; 
	private InformacionEquipo informacion_equipo;
	private int ID_actual=0;
	private ArrayList<Integer> actualz = new ArrayList<Integer>();
	private HashMap<Integer, InformacionEquipo> pruebas = new HashMap<Integer, InformacionEquipo>();
	private JTable jTable_equipos = new JTable(){
		public boolean isCellEditable(int rowIndex, int colIndex) {
			return false;}};
	private JComboBox cmb_marca;

	public GestionarEquipos() {
		actualizar++;
		this.addWindowListener( new WindowAdapter() {
			   public void windowClosing(WindowEvent e)  {
			   actualizar=0;
			 }
			  });
		GE= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEquiposRegistrados = new JLabel("Equipos Registrados");
		lblEquiposRegistrados.setForeground(Color.WHITE);
		lblEquiposRegistrados.setFont(new Font("Arial", Font.BOLD, 24));
		lblEquiposRegistrados.setBounds(46, 10, 272, 35);
		contentPane.add(lblEquiposRegistrados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 56, 530, 193);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(jTable_equipos);
		
		user= Login.user;
		setTitle("Equipos registrados - Sesión de " + user);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		ImageIcon wallpaper = new ImageIcon(GestionarEquipos.class.getResource("/images/wallpaperPrincipal.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestionarEquipos.class.getResource("/images/icon.png")));
		this.repaint();

		actualizar();
		
		jTable_equipos.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e)
		{
			int fila_point = jTable_equipos.rowAtPoint(e.getPoint());
			int columna_point =0;
			if(fila_point > -1){	
				ID_actual = (int) model.getValueAt(fila_point, columna_point);		
			if((!actualz.contains(ID_actual)))	{
				informacion_equipo = new InformacionEquipo(ID_actual,GE);
				informacion_equipo.setVisible(true);
				pruebas.put(ID_actual,informacion_equipo);
			}
		else	{
			InformacionEquipo informacion_equipo2;
			informacion_equipo2 = pruebas.get(ID_actual);
			informacion_equipo2.setVisible(true);
			informacion_equipo2.toFront();
			informacion_equipo2.requestFocus();}}}
		});
	
		scrollPane.setViewportView(jTable_equipos);		
		
		JButton jButto_equipo = new JButton("Aplicar filtro");
		jButto_equipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String seleccion=cmb_marca.getSelectedItem().toString();
				String query ="";
				model.setRowCount(0);
				model.setColumnCount(0);
				
				try {
					Connection cn = Conexion.conectar();
					if (seleccion.equalsIgnoreCase("Todos")) {
						query="select id_equipo, tipo_equipo, marca, estatus from equipos";
					} else {
						query="select id_equipo, tipo_equipo, marca, estatus from equipos where estatus= '"+ seleccion +"'";
					}
					PreparedStatement pst = cn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					
					model.addColumn("");
					model.addColumn("Tipo");
					model.addColumn("Marca");
					model.addColumn("Estatus");
					
					while(rs.next()){
						Object[] fila = new Object[4];
						for (int i = 0; i < 4; i++) {
							fila[i] = rs.getObject(i+1);
						}
						model.addRow(fila);
					}
					cn.close();
					jTable_equipos.setModel(model);
					jTable_equipos.getColumnModel().getColumn(0).setPreferredWidth(5);
					jTable_equipos.getTableHeader().setResizingAllowed(false);
					jTable_equipos.getTableHeader().setReorderingAllowed(false);
					
				} catch (SQLException e2) {
					System.out.print("error en bd de gestionar equipos: " + e2);
					JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
				}
			}
		});
		jButto_equipo.setFocusPainted(false);
		jButto_equipo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		jButto_equipo.setBounds(401, 260, 175, 39);
		contentPane.add(jButto_equipo);
		
	    cmb_marca = new JComboBox();
	    cmb_marca.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Nuevo ingreso", "No reparado", "En revision", "Reparado", "Entregado"}));
		cmb_marca.setBounds(401, 15, 163, 30);
		contentPane.add(cmb_marca);
		JLabel jLabel_Wallpaper = new JLabel("");
		jLabel_Wallpaper.setBounds(0, 0, 624, 301);
		contentPane.add(jLabel_Wallpaper);
		Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(), 
												jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
		jLabel_Wallpaper.setIcon(icono);
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
			PreparedStatement pst = cn.prepareStatement("select id_equipo, tipo_equipo, marca, estatus from equipos");
			ResultSet rs = pst.executeQuery();
			while (model.getRowCount()>0) {
				model.removeRow(0);
	          }
			   while (model.getColumnCount()!=0) {                
		        model.setColumnCount(0);
		      }
			model.addColumn("");
			model.addColumn("Tipo");
			model.addColumn("Marca");
			model.addColumn("Estatus");
			
			while(rs.next()){
				Object[] fila = new Object[4];
				for (int i = 0; i < 4; i++) {
					fila[i] = rs.getObject(i+1);
				}
				model.addRow(fila);
			}
			cn.close();
			jTable_equipos.setModel(model);
			jTable_equipos.getColumnModel().getColumn(0).setPreferredWidth(5);
			jTable_equipos.getTableHeader().setResizingAllowed(false);
			jTable_equipos.getTableHeader().setReorderingAllowed(false);
			
		} catch (SQLException e) {
			System.out.print("error en bd de gestionar equipos: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		}
	}
}
