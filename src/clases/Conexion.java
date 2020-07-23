package clases;
import java.sql.*;

public class Conexion {
public static Connection conectar()
{
	try {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_ds","root","");
		return cn;
	} 
	catch (SQLException e) {
		System.out.println("conexion error " + e);
	}
	return (null);
}

}

