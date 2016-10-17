package hello;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final AtomicInteger counter = new AtomicInteger();

  @RequestMapping(value = "/message", method = RequestMethod.GET)
  public Message message(@RequestParam(value = "id") Integer id) {
    // Read to DB here.

    return new Message(id, "blank");
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  public Message message(@RequestParam(value = "message") String message) {
    Integer newId = counter.incrementAndGet();

    // Write to DB here.

    return new Message(newId, message);
  }
}