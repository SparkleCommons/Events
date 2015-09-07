package com.github.qrokodial.sparkle.events.emission;

import com.github.qrokodial.sparkle.events.Event;
import com.github.qrokodial.sparkle.events.EventListener;
import com.github.qrokodial.sparkle.events.filtering.ListenerFilter;
import com.github.qrokodial.sparkle.events.util.Priority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager implements EventEmitter {
    private List<EventListener> listeners;
    private Map<EventListener, List<ListenerFilter>> filterMap;

    /**
     * Instantiates the class.
     */
    public EventManager() {
        listeners = new ArrayList<>();
        filterMap = new ConcurrentHashMap<>();
    }

    /**
     * Registers event listeners with a given order/priority.
     *
     * @param priority indicates the order/priority in which an event will travel through event listeners
     * @param listeners
     */
    @Override
    public void registerListeners(Priority priority, EventListener... listeners) {
        for (EventListener listener : listeners) {
            registerListener(priority, listener);
        }
    }

    /**
     * Registers event listeners. Defaults to the {@link Priority#NORMAL} priority.
     *
     * @param listeners
     */
    @Override
    public void registerListeners(EventListener... listeners) {
        registerListeners(Priority.NORMAL, listeners);
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
        // Attempt to put it in order. If the list ends before the index, it is safe to just stick it at the end.
        try {
            listeners.add(priority.ordinal(), listener);
        } catch(IndexOutOfBoundsException e) {
            listeners.add(listener);
        }

        if(!filterMap.containsKey(listener)) {
            filterMap.put(listener, new ArrayList<>());
        }

        for(ListenerFilter filter : filters) {
            filterMap.get(listener).add(filter);
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
        registerListener(Priority.NORMAL, listener, filters);
    }

    /**
     * Pushes events into the system. Once in the system, they will be handled by event listeners.
     *
     * @param events
     */
    @Override
    public void pushEvents(Event... events) {
        for(Event event : events) {
            pushEvent(event);
        }
    }

    private void pushEvent(Event event) {
        Collections.unmodifiableList(listeners).stream().filter(filterMap::containsKey).filter(listener -> !isFiltered(listener, event)).forEach(listener -> listener.onEvent(event));
    }

    private boolean isFiltered(EventListener listener, Event event) {
        for(ListenerFilter filter : Collections.unmodifiableList(filterMap.get(listener))) {
            if(filter.shouldFilter(event)) {
                return true;
            }
        }
        return false;
    }
}
