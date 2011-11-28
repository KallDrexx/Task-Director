package taskdirector.events.listeners;

import taskdirector.services.viewmodels.TaskDetailsViewModel;

/**
 * Listener for 
 * @author KallDrexx
 */
public interface ITaskDetailsSaveListener {
    
    /**
     * Saves the details for the specified task
     * @param task 
     */
    void SaveTaskDetails(TaskDetailsViewModel task);
}
