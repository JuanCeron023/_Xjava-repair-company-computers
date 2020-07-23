package ventanas;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import clases.Conexion;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javax.swing.SwingUtilities;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.scene.chart.*;

public class TecnicoGraficoUno {
	public static int actualizar=0;
	
    private static void initAndShowGUI() {
    	actualizar=1;
        JFrame frame = new JFrame("Grafico de marcas");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(520, 450);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Administrador.class.getResource("/images/icon.png")));
        frame.addWindowListener( new WindowAdapter() {
			   public void windowClosing(WindowEvent e) {
				   frame.setVisible(false);actualizar=0;
		}});
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxPanel);
            }});
        }

    private static void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {	
    	HashMap<String, Integer> marcas_hash = new HashMap<String, Integer>();
		Set<String> marcas_set = new HashSet<String>();
		int porcentaje=0;
		String marcaTemp="";
    	try {
			Connection cn = Conexion.conectar();
			PreparedStatement pst = cn.prepareStatement("select marca from equipos");
			ResultSet rs = pst.executeQuery();		
			while(rs.next()){
				marcaTemp = (rs.getString("marca"));
				if(!marcas_set.add(marcaTemp)){
					int tem=0;
					tem = marcas_hash.get(marcaTemp) +1;
					marcas_hash.put(marcaTemp, (tem));
				}
				else {marcas_hash.put(marcaTemp, (1));}}
			cn.close();}
    		catch (SQLException e) {
			System.out.print("error en bd de graficos: " + e);
			JOptionPane.showMessageDialog(null, "Error al mostrar la informacion");
		}
        Scene scene = new Scene(new Group());
            ObservableList<PieChart.Data> marcas =FXCollections.observableArrayList();
        	for (String palabra : marcas_set)  {
        		porcentaje+=marcas_hash.get(palabra);
                marcas.add(new PieChart.Data(palabra,marcas_hash.get(palabra)));
    		}
            final PieChart chart = new PieChart(marcas);
            int porcentajes=porcentaje;
            chart.setTitle("Marcas");
            marcas.forEach(data ->
                    data.nameProperty().bind(Bindings.concat( data.getName(), ": ", data.pieValueProperty().getValue().intValue(), " Equipos"   )) );
            ((Group) scene.getRoot()).getChildren().add(chart);
            return (scene);
        }

    public void h(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }});}
}