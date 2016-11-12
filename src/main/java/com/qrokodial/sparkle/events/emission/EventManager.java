package com.qrokodial.sparkle.events.emission;

import com.qrokodial.sparkle.events.Event;
import com.qrokodial.sparkle.events.EventSubscriber;
import com.qrokodial.sparkle.events.filtering.ListenerFilter;
import com.qrokodial.sparkle.utilities.reflection.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class EventManager {
    private List<EventListener> listeners;

    /**
     * Instantiates the class.
     */
    public EventManager() {
        listeners = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Registers event listeners.
     *
     * @param listeners
     */
    public void registerListeners(Object... listeners) {
        for (Object listener : listeners) {
            registerListener(listener);
        }
    }

    /**
     * Registers an event listener and associates filters with it.
     *
     * @param listener
     * @param filters
     */
    public void registerListener(Object listener, ListenerFilter... filters) {
        List<Method> methods = ReflectionUtils.getMethodsWithAnnotation(listener.getClass(), EventSubscriber.class);

        listeners.addAll(methods.stream().map(method -> new EventListener(listener, method, method.getAnnotation(EventSubscriber.class), filters)).collect(Collectors.toList()));

        Collections.sort(listeners);
    }

    /**
     * Unregisters event listeners.
     *
     * @param listeners
     */
    public void unregisterListeners(Object... listeners) {
        for (Object listener : listeners) {
            unregisterListener(listener);
        }
    }

    /**
     * Unregisters an event listener.
     *
     * @param listener
     */
    public void unregisterListener(Object listener) {
        List<Method> methods = ReflectionUtils.getMethodsWithAnnotation(listener.getClass(), EventSubscriber.class);

        for (Method method : methods) {
            Iterator<EventListener> iterator = listeners.iterator();

            while (iterator.hasNext()) {
                EventListener element = iterator.next();

                if (element.getMethod().equals(method)) {
                    iterator.remove();
                }
            }
        }
    }

    /**
     * Pushes events into the system. Once in the system, they will be handled by event listeners.
     *
     * @param events
     */
    public void pushEvents(Event... events) {
        for(Event event : events) {
            pushEvent(event);
        }
    }

    private void pushEvent(Event event) {
        listeners.stream().filter(listener -> !listener.shoudFilter(event)).forEach(listener -> listener.fire(event));
    }
}
