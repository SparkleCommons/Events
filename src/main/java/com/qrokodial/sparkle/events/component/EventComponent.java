package com.qrokodial.sparkle.events.component;

import com.qrokodial.sparkle.components.SparkleComponent;
import com.qrokodial.sparkle.events.Event;

public class EventComponent extends SparkleComponent<Event> {
    /**
     * @return the event this component is attached to
     */
    public Event getEvent() {
        return getHolder().get();
    }
}
