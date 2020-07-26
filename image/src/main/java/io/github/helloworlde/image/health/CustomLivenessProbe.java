package io.github.helloworlde.image.health;

import org.springframework.boot.actuate.availability.LivenessStateHealthIndicator;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component(value = "livenessStateProbeIndicator")
public class CustomLivenessProbe extends LivenessStateHealthIndicator {

    private final ApplicationEventPublisher publisher;

    public CustomLivenessProbe(ApplicationAvailability availability,
                               ApplicationEventPublisher publisher) {
        super(availability);
        this.publisher = publisher;
    }

    public void setLiveness(boolean liveness) {
        LivenessState status = LivenessState.CORRECT;
        if (!liveness) {
            status = LivenessState.BROKEN;
        }
        AvailabilityChangeEvent.publish(this.publisher, "Custom Liveness Status", status);
    }
}
