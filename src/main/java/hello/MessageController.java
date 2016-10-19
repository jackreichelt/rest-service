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

  @RequestMapping(value = "/message", method = RequestMethod.GET)
  public Message message(@RequestParam(value = "id") Integer id) {

    // Read from DB.
    return new Message(id, dbc.readMessage(id));
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  public Message message(@RequestParam(value = "message") String message) {
    Integer newId = counter.incrementAndGet();

    // Write to DB.
    dbc.writeMessage(newId, message);

    return new Message(newId, message);
  }
}