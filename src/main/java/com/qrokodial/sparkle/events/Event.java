package com.qrokodial.sparkle.events;

import com.qrokodial.sparkle.components.SparkleComponentHolder;

import java.util.concurrent.ConcurrentHashMap;

public class Event extends SparkleComponentHolder {
    /**
     * Instantiates the class.
     */
    public Event() {
        super(new ConcurrentHashMap<>());
    }
}
