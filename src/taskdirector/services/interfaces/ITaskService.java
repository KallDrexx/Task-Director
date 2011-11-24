
package taskdirector.services.interfaces;

import java.util.*;
import taskdirector.services.viewmodels.*;

/**
 * Interface for interacting with tasks
 * @author KallDrexx
 */
public interface ITaskService {
    
    /**
     * Sets the current user session id for service calls to use
     * @param sessionId 
     */
    void setCurrentSessionId(UUID sessionId);
    
    /**
     * Retrieves a summary of all tasks for a specific user session
     * @return 
     */
    List<TaskSummaryViewModel> getAllTasks();
    
    /**
     * Retrieves the details for the specified task
     * @param taskId Id value of the task to get the details of
     * @return 
     */
    TaskDetailsViewModel getTaskDetails(UUID taskId);
    
    /**
     * Updates the task based on the entered task details
     * @param task 
     */
    void UpdateTaskDetails(TaskDetailsViewModel task);
}
