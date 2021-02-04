package sql_package;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLiteManager 
{
	private static final String URL = "jdbc:sqlite:MyDB.db";

	/**
	 * This function checks that you can connect to our database
	 * @return
	 */
	private static Connection connect() 
	{
		Connection conn = null;
		try 
		{
			conn = DriverManager.getConnection(URL);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	/**
	 * This function creates a database if we were able 
	 * to connect to the database we defined
	 */
	public static void createNewDatabase()
	{ 
		Connection conn = connect();

		if (conn != null) 
		{
			System.out.println("Connected to: " + URL);
		}
	}
	
	/**
	 * This function creates the Players table
	 */
	public static void createPlayersTable()
	{
		String sql ="CREATE TABLE Players ("
				+ "Name varchar(255),"
				+ "Score varchar(255),"
				+ "Num_games varchar(255)"
				+ ");";

		try 
		{
			Connection conn = connect(); 
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This function enters information into the Players table
	 * @param name
	 * @param score
	 * @param num_games
	 */
	public static void insertPlayerData(String name, String score, String num_games) 
	{
		String sql = "INSERT INTO Players(name, score, num_games) VALUES(?,?,?)";

		try
		{  
			Connection conn = connect(); 

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, score);
			pstmt.setString(3, num_games);

			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This function creates an arrayList of all the information from the table
	 * @return players - an arrayList of all information in the table
	 */
	public static ArrayList<String> selectAllUsers()
	{
		String sql = "SELECT * FROM Players;";
		ArrayList<String> players = new ArrayList<>();
		try 
		{
			Connection conn = connect();

			Statement stmt  = conn.createStatement();
			ResultSet rs    = stmt.executeQuery(sql);

			while (rs.next()) 
			{
				String data = "name: " + rs.getString("Name") + " score: " + rs.getString("Score")
				+ " num_games: " + rs.getString("Num_games");
				System.out.println(data);
				players.add(data);
			}

		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return players;
	}
	
}
