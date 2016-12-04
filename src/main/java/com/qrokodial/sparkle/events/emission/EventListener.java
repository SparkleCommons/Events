package com.qrokodial.sparkle.events.emission;

import com.qrokodial.sparkle.events.Event;
import com.qrokodial.sparkle.events.EventSubscriber;
import com.qrokodial.sparkle.events.filtering.IgnoreExcept;
import com.qrokodial.sparkle.events.filtering.ListenerFilter;
import com.qrokodial.sparkle.events.util.Priority;
import com.qrokodial.sparkle.utilities.collections.ArrayUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventListener implements Comparable<EventListener> {
    private Object instance;
    private Method method;
    private EventSubscriber subscriber;
    private List<ListenerFilter> filters;

    /**
     * Constructs the class.
     *
     * @param instance
     * @param method
     * @param subscriber
     * @param filters
     */
    public EventListener(Object instance, Method method, EventSubscriber subscriber, ListenerFilter... filters) {
        this.instance = instance;
        this.method = method;
        this.subscriber = subscriber;
        this.filters = Collections.synchronizedList(Arrays.stream(filters).collect(Collectors.toList()));

        if (subscriber.requires().length > 0) {
            this.filters.add(new IgnoreExcept(subscriber.requires()));
        }
    }

    /**
     * @return the priority that this event listener has
     */
    public Priority getPriority() {
        return getSubscriber().priority();
    }

    /**
     * @return the instance of the listener that owns this {@link #getMethod()}
     */
    public Object getInstance() {
        return instance;
    }

    /**
     * @return the method to be invoked when an event is fired
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @return the subscriber annotation attached to this {@link #getMethod()}
     */
    public EventSubscriber getSubscriber() {
        return subscriber;
    }

    /**
     * @return the filters associated with this event listener
     */
    public List<ListenerFilter> getFilters() {
        return Collections.unmodifiableList(filters);
    }

    /**
     * Checks to see if an event should be filtered.
     *
     * @param event the event to check
     * @return true if the event should be filtered, false otherwise
     */
    public boolean shoudFilter(Event event) {
        for (ListenerFilter filter : getFilters()) {
            if (filter.shouldFilter(event)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Fires the method with an event.
     *
     * @param event the event to pass to the method
     */
    public void fire(Event event) {
        try {
            getMethod().invoke(getInstance(), event);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(EventListener otherListener) {
        return getPriority().compareTo(otherListener.getPriority());
    }
}
