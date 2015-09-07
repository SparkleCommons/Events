package com.github.qrokodial.sparkle.events.util;

public enum Priority {
    /**
     * For core functionality. Do not use unless you know exactly what you're doing. If unsure, use {@link #HIGHEST}.
     */
    REALTIME(3),

    /**
     * For things requiring the highest priority.
     */
    HIGHEST(2),

    /**
     * For things requiring higher priority.
     */
    HIGH(1),

    /**
     * The default priority. If unsure, use this.
     */
    NORMAL(0),

    /**
     * For things of low priority.
     */
    LOW(-1),

    /**
     * For things of the lowest, non-monitoring priority. If you need to modify data, use this; Otherwise use {@link #MONITOR}.
     */
    LOWEST(-2),

    /**
     * For things that need to monitor, but not modify.
     */
    MONITOR(-3);

    private int id;

    Priority(int id) {
        this.id = id;
    }

    /**
     * Gets the inverse of the priority. {@link #HIGHEST} becomes {@link #LOWEST}, {@link #LOWEST} becomes
     * {@link #HIGHEST}, and so on.
     *
     * @return the inverse of the priority
     */
    public Priority inverse() {
        int id = -this.id;
        for (Priority priority : Priority.values()) {
            if (priority.id == id) {
                return priority;
            }
        }
        return null;
    }
}
