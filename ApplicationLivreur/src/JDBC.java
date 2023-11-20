import java.sql.*;

public class JDBC {

	public static Connection openConnection (String url) {
		Connection co=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			co= DriverManager.getConnection(url);
			System.out.println("Connexion Ouverte !");
		}
		catch (ClassNotFoundException e){
			System.out.println("il manque le driver oracle");
			System.exit(1);
		}
		catch (SQLException e) {
			System.out.println("impossible de se connecter à l'url : "+url);
			System.exit(1);
		}
		return co;
	}

	public static ResultSet exec1Requete (String requete, Connection co, int type){
		ResultSet res=null;
		try {
			Statement st;
			if (type==0){
				st=co.createStatement();}
			else {
				st=co.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
			}
			res= st.executeQuery(requete);
		}
		catch (SQLException e){
			System.out.println("Problème lors de l'exécution de la requete : "+requete);
		}
		return res;
	}

	public static void closeConnection(Connection co){
		try {
			co.close();
			System.out.println("Connexion fermée!");
	}
		catch (SQLException e) {
			System.out.println("Impossible de fermer la connexion");
		}
	}

	//Récupération des données de la base données
	public static void main(String[] args) throws SQLException{
		//url de la base de données à laquelle on souhaite ce connecter
		String url= "jdbc:oracle:thin:vstadle/20041226@oracle.iut-orsay.fr:1521:etudom";

		Connection co = JDBC.openConnection(url);
		closeConnection(co);
	}
}