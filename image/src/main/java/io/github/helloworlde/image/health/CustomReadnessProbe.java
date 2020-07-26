package io.github.helloworlde.image.health;

import org.springframework.boot.actuate.availability.ReadinessStateHealthIndicator;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component(value = "readinessStateProbeIndicator")
public class CustomReadnessProbe extends ReadinessStateHealthIndicator {

    private final ApplicationEventPublisher publisher;

    public CustomReadnessProbe(ApplicationAvailability availability,
                               ApplicationEventPublisher publisher) {
        super(availability);
        this.publisher = publisher;
    }

    public void setReadness(boolean readness) {
        ReadinessState status = ReadinessState.ACCEPTING_TRAFFIC;
        if (!readness) {
            status = ReadinessState.REFUSING_TRAFFIC;
        }
        AvailabilityChangeEvent.publish(this.publisher, "Custom Readness Status", status);
    }
}
