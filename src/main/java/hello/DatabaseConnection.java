package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseConnection {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

//  JdbcTemplate jdbcTemplate;

  public DatabaseConnection () {
    log.info("Creating table");

    String sql = "CREATE TABLE IF NOT EXISTS messages " +
        "(ID      INT PRIMARY KEY NOT NULL," +
        " MESSAGE TEXT            NOT NULL)";

    if (Application.jdbcTemplate == null) {
      log.error("!!jdbcTemplate is null!!");
    } else {
      Application.jdbcTemplate.execute(sql);
    }
  }

  public void writeMessage(Integer id, String message) {

    log.info("Inserting new message");
    log.info("  id     : " + id.toString());
    log.info("  message: " + message);

    String sql = "INSERT INTO messsages VALUES " +
        "(" + id.toString() + ", " +
        message + ")";

    if (Application.jdbcTemplate == null) {
      log.error("!!jdbcTemplate is null!!");
    } else {
      Application.jdbcTemplate.update(sql);
    }

    System.out.println("Row added");
  }

  public String readMessage(Integer id) {

    String sql = "SELECT messsage FROM messages " +
        "WHERE ID = ?";

    if (Application.jdbcTemplate == null) {
      log.error("!!jdbcTemplate is null!!");
      return null;
    } else {
      String message = Application.jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
      log.info("Row read");
      return message;
    }

  }
}