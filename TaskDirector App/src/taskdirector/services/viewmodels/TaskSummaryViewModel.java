
package taskdirector.services.viewmodels;

import java.util.*;

/**
 * Contains the summary of a task
 * @author KallDrexx
 */
public class TaskSummaryViewModel {
    protected UUID id;
    protected String name;
    protected boolean isCompleted;
    
    @Override
    public String toString()
    {
        return name;
    }
    
    public void setId(UUID id)
    {
        this.id = id;
    }
    
    public UUID getId()
    {
        return id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setIsCompleted(boolean completed)
    {
        this.isCompleted = completed;
    }
    
    public boolean getIsCompleted()
    {
        return isCompleted;
    }
}
