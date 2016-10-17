package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

  private Connection c = null;
  Statement stmt = null;

  public DatabaseConnection() {
    // Open the DB connection
    try {
      Class.forName("org.postgresql.Driver");
      c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "123");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Opened database successfully");

    this.safeCreateTable();
  }

  public void safeCreateTable() {
    try {
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
      stmt = c.createStatement();
      String sql = "INSERT INTO messsages";
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
}