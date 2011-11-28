package taskdirector.events.listeners;

import java.util.UUID;

/**
 *
 * @author KallDrexx
 */
public interface ITaskDetailsPanelClosePressedListener {
    
    /**
     * Processes the close task event
     * @param taskId 
     */
    void CloseTaskDetailsPane(UUID taskId);
}
