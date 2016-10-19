package hello;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final AtomicInteger counter = new AtomicInteger();

  private static DatabaseConnection dbc = new DatabaseConnection();

  public MessageController() {
    // Get max id from dbc and set counter

  }

  @RequestMapping(value = "/message", method = RequestMethod.GET)
  public Message message(@RequestParam(value = "id") Integer id) {

    // Read from DB.
    return new Message(id, dbc.readMessage(id));
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  public Message message(@RequestParam(value = "message") String message) {
    Integer newId = counter.getAndIncrement();

    // Write to DB.
    dbc.writeMessage(newId, message);

    // Read, to ensure it's correctly written.
    String messageText = dbc.readMessage(newId);
    if (messageText == null) {
      // Ensure no blanks in the database.
      counter.decrementAndGet();
    }

    return new Message(newId, messageText);
  }
}