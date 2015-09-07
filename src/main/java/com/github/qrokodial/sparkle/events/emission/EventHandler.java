package com.github.qrokodial.sparkle.events.emission;

import com.github.qrokodial.sparkle.events.Event;
import com.github.qrokodial.sparkle.events.EventListener;
import com.github.qrokodial.sparkle.events.filtering.ListenerFilter;
import com.github.qrokodial.sparkle.events.util.Priority;

import java.util.Optional;

public class EventHandler implements EventEmitter {
    private Optional<EventManager> manager;

    /**
     * Instantiates the class.
     *
     * @param manager|null
     */
    public EventHandler(EventManager manager) {
        setManager(manager);
    }

    /**
     * Instantiates the class using a generic {@link EventManager}.
     */
    public EventHandler() {
        this(new EventManager());
    }

    /**
     * @return the event manager
     */
    public Optional<EventManager> getManager() {
        return manager;
    }

    /**
     * Sets the event manager.
     *
     * @param manager
     */
    public void setManager(EventManager manager) {
        this.manager = Optional.ofNullable(manager);
    }

    /**
     * Registers event listeners with a given order/priority.
     *
     * @param priority  indicates the order/priority in which an event will travel through event listeners
     * @param listeners
     */
    @Override
    public void registerListeners(Priority priority, EventListener... listeners) {
        if (manager.isPresent()) {
            manager.get().registerListeners(priority, listeners);
        }
    }

    /**
     * Registers event listeners. Defaults to the {@link Priority#NORMAL} priority.
     *
     * @param listeners
     */
    @Override
    public void registerListeners(EventListener... listeners) {
        if (manager.isPresent()) {
            manager.get().registerListeners(listeners);
        }
    }

    /**
     * Registers an event listener and associates filters with it.
     *
     * @param priority
     * @param listener
     * @param filters
     */
    @Override
    public void registerListener(Priority priority, EventListener listener, ListenerFilter... filters) {
        if (manager.isPresent()) {
            manager.get().registerListener(priority, listener, filters);
        }
    }

    /**
     * Registers an event listener and associates filters with it. Defaults to the {@link Priority#NORMAL} priority.
     *
     * @param listener
     * @param filters
     */
    @Override
    public void registerListener(EventListener listener, ListenerFilter... filters) {
        if (manager.isPresent()) {
            manager.get().registerListener(listener, filters);
        }
    }

    /**
     * Unregisters an event listener.
     *
     * @param listener
     */
    @Override
    public void unregisterListener(EventListener listener) {
        if (manager.isPresent()) {
            manager.get().unregisterListener(listener);
        }
    }

    /**
     * Pushes events into the system. Once in the system, they will be handled by event listeners.
     *
     * @param events
     */
    @Override
    public void pushEvents(Event... events) {
        if (manager.isPresent()) {
            manager.get().pushEvents(events);
        }
    }
}
