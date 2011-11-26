package taskdirector.events.listeners;

/**
 * Interface designated for listening for create task events
 * @author KallDrexx
 */
public interface ICreateTaskEventListener {
    
    /**
     * Handles a request to create a task
     * @param taskName Name of the task to create
     */
    void handleCreateTaskEvent(String taskName);
}
