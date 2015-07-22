package com.github.qrokodial.sparkle.events.component;

import com.github.qrokodial.sparkle.components.SparkleComponent;
import com.github.qrokodial.sparkle.events.Event;

public class EventComponent extends SparkleComponent<Event> {
    /**
     * @return the event this component is attached to
     */
    public Event getEvent() {
        return getHolder().get();
    }
}
