package taskdirector.events.listeners;

import java.util.UUID;
import taskdirector.services.viewmodels.TaskDetailsViewModel;

/**
 * Interface for classes that listen to requests for task details
 * @author KallDrexx
 */
public interface ITaskDetailsRequestedListener {
    
    /**
     * Retrieves the details for the specified task
     * @param taskId Id value for the task to get the details of
     * @return 
     */
    TaskDetailsViewModel getTaskDetails(UUID taskId);
}
