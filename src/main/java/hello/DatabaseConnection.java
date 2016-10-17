package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {

  private Connection c = null;
  Statement stmt = null;

  public DatabaseConnection() {
    this.safeCreateTable();
  }

  private Connection connect(){
    try {
      Class.forName("org.postgresql.Driver");
      return DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "123");
      
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    return null;
  }

  public void safeCreateTable() {
    try {
      Connection c = connect();
      stmt = c.createStatement();
      String sql = "CREATE TABLE IF NOT EXISTS messages " +
          "(ID      INT PRIMARY KEY NOT NULL," +
          " MESSAGE TEXT            NOT NULL)";
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Table created successfully");
  }

  public void writeMessage(Integer id, String message) {
    try {
      Connection c = connect();
      stmt = c.createStatement();
      String sql = "INSERT INTO messsages VALUES " +
          "(" + id.toString() + ", " +
          message + ")";
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Row added");
  }

  public void readMessage(Integer id) {
    try {
      Connection c = connect();
      stmt = c.createStatement();
      String sql = "SELECT messsage from messages " +
          "WHERE ID = " + id;
      ResultSet rs = stmt.executeQuery(sql);
      while ( rs.next() ) {
        // Read results
      }
      rs.close();
      stmt.close();
      c.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Row added");
  }
}