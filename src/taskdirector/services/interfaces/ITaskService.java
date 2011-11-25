
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
     * @param task Task to update.  If the id value is null, the task is added to the system
     */
    void UpdateTaskDetails(TaskDetailsViewModel task);
    
    /**
     * Creates a new task in the system
     * @param newTask Details of the new task
     * @return ID value for the new task
     */
    UUID CreateTask(NewTaskViewModel newTask);
}
