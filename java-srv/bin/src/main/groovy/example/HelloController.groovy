package example;

import org.springframework.web.bind.annotation.*;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final MeterRegistry registry;

    /**
     * We inject the MeterRegistry into this class
     */
    public HelloController(MeterRegistry registry) {
        this.registry = registry;
    }

    @GetMapping(value="/")
    @Timed(value = "greeting.time"
    , description = "Time taken to return greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
			counter.incrementAndGet();
      return String.format(template, name);
    }

}
