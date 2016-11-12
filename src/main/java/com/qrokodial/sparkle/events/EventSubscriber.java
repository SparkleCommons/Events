package com.qrokodial.sparkle.events;

import com.qrokodial.sparkle.events.component.EventComponent;
import com.qrokodial.sparkle.events.util.Priority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventSubscriber {
    /**
     * @return the priority of this event subscriber
     */
    Priority priority() default Priority.NORMAL;

    /**
     * @return the event components required to be present for this event subscriber to fire
     */
    Class<? extends EventComponent>[] requires();
}
