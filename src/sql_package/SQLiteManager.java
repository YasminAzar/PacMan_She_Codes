package sql_package;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SQLiteManager 
{
	private static final String URL = "jdbc:sqlite:MyDB.db";

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

	public static void createNewDatabase()
	{ 
		Connection conn = connect();

		if (conn != null) 
		{
			System.out.println("Connected to: " + URL);
		}
	}

	public static void createUsersTable()
	{
		String sql ="CREATE TABLE Users ("
				+ "UserID int,"
				+ "Name varchar(255),"
				+ "Email varchar(255)"
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

	public static void insert(int userID, String name, String email) 
	{
		String sql = "INSERT INTO Users(userID, Name, Email) VALUES(?,?,?)";

		try
		{  
			Connection conn = connect(); 

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userID);
			pstmt.setString(2, name);
			pstmt.setString(3, email);

			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

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
	public static void delete(int userID) 
	{
		String sql = "DELETE FROM Users WHERE UserID=?;";

		try
		{
			Connection conn = connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, userID);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

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
			
			//arraylist
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return players;
	}

	public static void update(String name, String email)
	{
		String sql = "UPDATE Users SET Email = ? WHERE Name = ?;";

		try
		{	
			Connection conn = connect();

			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, email);
			pstmt.setString(2, name);

			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updatePicture(int userID, String filePath)
	{
		String updateSQL = "UPDATE Users SET img = ? WHERE UserID = ?";

		try {
			Connection conn = connect();

			PreparedStatement pstmt = conn.prepareStatement(updateSQL);

			pstmt.setBytes(1, readFile(filePath));
			pstmt.setInt(2, userID);

			pstmt.executeUpdate();
			System.out.println("Stored the file in the BLOB column.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static byte[] readFile(String file) 
	{
		ByteArrayOutputStream bos = null;
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}


}
