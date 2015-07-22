package com.github.qrokodial.sparkle.events.filtering;

import com.github.qrokodial.sparkle.events.Event;
import com.github.qrokodial.sparkle.events.component.CancellableComponent;

public class IgnoreCancelled implements ListenerFilter {
    /**
     * @param event
     * @return returns true if the event has been cancelled, or false otherwise
     */
    @Override
    public boolean shouldFilter(Event event) {
        return event.hasComponent(CancellableComponent.class) && event.getComponent(CancellableComponent.class).get().isCancelled();
    }
}
