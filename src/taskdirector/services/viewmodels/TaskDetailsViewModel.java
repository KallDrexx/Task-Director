
package taskdirector.services.viewmodels;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author KallDrexx
 */
public class TaskDetailsViewModel {
    
    protected UUID id;
    protected String name;
    protected Date dueDate;
    protected double percentCompleted;
    protected String notes;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(double percentCompleted) {
        this.percentCompleted = percentCompleted;
    }
}
