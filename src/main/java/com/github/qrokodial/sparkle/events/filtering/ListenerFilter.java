package com.github.qrokodial.sparkle.events.filtering;

import com.github.qrokodial.sparkle.events.Event;

public interface ListenerFilter {
    /**
     * @param event
     * @return true if the given event should be filtered, false otherwise
     */
    boolean shouldFilter(Event event);
}
