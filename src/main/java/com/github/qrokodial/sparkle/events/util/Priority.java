package com.github.qrokodial.sparkle.events.util;

public enum Priority {
    /**
     * For core functionality. Do not use unless you know exactly what you're doing. If unsure, use {@link #HIGHEST}.
     */
    REALTIME,

    /**
     * For things requiring the highest priority.
     */
    HIGHEST,

    /**
     * For things requiring higher priority.
     */
    HIGH,

    /**
     * The default priority. If unsure, use this.
     */
    NORMAL,

    /**
     * For things of low priority.
     */
    LOW,

    /**
     * For things of the lowest, non-monitoring priority. If you need to modify data, use this; Otherwise use {@link #MONITOR}.
     */
    LOWEST,

    /**
     * For things that need to monitor, but not modify.
     */
    MONITOR,
}
