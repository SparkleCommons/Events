package com.qrokodial.sparkle.events.filtering;

import com.qrokodial.sparkle.events.Event;
import com.qrokodial.sparkle.events.component.EventComponent;

public class IgnoreExcept implements ListenerFilter {
    private Class<? extends EventComponent>[] componentClasses;

    /**
     * Instantiates the class.
     *
     * @param componentClasses
     */
    public IgnoreExcept(Class<? extends EventComponent>... componentClasses) {
        this.componentClasses = componentClasses;
    }

    /**
     * @param event
     * @return true if the event doesn't have one of the listed components attached to it, false if it does
     */
    @Override
    public boolean shouldFilter(Event event) {
        for (Class<? extends EventComponent> componentClass : componentClasses) {
            if (event.hasComponent(componentClass)) {
                return false;
            }
        }

        return true;
    }
}
