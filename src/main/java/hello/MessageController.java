package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  private final AtomicLong counter = new AtomicLong();

  @RequestMapping(value = "/message", method = RequestMethod.GET)
  public Message message(@RequestParam(value = "id") Long id) {


    return new Message(id, "blank");
  }

  @RequestMapping(value = "/message", method = RequestMethod.POST)
  public Message message(@RequestParam(value = "message") String message) {

    Long newId = counter.incrementAndGet();

    return new Message(newId, message);
  }
}