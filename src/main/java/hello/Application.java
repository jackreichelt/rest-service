package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application{

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired
  public static JdbcTemplate jdbcTemplate;

  public static void main(String args[]) {
    SpringApplication.run(Application.class, args);
  }

}