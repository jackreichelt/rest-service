package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.SyncTaskExecutor;

import javax.xml.crypto.Data;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    System.out.println("App starting");
    DatabaseConnection db = new DatabaseConnection();
    
    SpringApplication.run(Application.class, args);
    System.out.println("App started");
  }
}