package io.github.helloworlde.image.controller;

import io.github.helloworlde.image.health.CustomLivenessProbe;
import io.github.helloworlde.image.health.CustomReadnessProbe;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomController {

    private final CustomLivenessProbe livenessProbe;

    private final CustomReadnessProbe readnessProbe;

    public CustomController(CustomLivenessProbe livenessProbe, CustomReadnessProbe readnessProbe) {
        this.livenessProbe = livenessProbe;
        this.readnessProbe = readnessProbe;
    }

    @RequestMapping("/")
    public Map<String, String> root() {
        return new HashMap<String, String>() {{
            put("message", "hello");
        }};
    }

    @RequestMapping("/liveness")
    public Boolean switchLiveness(boolean live) {
        livenessProbe.setLiveness(live);
        return true;
    }

    @RequestMapping("/readness")
    public Boolean switchReadness(boolean read) {
        readnessProbe.setReadness(read);
        return true;
    }
}
