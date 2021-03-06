package com.qrokodial.sparkle.events.component;

import com.qrokodial.sparkle.events.util.Cancellable;

public class CancellableComponent extends EventComponent implements Cancellable {
    private boolean cancelled;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
