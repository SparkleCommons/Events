package com.github.qrokodial.sparkle.events.util;

public interface Cancellable {
    /**
     * @return true if cancelled, false otherwise
     */
    boolean isCancelled();

    /**
     * Sets the cancelled status.
     *
     * @param cancelled
     */
    void setCancelled(boolean cancelled);
}
