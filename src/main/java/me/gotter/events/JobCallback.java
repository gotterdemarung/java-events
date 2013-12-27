package me.gotter.events;

// TODO docs
// TODO logic
public interface JobCallback
{
    /**
     * Callback to be called on job finished
     *
     * @param job
     */
    void onJobFinished(Job job);
}
