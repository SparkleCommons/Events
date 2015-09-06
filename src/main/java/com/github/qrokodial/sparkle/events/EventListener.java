package com.github.qrokodial.sparkle.events;

import com.github.qrokodial.sparkle.events.Event;

public interface EventListener {
    /**
     * Called when an event is fired that doesn't break the filtering rules that the event listener was created with.
     *
     * @param event
     */
    void onEvent(Event event);
}
