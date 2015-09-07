package com.github.qrokodial.sparkle.events.emission;

import com.github.qrokodial.sparkle.events.Event;
import com.github.qrokodial.sparkle.events.EventListener;
import com.github.qrokodial.sparkle.events.filtering.ListenerFilter;
import com.github.qrokodial.sparkle.events.util.Priority;

public interface EventEmitter {
    /**
     * Registers event listeners with a given order/priority.
     *
     * @param priority indicates the order/priority in which an event will travel through event listeners
     * @param listeners
     */
    void registerListeners(Priority priority, EventListener... listeners);

    /**
     * Registers event listeners. Defaults to the {@link Priority#NORMAL} priority.
     *
     * @param listeners
     */
    void registerListeners(EventListener... listeners);

    /**
     * Registers an event listener and associates filters with it.
     *
     * @param priority
     * @param listener
     * @param filters
     */
    void registerListener(Priority priority, EventListener listener, ListenerFilter... filters);

    /**
     * Registers an event listener and associates filters with it. Defaults to the {@link Priority#NORMAL} priority.
     *
     * @param listener
     * @param filters
     */
    void registerListener(EventListener listener, ListenerFilter... filters);

    /**
     * Unregisters an event listener.
     *
     * @param listener
     */
    void unregisterListener(EventListener listener);

    /**
     * Pushes events into the system. Once in the system, they will be handled by event listeners.
     *
     * @param events
     */
    void pushEvents(Event... events);
}
