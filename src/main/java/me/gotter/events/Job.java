package me.gotter.events;

// TODO docs
// TODO logic
public interface Job extends Event
{
    /**
     * Acknowledge finished job
     */
    void ack();

    /**
     * Return job back
     */
    void nack();
}
